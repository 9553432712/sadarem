package com.tcs.sadarem.issuetracksystem.actions;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IssueRequestRaiseReportDAO {
	
	public ArrayList ExtractRoleNmaes();		
	public ArrayList ExtractRolesServicesMap();	
	public  ArrayList getaccData(String query) throws SQLException, Exception;
	public ArrayList columnData(String qry) throws SQLException, Exception;
}
