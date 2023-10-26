package net.extrabiomes.datagen;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import mod.alexndr.simplecorelib.api.datagen.SimpleLootTableProvider;
import net.extrabiomes.ExtrabiomesXS;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid=ExtrabiomesXS.MODID, bus=EventBusSubscriber.Bus.MOD)
public class ExtrabiomesDataGenerator 
{
    private static final RegistrySetBuilder DO_BOOTSTRAPS = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ExtrabiomesBootstraps::bootstrap_ConfiguredFeature)
            .add(Registries.PLACED_FEATURE, ExtrabiomesBootstraps::bootstrap_PlacedFeature);

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
        gen.addProvider(event.includeServer(),
                new ExtrabiomesItemTags(packOutput, lookupProvider, blockTags.contentsGetter(), existingFileHelper));
        gen.addProvider(event.includeServer(), new SimpleLootTableProvider(packOutput, List.of(
        		new LootTableProvider.SubProviderEntry(ExtrabiomesLootSubprovider::new, LootContextParamSets.BLOCK),
        		new LootTableProvider.SubProviderEntry(ExtrabiomesEntityLootSubprovider::new , LootContextParamSets.ENTITY))));
        
        gen.addProvider(event.includeServer(), new ExtrabiomesRecipes(packOutput));
        gen.addProvider(event.includeServer(),
                new DatapackBuiltinEntriesProvider(packOutput, event.getLookupProvider(), DO_BOOTSTRAPS, Set.of(ExtrabiomesXS.MODID)));

                // client datagen
        gen.addProvider(event.includeClient(), new ExtrabiomesBlockStateProvider(packOutput, event.getExistingFileHelper()));
        gen.addProvider(event.includeClient(), new ExtrabiomesItemModelProvider(packOutput, event.getExistingFileHelper()));
    } // end gatherData()
    
} // end-class
