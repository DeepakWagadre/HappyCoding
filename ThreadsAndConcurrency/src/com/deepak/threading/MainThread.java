package com.deepak.threading;
/**
 * Program for deadlock with single thread..
 * @author dwagadre
 *
 */
public class MainThread 
{

	    public static void main(String[] args)
	    {
	        try
	        {
	             
	            System.out.println("Entering into Deadlock");
	             
	            Thread.currentThread().join();
	             
	            // the following statement will never execute
	            System.out.println("This statement will never execute");
	             
	        } 
	         
	        catch (InterruptedException e) 
	        {
	            e.printStackTrace();
	        }
	    
	}
	
}
