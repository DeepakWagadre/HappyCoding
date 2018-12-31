package com.deepak.CommandDesignPattern;

public class TestSimpleRemote
{
     
	public static void main(String[] args) {
		SimpleRemote remote=new SimpleRemote();
	     Light light=new Light();
	     Radio radio=new Radio();
	     
	     remote.setCommand(new LightOnCommand(light));
	     remote.buttonWasPressed();
	     
	     remote.setCommand(new RadioOffCommand(radio));
	     remote.buttonWasPressed();
		
	}
	
	
}
