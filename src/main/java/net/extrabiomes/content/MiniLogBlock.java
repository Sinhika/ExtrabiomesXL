package net.extrabiomes.content;

import java.util.List;

import net.extrabiomes.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;

public class MiniLogBlock extends PipeBlock 
{
	protected final String tooltipKey;
	protected final static float Apothem = 0.3125F;
	
	public MiniLogBlock(Properties pProperties, String toolTip) 
	{
		super(Apothem, pProperties);
		this.shapeByIndex = this.makeLogShapes(Apothem);
        this.registerDefaultState(this.stateDefinition.any().setValue(NORTH, Boolean.valueOf(false))
        		.setValue(EAST, Boolean.valueOf(false)).setValue(SOUTH, Boolean.valueOf(false))
        		.setValue(WEST, Boolean.valueOf(false)).setValue(UP, Boolean.valueOf(false))
        		.setValue(DOWN, Boolean.valueOf(false)));

		this.tooltipKey = toolTip;

	} // end ctor

	private VoxelShape[] makeLogShapes(float pApothem)
	{
		float f = 0.5F - pApothem;
		float f1 = 0.5F + pApothem;
		VoxelShape voxelshape = Shapes.box((double)(f), (double)(f), (double)(f), 
											(double)(f1), (double)(f1), (double)(f1));
		
		VoxelShape[] avoxelshape = new VoxelShape[DIRECTIONS.length];

	}
	
	
	@Override
	public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) 
	{
		// TODO Auto-generated method stub
		return super.getShape(pState, pLevel, pPos, pContext);
	}


	@Override
   protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) 
   {
	      pBuilder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN);
   }

   @Override
   public BlockState getStateForPlacement(BlockPlaceContext pContext) 
   {
	      return this.getStateForPlacement(pContext.getLevel(), pContext.getClickedPos());
   }

   public BlockState getStateForPlacement(BlockGetter pLevel, BlockPos pPos) 
   {
	   LeavesBlock myLeafBlock = ModBlocks.leaves_sakura.get();
	   
		BlockState blockstate = pLevel.getBlockState(pPos.below());
		BlockState blockstate1 = pLevel.getBlockState(pPos.above());
		BlockState blockstate2 = pLevel.getBlockState(pPos.north());
		BlockState blockstate3 = pLevel.getBlockState(pPos.east());
		BlockState blockstate4 = pLevel.getBlockState(pPos.south());
		BlockState blockstate5 = pLevel.getBlockState(pPos.west());
		return this.defaultBlockState()
				.setValue(DOWN,Boolean.valueOf(blockstate.is(this)))
				.setValue(UP, Boolean.valueOf(blockstate1.is(this) || blockstate1.is(myLeafBlock)))
				.setValue(NORTH, Boolean.valueOf(blockstate2.is(this) || blockstate2.is(myLeafBlock)))
				.setValue(EAST, Boolean.valueOf(blockstate3.is(this) || blockstate3.is(myLeafBlock)))
				.setValue(SOUTH, Boolean.valueOf(blockstate4.is(this) || blockstate4.is(myLeafBlock)))
				.setValue(WEST, Boolean.valueOf(blockstate5.is(this) || blockstate5.is(myLeafBlock)));
   } // end getStateForPlacement

   @Override
    public BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction,
            boolean simulate)
    {
        BlockState toolModifiedState;
        
        if (ToolActions.AXE_STRIP == toolAction) 
        {
        	// TODO create stripped versions of logs and make a match table.
//	            toolModifiedState = ModBlocks.stripped_blaze_log.get().defaultBlockState()
//	                    .setValue(RotatedPillarBlock.AXIS, state.getValue(RotatedPillarBlock.AXIS));
            toolModifiedState = super.getToolModifiedState(state, context, toolAction, simulate);
        }
        else {
            toolModifiedState = super.getToolModifiedState(state, context, toolAction, simulate);
        }
        return toolModifiedState;
    }

	@Override
	public void appendHoverText(ItemStack pStack, BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) 
	{
		super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
		pTooltip.add(Component.translatable(tooltipKey));
	}

	@Override
   public boolean isPathfindable(BlockState pState, BlockGetter pLevel, BlockPos pPos, PathComputationType pType) 
   {
	   return false;
   }
	
} // end-class
