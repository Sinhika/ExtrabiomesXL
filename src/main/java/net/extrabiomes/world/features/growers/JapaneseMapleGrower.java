package net.extrabiomes.world.features.growers;

import net.extrabiomes.init.ModConfiguredFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class JapaneseMapleGrower extends AbstractTreeGrower 
{
	@Override
	protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource pRandom, boolean pHasFlowers) 
	{
		return ModConfiguredFeatures.JAPANESE_MAPLE_TREE;
	}
} // end class
