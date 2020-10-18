package com.bixel.rec.world.gen;

import java.util.ArrayList;
import java.util.List;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import com.bixel.rec.config.RecConfig;
import com.bixel.rec.config.WorldConfig.OreConfig;
import com.bixel.rec.config.WorldConfig.SaltConfig;
import com.bixel.rec.init.RegisterBlocks;
import com.bixel.rec.objects.blocks.RecOreBlock;
import com.bixel.rec.objects.ore.OreLibrary;
import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig.FillerBlockType;
import net.minecraft.world.gen.feature.SphereReplaceConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class OreGen 
{
	//See GenHandler
	//https://github.com/mekanism/Mekanism/blob/df64036a4b62b3f55985abb0ea23bde495ae2378/src/main/java/mekanism/common/world/GenHandler.java
	//private static final Map<Ores.OreLibrary, ConfiguredFeature<?, ?>> ORES = new EnumMap<>(Ores.OreLibrary.class);
    private static final Map<OreLibrary, OreConfig> ORES = new EnumMap<>(OreLibrary.class);
    //private static final Map<Ores.OreLibrary, ConfiguredFeature<?, ?>> ORE_RETROGENS = new EnumMap<>(Ores.OreLibrary.class);

	private static ConfiguredFeature<?, ?> SALT_FEATURE;
    //private static ConfiguredFeature<?, ?> SALT_RETROGEN_FEATURE;
	
	public static void GenerateOre()
	{
		for (OreLibrary type : OreLibrary.values()) 
		{
			//ORES.put(type, getOreFeature(type.block, RecConfig.world.ores.get(type)));
            ORES.put(type, RecConfig.world.ores.get(type));
        }
		SALT_FEATURE = getSaltFeature(RegisterBlocks.SALT_BLOCK.get(), RecConfig.world.salt);
        //Retrogen features
		/*
        if (RecConfig.world.enableRegeneration.get()) 
        {
            for (Ores.OreLibrary type : Ores.OreLibrary.values()) 
            {
                ORE_RETROGENS.put(type, getOreFeature(type.block, RecConfig.world.ores.get(type)));
            }
            SALT_RETROGEN_FEATURE = getSaltFeature(BlockRegister.SALT_BLOCK.get(), RecConfig.world.salt);
        }*/
        
		for (Biome biome : ForgeRegistries.BIOMES) 
		{
			addToBiome(biome);
		}
	}
	
	/**
	 * Cycles through to saved OreConfigTypes based on the Ore Type
	 * Return an OreConfig for Ore generation
	 * @param type
	 * @return
	 */
	private static OreConfig getOreConfig(OreLibrary type)
	{
		for (Map.Entry<OreLibrary, OreConfig> me : ORES.entrySet()) 
		{ 
			if(type.name == me.getKey().name)
			{
				return me.getValue();
			}
        }
		return null;
	}
	
	@Nullable
	private static ConfiguredFeature<?, ?> getOreFeature(OreLibrary type) 
	{
		RecOreBlock block = type.block;
		OreConfig oreConfig = getOreConfig(type);
		
	    if (oreConfig.shouldGenerate.get()) 
	    {
	    	return Feature.ORE.withConfiguration(new OreFeatureConfig(FillerBlockType.field_241882_a, block.getDefaultState(), 
	    			oreConfig.maxVeinSize.get())).withPlacement(Placement.field_242907_l.configure(
	    					new TopSolidRangeConfig(0, 0, oreConfig.maxHeight.get())).func_242728_a().func_242731_b(oreConfig.perChunk.get()));
	        //return Feature.ORE.withConfiguration(new OreFeatureConfig(FillerBlockType.NATURAL_STONE, block.getDefaultState(), oreConfig.maxVeinSize.get())).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(oreConfig.perChunk.get(), oreConfig.bottomOffset.get(), oreConfig.topOffset.get(), oreConfig.maxHeight.get())));
	    }
	    return null;
	}
	
	/**
	 * Returns a generation config loaded by the configfile
	 * @param block
	 * @param saltConfig
	 * @return
	 */
	@Nullable
	private static ConfiguredFeature<?, ?> getSaltFeature(Block block, SaltConfig saltConfig) 
	{
        if (saltConfig.shouldGenerate.get()) 
        {
            BlockState state = block.getDefaultState();
            
            ArrayList<BlockState> list = Lists.newArrayList(Blocks.DIRT.getDefaultState(), Blocks.CLAY.getDefaultState(), state);
            
            return Feature.DISK.withConfiguration(new SphereReplaceConfig(state, FeatureSpread.func_242252_a(saltConfig.ySize.get()), saltConfig.maxVeinSize.get(), list))
                    .withPlacement(Placement.field_242907_l.configure(new TopSolidRangeConfig(0, 0, 90)).func_242728_a().func_242731_b(saltConfig.perChunk.get()));
            /*
            return Feature.DISK.withConfiguration(new SphereReplaceConfig(state, saltConfig.maxVeinSize.get(), saltConfig.ySize.get(), list))
                  .withPlacement(Placement.COUNT_TOP_SOLID.configure(new FrequencyConfig(saltConfig.perChunk.get())));*/
        }
        return null;
    }
	
	private static void addToBiome(Biome biome)
	{
		if (biome.getCategory() == Category.NETHER) 
		{
			//addOre(biome, OreFeatureConfig.FillerBlockType.NETHERRACK, RecOres.Ores.DIGENITE);
		}
		else if (biome.getCategory() == Category.THEEND) 
		{
			//addEndOre(biome, TRContent.Ores.PERIDOT);
			//addEndOre(biome, TRContent.Ores.SHELDONITE);
			//addEndOre(biome, TRContent.Ores.SODALITE);
			//addEndOre(biome, TRContent.Ores.TUNGSTEN);
		} 
		else 
		{
			//adding ores
			addFeature(biome, getOreFeature(OreLibrary.DIGENITE));
			addFeature(biome, getOreFeature(OreLibrary.HEMATITE));
			addFeature(biome, getOreFeature(OreLibrary.MOLYBDENITE));
			addFeature(biome, getOreFeature(OreLibrary.SKUTTERUDITE));
			addFeature(biome, getOreFeature(OreLibrary.WOLFRAMITE)); //in granite only
			addFeature(biome, getOreFeature(OreLibrary.CHALCOPYRITE));
			
			if (biome.getCategory() == Category.EXTREME_HILLS || biome.getCategory() == Category.MESA) 
			{
				addFeature(biome, getOreFeature(OreLibrary.BORNITE));
			}
			//any water
			if (biome.getCategory() == Category.SWAMP || biome.getCategory() == Category.BEACH || biome.getCategory() == Category.RIVER 
					|| biome.getCategory() == Category.OCEAN) 
			{
				addFeature(biome, getOreFeature(OreLibrary.COBALTITE));
				addFeature(biome, getOreFeature(OreLibrary.ERYTHRITE));
				addFeature(biome, SALT_FEATURE);
			}
			if (biome.getCategory() == Category.SWAMP)
			{
				addFeature(biome, getOreFeature(OreLibrary.GOETHITE));
			}
			if (biome.getCategory() == Category.RIVER)
			{
				addFeature(biome, getOreFeature(OreLibrary.LIMONITE));
			}
			if (biome.getCategory() == Category.BEACH) 
			{
				addFeature(biome, getOreFeature(OreLibrary.MAGNETITE));		
			}
			if (biome.getCategory() == Category.DESERT || biome.getCategory() == Category.BEACH) 
			{
				addFeature(biome, getOreFeature(OreLibrary.DIOPTASE));
				addFeature(biome, getOreFeature(OreLibrary.AZURITE));
			}
		}
	}
	

	
	//https://github.com/Qmunity/BluePower/blob/59c3c81cccc8e83fe6c6f415cb2be99a41d7f8dc/src/main/java/com/bluepowermod/world/BPWorldGen.java#L69
	public static void addFeature(Biome biome, ConfiguredFeature<?, ?> configuredFeature) 
	{
		
		//List<List<Supplier<ConfiguredFeature<?, ?>>>> biomeFeatures = new ArrayList<>(biome.func_242440_e().func_242498_c());
        List<List<Supplier<ConfiguredFeature<?, ?>>>> biomeFeatures = biome.getGenerationSettings().getFeatures();


        //List<List<Supplier<ConfiguredFeature<?, ?>>>> biomeFeatures = new ArrayList<>(biome.getGenerationSettings().getFeatures());
        //WHY do we do this
        while (biomeFeatures.size() <= GenerationStage.Decoration.UNDERGROUND_ORES.ordinal()) 
        {
            biomeFeatures.add(Lists.newArrayList());
        }
        List<Supplier<ConfiguredFeature<?, ?>>> features = new ArrayList<>(biomeFeatures.get(GenerationStage.Decoration.UNDERGROUND_ORES.ordinal()));
        features.add(() -> configuredFeature);
        biomeFeatures.set(GenerationStage.Decoration.UNDERGROUND_ORES.ordinal(), features);
        
        //WHY do we do this
        ObfuscationReflectionHelper.setPrivateValue(BiomeGenerationSettings.class, biome.getGenerationSettings(), biomeFeatures, "field_242484_f");
    }
	
	/* OLD 1.16.1
	private static void addFeature(Biome biome, @Nullable ConfiguredFeature<?, ?> feature) 
	{
        if (feature != null) 
        {
            biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, feature);
        }
    }
    */
	
	/*
	 * OLD 

	private static void addOre(Biome biome, Ores.OreLibrary ore, Block block)
	{
		ConfiguredPlacement customConfig = Placement.COUNT_RANGE.configure(new CountRangeConfig(ore.perChunk, ore.bottomOffset, ore.topOffset, ore.maxHeight));
		
		biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, 
				Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, 
						block.getDefaultState(), ore.maxVeinSize)).withPlacement(customConfig));
	}
	
	private static void addResource(Biome biome, Block block)
	{
		ConfiguredPlacement customConfig = Placement.COUNT_RANGE.configure(new CountRangeConfig(6, 60, 0, 90));
		
		biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, 
				Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, 
						block.getDefaultState(), 12)).withPlacement(customConfig));
	}
	*/
}
