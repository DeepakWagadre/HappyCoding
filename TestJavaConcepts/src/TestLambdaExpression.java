interface FuncInterface 
{ 
    // An abstract function 
    void abstractFun(int x); 
  
    // A non-abstract (or default) function 
    default void normalFun() 
    { 
       System.out.println("Hello"); 
    } 
} 
 




public class TestLambdaExpression 
{
	//FuncInterface fob=(int x)->System.out.println(2*x);
    //fob.abstractFun(5);
    
}
