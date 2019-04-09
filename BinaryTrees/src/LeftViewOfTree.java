
public class LeftViewOfTree 
{
	
	static int max=0;
	public static void leftView(TreeNode root,int level)
	{
		if(root==null)
			return;
		if(level>max)
		{
			System.out.println(root.data);
			max=level;
		}
		leftView(root.left,level+1);
	    leftView(root.right,level+1);
		
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
	      leftView(root, 1);
  	 }
}
