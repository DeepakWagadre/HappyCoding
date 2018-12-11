
public class FibonniciUsingDP
{
	public static int fib1(int n)
	{
		if(n <= 2) return 1;
		return fib1(n-1) + fib1(n-2);
	}
	public static long fib2(int n) {
		long[] mem = new long[n+1];
		auxfib2(n, mem);
		return mem[n];
	}
	//recursion with memory of previous results
	private static long auxfib2(int n, long[] mem) {
		if(n<=2) return 1;
		if(mem[n] != 0) return mem[n];
		mem[n] = auxfib2(n-1, mem)+ auxfib2(n-2, mem);
		return mem[n];
	}
     public static void main(String[] args)
     {
	    int ans1=fib1(4);
	    System.out.println(ans1);
	     
	     long ans2=fib2(1);
	     System.out.println(ans2);
	 }
}
