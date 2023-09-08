package net.extrabiomes.world;

import net.extrabiomes.init.ModFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class AutumnTreeGrower extends AbstractTreeGrower 
{
	protected final String leaf_color;
	
	public AutumnTreeGrower(String leaf_color) 
	{
		super();
		this.leaf_color = leaf_color;
	}

	@Override
	protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource pRandom, boolean pHasFlowers)
	{
		ResourceKey<ConfiguredFeature<?, ?>> fancy_tree;
		ResourceKey<ConfiguredFeature<?, ?>> normal_tree;
		
		switch (this.leaf_color)
		{
			case "umber":
				fancy_tree = ModFeatures.FANCY_UMBER_AUTUMN_TREE;
				normal_tree = ModFeatures.UMBER_AUTUMN_TREE;
				break;
			default:
				fancy_tree = null;
				normal_tree = null;
		}
		
		if (pRandom.nextInt(3) != 0 )
		{
			return fancy_tree;
		}
		else {
			return normal_tree;
		}
	} // end getConfiguredFeature()

	
} // end class
