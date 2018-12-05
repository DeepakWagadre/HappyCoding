package com.deepak.map;

import java.util.HashMap;
import java.util.Map;

public class MapDriver 
{
     public static void main(String[] args) {
		
    	 DHashMap map=new DHashMap();
    	 map.put("first", 1);
    	 map.put("second",2);
    	 map.put("third", 3);
    	 map.put("fourth",4);
    	 boolean isPut= map.put("fourth", 5);
    	 System.out.println("Is Put"+isPut); 
    	 int value=map.get("fourth");
    	 boolean isRemoved=map.remove("third");
    	 System.out.println("is Removed : "+isRemoved);
    	 System.out.println("value is :"+value);
    	 
    	 map.display();
	}
     public static void testMap()
     {
    	 Map<Integer,Integer> map=new HashMap<Integer,Integer>();
    	 
    	 
     }
}
