package com.tcs.sadarem.reports.DAO;


import java.util.ArrayList;


public interface NewDirectorReportOutsideDAO {
	
	public ArrayList  getabstractassesseddata(String fromdate,String todate,String district);

	public ArrayList getabstractassessedcampdata(String fromdate,
			String todate, String district1, String mandal, String village,
			String campId, String areatype2);

	public String getDistrictWiseHeading(String fromdate, String todate,
			String distname,String districtid);

	public String getCampWiseHeading(String fromdate, String todate,
			String distname, String mandalname, String villagename,
			String district1, String mandal, String village, String campId, String campname, String areatype2);

}
