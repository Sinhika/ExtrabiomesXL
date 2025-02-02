package net.extrabiomes.world.features.tree;

import com.google.common.collect.AbstractIterator;
import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;
import net.extrabiomes.world.features.configuration.EBTreeConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.function.BiConsumer;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.AXIS;

public abstract class EBBaseTreeFeature extends Feature<EBTreeConfiguration>
{
    protected EBTreeConfiguration treeConfig;
    protected int BASE_HEIGHT;            // The base height for trees
    protected int BASE_HEIGHT_VARIANCE;   // How many extra blocks high may this tree be
    protected int CANOPY_WIDTH;           // How many blocks will this tree cover
    protected int CANOPY_WIDTH_VARIANCE;  // How many extra blocks may this tree cover
    protected int actual_height;          // final generated height of tree.
    protected double actual_radius;       // final generated radius of canopy.
    protected RandomSource sourceRand;   // random number source.
    protected WorldGenLevel level;       // level accessor
    protected int BRANCHES_BASE_NUMBER;  // The total number of branches on the tree
    protected int BRANCHES_EXTRA; // The how many extra branches can occur on the tree
    protected double[] AVERAGE = {0, 0, 0}; // center of canopy relative to top of trunk.
    protected Set<BlockPos> posLogs;
    protected Set<BlockPos> posLeaves;
    protected Set<BlockPos> posRoots;
    protected Set<BlockPos> posTrunks;

    public EBBaseTreeFeature(Codec<EBTreeConfiguration> pCodec)
    {
        super(pCodec);
        posLogs = Sets.newHashSet();
        posLeaves = Sets.newHashSet();
        posRoots = Sets.newHashSet();
        posTrunks = Sets.newHashSet();
    }

    /**
     * Places the given feature at the given location.
     * During world generation, features are provided with a 3x3 region of chunks, centered on the chunk being generated,
     * that they can safely generate into.
     *
     * @param pContext A context object with a reference to the level and the position the feature is being placed at
     * @return true if successful and tree placed; false if not.
     */
    @Override public boolean place(FeaturePlaceContext<EBTreeConfiguration> pContext)
    {
        this.treeConfig = pContext.config();
        this.BASE_HEIGHT = treeConfig.base_height;
        this.BASE_HEIGHT_VARIANCE = treeConfig.base_height_variance;
        this.CANOPY_WIDTH = treeConfig.canopy_width;
        this.CANOPY_WIDTH_VARIANCE = treeConfig.canopy_width_variance;
        this.sourceRand = pContext.random();
        this.level = pContext.level();

        if (this.BASE_HEIGHT_VARIANCE > 0) {
            this.actual_height = sourceRand.nextInt(this.BASE_HEIGHT_VARIANCE) + this.BASE_HEIGHT;
        }
        else {
            this.actual_height = this.BASE_HEIGHT;
        }
        if (this.CANOPY_WIDTH_VARIANCE > 0) {
            this.actual_radius = (this.CANOPY_WIDTH + sourceRand.nextInt(this.CANOPY_WIDTH_VARIANCE)) / 2.0D;
        }
        else {
            this.actual_radius = this.CANOPY_WIDTH / 2.0D;
        }

        return true;
    } // end place.

    /**
     * place decorators
     */
    public void placeDecorators()
    {
        BiConsumer<BlockPos, BlockState> biconsumer2 = (p_160543_, p_160544_) -> {
            posTrunks.add(p_160543_.immutable());
            this.level.setBlock(p_160543_, p_160544_, 19);
        };
        if (!posLogs.isEmpty() || !posLeaves.isEmpty())
        {
            if (!this.treeConfig.decorators.isEmpty())
            {
                TreeDecorator.Context treedecorator_context
                        = new TreeDecorator.Context(this.level, biconsumer2, this.sourceRand, posLogs, posLeaves,
                                                    posTrunks);
                treeConfig.decorators.forEach((p_225282_) -> {
                    p_225282_.place(treedecorator_context);
                });
            } // end-if
        } // end-if
    } // end-placeDecorators()

