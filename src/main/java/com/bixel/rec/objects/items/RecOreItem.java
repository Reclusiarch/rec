package com.bixel.rec.objects.items;

import java.util.List;

import com.bixel.rec.init.ModSetup;
import com.bixel.rec.objects.blocks.RecOreBlock;
import com.bixel.rec.util.helper.KeyboardHelper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class RecOreItem extends BlockItem
{
	public RecOreItem(RecOreBlock blockIn) 
	{
		super(blockIn, new Item.Properties().group(ModSetup.itemGroup));
		this.oreInfo = blockIn.category();
		//handled by the register event
		//this.setRegistryName(blockIn.getRegistryName());
	}
	
	private String oreInfo = "";

	//use Ctrl + Space to see inherited methods
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		if(KeyboardHelper.isHoldingShift())
		{
			tooltip.add(new StringTextComponent("Break this ore into a dust to process."));
		}
		else
		{
			//color codes https://minecraft.tools/en/color-code.php
			tooltip.add(new StringTextComponent("Category: " + "\u00A7e" + oreInfo + "\u00A77" + "."));
		}
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
}
