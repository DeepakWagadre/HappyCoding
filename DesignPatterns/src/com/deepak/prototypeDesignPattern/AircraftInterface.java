package com.deepak.prototypeDesignPattern;

public interface AircraftInterface 
{
    public void fly();
    public  AircraftInterface clone();
    public void setEngine(F16Engine engine);
    
}
