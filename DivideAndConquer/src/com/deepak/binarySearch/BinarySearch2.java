package com.deepak.binarySearch;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class BinarySearch2
{
	  public static  boolean binarySearch2(int[] arr,int start,int end, int elem)
	  {
		 
			  int mid=(start+end)/2;
			  if(start>end)
				  return false;
			  
			  if(arr[mid]==elem)
				    return true;
			  else if(arr[mid]<elem)
				  return binarySearch2(arr,mid+1,end,elem);
			  else if(arr[mid]>elem)
				return  binarySearch2(arr,start,mid-1,elem);
			  return false;
		  
	  }
	  
	  public static void main(String[] args) 
	  {
		    int arr[]={2,5,7,3,1,8,9,56,78,90,45};
		    
		    boolean isFound=true;
		    	isFound=	binarySearch2(arr,0,arr.length-1,9);
		    System.out.println("element found ?"+isFound);
		    
		   
	  }
}
