package net.extrabiomes.world.regions;

import java.util.function.Consumer;

import com.mojang.datafixers.util.Pair;

import net.extrabiomes.config.ExtrabiomesConfig;
import net.extrabiomes.init.ModBiomes;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate.ParameterPoint;
import terrablender.api.Region;
import terrablender.api.RegionType;

public class TemperateHillyRegion extends Region 
{

	public TemperateHillyRegion(ResourceLocation name, int weight) 
	{
		super(name, RegionType.OVERWORLD, weight);
	}

	@Override
	public void addBiomes(Registry<Biome> registry, Consumer<Pair<ParameterPoint, ResourceKey<Biome>>> mapper) 
	{
		if (ExtrabiomesConfig.enable_autumnwoods_biome) {
			this.addModifiedVanillaOverworldBiomes(mapper, builder -> {
				builder.replaceBiome(Biomes.BIRCH_FOREST, ModBiomes.AUTUMNWOODS);
			});
		}

	} // end addBiomes()

	
} // end class
