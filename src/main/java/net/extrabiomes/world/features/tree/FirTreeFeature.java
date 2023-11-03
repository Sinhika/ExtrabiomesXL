package net.extrabiomes.world.features.tree;

import com.mojang.serialization.Codec;
import net.extrabiomes.world.features.configuration.EBTreeConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.TreeFeature;

public class FirTreeFeature extends EBBaseTreeFeature
{
    int CANOPY_RADIUS;
    int MIN_LEAF_HEIGHT;

    public FirTreeFeature(Codec<EBTreeConfiguration> pCodec)
    {
        super(pCodec);
    }

    /**
     * Places the given feature at the given location.
     * During world generation, features are provided with a 3x3 region of chunks, centered on the chunk being generated,
     * that they can safely generate into.
     *
     * @param pContext A context object with a reference to the level and the position the feature is being placed at
     */
    @Override public boolean place(FeaturePlaceContext<EBTreeConfiguration> pContext)
    {
        boolean place = super.place(pContext);
        BlockPos pos = pContext.origin().immutable();
        int max_tree_altitude = pos.getY() + actual_height + 1;

        // height check
        if (pos.getY() < level.getMinBuildHeight() + 1 || max_tree_altitude > level.getMaxBuildHeight())
        {
            return false;
        }

        // min_leaf_height - was 1+rand(12)
        MIN_LEAF_HEIGHT = treeConfig.canopy_start_height + sourceRand.nextInt(treeConfig.canopy_start_variance);
        // canopy_radius - was 2+rand(6)
        CANOPY_RADIUS = Math.min((treeConfig.canopy_width + sourceRand.nextInt(treeConfig.canopy_width_variance)), 6);

        // make sure space for tree is clear or replaceable.
        BlockPos.MutableBlockPos checkpos = pos.mutable();
        for (int i1 = pos.getY(); i1 <= max_tree_altitude; i1++)
        {
            int k1;  // current canopy radius
            if (i1 - pos.getY() < MIN_LEAF_HEIGHT) {
                k1 = 0;
            }
            else {
                k1 = CANOPY_RADIUS;
            }

            for (int x1 = pos.getX() - k1; x1 <= pos.getX() + k1; x1++)
            {
                for (int z1 = pos.getZ() - k1; z1 <= pos.getZ() + k1; z1++)
                {
                    //final Block block = world.getBlock(x1, i1, z1);
                    checkpos.set(x1,i1,z1);
                    if (!TreeFeature.validTreePos(level, checkpos)) {
                        return false;
                    }
                } // end-for z1
            } // end-for x1
        } // end for i1

        // START PLACING BLOCKS
        // place the 'dirt' block.
        this.setBlock(level, pos.below(), treeConfig.dirt_provider.getState(sourceRand, pos.below()));

        // stick some leaves up.
        generateCanopy(level, sourceRand, pos, actual_height - MIN_LEAF_HEIGHT, CANOPY_RADIUS,
                        treeConfig.foliage_provider.getState(sourceRand, pos));

        // TODO: what are these variables?
        final int j3 = sourceRand.nextInt(3); // TODO: do these constants need to be config VARIABLES?

        // place trunk blocks.
        generateBranches(level, sourceRand, pos, actual_height - j3, 0.0);

        return true;
    } // end place

    /**
     * Actually place the branches.
     *
     * @param world     current LevalAccessor
     * @param rand      current RandomSource
     * @param pos       BlockPos of tree origin
     * @param height    canopy height  = actual_height - MIN_LEAF_HEIGHT
     * @param radius    canopy radius
     */
    @Override public boolean generateBranches(LevelAccessor world, RandomSource rand, BlockPos pos, int height,
                                              double radius)
    {

        // place trunk blocks.
        BlockPos.MutableBlockPos placePos = pos.mutable();
        placePos.set(pos.getX(), pos.getY(), pos.getZ());

        for (int l3 = 0; l3 < height; l3++)
        {
            // final Block block = world.getBlock(x, y + l3, z);
            placePos.setY(pos.getY()+l3);

            //if (block == null || block.isLeaves(world, x, y + l3, z) || block.isAir(world, x, y + l3, z) )
            if (TreeFeature.validTreePos(level, placePos))
            {
                this.setBlock(world, placePos, treeConfig.trunk_provider.getState(sourceRand, placePos));
            }
        } // end-for l3

        return true;
    } // end generateBranches()

    /**
     * Generate the tree's canopy.
     *
     * @param world  LevelAccessor
     * @param rand   a RandomSource
     * @param pos    BlockPos of center of canopy
     * @param radius canopy radius
     * @param height canopy height
     * @param leaves leaf BlockState
     */
    @Override public void generateCanopy(LevelAccessor world, RandomSource rand, BlockPos pos, double radius,
                                         int height, BlockState leaves)
    {
        BlockPos.MutableBlockPos branchpos = pos.mutable();

        // num block below treetop that trunk stops.
        int l1 = sourceRand.nextInt(2);   // TODO: do these constants need to be config VARIABLES?
        // TODO: what are these variables?
        int j2 = 1;
        boolean flag1 = false;

        // stick some leaves up.
        for (int i3 = 0; i3 <= height; i3++)
        {
            final int k3 = pos.getY() + actual_height - i3;

            for (int i4 = pos.getX() - l1; i4 <= pos.getX() + l1; i4++)
            {
                final int k4 = i4 - pos.getX();

                for (int l4 = pos.getZ() - l1; l4 <= pos.getZ() + l1; l4++)
                {
                    final int i5 = l4 - pos.getZ();
                    branchpos.set(i4, k3, l4);
                    if ((Math.abs(k4) != l1 || Math.abs(i5) != l1 || l1 <= 0)
                            && (TreeFeature.isAirOrLeaves(world, branchpos)))
                    {
                        this.setBlock(world, branchpos, leaves);
                    }
                }  // end-for l4
            } // end-for i4

            if (l1 >= j2)
            {
                l1 = flag1 ? 1 : 0;
                flag1 = true;

                if (++j2 > radius)
                    j2 = (int) radius;
            }
            else
            {
                l1++;
            }
        } // end-for i3
    } // end generateCanopy()

} // end class
