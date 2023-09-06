package net.extrabiomes.init;

import net.extrabiomes.ExtrabiomesXS;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
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
	            () -> new LeavesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(0.2F).randomTicks()
	                    .noOcclusion().sound(SoundType.GRASS).isSuffocating((a,b,c)->{return false;})
	                    .isViewBlocking((a,b,c)->{return false;}).pushReaction(PushReaction.DESTROY)));
	   
	   public static final RegistryObject<LeavesBlock> leaves_goldenrod = BLOCKS.register("leaves_goldenrod", 
	            () -> new LeavesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).strength(0.2F).randomTicks()
	                    .noOcclusion().sound(SoundType.GRASS).isSuffocating((a,b,c)->{return false;})
	                    .isViewBlocking((a,b,c)->{return false;}).pushReaction(PushReaction.DESTROY)));

	   public static final RegistryObject<LeavesBlock> leaves_vermillion = BLOCKS.register("leaves_vermillion", 
	            () -> new LeavesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).strength(0.2F).randomTicks()
	                    .noOcclusion().sound(SoundType.GRASS).isSuffocating((a,b,c)->{return false;})
	                    .isViewBlocking((a,b,c)->{return false;}).pushReaction(PushReaction.DESTROY)));

	   public static final RegistryObject<LeavesBlock> leaves_citrine = BLOCKS.register("leaves_citrine", 
	            () -> new LeavesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).strength(0.2F).randomTicks()
	                    .noOcclusion().sound(SoundType.GRASS).isSuffocating((a,b,c)->{return false;})
	                    .isViewBlocking((a,b,c)->{return false;}).pushReaction(PushReaction.DESTROY)));
	   
} // end class
