package com.deepak.trees.treetraversal;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import com.deepak.trees.TreeNode;

public class VerticalTreeTraversing 
{
    /**
     *
     * @param root
     * @param map - we create map to store elements at each horizontal distance
     * @param height - horizontal distance. For root it's 0, for left child it's -1 and 1 for right child
     */
	public static void addElements(TreeNode root,Map<Integer,Vector<Integer>> map,int height)
	{
		if(root==null)
			return;
		/**
		 * Check whether for a particular horizontal distance any array is present or not. If not create a new one 
		 * or add to the existing one.
		 */
		Vector<Integer> vector=map.get(height);
		if(vector==null)
		{
			vector=new Vector<Integer>();
			vector.add(root.data);
			
		}
		else
		{
			vector.addElement(root.data);
		}
		
		map.put(height,vector);
		addElements(root.left, map, height-1);
		addElements(root.right,map,height+1);
		
	}
	public static void printVerticalTree(TreeNode root)
	{
		Map<Integer,Vector<Integer>> map=new TreeMap<Integer,Vector<Integer>>();
		addElements(root,map,0);
		for(Map.Entry<Integer,Vector<Integer>> entry:map.entrySet())
		{
			System.out.println(entry.getValue());
		}
	}
	
   public static void main(String[] args) 
   {
	   TreeNode root=new TreeNode(5);
	   root.left=new TreeNode(6);
       root.right=new TreeNode(8);
       root.left.left=new TreeNode(10);
       root.left.right=new TreeNode(4);
       root.right.left=new TreeNode(11);
       printVerticalTree(root);
}
   
}
