package com.deepak.trees;


class Node 
{
    int data;
    Node left, right;
  
    Node(int item) 
    {
        data = item;
        left = right = null;
    }
}
  
class IdenticalTrees 
{
    Node root1, root2;
  
    /* Given two trees, return true if they are
       structurally identical */
    boolean identicalTrees(Node a, Node b) 
    {
        /*1. both empty */
        if (a == null && b == null)
            return true;
             
        if (a != null && b != null) 
             return (a.data == b.data && identicalTrees(a.left, b.left)&& identicalTrees(a.right, b.right));
        
        return false;
        
    }
   public static  void printAllPaths(Node root,int[] arr,int level)
   {
	   if(root==null)
		   return;
	   arr[level]=root.data;
	   if(root.left==null&&root.right==null)
	   {
		   printArray(arr,level);
		   return;
	   }
	   printAllPaths(root.left,arr,level+1);
	   printAllPaths(root.right,arr,level+1);
	   
   }
   public static void printArray(int[] arr,int level)
   {
	   for(int i=0;i<=level;i++)
	   {
		   System.out.print(arr[i]+",");
	   }
	   
	   System.out.println();
   }
    
   public static int printMaxPath(Node root)
   {
	   if(root==null)
		       return 0;
	  if(root.left==null&&root.right==null)
	  {
		  return root.data;
	  }
	  int ls=printMaxPath(root.left);
	  int rs=printMaxPath(root.right);
	  
	  return Math.max(ls, rs)+root.data;
   }
  
    /* Driver program to test identicalTrees() function */
    public static void main(String[] args) 
    {
    	IdenticalTrees tree = new IdenticalTrees();
  
        tree.root1 = new Node(1);
        tree.root1.left = new Node(2);
        tree.root1.right = new Node(3);
        tree.root1.left.left = new Node(4);
        tree.root1.left.right = new Node(5);
  
        tree.root2 = new Node(1);
        tree.root2.left = new Node(2);
        tree.root2.right = new Node(13);
        tree.root2.left.left = new Node(14);
        tree.root2.left.right = new Node(5);
  
      //  if (tree.identicalTrees(tree.root1, tree.root2))
         //   System.out.println("Both trees are identical");
      //  else
       //     System.out.println("Trees are not identical");
        int arr[]=new int[100];
        int arr2[]=new int[100];
        printAllPaths(tree.root2,arr,0);
        int max=printMaxPath(tree.root2);
        System.out.println(max);
  
    }
}
 
