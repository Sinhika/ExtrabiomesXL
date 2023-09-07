package net.extrabiomes.config;

import net.minecraftforge.fml.config.ModConfig;

public final class ConfigHelper 
{
   public static void bakeServer(final ModConfig config)
    {
	   // fabrica_enabled allows recipes mod-wide.
	   ExtrabiomesConfig.INSTANCE.putFlag("fabrica_enabled", ConfigHolder.SERVER.fabrica_enabled.get());
	   
    } // end bakeServer
   
   public static void bakeClient(final ModConfig config)
   {
   } // end bakeClient
   
} // end-class
