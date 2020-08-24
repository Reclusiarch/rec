package com.bixel.rec.config;

import java.util.function.BooleanSupplier;

import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public class CachedBooleanValue extends CachedPrimitiveValue<Boolean> implements BooleanSupplier 
{

    private boolean cachedValue;

    private CachedBooleanValue(IRecConfig config, ConfigValue<Boolean> internal) 
    {
        super(config, internal);
    }

    public static CachedBooleanValue wrap(IRecConfig config, ConfigValue<Boolean> internal) 
    {
        return new CachedBooleanValue(config, internal);
    }

    public boolean get() {
        if (!resolved) {
            //If we don't have a cached value or need to resolve it again, get it from the actual ConfigValue
            cachedValue = internal.get();
            resolved = true;
        }
        return cachedValue;
    }

    @Override
    public boolean getAsBoolean() 
    {
        return get();
    }

    public void set(boolean value) 
    {
        internal.set(value);
        cachedValue = value;
    }
}