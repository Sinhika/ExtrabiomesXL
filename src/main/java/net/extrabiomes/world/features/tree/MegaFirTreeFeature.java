package net.extrabiomes.world.features.tree;

import com.mojang.serialization.Codec;
import net.extrabiomes.world.features.configuration.EBTreeConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.TreeFeature;

public class MegaFirTreeFeature extends Feature<EBTreeConfiguration>
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
        EBTreeConfiguration treeConfig = pContext.config();
        BlockPos pos = pContext.origin().immutable();
        RandomSource sourceRand = pContext.random();
        WorldGenLevel level = pContext.level();

        final int height = treeConfig.base_height + sourceRand.nextInt(treeConfig.base_height_variance);
        int max_tree_altitude = pos.getY() + height + 1;

        // height check
        if (pos.getY() < level.getMinBuildHeight() + 1 || max_tree_altitude > level.getMaxBuildHeight())
        {
            return false;
        }

        // probably min_leaf_height - was 1 + rand(12)
        final int min_leaf_height = treeConfig.canopy_start_height + sourceRand.nextInt(treeConfig.canopy_start_variance);
        // probably depth of canopy
        final int canopy_depth = height - min_leaf_height;
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

        // place the 'dirt' block.
        placePos.move(Direction.DOWN);
        level.setBlock(placePos, treeConfig.dirt_provider.getState(sourceRand, placePos), 2);
        placePos.move(Direction.NORTH);
        level.setBlock(placePos, treeConfig.dirt_provider.getState(sourceRand, placePos), 2);
        placePos.move(Direction.WEST);
        level.setBlock(placePos, treeConfig.dirt_provider.getState(sourceRand, placePos), 2);
        placePos.move(Direction.SOUTH);
        level.setBlock(placePos, treeConfig.dirt_provider.getState(sourceRand, placePos), 2);

        int l1 = sourceRand.nextInt(2);  // TODO: do these constants need to be config VARIABLES?
        int j2 = 1;
        boolean flag1 = false;

        // place leaves.
        BlockState bsLeaves = treeConfig.foliage_provider.getState(sourceRand, pos);
        for (int i3 = 0; i3 <= canopy_depth; i3++)
        {
            final int k3 = pos.getY() + height - i3;
            for (int i4 = pos.getX() - l1; i4 <= pos.getX() + l1; i4++)
            {
                final int k4 = i4 - pos.getX();
                for (int l4 = pos.getZ() - l1; l4 <= pos.getZ() + l1; l4++)
                {
                    final int i5 = l4 - pos.getZ();
                    if (Math.abs(k4) != l1 || Math.abs(i5) != l1 || l1 <= 0)
                    {
                        BlockPos newpos = new BlockPos(i4, k3, l4);
                        if (TreeFeature.validTreePos(level, newpos))
                        {
                            level.setBlock(newpos, bsLeaves, 2);
                        }
                        newpos = new BlockPos(i4 - 1, k3, l4);
                        if (TreeFeature.validTreePos(level, newpos))
                        {
                            level.setBlock(newpos, bsLeaves, 2);
                        }

                        newpos = new BlockPos(i4, k3, l4 - 1);
                        if (TreeFeature.validTreePos(level, newpos))
                        {
                            level.setBlock(newpos, bsLeaves, 2);
                        }

                        newpos = new BlockPos(i4 - 1, k3, l4 - 1);
                        if (TreeFeature.validTreePos(level, newpos))
                        {
                            level.setBlock(newpos, bsLeaves, 2);
                        }
                    } // end-if
                } // end-for l4
            } // end-for i4

            if (l1 >= j2)
            {
                l1 = flag1 ? 1 : 0;
                flag1 = true;

                if (++j2 > canopy_radius)
                {
                    j2 = canopy_radius;
                }
            }
            else
            {
                l1++;
            }
        } // end-for i3

        // place trunk blocks
        final int j3 = sourceRand.nextInt(3);

        for (int l3 = 0; l3 < height - j3; l3++)
        {
            //final Block block = world.getBlock(x, y + l3, z);
            BlockPos se_pos = new BlockPos(pos.getX(), pos.getY() + l3, pos.getZ());

            if (TreeFeature.validTreePos(level, se_pos))
            {
                BlockPos sw_pos = new BlockPos( pos.getX() - 1, pos.getY() + l3, pos.getZ());
                BlockPos ne_pos = new BlockPos(pos.getX(), pos.getY() + l3, pos.getZ() - 1);
                BlockPos nw_pos = new BlockPos(pos.getX() - 1, pos.getY() + l3, pos.getZ() - 1);

                // TODO revise for quarter blocks
                level.setBlock(se_pos, treeConfig.trunk_provider.getState(sourceRand, se_pos), 2);
                level.setBlock(sw_pos, treeConfig.trunk_provider.getState(sourceRand, sw_pos), 2);
                level.setBlock(ne_pos, treeConfig.trunk_provider.getState(sourceRand, ne_pos), 2);
                level.setBlock(nw_pos, treeConfig.trunk_provider.getState(sourceRand, nw_pos), 2);
            } // end-if
        } // end-for l3
        return true;
    } // end place()

} // end-class
