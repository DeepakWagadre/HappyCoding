package com.deepak.trees;

public class CheckTwoMirrorTrees
{
	
	public static boolean checkForMirrorTrees(TreeNode root1, TreeNode root2)
	{
		if(root1==null&&root2==null)
			return true;
		if(root1==null||root2==null)
			return false;
		if(root1.data!=root2.data)
			return false;
		
		boolean flag1=checkForMirrorTrees(root1.left, root2.right);
		boolean flag2=checkForMirrorTrees(root1.right, root2.left);
		
	  return flag1&&flag2;	
	} 
    public static void main(String[] args)
    {
    	TreeNode root1=new TreeNode(5);
		root1.left=new TreeNode(6);
	    root1.right=new TreeNode(8);
	    root1.left.left=new TreeNode(10);
	    root1.left.right=new TreeNode(4);
	    root1.right.left=new TreeNode(11);	
	    
	    TreeNode root2=new TreeNode(5);
	    root2.left=new TreeNode(8);
	    root2.right=new TreeNode(6);
	    root2.left.right=new TreeNode(11);
	    root2.right.left=new TreeNode(4);
	    root2.right.right=new TreeNode(10);
	    boolean isMirror=checkForMirrorTrees(root1, root2);
	    System.out.println(isMirror);
	    
	}
}
