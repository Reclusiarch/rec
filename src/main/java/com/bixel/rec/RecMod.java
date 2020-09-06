package com.bixel.rec;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bixel.rec.config.RecConfig;
import com.bixel.rec.init.RegisterBlocks;
import com.bixel.rec.init.ClientSetup;
import com.bixel.rec.init.RegisterContainers;
import com.bixel.rec.init.RegisterFluids;
import com.bixel.rec.init.RegisterItems;
import com.bixel.rec.init.ModSetup;
import com.bixel.rec.init.RegisterRecipeSerializer;
import com.bixel.rec.init.RegisterTileEntities;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod.EventBusSubscriber(modid = RecMod.MOD_ID, bus = Bus.MOD)
@Mod("rec")
public class RecMod 
{
	public static RecMod instance;
	public static final String MOD_ID = "rec";
	public static final String MOD_NAME = "Bixel's Mod";

	public static final String VERSION = "0.1.0";
	
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
	
    public static ResourceLocation loc(String name)
    {
    	return new ResourceLocation(MOD_ID, name);
    }
    
    public RecMod() 
    {
    	final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    	RecConfig.registerConfigs(ModLoadingContext.get());

    	RegisterFluids.FLUIDS_ADVANCED.register(modEventBus);
    	//FluidRegister.FLUIDS.register(modEventBus);
    	RegisterBlocks.BLOCKS.register(modEventBus);
        RegisterItems.ITEMS.register(modEventBus);
        RegisterRecipeSerializer.RECIPE_SERIALIZERS.register(modEventBus);
        RegisterTileEntities.TILE_ENTITY_TYPES.register(modEventBus);
        RegisterContainers.CONTAINER_TYPES.register(modEventBus);

    	modEventBus.addListener(ModSetup::init);
    	modEventBus.addListener(ClientSetup::init);
    	instance = this;
    }

    /*
    private void setup(final FMLCommonSetupEvent event)
    {
    	OreGen.GenerateOre();
    }

    private void doClientStuff(final FMLClientSetupEvent event) 
    {
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        InterModComms.sendTo("rec", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
    }*/
    
    //done before the server starts...
    @SubscribeEvent
    public static void loadCompleteEvent(FMLLoadCompleteEvent event)
    {
    	//OreGen.GenerateOre();
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) 
    {

    }
}
