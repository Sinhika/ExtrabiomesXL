package net.extrabiomes;

import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.extrabiomes.config.ConfigHelper;
import net.extrabiomes.config.ConfigHolder;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@EventBusSubscriber(modid = ExtrabiomesXS.MODID, bus = MOD)
public final class ModEventSubscriber 
{
    private static final Logger LOGGER = LogManager.getLogger(ExtrabiomesXS.MODID + " Mod Event Subscriber");

    /**
     * @param event
     */
    @SubscribeEvent
    public static void onCommonSetup(final FMLCommonSetupEvent event)
    {
        LOGGER.debug("Common setup done");
    } // end onCommonSetup

    @SubscribeEvent
    public static void onModConfigEvent(final ModConfigEvent event)
    {
        final ModConfig config = event.getConfig();

        // Rebake the configs when they change
        if (config.getSpec() == ConfigHolder.SERVER_SPEC)
        {
            ConfigHelper.bakeServer(config);
        }
        if (config.getSpec() == ConfigHolder.CLIENT_SPEC) 
        {
            ConfigHelper.bakeClient(config);
        }
    } // onModConfigEvent


} // end class ModEventSubscriber
