package net.extrabiomes.datagen;

import mod.alexndr.simplecorelib.api.datagen.SimpleBlockLootSubProvider;
import net.extrabiomes.init.ModBlocks;
import net.extrabiomes.init.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

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
		
		// flowers
		this.dropSelf(ModBlocks.flower_redrover.get());
		this.dropSelf(ModBlocks.flower_hydrangea.get());
		this.dropSelf(ModBlocks.flower_buttercup.get());
		this.dropSelf(ModBlocks.flower_lavender.get());
		this.dropSelf(ModBlocks.flower_tiny_cactus.get());
		this.dropSelf(ModBlocks.flower_allium.get());
		this.dropSelf(ModBlocks.flower_calla_white.get());
		this.dropSelf(ModBlocks.flower_toadstool.get());
		this.dropSelf(ModBlocks.flower_amaryllis_pink.get());
		this.dropSelf(ModBlocks.flower_amaryllis_red.get());
		this.dropSelf(ModBlocks.flower_amaryllis_white.get());
		this.dropSelf(ModBlocks.flower_bachelors_button.get());
		this.dropSelf(ModBlocks.flower_bells_of_ireland.get());
		this.dropSelf(ModBlocks.flower_bluebell.get());
		this.dropSelf(ModBlocks.flower_calla_black.get());
		this.dropSelf(ModBlocks.flower_daisy.get());
		this.dropSelf(ModBlocks.flower_dandelion.get());
		this.dropSelf(ModBlocks.flower_gardenia.get());
		this.dropSelf(ModBlocks.flower_gerbera_orange.get());
		this.dropSelf(ModBlocks.flower_gerbera_pink.get());
		this.dropSelf(ModBlocks.flower_gerbera_red.get());
		this.dropSelf(ModBlocks.flower_gerbera_yellow.get());
	   	this.dropSelf(ModBlocks.flower_oriental_pink_lily.get());
    	this.dropSelf(ModBlocks.flower_lily.get());
    	this.dropSelf(ModBlocks.flower_iris_blue.get());
    	this.dropSelf(ModBlocks.flower_iris_purple.get());
    	this.dropSelf(ModBlocks.flower_marsh_marigold.get());
    	this.dropSelf(ModBlocks.flower_pansy.get());
    	this.dropSelf(ModBlocks.flower_poppy.get());
    	this.dropSelf(ModBlocks.flower_blue_poppy.get());
    	this.dropSelf(ModBlocks.flower_snapdragon.get());
    	this.dropSelf(ModBlocks.flower_tulip.get());
    	this.dropSelf(ModBlocks.flower_violet.get());
    	this.dropSelf(ModBlocks.flower_yarrow.get());
    	this.dropSelf(ModBlocks.flower_belladonna.get());
		
		// flowerpots
		this.dropPottedContents(ModBlocks.potted_allium.get());
		this.dropPottedContents(ModBlocks.potted_redrover.get());
		this.dropPottedContents(ModBlocks.potted_hydrangea.get());
		this.dropPottedContents(ModBlocks.potted_buttercup.get());
		this.dropPottedContents(ModBlocks.potted_lavender.get());
		this.dropPottedContents(ModBlocks.potted_tiny_cactus.get());
		this.dropPottedContents(ModBlocks.potted_toadstool.get());
		this.dropPottedContents(ModBlocks.potted_calla_white.get());
		this.dropPottedContents(ModBlocks.potted_amaryllis_pink.get());
		this.dropPottedContents(ModBlocks.potted_amaryllis_red.get());
		this.dropPottedContents(ModBlocks.potted_amaryllis_white.get());
		this.dropPottedContents(ModBlocks.potted_bachelors_button.get());
		this.dropPottedContents(ModBlocks.potted_bells_of_ireland.get());
		this.dropPottedContents(ModBlocks.potted_sapling_citrine.get());
		this.dropPottedContents(ModBlocks.potted_sapling_umber.get());
		this.dropPottedContents(ModBlocks.potted_sapling_goldenrod.get());
		this.dropPottedContents(ModBlocks.potted_sapling_japanese_maple.get());
		this.dropPottedContents(ModBlocks.potted_sapling_vermillion.get());
		this.dropPottedContents(ModBlocks.potted_bluebell.get());
		this.dropPottedContents(ModBlocks.potted_calla_black.get());
		this.dropPottedContents(ModBlocks.potted_daisy.get());
		this.dropPottedContents(ModBlocks.potted_dandelion.get());
		this.dropPottedContents(ModBlocks.potted_gardenia.get());
		this.dropPottedContents(ModBlocks.potted_gerbera_orange.get());
		this.dropPottedContents(ModBlocks.potted_gerbera_pink.get());
		this.dropPottedContents(ModBlocks.potted_gerbera_red.get());
		this.dropPottedContents(ModBlocks.potted_gerbera_yellow.get());
		this.dropPottedContents(ModBlocks.potted_hydrangea.get());
	   	this.dropPottedContents(ModBlocks.potted_oriental_pink_lily.get());
    	this.dropPottedContents(ModBlocks.potted_lily.get());
    	this.dropPottedContents(ModBlocks.potted_iris_blue.get());
    	this.dropPottedContents(ModBlocks.potted_iris_purple.get());
    	this.dropPottedContents(ModBlocks.potted_marsh_marigold.get());
    	this.dropPottedContents(ModBlocks.potted_pansy.get());
    	this.dropPottedContents(ModBlocks.potted_poppy.get());
    	this.dropPottedContents(ModBlocks.potted_blue_poppy.get());
    	this.dropPottedContents(ModBlocks.potted_snapdragon.get());
    	this.dropPottedContents(ModBlocks.potted_tulip.get());
    	this.dropPottedContents(ModBlocks.potted_violet.get());
    	this.dropPottedContents(ModBlocks.potted_yarrow.get());
    	this.dropPottedContents(ModBlocks.potted_belladonna.get());

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
		
		// crops
		LootItemCondition.Builder strawberry_condition = 
				LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.crop_strawberry.get())
					.setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CropBlock.AGE, 7));
		this.add(ModBlocks.crop_strawberry.get(), createCropDrops(ModBlocks.crop_strawberry.get(), 
				ModItems.food_strawberry.get(), ModItems.seed_strawberry.get(), strawberry_condition));
		
		// plants
		this.dropSelf(ModBlocks.cattail.get());
		this.dropSelf(ModBlocks.flower_autumn_shrub.get());
		
		// grasses
		this.add(ModBlocks.brown_grass_short.get(), createGrassDrops(ModBlocks.brown_grass_short.get()));
		this.add(ModBlocks.brown_grass_tall.get(), createGrassDrops(ModBlocks.brown_grass_tall.get()));
		this.add(ModBlocks.dead_grass_short.get(), createGrassDrops(ModBlocks.dead_grass_short.get()));
		this.add(ModBlocks.dead_grass_tall.get(), createGrassDrops(ModBlocks.dead_grass_tall.get()));
		this.add(ModBlocks.dead_grass_yellow.get(), createGrassDrops(ModBlocks.dead_grass_yellow.get()));
		
		// vines
		this.add(ModBlocks.vine_gloriosa.get(), createShearsOnlyDrop(ModBlocks.vine_gloriosa.get()));
		this.add(ModBlocks.vine_spanish_moss.get(), createShearsOnlyDrop(ModBlocks.vine_spanish_moss.get()));
		
		// terrain blocks
		this.dropSelf(ModBlocks.crackedsand.get());
		this.dropSelf(ModBlocks.quicksand.get());
		this.dropSelf(ModBlocks.redcobble.get());
		this.dropSelf(ModBlocks.redrock_brick.get());
		this.dropOther(ModBlocks.redrock.get(), ModBlocks.redcobble.get());
		
	} // end generate()


} // end class
