package com.tcs.sadarem.reports.DAO;
import java.util.ArrayList;


public interface DirectorReportOutsideDAO {
	
	public ArrayList  getabstractassesseddata(String fromdate,String todate,String district,String mandal,String village,String disability, String areatype1,String Trigp);

	public ArrayList getabstractassessedcampdata(String fromdate,
			String todate, String district1, String mandal, String village,
			String campId, String areatype2);

	public String getDistrictWiseHeading(String fromdate, String todate,
			String distname, String mandalname, String villagename, String district1, String mandal, String village, String areatype1, String tRIGPSel1);

	public String getCampWiseHeading(String fromdate, String todate,
			String distname, String mandalname, String villagename,
			String district1, String mandal, String village, String campId, String campname, String areatype2);

}
