package com.deepak.arrays;

public class RainWaterTrappingProblem
{
	  public static int getMax(int[] arr,int start,int end)
	  {
		  int max=0;
		  if(start<arr.length-1);
		  {
		     max=arr[start];
		     if(start+1<arr.length)
		     {
		        for(int i=arr[start+1];i<arr.length;i++)
		        {
			      if(arr[i]>max)
				      max=arr[i];
		        }
		     }
		  }
		  System.out.println("the max height is "+max);
		  return max;
		  
	  }
	 
	  public static int waterVolumeTrapping(int[] arr)
	  {
		  int rmax=getMax(arr,0,arr.length-1);   //get the max element in the array
		  int lmax=arr[0];
		  int waterTrapped=0;
		  for(int i=0;i<arr.length;i++)    //run loop 
		  {
			  
			  if(arr[i]>lmax)              //to check the left max 
				  lmax=arr[i];
			  if(lmax==rmax)
			  {
				 rmax= getMax(arr,i+1,arr.length-1);
			  }
			       
			  if(lmax>arr[i]&&arr[i]<rmax)
			  {
				  int minLmaxRmax=Math.min(lmax,rmax); //check which is smaller whether it's lmax or rmax
				  waterTrapped+=Math.abs(minLmaxRmax-arr[i]);     //reduce the element from the min pf lmax and rmax
			  }
		  }
		  return waterTrapped;   //return the result.
	  }
	
      public static void main(String[] args) 
      {
	      int arr[]= {9,4,5,1,2,9,3};
	      int waterTrapped=waterVolumeTrapping(arr);
	      System.out.println("The total water trapped is "+waterTrapped);
	      
	 }
  }
