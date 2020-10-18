package com.bixel.rec.config.value;

import com.bixel.rec.config.IRecConfig;

import net.minecraftforge.common.ForgeConfigSpec.EnumValue;

public class CachedEnumValue<T extends Enum<T>> extends CachedConfigValue<T> 
{
	private CachedEnumValue(IRecConfig config, EnumValue<T> internal) 
	{
        super(config, internal);
    }

    public static <T extends Enum<T>> CachedEnumValue<T> wrap(IRecConfig config, EnumValue<T> internal) 
    {
        return new CachedEnumValue<>(config, internal);
    }
}
