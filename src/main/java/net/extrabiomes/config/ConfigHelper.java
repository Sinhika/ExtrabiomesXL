package net.extrabiomes.config;

import net.minecraftforge.fml.config.ModConfig;

public final class ConfigHelper 
{
   public static void bakeServer(final ModConfig config)
    {
        // General
        ExtrabiomesConfig.decorate_vanilla_biomes = ConfigHolder.SERVER.decorate_vanilla_biomes.get();
        ExtrabiomesConfig.generate_legendary_oak = ConfigHolder.SERVER.generate_legendary_oak.get();

        // Biomes
	    ExtrabiomesConfig.enable_autumnwoods_biome = ConfigHolder.SERVER.enable_autumnwoods_biome.get();

	  // ExtrabiomesConfig.INSTANCE.putFlag("fabrica_enabled", ConfigHolder.SERVER.fabrica_enabled.get());
	   
    } // end bakeServer
   
   public static void bakeClient(final ModConfig config)
   {
   } // end bakeClient
   
} // end-class
