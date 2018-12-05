package com.deepak.trees;

public class PrintAllPaths
{
	     public static void printAllPathes(TreeNode root,int[] treePaths,int pathLength)
	     {
	    	 if(root==null)
	    		 return;
	    	 treePaths[pathLength]=root.data;
	    	 pathLength++;
	    	 if(root.left==null&&root.right==null)
	    	 {
	    		 printArray(treePaths,pathLength);
	    	 }
	    	 else
	    	 {
	    		 printAllPathes(root.left,treePaths,pathLength);
	    		 printAllPathes(root.right,treePaths,pathLength);
	    	 }
	     }
	     
	     public static void printArray(int[] treePath,int length)
	     {
	    	 for(int i=0;i<length;i++)
	    	 {
	    		 System.out.print(treePath[i]+",");
	    	 }
	    	 System.out.println();
	     }
	
         public static void main(String[] args) 
         {
			TreeNode root=new TreeNode(5);
			root.left=new TreeNode(6);
		    root.right=new TreeNode(8);
		    root.left.left=new TreeNode(10);
		    root.left.right=new TreeNode(4);
		    int[] paths=new int[1000];
		    printAllPathes(root,paths,0);
		  }
}
