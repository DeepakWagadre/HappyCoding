package com.deepak.CommandDesignPattern;

public class SimpleRemote
{
	  Command command;
	  
	  public SimpleRemote()
	  {
		  super();
	  }
      
	  public void setCommand(Command command)
	  {
		  this.command=command;
	  }
      
	  public void buttonWasPressed()
	  {
		  command.execute();
	  }
      
}
