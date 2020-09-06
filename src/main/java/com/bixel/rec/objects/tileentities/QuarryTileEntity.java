package com.bixel.rec.objects.tileentities;

import com.bixel.rec.init.RegisterTileEntities;
import com.bixel.rec.util.helper.NBTHelper;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.FluidState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;

public class QuarryTileEntity extends TileEntity implements ITickableTileEntity
{
	public int x, y, z, tick;
	boolean initialized = false;
	
	public QuarryTileEntity(final TileEntityType<?> tileEntityTypeIn) 
	{
		super(tileEntityTypeIn);
	}

	public QuarryTileEntity()
	{
		this(RegisterTileEntities.QUARRY.get());
	}

	@Override
	public void tick() 
	{
		if(!initialized)
		{
			init();
		}
		tick++;
		if(tick == 40)
		{
			tick = 0;
			if(y > 2) execute();
		}
	}
	
	private void init() 
	{
		initialized = true;
		x = this.pos.getX() -1;
		y = this.pos.getY() -1;
		z = this.pos.getZ() -1;
		tick = 0;
	}
	
	private void execute() //3x3
	{
		int index = 0;
		Block[] blocksRemoved = new Block[9];
		for(int x = 0; x < 3; x++)
		{
			for(int z = 0; z < 3; z++)
			{
				BlockPos posToBreak = new BlockPos(this.x + x, this.y, this.z + z);
				blocksRemoved[index] = this.world.getBlockState(posToBreak).getBlock();
				destroyBlock(posToBreak, true);
				index++;
			}
		}
		this.y--; //makes it go down
	}
	
	private boolean destroyBlock(BlockPos pos, boolean dropBlock)
	{
		BlockState blockState = world.getBlockState(pos);
		if(blockState.isAir(world, pos)) return false;
		else
		{
			FluidState fluidState = world.getFluidState(pos);
			world.playEvent(2001, pos, Block.getStateId(blockState)); //2001 is the sound of the block being broken
			if(dropBlock)
			{
				TileEntity tileEntity = blockState.hasTileEntity() ? world.getTileEntity(pos) : null;
				Block.spawnDrops(blockState, world, this.pos.add(0, 1.5, 0), tileEntity);
				//Block.spawnDrops(blockState, world, this.pos.add(0, 1.5, 0), tileEntity, entity, ItemStack.EMPTY);
			}
			return world.setBlockState(pos, fluidState.getBlockState(), 3); //flag 3 to update Fluids
			//meaning fluids are unaffected but updated by animations
		}
	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound)
	{
		compound.put("initvalues", NBTHelper.toNBT(this));
		return super.write(compound);
	}
	
	@Override
	public void read(BlockState blockState, CompoundNBT compound)
	{
		//super.read(blockState, compound);
		super.read(blockState, compound);
		CompoundNBT initValues = compound.getCompound("initvalues");
		if(initValues != null)
		{
			this.x = initValues.getInt("x");
			this.y = initValues.getInt("y");
			this.z = initValues.getInt("z");
			this.tick = 0;
			initialized = true;
			return;
		}
		init();
	}
}
