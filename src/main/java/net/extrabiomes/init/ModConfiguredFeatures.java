package net.extrabiomes.init;

import net.extrabiomes.ExtrabiomesXS;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraftforge.registries.DeferredRegister;

public class ModConfiguredFeatures
{
    /* CONFIGURED FEATURES REGISTRY */
    public static final DeferredRegister<ConfiguredFeature<?,?>> CONFIGURED_FEATURE_REGISTRY
        = DeferredRegister.create(Registries.CONFIGURED_FEATURE, ExtrabiomesXS.MODID);

    /* configured features */
    public static final ResourceKey<ConfiguredFeature<?, ?>> UMBER_AUTUMN_TREE = createKey("umber_autumn_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_UMBER_AUTUMN_TREE = createKey("fancy_umber_autumn_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GOLDENROD_AUTUMN_TREE = createKey("goldenrod_autumn_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_GOLDENROD_AUTUMN_TREE = createKey("fancy_goldenrod_autumn_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> VERMILLION_AUTUMN_TREE = createKey("vermillion_autumn_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_VERMILLION_AUTUMN_TREE = createKey("fancy_vermillion_autumn_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CITRINE_AUTUMN_TREE = createKey("citrine_autumn_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_CITRINE_AUTUMN_TREE = createKey("fancy_citrine_autumn_tree");

    public static final ResourceKey<ConfiguredFeature<?, ?>> JAPANESE_MAPLE_TREE = createKey("japanese_maple");


    public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name)
    {
    	return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(ExtrabiomesXS.MODID, name));
    }
    
  
} // end class
