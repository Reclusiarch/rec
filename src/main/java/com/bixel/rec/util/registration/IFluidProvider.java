package com.bixel.rec.util.registration;

import javax.annotation.Nonnull;

import net.minecraft.fluid.Fluid;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fluids.FluidStack;

public interface IFluidProvider 
{
	@Nonnull
    Fluid getFluid();

    //Note: Uses FluidStack in case we want to check NBT or something
    default boolean fluidMatches(FluidStack other) 
    {
        return getFluid() == other.getFluid();
    }

    @Nonnull
    default FluidStack getFluidStack(int size) 
    {
        return new FluidStack(getFluid(), size);
    }

    default ResourceLocation getRegistryName() 
    {
        return getFluid().getRegistryName();
    }
    
    default String getName() 
    {
        return getRegistryName().getPath();
    }


    default ITextComponent getTextComponent() 
    {
        return getFluid().getAttributes().getDisplayName(getFluidStack(1));
    }


    default String getTranslationKey() 
    {
        return getFluid().getAttributes().getTranslationKey();
    }
}
