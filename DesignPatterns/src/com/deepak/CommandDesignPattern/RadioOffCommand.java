package com.deepak.CommandDesignPattern;

public class RadioOffCommand implements Command
{
	 Radio radio;
     public RadioOffCommand(Radio radio)
     {
    	 this.radio=radio;
     }
	@Override
	public void execute() {
		radio.turnRadioOFF();
		
	}
}
