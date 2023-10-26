package net.extrabiomes;

import net.extrabiomes.config.ConfigHolder;
import net.extrabiomes.init.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
	    
	    ModBiomes.BIOME_REGISTRY.register(modEventBus);
        ModBlocks.BLOCKS_REGISTRY.register(modEventBus);
        ModItems.ITEM_REGISTRY.register(modEventBus);
        ModCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);
        ModEntities.ENTITY_TYPE_REGISTRY.register(modEventBus);
        ModFeatures.FEATURES_REGISTRY.register(modEventBus);
        ModConfiguredFeatures.CONFIGURED_FEATURE_REGISTRY.register(modEventBus);
        ModPlacedFeatures.PLACED_FEATURE_REGISTRY.register(modEventBus);

        
        // Register Configs
        modLoadingContext.registerConfig(ModConfig.Type.COMMON, ConfigHolder.SERVER_SPEC);
        modLoadingContext.registerConfig(ModConfig.Type.CLIENT, ConfigHolder.CLIENT_SPEC);
		
	} // end 

} // end-class
