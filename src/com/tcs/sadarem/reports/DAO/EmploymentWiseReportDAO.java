

package com.tcs.sadarem.reports.DAO;

import java.util.ArrayList;

public interface EmploymentWiseReportDAO {

	ArrayList getabstractemploymentdata(String fromdate, String todate,
			String district, String mandal, String village, String employment,String TRIGP);

	String getHeading(String fromdate, String todate, String distname,
			String mandalname,  String district,
			String mandal, String areatype, String employmentname, String tRIGPSel);

}
