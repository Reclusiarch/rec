package com.bixel.rec.objects.blocks;

import com.bixel.rec.objects.ore.OreCategory;

import net.minecraft.block.Block;
import net.minecraft.block.OreBlock;
import net.minecraft.block.material.Material;

public class RecOreBlock extends OreBlock
{
	private OreCategory oreCategory;
	public String category() { return oreCategory.getName(); }
	
	public RecOreBlock(OreCategory oc, int hardnessIn, int resistanceIn, int harvestLevel) 
	{
		super(Block.Properties.create(Material.ROCK).hardnessAndResistance(hardnessIn, resistanceIn).harvestLevel(harvestLevel));
		this.oreCategory = oc;	
	}
}
