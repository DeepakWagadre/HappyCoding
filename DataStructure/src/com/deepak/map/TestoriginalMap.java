package com.deepak.map;

import java.util.HashMap;
import java.util.Map;

public class TestoriginalMap
{
    public static void main(String[] args)
    {
	       Map<String,Integer> map=new HashMap<String,Integer>();
	         map.put("first", 1);
	    	 map.put("second",2);
	    	 map.put("third", 3);
	    	 map.put("fourth",4);
	    	 map.put("fifth",5);
	    	 
	    	for(Map.Entry<String,Integer> entry:map.entrySet())
	    	{
	    		System.out.println(entry.getKey() + " : "+entry.getValue());
	    	}
	}
}
