package net.extrabiomes.init;

import net.extrabiomes.ExtrabiomesXS;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.registries.DeferredRegister;

public class ModFeatures 
{
	/* FEATURES REGISTRY */
    public static final DeferredRegister<Feature<?>> FEATURES_REGISTRY =
    		DeferredRegister.create(Registries.FEATURE, ExtrabiomesXS.MODID);

    /* CONFIGURED FEATURES REGISTRY */
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURE_REGISTRY = 
    		DeferredRegister.create(Registries.CONFIGURED_FEATURE, ExtrabiomesXS.MODID);
    
    /* PLACED FEATURE REGISTRY */
    public static DeferredRegister<PlacedFeature> PLACED_FEATURE_REGISTRY = 
    		DeferredRegister.create(Registries.PLACED_FEATURE,  ExtrabiomesXS.MODID);
    
    // configured features
    public static final ResourceKey<ConfiguredFeature<?, ?>> UMBER_AUTUMN_TREE = registerCF("umber_autumn_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_UMBER_AUTUMN_TREE = registerCF("fancy_umber_autumn_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GOLDENROD_AUTUMN_TREE = registerCF("goldenrod_autumn_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_GOLDENROD_AUTUMN_TREE = registerCF("fancy_goldenrod_autumn_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> VERMILLION_AUTUMN_TREE = registerCF("vermillion_autumn_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_VERMILLION_AUTUMN_TREE = registerCF("fancy_vermillion_autumn_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CITRINE_AUTUMN_TREE = registerCF("citrine_autumn_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_CITRINE_AUTUMN_TREE = registerCF("fancy_citrine_autumn_tree");
    
    public static final ResourceKey<ConfiguredFeature<?, ?>> JAPANESE_MAPLE_TREE = registerCF("japanese_maple");
    	
    private static ResourceKey<ConfiguredFeature<?, ?>> registerCF(String name)
    {
    	return ResourceKey.create(CONFIGURED_FEATURE_REGISTRY.getRegistryKey(),
    								new ResourceLocation(ExtrabiomesXS.MODID, name));
    }
    
  
} // end class
