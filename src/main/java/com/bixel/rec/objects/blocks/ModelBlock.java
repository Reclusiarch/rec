package com.bixel.rec.objects.blocks;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ModelBlock extends Block
{
	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
	
	public ModelBlock(Properties properties) 
	{
		super(properties); 
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
	}
	
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) { }

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context)
	{
		//enables this to be placed "facing towards you" when placed
		return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
	}
	
	@Override
	public BlockState rotate(BlockState state, Rotation rot)
	{
		return state.with(FACING, rot.rotate(state.get(FACING)));
	}
	
	@Override
	public BlockState mirror(BlockState state, Mirror mirrorIn)
	{
		return state.rotate(mirrorIn.toRotation(state.get(FACING)));
	}
	
	//use this method to add blockstate properties to the block
	protected void fillStateContainer(Builder<Block, BlockState> builder)
	{
		//adds the BlockState Properties of Direction
		builder.add(FACING);
	}

	protected void onBlockPlacedby(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) 
	{
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
	}


}
