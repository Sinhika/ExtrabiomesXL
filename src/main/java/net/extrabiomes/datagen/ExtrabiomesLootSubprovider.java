package net.extrabiomes.datagen;

import mod.alexndr.simplecorelib.api.datagen.SimpleBlockLootSubProvider;
import net.extrabiomes.init.ModBlocks;

public class ExtrabiomesLootSubprovider extends SimpleBlockLootSubProvider {

	@Override
	protected void generate() 
	{
		// leaves
		this.add(ModBlocks.leaves_citrine.get(), createLeavesDrops(ModBlocks.leaves_citrine.get(), 
				ModBlocks.sapling_citrine.get(), NORMAL_LEAVES_SAPLING_CHANCES));
		this.add(ModBlocks.leaves_goldenrod.get(), createLeavesDrops(ModBlocks.leaves_goldenrod.get(), 
				ModBlocks.sapling_goldenrod.get(),NORMAL_LEAVES_SAPLING_CHANCES));
		this.add(ModBlocks.leaves_umber.get(), createLeavesDrops(ModBlocks.leaves_umber.get(), ModBlocks.sapling_umber.get(), 
				NORMAL_LEAVES_SAPLING_CHANCES));
		this.add(ModBlocks.leaves_vermillion.get(), createLeavesDrops(ModBlocks.leaves_vermillion.get(), 
				ModBlocks.sapling_vermillion.get(), NORMAL_LEAVES_SAPLING_CHANCES));
		this.add(ModBlocks.leaves_japanese_maple.get(), createLeavesDrops(ModBlocks.leaves_japanese_maple.get(),
				ModBlocks.sapling_japanese_maple.get(), NORMAL_LEAVES_SAPLING_CHANCES));
		
		// saplings
		this.dropSelf(ModBlocks.sapling_citrine.get());
		this.dropSelf(ModBlocks.sapling_goldenrod.get());
		this.dropSelf(ModBlocks.sapling_umber.get());
		this.dropSelf(ModBlocks.sapling_vermillion.get());
		this.dropSelf(ModBlocks.sapling_japanese_maple.get());
		
		// logs
		this.dropSelf(ModBlocks.log_autumn.get());
		this.dropSelf(ModBlocks.log_japanese_maple.get());
		
		// planks
		this.dropSelf(ModBlocks.planks_autumn_wood.get());
		this.dropSelf(ModBlocks.planks_japanese_maple.get());
		
		// stairs
		this.dropSelf(ModBlocks.stairs_autumn.get());
		this.dropSelf(ModBlocks.stairs_japanesemaple.get());
		
		// slabs
		this.dropSlab(ModBlocks.slab_autumn.get());
		this.dropSlab(ModBlocks.slab_japanese_maple.get());
		
		// doors
		this.doorDropTable(ModBlocks.door_autumn.get());
		this.doorDropTable(ModBlocks.door_japanesemaple.get());
		
		// fences
		this.dropSelf(ModBlocks.fence_autumn.get());
		this.dropSelf(ModBlocks.fence_japanesemaple.get());
		
		// fence gates
		this.dropSelf(ModBlocks.gate_autumn.get());
		this.dropSelf(ModBlocks.gate_japanesemaple.get());
		
		// buttons
		this.dropSelf(ModBlocks.button_autumn.get());
		this.dropSelf(ModBlocks.button_japanesemaple.get());
		
		// pressure plates
		this.dropSelf(ModBlocks.pressureplate_autumn.get());
		this.dropSelf(ModBlocks.pressureplate_japanesemaple.get());
	} // end generate()

} // end class
