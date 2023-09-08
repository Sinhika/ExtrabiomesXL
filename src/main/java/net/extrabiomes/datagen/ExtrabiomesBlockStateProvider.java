package net.extrabiomes.datagen;

import java.util.HashMap;
import java.util.Map;

import mod.alexndr.simplecorelib.api.datagen.SimpleBlockStateProvider;
import net.extrabiomes.ExtrabiomesXS;
import net.extrabiomes.content.CustomLogBlock;
import net.extrabiomes.init.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SaplingBlock;
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

    	HashMap<RegistryObject<CustomLogBlock>, String> log2model
    		= new HashMap<RegistryObject<CustomLogBlock>, String>();
    	log2model.put(ModBlocks.log_autumn, "block/logautumn");
    	
    	HashMap<RegistryObject<Block>, ResourceLocation> planks2model
    		= new HashMap<RegistryObject<Block>, ResourceLocation>();
    	planks2model.put(ModBlocks.planks_autumn_wood, modLoc("block/planksautumn"));
    	
    	// leaves
    	for (Map.Entry<RegistryObject<LeavesBlock>, ResourceLocation> entry: leaves2model.entrySet())
    	{
    		String name = getRegistryNameFromHolder(entry.getKey());
    		
            ModelFile leaves = this.models().cubeAll(name, entry.getValue()).renderType("cutout_mipped");
            this.simpleBlock(entry.getKey().get(), leaves);
            this.itemModels().withExistingParent(name, modLoc("block/" + name));
    	}

     	// logs - use axisBlock because texture names are not standard.
    	for (Map.Entry<RegistryObject<CustomLogBlock>, String> entry: log2model.entrySet())
    	{
    		String sidename = entry.getValue() + "side";
    		String topname = entry.getValue() + "top";
    		String name = getRegistryNameFromHolder(entry.getKey());
    		
            this.axisBlock(entry.getKey().get(), modLoc(sidename), modLoc(topname));
            this.itemModels().withExistingParent(name, modLoc("block/" + name));
    	}

        // autumn planks
       	for (Map.Entry<RegistryObject<Block>, ResourceLocation> entry: planks2model.entrySet())
    	{
    		String name = getRegistryNameFromHolder(entry.getKey());
	        ModelFile planks = this.models().cubeAll(name, entry.getValue());
	        this.simpleBlock(entry.getKey().get(), planks);
            this.itemModels().withExistingParent(name, modLoc("block/" + name));
    	}
       	
       	
    } // end registerTreeBlocks
    
    private void registerMisc()
    {
    }
    
    // crop blocks
    private void registerCropBlocks()
    {
    	HashMap<RegistryObject<SaplingBlock>, ResourceLocation> sapling2model = 
    			new HashMap<RegistryObject<SaplingBlock>, ResourceLocation>();
    	sapling2model.put(ModBlocks.sapling_citrine, modLoc("block/saplingyellowautumn"));
    	sapling2model.put(ModBlocks.sapling_goldenrod, modLoc("block/saplingorangeautumn"));
    	sapling2model.put(ModBlocks.sapling_umber, modLoc("block/saplingbrownautumn"));
       	sapling2model.put(ModBlocks.sapling_vermillion, modLoc("block/saplingredautumn"));
   	
       	// saplings
    	for (Map.Entry<RegistryObject<SaplingBlock>, ResourceLocation> entry: sapling2model.entrySet())
    	{
    		String name = getRegistryNameFromHolder(entry.getKey());
    		ModelFile sapling = this.models().cross(name, entry.getValue()).renderType("cutout_mipped");
        	this.simpleBlock(entry.getKey().get(), sapling);
        	this.itemModels().withExistingParent(name, modLoc("block/" + name));
    	}
    } // end registerCropBlocks
    
} // end class
