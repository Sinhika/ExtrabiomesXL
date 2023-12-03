package net.extrabiomes.world.features.tree;

import com.mojang.serialization.Codec;
import net.extrabiomes.world.features.configuration.EBTreeConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

import java.util.LinkedList;
import java.util.Queue;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.AXIS;

public class SakuraTreeFeature extends EBBaseTreeFeature
{
    private static final double TRUNK_HEIGHT_PERCENT  = 0.30D; // What percent of the total height the main trunk extends

    public SakuraTreeFeature(Codec<EBTreeConfiguration> pCodec)
    {
        super(pCodec);
        BRANCHES_BASE_NUMBER  = 2;
        BRANCHES_EXTRA        = 4;
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
        super.place(pContext);
        BlockPos pos = pContext.origin().immutable();
        int max_tree_altitude = pos.getY() + actual_height + 4;
        int trunk_height = (int) (actual_height * TRUNK_HEIGHT_PERCENT);

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


        // place trunk;
        if (place1x1Trunk(pos, trunk_height, treeConfig.trunk_provider.getState(sourceRand, pos), level))
        {
            BlockPos branchpos = new BlockPos(pos.getX(), pos.getY() + trunk_height, pos.getZ());
            return generateBranches(level, sourceRand, branchpos, actual_height - trunk_height, actual_radius);
        }

        return false;
    } // end place()

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
        BlockState leaves = treeConfig.foliage_provider.getState(rand, branchpos);
        BlockState trunk = treeConfig.trunk_provider.getState(rand, branchpos);

        int branchCount = BRANCHES_BASE_NUMBER + rand.nextInt(BRANCHES_EXTRA);
        double curAngle = 0.0D;

        double[] average = { 0, 0, 0 };
        Queue<BlockPos> branches = new LinkedList<BlockPos>();

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
            BlockPos node = new BlockPos(x1 + branchpos.getX(), (int) thisHeight + branchpos.getY(), z1 + branchpos.getZ());

            // Add the branch end for leaf generation
            branches.add(node);

