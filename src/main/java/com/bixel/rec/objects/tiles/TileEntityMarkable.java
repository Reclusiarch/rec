package com.bixel.rec.objects.tiles;

import java.util.Objects;

import javax.annotation.Nonnull;

import com.bixel.rec.util.ModHelperUtils;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.world.World;

/**
 * Extension of TileEntity that adds various helpers we use across the majority of our Tiles even those that are not an instance of TileEntityMekanism. Additionally we
 * improve the performance of markDirty by not firing neighbor updates unless the markDirtyComparator method is overridden.
 * based on https://github.com/mekanism/Mekanism/blob/b78f912cb509962adbcabfc6eb1e1da720bb8dd3/src/main/java/mekanism/common/tile/base/TileEntityUpdateable.java#L24
 */
public abstract class TileEntityMarkable extends TileEntity
{
	public TileEntityMarkable(TileEntityType<?> type) { super(type); }
	
	   /**
     * Like getWorld(), but for when you _know_ world won't be null
     *
     * @return The world!
     */
    @Nonnull
    protected World getWorldNN() 
    {
        return Objects.requireNonNull(getWorld(), "getWorldNN called before world set");
    }

    public boolean isRemote() 
    {
        return getWorldNN().isRemote();
    }

    /**
     * Used for checking if we need to update comparators. Note only called on the server
     */
    public void markDirtyComparator() { }
    
    @Override
    public void markDirty() {
        markDirty(true);
    }

    public void markDirty(boolean recheckBlockState) 
    {
        //Copy of the base impl of markDirty in TileEntity, except only updates comparator state when something changed
        // and if our block supports having a comparator signal, instead of always doing it
        if (world != null) 
        {
        	/*
            if (recheckBlockState) 
            { 
            	cachedBlockState = world.getBlockState(pos); 
            }*/
            ModHelperUtils.markChunkDirty(world, pos);
            if (!isRemote()) 
            {
                markDirtyComparator();
            }
        }
    }
}
