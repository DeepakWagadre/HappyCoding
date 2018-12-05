package com.deepak.bst;

import com.deepak.trees.TreeNode;

public class CheckIfBST 
{
	public static boolean isBST(TreeNode root,int min, int max)
	{
		if(root==null)
			return true;
		 if(root.data<min||root.data>max)
			return false;
		boolean ls=isBST(root.left,min,root.data-1);
		boolean rs=isBST(root.right,root.data+1,max);
		
		return ls&&rs;
	}
	static boolean isBSTUtil(TreeNode node, int min, int max)
    {
        /* an empty tree is BST */
        if (node == null)
            return true;
 
        /* false if this node violates the min/max constraints */
        if (node.data < min || node.data > max)
            return false;
 
        /* otherwise check the subtrees recursively
        tightening the min/max constraints */
        // Allow only distinct values
        return (isBSTUtil(node.left, min, node.data-1) &&
                isBSTUtil(node.right, node.data+1, max));
    }
    public static void main(String[] args)
    {
    	int min=Integer.MIN_VALUE;
    	int max=Integer.MAX_VALUE;
        TreeNode root=new TreeNode(15);
        root.left=new TreeNode(12);
        root.right=new TreeNode(18);
        root.left.left=new TreeNode(12);
        root.left.right=new TreeNode(13);
        root.right.left=new TreeNode(16);
        root.right.right=new TreeNode(20);
        boolean isBST=isBST(root, min, max);
        System.out.println(isBST);
	}
}
