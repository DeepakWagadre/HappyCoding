
public class BinarySearch
{
	public static boolean auxBinarySearch(int []arr,int elem) {
		boolean isElemFound=binarySearch(arr,0,arr.length-1,elem);
		if(arr==null)
			return false;
		return isElemFound;
	}
	public static boolean binarySearch(int[] arr, int start, int end,int elem)
	{
	
		if(start>end)
			return false;
		int mid=(start+end)/2;
		
		if(arr[mid]==elem)
		  	return true;
		else if(arr[mid]>elem)
			return binarySearch(arr,start,mid-1, elem);
		else
			return binarySearch(arr,mid+1, end, elem);
		
		 
	}
    public static void main(String[] args) {
		int[] arr= {1,2,3,5,6,7,8,9};
		boolean isFound=auxBinarySearch(arr,2);
		System.out.println("element found :"+isFound);
	}
}
