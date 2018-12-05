package com.deepak.threading;

public class DeadlockClass 
{
   public static void main(String[] args) 
   {
	
	   String s1=new String("My Name Is Deepak Wagadre");
	   String s2=new String("I live In Hyderabad"); 
	   
	  Thread t1=new Thread()
			{
		         @SuppressWarnings("static-access")
				public void run()
		         {
		        	 synchronized(s1)
		        	 {
		        	    System.out.println(" Thread 1 : locked resource 1");
		        	    try 
		        	    {
						    Thread.currentThread().sleep(1000000);
					    } 
		        	    catch (InterruptedException e) 
		        	    {
						     e.printStackTrace();
			    	    } 
		        	 } //synchronized
		        	 
		        	 synchronized(s2)
		        	 {
		        		 System.out.println(" Thread 1 : locked resource 2 ");
		        		
		        	 } //synchronized 
		         }//run

			}; //thread1
			
		    Thread t2=new Thread()
		    {
		    	public void run()
		    	{
		    		
		    		synchronized (s2)
		    		{
						    System.out.println("Thread 2 :loked resource 2");
						    
						    try
						    {
						    	Thread.currentThread().sleep(10000);
						    }
						    catch(InterruptedException e)
						    {
						    	e.printStackTrace();
						    }
					} //synchronized
		    		
		    		synchronized(s1)
		    		{
		    			  System.out.println("Thread 2 :locked resource 1");
		    			  
		    		}
		    		
		    		
		    	}
		    	
		    }; // thread2
		    
		    t1.start();
		    t2.start();
   }
}
