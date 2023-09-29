package net.extrabiomes.datagen;

import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;

public class ExtrabiomesEntityLootSubprovider extends EntityLootSubProvider 
{

	public ExtrabiomesEntityLootSubprovider() {
		super(FeatureFlags.REGISTRY.allFlags());
	}


	@Override
	public void generate() 
	{
		// TODO Auto-generated method stub

	}

} // end class
