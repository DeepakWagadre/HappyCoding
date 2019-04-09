
public class MirrorImageOfTree 
{
	
	
	public static TreeNode mirrorImageOfTree(TreeNode root1)
	{
		if(root1==null)
			return root1;

		TreeNode root2=new TreeNode();
		root2=root1;
		TreeNode temp=null;;
		TreeNode left=mirrorImageOfTree(root1.left);
		TreeNode right=mirrorImageOfTree(root1.right);
		temp=left;
		root2.left=right;
		root2.right=temp;
		return root2;
	}
	public static void inOrderTraversal(TreeNode root)
	{
		if(root==null)
			return;
		inOrderTraversal(root.left);
		System.out.print(root.data);
		inOrderTraversal(root.right);
		
	}
    public static void main(String[] args) 
    {
    	 TreeNode root=new TreeNode(5);
	      root.left=new TreeNode(4);
	      root.right=new TreeNode(9);
	      root.left.left=new TreeNode(3);
	      //root.left.left.left=new TreeNode(7);
	      //root.left.right=new TreeNode(8);
	      //root.right.left=new TreeNode(1);
	       root.right.right=new TreeNode(2);
	      //root.right.right.left=new TreeNode(10);
	      inOrderTraversal(root);
	      TreeNode root2=mirrorImageOfTree(root);
	      //inOrderTraversal(root);
	      System.out.println("================  Mirror Tree =================");
	      inOrderTraversal(root2);
	      
	}
}
