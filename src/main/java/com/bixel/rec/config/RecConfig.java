package com.bixel.rec.config;

import java.nio.file.Path;

import com.bixel.rec.RecMod;

import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

public class RecConfig
{
	public static final WorldConfig world = new WorldConfig();
	public static final GeneralConfig general = new GeneralConfig();
	
    public static void registerConfigs(ModLoadingContext modLoadingContext) 
    {
        ModContainer modContainer = modLoadingContext.getActiveContainer();
        registerConfig(modContainer, world);
        registerConfig(modContainer, general);
    }
    
    public static final Path CONFIG_DIR;

    static { CONFIG_DIR = FMLPaths.getOrCreateGameRelativePath(FMLPaths.CONFIGDIR.get().resolve(RecMod.MOD_NAME), RecMod.MOD_NAME); }

    /**
     * Creates a mod config so that {@link net.minecraftforge.fml.config.ConfigTracker} will track it and sync server configs from server to client.
     */
    public static void registerConfig(ModContainer modContainer, IRecConfig config) 
    {
    	RecModConfig modConfig = new RecModConfig(modContainer, config);
        if (config.addToContainer()) 
        {
            modContainer.addConfig(modConfig);
        }
    }

}
