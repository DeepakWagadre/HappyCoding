package com.deepak.map;

public class DHashMap implements IMap
{
	
	private MapNode[] hashTbl;
	private int size;

	public DHashMap()
	{
	     hashTbl=new MapNode[11];
	     
	     for(int i=0;i<hashTbl.length;i++)
	     {
	    	 hashTbl[i]=new MapNode();
	     }
	     size=0;
	}
	
	
	@Override
	public boolean put(String key, Integer value) 
	{
		int bucket=Math.abs((key.hashCode()%hashTbl.length));
		
		for(MapNode current=hashTbl[bucket].next;current!=null;current=current.next)
		{
			if(current.key.equals(key))
			{
				   return false;
			}
		}
		MapNode node=new MapNode(key,value);
		node.next=hashTbl[bucket].next;
        hashTbl[bucket].next=node;		
        size++;
		return true;
	}

	@Override
	public Integer get(String key) {
		
     	int bucket=Math.abs((key.hashCode()%hashTbl.length));
		
		for(MapNode current=hashTbl[bucket].next;current!=null;current=current.next)
		{
			if(current.key.equals(key))
				return current.value;
		}
		
		return null;
	}

	@Override
	public int size() {
	
		return 0;
	}

	@Override
	public void rehash() {
	
		
	}


	@Override
	public boolean remove(String key) {
		int bucket=Math.abs((key.hashCode()%hashTbl.length));
		for(MapNode current=hashTbl[bucket];current!=null;current=current.next)
		{
		    MapNode tmp=current.next; 
		    if(key.equals(tmp.key))
		    {
		    	current.next=tmp.next;
		    	size--;
		    	return true;
		    }
		}
		
		return false;
	}
     
	public void display()
	{
		for(int i=0;i<hashTbl.length;i++)
		{
			System.out.println(hashTbl[i].key +"  : "+hashTbl[i].value );
		}
	}
	
}
