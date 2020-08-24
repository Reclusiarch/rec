package com.bixel.rec.datagen;

import com.bixel.rec.init.BlockRegister;
import com.bixel.rec.init.ItemRegister;

import net.minecraft.data.DataGenerator;

public class LootTables extends BaseLootTableProvider
{
	public LootTables(DataGenerator dataGeneratorIn) { super(dataGeneratorIn); }

    @Override
    protected void addTables() 
    {
    	lootTables.put(BlockRegister.ORE_AZURITE.get(), createOreMiningTable(BlockRegister.ORE_AZURITE.get(), ItemRegister.CHUNKS_AZURITE.get()));
    	lootTables.put(BlockRegister.ORE_BORNITE.get(), createOreMiningTable(BlockRegister.ORE_BORNITE.get(), ItemRegister.CHUNKS_BORNITE.get()));
    	lootTables.put(BlockRegister.ORE_COBALTITE.get(), createOreMiningTable(BlockRegister.ORE_COBALTITE.get(), ItemRegister.CHUNKS_COBALTITE.get()));
    	lootTables.put(BlockRegister.ORE_DIGENITE.get(), createOreMiningTable(BlockRegister.ORE_DIGENITE.get(), ItemRegister.CHUNKS_DIGENITE.get()));
    	lootTables.put(BlockRegister.ORE_DIOPTASE.get(), createOreMiningTable(BlockRegister.ORE_DIOPTASE.get(), ItemRegister.CHUNKS_DIOPTASE.get()));
    	lootTables.put(BlockRegister.ORE_ERYTHRITE.get(), createOreMiningTable(BlockRegister.ORE_ERYTHRITE.get(), ItemRegister.CHUNKS_ERYTHRITE.get()));
    	lootTables.put(BlockRegister.ORE_GOETHITE.get(), createOreMiningTable(BlockRegister.ORE_GOETHITE.get(), ItemRegister.CHUNKS_GOETHITE.get()));
    	lootTables.put(BlockRegister.ORE_HEMATITE.get(), createOreMiningTable(BlockRegister.ORE_HEMATITE.get(), ItemRegister.CHUNKS_HEMATITE.get()));
    	lootTables.put(BlockRegister.ORE_LIMONITE.get(), createOreMiningTable(BlockRegister.ORE_LIMONITE.get(), ItemRegister.CHUNKS_LIMONITE.get()));
    	lootTables.put(BlockRegister.ORE_MAGNETITE.get(), createOreMiningTable(BlockRegister.ORE_MAGNETITE.get(), ItemRegister.CHUNKS_MAGNETITE.get()));
    	lootTables.put(BlockRegister.ORE_MOLYBDENITE.get(), createOreMiningTable(BlockRegister.ORE_MAGNETITE.get(), ItemRegister.CHUNKS_MOLYBDENITE.get()));
    	lootTables.put(BlockRegister.ORE_SKUTTERUDITE.get(), createOreMiningTable(BlockRegister.ORE_SKUTTERUDITE.get(), ItemRegister.CHUNKS_SKUTTERUDITE.get()));
    	lootTables.put(BlockRegister.ORE_WOLFRAMITE.get(), createOreMiningTable(BlockRegister.ORE_WOLFRAMITE.get(), ItemRegister.CHUNKS_WOLFRAMITE.get()));
        //lootTables.put(Registration.FIRSTBLOCK.get(), createStandardTable("firstblock", Registration.FIRSTBLOCK.get()));
        //lootTables.put(Registration.FANCYBLOCK.get(), createStandardTable("fancyblock", Registration.FANCYBLOCK.get()));
    }
}
