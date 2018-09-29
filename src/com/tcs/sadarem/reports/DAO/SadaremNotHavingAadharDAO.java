package com.tcs.sadarem.reports.DAO;

import java.util.ArrayList;

public interface SadaremNotHavingAadharDAO {
	public ArrayList  getData(String district,String mandal,String village,String areatype,String Trigp);

	public String sadaremnothavingheadingmsg(String distname,
			String mandalname, String villagename, String district1,
			String mandal, String village, String areatype1, String trigpsel);


	

}
