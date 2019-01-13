package com.deepak.arrays.rearrange;

import java.util.Arrays;

public class Arrange0and1Separately 
{
	
	public static void reArrrange1(int[] arr)
	{
		if(arr!=null)
		{
		     int start=0;
		     int end=arr.length-1;
		     while(start<end)
		     {
		    	 if(arr[start]==1&&arr[end]==0)
		    	 {
		    		 arr[start]=0;
		    		 arr[end]=1;
		    		 start++;
			    	 end--;
		    	 }
		    	 else if(arr[start]==0&&arr[end]==0)
		    	 {
		    		  start++;
		    	 }
		    	 else if(arr[start]==1&&arr[end]==1)
		    	 {
		    		 end--;
		    	 }
		     }
		}
		
		
	}
	
	
     	
     public static void main(String[] args) 
     {
	      int [] arr= {0,1,0,0,0,0,0,1,1,0,0,0};
	      
	      int arr1[]=new int[0];
	    //  System.out.println(arr1.length);
	     
	      reArrrange1(arr);
	      System.out.println(Arrays.toString(arr));
	      
	  }
}
