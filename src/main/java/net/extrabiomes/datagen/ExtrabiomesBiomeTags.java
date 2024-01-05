package net.extrabiomes.datagen;

import net.extrabiomes.ExtrabiomesXS;
import net.extrabiomes.init.ModBiomes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ExtrabiomesBiomeTags extends BiomeTagsProvider
{
    public ExtrabiomesBiomeTags(PackOutput pOutput,
                                CompletableFuture<HolderLookup.Provider> pProvider,
                                @Nullable ExistingFileHelper existingFileHelper)
    {
        super(pOutput, pProvider, ExtrabiomesXS.MODID, existingFileHelper);
    }

    @Override protected void addTags(HolderLookup.Provider pProvider)
    {
        this.tag(BiomeTags.IS_FOREST).add(ModBiomes.AUTUMNWOODS);
        // TODO more

        for (RegistryObject<Biome> foo : ModBiomes.BIOME_REGISTRY.getEntries())
        {
            this.tag(BiomeTags.IS_OVERWORLD).add(foo.getKey());
        }
    } // end addTags()

} // end class
