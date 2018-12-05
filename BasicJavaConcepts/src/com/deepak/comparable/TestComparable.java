package com.deepak.comparable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestComparable 
{
     public static void main(String[] args)
     {
    	   Employee emp2=new Employee();
	       emp2.setEmpName("Pooja Wagadre");
	       emp2.setAge(251);
	       Employee emp1=new Employee();
	       emp1.setEmpName("Deepak Wagadre");
	       emp1.setAge(22);
	       Employee emp3=new Employee();
	       emp3.setEmpName("Ayush Wagadre");
	       emp3.setAge(130);
	       List<Employee> list=new ArrayList<Employee>();
	       list.add(emp1);
	       list.add(emp2);
	       list.add(emp3);
	       Collections.sort(list);
	       
	       for(Employee emp:list)
	       {
	    	   System.out.println(emp.getEmpName());
	    	   System.out.println(emp.getAge());
	       }
	   
	 }
}
