package com.bixel.rec.data;

import javax.annotation.Nullable;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityHeat 
{
	@CapabilityInject(IHeat.class)
	public static Capability<IHeat> CAPABILITY_HEAT = null;
	
	public static void register()
	{
		CapabilityManager.INSTANCE.register(IHeat.class, new Storage(), DefaultHeat::new);
	}
	
	public static class Storage implements Capability.IStorage<IHeat>
	{
        @Nullable
        @Override
        public INBT writeNBT(Capability<IHeat> capability, IHeat instance, Direction side) 
        {
            CompoundNBT tag = new CompoundNBT();
            tag.putInt("heat", instance.getHeat());
            return tag;
        }

        @Override
        public void readNBT(Capability<IHeat> capability, IHeat instance, Direction side, INBT nbt) 
        {
            int heat = ((CompoundNBT) nbt).getInt("heat");
            instance.setHeat(heat);
        }
	}
}
