package com.deepak.arrays.rearrange;

import java.util.Arrays;
/**
 * The idea behind this approach is :
 * Consider the middle element as pivot.
 * Elements lesser than the pivot will be on left and greater than the pivot will be on right
 * if(elem)==0
 *     check for the case
 *     start++;mid++
 *  if(elem)==1
 *     check for the case
 *      mid++;
 *  if(elem)==2
 *      check for the case
 *      end--;
 * 
 * @author dwagadre
 *
 */
public class DutchNationalFlagAlgo
{
	
	  public static void dutchNationalFlagProblem(int[] arr)
	  {
		  int start=0;
		  int mid=1;
		  int end=arr.length-1;
	        while(mid<=end)
	        {
	        	switch(arr[mid])
	        	{
		        	case 0:
		        	{
		        	    if(arr[start]>arr[mid])
		        	    {
		        	    	swap(start,mid,arr);
		        	    	start++;
		        	    	mid++;
		        	    }
		        	    else
		        	    {   start++;
		        	    	mid++;
		        	    }
		        	    break;
		        	}
		        	case 1:
		        	{
		        		if(arr[start]>arr[mid])
		        	    {
		        	    	swap(start,mid,arr);
		        	  	   // start++; 
		        	  	    mid++;
		        	    }
		        		else
		        		{
		        			//start++;
		        		    mid++;
		        		}
		        		break;
		        	}
		        	case 2:
		        	{
		        		if(arr[mid]>arr[end])
		        		{
		        			swap(mid,end,arr);
		        			//mid++;
		        			end--;
		        		}
		        		break;
		        	}
	        	}
	        }
	  }
	  
	  public static void dutchNationalFlagOpt(int[] arr)
	  {
		  int start=0;
		  int mid=1;
		  int end=arr.length-1;
	        while(mid<=end)
	        {
	        	switch(arr[mid])
	        	{
		        	case 0:
		        	{
		        	    if(arr[start]>arr[mid])
		        	             swap(start,mid,arr);
		        	    	start++;
		        	    	mid++;
		        	    break;
		        	}
		        	case 1:
		        	{
		        		if(arr[start]>arr[mid])
		        	    	swap(start,mid,arr);
		        	  	   mid++;    	
		        		break;
		        	}
		        	case 2:
		        	{
		        		if(arr[mid]>arr[end])
		        		    swap(mid,end,arr);
		        			end--;
		        		break;
		        	}
	        	}
	        }
	  }
	  
	  public static void swap(int start,int mid,int arr[])
	  {
		  int temp=arr[start];
		  arr[start]=arr[mid];
		  arr[mid]=temp;
	  }
	
	
       public static void main(String[] args) 
       {
		    int[] arr= {1,0,0,0,0,0,0,0,0,0,2,2,0,1,1,1,1,1,1,
		    		1,1,0,1,2,0,0,0,2,2,1,1,0,0,0,0,0,1};
		    dutchNationalFlagProblem(arr);
		    dutchNationalFlagOpt(arr);
		    System.out.println(Arrays.toString(arr));
	   }
}
