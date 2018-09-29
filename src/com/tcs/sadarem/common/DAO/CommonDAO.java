package com.tcs.sadarem.common.DAO;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecentric.servicemodels.AadhaarProfile;

public interface CommonDAO 
{
	public AadhaarProfile get101AadhaarProfile(String aadhaarId);
	public ArrayList getPentionTypeList();
	public ArrayList getGenderList();
	public ArrayList getRelationDetails(String GenderId);
	public ArrayList getReligionList();
	public ArrayList getContactDetails();
	public ArrayList getRationCardTypeList();
	public ArrayList getDistrictIDSList();
	public ArrayList getDistrictList();
	public ArrayList getMandalListByDistrictID(String distID);
	public ArrayList getCampListByDistrictID(String distID);
	public ArrayList getVillageListByDistrictIDMandalID(String distID,String mandID);
	public String getCampName(int campID);
	public String getRoleName(int roleID);
	public String getDistrictNameBySadaremID(String sadaremID);
	public String getDistrictName(String distID);
	public String getMandalName(String distID,String Mandalid);
	public ArrayList getMenuList(int roleID);
	public ArrayList getHabitationListByDistrictIDMandalIDvillageID(String distID,String mandID,String villageID);
	public String getNewSADAREMID(String districtID,String mandalID,String villageID);
	public String getHabCode(String districtID,String mandalID,String PanchayatId,String villageID,String habitationID);
	public ArrayList getpanchayatListByDistrictIDMandalIDVillageID(String distID,String mandID);
	public ArrayList getDisabilityCountDetails();
	public String getHabCodewithoutpanchayat(String string, String string2,String string3, String string4);
	public ArrayList searchForSadaremDetails(String sadaremID,String aadhaarID,String distID,String mandalID,String villID,String personName,String relationName);
	public String checkAadharTagging(String aadharNo,String sadaremId);
	public ArrayList sadaremBasicDetails(String sadaremID,String aadhaarID); 
	public boolean sendTKTRequestStatusbySMS(String reqID,String author);
	public String getDistrictId(String sadaremId,String AadharId);
	public Object getMandalId(String sadaremID, String aadhaarID,String sadaremDistId);
	public ArrayList getSADAREMIDByAadhaarID(String aadhaarID);
	public ArrayList searchForSadaremHistory(String sadaremID,String aadhaarID,String distID,String mandalID,String villID,String personName,String relationName);
	public String searchForStatusRemarks(String sadaremID,String aadhaarID);
	public String getHabitatNameByDistIdMandalIdPanchIdVillageIdHabitateId(String distId,String mandalId,String panchId, String villageId, String habitateId);
	public String getPanchayatNameByDistIdMandalIdPanchId(String distId,String mandalId, String panchId);
	public String getvillageNameByDistIdMandalIdVillageId(String distId,String mandalId ,String villageId);
	public ArrayList insertPersonalDetails(HashMap partAdetails);
	public ArrayList getMaritalList();
	public ArrayList getLoginUrlList(int roleId);
	public ArrayList getAllMenuUrlList();
	public String getHabitatName(String distId,String mandalId,String villageId, String habitateId);
	public ArrayList getAllRolesList();
	public void exportExcel(ArrayList HeaderList ,ArrayList resultList ,HttpServletRequest request, HttpServletResponse response, String title);
	public String getPartBStatusRemarks(String SadaremID);
	public ArrayList getVillageListByDistrictIDMandalIDPanchayatId(String distID,String mandID, String PanchayaId);
	public ArrayList getEmployeeInformation(String roleId, String distId, String campId, String mandalId);
	public HashMap getSADAREMBasicDetailsByID(String sadarem_id);
	public HashMap getSADAREMPartABasicDetailsByID(String sadarem_id);
	public String getRequestIdForPartADeowitoutProof();
	public String checkHabitation( String districtId, String mandalId, String panchayatId, String villageId, String habitationId);
	public String getPartBFormStatus(String sadarem_id);
	public ArrayList getSADAREMOrginalDetails(String sadarem_id,String aadhaar_id);
}
