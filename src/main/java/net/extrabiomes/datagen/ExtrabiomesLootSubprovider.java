package net.extrabiomes.datagen;

import mod.alexndr.simplecorelib.api.datagen.SimpleBlockLootSubProvider;
import net.extrabiomes.init.ModBlocks;
import net.extrabiomes.init.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.world.level.block.Blocks;
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
		this.add(ModBlocks.leaves_japanese_maple_shrub.get(), createLeavesDrops(ModBlocks.leaves_japanese_maple_shrub.get(),
				ModBlocks.sapling_japanese_maple_shrub.get(), NORMAL_LEAVES_SAPLING_CHANCES));
		this.add(ModBlocks.leaves_fir.get(), createLeavesDrops(ModBlocks.leaves_fir.get(),
				ModBlocks.sapling_fir.get(), NORMAL_LEAVES_SAPLING_CHANCES));
		this.add(ModBlocks.leaves_redwood.get(), createLeavesDrops(ModBlocks.leaves_redwood.get(),
				ModBlocks.sapling_redwood.get(), NORMAL_LEAVES_SAPLING_CHANCES));
		this.add(ModBlocks.leaves_acacia.get(), createLeavesDrops(ModBlocks.leaves_acacia.get(),
				ModBlocks.sapling_acacia.get(), NORMAL_LEAVES_SAPLING_CHANCES));
		this.add(ModBlocks.leaves_cypress.get(), createLeavesDrops(ModBlocks.leaves_cypress.get(),
				ModBlocks.sapling_cypress.get(), NORMAL_LEAVES_SAPLING_CHANCES));
		this.add(ModBlocks.leaves_bald_cypress.get(), createLeavesDrops(ModBlocks.leaves_bald_cypress.get(),
				ModBlocks.sapling_bald_cypress.get(), NORMAL_LEAVES_SAPLING_CHANCES));
		this.add(ModBlocks.leaves_rainbow_eucalyptus.get(), createLeavesDrops(ModBlocks.leaves_rainbow_eucalyptus.get(),
				ModBlocks.sapling_rainbow_eucalyptus.get(), NORMAL_LEAVES_SAPLING_CHANCES));
		this.add(ModBlocks.leaves_sakura.get(), createLeavesDrops(ModBlocks.leaves_sakura.get(),
				ModBlocks.sapling_sakura.get(), NORMAL_LEAVES_SAPLING_CHANCES));
		
		// saplings
		this.dropSelf(ModBlocks.sapling_citrine.get());
		this.dropSelf(ModBlocks.sapling_goldenrod.get());
		this.dropSelf(ModBlocks.sapling_umber.get());
		this.dropSelf(ModBlocks.sapling_vermillion.get());
		this.dropSelf(ModBlocks.sapling_japanese_maple_shrub.get());
		this.dropSelf(ModBlocks.sapling_fir.get());
		this.dropSelf(ModBlocks.sapling_redwood.get());
		this.dropSelf(ModBlocks.sapling_acacia.get());
		this.dropSelf(ModBlocks.sapling_cypress.get());
		this.dropSelf(ModBlocks.sapling_bald_cypress.get());
		this.dropSelf(ModBlocks.sapling_rainbow_eucalyptus.get());
		this.dropSelf(ModBlocks.sapling_sakura.get());
		
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
		this.dropPottedContents(ModBlocks.potted_sapling_citrine.get());
		this.dropPottedContents(ModBlocks.potted_sapling_umber.get());
		this.dropPottedContents(ModBlocks.potted_sapling_goldenrod.get());
		this.dropPottedContents(ModBlocks.potted_sapling_vermillion.get());
		this.dropPottedContents(ModBlocks.potted_sapling_japanese_maple.get());
		this.dropPottedContents(ModBlocks.potted_sapling_japanese_maple_shrub.get());
		this.dropPottedContents(ModBlocks.potted_sapling_fir.get());
		this.dropPottedContents(ModBlocks.potted_sapling_redwood.get());
		this.dropPottedContents(ModBlocks.potted_sapling_acacia.get());
		this.dropPottedContents(ModBlocks.potted_sapling_cypress.get());
		this.dropPottedContents(ModBlocks.potted_sapling_bald_cypress.get());
		this.dropPottedContents(ModBlocks.potted_sapling_rainbow_eucalyptus.get());
		this.dropPottedContents(ModBlocks.potted_sapling_sakura.get());

		// logs
		this.dropSelf(ModBlocks.log_autumn.get());
		this.dropSelf(ModBlocks.log_japanese_maple.get());
		this.dropSelf(ModBlocks.log_fir.get());
		this.dropSelf(ModBlocks.log_redwood.get());
		this.dropSelf(ModBlocks.log_acacia.get());
		this.dropSelf(ModBlocks.log_baldcypress.get());
		this.dropSelf(ModBlocks.log_cypress.get());
		this.dropSelf(ModBlocks.log_rainboweucalyptus.get());
		this.dropSelf(ModBlocks.log_sakura.get());
		
		this.dropOther(ModBlocks.oakquarter.get(), Blocks.OAK_LOG);
		this.dropOther(ModBlocks.firquarter.get(), ModBlocks.log_fir.get());
		this.dropOther(ModBlocks.redwoodquarter.get(), ModBlocks.log_redwood.get());
		this.dropOther(ModBlocks.baldcypressquarter.get(), ModBlocks.log_baldcypress.get());
		this.dropOther(ModBlocks.baldcypresskneelog.get(), ModBlocks.log_baldcypress.get());
		this.dropOther(ModBlocks.rainboweucalyptusquarter.get(), ModBlocks.log_rainboweucalyptus.get());
		this.dropOther(ModBlocks.rainboweucalyptuskneelog.get(), ModBlocks.log_rainboweucalyptus.get());
		
		// planks
		this.dropSelf(ModBlocks.planks_autumn_wood.get());
		this.dropSelf(ModBlocks.planks_japanese_maple.get());
		this.dropSelf(ModBlocks.planks_fir.get());
		this.dropSelf(ModBlocks.planks_redwood.get());
		this.dropSelf(ModBlocks.planks_acacia.get());
		this.dropSelf(ModBlocks.planks_baldcypress.get());
		this.dropSelf(ModBlocks.planks_cypress.get());
		this.dropSelf(ModBlocks.planks_rainboweucalyptus.get());
		this.dropSelf(ModBlocks.planks_sakura.get());
		
		// stairs
		this.dropSelf(ModBlocks.stairs_redcobble.get());
		this.dropSelf(ModBlocks.stairs_redrock.get());
		this.dropSelf(ModBlocks.stairs_redrockbrick.get());
		this.dropSelf(ModBlocks.stairs_autumn.get());
		this.dropSelf(ModBlocks.stairs_japanesemaple.get());
		this.dropSelf(ModBlocks.stairs_fir.get());
		this.dropSelf(ModBlocks.stairs_redwood.get());
		this.dropSelf(ModBlocks.stairs_acacia.get());
		this.dropSelf(ModBlocks.planks_baldcypress.get());
		this.dropSelf(ModBlocks.planks_cypress.get());
		this.dropSelf(ModBlocks.planks_rainboweucalyptus.get());
		this.dropSelf(ModBlocks.planks_sakura.get());
		
		// slabs
		this.dropSlab(ModBlocks.slab_redcobble.get());
		this.dropSlab(ModBlocks.slab_redrock.get());
		this.dropSlab(ModBlocks.slab_redrockbrick.get());
		this.dropSlab(ModBlocks.slab_autumn.get());
		this.dropSlab(ModBlocks.slab_japanese_maple.get());
		this.dropSlab(ModBlocks.slab_fir.get());
		this.dropSlab(ModBlocks.slab_redwood.get());
		this.dropSlab(ModBlocks.slab_acacia.get());
		this.dropSlab(ModBlocks.slab_baldcypress.get());
		this.dropSlab(ModBlocks.slab_cypress.get());
		this.dropSlab(ModBlocks.slab_rainboweucalyptus.get());
		this.dropSlab(ModBlocks.slab_sakura.get());
		
		// doors
		this.doorDropTable(ModBlocks.door_autumn.get());
		this.doorDropTable(ModBlocks.door_japanesemaple.get());
		this.doorDropTable(ModBlocks.door_fir.get());
		this.doorDropTable(ModBlocks.door_redwood.get());
		this.doorDropTable(ModBlocks.door_acacia.get());
		this.doorDropTable(ModBlocks.door_baldcypress.get());
		this.doorDropTable(ModBlocks.door_cypress.get());
		this.doorDropTable(ModBlocks.door_rainboweucalyptus.get());
		this.doorDropTable(ModBlocks.door_sakura.get());
		
		// fences
		this.dropSelf(ModBlocks.fence_autumn.get());
		this.dropSelf(ModBlocks.fence_japanesemaple.get());
		this.dropSelf(ModBlocks.fence_fir.get());
		this.dropSelf(ModBlocks.fence_redwood.get());
		this.dropSelf(ModBlocks.fence_acacia.get());
		this.dropSelf(ModBlocks.fence_baldcypress.get());
		this.dropSelf(ModBlocks.fence_cypress.get());
		this.dropSelf(ModBlocks.fence_rainboweucalyptus.get());
		this.dropSelf(ModBlocks.fence_sakura.get());
		
		// fence gates
		this.dropSelf(ModBlocks.gate_autumn.get());
		this.dropSelf(ModBlocks.gate_japanesemaple.get());
		this.dropSelf(ModBlocks.gate_fir.get());
		this.dropSelf(ModBlocks.gate_redwood.get());
		this.dropSelf(ModBlocks.gate_acacia.get());
		this.dropSelf(ModBlocks.gate_baldcypress.get());
		this.dropSelf(ModBlocks.gate_cypress.get());
		this.dropSelf(ModBlocks.gate_rainboweucalyptus.get());
		this.dropSelf(ModBlocks.gate_sakura.get());
		
		// walls
		this.dropSelf(ModBlocks.wall_redcobble.get());
		this.dropSelf(ModBlocks.wall_redrockbrick.get());

		// buttons
		this.dropSelf(ModBlocks.button_autumn.get());
		this.dropSelf(ModBlocks.button_japanesemaple.get());
		this.dropSelf(ModBlocks.button_fir.get());
		this.dropSelf(ModBlocks.button_redwood.get());
		this.dropSelf(ModBlocks.button_acacia.get());
		this.dropSelf(ModBlocks.button_baldcypress.get());
		this.dropSelf(ModBlocks.button_cypress.get());
		this.dropSelf(ModBlocks.button_rainboweucalyptus.get());
		this.dropSelf(ModBlocks.button_sakura.get());
		
		// pressure plates
		this.dropSelf(ModBlocks.pressureplate_autumn.get());
		this.dropSelf(ModBlocks.pressureplate_japanesemaple.get());
		this.dropSelf(ModBlocks.pressureplate_fir.get());
		this.dropSelf(ModBlocks.pressureplate_redwood.get());
		this.dropSelf(ModBlocks.pressureplate_acacia.get());
		this.dropSelf(ModBlocks.pressureplate_baldcypress.get());
		this.dropSelf(ModBlocks.pressureplate_cypress.get());
		this.dropSelf(ModBlocks.pressureplate_rainboweucalyptus.get());
		this.dropSelf(ModBlocks.pressureplate_sakura.get());
		
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
