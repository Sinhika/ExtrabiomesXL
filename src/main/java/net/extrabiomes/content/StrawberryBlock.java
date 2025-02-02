package net.extrabiomes.content;

import net.extrabiomes.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class StrawberryBlock extends CropBlock 
{
	// potato shape
	private static final VoxelShape[] SHAPE_BY_AGE = 
			new VoxelShape[]{Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), 
							 Block.box(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D), 
							 Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D), 
							 Block.box(0.0D, 0.0D, 0.0D, 16.0D, 5.0D, 16.0D), 
							 Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D), 
							 Block.box(0.0D, 0.0D, 0.0D, 16.0D, 7.0D, 16.0D), 
							 Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D), 
							 Block.box(0.0D, 0.0D, 0.0D, 16.0D, 9.0D, 16.0D)};

	public StrawberryBlock(Properties pProperties) {
		super(pProperties);
	}

	@Override
	protected ItemLike getBaseSeedId() {
		return ModItems.seed_strawberry.get();
	}

   public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) 
   {
	      return SHAPE_BY_AGE[this.getAge(pState)];
   }
} // end class
