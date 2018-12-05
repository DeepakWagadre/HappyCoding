package com.deepak.heaps;

import java.util.ArrayList;
import java.util.List;

public class MaxHeap
{
	   static List<Integer> heap;
	   public MaxHeap()
	   {
		   heap=new ArrayList<Integer>();
	   }
	   
	   /**
	    * add elements to the heap
	    * @param data
	    */
	   public static void addHeap(int data)
	   {
		   
		   heap.add(data);
		   int current=heap.size()-1;
		   while(current>0)
		   {
		      int parent=(current-1)/2;
		      if(heap.get(parent)<heap.get(current))
		      {
		    	  swap(parent,current);
		    	  current=parent;
		      }
		      else 
		    	  break;
		   }
	   }
	   /**
	    * Swap the elements
	    * @param parent
	    * @param current
	    */
	   public static void swap(int parent,int current)
	   {
		   int temp=heap.get(parent);
		   heap.set(parent,heap.get(current));
		   heap.set(current,temp);
	   }
	   
	   public static int removeMax()
	   {
		   int current=heap.size()-1;
		   int parent=0;
		   int max=heap.get(parent);
		   heapify(parent,current);
		   return max;
	   }
	   /**
	    * Heapify
	    * @param parent
	    * @param current
	    */
	   public static void heapify(int parent,int current)
	   {
		   int temp=heap.get(parent);
		   heap.set(parent, heap.get(current));
		   heap.remove(current);
           while (true)
           {
	        	int lChildIndex=2*parent+1;
	    		int rChildIndex=2*parent+2; 
	        	if(lChildIndex>heap.size()-1)   
	        		break;
	        	int maxChildIndex=lChildIndex;   //initilaizing the lchild as maxChild
	        	if(rChildIndex<heap.size()-1&&heap.get(rChildIndex)>heap.get(maxChildIndex))       //now checking whether the maxChild is lesser than rChild,if yes the set rChild as maxXhild 
	        		maxChildIndex=rChildIndex;
	        	
	        	if(heap.get(parent)<heap.get(maxChildIndex)) // if (maxChild is bigger then swap the elements)
	        	{
	        		swap(parent,maxChildIndex);
	        		parent=maxChildIndex;                    // make the maxXhild as parent to verify the below nodes.
	        	}
	        	else
	        		break;
           }
	   }
	   
	   
      public static void main(String[] args) 
      {
    	  MaxHeap heap1=new MaxHeap();
    	  addHeap(5);
    	  addHeap(7);
    	  addHeap(8);
    	  addHeap(10);
    	  addHeap(18);
    	  addHeap(20);
    	  addHeap(35);
    	  int max1=removeMax();
    	  System.out.println("Max is "+max1);
    	  int max2=removeMax();
    	  System.out.println("Max is "+max2);
	      for(int i=0;i<heap.size();i++)
	     {
	    	 System.out.println(heap.get(i));
	     }
	  }
}
