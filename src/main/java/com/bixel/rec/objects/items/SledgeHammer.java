package com.bixel.rec.objects.items;

import java.util.List;

import com.bixel.rec.init.ModSetup;
import com.bixel.rec.objects.materials.RecMaterials;
import com.bixel.rec.util.helper.KeyboardHelper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class SledgeHammer extends PickaxeItem
{
	//check loot tables
		//https://github.com/skylinerw/guides/blob/master/java/loot%20tables.md#duplicates
		//deeper tutorial https://www.youtube.com/watch?v=-DHk61mLjHA
	public SledgeHammer(RecMaterials Material) 
	{
		super(Material, -1, -3f, new Item.Properties()
				.maxStackSize(1)
				.group(ModSetup.itemGroup));
		//setRegistryName(RecMod.loc("iron_mallet"));
	}
	
	/*
	@Override
	public boolean hasEffect(ItemStack stack) //glowy enchanted effect on item
	{
		return true;
	}*/
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		if(KeyboardHelper.isHoldingShift())
		{
			tooltip.add(new StringTextComponent("Use to break ores into dust"));
		}
		else
		{
			//color codes https://minecraft.tools/en/color-code.php
			tooltip.add(new StringTextComponent("Hold" + "\u00A7e" + " Shift " + "\u00A77" + "for more info"));
		}
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
		
}
