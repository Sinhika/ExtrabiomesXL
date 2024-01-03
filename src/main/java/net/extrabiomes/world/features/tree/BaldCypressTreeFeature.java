package net.extrabiomes.world.features.tree;

import com.mojang.serialization.Codec;
import net.extrabiomes.world.features.configuration.EBTreeConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class BaldCypressTreeFeature extends EBKneeTreeFeature
{

    public BaldCypressTreeFeature(Codec<EBTreeConfiguration> pCodec)
    {
        super(pCodec);
        BRANCHES_BASE_NUMBER = 15;
        BRANCHES_EXTRA = 10;
        CLUSTER_DIAMETER = 3;
        CLUSTER_DIAMETER_VARIANCE = 3;
        CLUSTER_HEIGHT = 1;
        CLUSTER_HEIGHT_VARIANCE = 3;
        TRUNK_HEIGHT_PERCENT = 0.75D;
        TRUNK_BRANCHES_START = 0.25D;
    } // end ctor

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
        this.bonusHeight = waterLevelCheck(pos);

        // adjust position to actual bottom.
        pos = new BlockPos(pos.getX(), pos.getY() - bonusHeight, pos.getZ());

        // determine trunk_height
        int trunk_height = (int) (actual_height * TRUNK_HEIGHT_PERCENT) + bonusHeight;

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
            this.posTrunks.add(nw_pos.immutable());
            this.setBlock(level, ne_pos, log_ne);
            this.posTrunks.add(ne_pos.immutable());
            this.setBlock(level, se_pos, log_se);
            this.posTrunks.add(se_pos.immutable());
            this.setBlock(level, sw_pos, log_sw);
            this.posTrunks.add(sw_pos.immutable());
        } // end-for

        // Draw the knees
        generateKnees(level, sourceRand, pos, bonusHeight);

        // Generate the branches
        generateBranches(level, sourceRand, pos, actual_height, actual_radius);

        // place the topper leaves
        BlockPos.MutableBlockPos leafPos = pos.mutable();
        leafPos.setY((int) (actual_height * TRUNK_HEIGHT_PERCENT) + pos.getY());
        generateLeafCluster(level, leafPos, 4 + sourceRand.nextInt(CLUSTER_HEIGHT_VARIANCE),
                            4 + sourceRand.nextInt(CLUSTER_DIAMETER_VARIANCE),
                            treeConfig.foliage_provider.getState(sourceRand, leafPos));

        placeDecorators();
        return true;
    } // end place()


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

} // end class
