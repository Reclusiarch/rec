package com.bixel.rec.energy;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.bixel.rec.energy.EnergyCompatUtils.EnergyType;
import com.bixel.rec.math.FloatingLong;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ForgeStrictEnergyHandler implements IStrictEnergyHandler {

    private final IEnergyStorage storage;

    public ForgeStrictEnergyHandler(IEnergyStorage storage) {
        this.storage = storage;
    }

    @Override
    public int getEnergyContainerCount() {
        return 1;
    }

    @Override
    public FloatingLong getEnergy(int container) {
        return container == 0 ? EnergyType.FORGE.convertFrom(storage.getEnergyStored()) : FloatingLong.ZERO;
    }

    @Override
    public void setEnergy(int container, FloatingLong energy) {
        //Not implemented or directly needed
    }

    @Override
    public FloatingLong getMaxEnergy(int container) {
        return container == 0 ? EnergyType.FORGE.convertFrom(storage.getMaxEnergyStored()) : FloatingLong.ZERO;
    }

    @Override
    public FloatingLong getNeededEnergy(int container) {
        return container == 0 ? EnergyType.FORGE.convertFrom(Math.max(0, storage.getMaxEnergyStored() - storage.getEnergyStored())) : FloatingLong.ZERO;
    }

    @Override
    public FloatingLong insertEnergy(int container, FloatingLong amount, @Nonnull FluidAction action) {
        if (container == 0 && storage.canReceive()) {
            int inserted = storage.receiveEnergy(EnergyType.FORGE.convertToAsInt(amount), action.simulate());
            if (inserted > 0) {
                //Only bother converting back if any was able to be inserted
                return amount.subtract(EnergyType.FORGE.convertFrom(inserted));
            }
        }
        return amount;
    }

    @Override
    public FloatingLong extractEnergy(int container, FloatingLong amount, @Nonnull FluidAction action) {
        if (container == 0 && storage.canExtract()) {
            return EnergyType.FORGE.convertFrom(storage.extractEnergy(EnergyType.FORGE.convertToAsInt(amount), action.simulate()));
        }
        return FloatingLong.ZERO;
    }
}
