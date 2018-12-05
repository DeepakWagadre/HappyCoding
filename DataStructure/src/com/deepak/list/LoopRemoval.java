package com.deepak.list;

//Java program to detect and remove loop in linked list

class LoopRemoval {

 static Node head;

 static class Node {

     int data;
     Node next;

     Node(int d) {
         data = d;
         next = null;
     }
 }

 // Function that detects loop in the list
 int detectLoop(Node node) {
	 Node slow=node;
	 Node fast=node;
	 while(fast.next!=null)
	 {
		 slow=slow.next;
	     fast=fast.next.next;
	     if(slow==fast)
	    	 return 1;
	 }
   return -1;
 }

 // Function to remove loop
 void removeLoop(Node head) 
 {
   Node current,fast,slow;
   fast=head;
   slow=head;
   current=head;
   boolean flag=false;
   while(fast.next!=null)
   {
	   slow=slow.next;
	   fast=fast.next.next;
	   if(fast==slow)
	   {
		   flag=true;
		   break;
	   }
   }
   if(flag)
   {
	   slow=head;
	   Node temp=null;
	   while(!(slow==fast))
	   {
		   temp=fast;
		   slow=slow.next;
		   fast=fast.next;
		   
	   }
	   temp.next=null;
   }
 }

 // Function to print the linked list
 void printList(Node node) {
     while (node != null) {
         System.out.print(node.data + " ");
         node = node.next;
     }
 }

 // Driver program to test above functions
 public static void main(String[] args) {
	 LoopRemoval list = new LoopRemoval();
     list.head = new Node(50);
     list.head.next = new Node(20);
     list.head.next.next = new Node(15);
     list.head.next.next.next = new Node(4);
     list.head.next.next.next.next = new Node(10);

     // Creating a loop for testing 
     head.next.next.next.next.next = head.next.next;
     int s=list.detectLoop(head);
     System.out.println(s);
     list.removeLoop(head);
     s=list.detectLoop(head);
     System.out.println(s);
  //   System.out.println("Linked List after removing loop : ");
   //  list.printList(head);
 }
}