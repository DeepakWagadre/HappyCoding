import java.util.Stack;

public class StackMinInConstantTime 
{
	static int minimum=0;
	public static void addElem(int data,Stack<Integer> stack)
	{
		if(stack.isEmpty())
		{
			stack.push(data);
			minimum=data;
		}
		else
		{
			if(data<minimum)
			{
			    
				stack.push(2*data-minimum);
				minimum=data;
			}
			else
			{
				stack.push(data);
			}
		}
	}
	
	public static void popElem(Stack<Integer> stack)
	{
		if(stack.isEmpty())
		{
			System.out.println("stack is empty");
		}
		else
		{
			int elem=stack.peek();
			if(elem<minimum)
			{
				minimum=2*minimum-elem;
				stack.pop();
			}
			else
			{
				stack.pop();
			}
		}
	}
	
	public static void main(String[] args) {
		Stack<Integer> stack=new Stack<Integer>();
		addElem(8,stack);
		addElem(10,stack);
		addElem(12,stack);
		addElem(1,stack);
		addElem(3,stack);
		addElem(11,stack);
		addElem(0,stack);
		popElem(stack);
		popElem(stack);
		popElem(stack);
		popElem(stack);
		System.out.println("minimum element is : "+minimum);
		
		
	}
	
}
