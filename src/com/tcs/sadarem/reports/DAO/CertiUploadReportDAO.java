
package com.tcs.sadarem.reports.DAO;
import java.util.ArrayList;
public interface CertiUploadReportDAO {

	ArrayList getabstractcertiuploaddata(String fromdate, String todate,
			String district, String mandal,String village, String areatype,  String Trigp);

	String getHeading(String fromdate, String todate, String distname,
			String mandalname,  String villagename,
			String district, String mandal, String village, String areatype, String tRIGPSel);

}

