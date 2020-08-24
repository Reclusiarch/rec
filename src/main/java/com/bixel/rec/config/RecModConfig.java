package com.bixel.rec.config;

import java.nio.file.Path;
import java.util.function.Function;

import com.bixel.rec.RecMod;
import com.electronwill.nightconfig.core.file.CommentedFileConfig;

import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.config.ConfigFileTypeHandler;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.loading.FMLPaths;

//https://github.com/mekanism/Mekanism/blob/a8572791196d30e9108d9db7679c634a42bd7838/src/main/java/mekanism/common/config/MekanismModConfig.java#L17
/**
 * Custom {@link ModConfig} implementation that allows for rerouting the server config from being in the worlds folder to being in the normal config folder. This allows
 * for us to use the built in sync support, without the extra hassle of having to explain to people where the config file is, or require people in single player to edit
 * the config each time they make a new world.
 */
public class RecModConfig extends ModConfig
{

    private static final RecConfigFileTypeHandler REC_TOML = new RecConfigFileTypeHandler();

    private final IRecConfig recConfig;
    
    public RecModConfig(ModContainer container, IRecConfig config) 
    {
        super(config.getConfigType(), config.getConfigSpec(), container, RecMod.MOD_NAME + "/" + config.getFileName() + ".toml");
        this.recConfig = config;
    }

    @Override
    public ConfigFileTypeHandler getHandler() 
    {
        return REC_TOML;
    }

    public void clearCache() 
    {
    	recConfig.clearCache();
    }

    private static class RecConfigFileTypeHandler extends ConfigFileTypeHandler 
    {

        private static Path getPath(Path configBasePath) 
        {
            //Intercept server config path reading for Rec configs and reroute it to the normal config directory
            if (configBasePath.endsWith("serverconfig")) 
            {
                return FMLPaths.CONFIGDIR.get();
            }
            return configBasePath;
        }

        @Override
        public Function<ModConfig, CommentedFileConfig> reader(Path configBasePath) 
        {
            return super.reader(getPath(configBasePath));
        }

        @Override
        public void unload(Path configBasePath, ModConfig config) 
        {
            super.unload(getPath(configBasePath), config);
        }
    }
}
