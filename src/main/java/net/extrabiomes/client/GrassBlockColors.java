package net.extrabiomes.client;

import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.block.state.BlockState;

public class GrassBlockColors implements BlockColor 
{

	@Override
	public int getColor(BlockState pState, BlockAndTintGetter pLevel, BlockPos pPos, int pTintIndex) 
	{
		return (pLevel != null && pPos != null) 
				 ? BiomeColors.getAverageGrassColor(pLevel, pPos) : GrassColor.getDefaultColor();
	}

} // end class
