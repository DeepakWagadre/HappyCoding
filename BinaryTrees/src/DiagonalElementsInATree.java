import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class DiagonalElementsInATree {
	
	
	public static void diagonalElements(TreeNode root,Map<Integer,List<Integer>> map,int depth)
	{
		if(root==null)
			return;
		List<Integer> list=map.get(depth);
		
		if(list==null)
		{
			list=new ArrayList<Integer>();
			list.add(root.data);
		}
		else
			list.add(root.data);
		map.put(depth, list);
		
		diagonalElements(root.left, map, depth+1);
		diagonalElements(root.right, map, depth);
	}
	 static void diagonalPrint(TreeNode root,Map<Integer,List<Integer>> map) 
	    { 
	        
	        System.out.println("Diagonal Traversal of Binnary Tree"); 
	        for (Entry<Integer, List<Integer>> entry : map.entrySet()) 
	        { 
	            System.out.println(entry.getValue()); 
	        } 
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
	      Map<Integer,List<Integer>> map=new HashMap<Integer,List<Integer>>();
	      
	      diagonalElements(root,map,0);
	      diagonalPrint(root,map);
		
	}

}
