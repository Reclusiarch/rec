package com.bixel.rec.datagen;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bixel.rec.init.ItemRegister;
import com.bixel.rec.objects.blocks.RecOreBlock;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.data.LootTableProvider;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.ConstantRange;
import net.minecraft.loot.DynamicLootEntry;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTableManager;
import net.minecraft.loot.conditions.MatchTool;
import net.minecraft.loot.conditions.SurvivesExplosion;
import net.minecraft.loot.functions.CopyName;
import net.minecraft.loot.functions.CopyNbt;
import net.minecraft.loot.functions.SetContents;
import net.minecraft.util.ResourceLocation;



//https://www.youtube.com/watch?v=YrB39leQBVk
public abstract class BaseLootTableProvider extends LootTableProvider
{
	private static final Logger LOGGER = LogManager.getLogger();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    protected final Map<Block, LootTable.Builder> lootTables = new HashMap<>();
    private final DataGenerator generator;

    public BaseLootTableProvider(DataGenerator dataGeneratorIn) 
    {
        super(dataGeneratorIn);
        this.generator = dataGeneratorIn;
    }

    protected abstract void addTables();

    protected LootTable.Builder createStandardTable(String name, Block block) 
    {
        LootPool.Builder builder = LootPool.builder()
                .name(name)
                .rolls(ConstantRange.of(1))
                .addEntry(ItemLootEntry.builder(block)
                        .acceptFunction(CopyName.builder(CopyName.Source.BLOCK_ENTITY))
                        .acceptFunction(CopyNbt.builder(CopyNbt.Source.BLOCK_ENTITY)
                                .addOperation("inv", "BlockEntityTag.inv", CopyNbt.Action.REPLACE)
                                .addOperation("energy", "BlockEntityTag.energy", CopyNbt.Action.REPLACE))
                        .acceptFunction(SetContents.func_215920_b() //.builder().addLootEntry(....
                        		.func_216075_a(DynamicLootEntry.func_216162_a(new ResourceLocation("minecraft", "contents"))))
                );
        return LootTable.builder().addLootPool(builder);
    }
    
    protected LootTable.Builder createOreMiningTable(RecOreBlock block, Item item) 
    {
    	ItemPredicate.Builder predicate_stone_pickaxe = ItemPredicate.Builder.create();
    	predicate_stone_pickaxe.item(Items.STONE_PICKAXE);
    	ItemPredicate.Builder predicate_iron_pickaxe = ItemPredicate.Builder.create();
    	predicate_iron_pickaxe.item(Items.IRON_PICKAXE);
    	ItemPredicate.Builder predicate_stone_hammer = ItemPredicate.Builder.create();
    	predicate_stone_hammer.item(ItemRegister.STONE_SLEDGEHAMMER.get());
    	ItemPredicate.Builder predicate_iron_hammer = ItemPredicate.Builder.create();
    	predicate_iron_hammer.item(ItemRegister.IRON_SLEDGEHAMMER.get());
    	
    	LootPool.Builder builder = LootPool.builder()
                .rolls(ConstantRange.of(1))
                .addEntry(ItemLootEntry.builder(block)
                		.acceptCondition(MatchTool.builder(predicate_stone_pickaxe))
                		.acceptCondition(SurvivesExplosion.builder())
                		)
                .addEntry(ItemLootEntry.builder(block)
                		.acceptCondition(MatchTool.builder(predicate_iron_pickaxe))
                		.acceptCondition(SurvivesExplosion.builder())
                		)
        		.addEntry(ItemLootEntry.builder(item)
        				.acceptCondition(MatchTool.builder(predicate_stone_hammer))
        				.acceptCondition(SurvivesExplosion.builder())
        				)
				.addEntry(ItemLootEntry.builder(item)
						.acceptCondition(MatchTool.builder(predicate_iron_hammer))
						.acceptCondition(SurvivesExplosion.builder())
						);
    	return LootTable.builder().addLootPool(builder);
    }
    

    @Override
    public void act(DirectoryCache cache) 
    {
        addTables();

        Map<ResourceLocation, LootTable> tables = new HashMap<>();
        for (Map.Entry<Block, LootTable.Builder> entry : lootTables.entrySet()) 
        {
            tables.put(entry.getKey().getLootTable(), entry.getValue().setParameterSet(LootParameterSets.BLOCK).build());
        }
        writeTables(cache, tables);
    }

    private void writeTables(DirectoryCache cache, Map<ResourceLocation, LootTable> tables) 
    {
        Path outputFolder = this.generator.getOutputFolder();
        tables.forEach((key, lootTable) -> {
            Path path = outputFolder.resolve("data/" + key.getNamespace() + "/loot_tables/" + key.getPath() + ".json");
            try {
                IDataProvider.save(GSON, cache, LootTableManager.toJson(lootTable), path);
            } catch (IOException e) {
                LOGGER.error("Couldn't write loot table {}", path, e);
            }
        });
    }

    @Override
    public String getName() 
    {
        return "Bixel's Mod LootTables";
    }
}
