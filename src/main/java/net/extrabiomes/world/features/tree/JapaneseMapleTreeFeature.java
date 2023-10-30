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

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class JapaneseMapleTreeFeature extends EBBaseTreeFeature
{
    private final int BRANCHES_BASE_NUMBER  = 2;    // The total number of branches on the tree
    private final int BRANCHES_EXTRA        = 4;    // The how many extra branches can occur on the tree
    private final double TRUNK_HEIGHT_PERCENT  = 0.30D; // What percent of the total height the main trunk extends
    private int BASE_HEIGHT;
    private int BASE_HEIGHT_VARIANCE;
    private int CANOPY_WIDTH;
    private int CANOPY_WIDTH_VARIANCE;
    private EBTreeConfiguration treeConfig;
    private int actual_height;
    private double actual_radius;

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
        BASE_HEIGHT = treeConfig.base_height;
        BASE_HEIGHT_VARIANCE = treeConfig.base_height_variance;
        CANOPY_WIDTH = treeConfig.canopy_width;
        CANOPY_WIDTH_VARIANCE = treeConfig.canopy_width_variance;
        BlockPos pos = pContext.origin().immutable();
        RandomSource sourceRand = pContext.random();
        WorldGenLevel level = pContext.level();

        actual_height = sourceRand.nextInt(BASE_HEIGHT_VARIANCE) + BASE_HEIGHT;
        actual_radius = (CANOPY_WIDTH + sourceRand.nextInt(CANOPY_WIDTH_VARIANCE)) / 2.0D;

        int max_tree_altitude = pos.getY() + actual_height + 4;

        // height check
        if (pos.getY() < level.getMinBuildHeight() + 1 || max_tree_altitude > level.getMaxBuildHeight())
        {
            return false;
        }

        // CHECK clearances...
        // check the main trunk
        if (!check1x1Trunk(pos, (int) (actual_height * TRUNK_HEIGHT_PERCENT), treeConfig.trunk_provider.getState(sourceRand, pos), level))
        {
            return false;
        }
        // check the branches
        BlockPos branchpos = new BlockPos(pos.getX(), pos.getY()+(int) (actual_height * TRUNK_HEIGHT_PERCENT), pos.getZ());
        if (!checkBranches(level, sourceRand, branchpos,actual_height - (int) (actual_height * TRUNK_HEIGHT_PERCENT) - 2, actual_radius))
        {
            return false;
        }

        // Draw the main trunk
        if (place1x1Trunk(pos, (int) (actual_height * TRUNK_HEIGHT_PERCENT), treeConfig.trunk_provider.getState(sourceRand, pos), level))
        {
            // Generate the branches
            generateBranches(level, sourceRand, branchpos, actual_height - (int) (actual_height * TRUNK_HEIGHT_PERCENT) - 2, actual_radius);
            return true;
        }

        return true;
    } // end place()

    /**
     * Actually place the branches.
     * @param world
     * @param rand
     * @param branchpos
     * @param height
     * @param radius
     */
    public void generateBranches(LevelAccessor world, RandomSource rand, BlockPos branchpos, int height, double radius)
    {
        int branchCount = BRANCHES_BASE_NUMBER + rand.nextInt(BRANCHES_EXTRA);
        double curAngle = 0.0D;

        double[] average = { 0, 0, 0 };
        int[] start = { branchpos.getX(), branchpos.getY(), branchpos.getZ() };
        Queue<int[]> branches = new LinkedList<int[]>();
        BlockState leavesBlockState = treeConfig.foliage_provider.getState(rand, branchpos);

        // Generate the branches
        for (int ii = 0; ii < branchCount; ii++)
        {
            // Get the branch radius and height
            double angle = (rand.nextInt(50) + 35) / 90.0D;
            double thisHeight = (height + 1) * Math.sin(angle) / 1.3;
            double thisRadius = radius * Math.cos(angle);

            // Get the branch rotation
            curAngle += (rand.nextInt(360 / branchCount) + (360 / branchCount)) / 90.0D; //  + (360.0D/branchCount) / 180.0D ;

            int x1 = (int) ((thisRadius) * Math.cos(curAngle));
            int z1 = (int) ((thisRadius) * Math.sin(curAngle));

            // Add the the average count
            average[0] += x1;
            average[1] += thisHeight;
            average[2] += z1;

            // Add to the branch list
            int[] node = new int[] { x1 + branchpos.getX(), (int) thisHeight + branchpos.getY(), z1 + branchpos.getZ() };

            // Add the branch end for leaf generation
            branches.add(node);

            // Generate the branch
            placeBlockLine(start, node, leavesBlockState, world);
        } // end-for ii

        // Place the branch tips
        BlockPos.MutableBlockPos mpos = new BlockPos.MutableBlockPos();
        for (int[] cluster : branches) {
            mpos.set(cluster[0], cluster[1], cluster[2]);
            generateLeafCluster(world, mpos, 2, 1, leavesBlockState);
        }

        // Calculate the center position
        average[0] /= branchCount;
        average[1] = (branchCount / average[1]) + 2.3D;
        average[2] /= branchCount;

        // Generate the canopy
        generateCanopy(world, rand,
                new BlockPos((int) average[0] + branchpos.getX(), branchpos.getY(), (int) average[2] + branchpos.getZ()),
                radius, height, leavesBlockState);

        // Generate the center cone
        generateVerticalCone(world, branchpos, height - 1, .75, 2, leavesBlockState);

    } // end generateBranches()


    /**
     * Can we place all the branches and leaves?
     * @param world
     * @param rand
     * @param branchpos
     * @param height
     * @param radius
     * @return
     */
    public boolean checkBranches(LevelAccessor world, RandomSource rand, BlockPos branchpos, int height, double radius)
    {
        int branchCount = BRANCHES_BASE_NUMBER + rand.nextInt(BRANCHES_EXTRA);
        double curAngle = 0.0D;

        double[] average = { 0, 0, 0 };
        int[] start = { branchpos.getX(), branchpos.getY(), branchpos.getZ() };
        Queue<int[]> branches = new LinkedList<int[]>();

        // Generate the branches
        for (int ii = 0; ii < branchCount; ii++)
        {
            // Get the branch radius and height
            double angle = (rand.nextInt(50) + 35) / 90.0D;
            double thisHeight = (height + 1) * Math.sin(angle) / 1.3;
            double thisRadius = radius * Math.cos(angle);

            // Get the branch rotation
            curAngle += (rand.nextInt(360 / branchCount) + (360 / branchCount)) / 90.0D;//  + (360.0D/branchCount) / 180.0D ;

            int x1 = (int) ((thisRadius) * Math.cos(curAngle));
            int z1 = (int) ((thisRadius) * Math.sin(curAngle));

            // Add the the average count
            average[0] += x1;
            average[1] += thisHeight;
            average[2] += z1;

            // Add to the branch list
            int[] node = new int[] { x1 + branchpos.getX(), (int) thisHeight + branchpos.getY(), z1 + branchpos.getZ() };

            // Add the branch end for leaf generation
            branches.add(node);

            // check the branch that would generate...
            if (!checkBlockLine(start, node, world))
                return false;
        } // end-for ii

        // Place the branch tips
        for (int[] cluster : branches) {
            if (!checkLeafCluster(world, new BlockPos(cluster[0], cluster[1], cluster[2]), 2, 1))
                return false;
        }

        // Calculate the center position
        average[0] /= branchCount;
        average[1] = (branchCount / average[1]) + 2.3D;
        average[2] /= branchCount;

        // Generate the canopy
        if (!checkCanopy(world, new BlockPos((int) average[0] + branchpos.getX(),
                                             branchpos.getY(), (int) average[2] + branchpos.getZ()), radius, height))
        {
            return false;
        }

        return true;

    } // end checkBranches()


    public boolean checkCanopy(LevelAccessor world, BlockPos pos, double radius, int height)
    {
        int layers = height + 2;
        BlockPos.MutableBlockPos checkpos = pos.mutable();

        for (int y1 = pos.getY(), layer = 0; layer < layers; layer++, y1++)
        {
            checkpos.setY(y1);
            if (!checkCanopyLayer(world, checkpos, radius * Math.cos((layer) / (height / 1.3))))
                return false;
        }

        return true;
    }  // end checkCanopy()

    public boolean checkCanopyLayer(LevelAccessor world, BlockPos pos, double radius)
    {
        double minDist = (radius - 2 > 0) ? ((radius - 2) * (radius - 2)) : -1;
        double maxDist = radius * radius;
        BlockPos.MutableBlockPos checkpos = pos.mutable();

        for (int z1 = (int) -radius; z1 < (radius + 1); z1++)
        {
            for (int x1 = (int) -radius; x1 < (radius + 1); x1++)
            {
                checkpos.set(x1+pos.getX(), pos.getY(), z1+pos.getZ());
                if ((((x1 * x1) + (z1 * z1)) <= maxDist) && (((x1 * x1) + (z1 * z1)) >= minDist))
                {
                    if (!TreeFeature.isAirOrLeaves(world, checkpos))
                    {
                        return false;
                    }
                } // end-if
            } // end-for x1
        } // end-for z1

        return true;
    } // end checkCanopyLayer()

    public void generateCanopy(LevelAccessor world, RandomSource rand, BlockPos pos, double radius, int height, BlockState leaves)
    {
        BlockPos.MutableBlockPos cpos = pos.mutable();

        int layers = height + 2;
        for (int y1 = (int) pos.getY(), layer = 0; layer < layers; layer++, y1++)
        {
            cpos.setY(y1);
            if (layer < 2)
            {
                generateCanopyLayer(world, rand, cpos, radius * Math.cos((layer) / (height / 1.3)), 2 + (layer * 5), leaves);
            }
            else
            {
                generateCanopyLayer(world, rand, cpos, radius * Math.cos((layer) / (height / 1.3)), 1000, leaves);
            }
        }
    } // end generateCanopy


    public void generateCanopyLayer(LevelAccessor world, RandomSource rand, BlockPos pos, double radius, int skipChance,
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
                    if (TreeFeature.isAirOrLeaves(world, cpos))
                    {
                        if (rand.nextInt(skipChance) != 0)
                        {
                            world.setBlock(cpos, leaves, 2);
                        }
                    } // end-if
                } // end-if
            } // end-for x1
        } // end-for z1
    } // end generateCanopyLayer

    public void generateVerticalCone(LevelAccessor world, BlockPos pos, int height, double r1, double r2, BlockState leaves)
    {
        double ratio = (r2 - r1) / (height - 1);
        BlockPos.MutableBlockPos conepos = pos.mutable();
        for (int offset = 0; offset < height; offset++)
        {
            conepos.setY(pos.getY() + offset);
            placeLeavesCircle(conepos, (ratio * offset) + r1, leaves, world);
        }
    } // end generateVerticalCone()



} // end class
