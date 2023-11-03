package net.extrabiomes.world.features.tree;

import com.mojang.serialization.Codec;
import net.extrabiomes.world.features.configuration.EBTreeConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class JapaneseMapleShrubFeature extends EBBaseTreeFeature
{
    private static final double TRUNK_HEIGHT_PERCENT = 0.10D;

    public JapaneseMapleShrubFeature(Codec<EBTreeConfiguration> pCodec)
    {
        super(pCodec);
        BRANCHES_BASE_NUMBER = 3;
        BRANCHES_EXTRA = 1;
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

        // What percent of the total height the main trunk extends
        int trunk_height = Math.max(1, (int) (actual_height * TRUNK_HEIGHT_PERCENT));
        int max_tree_altitude = pos.getY() + actual_height + 4;

        // height check
        if (pos.getY() < level.getMinBuildHeight() + 1 || max_tree_altitude > level.getMaxBuildHeight())
        {
            return false;
        }

        // make sure space for tree is clear or replaceable.
        if (!checkRoughClearance(pos, (int) actual_radius, trunk_height, max_tree_altitude))
        {
            return false;
        }

        // place the dirt block.
        this.setBlock(level, pos.below(), treeConfig.dirt_provider.getState(sourceRand, pos.below()));

        // Draw the main trunk
        if (place1x1Trunk(pos, trunk_height, treeConfig.trunk_provider.getState(sourceRand, pos), level))
        {
            // Generate the branches
            BlockPos branchpos = new BlockPos(pos.getX(), pos.getY()+trunk_height, pos.getZ());
            if (generateBranches(level, sourceRand, branchpos, Math.max(actual_height - trunk_height - 2, 1), actual_radius))
            {
                return true;
            }
        } // end-if

        return false;
    } // end place()


    @Override public boolean generateBranches(LevelAccessor world, RandomSource rand, BlockPos branchpos, int height,
                                              double radius)
    {
        BlockState leavesBlockState = treeConfig.foliage_provider.getState(rand, branchpos);

        if (!generateBasicBranches(world, rand, branchpos, height, radius, 1 + rand.nextInt(2), 2))
        {
            return false;
        }
        // Generate the canopy
        generateCanopy(world, rand,
                new BlockPos((int) AVERAGE[0] + branchpos.getX(), branchpos.getY(), (int) AVERAGE[2] + branchpos.getZ()),
                radius, height, leavesBlockState);

        return true;
    } // end generateBranches()

    /**
     * Generate the tree's canopy.
     *
     * @param world LevelAccessor
     * @param rand a RandomSource
     * @param pos BlockPos of center of canopy
     * @param radius canopy radius
     * @param height canopy height
     * @param leaves leaf BlockState
     */
    @Override public void generateCanopy(LevelAccessor world, RandomSource rand, BlockPos pos, double radius,
                                            int height, BlockState leaves)
    {
        BlockPos.MutableBlockPos cpos = pos.mutable();

        int layers = height + 2;
        for (int y1 = pos.getY(), layer = 0; layer < layers; layer++, y1++)
        {
            cpos.setY(y1);
            double layer_radius = radius * Math.cos((layer) / (height / 1.3D));

            if (layer < 2)
            {
                generateCanopyLayer(world, rand, cpos, layer_radius, 2 + (layer * 5), leaves);
            }
            else
            {
                generateCanopyLayer(world, rand, cpos, layer_radius, 1000, leaves);
            }
        }
    }
} // end-class
