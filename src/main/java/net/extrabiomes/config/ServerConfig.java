package net.extrabiomes.config;

import net.minecraftforge.common.ForgeConfigSpec;

public final class ServerConfig 
{
	final ForgeConfigSpec.BooleanValue amica_enabled;
	final ForgeConfigSpec.BooleanValue cautia_enabled;
	final ForgeConfigSpec.BooleanValue fabrica_enabled;
 
	ServerConfig(final ForgeConfigSpec.Builder builder)
	{
		builder.push("General");
		builder.pop();
		
		builder.push("Module_Control");
		amica_enabled = builder.comment("Not currently active").translation("config.amica_comment")
				.define("amica_enabled", false);
		cautia_enabled = builder.translation("config.cautia_enabled").define("cautia_enabled", true);
		fabrica_enabled = builder.translation("config.fabrica_enabled").define("fabrica_enabled", true);
		builder.pop();


	} // end
	
} // end-class
