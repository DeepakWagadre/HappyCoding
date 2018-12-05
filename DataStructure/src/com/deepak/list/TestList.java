package com.deepak.list;

public class TestList {
	private class Node {
		Node next;
		int data;
	}

	private Node head = new Node();
	private Node current;
	private int size = 0;

	public void add(int data) {
		Node node = new Node();
		node.data = data;
		if (head.next == null) {
			head.next = node;
			current = node;
			size++;
		} else {
			current.next = node;
			current = node;
			size++;
		}
	}

	public void add(int data, int index) {
		Node current = head.next;
		Node node = new Node();
		node.data = data;

		for (int i = 0; i < index - 1; i++) {
			current = current.next;
		}
		Node temp = current.next;
		current.next = node;
		node.next = temp;

	}

	public void display(Node head) {
		Node tmp = head.next;
		while (tmp != null) {
			

			System.out.println(tmp.data);
			tmp = tmp.next;
		}

	}

	public void addFirst(int data) {
		Node tmp = head.next;
		Node node = new Node();
		node.data = data;
		head.next = node;
		node.next = tmp;
		size++;
	}

	public void removeFirst() {
		Node tmp = head.next;
		head.next = tmp.next;

	}

	public void remove(int index) {
		Node current = head.next;

		for (int i = 0; i < index - 1; i++) {
			current = current.next;

		}
		Node temp = current.next.next;
		current.next = temp;
	}

	public void reverse(Node head) {
		Node temp,previous=null;
		current=head.next;
		
		while(current!=null)
		{
			temp=current.next;
			current.next=previous;
			previous=current;
			current=temp;
		}
		head.next=previous;
	}
	
	public void removeLoop(Node head)
	{
		Node tortoise;
		Node rabbit;
		tortoise=rabbit=head.next;
		
	}
    public boolean loopDetection(Node head)
    {
	      Node rabbit,tortoise;
	      rabbit=tortoise=head;
	      
	      do
	      {
	    	  tortoise=tortoise.next;
	    	  if(rabbit==null||rabbit.next==null)
	    		  return false;
	    	  
	    	  rabbit=head.next.next;
	    	  
	      }
	      while(tortoise!=rabbit);
	      return true;
    }
	
    public boolean loopRemoval(Node head)
    {
    	Node rabbit,tortoise;
    	rabbit=tortoise=head;
        boolean flag=true;
    	// loop detection starts
    	do
    	{
    		rabbit=head.next.next;
    		if(rabbit==null||rabbit.next==null	)
    		{
    			flag=false;
    			return flag;
    		}
    		tortoise=tortoise.next;
    	}
    	while(tortoise!=rabbit);
    	
    	Node previous=null;
    	Node tortoise2=head;
    	while(tortoise!=tortoise2)
    	{
    		previous=tortoise;
    		tortoise=tortoise.next;
    	}
    	
    	previous.next=null;
    	
    	return true;
    	//loop detection ends
    	
    }//remove loop
    
    public Node reverseInKOrder(Node head,int k)
    {
  
   /*     Node current = head;
        Node next = null;
        Node prev = null;
         
        int count = 0;
  
         Reverse first k nodes of linked list 
        while (count < k && current != null) 
        {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
            count++;
        }
  
         next is now a pointer to (k+1)th node 
           Recursively call for the list starting from current.
           And make rest of the list as next of first node 
        if (next != null) 
           head.next = reverseInKOrder(next, k);
  
        // prev is now head of input list
         return prev;
    	*/
    	
    	       Node current = head;
    	       Node next = null;
    	       Node prev = null;
    	        
    	       int count = 0;
    	 
    	       /* Reverse first k nodes of linked list */
    	       while (count < k && current != null) 
    	       {
    	           next = current.next;
    	           current.next = prev;
    	           prev = current;
    	           current = next;
    	           count++;
    	       }
    	 
    	       /* next is now a pointer to (k+1)th node 
    	          Recursively call for the list starting from current.
    	          And make rest of the list as next of first node */
    	       if (next != null) 
    	          head.next = reverseInKOrder(next, k);
    	 
    	       // prev is now head of input list
    	       return prev;
    	    
    	
    }
	public void display1(Node head) {
		Node tmp = head;
		while (tmp != null) {
			

			System.out.println(tmp.data);
			tmp = tmp.next;
		}

	}
	public static void main(String[] args) {
		TestList list = new TestList();

		list.add(10);
		list.add(20);
		list.add(30);
		list.add(40);
		list.add(50);
		list.add(60);
		list.add(70);
		list.add(80);
		list.add(90);
		list.add(100);
		list.add(110);
	//	list.add(110);
		// list.addFirst(05);
		// list.removeFirst();
		// list.add(15,3);
		System.out.println("List Size Is : " + list.size);
		System.out.println("<<<<<<<<<<<<<<<<<<<<<< Original List >>>>>>>>>>>>>>>");
		list.display(list.head);
		// list.remove(3);

		/**
		 * Reverse a list
		 */
		//System.out.println("<<<<<<<<<<<<<<<<<<<<<< Reversed List >>>>>>>>>>>>>>>");
	//	list.reverse(list.head);
	//	list.display(list.head);
    
		 /**
		  * Reverse a list in kth order
		  */
		
	//	System.out.println("<<<<<<<<<<<<<<<<<<<<<< Reversed List In Kth Order>>>>>>>>>>>>>>>");
	//	list.head=list.reverseInKOrder(list.head.next,3);
	//	list.display1(list.head);
	}

}






/*	Reverse using iterative function.
 * 
 * 
 * 
 * Node current=head.next;
Node previous=current;
Node temp=null;
while(current!=null&&current.next!=null)
{
	previous=current;
	current=current.next;
	temp=current.next;
	current.next=previous;
	previous.next=temp.next;
	current=temp;
}*/
