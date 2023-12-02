package net.extrabiomes.world.features.tree;

import com.mojang.serialization.Codec;
import net.extrabiomes.world.features.configuration.EBTreeConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.TreeFeature;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public abstract class EBKneeTreeFeature extends EBBaseTreeFeature
{
    protected int CLUSTER_DIAMETER;    // How wide should the leaf cluster be generated
    protected int CLUSTER_DIAMETER_VARIANCE; // How many extra blocks can be added to the leaf cluster.
    protected int CLUSTER_HEIGHT;          // How tall should the leaf cluster be generated
    protected int CLUSTER_HEIGHT_VARIANCE; // How many extra layers can be added to the leaf cluster.
    protected double TRUNK_HEIGHT_PERCENT; // What percent of the total height the main trunk extends
    protected double TRUNK_BRANCHES_START; // How far up the tree the trunk branches start
    protected int bonusHeight = 0;

    public EBKneeTreeFeature(Codec<EBTreeConfiguration> pCodec)
    {
        super(pCodec);
    }

    /**
     * generate cypress knees around base of tree.
     *
     * @param world       LevelAccessor
     * @param rand        RandomSource
     * @param pos         BlockPos of base of tree
     * @param bonusHeight number of water blocks.
     */
    protected void generateKnees(LevelAccessor world, RandomSource rand, BlockPos pos, int bonusHeight)
    {
        BlockPos.MutableBlockPos placePos = pos.mutable();
        BlockState log = treeConfig.trunk_provider.getState(rand, pos);
        BlockState knee = treeConfig.root_provider.getState(rand, pos);

        // EAST-facing knees (on WEST side of tree)
        switch (rand.nextInt(11)) {
            case 0:
            case 1:
            case 2:
            case 3:
                placePos.set(pos.getX() - 1, pos.getY(), pos.getZ());
                placeKnee(placePos, ((rand.nextInt(3) != 0) ? 1 : 2) + bonusHeight, Direction.EAST, log, knee, world);
                break;
            case 4:
            case 5:
            case 6:
            case 7:
                placePos.set(pos.getX() - 1, pos.getY(), pos.getZ() + 1);
                placeKnee(placePos, ((rand.nextInt(3) != 0) ? 1 : 2) + bonusHeight, Direction.EAST, log, knee, world);
                break;
            case 8:
                placePos.set(pos.getX() - 1, pos.getY(), pos.getZ());
                placeKnee(placePos, ((rand.nextInt(5) != 0) ? 1 : 2) + bonusHeight, Direction.EAST, log, knee, world);
                placePos.set(pos.getX() - 1, pos.getY(), pos.getZ() + 1);
                placeKnee(placePos, ((rand.nextInt(2) != 0) ? 1 : 2) + bonusHeight, Direction.EAST, log, knee, world);
                break;
            default:
                break;
        } // end-switch EAST

        // SOUTH
        switch (rand.nextInt(11)) {
            case 0:
            case 1:
            case 2:
            case 3:
                placePos.set(pos.getX(), pos.getY(), pos.getZ() - 1);
                placeKnee(placePos, ((rand.nextInt(3) != 0) ? 1 : 2) + bonusHeight, Direction.SOUTH, log, knee, world);
                break;
            case 4:
            case 5:
            case 6:
            case 7:
                placePos.set(pos.getX() + 1, pos.getY(), pos.getZ() - 1);
                placeKnee(placePos, ((rand.nextInt(3) != 0) ? 1 : 2) + bonusHeight, Direction.SOUTH, log, knee, world);
                break;
            case 8:
                placePos.set(pos.getX(), pos.getY(), pos.getZ() - 1);
                placeKnee(placePos, ((rand.nextInt(3) != 0) ? 1 : 2) + bonusHeight, Direction.SOUTH, log, knee, world);
                placePos.set(pos.getX() + 1, pos.getY(), pos.getZ() - 1);
                placeKnee(placePos, ((rand.nextInt(5) != 0) ? 1 : 2) + bonusHeight, Direction.SOUTH, log, knee, world);
                break;
            default:
                break;
        } // end switch SOUTH

        // WEST
        switch (rand.nextInt(11)) {
            case 0:
            case 1:
            case 2:
            case 3:
                placePos.set(pos.getX() + 2, pos.getY(), pos.getZ());
                placeKnee(placePos, ((rand.nextInt(3) != 0) ? 1 : 2) + bonusHeight, Direction.WEST, log, knee, world);
                break;
            case 4:
            case 5:
            case 6:
            case 7:
                placePos.set(pos.getX() + 2, pos.getY(), pos.getZ() + 1);
                placeKnee(placePos, ((rand.nextInt(3) != 0) ? 1 : 2) + bonusHeight, Direction.WEST, log, knee, world);
                break;
            case 8:
                placePos.set(pos.getX() + 2, pos.getY(), pos.getZ());
                placeKnee(placePos, ((rand.nextInt(3) != 0) ? 1 : 2) + bonusHeight, Direction.WEST, log, knee, world);
                placePos.set(pos.getX() + 2, pos.getY(), pos.getZ() + 1);
                placeKnee(placePos, ((rand.nextInt(3) != 0) ? 1 : 2) + bonusHeight, Direction.WEST, log, knee, world);
                break;
            default:
                break;
        } // end switch WEST

        // NORTH
        switch (rand.nextInt(11)) {
            case 0:
            case 1:
            case 2:
            case 3:
                placePos.set(pos.getX(), pos.getY(), pos.getZ() + 2);
                placeKnee(placePos, ((rand.nextInt(3) != 0) ? 1 : 2) + bonusHeight, Direction.NORTH, log, knee, world);
                break;
            case 4:
            case 5:
            case 6:
            case 7:
                placePos.set(pos.getX() + 1, pos.getY(), pos.getZ() + 2);
                placeKnee(placePos, ((rand.nextInt(3) != 0) ? 1 : 2) + bonusHeight, Direction.NORTH, log, knee, world);
                break;
            case 8:
                placePos.set(pos.getX(), pos.getY(), pos.getZ() + 2);
                placeKnee(placePos, ((rand.nextInt(2) != 0) ? 1 : 2) + bonusHeight, Direction.NORTH, log, knee, world);
                placePos.set(pos.getX() + 1, pos.getY(), pos.getZ() + 2);
                placeKnee(placePos, ((rand.nextInt(5) != 0) ? 1 : 2) + bonusHeight, Direction.NORTH, log, knee, world);
                break;
            default:
                break;
        } // end switch NORTH
    } // end generateKnees()

    /**
     * @param pos          knee position
     * @param height
     * @param direction
     * @param log          trunk BlockState
     * @param default_knee cypress knee BlockState
     * @param world        LevelAccessor
     */
    public void placeKnee(BlockPos pos, int height, Direction direction, BlockState log, BlockState default_knee,
                          LevelAccessor world)
    {
        // bail if not one of swne
        if (direction.get2DDataValue() < 0) {
            return;
        }
        Direction new_facing = direction.getOpposite();
        BlockState knee = default_knee.setValue(BlockStateProperties.FACING, new_facing);

        BlockPos.MutableBlockPos placePos = pos.mutable();

        for (int y1 = pos.getY() - 1; y1 > 1; y1--) {
            placePos.setY(y1);
            if (TreeFeature.validTreePos(world, placePos)) {
                this.setBlock(world, placePos, log);
            }
            else {
                break;
            }
        } // end-for
        for (int y1 = pos.getY(); y1 < pos.getY() + height - 1; y1++) {
            placePos.setY(y1);
            this.setBlock(world, placePos, log);
        } // end-for

        // place knee on top
        placePos.setY(pos.getY() + height - 1);
        this.setBlock(world, placePos, knee);
    } // end placeKnee()

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
        int branchCount = BRANCHES_BASE_NUMBER + rand.nextInt(BRANCHES_EXTRA);
        // Make sure that the width is even
        int width = (int) (((int) radius % 2 == 1) ? radius + 1 : radius);
        // Cache the offset
        int offset = width / 2;

        // The max distance for branches to generate
        int branchStart = (int) (height * TRUNK_BRANCHES_START) + this.bonusHeight;
        int maxBranchHeight = height - ((int) (height * TRUNK_BRANCHES_START)) - 3;
        int[] start = {0, 0, 0};
        int[] end = {0, 0, 0};
        Queue<int[]> branches = new LinkedList<int[]>();

        // Generate some test branches
        for (int branch = 0; branch < branchCount; branch++) {
            // The end position
            end[0] = rand.nextInt(width + 1) - offset + branchpos.getX();
            end[1] = rand.nextInt(maxBranchHeight) + branchStart + branchpos.getY();
            end[2] = rand.nextInt(width + 1) - offset + branchpos.getZ();

            // Max of tree height
            // Min of branch start
            start[1] = Math.max(branchStart + branchpos.getY(),
                    Math.min(height, rand.nextInt(
                            Math.max(end[1] - branchStart - branchpos.getY(), 1))
                            + branchpos.getY()));

            if (end[0] > branchpos.getX() && end[2] > branchpos.getZ()) {
                start[0] = branchpos.getX() + 1;
                start[2] = branchpos.getZ() + 1;
            }
            else if (end[0] > branchpos.getX()) {
                start[0] = branchpos.getX() + 1;
                start[2] = branchpos.getZ();
            }
            else if (end[2] > branchpos.getZ()) {
                start[0] = branchpos.getX();
                start[2] = branchpos.getZ() + 1;
            }
            else {
                start[0] = branchpos.getX();
                start[2] = branchpos.getZ();
            }

            // Place the branch
            placeBlockLine(start, end, treeConfig.trunk_provider.getState(rand, branchpos), world);

            int[] node = new int[]{end[0], end[1], end[2]};

            // Add the branch end for leaf generation
            branches.add(node);
        } // end-for branch

        // Generate the leaf clusters
        Iterator<int[]> itt = branches.iterator();
        BlockPos.MutableBlockPos leafPos = new BlockPos.MutableBlockPos();

        while (itt.hasNext()) {
            int[] cluster = itt.next();
            leafPos.set(cluster[0], cluster[1], cluster[2]);
            generateLeafCluster(world, leafPos, CLUSTER_HEIGHT + rand.nextInt(CLUSTER_HEIGHT_VARIANCE),
                    CLUSTER_DIAMETER + rand.nextInt(CLUSTER_DIAMETER_VARIANCE),
                    treeConfig.foliage_provider.getState(rand, leafPos));
        }
        return true;
    } // end generateBranches

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

    }
}
