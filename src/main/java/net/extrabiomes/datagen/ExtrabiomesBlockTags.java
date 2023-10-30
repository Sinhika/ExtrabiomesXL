package net.extrabiomes.datagen;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import mod.alexndr.simplecorelib.api.datagen.MiningBlockTags;
import mod.alexndr.simplecorelib.api.helpers.TagUtils;
import net.extrabiomes.ExtrabiomesXS;
import net.extrabiomes.init.ModBlocks;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ExtrabiomesBlockTags extends MiningBlockTags 
{

	public ExtrabiomesBlockTags(PackOutput output, CompletableFuture<Provider> lookupProvider, ExistingFileHelper existingFileHelper) 
	{
		super(output, lookupProvider, ExtrabiomesXS.MODID, existingFileHelper);
	}

	
	@Override
	protected void addTags(Provider pProvider) 
	{
		super.addTags(pProvider);
		registerPlankBlockTags();
		registerLogTags();
		registerLeafSaplingTags();
		registerOtherPlantTags();
		registerNonWoodBlockTags();
		registerMiscTags();
	} // end addTags()


	@Override
	protected void registerMiningTags() 
	{
		registerMineableTags(List.of(ModBlocks.crackedsand.get(), ModBlocks.redcobble.get(), ModBlocks.redrock.get(),
				ModBlocks.redrock_brick.get(), ModBlocks.slab_redcobble.get(), ModBlocks.slab_redrock.get(),
				ModBlocks.slab_redrockbrick.get(), ModBlocks.stairs_redcobble.get(), ModBlocks.stairs_redrock.get(),
				ModBlocks.stairs_redrockbrick.get()), 
				List.of(), List.of(), List.of(), List.of());
		registerAxeableTags(List.of(ModBlocks.vine_gloriosa.get(), ModBlocks.vine_spanish_moss.get()));
		registerShovelableTags(List.of(ModBlocks.quicksand.get()));
		
		this.tag(BlockTags.SWORD_EFFICIENT)
			.add(ModBlocks.vine_gloriosa.get())
			.add(ModBlocks.vine_spanish_moss.get());
	} // end registerMiningTags()
	

	@Override
	protected void registerOreTags() {
		// TODO Auto-generated method stub
	}

	/**
	 * non-wood parallel to registerPlankBlockTags().
	 */
	private void registerNonWoodBlockTags()
	{
		this.tag(BlockTags.STAIRS)
			.add(ModBlocks.stairs_redcobble.get())
			.add(ModBlocks.stairs_redrock.get())
			.add(ModBlocks.stairs_redrockbrick.get());
		
		this.tag(BlockTags.SLABS)
			.add(ModBlocks.slab_redcobble.get())
			.add(ModBlocks.slab_redrock.get())
			.add(ModBlocks.slab_redrockbrick.get());
		
		this.tag(BlockTags.WALLS)
			.add(ModBlocks.wall_redcobble.get())
			.add(ModBlocks.wall_redrockbrick.get());
		
	} // end registerNonWoodBlockTags()
	
	/**
	 * note that we only need to add blocks to the most specific tag, such as BlockTags.WOODEN_STAIRS,
	 * because BlockTags.STAIRS automatically includes all WOODEN_STAIRS. Same for everthing WOODEN_*.
	 */
	private void registerPlankBlockTags()
	{

		this.tag(BlockTags.WOODEN_STAIRS)
			.add(ModBlocks.stairs_japanesemaple.get())
			.add(ModBlocks.stairs_autumn.get())
			.add(ModBlocks.stairs_fir.get())
			.add(ModBlocks.stairs_redwood.get())
			.add(ModBlocks.stairs_acacia.get())
			.add(ModBlocks.stairs_baldcypress.get())
			.add(ModBlocks.stairs_cypress.get())
			.add(ModBlocks.stairs_rainboweucalyptus.get())
			.add(ModBlocks.stairs_sakura.get());
		
		this.tag(BlockTags.WOODEN_SLABS)
			.add(ModBlocks.slab_japanese_maple.get())
			.add(ModBlocks.slab_autumn.get())
			.add(ModBlocks.slab_fir.get())
			.add(ModBlocks.slab_redwood.get())
			.add(ModBlocks.slab_acacia.get())
			.add(ModBlocks.slab_baldcypress.get())
			.add(ModBlocks.slab_cypress.get())
			.add(ModBlocks.slab_rainboweucalyptus.get())
			.add(ModBlocks.slab_sakura.get());
		
		this.addFencetoAllFenceTags(ModBlocks.fence_autumn.get());
		this.addFencetoAllFenceTags(ModBlocks.fence_japanesemaple.get());
		this.addFencetoAllFenceTags(ModBlocks.fence_fir.get());
		this.addFencetoAllFenceTags(ModBlocks.fence_redwood.get());
		this.addFencetoAllFenceTags(ModBlocks.fence_acacia.get());
		this.addFencetoAllFenceTags(ModBlocks.fence_baldcypress.get());
		this.addFencetoAllFenceTags(ModBlocks.fence_cypress.get());
		this.addFencetoAllFenceTags(ModBlocks.fence_rainboweucalyptus.get());
		this.addFencetoAllFenceTags(ModBlocks.fence_sakura.get());
		
		this.addGatetoAllGateTags(ModBlocks.gate_autumn.get());
		this.addGatetoAllGateTags(ModBlocks.gate_japanesemaple.get());
		this.addGatetoAllGateTags(ModBlocks.gate_fir.get());
		this.addGatetoAllGateTags(ModBlocks.gate_redwood.get());
		this.addGatetoAllGateTags(ModBlocks.gate_acacia.get());
		this.addGatetoAllGateTags(ModBlocks.gate_baldcypress.get());
		this.addGatetoAllGateTags(ModBlocks.gate_cypress.get());
		this.addGatetoAllGateTags(ModBlocks.gate_rainboweucalyptus.get());
		this.addGatetoAllGateTags(ModBlocks.gate_sakura.get());
		
        this.tag(BlockTags.WOODEN_DOORS)
        	.add(ModBlocks.door_japanesemaple.get())
        	.add(ModBlocks.door_autumn.get())
        	.add(ModBlocks.door_fir.get())
        	.add(ModBlocks.door_redwood.get())
        	.add(ModBlocks.door_acacia.get())
        	.add(ModBlocks.door_baldcypress.get())
    		.add(ModBlocks.door_cypress.get())
			.add(ModBlocks.door_rainboweucalyptus.get())
			.add(ModBlocks.door_sakura.get());
        
        this.tag(BlockTags.WOODEN_PRESSURE_PLATES)
        	.add(ModBlocks.pressureplate_japanesemaple.get())
        	.add(ModBlocks.pressureplate_autumn.get())
        	.add(ModBlocks.pressureplate_fir.get())
        	.add(ModBlocks.pressureplate_redwood.get())
        	.add(ModBlocks.pressureplate_acacia.get())
        	.add(ModBlocks.pressureplate_baldcypress.get())
			.add(ModBlocks.pressureplate_cypress.get())
			.add(ModBlocks.pressureplate_rainboweucalyptus.get())
			.add(ModBlocks.pressureplate_sakura.get());
        
        this.tag(BlockTags.WOODEN_BUTTONS)
        	.add(ModBlocks.button_japanesemaple.get())
        	.add(ModBlocks.button_autumn.get())
        	.add(ModBlocks.button_fir.get())
        	.add(ModBlocks.button_redwood.get())
        	.add(ModBlocks.button_acacia.get())
        	.add(ModBlocks.button_baldcypress.get())
    		.add(ModBlocks.button_cypress.get())
			.add(ModBlocks.button_rainboweucalyptus.get())
			.add(ModBlocks.button_sakura.get());

	} // end registerPlankBlockTags
   
	// also too many gate tags.
	private void addGatetoAllGateTags(FenceGateBlock fg)
	{
		this.tag(Tags.Blocks.FENCE_GATES_WOODEN).add(fg);
		this.tag(BlockTags.FENCE_GATES).add(fg);
	}
	
	// because there are too many fence tags!!
	private void addFencetoAllFenceTags(FenceBlock ff)
	{
	      this.tag(Tags.Blocks.FENCES_WOODEN).add(ff);
	      this.tag(BlockTags.FENCES).add(ff);
	}
	
	private void registerLogTags()
	{
		// TODO add stripped wood, bark when available.
		this.tag(TagUtils.modBlockTag(ExtrabiomesXS.MODID, "autumn_logs"))
           	.add(ModBlocks.log_autumn.get());
		this.tag(TagUtils.modBlockTag(ExtrabiomesXS.MODID, "japanese_maple_logs"))
			.add(ModBlocks.log_japanese_maple.get());
		this.tag(TagUtils.modBlockTag(ExtrabiomesXS.MODID, "fir_logs"))
			.add(ModBlocks.log_fir.get())
			.add(ModBlocks.firquarter.get());
		this.tag(TagUtils.modBlockTag(ExtrabiomesXS.MODID, "redwood_logs"))
			.add(ModBlocks.log_redwood.get())
			.add(ModBlocks.redwoodquarter.get());
		this.tag(TagUtils.modBlockTag(ExtrabiomesXS.MODID, "black_wattle_logs"))
			.add(ModBlocks.log_acacia.get());
		this.tag(BlockTags.OAK_LOGS)
			.add(ModBlocks.oakquarter.get());
		this.tag(TagUtils.modBlockTag(ExtrabiomesXS.MODID, "baldcypress_logs"))
			.add(ModBlocks.log_baldcypress.get())
			.add(ModBlocks.baldcypressquarter.get())
			.add(ModBlocks.baldcypresskneelog.get());
		this.tag(TagUtils.modBlockTag(ExtrabiomesXS.MODID, "rainboweucalyptus_logs"))
			.add(ModBlocks.log_rainboweucalyptus.get())
			.add(ModBlocks.rainboweucalyptusquarter.get())
			.add(ModBlocks.rainboweucalyptuskneelog.get());
		this.tag(TagUtils.modBlockTag(ExtrabiomesXS.MODID, "cypress_logs"))
			.add(ModBlocks.log_cypress.get());
		this.tag(TagUtils.modBlockTag(ExtrabiomesXS.MODID, "sakura_logs"))
			.add(ModBlocks.log_sakura.get());

		// only add actual logs--not stripped--to this tag.
		this.tag(BlockTags.OVERWORLD_NATURAL_LOGS)
			.add(ModBlocks.log_autumn.get())
			.add(ModBlocks.log_japanese_maple.get())
			.add(ModBlocks.log_fir.get())
			.add(ModBlocks.firquarter.get())
			.add(ModBlocks.log_redwood.get())
			.add(ModBlocks.redwoodquarter.get())
			.add(ModBlocks.log_acacia.get())
			.add(ModBlocks.oakquarter.get())
			.add(ModBlocks.log_baldcypress.get())
			.add(ModBlocks.baldcypressquarter.get())
			.add(ModBlocks.baldcypresskneelog.get())
			.add(ModBlocks.log_cypress.get())
			.add(ModBlocks.log_sakura.get())
			.add(ModBlocks.log_rainboweucalyptus.get())
			.add(ModBlocks.rainboweucalyptuskneelog.get())
			.add(ModBlocks.rainboweucalyptusquarter.get());
		
		// LOGS_THAT_BURN are automatically included in LOGS.
		this.tag(BlockTags.LOGS_THAT_BURN)
			.addTag(TagUtils.modBlockTag(ExtrabiomesXS.MODID, "japanese_maple_logs"))
			.addTag(TagUtils.modBlockTag(ExtrabiomesXS.MODID, "autumn_logs"))
			.addTag(TagUtils.modBlockTag(ExtrabiomesXS.MODID, "fir_logs"))
			.addTag(TagUtils.modBlockTag(ExtrabiomesXS.MODID, "redwood_logs"))
			.addTag(TagUtils.modBlockTag(ExtrabiomesXS.MODID, "black_wattle_logs"))
			.addTag(TagUtils.modBlockTag(ExtrabiomesXS.MODID, "baldcypress_logs"))
			.addTag(TagUtils.modBlockTag(ExtrabiomesXS.MODID, "cypress_logs"))
			.addTag(TagUtils.modBlockTag(ExtrabiomesXS.MODID, "rainboweucalyptus_logs"))
			.addTag(TagUtils.modBlockTag(ExtrabiomesXS.MODID, "sakura_logs"));
		
        this.tag(BlockTags.PLANKS)
        	.add(ModBlocks.planks_japanese_maple.get())
        	.add(ModBlocks.planks_autumn_wood.get())
        	.add(ModBlocks.planks_fir.get())
        	.add(ModBlocks.planks_redwood.get())
        	.add(ModBlocks.planks_acacia.get())
        	.add(ModBlocks.planks_baldcypress.get())
        	.add(ModBlocks.planks_cypress.get())
        	.add(ModBlocks.planks_rainboweucalyptus.get())
        	.add(ModBlocks.planks_sakura.get());
        
	} // end registerLogTags()
   
	private void registerLeafSaplingTags()
	{
		this.tag(BlockTags.LEAVES)
			.add(ModBlocks.leaves_japanese_maple.get())
			.add(ModBlocks.leaves_citrine.get())
			.add(ModBlocks.leaves_goldenrod.get())
			.add(ModBlocks.leaves_umber.get())
			.add(ModBlocks.leaves_vermillion.get())
			.add(ModBlocks.leaves_japanese_maple_shrub.get())
			.add(ModBlocks.leaves_fir.get())
			.add(ModBlocks.leaves_redwood.get())
			.add(ModBlocks.leaves_acacia.get())
			.add(ModBlocks.leaves_cypress.get())
			.add(ModBlocks.leaves_bald_cypress.get())
			.add(ModBlocks.leaves_rainbow_eucalyptus.get())
			.add(ModBlocks.leaves_sakura.get());
	      
        this.tag(BlockTags.SAPLINGS)
        	.add(ModBlocks.sapling_japanese_maple.get())
	    	.add(ModBlocks.sapling_citrine.get())
	    	.add(ModBlocks.sapling_goldenrod.get())
	    	.add(ModBlocks.sapling_umber.get())
	    	.add(ModBlocks.sapling_vermillion.get())
	    	.add(ModBlocks.sapling_japanese_maple_shrub.get())
	    	.add(ModBlocks.sapling_fir.get())
	    	.add(ModBlocks.sapling_redwood.get())
	    	.add(ModBlocks.sapling_acacia.get())
	    	.add(ModBlocks.sapling_cypress.get())
	    	.add(ModBlocks.sapling_bald_cypress.get())
	    	.add(ModBlocks.sapling_rainbow_eucalyptus.get())
	    	.add(ModBlocks.sapling_sakura.get());

	} // end registerLeafSaplingTags()
	
	
	private void registerOtherPlantTags()
	{
		// flowers
		this.tag(BlockTags.SMALL_FLOWERS)
			.add(ModBlocks.flower_redrover.get())
			.add(ModBlocks.flower_buttercup.get())
			.add(ModBlocks.flower_lavender.get())
			.add(ModBlocks.flower_tiny_cactus.get())
			.add(ModBlocks.flower_allium.get())
			.add(ModBlocks.flower_calla_white.get())
			.add(ModBlocks.flower_amaryllis_pink.get())
			.add(ModBlocks.flower_amaryllis_red.get())
			.add(ModBlocks.flower_amaryllis_white.get())
			.add(ModBlocks.flower_bachelors_button.get())
			.add(ModBlocks.flower_bells_of_ireland.get())
			.add(ModBlocks.flower_bluebell.get())
			.add(ModBlocks.flower_calla_black.get())
			.add(ModBlocks.flower_daisy.get())
			.add(ModBlocks.flower_dandelion.get())
			.add(ModBlocks.flower_gardenia.get())
			.add(ModBlocks.flower_gerbera_orange.get())
			.add(ModBlocks.flower_gerbera_pink.get())
			.add(ModBlocks.flower_gerbera_red.get())
			.add(ModBlocks.flower_gerbera_yellow.get())
			.add(ModBlocks.flower_hydrangea.get())
		   	.add(ModBlocks.flower_oriental_pink_lily.get())
	    	.add(ModBlocks.flower_lily.get())
	    	.add(ModBlocks.flower_iris_blue.get())
	    	.add(ModBlocks.flower_iris_purple.get())
	    	.add(ModBlocks.flower_marsh_marigold.get())
	    	.add(ModBlocks.flower_pansy.get())
	    	.add(ModBlocks.flower_poppy.get())
	    	.add(ModBlocks.flower_blue_poppy.get())
	    	.add(ModBlocks.flower_snapdragon.get())
	    	.add(ModBlocks.flower_tulip.get())
	    	.add(ModBlocks.flower_violet.get())
	    	.add(ModBlocks.flower_yarrow.get())
	    	.add(ModBlocks.flower_belladonna.get());
			
		// flower pots
		this.tag(BlockTags.FLOWER_POTS)
			.add(ModBlocks.potted_redrover.get())
			.add(ModBlocks.potted_buttercup.get())
			.add(ModBlocks.potted_lavender.get())
			.add(ModBlocks.potted_tiny_cactus.get())
			.add(ModBlocks.potted_allium.get())
			.add(ModBlocks.potted_calla_white.get())
			.add(ModBlocks.potted_amaryllis_pink.get())
			.add(ModBlocks.potted_amaryllis_red.get())
			.add(ModBlocks.potted_amaryllis_white.get())
			.add(ModBlocks.potted_bachelors_button.get())
			.add(ModBlocks.potted_sapling_citrine.get())
			.add(ModBlocks.potted_sapling_umber.get())
			.add(ModBlocks.potted_sapling_goldenrod.get())
			.add(ModBlocks.potted_sapling_japanese_maple.get())
			.add(ModBlocks.potted_sapling_vermillion.get())
			.add(ModBlocks.potted_bells_of_ireland.get())
			.add(ModBlocks.potted_bluebell.get())
			.add(ModBlocks.potted_calla_black.get())
			.add(ModBlocks.potted_daisy.get())
			.add(ModBlocks.potted_dandelion.get())
			.add(ModBlocks.potted_gardenia.get())
			.add(ModBlocks.potted_gerbera_orange.get())
			.add(ModBlocks.potted_gerbera_pink.get())
			.add(ModBlocks.potted_gerbera_red.get())
			.add(ModBlocks.potted_gerbera_yellow.get())
			.add(ModBlocks.potted_hydrangea.get())
		   	.add(ModBlocks.potted_oriental_pink_lily.get())
	    	.add(ModBlocks.potted_lily.get())
	    	.add(ModBlocks.potted_iris_blue.get())
	    	.add(ModBlocks.potted_iris_purple.get())
	    	.add(ModBlocks.potted_marsh_marigold.get())
	    	.add(ModBlocks.potted_pansy.get())
	    	.add(ModBlocks.potted_poppy.get())
	    	.add(ModBlocks.potted_blue_poppy.get())
	    	.add(ModBlocks.potted_snapdragon.get())
	    	.add(ModBlocks.potted_tulip.get())
	    	.add(ModBlocks.potted_violet.get())
	    	.add(ModBlocks.potted_yarrow.get())
	    	.add(ModBlocks.potted_belladonna.get())
	    	.add(ModBlocks.potted_sapling_japanese_maple_shrub.get())
	    	.add(ModBlocks.potted_sapling_fir.get())
	    	.add(ModBlocks.potted_sapling_redwood.get())
	    	.add(ModBlocks.potted_sapling_acacia.get())
	    	.add(ModBlocks.potted_sapling_cypress.get())
	    	.add(ModBlocks.potted_sapling_bald_cypress.get())
	    	.add(ModBlocks.potted_sapling_rainbow_eucalyptus.get())
	    	.add(ModBlocks.potted_sapling_sakura.get());

		
		// crops
		this.tag(BlockTags.MAINTAINS_FARMLAND)
			.add(ModBlocks.crop_strawberry.get());
		this.tag(BlockTags.CROPS)
			.add(ModBlocks.crop_strawberry.get());
		
		this.tag(BlockTags.REPLACEABLE_BY_TREES)
			.add(ModBlocks.flower_autumn_shrub.get())
			.add(ModBlocks.vine_gloriosa.get())
			.add(ModBlocks.vine_spanish_moss.get())
			.add(ModBlocks.brown_grass_short.get())
			.add(ModBlocks.brown_grass_tall.get())
			.add(ModBlocks.dead_grass_short.get())
			.add(ModBlocks.dead_grass_tall.get())
			.add(ModBlocks.dead_grass_yellow.get());
		
		this.tag(BlockTags.CLIMBABLE)
			.add(ModBlocks.vine_gloriosa.get());
		// note: vine_spanish_moss is too weak to climb.
		
		this.tag(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH)
			.add(ModBlocks.vine_gloriosa.get())
			.add(ModBlocks.vine_spanish_moss.get())
			.add(ModBlocks.leafpile.get());
		
		this.tag(BlockTags.MANGROVE_ROOTS_CAN_GROW_THROUGH)
			.add(ModBlocks.vine_gloriosa.get())
			.add(ModBlocks.vine_spanish_moss.get())
			.add(ModBlocks.leafpile.get());
		
		this.tag(TagUtils.modBlockTag(ExtrabiomesXS.MODID, "logs_can_grow_through"))
			.add(Blocks.VINE)
			.add(Blocks.MUD)
			.add(Blocks.MOSS_CARPET)
			.add(ModBlocks.vine_gloriosa.get())
			.add(ModBlocks.vine_spanish_moss.get())
			.add(ModBlocks.leafpile.get());

	} // end registerOtherPlantTags()
	
	
	private void registerMiscTags()
	{
		this.tag(BlockTags.DEAD_BUSH_MAY_PLACE_ON)
			.add(ModBlocks.crackedsand.get())
			.add(ModBlocks.redrock.get());
		
		this.tag(BlockTags.OVERWORLD_CARVER_REPLACEABLES)
			.add(ModBlocks.crackedsand.get())
			.add(ModBlocks.redrock.get());
		
		this.tag(BlockTags.CONVERTABLE_TO_MUD)
			.add(ModBlocks.crackedsand.get());
		
		this.tag(BlockTags.SCULK_REPLACEABLE)
			.add(ModBlocks.crackedsand.get());
		
		this.tag(BlockTags.SMELTS_TO_GLASS)
			.add(ModBlocks.crackedsand.get())
			.add(ModBlocks.quicksand.get());
		
	} // end registerMiscTags()
	
} // end class
