
/**
 * @author dwagadre
 *
 */
public class SpiralMatrix 
{
	
	public static  void printSpiralMatrix(int[][] arr,int m,int n)
	{
		
		int x=0,y=n-1;
		while(x<y)
		{
			int i=x,j=x;
		
		for(;j<=y;j++)
		{
			System.out.print(arr[i][j]+",");
		}
		i=i+1;j=j-1;
		
		for(;i<y;i++)
		{
			System.out.print(arr[i][j]+",");
		}
	
		for(;j>x;j--)
		{
			System.out.print(arr[i][j]+",");
		}
	
		for(;i>x;i--)
		{
			System.out.print(arr[i][j]+",");
		}
		 x++; y--;	
		 
		//System.out.println("count changed");
		}
		
		
	}
    public static void main(String[] args)
    {
	      int arr4[][]={{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
	      
	      int arr5[][]={{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20},{21,22,23,24,25}};
	      
	      int arr6[][]={{1,2,3,4,5,6},{7,8,9,10,11,12},{13,14,15,16,17,18},{19,20,21,22,23,24},{25,26,27,28,29,30},{31,32,33,34,35,36}};
	      
	      //Output ---> 1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10
	      
	      printSpiralMatrix(arr4, 4, 4);
	      System.out.println();
	      printSpiralMatrix(arr5, 5, 5); 
	      System.out.println();
	      printSpiralMatrix(arr6, 6, 6); 
	      
	      
	}
}
