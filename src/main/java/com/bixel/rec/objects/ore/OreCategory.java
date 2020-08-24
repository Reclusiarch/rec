package com.bixel.rec.objects.ore;

public enum OreCategory 
{
	AMORPHOUS_MINERAL("Amorphous Mineral"),
	ARSENATE_MINERAL("Arsenate Mineral"),
	CARBONATE_MINERAL("Carbonate Mineral"),
	CYCLOSILICATE_MINERAL("Cyclosilicate Mineral"),
	OXIDE_MINERAL("Oxide Mineral"),
	PHYLLOSILICATE_MINERAL("Phyllosilicate Mineral"),
	SULFIDE_MINERAL("Sulfide Mineral"),
	SULFOSALT_MINERAL("Sulfosalt Mineral"),
	TUNGSTATE_MINERAL("Tungstate Mineral");
	
	private String cat;

	OreCategory(String desc) 
	{
        this.cat = desc;
    }
 
    public String getName() 
    {
        return cat;
    }
}
