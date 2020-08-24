package com.bixel.rec.objects.ore;

import com.bixel.rec.objects.blocks.RecOreBlock;

import net.minecraft.item.Item;
import net.minecraft.util.IItemProvider;

public enum OreLibrary implements IItemProvider
{
	//setting ores to the same as azurite - kinda high but will scale back later
	//gangue is collected from mining the ore
	//to color the dusts use 64, 64, 64 and set to 208 opacity
	
	//int perChunk, int bottomOffset, int topOffset, int maxHeight, int maxVeinSize,
	//Common Copper Ores
	DIGENITE(8, 0, 0, 65, 8, OreCategory.SULFIDE_MINERAL, 2, 15, 1), //anywhere 
	//CHALCOCITE(6, 1, 20, 60, OreCategory.SULFIDE_MINERAL, 2, 15, 1),
	//Colors for Bornite:: pink-purple 255, 43, 96 -- blue 74, 61, 255 -- yellow-orange 255, 165,48 -- glow 88 //dust color burn 88
	BORNITE(8, 0, 0, 100, 8, OreCategory.SULFIDE_MINERAL, 2, 15, 1), //extreme hills, mesa
	//COVELLITE(3, 2, 20, 60, OreCategory.SULFIDE_MINERAL, 1, 10, 0),
	//CHALCOPYRITE(6, 1, 20, 60, OreCategory.SULFIDE_MINERAL, 3, 30, 2),
	//MALACHITE(2, 4, 20, 60, OreCategory.CARBONATE_MINERAL, 4, 30, 2),
	//CUPRITE(2, 1, 20, 60, OreCategory.OXIDE_MINERAL, 3, 15, 2),
	AZURITE(8, 0, 0, 70, 8, OreCategory.CARBONATE_MINERAL, 3, 30, 2), //desert, beaches
	//TETRAHEDRITE(8, 16, 20, 60, OreCategory.SULFOSALT_MINERAL, 2, 15, 1),
	//TENNANTITE(8, 16, 10, 60, OreCategory.SULFOSALT_MINERAL, 3, 30, 2),
	//CHRYSOCOLLA(3, 3, 5, 60, OreCategory.PHYLLOSILICATE_MINERAL, 2, 15, 0), 
	DIOPTASE(8, 0, 0, 80, 8, OreCategory.PHYLLOSILICATE_MINERAL, 5, 30, 2), //desert, beaches
	
	//Common Iron Ores
	//magnetite 72.4% Fe 
	//hematite 69.9% Fe
	//goethite 62.9% Fe
	//limonite 55% Fe
	//siderite 48.2% Fe
	
	MAGNETITE(8, 0, 0, 100, 8, OreCategory.OXIDE_MINERAL, 5, 30, 2), //beaches only
	HEMATITE(8, 0, 0, 100, 8,OreCategory.OXIDE_MINERAL, 5, 30, 2), //anywhere - similar to iron
	GOETHITE(8, 0, 0, 80, 8, OreCategory.OXIDE_MINERAL, 5, 30, 2), //swamps streams lakes
	LIMONITE(8, 0, 0, 80, 8, OreCategory.AMORPHOUS_MINERAL, 4, 30, 1), //streams rivers
	SIDERITE(8, 0, 0, 60, 8, OreCategory.CARBONATE_MINERAL, 4, 30, 1),
	
	//Other ores
	//Molybdenite
	//Molybdenum - high strength high temperature applications
	MOLYBDENITE(8, 0, 0, 30, 8, OreCategory.SULFIDE_MINERAL, 5, 30, 3), //anywhere
	
	//Cobalt ores
	//cobaltite 55% Cobalt 10% Arsenic 10% Fe 5% Nickle 15% Sulfur

	//goethite 62.9% Fe
	//limonite 55% Fe
	COBALTITE(8, 0, 0, 40, 8, OreCategory.SULFIDE_MINERAL, 5, 30, 3), //near water
	//erythrite 55% Arsenic 35% Cobalt 5% Fe 5% Nickle 5% Zinc
	ERYTHRITE(8, 0, 0,  40, 8, OreCategory.ARSENATE_MINERAL, 5, 30, 3), //near water
	//GLAUCODOT(2, 1, 0, 30, OreCategory.SULFIDE_MINERAL, 5, 30, 3),
	//skutterudite 30% Arsenic 45% Cobalt 5% Fe 30% Nickle
	SKUTTERUDITE(8, 0, 0, 30, 8, OreCategory.ARSENATE_MINERAL, 5, 30, 3), //anywhere
	
	//Tungsten ores
	//Wolframite 50% Tung, 10% Iron 10% Mn (Fe,Mn)WO4, is an iron manganese tungstate mineral
	//Scheelite 40% Tung,   CaWO4
	//Ferberite 40% Tung, 15% Iron,   FeWO4
	WOLFRAMITE(8, 0, 0, 79, 8, OreCategory.OXIDE_MINERAL, 5, 30, 3), //near granite
	SCHEELITE(8, 0, 0, 79, 8, OreCategory.TUNGSTATE_MINERAL, 5, 30, 3), //near granite
	FERBERITE(8, 0, 0, 79, 8, OreCategory.TUNGSTATE_MINERAL, 5, 30, 3); //near granite
	
	public final String name;
	public final RecOreBlock block;
	public final int perChunk; //20 is very common (more common than coal)
	public final int bottomOffset; //starts from the bottom of the world
	public final int topOffset; //starts from the top of the world
	public final int maxHeight; //max height it can generate ** the topoffset is an additional minus
	public final int maxVeinSize;
	
	OreLibrary(int perChunk, int bottomOffset, int topOffset, int maxHeight, int maxVeinSize, OreCategory oc, int hardnessIn, int resistanceIn, int harvestLevel) 
	{
		name = this.toString().toLowerCase();
		block = new RecOreBlock(oc, hardnessIn, resistanceIn, harvestLevel);
		//OreGeneration
		this.perChunk = perChunk;
		this.bottomOffset = bottomOffset;
		this.topOffset = topOffset;
		this.maxHeight = maxHeight;
		this.maxVeinSize = maxVeinSize;
		
		//block.setRegistryName(RecMod.loc("ore_" + name));
	}
	
	@Override
	public Item asItem() 
	{
		return block.asItem();
	}
}
