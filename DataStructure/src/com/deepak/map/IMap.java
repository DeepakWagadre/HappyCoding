package com.deepak.map;

public interface IMap 
{
       public boolean put(String key,Integer value);
       public Integer get(String key);
       public int size();
       public void rehash();
       public boolean remove(String key);
}
