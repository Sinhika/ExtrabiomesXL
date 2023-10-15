package net.extrabiomes.content;

import java.util.List;

import net.extrabiomes.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
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
	public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;
	
	public MiniLogBlock(Properties pProperties, String toolTip) 
	{
		super(Apothem, pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(NORTH, Boolean.valueOf(false))
        		.setValue(EAST, Boolean.valueOf(false)).setValue(SOUTH, Boolean.valueOf(false))
        		.setValue(WEST, Boolean.valueOf(false)).setValue(UP, Boolean.valueOf(false))
        		.setValue(DOWN, Boolean.valueOf(false)).setValue(AXIS, Direction.Axis.Y));

		this.tooltipKey = toolTip;

	} // end ctor

	@Override
	public boolean propagatesSkylightDown(BlockState pState, BlockGetter pReader, BlockPos pPos) 
	{
		return true;
	}
	
	@Override
	public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) 
	{
		// for now, until we get model right.
		return  Shapes.block();
	}


	@Override
   protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) 
   {
	      pBuilder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN, AXIS);
   }

   @Override
   public BlockState getStateForPlacement(BlockPlaceContext pContext) 
   {
	   	BlockGetter pLevel = pContext.getLevel();
	   	BlockPos pPos = pContext.getClickedPos();
	   	Direction.Axis faceAxis =  pContext.getClickedFace().getAxis();
	   
	   	LeavesBlock myLeafBlock = ModBlocks.leaves_sakura.get();
	   
		BlockState blockstate = pLevel.getBlockState(pPos.below());
		BlockState blockstate1 = pLevel.getBlockState(pPos.above());
		BlockState blockstate2 = pLevel.getBlockState(pPos.north());
		BlockState blockstate3 = pLevel.getBlockState(pPos.east());
		BlockState blockstate4 = pLevel.getBlockState(pPos.south());
		BlockState blockstate5 = pLevel.getBlockState(pPos.west());
		
		return this.defaultBlockState()
				.setValue(AXIS, faceAxis)
				.setValue(DOWN,Boolean.valueOf(blockstate.is(this) || blockstate.is(myLeafBlock)))
				.setValue(UP, Boolean.valueOf(blockstate1.is(this) || blockstate1.is(myLeafBlock)))
				.setValue(NORTH, Boolean.valueOf(blockstate2.is(this) || blockstate2.is(myLeafBlock)))
				.setValue(EAST, Boolean.valueOf(blockstate3.is(this) || blockstate3.is(myLeafBlock)))
				.setValue(SOUTH, Boolean.valueOf(blockstate4.is(this) || blockstate4.is(myLeafBlock)))
				.setValue(WEST, Boolean.valueOf(blockstate5.is(this) || blockstate5.is(myLeafBlock)));
   } // end getStateForPlacement()

   /**
    * Update the provided state given the provided neighbor direction and neighbor state, returning a new state.
    * For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately
    * returns its solidified counterpart.
    * Note that this method should ideally consider only the specific direction passed in.
    */
   public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, 
		   						 BlockPos pCurrentPos, BlockPos pFacingPos) 
   {
         boolean flag = pFacingState.is(this) || pFacingState.is(ModBlocks.leaves_sakura.get());
         return pState.setValue(PROPERTY_BY_DIRECTION.get(pFacing), Boolean.valueOf(flag));
   }

   /**
    * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed
    * blockstate.
    * @deprecated call via {@link net.minecraft.world.level.block.state.BlockBehaviour.BlockStateBase#rotate} whenever
    * possible. Implementing/overriding is fine.
    */
   @Override
   public BlockState rotate(BlockState pState, Rotation pRot) 
   {
      return rotatePillar(pState, pRot);
   }

   public static BlockState rotatePillar(BlockState pState, Rotation pRotation) 
   {
      switch (pRotation) {
         case COUNTERCLOCKWISE_90:
         case CLOCKWISE_90:
            switch ((Direction.Axis)pState.getValue(AXIS)) {
               case X:
                  return pState.setValue(AXIS, Direction.Axis.Z);
               case Z:
                  return pState.setValue(AXIS, Direction.Axis.X);
               default:
                  return pState;
            }
         default:
            return pState;
      }
   }
   
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
