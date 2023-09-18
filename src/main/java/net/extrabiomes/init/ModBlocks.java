package net.extrabiomes.init;

import net.extrabiomes.ExtrabiomesXS;
import net.extrabiomes.content.CattailBlock;
import net.extrabiomes.content.CustomFlowerBlock;
import net.extrabiomes.content.CustomLogBlock;
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
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
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

	   // LOGS, WOOD & PLANKS
	   // autumn tree logs & wood.
	   public static final RegistryObject<CustomLogBlock> log_autumn = BLOCKS.register("log_autumn", 
			   () -> log(MapColor.COLOR_YELLOW, MapColor.PODZOL));
	   public static final RegistryObject<Block> planks_autumn_wood = BLOCKS.register("planks_autumn_wood",
			   () -> planks(MapColor.WOOD));
	   // japanese maple
	   public static final RegistryObject<CustomLogBlock> log_japanese_maple = BLOCKS.register("log_japanese_maple", 
			   () -> log(MapColor.COLOR_PINK, MapColor.CRIMSON_NYLIUM));
	   public static final RegistryObject<Block> planks_japanese_maple = BLOCKS.register("planks_japanese_maple",
			   () -> planks(MapColor.COLOR_PINK));
	   
	   // SAPLINGS
	   // autumn saplings
	   public static final RegistryObject<SaplingBlock> sapling_umber = BLOCKS.register("sapling_umber", 
			   () -> sapling(new AutumnTreeGrower(ModFeatures.UMBER_AUTUMN_TREE, ModFeatures.FANCY_UMBER_AUTUMN_TREE)));
	   public static final RegistryObject<SaplingBlock> sapling_goldenrod = BLOCKS.register("sapling_goldenrod", 
			   () -> sapling(new AutumnTreeGrower(ModFeatures.GOLDENROD_AUTUMN_TREE, ModFeatures.FANCY_GOLDENROD_AUTUMN_TREE)));
	   public static final RegistryObject<SaplingBlock> sapling_vermillion = BLOCKS.register("sapling_vermillion", 
			   () -> sapling(new AutumnTreeGrower(ModFeatures.VERMILLION_AUTUMN_TREE, ModFeatures.FANCY_VERMILLION_AUTUMN_TREE)));
	   public static final RegistryObject<SaplingBlock> sapling_citrine = BLOCKS.register("sapling_citrine", 
			   () -> sapling(new AutumnTreeGrower(ModFeatures.CITRINE_AUTUMN_TREE, ModFeatures.FANCY_CITRINE_AUTUMN_TREE)));
	   // japanese maple saplings
	   public static final RegistryObject<SaplingBlock> sapling_japanese_maple = BLOCKS.register("sapling_japanese_maple",
			   () -> sapling(new JapaneseMapleGrower()));
			   
	   // AESTHETIC BLOCKS
	   // stairs
	   public static final RegistryObject<StairBlock> stairs_autumn = BLOCKS.register("stairs_autumn", 
			   () -> stairs(planks_autumn_wood));
	   public static final RegistryObject<StairBlock> stairs_japanesemaple = BLOCKS.register("stairs_japanesemaple", 
			   () -> stairs(planks_japanese_maple));
	   
	   // slabs
	   public static final RegistryObject<SlabBlock> slab_autumn = BLOCKS.register("slab_autumn", 
			   () -> new SlabBlock(BlockBehaviour.Properties.copy(planks_autumn_wood.get())));
	   public static final RegistryObject<SlabBlock> slab_japanese_maple =  BLOCKS.register("slab_japanese_maple", 
			   () -> new SlabBlock(BlockBehaviour.Properties.copy(planks_japanese_maple.get())));

	   // doors
	   public static RegistryObject<DoorBlock> door_autumn = BLOCKS.register("door_autumn",
			   () -> doors(ModBlocks.planks_autumn_wood.get().defaultMapColor(), BlockSetType.OAK));
	   public static RegistryObject<DoorBlock> door_japanesemaple = BLOCKS.register("door_japanesemaple",
			   () -> doors(ModBlocks.planks_japanese_maple.get().defaultMapColor(), BlockSetType.OAK));
	   
	   // fences
	   public static final RegistryObject<FenceBlock> fence_autumn = BLOCKS.register("fence_autumn", 
	            () -> fences(ModBlocks.planks_autumn_wood));
	   public static final RegistryObject<FenceBlock> fence_japanesemaple = BLOCKS.register("fence_japanesemaple",
	            () -> fences(ModBlocks.planks_japanese_maple));
		   
	   // fence gates
	   public static final RegistryObject<FenceGateBlock> gate_autumn = BLOCKS.register("gate_autumn",
			   () -> gates(ModBlocks.planks_autumn_wood.get().defaultMapColor(), WoodType.OAK));
	   public static final RegistryObject<FenceGateBlock> gate_japanesemaple = BLOCKS.register("gate_japanesemaple",
			   () -> gates(ModBlocks.planks_japanese_maple.get().defaultMapColor(), WoodType.OAK));
	   
	   // buttons
	   public static final RegistryObject<ButtonBlock> button_autumn =  BLOCKS.register("button_autumn",
			   () -> buttons(BlockSetType.OAK, 30, true));
	   public static final RegistryObject<ButtonBlock> button_japanesemaple =  BLOCKS.register("button_japanesemaple",
			   () -> buttons(BlockSetType.OAK, 30, true));
	   	   
	   // pressure plates
	   public static final RegistryObject<PressurePlateBlock> pressureplate_autumn = BLOCKS.register("pressureplate_autumn",
			   () -> pplates(PressurePlateBlock.Sensitivity.EVERYTHING, ModBlocks.planks_autumn_wood.get().defaultMapColor(),
					   		BlockSetType.OAK));
	   public static final RegistryObject<PressurePlateBlock> pressureplate_japanesemaple = BLOCKS.register("pressureplate_japanesemaple",
			   () -> pplates(PressurePlateBlock.Sensitivity.EVERYTHING, ModBlocks.planks_japanese_maple.get().defaultMapColor(),
					   		BlockSetType.OAK));
	   
	   // FLOWERS
	   public static final RegistryObject<CustomFlowerBlock> flower_redrover = BLOCKS.register("flower_redrover", 
			   () -> flowers(MobEffects.SATURATION, 30, "block.extrabiomes.flower_redrover.description"));
	   public static final RegistryObject<CustomFlowerBlock> flower_hydrangea = BLOCKS.register("flower_hydrangea",
			   () -> flowers(MobEffects.HARM, 2, "block.extrabiomes.flower_hydrangea.description"));
	   public static final RegistryObject<CustomFlowerBlock> flower_buttercup = BLOCKS.register("flower_buttercup", 
			   () -> flowers(MobEffects.POISON, 5, "block.extrabiomes.flower_buttercup.description"));
	   public static final RegistryObject<CustomFlowerBlock> flower_lavender = BLOCKS.register("flower_lavender",
			   () -> flowers(MobEffects.SATURATION, 5, "block.extrabiomes.flower_lavender.description"));
	   public static final RegistryObject<CustomFlowerBlock> flower_tiny_cactus = BLOCKS.register("flower_tiny_cactus",
			   () -> flowers(null, 0, "block.extrabiomes.flower_tiny_cactus.description"));
	   
	   // OTHER PLANTS
	   public static final RegistryObject<CattailBlock> cattail = BLOCKS.register("cattail",
			   () -> new CattailBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).replaceable()
					   .noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)
					   .ignitedByLava().pushReaction(PushReaction.DESTROY)));
	   public static final RegistryObject<BushBlock> flower_autumn_shrub = BLOCKS.register("flower_autumn_shrub",
			   () -> new BushBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).replaceable().noCollission()
					   .instabreak().sound(SoundType.GRASS).ignitedByLava().pushReaction(PushReaction.DESTROY)));
	   
	   // CROPS
	   public static final RegistryObject<StrawberryBlock> crop_strawberry = BLOCKS.register("crop_strawberry", 
			   () -> new StrawberryBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission()
					   	.randomTicks().instabreak().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY)));
	   
	   // ======== BLOCK INITIALIZATION HELPER FUNCTIONS ========== //
	   
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
	   private static CustomLogBlock log(MapColor pTopMapColor, MapColor pSideMapColor) 
	   {
		   return new CustomLogBlock(BlockBehaviour.Properties.of().mapColor((p_152624_) -> {
		         return p_152624_.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? pTopMapColor : pSideMapColor;
		      }).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava());
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
