package net.extrabiomes.world.features.tree;

import com.mojang.serialization.Codec;
import net.extrabiomes.world.features.configuration.EBTreeConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class FirTreeFeature extends Feature<EBTreeConfiguration>
{
    public FirTreeFeature(Codec<EBTreeConfiguration> pCodec)
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
        EBTreeConfiguration treeConfig = pContext.config();
        BlockPos pos = pContext.origin();
        RandomSource sourceRand = pContext.random();
        WorldGenLevel level = pContext.level();
        int height = treeConfig.base_height + sourceRand.nextInt(treeConfig.base_height_variance);
        int max_tree_altitude = pos.getY() + height + 1;

        if (pos.getY() < level.getMinBuildHeight() + 1 || max_tree_altitude > level.getMaxBuildHeight())
        {
            return false;
        }

        // TODO
    } // end place

} // end class
