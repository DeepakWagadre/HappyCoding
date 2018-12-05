package com.deepak.trees.treetraversal;

import java.util.LinkedHashMap;
import java.util.Map;

import com.deepak.trees.TreeNode;

public class BottomViewOfATree
{
	public static void addElements(TreeNode root,LinkedHashMap<Integer,Integer> map,int hd)
    {
    	if(root==null)
    		return;
    	if(!map.containsKey(hd))
    	{
    		map.put(hd,root.data);
    	}
    	
    	addElements(root.left,map,hd-1);
    	
    	addElements(root.right,map,hd+1);
    }
    public static void printBottomView(TreeNode root)
    {
    	if(root==null)
    		return;
    	LinkedHashMap<Integer,Integer> map=new LinkedHashMap<Integer,Integer>();
    	addElements(root,map,0);
    	for(Map.Entry<Integer,Integer> entry:map.entrySet())
    	{
    		System.out.println(entry.getValue());
    	}
    	
    }
    
    
	public static void main(String[] args) 
	{
	    TreeNode root=new  TreeNode(2);
	    root.left=new TreeNode(5);
	    root.right=new TreeNode(6);
	    root.left.left=new TreeNode(1);
	    root.left.right=new TreeNode(8);
	    root.right.right=new TreeNode(7);
	    printBottomView(root);
	    
	}
}
