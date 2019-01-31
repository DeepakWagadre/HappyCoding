
public class LongestCommonSubsequence {

	/**
	 * recursive approach - first checks the last chars of the String and then build the solution
	 * @param ch1
	 * @param ch2
	 * @param m
	 * @param n
	 * @return
	 */
	
	public static  int recursiveLCS(char[] ch1,char[] ch2,int m,int n)
	{
		if(m==0||n==0)
			return 0;
		if(ch1[m-1]==ch2[n-1])
		{
			return 1+recursiveLCS(ch1, ch2, m-1, n-1);
		}
		else
		{
			return Math.max(recursiveLCS(ch1, ch2, m, n-1), recursiveLCS(ch1, ch2, m-1, n));
		}
		
		
	}
	/**
	 * Checks the first character of each string and build the solution
	 * @param ch1
	 * @param ch2
	 * @param m
	 * @param n
	 * @return
	 */
	public static int recursiveLCS2(char[] ch1,char[] ch2,int m,int n)
	{
		
		if(m==ch1.length||n==ch2.length)
		    return 0;
		if(ch1[m]==ch2[n])
		{
			return 1+recursiveLCS2(ch1, ch2, m+1, n+1);
		}
		else
		{
			return Math.max(recursiveLCS2(ch1, ch2,m+1, n), recursiveLCS2(ch1, ch2, m, n+1));
		}
		
	}
	
	
	public static int tabularLCS(char[] ch1,char[] ch2,int m,int n)
	{
		
		int[][] out=new int[m+1][n+1];
		for(int i=0;i<m;i++)
		{
			out[0][i]=0;
		}
		for(int i=0;i<n;i++)
		{
			out[i][0]=0;
		}
		
		for(int i=1;i<=m;i++)
		{
			for(int j=1;j<=n;j++)
			{
				if(ch1[i-1]==ch2[j-1])
				{
					out[i][j]=1+out[i-1][j-1];
				}
				else
				{
					out[i][j]=Math.max(out[i-1][j],out[i][j-1]);
				}
			}
		}
		printLCS(ch1,ch2,m,n,out);
		
		
		
		return out[m][n];
	}
	
	public static int tabularLCS2(char[] ch1,char[] ch2,int m,int n)
	{
		int out[][]=new int[m+1][n+1];
		for(int i=0;i<m;i++)
		{
			out[i][n]=0;
		}
		for(int j=0;j<n;j++)
		{
			out[m][j]=0;
		}
		for(int i=m-1;i>=0;i--)
		{
			for(int j=n-1;j>=0;j--)
			{
				if(ch1[i]==ch2[j])
				{
					out[i][j]=1+out[i+1][j+1];
				}
				else
				{
					out[i][j]=Math.max(out[i+1][j],out[i][j+1]);
				}
			}
		}
		
		for(int i=0;i<m+1;i++)
		{
			for(int j=0;j<n+1;j++)
			{
				System.out.print(out[i][j]+",");
			}
			System.out.println("");
		}
		return out[0][0];
	}
	
	public static void printLCS(char[] ch1,char[] ch2,int m,int n,int[][] out) 
	{
		System.out.println("in Print lCS ");
		int index=out[m][n];
		char[] ch=new char[index];
		int i=m;int j=n;
		while(i>0&&j>0)
		{
			if(ch1[i-1]==ch2[j-1])
			{
				ch[index-1]=ch1[i-1];
				i--;j--;index--;
			}
			else if(out[i-1][j]>out[i][j-1])
			{
				i--;
			}
			else
			{
				j--;
			}
		}//while
		
		for(int x=0;x<ch.length;x++)
		{  
			//System.out.println("printing....");
			System.out.println(ch[x]);
		}
		
	}
	
	
	public static void main(String[] args)
	{
              String str1="ABCDGH";
              //String str1="AGGTAB";
              String str2="AEDFHR";
              //String str2="GXTXAYB";
              char[] ch1=str1.toCharArray();
              char[] ch2=str2.toCharArray();
              int lcs=recursiveLCS(ch1, ch2, ch1.length,ch2.length);
              int lcs2=recursiveLCS2(ch1, ch2, 0, 0);
              int tabularLCS=tabularLCS(ch1, ch2, ch1.length,ch2.length);
              int tabularLCS2=tabularLCS2(ch1, ch2, ch1.length,ch2.length);
              System.out.println(lcs);
              System.out.println(lcs2);
              System.out.println(tabularLCS);
              System.out.println(tabularLCS2);
	}

}
