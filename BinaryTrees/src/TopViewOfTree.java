import java.util.HashSet;
import java.util.Set;

class TreeNode
{
	TreeNode right;
	TreeNode left;
	int data;
	TreeNode(int data)
	{
		this.data=data;
	}
	public TreeNode() {
		// TODO Auto-generated constructor stub
	}
}


public class TopViewOfTree 
{
	
	public static void topView(TreeNode root,int level,Set<Integer> set)
	{
		if(root==null)
			return;
		if(!set.contains(level))
		{
			System.out.println(root.data);
			set.add(level);
		}
		topView(root.left, level-1, set);
		topView(root.right, level+1, set);
		
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
	      Set<Integer> set=new HashSet<Integer>();
	      topView(root, 0, set);
	  }
}
