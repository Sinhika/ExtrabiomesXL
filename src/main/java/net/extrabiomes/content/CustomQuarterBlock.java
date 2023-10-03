package net.extrabiomes.content;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;

public class CustomQuarterBlock extends DirectionalBlock 
{
	protected final String tooltipKey;

	public CustomQuarterBlock(Properties pProperties, String toolTip) 
	{
		super(pProperties);
		this.tooltipKey = toolTip;
		this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.UP));
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
		return this.defaultBlockState().setValue(FACING, pContext.getClickedFace());
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

	/**
	 * Allow axe to change logs to stripped logs.
	 */
    @Override
    public BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction,
            boolean simulate)
    {
        BlockState toolModifiedState;
        
        if (ToolActions.AXE_STRIP == toolAction) 
        {
        	// TODO create stripped versions of logs and make a match table.
//            toolModifiedState = ModBlocks.stripped_blaze_log.get().defaultBlockState()
//                    .setValue(RotatedPillarBlock.AXIS, state.getValue(RotatedPillarBlock.AXIS));
            toolModifiedState = super.getToolModifiedState(state, context, toolAction, simulate);
        }
        else {
            toolModifiedState = super.getToolModifiedState(state, context, toolAction, simulate);
        }
        return toolModifiedState;
    }

	@Override
	public void appendHoverText(ItemStack pStack, @Nullable BlockGetter pLevel, List<Component> pTooltip, 
			  TooltipFlag pFlag) 
	{
		super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
		pTooltip.add(Component.translatable(tooltipKey));
	}

} // end class
