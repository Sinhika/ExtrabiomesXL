package net.extrabiomes.init;

import net.extrabiomes.ExtrabiomesXS;
import net.extrabiomes.content.CustomLogBlock;
import net.extrabiomes.world.AutumnTreeGrower;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.LeavesBlock;
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


	   // autumn tree leaf blocks
	   public static final RegistryObject<LeavesBlock> leaves_umber = BLOCKS.register("leaves_umber", 
	            () -> leaves(SoundType.GRASS));
	   public static final RegistryObject<LeavesBlock> leaves_goldenrod = BLOCKS.register("leaves_goldenrod", 
	            () -> leaves(SoundType.GRASS));
	   public static final RegistryObject<LeavesBlock> leaves_vermillion = BLOCKS.register("leaves_vermillion", 
	            () -> leaves(SoundType.GRASS));
	   public static final RegistryObject<LeavesBlock> leaves_citrine = BLOCKS.register("leaves_citrine", 
	            () -> leaves(SoundType.GRASS));
	   
	   // autumn tree logs & wood.
	   public static final RegistryObject<CustomLogBlock> log_autumn = BLOCKS.register("log_autumn", 
			   () -> log(MapColor.COLOR_YELLOW, MapColor.PODZOL));
	   public static final RegistryObject<Block> planks_autumn_wood = BLOCKS.register("planks_autumn_wood",
			   () -> planks(MapColor.WOOD));

	   
	   // saplings
	   public static final RegistryObject<SaplingBlock> sapling_umber = BLOCKS.register("sapling_umber", 
			   () -> sapling(new AutumnTreeGrower(ModFeatures.UMBER_AUTUMN_TREE, ModFeatures.FANCY_UMBER_AUTUMN_TREE)));
	   public static final RegistryObject<SaplingBlock> sapling_goldenrod = BLOCKS.register("sapling_goldenrod", 
			   () -> sapling(new AutumnTreeGrower(ModFeatures.GOLDENROD_AUTUMN_TREE, ModFeatures.FANCY_GOLDENROD_AUTUMN_TREE)));
	   public static final RegistryObject<SaplingBlock> sapling_vermillion = BLOCKS.register("sapling_vermillion", 
			   () -> sapling(new AutumnTreeGrower(ModFeatures.VERMILLION_AUTUMN_TREE, ModFeatures.FANCY_VERMILLION_AUTUMN_TREE)));
	   public static final RegistryObject<SaplingBlock> sapling_citrine = BLOCKS.register("sapling_citrine", 
			   () -> sapling(new AutumnTreeGrower(ModFeatures.CITRINE_AUTUMN_TREE, ModFeatures.FANCY_CITRINE_AUTUMN_TREE)));
	   
	   // aesthetic blocks
	   // stairs
	   public static final RegistryObject<StairBlock> stairs_autumn = BLOCKS.register("stairs_autumn", 
			   () -> stairs(planks_autumn_wood));
	   
	   // slabs
	   public static final RegistryObject<SlabBlock> slab_autumn = BLOCKS.register("slab_autumn", 
			   () -> new SlabBlock(BlockBehaviour.Properties.copy(planks_autumn_wood.get())));
	                     
	   // fences
	   public static final RegistryObject<FenceBlock> fence_autumn = BLOCKS.register("fence_autumn", 
	            () -> fences(ModBlocks.planks_autumn_wood));
	   
	   // doors
	   public static RegistryObject<DoorBlock> door_autumn = BLOCKS.register("door_autumn",
			   () -> doors(ModBlocks.planks_autumn_wood.get().defaultMapColor(), BlockSetType.OAK));
	   
	   // fence gates
	   public static final RegistryObject<FenceGateBlock> gate_autumn = BLOCKS.register("gate_autumn",
			   () -> gates(ModBlocks.planks_autumn_wood.get().defaultMapColor(), WoodType.OAK));
	   
	   	   
	   // ======== BLOCK INITIALIZATION HELPER FUNCTIONS ========== //
	   
	   /**
	    * make new fence gates
	    */
	   public static FenceGateBlock gates(MapColor color, WoodType woodType)
	   {
		   return new FenceGateBlock(BlockBehaviour.Properties.of().mapColor(color).strength(2.0F, 3.0F)
				   .sound(SoundType.WOOD),woodType);
	   }
	   
	   /**
	    * make new doors
	    */
	   public static DoorBlock doors(MapColor color, BlockSetType blockSetType)
	   {
		   return new DoorBlock(BlockBehaviour.Properties.of().mapColor(color)
					.strength(3.0F).noOcclusion().ignitedByLava().pushReaction(PushReaction.DESTROY),
					blockSetType);
	   }
	   
	   /**
	    * make new fences
	    */
	   public static FenceBlock fences(RegistryObject<Block> wood_planks)
	   {
		   return new FenceBlock(BlockBehaviour.Properties.of().mapColor(wood_planks.get().defaultMapColor())
                   .strength(2.0F, 3.0F).sound(SoundType.WOOD));
	   }
	   
	   /**
	    * make new stairs
	    */
	   public static StairBlock stairs(RegistryObject<Block> wood_planks) 
	   {
		   return new StairBlock( () -> wood_planks.get().defaultBlockState(),  Block.Properties.copy(wood_planks.get()));
	   }
	   
	   /**
	    * make a new sapling.
	    * @param pTreeGrower our TreeGrower function
	    * @returns a new SaplingBlock object.
	    */
	   public static SaplingBlock sapling(AbstractTreeGrower pTreeGrower)
	   {
		   return new SaplingBlock(pTreeGrower, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT)
				   .noCollission().randomTicks().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY));
	   }
	   
	   /**
	    * make a new leaf block.
	    * @param pType - SoundType for walking on leaves. Usually GRASS.
	    * @returns a new LeavesBlock object.
	    */
	   public static LeavesBlock leaves(SoundType pType) 
	   {
		      return new LeavesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).strength(0.2F).randomTicks()
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
