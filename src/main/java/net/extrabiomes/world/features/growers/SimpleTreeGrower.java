package net.extrabiomes.world.features.growers;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class SimpleTreeGrower extends AbstractTreeGrower
{
    protected final ResourceKey<ConfiguredFeature<?, ?>> normal_tree;

    public SimpleTreeGrower(ResourceKey<ConfiguredFeature<?, ?>> normalTree)
    {
        super();
        normal_tree = normalTree;
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

