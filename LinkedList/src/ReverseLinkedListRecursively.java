
public class ReverseLinkedListRecursively 
{
	
	public static ListNode reverseList(ListNode start)
	{
		if(start==null)
			return start;
	     return null;
		
	}
      public static void main(String[] args) 
      {
    		ListNode start=new ListNode(2);
    		start.next=new ListNode(7);
    		start.next.next=new ListNode(6);
    		start.next.next.next=new ListNode(9);
    		start.next.next.next.next=new ListNode(5);
    		start.next.next.next.next.next=new ListNode(1);
    		ListNode current=reverseList(start);
    		while(current!=null)
    		{
    			System.out.println(current.data);
    			current=current.next;
    		}
	  }
}
