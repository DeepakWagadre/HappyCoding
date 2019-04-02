import java.util.Arrays;

/**
 * Dutch National Flag Algorithm
 * @author deepakwagadre
 *
 */
public class Sort012 
{
	
	public static void sort012(int[] arr)
	{
		int start=0;
		int mid=1;
		int end=arr.length-1;
		int i=0;
		while(end>=mid)
		{
			switch(arr[mid])
			{
			     case 0:
			     {
			    	 
			         swap(arr,start,mid);
			    	 start++;mid++;
			    	 break;
			     }
			     case 1:
			     {
			    	 mid++;
			    	 break;
			     }
			     case 2:
			     {
			    	swap(arr,mid,end);
			    
			    	 end--;
			    	 break;
			     }
			}//switch
		}
	}
	
	public static void swap(int[] arr,int start, int end)
	{
		int temp=arr[start];
		arr[start]=arr[end];
		arr[end]=temp;
		
	}
    public static void main(String[] args)
    {
	      int[] arr= {0,0,0,0,0,2,1,1,2,2,0,2,2,2,1,1,1,0,0,0,0};	
	      sort012(arr);
	      System.out.println(Arrays.toString(arr));
	}
}
