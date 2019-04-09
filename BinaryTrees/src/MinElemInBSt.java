
public class MinElemInBSt 
{
	public static TreeNode minElemInBST(TreeNode root)
	{
		if(root==null)
			return root;
		if(root.left==null&&root.right==null)
			return root;
		return minElemInBST(root.left);
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
	      TreeNode min=minElemInBST(root);
	      System.out.println(min.data);
	      //levelOrderTraversal(root);
    }
}
