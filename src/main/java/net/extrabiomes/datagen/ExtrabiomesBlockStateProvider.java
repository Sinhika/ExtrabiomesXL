package net.extrabiomes.datagen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import mod.alexndr.simplecorelib.api.datagen.SimpleBlockStateProvider;
import net.extrabiomes.ExtrabiomesXS;
import net.extrabiomes.content.CustomFlowerBlock;
import net.extrabiomes.content.CustomLogBlock;
import net.extrabiomes.init.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraftforge.client.model.generators.ConfiguredModel;
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
        registerSaplings();
        registerCropBlocks();
        registerFlowers();
        registerDoorStatesAndModels();
        registerFenceLikeStatesAndModels();
        registerPressurePlatesAndButtons();
        registerTerrainBlocks();
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
    	leaves2model.put(ModBlocks.leaves_japanese_maple, modLoc("block/leavesjapanesemaplefancy"));
    	
    	HashMap<RegistryObject<CustomLogBlock>, String> log2model
    		= new HashMap<RegistryObject<CustomLogBlock>, String>();
    	log2model.put(ModBlocks.log_autumn, "block/logautumn");
    	log2model.put(ModBlocks.log_japanese_maple, "block/logjapanesemaple");
    	
    	HashMap<RegistryObject<Block>, ResourceLocation> planks2model
    		= new HashMap<RegistryObject<Block>, ResourceLocation>();
    	planks2model.put(ModBlocks.planks_autumn_wood, modLoc("block/planksautumn"));
    	planks2model.put(ModBlocks.planks_japanese_maple, modLoc("block/planksjapanesemaple"));
    	
    	HashMap<RegistryObject<StairBlock>, ResourceLocation> stairs2model
			= new HashMap<RegistryObject<StairBlock>, ResourceLocation>();
    	stairs2model.put(ModBlocks.stairs_autumn,  modLoc("block/planksautumn"));
    	stairs2model.put(ModBlocks.stairs_japanesemaple, modLoc("block/planksjapanesemaple"));
    	
    	HashMap<RegistryObject<SlabBlock>, ResourceLocation> slab2model
    		= new HashMap<RegistryObject<SlabBlock>, ResourceLocation>();
    	slab2model.put(ModBlocks.slab_autumn, modLoc("block/planksautumn"));
    	slab2model.put(ModBlocks.slab_japanese_maple, modLoc("block/planksjapanesemaple"));
    	
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
    
    private void registerPressurePlatesAndButtons()
    {
    	HashMap<RegistryObject<PressurePlateBlock>, ResourceLocation> plate2model = 
    			new HashMap<RegistryObject<PressurePlateBlock>, ResourceLocation> ();
    	plate2model.put(ModBlocks.pressureplate_autumn, modLoc("block/planksautumn"));
    	plate2model.put(ModBlocks.pressureplate_japanesemaple, modLoc("block/planksjapanesemaple"));
    	
    	HashMap<RegistryObject<ButtonBlock>, ResourceLocation> button2model = 
    			new HashMap<RegistryObject<ButtonBlock>, ResourceLocation> ();
    	button2model.put(ModBlocks.button_autumn, modLoc("block/planksautumn"));
    	button2model.put(ModBlocks.button_japanesemaple, modLoc("block/planksjapanesemaple"));
    	
        // pressure_plate
     	for (Map.Entry<RegistryObject<PressurePlateBlock>, ResourceLocation> entry: plate2model.entrySet())
       	{
    		String name = getRegistryNameFromHolder(entry.getKey());
     		this.pressurePlateBlock(entry.getKey().get(), entry.getValue());
     		this.itemModels().withExistingParent(name, modLoc("block/" + name));
       	}
     	
        // buttons
    	for (Map.Entry<RegistryObject<ButtonBlock>, ResourceLocation> entry: button2model.entrySet())
       	{
    		String name = getRegistryNameFromHolder(entry.getKey());
	        this.buttonBlock(entry.getKey().get(), entry.getValue());
	        this.models().buttonInventory(name + "_inventory", entry.getValue());
	        this.itemModels().withExistingParent(name, modLoc("block/" + name +"_inventory"));
       	}
    } // end registerMisc()

    // saplings
    private void registerSaplings()
    {
    	HashMap<RegistryObject<SaplingBlock>, ResourceLocation> sapling2model = 
    			new HashMap<RegistryObject<SaplingBlock>, ResourceLocation>();
    	sapling2model.put(ModBlocks.sapling_citrine, modLoc("block/saplingyellowautumn"));
    	sapling2model.put(ModBlocks.sapling_goldenrod, modLoc("block/saplingorangeautumn"));
    	sapling2model.put(ModBlocks.sapling_umber, modLoc("block/saplingbrownautumn"));
       	sapling2model.put(ModBlocks.sapling_vermillion, modLoc("block/saplingredautumn"));
       	sapling2model.put(ModBlocks.sapling_japanese_maple, modLoc("block/saplingjapanesemaple"));
       	
       	// saplings
    	for (Map.Entry<RegistryObject<SaplingBlock>, ResourceLocation> entry: sapling2model.entrySet())
    	{
    		String name = getRegistryNameFromHolder(entry.getKey());
    		ModelFile sapling = this.models().cross(name, entry.getValue()).renderType("cutout");
        	this.simpleBlock(entry.getKey().get(), sapling);
        	this.itemModels().singleTexture(name, mcLoc("generated"), "layer0", entry.getValue());
    	}
    } // end registerSaplings()
    
    // crop blocks
    private void registerCropBlocks()
    {
    	List<ModelFile> strawberry_models = new ArrayList<ModelFile>(7);
        for (int ii=0; ii<6; ii++)
        {
        	strawberry_models.add(this.models().crop("crop_strawberry" + ii, 
                                                    modLoc("block/plant_strawberry" + ii)).renderType("cutout_mipped"));
        } // end-for 
        // the last texture skips a number for some reason.
    	strawberry_models.add(this.models().crop("crop_strawberry6", modLoc("block/plant_strawberry7")).renderType("cutout_mipped"));
        
        this.getVariantBuilder(ModBlocks.crop_strawberry.get())
	        .partialState().with(CropBlock.AGE, 0).addModels(new ConfiguredModel(strawberry_models.get(0)))
	        .partialState().with(CropBlock.AGE, 1).addModels(new ConfiguredModel(strawberry_models.get(1)))
	        .partialState().with(CropBlock.AGE, 2).addModels(new ConfiguredModel(strawberry_models.get(2)))
	        .partialState().with(CropBlock.AGE, 3).addModels(new ConfiguredModel(strawberry_models.get(3)))
	        .partialState().with(CropBlock.AGE, 4).addModels(new ConfiguredModel(strawberry_models.get(4)))
	        .partialState().with(CropBlock.AGE, 5).addModels(new ConfiguredModel(strawberry_models.get(5)))
	        .partialState().with(CropBlock.AGE, 6).addModels(new ConfiguredModel(strawberry_models.get(5)))
	        .partialState().with(CropBlock.AGE, 7).addModels(new ConfiguredModel(strawberry_models.get(6)));

    } // end registerCropBlocks

    
    // flowers
    private void registerFlowers()
    {
    	HashMap<RegistryObject<CustomFlowerBlock>,ResourceLocation> flower2model =
    			new HashMap<RegistryObject<CustomFlowerBlock>,ResourceLocation>();
    	flower2model.put(ModBlocks.flower_allium, modLoc("block/allium"));
    	flower2model.put(ModBlocks.flower_amaryllis_pink, modLoc("block/amaryllis_pink"));
    	flower2model.put(ModBlocks.flower_amaryllis_red, modLoc("block/amaryllis_red"));
    	flower2model.put(ModBlocks.flower_amaryllis_white, modLoc("block/amaryllis_white"));
    	flower2model.put(ModBlocks.flower_buttercup, modLoc("block/buttercup"));
    	flower2model.put(ModBlocks.flower_calla_white, modLoc("block/calla_white"));
    	flower2model.put(ModBlocks.flower_hydrangea, modLoc("block/hydrangea"));
    	flower2model.put(ModBlocks.flower_lavender, modLoc("block/lavender"));
    	flower2model.put(ModBlocks.flower_redrover, modLoc("block/redrover"));
    	flower2model.put(ModBlocks.flower_tiny_cactus, modLoc("block/tinycactus"));
    	flower2model.put(ModBlocks.flower_toadstool, modLoc("block/toadstools"));
    	flower2model.put(ModBlocks.flower_bachelors_button, modLoc("block/bachelorsbutton_blue"));
    	flower2model.put(ModBlocks.flower_bells_of_ireland, modLoc("block/bellsofireland"));
    	flower2model.put(ModBlocks.flower_bluebell, modLoc("block/bluebell"));
    	flower2model.put(ModBlocks.flower_calla_black, modLoc("block/calla_black"));
    	flower2model.put(ModBlocks.flower_daisy, modLoc("block/daisy"));
    	flower2model.put(ModBlocks.flower_dandelion, modLoc("block/dandelion"));
    	flower2model.put(ModBlocks.flower_gardenia, modLoc("block/gardenia"));
    	flower2model.put(ModBlocks.flower_gerbera_orange, modLoc("block/gerbera_orange"));
    	flower2model.put(ModBlocks.flower_gerbera_pink, modLoc("block/gerbera_pink"));
    	flower2model.put(ModBlocks.flower_gerbera_red, modLoc("block/gerbera_red"));
    	flower2model.put(ModBlocks.flower_gerbera_yellow, modLoc("block/gerbera_yellow"));
    	
    	for (Map.Entry<RegistryObject<CustomFlowerBlock>, ResourceLocation> entry: flower2model.entrySet())
    	{
    		String name = getRegistryNameFromHolder(entry.getKey());
    		ModelFile flower = this.models().cross(name, entry.getValue()).renderType("cutout");
    		this.simpleBlock(entry.getKey().get(), flower);
        	this.itemModels().singleTexture(name, mcLoc("generated"), "layer0", entry.getValue());
    	} // end-foreach flower

		// flower pots
    	HashMap<RegistryObject<FlowerPotBlock>,ResourceLocation> pot2model =
    			new HashMap<RegistryObject<FlowerPotBlock>,ResourceLocation>();
    	pot2model.put(ModBlocks.potted_allium, modLoc("block/allium"));
    	pot2model.put(ModBlocks.potted_amaryllis_pink, modLoc("block/amaryllis_pink"));
    	pot2model.put(ModBlocks.potted_amaryllis_red, modLoc("block/amaryllis_red"));
    	pot2model.put(ModBlocks.potted_amaryllis_white, modLoc("block/amaryllis_white"));
    	pot2model.put(ModBlocks.potted_buttercup, modLoc("block/buttercup"));
    	pot2model.put(ModBlocks.potted_calla_white, modLoc("block/calla_white"));
    	pot2model.put(ModBlocks.potted_hydrangea, modLoc("block/hydrangea"));
    	pot2model.put(ModBlocks.potted_lavender, modLoc("block/lavender"));
    	pot2model.put(ModBlocks.potted_redrover, modLoc("block/redrover"));
    	pot2model.put(ModBlocks.potted_tiny_cactus, modLoc("block/tinycactus"));
    	pot2model.put(ModBlocks.potted_toadstool, modLoc("block/toadstools"));
    	pot2model.put(ModBlocks.potted_bachelors_button, modLoc("block/bachelorsbutton_blue"));
    	pot2model.put(ModBlocks.potted_bells_of_ireland, modLoc("block/bellsofireland"));
    	
    	for (Map.Entry<RegistryObject<FlowerPotBlock>, ResourceLocation> entry: pot2model.entrySet())
    	{
    		String name = getRegistryNameFromHolder(entry.getKey());
	    	ModelFile flower_pot = this.models().withExistingParent(name, mcLoc("block/flower_pot_cross"))
	    			.texture("plant", entry.getValue());
	    	this.simpleBlock(entry.getKey().get(), flower_pot);
    	} // end-foreach pot
    	
    	// cattail
    	ModelFile bush = this.models().cross("cattail", modLoc("block/cattail")).renderType("cutout");
    	this.simpleBlock(ModBlocks.cattail.get(), bush);
    	this.itemModels().singleTexture("cattail", mcLoc("generated"), "layer0", modLoc("block/cattail"));
    	
    	// autumn shrub
    	bush = this.models().cross("flower_autumn_shrub", modLoc("block/autumnshrub")).renderType("cutout");
    	this.simpleBlock(ModBlocks.flower_autumn_shrub.get(), bush);
    	this.itemModels().singleTexture("flower_autumn_shrub", mcLoc("generated"), "layer0", modLoc("block/autumnshrub"));
    } // end registerFlowers
    
    

    private void registerDoorStatesAndModels()
    {
    	List<RegistryObject<DoorBlock>> doorlist = new ArrayList<RegistryObject<DoorBlock>>();
    	doorlist.add(ModBlocks.door_autumn);
    	doorlist.add(ModBlocks.door_japanesemaple);
    	
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
    	fence2model.put(ModBlocks.fence_japanesemaple, modLoc("block/planksjapanesemaple"));
    	
    	HashMap<RegistryObject<FenceGateBlock>, ResourceLocation> gate2model = 
    			new HashMap<RegistryObject<FenceGateBlock>, ResourceLocation>();
    	gate2model.put(ModBlocks.gate_autumn,  modLoc("block/planksautumn"));
    	gate2model.put(ModBlocks.gate_japanesemaple, modLoc("block/planksjapanesemaple"));
    	
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
    
    private void registerTerrainBlocks()
    {
    	this.simpleBlockWithItem(ModBlocks.crackedsand.get(), this.cubeAll(ModBlocks.crackedsand.get()));
    } // end registerTerrainBlocks()
    
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
