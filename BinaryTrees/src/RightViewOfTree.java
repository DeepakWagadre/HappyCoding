import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class RightViewOfTree 
{
	  public static void rightView(TreeNode root)
	  {
		  Map<Integer,TreeNode> map=new HashMap<Integer, TreeNode>();
		  int level=0;
		  Queue<TreeNode> queue=new LinkedList<TreeNode>();
		  if(root==null)
			  return;
		  else
		  {
		       queue.add(root);
		       
		       while(!queue.isEmpty())
		       {
		    	   TreeNode temp=queue.poll();
		    	   if(!map.containsKey(level))
		    	              map.put(level, temp);
		    	   if(temp.right!=null)
		    	   {
		    		   level++;
		    		    queue.add(temp.right);
		    		    if(!map.containsKey(level))
		    		           map.put(level,temp.right);
		    	   }
		    	   else if(temp.left!=null)
		    	   {
		    		   level++;
		    		   queue.add(temp.left);
		    		   if(!map.containsKey(level))
		    		             map.put(++level, temp.left);
		    	   }
		       }
		  }
		  for(Map.Entry<Integer,TreeNode>entry:map.entrySet())
		  {
			System.out.println(entry.getValue().data);  
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
	      root.right.right.left=new TreeNode(10);
	      rightView(root);
	 }
}
