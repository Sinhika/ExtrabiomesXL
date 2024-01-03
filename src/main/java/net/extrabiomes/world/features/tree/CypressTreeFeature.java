package net.extrabiomes.world.features.tree;

import com.mojang.serialization.Codec;
import net.extrabiomes.world.features.configuration.EBTreeConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class CypressTreeFeature extends EBBaseTreeFeature
{
    protected int CANOPY_START_HEIGHT;
    protected double CANOPY_START_VARIANCE;
    protected int canopy_start;

    public CypressTreeFeature(Codec<EBTreeConfiguration> pCodec)
    {
        super(pCodec);
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
        this.CANOPY_START_HEIGHT = treeConfig.canopy_start_height;
        this.CANOPY_START_VARIANCE = treeConfig.canopy_start_variance;
        this.canopy_start = CANOPY_START_HEIGHT + (int) ((sourceRand.nextDouble() * CANOPY_START_VARIANCE) - (CANOPY_START_VARIANCE / 2.0));
        double canopy_radius = ((double) CANOPY_WIDTH)/2.0D;
        double canopy_radius_variance = ((double) CANOPY_WIDTH_VARIANCE)/2.0D;
        this.actual_radius = (canopy_radius + ((sourceRand.nextDouble() * canopy_radius_variance) + (canopy_radius_variance / 2)));

        BlockPos pos = pContext.origin().immutable();
        int max_tree_altitude = pos.getY() + actual_height + 4;

        // height check
        if (pos.getY() < level.getMinBuildHeight() + 1 || max_tree_altitude > level.getMaxBuildHeight())
        {
            return false;
        }

        // clearance check
        if (! checkRoughClearance(pos, (int) actual_radius, actual_height - canopy_start, max_tree_altitude)) {
            return false;
        }

        // place the dirt block.
        this.setBlock(level, pos.below(), treeConfig.dirt_provider.getState(sourceRand, pos.below()));

        // place trunk
        if (place1x1Trunk(pos, actual_height, treeConfig.trunk_provider.getState(sourceRand, pos), level))
        {
            BlockPos.MutableBlockPos placePos = pos.mutable();
            placePos.setY(pos.getY() + canopy_start);
            // place canopy
            generateCanopy(level, sourceRand, placePos, actual_radius, 4 + actual_height - canopy_start,
                    treeConfig.foliage_provider.getState(sourceRand, placePos));
            placeDecorators();
            return true;
        } // end-if

        return false;
    } // end place()

    /**
     * Generate the tree's canopy.
     *
     * @param world  LevelAccessor
     * @param rand   a RandomSource
     * @param pos    BlockPos of center of canopy
     * @param radius canopy radius
     * @param depth canopy depth
     * @param leaves leaf BlockState
     */
    @Override public void generateCanopy(LevelAccessor world, RandomSource rand, BlockPos pos, double radius,
                                         int depth, BlockState leaves)
    {
        double factor = 16.0D / (2 + this.actual_height - this.canopy_start);
        BlockPos.MutableBlockPos layerPos = pos.mutable();

        for (int layer = 0; layer < depth; layer++)
        {
            layerPos.setY(pos.getY() + layer);
            double offset = factor * layer;
            double offset2 = offset * offset;
            double offset3 = offset2 * offset;
            double r1 = radius * ((0.00142 * offset3) - (0.0517 * offset2) + (0.5085 * offset) - 0.4611);
            placeLeavesCircle(layerPos, r1, leaves, world);
        } // end-for layer
    } // generateCanopy


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
        return false;
    }

} // end class
