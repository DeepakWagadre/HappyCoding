
public class MaxCoins 
{
	 private static int max = Integer.MIN_VALUE;
	 static  int count=0;
	 
	// explore all paths
		public static void auxMaxCoins1(int[][] arr, int i, int j, int csum) {
			if (i >= arr.length || j >= arr.length)
				return;
			if (i == arr.length - 1 && j == arr.length - 1) {
				max = Math.max(max, csum + arr[i][j]);
				//System.out.println(csum + arr[i][j]+" "+count++);
				return;
			}
			auxMaxCoins1(arr, i + 1, j, csum + arr[i][j]);
			auxMaxCoins1(arr, i, j + 1, csum + arr[i][j]);
		}
	 
	 public static int maxCoins1(int arr[][])
	 {
		 auxMaxCoins1(arr, 0, 0, 0);
		 return max;
	 }
	 
	 public static int auxMaxCoins2(int[][] arr, int i, int j) {
			if (i < 0 || j < 0)
				return 0;
			int topcell = auxMaxCoins2(arr, i - 1, j);
			int leftcell = auxMaxCoins2(arr, i, j - 1);
			return Math.max(topcell, leftcell) + arr[i][j];
		}

		public static int findMaxCoins2(int[][] arr) {
			return auxMaxCoins2(arr, arr.length - 1, arr.length - 1);
		}
	
     public static void main(String[] args)
     {
	   //  int arr[][]={{5,2,6,8},{12,4,8,6},{4,3,2,1},{1,8,12,3}};
    	// int arr[][]={{5,2,6,8,9},{12,4,8,6,9},{4,3,2,1,9},{1,8,12,3,9},{1,8,12,3,9}};
    	int arr[][]={{5,2,361},{12,244,8},{4,3,2}};
	     int maxCoins=maxCoins1(arr);
	     int maxCoins2=findMaxCoins2(arr);
	     System.out.println("max sum is "+maxCoins2);
	     System.out.println("max sum is "+maxCoins);

	     
	     
	 }
}
