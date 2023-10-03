package net.extrabiomes.datagen;

import java.util.concurrent.CompletableFuture;

import mod.alexndr.simplecorelib.api.datagen.MiningItemTags;
import mod.alexndr.simplecorelib.api.helpers.TagUtils;
import net.extrabiomes.ExtrabiomesXS;
import net.extrabiomes.init.ModBlocks;
import net.extrabiomes.init.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ExtrabiomesItemTags extends MiningItemTags 
{

	public ExtrabiomesItemTags(PackOutput output, CompletableFuture<Provider> lookupProvider,
			CompletableFuture<TagLookup<Block>> blockTagProvider, ExistingFileHelper existingFileHelper) 
	{
		super(output, lookupProvider, blockTagProvider, ExtrabiomesXS.MODID, existingFileHelper);
	}

	@Override
    protected void addTags(HolderLookup.Provider lookupProvider)
    {
        super.addTags(lookupProvider);
        registerLogTags();
        registerWoodPlankItems();
        registerNonWoodItems();
        registerOtherPlantTags();
        registerMisc();
    }

	private void registerOtherPlantTags()
	{
   		// crops/food
   		this.tag(Tags.Items.CROPS)
   			.addTag(TagUtils.forgeTag("crops/strawberry"));
   		this.tag(TagUtils.forgeTag("crops/strawberry"))
   			.add(ModItems.food_strawberry.get());
		
   	   	this.tag(ItemTags.VILLAGER_PLANTABLE_SEEDS)
			.add(ModItems.food_strawberry.get());
   	 
   	   	this.tag(ItemTags.FOX_FOOD)
   	   		.add(ModItems.food_strawberry.get());
   	   	   		
   		// flowers
   		this.tag(ItemTags.SMALL_FLOWERS)
   			.add(ModBlocks.flower_hydrangea.get().asItem())
   			.add(ModBlocks.flower_buttercup.get().asItem())
   			.add(ModBlocks.flower_lavender.get().asItem())
   			.add(ModBlocks.flower_tiny_cactus.get().asItem())
   			.add(ModBlocks.flower_allium.get().asItem())
   			.add(ModBlocks.flower_calla_white.get().asItem())
			.add(ModBlocks.flower_amaryllis_pink.get().asItem())
			.add(ModBlocks.flower_amaryllis_red.get().asItem())
			.add(ModBlocks.flower_amaryllis_white.get().asItem())
			.add(ModBlocks.flower_bachelors_button.get().asItem())
			.add(ModBlocks.flower_bells_of_ireland.get().asItem())
			.add(ModBlocks.flower_bluebell.get().asItem())
			.add(ModBlocks.flower_calla_black.get().asItem())
			.add(ModBlocks.flower_daisy.get().asItem())
			.add(ModBlocks.flower_dandelion.get().asItem())
			.add(ModBlocks.flower_gardenia.get().asItem())
			.add(ModBlocks.flower_gerbera_orange.get().asItem())
			.add(ModBlocks.flower_gerbera_pink.get().asItem())
			.add(ModBlocks.flower_gerbera_red.get().asItem())
			.add(ModBlocks.flower_gerbera_yellow.get().asItem())			
   			.add(ModBlocks.flower_redrover.get().asItem())
		   	.add(ModBlocks.flower_oriental_pink_lily.get().asItem())
	    	.add(ModBlocks.flower_lily.get().asItem())
	    	.add(ModBlocks.flower_iris_blue.get().asItem())
	    	.add(ModBlocks.flower_iris_purple.get().asItem())
	    	.add(ModBlocks.flower_marsh_marigold.get().asItem())
	    	.add(ModBlocks.flower_pansy.get().asItem())
	    	.add(ModBlocks.flower_poppy.get().asItem())
	    	.add(ModBlocks.flower_blue_poppy.get().asItem())
	    	.add(ModBlocks.flower_snapdragon.get().asItem())
	    	.add(ModBlocks.flower_tulip.get().asItem())
	    	.add(ModBlocks.flower_violet.get().asItem())
	    	.add(ModBlocks.flower_yarrow.get().asItem())
	    	.add(ModBlocks.flower_belladonna.get().asItem());
   		
	} // end registerOtherPlantTags()
	
   	private void registerMisc()
    {
   		// food
   		this.tag(TagUtils.forgeTag("food"))
   			.addTag(TagUtils.forgeTag("food/chocolate"));
   		
   		this.tag(TagUtils.forgeTag("chocolatebar")) // HarvestCraft chocolate
			.add(ModItems.food_chocolate.get());
   		this.tag(TagUtils.forgeTag("chocolates"))  // Croptopia chocolate
   			.add(ModItems.food_chocolate.get());

   		this.tag(TagUtils.forgeTag("food/chocolate"))  // my chocolate
   			.addTag(TagUtils.forgeTag("chocolatebar"))
   			.addTag(TagUtils.forgeTag("chocolates"))
   			.add(ModItems.food_chocolate.get());

		this.tag(ItemTags.SMELTS_TO_GLASS)
			.add(ModBlocks.crackedsand.get().asItem())
			.add(ModBlocks.quicksand.get().asItem());

		this.tag(TagUtils.forgeTag("tools"))
			.add(ModItems.log_turner.get());
		
    } // end registerMisc()
   	
   	/**
   	 * wooden building blocks
   	 */
    private void registerWoodPlankItems()
    {
		this.tag(ItemTags.WOODEN_STAIRS)
			.add(ModBlocks.stairs_japanesemaple.get().asItem())
			.add(ModBlocks.stairs_autumn.get().asItem())
			.add(ModBlocks.stairs_fir.get().asItem())
			.add(ModBlocks.stairs_redwood.get().asItem())
			.add(ModBlocks.stairs_acacia.get().asItem())
			.add(ModBlocks.stairs_baldcypress.get().asItem());
		
		this.tag(ItemTags.WOODEN_SLABS)
			.add(ModBlocks.slab_japanese_maple.get().asItem())
			.add(ModBlocks.slab_autumn.get().asItem())
			.add(ModBlocks.slab_fir.get().asItem())
			.add(ModBlocks.slab_redwood.get().asItem())
			.add(ModBlocks.slab_acacia.get().asItem())
			.add(ModBlocks.slab_baldcypress.get().asItem());
		
	    this.tag(ItemTags.WOODEN_DOORS)
	    	.add(ModBlocks.door_japanesemaple.get().asItem())
       		.add(ModBlocks.door_autumn.get().asItem())
       		.add(ModBlocks.door_fir.get().asItem())
       		.add(ModBlocks.door_redwood.get().asItem())
       		.add(ModBlocks.door_acacia.get().asItem())
       		.add(ModBlocks.door_baldcypress.get().asItem());
	    
        this.tag(Tags.Items.FENCES_WOODEN)
        	.add(ModBlocks.fence_japanesemaple.get().asItem())
        	.add(ModBlocks.fence_autumn.get().asItem())
        	.add(ModBlocks.fence_fir.get().asItem())
        	.add(ModBlocks.fence_redwood.get().asItem())
    		.add(ModBlocks.fence_acacia.get().asItem())
    		.add(ModBlocks.fence_baldcypress.get().asItem());

        this.tag(Tags.Items.FENCE_GATES_WOODEN)
        	.add(ModBlocks.gate_japanesemaple.get().asItem())
        	.add(ModBlocks.gate_autumn.get().asItem())
        	.add(ModBlocks.gate_fir.get().asItem())
        	.add(ModBlocks.gate_redwood.get().asItem())
        	.add(ModBlocks.gate_acacia.get().asItem())
        	.add(ModBlocks.gate_baldcypress.get().asItem());
        
        this.tag(ItemTags.WOODEN_PRESSURE_PLATES)
        	.add(ModBlocks.pressureplate_japanesemaple.get().asItem())
    		.add(ModBlocks.pressureplate_autumn.get().asItem())
    		.add(ModBlocks.pressureplate_fir.get().asItem())
    		.add(ModBlocks.pressureplate_redwood.get().asItem())
        	.add(ModBlocks.pressureplate_acacia.get().asItem())
        	.add(ModBlocks.pressureplate_baldcypress.get().asItem());
    
        this.tag(ItemTags.WOODEN_BUTTONS)
        	.add(ModBlocks.button_japanesemaple.get().asItem())
    		.add(ModBlocks.button_autumn.get().asItem())
    		.add(ModBlocks.button_fir.get().asItem())
    		.add(ModBlocks.button_redwood.get().asItem())
    		.add(ModBlocks.button_acacia.get().asItem())
    		.add(ModBlocks.button_baldcypress.get().asItem());
    
    } // end registerWoodPlankItems()
    
    /**
     * non-wooden building blocks.
     */
    private void registerNonWoodItems()
    {
		this.tag(ItemTags.STAIRS)
			.add(ModBlocks.stairs_redcobble.get().asItem())
			.add(ModBlocks.stairs_redrock.get().asItem())
			.add(ModBlocks.stairs_redrockbrick.get().asItem());
		
		this.tag(ItemTags.SLABS)
			.add(ModBlocks.slab_redcobble.get().asItem())
			.add(ModBlocks.slab_redrock.get().asItem())
			.add(ModBlocks.slab_redrockbrick.get().asItem());
		
		this.tag(ItemTags.WALLS)
			.add(ModBlocks.wall_redcobble.get().asItem())
			.add(ModBlocks.wall_redrockbrick.get().asItem());
    } // end registerNonWoodItems
    
    
    protected void registerLogTags()
    {
        this.tag(ItemTags.LEAVES)
        	.add(ModBlocks.leaves_japanese_maple.get().asItem())
			.add(ModBlocks.leaves_citrine.get().asItem())
			.add(ModBlocks.leaves_goldenrod.get().asItem())
			.add(ModBlocks.leaves_umber.get().asItem())
			.add(ModBlocks.leaves_vermillion.get().asItem())
			.add(ModBlocks.leaves_fir.get().asItem())
			.add(ModBlocks.leaves_redwood.get().asItem())
			.add(ModBlocks.leaves_acacia.get().asItem())
			.add(ModBlocks.leaves_cypress.get().asItem())
			.add(ModBlocks.leaves_bald_cypress.get().asItem())
			.add(ModBlocks.leaves_rainbow_eucalyptus.get().asItem())
			.add(ModBlocks.leaves_sakura.get().asItem());

        this.tag(ItemTags.SAPLINGS)
        	.add(ModBlocks.sapling_japanese_maple.get().asItem())
	    	.add(ModBlocks.sapling_citrine.get().asItem())
	    	.add(ModBlocks.sapling_goldenrod.get().asItem())
	    	.add(ModBlocks.sapling_umber.get().asItem())
	    	.add(ModBlocks.sapling_vermillion.get().asItem());

		// TODO add stripped wood, bark when available.
		this.tag(TagUtils.modTag(ExtrabiomesXS.MODID, "autumn_logs"))
           	.add(ModBlocks.log_autumn.get().asItem());
		this.tag(TagUtils.modTag(ExtrabiomesXS.MODID, "japanese_maple_logs"))
			.add(ModBlocks.log_japanese_maple.get().asItem());
		this.tag(TagUtils.modTag(ExtrabiomesXS.MODID, "fir_logs"))
			.add(ModBlocks.log_fir.get().asItem())
			.add(ModBlocks.firquarter.get().asItem());
		this.tag(TagUtils.modTag(ExtrabiomesXS.MODID, "redwood_logs"))
			.add(ModBlocks.log_redwood.get().asItem())
			.add(ModBlocks.redwoodquarter.get().asItem());
		this.tag(TagUtils.modTag(ExtrabiomesXS.MODID, "black_wattle_logs"))
			.add(ModBlocks.log_acacia.get().asItem());
		this.tag(ItemTags.OAK_LOGS)
			.add(ModBlocks.oakquarter.get().asItem());
		this.tag(TagUtils.modTag(ExtrabiomesXS.MODID, "baldcypress_logs"))
			.add(ModBlocks.log_baldcypress.get().asItem())
			.add(ModBlocks.baldcypressquarter.get().asItem())
			.add(ModBlocks.baldcypresskneelog.get().asItem());
		
        this.tag(ItemTags.LOGS_THAT_BURN)
			.addTag(TagUtils.modTag(ExtrabiomesXS.MODID, "japanese_maple_logs"))
        	.addTag(TagUtils.modTag(ExtrabiomesXS.MODID, "autumn_logs"))	
        	.addTag(TagUtils.modTag(ExtrabiomesXS.MODID, "fir_logs"))
        	.addTag(TagUtils.modTag(ExtrabiomesXS.MODID, "redwood_logs"))
        	.addTag(TagUtils.modTag(ExtrabiomesXS.MODID, "black_wattle_logs"))
        	.addTag(TagUtils.modTag(ExtrabiomesXS.MODID, "baldcypress_logs"));
        	
        this.tag(ItemTags.PLANKS)
        	.add(ModBlocks.planks_japanese_maple.get().asItem())
        	.add(ModBlocks.planks_autumn_wood.get().asItem())
        	.add(ModBlocks.planks_fir.get().asItem())
        	.add(ModBlocks.planks_redwood.get().asItem())
        	.add(ModBlocks.planks_acacia.get().asItem())
        	.add(ModBlocks.planks_baldcypress.get().asItem());

    } // end registerLogTags()
    
} // end class
