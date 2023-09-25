package net.extrabiomes.client;

import net.extrabiomes.ExtrabiomesXS;
import net.extrabiomes.init.ModBlocks;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = ExtrabiomesXS.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class ClientModEventSubscriber 
{
	/**
	 * register block color handlers for leaves and suchlike.
	 * @param event
	 */
	@SubscribeEvent
	public static void onRegisterColorHandlers( RegisterColorHandlersEvent.Block event)
	{
		event.register(new LeafBlockColors(), ModBlocks.leaves_acacia.get(), ModBlocks.leaves_fir.get(),
				ModBlocks.leaves_redwood.get(), ModBlocks.leaves_cypress.get(), ModBlocks.leafpile.get(),
				ModBlocks.vine_gloriosa.get(), ModBlocks.vine_spanish_moss.get(), ModBlocks.leaves_sakura_blossom.get(),
				ModBlocks.leaves_japanese_maple_shrub.get(), ModBlocks.leaves_bald_cypress.get(), 
				ModBlocks.leaves_japanese_maple.get(), ModBlocks.leaves_rainbow_eucalyptus.get());
		
		event.register(new GrassBlockColors(), ModBlocks.brown_grass_short.get(), ModBlocks.brown_grass_tall.get(),
				ModBlocks.dead_grass_short.get(), ModBlocks.dead_grass_tall.get(), ModBlocks.dead_grass_yellow.get());
	} // end onRegisterColorHandlers()
	
	/**
	 * register item color handlers for leaves and grasses and suchlike.
	 * @param event
	 */
	@SubscribeEvent
	public static void onRegisterItemColorHandlers( RegisterColorHandlersEvent.Item event)
	{
		BlockColors bc = event.getBlockColors();
		event.register(
				(itemstack, tintIndex) -> {
	         BlockState blockstate = ((BlockItem)itemstack.getItem()).getBlock().defaultBlockState();
	         return bc.getColor(blockstate, (BlockAndTintGetter)null, (BlockPos)null, tintIndex);
	      }, 
			ModBlocks.leaves_acacia.get(), ModBlocks.leaves_fir.get(),
			ModBlocks.leaves_redwood.get(), ModBlocks.leaves_cypress.get(), ModBlocks.leafpile.get(),
			ModBlocks.vine_gloriosa.get(), ModBlocks.vine_spanish_moss.get(), ModBlocks.leaves_sakura_blossom.get(),
			ModBlocks.leaves_japanese_maple_shrub.get(), ModBlocks.leaves_bald_cypress.get(), 
			ModBlocks.leaves_japanese_maple.get(), ModBlocks.leaves_rainbow_eucalyptus.get(),
			ModBlocks.brown_grass_short.get(), ModBlocks.brown_grass_tall.get(),
			ModBlocks.dead_grass_short.get(), ModBlocks.dead_grass_tall.get(), ModBlocks.dead_grass_yellow.get()
		);
		
	} // end onRegisterItemColorHandlers()
} // end class
