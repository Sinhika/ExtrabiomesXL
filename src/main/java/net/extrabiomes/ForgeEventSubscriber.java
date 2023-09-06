package net.extrabiomes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

/**
 * Subscribe to events from the FORGE EventBus that should be handled on both PHYSICAL sides in this class
 *
 */
@EventBusSubscriber(modid = ExtrabiomesXS.MODID, bus = EventBusSubscriber.Bus.FORGE)
public final class ForgeEventSubscriber 
{
    private static final Logger LOGGER = LogManager.getLogger(ExtrabiomesXS.MODID + " Forge Event Subscriber");

} // end class ForgeEventSubscriber 
