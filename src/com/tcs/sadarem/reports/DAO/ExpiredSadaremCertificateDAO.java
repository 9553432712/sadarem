package com.tcs.sadarem.reports.DAO;
import java.util.ArrayList;
public interface ExpiredSadaremCertificateDAO {

	ArrayList getabstractsadaremexpireddata(String fromdate, String todate,
			String district, String mandal,String areatype,String Trigp);

	String getHeading(String fromdate, String todate, String distname,
			String mandalname,  String district,
			String mandal, String areatype, String tRIGPSel);

}
