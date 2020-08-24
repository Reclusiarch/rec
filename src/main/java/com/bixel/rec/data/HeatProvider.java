package com.bixel.rec.data;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class HeatProvider implements ICapabilitySerializable<CompoundNBT>
{
	private final DefaultHeat heat = new DefaultHeat();
    private final LazyOptional<IHeat> heatOptional = LazyOptional.of(() -> heat);

    public void invalidate() 
    {
    	heatOptional.invalidate();
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) 
    {
        return heatOptional.cast();
    }

    @Override
    public CompoundNBT serializeNBT() 
    {
        if (CapabilityHeat.CAPABILITY_HEAT == null) 
        {
            return new CompoundNBT();
        } else 
        {
            return (CompoundNBT) CapabilityHeat.CAPABILITY_HEAT.writeNBT(heat, null);
        }
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) 
    {
        if (CapabilityHeat.CAPABILITY_HEAT != null) 
        {
        	CapabilityHeat.CAPABILITY_HEAT.readNBT(heat, null, nbt);
        }
    }
}
