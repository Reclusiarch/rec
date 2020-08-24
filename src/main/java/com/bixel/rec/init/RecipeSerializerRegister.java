package com.bixel.rec.init;

import com.bixel.rec.RecMod;
import com.bixel.rec.recipes.IModRecipe;
import com.bixel.rec.recipes.ModRecipeSerializer;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RecipeSerializerRegister 
{
	public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, RecMod.MOD_ID);
	
	public static final RegistryObject<ModRecipeSerializer> EXAMPLE_SERIALIZER = RECIPE_SERIALIZERS.register("example", () -> new ModRecipeSerializer());
	
	//https://www.youtube.com/watch?v=Ri0Mqv_FXA4&list=PLaevjqy3XufYmltqo0eQusnkKVN7MpTUe&index=48&t=3s
	//public static final IRecipeSerializer<ModRecipe> EXAMPLE_RECIPE_SERIALIZER = new ModRecipeSerializer();
	public static final IRecipeType<IModRecipe> MOD_RECIPE = registerType(IModRecipe.RECIPE_TYPE_ID);

	private static <T extends IRecipeType> T registerType(ResourceLocation recipeTypeId) 
	{
		return (T) Registry.register(Registry.RECIPE_TYPE, recipeTypeId, new RegistryType<>());
	}
	
	private static class RegistryType<T extends IRecipe<?>> implements IRecipeType<T>
	{
		@Override
		public String toString() //returns the ResourceLocation...
		{
			return Registry.RECIPE_TYPE.getKey(this).toString();
		}
	}
}
