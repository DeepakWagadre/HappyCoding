package com.deepak.heaps;

import java.util.ArrayList;
import java.util.List;

public class MinHeap 
{
       static List<Integer> heap=new ArrayList<Integer>();
       
       public static void addHeap(int data)
       {
    	   heap.add(data);
    	   int current=heap.size()-1;
    	   while(current>0)
    	   {
    	      int parent=(current-1)/2;
    	      if(heap.get(current)<heap.get(parent))
    	     {
    		     swap(parent,current);
    		     current=parent;
    	     }
    	      else
    	    	 break;
    	   }
    	   
       }
       public static  int removeMin()
       {
    	   int current=heap.size()-1;
    	   int parent=0;
    	   int max=heap.get(parent);
    	   heapify(parent,current);
    	   return max;
       }
       public static void heapify(int parent,int current)
       {
    	   heap.set(parent, heap.get(current));
    	   heap.remove(current);
    	   while(true)
    	   {
	    	    int lchildIndex=2*parent+1;
	    	    int rchildIndex=2*parent+2;
	    	    int maxchildIndex=lchildIndex;
	    	    
		    	if(lchildIndex>heap.size()-1)
	    	           break;
	    	   if(rchildIndex<heap.size()-1&&heap.get(maxchildIndex)<heap.get(rchildIndex))
	    	   {
	    		    maxchildIndex=rchildIndex;
	    		    		
	    	   }
	    	   if(heap.get(parent)>heap.get(maxchildIndex))
	    	   {
	    		   swap(parent,maxchildIndex);
	    		   parent=maxchildIndex;
	    		   
	    	   }
	    	   else
	    		   break;
    	   }//WHILE	   
       }
       public static void swap(int parent,int current)
	   {
		   int temp=heap.get(parent);
		   heap.set(parent,heap.get(current));
		   heap.set(current,temp);
	   }
       
       public static void main(String[] args) 
       {
    	   //MinHeap heap1=new MinHeap();
     	  addHeap(5);
     	  addHeap(7);
     	  addHeap(4);
     	  addHeap(3);
     	  addHeap(8);
     	  addHeap(10);
     	  addHeap(18);
     	  addHeap(20);
     	  addHeap(35);
     	  int min1=removeMin();
     	  int min2=removeMin();
     	  System.out.println("Min is "+min1);
     	 System.out.println("Min is "+min2); 
 	      for(int i=0;i<heap.size()-1;i++)
 	     {
 	    	 System.out.println(heap.get(i));
 	     }
 	  }
 }
       

