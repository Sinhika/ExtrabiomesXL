package net.extrabiomes.datagen;

import net.extrabiomes.init.ModBlocks;
import net.extrabiomes.init.ModConfiguredFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ExtrabiomesBootstraps
{
    public static void bootstrap_ConfiguredFeature(BootstapContext<ConfiguredFeature<?,?>> context)
    {
        register(context, ModConfiguredFeatures.CITRINE_AUTUMN_TREE, Feature.TREE,
            new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(ModBlocks.log_autumn.get().defaultBlockState()),
                    new StraightTrunkPlacer(4,2,0),
                    BlockStateProvider.simple(ModBlocks.leaves_citrine.get()),
                    new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), 2),
                    new TwoLayersFeatureSize(1, 0, 1))
                    .ignoreVines().build());

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
