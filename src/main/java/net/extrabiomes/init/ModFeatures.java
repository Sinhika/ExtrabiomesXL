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

} // end class
