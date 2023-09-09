package net.extrabiomes.datagen;

import java.util.function.Consumer;

import mod.alexndr.simplecorelib.api.datagen.ISimpleConditionBuilder;
import mod.alexndr.simplecorelib.api.datagen.RecipeSetBuilder;
import mod.alexndr.simplecorelib.api.helpers.TagUtils;
import net.extrabiomes.ExtrabiomesXS;
import net.extrabiomes.config.ExtrabiomesConfig;
import net.extrabiomes.init.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

public class ExtrabiomesRecipes extends RecipeProvider implements IConditionBuilder, ISimpleConditionBuilder 
{
    @SuppressWarnings("unused")
	private RecipeSetBuilder setbuilder;

    public ExtrabiomesRecipes(PackOutput pOutput) 
	{
		super(pOutput);
        setbuilder = new RecipeSetBuilder(ExtrabiomesXS.MODID);
	}

	@Override
	public ICondition flag(String name) 
	{
		return impl_flag(ExtrabiomesXS.MODID, ExtrabiomesConfig.INSTANCE, name);
	}

	@Override
	protected void buildRecipes(Consumer<FinishedRecipe> consumer) 
	{
		registerMiscRecipes(consumer);
		registerWoodRecipes(consumer);
	}

	private void registerMiscRecipes(Consumer<FinishedRecipe> consumer)
	{
		
	}
	
	private void registerWoodRecipes(Consumer<FinishedRecipe> consumer)
	{
		TagKey<Item> autumn_logs = TagUtils.modTag(ExtrabiomesXS.MODID, "autumn_logs");
		
	    // log -> planks
		planksFromLogs(consumer, ModBlocks.planks_autumn_wood.get(), autumn_logs, 4);
		
        // stairs
        stairBuilder(ModBlocks.stairs_autumn.get(), Ingredient.of(ModBlocks.planks_autumn_wood.get()))
            .unlockedBy("has_item", has(ModBlocks.planks_autumn_wood.get()))
            .save(consumer);

        // slabs
        slab(consumer,RecipeCategory.BUILDING_BLOCKS, ModBlocks.slab_autumn.get(), ModBlocks.planks_autumn_wood.get());
        
        // doors
        doorBuilder(ModBlocks.door_autumn.get(), Ingredient.of(ModBlocks.planks_autumn_wood.get()))
            .unlockedBy("has_item", has(ModBlocks.planks_autumn_wood.get()))
            .save(consumer);

        // fences
        fenceBuilder(ModBlocks.fence_autumn.get(), Ingredient.of(ModBlocks.planks_autumn_wood.get()))
	        .unlockedBy("has_item", has(ModBlocks.planks_autumn_wood.get()))
	        .save(consumer);

        // fence gates
        fenceGateBuilder(ModBlocks.gate_autumn.get(), Ingredient.of(ModBlocks.planks_autumn_wood.get()))
	        .unlockedBy("has_item", has(ModBlocks.planks_autumn_wood.get()))
	        .save(consumer);
        
	} // end registerWoodRecipes()
	
	
} // end
