package net.extrabiomes.init;

import net.extrabiomes.ExtrabiomesXS;
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
	   
	   // food items
	   public static final RegistryObject<Item> food_strawberry = ITEMS.register("food_strawberry",
			   () -> new Item(new Item.Properties().food(STRAWBERRY)));
	   
	   // seeds
	   public static final RegistryObject<ItemNameBlockItem> seed_strawberry = ITEMS.register("seed_strawberry",
			   () -> new ItemNameBlockItem(ModBlocks.crop_strawberry.get(), new Item.Properties()));
} // end class
