package net.extrabiomes.init;

import net.extrabiomes.ExtrabiomesXS;
import net.extrabiomes.content.CustomItem;
import net.extrabiomes.content.LogTurnerItem;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ModItems 
{
	   public static final DeferredRegister<Item> ITEMS = 
	            DeferredRegister.create(ForgeRegistries.ITEMS, ExtrabiomesXS.MODID);
	   
	   // food values
	   public static final FoodProperties STRAWBERRY = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.6F).build();
	   public static final FoodProperties CHOCOLATE = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.5F).build();
	   public static final FoodProperties CHOCOLATE_STRAWBERRY = (new FoodProperties.Builder()).nutrition(7).saturationMod(1.0F).build();
 
	   // food items
	   public static final RegistryObject<Item> food_strawberry = ITEMS.register("food_strawberry",
			   () -> new Item(new Item.Properties().food(STRAWBERRY)));
	   public static final RegistryObject<Item> food_chocolate = ITEMS.register("food_chocolate",
			   () -> new Item(new Item.Properties().food(CHOCOLATE)));
	   public static final RegistryObject<Item> food_chocolate_strawberry = ITEMS.register("food_chocolate_strawberry",
			   () -> new Item(new Item.Properties().food(CHOCOLATE_STRAWBERRY)));
	   
	   // misc
	   public static final RegistryObject<CustomItem> cactus_paste = ITEMS.register("paste", 
			   () -> new CustomItem(new Item.Properties(), "item.extrabiomes.paste.description"));
	   public static final RegistryObject<LogTurnerItem> log_turner = ITEMS.register("log_turner",
			   () -> new LogTurnerItem(new Item.Properties(), "item.extrabiomes.logturner.description"));
	   
	   // seeds
	   public static final RegistryObject<ItemNameBlockItem> seed_strawberry = ITEMS.register("seed_strawberry",
			   () -> new ItemNameBlockItem(ModBlocks.crop_strawberry.get(), new Item.Properties()));
} // end class
