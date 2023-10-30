package net.extrabiomes.world.features.tree;

import com.mojang.serialization.Codec;
import net.extrabiomes.world.features.configuration.EBTreeConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.TreeFeature;

import java.util.LinkedList;
import java.util.Queue;

public class JapaneseMapleTreeFeature extends EBBaseTreeFeature
{
    private EBTreeConfiguration treeConfig;

    public JapaneseMapleTreeFeature(Codec<EBTreeConfiguration> pCodec)
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
        treeConfig = pContext.config();
        int BASE_HEIGHT = treeConfig.base_height;
        int BASE_HEIGHT_VARIANCE = treeConfig.base_height_variance;
        int CANOPY_WIDTH = treeConfig.canopy_width;
        int CANOPY_WIDTH_VARIANCE = treeConfig.canopy_width_variance;
        BlockPos pos = pContext.origin().immutable();
        RandomSource sourceRand = pContext.random();
        WorldGenLevel level = pContext.level();

        int actual_height = sourceRand.nextInt(BASE_HEIGHT_VARIANCE) + BASE_HEIGHT;
        double actual_radius = (CANOPY_WIDTH + sourceRand.nextInt(CANOPY_WIDTH_VARIANCE)) / 2.0D;
        // What percent of the total height the main trunk extends
        double TRUNK_HEIGHT_PERCENT = 0.30D;
        int trunk_height = (int) (actual_height * TRUNK_HEIGHT_PERCENT);
        int max_tree_altitude = pos.getY() + actual_height + 4;

        // height check
        if (pos.getY() < level.getMinBuildHeight() + 1 || max_tree_altitude > level.getMaxBuildHeight())
        {
            return false;
        }

        // make sure space for tree is clear or replaceable.
        int iRadius = (int) actual_radius;
        BlockPos.MutableBlockPos checkpos = pos.mutable();
        // check trunk column
        for (int yy = pos.getY(); yy < trunk_height; yy++)
        {
            checkpos.setY(yy);
            if (!TreeFeature.validTreePos(level, checkpos)) {
                return false;
            }
        }
        // check canopy
        for (int i1 = pos.getY() + trunk_height; i1 <= max_tree_altitude; i1++)
        {
            for (int x1 = pos.getX() - iRadius; x1 <= pos.getX() + iRadius; x1++)
            {
                for (int z1 = pos.getZ() - iRadius; z1 <= pos.getZ() + iRadius; z1++)
                {
                    checkpos.set(x1,i1,z1);
                    if (!TreeFeature.validTreePos(level, checkpos)) {
                        return false;
                    }
                } // end-for z1
            } // end-for x1
        } // end for i1

        // place the dirt block.
        this.setBlock(level, pos.below(), treeConfig.dirt_provider.getState(sourceRand, pos.below()));