    /**
     * Actually place the branches.
     * @param world current LevalAccessor
     * @param rand current RandomSource
     * @param branchpos Starting position of branches
     * @param height canopy height
     * @param radius canopy radius
     * @return true if successfully placed, false if not.
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
                this.posTrunks.add(placePos.immutable());
            }
            else {
                return false;
            }
        }

        return true;
    } // end place1x1Trunk()


    /**
     * Actually place the branches.
     * @param world current LevalAccessor
     * @param rand current RandomSource
     * @param branchpos Starting position of branches
     * @param height canopy height
     * @param radius canopy radius
     * @param cluster_height height of attached leaf cluster
     * @param cluster_radius radius of attached leaf cluster
     * @return true if successfully placed, false if not.
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
     * @return true if successfully placed, false if not.
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
                        this.posLogs.add(bpos.immutable());
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
                        this.posLogs.add(bpos.immutable());
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
                        this.posLogs.add(bpos.immutable());
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
                        this.posLogs.add(bpos.immutable());
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
                        this.posLogs.add(bpos.immutable());
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
                        this.posLogs.add(bpos.immutable());
                    }
                } // end-for y
            } // end-else
        } // end-else

        return true;
    } // end placeBlockLine()

    /**
     *
     * @param world level accessor
     * @param pos
     * @param height
     * @param radius
     * @param leaves
     */
    public void generateLeafCluster(LevelAccessor world, BlockPos pos, int height, int radius, BlockState leaves)
    {
        BlockPos.MutableBlockPos leafpos = pos.mutable();

        for (int layer = -height; layer <= height; layer++)
        {
            leafpos.setY(pos.getY() + layer);
            placeLeavesCircle(leafpos, radius * Math.cos(layer / (height / 1.3)), leaves, world);
        }
    } // end generateLeafCluster()

    /**
     * Place a radius r circle of leaves around position pos.
     * @param pos center position of leaf circle
     * @param r   radius in blocks of leaf circle.
     * @param leaves    leaf provider
     * @param world level accessor
     */
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
                    this.posLeaves.add(leafpos.immutable());
                }
            } // end-for x1
        } // end-for z1

    } // end placeLeavesCircle()

    /**
     * Place leaves in a square with width 2r around position pos.
     * @param pos center of leaf square
     * @param r   half-width of square.
     * @param dist_from_top distance from top of canopy
     * @param leaves foliage provider
     * @param world level accessor
     *
     */
    public void placeLeavesSquare(BlockPos pos, int r, int dist_from_top, BlockState leaves, LevelAccessor world)
    {
        BlockPos.MutableBlockPos placePos = pos.mutable();

        for (int x1 = pos.getX() - r; x1 <= pos.getX() + r; x1++)
        {
            final int xOnRadius = x1 - pos.getX();

            for (int z1 = pos.getZ() - r; z1 <= pos.getZ() + r; z1++)
            {
                final int zOnRadius = z1 - pos.getZ();

                placePos.set(x1, pos.getY(), z1);
                if ( (Math.abs(xOnRadius) != r || Math.abs(zOnRadius) != r
                              || sourceRand.nextInt(2) != 0 && dist_from_top != 0)
                        && TreeFeature.validTreePos(world, placePos))
                {
                    this.setBlock(world, placePos, leaves);
                    this.posLeaves.add(placePos.immutable());
                }
            }
        }
    } // end placeLeavesSquare


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
                            this.posLeaves.add(cpos.immutable());
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

    public static Iterable<BlockPos> find2x2(BlockPos pos)
    {
        int jj = pos.getX();
        int kk = pos.getY();
        int ll = pos.getZ();

        return () -> {
            return new AbstractIterator<BlockPos>() {
                final BlockPos.MutableBlockPos cursor = new BlockPos.MutableBlockPos();
                int step = 1;
                private int xx;
                private int zz;

                protected BlockPos computeNext()
                {
                    BlockPos blockpos;

                    if (step > 4) {
                        return this.endOfData();
                    }
                    else {
                        switch(step)
                        {
                            case 1:
                                xx = 0; zz = 0;
                                break;
                            case 2:
                                xx = 0; zz = 1;
                                break;
                            case 3:
                                xx = 1; zz = 0;
                                break;
                            case 4:
                                xx = 1; zz = 1;
                        }
                        step++;
                        blockpos = this.cursor.set(jj + xx, kk, ll + zz);
                    }
                    return blockpos;
                } // end computeNext()
            }; // end lambda class
        }; // end supplier
    } // end find2x2

    public boolean check2x2Trunk(BlockPos pos, int height, LevelAccessor world, boolean inWater)
    {
        BlockPos.MutableBlockPos checkpos = pos.mutable();
        int yy = pos.getY();

        if (inWater)
        {
            for (int i = 0; i < height; i++)
            {
                checkpos.setY(yy+i);
                for (BlockPos here : find2x2(checkpos))
                {
                    BlockState thisHere = world.getBlockState(here);
                    if (!thisHere.getFluidState().is(FluidTags.WATER) && !thisHere.isAir() && !thisHere.is(BlockTags.REPLACEABLE))
                    {
                        return false;
                    }
                } // end-for here
            }
        } // end-if inWater
        else {
            for (int i = 0; i < height; i++)
            {
                checkpos.setY(yy + i);
                for (BlockPos here : find2x2(checkpos))
                {
                    if (!world.getBlockState(here).isAir() && !world.getBlockState(here).canBeReplaced())
                    {
                        return false;
                    }
                } // end-for here
            }
        } // end-else not inWater

        return true;
    } // end check2x2Trunk


} // end class
