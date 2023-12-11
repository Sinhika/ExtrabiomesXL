package net.extrabiomes.content;

import java.util.List;
import java.util.HashMap;

import javax.annotation.Nullable;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.registries.RegistryObject;

public class CustomLogBlock extends RotatedPillarBlock 
{
	protected final String tooltipKey;
    protected static HashMap<CustomLogBlock, RegistryObject<RotatedPillarBlock>> strip_table
            = new HashMap<CustomLogBlock, RegistryObject<RotatedPillarBlock>>();

	public CustomLogBlock(Properties pProperties, String toolTip)
	{
		super(pProperties);
		this.tooltipKey = toolTip;
	}

    public CustomLogBlock(Properties pProperties, String toolTip, RegistryObject<RotatedPillarBlock> stripped_version)
    {
        super(pProperties);
        this.tooltipKey = toolTip;
        strip_table.put(this, stripped_version);
    }

    @Override
    public BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction,
            boolean simulate)
    {
        BlockState toolModifiedState;
        
        if (ToolActions.AXE_STRIP == toolAction) 
        {
        	// create stripped versions of logs and make a match table.
            if (strip_table.containsKey(this)) {
                toolModifiedState = strip_table.get(this).get().defaultBlockState()
                    .setValue(RotatedPillarBlock.AXIS, state.getValue(RotatedPillarBlock.AXIS));
            }
            else {
                toolModifiedState = super.getToolModifiedState(state, context, toolAction, simulate);
            }
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
