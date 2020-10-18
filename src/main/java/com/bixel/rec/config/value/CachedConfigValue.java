package com.bixel.rec.config.value;

import com.bixel.rec.config.IRecConfig;

import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public class CachedConfigValue<T> extends CachedResolvableConfigValue<T, T> 
{
	protected CachedConfigValue(IRecConfig config, ConfigValue<T> internal) 
	{
        super(config, internal);
    }

    public static <T> CachedConfigValue<T> wrap(IRecConfig config, ConfigValue<T> internal) 
    {
        return new CachedConfigValue<>(config, internal);
    }

    @Override
    protected T resolve(T encoded) 
    {
        return encoded;
    }

    @Override
    protected T encode(T value) 
    {
        return value;
    }
}
