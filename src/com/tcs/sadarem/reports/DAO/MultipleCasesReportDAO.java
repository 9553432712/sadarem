
package com.tcs.sadarem.reports.DAO;
import java.util.ArrayList;
public interface MultipleCasesReportDAO {

	ArrayList getsearchddata(String fromdate, String todate,
			String district, String mandal,String village, String areatype,
			String Disability,String gender,String caste,String education,
			String employment,String agefrom,String ageto,String disfrom, String disto,String Trigp);

	String getHeading(String fromdate, String todate, String distname,
			String mandalname,  String villagename,
			String district, String mandal, String village, String areatype, String tRIGPSel);

}
