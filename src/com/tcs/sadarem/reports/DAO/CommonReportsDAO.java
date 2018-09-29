package com.tcs.sadarem.reports.DAO;

import java.util.ArrayList;

public interface CommonReportsDAO {
	  
	public ArrayList getDistrictList();
	public ArrayList getCampListByDistrictID(String district_id);
	public ArrayList getDisabilityList();
	public ArrayList getMandalListbyDistrictId(String distID);
	public ArrayList getDistrictIDsList();
	public ArrayList getVillageListByDistrictIDMandalID(String distID,String mandID);
	public String getCampName(int campID);
	public String getDistrictNameBySadaremID(String sadaremID);
	public String getDistrictName(String distID);
	public String getMandalName(String distID, String Mandalid);
	public String getVillageName(String distID, String Mandalid,String villageID);
	public ArrayList getHabitationListByDistrictIDMandalIDvillageID(String distID, String mandID, String villageID);
	public String getCampName(String campId);
	public ArrayList getDistrictListbyAreatype(String areatype);
	public ArrayList getMandalListbyDistrictIdAreatype(String distID,
			String areatype);
	public ArrayList getVillageListByDistrictIDMandalIDAreaWise(String distID,
			String mandID, String areatype);
	public ArrayList getEducationList();
	public String getEducationName(String education);
	public ArrayList getCasteList();
	public ArrayList getEmploymentList();
	public String getEmploymentName(String employment);
	public ArrayList getNewDistrictList();
	public String getNewDistrictName(String district1);
	public ArrayList PartAWithoutProofPDReportList(String loginId);

}
