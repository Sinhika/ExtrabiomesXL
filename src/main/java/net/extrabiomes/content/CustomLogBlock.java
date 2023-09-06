package net.extrabiomes.content;

import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;

public class CustomLogBlock extends RotatedPillarBlock 
{

	public CustomLogBlock(Properties pProperties) 
	{
		super(pProperties);
	}
	
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


} // end class
