import java.util.LinkedList;
import java.util.Queue;

public class MirrorViewOfTree 
{
	public static void mirrorViewOfTree(TreeNode root)
	{
		if(root==null)
			return;
		mirrorViewOfTree(root.left);
		mirrorViewOfTree(root.right);
		TreeNode temp=root.left;
		root.left=root.right;
		root.right=temp;
	}
	 public static void levelOrderTraversal(TreeNode root)
	   {
		   if(root==null)
			   return;
		   Queue<TreeNode> queue=new LinkedList<TreeNode>();
		   queue.add(root);
		   while(!queue.isEmpty())
		   {
			  // System.out.println("in queueu loop");
			   TreeNode temp=queue.poll();
			   System.out.print(temp.data+" ");
			   if(temp.left!=null)
				   queue.add(temp.left);
			   if(temp.right!=null)
				   queue.add(temp.right);
		   }
		   
	   }
    public static void main(String[] args)
    {
    	TreeNode root=new TreeNode(5);
	      root.left=new TreeNode(4);
	      root.right=new TreeNode(9);
	      root.left.left=new TreeNode(3);
	      root.left.left.left=new TreeNode(7);
	      root.left.right=new TreeNode(8);
	      root.right.left=new TreeNode(1);
	      root.right.right=new TreeNode(2);
	      root.right.right.left=new TreeNode(10);
	      levelOrderTraversal(root);
	      mirrorViewOfTree(root);	
	      System.out.println("----");
	      levelOrderTraversal(root);
	}
}
