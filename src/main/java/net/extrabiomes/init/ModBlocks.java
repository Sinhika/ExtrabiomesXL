package net.extrabiomes.init;

import net.extrabiomes.ExtrabiomesXS;
import net.extrabiomes.content.CustomLogBlock;
import net.extrabiomes.world.AutumnTreeGrower;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
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
			   () -> sapling(new AutumnTreeGrower()));
	   public static final RegistryObject<SaplingBlock> sapling_goldenrod = BLOCKS.register("sapling_goldenrod", 
			   () -> sapling(new AutumnTreeGrower()));
	   public static final RegistryObject<SaplingBlock> sapling_vermillion = BLOCKS.register("sapling_vermillion", 
			   () -> sapling(new AutumnTreeGrower()));
	   public static final RegistryObject<SaplingBlock> sapling_citrine = BLOCKS.register("sapling_citrine", 
			   () -> sapling(new AutumnTreeGrower()));
	   
	   // Block initialization helper functions
	   public static SaplingBlock sapling(AbstractTreeGrower pTreeGrower)
	   {
		   return new SaplingBlock(pTreeGrower, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT)
				   .noCollission().randomTicks().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY));
	   }
	   
	   public static LeavesBlock leaves(SoundType pType) 
	   {
		      return new LeavesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).strength(0.2F).randomTicks()
		    		  .sound(pType).noOcclusion().isValidSpawn(ModBlocks::ocelotOrParrot).isSuffocating((a,b,c)->{return false;})
		    		  .isViewBlocking((a,b,c)->{return false;}).ignitedByLava().pushReaction(PushReaction.DESTROY)
		    		  .isRedstoneConductor((a,b,c)->{return false;}));
	   }
	
	   private static CustomLogBlock log(MapColor pTopMapColor, MapColor pSideMapColor) 
	   {
		   return new CustomLogBlock(BlockBehaviour.Properties.of().mapColor((p_152624_) -> {
		         return p_152624_.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? pTopMapColor : pSideMapColor;
		      }).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava());
	   }
	
	   private static Block planks(MapColor mColor)
	   {
		   return new Block(BlockBehaviour.Properties.of().mapColor(mColor).instrument(NoteBlockInstrument.BASS)
				   .strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava());
	   }
  
	   private static Boolean ocelotOrParrot(BlockState p_50822_, BlockGetter p_50823_, BlockPos p_50824_, EntityType<?> p_50825_) 
	   {
		      return (boolean)(p_50825_ == EntityType.OCELOT || p_50825_ == EntityType.PARROT);
	   }

} // end class
