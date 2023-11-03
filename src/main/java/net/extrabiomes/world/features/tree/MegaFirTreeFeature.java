package net.extrabiomes.world.features.tree;

import com.mojang.serialization.Codec;
import net.extrabiomes.world.features.configuration.EBTreeConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.TreeFeature;

public class MegaFirTreeFeature extends EBBaseTreeFeature
{
    public MegaFirTreeFeature(Codec<EBTreeConfiguration> pCodec)
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

        // probably min_leaf_height - was 1 + rand(12)
        final int min_leaf_height = treeConfig.canopy_start_height + sourceRand.nextInt(treeConfig.canopy_start_variance);
        // probably depth of canopy
        final int canopy_depth = actual_height - min_leaf_height;
        // canopy radius -- originally 2+rand(9). Clamp to total 6.
        final int canopy_radius = Math.min((treeConfig.canopy_width + sourceRand.nextInt(treeConfig.canopy_width_variance)), 6);

        // make sure space for tree is clear or replaceable.
        for (int y1 = pos.getY(); y1 <= max_tree_altitude; ++y1)
        {
            int k1;
            if (y1 - pos.getY() < min_leaf_height) {
                k1 = 0;
            }
            else {
                k1 = canopy_radius;
            }
            for (int x1 = pos.getX() - k1; x1 <= pos.getX() + k1; x1++)
            {
                for (int z1 = pos.getZ() - k1; z1 <= pos.getZ() + k1; z1++)
                {
                    BlockPos newpos = new BlockPos(x1,y1,z1);
                    if (!TreeFeature.validTreePos(level, newpos)) {
                        return false;
                    }
                } // end-for z1
            } // end-for x1
        } // end-for y1

        // START PLACING BLOCKS
        BlockPos.MutableBlockPos placePos = pos.mutable();
        BlockState dirt = treeConfig.dirt_provider.getState(sourceRand, placePos);

        // place the 'dirt' block.
        placePos.move(Direction.DOWN);
        this.setBlock(level, placePos, dirt);
        placePos.move(Direction.NORTH);
        this.setBlock(level, placePos, dirt);
        placePos.move(Direction.WEST);
        this.setBlock(level, placePos, dirt);
        placePos.move(Direction.SOUTH);
        this.setBlock(level, placePos, dirt);

        // place leaf blocks
        BlockState bsLeaves = treeConfig.foliage_provider.getState(sourceRand, pos);
        generateCanopy(level, sourceRand, pos, canopy_radius, canopy_depth, bsLeaves);

        // place trunk blocks
        final int j3 = sourceRand.nextInt(3);
        generateBranches(level, sourceRand, pos, actual_height - j3, 0.0D);

        return true;
    } // end place()

    /**
     * Actually place the branches.
     *
     * @param world     current LevalAccessor
     * @param rand      current RandomSource
     * @param pos Starting position of branches
     * @param height    canopy height
     * @param radius    canopy radius
     */
    @Override public boolean generateBranches(LevelAccessor world, RandomSource rand, BlockPos pos, int height,
                                              double radius)
    {
        for (int l3 = 0; l3 < height; l3++)
        {
            //final Block block = world.getBlock(x, y + l3, z);
            BlockPos se_pos = new BlockPos(pos.getX(), pos.getY() + l3, pos.getZ());

            if (TreeFeature.validTreePos(level, se_pos))
            {
                BlockPos sw_pos = new BlockPos( pos.getX() - 1, pos.getY() + l3, pos.getZ());
                BlockPos ne_pos = new BlockPos(pos.getX(), pos.getY() + l3, pos.getZ() - 1);
                BlockPos nw_pos = new BlockPos(pos.getX() - 1, pos.getY() + l3, pos.getZ() - 1);

                // TODO revise for quarter blocks
                this.setBlock(level, se_pos, treeConfig.trunk_provider.getState(sourceRand, se_pos));
                this.setBlock(level,sw_pos, treeConfig.trunk_provider.getState(sourceRand, sw_pos));
                this.setBlock(level,ne_pos, treeConfig.trunk_provider.getState(sourceRand, ne_pos));
                this.setBlock(level,nw_pos, treeConfig.trunk_provider.getState(sourceRand, nw_pos));
            } // end-if
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
     * @return true if successful, false if not.
     */
    @Override public void generateCanopy(LevelAccessor world, RandomSource rand, BlockPos pos, double radius,
                                         int height, BlockState leaves)
    {
        int l1 = sourceRand.nextInt(2);  // TODO: do these constants need to be config VARIABLES?
        int j2 = 1;
        boolean flag1 = false;
        BlockPos.MutableBlockPos newpos = new BlockPos.MutableBlockPos();

        // place leaves.
        for (int i3 = 0; i3 <= height; i3++)
        {
            final int k3 = pos.getY() + actual_height - i3;
            for (int i4 = pos.getX() - l1; i4 <= pos.getX() + l1; i4++)
            {
                final int k4 = i4 - pos.getX();
                for (int l4 = pos.getZ() - l1; l4 <= pos.getZ() + l1; l4++)
                {
                    final int i5 = l4 - pos.getZ();
                    if (Math.abs(k4) != l1 || Math.abs(i5) != l1 || l1 <= 0)
                    {
                        newpos.set(i4, k3, l4);
                        if (TreeFeature.validTreePos(level, newpos))
                        {
                            this.setBlock(level, newpos, leaves);
                        }
                        newpos.set(i4 - 1, k3, l4);
                        if (TreeFeature.validTreePos(level, newpos))
                        {
                            this.setBlock(level, newpos, leaves);
                        }

                        newpos.set(i4, k3, l4 - 1);
                        if (TreeFeature.validTreePos(level, newpos))
                        {
                            this.setBlock(level, newpos, leaves);
                        }

                        newpos.set(i4 - 1, k3, l4 - 1);
                        if (TreeFeature.validTreePos(level, newpos))
                        {
                            this.setBlock(level, newpos, leaves);
                        }
                    } // end-if
                } // end-for l4
            } // end-for i4

            if (l1 >= j2)
            {
                l1 = flag1 ? 1 : 0;
                flag1 = true;

                if (++j2 > radius)
                {
                    j2 = (int) radius;
                }
            }
            else
            {
                l1++;
            }
        } // end-for i3

    } // end generateCanopy

} // end-class
