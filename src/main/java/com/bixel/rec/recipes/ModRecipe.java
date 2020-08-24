package com.bixel.rec.recipes;

import com.bixel.rec.init.RecipeSerializerRegister;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public class ModRecipe implements IModRecipe
{
	private final ResourceLocation id;
	private Ingredient input;
	private ItemStack output;
	
	public ModRecipe(ResourceLocation id, Ingredient input, ItemStack output)
	{
		this.id = id; this.input = input; this.output = output;
	}

	@Override
	public boolean matches(RecipeWrapper inv, World worldIn) 
	{
		//test the item in the 1st slot - to check if it is valid
		if(this.input.test(inv.getStackInSlot(0))) //can have multiple slots
		{
			return true;
		}
		return false;
	}

	@Override
	public ItemStack getCraftingResult(RecipeWrapper inv) { return this.output; }

	@Override
	public ItemStack getRecipeOutput() { return this.output; }

	@Override
	public ResourceLocation getId() { return this.id; }

	@Override
	public IRecipeSerializer<?> getSerializer() { return RecipeSerializerRegister.EXAMPLE_SERIALIZER.get(); }

	@Override
	public Ingredient getInput() { return this.input; }
	
	@Override
	public NonNullList<Ingredient> getIngredients()
	{
		return NonNullList.from(null, this.input);
	}

}
