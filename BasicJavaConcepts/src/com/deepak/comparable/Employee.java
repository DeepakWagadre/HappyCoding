package com.deepak.comparable;

public class Employee implements Comparable<Employee>
{
       private String empName;
       private int age;
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public int compareTo(Employee emp) {
		
		return this.age-emp.age;
	}
       
       
}
