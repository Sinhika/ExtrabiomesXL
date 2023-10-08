package net.extrabiomes.content;

import java.util.List;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;

public class MiniLogBlock extends PipeBlock 
{
	protected final String tooltipKey;
	protected final static float Apothem = 0.3125F;
	
	public MiniLogBlock(Properties pProperties, String toolTip) 
	{
		super(Apothem, pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(NORTH, Boolean.valueOf(false))
        		.setValue(EAST, Boolean.valueOf(false)).setValue(SOUTH, Boolean.valueOf(false))
        		.setValue(WEST, Boolean.valueOf(false)).setValue(UP, Boolean.valueOf(false))
        		.setValue(DOWN, Boolean.valueOf(false)));

		this.tooltipKey = toolTip;

	} // end ctor

   protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) 
   {
	      pBuilder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN);
   }

   @Override
    public BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction,
            boolean simulate)
    {
        BlockState toolModifiedState;
        
        if (ToolActions.AXE_STRIP == toolAction) 
        {
        	// TODO create stripped versions of logs and make a match table.
//	            toolModifiedState = ModBlocks.stripped_blaze_log.get().defaultBlockState()
//	                    .setValue(RotatedPillarBlock.AXIS, state.getValue(RotatedPillarBlock.AXIS));
            toolModifiedState = super.getToolModifiedState(state, context, toolAction, simulate);
        }
        else {
            toolModifiedState = super.getToolModifiedState(state, context, toolAction, simulate);
        }
        return toolModifiedState;
    }

	@Override
	public void appendHoverText(ItemStack pStack, BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) 
	{
		super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
		pTooltip.add(Component.translatable(tooltipKey));
	}

	
} // end-class
