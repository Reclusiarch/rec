package com.bixel.rec.data;

public class DefaultCharge implements ICharge
{
	private int charge;
	
	@Override
	public void setCharge(int charge) 
	{
		this.charge = charge;
	}

	@Override
	public int getCharge() 
	{
		return charge;
	}
}
