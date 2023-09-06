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
        registerMisc();
    }

   	private void registerMisc()
    {
   		// crops/food
   		this.tag(Tags.Items.CROPS)
   			.addTag(TagUtils.forgeTag("crops/strawberry"));
   		this.tag(TagUtils.forgeTag("crops/strawberry"))
   			.add(ModItems.crop_strawberry.get());
    }
   	
    private void registerWoodPlankItems()
    {
    } 
    
    protected void registerLogTags()
    {
        this.tag(ItemTags.LEAVES)
			.add(ModBlocks.leaves_citrine.get().asItem())
			.add(ModBlocks.leaves_goldenrod.get().asItem())
			.add(ModBlocks.leaves_umber.get().asItem())
			.add(ModBlocks.leaves_vermillion.get().asItem());
    }
    
} // end class
