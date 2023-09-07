package net.extrabiomes.datagen;

import java.util.function.Consumer;

import mod.alexndr.simplecorelib.api.datagen.RecipeSetBuilder;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.ICondition;

public class XBrecipeSetBuilder extends RecipeSetBuilder 
{

	public XBrecipeSetBuilder(String modid) 
	{
		super(modid);
	}

	/**
	 * build standard wood to plank recipes.
	 * 
	 * consumer - passed in from RecipeProvider to builder() call.
	 * woodIn - log/stripped wood ingredient (likely tag)
	 * planksOut - plank item returned.
	 * pCount - number of planks returned (usually 4).
	 * criterion - required to get the recipe advancement; usually hasItem().
	 * condition - null for no conditions, ICondition object for a conditional recipe.
	 */
	public void buildWood2PlankRecipes(Consumer<FinishedRecipe> consumer, Ingredient woodIn, ItemLike planksOut, int pCount,
			CriterionTriggerInstance criterion, ICondition condition)
	{
		
		@SuppressWarnings("deprecation")
		ResourceLocation planks = BuiltInRegistries.ITEM.getKey(planksOut.asItem());
		
        if (condition==null) 
        {
        	ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, planksOut, pCount)
        		.requires(woodIn)
        		.unlockedBy("has_item", criterion)
        		.save(consumer);
        } // end if no condition
        else // has a condition
        {
        	 ConditionalRecipe.builder().addCondition(condition)
             .addRecipe(
            		 ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, planksOut, pCount)
             		.requires(woodIn)
             		.unlockedBy("has_item", criterion)
             		::save)
             .setAdvancement(planks, build_advancement_with_condition(planks, condition, criterion))
             .build(consumer, planks);
        } // end else has a condition
	} // end ()
	
} // end class
