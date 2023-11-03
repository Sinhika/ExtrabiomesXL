package net.extrabiomes.world.features.tree;

import com.mojang.serialization.Codec;
import net.extrabiomes.world.features.configuration.EBTreeConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.TreeFeature;

import java.util.LinkedList;
import java.util.Queue;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.AXIS;

public abstract class EBBaseTreeFeature extends Feature<EBTreeConfiguration>
{
    protected EBTreeConfiguration treeConfig;
    protected int BASE_HEIGHT;
    protected int BASE_HEIGHT_VARIANCE;
    protected int CANOPY_WIDTH;
    protected int CANOPY_WIDTH_VARIANCE;
    protected int actual_height;
    protected double actual_radius;
    protected RandomSource sourceRand;
    protected WorldGenLevel level;
    protected int BRANCHES_BASE_NUMBER;  // The total number of branches on the tree
    protected int BRANCHES_EXTRA; // The how many extra branches can occur on the tree
    protected double[] AVERAGE = {0, 0, 0}; // center of canopy relative to top of trunk.

    public EBBaseTreeFeature(Codec<EBTreeConfiguration> pCodec) {
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
        sourceRand = pContext.random();
        level = pContext.level();

        actual_height = sourceRand.nextInt(BASE_HEIGHT_VARIANCE) + BASE_HEIGHT;
        actual_radius = (CANOPY_WIDTH + sourceRand.nextInt(CANOPY_WIDTH_VARIANCE)) / 2.0D;

        return true;
    }

    /**
     * Actually place the branches.
     * @param world current LevalAccessor
     * @param rand current RandomSource
     * @param branchpos Starting position of branches
     * @param height canopy height
     * @param radius canopy radius
     */
    public abstract boolean generateBranches(LevelAccessor world, RandomSource rand, BlockPos branchpos, int height,
                                             double radius);

    /**
     * Actually place a 1x1 tree trunk.
     * @param pos BlockPos of bottom of trunk
     * @param height height of trunk
     * @param logs  trunk consists of this log (blockstate)
     * @param world LevelAccessor
     * @return true if successfully placed, false if not.
     */
    public boolean place1x1Trunk(BlockPos pos, int height, BlockState logs, LevelAccessor world)
    {
        BlockPos.MutableBlockPos placePos = pos.mutable();

        // Place the wood blocks
        for (int y1 = pos.getY(); y1 < pos.getY() + height; y1++)
        {
            placePos.setY(y1);
            if (TreeFeature.validTreePos(world, placePos)
                    || (y1 == pos.getY() && world.getBlockState(placePos).is(BlockTags.SAPLINGS)))
            {
                this.setBlock(world, placePos, logs);
            }
            else {
                return false;
            }
        }

        return true;
    }

    /**
     * Actually place the branches.
     * @param world current LevalAccessor
     * @param rand current RandomSource
     * @param branchpos Starting position of branches
     * @param height canopy height
     * @param radius canopy radius
     * @param cluster_height height of attached leaf cluster
     * @param cluster_radius radius of attached leaf cluster
     */
    public boolean generateBasicBranches(LevelAccessor world, RandomSource rand, BlockPos branchpos, int height, double radius,
                                         int cluster_height, int cluster_radius)
    {
        int branchCount = BRANCHES_BASE_NUMBER + rand.nextInt(BRANCHES_EXTRA);
        double curAngle = 0.0D;

        int[] start = {branchpos.getX(), branchpos.getY(), branchpos.getZ()};
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
            curAngle += (double) (rand.nextInt(360 / branchCount) + (360.0D / (double) branchCount)) /
                    90.0D; //  + (360.0D/branchCount) / 180.0D ;

            int x1 = (int) ((thisRadius) * Math.cos(curAngle));
            int z1 = (int) ((thisRadius) * Math.sin(curAngle));

            // Add the AVERAGE count
            AVERAGE[0] += x1;
            AVERAGE[1] += thisHeight;
            AVERAGE[2] += z1;

            // Add to the branch list
            int[] node = new int[]{x1 + branchpos.getX(), (int) thisHeight + branchpos.getY(), z1 + branchpos.getZ()};

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
            generateLeafCluster(world, mpos, cluster_height, cluster_radius, leavesBlockState);
        } // end-for cluster

        // Calculate the center position and store.
        AVERAGE[0] /= branchCount;
        AVERAGE[1] = (branchCount / AVERAGE[1]) + 2.3D;
        AVERAGE[2] /= branchCount;

