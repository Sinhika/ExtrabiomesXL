package net.extrabiomes.config;

import net.minecraftforge.common.ForgeConfigSpec;

public final class ServerConfig 
{
	final ForgeConfigSpec.BooleanValue decorate_vanilla_biomes;
	final ForgeConfigSpec.BooleanValue generate_legendary_oak;
	final ForgeConfigSpec.BooleanValue enable_autumnwoods_biome;

	ServerConfig(final ForgeConfigSpec.Builder builder)
	{
		builder.push("General");
		decorate_vanilla_biomes = builder.comment("Add ExtrabiomesXS decorations to vanilla biomes")
				.define("decorate_vanilla_biomes", true);
		generate_legendary_oak = builder
				.comment("Add legendary oaks to biomes. Only added to EBXS biomes if decorate_vanilla_biomes=false")
				.define("generate_legendary_oak", false);
		builder.pop();
		
		builder.push("Biomes");
		enable_autumnwoods_biome = builder.comment("Generate Autumn Woods biome")
						.define("enable_autumnwoods_biome", true);
		builder.pop();


	} // end
	
} // end-class
