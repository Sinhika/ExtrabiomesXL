package net.extrabiomes.datagen;

import java.util.concurrent.CompletableFuture;

import mod.alexndr.simplecorelib.api.datagen.MiningBlockTags;
import net.extrabiomes.ExtrabiomesXS;
import net.extrabiomes.init.ModBlocks;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ExtrabiomesBlockTags extends MiningBlockTags 
{

	public ExtrabiomesBlockTags(PackOutput output, CompletableFuture<Provider> lookupProvider, ExistingFileHelper existingFileHelper) 
	{
		super(output, lookupProvider, ExtrabiomesXS.MODID, existingFileHelper);
	}

	
	@Override
	protected void addTags(Provider pProvider) {
		super.addTags(pProvider);
		registerPlankBlockTags();
		registerLogTags();
		registerLeafSaplingTags();
		registerMiscTags();
	} // end addTags()


	@Override
	protected void registerMiningTags() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void registerOreTags() {
		// TODO Auto-generated method stub
	}

	protected void registerPlankBlockTags()
	{
	}
   
	protected void registerLogTags()
	{
	}
   
	protected void registerLeafSaplingTags()
	{
		this.tag(BlockTags.LEAVES)
			.add(ModBlocks.leaves_citrine.get())
			.add(ModBlocks.leaves_goldenrod.get())
			.add(ModBlocks.leaves_umber.get())
			.add(ModBlocks.leaves_vermillion.get());
	      
	}
	
	protected void registerMiscTags()
	{
	}
} // end class
