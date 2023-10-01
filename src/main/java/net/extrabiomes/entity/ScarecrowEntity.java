package net.extrabiomes.entity;

import java.util.EnumSet;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import net.extrabiomes.init.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;

public class ScarecrowEntity extends AbstractGolem 
{
	
	public ScarecrowEntity(EntityType<? extends ScarecrowEntity> pEntityType, Level pLevel) 
	{
		super(pEntityType, pLevel);
		this.setCanPickUpLoot(false);
		this.setPersistenceRequired();
	}

	@Override
	protected boolean shouldDespawnInPeaceful() {
		return false;
	}
	
	/**
	 * set movement_speed to 0.
	 * @return
	 */
	public static AttributeSupplier.Builder prepareAttributes()
    {
    	return Mob.createMobAttributes()
    			.add(Attributes.MAX_HEALTH, 10.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.0D);
    }
	
	
	@Override
	protected void registerGoals() 
	{
		this.goalSelector.addGoal(1, new ScareClosestGoal(this, 20.0F));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Mob.class, true, 
				(Predicate<LivingEntity>)(tgt) -> {return ((tgt instanceof PathfinderMob) && ! (tgt instanceof AbstractGolem));} ));
	}

	public boolean checkSpawnObstruction(LevelReader pLevel) 
	{
	      BlockPos blockpos = this.blockPosition();
	      BlockPos blockpos1 = blockpos.below();
	      BlockState blockstate = pLevel.getBlockState(blockpos1);
	      if (!blockstate.entityCanStandOn(pLevel, blockpos1, this)) 
	      {
	         return false;
	      } 
	      else 
	      {
	         for(int i = 1; i < 3; ++i) 
	         {
		            BlockPos blockpos2 = blockpos.above(i);
		            BlockState blockstate1 = pLevel.getBlockState(blockpos2);
		            if (!NaturalSpawner.isValidEmptySpawnBlock(pLevel, blockpos2, blockstate1, blockstate1.getFluidState(), 
		            										   ModEntities.scarecrow.get())) 
		            {
		            	return false;
		            }
	         } // end-for
	         return NaturalSpawner.isValidEmptySpawnBlock(pLevel, blockpos, pLevel.getBlockState(blockpos), 
	        		 Fluids.EMPTY.defaultFluidState(),  ModEntities.scarecrow.get()) && pLevel.isUnobstructed(this);
	      } // end else
	} // end checkSpawnObstruction

	/**
	 * Scarecrows don't move.
	 */
	@Override
	public void setSpeed(float pSpeed) {
		super.setSpeed(0.0F);
	}

	/**
	 * scarecrows don't drown.
	 */
	@Override
	protected int decreaseAirSupply(int pCurrentAir) {
		return pCurrentAir;
	}


   private class ScareClosestGoal extends Goal
   {
	   private final AbstractGolem golemMob;
	   private float maxDistance;
	   @Nullable
	   private LivingEntity target;
	   
	   public ScareClosestGoal(AbstractGolem scarecrow, float max_distance) 
	   {
			this.golemMob = scarecrow;
			this.maxDistance = max_distance;
			this.setFlags(EnumSet.of(Goal.Flag.LOOK));
	   }

	   @Override
	   public boolean canUse() 
	   {
		   LivingEntity livingentity = this.golemMob.getTarget();
		   if (livingentity != null && livingentity.isAlive() && livingentity instanceof PathfinderMob)
		   {
			   this.target = livingentity;
			   return true;
		   }
		   return false;
	   }

	   @Override
		public boolean canContinueToUse() 
	   	{
		   return this.canUse() || this.target.isAlive();
		}

		@Override
		public void stop() {
			this.target = null;
		}

		@Override
		public void start() 
		{
			((Mob)this.target).goalSelector.addGoal(1, 
					new AvoidEntityGoal<>((PathfinderMob) this.target, ScarecrowEntity.class, this.maxDistance,1.0D, 1.2D) );
		}
   } // end class ScareClosestGoal
   

} // end class
