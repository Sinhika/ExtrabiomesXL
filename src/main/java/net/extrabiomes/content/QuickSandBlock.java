package net.extrabiomes.content;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SandBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.Vec3;

public class QuickSandBlock extends SandBlock 
{

	public QuickSandBlock() 
	{
		super(14406560, BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.SNARE)
						.strength(4.0F).sound(SoundType.SAND).forceSolidOn().noCollission());
	}

	public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) 
	{
	      pEntity.makeStuckInBlock(pState, new Vec3(0.25D, (double)0.05F, 0.25D));
	}

} // end class
