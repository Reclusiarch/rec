package com.bixel.rec.capabilties;

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
        if (Capabilities.CAPABILITY_CHARGE == null) 
        {
            return new CompoundNBT();
        } else 
        {
            return (CompoundNBT) Capabilities.CAPABILITY_CHARGE.writeNBT(charge, null);
        }
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) 
    {
        if (Capabilities.CAPABILITY_CHARGE != null) 
        {
        	Capabilities.CAPABILITY_CHARGE.readNBT(charge, null, nbt);
        }
    }
}
