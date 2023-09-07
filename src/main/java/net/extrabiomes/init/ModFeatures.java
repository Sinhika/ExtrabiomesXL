package net.extrabiomes.init;

import net.extrabiomes.ExtrabiomesXS;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.registries.DeferredRegister;

public class ModFeatures 
{
	   /** FEATURES REGISTRY */
    public static final DeferredRegister<Feature<?>> MOD_FEATURES =
    		DeferredRegister.create(Registries.FEATURE, ExtrabiomesXS.MODID);

    
} // end class
