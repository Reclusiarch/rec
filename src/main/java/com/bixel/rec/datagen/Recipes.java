package com.bixel.rec.datagen;

import java.util.function.Consumer;

import com.bixel.rec.init.RegisterBlocks;

import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraftforge.common.Tags;

public class Recipes extends RecipeProvider
{
	public Recipes(DataGenerator generatorIn) { super(generatorIn); }
	
	protected void registerRecipes(Consumer<IFinishedRecipe> consumer)
	{
		//just an example - dont use
		ShapedRecipeBuilder.shapedRecipe(RegisterBlocks.MACHINE_FRAME.get())
        .patternLine("xxx")
        .patternLine("x#x")
        .patternLine("xxx")
        .key('x', Tags.Items.COBBLESTONE)
        .key('#', Tags.Items.DYES_RED)
        .setGroup("mytutorial")
        .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
        .build(consumer);
	}

}
