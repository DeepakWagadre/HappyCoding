package com.deepak.map;

public class MapNode 
{
      String key;
      Integer value;
      MapNode next;
      
      public MapNode()
      {
    	  
      }
      public MapNode(String key,Integer value)
      {
    	  this.key=key;
    	  this.value=value;
      }
}
