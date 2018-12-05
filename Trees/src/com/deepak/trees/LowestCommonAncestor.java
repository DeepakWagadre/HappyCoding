package com.deepak.trees;

public class LowestCommonAncestor 
{
	/**
	 * Boolean variables which get true when the nodes n1 and n2 exists in the tree.
	 */
	static boolean v1=false;
	static boolean v2=false; 
	
	public static TreeNode lowestCommonAncestor(TreeNode root,int n1,int n2)
	{
		if(root==null)
		{
			return null;
		}
		if(root.data==n1)
		{
			v1=true;
			return root;
		}
		if(root.data==n2)
		{
			v2=true;
			return root;
		}
		TreeNode lca=lowestCommonAncestor(root.left, n1, n2);
		TreeNode rca=lowestCommonAncestor(root.right, n1, n2);
		
		if(lca!=null&&rca!=null)
			return root;
		return lca!=null?lca:rca;
	}
	public static void  lcaUtil(TreeNode root,int n1,int n2)
	{
		TreeNode lca=lowestCommonAncestor(root, n1, n2);
		if(v1==true&&v2==true)
		{
			System.out.println(lca.data);
		}
		else
		{
			System.out.println("Nodes doesn't exist in tree");
		}
	}
    public static void main(String[] args)
   {
	    TreeNode root=new TreeNode(5);
		root.left=new TreeNode(7);
		root.right=new TreeNode(19);
		root.left.right=new TreeNode(15);
		root.right.left=new TreeNode(9);
		root.left.left=new TreeNode(10);
		root.right.right=new TreeNode(11);
		lcaUtil(root,10,11);
   }
    //my Test
    
    public static TreeNode  testLCA(TreeNode root,int n1,int n2)
    {
    	if(root==null)
    	     return root;
    	if(root.data==n1&&root.data==n2)
    		 return root;
    	TreeNode lca=testLCA(root.left,n1,n2);
    	TreeNode rca=testLCA(root.right,n1,n2);
    	if(lca!=null&&rca!=null)
    		return root;
    	return lca!=null?lca:rca;
    	
    }
}
