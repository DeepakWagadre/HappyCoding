
public class SubArrayWithGivenSum 
{
	
	 static int subArraySum(int arr[], int n, int sum)  
    { 
        int curr_sum = arr[0], start = 0, i; 
        // Pick a starting point 
        for (i = 1; i <= n; i++)  
        { 
            // If curr_sum exceeds the sum, then remove the starting elements 
            while (curr_sum > sum && start < i-1) 
            { 
                curr_sum = curr_sum - arr[start]; 
                start++; 
            } 
            // If curr_sum becomes equal to sum, then return true 
            if (curr_sum == sum)  
            { 
                int p = i-1; 
                System.out.println("Sum found between indexes " + start 
                        + " and " + p); 
                return 1; 
            } 
            // Add this element to curr_sum 
            if (i < n) 
            curr_sum = curr_sum + arr[i]; 
        } 
  
        System.out.println("No subarray found"); 
        return 0; 
    } 
    public static void subArrayWithSum(int[] arr, int sum)
    {
    	int left=0;int right=0; int pointer=0;
    	int currentSum=arr[0];
    	for(int i=1;i<=arr.length;i++)
    	{
    		
    		while(currentSum>sum&&pointer<i-1)
    		{
    			currentSum-=arr[pointer];
    			pointer++;
    		}
    		if(currentSum==sum)
    		{
    			System.out.println("sum found between indexes "+pointer+" : " +(i-1));
    			
    		}
    		
    		if(i<arr.length)
    		    currentSum+=arr[i];
    		
    		
    	}
    }
	 
	 
    public static void main(String[] args) 
    {
	    int [] arr= {5,4,8,9,3,7,1};
	    int sum=12;
	    
	   // subArraySum(arr,arr.length,sum);
	    subArrayWithSum(arr,sum);
	    
	}
}
