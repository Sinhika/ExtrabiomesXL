package net.extrabiomes.datagen;

import net.extrabiomes.init.ModBlocks;
import net.extrabiomes.init.ModConfiguredFeatures;
import net.extrabiomes.init.ModFeatures;
import net.extrabiomes.world.features.configuration.EBTreeConfiguration;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.OptionalInt;

import static net.extrabiomes.init.ModFeatures.FEATURES_REGISTRY;

public class ExtrabiomesBootstraps
{
    public static void bootstrap_ConfiguredFeature(BootstapContext<ConfiguredFeature<?,?>> context)
    {
        // CITRINE_AUTUMN_TREES
        register(context, ModConfiguredFeatures.CITRINE_AUTUMN_TREE, Feature.TREE,
            new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(ModBlocks.log_autumn.get().defaultBlockState()),
                    new StraightTrunkPlacer(4,2,0),
                    BlockStateProvider.simple(ModBlocks.leaves_citrine.get()),
                    new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), 2),
                    new TwoLayersFeatureSize(1, 0, 1))
                    .ignoreVines().build());

        register(context, ModConfiguredFeatures.FANCY_CITRINE_AUTUMN_TREE, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(ModBlocks.log_autumn.get().defaultBlockState()),
                        new FancyTrunkPlacer(5,12,0),
                        BlockStateProvider.simple(ModBlocks.leaves_citrine.get()),
                        new FancyFoliagePlacer(ConstantInt.of(4), ConstantInt.of(4), 2),
                        new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))
                        .ignoreVines().build());

        // GOLDENROD_AUTUMN_TREES
        register(context, ModConfiguredFeatures.GOLDENROD_AUTUMN_TREE, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(ModBlocks.log_autumn.get().defaultBlockState()),
                        new StraightTrunkPlacer(4,2,0),
                        BlockStateProvider.simple(ModBlocks.leaves_goldenrod.get()),
                        new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), 2),
                        new TwoLayersFeatureSize(1, 0, 1))
                        .ignoreVines().build());

        register(context, ModConfiguredFeatures.FANCY_GOLDENROD_AUTUMN_TREE, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(ModBlocks.log_autumn.get().defaultBlockState()),
                        new FancyTrunkPlacer(5,12,0),
                        BlockStateProvider.simple(ModBlocks.leaves_goldenrod.get()),
                        new FancyFoliagePlacer(ConstantInt.of(4), ConstantInt.of(4), 2),
                        new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))
                        .ignoreVines().build());

        // UMBER_AUTUMN_TREES
        register(context, ModConfiguredFeatures.UMBER_AUTUMN_TREE, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(ModBlocks.log_autumn.get().defaultBlockState()),
                        new StraightTrunkPlacer(4,2,0),
                        BlockStateProvider.simple(ModBlocks.leaves_umber.get()),
                        new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), 2),
                        new TwoLayersFeatureSize(1, 0, 1))
                        .ignoreVines().build());

        register(context, ModConfiguredFeatures.FANCY_UMBER_AUTUMN_TREE, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(ModBlocks.log_autumn.get().defaultBlockState()),
                        new FancyTrunkPlacer(5,12,0),
                        BlockStateProvider.simple(ModBlocks.leaves_umber.get()),
                        new FancyFoliagePlacer(ConstantInt.of(4), ConstantInt.of(4), 2),
                        new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))
                        .ignoreVines().build());

        // VERMILLION_AUTUMN_TREES
        register(context, ModConfiguredFeatures.VERMILLION_AUTUMN_TREE, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(ModBlocks.log_autumn.get().defaultBlockState()),
                        new StraightTrunkPlacer(4,2,0),
                        BlockStateProvider.simple(ModBlocks.leaves_vermillion.get()),
                        new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), 2),
                        new TwoLayersFeatureSize(1, 0, 1))
                        .ignoreVines().build());

        register(context, ModConfiguredFeatures.FANCY_VERMILLION_AUTUMN_TREE, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(ModBlocks.log_autumn.get().defaultBlockState()),
                        new FancyTrunkPlacer(5,12,0),
                        BlockStateProvider.simple(ModBlocks.leaves_vermillion.get()),
                        new FancyFoliagePlacer(ConstantInt.of(4), ConstantInt.of(4), 2),
                        new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))
                        .ignoreVines().build());

        // FIR_TREE
        register(context, ModConfiguredFeatures.FIR_TREE, ModFeatures.FIR_TREE.get(),
            new EBTreeConfiguration(
                    BlockStateProvider.simple(ModBlocks.log_fir.get().defaultBlockState()),
                    BlockStateProvider.simple(ModBlocks.leaves_fir.get()),
                    BlockStateProvider.simple(Blocks.AIR),
                    BlockStateProvider.simple(Blocks.DIRT),
                    24, 8));

        // BIG_FIR_TREE
        register(context, ModConfiguredFeatures.BIG_FIR_TREE, ModFeatures.BIG_FIR_TREE.get(),
                new EBTreeConfiguration(
                        BlockStateProvider.simple(ModBlocks.log_fir.get().defaultBlockState()),
                        BlockStateProvider.simple(ModBlocks.leaves_fir.get()),
                        BlockStateProvider.simple(Blocks.AIR),
                        BlockStateProvider.simple(Blocks.DIRT),
                        32, 16));

        // TODO
    } // end bootstrap_ConfiguredFeature()

    public static void bootstrap_PlacedFeature(BootstapContext<PlacedFeature> context)
    {
        // TODO
    } // end bootstrap_PlacedFeature()


    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(
            BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC config)
    {
        context.register(key, new ConfiguredFeature<>(feature, config));
    }
} // end class
