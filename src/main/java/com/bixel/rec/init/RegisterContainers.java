package com.bixel.rec.init;

import com.bixel.rec.RecMod;
import com.bixel.rec.container.RecFurnaceContainer;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegisterContainers 
{
	public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = DeferredRegister.create(ForgeRegistries.CONTAINERS, RecMod.MOD_ID);
	
	public static final RegistryObject<ContainerType<RecFurnaceContainer>> REC_FURNACE_CONTAINER = CONTAINER_TYPES.register("rec_furnace_block",
			() -> IForgeContainerType.create(RecFurnaceContainer::new));
}
