package com.deepak.prototypeDesignPattern;

public class F16 implements AircraftInterface
{

	 F16Engine engine=new F16Engine();
	@Override
	public void fly() {
		//F16 fly
		
	}

	@Override
	public AircraftInterface clone() {
		
		return new F16();
	}

	@Override
	public void setEngine(F16Engine engine) {
	
		this.engine=engine;
	}
       
}
