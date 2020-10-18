package com.bixel.rec.config.value;

import java.util.function.Predicate;

import javax.annotation.Nullable;

import com.bixel.rec.config.IRecConfig;
import com.bixel.rec.math.FloatingLong;
import com.bixel.rec.math.FloatingLongSupplier;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.Builder;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public class CachedFloatingLongValue extends CachedResolvableConfigValue<FloatingLong, String> implements FloatingLongSupplier
{
	private static final Predicate<Object> VALIDATOR = object -> tryGetValue(object) != null;
    public static final Predicate<Object> POSITIVE = object -> {
        FloatingLong value = tryGetValue(object);
        return value != null && value.greaterThan(FloatingLong.ZERO);
    };

    @Nullable
    private static FloatingLong tryGetValue(Object object) {
        if (object instanceof String) {
            try {
                return FloatingLong.parseFloatingLong((String) object);
            } catch (NumberFormatException ignored) {
            }
        }
        return null;
    }

    private CachedFloatingLongValue(IRecConfig config, ConfigValue<String> internal) {
        super(config, internal);
    }

    public static CachedFloatingLongValue define(IRecConfig config, ForgeConfigSpec.Builder builder, String comment, String path, FloatingLong defaultValue) {
        return define(config, builder, comment, path, defaultValue, false);
    }

    public static CachedFloatingLongValue define(IRecConfig config, ForgeConfigSpec.Builder builder, String comment, String path, FloatingLong defaultValue, boolean worldRestart) {
        return define(config, builder, comment, path, defaultValue, worldRestart, VALIDATOR);
    }

    public static CachedFloatingLongValue define(IRecConfig config, ForgeConfigSpec.Builder builder, String comment, String path, FloatingLong defaultValue,
          Predicate<Object> validator) {
        return define(config, builder, comment, path, defaultValue, false, validator);
    }

    public static CachedFloatingLongValue define(IRecConfig config, Builder builder, String comment, String path, FloatingLong defaultValue, boolean worldRestart,
          Predicate<Object> validator) {
        if (worldRestart) {
            builder.worldRestart();
        }
        return new CachedFloatingLongValue(config, builder.comment(comment).define(path, defaultValue.toString(), validator));
    }

    @Override
    protected FloatingLong resolve(String encoded) {
        return FloatingLong.parseFloatingLong(encoded, true);
    }

    @Override
    protected String encode(FloatingLong value) {
        return value.toString();
    }
}
