package net.extrabiomes.content;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;

public class LeafPileBlock extends CarpetBlock implements IPlantable
{

	public LeafPileBlock(Properties pProperties) 
	{
		super(pProperties);
	}

   protected boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) 
   {
      return pState.is(BlockTags.DIRT) || pState.is(Blocks.GRASS_BLOCK);
   }

	public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) 
	{
		BlockPos blockpos = pPos.below();
		if (pState.getBlock() == this) //Forge: This function is called during world gen and placement, before this block is set, so if we are not 'here' then assume it's the pre-check.
			return pLevel.getBlockState(blockpos).canSustainPlant(pLevel, blockpos, Direction.UP, this);
		return this.mayPlaceOn(pLevel.getBlockState(blockpos), pLevel, blockpos);
	}

	@Override
	public BlockState getPlant(BlockGetter level, BlockPos pos) 
	{
		BlockState state = level.getBlockState(pos);
		if (state.getBlock() != this) return defaultBlockState();
		return state;
	}

	@Override
	public PlantType getPlantType(BlockGetter level, BlockPos pos) 
	{
	
		return PlantType.PLAINS;
	}

	@Override
	public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing,
			IPlantable plantable) 
	{
	    if (plantable instanceof LeafPileBlock && ((LeafPileBlock)plantable).mayPlaceOn(state, world, pos))
	    	return true;
	    return super.canSustainPlant(state, world, pos, facing, plantable);
	}
	   
} // end class
