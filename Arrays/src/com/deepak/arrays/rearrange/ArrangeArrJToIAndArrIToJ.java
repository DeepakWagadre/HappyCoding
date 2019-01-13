package com.deepak.arrays.rearrange;

import java.util.Arrays;

public class ArrangeArrJToIAndArrIToJ
{
	
	public static void rearrange(int[] arr,int[] tempArray)
	{
		 int temp=0;
		 
	     for(int i=0;i<arr.length;i++)
	     {
	    	 temp=arr[i];
	    	 tempArray[temp]=i;
	    	
	     }
	     
	}
	
    public static void main(String[] args) {
    	
    	int[] arr= {1,3,0,2};
    	int[] tempArray=new int[arr.length];
		rearrange(arr,tempArray);
		System.out.println(Arrays.toString(tempArray));
	}
}
