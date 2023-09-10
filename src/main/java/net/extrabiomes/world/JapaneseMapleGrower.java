package net.extrabiomes.world;

import net.extrabiomes.init.ModFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class JapaneseMapleGrower extends AbstractTreeGrower 
{
	@Override
	protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource pRandom, boolean pHasFlowers) 
	{
		return ModFeatures.JAPANESE_MAPLE_TREE;
	}
} // end class
