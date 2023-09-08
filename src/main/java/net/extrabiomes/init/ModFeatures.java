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
    		ResourceKey.create(Registries.CONFIGURED_FEATURE,new ResourceLocation(ExtrabiomesXS.MODID, "unber_autumn_tree"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_UMBER_AUTUMN_TREE = 
    		ResourceKey.create(Registries.CONFIGURED_FEATURE,new ResourceLocation(ExtrabiomesXS.MODID, "fancy_unber_autumn_tree"));
   
    	
} // end class
