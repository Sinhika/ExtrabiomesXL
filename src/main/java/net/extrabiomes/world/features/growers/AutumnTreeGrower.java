package net.extrabiomes.world.features.growers;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class AutumnTreeGrower extends AbstractTreeGrower 
{
	protected final ResourceKey<ConfiguredFeature<?, ?>> fancy_tree;
	protected final ResourceKey<ConfiguredFeature<?, ?>> normal_tree;
	protected final ResourceKey<ConfiguredFeature<?, ?>> fancy_tree_bees;
	protected final ResourceKey<ConfiguredFeature<?, ?>> normal_tree_bees;

	
	public AutumnTreeGrower(ResourceKey<ConfiguredFeature<?, ?>> normal_treeIn, ResourceKey<ConfiguredFeature<?, ?>> fancy_treeIn,
							ResourceKey<ConfiguredFeature<?, ?>> normal_wbees_treeIn, ResourceKey<ConfiguredFeature<?, ?>> fancy_wbees_treeIn)
	{
		super();
		this.fancy_tree = fancy_treeIn;
		this.normal_tree = normal_treeIn;
		this.fancy_tree_bees = fancy_wbees_treeIn;
		this.normal_tree_bees = normal_wbees_treeIn;
	}

	@Override
	protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource pRandom, boolean pHasBees)
	{
		if (pRandom.nextInt(3) != 0 )
		{
			return pHasBees ? this.fancy_tree_bees : this.fancy_tree;
		}
		else {
			return pHasBees ? this.normal_tree_bees : this.normal_tree;
		}
	} // end getConfiguredFeature()

	
} // end class
