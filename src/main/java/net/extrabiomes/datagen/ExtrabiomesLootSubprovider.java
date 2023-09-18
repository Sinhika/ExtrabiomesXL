package net.extrabiomes.datagen;

import mod.alexndr.simplecorelib.api.datagen.SimpleBlockLootSubProvider;
import net.extrabiomes.init.ModBlocks;
import net.extrabiomes.init.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

public class ExtrabiomesLootSubprovider extends SimpleBlockLootSubProvider {

	@Override
	protected void generate() 
	{
		// leaves
		this.add(ModBlocks.leaves_citrine.get(), createLeavesDrops(ModBlocks.leaves_citrine.get(), 
				ModBlocks.sapling_citrine.get(), NORMAL_LEAVES_SAPLING_CHANCES));
		this.add(ModBlocks.leaves_goldenrod.get(), createLeavesDrops(ModBlocks.leaves_goldenrod.get(), 
				ModBlocks.sapling_goldenrod.get(),NORMAL_LEAVES_SAPLING_CHANCES));
		this.add(ModBlocks.leaves_umber.get(), createLeavesDrops(ModBlocks.leaves_umber.get(), ModBlocks.sapling_umber.get(), 
				NORMAL_LEAVES_SAPLING_CHANCES));
		this.add(ModBlocks.leaves_vermillion.get(), createLeavesDrops(ModBlocks.leaves_vermillion.get(), 
				ModBlocks.sapling_vermillion.get(), NORMAL_LEAVES_SAPLING_CHANCES));
		this.add(ModBlocks.leaves_japanese_maple.get(), createLeavesDrops(ModBlocks.leaves_japanese_maple.get(),
				ModBlocks.sapling_japanese_maple.get(), NORMAL_LEAVES_SAPLING_CHANCES));
		
		// saplings
		this.dropSelf(ModBlocks.sapling_citrine.get());
		this.dropSelf(ModBlocks.sapling_goldenrod.get());
		this.dropSelf(ModBlocks.sapling_umber.get());
		this.dropSelf(ModBlocks.sapling_vermillion.get());
		this.dropSelf(ModBlocks.sapling_japanese_maple.get());
		
		// flowers
		this.dropSelf(ModBlocks.flower_redrover.get());
		this.dropSelf(ModBlocks.flower_hydrangea.get());
		this.dropSelf(ModBlocks.flower_buttercup.get());
		this.dropSelf(ModBlocks.flower_lavender.get());
		this.dropSelf(ModBlocks.flower_tiny_cactus.get());
		this.dropSelf(ModBlocks.flower_allium.get());
		this.dropSelf(ModBlocks.flower_calla_white.get());
		this.dropSelf(ModBlocks.flower_toadstool.get());
		this.dropSelf(ModBlocks.flower_amaryllis_pink.get());
		this.dropSelf(ModBlocks.flower_amaryllis_red.get());
		this.dropSelf(ModBlocks.flower_amaryllis_white.get());
		this.dropSelf(ModBlocks.flower_bachelors_button.get());
		this.dropSelf(ModBlocks.flower_bells_of_ireland.get());
		this.dropSelf(ModBlocks.flower_bluebell.get());
		this.dropSelf(ModBlocks.flower_calla_black.get());
		
		// logs
		this.dropSelf(ModBlocks.log_autumn.get());
		this.dropSelf(ModBlocks.log_japanese_maple.get());
		
		// planks
		this.dropSelf(ModBlocks.planks_autumn_wood.get());
		this.dropSelf(ModBlocks.planks_japanese_maple.get());
		
		// stairs
		this.dropSelf(ModBlocks.stairs_autumn.get());
		this.dropSelf(ModBlocks.stairs_japanesemaple.get());
		
		// slabs
		this.dropSlab(ModBlocks.slab_autumn.get());
		this.dropSlab(ModBlocks.slab_japanese_maple.get());
		
		// doors
		this.doorDropTable(ModBlocks.door_autumn.get());
		this.doorDropTable(ModBlocks.door_japanesemaple.get());
		
		// fences
		this.dropSelf(ModBlocks.fence_autumn.get());
		this.dropSelf(ModBlocks.fence_japanesemaple.get());
		
		// fence gates
		this.dropSelf(ModBlocks.gate_autumn.get());
		this.dropSelf(ModBlocks.gate_japanesemaple.get());
		
		// buttons
		this.dropSelf(ModBlocks.button_autumn.get());
		this.dropSelf(ModBlocks.button_japanesemaple.get());
		
		// pressure plates
		this.dropSelf(ModBlocks.pressureplate_autumn.get());
		this.dropSelf(ModBlocks.pressureplate_japanesemaple.get());
		
		// crops
		LootItemCondition.Builder strawberry_condition = 
				LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.crop_strawberry.get())
					.setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CropBlock.AGE, 7));
		this.add(ModBlocks.crop_strawberry.get(), createCropDrops(ModBlocks.crop_strawberry.get(), 
				ModItems.food_strawberry.get(), ModItems.seed_strawberry.get(), strawberry_condition));
		
		// plants
		this.dropSelf(ModBlocks.cattail.get());
		this.dropSelf(ModBlocks.flower_autumn_shrub.get());
		
		// terrain blocks
		this.dropSelf(ModBlocks.crackedsand.get());
		
	} // end generate()


} // end class
