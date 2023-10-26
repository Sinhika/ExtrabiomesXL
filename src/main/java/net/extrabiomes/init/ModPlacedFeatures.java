package net.extrabiomes.init;

import net.extrabiomes.ExtrabiomesXS;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.registries.DeferredRegister;

public class ModPlacedFeatures
{
    /* PLACED FEATURE REGISTRY */
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURE_REGISTRY =
    		DeferredRegister.create(Registries.PLACED_FEATURE,  ExtrabiomesXS.MODID);


    public static ResourceKey<PlacedFeature> createKey(String name)
    {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(ExtrabiomesXS.MODID, name));
    }
} // end class
