package com.bixel.rec.data;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class ChargeProvider implements ICapabilitySerializable<CompoundNBT>
{
	private final DefaultCharge charge = new DefaultCharge();
    private final LazyOptional<ICharge> chargeOptional = LazyOptional.of(() -> charge);

    public void invalidate() 
    {
        chargeOptional.invalidate();
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) 
    {
        return chargeOptional.cast();
    }

    @Override
    public CompoundNBT serializeNBT() 
    {
        if (CapabilityCharge.CAPABILITY_CHARGE == null) 
        {
            return new CompoundNBT();
        } else 
        {
            return (CompoundNBT) CapabilityCharge.CAPABILITY_CHARGE.writeNBT(charge, null);
        }
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) 
    {
        if (CapabilityCharge.CAPABILITY_CHARGE != null) 
        {
        	CapabilityCharge.CAPABILITY_CHARGE.readNBT(charge, null, nbt);
        }
    }
}