        return true;
    } // end generateBasicBranches

    /**
     * Places a line of blocks that represent a branch.
     * @param start
     * @param end
     * @param logBlock
     * @param world
     * @return
     */
    public boolean placeBlockLine(int[] start, int[] end, BlockState logBlock, LevelAccessor world)
    {
        if (start.length != 3 || end.length != 3)
            return false;

        // Get the direction vector
        int[] direction = {
                start[0] - end[0],
                start[1] - end[1],
                start[2] - end[2]
        };
        BlockPos.MutableBlockPos bpos = new BlockPos.MutableBlockPos();

        if (Math.abs(direction[2]) > Math.abs(direction[1]) && Math.abs(direction[2]) > Math.abs(direction[0]))
        {
            // We are going to use the y axis as our major axis
            if (direction[2] >= 0)
            {
                for (int z = start[2]; z >= end[2]; z--)
                {
                    double m = (z - start[2]) / (double) direction[2];
                    int x = (int) (start[0] + (direction[0] * m));
                    int y = (int) (start[1] + (direction[1] * m));
                    bpos.set(x, y, z);
                    if (world.getBlockState(bpos).isAir())
                    {
                        this.setBlock(world, bpos, logBlock.setValue(AXIS, Direction.Axis.Z));
                    }
                } // end-for z
            } // end-if
            else
            {
                for (int z = start[2]; z <= end[2]; z++)
                {
                    double m = (z - start[2]) / (double) direction[2];
                    int x = (int) (start[0] + (direction[0] * m));
                    int y = (int) (start[1] + (direction[1] * m));
                    bpos.set(x, y, z);
                    if (world.getBlockState(bpos).isAir())
                    {
                        this.setBlock(world, bpos, logBlock.setValue(AXIS, Direction.Axis.Z));
                    }
                } // end-for z
            } // end-else
        } // end-if
        else if (Math.abs(direction[0]) > Math.abs(direction[1]))
        {
            // Treverse along the x axis
            if (direction[0] >= 0)
            {
                for (int x = start[0]; x >= end[0]; x--)
                {
                    double m = (x - start[0]) / (double) direction[0];
                    int z = (int) (start[2] + (direction[2] * m));
                    int y = (int) (start[1] + (direction[1] * m));
                    bpos.set(x, y, z);
                    if (world.getBlockState(bpos).isAir())
                    {
                        this.setBlock(world, bpos, logBlock.setValue(AXIS, Direction.Axis.X));
                    }
                } // end-for x
            } // end-if
            else
            {
                for (int x = start[0]; x <= end[0]; x++)
                {
                    double m = (x - start[0]) / (double) direction[0];
                    int z = (int) (start[2] + (direction[2] * m));
                    int y = (int) (start[1] + (direction[1] * m));
                    bpos.set(x, y, z);
                    if (world.getBlockState(bpos).isAir())
                    {
                        this.setBlock(world, bpos, logBlock.setValue(AXIS, Direction.Axis.X));
                    }
                } // end-for x
            } // end-else
        } // end else-if
        else
        {
            // We will use the y axis as our major axis
            if (direction[1] >= 0)
            {
                for (int y = start[1]; y >= end[1]; y--)
                {
                    double m = (y - start[1]) / (double) direction[1];
                    int x = (int) (start[0] + (direction[0] * m));
                    int z = (int) (start[2] + (direction[2] * m));
                    bpos.set(x, y, z);
                    if (world.getBlockState(bpos).isAir() || world.getBlockState(bpos).is(BlockTags.SAPLINGS))
                    {
                        this.setBlock(world, bpos, logBlock);
                    }
                } // end-for y
            } // end-if
            else
            {
                for (int y = start[1]; y <= end[1]; y++)
                {
                    double m = (y - start[1]) / (double) direction[1];
                    int x = (int) (start[0] + (direction[0] * m));
                    int z = (int) (start[2] + (direction[2] * m));
                    bpos.set(x, y, z);
                    if (world.getBlockState(bpos).isAir() || world.getBlockState(bpos).is(BlockTags.SAPLINGS))
                    {
                        this.setBlock(world, bpos, logBlock);
                    }
                } // end-for y
            } // end-else
        } // end-else

        return true;
    } // end placeBlockLine()

    public void generateLeafCluster(LevelAccessor world, BlockPos pos, int height, int radius, BlockState leaves)
    {
        BlockPos.MutableBlockPos leafpos = pos.mutable();

        for (int layer = -height; layer <= height; layer++)
        {
            leafpos.setY(pos.getY() + layer);
            placeLeavesCircle(leafpos, radius * Math.cos(layer / (height / 1.3)), leaves, world);
        }
    } // end generateLeafCluster()

    public void placeLeavesCircle(BlockPos pos, double r, BlockState leaves, LevelAccessor world)
    {
        double dist = r * r;
        BlockPos.MutableBlockPos leafpos = pos.mutable();

        for (double z1 = Math.floor(-r); z1 < r + 1; z1++)
        {
            for (double x1 = Math.floor(-r); x1 < r + 1; x1++)
            {
                int x2 = (int) (x1 + pos.getX());
                int z2 = (int) (z1 + pos.getZ());

                leafpos.set(x2, pos.getY(), z2);

                if ((((x1 * x1) + (z1 * z1)) <= dist) && world.getBlockState(leafpos).isAir())
                {
                    this.setBlock(world, leafpos, leaves);
                }
            } // end-for x1
        } // end-for z1

    } // end placeLeavesCircle()

    /**
     * Generate the tree's canopy.
     *
     * @param world LevelAccessor
     * @param rand a RandomSource
     * @param pos BlockPos of center of canopy
     * @param radius canopy radius
     * @param height canopy height
     * @param leaves leaf BlockState
     * @return true if successful, false if not.
     */
    public abstract void generateCanopy(LevelAccessor world, RandomSource rand, BlockPos pos, double radius,
                                           int height, BlockState leaves);

    /**
     * Generate a layer of the leaf canopy.
     *
     * @param world LevelAccessor
     * @param rand a RandomSource
     * @param pos BlockPos of canopy layer center.
     * @param radius  canopy radius
     * @param skipChance 1 in skipChance that we don't place leaves (i.e., leave holes in leaf layer)
     * @param leaves  leaf BlockState
     * @return
     */
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
    } // end generateCanopyLayer

    /**
     * Check clearance of trunk and canopy-cylinder region. May not be most desireable approach for
     * a particularly spread-out canopy.
     *
     * @param pos BlockPos of base of trunk.
     * @param iRadius canopy radius.
     * @param trunk_height height of trunk only.
     * @param max_tree_altitude highest Y point of tree.
     * @return true if trunk + cylinder is clear of obstructions, false if not.
     */
    public boolean checkRoughClearance(BlockPos pos, int iRadius, int trunk_height, int max_tree_altitude)
    {
        // make sure space for tree is clear or replaceable.
       // int iRadius = (int) actual_radius;
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
        return true;
    } // end checkRoughClearance()



} // end class
