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
		registerMiscTags();
	} // end addTags()


	@Override
	protected void registerMiningTags() 
	{
		registerMineableTags(List.of(ModBlocks.crackedsand.get(), ModBlocks.redcobble.get(), ModBlocks.redrock.get(),
				ModBlocks.redrock_brick.get()), 
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
	 * note that we only need to add blocks to the most specific tag, such as BlockTags.WOODEN_STAIRS,
	 * because BlockTags.STAIRS automatically includes all WOODEN_STAIRS. Same for everthing WOODEN_*.
	 */
	private void registerPlankBlockTags()
	{

		this.tag(BlockTags.WOODEN_STAIRS)
			.add(ModBlocks.stairs_japanesemaple.get())
			.add(ModBlocks.stairs_autumn.get());
		
		this.tag(BlockTags.WOODEN_SLABS)
			.add(ModBlocks.slab_japanese_maple.get())
			.add(ModBlocks.slab_autumn.get());
		
		this.addFencetoAllFenceTags(ModBlocks.fence_autumn.get());
		this.addFencetoAllFenceTags(ModBlocks.fence_japanesemaple.get());
		
		this.addGatetoAllGateTags(ModBlocks.gate_autumn.get());
		this.addGatetoAllGateTags(ModBlocks.gate_autumn.get());
		
        this.tag(BlockTags.WOODEN_DOORS)
        	.add(ModBlocks.door_japanesemaple.get())
        	.add(ModBlocks.door_autumn.get());
        
        this.tag(BlockTags.WOODEN_PRESSURE_PLATES)
        	.add(ModBlocks.pressureplate_japanesemaple.get())
        	.add(ModBlocks.pressureplate_autumn.get());
        
        this.tag(BlockTags.WOODEN_BUTTONS)
        	.add(ModBlocks.button_japanesemaple.get())
        	.add(ModBlocks.button_autumn.get());
        
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

		// only add actual logs to this tag.
		this.tag(BlockTags.OVERWORLD_NATURAL_LOGS)
			.add(ModBlocks.log_autumn.get())
			.add(ModBlocks.log_japanese_maple.get());
		
		// LOGS_THAT_BURN are automatically included in LOGS.
		this.tag(BlockTags.LOGS_THAT_BURN)
			.addTag(TagUtils.modBlockTag(ExtrabiomesXS.MODID, "japanese_maple_logs"))
			.addTag(TagUtils.modBlockTag(ExtrabiomesXS.MODID, "autumn_logs"));
		
        this.tag(BlockTags.PLANKS)
        	.add(ModBlocks.planks_japanese_maple.get())
        	.add(ModBlocks.planks_autumn_wood.get());
        
	} // end registerLogTags()
   
	private void registerLeafSaplingTags()
	{
		this.tag(BlockTags.LEAVES)
			.add(ModBlocks.leaves_japanese_maple.get())
			.add(ModBlocks.leaves_citrine.get())
			.add(ModBlocks.leaves_goldenrod.get())
			.add(ModBlocks.leaves_umber.get())
			.add(ModBlocks.leaves_vermillion.get());
	      
        this.tag(BlockTags.SAPLINGS)
        	.add(ModBlocks.sapling_japanese_maple.get())
	    	.add(ModBlocks.sapling_citrine.get())
	    	.add(ModBlocks.sapling_goldenrod.get())
	    	.add(ModBlocks.sapling_umber.get())
	    	.add(ModBlocks.sapling_vermillion.get());

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
	    	.add(ModBlocks.potted_belladonna.get());
		
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
			.add(ModBlocks.vine_spanish_moss.get());
		
		this.tag(BlockTags.MANGROVE_ROOTS_CAN_GROW_THROUGH)
			.add(ModBlocks.vine_gloriosa.get())
			.add(ModBlocks.vine_spanish_moss.get());
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
