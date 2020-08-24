package com.bixel.rec.data;

public class DefaultHeat implements IHeat
{
	private int heat;
	
	@Override
	public void setHeat(int heat) 
	{
		this.heat = heat;
	}

	@Override
	public int getHeat() 
	{
		return heat;
	}
}
