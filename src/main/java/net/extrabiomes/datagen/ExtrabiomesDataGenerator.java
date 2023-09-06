package net.extrabiomes.datagen;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import mod.alexndr.simplecorelib.api.datagen.SimpleLootTableProvider;
import net.extrabiomes.ExtrabiomesXS;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid=ExtrabiomesXS.MODID, bus=EventBusSubscriber.Bus.MOD)
public class ExtrabiomesDataGenerator 
{
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event)
    {
        DataGenerator gen = event.getGenerator();
        PackOutput packOutput = gen.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();		
        
        // server datagen
        ExtrabiomesBlockTags blockTags = new ExtrabiomesBlockTags(packOutput, lookupProvider, existingFileHelper);
        gen.addProvider(event.includeServer(), blockTags);
        gen.addProvider(event.includeServer(), new ExtrabiomesItemTags(packOutput, lookupProvider, blockTags.contentsGetter(), 
        		existingFileHelper));
        gen.addProvider(event.includeServer(), new SimpleLootTableProvider(packOutput, List.of(
        		new LootTableProvider.SubProviderEntry(ExtrabiomesLootSubprovider::new, LootContextParamSets.BLOCK))));
        // TODO: recipe provider.
        
         // client datagen
        gen.addProvider(event.includeClient(), new ExtrabiomesBlockStateProvider(packOutput, event.getExistingFileHelper()));
        gen.addProvider(event.includeClient(), new ExtrabiomesItemModelProvider(packOutput, event.getExistingFileHelper()));
    } // end gatherData()
    
} // end-class
