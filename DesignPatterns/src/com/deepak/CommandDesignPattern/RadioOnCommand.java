package com.deepak.CommandDesignPattern;

public class RadioOnCommand implements Command
{
     Radio radio;
     public RadioOnCommand(Radio radio)
     {
    	 this.radio=radio;
     }
	@Override
	public void execute() {
		radio.turnRadioON();
		
	}
     
     
}
