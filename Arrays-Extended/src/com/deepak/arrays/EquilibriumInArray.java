package com.deepak.arrays;

public class EquilibriumInArray 
{
	
	 /**
	  * For each element check the lsum (0 -(elem-1)) and rsum(elem-
	  * @param arr
	  * @return
	  */
	 public static int findEquilibrium1(int[] arr)
	 {
		 
		 for(int i=0;i<arr.length;i++)
		 {
			 int lsum=0,rsum=0;
			 for(int j=0;j<i;j++)
			 {
				 lsum+=arr[j];
			 }//for
			 for(int k=i+1;k<arr.length;k++)
			 {
				 rsum+=arr[k];
			 }//for
			 if(lsum==rsum)
				 return i;
		 }  //for
		 return -1;
	 }//findEquilibrium1 
	 
	 public static int findEquilibrium2(int[] arr)
	 {
		 int sum=0;
		 for(int i=0;i<arr.length;i++)
		 {
			 sum+=arr[i];
		 }
		 int l=0,r=arr.length;
		 int lsum=0;
		 while(l<arr.length)
		 {
			 lsum+=arr[l];
			 l++;
			 sum-=arr[r-(l+1)];
			 if(lsum==sum)
			 {
				 return l;
			 }
		 }
		 
		 return -1;
	 }
	 
	
     public static void main(String[] args)
     {
		  int[] array={-7,1,5,2,-14,3,0};
		  int equilibriumPoint1=findEquilibrium1(array);
		  System.out.println(equilibriumPoint1);
		  
		  int equilibriumPoint2=findEquilibrium2(array);
		  System.out.println(equilibriumPoint2);
		  
		  
     }
}
