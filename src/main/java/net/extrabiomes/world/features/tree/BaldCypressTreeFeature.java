package net.extrabiomes.world.features.tree;

import com.mojang.serialization.Codec;
import net.extrabiomes.world.features.configuration.EBTreeConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.TreeFeature;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BaldCypressTreeFeature extends EBBaseTreeFeature
{
    protected final static int CLUSTER_DIAMETER = 3;    // How wide should the leaf cluster be generated
    protected static final int CLUSTER_DIAMETER_VARIANCE = 3;    // How many extra blocks can be added to the leaf cluster.
    protected static final int CLUSTER_HEIGHT            = 1;    // How tall should the leaf cluster be generated
    protected static final int CLUSTER_HEIGHT_VARIANCE   = 3;    // How many extra layers can be added to the leaf cluster.
    protected static final double TRUNK_HEIGHT_PERCENT  = 0.75D; // What percent of the total height the main trunk extends
    protected static final double TRUNK_BRANCHES_START  = 0.25D; // How far up the tree the trunk branches start
    protected int waterLevel = 0;

    public BaldCypressTreeFeature(Codec<EBTreeConfiguration> pCodec)
    {
        super(pCodec);
        BRANCHES_BASE_NUMBER = 15;
        BRANCHES_EXTRA = 10;
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
        this.actual_radius = this.CANOPY_WIDTH + sourceRand.nextInt(this.CANOPY_WIDTH_VARIANCE);

        // find water level, if any.
        this.waterLevel = waterLevelCheck(pos);

        // adjust position to actual bottom.
        pos = new BlockPos(pos.getX(), pos.getY() - waterLevel, pos.getZ());

        // determine trunk_height
        int trunk_height = (int) (actual_height * TRUNK_HEIGHT_PERCENT) + waterLevel;

        // height check
        if (pos.getY() < level.getMinBuildHeight() + 1 || max_tree_altitude > level.getMaxBuildHeight())
        {
            return false;
        }

        // Trunk check
        if (!check2x2Trunk(pos, trunk_height, level, true)) {
            return false;
        }

        // leaf & branch check
        if (!checkRoughClearance(pos, (int) actual_radius+1, trunk_height, max_tree_altitude)) {
            return false;
        }

        // place the 'dirt' blocks.
        BlockState dirt = treeConfig.dirt_provider.getState(sourceRand, pos.below());
        for (BlockPos here : find2x2(pos.below())) {
            this.setBlock(level, here, dirt);
        }

        // place 2x2 trunk
        // place the trunk -- TODO adapt to quarter logs.
        BlockState log_nw = treeConfig.trunk_provider.getState(sourceRand, pos);
        BlockState log_ne = treeConfig.trunk_provider.getState(sourceRand, pos);
        BlockState log_se = treeConfig.trunk_provider.getState(sourceRand, pos);
        BlockState log_sw = treeConfig.trunk_provider.getState(sourceRand, pos);
        BlockPos.MutableBlockPos nw_pos = new BlockPos.MutableBlockPos();
        BlockPos.MutableBlockPos ne_pos = new BlockPos.MutableBlockPos();
        BlockPos.MutableBlockPos se_pos = new BlockPos.MutableBlockPos();
        BlockPos.MutableBlockPos sw_pos = new BlockPos.MutableBlockPos();

        for (int offset = 0; offset <= trunk_height; offset++)
        {
            nw_pos.set(pos.getX(), pos.getY() + offset, pos.getZ());
            ne_pos.set(pos.getX()+1, pos.getY() + offset, pos.getZ());
            se_pos.set(pos.getX()+1, pos.getY() + offset, pos.getZ()+1);
            sw_pos.set(pos.getX(), pos.getY() + offset, pos.getZ()+1);
            this.setBlock(level, nw_pos, log_nw);
            this.setBlock(level, ne_pos, log_ne);
            this.setBlock(level, se_pos, log_se);
            this.setBlock(level, sw_pos, log_sw);
        }

        // Draw the knees
        generateKnees(level, sourceRand, pos, waterLevel);

        // Generate the branches
        generateBranches(level, sourceRand, pos, actual_height, actual_radius);

        // place the topper leaves
        BlockPos.MutableBlockPos leafPos = pos.mutable();
        leafPos.setY((int) (actual_height * TRUNK_HEIGHT_PERCENT) + pos.getY());
        generateLeafCluster(level, leafPos, 4 + sourceRand.nextInt(CLUSTER_HEIGHT_VARIANCE),
                            4 + sourceRand.nextInt(CLUSTER_DIAMETER_VARIANCE),
                            treeConfig.foliage_provider.getState(sourceRand, leafPos));

        return true;
    } // end place()

    /**
     * generate cypress knees around base of tree.
     * @param world LevelAccessor
     * @param rand RandomSource
     * @param pos BlockPos of base of tree
     * @param bonusHeight number of water blocks.
     */
    protected void generateKnees(LevelAccessor world, RandomSource rand, BlockPos pos, int bonusHeight)
    {
        BlockPos.MutableBlockPos placePos = pos.mutable();
        BlockState log = treeConfig.trunk_provider.getState(rand, pos);
        BlockState knee = treeConfig.root_provider.getState(rand, pos);

        // EAST-facing knees (on WEST side of tree)
        switch (rand.nextInt(11))
        {
            case 0:  case 1: case 2: case 3:
                placePos.set(pos.getX()-1, pos.getY(), pos.getZ());
                placeKnee(placePos, ((rand.nextInt(3) != 0) ? 1 : 2) + bonusHeight, Direction.EAST, log, knee, world);
                break;
            case 4: case 5: case 6: case 7:
                placePos.set(pos.getX()-1, pos.getY(), pos.getZ()+1);
                placeKnee(placePos, ((rand.nextInt(3) != 0) ? 1 : 2) + bonusHeight,  Direction.EAST, log, knee, world);
                break;
            case 8:
                placePos.set(pos.getX()-1, pos.getY(), pos.getZ());
                placeKnee(placePos, ((rand.nextInt(5) != 0) ? 1 : 2) + bonusHeight,Direction.EAST, log, knee, world);
                placePos.set(pos.getX()-1, pos.getY(), pos.getZ()+1);
                placeKnee(placePos, ((rand.nextInt(2) != 0) ? 1 : 2) + bonusHeight,Direction.EAST, log, knee, world);
                break;
            default:
                break;
        } // end-switch EAST

        // SOUTH
        switch (rand.nextInt(11))
        {
            case 0: case 1: case 2: case 3:
                placePos.set(pos.getX(), pos.getY(), pos.getZ()-1);
                placeKnee(placePos, ((rand.nextInt(3) != 0) ? 1 : 2) + bonusHeight, Direction.SOUTH, log, knee, world);
                break;
            case 4: case 5: case 6: case 7:
                placePos.set(pos.getX()+1, pos.getY(), pos.getZ()-1);
                placeKnee(placePos, ((rand.nextInt(3) != 0) ? 1 : 2) + bonusHeight, Direction.SOUTH, log, knee, world);
                break;
            case 8:
                placePos.set(pos.getX(), pos.getY(), pos.getZ()-1);
                placeKnee(placePos, ((rand.nextInt(3) != 0) ? 1 : 2) + bonusHeight, Direction.SOUTH, log, knee, world);
                placePos.set(pos.getX()+1, pos.getY(), pos.getZ()-1);
                placeKnee(placePos, ((rand.nextInt(5) != 0) ? 1 : 2) + bonusHeight, Direction.SOUTH, log, knee, world);
                break;
            default:
                break;
        } // end switch SOUTH

        // WEST
        switch (rand.nextInt(11))
        {
            case 0: case 1: case 2: case 3:
                placePos.set(pos.getX()+2, pos.getY(), pos.getZ());
                placeKnee(placePos, ((rand.nextInt(3) != 0) ? 1 : 2) + bonusHeight, Direction.WEST, log, knee, world);
                break;
            case 4: case 5: case 6: case 7:
                placePos.set(pos.getX()+2, pos.getY(), pos.getZ()+1);
                placeKnee(placePos, ((rand.nextInt(3) != 0) ? 1 : 2) + bonusHeight, Direction.WEST, log, knee, world);
                break;
            case 8:
                placePos.set(pos.getX()+2, pos.getY(), pos.getZ());
                placeKnee(placePos, ((rand.nextInt(3) != 0) ? 1 : 2) + bonusHeight, Direction.WEST, log, knee, world);
                placePos.set(pos.getX()+2, pos.getY(), pos.getZ()+1);
                placeKnee(placePos, ((rand.nextInt(3) != 0) ? 1 : 2) + bonusHeight, Direction.WEST, log, knee, world);
                break;
            default:
                break;
        } // end switch WEST

        // NORTH
        switch (rand.nextInt(11))
        {
            case 0: case 1: case 2: case 3:
                placePos.set(pos.getX(), pos.getY(), pos.getZ()+2);
                placeKnee(placePos, ((rand.nextInt(3) != 0) ? 1 : 2) + bonusHeight, Direction.NORTH, log, knee, world);
                break;
            case 4: case 5: case 6: case 7:
                placePos.set(pos.getX()+1, pos.getY(), pos.getZ()+2);
                placeKnee(placePos, ((rand.nextInt(3) != 0) ? 1 : 2) + bonusHeight, Direction.NORTH, log, knee, world);
                break;
            case 8:
                placePos.set(pos.getX(), pos.getY(), pos.getZ()+2);
                placeKnee(placePos, ((rand.nextInt(2) != 0) ? 1 : 2) + bonusHeight, Direction.NORTH, log, knee, world);
                placePos.set(pos.getX()+1, pos.getY(), pos.getZ()+2);
                placeKnee(placePos, ((rand.nextInt(5) != 0) ? 1 : 2) + bonusHeight, Direction.NORTH, log, knee, world);
                break;
            default:
                break;
        } // end switch NORTH
    } // end generateKnees()

    /**
     *
     * @param pos knee position
     * @param height
     * @param direction
     * @param log   trunk BlockState
     * @param default_knee  cypress knee BlockState
     * @param world LevelAccessor
     */
    public void placeKnee(BlockPos pos, int height, Direction direction, BlockState log, BlockState default_knee, LevelAccessor world)
    {
        // bail if not one of swne
        if (direction.get2DDataValue() < 0) return;
        Direction new_facing = direction.getOpposite();
        BlockState knee = default_knee.setValue(BlockStateProperties.FACING, new_facing);

        BlockPos.MutableBlockPos placePos = pos.mutable();

        for (int y1 = pos.getY()-1; y1 > 1; y1--)
        {
            placePos.setY(y1);
            if (TreeFeature.validTreePos(world, placePos))
            {
                this.setBlock(world, placePos, log);
            }
            else {
                break;
            }
        } // end-for
        for (int y1=pos.getY(); y1 < pos.getY() + height - 1; y1++)
        {
            placePos.setY(y1);
            this.setBlock(world, placePos, log);
        } // end-for

        // place knee on top
        placePos.setY(pos.getY() + height - 1);
        this.setBlock(world, placePos, knee);
    } // end placeKnee()


    /**
     * Find bottom of swamp water.
     * @param pos BlockPos of base of tree.
     * @return number of blocks of water above first dirt block.
     */
    protected int waterLevelCheck(BlockPos pos)
    {
        int water_level = 0;
        BlockPos.MutableBlockPos checkPos = pos.mutable();

        for (int yy = pos.getY()-1; (yy > pos.getY()-6) && (yy > this.level.getMinBuildHeight()); yy--)
        {
            checkPos.setY(yy);
            BlockState thisHere = this.level.getBlockState(checkPos);
            if (!thisHere.getFluidState().is(FluidTags.WATER)) {
                break;
            }
            water_level++;
        }
        return water_level;
    } // end waterLevelCheck()

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
        int branchStart = (int) (height * TRUNK_BRANCHES_START) + waterLevel;
        int maxBranchHeight = height - ((int) (height * TRUNK_BRANCHES_START)) - 3;
        int[] start = { 0, 0, 0 };
        int[] end = { 0, 0, 0 };
        Queue<int[]> branches = new LinkedList<int[]>();

        // Generate some test branches
        for (int branch = 0; branch < branchCount; branch++)
        {
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

            if (end[0] > branchpos.getX() && end[2] > branchpos.getZ())
            {
                start[0] = branchpos.getX() + 1;
                start[2] = branchpos.getZ() + 1;
            }
            else if (end[0] > branchpos.getX())
            {
                start[0] = branchpos.getX() + 1;
                start[2] = branchpos.getZ();
            }
            else if (end[2] > branchpos.getZ())
            {
                start[0] = branchpos.getX();
                start[2] = branchpos.getZ() + 1;
            }
            else
            {
                start[0] = branchpos.getX();
                start[2] = branchpos.getZ();
            }

            // Place the branch
            placeBlockLine(start, end, treeConfig.trunk_provider.getState(rand, branchpos), world);

            int[] node = new int[] { end[0], end[1], end[2] };

            // Add the branch end for leaf generation
            branches.add(node);
        } // end-for branch

        // Generate the leaf clusters
        Iterator<int[]> itt = branches.iterator();
        BlockPos.MutableBlockPos leafPos = new BlockPos.MutableBlockPos();

        while (itt.hasNext())
        {
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
} // end class
