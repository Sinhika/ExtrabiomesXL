package net.extrabiomes.datagen;

import java.util.stream.Stream;

import net.extrabiomes.init.ModEntities;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class ExtrabiomesEntityLootSubprovider extends EntityLootSubProvider 
{

	public ExtrabiomesEntityLootSubprovider() {
		super(FeatureFlags.REGISTRY.allFlags());
	}


	@Override
	public void generate() 
	{
		this.add(ModEntities.scarecrow.get(), LootTable.lootTable().withPool(
				LootPool.lootPool().setRolls(UniformGenerator.between(0.0F,2.0F))
					.add(LootItem.lootTableItem(Items.STICK).apply(
														SetItemCountFunction.setCount(ConstantValue.exactly(3.0F))))
					.add(LootItem.lootTableItem(Blocks.MELON.asItem()).apply(
														SetItemCountFunction.setCount(ConstantValue.exactly(1.0F))))
					.add(LootItem.lootTableItem(Blocks.CARVED_PUMPKIN.asItem()).apply(
							SetItemCountFunction.setCount(ConstantValue.exactly(1.0F))))
					));
	} // end generate


	@Override
	protected Stream<EntityType<?>> getKnownEntityTypes() 
	{
		return ModEntities.ENTITIES.getEntries().stream().map(a -> a.get());
	}


	@Override
	protected boolean canHaveLootTable(EntityType<?> pEntityType) {
		return pEntityType == ModEntities.scarecrow.get();
	}

	
} // end class
