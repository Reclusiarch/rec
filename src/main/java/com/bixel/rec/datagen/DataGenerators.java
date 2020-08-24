package com.bixel.rec.datagen;

import com.bixel.rec.RecMod;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = RecMod.MOD_ID, bus = Bus.MOD)
public class DataGenerators 
{
	//public static final DeferredRegister<GlobalLootModifierSerializer<?>> LOOT = DeferredRegister.create(ForgeRegistries.LOOT_MODIFIER_SERIALIZERS, RecMod.MOD_ID);
	//public static final DeferredRegister<IRecipeSerializer<?>> RECIPES = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, RecMod.MOD_ID);
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event)
	{
		DataGenerator generator = event.getGenerator();
		generator.addProvider(new Recipes(generator));
		generator.addProvider(new LootTables(generator));
	}

}
