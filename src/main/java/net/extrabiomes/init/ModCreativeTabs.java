package net.extrabiomes.init;

import net.extrabiomes.ExtrabiomesXS;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public final class ModCreativeTabs 
{

	// formerly MOD_ITEM_GROUP
	public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = 
			DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ExtrabiomesXS.MODID);
	
	public static final RegistryObject<CreativeModeTab> SIMPLECORE_TAB = CREATIVE_MODE_TABS.register("extrabiomes_tab",
			() -> CreativeModeTab.builder()
				.title(Component.translatable("item_group." + ExtrabiomesXS.MODID + ".tab"))
				.icon(() -> new ItemStack(ModBlocks.leaves_vermillion.get().asItem()))
				.displayItems((parameters, output) -> {
					output.acceptAll(ModBlocks.BLOCKS_REGISTRY.getEntries().stream()
										.map(RegistryObject::get)
						                // You can do extra filtering here if you don't want some blocks to appear.
						                .filter(b -> needsCreativeTab(b))
						                .map(b -> (new ItemStack(b.asItem())))
										.toList()
										);
					output.acceptAll(ModItems.ITEM_REGISTRY.getEntries().stream()
							.map(RegistryObject::get)
							.map(b -> (new ItemStack(b)))
							.toList()
							);
				}).build());

	private static boolean needsCreativeTab(Block block)
	{
		if (block instanceof FlowerPotBlock) {
		    return false;
		}
		return true;
	}

} // end class
