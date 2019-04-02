
public class DeleteANode
{
	
	public static void deleteNode(ListNode delNode)
	{
		ListNode temp=delNode.next;
		delNode.data=temp.data;
		delNode.next=temp.next;
		temp.next=null;
	}
	public static void display(ListNode start)
	{
		while(start!=null)
		{
			System.out.print(start.data+",");
			start=start.next;
		}
	}
   public static void main(String[] args) 
   {
	    ListNode start=new ListNode(2);
		start.next=new ListNode(7);
		start.next.next=new ListNode(6);   
		start.next.next.next=new ListNode(9);
		start.next.next.next.next=new ListNode(5);
		start.next.next.next.next.next=new ListNode(1);
		display(start);
		System.out.println("");
		System.out.println("------- After Delete ---------");
		deleteNode(start.next.next.next); 
		
		display(start);
   }
}
