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
   			.add(ModBlocks.flower_redrover.get().asItem());
   		
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
			.add(ModBlocks.crackedsand.get().asItem());

    } // end registerMisc()
   	
    private void registerWoodPlankItems()
    {
		this.tag(ItemTags.WOODEN_STAIRS)
			.add(ModBlocks.stairs_japanesemaple.get().asItem())
			.add(ModBlocks.stairs_autumn.get().asItem());
		
		this.tag(ItemTags.WOODEN_SLABS)
			.add(ModBlocks.slab_japanese_maple.get().asItem())
			.add(ModBlocks.slab_autumn.get().asItem());
	
	    this.tag(ItemTags.WOODEN_DOORS)
	    	.add(ModBlocks.door_japanesemaple.get().asItem())
       		.add(ModBlocks.door_autumn.get().asItem());

        this.tag(Tags.Items.FENCES_WOODEN)
        	.add(ModBlocks.fence_japanesemaple.get().asItem())
        	.add(ModBlocks.fence_autumn.get().asItem());

        this.tag(Tags.Items.FENCE_GATES_WOODEN)
        	.add(ModBlocks.gate_japanesemaple.get().asItem())
        	.add(ModBlocks.gate_autumn.get().asItem());

        this.tag(ItemTags.WOODEN_PRESSURE_PLATES)
        	.add(ModBlocks.pressureplate_japanesemaple.get().asItem())
    		.add(ModBlocks.pressureplate_autumn.get().asItem());
    
        this.tag(ItemTags.WOODEN_BUTTONS)
        	.add(ModBlocks.button_japanesemaple.get().asItem())
    		.add(ModBlocks.button_autumn.get().asItem());
    
    } // end registerWoodPlankItems()
    
    protected void registerLogTags()
    {
        this.tag(ItemTags.LEAVES)
        	.add(ModBlocks.leaves_japanese_maple.get().asItem())
			.add(ModBlocks.leaves_citrine.get().asItem())
			.add(ModBlocks.leaves_goldenrod.get().asItem())
			.add(ModBlocks.leaves_umber.get().asItem())
			.add(ModBlocks.leaves_vermillion.get().asItem());
        
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
		
        this.tag(ItemTags.LOGS_THAT_BURN)
			.addTag(TagUtils.modTag(ExtrabiomesXS.MODID, "japanese_maple_logs"))
        	.addTag(TagUtils.modTag(ExtrabiomesXS.MODID, "autumn_logs"));	
        	
        this.tag(ItemTags.PLANKS)
        	.add(ModBlocks.planks_japanese_maple.get().asItem())
        	.add(ModBlocks.planks_autumn_wood.get().asItem());

    } // end registerLogTags()
    
} // end class
