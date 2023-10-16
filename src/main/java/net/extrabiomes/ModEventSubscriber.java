package net.extrabiomes;

import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.extrabiomes.config.ConfigHelper;
import net.extrabiomes.config.ConfigHolder;
import net.extrabiomes.entity.ScarecrowEntity;
import net.extrabiomes.init.ModBlocks;
import net.extrabiomes.init.ModEntities;
import net.extrabiomes.world.regions.TemperateHillyRegion;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent.Operation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;
import terrablender.api.Regions;

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
        event.enqueueWork(() ->
        {
        	Regions.register(new TemperateHillyRegion(
        								new ResourceLocation(ExtrabiomesXS.MODID, "temperate_hilly_region"), 2));
        });
        
    } // end onCommonSetup

    /**
     *  initialize scarecrow attributes.
     */
    @SubscribeEvent
    public static void onEntityAttributeCreation( final EntityAttributeCreationEvent event)
    {
        event.put(ModEntities.scarecrow.get(), ScarecrowEntity.prepareAttributes().build());        
    } // end onEntityAttributeCreation

    /**
     * set up scarecrow placement rules
     */
    @SubscribeEvent
    public static void onSpawnPlacementRegistry(final SpawnPlacementRegisterEvent event)
    {
    	event.register(ModEntities.scarecrow.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, 
    			Mob::checkMobSpawnRules, Operation.REPLACE);
    }
    
	/**
	 * This method will be called by Forge when it is time for the mod to register its Items.
	 * This method will always be called after the Block registry method.
	 */
    @SubscribeEvent
    public static void onRegisterItems(final RegisterEvent event) 
    {
        if (event.getRegistryKey() == Registries.ITEM)
        {
	         // Automatically register BlockItems for all our Blocks
	        ModBlocks.BLOCKS.getEntries().stream()
	                .map(RegistryObject::get)
	                // You can do extra filtering here if you don't want some blocks to have an BlockItem automatically registered for them
	                .filter(block -> needsItemBlock(block))
	                // Register the BlockItem for the block
	                .forEach(block -> {
	                    // Create the new BlockItem with the block and it's properties
	                    final BlockItem blockItem = new BlockItem(block, new Item.Properties());
	                    // Register the BlockItem
	                    event.register(Registries.ITEM,  helper -> {
	                        helper.register(ForgeRegistries.BLOCKS.getKey(block), blockItem);
	                    });
	                });
	        LOGGER.debug("Registered BlockItems");
        }
    } // end onRegisterItems
    
    private static boolean needsItemBlock(Block block)
    {
        if (block instanceof FlowerPotBlock) {
            return false;
        }
        return true;
    }
    
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
