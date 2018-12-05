
public class Employee12 
{
   private int empId;
   private String empName;
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + empId;
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Employee12 other = (Employee12) obj;
	if (empId != other.empId)
		return false;
	return true;
}

   
}
