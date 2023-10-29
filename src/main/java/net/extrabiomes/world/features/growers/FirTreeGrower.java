package net.extrabiomes.world.features.growers;

import net.extrabiomes.init.ModConfiguredFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractMegaTreeGrower;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class FirTreeGrower extends AbstractMegaTreeGrower
{
    /**
     * @param pRandom
     * @param pHasFlowers
     * @return the key of this tree
     */
    @Nullable @Override protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource pRandom,
                                                                                            boolean pHasFlowers)
    {
        return ModConfiguredFeatures.FIR_TREE;
    }

    /**
     * @param pRandom
     * @return the key of the huge variant of this tree
     */
    @Nullable @Override protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredMegaFeature(RandomSource pRandom)
    {
        return ModConfiguredFeatures.BIG_FIR_TREE;
    }

} // end class
