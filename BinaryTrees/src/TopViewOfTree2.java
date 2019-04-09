import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;



class QNode
{
	TreeNode node;
	int level;
	public QNode(TreeNode node, int level) {
		super();
		this.node = node;
		this.level = level;
	}
	
}

public class TopViewOfTree2 
{
	  public static void topView(TreeNode root)
	  {
		  Map<Integer,TreeNode> map=new HashMap<Integer,TreeNode>();
		  Queue<QNode> queue=new LinkedList<QNode>();
		  if(root==null)
			  return;
		  else
		  {
			  queue.add(new QNode(root,0));
		  }
		  while(!queue.isEmpty())
		  {
			QNode temp=queue.poll();
			if(!map.containsKey(temp.level))
				  map.put(temp.level, temp.node);
			
			if(temp.node.left!=null)
			{
				queue.add(new QNode(temp.node.left, temp.level-1));
			}
			if(temp.node.right!=null)
			{
				queue.add(new QNode(temp.node.right, temp.level+1));
			}
		  }
		  for(Map.Entry<Integer,TreeNode> entry:map.entrySet())
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
	      topView(root);
	         	
	  }
}
