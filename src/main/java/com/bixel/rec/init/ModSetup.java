package com.bixel.rec.init;

import com.bixel.rec.RecMod;
import com.bixel.rec.capabilties.CapabilityCharge;
import com.bixel.rec.capabilties.CapabilityHeat;

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
    	//OreGen.GenerateOre();
    }
}
