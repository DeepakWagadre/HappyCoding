package com.deepak.arrays;

public class LongestSubArray 
{
	 public static int largestSubArraySum(int arr[])
	 {
		 int currentSum=0;
		 int maxSum=0;
	     for(int i=0;i<arr.length;i++)
	     {
	    	 currentSum=arr[i]+currentSum;
	    	 if(currentSum<0)
	    		 currentSum=0;
	    	 if(currentSum>maxSum)
	    		 maxSum=currentSum;
	     }
		 return maxSum;
	 }
	
       public static void main(String[] args) 
       {
    	   int arr[]={-2, -3, 4, -1, -2, 1, 5, -3};
    	   int arr1[]={-5,-1,-1,-1,-1,5,0,7,-1,-1,-1,-1};
    	   int arr2[]={-2,-3,-4,-5};
    	   System.out.println(largestSubArraySum(arr));
    	   System.out.println(largestSubArraySum(arr1));
    	   System.out.println(largestSubArraySum(arr2));
    	   
    	    
	   }
}
