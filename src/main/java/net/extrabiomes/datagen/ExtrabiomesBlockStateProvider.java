package net.extrabiomes.datagen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import mod.alexndr.simplecorelib.api.datagen.SimpleBlockStateProvider;
import net.extrabiomes.ExtrabiomesXS;
import net.extrabiomes.content.CustomLogBlock;
import net.extrabiomes.init.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
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
        registerDoorStatesAndModels();
        registerFenceLikeStatesAndModels();
        registerMisc();
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

    	HashMap<RegistryObject<StairBlock>, ResourceLocation> stairs2model
			= new HashMap<RegistryObject<StairBlock>, ResourceLocation>();
    	stairs2model.put(ModBlocks.stairs_autumn,  modLoc("block/planksautumn"));

    	HashMap<RegistryObject<SlabBlock>, ResourceLocation> slab2model
    		= new HashMap<RegistryObject<SlabBlock>, ResourceLocation>();
    	slab2model.put(ModBlocks.slab_autumn, modLoc("block/planksautumn"));
    	
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

        // planks
       	for (Map.Entry<RegistryObject<Block>, ResourceLocation> entry: planks2model.entrySet())
    	{
    		String name = getRegistryNameFromHolder(entry.getKey());
	        ModelFile planks = this.models().cubeAll(name, entry.getValue());
	        this.simpleBlock(entry.getKey().get(), planks);
            this.itemModels().withExistingParent(name, modLoc("block/" + name));
    	} // end-for planks
       	
       	// stairs
       	for (Map.Entry<RegistryObject<StairBlock>, ResourceLocation> entry: stairs2model.entrySet())
       	{
    		String name = getRegistryNameFromHolder(entry.getKey());
       		this.stairsBlock(entry.getKey().get(), entry.getValue());
       		this.itemModels().withExistingParent(name, modLoc("block/" + name));
       	} // end-for stairs
       	
       	// slabs
       	for (Map.Entry<RegistryObject<SlabBlock>, ResourceLocation> entry: slab2model.entrySet())
       	{
    		String name = getRegistryNameFromHolder(entry.getKey());
    		String plankname = GetNameKeyByValue(planks2model, entry.getValue());
    		this.slabBlock(entry.getKey().get(), modLoc("block/" + plankname), entry.getValue());  
       		this.itemModels().withExistingParent(name, modLoc("block/" + name));
       	} // end-for slabs
       	
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

    
    private void registerDoorStatesAndModels()
    {
    	List<RegistryObject<DoorBlock>> doorlist = new ArrayList<RegistryObject<DoorBlock>>();
    	doorlist.add(ModBlocks.door_autumn);
    	
    	for(RegistryObject<DoorBlock> val: doorlist)
    	{
    		String name = getRegistryNameFromHolder(val);
    		doorBlockWithRenderType(val.get(), modLoc("block/" + name + "_lower"), 
                modLoc("block/" + name + "_upper"), "cutout");
   	       	this.itemModels().basicItem(val.get().asItem());

    	} // end-for doorlist
    	
    } // end registerDoorStatesAndModels()
    
    
    private void registerFenceLikeStatesAndModels()
    {
    	HashMap<RegistryObject<FenceBlock>, ResourceLocation> fence2model = 
    			new HashMap<RegistryObject<FenceBlock>, ResourceLocation>();
    	fence2model.put(ModBlocks.fence_autumn, modLoc("block/planksautumn"));
    	
    	HashMap<RegistryObject<FenceGateBlock>, ResourceLocation> gate2model = 
    			new HashMap<RegistryObject<FenceGateBlock>, ResourceLocation>();
    	gate2model.put(ModBlocks.gate_autumn,  modLoc("block/planksautumn"));

    	// fences
       	for (Map.Entry<RegistryObject<FenceBlock>, ResourceLocation> entry: fence2model.entrySet())
    	{
    		String name = getRegistryNameFromHolder(entry.getKey());
    		this.fenceBlock(entry.getKey().get(), entry.getValue());
    		this.models().fenceInventory(name + "_inventory", entry.getValue());
    		this.itemModels().withExistingParent(name, modLoc("block/" + name + "_inventory"));
    	} // end-foreach fence
        
       	// gates
       	for (Map.Entry<RegistryObject<FenceGateBlock>, ResourceLocation> entry: gate2model.entrySet())
    	{
    		String name = getRegistryNameFromHolder(entry.getKey());
    		this.fenceGateBlock(entry.getKey().get(), entry.getValue());
    		this.itemModels().withExistingParent(name, modLoc("block/" + name));
    	} // end-foreach gate

    } // end registerFenceLikeStatesAndModels()
    
    // =================== UTILITY FUNCTIONS ================= //
    
	private static String getRegistryNameFromHolder(RegistryObject<? extends Block> blockRef)
	{
		return blockRef.getId().getPath();
	}
	

    /**
     * reverse lookup on one of the many, many maps of <RegistryObject, ResourceLocation> that we use.
     * @param map A RegistryObject<Block> map.
     * @param val The ResourceLocation of the texture that we want to find the attached block name for.
     * @return string of block name.
     */
    public static String GetNameKeyByValue(HashMap<RegistryObject<Block>, ResourceLocation> map, ResourceLocation val)
    {
    	Set<RegistryObject<Block>> resultSet = map.entrySet().stream()
    			.filter(entry -> entry.getValue().equals(val))
    			.map(Map.Entry::getKey)
    			.collect(Collectors.toSet());
    	@SuppressWarnings("unchecked")
		RegistryObject<Block> ro = (RegistryObject<Block>) resultSet.toArray()[0];
    	return getRegistryNameFromHolder(ro);
    }
    

} // end class
