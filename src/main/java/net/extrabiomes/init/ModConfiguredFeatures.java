package net.extrabiomes.init;

import net.extrabiomes.ExtrabiomesXS;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModConfiguredFeatures
{
    /* CONFIGURED FEATURES REGISTRY */
    public static final DeferredRegister<ConfiguredFeature<?,?>> CONFIGURED_FEATURE_REGISTRY
        = DeferredRegister.create(Registries.CONFIGURED_FEATURE, ExtrabiomesXS.MODID);

    /* configured features */
    public static final ResourceKey<ConfiguredFeature<?, ?>> CITRINE_AUTUMN_TREE = createKey("citrine_autumn_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CITRINE_AUTUMN_TREE_BEES_005 = createKey("citrine_autumn_tree_bees_005");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_CITRINE_AUTUMN_TREE = createKey("fancy_citrine_autumn_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_CITRINE_AUTUMN_TREE_BEES_005 = createKey("fancy_citrine_autumn_tree_bees_005");

    public static final ResourceKey<ConfiguredFeature<?, ?>> UMBER_AUTUMN_TREE = createKey("umber_autumn_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> UMBER_AUTUMN_TREE_BEES_005 = createKey("umber_autumn_tree_bees_005");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_UMBER_AUTUMN_TREE = createKey("fancy_umber_autumn_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_UMBER_AUTUMN_TREE_BEES_005 = createKey("fancy_umber_autumn_tree_bees_005");

    public static final ResourceKey<ConfiguredFeature<?, ?>> GOLDENROD_AUTUMN_TREE = createKey("goldenrod_autumn_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_GOLDENROD_AUTUMN_TREE = createKey("fancy_goldenrod_autumn_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GOLDENROD_AUTUMN_TREE_BEES_005 = createKey("goldenrod_autumn_tree_bees_005");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_GOLDENROD_AUTUMN_TREE_BEES_005 = createKey("fancy_goldenrod_autumn_tree_bees_005");

    public static final ResourceKey<ConfiguredFeature<?, ?>> VERMILLION_AUTUMN_TREE = createKey("vermillion_autumn_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_VERMILLION_AUTUMN_TREE = createKey("fancy_vermillion_autumn_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> VERMILLION_AUTUMN_TREE_BEES_005 = createKey("vermillion_autumn_tree_bees_005");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_VERMILLION_AUTUMN_TREE_BEES_005 = createKey("fancy_vermillion_autumn_tree_bees_005");

    public static final ResourceKey<ConfiguredFeature<?, ?>> JAPANESE_MAPLE_TREE = createKey("japanese_maple_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FIR_TREE = createKey("fir_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_FIR_TREE = createKey("big_fir_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ACACIA_TREE = createKey("acacia_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CYPRESS_TREE = createKey("cypress_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CUSTOM_SWAMP_TREE = createKey("custom_swamp_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LEGENDARY_OAK_TREE = createKey("legedary_oak_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> REDWOOD_TREE = createKey("redwood_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> JAPANESE_MAPLE_SHRUB = createKey("japanese_maple_shrub");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BALD_CYPRESS_TREE = createKey("bald_cypress_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> RAINBOW_EUCALYPTUS_TREE =createKey("rainbow_eucalyptus_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SAKURA_TREE = createKey("sakura_tree");


    public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name)
    {
    	return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(ExtrabiomesXS.MODID, name));
    }
    
  
} // end class
