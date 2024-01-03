package net.extrabiomes.world.features.tree;

import com.mojang.serialization.Codec;
import net.extrabiomes.world.features.configuration.EBTreeConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.TreeFeature;

import java.util.LinkedList;
import java.util.Queue;

public class JapaneseMapleTreeFeature extends EBBaseTreeFeature
{
    private static final double TRUNK_HEIGHT_PERCENT = 0.30D;

    public JapaneseMapleTreeFeature(Codec<EBTreeConfiguration> pCodec)
    {
        super(pCodec);
        BRANCHES_BASE_NUMBER = 2;
        BRANCHES_EXTRA = 4;
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
        super.place(pContext);
        BlockPos pos = pContext.origin().immutable();

        // What percent of the total height the main trunk extends
        int trunk_height = (int) (actual_height * TRUNK_HEIGHT_PERCENT);
        int max_tree_altitude = pos.getY() + actual_height + 4;

        // height check
        if (pos.getY() < level.getMinBuildHeight() + 1 || max_tree_altitude > level.getMaxBuildHeight())
        {
            return false;
        }

        // make sure space for tree is clear or replaceable.
        if (!checkRoughClearance(pos, (int) actual_radius, trunk_height, max_tree_altitude)) {
            return false;
        }

        // place the dirt block.
        this.setBlock(level, pos.below(), treeConfig.dirt_provider.getState(sourceRand, pos.below()));

        // Draw the main trunk
        if (place1x1Trunk(pos, trunk_height, treeConfig.trunk_provider.getState(sourceRand, pos), level))
        {
            // Generate the branches
            BlockPos branchpos = new BlockPos(pos.getX(), pos.getY()+trunk_height, pos.getZ());
            if (generateBranches(level, sourceRand, branchpos, actual_height - trunk_height - 2, actual_radius))
            {
                placeDecorators();
                return true;
            }
        } // end-if

        return false;
    } // end place()

    /**
     * Actually place the branches.
     * @param world current LevalAccessor
     * @param rand current RandomSource
     * @param branchpos Starting position of branches
     * @param height canopy height
     * @param radius canopy radius
     *
     */
    public boolean generateBranches(LevelAccessor world, RandomSource rand, BlockPos branchpos, int height, double radius)
    {
        BlockState leavesBlockState = treeConfig.foliage_provider.getState(rand, branchpos);

        if (!generateBasicBranches(world, rand, branchpos, height, radius, 2, 1))
        {
            return false;
        }
        // Generate the canopy
        generateCanopy(world, rand,
                new BlockPos((int) AVERAGE[0] + branchpos.getX(), branchpos.getY(), (int) AVERAGE[2] + branchpos.getZ()),
                radius, height, leavesBlockState);

        // Generate the center cone
        generateVerticalCone(world, branchpos, height - 1, .75, 2, leavesBlockState);

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
    public void generateCanopy(LevelAccessor world, RandomSource rand, BlockPos pos, double radius, int height, BlockState leaves)
    {
        BlockPos.MutableBlockPos cpos = pos.mutable();

        int layers = height + 2;
        for (int y1 = pos.getY(), layer = 0; layer < layers; layer++, y1++)
        {
            cpos.setY(y1);
            double canopy_radius = radius * Math.cos((float) layer / ((float) height / 1.3));

            if (layer < 2)
            {
                generateCanopyLayer(world, rand, cpos, canopy_radius, 2 + (layer * 5), leaves);
            }
            else
            {
                generateCanopyLayer(world, rand, cpos, canopy_radius, 1000, leaves);
            }
        } // end-for y1
    } // end generateCanopy

    /**
     * Generate cone of leaves that tops main canopy.
     * @param world
     * @param pos
     * @param height
     * @param r1
     * @param r2
     * @param leaves
     */
    public void generateVerticalCone(LevelAccessor world, BlockPos pos, int height, double r1, double r2, BlockState leaves)
    {
        double ratio = (r2 - r1) / (height - 1);
        BlockPos.MutableBlockPos conepos = pos.mutable();
        for (int offset = 0; offset < height; offset++)
        {
            conepos.setY(pos.getY() + offset);
            placeLeavesCircle(conepos, (ratio * offset) + r1, leaves, world);
        } // end-for offset
    } // end generateVerticalCone()



} // end class
