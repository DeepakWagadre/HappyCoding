
public class LocalMinimaMaxima 
{
	
	
	public static int  localMinima(int[] arr)
	{
		int localMinima=auxlocalMinima(arr,0,arr.length-1);
		return localMinima;
	}
	public static int auxlocalMinima(int[] arr,int start,int end)
	{
		
		int mid=(start+end)/2;
		if(start > end)
			return -1;
		if(arr[mid-1]>arr[mid]&&arr[mid]<arr[mid+1])
			       return mid;
		else if(arr[mid-1]<arr[mid])
			return auxlocalMinima(arr,0,mid-1);
		else
			return auxlocalMinima(arr,mid+1,end);
		
	}
     public static void main(String[] args) 
     {
	     int[] arr= {9,6,13,13,14,5,7,4};	
	    int localMinima= localMinima(arr);
	    System.out.println(localMinima);
	}
}
