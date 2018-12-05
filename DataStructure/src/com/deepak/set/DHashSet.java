package com.deepak.set;

public class DHashSet implements ISet
{
     private  ListNode[] hashTbl;
     private int size;
     
     // constructor
     public DHashSet()
     {
    	 hashTbl=new ListNode[11];
    	 
    	 for(int i=0;i<hashTbl.length;i++)
    	 {
    		 hashTbl[i]=new ListNode();
    	 }
    	 size=0;
    	 
     }

	@Override
	public boolean contains(Integer key) 
	{
		int bucket=key.hashCode()%hashTbl.length;
		
		for(ListNode current=hashTbl[bucket].next;current!=null;current=current.next)
		{
			if(current.data==key)
				return true;
		}
		
		return false;
	}

	@Override
	public boolean add(Integer value) 
	{
	   int bucket=value.hashCode()%hashTbl.length;
	   ListNode current=hashTbl[bucket].next;
	   
	   if(current!=null)
	   {
		   while(current!=null)
		   {
			   if(current.data==value)
			   {
				      return false;
			   }
			   current=current.next;
			   
		   }
	   }
	   else
	   {
		   ListNode node=new ListNode();
		   node.setData(value);
		   node.setNext(current);
		   hashTbl[bucket].setNext(node);
		   size++;
	   }
	   return true;
	}
	
	@Override
	public boolean remove(Integer value) {
		
		int bucket=value.hashCode()%hashTbl.length;
		
		for(ListNode current=hashTbl[bucket];current !=null;current=current.next)
		{
			ListNode temp=current.next;
			if(temp.data==value)
			{
				current.next=temp.next;
				size--;
				return true;
			}
		}
		
		return false;
	}
	
	public  void display()
	{
            for(int i=0;i<hashTbl.length;i++)
            {
            	for(ListNode current=hashTbl[i].next;current!=null;current=current.next)
            	{
            		System.out.println(current.data);
            	}
            }
	}

	@Override
	public int getSize() {
	    return size;
	}

	@Override
	public void rehash() {
		
		ListNode[] newHashTbl=new ListNode[hashTbl.length*2];
		
		//add new nodes for each hashTable index
		for(int i=0;i<newHashTbl.length;i++)
		{
			newHashTbl[i]=new ListNode();
		}
		
		
		//rehash
		for(int i=0;i<hashTbl.length;i++)
		{
			for(ListNode current=hashTbl[i].next;current!=null;current=current.next)
			{
				Integer newBucket=(Integer)current.data;
				Integer nBHC=newBucket.hashCode()%newHashTbl.length;
				
				newHashTbl[nBHC].next=current;
				
				
			}
			
		}
		
	}
     
   
	
	
     

}
