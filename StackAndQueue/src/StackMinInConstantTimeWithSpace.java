import java.util.Stack;

public class StackMinInConstantTimeWithSpace
{
	  public static void addElem(int data,Stack<Integer> stack,Stack<Integer> minStack)
	  {
		  if(stack.isEmpty())
		  {
			  stack.push(data);
			  
		  }
		  else
		  {
			 stack.push(data);
			 if(minStack.isEmpty())
			 {
				 minStack.push(data);
			 }
			 else if(minStack.peek()>data)
			 {
				 minStack.push(data);
			 }
		  }
	  }
	  
	  public static int  getMin(Stack<Integer> stack,Stack<Integer> minStack)
	  {
		  int min=minStack.peek();
		  return min;
	  }
	  public static void popElem(Stack<Integer> stack,Stack<Integer> minStack)
	  {
		  int top=stack.peek();
		  int min=minStack.peek();
		  if(top==min)
		  {
			  stack.pop();
			  minStack.pop();
		  }
		  else
		  {
			  stack.pop();
		  }
	  }
      public static void main(String[] args)
      {
	           Stack<Integer> stack=new Stack<Integer>();
	           Stack<Integer> minStack=new Stack<Integer>();
	           addElem(9,stack,minStack);
	           addElem(10,stack,minStack);
	           addElem(11,stack,minStack);
	           addElem(5,stack,minStack);
	           addElem(4,stack,minStack);
	           addElem(15,stack,minStack);
	           System.out.println("minimum element is "+getMin(stack, minStack));
	           System.out.println(stack);
	           popElem(stack,minStack);
	           popElem(stack,minStack);
	           System.out.println(stack);
	           System.out.println("minimum element is "+getMin(stack, minStack));
	           
	  }
}
