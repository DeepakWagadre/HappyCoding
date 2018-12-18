import java.util.Scanner;

abstract class Plan
{
	double rate=0;
	public  abstract void getRate();
	public double calculateBill(int units)
	{
	     return rate*units;	
	}
	
}

class DomesticPlan extends Plan
{

	@Override
	public void getRate() {
		rate=3.50;
	}
}

class CommercialPlan extends Plan
{

	@Override
	public void getRate() {
		rate=7.5;
		
	}
}

class InstitutionalPlan extends Plan
{

	@Override
	public void getRate() {
		rate=5.2;
		
	}
}
class GetPlanFactory
{
	public Plan getPlan(String plan)
	{
		if(plan==null)
			return null;
		else if(plan.equals("domestic"))
			return new DomesticPlan();
		else if(plan.equals("commercial"))
			return new CommercialPlan();
		else
			return new InstitutionalPlan();
		
	}
}


public class FactoryDesignPattern 
{
      public static void main(String[] args)
      {
	          	
	          GetPlanFactory factory=new GetPlanFactory();
	          System.out.println("Please enter the plan:");
	          Scanner sc=new Scanner(System.in);
	          String plStr=sc.next();
	          Plan plan=factory.getPlan(plStr);
	          plan.getRate();
	          System.out.println("Please enter the units:");
	          int units=sc.nextInt();
	          double bill=plan.calculateBill(units);
              System.out.println("bill amount for the month is "+bill);
      }
}
