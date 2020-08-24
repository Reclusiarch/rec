package com.bixel.rec.objects.blocks;

import com.bixel.rec.init.TileEntityRegister;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class QuarryBlock extends Block
{
	public QuarryBlock(Properties properties) 
	{
		super(properties);
	}
	
	@Override
    public boolean hasTileEntity(BlockState state)
    {
        return true;
    }

	@Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world)
    {
    	return TileEntityRegister.QUARRY.get().create();
    }
}
