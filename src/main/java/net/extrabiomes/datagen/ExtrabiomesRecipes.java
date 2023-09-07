package net.extrabiomes.datagen;

import java.util.function.Consumer;

import mod.alexndr.simplecorelib.api.datagen.ISimpleConditionBuilder;
import mod.alexndr.simplecorelib.api.helpers.TagUtils;
import net.extrabiomes.ExtrabiomesXS;
import net.extrabiomes.config.ExtrabiomesConfig;
import net.extrabiomes.init.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

public class ExtrabiomesRecipes extends RecipeProvider implements IConditionBuilder, ISimpleConditionBuilder 
{
	private XBrecipeSetBuilder setbuilder;

	public ExtrabiomesRecipes(PackOutput pOutput) 
	{
		super(pOutput);
		setbuilder = new XBrecipeSetBuilder(ExtrabiomesXS.MODID);
	}

	@Override
	public ICondition flag(String arg0) {
		return impl_flag(ExtrabiomesXS.MODID, ExtrabiomesConfig.INSTANCE, arg0);
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
		
		setbuilder.buildWood2PlankRecipes(consumer, Ingredient.of(autumn_logs), ModBlocks.planks_autumn_wood.get(), 4, 
				has(autumn_logs), flag("fabrica_enabled"));
	} // end registerWoodRecipes()
	
	
} // end
