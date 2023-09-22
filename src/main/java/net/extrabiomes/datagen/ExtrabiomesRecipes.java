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
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
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
		oneToOneConversionRecipe(consumer, Items.LIGHT_BLUE_DYE, ModBlocks.flower_hydrangea.get(), "light_blue_dye");
		oneToOneConversionRecipe(consumer, Items.YELLOW_DYE, ModBlocks.flower_buttercup.get(), "yellow_dye");
		oneToOneConversionRecipe(consumer, Items.PURPLE_DYE, ModBlocks.flower_lavender.get(), "purple_dye");
		oneToOneConversionRecipe(consumer, Items.MAGENTA_DYE, ModBlocks.flower_allium.get(), "magenta_dye");
		oneToOneConversionRecipe(consumer, Items.LIGHT_GRAY_DYE, ModBlocks.flower_calla_white.get(), "light_gray_dye");
		oneToOneConversionRecipe(consumer, Items.BLUE_DYE, ModBlocks.flower_bachelors_button.get(), "light_blue_dye");
		oneToOneConversionRecipe(consumer, Items.LIGHT_BLUE_DYE, ModBlocks.flower_bluebell.get(), "blue_dye");
		oneToOneConversionRecipe(consumer, Items.PURPLE_DYE, ModBlocks.flower_iris_purple.get(), "purple_dye");
		oneToOneConversionRecipe(consumer, Items.CYAN_DYE, ModBlocks.flower_blue_poppy.get(), "cyan_dye");
		
		// cactus to paste
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.cactus_paste.get())
			.requires(ModBlocks.flower_tiny_cactus.get(), 4)
			.unlockedBy("has_item", has(ModBlocks.flower_tiny_cactus.get()))
			.save(consumer);
		// paste to dye
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.cactus_paste.get()), RecipeCategory.MISC, 
				Items.GREEN_DYE, 1.0F,200)
			.unlockedBy("has_item", has(ModItems.cactus_paste.get()))
			.save(consumer);
			
		
		// cracked sand to water
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Blocks.SAND)
			.requires(ModBlocks.crackedsand.get()).requires(Items.WATER_BUCKET)
			.unlockedBy("has_item", has(ModBlocks.crackedsand.get()))
			.save(consumer, "sand_from_crackedsand");
		
		// log turner
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.log_turner.get())
			.define('s', Ingredient.of(Tags.Items.RODS_WOODEN))
			.pattern(" ss")
			.pattern("  s")
			.pattern(" ss")
			.unlockedBy("has_item", has(Tags.Items.RODS_WOODEN))
			.save(consumer);
		
	} // end registerMiscRecipes()
	
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
