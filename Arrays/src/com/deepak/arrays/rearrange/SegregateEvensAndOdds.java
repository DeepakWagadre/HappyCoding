package com.deepak.arrays.rearrange;

import java.util.Arrays;

//int[] arr= {2,1,3,4,5,8,9,0};
/**
 * First Even Numbers Followed by Odds
 * @author dwagadre
 *
 */
public class SegregateEvensAndOdds 
{
	
	public static void segregateEvensAndOdds(int[] arr)
	{
		int i=0;
		int j=i+1;
		for(;i<arr.length;i++)
		{
			if(arr[i]%2!=0)
			{
				   while(j<arr.length)
				   {
				      if(arr[j]%2==0)
				      {
					      int temp=arr[i];
					      arr[i]=arr[j];
					      arr[j]=temp;
					      j++;
					      break;
				      }
				      else
				      {
					    j++;
				      }
				   }//while
			}
		}
	}
	
	
    public static void main(String[] args)
    {
	        int[] arr= {2,1,3,4,5,8,9,0,2,44,66,78,99,45,67};
    	 // int[] arr= {2};
    	//  int[] arr= {2,1};
	      segregateEvensAndOdds(arr);
	      System.out.println(Arrays.toString(arr));
	}
}
