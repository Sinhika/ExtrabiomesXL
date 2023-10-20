package net.extrabiomes.init;

import net.extrabiomes.ExtrabiomesXS;
import net.extrabiomes.world.regions.TemperateHillyRegion;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.registries.DeferredRegister;
import terrablender.api.Regions;

public final class ModBiomes 
{
	public static final DeferredRegister<Biome> BIOME_REGISTRY 
		= DeferredRegister.create(Registries.BIOME, ExtrabiomesXS.MODID);
			
	public static final ResourceKey<Biome> AUTUMN_WOODS = register("autumn_woods");
	
    private static ResourceKey<Biome> register(String name)
    {
    	return ResourceKey.create(BIOME_REGISTRY.getRegistryKey(), new ResourceLocation(ExtrabiomesXS.MODID, name));
    }
    
} // end class
