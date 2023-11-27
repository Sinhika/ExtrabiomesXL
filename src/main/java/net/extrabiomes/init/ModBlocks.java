package net.extrabiomes.init;

import net.extrabiomes.ExtrabiomesXS;
import net.extrabiomes.content.CattailBlock;
import net.extrabiomes.content.CustomFlowerBlock;
import net.extrabiomes.content.CustomLogBlock;
import net.extrabiomes.content.CustomQuarterBlock;
import net.extrabiomes.content.LeafPileBlock;
import net.extrabiomes.content.MiniLogBlock;
import net.extrabiomes.content.QuickSandBlock;
import net.extrabiomes.content.StrawberryBlock;
import net.extrabiomes.world.features.growers.AutumnTreeGrower;
import net.extrabiomes.world.features.growers.SimpleMegaTreeGrower;
import net.extrabiomes.world.features.growers.SimpleTreeGrower;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
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
import net.minecraftforge.registries.RegistryObject;

public final class ModBlocks 
{
	   public static final DeferredRegister<Block> BLOCKS_REGISTRY = 
	            DeferredRegister.create(Registries.BLOCK, ExtrabiomesXS.MODID);

	   // TERRAIN BLOCKS
	   public static final RegistryObject<Block> crackedsand = BLOCKS_REGISTRY.register("crackedsand", 
			   () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY)
					   .instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.2F)));
	   
	   public static final RegistryObject<Block> quicksand = BLOCKS_REGISTRY.register("quicksand",
			   () -> new QuickSandBlock());
	   
	   public static final RegistryObject<Block> redrock = BLOCKS_REGISTRY.register("redrock",
			   () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE)
					   .instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
	   
	   public static final RegistryObject<Block> redcobble = BLOCKS_REGISTRY.register("redcobble",		
			   () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE)
					   .instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(2.0F, 6.0F)));
	   
	   public static final RegistryObject<Block> redrock_brick = BLOCKS_REGISTRY.register("redrock_brick",		
			   () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE)
					   .instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));

	   
	   // LEAVES
	   // autumn tree leaf blocks
	   public static final RegistryObject<LeavesBlock> leaves_umber = BLOCKS_REGISTRY.register("leaves_umber", 
	            () -> leaves(MapColor.COLOR_BROWN, SoundType.GRASS));
	   public static final RegistryObject<LeavesBlock> leaves_goldenrod = BLOCKS_REGISTRY.register("leaves_goldenrod", 
	            () -> leaves(MapColor.COLOR_ORANGE, SoundType.GRASS));
	   public static final RegistryObject<LeavesBlock> leaves_vermillion = BLOCKS_REGISTRY.register("leaves_vermillion", 
	            () -> leaves(MapColor.COLOR_RED, SoundType.GRASS));
	   public static final RegistryObject<LeavesBlock> leaves_citrine = BLOCKS_REGISTRY.register("leaves_citrine", 
	            () -> leaves(MapColor.COLOR_YELLOW, SoundType.GRASS));
	   // japanese maple
	   public static final RegistryObject<LeavesBlock> leaves_japanese_maple = BLOCKS_REGISTRY.register("leaves_japanese_maple", 
			   () -> leaves(MapColor.CRIMSON_NYLIUM, SoundType.GRASS));
	   // japanese maple shrub
	   public static final RegistryObject<LeavesBlock> leaves_japanese_maple_shrub = 
			   BLOCKS_REGISTRY.register("leaves_japanese_maple_shrub", () -> leaves(MapColor.COLOR_LIGHT_GREEN, SoundType.GRASS));
	   
	   // fir
	   public static final RegistryObject<LeavesBlock> leaves_fir = BLOCKS_REGISTRY.register("leaves_fir",
			   () -> leaves(MapColor.PLANT, SoundType.GRASS));
	   // redwood
	   public static final RegistryObject<LeavesBlock> leaves_redwood = BLOCKS_REGISTRY.register("leaves_redwood",
			   () -> leaves(MapColor.PLANT, SoundType.GRASS));
	   // black wattle/Austrailian acacia
	   public static final RegistryObject<LeavesBlock> leaves_acacia = BLOCKS_REGISTRY.register("leaves_acacia",
			   () -> leaves(MapColor.PLANT, SoundType.GRASS));
	   // cypress (Cupressos sp.)
	   public static final RegistryObject<LeavesBlock> leaves_cypress = BLOCKS_REGISTRY.register("leaves_cypress",
			   () -> leaves(MapColor.PLANT, SoundType.GRASS));
	   // bald cypress (Taxodium sp)
	   public static final RegistryObject<LeavesBlock> leaves_bald_cypress = BLOCKS_REGISTRY.register("leaves_bald_cypress",
			   () -> leaves(MapColor.PLANT, SoundType.GRASS));
	   // rainbow eucalyptus
	   public static final RegistryObject<LeavesBlock> leaves_rainbow_eucalyptus = BLOCKS_REGISTRY.register("leaves_rainbow_eucalyptus",
			   () -> leaves(MapColor.PLANT, SoundType.GRASS));
	   // sakura (cherry)
	   public static final RegistryObject<LeavesBlock> leaves_sakura = BLOCKS_REGISTRY.register("leaves_sakura",
			   () -> leaves(MapColor.PLANT, SoundType.GRASS));

	   
	   
	   // LOGS, WOOD & PLANKS
	   // autumn tree logs & wood.
	   public static final RegistryObject<CustomLogBlock> log_autumn = BLOCKS_REGISTRY.register("log_autumn", 
			   () -> log(MapColor.COLOR_YELLOW, MapColor.PODZOL, ""));
	   public static final RegistryObject<Block> planks_autumn_wood = BLOCKS_REGISTRY.register("planks_autumn_wood",
			   () -> planks(MapColor.WOOD));
	   // japanese maple
	   public static final RegistryObject<CustomLogBlock> log_japanese_maple = BLOCKS_REGISTRY.register("log_japanese_maple", 
			   () -> log(MapColor.COLOR_PINK, MapColor.CRIMSON_NYLIUM, ""));
	   public static final RegistryObject<Block> planks_japanese_maple = BLOCKS_REGISTRY.register("planks_japanese_maple",
			   () -> planks(MapColor.COLOR_PINK));
	   // fir
	   public static final RegistryObject<CustomLogBlock> log_fir = BLOCKS_REGISTRY.register("log_fir",
			   () -> log(MapColor.WOOD, MapColor.COLOR_BROWN, ""));
	   public static final RegistryObject<CustomQuarterBlock> firquarter = BLOCKS_REGISTRY.register("firquarter",
			   () -> bigLog(MapColor.WOOD, MapColor.COLOR_BROWN));
	   public static final RegistryObject<Block> planks_fir = BLOCKS_REGISTRY.register("planks_fir",
			   () -> planks(MapColor.WOOD));
	   // redwood
	   public static final RegistryObject<CustomLogBlock> log_redwood = BLOCKS_REGISTRY.register("log_redwood",
			   () -> log(MapColor.WOOD, MapColor.COLOR_BROWN, ""));
	   public static final RegistryObject<CustomQuarterBlock> redwoodquarter = BLOCKS_REGISTRY.register("redwoodquarter",
			   () -> bigLog(MapColor.WOOD, MapColor.COLOR_BROWN));
	   public static final RegistryObject<Block> planks_redwood = BLOCKS_REGISTRY.register("planks_redwood",
			   () -> planks(MapColor.WOOD));
	   // black wattle
	   public static final RegistryObject<CustomLogBlock> log_acacia = BLOCKS_REGISTRY.register("log_acacia", 
			   () -> log(MapColor.COLOR_YELLOW, MapColor.PODZOL, "block.extrabiomes.log_acacia.description"));
	   public static final RegistryObject<Block> planks_acacia = BLOCKS_REGISTRY.register("planks_acacia",
			   () -> planks(MapColor.WOOD));
	   // extra oak
	   public static final RegistryObject<CustomQuarterBlock> oakquarter = BLOCKS_REGISTRY.register("oakquarter",
			   () -> bigLog(MapColor.WOOD, MapColor.COLOR_BROWN));
	   // bald cypress
	   public static final RegistryObject<CustomLogBlock> log_baldcypress = BLOCKS_REGISTRY.register("log_baldcypress",
			   () -> log(MapColor.WOOD, MapColor.COLOR_GRAY, "block.extrabiomes.log_baldcypress.description"));
	   public static final RegistryObject<CustomQuarterBlock> baldcypressquarter = BLOCKS_REGISTRY.register("baldcypressquarter",
			   () -> bigLog(MapColor.WOOD, MapColor.COLOR_GRAY));
	   public static final RegistryObject<CustomQuarterBlock> baldcypresskneelog = BLOCKS_REGISTRY.register("baldcypresskneelog",
			   () -> bigLog2(MapColor.WOOD, MapColor.COLOR_GRAY));
	   public static final RegistryObject<Block> planks_baldcypress = BLOCKS_REGISTRY.register("planks_baldcypress",
			   () -> planks(MapColor.WOOD));
	   // cypress
	   public static final RegistryObject<CustomLogBlock> log_cypress = BLOCKS_REGISTRY.register("log_cypress",
			   () -> log(MapColor.WOOD, MapColor.COLOR_GRAY, "block.extrabiomes.log_cypress.description"));
	   public static final RegistryObject<Block> planks_cypress = BLOCKS_REGISTRY.register("planks_cypress",
			   () -> planks(MapColor.WOOD));
	   // rainbow eucalyptus
	   public static final RegistryObject<CustomLogBlock> log_rainboweucalyptus = BLOCKS_REGISTRY.register("log_rainboweucalyptus",
			   () -> log(MapColor.WOOD, MapColor.COLOR_GRAY, ""));
	   public static final RegistryObject<CustomQuarterBlock> rainboweucalyptusquarter = BLOCKS_REGISTRY.register("rainboweucalyptusquarter",
			   () -> bigLog(MapColor.WOOD, MapColor.COLOR_GRAY));
	   public static final RegistryObject<CustomQuarterBlock> rainboweucalyptuskneelog = BLOCKS_REGISTRY.register("rainboweucalyptuskneelog",
			   () -> bigLog2(MapColor.WOOD, MapColor.COLOR_GRAY));
	   public static final RegistryObject<Block> planks_rainboweucalyptus = BLOCKS_REGISTRY.register("planks_rainboweucalyptus",
			   () -> planks(MapColor.WOOD));
	   
	   // sakura
	   public static final RegistryObject<MiniLogBlock> log_sakura = BLOCKS_REGISTRY.register("log_sakura",
			   () -> minilog(MapColor.WOOD, MapColor.COLOR_GRAY, "block.extrabiomes.log_sakura.description"));
	   public static final RegistryObject<Block> planks_sakura = BLOCKS_REGISTRY.register("planks_sakura",
			   () -> planks(MapColor.WOOD));
	   
	   // SAPLINGS
	   // autumn saplings
	   public static final RegistryObject<BushBlock> sapling_umber = BLOCKS_REGISTRY.register("sapling_umber", 
			   () -> sapling(new AutumnTreeGrower(ModConfiguredFeatures.UMBER_AUTUMN_TREE, ModConfiguredFeatures.FANCY_UMBER_AUTUMN_TREE)));
	   public static final RegistryObject<BushBlock> sapling_goldenrod = BLOCKS_REGISTRY.register("sapling_goldenrod", 
			   () -> sapling(new AutumnTreeGrower(ModConfiguredFeatures.GOLDENROD_AUTUMN_TREE, ModConfiguredFeatures.FANCY_GOLDENROD_AUTUMN_TREE)));
	   public static final RegistryObject<BushBlock> sapling_vermillion = BLOCKS_REGISTRY.register("sapling_vermillion", 
			   () -> sapling(new AutumnTreeGrower(ModConfiguredFeatures.VERMILLION_AUTUMN_TREE, ModConfiguredFeatures.FANCY_VERMILLION_AUTUMN_TREE)));
	   public static final RegistryObject<BushBlock> sapling_citrine = BLOCKS_REGISTRY.register("sapling_citrine", 
			   () -> sapling(new AutumnTreeGrower(ModConfiguredFeatures.CITRINE_AUTUMN_TREE, ModConfiguredFeatures.FANCY_CITRINE_AUTUMN_TREE)));
	   // japanese maple saplings
	   public static final RegistryObject<BushBlock> sapling_japanese_maple = BLOCKS_REGISTRY.register("sapling_japanese_maple",
			   () -> sapling(new SimpleTreeGrower(ModConfiguredFeatures.JAPANESE_MAPLE_TREE)));
	   // japanese maple shrub saplings
	   public static final RegistryObject<BushBlock> sapling_japanese_maple_shrub 
	   		= BLOCKS_REGISTRY.register("sapling_japanese_maple_shrub",
			   () -> sapling(new SimpleTreeGrower(ModConfiguredFeatures.JAPANESE_MAPLE_SHRUB)));
	   // fir saplings
	   public static final RegistryObject<BushBlock> sapling_fir = BLOCKS_REGISTRY.register("sapling_fir",
			   () -> sapling(new SimpleMegaTreeGrower(ModConfiguredFeatures.FIR_TREE, ModConfiguredFeatures.BIG_FIR_TREE)));

	   // redwood saplings
	   public static final RegistryObject<BushBlock> sapling_redwood = BLOCKS_REGISTRY.register("sapling_redwood",
			   () -> sapling(new SimpleMegaTreeGrower(null, ModConfiguredFeatures.REDWOOD_TREE)));

	   // acacia/black wattle saplings
	   public static final RegistryObject<BushBlock> sapling_acacia = BLOCKS_REGISTRY.register("sapling_acacia",
			   () -> sapling(new SimpleTreeGrower(ModConfiguredFeatures.ACACIA_TREE)));
	   // cypress saplings
	   public static final RegistryObject<BushBlock> sapling_cypress = BLOCKS_REGISTRY.register("sapling_cypress",
			   () -> sapling(new SimpleTreeGrower(ModConfiguredFeatures.CYPRESS_TREE)));

	   // bald cypress saplings
	   public static final RegistryObject<BushBlock> sapling_bald_cypress = BLOCKS_REGISTRY.register("sapling_bald_cypress",
			   () -> sapling(new SimpleMegaTreeGrower(null, ModConfiguredFeatures.BALD_CYPRESS_TREE)));

	// TODO - fill in correct AbstractTreeGrowers for all OakTreeGrower in saplings.
	   // rainbow eucalypus sapling
	   public static final RegistryObject<BushBlock> sapling_rainbow_eucalyptus = BLOCKS_REGISTRY.register("sapling_rainbow_eucalyptus",
			   () -> sapling(new OakTreeGrower()));
	   // sakura sapling
	   public static final RegistryObject<BushBlock> sapling_sakura = BLOCKS_REGISTRY.register("sapling_sakura",
			   () -> sapling(new OakTreeGrower()));
	   
	   // POTTED SAPLINGS
	   public static final RegistryObject<FlowerPotBlock> potted_sapling_umber = BLOCKS_REGISTRY.register("potted_sapling_umber",
			   () -> flowerpot(ModBlocks.sapling_umber));
	   public static final RegistryObject<FlowerPotBlock> potted_sapling_goldenrod = BLOCKS_REGISTRY.register("potted_sapling_goldenrod",
			   () -> flowerpot(ModBlocks.sapling_goldenrod));
	   public static final RegistryObject<FlowerPotBlock> potted_sapling_vermillion = BLOCKS_REGISTRY.register("potted_sapling_vermillion",
			   () -> flowerpot(ModBlocks.sapling_vermillion));
	   public static final RegistryObject<FlowerPotBlock> potted_sapling_citrine = BLOCKS_REGISTRY.register("potted_sapling_citrine",
			   () -> flowerpot(ModBlocks.sapling_citrine));
	   public static final RegistryObject<FlowerPotBlock> potted_sapling_japanese_maple = BLOCKS_REGISTRY.register("potted_sapling_japanese_maple",
			   () -> flowerpot(ModBlocks.sapling_japanese_maple));
	   public static final RegistryObject<FlowerPotBlock> potted_sapling_japanese_maple_shrub = BLOCKS_REGISTRY.register("potted_sapling_japanese_maple_shrub",
			   () -> flowerpot(ModBlocks.sapling_japanese_maple_shrub));
	   public static final RegistryObject<FlowerPotBlock> potted_sapling_fir = BLOCKS_REGISTRY.register("potted_sapling_fir",
			   () -> flowerpot(ModBlocks.sapling_fir));
	   public static final RegistryObject<FlowerPotBlock> potted_sapling_redwood = BLOCKS_REGISTRY.register("potted_sapling_redwood",
			   () -> flowerpot(ModBlocks.sapling_redwood));
	   public static final RegistryObject<FlowerPotBlock> potted_sapling_acacia = BLOCKS_REGISTRY.register("potted_sapling_acacia",
			   () -> flowerpot(ModBlocks.sapling_acacia));
	   public static final RegistryObject<FlowerPotBlock> potted_sapling_cypress = BLOCKS_REGISTRY.register("potted_sapling_cypress",
			   () -> flowerpot(ModBlocks.sapling_cypress));
	   public static final RegistryObject<FlowerPotBlock> potted_sapling_bald_cypress = BLOCKS_REGISTRY.register("potted_sapling_bald_cypress",
			   () -> flowerpot(ModBlocks.sapling_bald_cypress));
	   public static final RegistryObject<FlowerPotBlock> potted_sapling_rainbow_eucalyptus = BLOCKS_REGISTRY.register("potted_sapling_rainbow_eucalyptus",
			   () -> flowerpot(ModBlocks.sapling_rainbow_eucalyptus));
	   public static final RegistryObject<FlowerPotBlock> potted_sapling_sakura = BLOCKS_REGISTRY.register("potted_sapling_sakura",
			   () -> flowerpot(ModBlocks.sapling_sakura));
	   
	   // AESTHETIC BLOCKS
	   // stairs
	   public static final RegistryObject<StairBlock> stairs_autumn = BLOCKS_REGISTRY.register("stairs_autumn", 
			   () -> stairs(planks_autumn_wood));
	   public static final RegistryObject<StairBlock> stairs_japanesemaple = BLOCKS_REGISTRY.register("stairs_japanesemaple", 
			   () -> stairs(planks_japanese_maple));
	   public static final RegistryObject<StairBlock> stairs_fir = BLOCKS_REGISTRY.register("stairs_fir",
			   () -> stairs(planks_fir));
	   public static final RegistryObject<StairBlock> stairs_redwood = BLOCKS_REGISTRY.register("stairs_redwood",
			   () -> stairs(planks_redwood));
	   public static final RegistryObject<StairBlock> stairs_acacia = BLOCKS_REGISTRY.register("stairs_acacia",
			   () -> stairs(planks_acacia));
	   public static final RegistryObject<StairBlock> stairs_baldcypress = BLOCKS_REGISTRY.register("stairs_baldcypress",
			   () -> stairs(planks_baldcypress));
	   public static final RegistryObject<StairBlock> stairs_cypress = BLOCKS_REGISTRY.register("stairs_cypress",
			   () -> stairs(planks_cypress));
	   public static final RegistryObject<StairBlock> stairs_rainboweucalyptus = BLOCKS_REGISTRY.register("stairs_rainboweucalyptus",
			   () -> stairs(planks_rainboweucalyptus));
	   public static final RegistryObject<StairBlock> stairs_sakura = BLOCKS_REGISTRY.register("stairs_sakura",
			   () -> stairs(planks_sakura));

	   public static final RegistryObject<StairBlock> stairs_redcobble = BLOCKS_REGISTRY.register("stairs_redcobble",
			   () -> stairs(redcobble));
	   public static final RegistryObject<StairBlock> stairs_redrockbrick = BLOCKS_REGISTRY.register("stairs_redrockbrick",
			   () -> stairs(redrock_brick));
	   public static final RegistryObject<StairBlock> stairs_redrock = BLOCKS_REGISTRY.register("stairs_redrock",
			   () -> stairs(redrock));

	   // slabs
	   public static final RegistryObject<SlabBlock> slab_autumn = BLOCKS_REGISTRY.register("slab_autumn", 
			   () -> new SlabBlock(BlockBehaviour.Properties.copy(planks_autumn_wood.get())));
	   public static final RegistryObject<SlabBlock> slab_japanese_maple =  BLOCKS_REGISTRY.register("slab_japanese_maple", 
			   () -> new SlabBlock(BlockBehaviour.Properties.copy(planks_japanese_maple.get())));
	   public static final RegistryObject<SlabBlock> slab_fir = BLOCKS_REGISTRY.register("slab_fir",
			   () -> new SlabBlock(BlockBehaviour.Properties.copy(planks_fir.get())));
	   public static final RegistryObject<SlabBlock> slab_redwood = BLOCKS_REGISTRY.register("slab_redwood",
			   () -> new SlabBlock(BlockBehaviour.Properties.copy(planks_redwood.get())));
	   public static final RegistryObject<SlabBlock> slab_acacia = BLOCKS_REGISTRY.register("slab_acacia",
			   () -> new SlabBlock(BlockBehaviour.Properties.copy(planks_acacia.get())));
	   public static final RegistryObject<SlabBlock> slab_baldcypress = BLOCKS_REGISTRY.register("slab_baldcypress",
			   () -> new SlabBlock(BlockBehaviour.Properties.copy(planks_baldcypress.get())));
	   public static final RegistryObject<SlabBlock> slab_cypress = BLOCKS_REGISTRY.register("slab_cypress",
			   () -> new SlabBlock(BlockBehaviour.Properties.copy(planks_cypress.get())));
	   public static final RegistryObject<SlabBlock> slab_rainboweucalyptus = BLOCKS_REGISTRY.register("slab_rainboweucalyptus",
			   () -> new SlabBlock(BlockBehaviour.Properties.copy(planks_rainboweucalyptus.get())));
	   public static final RegistryObject<SlabBlock> slab_sakura = BLOCKS_REGISTRY.register("slab_sakura",
			   () -> new SlabBlock(BlockBehaviour.Properties.copy(planks_sakura.get())));
	   
	   public static final RegistryObject<SlabBlock> slab_redcobble =  BLOCKS_REGISTRY.register("slab_redcobble",
			   () -> new SlabBlock(BlockBehaviour.Properties.copy(redcobble.get())));
	   public static final RegistryObject<SlabBlock> slab_redrock =  BLOCKS_REGISTRY.register("slab_redrock",
			   () -> new SlabBlock(BlockBehaviour.Properties.copy(redrock.get())));
	   public static final RegistryObject<SlabBlock> slab_redrockbrick =  BLOCKS_REGISTRY.register("slab_redrockbrick",
			   () -> new SlabBlock(BlockBehaviour.Properties.copy(redrock_brick.get())));
	   
	   // doors
	   public static RegistryObject<DoorBlock> door_autumn = BLOCKS_REGISTRY.register("door_autumn",
			   () -> doors(ModBlocks.planks_autumn_wood.get().defaultMapColor(), BlockSetType.OAK));
	   public static RegistryObject<DoorBlock> door_japanesemaple = BLOCKS_REGISTRY.register("door_japanesemaple",
			   () -> doors(ModBlocks.planks_japanese_maple.get().defaultMapColor(), BlockSetType.OAK));
	   public static RegistryObject<DoorBlock> door_fir = BLOCKS_REGISTRY.register("door_fir",
			   () -> doors(ModBlocks.planks_fir.get().defaultMapColor(), BlockSetType.SPRUCE));
	   public static RegistryObject<DoorBlock> door_redwood = BLOCKS_REGISTRY.register("door_redwood",
			   () -> doors(ModBlocks.planks_redwood.get().defaultMapColor(), BlockSetType.SPRUCE));
	   public static RegistryObject<DoorBlock> door_acacia = BLOCKS_REGISTRY.register("door_acacia",
			   () -> doors(ModBlocks.planks_acacia.get().defaultMapColor(), BlockSetType.ACACIA));
	   public static RegistryObject<DoorBlock> door_baldcypress = BLOCKS_REGISTRY.register("door_baldcypress",
			   () -> doors(ModBlocks.planks_baldcypress.get().defaultMapColor(), BlockSetType.JUNGLE));
	   public static RegistryObject<DoorBlock> door_cypress = BLOCKS_REGISTRY.register("door_cypress",
			   () -> doors(ModBlocks.planks_cypress.get().defaultMapColor(), BlockSetType.SPRUCE));
	   public static RegistryObject<DoorBlock> door_rainboweucalyptus = BLOCKS_REGISTRY.register("door_rainboweucalyptus",
			   () -> doors(ModBlocks.planks_rainboweucalyptus.get().defaultMapColor(), BlockSetType.JUNGLE));
	   public static RegistryObject<DoorBlock> door_sakura = BLOCKS_REGISTRY.register("door_sakura",
			   () -> doors(ModBlocks.planks_sakura.get().defaultMapColor(), BlockSetType.CHERRY));
	   
	   // fences
	   public static final RegistryObject<FenceBlock> fence_autumn = BLOCKS_REGISTRY.register("fence_autumn", 
	            () -> fences(ModBlocks.planks_autumn_wood));
	   public static final RegistryObject<FenceBlock> fence_japanesemaple = BLOCKS_REGISTRY.register("fence_japanesemaple",
	            () -> fences(ModBlocks.planks_japanese_maple));
	   public static final RegistryObject<FenceBlock> fence_fir = BLOCKS_REGISTRY.register("fence_fir", 
	            () -> fences(ModBlocks.planks_fir));
	   public static final RegistryObject<FenceBlock> fence_redwood = BLOCKS_REGISTRY.register("fence_redwood", 
	            () -> fences(ModBlocks.planks_redwood));
	   public static final RegistryObject<FenceBlock> fence_acacia = BLOCKS_REGISTRY.register("fence_acacia", 
	            () -> fences(ModBlocks.planks_acacia));
	   public static final RegistryObject<FenceBlock> fence_baldcypress = BLOCKS_REGISTRY.register("fence_baldcypress", 
	            () -> fences(ModBlocks.planks_baldcypress));
	   public static final RegistryObject<FenceBlock> fence_cypress = BLOCKS_REGISTRY.register("fence_cypress", 
	            () -> fences(ModBlocks.planks_cypress));
	   public static final RegistryObject<FenceBlock> fence_rainboweucalyptus = BLOCKS_REGISTRY.register("fence_rainboweucalyptus", 
	            () -> fences(ModBlocks.planks_rainboweucalyptus));
	   public static final RegistryObject<FenceBlock> fence_sakura = BLOCKS_REGISTRY.register("fence_sakura", 
	            () -> fences(ModBlocks.planks_sakura));
		   
	   // walls
	   public static final RegistryObject<WallBlock> wall_redcobble = BLOCKS_REGISTRY.register("wall_redcobble",
			   () -> walls(redcobble));
	   public static final RegistryObject<WallBlock> wall_redrockbrick = BLOCKS_REGISTRY.register("wall_redrockbrick",
			   () -> walls(redrock_brick));
	   
	   // fence gates
	   public static final RegistryObject<FenceGateBlock> gate_autumn = BLOCKS_REGISTRY.register("gate_autumn",
			   () -> gates(ModBlocks.planks_autumn_wood.get().defaultMapColor(), WoodType.OAK));
	   public static final RegistryObject<FenceGateBlock> gate_japanesemaple = BLOCKS_REGISTRY.register("gate_japanesemaple",
			   () -> gates(ModBlocks.planks_japanese_maple.get().defaultMapColor(), WoodType.OAK));
	   public static final RegistryObject<FenceGateBlock> gate_fir = BLOCKS_REGISTRY.register("gate_fir",
			   () -> gates(ModBlocks.planks_fir.get().defaultMapColor(), WoodType.SPRUCE));
	   public static final RegistryObject<FenceGateBlock> gate_redwood = BLOCKS_REGISTRY.register("gate_redwood",
			   () -> gates(ModBlocks.planks_redwood.get().defaultMapColor(), WoodType.SPRUCE));
	   public static final RegistryObject<FenceGateBlock> gate_acacia = BLOCKS_REGISTRY.register("gate_acacia",
			   () -> gates(ModBlocks.planks_acacia.get().defaultMapColor(), WoodType.ACACIA));
	   public static final RegistryObject<FenceGateBlock> gate_baldcypress = BLOCKS_REGISTRY.register("gate_baldcypress",
			   () -> gates(ModBlocks.planks_baldcypress.get().defaultMapColor(), WoodType.JUNGLE));
	   public static final RegistryObject<FenceGateBlock> gate_cypress = BLOCKS_REGISTRY.register("gate_cypress",
			   () -> gates(ModBlocks.planks_cypress.get().defaultMapColor(), WoodType.SPRUCE));
	   public static final RegistryObject<FenceGateBlock> gate_rainboweucalyptus = BLOCKS_REGISTRY.register("gate_rainboweucalyptus",
			   () -> gates(ModBlocks.planks_rainboweucalyptus.get().defaultMapColor(), WoodType.JUNGLE));
	   public static final RegistryObject<FenceGateBlock> gate_sakura = BLOCKS_REGISTRY.register("gate_sakura",
			   () -> gates(ModBlocks.planks_sakura.get().defaultMapColor(), WoodType.CHERRY));
	   
	   // buttons
	   public static final RegistryObject<ButtonBlock> button_autumn =  BLOCKS_REGISTRY.register("button_autumn",
			   () -> buttons(BlockSetType.OAK, 30, true));
	   public static final RegistryObject<ButtonBlock> button_japanesemaple =  BLOCKS_REGISTRY.register("button_japanesemaple",
			   () -> buttons(BlockSetType.OAK, 30, true));
	   public static final RegistryObject<ButtonBlock> button_fir =  BLOCKS_REGISTRY.register("button_fir",
			   () -> buttons(BlockSetType.SPRUCE, 30, true));
	   public static final RegistryObject<ButtonBlock> button_redwood =  BLOCKS_REGISTRY.register("button_redwood",
			   () -> buttons(BlockSetType.SPRUCE, 30, true));
	   public static final RegistryObject<ButtonBlock> button_acacia =  BLOCKS_REGISTRY.register("button_acacia",
			   () -> buttons(BlockSetType.ACACIA, 30, true));
	   public static final RegistryObject<ButtonBlock> button_baldcypress =  BLOCKS_REGISTRY.register("button_baldcypress",
			   () -> buttons(BlockSetType.JUNGLE, 30, true));
	   public static final RegistryObject<ButtonBlock> button_cypress =  BLOCKS_REGISTRY.register("button_cypress",
			   () -> buttons(BlockSetType.SPRUCE, 30, true));
	   public static final RegistryObject<ButtonBlock> button_rainboweucalyptus =  BLOCKS_REGISTRY.register("button_rainboweucalyptus",
			   () -> buttons(BlockSetType.JUNGLE, 30, true));
	   public static final RegistryObject<ButtonBlock> button_sakura =  BLOCKS_REGISTRY.register("button_sakura",
			   () -> buttons(BlockSetType.CHERRY, 30, true));
	   	   
	   // pressure plates
	   public static final RegistryObject<PressurePlateBlock> pressureplate_autumn = BLOCKS_REGISTRY.register("pressureplate_autumn",
			   () -> pplates(PressurePlateBlock.Sensitivity.EVERYTHING, ModBlocks.planks_autumn_wood.get().defaultMapColor(),
					   		BlockSetType.OAK));
	   public static final RegistryObject<PressurePlateBlock> pressureplate_japanesemaple = BLOCKS_REGISTRY.register("pressureplate_japanesemaple",
			   () -> pplates(PressurePlateBlock.Sensitivity.EVERYTHING, ModBlocks.planks_japanese_maple.get().defaultMapColor(),
					   		BlockSetType.OAK));
	   public static final RegistryObject<PressurePlateBlock> pressureplate_fir = BLOCKS_REGISTRY.register("pressureplate_fir",
			   () -> pplates(PressurePlateBlock.Sensitivity.EVERYTHING, ModBlocks.planks_fir.get().defaultMapColor(),
					   		BlockSetType.SPRUCE));
	   public static final RegistryObject<PressurePlateBlock> pressureplate_redwood = BLOCKS_REGISTRY.register("pressureplate_redwood",
			   () -> pplates(PressurePlateBlock.Sensitivity.EVERYTHING, ModBlocks.planks_redwood.get().defaultMapColor(),
					   		BlockSetType.SPRUCE));
	   public static final RegistryObject<PressurePlateBlock> pressureplate_acacia = BLOCKS_REGISTRY.register("pressureplate_acacia",
			   () -> pplates(PressurePlateBlock.Sensitivity.EVERYTHING, ModBlocks.planks_acacia.get().defaultMapColor(),
					   		BlockSetType.ACACIA));
	   public static final RegistryObject<PressurePlateBlock> pressureplate_baldcypress = BLOCKS_REGISTRY.register("pressureplate_baldcypress",
			   () -> pplates(PressurePlateBlock.Sensitivity.EVERYTHING, ModBlocks.planks_baldcypress.get().defaultMapColor(),
					   		BlockSetType.JUNGLE));
	   public static final RegistryObject<PressurePlateBlock> pressureplate_cypress = BLOCKS_REGISTRY.register("pressureplate_cypress",
			   () -> pplates(PressurePlateBlock.Sensitivity.EVERYTHING, ModBlocks.planks_cypress.get().defaultMapColor(),
					   		BlockSetType.SPRUCE));
	   public static final RegistryObject<PressurePlateBlock> pressureplate_rainboweucalyptus = BLOCKS_REGISTRY.register("pressureplate_rainboweucalyptus",
			   () -> pplates(PressurePlateBlock.Sensitivity.EVERYTHING, ModBlocks.planks_rainboweucalyptus.get().defaultMapColor(),
					   		BlockSetType.JUNGLE));
	   public static final RegistryObject<PressurePlateBlock> pressureplate_sakura = BLOCKS_REGISTRY.register("pressureplate_sakura",
			   () -> pplates(PressurePlateBlock.Sensitivity.EVERYTHING, ModBlocks.planks_sakura.get().defaultMapColor(),
					   		BlockSetType.CHERRY));
	   
	   // FLOWERS
	   public static final RegistryObject<BushBlock> flower_redrover = BLOCKS_REGISTRY.register("flower_redrover", 
			   () -> flowers(MobEffects.SATURATION, 30, "block.extrabiomes.flower_redrover.description"));
	   public static final RegistryObject<BushBlock> flower_hydrangea = BLOCKS_REGISTRY.register("flower_hydrangea",
			   () -> flowers(MobEffects.HARM, 2, "block.extrabiomes.flower_hydrangea.description"));
	   public static final RegistryObject<BushBlock> flower_buttercup = BLOCKS_REGISTRY.register("flower_buttercup", 
			   () -> flowers(MobEffects.POISON, 5, "block.extrabiomes.flower_buttercup.description"));
	   public static final RegistryObject<BushBlock> flower_lavender = BLOCKS_REGISTRY.register("flower_lavender",
			   () -> flowers(MobEffects.SATURATION, 5, "block.extrabiomes.flower_lavender.description"));
	   public static final RegistryObject<BushBlock> flower_tiny_cactus = BLOCKS_REGISTRY.register("flower_tiny_cactus",
			   () -> flowers(MobEffects.ABSORPTION, 0, "block.extrabiomes.flower_tiny_cactus.description"));
	   public static final RegistryObject<BushBlock> flower_toadstool = BLOCKS_REGISTRY.register("flower_toadstool",
			   () -> flowers(MobEffects.POISON, 10, ""));
	   public static final RegistryObject<BushBlock> flower_calla_white = BLOCKS_REGISTRY.register("flower_calla_white",
			   () -> flowers(MobEffects.POISON, 12, "block.extrabiomes.flower_calla_white.description"));
	   public static final RegistryObject<BushBlock> flower_allium = BLOCKS_REGISTRY.register("flower_allium",
			   () -> flowers(MobEffects.FIRE_RESISTANCE, 4, "block.extrabiomes.flower_allium.description"));
	   public static final RegistryObject<BushBlock> flower_amaryllis_pink = BLOCKS_REGISTRY.register("flower_amaryllis_pink",
			   () -> flowers(MobEffects.REGENERATION, 4, ""));
	   public static final RegistryObject<BushBlock> flower_amaryllis_red = BLOCKS_REGISTRY.register("flower_amaryllis_red",
			   () -> flowers(MobEffects.REGENERATION, 4, ""));
	   public static final RegistryObject<BushBlock> flower_amaryllis_white = BLOCKS_REGISTRY.register("flower_amaryllis_white",
			   () -> flowers(MobEffects.HEALTH_BOOST, 10, ""));
	   public static final RegistryObject<BushBlock> flower_bachelors_button= BLOCKS_REGISTRY.register("flower_bachelors_button",
			   () -> flowers(MobEffects.SATURATION, 5, "block.extrabiomes.flower_bachelors_button.description"));
	   public static final RegistryObject<BushBlock> flower_bells_of_ireland =  BLOCKS_REGISTRY.register("flower_bells_of_ireland",
			   () -> flowers(MobEffects.LUCK, 10, ""));
	   
	   public static final RegistryObject<BushBlock> flower_bluebell = BLOCKS_REGISTRY.register("flower_bluebell",
			   () -> flowers(MobEffects.WEAKNESS, 4, "block.extrabiomes.flower_bluebell.description"));
	   public static final RegistryObject<BushBlock> flower_calla_black  = BLOCKS_REGISTRY.register("flower_calla_black",
			   () -> flowers(MobEffects.POISON, 12, ""));
	   public static final RegistryObject<BushBlock> flower_daisy = BLOCKS_REGISTRY.register("flower_daisy",
			   () -> flowers(MobEffects.HEALTH_BOOST, 12, ""));
	   public static final RegistryObject<BushBlock> flower_dandelion = BLOCKS_REGISTRY.register("flower_dandelion",
			   () -> flowers(MobEffects.SATURATION, 5, ""));
	   public static final RegistryObject<BushBlock> flower_gardenia  = BLOCKS_REGISTRY.register("flower_gardenia",
			   () -> flowers(MobEffects.MOVEMENT_SLOWDOWN, 5, ""));
	   public static final RegistryObject<BushBlock> flower_gerbera_orange = BLOCKS_REGISTRY.register("flower_gerbera_orange",
			   () -> flowers(MobEffects.WEAKNESS, 5, ""));
	   public static final RegistryObject<BushBlock> flower_gerbera_pink = BLOCKS_REGISTRY.register("flower_gerbera_pink",
			   () -> flowers(MobEffects.WEAKNESS, 5, ""));
	   public static final RegistryObject<BushBlock> flower_gerbera_red = BLOCKS_REGISTRY.register("flower_gerbera_red",
			   () -> flowers(MobEffects.WEAKNESS, 5, ""));
	   public static final RegistryObject<BushBlock> flower_gerbera_yellow = BLOCKS_REGISTRY.register("flower_gerbera_yellow",
			   () -> flowers(MobEffects.WEAKNESS, 5, ""));
			   
	   public static final RegistryObject<BushBlock> flower_oriental_pink_lily  = BLOCKS_REGISTRY.register("flower_oriental_pink_lily",
			   () -> flowers(MobEffects.SATURATION, 5, ""));
	   public static final RegistryObject<BushBlock> flower_lily  = BLOCKS_REGISTRY.register("flower_lily",
			   () -> flowers(MobEffects.SATURATION, 5, ""));
	   public static final RegistryObject<BushBlock> flower_iris_blue = BLOCKS_REGISTRY.register("flower_iris_blue",
			   () -> flowers(MobEffects.WEAKNESS, 4, ""));
	   public static final RegistryObject<BushBlock> flower_iris_purple = BLOCKS_REGISTRY.register("flower_iris_purple",
			   () -> flowers(MobEffects.WEAKNESS, 4, "block.extrabiomes.flower_iris_purple.description"));
	   public static final RegistryObject<BushBlock> flower_marsh_marigold  = BLOCKS_REGISTRY.register("flower_marsh_marigold",
			   () -> flowers(MobEffects.SATURATION, 4, ""));
	   public static final RegistryObject<BushBlock> flower_pansy  = BLOCKS_REGISTRY.register("flower_pansy",
			   () -> flowers(MobEffects.SATURATION, 1, ""));
	   public static final RegistryObject<BushBlock> flower_poppy  = BLOCKS_REGISTRY.register("flower_poppy",
			   () -> flowers(MobEffects.CONFUSION, 10, ""));
	   public static final RegistryObject<BushBlock> flower_blue_poppy  = BLOCKS_REGISTRY.register("flower_blue_poppy",
			   () -> flowers(MobEffects.CONFUSION, 10, "block.extrabiomes.flower_blue_poppy.description"));
	   public static final RegistryObject<BushBlock> flower_snapdragon = BLOCKS_REGISTRY.register("flower_snapdragon",
			   () -> flowers(MobEffects.SATURATION, 1, ""));
	   public static final RegistryObject<BushBlock> flower_tulip = BLOCKS_REGISTRY.register("flower_tulip",
			   () -> flowers(MobEffects.WEAKNESS, 4, ""));
	   public static final RegistryObject<BushBlock> flower_violet = BLOCKS_REGISTRY.register("flower_violet",
			   () -> flowers(MobEffects.SATURATION, 1, ""));
	   public static final RegistryObject<BushBlock> flower_yarrow = BLOCKS_REGISTRY.register("flower_yarrow",
			   () -> flowers(MobEffects.REGENERATION, 4, ""));
	   public static final RegistryObject<BushBlock> flower_belladonna = BLOCKS_REGISTRY.register("flower_belladonna",
			   () -> flowers(MobEffects.BLINDNESS, 4, ""));
		   
	   
	   // POTTED FLOWERS
	   public static final RegistryObject<FlowerPotBlock> potted_redrover = BLOCKS_REGISTRY.register("potted_redrover",
			   () -> flowerpot(ModBlocks.flower_redrover));
	   public static final RegistryObject<FlowerPotBlock> potted_hydrangea = BLOCKS_REGISTRY.register("potted_hydrangea",
			   () -> flowerpot(ModBlocks.flower_hydrangea));
	   public static final RegistryObject<FlowerPotBlock> potted_buttercup = BLOCKS_REGISTRY.register("potted_buttercup",
			   () -> flowerpot(ModBlocks.flower_buttercup));
	   public static final RegistryObject<FlowerPotBlock> potted_lavender = BLOCKS_REGISTRY.register("potted_lavender",
			   () -> flowerpot(ModBlocks.flower_lavender));
	   public static final RegistryObject<FlowerPotBlock> potted_tiny_cactus = BLOCKS_REGISTRY.register("potted_tiny_cactus",
			   () -> flowerpot(ModBlocks.flower_tiny_cactus));
	   public static final RegistryObject<FlowerPotBlock> potted_toadstool = BLOCKS_REGISTRY.register("potted_toadstool",
			   () -> flowerpot(ModBlocks.flower_toadstool));
	   public static final RegistryObject<FlowerPotBlock> potted_calla_white = BLOCKS_REGISTRY.register("potted_calla_white",
			   () -> flowerpot(ModBlocks.flower_calla_white));
	   public static final RegistryObject<FlowerPotBlock> potted_allium = BLOCKS_REGISTRY.register("potted_allium",
			   () -> flowerpot(ModBlocks.flower_allium));
	   public static final RegistryObject<FlowerPotBlock> potted_amaryllis_pink = BLOCKS_REGISTRY.register("potted_amaryllis_pink",
			   () -> flowerpot(ModBlocks.flower_amaryllis_pink));
	   public static final RegistryObject<FlowerPotBlock> potted_amaryllis_red = BLOCKS_REGISTRY.register("potted_amaryllis_red",
			   () -> flowerpot(ModBlocks.flower_amaryllis_red));
	   public static final RegistryObject<FlowerPotBlock> potted_amaryllis_white = BLOCKS_REGISTRY.register("potted_amaryllis_white",
			   () -> flowerpot(ModBlocks.flower_amaryllis_white));
	   public static final RegistryObject<FlowerPotBlock> potted_bachelors_button = BLOCKS_REGISTRY.register("potted_bachelors_button",
			   () -> flowerpot(ModBlocks.flower_bachelors_button));
	   public static final RegistryObject<FlowerPotBlock> potted_bells_of_ireland = BLOCKS_REGISTRY.register("potted_bells_of_ireland",
			   () -> flowerpot(ModBlocks.flower_bells_of_ireland));

	   public static final RegistryObject<FlowerPotBlock> potted_bluebell = BLOCKS_REGISTRY.register("potted_bluebell",
			   () -> flowerpot(ModBlocks.flower_bluebell));
	   public static final RegistryObject<FlowerPotBlock> potted_calla_black = BLOCKS_REGISTRY.register("potted_calla_black",
			   () -> flowerpot(ModBlocks.flower_calla_black));
	   public static final RegistryObject<FlowerPotBlock> potted_daisy = BLOCKS_REGISTRY.register("potted_daisy",
			   () -> flowerpot(ModBlocks.flower_daisy));
	   public static final RegistryObject<FlowerPotBlock> potted_dandelion = BLOCKS_REGISTRY.register("potted_dandelion",
			   () -> flowerpot(ModBlocks.flower_dandelion));
	   public static final RegistryObject<FlowerPotBlock> potted_gardenia = BLOCKS_REGISTRY.register("potted_gardenia",
			   () -> flowerpot(ModBlocks.flower_gardenia));
	   public static final RegistryObject<FlowerPotBlock> potted_gerbera_orange  = BLOCKS_REGISTRY.register("potted_gerbera_orange",
			   () -> flowerpot(ModBlocks.flower_gerbera_orange));
	   public static final RegistryObject<FlowerPotBlock> potted_gerbera_pink = BLOCKS_REGISTRY.register("potted_gerbera_pink",
			   () -> flowerpot(ModBlocks.flower_gerbera_pink));
	   public static final RegistryObject<FlowerPotBlock> potted_gerbera_red = BLOCKS_REGISTRY.register("potted_gerbera_red",
			   () -> flowerpot(ModBlocks.flower_gerbera_red));
	   public static final RegistryObject<FlowerPotBlock> potted_gerbera_yellow = BLOCKS_REGISTRY.register("potted_gerbera_yellow",
			   () -> flowerpot(ModBlocks.flower_gerbera_yellow));

	   public static final RegistryObject<FlowerPotBlock> potted_oriental_pink_lily  = BLOCKS_REGISTRY.register("potted_oriental_pink_lily",
			   () -> flowerpot(ModBlocks.flower_oriental_pink_lily));
	   public static final RegistryObject<FlowerPotBlock> potted_lily  = BLOCKS_REGISTRY.register("potted_lily",
			   () -> flowerpot(ModBlocks.flower_lily));
	   public static final RegistryObject<FlowerPotBlock> potted_iris_blue = BLOCKS_REGISTRY.register("potted_iris_blue",
			   () -> flowerpot(ModBlocks.flower_iris_blue));
	   public static final RegistryObject<FlowerPotBlock> potted_iris_purple = BLOCKS_REGISTRY.register("potted_iris_purple",
			   () -> flowerpot(ModBlocks.flower_iris_purple));
	   public static final RegistryObject<FlowerPotBlock> potted_marsh_marigold  = BLOCKS_REGISTRY.register("potted_marsh_marigold",
			   () -> flowerpot(ModBlocks.flower_marsh_marigold));
	   public static final RegistryObject<FlowerPotBlock> potted_pansy  = BLOCKS_REGISTRY.register("potted_pansy",
			   () -> flowerpot(ModBlocks.flower_pansy));
	   public static final RegistryObject<FlowerPotBlock> potted_poppy  = BLOCKS_REGISTRY.register("potted_poppy",
			   () -> flowerpot(ModBlocks.flower_poppy));
	   public static final RegistryObject<FlowerPotBlock> potted_blue_poppy  = BLOCKS_REGISTRY.register("potted_blue_poppy",
			   () -> flowerpot(ModBlocks.flower_blue_poppy));
	   public static final RegistryObject<FlowerPotBlock> potted_snapdragon = BLOCKS_REGISTRY.register("potted_snapdragon",
			   () -> flowerpot(ModBlocks.flower_snapdragon));
	   public static final RegistryObject<FlowerPotBlock> potted_tulip = BLOCKS_REGISTRY.register("potted_tulip",
			   () -> flowerpot(ModBlocks.flower_tulip));
	   public static final RegistryObject<FlowerPotBlock> potted_violet = BLOCKS_REGISTRY.register("potted_violet",
			   () -> flowerpot(ModBlocks.flower_violet));
	   public static final RegistryObject<FlowerPotBlock> potted_yarrow = BLOCKS_REGISTRY.register("potted_yarrow",
			   () -> flowerpot(ModBlocks.flower_yarrow));
	   public static final RegistryObject<FlowerPotBlock> potted_belladonna = BLOCKS_REGISTRY.register("potted_belladonna",
			   () -> flowerpot(ModBlocks.flower_belladonna));
	   
	   
	   // OTHER PLANTS
	   public static final RegistryObject<BushBlock> cattail = BLOCKS_REGISTRY.register("cattail",
			   () -> new CattailBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).replaceable()
					   .noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)
					   .ignitedByLava().pushReaction(PushReaction.DESTROY)));
	   public static final RegistryObject<BushBlock> flower_autumn_shrub = BLOCKS_REGISTRY.register("flower_autumn_shrub",
			   () -> new BushBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).replaceable().noCollission()
					   .instabreak().sound(SoundType.GRASS).ignitedByLava().pushReaction(PushReaction.DESTROY)));
	   public static final RegistryObject<BushBlock> brown_grass_tall = BLOCKS_REGISTRY.register("brown_grass_tall",
			   () -> new CattailBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).replaceable()
					   .noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)
					   .ignitedByLava().pushReaction(PushReaction.DESTROY)));
	   public static final RegistryObject<BushBlock> brown_grass_short = BLOCKS_REGISTRY.register("brown_grass_short",
			   () -> new CattailBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).replaceable()
					   .noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)
					   .ignitedByLava().pushReaction(PushReaction.DESTROY)));
	   public static final RegistryObject<BushBlock> dead_grass_short = BLOCKS_REGISTRY.register("dead_grass_short",
			   () -> new CattailBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).replaceable()
					   .noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)
					   .ignitedByLava().pushReaction(PushReaction.DESTROY)));
	   public static final RegistryObject<BushBlock> dead_grass_tall = BLOCKS_REGISTRY.register("dead_grass_tall",
			   () -> new CattailBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).replaceable()
					   .noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)
					   .ignitedByLava().pushReaction(PushReaction.DESTROY)));
	   public static final RegistryObject<BushBlock> dead_grass_yellow = BLOCKS_REGISTRY.register("dead_grass_yellow",
			   () -> new CattailBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).replaceable()
					   .noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)
					   .ignitedByLava().pushReaction(PushReaction.DESTROY)));
			   
	   // vines
	   public static final RegistryObject<VineBlock> vine_gloriosa = BLOCKS_REGISTRY.register("vine_gloriosa",
			   () -> new VineBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).replaceable().noCollission().randomTicks()
					   .strength(0.2F).sound(SoundType.VINE).ignitedByLava().pushReaction(PushReaction.DESTROY)));
	   public static final RegistryObject<VineBlock> vine_spanish_moss = BLOCKS_REGISTRY.register("vine_spanish_moss",
			   () -> new VineBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).replaceable().noCollission().randomTicks()
					   .strength(0.2F).sound(SoundType.VINE).ignitedByLava().pushReaction(PushReaction.DESTROY)));

	   // OBJECTS?
	   public static final RegistryObject<LeafPileBlock> leafpile = BLOCKS_REGISTRY.register("leafpile", 
			   () -> new LeafPileBlock(BlockBehaviour.Properties.of().mapColor(MapColor.NONE).replaceable()
					   .noCollission().instabreak().sound(SoundType.GRASS).ignitedByLava()
					   .pushReaction(PushReaction.DESTROY)));
	   
	   // CROPS
	   public static final RegistryObject<StrawberryBlock> crop_strawberry = BLOCKS_REGISTRY.register("crop_strawberry", 
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
	    * @return a new SaplingBlock object.
	    */
	   private static SaplingBlock sapling(AbstractTreeGrower pTreeGrower)
	   {
		   return new SaplingBlock(pTreeGrower, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT)
				   .noCollission().randomTicks().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY));
	   }
	   
	   /**
	    * make a new leaf block.
	    * @param pType - SoundType for walking on leaves. Usually GRASS.
	    * @return a new LeavesBlock object.
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
	    * @return a new CustomLogBlock object.
	    */
	   private static CustomLogBlock log(MapColor pTopMapColor, MapColor pSideMapColor, String tooltip) 
	   {
		   return new CustomLogBlock(BlockBehaviour.Properties.of().mapColor((p_152624_) -> {
		         return p_152624_.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? pTopMapColor : pSideMapColor;
		      }).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava(),
				   tooltip);
	   }
	
	   /**
	    * Make a new minilog block.
	    * @param pTopMapColor - MapColor for top.
	    * @param pSideMapColor - MapColor for side.
	    * @returns a new CustomLogBlock object.
	    */
	   private static MiniLogBlock minilog(MapColor pTopMapColor, MapColor pSideMapColor, String tooltip) 
	   {
		   return new MiniLogBlock(BlockBehaviour.Properties.of().mapColor(pTopMapColor).noOcclusion()
				   .instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava(),
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
	    * make a new knee-log block
	    */
	   private static CustomQuarterBlock bigLog2(MapColor pTopMapColor, MapColor pSideMapColor)
	   {
		   final String tooltip  = "extrabiomes.elbow.crafting";
		   
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
