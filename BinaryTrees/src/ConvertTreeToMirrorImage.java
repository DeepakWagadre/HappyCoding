
public class ConvertTreeToMirrorImage 
{
	public static void mirrorImage(TreeNode root)
	{
		if(root==null)
			return;
		mirrorImage(root.left);
		mirrorImage(root.right);
		TreeNode temp=root.left;
		root.left=root.right;
		root.right=temp;
	}
	
	
     public static void main(String[] args)
     {
    	  TreeNode root=new TreeNode(5);
 	      root.left=new TreeNode(4);
 	      root.right=new TreeNode(9);
 	     
 	      
		
		  root.left.left=new TreeNode(3); root.left.left.left=new TreeNode(7);
		  root.left.right=new TreeNode(8); root.right.left=new TreeNode(1);
		  root.right.right=new TreeNode(2); root.right.right.left=new TreeNode(10);
		  LevelOrderTraversal.levelOrderTraversal(root);
 	      mirrorImage(root);
 	      System.out.println("----------------");
 	      LevelOrderTraversal.levelOrderTraversal(root);
		 	
	 }
}
