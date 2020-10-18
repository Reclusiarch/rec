package com.bixel.rec.energy;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import com.bixel.rec.capabilties.Capabilities;
import com.bixel.rec.util.CapabilityUtils;

import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

@ParametersAreNonnullByDefault
public class StrictEnergyCompat implements IEnergyCompat {

    @Override
    public boolean isUsable() {
        return true;
    }

    @Nonnull
    @Override
    public Capability<?> getCapability() {
        return Capabilities.STRICT_ENERGY_CAPABILITY;
    }

    @Override
    public boolean isCapabilityPresent(ICapabilityProvider provider, @Nullable Direction side) {
        return CapabilityUtils.getCapability(provider, Capabilities.STRICT_ENERGY_CAPABILITY, side).isPresent();
    }

    @Nonnull
    @Override
    public LazyOptional<IStrictEnergyHandler> getHandlerAs(IStrictEnergyHandler handler) {
        return LazyOptional.of(() -> handler);
    }

    @Nonnull
    @Override
    public LazyOptional<IStrictEnergyHandler> getLazyStrictEnergyHandler(ICapabilityProvider provider, @Nullable Direction side) {
        return CapabilityUtils.getCapability(provider, Capabilities.STRICT_ENERGY_CAPABILITY, side);
    }
}