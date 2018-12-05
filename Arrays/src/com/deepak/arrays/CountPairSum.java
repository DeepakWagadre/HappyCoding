package com.deepak.arrays;

import java.util.HashSet;
import java.util.Set;

public class CountPairSum 
{
	 /**Using Set
	  * Add the elements in the set and check whether the (sum -difference) is present 
	  *in the set.If present , increment the counter
	  * Complexity O(n)
	  * @param arr
	  * @param sum
	  * @return
	  */
	   public static int countPairsUsingSet(int[] arr,int sum)
	   {
		   int count =0;
		   Set<Integer> set=new HashSet<Integer>();
		   for(int i=0;i<arr.length;i++)
		   {
			   set.add(arr[i]);
			   if(set.contains(sum-arr[i]))
			   {
				   count++;
			   }
		
		   }
		   return count;
	   }
	   /**
	    * 
	    * @param args
	    */
	   public static int countPairsUsingSorting(int[] arr,int sum)
	   {
		   int count=0;
		   
		   return count; 
	   }
       public static void main(String[] args) 
       {
		// int arr[]={5,1,12,9,7,4,8,10,3,27,-14};
    	   int arr[]={2,1,-4,-1,-2,7};
		   int sum=3;
		   int totalPairsCount=countPairsUsingSet(arr,sum);
		   System.out.println(totalPairsCount);
	   }
}
