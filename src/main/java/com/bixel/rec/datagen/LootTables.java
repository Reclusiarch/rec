package com.bixel.rec.datagen;

import com.bixel.rec.init.RegisterBlocks;
import com.bixel.rec.init.RegisterItems;

import net.minecraft.data.DataGenerator;

public class LootTables extends BaseLootTableProvider
{
	public LootTables(DataGenerator dataGeneratorIn) { super(dataGeneratorIn); }

    @Override
    protected void addTables() 
    {
    	lootTables.put(RegisterBlocks.ORE_AZURITE.get(), createOreMiningTable(RegisterBlocks.ORE_AZURITE.get(), RegisterItems.CHUNKS_AZURITE.get()));
    	lootTables.put(RegisterBlocks.ORE_BORNITE.get(), createOreMiningTable(RegisterBlocks.ORE_BORNITE.get(), RegisterItems.CHUNKS_BORNITE.get()));
    	lootTables.put(RegisterBlocks.ORE_COBALTITE.get(), createOreMiningTable(RegisterBlocks.ORE_COBALTITE.get(), RegisterItems.CHUNKS_COBALTITE.get()));
    	lootTables.put(RegisterBlocks.ORE_DIGENITE.get(), createOreMiningTable(RegisterBlocks.ORE_DIGENITE.get(), RegisterItems.CHUNKS_DIGENITE.get()));
    	lootTables.put(RegisterBlocks.ORE_DIOPTASE.get(), createOreMiningTable(RegisterBlocks.ORE_DIOPTASE.get(), RegisterItems.CHUNKS_DIOPTASE.get()));
    	lootTables.put(RegisterBlocks.ORE_ERYTHRITE.get(), createOreMiningTable(RegisterBlocks.ORE_ERYTHRITE.get(), RegisterItems.CHUNKS_ERYTHRITE.get()));
    	lootTables.put(RegisterBlocks.ORE_GOETHITE.get(), createOreMiningTable(RegisterBlocks.ORE_GOETHITE.get(), RegisterItems.CHUNKS_GOETHITE.get()));
    	lootTables.put(RegisterBlocks.ORE_HEMATITE.get(), createOreMiningTable(RegisterBlocks.ORE_HEMATITE.get(), RegisterItems.CHUNKS_HEMATITE.get()));
    	lootTables.put(RegisterBlocks.ORE_LIMONITE.get(), createOreMiningTable(RegisterBlocks.ORE_LIMONITE.get(), RegisterItems.CHUNKS_LIMONITE.get()));
    	lootTables.put(RegisterBlocks.ORE_MAGNETITE.get(), createOreMiningTable(RegisterBlocks.ORE_MAGNETITE.get(), RegisterItems.CHUNKS_MAGNETITE.get()));
    	lootTables.put(RegisterBlocks.ORE_MOLYBDENITE.get(), createOreMiningTable(RegisterBlocks.ORE_MAGNETITE.get(), RegisterItems.CHUNKS_MOLYBDENITE.get()));
    	lootTables.put(RegisterBlocks.ORE_SKUTTERUDITE.get(), createOreMiningTable(RegisterBlocks.ORE_SKUTTERUDITE.get(), RegisterItems.CHUNKS_SKUTTERUDITE.get()));
    	lootTables.put(RegisterBlocks.ORE_WOLFRAMITE.get(), createOreMiningTable(RegisterBlocks.ORE_WOLFRAMITE.get(), RegisterItems.CHUNKS_WOLFRAMITE.get()));
        //lootTables.put(Registration.FIRSTBLOCK.get(), createStandardTable("firstblock", Registration.FIRSTBLOCK.get()));
        //lootTables.put(Registration.FANCYBLOCK.get(), createStandardTable("fancyblock", Registration.FANCYBLOCK.get()));
    }
}
