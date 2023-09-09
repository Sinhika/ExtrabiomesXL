package net.extrabiomes.datagen;

import mod.alexndr.simplecorelib.api.datagen.SimpleBlockLootSubProvider;
import net.extrabiomes.init.ModBlocks;

public class ExtrabiomesLootSubprovider extends SimpleBlockLootSubProvider {

	@Override
	protected void generate() 
	{
		// leaves
		this.createLeavesDrops(ModBlocks.leaves_citrine.get(), ModBlocks.sapling_citrine.get(), NORMAL_LEAVES_SAPLING_CHANCES);
		this.createLeavesDrops(ModBlocks.leaves_goldenrod.get(), ModBlocks.sapling_goldenrod.get(), NORMAL_LEAVES_SAPLING_CHANCES);
		this.createLeavesDrops(ModBlocks.leaves_umber.get(), ModBlocks.sapling_umber.get(), NORMAL_LEAVES_SAPLING_CHANCES);
		this.createLeavesDrops(ModBlocks.leaves_vermillion.get(), ModBlocks.sapling_vermillion.get(), NORMAL_LEAVES_SAPLING_CHANCES);
		
		// saplings
		this.dropSelf(ModBlocks.sapling_citrine.get());
		this.dropSelf(ModBlocks.sapling_goldenrod.get());
		this.dropSelf(ModBlocks.sapling_umber.get());
		this.dropSelf(ModBlocks.sapling_vermillion.get());

		// logs
		this.dropSelf(ModBlocks.log_autumn.get());
		// planks
		this.dropSelf(ModBlocks.planks_autumn_wood.get());
		
		// stairs
		this.dropSelf(ModBlocks.stairs_autumn.get());
		
		// slabs
		this.dropSlab(ModBlocks.slab_autumn.get());
	} // end generate()

} // end class
