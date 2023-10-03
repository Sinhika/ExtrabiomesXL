package net.extrabiomes.client;

import net.extrabiomes.init.ModBlocks;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.state.BlockState;

public class LeafBlockColors implements BlockColor 
{

	@Override
	public int getColor(BlockState pState, BlockAndTintGetter pLevel, BlockPos pPos, int pTintIndex) 
	{
		if (pState.is(ModBlocks.leaves_acacia.get()))
		{
			return FoliageColor.get(0.9F, 0.1F);
		}
		else if (pState.is(ModBlocks.leaves_cypress.get()))
		{
			return 0xe5fff3;
		}
		else if (pState.is(ModBlocks.leaves_fir.get()))
		{
			return FoliageColor.getEvergreenColor();
		}
		else if (pState.is(ModBlocks.leaves_redwood.get()))
		{
			return FoliageColor.getDefaultColor();
		}
		else if (pState.is(ModBlocks.vine_gloriosa.get()))
		{
			return 0xffffff;
		}
		else if (pState.is(ModBlocks.vine_spanish_moss.get()))
		{
			return ((pLevel != null && pPos != null) 
					 ? BiomeColors.getAverageFoliageColor(pLevel, pPos) : FoliageColor.getDefaultColor());
		}
		else if (pState.is(ModBlocks.leaves_sakura.get()))
		{
			return 0xffffff;
		}
		else if (pState.is(ModBlocks.leafpile.get()))
		{
			return ((pLevel != null && pPos != null) 
					 ? BiomeColors.getAverageFoliageColor(pLevel, pPos) : FoliageColor.getDefaultColor());
		}
		else {
			return FoliageColor.get(0.5D, 1.0D);
		}
	} // end getColor()

} // end class