        // Draw the main trunk
        if (place1x1Trunk(pos, trunk_height, treeConfig.trunk_provider.getState(sourceRand, pos), level))
        {
            // Generate the branches
            BlockPos branchpos = new BlockPos(pos.getX(), pos.getY()+trunk_height, pos.getZ());
            if (generateBranches(level, sourceRand, branchpos, actual_height - trunk_height - 2, actual_radius))
            {
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
     * @param height
     * @param radius
     */
    public boolean generateBranches(LevelAccessor world, RandomSource rand, BlockPos branchpos, int height, double radius)
    {
        // The total number of branches on the tree
        int BRANCHES_BASE_NUMBER = 2;
        // The how many extra branches can occur on the tree
        int BRANCHES_EXTRA = 4;
        int branchCount = BRANCHES_BASE_NUMBER + rand.nextInt(BRANCHES_EXTRA);
        double curAngle = 0.0D;

        double[] average = { 0, 0, 0 };
        int[] start = { branchpos.getX(), branchpos.getY(), branchpos.getZ() };
        Queue<int[]> branches = new LinkedList<int[]>();
        BlockState leavesBlockState = treeConfig.foliage_provider.getState(rand, branchpos);
        BlockState logBlockState = treeConfig.trunk_provider.getState(rand, branchpos);

        // Generate the branches
        for (int ii = 0; ii < branchCount; ii++)
        {
            // Get the branch radius and height
            double angle = (rand.nextInt(50) + 35) / 90.0D;
            double thisHeight = (height + 1) * Math.sin(angle) / 1.3;
            double thisRadius = radius * Math.cos(angle);

            // Get the branch rotation
            curAngle += (double) (rand.nextInt(360/branchCount) + (360.0D / (double) branchCount)) / 90.0D; //  + (360.0D/branchCount) / 180.0D ;

            int x1 = (int) ((thisRadius) * Math.cos(curAngle));
            int z1 = (int) ((thisRadius) * Math.sin(curAngle));

            // Add the average count
            average[0] += x1;
            average[1] += thisHeight;
            average[2] += z1;

            // Add to the branch list
            int[] node = new int[] { x1 + branchpos.getX(), (int) thisHeight + branchpos.getY(), z1 + branchpos.getZ() };

            // Add the branch end for leaf generation
            branches.add(node);

            // Generate the branch
            if (!placeBlockLine(start, node, logBlockState, world)) {
                return false;
            }
        } // end-for ii

        // Place the branch tips
        BlockPos.MutableBlockPos mpos = new BlockPos.MutableBlockPos();
        for (int[] cluster : branches) {
            mpos.set(cluster[0], cluster[1], cluster[2]);
            if (!generateLeafCluster(world, mpos, 2, 1, leavesBlockState))
            {
                return false;
            }
        } // end-for cluster

        // Calculate the center position
        average[0] /= branchCount;
        average[1] = (branchCount / average[1]) + 2.3D;
        average[2] /= branchCount;

        // Generate the canopy
        if (!generateCanopy(world, rand,
                new BlockPos((int) average[0] + branchpos.getX(), branchpos.getY(), (int) average[2] + branchpos.getZ()),
                radius, height, leavesBlockState))
        {
            return false;
        }

        // Generate the center cone
        if (! generateVerticalCone(world, branchpos, height - 1, .75, 2, leavesBlockState))
        {
            return false;
        }

        return true;
    } // end generateBranches()

    public boolean generateCanopy(LevelAccessor world, RandomSource rand, BlockPos pos, double radius, int height, BlockState leaves)
    {
        BlockPos.MutableBlockPos cpos = pos.mutable();

        int layers = height + 2;
        for (int y1 = pos.getY(), layer = 0; layer < layers; layer++, y1++)
        {
            cpos.setY(y1);
            double canopy_radius = radius * Math.cos((float) layer / ((float) height / 1.3));

            if (layer < 2)
            {
                if (!generateCanopyLayer(world, rand, cpos, canopy_radius, 2 + (layer * 5), leaves))
                {
                    return false;
                }
            }
            else
            {
                if (! generateCanopyLayer(world, rand, cpos, canopy_radius, 1000, leaves))
                {
                    return false;
                }
            }
        } // end-for y1
        return true;
    } // end generateCanopy


    public boolean generateCanopyLayer(LevelAccessor world, RandomSource rand, BlockPos pos, double radius, int skipChance,
                                        BlockState leaves)
    {
        double minDist = (radius - 2 > 0) ? ((radius - 2) * (radius - 2)) : -1;
        double maxDist = radius * radius;
        BlockPos.MutableBlockPos cpos = pos.mutable();

        for (int z1 = (int) -radius; z1 < (radius + 1); z1++)
        {
            for (int x1 = (int) -radius; x1 < (radius + 1); x1++)
            {
                cpos.set(x1 + pos.getX(), pos.getY(), z1 + pos.getZ());

                if ((((x1 * x1) + (z1 * z1)) <= maxDist) && (((x1 * x1) + (z1 * z1)) >= minDist))
                {
                    if (world.getBlockState(cpos).isAir())
                    {
                        if (rand.nextInt(skipChance) != 0)
                        {
                            this.setBlock(world, cpos, leaves);
                        }
                    } // end-if
                } // end-if
            } // end-for x1
        } // end-for z1
        return true;
    } // end generateCanopyLayer

    public boolean generateVerticalCone(LevelAccessor world, BlockPos pos, int height, double r1, double r2, BlockState leaves)
    {
        double ratio = (r2 - r1) / (height - 1);
        BlockPos.MutableBlockPos conepos = pos.mutable();
        for (int offset = 0; offset < height; offset++)
        {
            conepos.setY(pos.getY() + offset);
            placeLeavesCircle(conepos, (ratio * offset) + r1, leaves, world);
        } // end-for offset
        return true;
    } // end generateVerticalCone()



} // end class
