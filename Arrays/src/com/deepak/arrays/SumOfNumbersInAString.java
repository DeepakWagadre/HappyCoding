package com.deepak.arrays;

public class SumOfNumbersInAString
{
     public static void main(String[] args) 
     {
	      String str="123deepak007";
	      int sum=sumONumbers(str);
	      System.out.println("sum is "+sum);
	      
	      
	 }
     public static int sumONumbers(String str)
     {
    	 int sum=0;
    	 char[] ch=str.toCharArray();
    	 
    	 for(int i=0;i<ch.length;i++)
    	 {
    		 
    		 int c=ch[i]-'0';
    		 if(0<=c&&9>=c)
    		 {
    			 sum=sum+c;
    		 }
    	 }
    	 
    	 return sum;
     }
}
