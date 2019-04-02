
public class MaximumSubArraySum 

{
	
	public static int maxSubArraySum(int[] arr)
	{
		int currentSum=0;
		int maxSum=0;
		for(int i=0;i<arr.length;i++)
		{
		    	currentSum+=arr[i];
		    	if(currentSum<0)
		    		currentSum=0;
		    	if(currentSum>maxSum)
		    		  maxSum=currentSum;
		}
		return maxSum;
	}
    public static void main(String[] args) 
    {
	    int[] arr= {1,2,3,4,-67,-89,5,6,7,9};	
	    System.out.println(maxSubArraySum(arr));
	}
}
