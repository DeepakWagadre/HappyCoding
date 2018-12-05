package com.deepak.arrays;

import java.util.Arrays;

public class PairSum 
{
	
	public static int  pairSum(int arr[],int sum)
	{
	      Arrays.sort(arr);	
	      int l=0,r=arr.length-1;
	      while(l<r)
	      {
	    	  if(arr[l]+arr[r]==sum)
	    		  return 1;
	    	  else if(arr[l]+arr[r]>sum)
	    		   r--;
	    	  else
	    	       l++;
	      }
	      return 0;
	}
	
    public static void main(String[] args)
    {
	     int arr[]={1, 4, 45, 16, 11, -8};
	     int ans=pairSum(arr,17);
	     if(ans==1)
	     {
	    	 System.out.println("Pair Found");
	     }
	     else
	     {
	    	 System.out.println("No luck");
	     }
	     
	     
	}
}
