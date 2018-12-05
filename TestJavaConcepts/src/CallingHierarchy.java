
public class CallingHierarchy 
{
	
	public static void test(Object obj)
	{
		System.out.println("in Object");
		System.out.println(obj);
	}
	public static void test(String str)
	{
		System.out.println("in str");
		System.out.println(str);
	}
	public static void test(int a)
	{
		System.out.println(a);
	}
    public static void main(String[] args)
    {
		//System.out.println("a");
		test(null);
		System.out.println();
		String str=null;
		System.out.println(str.toString());
		
	}
}
