package net.extrabiomes.datagen;

import java.util.HashMap;
import java.util.Map;

import mod.alexndr.simplecorelib.api.datagen.SimpleBlockStateProvider;
import net.extrabiomes.ExtrabiomesXS;
import net.extrabiomes.init.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ExtrabiomesBlockStateProvider extends SimpleBlockStateProvider 
{

	public ExtrabiomesBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) 
	{
		super(output, ExtrabiomesXS.MODID, exFileHelper);
	}

	@Override
	protected void registerStatesAndModels() 
	{
        registerTreeBlocks();
        registerCropBlocks();
        registerMisc();
	}

	private String getRegistryNameFromHolder(RegistryObject<? extends Block> blockRef)
	{
		return blockRef.getId().getPath();
	}
	
    // tree blocks
    private void registerTreeBlocks()
    {
    	HashMap<RegistryObject<LeavesBlock>, ResourceLocation> leaves2model 
    		= new HashMap<RegistryObject<LeavesBlock>, ResourceLocation>();
    	leaves2model.put(ModBlocks.leaves_umber, modLoc("block/leavesbrownautumnfancy"));
    	leaves2model.put(ModBlocks.leaves_goldenrod, modLoc("block/leavesorangeautumnfancy"));
    	leaves2model.put(ModBlocks.leaves_vermillion, modLoc("block/leavesredautumnfancy"));
    	leaves2model.put(ModBlocks.leaves_citrine, modLoc("block/leavesyellowautumnfancy"));

    	// leaves
    	for (Map.Entry<RegistryObject<LeavesBlock>, ResourceLocation> entry: leaves2model.entrySet())
    	{
    		String name = getRegistryNameFromHolder(entry.getKey());
    		
            ModelFile leaves = this.models().cubeAll(name, entry.getValue()).renderType("cutout_mipped");
            this.simpleBlock(entry.getKey().get(), leaves);
            this.itemModels().withExistingParent(name, modLoc("block/" + name));
    	}

     	// logs - use axisBlock because texture names are not standard.
        this.axisBlock(ModBlocks.log_autumn.get(), modLoc("block/logautumnside"), modLoc("block/logautumntop"));
        this.itemModels().withExistingParent("log_autumn", modLoc("block/log_autumn"));

    } // end registerTreeBlocks
    
    private void registerMisc()
    {
    }
    
    // crop blocks
    private void registerCropBlocks()
    {
    } // end registerCropBlocks
    
} // end class
