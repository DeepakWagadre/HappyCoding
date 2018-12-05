package com.deepak.arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test
{
	
	 
    public  static boolean findNumberUtil(List<Integer> arr,int elem,int start,int end)
    {
        
        
        if(start>=end)
              return false;
        int mid=(start+end)/2;
        if(arr.get(mid)==elem)
            return true;
        else if(arr.get(mid)>elem)
            return findNumberUtil(arr,elem,start,mid-1);
        else if(arr.get(mid)<elem)
            return findNumberUtil(arr,elem,mid+1,arr.size()-1);
        return false;
        
    }
	
	public static void main(String[] args)
	{
		List<Integer> list=new ArrayList<Integer>();
		list.add(8);
		list.add(10);
		list.add(11);
		list.add(5);
		list.add(9);
		Collections.sort(list);
		System.out.println(list);
		 boolean isFound=findNumberUtil(list,8,0,list.size());
		 System.out.println(isFound);
		 List<Color> enumList=new ArrayList<Color>();
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

	
	
	enum Color
	{
		RED,BLUE,GREEN;
	}

}
