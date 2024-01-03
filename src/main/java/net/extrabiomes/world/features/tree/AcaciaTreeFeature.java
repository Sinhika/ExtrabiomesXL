package net.extrabiomes.world.features.tree;

import com.mojang.serialization.Codec;
import net.extrabiomes.world.features.configuration.EBTreeConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class AcaciaTreeFeature  extends EBBaseTreeFeature
{

    public AcaciaTreeFeature(Codec<EBTreeConfiguration> pCodec)
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
        BlockPos pos = pContext.origin().immutable();
        int max_tree_altitude = pos.getY() + actual_height + 1;
        int trunk_height = actual_height - 2;

        // height check
        if (pos.getY() < level.getMinBuildHeight() + 1 || max_tree_altitude > level.getMaxBuildHeight())
        {
            return false;
        }

        // make sure space for tree is clear or replaceable.
        if (!checkRoughClearance(pos, (int) actual_radius, trunk_height, max_tree_altitude)) {
            return false;
        }

        // place the dirt block.
        this.setBlock(level, pos.below(), treeConfig.dirt_provider.getState(sourceRand, pos.below()));

        // place canopy
        generateCanopy(level, sourceRand, pos, actual_radius, treeConfig.canopy_start_height,
                        treeConfig.foliage_provider.getState(sourceRand, pos));

        // place trunk;
        if (! place1x1Trunk(pos, trunk_height, treeConfig.trunk_provider.getState(sourceRand, pos), level))
        {
            return false;
        }

        placeDecorators();
        return true;
    } // end place()

    /**
     * Generate the tree's canopy.
     *
     * @param world  LevelAccessor
     * @param rand   a RandomSource
     * @param pos    BlockPos of center of canopy
     * @param radius canopy radius
     * @param height canopy height or depth
     * @param leaves leaf BlockState
     */
    @Override public void generateCanopy(LevelAccessor world, RandomSource rand, BlockPos pos, double radius,
                                         int height, BlockState leaves)
    {
        final int canopyStartHeight = pos.getY() + actual_height;
        final int minCanopyRadius = 0;
        BlockPos.MutableBlockPos placePos = pos.mutable();

        for (int y1 = canopyStartHeight - height; y1 <= canopyStartHeight; y1++)
        {
            int distanceFromTop = y1 - canopyStartHeight;
            int canopyRadius = minCanopyRadius + 1 - distanceFromTop;
            placePos.setY(y1);
            placeLeavesSquare(placePos, canopyRadius, distanceFromTop, leaves, level);
        } // end-for y1

    } // end generateCanopy

    /**
     * Actually place the branches.
     *
     * @param world     current LevalAccessor
     * @param rand      current RandomSource
     * @param branchpos Starting position of branches
     * @param height    canopy height
     * @param radius    canopy radius
     */
    @Override public boolean generateBranches(LevelAccessor world, RandomSource rand, BlockPos branchpos, int height,
                                              double radius)
    {
        return false;
    }

} // end class
