class ListNode
{
	int data;
	ListNode next;
	public ListNode(int data)
	{
		super();
		this.data = data;
	}
	
}
public class NthFromLast {
   
	static int count=0;
	public static ListNode getNLast(ListNode start,int n)
	{
		if(start==null)
			return start;
		ListNode temp=getNLast(start.next,n);
		System.out.println(start.data);
		count++;
		if(count==n)
		{ 
			return start;
		}
		else
		   return temp;
	}
	public static void main(String[] args) {
		ListNode start=new ListNode(2);
		start.next=new ListNode(7);
		start.next.next=new ListNode(6);
		start.next.next.next=new ListNode(9);
		start.next.next.next.next=new ListNode(5);
		start.next.next.next.next.next=new ListNode(1);
		int n=5;
		ListNode temp=getNLast(start, n);
		System.out.println(n+":last node is "+temp.data);
	     			
	}
}
