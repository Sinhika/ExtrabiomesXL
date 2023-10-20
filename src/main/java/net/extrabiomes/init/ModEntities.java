package net.extrabiomes.init;

import net.extrabiomes.ExtrabiomesXS;
import net.extrabiomes.entity.ScarecrowEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public final class ModEntities 
{
	 public static final DeferredRegister<EntityType<?>> ENTITY_TYPE_REGISTRY =
	            DeferredRegister.create(Registries.ENTITY_TYPE, ExtrabiomesXS.MODID);
	 
	 
	 public static final RegistryObject<EntityType<ScarecrowEntity>> scarecrow = 
			 ENTITY_TYPE_REGISTRY.register("scarecrow", 
					 ()-> EntityType.Builder.of(ScarecrowEntity::new, MobCategory.MISC)
					 .sized(1.0F,1.0F).build("scarecrow"));
} // end class
