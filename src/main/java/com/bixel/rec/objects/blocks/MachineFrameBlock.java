package com.bixel.rec.objects.blocks;

import java.util.stream.Stream;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

public class MachineFrameBlock extends ModelBlock
{
	private static final VoxelShape SHAPE = Stream.of(
			Block.makeCuboidShape(0, 0, 0, 5, 16, 5),
			Block.makeCuboidShape(0, 0, 11, 5, 16, 16),
			Block.makeCuboidShape(11, 0, 0, 16, 16, 5),
			Block.makeCuboidShape(11, 0, 11, 16, 16, 16),
			Block.makeCuboidShape(5, 0, 11, 11, 5, 16),
			Block.makeCuboidShape(5, 11, 11, 11, 16, 16),
			Block.makeCuboidShape(5, 0, 0, 11, 5, 5),
			Block.makeCuboidShape(5, 11, 0, 11, 16, 5),
			Block.makeCuboidShape(11, 0, 5, 16, 5, 11),
			Block.makeCuboidShape(11, 11, 5, 16, 16, 11),
			Block.makeCuboidShape(0, 0, 5, 5, 5, 11),
			Block.makeCuboidShape(0, 11, 5, 5, 16, 11)
			).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();
	
	public MachineFrameBlock(Properties properties) 
	{
		super(properties);
	}
	
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	{
		switch (state.get(FACING))
		{
		case NORTH:
			return SHAPE;
		case SOUTH:
			return SHAPE;
		case WEST:
			return SHAPE;
		case EAST:
			return SHAPE;
		default:
			return SHAPE;
		}
	}
}
