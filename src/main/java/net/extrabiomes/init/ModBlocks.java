package net.extrabiomes.init;

import net.extrabiomes.ExtrabiomesXS;
import net.extrabiomes.content.CattailBlock;
import net.extrabiomes.content.CustomFlowerBlock;
import net.extrabiomes.content.CustomLogBlock;
import net.extrabiomes.content.CustomQuarterBlock;
import net.extrabiomes.content.LeafPileBlock;
import net.extrabiomes.content.QuickSandBlock;
import net.extrabiomes.content.StrawberryBlock;
import net.extrabiomes.world.AutumnTreeGrower;
import net.extrabiomes.world.JapaneseMapleGrower;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.grower.OakTreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ModBlocks 
{
	   public static final DeferredRegister<Block> BLOCKS = 
	            DeferredRegister.create(ForgeRegistries.BLOCKS, ExtrabiomesXS.MODID);

	   // TERRAIN BLOCKS
	   public static final RegistryObject<Block> crackedsand = BLOCKS.register("crackedsand", 
			   () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY)
					   .instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.2F)));
	   
	   public static final RegistryObject<Block> quicksand = BLOCKS.register("quicksand",
			   () -> new QuickSandBlock());
	   
	   public static final RegistryObject<Block> redrock = BLOCKS.register("redrock",
			   () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE)
					   .instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
	   
	   public static final RegistryObject<Block> redcobble = BLOCKS.register("redcobble",		
			   () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE)
					   .instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(2.0F, 6.0F)));
	   
	   public static final RegistryObject<Block> redrock_brick = BLOCKS.register("redrock_brick",		
			   () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE)
					   .instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));

	   
	   // LEAVES
	   // autumn tree leaf blocks
	   public static final RegistryObject<LeavesBlock> leaves_umber = BLOCKS.register("leaves_umber", 
	            () -> leaves(MapColor.COLOR_BROWN, SoundType.GRASS));
	   public static final RegistryObject<LeavesBlock> leaves_goldenrod = BLOCKS.register("leaves_goldenrod", 
	            () -> leaves(MapColor.COLOR_ORANGE, SoundType.GRASS));
	   public static final RegistryObject<LeavesBlock> leaves_vermillion = BLOCKS.register("leaves_vermillion", 
	            () -> leaves(MapColor.COLOR_RED, SoundType.GRASS));
	   public static final RegistryObject<LeavesBlock> leaves_citrine = BLOCKS.register("leaves_citrine", 
	            () -> leaves(MapColor.COLOR_YELLOW, SoundType.GRASS));
	   // japanese maple
	   public static final RegistryObject<LeavesBlock> leaves_japanese_maple = BLOCKS.register("leaves_japanese_maple", 
			   () -> leaves(MapColor.CRIMSON_NYLIUM, SoundType.GRASS));
	   // japanese maple shrub
	   public static final RegistryObject<LeavesBlock> leaves_japanese_maple_shrub = 
			   BLOCKS.register("leaves_japanese_maple_shrub", () -> leaves(MapColor.COLOR_LIGHT_GREEN, SoundType.GRASS));
	   
	   // fir
	   public static final RegistryObject<LeavesBlock> leaves_fir = BLOCKS.register("leaves_fir",
			   () -> leaves(MapColor.PLANT, SoundType.GRASS));
	   // redwood
	   public static final RegistryObject<LeavesBlock> leaves_redwood = BLOCKS.register("leaves_redwood",
			   () -> leaves(MapColor.PLANT, SoundType.GRASS));
	   // black wattle/Austrailian acacia
	   public static final RegistryObject<LeavesBlock> leaves_acacia = BLOCKS.register("leaves_acacia",
			   () -> leaves(MapColor.PLANT, SoundType.GRASS));
	   // cypress (Cupressos sp.)
	   public static final RegistryObject<LeavesBlock> leaves_cypress = BLOCKS.register("leaves_cypress",
			   () -> leaves(MapColor.PLANT, SoundType.GRASS));
	   // bald cypress (Taxodium sp)
	   public static final RegistryObject<LeavesBlock> leaves_bald_cypress = BLOCKS.register("leaves_bald_cypress",
			   () -> leaves(MapColor.PLANT, SoundType.GRASS));
	   // rainbow eucalyptus
	   public static final RegistryObject<LeavesBlock> leaves_rainbow_eucalyptus = BLOCKS.register("leaves_rainbow_eucalyptus",
			   () -> leaves(MapColor.PLANT, SoundType.GRASS));
	   // sakura (cherry)
	   public static final RegistryObject<LeavesBlock> leaves_sakura = BLOCKS.register("leaves_sakura",
			   () -> leaves(MapColor.PLANT, SoundType.GRASS));

	   
	   
	   // LOGS, WOOD & PLANKS
	   // autumn tree logs & wood.
	   public static final RegistryObject<CustomLogBlock> log_autumn = BLOCKS.register("log_autumn", 
			   () -> log(MapColor.COLOR_YELLOW, MapColor.PODZOL, ""));
	   public static final RegistryObject<Block> planks_autumn_wood = BLOCKS.register("planks_autumn_wood",
			   () -> planks(MapColor.WOOD));
	   // japanese maple
	   public static final RegistryObject<CustomLogBlock> log_japanese_maple = BLOCKS.register("log_japanese_maple", 
			   () -> log(MapColor.COLOR_PINK, MapColor.CRIMSON_NYLIUM, ""));
	   public static final RegistryObject<Block> planks_japanese_maple = BLOCKS.register("planks_japanese_maple",
			   () -> planks(MapColor.COLOR_PINK));
	   // fir
	   public static final RegistryObject<CustomLogBlock> log_fir = BLOCKS.register("log_fir",
			   () -> log(MapColor.WOOD, MapColor.COLOR_BROWN, ""));
	   public static final RegistryObject<CustomQuarterBlock> firquarter = BLOCKS.register("firquarter",
			   () -> bigLog(MapColor.WOOD, MapColor.COLOR_BROWN));
	   public static final RegistryObject<Block> planks_fir = BLOCKS.register("planks_fir",
			   () -> planks(MapColor.WOOD));
	   // redwood
	   public static final RegistryObject<CustomLogBlock> log_redwood = BLOCKS.register("log_redwood",
			   () -> log(MapColor.WOOD, MapColor.COLOR_BROWN, ""));
	   public static final RegistryObject<CustomQuarterBlock> redwoodquarter = BLOCKS.register("redwoodquarter",
			   () -> bigLog(MapColor.WOOD, MapColor.COLOR_BROWN));
	   public static final RegistryObject<Block> planks_redwood = BLOCKS.register("planks_redwood",
			   () -> planks(MapColor.WOOD));
	   // black wattle
	   public static final RegistryObject<CustomLogBlock> log_acacia = BLOCKS.register("log_acacia", 
			   () -> log(MapColor.COLOR_YELLOW, MapColor.PODZOL, "block.extrabiomes.log_acacia.description"));
	   public static final RegistryObject<Block> planks_acacia = BLOCKS.register("planks_acacia",
			   () -> planks(MapColor.WOOD));
	   // extra oak
	   public static final RegistryObject<CustomQuarterBlock> oakquarter = BLOCKS.register("oakquarter",
			   () -> bigLog(MapColor.WOOD, MapColor.COLOR_BROWN));
	   
	   // SAPLINGS
	   // autumn saplings
	   public static final RegistryObject<BushBlock> sapling_umber = BLOCKS.register("sapling_umber", 
			   () -> sapling(new AutumnTreeGrower(ModFeatures.UMBER_AUTUMN_TREE, ModFeatures.FANCY_UMBER_AUTUMN_TREE)));
	   public static final RegistryObject<BushBlock> sapling_goldenrod = BLOCKS.register("sapling_goldenrod", 
			   () -> sapling(new AutumnTreeGrower(ModFeatures.GOLDENROD_AUTUMN_TREE, ModFeatures.FANCY_GOLDENROD_AUTUMN_TREE)));
	   public static final RegistryObject<BushBlock> sapling_vermillion = BLOCKS.register("sapling_vermillion", 
			   () -> sapling(new AutumnTreeGrower(ModFeatures.VERMILLION_AUTUMN_TREE, ModFeatures.FANCY_VERMILLION_AUTUMN_TREE)));
	   public static final RegistryObject<BushBlock> sapling_citrine = BLOCKS.register("sapling_citrine", 
			   () -> sapling(new AutumnTreeGrower(ModFeatures.CITRINE_AUTUMN_TREE, ModFeatures.FANCY_CITRINE_AUTUMN_TREE)));
	   // japanese maple saplings
	   public static final RegistryObject<BushBlock> sapling_japanese_maple = BLOCKS.register("sapling_japanese_maple",
			   () -> sapling(new JapaneseMapleGrower()));
	   
	   // TODO - fill in correct AbstractTreeGrowers for all OakTreeGrower in saplings.
	   // japanese maple shrub saplings
	   public static final RegistryObject<BushBlock> sapling_japanese_maple_shrub 
	   		= BLOCKS.register("sapling_japanese_maple_shrub", () -> sapling(new OakTreeGrower()));
	   // fir saplings
	   public static final RegistryObject<BushBlock> sapling_fir = BLOCKS.register("sapling_fir",
			   () -> sapling(new OakTreeGrower()));
	   // redwood saplings
	   public static final RegistryObject<BushBlock> sapling_redwood = BLOCKS.register("sapling_redwood",
			   () -> sapling(new OakTreeGrower()));
	   // acacia/black wattle saplings
	   public static final RegistryObject<BushBlock> sapling_acacia = BLOCKS.register("sapling_acacia",
			   () -> sapling(new OakTreeGrower()));
	   // cypress saplings
	   public static final RegistryObject<BushBlock> sapling_cypress = BLOCKS.register("sapling_cypress",
			   () -> sapling(new OakTreeGrower()));
	   // bald cypress saplings
	   public static final RegistryObject<BushBlock> sapling_bald_cypress = BLOCKS.register("sapling_bald_cypress",
			   () -> sapling(new OakTreeGrower()));
	   // rainbow eucalypus sapling
	   public static final RegistryObject<BushBlock> sapling_rainbow_eucalyptus = BLOCKS.register("sapling_rainbow_eucalyptus",
			   () -> sapling(new OakTreeGrower()));
	   // sakura sapling
	   public static final RegistryObject<BushBlock> sapling_sakura = BLOCKS.register("sapling_sakura",
			   () -> sapling(new OakTreeGrower()));
	   
	   // POTTED SAPLINGS
	   public static final RegistryObject<FlowerPotBlock> potted_sapling_umber = BLOCKS.register("potted_sapling_umber",
			   () -> flowerpot(ModBlocks.sapling_umber));
	   public static final RegistryObject<FlowerPotBlock> potted_sapling_goldenrod = BLOCKS.register("potted_sapling_goldenrod",
			   () -> flowerpot(ModBlocks.sapling_goldenrod));
	   public static final RegistryObject<FlowerPotBlock> potted_sapling_vermillion = BLOCKS.register("potted_sapling_vermillion",
			   () -> flowerpot(ModBlocks.sapling_vermillion));
	   public static final RegistryObject<FlowerPotBlock> potted_sapling_citrine = BLOCKS.register("potted_sapling_citrine",
			   () -> flowerpot(ModBlocks.sapling_citrine));
	   public static final RegistryObject<FlowerPotBlock> potted_sapling_japanese_maple = BLOCKS.register("potted_sapling_japanese_maple",
			   () -> flowerpot(ModBlocks.sapling_japanese_maple));
	   public static final RegistryObject<FlowerPotBlock> potted_sapling_japanese_maple_shrub = BLOCKS.register("potted_sapling_japanese_maple_shrub",
			   () -> flowerpot(ModBlocks.sapling_japanese_maple_shrub));
	   public static final RegistryObject<FlowerPotBlock> potted_sapling_fir = BLOCKS.register("potted_sapling_fir",
			   () -> flowerpot(ModBlocks.sapling_fir));
	   public static final RegistryObject<FlowerPotBlock> potted_sapling_redwood = BLOCKS.register("potted_sapling_redwood",
			   () -> flowerpot(ModBlocks.sapling_redwood));
	   public static final RegistryObject<FlowerPotBlock> potted_sapling_acacia = BLOCKS.register("potted_sapling_acacia",
			   () -> flowerpot(ModBlocks.sapling_acacia));
	   public static final RegistryObject<FlowerPotBlock> potted_sapling_cypress = BLOCKS.register("potted_sapling_cypress",
			   () -> flowerpot(ModBlocks.sapling_cypress));
	   public static final RegistryObject<FlowerPotBlock> potted_sapling_bald_cypress = BLOCKS.register("potted_sapling_bald_cypress",
			   () -> flowerpot(ModBlocks.sapling_bald_cypress));
	   public static final RegistryObject<FlowerPotBlock> potted_sapling_rainbow_eucalyptus = BLOCKS.register("potted_sapling_rainbow_eucalyptus",
			   () -> flowerpot(ModBlocks.sapling_rainbow_eucalyptus));
	   public static final RegistryObject<FlowerPotBlock> potted_sapling_sakura = BLOCKS.register("potted_sapling_sakura",
			   () -> flowerpot(ModBlocks.sapling_sakura));
	   
	   // AESTHETIC BLOCKS
	   // stairs
	   public static final RegistryObject<StairBlock> stairs_autumn = BLOCKS.register("stairs_autumn", 
			   () -> stairs(planks_autumn_wood));
	   public static final RegistryObject<StairBlock> stairs_japanesemaple = BLOCKS.register("stairs_japanesemaple", 
			   () -> stairs(planks_japanese_maple));
	   public static final RegistryObject<StairBlock> stairs_redcobble = BLOCKS.register("stairs_redcobble",
			   () -> stairs(redcobble));
	   public static final RegistryObject<StairBlock> stairs_redrockbrick = BLOCKS.register("stairs_redrockbrick",
			   () -> stairs(redrock_brick));
	   public static final RegistryObject<StairBlock> stairs_redrock = BLOCKS.register("stairs_redrock",
			   () -> stairs(redrock));
	   public static final RegistryObject<StairBlock> stairs_fir = BLOCKS.register("stairs_fir",
			   () -> stairs(planks_fir));
	   public static final RegistryObject<StairBlock> stairs_redwood = BLOCKS.register("stairs_redwood",
			   () -> stairs(planks_redwood));
	   public static final RegistryObject<StairBlock> stairs_acacia = BLOCKS.register("stairs_acacia",
			   () -> stairs(planks_acacia));
	   
	   // slabs
	   public static final RegistryObject<SlabBlock> slab_autumn = BLOCKS.register("slab_autumn", 
			   () -> new SlabBlock(BlockBehaviour.Properties.copy(planks_autumn_wood.get())));
	   public static final RegistryObject<SlabBlock> slab_japanese_maple =  BLOCKS.register("slab_japanese_maple", 
			   () -> new SlabBlock(BlockBehaviour.Properties.copy(planks_japanese_maple.get())));
	   public static final RegistryObject<SlabBlock> slab_fir = BLOCKS.register("slab_fir",
			   () -> new SlabBlock(BlockBehaviour.Properties.copy(planks_fir.get())));
	   public static final RegistryObject<SlabBlock> slab_redwood = BLOCKS.register("slab_redwood",
			   () -> new SlabBlock(BlockBehaviour.Properties.copy(planks_redwood.get())));
	   public static final RegistryObject<SlabBlock> slab_acacia = BLOCKS.register("slab_acacia",
			   () -> new SlabBlock(BlockBehaviour.Properties.copy(planks_acacia.get())));
	   
	   public static final RegistryObject<SlabBlock> slab_redcobble =  BLOCKS.register("slab_redcobble",
			   () -> new SlabBlock(BlockBehaviour.Properties.copy(redcobble.get())));
	   public static final RegistryObject<SlabBlock> slab_redrock =  BLOCKS.register("slab_redrock",
			   () -> new SlabBlock(BlockBehaviour.Properties.copy(redrock.get())));
	   public static final RegistryObject<SlabBlock> slab_redrockbrick =  BLOCKS.register("slab_redrockbrick",
			   () -> new SlabBlock(BlockBehaviour.Properties.copy(redrock_brick.get())));
	   
	   // doors
	   public static RegistryObject<DoorBlock> door_autumn = BLOCKS.register("door_autumn",
			   () -> doors(ModBlocks.planks_autumn_wood.get().defaultMapColor(), BlockSetType.OAK));
	   public static RegistryObject<DoorBlock> door_japanesemaple = BLOCKS.register("door_japanesemaple",
			   () -> doors(ModBlocks.planks_japanese_maple.get().defaultMapColor(), BlockSetType.OAK));
	   public static RegistryObject<DoorBlock> door_fir = BLOCKS.register("door_fir",
			   () -> doors(ModBlocks.planks_fir.get().defaultMapColor(), BlockSetType.SPRUCE));
	   public static RegistryObject<DoorBlock> door_redwood = BLOCKS.register("door_redwood",
			   () -> doors(ModBlocks.planks_redwood.get().defaultMapColor(), BlockSetType.SPRUCE));
	   public static RegistryObject<DoorBlock> door_acacia = BLOCKS.register("door_acacia",
			   () -> doors(ModBlocks.planks_acacia.get().defaultMapColor(), BlockSetType.ACACIA));
	   
	   // fences
	   public static final RegistryObject<FenceBlock> fence_autumn = BLOCKS.register("fence_autumn", 
	            () -> fences(ModBlocks.planks_autumn_wood));
	   public static final RegistryObject<FenceBlock> fence_japanesemaple = BLOCKS.register("fence_japanesemaple",
	            () -> fences(ModBlocks.planks_japanese_maple));
	   public static final RegistryObject<FenceBlock> fence_fir = BLOCKS.register("fence_fir", 
	            () -> fences(ModBlocks.planks_fir));
	   public static final RegistryObject<FenceBlock> fence_redwood = BLOCKS.register("fence_redwood", 
	            () -> fences(ModBlocks.planks_redwood));
	   public static final RegistryObject<FenceBlock> fence_acacia = BLOCKS.register("fence_acacia", 
	            () -> fences(ModBlocks.planks_acacia));
		   
	   // walls
	   public static final RegistryObject<WallBlock> wall_redcobble = BLOCKS.register("wall_redcobble",
			   () -> walls(redcobble));
	   public static final RegistryObject<WallBlock> wall_redrockbrick = BLOCKS.register("wall_redrockbrick",
			   () -> walls(redrock_brick));
	   
	   // fence gates
	   public static final RegistryObject<FenceGateBlock> gate_autumn = BLOCKS.register("gate_autumn",
			   () -> gates(ModBlocks.planks_autumn_wood.get().defaultMapColor(), WoodType.OAK));
	   public static final RegistryObject<FenceGateBlock> gate_japanesemaple = BLOCKS.register("gate_japanesemaple",
			   () -> gates(ModBlocks.planks_japanese_maple.get().defaultMapColor(), WoodType.OAK));
	   public static final RegistryObject<FenceGateBlock> gate_fir = BLOCKS.register("gate_fir",
			   () -> gates(ModBlocks.planks_fir.get().defaultMapColor(), WoodType.SPRUCE));
	   public static final RegistryObject<FenceGateBlock> gate_redwood = BLOCKS.register("gate_redwood",
			   () -> gates(ModBlocks.planks_redwood.get().defaultMapColor(), WoodType.SPRUCE));
	   public static final RegistryObject<FenceGateBlock> gate_acacia = BLOCKS.register("gate_acacia",
			   () -> gates(ModBlocks.planks_acacia.get().defaultMapColor(), WoodType.ACACIA));
	   
	   // buttons
	   public static final RegistryObject<ButtonBlock> button_autumn =  BLOCKS.register("button_autumn",
			   () -> buttons(BlockSetType.OAK, 30, true));
	   public static final RegistryObject<ButtonBlock> button_japanesemaple =  BLOCKS.register("button_japanesemaple",
			   () -> buttons(BlockSetType.OAK, 30, true));
	   public static final RegistryObject<ButtonBlock> button_fir =  BLOCKS.register("button_fir",
			   () -> buttons(BlockSetType.SPRUCE, 30, true));
	   public static final RegistryObject<ButtonBlock> button_redwood =  BLOCKS.register("button_redwood",
			   () -> buttons(BlockSetType.SPRUCE, 30, true));
	   public static final RegistryObject<ButtonBlock> button_acacia =  BLOCKS.register("button_acacia",
			   () -> buttons(BlockSetType.ACACIA, 30, true));
	   	   
	   // pressure plates
	   public static final RegistryObject<PressurePlateBlock> pressureplate_autumn = BLOCKS.register("pressureplate_autumn",
			   () -> pplates(PressurePlateBlock.Sensitivity.EVERYTHING, ModBlocks.planks_autumn_wood.get().defaultMapColor(),
					   		BlockSetType.OAK));
	   public static final RegistryObject<PressurePlateBlock> pressureplate_japanesemaple = BLOCKS.register("pressureplate_japanesemaple",
			   () -> pplates(PressurePlateBlock.Sensitivity.EVERYTHING, ModBlocks.planks_japanese_maple.get().defaultMapColor(),
					   		BlockSetType.OAK));
	   public static final RegistryObject<PressurePlateBlock> pressureplate_fir = BLOCKS.register("pressureplate_fir",
			   () -> pplates(PressurePlateBlock.Sensitivity.EVERYTHING, ModBlocks.planks_fir.get().defaultMapColor(),
					   		BlockSetType.SPRUCE));
	   public static final RegistryObject<PressurePlateBlock> pressureplate_redwood = BLOCKS.register("pressureplate_redwood",
			   () -> pplates(PressurePlateBlock.Sensitivity.EVERYTHING, ModBlocks.planks_redwood.get().defaultMapColor(),
					   		BlockSetType.SPRUCE));
	   public static final RegistryObject<PressurePlateBlock> pressureplate_acacia = BLOCKS.register("pressureplate_acacia",
			   () -> pplates(PressurePlateBlock.Sensitivity.EVERYTHING, ModBlocks.planks_acacia.get().defaultMapColor(),
					   		BlockSetType.ACACIA));
	   
	   // FLOWERS
	   public static final RegistryObject<BushBlock> flower_redrover = BLOCKS.register("flower_redrover", 
			   () -> flowers(MobEffects.SATURATION, 30, "block.extrabiomes.flower_redrover.description"));
	   public static final RegistryObject<BushBlock> flower_hydrangea = BLOCKS.register("flower_hydrangea",
			   () -> flowers(MobEffects.HARM, 2, "block.extrabiomes.flower_hydrangea.description"));
	   public static final RegistryObject<BushBlock> flower_buttercup = BLOCKS.register("flower_buttercup", 
			   () -> flowers(MobEffects.POISON, 5, "block.extrabiomes.flower_buttercup.description"));
	   public static final RegistryObject<BushBlock> flower_lavender = BLOCKS.register("flower_lavender",
			   () -> flowers(MobEffects.SATURATION, 5, "block.extrabiomes.flower_lavender.description"));
	   public static final RegistryObject<BushBlock> flower_tiny_cactus = BLOCKS.register("flower_tiny_cactus",
			   () -> flowers(MobEffects.ABSORPTION, 0, "block.extrabiomes.flower_tiny_cactus.description"));
	   public static final RegistryObject<BushBlock> flower_toadstool = BLOCKS.register("flower_toadstool",
			   () -> flowers(MobEffects.POISON, 10, ""));
	   public static final RegistryObject<BushBlock> flower_calla_white = BLOCKS.register("flower_calla_white",
			   () -> flowers(MobEffects.POISON, 12, "block.extrabiomes.flower_calla_white.description"));
	   public static final RegistryObject<BushBlock> flower_allium = BLOCKS.register("flower_allium",
			   () -> flowers(MobEffects.FIRE_RESISTANCE, 4, "block.extrabiomes.flower_allium.description"));
	   public static final RegistryObject<BushBlock> flower_amaryllis_pink = BLOCKS.register("flower_amaryllis_pink",
			   () -> flowers(MobEffects.REGENERATION, 4, ""));
	   public static final RegistryObject<BushBlock> flower_amaryllis_red = BLOCKS.register("flower_amaryllis_red",
			   () -> flowers(MobEffects.REGENERATION, 4, ""));
	   public static final RegistryObject<BushBlock> flower_amaryllis_white = BLOCKS.register("flower_amaryllis_white",
			   () -> flowers(MobEffects.HEALTH_BOOST, 10, ""));
	   public static final RegistryObject<BushBlock> flower_bachelors_button= BLOCKS.register("flower_bachelors_button",
			   () -> flowers(MobEffects.SATURATION, 5, "block.extrabiomes.flower_bachelors_button.description"));
	   public static final RegistryObject<BushBlock> flower_bells_of_ireland =  BLOCKS.register("flower_bells_of_ireland",
			   () -> flowers(MobEffects.LUCK, 10, ""));
	   
	   public static final RegistryObject<BushBlock> flower_bluebell = BLOCKS.register("flower_bluebell",
			   () -> flowers(MobEffects.WEAKNESS, 4, "block.extrabiomes.flower_bluebell.description"));
	   public static final RegistryObject<BushBlock> flower_calla_black  = BLOCKS.register("flower_calla_black",
			   () -> flowers(MobEffects.POISON, 12, ""));
	   public static final RegistryObject<BushBlock> flower_daisy = BLOCKS.register("flower_daisy",
			   () -> flowers(MobEffects.HEALTH_BOOST, 12, ""));
	   public static final RegistryObject<BushBlock> flower_dandelion = BLOCKS.register("flower_dandelion",
			   () -> flowers(MobEffects.SATURATION, 5, ""));
	   public static final RegistryObject<BushBlock> flower_gardenia  = BLOCKS.register("flower_gardenia",
			   () -> flowers(MobEffects.MOVEMENT_SLOWDOWN, 5, ""));
	   public static final RegistryObject<BushBlock> flower_gerbera_orange = BLOCKS.register("flower_gerbera_orange",
			   () -> flowers(MobEffects.WEAKNESS, 5, ""));
	   public static final RegistryObject<BushBlock> flower_gerbera_pink = BLOCKS.register("flower_gerbera_pink",
			   () -> flowers(MobEffects.WEAKNESS, 5, ""));
	   public static final RegistryObject<BushBlock> flower_gerbera_red = BLOCKS.register("flower_gerbera_red",
			   () -> flowers(MobEffects.WEAKNESS, 5, ""));
	   public static final RegistryObject<BushBlock> flower_gerbera_yellow = BLOCKS.register("flower_gerbera_yellow",
			   () -> flowers(MobEffects.WEAKNESS, 5, ""));
			   
	   public static final RegistryObject<BushBlock> flower_oriental_pink_lily  = BLOCKS.register("flower_oriental_pink_lily",
			   () -> flowers(MobEffects.SATURATION, 5, ""));
	   public static final RegistryObject<BushBlock> flower_lily  = BLOCKS.register("flower_lily",
			   () -> flowers(MobEffects.SATURATION, 5, ""));
	   public static final RegistryObject<BushBlock> flower_iris_blue = BLOCKS.register("flower_iris_blue",
			   () -> flowers(MobEffects.WEAKNESS, 4, ""));
	   public static final RegistryObject<BushBlock> flower_iris_purple = BLOCKS.register("flower_iris_purple",
			   () -> flowers(MobEffects.WEAKNESS, 4, "block.extrabiomes.flower_iris_purple.description"));
	   public static final RegistryObject<BushBlock> flower_marsh_marigold  = BLOCKS.register("flower_marsh_marigold",
			   () -> flowers(MobEffects.SATURATION, 4, ""));
	   public static final RegistryObject<BushBlock> flower_pansy  = BLOCKS.register("flower_pansy",
			   () -> flowers(MobEffects.SATURATION, 1, ""));
	   public static final RegistryObject<BushBlock> flower_poppy  = BLOCKS.register("flower_poppy",
			   () -> flowers(MobEffects.CONFUSION, 10, ""));
	   public static final RegistryObject<BushBlock> flower_blue_poppy  = BLOCKS.register("flower_blue_poppy",
			   () -> flowers(MobEffects.CONFUSION, 10, "block.extrabiomes.flower_blue_poppy.description"));
	   public static final RegistryObject<BushBlock> flower_snapdragon = BLOCKS.register("flower_snapdragon",
			   () -> flowers(MobEffects.SATURATION, 1, ""));
	   public static final RegistryObject<BushBlock> flower_tulip = BLOCKS.register("flower_tulip",
			   () -> flowers(MobEffects.WEAKNESS, 4, ""));
	   public static final RegistryObject<BushBlock> flower_violet = BLOCKS.register("flower_violet",
			   () -> flowers(MobEffects.SATURATION, 1, ""));
	   public static final RegistryObject<BushBlock> flower_yarrow = BLOCKS.register("flower_yarrow",
			   () -> flowers(MobEffects.REGENERATION, 4, ""));
	   public static final RegistryObject<BushBlock> flower_belladonna = BLOCKS.register("flower_belladonna",
			   () -> flowers(MobEffects.BLINDNESS, 4, ""));
		   
	   
	   // POTTED FLOWERS
	   public static final RegistryObject<FlowerPotBlock> potted_redrover = BLOCKS.register("potted_redrover",
			   () -> flowerpot(ModBlocks.flower_redrover));
	   public static final RegistryObject<FlowerPotBlock> potted_hydrangea = BLOCKS.register("potted_hydrangea",
			   () -> flowerpot(ModBlocks.flower_hydrangea));
	   public static final RegistryObject<FlowerPotBlock> potted_buttercup = BLOCKS.register("potted_buttercup",
			   () -> flowerpot(ModBlocks.flower_buttercup));
	   public static final RegistryObject<FlowerPotBlock> potted_lavender = BLOCKS.register("potted_lavender",
			   () -> flowerpot(ModBlocks.flower_lavender));
	   public static final RegistryObject<FlowerPotBlock> potted_tiny_cactus = BLOCKS.register("potted_tiny_cactus",
			   () -> flowerpot(ModBlocks.flower_tiny_cactus));
	   public static final RegistryObject<FlowerPotBlock> potted_toadstool = BLOCKS.register("potted_toadstool",
			   () -> flowerpot(ModBlocks.flower_toadstool));
	   public static final RegistryObject<FlowerPotBlock> potted_calla_white = BLOCKS.register("potted_calla_white",
			   () -> flowerpot(ModBlocks.flower_calla_white));
	   public static final RegistryObject<FlowerPotBlock> potted_allium = BLOCKS.register("potted_allium",
			   () -> flowerpot(ModBlocks.flower_allium));
	   public static final RegistryObject<FlowerPotBlock> potted_amaryllis_pink = BLOCKS.register("potted_amaryllis_pink",
			   () -> flowerpot(ModBlocks.flower_amaryllis_pink));
	   public static final RegistryObject<FlowerPotBlock> potted_amaryllis_red = BLOCKS.register("potted_amaryllis_red",
			   () -> flowerpot(ModBlocks.flower_amaryllis_red));
	   public static final RegistryObject<FlowerPotBlock> potted_amaryllis_white = BLOCKS.register("potted_amaryllis_white",
			   () -> flowerpot(ModBlocks.flower_amaryllis_white));
	   public static final RegistryObject<FlowerPotBlock> potted_bachelors_button = BLOCKS.register("potted_bachelors_button",
			   () -> flowerpot(ModBlocks.flower_bachelors_button));
	   public static final RegistryObject<FlowerPotBlock> potted_bells_of_ireland = BLOCKS.register("potted_bells_of_ireland",
			   () -> flowerpot(ModBlocks.flower_bells_of_ireland));

	   public static final RegistryObject<FlowerPotBlock> potted_bluebell = BLOCKS.register("potted_bluebell",
			   () -> flowerpot(ModBlocks.flower_bluebell));
	   public static final RegistryObject<FlowerPotBlock> potted_calla_black = BLOCKS.register("potted_calla_black",
			   () -> flowerpot(ModBlocks.flower_calla_black));
	   public static final RegistryObject<FlowerPotBlock> potted_daisy = BLOCKS.register("potted_daisy",
			   () -> flowerpot(ModBlocks.flower_daisy));
	   public static final RegistryObject<FlowerPotBlock> potted_dandelion = BLOCKS.register("potted_dandelion",
			   () -> flowerpot(ModBlocks.flower_dandelion));
	   public static final RegistryObject<FlowerPotBlock> potted_gardenia = BLOCKS.register("potted_gardenia",
			   () -> flowerpot(ModBlocks.flower_gardenia));
	   public static final RegistryObject<FlowerPotBlock> potted_gerbera_orange  = BLOCKS.register("potted_gerbera_orange",
			   () -> flowerpot(ModBlocks.flower_gerbera_orange));
	   public static final RegistryObject<FlowerPotBlock> potted_gerbera_pink = BLOCKS.register("potted_gerbera_pink",
			   () -> flowerpot(ModBlocks.flower_gerbera_pink));
	   public static final RegistryObject<FlowerPotBlock> potted_gerbera_red = BLOCKS.register("potted_gerbera_red",
			   () -> flowerpot(ModBlocks.flower_gerbera_red));
	   public static final RegistryObject<FlowerPotBlock> potted_gerbera_yellow = BLOCKS.register("potted_gerbera_yellow",
			   () -> flowerpot(ModBlocks.flower_gerbera_yellow));

	   public static final RegistryObject<FlowerPotBlock> potted_oriental_pink_lily  = BLOCKS.register("potted_oriental_pink_lily",
			   () -> flowerpot(ModBlocks.flower_oriental_pink_lily));
	   public static final RegistryObject<FlowerPotBlock> potted_lily  = BLOCKS.register("potted_lily",
			   () -> flowerpot(ModBlocks.flower_lily));
	   public static final RegistryObject<FlowerPotBlock> potted_iris_blue = BLOCKS.register("potted_iris_blue",
			   () -> flowerpot(ModBlocks.flower_iris_blue));
	   public static final RegistryObject<FlowerPotBlock> potted_iris_purple = BLOCKS.register("potted_iris_purple",
			   () -> flowerpot(ModBlocks.flower_iris_purple));
	   public static final RegistryObject<FlowerPotBlock> potted_marsh_marigold  = BLOCKS.register("potted_marsh_marigold",
			   () -> flowerpot(ModBlocks.flower_marsh_marigold));
	   public static final RegistryObject<FlowerPotBlock> potted_pansy  = BLOCKS.register("potted_pansy",
			   () -> flowerpot(ModBlocks.flower_pansy));
	   public static final RegistryObject<FlowerPotBlock> potted_poppy  = BLOCKS.register("potted_poppy",
			   () -> flowerpot(ModBlocks.flower_poppy));
	   public static final RegistryObject<FlowerPotBlock> potted_blue_poppy  = BLOCKS.register("potted_blue_poppy",
			   () -> flowerpot(ModBlocks.flower_blue_poppy));
	   public static final RegistryObject<FlowerPotBlock> potted_snapdragon = BLOCKS.register("potted_snapdragon",
			   () -> flowerpot(ModBlocks.flower_snapdragon));
	   public static final RegistryObject<FlowerPotBlock> potted_tulip = BLOCKS.register("potted_tulip",
			   () -> flowerpot(ModBlocks.flower_tulip));
	   public static final RegistryObject<FlowerPotBlock> potted_violet = BLOCKS.register("potted_violet",
			   () -> flowerpot(ModBlocks.flower_violet));
	   public static final RegistryObject<FlowerPotBlock> potted_yarrow = BLOCKS.register("potted_yarrow",
			   () -> flowerpot(ModBlocks.flower_yarrow));
	   public static final RegistryObject<FlowerPotBlock> potted_belladonna = BLOCKS.register("potted_belladonna",
			   () -> flowerpot(ModBlocks.flower_belladonna));
	   
	   
	   // OTHER PLANTS
	   public static final RegistryObject<BushBlock> cattail = BLOCKS.register("cattail",
			   () -> new CattailBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).replaceable()
					   .noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)
					   .ignitedByLava().pushReaction(PushReaction.DESTROY)));
	   public static final RegistryObject<BushBlock> flower_autumn_shrub = BLOCKS.register("flower_autumn_shrub",
			   () -> new BushBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).replaceable().noCollission()
					   .instabreak().sound(SoundType.GRASS).ignitedByLava().pushReaction(PushReaction.DESTROY)));
	   public static final RegistryObject<BushBlock> brown_grass_tall = BLOCKS.register("brown_grass_tall",
			   () -> new CattailBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).replaceable()
					   .noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)
					   .ignitedByLava().pushReaction(PushReaction.DESTROY)));
	   public static final RegistryObject<BushBlock> brown_grass_short = BLOCKS.register("brown_grass_short",
			   () -> new CattailBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).replaceable()
					   .noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)
					   .ignitedByLava().pushReaction(PushReaction.DESTROY)));
	   public static final RegistryObject<BushBlock> dead_grass_short = BLOCKS.register("dead_grass_short",
			   () -> new CattailBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).replaceable()
					   .noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)
					   .ignitedByLava().pushReaction(PushReaction.DESTROY)));
	   public static final RegistryObject<BushBlock> dead_grass_tall = BLOCKS.register("dead_grass_tall",
			   () -> new CattailBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).replaceable()
					   .noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)
					   .ignitedByLava().pushReaction(PushReaction.DESTROY)));
	   public static final RegistryObject<BushBlock> dead_grass_yellow = BLOCKS.register("dead_grass_yellow",
			   () -> new CattailBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).replaceable()
					   .noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)
					   .ignitedByLava().pushReaction(PushReaction.DESTROY)));
			   
	   // vines
	   public static final RegistryObject<VineBlock> vine_gloriosa = BLOCKS.register("vine_gloriosa",
			   () -> new VineBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).replaceable().noCollission().randomTicks()
					   .strength(0.2F).sound(SoundType.VINE).ignitedByLava().pushReaction(PushReaction.DESTROY)));
	   public static final RegistryObject<VineBlock> vine_spanish_moss = BLOCKS.register("vine_spanish_moss",
			   () -> new VineBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).replaceable().noCollission().randomTicks()
					   .strength(0.2F).sound(SoundType.VINE).ignitedByLava().pushReaction(PushReaction.DESTROY)));

	   // OBJECTS?
	   public static final RegistryObject<LeafPileBlock> leafpile = BLOCKS.register("leafpile", 
			   () -> new LeafPileBlock(BlockBehaviour.Properties.of().mapColor(MapColor.NONE).replaceable()
					   .noCollission().instabreak().sound(SoundType.GRASS).ignitedByLava()
					   .pushReaction(PushReaction.DESTROY)));
	   
	   // CROPS
	   public static final RegistryObject<StrawberryBlock> crop_strawberry = BLOCKS.register("crop_strawberry", 
			   () -> new StrawberryBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission()
					   	.randomTicks().instabreak().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY)));
	   
	   // ======== BLOCK INITIALIZATION HELPER FUNCTIONS ========== //
	   
	   /**
	    * make flowerpots
	    */
	   @SuppressWarnings("deprecation")
	   private static FlowerPotBlock flowerpot(RegistryObject<BushBlock> pContent) 
	  
	   {
		   BlockBehaviour.Properties blockbehaviour$properties = 
				   BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY);
		   return new FlowerPotBlock(pContent.get(), blockbehaviour$properties);
	   }
	   
	   /**
	    * make flowers
	    */
	   private static CustomFlowerBlock flowers(MobEffect effect, int pEffectDuration, String tooltipKey)
	   {
		   return new CustomFlowerBlock(net.minecraftforge.registries.ForgeRegistries.MOB_EFFECTS.getDelegateOrThrow(effect), 
				   	pEffectDuration, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak()
	   				 .sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY),
	   				 tooltipKey);
	   }
	   
	   /**
	    * make pressure plates, wooden or otherwise
	    */
	   private static PressurePlateBlock pplates(PressurePlateBlock.Sensitivity pSensitivity, MapColor pColor, BlockSetType pBS)
	   {
		   return new PressurePlateBlock(pSensitivity, BlockBehaviour.Properties.of().mapColor(pColor).forceSolidOn()
				   .instrument(NoteBlockInstrument.BASS).noCollission().strength(0.5F).ignitedByLava()
				   .pushReaction(PushReaction.DESTROY), pBS);
	   }
	   
	   /**
	    * make buttons, wooden or otherwise
	    */
	   private static ButtonBlock buttons(BlockSetType bSet, int pTicks, boolean pArrowCanPress)
	   {
		   return new ButtonBlock(BlockBehaviour.Properties.of().noCollission().strength(0.5F)
           		.pushReaction(PushReaction.DESTROY), bSet, pTicks, pArrowCanPress);
	   }
	   
	   /**
	    * make new fence gates
	    */
	   private static FenceGateBlock gates(MapColor color, WoodType woodType)
	   {
		   return new FenceGateBlock(BlockBehaviour.Properties.of().mapColor(color).strength(2.0F, 3.0F)
				   .sound(SoundType.WOOD), woodType);
	   }
	   
	   /**
	    * make new walls
	    */
	   private static WallBlock walls(RegistryObject<Block> solid_block)
	   {
		   return new WallBlock(BlockBehaviour.Properties.copy(solid_block.get()).forceSolidOn());
	   }
	   
	   /**
	    * make new doors
	    */
	   private static DoorBlock doors(MapColor color, BlockSetType blockSetType)
	   {
		   return new DoorBlock(BlockBehaviour.Properties.of().mapColor(color).instrument(NoteBlockInstrument.BASS)
					.strength(3.0F).noOcclusion().ignitedByLava().pushReaction(PushReaction.DESTROY),
					blockSetType);
	   }
	   
	   /**
	    * make new fences
	    */
	   private static FenceBlock fences(RegistryObject<Block> wood_planks)
	   {
		   return new FenceBlock(BlockBehaviour.Properties.of().mapColor(wood_planks.get().defaultMapColor())
                   .strength(2.0F, 3.0F).sound(SoundType.WOOD));
	   }
	   
	   /**
	    * make new stairs
	    */
	   private static StairBlock stairs(RegistryObject<Block> wood_planks) 
	   {
		   return new StairBlock( () -> wood_planks.get().defaultBlockState(),  Block.Properties.copy(wood_planks.get()));
	   }
	   
	   /**
	    * make a new sapling.
	    * @param pTreeGrower our TreeGrower function
	    * @returns a new SaplingBlock object.
	    */
	   private static SaplingBlock sapling(AbstractTreeGrower pTreeGrower)
	   {
		   return new SaplingBlock(pTreeGrower, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT)
				   .noCollission().randomTicks().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY));
	   }
	   
	   /**
	    * make a new leaf block.
	    * @param pType - SoundType for walking on leaves. Usually GRASS.
	    * @returns a new LeavesBlock object.
	    */
	   private static LeavesBlock leaves(MapColor color, SoundType pType) 
	   {
		      return new LeavesBlock(BlockBehaviour.Properties.of().mapColor(color).strength(0.2F).randomTicks()
		    		  .sound(pType).noOcclusion().isValidSpawn(ModBlocks::ocelotOrParrot).isSuffocating((a,b,c)->{return false;})
		    		  .isViewBlocking((a,b,c)->{return false;}).ignitedByLava().pushReaction(PushReaction.DESTROY)
		    		  .isRedstoneConductor((a,b,c)->{return false;}));
	   }
	
	   /**
	    * Make a new log block.
	    * @param pTopMapColor - MapColor for top.
	    * @param pSideMapColor - MapColor for side.
	    * @returns a new CustomLogBlock object.
	    */
	   private static CustomLogBlock log(MapColor pTopMapColor, MapColor pSideMapColor, String tooltip) 
	   {
		   return new CustomLogBlock(BlockBehaviour.Properties.of().mapColor((p_152624_) -> {
		         return p_152624_.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? pTopMapColor : pSideMapColor;
		      }).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava(),
				   tooltip);
	   }
	
	   /**
	    * make a new quarter-log block
	    */
	   private static CustomQuarterBlock bigLog(MapColor pTopMapColor, MapColor pSideMapColor)
	   {
		   final String tooltip  = "extrabiomes.cornerlog.crafting";
		   
		   return new CustomQuarterBlock(BlockBehaviour.Properties.of().mapColor((p_152624_) -> {
		         return p_152624_.getValue(DirectionalBlock.FACING) == Direction.UP ? pTopMapColor : pSideMapColor;
		      }).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava(),
				   tooltip);
	   }
	   
	   /**
	    * make new planks.
	    * @param mColor - MapColor for planks block.
	    * @returns new Block object configured as a wooden plank.
	    */
	   private static Block planks(MapColor mColor)
	   {
		   return new Block(BlockBehaviour.Properties.of().mapColor(mColor).instrument(NoteBlockInstrument.BASS)
				   .strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava());
	   }
  
	   /**
	    * A predicate function used by ModBlocks::leaves() that allows ocelots and parrots to spawn here.
	    * @param p_50822_
	    * @param p_50823_
	    * @param p_50824_
	    * @param p_50825_
	    * @return true or false
	    */
	   private static Boolean ocelotOrParrot(BlockState p_50822_, BlockGetter p_50823_, BlockPos p_50824_, EntityType<?> p_50825_) 
	   {
		      return (boolean)(p_50825_ == EntityType.OCELOT || p_50825_ == EntityType.PARROT);
	   }

} // end class
