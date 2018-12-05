package com.deepak.designpatterns.singleton;

public class SingletonDesignPattern 
{
	    private static SingletonDesignPattern instance=null;
	
	    public static  SingletonDesignPattern getInstance()
	    {
            if(instance==null)
            {
			    	synchronized(SingletonDesignPattern.class)
			    	{
			     	   if(instance==null)
			    	  {
			    		instance=new SingletonDesignPattern();
			    	  }
			    	}
            }
			    	return instance;
	    }
         public static void main(String[] args) 
         {
		      SingletonDesignPattern instance=getInstance();
		      System.out.println(instance);
         	 
		 }
}
