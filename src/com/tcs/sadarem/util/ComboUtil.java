package com.tcs.sadarem.util;
/**
 * Utility Class to create dropdowns for the JSPs.
 * 
 * 
 *@author Neelima Nair(155411) 10-Aug-2007
 * 
 * */
import java.util.*;

public class  ComboUtil 
{
       /**
       * Returns as a String the HTML text for the dropdown box having the values in the arraylist
     * @throws Exception 
       * @params name The name and Id for the dropdown(the Name and Id are the same)
       * @params datalist Contains the list of options to be displayed in the dropdown
       * @params selVal The value to which the dropdown has to be seleted to
       * @params style The stylesheet class, that the dropdown has to implement
       * @params action Any Event that the drop-down has to implement
       * @params def has to be set to true if the drop-down requires an option for -1(ALL)
       * @returns HTMLText for the dropdown box
       * 
       */

  
  public static String createStrComboBoxAuto(String name,ArrayList dataList,String selVal,String style,String action,boolean def,boolean type,String DisplayText)  
  {
      String strOut="<SELECT NAME='"+name+"' id='"+name+"' class='"+style+"' "+ action+">";      
      if(selVal==null)
      {
          selVal="";
      }
      selVal=selVal.trim();
      
      if(def)
      {
    	  if(!DisplayText.equals(""))
    	  {
    		  strOut=strOut+"<option value='-1' title="+DisplayText+">"+DisplayText+"</option>";    		  
    	  }
    	  else if(type==true)
    	  {
    		  strOut=strOut+"<option value='-1'>-Select-</option>";
    	  }
    	  else
    	  {
    		  strOut=strOut+"<option value='-1'>All</option>";  
    	  }
      }
      else if(selVal.equals("-1"))
      {
    	  if(!DisplayText.equals(""))
    	  {
    		  strOut=strOut+"<option value='-1' title='"+DisplayText+"'>"+DisplayText+"</option>";    		  
    	  }
    	  else if(type==true)
    	  {
    		  strOut=strOut+"<option value='-1' SELECTED>-Select-</option>";
    	  }
    	  else
    	  {
    		  strOut=strOut+"<option value='-1' SELECTED>All</option>";  
    	  }    
      }
      strOut=strOut+populateComboWithID(dataList,selVal)+"</SELECT>";
      return strOut;
  }
  

 /**
       * Returns the HTML text for the optionlist of dropdown box having the values in the arraylist
       * Called internally by createComboStrBox
       * @see createStrComboBox()
       * @params comboList Contains the list of options to be displayed in the dropdown
       * @params cmp The value to which the dropdown has to be seleted to
       * @returns HTML text for the optionlist
       * 
       * 
       */
private static String populateComboWithID(ArrayList comboList,String cmp)
{
		String strOut="";
		int liComboSize = 0;
		try
		{
		    if(comboList != null && !comboList.isEmpty())
		    {
		      liComboSize = comboList.size();
		      for(int i=0;i<liComboSize;i++)
		      {
		    	 
		         ArrayList dataList = (ArrayList)comboList.get(i);
		        
		         String ID=(String)dataList.get(0);
		         //System.out.println("Lengthb4ID"+ID.length());
		        // System.out.println("LengthAftertrip"+(ID.trim()).length());
		       
		        strOut=strOut+"<OPTION VALUE='"+dataList.get(0).toString().trim()+"'";
		         if ((ID.trim()).equalsIgnoreCase(cmp.trim()))
		         {
		        	// System.out.println("InsideCMP"+cmp);
		           //  System.out.println("Insideget(0)"+dataList.get(0));
		          strOut=strOut+" SELECTED";
		         }
		          strOut=strOut+" title='"+dataList.get(1).toString().trim().replaceAll("'","")+"'>"+dataList.get(1).toString().trim()+"</OPTION>";
		      }  
		    	}
		}
		catch(Exception e)
		{
			System.out.println("Exception in Combo Genration :: "+e.getLocalizedMessage());
		}
    return strOut;
	}
public static String createStrComboBoxAutoWithAttribute
(
		String name,
		ArrayList dataList,
		String selVal,
		String style,
		String action,
		boolean def,
		boolean type,
		String DisplayText,
		String attribute
) 
{
    String strOut=""; 
    try
    {
    	
    	strOut="<SELECT NAME='"+name+"' id='"+name+"' class='"+style+"' "+ action+" "+ attribute +">"; 
			    if(selVal==null)
			    {
			       selVal="";
			    }
			       selVal=selVal.trim();
			    
			    if(def)
			    {
			  	  if(!DisplayText.equals(""))
			  	  {
			  		  strOut=strOut+"<option value='-1' title="+DisplayText+">"+DisplayText+"</option>";    		  
			  	  }
			  	  else if(type==true)
			  	  {
			  		  strOut=strOut+"<option value='-1'>-Select-</option>";
			  	  }
			  	  else
			  	  {
			  		  strOut=strOut+"<option value='-1'>All</option>";  
			  	  }
			    }
			    else if(selVal.equals("-1"))
			    {
			  	  if(!DisplayText.equals(""))
			  	  {
			  		  strOut=strOut+"<option value='-1' title="+DisplayText+">"+DisplayText+"</option>";    		  
			  	  }
			  	  else if(type==true)
			  	  {
			  		  strOut=strOut+"<option value='-1' SELECTED>-Select-</option>";
			  	  }
			  	  else
			  	  {
			  		  strOut=strOut+"<option value='-1' SELECTED>All</option>";  
			  	  }    
			    }
			    
    strOut=strOut+populateComboWithID(dataList,selVal)+"</SELECT>";
    }
    catch(Exception e)
    {
    	e.printStackTrace();
    }
    return strOut;
}
}