package com.deepak.trees;

public class DiameterOfATree 
{
	   public  static int diameter(TreeNode root)
	   {
		  
		   if(root==null)
			   return 0;
		   int lheight=height(root.left);
		   int rheight=height(root.right);
		   int ldiameter=diameter(root.left);
		   int rdiameter=diameter(root.right);
		   int diameter=Math.max((lheight+rheight)+1,Math.max(ldiameter, rdiameter));
		   return diameter;
	   }
	   
	   public static int height(TreeNode root)
	   {
		   if(root==null)
			   return 0;
		   if(root.left==null&&root.right==null)
			   return 1;
		   int ls=height(root.left);
		   int rs=height(root.right);
		   return Math.max(ls,rs)+1;
	   }
	
       public static void main(String[] args)
       {
            TreeNode root=new TreeNode(5);
	        root.left=new TreeNode(6);
	  		root.right=new TreeNode(8);
	  		root.left.left=new TreeNode(10);
	  		root.left.right=new TreeNode(4);
	  		//root.right.left=new TreeNode(11);
	  		int diameter=diameter(root);
	  		System.out.println(diameter);
  	   }
}
