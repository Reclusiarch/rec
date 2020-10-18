package com.bixel.rec.capabilties;

import javax.annotation.Nullable;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityCharge 
{
	/*
	@CapabilityInject(ICharge.class)
	public static Capability<ICharge> CAPABILITY_CHARGE =  null;*/
	
	public static void register()
	{
		CapabilityManager.INSTANCE.register(ICharge.class, new Storage(), DefaultCharge::new);
	}
	
	public static class Storage implements Capability.IStorage<ICharge>
	{
        @Nullable
        @Override
        public INBT writeNBT(Capability<ICharge> capability, ICharge instance, Direction side) 
        {
            CompoundNBT tag = new CompoundNBT();
            tag.putInt("charge", instance.getCharge());
            return tag;
        }

        @Override
        public void readNBT(Capability<ICharge> capability, ICharge instance, Direction side, INBT nbt) 
        {
            int charge = ((CompoundNBT) nbt).getInt("charge");
            instance.setCharge(charge);
        }
	}
}
