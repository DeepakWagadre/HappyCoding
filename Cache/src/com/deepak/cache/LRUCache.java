package com.deepak.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

class Node
{
	
	String data;
	Node next;
	Node previous;
	public Node(String data)
	{
		this.data=data;
		
	}
}
public class LRUCache 
{
	final int MAXCAPACITY=10;
    Node head;
    int  capacity;
    Node current;
    Map<String,Node> lru=new HashMap<String,Node>();
    
    public boolean isCapacityFull()
    {
    	return capacity>=MAXCAPACITY?true:false;
    }
    public void addAtLast(String data)
    {
    	
    	System.out.println("Add to last");
    	Node temp;
    	if(capacity<MAXCAPACITY)
    	{	
		    	if(head==null)
		    	{
		    		temp=new Node(data);
		    		head=temp;
		    		current=temp;
		    		lru.put(data,temp);
		    		capacity++;
		    	}
		    	else
		    	{
		    		temp=new Node(data);
		    		current.next=temp;
		    		temp.previous=current;
		    		current=temp;
		    		lru.put(data,temp);
		    		capacity++;
		    	}
    	}
    }
    
    public void removeHead()
    {
    	System.out.println("Remove Head");
    	if(isCapacityFull())
    	{
    		String key=head.data;
    		Node temp=head.next;
    		
    		temp.previous=null;
    		head.next=null;
    		lru.remove(key);
    		head=temp;
    	    capacity--;
    	}
    }
    
    public void removeAndAddToCurrent(String data)
    {
    	System.out.println("in remove and All to current");
    	if(lru.containsKey(data))
    	{
    		Node temp=lru.get(data);
    		Node previous=temp.previous;
    		Node next=temp.next;
    		previous.next=next;
    		temp.previous=null;
    		temp.next=null;
    		lru.remove(data);
    		capacity--;
    		addAtLast(data);
    	}
    }
    public void putInLRU(String data)
    {
    	
         if(lru.containsKey(data))
         {
        	 removeAndAddToCurrent(data);
         }
         else
         {
        	 if(capacity>=MAXCAPACITY)
        	 {
        		 removeHead();
        		 addAtLast(data);
        	 }
        	 else
        	 {
        		 addAtLast(data);
        	 }
         }
    }
    
    public static void main(String[] args)
    {
		LRUCache cache=new LRUCache();
		cache.putInLRU("one");
		cache.putInLRU("two");cache.putInLRU("three");cache.putInLRU("four");
		cache.putInLRU("five");cache.putInLRU("six");cache.putInLRU("seven");
		cache.putInLRU("eight");cache.putInLRU("nine");cache.putInLRU("ten");
		cache.putInLRU("eleven");
		cache.putInLRU("five");
	  //Map<String,Node> map=cache.lru;
		System.out.println("capacity is "+cache.capacity);
	    Node temp=cache.head;
	    while(temp!=null)
	    {
	    	System.out.println(temp.data);
	    	temp=temp.next;
	    }
	    
	}
    
    
}
