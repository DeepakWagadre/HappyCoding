
import java.util.*;


class Car
{
    private String carManufacturer;
    private String modelNo;
	public String getCarManufacturer() {
		return carManufacturer;
	}
	public void setCarManufacturer(String carManufacturer) {
		this.carManufacturer = carManufacturer;
	}
	public String getModelNo() {
		return modelNo;
	}
	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}
    
   
    
}
class Technician
{
    String technicianId;
    String technicianName;
    Set<Skills> skillSet;
    Set<Car> specializesInCar;
    String contactNo;
    int rating;
    
}
class Skills
{
	
}


class ServiceRequest
{
   Car car;
   Technician technician;
    
}
class AdminUser
{
        private String userName;
        private String password;
        public String getUserName() {
            return userName;
        }
        public void setUserName(String userName) {
            this.userName = userName;
        }
        public String getPassword() {
            return password;
        }
        public void setPassword(String password) {
            this.password = password;
        }
        
}
class Parts
{
	/*
	 * Enum of parts
	 */
	 String comment;
}
class Utility
{
	public boolean validUser(AdminUser user)
	{
		/*
		 * This function authenticated the user.
		 */
		return true;
	}
	public List<Technician> getTechnicians(String carBrand)
	{
		return null;
	}
	
}

 public class Solution {
    public static void main(String args[]) throws Exception
    {
          //User sends a Http Request to the syetem and the system returns a AdminUser object 
          AdminUser user=new AdminUser();
          Utility util=new Utility();
          ServiceRequest request;
          System.out.println("Enter the username");
          Scanner in= new Scanner(System.in);
          String userName=in.next();
          System.out.println("Enter Password");
          String password=in.next();
          user.setPassword(password);
          user.setUserName(userName);
          if(util.validUser(user))
          {
                request=new ServiceRequest();
                System.out.println("Enter car manufacturer");
                String carManufacturer=in.next();
                System.out.println("Enter car model no.");
                String carModel=in.next();
                Car car=new Car();
                car.setCarManufacturer(carManufacturer);
                car.setModelNo(carModel);
                System.out.println("");
                
                
                
          }
          
          
    }
 }
