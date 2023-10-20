package net.extrabiomes.init;

import net.extrabiomes.ExtrabiomesXS;
import net.extrabiomes.content.CustomItem;
import net.extrabiomes.content.LogTurnerItem;
import net.extrabiomes.content.ScarecrowItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public final class ModItems 
{
	   public static final DeferredRegister<Item> ITEM_REGISTRY = 
	            DeferredRegister.create(Registries.ITEM, ExtrabiomesXS.MODID);
	   
	   // food values
	   public static final FoodProperties STRAWBERRY = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.6F).build();
	   public static final FoodProperties CHOCOLATE = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.5F).build();
	   public static final FoodProperties CHOCOLATE_STRAWBERRY = (new FoodProperties.Builder()).nutrition(7).saturationMod(1.0F).build();
 
	   // food items
	   public static final RegistryObject<Item> food_strawberry = ITEM_REGISTRY.register("food_strawberry",
			   () -> new Item(new Item.Properties().food(STRAWBERRY)));
	   public static final RegistryObject<Item> food_chocolate = ITEM_REGISTRY.register("food_chocolate",
			   () -> new Item(new Item.Properties().food(CHOCOLATE)));
	   public static final RegistryObject<Item> food_chocolate_strawberry = ITEM_REGISTRY.register("food_chocolate_strawberry",
			   () -> new Item(new Item.Properties().food(CHOCOLATE_STRAWBERRY)));
	   
	   // misc
	   public static final RegistryObject<CustomItem> cactus_paste = ITEM_REGISTRY.register("paste", 
			   () -> new CustomItem(new Item.Properties(), "item.extrabiomes.paste.description"));
	   public static final RegistryObject<LogTurnerItem> log_turner = ITEM_REGISTRY.register("log_turner",
			   () -> new LogTurnerItem(Tiers.WOOD, new Item.Properties(), "item.extrabiomes.logturner.description"));
	   public static final RegistryObject<ScarecrowItem> scarecrow_totem = ITEM_REGISTRY.register("scarecrow_totem",
			   () -> new ScarecrowItem(new Item.Properties(), "item.extrabiomes.scarecrow.description"));
	   
	   public static final RegistryObject<ItemNameBlockItem> seed_strawberry = ITEM_REGISTRY.register("seed_strawberry",
			   () -> new ItemNameBlockItem(ModBlocks.crop_strawberry.get(), new Item.Properties()));
} // end class
