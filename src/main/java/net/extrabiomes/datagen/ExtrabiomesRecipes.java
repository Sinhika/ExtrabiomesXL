package net.extrabiomes.datagen;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import mod.alexndr.simplecorelib.api.datagen.ISimpleConditionBuilder;
import mod.alexndr.simplecorelib.api.datagen.RecipeSetBuilder;
import mod.alexndr.simplecorelib.api.helpers.TagUtils;
import net.extrabiomes.ExtrabiomesXS;
import net.extrabiomes.config.ExtrabiomesConfig;
import net.extrabiomes.content.CustomQuarterBlock;
import net.extrabiomes.init.ModBlocks;
import net.extrabiomes.init.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
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
		registerWoodRecipes(consumer);
		registerQuarterLogConversionRecipes(consumer);
		registerDyeRecipes(consumer);
		registerMiscRecipes(consumer);
		registerStoneCutterRecipes(consumer);
	}

	/**
	 * convert to and from quarter logs.
	 */
	private void registerQuarterLogConversionRecipes(Consumer<FinishedRecipe> consumer)
	{
		HashMap<CustomQuarterBlock, Block> quarterlog2log
			= new HashMap<CustomQuarterBlock, Block>();
		quarterlog2log.put(ModBlocks.firquarter.get(), ModBlocks.log_fir.get());
		quarterlog2log.put(ModBlocks.redwoodquarter.get(), ModBlocks.log_redwood.get());
		quarterlog2log.put(ModBlocks.oakquarter.get(), Blocks.OAK_LOG);
		quarterlog2log.put(ModBlocks.baldcypressquarter.get(), ModBlocks.log_baldcypress.get());
		quarterlog2log.put(ModBlocks.rainboweucalyptusquarter.get(), ModBlocks.log_rainboweucalyptus.get());

		HashMap<CustomQuarterBlock, Block> kneelog2log
			= new HashMap<CustomQuarterBlock, Block>();
		kneelog2log.put(ModBlocks.baldcypresskneelog.get(), ModBlocks.log_baldcypress.get());
		kneelog2log.put(ModBlocks.rainboweucalyptuskneelog.get(), ModBlocks.log_rainboweucalyptus.get());
		
		for (Map.Entry<CustomQuarterBlock, Block> entry: quarterlog2log.entrySet())
		{
			ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, entry.getValue())
				.requires(entry.getKey().asItem())
				.unlockedBy("has", has(entry.getKey()))
				.save(consumer, RecipeBuilder.getDefaultRecipeId(entry.getValue()) + "_from_quarterlog");
			
			ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, entry.getKey(), 4)
				.define('L', entry.getValue().asItem())
				.pattern("LL")
				.pattern("LL")
				.unlockedBy("has", has(entry.getValue()))
				.save(consumer);
		} // end-for
		
		for (Map.Entry<CustomQuarterBlock, Block> entry: kneelog2log.entrySet())
		{
			ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, entry.getValue())
				.requires(entry.getKey().asItem())
				.unlockedBy("has", has(entry.getKey()))
				.save(consumer, RecipeBuilder.getDefaultRecipeId(entry.getValue()) + "_from_kneelog");
		
			ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, entry.getKey(), 3)
				.define('L', entry.getValue().asItem())
				.pattern("LL")
				.pattern(" L")
				.unlockedBy("has", has(entry.getValue()))
				.save(consumer);
		} // end-for
	} // end registerQuarterLogConversionRecipes()
	
	/**
	 * Stone-cutter recipes for redrock stuff.
	 * @param consumer
	 */
	private void registerStoneCutterRecipes(Consumer<FinishedRecipe> consumer)
	{
		// slabs
		SingleItemRecipeBuilder.stonecutting(
				Ingredient.of(ModBlocks.redcobble.get()), RecipeCategory.BUILDING_BLOCKS, ModBlocks.slab_redcobble.get(), 2)
	        .unlockedBy("has_item", has(ModBlocks.redcobble.get()))
	        .save(consumer, "slab_redcobble_from_stonecutting");
		SingleItemRecipeBuilder.stonecutting(
				Ingredient.of(ModBlocks.redrock.get()), RecipeCategory.BUILDING_BLOCKS, ModBlocks.slab_redrock.get(), 2)
	        .unlockedBy("has_item", has(ModBlocks.redrock.get()))
	        .save(consumer, "slab_redrock_from_stonecutting");
		SingleItemRecipeBuilder.stonecutting(
				Ingredient.of(ModBlocks.redrock_brick.get()), RecipeCategory.BUILDING_BLOCKS, ModBlocks.slab_redrockbrick.get(), 2)
	        .unlockedBy("has_item", has(ModBlocks.redrock_brick.get()))
	        .save(consumer, "slab_redrockbrick_from_stonecutting");
		
		// stairs
		SingleItemRecipeBuilder.stonecutting(
				Ingredient.of(ModBlocks.redcobble.get().asItem()), RecipeCategory.BUILDING_BLOCKS, 
					ModBlocks.stairs_redcobble.get())
	        .unlockedBy("has_item", has(ModBlocks.redcobble.get()))
	        .save(consumer, "stairs_redcobble_from_stonecutting");
		SingleItemRecipeBuilder.stonecutting(
				Ingredient.of(ModBlocks.redrock.get().asItem()), RecipeCategory.BUILDING_BLOCKS, 
					ModBlocks.stairs_redrock.get())
	        .unlockedBy("has_item", has(ModBlocks.redrock.get()))
	        .save(consumer, "stairs_redrock_from_stonecutting");
		SingleItemRecipeBuilder.stonecutting(
				Ingredient.of(ModBlocks.redrock_brick.get().asItem()), RecipeCategory.BUILDING_BLOCKS, 
					ModBlocks.stairs_redrockbrick.get())
	        .unlockedBy("has_item", has(ModBlocks.redrock_brick.get()))
	        .save(consumer, "stairs_redrockbrick_from_stonecutting");
		
		// walls
		SingleItemRecipeBuilder.stonecutting(
				Ingredient.of(ModBlocks.redrock_brick.get().asItem()), RecipeCategory.BUILDING_BLOCKS, 
					ModBlocks.wall_redrockbrick.get())
	        .unlockedBy("has_item", has(ModBlocks.redrock_brick.get()))
	        .save(consumer, "wall_redrockbrick_from_stonecutting");
		SingleItemRecipeBuilder.stonecutting(
				Ingredient.of(ModBlocks.redcobble.get().asItem()), RecipeCategory.BUILDING_BLOCKS, 
					ModBlocks.wall_redcobble.get())
	        .unlockedBy("has_item", has(ModBlocks.redcobble.get()))
	        .save(consumer, "wall_redcobble_from_stonecutting");
		
		// bricks
		SingleItemRecipeBuilder.stonecutting(
				Ingredient.of(ModBlocks.redrock.get().asItem()), RecipeCategory.BUILDING_BLOCKS, 
					ModBlocks.redrock_brick.get())
	        .unlockedBy("has_item", has(ModBlocks.redrock.get()))
	        .save(consumer, "redrock_brick_from_stonecutting");
		
	} // end registerStoneCutterRecipes()
	
	/**
	 * Dye recipes - large enough to get its own method.
	 * @param consumer
	 */
	private void registerDyeRecipes(Consumer<FinishedRecipe> consumer)
	{
		// flower dyes
		oneToOneConversionRecipe(consumer, Items.LIGHT_BLUE_DYE, ModBlocks.flower_hydrangea.get(), "light_blue_dye");
		oneToOneConversionRecipe(consumer, Items.YELLOW_DYE, ModBlocks.flower_buttercup.get(), "yellow_dye");
		oneToOneConversionRecipe(consumer, Items.PURPLE_DYE, ModBlocks.flower_lavender.get(), "purple_dye");
		oneToOneConversionRecipe(consumer, Items.LIGHT_GRAY_DYE, ModBlocks.flower_calla_white.get(), "light_gray_dye");
		oneToOneConversionRecipe(consumer, Items.MAGENTA_DYE, ModBlocks.flower_allium.get(), "magenta_dye");
		oneToOneConversionRecipe(consumer, Items.PINK_DYE, ModBlocks.flower_amaryllis_pink.get(), "pink_dye");
		oneToOneConversionRecipe(consumer, Items.RED_DYE, ModBlocks.flower_amaryllis_red.get(), "red_dye");
		oneToOneConversionRecipe(consumer, Items.WHITE_DYE, ModBlocks.flower_amaryllis_white.get(), "white_dye");
		oneToOneConversionRecipe(consumer, Items.BLUE_DYE, ModBlocks.flower_bachelors_button.get(), "blue_dye");
		oneToOneConversionRecipe(consumer, Items.LIME_DYE, ModBlocks.flower_bells_of_ireland.get(), "lime_dye");
		oneToOneConversionRecipe(consumer, Items.LIGHT_BLUE_DYE, ModBlocks.flower_bluebell.get(), "light_blue_dye");
		oneToOneConversionRecipe(consumer, Items.BLACK_DYE, ModBlocks.flower_calla_black.get(), "black_dye");
		oneToOneConversionRecipe(consumer, Items.WHITE_DYE, ModBlocks.flower_daisy.get(), "white_dye");
		oneToOneConversionRecipe(consumer, Items.LIGHT_GRAY_DYE, ModBlocks.flower_gardenia.get(), "light_gray_dye");
		oneToOneConversionRecipe(consumer, Items.ORANGE_DYE, ModBlocks.flower_gerbera_orange.get(), "orange_dye");
		oneToOneConversionRecipe(consumer, Items.PINK_DYE, ModBlocks.flower_gerbera_pink.get(), "pink_dye");
		oneToOneConversionRecipe(consumer, Items.RED_DYE, ModBlocks.flower_gerbera_red.get(), "red_dye");
		oneToOneConversionRecipe(consumer, Items.YELLOW_DYE, ModBlocks.flower_gerbera_yellow.get(), "yellow_dye");
		oneToOneConversionRecipe(consumer, Items.PINK_DYE, ModBlocks.flower_oriental_pink_lily.get(), "pink_dye");
		oneToOneConversionRecipe(consumer, Items.BLUE_DYE, ModBlocks.flower_iris_blue.get(), "blue_dye");
		oneToOneConversionRecipe(consumer, Items.PURPLE_DYE, ModBlocks.flower_iris_purple.get(), "purple_dye");
		oneToOneConversionRecipe(consumer, Items.MAGENTA_DYE, ModBlocks.flower_lily.get(), "magenta_dye");
		oneToOneConversionRecipe(consumer, Items.YELLOW_DYE, ModBlocks.flower_marsh_marigold.get(), "yellow_dye");
		oneToOneConversionRecipe(consumer, Items.YELLOW_DYE, ModBlocks.flower_pansy.get(), "yellow_dye");
		oneToOneConversionRecipe(consumer, Items.RED_DYE, ModBlocks.flower_poppy.get(), "red_dye");
		oneToOneConversionRecipe(consumer, Items.RED_DYE, ModBlocks.flower_redrover.get(), "red_dye");
		oneToOneConversionRecipe(consumer, Items.ORANGE_DYE, ModBlocks.flower_snapdragon.get(), "orange_dye");
		oneToOneConversionRecipe(consumer, Items.ORANGE_DYE, ModBlocks.flower_tulip.get(), "orange_dye");
		oneToOneConversionRecipe(consumer, Items.PURPLE_DYE, ModBlocks.flower_violet.get(), "purple_dye");
		oneToOneConversionRecipe(consumer, Items.YELLOW_DYE, ModBlocks.flower_yarrow.get(), "yellow_dye");
		oneToOneConversionRecipe(consumer, Items.BLUE_DYE, ModBlocks.flower_belladonna.get(), "blue_dye");
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
	} // end registerDyeRecipes
	
	/**
	 * All the other stuff.
	 * @param consumer
	 */
	private void registerMiscRecipes(Consumer<FinishedRecipe> consumer)
	{
		// strawberries to seeds.
		oneToOneConversionRecipe(consumer, ModItems.seed_strawberry.get(), ModItems.food_strawberry.get(), 
				"strawberry_seeds");
		
		// foods
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.food_chocolate.get())
			.requires(Items.COCOA_BEANS).requires(Items.SUGAR).requires(Items.MILK_BUCKET)
			.unlockedBy("has_item", has(Items.COCOA_BEANS))
			.save(consumer);
		
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.food_chocolate_strawberry.get())
			.requires(TagUtils.forgeTag("food/chocolate")).requires(TagUtils.forgeTag("crops/strawberry"))
			.unlockedBy("has_item", has(TagUtils.forgeTag("crops/strawberry")))
			.save(consumer);
		
		
		// cracked sand to sand
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
		
		// scarecrow
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModItems.scarecrow_totem.get())
			.define('p', Blocks.CARVED_PUMPKIN)
			.define('m', Blocks.MELON)
			.define('s', Ingredient.of(Tags.Items.RODS_WOODEN))
			.pattern(" p ")
			.pattern("sms")
			.pattern(" s ")
			.unlockedBy("has_item", has(Blocks.MELON))
			.save(consumer);
		
	} // end registerMiscRecipes()
	
	private void registerWoodRecipes(Consumer<FinishedRecipe> consumer)
	{
		TagKey<Item> autumn_logs = TagUtils.modTag(ExtrabiomesXS.MODID, "autumn_logs");
		TagKey<Item> japanese_maple_logs = TagUtils.modTag(ExtrabiomesXS.MODID, "japanese_maple_logs");
		TagKey<Item> fir_logs = TagUtils.modTag(ExtrabiomesXS.MODID, "fir_logs");
		TagKey<Item> redwood_logs = TagUtils.modTag(ExtrabiomesXS.MODID, "redwood_logs");
		TagKey<Item> acacia_logs  = TagUtils.modTag(ExtrabiomesXS.MODID, "black_wattle_logs");
		TagKey<Item> baldcypress_logs  = TagUtils.modTag(ExtrabiomesXS.MODID, "baldcypress_logs");
		TagKey<Item> cypress_logs  = TagUtils.modTag(ExtrabiomesXS.MODID, "cypress_logs");
		TagKey<Item> rainboweucalyptus_logs  = TagUtils.modTag(ExtrabiomesXS.MODID, "rainboweucalyptus_logs");
		TagKey<Item> sakura_logs  = TagUtils.modTag(ExtrabiomesXS.MODID, "sakura_logs");
		
	    // log -> planks
		planksFromLogs(consumer, ModBlocks.planks_autumn_wood.get(), autumn_logs, 4);
		planksFromLogs(consumer, ModBlocks.planks_japanese_maple.get(), japanese_maple_logs, 4);
		planksFromLogs(consumer, ModBlocks.planks_fir.get(), fir_logs, 4);
		planksFromLogs(consumer, ModBlocks.planks_redwood.get(), redwood_logs, 4);
		planksFromLogs(consumer, ModBlocks.planks_acacia.get(), acacia_logs, 4);
		planksFromLogs(consumer, ModBlocks.planks_baldcypress.get(), baldcypress_logs, 4);
		planksFromLogs(consumer, ModBlocks.planks_cypress.get(), cypress_logs, 4);
		planksFromLogs(consumer, ModBlocks.planks_rainboweucalyptus.get(), rainboweucalyptus_logs, 4);
		planksFromLogs(consumer, ModBlocks.planks_sakura.get(), sakura_logs, 4);

		// log -> bark (wood)
		woodFromLogs(consumer, ModBlocks.bark_autumn.get(), ModBlocks.log_autumn.get());
		woodFromLogs(consumer, ModBlocks.bark_japanese_maple.get(), ModBlocks.log_japanese_maple.get());
		woodFromLogs(consumer, ModBlocks.bark_fir.get(), ModBlocks.log_fir.get());
		woodFromLogs(consumer, ModBlocks.bark_redwood.get(), ModBlocks.log_redwood.get());
		woodFromLogs(consumer, ModBlocks.bark_acacia.get(), ModBlocks.log_acacia.get());
		woodFromLogs(consumer, ModBlocks.bark_baldcypress.get(), ModBlocks.log_baldcypress.get());
		woodFromLogs(consumer, ModBlocks.bark_cypress.get(), ModBlocks.log_cypress.get());
		woodFromLogs(consumer, ModBlocks.bark_rainboweucalyptus.get(), ModBlocks.log_rainboweucalyptus.get());
		woodFromLogs(consumer, ModBlocks.bark_sakura.get(), ModBlocks.log_sakura.get());

		// stripped log -> stripped wood
		woodFromLogs(consumer, ModBlocks.strippedwood_autumn.get(), ModBlocks.strippedlog_autumn.get());
		woodFromLogs(consumer, ModBlocks.strippedwood_japanesemaple.get(), ModBlocks.strippedlog_japanesemaple.get());
		woodFromLogs(consumer, ModBlocks.strippedwood_fir.get(), ModBlocks.strippedlog_fir.get());
		woodFromLogs(consumer, ModBlocks.strippedwood_redwood.get(), ModBlocks.strippedlog_redwood.get());
		woodFromLogs(consumer, ModBlocks.strippedwood_acacia.get(), ModBlocks.strippedlog_acacia.get());
		woodFromLogs(consumer, ModBlocks.strippedwood_baldcypress.get(), ModBlocks.strippedlog_baldcypress.get());
		woodFromLogs(consumer, ModBlocks.strippedwood_cypress.get(), ModBlocks.strippedlog_cypress.get());
		woodFromLogs(consumer, ModBlocks.strippedwood_rainboweucalyptus.get(), ModBlocks.strippedlog_rainboweucalyptus.get());
		woodFromLogs(consumer, ModBlocks.strippedwood_sakura.get(), ModBlocks.strippedlog_sakura.get());

        // stairs
        stairBuilder(ModBlocks.stairs_autumn.get(), Ingredient.of(ModBlocks.planks_autumn_wood.get()))
            .unlockedBy("has_item", has(ModBlocks.planks_autumn_wood.get()))
            .save(consumer);
        stairBuilder(ModBlocks.stairs_japanesemaple.get(), Ingredient.of(ModBlocks.planks_japanese_maple.get()))
	        .unlockedBy("has_item", has(ModBlocks.planks_japanese_maple.get()))
	        .save(consumer);
        stairBuilder(ModBlocks.stairs_fir.get(), Ingredient.of(ModBlocks.planks_fir.get()))
	        .unlockedBy("has_item", has(ModBlocks.planks_fir.get()))
	        .save(consumer);
        stairBuilder(ModBlocks.stairs_redwood.get(), Ingredient.of(ModBlocks.planks_redwood.get()))
	        .unlockedBy("has_item", has(ModBlocks.planks_redwood.get()))
	        .save(consumer);
        stairBuilder(ModBlocks.stairs_acacia.get(), Ingredient.of(ModBlocks.planks_acacia.get()))
	        .unlockedBy("has_item", has(ModBlocks.planks_acacia.get()))
	        .save(consumer);
        stairBuilder(ModBlocks.stairs_baldcypress.get(), Ingredient.of(ModBlocks.planks_baldcypress.get()))
	        .unlockedBy("has_item", has(ModBlocks.planks_baldcypress.get()))
	        .save(consumer);
        stairBuilder(ModBlocks.stairs_cypress.get(), Ingredient.of(ModBlocks.planks_cypress.get()))
	        .unlockedBy("has_item", has(ModBlocks.planks_cypress.get()))
	        .save(consumer);
        stairBuilder(ModBlocks.stairs_rainboweucalyptus.get(), Ingredient.of(ModBlocks.planks_rainboweucalyptus.get()))
	        .unlockedBy("has_item", has(ModBlocks.planks_rainboweucalyptus.get()))
	        .save(consumer);
        stairBuilder(ModBlocks.stairs_sakura.get(), Ingredient.of(ModBlocks.planks_sakura.get()))
	        .unlockedBy("has_item", has(ModBlocks.planks_sakura.get()))
	        .save(consumer);

        stairBuilder(ModBlocks.stairs_redcobble.get(), Ingredient.of(ModBlocks.redcobble.get()))
	        .unlockedBy("has_item", has(ModBlocks.redcobble.get()))
	        .save(consumer);
        stairBuilder(ModBlocks.stairs_redrockbrick.get(), Ingredient.of(ModBlocks.redrock_brick.get()))
	        .unlockedBy("has_item", has(ModBlocks.redrock_brick.get()))
	        .save(consumer);
        stairBuilder(ModBlocks.stairs_redrock.get(), Ingredient.of(ModBlocks.redrock.get()))
	        .unlockedBy("has_item", has(ModBlocks.redrock.get()))
	        .save(consumer);

        // slabs
        slab(consumer,RecipeCategory.BUILDING_BLOCKS, ModBlocks.slab_autumn.get(), ModBlocks.planks_autumn_wood.get());
        slab(consumer,RecipeCategory.BUILDING_BLOCKS, ModBlocks.slab_japanese_maple.get(), ModBlocks.planks_japanese_maple.get());
        slab(consumer,RecipeCategory.BUILDING_BLOCKS, ModBlocks.slab_fir.get(), ModBlocks.planks_fir.get());
        slab(consumer,RecipeCategory.BUILDING_BLOCKS, ModBlocks.slab_redwood.get(), ModBlocks.planks_redwood.get());
        slab(consumer,RecipeCategory.BUILDING_BLOCKS, ModBlocks.slab_acacia.get(), ModBlocks.planks_acacia.get());
        slab(consumer,RecipeCategory.BUILDING_BLOCKS, ModBlocks.slab_baldcypress.get(), ModBlocks.planks_baldcypress.get());
        slab(consumer,RecipeCategory.BUILDING_BLOCKS, ModBlocks.slab_cypress.get(), ModBlocks.planks_cypress.get());
        slab(consumer,RecipeCategory.BUILDING_BLOCKS, ModBlocks.slab_rainboweucalyptus.get(), ModBlocks.planks_rainboweucalyptus.get());
        slab(consumer,RecipeCategory.BUILDING_BLOCKS, ModBlocks.slab_sakura.get(), ModBlocks.planks_sakura.get());

        slab(consumer,RecipeCategory.BUILDING_BLOCKS, ModBlocks.slab_redrock.get(), ModBlocks.redrock.get());
        slab(consumer,RecipeCategory.BUILDING_BLOCKS, ModBlocks.slab_redrockbrick.get(), ModBlocks.redrock_brick.get());
        slab(consumer,RecipeCategory.BUILDING_BLOCKS, ModBlocks.slab_redcobble.get(), ModBlocks.redcobble.get());
        
        // doors
        doorBuilder(ModBlocks.door_autumn.get(), Ingredient.of(ModBlocks.planks_autumn_wood.get()))
            .unlockedBy("has_item", has(ModBlocks.planks_autumn_wood.get()))
            .save(consumer);
        doorBuilder(ModBlocks.door_japanesemaple.get(), Ingredient.of(ModBlocks.planks_japanese_maple.get()))
	        .unlockedBy("has_item", has(ModBlocks.planks_japanese_maple.get()))
	        .save(consumer);
        doorBuilder(ModBlocks.door_fir.get(), Ingredient.of(ModBlocks.planks_fir.get()))
	        .unlockedBy("has_item", has(ModBlocks.planks_fir.get()))
	        .save(consumer);
        doorBuilder(ModBlocks.door_redwood.get(), Ingredient.of(ModBlocks.planks_redwood.get()))
	        .unlockedBy("has_item", has(ModBlocks.planks_redwood.get()))
	        .save(consumer);
        doorBuilder(ModBlocks.door_acacia.get(), Ingredient.of(ModBlocks.planks_acacia.get()))
	        .unlockedBy("has_item", has(ModBlocks.planks_acacia.get()))
	        .save(consumer);
        doorBuilder(ModBlocks.door_baldcypress.get(), Ingredient.of(ModBlocks.planks_baldcypress.get()))
	        .unlockedBy("has_item", has(ModBlocks.planks_baldcypress.get()))
	        .save(consumer);
        doorBuilder(ModBlocks.door_cypress.get(), Ingredient.of(ModBlocks.planks_cypress.get()))
	        .unlockedBy("has_item", has(ModBlocks.planks_cypress.get()))
	        .save(consumer);
        doorBuilder(ModBlocks.door_rainboweucalyptus.get(), Ingredient.of(ModBlocks.planks_rainboweucalyptus.get()))
	        .unlockedBy("has_item", has(ModBlocks.planks_rainboweucalyptus.get()))
	        .save(consumer);
        doorBuilder(ModBlocks.door_sakura.get(), Ingredient.of(ModBlocks.planks_sakura.get()))
	        .unlockedBy("has_item", has(ModBlocks.planks_sakura.get()))
	        .save(consumer);

        // fences
        fenceBuilder(ModBlocks.fence_autumn.get(), Ingredient.of(ModBlocks.planks_autumn_wood.get()))
	        .unlockedBy("has_item", has(ModBlocks.planks_autumn_wood.get()))
	        .save(consumer);
        fenceBuilder(ModBlocks.fence_japanesemaple.get(), Ingredient.of(ModBlocks.planks_japanese_maple.get()))
	        .unlockedBy("has_item", has(ModBlocks.planks_japanese_maple.get()))
	        .save(consumer);
        fenceBuilder(ModBlocks.fence_fir.get(), Ingredient.of(ModBlocks.planks_fir.get()))
	        .unlockedBy("has_item", has(ModBlocks.planks_fir.get()))
	        .save(consumer);
        fenceBuilder(ModBlocks.fence_redwood.get(), Ingredient.of(ModBlocks.planks_redwood.get()))
	        .unlockedBy("has_item", has(ModBlocks.planks_redwood.get()))
	        .save(consumer);
        fenceBuilder(ModBlocks.fence_acacia.get(), Ingredient.of(ModBlocks.planks_acacia.get()))
	        .unlockedBy("has_item", has(ModBlocks.planks_acacia.get()))
	        .save(consumer);
        fenceBuilder(ModBlocks.fence_baldcypress.get(), Ingredient.of(ModBlocks.planks_baldcypress.get()))
	        .unlockedBy("has_item", has(ModBlocks.planks_baldcypress.get()))
	        .save(consumer);
        fenceBuilder(ModBlocks.fence_cypress.get(), Ingredient.of(ModBlocks.planks_cypress.get()))
	        .unlockedBy("has_item", has(ModBlocks.planks_cypress.get()))
	        .save(consumer);
        fenceBuilder(ModBlocks.fence_rainboweucalyptus.get(), Ingredient.of(ModBlocks.planks_rainboweucalyptus.get()))
	        .unlockedBy("has_item", has(ModBlocks.planks_rainboweucalyptus.get()))
	        .save(consumer);
        fenceBuilder(ModBlocks.fence_sakura.get(), Ingredient.of(ModBlocks.planks_sakura.get()))
	        .unlockedBy("has_item", has(ModBlocks.planks_sakura.get()))
	        .save(consumer);

        // fence gates
        fenceGateBuilder(ModBlocks.gate_autumn.get(), Ingredient.of(ModBlocks.planks_autumn_wood.get()))
	        .unlockedBy("has_item", has(ModBlocks.planks_autumn_wood.get()))
	        .save(consumer);
        fenceGateBuilder(ModBlocks.gate_japanesemaple.get(), Ingredient.of(ModBlocks.planks_japanese_maple.get()))
	        .unlockedBy("has_item", has(ModBlocks.planks_japanese_maple.get()))
	        .save(consumer);
        fenceGateBuilder(ModBlocks.gate_fir.get(), Ingredient.of(ModBlocks.planks_fir.get()))
	        .unlockedBy("has_item", has(ModBlocks.planks_fir.get()))
	        .save(consumer);
        fenceGateBuilder(ModBlocks.gate_redwood.get(), Ingredient.of(ModBlocks.planks_redwood.get()))
	        .unlockedBy("has_item", has(ModBlocks.planks_redwood.get()))
	        .save(consumer);
        fenceGateBuilder(ModBlocks.gate_acacia.get(), Ingredient.of(ModBlocks.planks_acacia.get()))
	        .unlockedBy("has_item", has(ModBlocks.planks_acacia.get()))
	        .save(consumer);
        fenceGateBuilder(ModBlocks.gate_baldcypress.get(), Ingredient.of(ModBlocks.planks_baldcypress.get()))
	        .unlockedBy("has_item", has(ModBlocks.planks_baldcypress.get()))
	        .save(consumer);
        fenceGateBuilder(ModBlocks.gate_cypress.get(), Ingredient.of(ModBlocks.planks_cypress.get()))
	        .unlockedBy("has_item", has(ModBlocks.planks_cypress.get()))
	        .save(consumer);
        fenceGateBuilder(ModBlocks.gate_rainboweucalyptus.get(), Ingredient.of(ModBlocks.planks_rainboweucalyptus.get()))
	        .unlockedBy("has_item", has(ModBlocks.planks_rainboweucalyptus.get()))
	        .save(consumer);
        fenceGateBuilder(ModBlocks.gate_sakura.get(), Ingredient.of(ModBlocks.planks_sakura.get()))
	        .unlockedBy("has_item", has(ModBlocks.planks_sakura.get()))
	        .save(consumer);
        
        // walls
        wallBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.wall_redcobble.get(), Ingredient.of(ModBlocks.redcobble.get()));
        wallBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.wall_redrockbrick.get(), 
        			Ingredient.of(ModBlocks.redrock_brick.get()));
        
        // buttons
        buttonBuilder(ModBlocks.button_autumn.get(), Ingredient.of(ModBlocks.planks_autumn_wood.get()))
	        .unlockedBy("has_item", has(ModBlocks.planks_autumn_wood.get()))
	        .save(consumer);
        buttonBuilder(ModBlocks.button_japanesemaple.get(), Ingredient.of(ModBlocks.planks_japanese_maple.get()))
	        .unlockedBy("has_item", has(ModBlocks.planks_japanese_maple.get()))
	        .save(consumer);
        buttonBuilder(ModBlocks.button_fir.get(), Ingredient.of(ModBlocks.planks_fir.get()))
	        .unlockedBy("has_item", has(ModBlocks.planks_fir.get()))
	        .save(consumer);
        buttonBuilder(ModBlocks.button_redwood.get(), Ingredient.of(ModBlocks.planks_redwood.get()))
	        .unlockedBy("has_item", has(ModBlocks.planks_redwood.get()))
	        .save(consumer);
        buttonBuilder(ModBlocks.button_acacia.get(), Ingredient.of(ModBlocks.planks_acacia.get()))
	        .unlockedBy("has_item", has(ModBlocks.planks_acacia.get()))
	        .save(consumer);
        buttonBuilder(ModBlocks.button_baldcypress.get(), Ingredient.of(ModBlocks.planks_baldcypress.get()))
	        .unlockedBy("has_item", has(ModBlocks.planks_baldcypress.get()))
	        .save(consumer);
        buttonBuilder(ModBlocks.button_cypress.get(), Ingredient.of(ModBlocks.planks_cypress.get()))
	        .unlockedBy("has_item", has(ModBlocks.planks_cypress.get()))
	        .save(consumer);
        buttonBuilder(ModBlocks.button_rainboweucalyptus.get(), Ingredient.of(ModBlocks.planks_rainboweucalyptus.get()))
	        .unlockedBy("has_item", has(ModBlocks.planks_rainboweucalyptus.get()))
	        .save(consumer);
        buttonBuilder(ModBlocks.button_sakura.get(), Ingredient.of(ModBlocks.planks_sakura.get()))
	        .unlockedBy("has_item", has(ModBlocks.planks_sakura.get()))
	        .save(consumer);
       	
        // pressure plates
        pressurePlate(consumer, ModBlocks.pressureplate_autumn.get(),ModBlocks.planks_autumn_wood.get());
        pressurePlate(consumer, ModBlocks.pressureplate_japanesemaple.get(),ModBlocks.planks_japanese_maple.get());
        pressurePlate(consumer, ModBlocks.pressureplate_fir.get(),ModBlocks.planks_fir.get());
        pressurePlate(consumer, ModBlocks.pressureplate_redwood.get(),ModBlocks.planks_redwood.get());
        pressurePlate(consumer, ModBlocks.pressureplate_acacia.get(),ModBlocks.planks_acacia.get());
        pressurePlate(consumer, ModBlocks.pressureplate_baldcypress.get(),ModBlocks.planks_baldcypress.get());
        pressurePlate(consumer, ModBlocks.pressureplate_cypress.get(),ModBlocks.planks_cypress.get());
        pressurePlate(consumer, ModBlocks.pressureplate_rainboweucalyptus.get(),ModBlocks.planks_rainboweucalyptus.get());
        pressurePlate(consumer, ModBlocks.pressureplate_sakura.get(),ModBlocks.planks_sakura.get());
        
	} // end registerWoodRecipes()
	
	
} // end
