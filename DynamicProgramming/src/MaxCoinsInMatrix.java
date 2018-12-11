
public class MaxCoinsInMatrix 
{
	
	  public  static int maxCoinSum(int i,int j,int n,int[][] arr)
	  {
		  if(i>n-1||j>n-1)
		  {
		       return 0;	  
		  }
		  int down=maxCoinSum(i+1, j, n, arr);
		  int side=maxCoinSum(i, j+1,n, arr);
		  return Math.max(down,side)+arr[i][j];
		  
	  }
     public static void main(String[] args)
     {
	       int[][] arr= {{5,8,2},{1,3,6},{5,4,7}};
	     //  System.out.println(arr.length);
	       int maxCoins=maxCoinSum(0,0,arr.length,arr);
	       System.out.println("max coin sum is "+maxCoins);
	       
	 }
}
