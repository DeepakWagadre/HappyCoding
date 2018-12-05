package com.deepak.trees;

import java.util.Random;

public class Driver
{
	
	public static void add(TreeNode root,TreeNode temp)
	{
		Random r=new Random(100);
		while(true)
		{
			if(r.nextInt(3)==0)
			{
				if(root.left==null)
				{
					root.left=temp;
					return;
				}
				else
				{
					root=root.left;
				}
			}//if
			else
			{
				if(root.right==null)
				{
					root.right=temp;
					return;
				}
				else
				{
					root=root.right;
				}
				
			}//else
		}
	}
	
    public static void main(String[] args) 
    {
    	System.out.println("constructing binary tree");
	    TreeNode root=new TreeNode(0);
	    int size=10;
	    for(int i=0;i<size-1;i++)
	    {
	    	TreeNode temp=new TreeNode(i);
	    	 add(root,temp);
	    }
	    System.out.println("Completed");
	    System.out.println("the size of the tree is "+BinaryTreeSize.treeSize1(root));
	    
	}
}
