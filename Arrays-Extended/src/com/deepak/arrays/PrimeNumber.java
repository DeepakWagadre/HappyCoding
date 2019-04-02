package com.deepak.arrays;

public class PrimeNumber 
{
     public static void  printPrimeNum(int start,int end)
     { 
    	 int j=1;
    	 for(int i=start;i<=end;i++)
    	 {
    		 if(!(i%2==0||i%3==0||i%5==0||i%7==0))
    		 {
    			 System.out.println(i);
    		 }
    			 
    	 }
     }
	public static void main(String[] args) {
		
		int start=0;
		int end=100;
		printPrimeNum(start,end);
	}
}
