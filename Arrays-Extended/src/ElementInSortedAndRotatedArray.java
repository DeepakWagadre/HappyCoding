
public class ElementInSortedAndRotatedArray 
{
	
	  public static int findPivot(int[]arr, int start,int end)
	  {
		  
		  if(start>end)  //when end becomes -1
			  return -1;
		 
		  if(start==end)  //if array has only 1 element
 			       return start;
		  int mid=(start+end)/2;
		  if(mid>start&&arr[mid-1]>arr[mid])
			  return mid-1;
		  if(arr[mid]>arr[mid+1])
			       return mid;
		  
		  if(arr[start]<arr[mid])
		     return findPivot(arr,mid+1, end);
		  else
			  return findPivot(arr,start,mid-1);
		     
       }
	  
	  static int findPivot1(int arr[], int low, int high) 
	  { 
	    // base cases 
	    if (high < low) return -1; 
	    if (high == low) return low; 
	    
	     int mid = (low + high)/2; /*low + (high - low)/2;*/
	     if (mid < high && arr[mid] > arr[mid + 1]) 
	      return mid; 
	        
	     if (mid > low && arr[mid] < arr[mid - 1]) 
	      return (mid-1); 
	        
	     if (arr[low] >= arr[mid]) 
	      return findPivot(arr, low, mid-1); 
	        
	     return findPivot(arr, mid + 1, high); 
	  } 
	
     public static void main(String[] args)
     {
	      int [] arr= {2,3};
	      int pivotElement1=findPivot1(arr,0, arr.length-1);
	      int pivotElement=findPivot(arr,0, arr.length-1);
	      System.out.println(pivotElement1);
	      System.out.println(pivotElement);
	      
	 }
}
