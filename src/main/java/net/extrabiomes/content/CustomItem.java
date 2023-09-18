package net.extrabiomes.content;

import java.util.List;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class CustomItem extends Item 
{
	protected final String tooltipKey;

	public CustomItem(Properties pProperties, String tooltipIn) 
	{
		super(pProperties);
		this.tooltipKey = tooltipIn;
	}

	@Override
	public void appendHoverText(ItemStack pStack, Level pLevel, List<Component> pTooltipComponents,
			TooltipFlag pIsAdvanced) 
	{
		super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
		pTooltipComponents.add(Component.translatable(tooltipKey));
	}

	
} // end class
