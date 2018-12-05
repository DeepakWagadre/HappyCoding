import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

public class Test {
	
	  
	      String findQualifiedNumbers(int[] numberArray) 
	    {
	    	  
	    	   Map<Integer,String> map=new HashMap<Integer,String>();
	    	 
	    	   LinkedHashSet<Integer> set=new LinkedHashSet<Integer>();
	    	   set.add(1);
	            StringBuilder str=null;
	            List<Integer> resultantList=new ArrayList<Integer>();
	            for(int i=0;i<numberArray.length;i++)
	            {
	                     int count=0;
	                     int number=numberArray[i];
	                     int orgNumber=number;
	                     try
	                     {
	                          while(number>0)
	                         {
	                             int temp=number%10;
	                             if(temp==1||temp==2||temp==3)
	                             {
	                                 count++;
	                             }
	                              number=number/10;
	                         }
	                        if(count>=3)
	                       {
	                            resultantList.add(orgNumber);
	                            str=new StringBuilder();
	                        }
	                     }
	                     catch(Exception e)
	                     {
	                    	 e.printStackTrace();
	                     }
	           }
	                  
	              
	             Collections.sort(resultantList);     
	             
	            
	            for(Integer l:resultantList)
	            {
	                 str=str.append(l.toString()).append(",");
	            }
	             if(str!=null)
	             {
	                 str=str.deleteCharAt(str.length()-1);
	                 return str.toString();
	             }
	            else
	                 return "-1";
	            
	    }

}
