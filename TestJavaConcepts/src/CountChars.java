//aaabbbushhhyyyysssssssss
public class CountChars
{
	public static StringBuffer compressString(String input) 
	{
		char[] arr=input.toCharArray();
		int count=0;
		StringBuffer output= new StringBuffer(" ");
		int length=arr.length;
		for(int i=0;i<arr.length;i++)
		{
			count=1;
			while((i<length-1)&&arr[i]==arr[i+1])
			{
				count++;
				i++;
			}
			output.append(count+""+arr[i]);
		}
		
		return output;
	}
     public static void main(String[] args)
     {
		String str="aaabbbfghjjjjj";
		StringBuffer output=compressString(str);
		System.out.println(output);
    	 
	 }
}
