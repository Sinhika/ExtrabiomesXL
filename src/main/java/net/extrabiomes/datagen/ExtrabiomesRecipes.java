package net.extrabiomes.datagen;

import java.util.function.Consumer;

import mod.alexndr.simplecorelib.api.datagen.ISimpleConditionBuilder;
import mod.alexndr.simplecorelib.api.datagen.RecipeSetBuilder;
import mod.alexndr.simplecorelib.api.helpers.TagUtils;
import net.extrabiomes.ExtrabiomesXS;
import net.extrabiomes.config.ExtrabiomesConfig;
import net.extrabiomes.init.ModBlocks;
import net.extrabiomes.init.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
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
		// strawberries to seeds.
		oneToOneConversionRecipe(consumer, ModItems.seed_strawberry.get(), ModItems.food_strawberry.get(), 
				"strawberry_seeds");
		
		// foods
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.food_chocolate.get())
			.requires(Items.BROWN_DYE).requires(Items.SUGAR).requires(Items.MILK_BUCKET)
			.unlockedBy("has_item", has(Items.COCOA_BEANS))
			.save(consumer);
		
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.food_chocolate_strawberry.get())
			.requires(TagUtils.forgeTag("food/chocolate")).requires(TagUtils.forgeTag("crops/strawberry"))
			.unlockedBy("has_item", has(TagUtils.forgeTag("crops/strawberry")))
			.save(consumer);
		
		// flower dyes
		oneToOneConversionRecipe(consumer, Items.RED_DYE, ModBlocks.flower_redrover.get(), "red_dye");
	}
	
	private void registerWoodRecipes(Consumer<FinishedRecipe> consumer)
	{
		TagKey<Item> autumn_logs = TagUtils.modTag(ExtrabiomesXS.MODID, "autumn_logs");
		TagKey<Item> japanese_maple_logs = TagUtils.modTag(ExtrabiomesXS.MODID, "japanese_maple_logs");
		
	    // log -> planks
		planksFromLogs(consumer, ModBlocks.planks_autumn_wood.get(), autumn_logs, 4);
		planksFromLogs(consumer, ModBlocks.planks_japanese_maple.get(), japanese_maple_logs, 4);
		
        // stairs
        stairBuilder(ModBlocks.stairs_autumn.get(), Ingredient.of(ModBlocks.planks_autumn_wood.get()))
            .unlockedBy("has_item", has(ModBlocks.planks_autumn_wood.get()))
            .save(consumer);
        stairBuilder(ModBlocks.stairs_japanesemaple.get(), Ingredient.of(ModBlocks.planks_japanese_maple.get()))
	        .unlockedBy("has_item", has(ModBlocks.planks_japanese_maple.get()))
	        .save(consumer);

        // slabs
        slab(consumer,RecipeCategory.BUILDING_BLOCKS, ModBlocks.slab_autumn.get(), ModBlocks.planks_autumn_wood.get());
        slab(consumer,RecipeCategory.BUILDING_BLOCKS, ModBlocks.slab_japanese_maple.get(), ModBlocks.planks_japanese_maple.get());
        
        // doors
        doorBuilder(ModBlocks.door_autumn.get(), Ingredient.of(ModBlocks.planks_autumn_wood.get()))
            .unlockedBy("has_item", has(ModBlocks.planks_autumn_wood.get()))
            .save(consumer);
        doorBuilder(ModBlocks.door_japanesemaple.get(), Ingredient.of(ModBlocks.planks_japanese_maple.get()))
	        .unlockedBy("has_item", has(ModBlocks.planks_japanese_maple.get()))
	        .save(consumer);

        // fences
        fenceBuilder(ModBlocks.fence_autumn.get(), Ingredient.of(ModBlocks.planks_autumn_wood.get()))
	        .unlockedBy("has_item", has(ModBlocks.planks_autumn_wood.get()))
	        .save(consumer);
        fenceBuilder(ModBlocks.fence_japanesemaple.get(), Ingredient.of(ModBlocks.planks_japanese_maple.get()))
	        .unlockedBy("has_item", has(ModBlocks.planks_japanese_maple.get()))
	        .save(consumer);

        // fence gates
        fenceGateBuilder(ModBlocks.gate_autumn.get(), Ingredient.of(ModBlocks.planks_autumn_wood.get()))
	        .unlockedBy("has_item", has(ModBlocks.planks_autumn_wood.get()))
	        .save(consumer);
        fenceGateBuilder(ModBlocks.gate_japanesemaple.get(), Ingredient.of(ModBlocks.planks_japanese_maple.get()))
	        .unlockedBy("has_item", has(ModBlocks.planks_japanese_maple.get()))
	        .save(consumer);
        
        // buttons
        buttonBuilder(ModBlocks.button_autumn.get(), Ingredient.of(ModBlocks.planks_autumn_wood.get()))
	        .unlockedBy("has_item", has(ModBlocks.planks_autumn_wood.get()))
	        .save(consumer);
        buttonBuilder(ModBlocks.button_japanesemaple.get(), Ingredient.of(ModBlocks.planks_japanese_maple.get()))
	        .unlockedBy("has_item", has(ModBlocks.planks_japanese_maple.get()))
	        .save(consumer);
       	
        // pressure plates
        pressurePlate(consumer, ModBlocks.pressureplate_autumn.get(),ModBlocks.planks_autumn_wood.get());
        pressurePlate(consumer, ModBlocks.pressureplate_japanesemaple.get(),ModBlocks.planks_japanese_maple.get());
        
	} // end registerWoodRecipes()
	
	
} // end
