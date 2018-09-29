package com.tcs.sadarem.openreports.forms;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

public class Openreport16 extends ActionForm
{

	private static final long serialVersionUID = 1L;
	
	  private String district_id;
	  private String district_name;
	  
	  private ArrayList districtList;
	  
	  
	    
	  
	public String getDistrict_name() 
	{
		return district_name;
	}
	
	public void setDistrict_name(String district_name)
	{
		this.district_name = district_name;
	}
	
	public String getDistrict_id() 
	{
		return district_id;
	}
	
	public void setDistrict_id(String district_id) 
	{
		this.district_id = district_id;
	}
	
	public ArrayList getDistrictList()
	{
		return districtList;
	}
	
	public void setDistrictList(ArrayList districtList)
	{
		this.districtList = districtList;
	}
	

}
