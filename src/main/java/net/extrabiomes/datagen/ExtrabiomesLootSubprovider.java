package net.extrabiomes.datagen;

import mod.alexndr.simplecorelib.api.datagen.SimpleBlockLootSubProvider;
import net.extrabiomes.init.ModBlocks;

public class ExtrabiomesLootSubprovider extends SimpleBlockLootSubProvider {

	@Override
	protected void generate() 
	{
		// TODO: change to normal leaf drops, including saplings, later.
		this.dropSelf(ModBlocks.leaves_citrine.get());
		this.dropSelf(ModBlocks.leaves_goldenrod.get());
		this.dropSelf(ModBlocks.leaves_umber.get());
		this.dropSelf(ModBlocks.leaves_vermillion.get());
		
		
	} // end generate()

} // end class
