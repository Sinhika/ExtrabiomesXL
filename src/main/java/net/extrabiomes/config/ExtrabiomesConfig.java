package net.extrabiomes.config;

import mod.alexndr.simplecorelib.api.config.SimpleConfig;

public class ExtrabiomesConfig extends SimpleConfig 
{
	public static ExtrabiomesConfig INSTANCE = new ExtrabiomesConfig();
	
	// server - General
	public static boolean decorate_vanilla_biomes = true;
	public static boolean generate_legendary_oak = false;

	// server - biomes.
	public static boolean enable_autumnwoods_biome = true;

	// client
	
	
} // end-class
