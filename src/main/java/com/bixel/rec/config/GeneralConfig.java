package com.bixel.rec.config;

import com.bixel.rec.config.value.CachedEnumValue;
import com.bixel.rec.config.value.CachedFloatingLongValue;
import com.bixel.rec.math.FloatingLong;
import com.bixel.rec.util.UnitDisplayUtils.EnergyType;
import com.bixel.rec.util.UnitDisplayUtils.TempType;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig.Type;

public class GeneralConfig extends BaseRecConfig
{
	private final ForgeConfigSpec configSpec;
	
	public final CachedEnumValue<TempType> tempUnit;
	public final CachedEnumValue<EnergyType> energyUnit;
    //Energy Conversion
    public final CachedFloatingLongValue FROM_IC2;
    public final CachedFloatingLongValue TO_IC2;
    public final CachedFloatingLongValue FROM_FORGE;
    public final CachedFloatingLongValue TO_FORGE;
	
	public GeneralConfig()
	{
		ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
		//World config for adding substance
		
	    tempUnit = CachedEnumValue.wrap(this, builder.comment("Displayed temperature unit in RecMod GUIs.")
		              .defineEnum("temperatureUnit", TempType.K));
	    energyUnit = CachedEnumValue.wrap(this, builder.comment("Displayed energy type in RecMod GUIs.")
	              .defineEnum("energyType", EnergyType.FE));
	    
        FROM_IC2 = CachedFloatingLongValue.define(this, builder, "Conversion multiplier from EU to Joules (EU * JoulePerEU = Joules)",
                "JoulePerEU", FloatingLong.createConst(10), CachedFloatingLongValue.POSITIVE);
        TO_IC2 = CachedFloatingLongValue.define(this, builder, "Conversion multiplier from Joules to EU (Joules * EUPerJoule = EU)",
                "EUPerJoule", FloatingLong.createConst(0.1), CachedFloatingLongValue.POSITIVE);
        FROM_FORGE = CachedFloatingLongValue.define(this, builder, "Conversion multiplier from Forge Energy to Joules (FE * JoulePerForgeEnergy = Joules)",
                "JoulePerForgeEnergy", FloatingLong.createConst(2.5), CachedFloatingLongValue.POSITIVE);
        TO_FORGE = CachedFloatingLongValue.define(this, builder, "Conversion multiplier from Joules to Forge Energy (Joules * ForgeEnergyPerJoule = FE)",
                "ForgeEnergyPerJoule", FloatingLong.createConst(0.4), CachedFloatingLongValue.POSITIVE);
        configSpec = builder.build();
	}

	@Override
	public String getFileName() 
	{
        return "general";
	}

	@Override
	public ForgeConfigSpec getConfigSpec() 
	{
        return configSpec;
	}

	@Override
	public Type getConfigType() 
	{
		return Type.COMMON;
	}

}
