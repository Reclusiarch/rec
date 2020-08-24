package com.bixel.rec.recipes;

import javax.annotation.Nonnull;

import com.bixel.rec.RecMod;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public interface IModRecipe extends IRecipe<RecipeWrapper>
{
	ResourceLocation RECIPE_TYPE_ID = RecMod.loc("example");
	
	@Nonnull
	@Override
	default IRecipeType<?> getType()
	{
		return Registry.RECIPE_TYPE.getValue(RECIPE_TYPE_ID).get();
	}
	
	@Override
	default boolean canFit(int width, int height)
	{
		return false;
	}
	
	Ingredient getInput();
}
