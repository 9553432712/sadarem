package com.tcs.sadarem.reports.DAO;

import java.util.ArrayList;

public interface EducationWiseDAO {

	ArrayList getabstracteducationdata(String fromdate, String todate,
			String district, String mandal, String village, String education,String Trigp);

	String getHeading(String fromdate, String todate, String distname,
			String mandalname,  String district,
			String mandal, String areatype, String educationname, String tRIGPSel);

}
