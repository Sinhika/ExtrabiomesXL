package net.extrabiomes.world.features.tree;

import com.mojang.serialization.Codec;
import net.extrabiomes.world.features.configuration.EBTreeConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.TreeFeature;

public class FirTreeFeature extends EBBaseTreeFeature
{
    protected int min_leaf_height;

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
        BlockPos pos = pContext.origin().immutable();

        int max_tree_altitude = pos.getY() + this.actual_height + 1;

        // height check
        if (pos.getY() < level.getMinBuildHeight() + 1 || max_tree_altitude > level.getMaxBuildHeight())
        {
            return false;
        }

        // min_leaf_height - was 1+rand(12)
        this.min_leaf_height = treeConfig.canopy_start_height + sourceRand.nextInt(treeConfig.canopy_start_variance);
        // canopy_radius - was 2+rand(6)
        this.actual_radius = Math.min((CANOPY_WIDTH + sourceRand.nextInt(CANOPY_WIDTH_VARIANCE)), 6);

        // make sure space for tree is clear or replaceable.
        BlockPos.MutableBlockPos checkpos = pos.mutable();
        for (int i1 = pos.getY(); i1 <= max_tree_altitude; i1++)
        {
            int k1;  // current canopy radius
            if (i1 - pos.getY() < min_leaf_height) {
                k1 = 0;
            }
            else {
                k1 = (int) actual_radius;
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
        BlockPos.MutableBlockPos placePos = pos.mutable();

        // place the 'dirt' block.
        placePos.move(Direction.DOWN);
        level.setBlock(placePos, treeConfig.dirt_provider.getState(sourceRand, placePos), 3);

        generateCanopy(this.level, this.sourceRand, placePos, this.actual_radius, this.actual_height,
                        treeConfig.foliage_provider.getState(sourceRand, placePos));

        // TODO: what are these variables?
        final int j3 = sourceRand.nextInt(3); // TODO: do these constants need to be config VARIABLES?

        // place trunk blocks.
        placePos.set(pos.getX(), pos.getY(), pos.getZ());

        for (int l3 = 0; l3 < actual_height - j3; l3++)
        {
            // final Block block = world.getBlock(x, y + l3, z);
            placePos.setY(pos.getY()+l3);

            //if (block == null || block.isLeaves(world, x, y + l3, z) || block.isAir(world, x, y + l3, z) )
            if (TreeFeature.validTreePos(level, placePos))
            {
                level.setBlock(placePos, treeConfig.trunk_provider.getState(sourceRand, placePos), 3);
                this.posTrunks.add(placePos.immutable());
            }
        }
        placeDecorators();
        return true;
    } // end place

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
        BlockPos.MutableBlockPos leafPos = pos.mutable();
        
        // num block below treetop that trunk stops.
        int l1 = sourceRand.nextInt(2);   // TODO: do these constants need to be config VARIABLES?
        // TODO: what are these variables?
        int j2 = 1;
        boolean flag1 = false;

        // stick some leaves up.
        for (int i3 = 0; i3 <= height - this.min_leaf_height; i3++)
        {
            final int k3 = leafPos.getY() + height - i3;

            for (int i4 = leafPos.getX() - l1; i4 <= leafPos.getX() + l1; i4++)
            {
                final int k4 = i4 - leafPos.getX();

                for (int l4 = leafPos.getZ() - l1; l4 <= leafPos.getZ() + l1; l4++)
                {
                    final int i5 = l4 - leafPos.getZ();
                    leafPos.set(i4, k3, l4);
                    if ((Math.abs(k4) != l1 || Math.abs(i5) != l1 || l1 <= 0)
                            && (TreeFeature.isAirOrLeaves(level, leafPos)))
                    {
                        level.setBlock(leafPos, treeConfig.foliage_provider.getState(sourceRand, leafPos), 3);
                        this.posLeaves.add(leafPos.immutable());
                    }
                }  // end-for l4
            } // end-for i4

            if (l1 >= j2)
            {
                l1 = flag1 ? 1 : 0;
                flag1 = true;

                if (++j2 > actual_radius)
                    j2 = (int) actual_radius;
            }
            else
            {
                l1++;
            }
        } // end-for i3

    } // end generateCanopy()

    /**
     * Actually place the branches.
     *
     * @param world     current LevalAccessor
     * @param rand      current RandomSource
     * @param branchpos Starting position of branches
     * @param height    canopy height
     * @param radius    canopy radius
     * @return true if successfully placed, false if not.
     */
    @Override public boolean generateBranches(LevelAccessor world, RandomSource rand, BlockPos branchpos, int height,
                                              double radius)
    {
        return false;
    }


} // end class
