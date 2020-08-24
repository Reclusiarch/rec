package com.bixel.rec.config;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecConfig implements IRecConfig 
{
    private final List<CachedResolvableConfigValue<?, ?>> cachedConfigValues = new ArrayList<>();
    private final List<CachedPrimitiveValue<?>> cachedPrimitiveValues = new ArrayList<>();

    @Override
    public void clearCache() 
    {
        cachedConfigValues.forEach(CachedResolvableConfigValue::clearCache);
        cachedPrimitiveValues.forEach(CachedPrimitiveValue::clearCache);
    }

    @Override
    public <T, R> void addCachedValue(CachedResolvableConfigValue<T, R> configValue) 
    {
        cachedConfigValues.add(configValue);
    }

    @Override
    public <T> void addCachedValue(CachedPrimitiveValue<T> configValue) 
    {
        cachedPrimitiveValues.add(configValue);
    }
}
