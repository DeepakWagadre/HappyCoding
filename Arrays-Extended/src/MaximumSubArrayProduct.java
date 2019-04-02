
public class MaximumSubArrayProduct 
{
	
	
	  public static int maxProductSubArray(int [] arr)
	  {
		  int maxProduct=1;
		  int minProduct=1;
		  int currentProduct=1;
		  
		  for(int i=0;i<arr.length;i++)
		  {
			  if(arr[i]>0)
			  {
				  currentProduct*=arr[i];
				  minProduct=minProduct*arr[i];
			  }
			  if(arr[i]==0)
			  {
				  currentProduct=1;
				  minProduct=1;
				  
			  }
			  if(arr[i]<0)
			  {
				  minProduct=minProduct*arr[i];
				  currentProduct=Math.max(currentProduct, minProduct);
			  }
			  if(currentProduct>maxProduct)
			  {
				  maxProduct=currentProduct;
				//  System.out.println(maxProduct);
			  }
		  }
		  
		  
		  return maxProduct;
	  }
      public static void main(String[] args)
      {
	      int[] arr= {3,4,5,98,-7,0,1,2,-9,-21,7,0};	
	      int[] arr1= {-2,-3,-4};
	      System.out.println(maxProductSubArray(arr1));
	      
	}
}
