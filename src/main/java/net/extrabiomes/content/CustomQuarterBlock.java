package net.extrabiomes.content;

import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

public class CustomQuarterBlock extends CustomLogBlock 
{
	public static final DirectionProperty FACING = BlockStateProperties.FACING;
	
	public CustomQuarterBlock(Properties pProperties) 
	{
		super(pProperties);
		this.registerDefaultState(this.defaultBlockState()
				.setValue(AXIS, Direction.Axis.Y)
				.setValue(FACING, Direction.NORTH));
	}

	
	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> pBuilder) 
	{
		super.createBlockStateDefinition(pBuilder);
		pBuilder.add(FACING);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext pContext) 
	{
		return this.defaultBlockState().setValue(AXIS, pContext.getClickedFace().getAxis())
				.setValue(FACING, pContext.getClickedFace().getOpposite());
	}
	
	/**
	* Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed
	* blockstate.
	* @deprecated call via {@link net.minecraft.world.level.block.state.BlockBehaviour.BlockStateBase#rotate} whenever
	* possible. Implementing/overriding is fine.
	*/
	@Override
	public BlockState rotate(BlockState pState, Rotation pRotation) 
	{
		return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
	}
	
	/**
	* Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed
	* blockstate.
	* @deprecated call via {@link net.minecraft.world.level.block.state.BlockBehaviour.BlockStateBase#mirror} whenever
	* possible. Implementing/overriding is fine.
	*/
	@Override
	public BlockState mirror(BlockState pState, Mirror pMirror) 
	{
		return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
	}

} // end class
