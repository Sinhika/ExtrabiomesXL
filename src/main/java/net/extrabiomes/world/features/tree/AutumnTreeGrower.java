package net.extrabiomes.world.features.tree;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class AutumnTreeGrower extends AbstractTreeGrower 
{
	protected final ResourceKey<ConfiguredFeature<?, ?>> fancy_tree;
	protected final ResourceKey<ConfiguredFeature<?, ?>> normal_tree;
	
	
	public AutumnTreeGrower(ResourceKey<ConfiguredFeature<?, ?>> normal_treeIn, ResourceKey<ConfiguredFeature<?, ?>> fancy_treeIn ) 
	{
		super();
		this.fancy_tree = fancy_treeIn;
		this.normal_tree = normal_treeIn;
	}

	@Override
	protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource pRandom, boolean pHasFlowers)
	{
		if (pRandom.nextInt(3) != 0 )
		{
			return this.fancy_tree;
		}
		else {
			return this.normal_tree;
		}
	} // end getConfiguredFeature()

	
} // end class
