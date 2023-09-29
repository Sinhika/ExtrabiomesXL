package net.extrabiomes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.extrabiomes.config.ConfigHolder;
import net.extrabiomes.init.ModBlocks;
import net.extrabiomes.init.ModCreativeTabs;
import net.extrabiomes.init.ModEntities;
import net.extrabiomes.init.ModFeatures;
import net.extrabiomes.init.ModItems;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ExtrabiomesXS.MODID)
public class ExtrabiomesXS 
{
    // modid 
    public static final String MODID = "extrabiomes";
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();

	public ExtrabiomesXS() 
	{
		LOGGER.info("Hello from ExtrabiomesXS!");
	    final ModLoadingContext modLoadingContext = ModLoadingContext.get();
	    final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
	    
        ModFeatures.MOD_FEATURES.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);
        ModEntities.ENTITIES.register(modEventBus);
        
        // Register Configs
        modLoadingContext.registerConfig(ModConfig.Type.COMMON, ConfigHolder.SERVER_SPEC);
        modLoadingContext.registerConfig(ModConfig.Type.CLIENT, ConfigHolder.CLIENT_SPEC);
		
	} // end 

} // end-class
