package com.bixel.rec.objects.tiles;

import com.bixel.rec.init.RegisterTileEntities;

import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class BaseTileEntity extends TileEntity implements ITickableTileEntity
{
	public BaseTileEntity(final TileEntityType<?> tileEntityTypeIn) 
	{
		super(tileEntityTypeIn);
	}
	
	public BaseTileEntity()
	{
		this(RegisterTileEntities.FIRST_BLOCK.get());
	}

	@Override
	public void tick() 
	{
		if(world.isRemote)
		{
			
		}		
	}
}
