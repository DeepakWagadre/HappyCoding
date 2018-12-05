package com.deepak.set;

public interface ISet 
{
    public boolean contains(Integer key);
    public boolean add(Integer value);
    public boolean remove(Integer value);
    public int getSize();
    public void rehash();
    
}
