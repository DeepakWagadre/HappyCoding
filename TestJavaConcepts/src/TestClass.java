import java.util.ArrayList;
import java.util.List;


class Employee
{
     int empId;
     String empName;
	public Employee(int empId, String empName) {
		super();
		this.empId = empId;
		this.empName = empName;
	}
     
}
public class TestClass 
{
   public static void main(String[] args) {
	
	   String str[]={"deepak","test"};
	   
	  List list=new ArrayList<String[]>();
	  
	  
	  list.add(str);
	  
	  System.out.println(str.toString());
	  
	  final Employee emp=new Employee(101,"deepak");
	  emp.empId=102;
	  emp.empName="Ayush";
	  System.out.println("employee id is"+emp.empId);
	  System.out.println("employee name is "+emp.empName);
	  
	  Employee emp2=new Employee(103,"Pooja");
	  
	  
	 // emp=emp2;
	  
	   
	   
}
}
