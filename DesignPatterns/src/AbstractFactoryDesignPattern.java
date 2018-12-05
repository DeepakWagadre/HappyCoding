import java.util.Scanner;

interface Bank
{
	
	public String getBankName();
	
}

abstract class Loan
{
	public  double interestRate;
	 abstract public void getRate();
	
	public double calculateLoanPayment(int amount,double period)
	{
		return amount+((amount*interestRate*period)/100);
	}
}

class HomeLoan extends Loan
{

	@Override
	public void getRate() {
		
		interestRate=7.3;
	}
	
}

class VehicleLoan extends Loan
{

	@Override
	public void getRate() {
		interestRate=8.5;
		
	}
}

class BusinessLoan extends Loan
{

	@Override
	public void getRate() {
		interestRate=12.8;
	}
	
}

class HDFCBank implements Bank
{

	@Override
	public String getBankName() {
		
		return "HDFC Bank";
	}
}

class ICICIBank implements Bank
{

	@Override
	public String getBankName() {
		
		return "ICICI Bank";
	}
}

class SBIBank implements Bank
{

	@Override
	public String getBankName() {
		
		return "SBI Bank";
	}
	
}

abstract class AbstractFactory{
	public abstract Loan getLoan(String loan);
	public abstract Bank getBank(String bank);
}

class LoanFactory extends AbstractFactory
{
	@Override
	public Loan getLoan(String loanType)
	{
		if(loanType==null)
			return null;
		if(loanType.equals("HomeLoan"))
			return new HomeLoan();
		else if(loanType.equals("VehicleLoan"))
			return new VehicleLoan();
		else 
			return new BusinessLoan();
	}

	@Override
	public Bank getBank(String bank) {
		// TODO Auto-generated method stub
		return null;
	}
}



class BankFactory extends AbstractFactory
{
	public Bank getBank(String bank)
	{
		if(bank.equalsIgnoreCase("HDFC Bank"))
			return new HDFCBank();
		else if(bank.equalsIgnoreCase("ICICI Bank"))
			return new ICICIBank();
		else 
			return new SBIBank();
		
	}

	@Override
	public Loan getLoan(String loan) {
		// TODO Auto-generated method stub
		return null;
	}
}
class FactoryCreator
{
	public static AbstractFactory getFactory(String factory)
	{
		if(factory==null)
			return null;
		else if(factory.equalsIgnoreCase("Bank"))
			return new BankFactory();
		else 
			return new LoanFactory();
	
	}
}




public class AbstractFactoryDesignPattern 
{
	
	  
      public static void main(String[] args) 
      {
    	 System.out.println("Enter the factory name");
    	 Scanner sc=new Scanner(System.in);
    	 String factoryType=sc.nextLine();
    	 AbstractFactory factory= FactoryCreator.getFactory(factoryType);
    	 System.out.println("Enter the bank");
    	 String bankName=sc.next();
    	 Bank bank=factory.getBank(bankName);
    	 System.out.println(bank.getBankName());
    	 
    	 
		   
	  }
}
