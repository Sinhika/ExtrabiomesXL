package net.extrabiomes.world.features.tree;

import com.mojang.serialization.Codec;
import net.extrabiomes.world.features.configuration.EBTreeConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.TreeFeature;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.AXIS;

public abstract class EBBaseTreeFeature extends Feature<EBTreeConfiguration>
{

    public EBBaseTreeFeature(Codec<EBTreeConfiguration> pCodec) {
        super(pCodec);
    }

    /**
     * Check that there is room for a tree trunk at position pos, of height height.
     * @param pos position of tree base.
     * @param height total height of tree.
     * @param logs BlockState of trunk block.
     * @param world level accessor.
     * @return true if enough room, false if not.
     */
    public boolean check1x1Trunk(BlockPos pos, int height, BlockState logs, LevelAccessor world)
    {
        BlockPos.MutableBlockPos checkPos = pos.mutable();

        for (int y1 = pos.getY() + 1; y1 < pos.getY() + height; y1++)
        {
            checkPos.setY(y1);
            if (!TreeFeature.validTreePos(world, checkPos))
                return false;
        }
        return true;
    } // end check1x1Trunk()

    /**
     * Actually place a 1x1 tree trunk.
     * @param pos
     * @param height
     * @param logs
     * @param world
     * @return
     */
    public boolean place1x1Trunk(BlockPos pos, int height, BlockState logs, LevelAccessor world)
    {
        BlockPos.MutableBlockPos placePos = pos.mutable();

        // Place the wood blocks
        for (int y1 = pos.getY(); y1 < pos.getY() + height; y1++)
        {
            placePos.setY(y1);
            world.setBlock(placePos, logs, 2);
        }

        return true;
    }

    /**
     * Check that a line of nodes has clearance for branches.
     * @param start
     * @param end
     * @param world
     * @return
     */
    public boolean checkBlockLine(int[] start, int[] end, LevelAccessor world)
    {
        if (start.length != 3 || end.length != 3)
            return false;
        BlockPos.MutableBlockPos bpos = new BlockPos.MutableBlockPos();

        // Get the direction vector
        int[] direction = {
                start[0] - end[0],
                start[1] - end[1],
                start[2] - end[2]
        };
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
                    if (!TreeFeature.validTreePos(world, bpos.set(x,y,z)))
                        return false;
                }
            }
            else
            {
                for (int z = start[2]; z <= end[2]; z++)
                {
                    double m = (z - start[2]) / (double) direction[2];
                    int x = (int) (start[0] + (direction[0] * m));
                    int y = (int) (start[1] + (direction[1] * m));
                    if (!TreeFeature.validTreePos(world, bpos.set(x,y,z)))
                        return false;
                }
            }
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
                    if (!TreeFeature.validTreePos(world, bpos.set(x,y,z)))
                        return false;
                }
            }
            else
            {
                for (int x = start[0]; x <= end[0]; x++)
                {
                    double m = (x - start[0]) / (double) direction[0];
                    int z = (int) (start[2] + (direction[2] * m));
                    int y = (int) (start[1] + (direction[1] * m));
                    if (!TreeFeature.validTreePos(world, bpos.set(x,y,z)))
                        return false;
                }
            }
        } // end-else-if
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
                    if (!TreeFeature.validTreePos(world, bpos.set(x,y,z)))
                        return false;
                }
            }
            else
            {
                for (int y = start[1]; y <= end[1]; y++)
                {
                    double m = (y - start[1]) / (double) direction[1];
                    int x = (int) (start[0] + (direction[0] * m));
                    int z = (int) (start[2] + (direction[2] * m));
                    if (!TreeFeature.validTreePos(world, bpos.set(x,y,z)))
                        return false;
                }
            }
        } // end-else

        return true;
    } // end checkBlockLine()

    public boolean checkLeafCluster(LevelAccessor world, BlockPos pos, int height, int radius)
    {
        BlockPos.MutableBlockPos checkpos = pos.mutable();
        for (int layer = -height; layer <= height; layer++)
        {
            checkpos.setY(pos.getY() + layer);
            if (!checkLeavesCircle(checkpos, radius * Math.cos(layer / (height / 1.3)), world))
                return false;
        }

        return true;
    } // end checkLeafCluster()

    public boolean checkLeavesCircle(BlockPos pos, double r, LevelAccessor world)
    {
        double dist = r * r;
        BlockPos.MutableBlockPos checkpos = pos.mutable();

        for (double z1 = Math.floor(-r); z1 < r + 1; z1++)
        {
            for (double x1 = Math.floor(-r); x1 < r + 1; x1++)
            {
                int x2 = (int) (x1 + pos.getX());
                int z2 = (int) (z1 + pos.getY());

                //final Block block = world.getBlock(x2, y, z2);
                checkpos.set(x2, pos.getY(), z2);

                if (((x1 * x1) + (z1 * z1)) <= dist)
                {
                    if (! TreeFeature.isAirOrLeaves(world, checkpos))
                        return false;
                }
            } // end-for x1
        } // end-for z1

        return true;
    } // end checkLeavesCircle()

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
                    if (TreeFeature.validTreePos(world, bpos)) {
                        world.setBlock(bpos, logBlock.setValue(AXIS, Direction.Axis.Z), 2);
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
                    if (TreeFeature.validTreePos(world, bpos)) {
                        world.setBlock(bpos, logBlock.setValue(AXIS, Direction.Axis.Z), 2);
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
                    if (TreeFeature.validTreePos(world, bpos)) {
                        world.setBlock(bpos, logBlock.setValue(AXIS, Direction.Axis.X), 2);
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
                    if (TreeFeature.validTreePos(world, bpos)) {
                        world.setBlock(bpos, logBlock.setValue(AXIS, Direction.Axis.X), 2);
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
                    if (TreeFeature.validTreePos(world, bpos)) {
                        world.setBlock(bpos, logBlock, 2);
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
                    if (TreeFeature.validTreePos(world, bpos)) {
                        world.setBlock(bpos, logBlock, 2);
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

                if ((((x1 * x1) + (z1 * z1)) <= dist) && TreeFeature.isAirOrLeaves(world, leafpos))
                {
                    world.setBlock(leafpos, leaves, 2);
                }
            } // end-for x1
        } // end-for z1
    } // end placeLeavesCircle()

} // end class
