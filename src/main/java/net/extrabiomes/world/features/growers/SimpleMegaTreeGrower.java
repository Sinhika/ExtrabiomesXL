package net.extrabiomes.world.features.growers;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractMegaTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class SimpleMegaTreeGrower extends AbstractMegaTreeGrower
{
    protected final ResourceKey<ConfiguredFeature<?, ?>> normal_tree;
    protected final ResourceKey<ConfiguredFeature<?, ?>> big_tree;

    public SimpleMegaTreeGrower(ResourceKey<ConfiguredFeature<?, ?>> normalTree,
                                ResourceKey<ConfiguredFeature<?, ?>> bigTree)
    {
        normal_tree = normalTree;
        big_tree = bigTree;
    }

    /**
     * @param pRandom
     * @return the key of the huge variant of this tree
     */
    @Nullable @Override protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredMegaFeature(RandomSource pRandom)
    {
        return this.big_tree;
    }

    /**
     * @param pRandom
     * @param pHasFlowers
     * @return the key of this tree
     */
    @Nullable @Override protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource pRandom,
                                                                                            boolean pHasFlowers)
    {
        return this.normal_tree;
    }
} // end class
