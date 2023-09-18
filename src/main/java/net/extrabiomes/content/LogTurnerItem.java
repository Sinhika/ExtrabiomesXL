package net.extrabiomes.content;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class LogTurnerItem extends TieredItem 
{
	protected final String tooltipKey;

	public LogTurnerItem(Tier pTier, Properties pProperties, String tooltipKey) 
	{
		super(pTier, pProperties);
		this.tooltipKey = tooltipKey;
	}

	
	@Override
	public InteractionResult useOn(UseOnContext pContext) 
	{
	     Level level = pContext.getLevel();
	     BlockPos blockpos = pContext.getClickedPos();
	     BlockState blockstate = level.getBlockState(blockpos);
	     Block block = blockstate.getBlock();
	     
	     
	      
		// TODO Auto-generated method stub
		return super.useOn(pContext);
	}


	@Override
	public void appendHoverText(ItemStack pStack, Level pLevel, List<Component> pTooltipComponents,
			TooltipFlag pIsAdvanced) 
	{
		super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
		pTooltipComponents.add(Component.translatable(tooltipKey));
	}

	@Override
	public boolean isDamageable(ItemStack stack) {
		return false;
	}

	@Override
	public boolean isRepairable(ItemStack stack) {
		return false;
	}

	
} // end class
