package com.bixel.rec.init;

import com.bixel.rec.RecMod;
import com.bixel.rec.objects.items.RecOreItem;
import com.bixel.rec.objects.items.SledgeHammer;
import com.bixel.rec.objects.materials.RecMaterials;

import net.minecraft.item.BlockItem;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegisterItems 
{
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RecMod.MOD_ID);
	
	static Properties prop = new Item.Properties().group(ModSetup.itemGroup);
	
	public static final RegistryObject<Item> DUST_TAILINGS = ITEMS.register("dust_tailings", () -> new Item(prop));
	public static final RegistryObject<Item> SMALLDUST_TAILINGS = ITEMS.register("smalldust_tailings", () -> new Item(prop));
	
	/**
	 * CHUNKS
	 */
	public static final RegistryObject<Item> CHUNKS_AZURITE = ITEMS.register("chunks_azurite", () -> new Item(prop));
	public static final RegistryObject<Item> CHUNKS_BORNITE = ITEMS.register("chunks_bornite", () -> new Item(prop));
	public static final RegistryObject<Item> CHUNKS_COBALTITE = ITEMS.register("chunks_cobaltite", () -> new Item(prop));
	public static final RegistryObject<Item> CHUNKS_DIGENITE = ITEMS.register("chunks_digenite", () -> new Item(prop));
	public static final RegistryObject<Item> CHUNKS_DIOPTASE = ITEMS.register("chunks_dioptase", () -> new Item(prop));
	public static final RegistryObject<Item> CHUNKS_ERYTHRITE = ITEMS.register("chunks_erythrite", () -> new Item(prop));
	public static final RegistryObject<Item> CHUNKS_GOETHITE = ITEMS.register("chunks_goethite", () -> new Item(prop));
	public static final RegistryObject<Item> CHUNKS_HEMATITE = ITEMS.register("chunks_hematite", () -> new Item(prop));
	public static final RegistryObject<Item> CHUNKS_LIMONITE = ITEMS.register("chunks_limonite", () -> new Item(prop));
	public static final RegistryObject<Item> CHUNKS_MAGNETITE = ITEMS.register("chunks_magnetite", () -> new Item(prop));
	public static final RegistryObject<Item> CHUNKS_MOLYBDENITE = ITEMS.register("chunks_molybdenite", () -> new Item(prop));
	public static final RegistryObject<Item> CHUNKS_SKUTTERUDITE = ITEMS.register("chunks_skutterudite", () -> new Item(prop));
	public static final RegistryObject<Item> CHUNKS_WOLFRAMITE = ITEMS.register("chunks_wolframite", () -> new Item(prop));
	
	/**
	 * GANGUE
	 */
	public static final RegistryObject<Item> GANGUE_AZURITE_DUST = ITEMS.register("gangue_azurite", () -> new Item(prop));
	public static final RegistryObject<Item> GANGUE_BORNITE_DUST = ITEMS.register("gangue_bornite", () -> new Item(prop));
	public static final RegistryObject<Item> GANGUE_COBALTITE_DUST = ITEMS.register("gangue_cobaltite", () -> new Item(prop));
	public static final RegistryObject<Item> GANGUE_DIGENITE_DUST = ITEMS.register("gangue_digenite", () -> new Item(prop));
	public static final RegistryObject<Item> GANGUE_DIOPTASE_DUST = ITEMS.register("gangue_dioptase", () -> new Item(prop));
	public static final RegistryObject<Item> GANGUE_ERYTHRITE_DUST = ITEMS.register("gangue_erythrite", () -> new Item(prop));
	public static final RegistryObject<Item> GANGUE_GOETHITE_DUST = ITEMS.register("gangue_goethite", () -> new Item(prop));
	public static final RegistryObject<Item> GANGUE_HEMATITE_DUST = ITEMS.register("gangue_hematite", () -> new Item(prop));
	public static final RegistryObject<Item> GANGUE_LIMONITE_DUST = ITEMS.register("gangue_limonite", () -> new Item(prop));
	public static final RegistryObject<Item> GANGUE_MAGNETITE_DUST = ITEMS.register("gangue_magnetite", () -> new Item(prop));
	public static final RegistryObject<Item> GANGUE_MOLYBDENITE_DUST = ITEMS.register("gangue_molybdenite", () -> new Item(prop));
	public static final RegistryObject<Item> GANGUE_SKUTTERUDITE_DUST = ITEMS.register("gangue_skutterudite", () -> new Item(prop));
	public static final RegistryObject<Item> GANGUE_WOLFRAMITE_DUST = ITEMS.register("gangue_wolframite", () -> new Item(prop));
	
	/**
	 * DUST
	 */
	public static final RegistryObject<Item> DUST_AZURITE = ITEMS.register("dust_azurite", () -> new Item(prop));
	public static final RegistryObject<Item> DUST_BORNITE = ITEMS.register("dust_bornite", () -> new Item(prop));
	public static final RegistryObject<Item> DUST_COBALTITE = ITEMS.register("dust_cobaltite", () -> new Item(prop));
	public static final RegistryObject<Item> DUST_DIGENITE = ITEMS.register("dust_digenite", () -> new Item(prop));
	public static final RegistryObject<Item> DUST_DIOPTASE = ITEMS.register("dust_dioptase", () -> new Item(prop));
	public static final RegistryObject<Item> DUST_ERYTHRITE = ITEMS.register("dust_erythrite", () -> new Item(prop));
	public static final RegistryObject<Item> DUST_HEMATITE = ITEMS.register("dust_hematite", () -> new Item(prop));
	public static final RegistryObject<Item> DUST_GOETHITE = ITEMS.register("dust_goethite", () -> new Item(prop));
	public static final RegistryObject<Item> DUST_LIMONITE = ITEMS.register("dust_limonite", () -> new Item(prop));
	public static final RegistryObject<Item> DUST_MAGNETITE = ITEMS.register("dust_magnetite", () -> new Item(prop));
	public static final RegistryObject<Item> DUST_MOLYBDENITE = ITEMS.register("dust_molybdenite", () -> new Item(prop));
	public static final RegistryObject<Item> DUST_SKUTTERUDITE = ITEMS.register("dust_skutterudite", () -> new Item(prop));
	public static final RegistryObject<Item> DUST_WOLFRAMITE = ITEMS.register("dust_wolframite", () -> new Item(prop));

	/**
	 * SMALL DUST
	 */
	public static final RegistryObject<Item> SMALLDUST_AZURITE = ITEMS.register("smalldust_azurite", () -> new Item(prop));
	public static final RegistryObject<Item> SMALLDUST_BORNITE = ITEMS.register("smalldust_bornite", () -> new Item(prop));
	public static final RegistryObject<Item> SMALLDUST_COBALTITE = ITEMS.register("smalldust_cobaltite", () -> new Item(prop));
	public static final RegistryObject<Item> SMALLDUST_DIGENITE = ITEMS.register("smalldust_digenite", () -> new Item(prop));
	public static final RegistryObject<Item> SMALLDUST_DIOPTASE = ITEMS.register("smalldust_dioptase", () -> new Item(prop));
	public static final RegistryObject<Item> SMALLDUST_ERYTHRITE = ITEMS.register("smalldust_erythrite", () -> new Item(prop));
	public static final RegistryObject<Item> SMALLDUST_GOETHITE = ITEMS.register("smalldust_goethite", () -> new Item(prop));
	public static final RegistryObject<Item> SMALLDUST_HEMATITE = ITEMS.register("smalldust_hematite", () -> new Item(prop));
	public static final RegistryObject<Item> SMALLDUST_LIMONITE = ITEMS.register("smalldust_limonite", () -> new Item(prop));
	public static final RegistryObject<Item> SMALLDUST_MAGNETITE = ITEMS.register("smalldust_magnetite", () -> new Item(prop));
	public static final RegistryObject<Item> SMALLDUST_MOLYBDENITE = ITEMS.register("smalldust_molybdenite", () -> new Item(prop));
	public static final RegistryObject<Item> SMALLDUST_SKUTTERUDITE = ITEMS.register("smalldust_skutterudite", () -> new Item(prop));
	public static final RegistryObject<Item> SMALLDUST_WOLFRAMITE = ITEMS.register("smalldust_wolframite", () -> new Item(prop));
	
	/**
	 * ORES
	 */
	public static final RegistryObject<RecOreItem> ORE_AZURITE = ITEMS.register("ore_azurite", () -> new RecOreItem(RegisterBlocks.ORE_AZURITE.get()));
	public static final RegistryObject<RecOreItem> ORE_BORNITE = ITEMS.register("ore_bornite", () -> new RecOreItem(RegisterBlocks.ORE_BORNITE.get()));
	public static final RegistryObject<RecOreItem> ORE_COBALTITE = ITEMS.register("ore_cobaltite", () -> new RecOreItem(RegisterBlocks.ORE_COBALTITE.get()));
	public static final RegistryObject<RecOreItem> ORE_DIGENITE = ITEMS.register("ore_digenite", () -> new RecOreItem(RegisterBlocks.ORE_DIGENITE.get()));
	public static final RegistryObject<RecOreItem> ORE_DIOPTASE = ITEMS.register("ore_dioptase", () -> new RecOreItem(RegisterBlocks.ORE_DIOPTASE.get()));
	public static final RegistryObject<RecOreItem> ORE_ERYTHRITE = ITEMS.register("ore_erythrite", () -> new RecOreItem(RegisterBlocks.ORE_ERYTHRITE.get()));
	public static final RegistryObject<RecOreItem> ORE_GOETHITE = ITEMS.register("ore_goethite", () -> new RecOreItem(RegisterBlocks.ORE_GOETHITE.get()));
	public static final RegistryObject<RecOreItem> ORE_HEMATITE = ITEMS.register("ore_hematite", () -> new RecOreItem(RegisterBlocks.ORE_HEMATITE.get()));
	public static final RegistryObject<RecOreItem> ORE_LIMONITE = ITEMS.register("ore_limonite", () -> new RecOreItem(RegisterBlocks.ORE_LIMONITE.get()));
	public static final RegistryObject<RecOreItem> ORE_MAGNETITE = ITEMS.register("ore_magnetite", () -> new RecOreItem(RegisterBlocks.ORE_MAGNETITE.get()));
	public static final RegistryObject<RecOreItem> ORE_MOLYBDENITE = ITEMS.register("ore_molybdenite", () -> new RecOreItem(RegisterBlocks.ORE_MOLYBDENITE.get()));
	public static final RegistryObject<RecOreItem> ORE_SKUTTERUDITE = ITEMS.register("ore_skutterudite", () -> new RecOreItem(RegisterBlocks.ORE_SKUTTERUDITE.get()));
	public static final RegistryObject<RecOreItem> ORE_WOLFRAMITE = ITEMS.register("ore_wolframite", () -> new RecOreItem(RegisterBlocks.ORE_WOLFRAMITE.get()));
	/**
	 * TOOLS
	 */
	public static final RegistryObject<SledgeHammer> IRON_SLEDGEHAMMER = ITEMS.register("tool_iron_sledgehammer", () -> new SledgeHammer(RecMaterials.SLEDGEHAMMER_IRON));
	public static final RegistryObject<SledgeHammer> STONE_SLEDGEHAMMER = ITEMS.register("tool_stone_sledgehammer", () -> new SledgeHammer(RecMaterials.SLEDGEHAMMER_STONE));
	/**
	 * BUCKETS
	 */
	//public static final RegistryObject<BucketItem> EXAMPLE_BUCKET = ITEMS.register("example_bucket", () -> new BucketItem(() -> RegisterFluids.ALKALINE_SOLUTION.getFluid(), prop.maxStackSize(1)));
	/**
	 * RESOURCES
	 */
	public static final RegistryObject<Item> SALT_BLOCK = ITEMS.register("salt_block", () -> new BlockItem(RegisterBlocks.SALT_BLOCK.get(), prop));
	public static final RegistryObject<Item> SALT = ITEMS.register("salt", () -> new Item(prop));
	public static final RegistryObject<Item> SMALL_SALT = ITEMS.register("small_salt", () -> new Item(prop));
	
	/**
	 * MACHINES
	 */
	public static final RegistryObject<Item> MACHINE_FRAME = ITEMS.register("machine_frame", () -> new BlockItem(RegisterBlocks.MACHINE_FRAME.get(), prop));
	public static final RegistryObject<Item> REC_FURNACE_BLOCK = ITEMS.register("rec_furnace_block", () -> new BlockItem(RegisterBlocks.FURNACE_BLOCK.get(), prop));
	public static final RegistryObject<Item> QUARRY = ITEMS.register("quarry", () -> new BlockItem(RegisterBlocks.QUARRY.get(), prop));
	public static final RegistryObject<Item> FIRST_BLOCK = ITEMS.register("first_block", () -> new BlockItem(RegisterBlocks.FIRST_BLOCK.get(), prop));
}