            // Generate the branch
            placeThinBlockLine(branchpos, node, trunk, world);
        } // end for

        // Place the branch tips
        for (BlockPos cluster : branches) {
            generateLeafCluster(world, cluster, 2, 2, leaves);
        }

        // Calculate the center position
        average[0] /= branchCount;
        average[1] = (branchCount / average[1]) + 2.3D;
        average[2] /= branchCount;

        // Generate the canopy
        BlockPos.MutableBlockPos leafpos = new BlockPos.MutableBlockPos();
        leafpos.set(average[0] + branchpos.getX(), branchpos.getY(), average[2] + branchpos.getZ());
        generateCanopy(world, rand, leafpos, radius, height, leaves);

        // Generate the center cone
        generateVerticalCone(world, branchpos, height - 1, .75, 2, leaves);
        return true;
    } // end generate branches

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
        int layers = height + 2;
        BlockPos.MutableBlockPos leafpos = pos.mutable();
        for (int y1 = pos.getY(), layer = 0; layer < layers; layer++, y1++)
        {
            leafpos.setY(y1);
            if (layer < 2)
            {
                generateCanopyLayer(world, rand, leafpos, radius * Math.cos((layer) / (height / 1.3)), 2 + (layer * 5), leaves);
            }
            else
            {
                generateCanopyLayer(world, rand, leafpos, radius * Math.cos((layer) / (height / 1.3)), 1000, leaves);
            }
        }
    } // end generateCanopy()

    /**
     * place top cone of leaves
     * @param world LevelAccessor
     * @param pos   base of top cone.
     * @param height depth of top cone
     * @param r1
     * @param r2
     * @param leaves BlockState of leaf block.
     */
    public void generateVerticalCone(LevelAccessor world, BlockPos pos, int height, double r1, double r2, BlockState leaves)
    {
        double ratio = (r2 - r1) / (height - 1);
        BlockPos.MutableBlockPos leafpos = pos.mutable();

        for (int offset = 0; offset < height; offset++)
        {
            leafpos.setY(pos.getY()+offset);
            placeLeavesCircle(leafpos, (ratio * offset) + r1, leaves, world);
        }
    } // end generateVerticalCone()


    public void placeThinBlockLine(BlockPos start, BlockPos end, BlockState logs, LevelAccessor world)
    {
        BlockPos.MutableBlockPos last = start.mutable();
        BlockPos.MutableBlockPos ppos = new BlockPos.MutableBlockPos();

        // Get the direction vector
        int[] direction = {
                start.getX() - end.getX(),
                start.getY() - end.getY(),
                start.getZ() - end.getZ()
        };

        if (Math.abs(direction[2]) > Math.abs(direction[1]) && Math.abs(direction[2]) > Math.abs(direction[0]))
        {
            // We are going to use the y axis as our major axis
            if (direction[2] >= 0)
            {
                for (int z = start.getZ(); z >= end.getZ(); z--)
                {
                    double m = (z - start.getZ()) / (double) direction[2];
                    int x = (int) (start.getX() + (direction[0] * m));
                    int y = (int) (start.getY() + (direction[1] * m));
                    ppos.set(x,y,z);
                    if (world.getBlockState(ppos).isAir())
                        this.setBlock(world, ppos, logs);
//                        setBlockAndNotifyAdequately(world, x, y, z, logBlock, logs.getItemDamage() | 8);

                    // Detect the distance
                    int dist = Math.abs(last.getX() - x) + Math.abs(last.getY() - y) + Math.abs(last.getZ() - z);
                    //LogHelper.info("Dist: %d", dist);
                    if (dist == 2)
                    {
                        ppos.set(last.getX(), last.getY(), z);
                        this.setBlock(world, ppos, logs);
                        //setBlockAndNotifyAdequately(world, last[0], last[1], z, logBlock, logs.getItemDamage() | 8);
                    }
                    else if (dist == 3)
                    {
                        if (direction[0] > 0)
                        {
                            ppos.set(x, last.getY(), last.getZ());
                            this.setBlock(world, ppos, logs);
//                            setBlockAndNotifyAdequately(world, x, last[1], last.getZ(), logBlock, logs.getItemDamage() | 8);
                            ppos.set(x, y, last.getZ());
                            this.setBlock(world, ppos, logs);
                            // setBlockAndNotifyAdequately(world, x, y, last.getZ(), logBlock, logs.getItemDamage() | 8);
                        }
                        else
                        {
                            ppos.set(last.getX(), y, last.getZ());
                            this.setBlock(world, ppos, logs);
//                            setBlockAndNotifyAdequately(world, last[0], y, last.getZ(), logBlock, logs.getItemDamage() | 8);
                            ppos.set(x,y,last.getZ());
                            this.setBlock(world, ppos, logs);
//                            setBlockAndNotifyAdequately(world, x, y, last.getZ(), logBlock, logs.getItemDamage() | 8);
                        }
                    }

                    last.set(x, y, z);
                } // end-for z
            } // end-if direction[2] >= 0
            else
            {
                for (int z = start.getZ(); z <= end.getZ(); z++)
                {
                    double m = (z - start.getZ()) / (double) direction[2];
                    int x = (int) (start.getX() + (direction[0] * m));
                    int y = (int) (start.getY() + (direction[1] * m));
                    ppos.set(x, y, z);
                    if (world.getBlockState(ppos).isAir())
                        this.setBlock(world,ppos,logs);
                        //setBlockAndNotifyAdequately(world, x, y, z, logBlock, logs.getItemDamage() | 8);

                    // Detect the distance
                    int dist = Math.abs(last.getX() - x) + Math.abs(last.getY() - y) + Math.abs(last.getZ() - z);
                    //LogHelper.info("Dist: %d", dist);
                    if (dist == 2)
                    {
                        ppos.set(last.getX(), last.getY(), z);
                        this.setBlock(world,ppos,logs);
//                        setBlockAndNotifyAdequately(world, last[0], last[1], z, logBlock, logs.getItemDamage() | 8);
                    }
                    else if (dist == 3)
                    {
                        if (direction[0] > 0)
                        {
                            ppos.set(x, last.getY(), last.getZ());
                            this.setBlock(world,ppos,logs);
//                            setBlockAndNotifyAdequately(world, x, last[1], last.getZ(), logBlock, logs.getItemDamage() | 8);
                            ppos.set(x, y, last.getZ());
                            this.setBlock(world,ppos,logs);
//                            setBlockAndNotifyAdequately(world, x, y, last.getZ(), logBlock, logs.getItemDamage() | 8);
                        }
                        else
                        {
                            ppos.set(last.getX(), y, last.getZ());
                            this.setBlock(world,ppos,logs);
//                            setBlockAndNotifyAdequately(world, last[0], y, last.getZ(), logBlock, logs.getItemDamage() | 8);
                            ppos.set(x, y, last.getZ());
                            this.setBlock(world,ppos,logs);
//                            setBlockAndNotifyAdequately(world, x, y, last.getZ(), logBlock, logs.getItemDamage() | 8);
                        }
                    }

                    last.set(x,y,z);
                } // end-for z
            } // end-else ! direction[2] >= 0
        } // end-if
        else if (Math.abs(direction[0]) > Math.abs(direction[0]))
        {
            // Treverse along the x axis
            if (direction[0] >= 0)
            {
                for (int x = start.getX(); x >= end.getX(); x--)
                {
                    double m = (x - start.getX()) / (double) direction[0];
                    int z = (int) (start.getZ() + (direction[2] * m));
                    int y = (int) (start.getY() + (direction[1] * m));
                    ppos.set(x,y,z);
                    if (world.getBlockState(ppos).isAir())
                        this.setBlock(world, ppos, logs.setValue(AXIS, Direction.Axis.X));  // we believe the old note.
//                        setBlockAndNotifyAdequately(world, x, y, z, logBlock, logs.getItemDamage() | 4);

                    // Detect the distance
                    int dist = Math.abs(last.getX() - x) + Math.abs(last.getY() - y) + Math.abs(last.getZ() - z);
                    if (dist == 2)
                    {
                        ppos.set(x, last.getY(), last.getZ());
                        this.setBlock(world, ppos, logs.setValue(AXIS, Direction.Axis.X));  // we believe the old note.
//                        setBlockAndNotifyAdequately(world, x, last[1], last.getZ(), logBlock, logs.getItemDamage() | 4);
                    }
                    else if (dist == 3)
                    {
                        if (direction[2] > 0)
                        {
                            ppos.set(last.getX(), last.getY(), z);
                            this.setBlock(world, ppos, logs.setValue(AXIS, Direction.Axis.X));  // we believe the old note.
//                            setBlockAndNotifyAdequately(world, last[0], last[1], z, logBlock, logs.getItemDamage() | 4);
                            ppos.set(last.getX(), y, z);
                            this.setBlock(world, ppos, logs.setValue(AXIS, Direction.Axis.X));  // we believe the old note.
//                            setBlockAndNotifyAdequately(world, last[0], y, z, logBlock, logs.getItemDamage() | 4);
                        }
                        else
                        {
                            ppos.set(last.getX(), y, last.getZ());
                            this.setBlock(world, ppos, logs.setValue(AXIS, Direction.Axis.X));  // we believe the old note.
//                            setBlockAndNotifyAdequately(world, last[0], y, last.getZ(), logBlock, logs.getItemDamage() | 4);
                            ppos.set(last.getX(), y, z);
                            this.setBlock(world, ppos, logs.setValue(AXIS, Direction.Axis.X));  // we believe the old note.
//                            setBlockAndNotifyAdequately(world, last[0], y, z, logBlock, logs.getItemDamage() | 4);
                        }
                    } // end else-if
                    last.set(x,y,z);
                } // end-for x
            } // end-if direction[0] >= 0
            else
            {
                for (int x = start.getX(); x <= end.getX(); x++)
                {
                    double m = (x - start.getX()) / (double) direction[0];
                    int z = (int) (start.getZ() + (direction[2] * m));
                    int y = (int) (start.getY() + (direction[1] * m));
                    ppos.set(x,y,z);
                    if (world.getBlockState(ppos).isAir())
                        this.setBlock(world, ppos, logs.setValue(AXIS, Direction.Axis.X));  // we believe the old note.
//                        setBlockAndNotifyAdequately(world, x, y, z, logBlock, logs.getItemDamage() | 4);

                    // Detect the distance
                    int dist = Math.abs(last.getX() - x) + Math.abs(last.getY() - y) + Math.abs(last.getZ() - z);
                    if (dist == 2)
                    {
                        ppos.set(x, last.getY(), last.getZ());
                        this.setBlock(world, ppos, logs.setValue(AXIS, Direction.Axis.X));  // we believe the old note.
//                        setBlockAndNotifyAdequately(world, x, last[1], last.getZ(), logBlock, logs.getItemDamage() | 4);
                    }
                    else if (dist == 3)
                    {
                        if (direction[2] > 0)
                        {
                            ppos.set(last.getX(), last.getY(), z);
                            this.setBlock(world, ppos, logs.setValue(AXIS, Direction.Axis.X));  // we believe the old note.
//                            setBlockAndNotifyAdequately(world, last[0], last[1], z, logBlock, logs.getItemDamage() | 4);
                            ppos.set(last.getX(), y, z);
                            this.setBlock(world, ppos, logs.setValue(AXIS, Direction.Axis.X));  // we believe the old note.
//                            setBlockAndNotifyAdequately(world, last[0], y, z, logBlock, logs.getItemDamage() | 4);
                        }
                        else
                        {
                            ppos.set(last.getX(), y, last.getZ());
                            this.setBlock(world, ppos, logs.setValue(AXIS, Direction.Axis.X));  // we believe the old note.
//                            setBlockAndNotifyAdequately(world, last[0], y, last.getZ(), logBlock, logs.getItemDamage() | 4);
                            ppos.set(last.getX(), y, z);
                            this.setBlock(world, ppos, logs.setValue(AXIS, Direction.Axis.X));  // we believe the old note.
//                            setBlockAndNotifyAdequately(world, last[0], y, z, logBlock, logs.getItemDamage() | 4);
                        }
                    }
                    last.set(x,y,z);
                } // end-for x
            } // end-else ! direction[0] >= 0
        } // end-else-if
        else
        {
            // We will use the y axis as our major axis
            if (direction[1] >= 0)
            {
                for (int y = start.getY(); y >= end.getY(); y--)
                {
                    double m = (y - start.getY()) / (double) direction[1];
                    int x = (int) (start.getX() + (direction[0] * m));
                    int z = (int) (start.getZ() + (direction[2] * m));
                    ppos.set(x,y,z);
                    if (world.getBlockState(ppos).isAir())
                        this.setBlock(world, ppos, logs);
//                        setBlockAndNotifyAdequately(world, x, y, z, logBlock, logs.getItemDamage());

                    // Detect the distance
                    int dist = Math.abs(last.getX() - x) + Math.abs(last.getY() - y) + Math.abs(last.getZ() - z);
                    if (dist == 2)
                    {
                        ppos.set(last.getX(), y, last.getZ());
                        this.setBlock(world, ppos, logs);
//                        setBlockAndNotifyAdequately(world, last[0], y, last[2], logBlock, logs.getItemDamage());
                    }
                    else if (dist == 3)
                    {
                        if (direction[2] > 0)
                        {
                            ppos.set(last.getX(), last.getY(), z);
                            this.setBlock(world, ppos, logs);
//                            setBlockAndNotifyAdequately(world, last[0], last[1], z, logBlock, logs.getItemDamage());
                            ppos.set(x, last.getY(), z);
                            this.setBlock(world, ppos, logs);
//                            setBlockAndNotifyAdequately(world, x, last[1], z, logBlock, logs.getItemDamage());
                        }
                        else
                        {
                            ppos.set(x, last.getY(), last.getZ());
                            this.setBlock(world, ppos, logs);
//                            setBlockAndNotifyAdequately(world, x, last[1], last.getZ(), logBlock, logs.getItemDamage());
                            ppos.set(x, last.getY(), z);
                            this.setBlock(world, ppos, logs);
//                            setBlockAndNotifyAdequately(world, x, last[1], z, logBlock, logs.getItemDamage());
                        }
                    } // end else-if
                    last.set(x,y,z);
                } // end-for y
            } // end-if direction[1] >= 0
            else
            {
                for (int y = start.getY(); y <= end.getY(); y++)
                {
                    double m = (y - start.getY()) / (double) direction[1];
                    int x = (int) (start.getX() + (direction[0] * m));
                    int z = (int) (start.getZ() + (direction[2] * m));
                    ppos.set(x, y, z);
                    if (world.getBlockState(ppos).isAir())
                        this.setBlock(world, ppos, logs);
//                        setBlockAndNotifyAdequately(world, x, y, z, logBlock, logs.getItemDamage());

                    // Detect the distance
                    int dist = Math.abs(last.getX() - x) + Math.abs(last.getY() - y) + Math.abs(last.getZ() - z);
                    if (dist == 2)
                    {
                        ppos.set(last.getX(), y, last.getZ());
                        this.setBlock(world, ppos, logs);
//                        setBlockAndNotifyAdequately(world, last[0], y, last[2], logBlock, logs.getItemDamage());
                    }
                    else if (dist == 3)
                    {
                        if (direction[2] > 0)
                        {
                            ppos.set(last.getX(), last.getY(), z);
                            this.setBlock(world, ppos, logs);
//                            setBlockAndNotifyAdequately(world, last[0], last[1], z, logBlock, logs.getItemDamage());
                            ppos.set(x, last.getY(), z);
                            this.setBlock(world, ppos, logs);
//                            setBlockAndNotifyAdequately(world, x, last[1], z, logBlock, logs.getItemDamage());
                        }
                        else
                        {
                            ppos.set(x, last.getY(), last.getZ());
                            this.setBlock(world, ppos, logs);
//                            setBlockAndNotifyAdequately(world, x, last[1], last[2], logBlock, logs.getItemDamage());
                            ppos.set(x, last.getY(), z);
                            this.setBlock(world, ppos, logs);
//                            setBlockAndNotifyAdequately(world, x, last[1], z, logBlock, logs.getItemDamage());
                        }
                    }
                    last.set(x,y,z);
                } // end-for y
            } // end-else
        } // end-else

    } // end placeThinBlockLine()

} // end class
