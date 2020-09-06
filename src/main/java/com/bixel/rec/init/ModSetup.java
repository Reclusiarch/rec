package com.bixel.rec.init;

import com.bixel.rec.RecMod;
import com.bixel.rec.data.CapabilityCharge;
import com.bixel.rec.data.CapabilityHeat;
import com.bixel.rec.world.gen.OreGen;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = RecMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)

public class ModSetup 
{
	public static ItemGroup itemGroup = new ItemGroup("rec")
	{
	    @Override
	    public ItemStack createIcon() 
	    {
	    	//return new ItemStack(BlockInit.chiseled_stone_cross);
	        return new ItemStack(RegisterBlocks.MACHINE_FRAME.get());
	    }
	};
	
    public static void init(final FMLCommonSetupEvent event) 
    {
    	//explore this
    	//https://github.com/McJty/YouTubeModding14/tree/master/src/main/java/com/mcjty/mytutorial/network
        //Networking.registerMessages();
    	CapabilityCharge.register();
    	CapabilityHeat.register();
    	OreGen.GenerateOre();
    	
    	//we could attach capabilities to existing non-mod items - even entities
    	//see >> https://www.youtube.com/watch?v=W3SS7yP4B2s
        //MinecraftForge.EVENT_BUS.addListener(ChargeEventHandler::onAttachCapabilitiesEvent);
        //MinecraftForge.EVENT_BUS.addListener(ChargeEventHandler::onAttackEvent);
        //MinecraftForge.EVENT_BUS.addListener(ChargeEventHandler::onDeathEvent);
    }
}
