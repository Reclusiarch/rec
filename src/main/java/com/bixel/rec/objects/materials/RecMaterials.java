package com.bixel.rec.objects.materials;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;

//https://www.youtube.com/watch?v=WB3hTA-mOtA
public enum RecMaterials implements IItemTier
{
	SLEDGEHAMMER_IRON(3, 6, 500, 3, 0, net.minecraft.item.Items.IRON_INGOT),
	SLEDGEHAMMER_STONE(3, 6, 20, 3, 0, net.minecraft.item.Items.STONE);
	
	private float attackDamage, miningSpeed;
	private int durability, harvestLevel, enchantAbility;
	private Item repairMaterial;
	
	private RecMaterials(float attackDamage, float miningSpeed, int durability, int harvestLevel, int enchantAbility, Item repairMaterial)
	{
		this.attackDamage = attackDamage;
		this.miningSpeed = miningSpeed;
		this.durability = durability;
		this.harvestLevel = harvestLevel;
		this.enchantAbility = enchantAbility;
		this.repairMaterial = repairMaterial;
	}

	@Override
	public int getMaxUses() 
	{
		return durability;
	}

	@Override
	public float getEfficiency() 
	{
		return miningSpeed;
	}

	@Override
	public float getAttackDamage() 
	{
		return attackDamage;
	}

	@Override
	public int getHarvestLevel() 
	{
		return harvestLevel;
	}

	@Override
	public int getEnchantability() 
	{
		return enchantAbility;
	}

	@Override
	public Ingredient getRepairMaterial() 
	{
		return Ingredient.fromItems(this.repairMaterial);
	}
}
