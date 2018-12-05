package com.deepak.set;

import java.util.LinkedHashSet;

import org.omg.Messaging.SyncScopeHelper;

/**
 * @author dwagadre
 *
 */
public class SetDriverClass 
{
   public static void main(String[] args) 
   {
	    DHashSet set=new DHashSet();
	    
	    boolean check1=set.add(10);
	    boolean check2=set.add(30);
	    boolean check3=set.add(10);
	    boolean check4=set.add(50);
	    boolean check5=set.add(60);
	    boolean check6=set.add(50);
	    
	    System.out.println( "1 : " + check1+ " 2 : "+check2 + " 3 : "+check3);
	    
	    System.out.println( " ");
	    
	    set.display();
	    
	    boolean check=set.contains(80);
	    
	    System.out.println("size of the list is "+set.getSize());
	    
	    System.out.println("contains : "+check);
	    
	    
	    boolean flag=set.remove(10);
	    
	    System.out.println("value removed :"+flag);
	    
	    set.display();
	    System.out.println("size of the list  after removing element is "+set.getSize());
	    
   }
}
