package net.extrabiomes.content;

import java.util.List;
import java.util.function.Supplier;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.FlowerBlock;

public class CustomFlowerBlock extends FlowerBlock 
{
	protected final String tooltipKey;
	
	public CustomFlowerBlock(Supplier<MobEffect> effectSupplier, int pEffectDuration, Properties pProperties, 
							String tooltipIn) 
	{
		super(effectSupplier, pEffectDuration, pProperties);
		this.tooltipKey = tooltipIn;
	}

	@Override
	public void appendHoverText(ItemStack pStack, BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) 
	{
		super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
		pTooltip.add(Component.translatable(tooltipKey));
	}

	
} // end class
