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
		registerMineableTags(List.of(ModBlocks.crackedsand.get()), 
				List.of(), List.of(), List.of(), List.of());
	}

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
			.add(ModBlocks.flower_hydrangea.get());
			
		// crops
		this.tag(BlockTags.MAINTAINS_FARMLAND)
			.add(ModBlocks.crop_strawberry.get());
		this.tag(BlockTags.CROPS)
			.add(ModBlocks.crop_strawberry.get());
		
		this.tag(BlockTags.REPLACEABLE_BY_TREES)
			.add(ModBlocks.flower_autumn_shrub.get());
		
		// this.tag(BlockTags.CLIMBABLE);
	} // end registerOtherPlantTags()
	
	
	private void registerMiscTags()
	{
		
		this.tag(BlockTags.DEAD_BUSH_MAY_PLACE_ON)
			.add(ModBlocks.crackedsand.get());
		
		this.tag(BlockTags.OVERWORLD_CARVER_REPLACEABLES)
			.add(ModBlocks.crackedsand.get());
		
		this.tag(BlockTags.CONVERTABLE_TO_MUD)
			.add(ModBlocks.crackedsand.get());
		
		this.tag(BlockTags.SCULK_REPLACEABLE)
			.add(ModBlocks.crackedsand.get());
		
		this.tag(BlockTags.SMELTS_TO_GLASS)
			.add(ModBlocks.crackedsand.get());
	} // end registerMiscTags()
	
} // end class
