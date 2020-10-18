package com.bixel.rec.capabilties;

import com.bixel.rec.energy.IStrictEnergyHandler;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class Capabilities 
{
	private Capabilities() {}
	
	@CapabilityInject(IHeat.class)
	public static Capability<IHeat> CAPABILITY_HEAT = null;
	
	@CapabilityInject(ICharge.class)
	public static Capability<ICharge> CAPABILITY_CHARGE =  null;
	
	@CapabilityInject(IStrictEnergyHandler.class)
	public static Capability<IStrictEnergyHandler> STRICT_ENERGY_CAPABILITY = null;
}
