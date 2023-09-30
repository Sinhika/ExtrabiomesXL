package net.extrabiomes.entity;

import java.util.EnumSet;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
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

public class ScarecrowEntity extends AbstractGolem 
{
	protected static final EntityDataAccessor<Byte> DATA_FLAGS_ID = 
			SynchedEntityData.defineId(ScarecrowEntity.class, EntityDataSerializers.BYTE);
	
	public ScarecrowEntity(EntityType<? extends AbstractGolem> pEntityType, Level pLevel) 
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
	 * Doesn't despawn.
	 */
	@Override
	public void checkDespawn() {
	}

	
	@Override
	protected void registerGoals() 
	{
		this.goalSelector.addGoal(1, new ScareClosestGoal(this, 20.0F));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Mob.class, true, 
				(Predicate<LivingEntity>)(tgt) -> {return ((tgt instanceof PathfinderMob) && ! (tgt instanceof AbstractGolem));} ));
	}

	@Override
	protected ResourceLocation getDefaultLootTable() {
		// TODO Auto-generated method stub
		return super.getDefaultLootTable();
	}

	/**
	 * Scarecrows don't move.
	 */
	@Override
	public void setSpeed(float pSpeed) {
		super.setSpeed(0.0F);
	}

	/**
	 * we may be using this later.
	 */
	@Override
	public void aiStep() {
		// TODO Auto-generated method stub
		super.aiStep();
	}

	/**
	 * scarecrows don't drown.
	 */
	@Override
	protected int decreaseAirSupply(int pCurrentAir) {
		return pCurrentAir;
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
	
	public boolean isPlayerCreated() {
		return (this.entityData.get(DATA_FLAGS_ID) & 1) != 0;
	}

	public void setPlayerCreated(boolean pPlayerCreated) 
	{
	      byte b0 = this.entityData.get(DATA_FLAGS_ID);
	      if (pPlayerCreated) {
	         this.entityData.set(DATA_FLAGS_ID, (byte)(b0 | 1));
	      } 
	      else {
	         this.entityData.set(DATA_FLAGS_ID, (byte)(b0 & -2));
	      }
	}

   public void addAdditionalSaveData(CompoundTag pCompound) 
   {
      super.addAdditionalSaveData(pCompound);
      pCompound.putBoolean("PlayerCreated", this.isPlayerCreated());
   }
   /**
    * (abstract) Protected helper method to read subclass entity data from NBT.
    */
   public void readAdditionalSaveData(CompoundTag pCompound) 
   {
      super.readAdditionalSaveData(pCompound);
      this.setPlayerCreated(pCompound.getBoolean("PlayerCreated"));
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
