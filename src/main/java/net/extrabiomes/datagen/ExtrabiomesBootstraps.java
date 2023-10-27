package net.extrabiomes.datagen;

import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ExtrabiomesBootstraps
{
    public static void bootstrap_ConfiguredFeature(BootstapContext<ConfiguredFeature<?,?>> context)
    {
        // TODO
    }

    public static void bootstrap_PlacedFeature(BootstapContext<PlacedFeature> context)
    {
        // TODO
    }


    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(
            BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC config)
    {
        context.register(key, new ConfiguredFeature<>(feature, config));
    }
} // end class
