package com.bixel.rec.config.value;

import java.util.ArrayList;
import java.util.List;

import com.bixel.rec.config.IRecConfig;

import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public class CachedPrimitiveValue<T> 
{

    protected final ConfigValue<T> internal;
    private List<Runnable> invalidationListeners;
    protected boolean resolved;

    protected CachedPrimitiveValue(IRecConfig config, ConfigValue<T> internal) 
    {
        this.internal = internal;
        config.addCachedValue(this);
    }

    public void addInvalidationListener(Runnable listener) 
    {
        if (invalidationListeners == null) 
        {
            invalidationListeners = new ArrayList<>();
        }
        invalidationListeners.add(listener);
    }

    public void clearCache() {
        resolved = false;
        if (invalidationListeners != null) 
        {
            invalidationListeners.forEach(Runnable::run);
        }
    }
}
