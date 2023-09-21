package net.extrabiomes.content;

import java.util.List;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

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
	     
	     if (blockstate.is(BlockTags.LOGS) && block instanceof RotatedPillarBlock)
	     {
	    	 Player player = pContext.getPlayer();
	         ItemStack itemstack = pContext.getItemInHand();
	    	 if (player.mayUseItemAt(blockpos, pContext.getHorizontalDirection(), itemstack))
	    	 {
	             if (player instanceof ServerPlayer) {
	                 CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer)player, blockpos, itemstack);
	              }
	             
	             level.playSound(player, blockpos, SoundEvents.WOOD_STEP, SoundSource.BLOCKS, 0.5F, 1.55F);
	             Direction face = pContext.getClickedFace();
	             BlockState blockstate2;
	              if (face == Direction.DOWN || face == Direction.UP)
	              {
	            	  Direction.Axis start_axis = (Direction.Axis) blockstate.getValue(RotatedPillarBlock.AXIS);
	            	  switch (start_axis)
	            	  {
	            	  	case X:
	            	  	case Z:
	            	  		blockstate2 = blockstate.setValue(RotatedPillarBlock.AXIS, Direction.Axis.Y);
	            	  		break;
	            	  	case Y: // case Y
	            	  	default:
	            	  		blockstate2 = blockstate.setValue(RotatedPillarBlock.AXIS, Direction.Axis.X);
	            	  		break;
	            	  }
	              }
	              else {
	            	  blockstate2 = RotatedPillarBlock.rotatePillar(blockstate, Rotation.CLOCKWISE_90);
	              }
	              level.setBlockAndUpdate(blockpos, blockstate2);
	              
	              level.gameEvent(GameEvent.BLOCK_CHANGE, blockpos, GameEvent.Context.of(pContext.getPlayer(), blockstate2));
	              
	              return InteractionResult.sidedSuccess(level.isClientSide);
	    	 } // end-if player.mayUseItemAt()
	     } // end-if
    	 return super.useOn(pContext);
	} // end useOn;

	
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
