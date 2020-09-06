package com.bixel.rec.objects.blocks;

import com.bixel.rec.init.RegisterTileEntities;

import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class FirstBlock extends ModelBlock
{
	public FirstBlock(Properties properties) 
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
    	return RegisterTileEntities.FIRST_BLOCK.get().create();
    }
}
