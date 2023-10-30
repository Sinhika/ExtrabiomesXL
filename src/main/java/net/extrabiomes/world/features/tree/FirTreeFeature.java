package net.extrabiomes.world.features.tree;

import com.mojang.serialization.Codec;
import net.extrabiomes.world.features.configuration.EBTreeConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.TreeFeature;

public class FirTreeFeature extends Feature<EBTreeConfiguration>
{
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
        EBTreeConfiguration treeConfig = pContext.config();
        BlockPos pos = pContext.origin().immutable();
        RandomSource sourceRand = pContext.random();
        WorldGenLevel level = pContext.level();
        int height = treeConfig.base_height + sourceRand.nextInt(treeConfig.base_height_variance);
        int max_tree_altitude = pos.getY() + height + 1;

        // height check
        if (pos.getY() < level.getMinBuildHeight() + 1 || max_tree_altitude > level.getMaxBuildHeight())
        {
            return false;
        }

        // min_leaf_height - was 1+rand(12)
        final int min_leaf_height = treeConfig.canopy_start_height + sourceRand.nextInt(treeConfig.canopy_start_variance);
        // canopy_radius - was 2+rand(6)
        final int canopy_radius = Math.min((treeConfig.canopy_width + sourceRand.nextInt(treeConfig.canopy_width_variance)), 6);

        // make sure space for tree is clear or replaceable.
        BlockPos.MutableBlockPos checkpos = pos.mutable();
        for (int i1 = pos.getY(); i1 <= max_tree_altitude; i1++)
        {
            int k1;  // current canopy radius
            if (i1 - pos.getY() < min_leaf_height) {
                k1 = 0;
            }
            else {
                k1 = canopy_radius;
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

        // num block below treetop that trunk stops.
        int l1 = sourceRand.nextInt(2);   // TODO: do these constants need to be config VARIABLES?
        // TODO: what are these variables?
        int j2 = 1;
        boolean flag1 = false;

        // stick some leaves up.
        for (int i3 = 0; i3 <= height - min_leaf_height; i3++)
        {
            final int k3 = pos.getY() + height - i3;

            for (int i4 = pos.getX() - l1; i4 <= pos.getX() + l1; i4++)
            {
                final int k4 = i4 - pos.getX();

                for (int l4 = pos.getZ() - l1; l4 <= pos.getZ() + l1; l4++)
                {
                    final int i5 = l4 - pos.getZ();
                    placePos.set(i4, k3, l4);
                    if ((Math.abs(k4) != l1 || Math.abs(i5) != l1 || l1 <= 0)
                            && (TreeFeature.isAirOrLeaves(level, placePos)))
                    {
                        level.setBlock(placePos, treeConfig.foliage_provider.getState(sourceRand, placePos), 3);
                    }
                }  // end-for l4
            } // end-for i4

            if (l1 >= j2)
            {
                l1 = flag1 ? 1 : 0;
                flag1 = true;

                if (++j2 > canopy_radius)
                    j2 = canopy_radius;
            }
            else
            {
                l1++;
            }
        } // end-for i3

        // TODO: what are these variables?
        final int j3 = sourceRand.nextInt(3); // TODO: do these constants need to be config VARIABLES?

        // place trunk blocks.
        placePos.set(pos.getX(), pos.getY(), pos.getZ());

        for (int l3 = 0; l3 < height - j3; l3++)
        {
            // final Block block = world.getBlock(x, y + l3, z);
            placePos.setY(pos.getY()+l3);

            //if (block == null || block.isLeaves(world, x, y + l3, z) || block.isAir(world, x, y + l3, z) )
            if (TreeFeature.validTreePos(level, placePos))
            {
                level.setBlock(placePos, treeConfig.trunk_provider.getState(sourceRand, placePos), 3);
            }
        }

        return true;
    } // end place

} // end class
