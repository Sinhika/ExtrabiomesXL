package net.extrabiomes.content;

import java.util.Objects;

import net.extrabiomes.init.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

/**
 * craftable scarecrow item acts like a scarecrow egg.
 */
public class ScarecrowItem extends CustomItem 
{

	public ScarecrowItem(Properties pProperties, String tooltipKey) 
	{
		super(pProperties, tooltipKey);
		// TODO Auto-generated constructor stub
	}

   /**
    * Called when this item is used when targeting a Block
    */
	@Override
	public InteractionResult useOn(UseOnContext pContext) 
	{
	      Level level = pContext.getLevel();
	      if (!(level instanceof ServerLevel)) 
	      {
	         return InteractionResult.SUCCESS;
	      } 
	      else 
	      {
	         ItemStack itemstack = pContext.getItemInHand();
	         BlockPos blockpos = pContext.getClickedPos();
	         Direction direction = pContext.getClickedFace();
	         BlockState blockstate = level.getBlockState(blockpos);
	         
	         // Scarecrows don't do spawners.
	         if (blockstate.is(Blocks.SPAWNER)) 
	         {
	            BlockEntity blockentity = level.getBlockEntity(blockpos);
	            if (blockentity instanceof SpawnerBlockEntity) 
	            {
	               return InteractionResult.PASS;
	            }
	         } // end-if SPAWNER
	
	         BlockPos blockpos1;
	         if (blockstate.getCollisionShape(level, blockpos).isEmpty()) 
	         {
	            blockpos1 = blockpos;
	         } 
	         else 
	         {
	            blockpos1 = blockpos.relative(direction);
	         }
	
	         EntityType<?> entitytype = this.getType(itemstack.getTag());
	         if (entitytype.spawn((ServerLevel)level, itemstack, pContext.getPlayer(), blockpos1, 
	        		 				MobSpawnType.SPAWN_EGG, true, !Objects.equals(blockpos, blockpos1) 
	        		 				&& direction == Direction.UP) != null) 
	         {
	            itemstack.shrink(1);
	            level.gameEvent(pContext.getPlayer(), GameEvent.ENTITY_PLACE, blockpos);
	         }
	
	         return InteractionResult.CONSUME;
	      } // end else is ServerLevel
	} // end onUse

	   /**
	    * Called to trigger the item's "innate" right click behavior. To handle when this item is used on a Block, see
	    * {@link #onItemUse}.
	    */
	   public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) 
	   {
	      ItemStack itemstack = pPlayer.getItemInHand(pHand);
	      BlockHitResult blockhitresult = getPlayerPOVHitResult(pLevel, pPlayer, ClipContext.Fluid.SOURCE_ONLY);
	      if (blockhitresult.getType() != HitResult.Type.BLOCK) 
	      {
	         return InteractionResultHolder.pass(itemstack);
	      } // end-if
	      else if (!(pLevel instanceof ServerLevel)) 
	      {
	         return InteractionResultHolder.success(itemstack);
	      } // end-else-if
	      else 
	      {
	         BlockPos blockpos = blockhitresult.getBlockPos();
	         if (!(pLevel.getBlockState(blockpos).getBlock() instanceof LiquidBlock)) 
	         {
	            return InteractionResultHolder.pass(itemstack);
	         } // end-if
	         else if (pLevel.mayInteract(pPlayer, blockpos) 
	        		  && pPlayer.mayUseItemAt(blockpos, blockhitresult.getDirection(), itemstack)) 
	         {
	            EntityType<?> entitytype = this.getType(itemstack.getTag());
	            Entity entity = entitytype.spawn((ServerLevel)pLevel, itemstack, pPlayer, blockpos, 
	            								  MobSpawnType.SPAWN_EGG, false, false);
	            if (entity == null) 
	            {
	               return InteractionResultHolder.pass(itemstack);
	            } 
	            else 
	            {
	               if (!pPlayer.getAbilities().instabuild) {
	                  itemstack.shrink(1);
	               }

	               pPlayer.awardStat(Stats.ITEM_USED.get(this));
	               pLevel.gameEvent(pPlayer, GameEvent.ENTITY_PLACE, entity.position());
	               return InteractionResultHolder.consume(itemstack);
	            }
	         } // end-else-if 
	         else {
	            return InteractionResultHolder.fail(itemstack);
	         } // end-else
	      } // end-else
	   } // end use()

	protected EntityType<?> getType(CompoundTag tag) 
	{
		return ModEntities.scarecrow.get();
	}

	
} // end class
