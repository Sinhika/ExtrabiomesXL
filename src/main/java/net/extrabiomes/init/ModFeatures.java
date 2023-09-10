package net.extrabiomes.init;

import net.extrabiomes.ExtrabiomesXS;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.registries.DeferredRegister;

public class ModFeatures 
{
	   /** FEATURES REGISTRY */
    public static final DeferredRegister<Feature<?>> MOD_FEATURES =
    		DeferredRegister.create(Registries.FEATURE, ExtrabiomesXS.MODID);

    // configured features
    public static final ResourceKey<ConfiguredFeature<?, ?>> UMBER_AUTUMN_TREE = 
    		ResourceKey.create(Registries.CONFIGURED_FEATURE,new ResourceLocation(ExtrabiomesXS.MODID, "umber_autumn_tree"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_UMBER_AUTUMN_TREE = 
    		ResourceKey.create(Registries.CONFIGURED_FEATURE,new ResourceLocation(ExtrabiomesXS.MODID, "fancy_umber_autumn_tree"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> GOLDENROD_AUTUMN_TREE = 
    		ResourceKey.create(Registries.CONFIGURED_FEATURE,new ResourceLocation(ExtrabiomesXS.MODID, "goldenrod_autumn_tree"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_GOLDENROD_AUTUMN_TREE = 
    		ResourceKey.create(Registries.CONFIGURED_FEATURE,new ResourceLocation(ExtrabiomesXS.MODID, "fancy_goldenrod_autumn_tree"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> VERMILLION_AUTUMN_TREE = 
    		ResourceKey.create(Registries.CONFIGURED_FEATURE,new ResourceLocation(ExtrabiomesXS.MODID, "vermillion_autumn_tree"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_VERMILLION_AUTUMN_TREE = 
    		ResourceKey.create(Registries.CONFIGURED_FEATURE,new ResourceLocation(ExtrabiomesXS.MODID, "fancy_vermillion_autumn_tree"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> CITRINE_AUTUMN_TREE = 
    		ResourceKey.create(Registries.CONFIGURED_FEATURE,new ResourceLocation(ExtrabiomesXS.MODID, "citrine_autumn_tree"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_CITRINE_AUTUMN_TREE = 
    		ResourceKey.create(Registries.CONFIGURED_FEATURE,new ResourceLocation(ExtrabiomesXS.MODID, "fancy_citrine_autumn_tree"));
  
    public static final ResourceKey<ConfiguredFeature<?, ?>> JAPANESE_MAPLE_TREE =
    		ResourceKey.create(Registries.CONFIGURED_FEATURE,new ResourceLocation(ExtrabiomesXS.MODID, "japanese_maple"));
    	
} // end class
