package net.extrabiomes.client;

import net.extrabiomes.ExtrabiomesXS;
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
		
	} // end onRegisterColorHandlers()
} // end class
