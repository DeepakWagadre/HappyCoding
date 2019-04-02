package com.deepak.arrays;

public class SubarrayWithGivenSum
{
	static void subArraySum(int[] arr, int n, int k) {
		int idx = 0, sum = 0, i = 0;

		for(; i<n; i++)
		{
			sum += arr[i];
			if(sum > k) {
				i = idx++;
				sum = 0;
		}
			else if(sum == k) {
				System.out.printf("Sum found between indexes %d and %d \n", idx, i);
				return;
			} 
 		}
 		
 		System.out.printf("No subarray found \n");
	}

	public static void main(String[] args) 
    {
        int arr[] = {15, 2, 4, 8, 9, 5, 10, 23};
        int n = arr.length;
        int sum = 23;
        subArraySum(arr, n, sum);
    }            
	
	
  }
