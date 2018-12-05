package com.deepak.list;

public class ReverseList
{
      class ListNode
      {
    	  int data;
    	  ListNode next;
    	  ListNode()
    	  {
    		  super();
    	  }
    	  ListNode(int data)
    	  {
    		  this.data=data;
    	  }
    	  
      }
      ListNode head=null;
      ListNode current=null;
      
      public void add(int data)
      {
    	  ListNode node=null;
    	  
    	  if(head==null)
    	  {
    		  node=new ListNode(data);
    		  head=node;
    		  current=head;
    	  }
    	  else
    	  {
    		  node=new ListNode(data);
    		  current.next=node;
    		  current=node;
    	  }
      }
      
      public void display(ListNode head)
      {
    	  ListNode temp=head;
    	  while(temp!=null)
    	  {
    		  System.out.print(temp.data+",");
    		  temp=temp.next;
    	  }
      }
      public void reverseList(ListNode start)
      {
    	  ListNode current=start;
    	  ListNode prev=null;
    	  ListNode next=null;
    	  while(current!=null)
    	  {
    		  next=current.next;
    		  current.next=prev;
    		  prev=current;
    		  current=next;
    	  }
    	  head=prev;
      }
      public ListNode reverseKPair(ListNode start,int k)
      {
    	  ListNode current=start;
    	  ListNode prev=null;
    	  ListNode next=null;
    	  int count=0;
    	  while(current!=null&&count<k)
    	  {
    		  next=current.next;
    		  current.next=prev;
    		  prev=current;
    		  current=next;
    		  count++;
    	  }
    	  if(next!=null)
    	          start.next=reverseKPair(next,k);
    	  return prev;
      }
      
      public ListNode reverseAltKPair(ListNode start, int k)
      {
    	  ListNode current=start;
    	  ListNode prev=null;
    	  ListNode next=null;
    	  int count=0;
    	  while(current!=null&&count<k)
    	  {
    		  next=current.next;
    		  current.next=prev;
    		  prev=current;
    		  current=next;
    		  count++;
    	  }
    	  if(next!=null)
    	  {
    		  start.next=current;
    	  }
    	  count=0;
    	  while(current!=null&&count<k-1)
    	  {
    		  current=current.next;
    		  
    		  count++;
    	  }
    	 if(next!=null)
    	 {
    		 current.next=reverseAltKPair(current.next,k);
    	 }
    	  return prev;
      }
      
      public static void main(String[] args) {
		
    	  ReverseList list=new ReverseList();
    	  list.add(1);
    	  list.add(2);
    	  list.add(3);
    	  list.add(4);
    	  list.add(5);
    	  list.add(6);
    	  list.add(7);
    	  list.add(8);
    	  list.add(9);
    	  list.display(list.head);
    	 // System.out.println("************** After Reverse *****************");
    	 // list.reverseList(list.head);
    	 System.out.println("");
    	 // System.out.println("************** After Reverse K Pair *****************");
    	 // list.head=list.reverseKPair(list.head,3);
    	 
    	 System.out.println("************** After Reverse Alternate K Pair *****************");
    	 list.head=list.reverseAltKPair(list.head, 3);
    	 list.display(list.head);
    	  
    	  
	}
}
