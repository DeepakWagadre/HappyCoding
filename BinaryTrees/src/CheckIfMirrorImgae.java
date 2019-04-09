
public class CheckIfMirrorImgae
{
	
	public static boolean checkIfMirrorImage(TreeNode root1,TreeNode root2)
	{
		
		if(root1==null&&root2==null)
			return true;
		if(root1==null||root2==null)
			return false;
		if(!(root1.data==root2.data))
			return false;
		
		boolean leftTree=checkIfMirrorImage(root1.left, root2.right);
		boolean rightTree=checkIfMirrorImage(root1.right,root2.left);
		return leftTree&&rightTree;
	}
      public static void main(String[] args)
      {
		
    	  TreeNode root1=new TreeNode(5);
 	      root1.left=new TreeNode(4);
 	      root1.right=new TreeNode(9);
 	      root1.right.right=new TreeNode(3);
 	     TreeNode root2=new TreeNode(5);
	      root2.left=new TreeNode(9);
	      root2.right=new TreeNode(4);
	      root2.left.left=new TreeNode(3);
	      boolean flag=checkIfMirrorImage(root1,root2);
 	      System.out.println(flag);
	}
}
