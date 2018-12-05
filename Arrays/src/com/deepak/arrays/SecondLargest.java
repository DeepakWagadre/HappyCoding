package com.deepak.arrays;

public class SecondLargest 
{
   public static void main(String[] args) 
   {
	  int arr[]={4,5,5,3,8,9,7};
	  int largest=arr[0];
	  int secondLargest=arr[1];
	
		  
	  for(int i=2;i<arr.length;i++)
	  {
		  if(arr[i]>largest&&arr[i]>secondLargest)
		  {
			
			  if(secondLargest<largest)
				  secondLargest=largest;
			  largest=arr[i];
			    
		  }
	  }
	  System.out.println("firstlatgest="+largest+" secondLargest="+secondLargest);
	  
	   findSecondLargest(arr);
   
   
    
   
   }
   public static void findSecondLargest(int[] arr)
   {
	   int firstMax=arr[0];
	   int secondMax=arr[1];
	   
	   for(int i=1;i<arr.length;i++)
	   {
		   if(arr[i]>firstMax)
		   {
			   secondMax=firstMax;
			   firstMax=arr[i];
			   
		   }
	   }
	   System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	   System.out.println("first max is"+firstMax);
	   System.out.println("Second max  is "+secondMax);
   }
   
}
