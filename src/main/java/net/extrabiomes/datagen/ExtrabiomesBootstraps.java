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
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;
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
                    .decorators(List.of())
                    .ignoreVines().build());

        register(context, ModConfiguredFeatures.CITRINE_AUTUMN_TREE_BEES_005, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(ModBlocks.log_autumn.get().defaultBlockState()),
                        new StraightTrunkPlacer(4,2,0),
                        BlockStateProvider.simple(ModBlocks.leaves_citrine.get()),
                        new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), 2),
                        new TwoLayersFeatureSize(1, 0, 1))
                        .decorators(List.of(new BeehiveDecorator(0.05F)))
                        .ignoreVines().build());

        register(context, ModConfiguredFeatures.FANCY_CITRINE_AUTUMN_TREE, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(ModBlocks.log_autumn.get().defaultBlockState()),
                        new FancyTrunkPlacer(5,12,0),
                        BlockStateProvider.simple(ModBlocks.leaves_citrine.get()),
                        new FancyFoliagePlacer(ConstantInt.of(4), ConstantInt.of(4), 2),
                        new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))
                        .decorators(List.of())
                        .ignoreVines().build());

        register(context, ModConfiguredFeatures.FANCY_CITRINE_AUTUMN_TREE_BEES_005, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(ModBlocks.log_autumn.get().defaultBlockState()),
                        new FancyTrunkPlacer(5,12,0),
                        BlockStateProvider.simple(ModBlocks.leaves_citrine.get()),
                        new FancyFoliagePlacer(ConstantInt.of(4), ConstantInt.of(4), 2),
                        new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))
                        .decorators(List.of(new BeehiveDecorator(0.05F)))
                        .ignoreVines().build());

        // GOLDENROD_AUTUMN_TREES
        register(context, ModConfiguredFeatures.GOLDENROD_AUTUMN_TREE, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(ModBlocks.log_autumn.get().defaultBlockState()),
                        new StraightTrunkPlacer(4,2,0),
                        BlockStateProvider.simple(ModBlocks.leaves_goldenrod.get()),
                        new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), 2),
                        new TwoLayersFeatureSize(1, 0, 1))
                        .decorators(List.of())
                        .ignoreVines().build());

        register(context, ModConfiguredFeatures.FANCY_GOLDENROD_AUTUMN_TREE, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(ModBlocks.log_autumn.get().defaultBlockState()),
                        new FancyTrunkPlacer(5,12,0),
                        BlockStateProvider.simple(ModBlocks.leaves_goldenrod.get()),
                        new FancyFoliagePlacer(ConstantInt.of(4), ConstantInt.of(4), 2),
                        new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))
                        .decorators(List.of())
                        .ignoreVines().build());

        register(context, ModConfiguredFeatures.GOLDENROD_AUTUMN_TREE_BEES_005, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(ModBlocks.log_autumn.get().defaultBlockState()),
                        new StraightTrunkPlacer(4,2,0),
                        BlockStateProvider.simple(ModBlocks.leaves_goldenrod.get()),
                        new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), 2),
                        new TwoLayersFeatureSize(1, 0, 1))
                        .decorators(List.of(new BeehiveDecorator(0.05F)))
                        .ignoreVines().build());

        register(context, ModConfiguredFeatures.FANCY_GOLDENROD_AUTUMN_TREE_BEES_005, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(ModBlocks.log_autumn.get().defaultBlockState()),
                        new FancyTrunkPlacer(5,12,0),
                        BlockStateProvider.simple(ModBlocks.leaves_goldenrod.get()),
                        new FancyFoliagePlacer(ConstantInt.of(4), ConstantInt.of(4), 2),
                        new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))
                        .decorators(List.of(new BeehiveDecorator(0.05F)))
                        .ignoreVines().build());


        // UMBER_AUTUMN_TREES
        register(context, ModConfiguredFeatures.UMBER_AUTUMN_TREE, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(ModBlocks.log_autumn.get().defaultBlockState()),
                        new StraightTrunkPlacer(4,2,0),
                        BlockStateProvider.simple(ModBlocks.leaves_umber.get()),
                        new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), 2),
                        new TwoLayersFeatureSize(1, 0, 1))
                        .decorators(List.of())
                        .ignoreVines().build());

        register(context, ModConfiguredFeatures.FANCY_UMBER_AUTUMN_TREE, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(ModBlocks.log_autumn.get().defaultBlockState()),
                        new FancyTrunkPlacer(5,12,0),
                        BlockStateProvider.simple(ModBlocks.leaves_umber.get()),
                        new FancyFoliagePlacer(ConstantInt.of(4), ConstantInt.of(4), 2),
                        new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))
                        .decorators(List.of())
                        .ignoreVines().build());

        register(context, ModConfiguredFeatures.UMBER_AUTUMN_TREE_BEES_005, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(ModBlocks.log_autumn.get().defaultBlockState()),
                        new StraightTrunkPlacer(4,2,0),
                        BlockStateProvider.simple(ModBlocks.leaves_umber.get()),
                        new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), 2),
                        new TwoLayersFeatureSize(1, 0, 1))
                        .decorators(List.of(new BeehiveDecorator(0.05F)))
                        .ignoreVines().build());

        register(context, ModConfiguredFeatures.FANCY_UMBER_AUTUMN_TREE_BEES_005, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(ModBlocks.log_autumn.get().defaultBlockState()),
                        new FancyTrunkPlacer(5,12,0),
                        BlockStateProvider.simple(ModBlocks.leaves_umber.get()),
                        new FancyFoliagePlacer(ConstantInt.of(4), ConstantInt.of(4), 2),
                        new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))
                        .decorators(List.of(new BeehiveDecorator(0.05F)))
                        .ignoreVines().build());


        // VERMILLION_AUTUMN_TREES
        register(context, ModConfiguredFeatures.VERMILLION_AUTUMN_TREE, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(ModBlocks.log_autumn.get().defaultBlockState()),
                        new StraightTrunkPlacer(4,2,0),
                        BlockStateProvider.simple(ModBlocks.leaves_vermillion.get()),
                        new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), 2),
                        new TwoLayersFeatureSize(1, 0, 1))
                        .decorators(List.of())
                        .ignoreVines().build());

        register(context, ModConfiguredFeatures.FANCY_VERMILLION_AUTUMN_TREE, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(ModBlocks.log_autumn.get().defaultBlockState()),
                        new FancyTrunkPlacer(5,12,0),
                        BlockStateProvider.simple(ModBlocks.leaves_vermillion.get()),
                        new FancyFoliagePlacer(ConstantInt.of(4), ConstantInt.of(4), 2),
                        new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))
                        .decorators(List.of())
                        .ignoreVines().build());

        register(context, ModConfiguredFeatures.VERMILLION_AUTUMN_TREE_BEES_005, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(ModBlocks.log_autumn.get().defaultBlockState()),
                        new StraightTrunkPlacer(4,2,0),
                        BlockStateProvider.simple(ModBlocks.leaves_vermillion.get()),
                        new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), 2),
                        new TwoLayersFeatureSize(1, 0, 1))
                        .decorators(List.of(new BeehiveDecorator(0.05F)))
                        .ignoreVines().build());

        register(context, ModConfiguredFeatures.FANCY_VERMILLION_AUTUMN_TREE_BEES_005, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(ModBlocks.log_autumn.get().defaultBlockState()),
                        new FancyTrunkPlacer(5,12,0),
                        BlockStateProvider.simple(ModBlocks.leaves_vermillion.get()),
                        new FancyFoliagePlacer(ConstantInt.of(4), ConstantInt.of(4), 2),
                        new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))
                        .decorators(List.of(new BeehiveDecorator(0.05F)))
                        .ignoreVines().build());

        // FIR_TREE
        register(context, ModConfiguredFeatures.FIR_TREE, ModFeatures.FIR_TREE.get(),
            new EBTreeConfiguration(
                    BlockStateProvider.simple(ModBlocks.log_fir.get().defaultBlockState()),
                    BlockStateProvider.simple(ModBlocks.leaves_fir.get()),
                    BlockStateProvider.simple(Blocks.AIR),
                    BlockStateProvider.simple(Blocks.DIRT),
                    24, 8, 1, 12, 2, 6, List.of()));

        // BIG_FIR_TREE
        register(context, ModConfiguredFeatures.BIG_FIR_TREE, ModFeatures.BIG_FIR_TREE.get(),
                new EBTreeConfiguration(
                        BlockStateProvider.simple(ModBlocks.log_fir.get().defaultBlockState()),
                        BlockStateProvider.simple(ModBlocks.leaves_fir.get()),
                        BlockStateProvider.simple(Blocks.AIR),
                        BlockStateProvider.simple(Blocks.DIRT),
                        32, 16, 1, 12, 2, 9, List.of()));

        // JAPANESE_MAPLE_TREE
        register(context, ModConfiguredFeatures.JAPANESE_MAPLE_TREE, ModFeatures.JAPANESE_MAPLE_TREE.get(),
            new EBTreeConfiguration(
                    BlockStateProvider.simple(ModBlocks.log_japanese_maple.get().defaultBlockState()),
                    BlockStateProvider.simple(ModBlocks.leaves_japanese_maple.get()),
                    BlockStateProvider.simple(Blocks.AIR),
                    BlockStateProvider.simple(Blocks.DIRT),
                    8, 4, 0, 0, 8, 6, List.of()));

        // JAPANESE_MAPLE_SHRUB
        register(context, ModConfiguredFeatures.JAPANESE_MAPLE_SHRUB, ModFeatures.JAPANESE_MAPLE_SHRUB.get(),
            new EBTreeConfiguration(
                    BlockStateProvider.simple(ModBlocks.log_japanese_maple.get().defaultBlockState()),
                    BlockStateProvider.simple(ModBlocks.leaves_japanese_maple_shrub.get()),
                    BlockStateProvider.simple(Blocks.AIR),
                    BlockStateProvider.simple(Blocks.DIRT),
                    3, 2, 0, 0, 4, 3, List.of()));

        // REDWOOD
        register(context, ModConfiguredFeatures.REDWOOD_TREE, ModFeatures.REDWOOD_TREE.get(),
            new EBTreeConfiguration(
                    BlockStateProvider.simple(ModBlocks.log_redwood.get().defaultBlockState()),
                    BlockStateProvider.simple(ModBlocks.leaves_redwood.get()),
                    BlockStateProvider.simple(Blocks.AIR),
                    BlockStateProvider.simple(Blocks.DIRT),
                    26, 23, 0, 0, 0, 0, List.of()));

        // ACACIA
        register(context, ModConfiguredFeatures.ACACIA_TREE, ModFeatures.ACACIA_TREE.get(),
                new EBTreeConfiguration(
                        BlockStateProvider.simple(ModBlocks.log_acacia.get().defaultBlockState()),
                        BlockStateProvider.simple(ModBlocks.leaves_acacia.get()),
                        BlockStateProvider.simple(Blocks.AIR),
                        BlockStateProvider.simple(Blocks.DIRT),
                        6, 4, 3, 0, 4, 0 , List.of()));

        // CYPRESS
        register(context, ModConfiguredFeatures.CYPRESS_TREE, ModFeatures.CYPRESS_TREE.get(),
                new EBTreeConfiguration(
                        BlockStateProvider.simple(ModBlocks.log_cypress.get().defaultBlockState()),
                        BlockStateProvider.simple(ModBlocks.leaves_cypress.get()),
                        BlockStateProvider.simple(Blocks.AIR),
                        BlockStateProvider.simple(Blocks.DIRT),
                        12, 6, 0, 4, 3, 2, List.of()));  // canopy figures doubled-up from original.

        // BALD CYPRESS
        register(context, ModConfiguredFeatures.BALD_CYPRESS_TREE, ModFeatures.BALD_CYPRESS_TREE.get(),
                new EBTreeConfiguration(
                        BlockStateProvider.simple(ModBlocks.log_baldcypress.get().defaultBlockState()),
                        BlockStateProvider.simple(ModBlocks.leaves_bald_cypress.get()),
                        BlockStateProvider.simple(ModBlocks.baldcypresskneelog.get()),
                        BlockStateProvider.simple(Blocks.DIRT),
                        24, 10, 0, 0, 15, 5, List.of()));

        // RAINBOW EUCALYPTUS
        register(context, ModConfiguredFeatures.RAINBOW_EUCALYPTUS_TREE, ModFeatures.RAINBOW_EUCALYPTUS_TREE.get(),
                new EBTreeConfiguration(
                        BlockStateProvider.simple(ModBlocks.log_rainboweucalyptus.get().defaultBlockState()),
                        BlockStateProvider.simple(ModBlocks.leaves_rainbow_eucalyptus.get()),
                        BlockStateProvider.simple(ModBlocks.rainboweucalyptuskneelog.get()),
                        BlockStateProvider.simple(Blocks.DIRT),
                        19, 8, 0, 0, 12, 3 , List.of()));

        // SAKURA BLOSSOM
        register(context, ModConfiguredFeatures.SAKURA_TREE, ModFeatures.SAKURA_TREE.get(),
                new EBTreeConfiguration(
                        BlockStateProvider.simple(ModBlocks.log_sakura.get().defaultBlockState()),
                        BlockStateProvider.simple(ModBlocks.leaves_sakura.get()),
                        BlockStateProvider.simple(Blocks.AIR),
                        BlockStateProvider.simple(Blocks.DIRT),
                        8, 4, 0, 0, 8, 4, List.of()));

        // LEGENDARY OAK
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
