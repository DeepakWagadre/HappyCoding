package com.deepak.comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.deepak.comparable.Employee;

public class TestComparator
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
	       TestAgeComparator ageComparator=new TestAgeComparator();
	       TestNameComparator nameComparator=new TestNameComparator();
	       
	       
	 }
}
