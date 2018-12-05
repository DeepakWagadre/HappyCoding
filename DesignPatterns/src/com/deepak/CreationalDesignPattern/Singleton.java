package com.deepak.CreationalDesignPattern;

/**
 *  Singleton Design Patterns are used where we need only one instance of a class.
 * @author dwagadre
 *
 */
public class Singleton
{
	   private static  Singleton singleton;
	   private Singleton()
	   {
		   
	   }
	    
	   /**
	    * The object of the class will get created first when the method is called for the first time , after that the same 
	    * instance will be returned.
	    * This method is not thread-safe.Like if a thread has created a instance and another thread is accessing the method , it may create 
	    * another instance meanwhile. 
	    * @return
	    */
	    public static Singleton getObject1()
	    {
	    	if(singleton==null)
	    	{
	    		singleton=new Singleton();
	    	}
	    	return singleton;
	    		
	    }
	   /**
	    * Here we have used synchronized keyword with the factory method which makes sure that only one thread access the getObject2 method.
	    * 
	    * Here the synchronized keyword makes the getObject2 heavy to use and lacks the performance criteria. 
	    * @return
	    */
	    public static synchronized Singleton getObject2()
	    {
	    	if(singleton==null)
	    	{
	    		singleton=new Singleton();
	    	}
	    	return singleton;
	    }
	    
	    public static Singleton getObject3()
	    {
	    	if(singleton==null)
	    	{
	    	   synchronized(Singleton.class)
	    	   {
	    	          if(singleton==null)
	    	        	   singleton=new Singleton();
	    	   }
	    	}
	    	return singleton;
	    }
	
         public static void main(String[] args)
         {
			final Singleton s=new Singleton();
			
			
		 }
}
