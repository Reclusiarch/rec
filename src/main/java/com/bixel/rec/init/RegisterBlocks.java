package com.bixel.rec.init;

import java.util.ArrayList;
import java.util.List;

import com.bixel.rec.RecMod;
import com.bixel.rec.objects.blocks.MachineFrameBlock;
import com.bixel.rec.objects.blocks.ModelBlock;
import com.bixel.rec.objects.blocks.QuarryBlock;
import com.bixel.rec.objects.blocks.RecFurnaceBlock;
import com.bixel.rec.objects.blocks.RecOreBlock;
import com.bixel.rec.objects.ore.OreLibrary;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegisterBlocks 
{

	public static final List<RecOreBlock> ORES = new ArrayList<RecOreBlock>();
	
	static 
	{
        for (OreLibrary ore : OreLibrary.values()) 
        {
            ORES.add(ore.block);
        }
    }
	
	//public static final IForgeRegistry<RecOreBlock> ORE_BLOCKS = RegistryManager.ACTIVE.getRegistry(RecOreBlock.class);
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, RecMod.MOD_ID);
	/**
	 * ORES
	 */
	public static final RegistryObject<RecOreBlock> ORE_AZURITE = BLOCKS.register("ore_azurite", () -> OreLibrary.AZURITE.block);
	public static final RegistryObject<RecOreBlock> ORE_BORNITE = BLOCKS.register("ore_bornite", () -> OreLibrary.BORNITE.block);
	public static final RegistryObject<RecOreBlock> ORE_CHALCOPYRITE = BLOCKS.register("ore_chalcopyrite", () -> OreLibrary.CHALCOPYRITE.block);
	public static final RegistryObject<RecOreBlock> ORE_COBALTITE = BLOCKS.register("ore_cobaltite", () -> OreLibrary.COBALTITE.block);
	public static final RegistryObject<RecOreBlock> ORE_DIGENITE = BLOCKS.register("ore_digenite", () -> OreLibrary.DIGENITE.block);
	public static final RegistryObject<RecOreBlock> ORE_DIOPTASE = BLOCKS.register("ore_dioptase", () -> OreLibrary.DIOPTASE.block);
	public static final RegistryObject<RecOreBlock> ORE_ERYTHRITE = BLOCKS.register("ore_erythrite", () -> OreLibrary.ERYTHRITE.block);
	public static final RegistryObject<RecOreBlock> ORE_GOETHITE = BLOCKS.register("ore_goethite", () -> OreLibrary.GOETHITE.block);
	public static final RegistryObject<RecOreBlock> ORE_HEMATITE = BLOCKS.register("ore_hematite", () -> OreLibrary.HEMATITE.block);
	public static final RegistryObject<RecOreBlock> ORE_LIMONITE = BLOCKS.register("ore_limonite", () -> OreLibrary.LIMONITE.block);
	public static final RegistryObject<RecOreBlock> ORE_MAGNETITE = BLOCKS.register("ore_magnetite", () -> OreLibrary.MAGNETITE.block);
	public static final RegistryObject<RecOreBlock> ORE_MOLYBDENITE = BLOCKS.register("ore_molybdenite", () -> OreLibrary.MOLYBDENITE.block);
	public static final RegistryObject<RecOreBlock> ORE_SKUTTERUDITE = BLOCKS.register("ore_skutterudite", () -> OreLibrary.SKUTTERUDITE.block);
	public static final RegistryObject<RecOreBlock> ORE_WOLFRAMITE = BLOCKS.register("ore_wolframite", () -> OreLibrary.WOLFRAMITE.block);
	/**
	 * RESOURCES
	 */
	public static final RegistryObject<Block> SALT_BLOCK = BLOCKS.register("salt_block", () -> new Block(Block.Properties.from(Blocks.CLAY)));
	/**
	 * MODELS
	 */
	public static final RegistryObject<Block> MACHINE_FRAME = BLOCKS.register("machine_frame", () -> 
		new MachineFrameBlock(Block.Properties.create(Material.IRON, MaterialColor.IRON).hardnessAndResistance(2f, 30.0f).sound(SoundType.METAL)));
	/**
	 * MACHINE BLOCKS
	 */
	public static final RegistryObject<Block> QUARRY = BLOCKS.register("quarry", () -> 
		new QuarryBlock(Block.Properties.create(Material.IRON, MaterialColor.IRON)));
	public static final RegistryObject<Block> FIRST_BLOCK = BLOCKS.register("first_block", () -> 
		new ModelBlock(Block.Properties.create(Material.IRON, MaterialColor.IRON)));
	public static final RegistryObject<RecFurnaceBlock> FURNACE_BLOCK = BLOCKS.register("rec_furnace_block", () -> 
		new RecFurnaceBlock(Block.Properties.create(Material.IRON, MaterialColor.IRON)));
}
