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
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
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
	     
	     // first, check to see if player is allowed to change things...
    	 Player player = pContext.getPlayer();
         ItemStack itemstack = pContext.getItemInHand();
    	 if (! player.mayUseItemAt(blockpos, pContext.getHorizontalDirection(), itemstack))
    	 {
    		 return InteractionResult.PASS;
    	 }
    	 
    	 // second, is this even a log?
	     if (! blockstate.is(BlockTags.LOGS))
	     {
    		 return InteractionResult.PASS;
	     }
	     
	     // okay, player is allowed to turn log. Credit them, and make sound.
         if (player instanceof ServerPlayer) 
         {
             CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer)player, blockpos, itemstack);
         }
         level.playSound(player, blockpos, SoundEvents.WOOD_STEP, SoundSource.BLOCKS, 0.5F, 1.55F);

         BlockState blockstate2;

         // regular logs or custom logs or mini-logs
         // 1.7.10 code
//       int orientation = metadata & 12;
//       orientation: 0 up-down (y-axis), 4 east-west (z-axis), 8 north-south (x-axis)
//       orientation = orientation == 0 
//						? 4 
//       				: orientation == 4 
       //				    ? 8 : 0;
         if (block instanceof RotatedPillarBlock || block instanceof MiniLogBlock)
         {
        	 Direction.Axis start_axis = (Direction.Axis) blockstate.getValue(RotatedPillarBlock.AXIS);
	    	 if (start_axis == Direction.Axis.Y)
	    	 {
	    		 blockstate2 = blockstate.setValue(RotatedPillarBlock.AXIS, Direction.Axis.Z);
	    	 }
	    	 else if (start_axis == Direction.Axis.Z)
	    	 {
	    		 blockstate2 = blockstate.setValue(RotatedPillarBlock.AXIS, Direction.Axis.X);
	    	 }
	    	 else {
	    		 blockstate2 = blockstate.setValue(RotatedPillarBlock.AXIS, Direction.Axis.Y);
	    	 }
         } // end-if RotatedPillarBlock
         // quarter logs
         // knee logs
         else if (block instanceof CustomQuarterBlock)
         {
        	 // use FACING to fake AXIS
        	 Direction facing = blockstate.getValue(DirectionalBlock.FACING);
        	 Direction.Axis start_axis = facing.getAxis();
	    	 if (start_axis == Direction.Axis.Y)
	    	 {
	    		 blockstate2 = blockstate.setValue(DirectionalBlock.FACING, Direction.EAST);
	    	 }
	    	 else if (start_axis == Direction.Axis.Z)
	    	 {
	    		 blockstate2 = blockstate.setValue(DirectionalBlock.FACING, Direction.NORTH);
	    	 }
	    	 else {
	    		 blockstate2 = blockstate.setValue(DirectionalBlock.FACING, Direction.UP);
	    	 }
         }
         else  // we shouldn't be here. 
         {
        	 return InteractionResult.PASS;
         }
         level.setBlockAndUpdate(blockpos, blockstate2);
         level.gameEvent(GameEvent.BLOCK_CHANGE, blockpos, GameEvent.Context.of(pContext.getPlayer(), blockstate2));
         return InteractionResult.sidedSuccess(level.isClientSide);
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
