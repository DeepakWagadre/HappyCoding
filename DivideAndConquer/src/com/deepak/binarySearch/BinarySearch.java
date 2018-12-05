package com.deepak.binarySearch;

public class BinarySearch
{
	public static boolean binarySearch(int[] arr,int left,int right,int elem)
	{
		
		int mid=(left+right)/2;
		if(left>right)
		      return false;
		
		     
		 if(elem==arr[mid])
			        return true;
	 if(elem>arr[mid])
			         return  binarySearch(arr, mid+1, right, elem);
	  if(elem<arr[mid])
			          return binarySearch(arr, left,mid-1, elem);
		
		return false;
	}
	
	public static boolean binarySearch2(int[] arr,int left,int right,int elem)
	{
		int mid=(left+right)/2;
		if(left>right)
			return false;
		if(elem==arr[mid])
			return true;
		if(elem>arr[mid])
			return binarySearch2(arr,mid+1, right, elem);
		if(elem<arr[mid])
			return binarySearch(arr, left,mid-1, elem);
		
		return false;
	}
	
	
     public static void main(String[] args) 
     {
		     int arr[]={5,10,15,20,25,30,35,40,45};
		     boolean flag=binarySearch(arr,0,8,45);
		     
		     System.out.println("element found : "+flag);
	   
     }
}
