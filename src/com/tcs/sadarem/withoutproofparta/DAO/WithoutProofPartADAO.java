package com.tcs.sadarem.withoutproofparta.DAO;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

public interface WithoutProofPartADAO 
{

	public ArrayList getPartARequestDetailsListByCampId(String camp_id,String request_type,String start_date,String end_date);
	public ArrayList getPartARequesStatustDetailsListByLoginId(String userId,String request_id,String proof_id,String start_date,String end_date);
	public ArrayList getWithoutProofPartaList(String reqId, String aadharId);
	public ArrayList getPartAProofDetailsList(String request_id);
	public HashMap getRequestDetailsByAadhaarIdorRequestId(String aadhaarId,String request_id);
	public boolean logPartADtlsWithAadhaar(HttpServletRequest request,HashMap formDataList,String district_id,String aadhaar_id,String author);
	public boolean logPartARequestFormWithOutProof(HttpServletRequest request,HashMap formDataList,String district_id,String aadhaar_id,String author);
	public void logPartARequestUpdate(HttpServletRequest request,String request_id,String district_id,String camp_id,String author);
	public void logPartARejectRequest(HttpServletRequest request,String request_id,String status_remarks,String termFlag,String author);
	public boolean logPartARequestApproval(HttpServletRequest request,String request_id,String district_id,String mandal_id,String village_id,String status_remarks,String termFlag,String profile_pic_path,String author);
	public ArrayList getPartARequestStatusAtGlance(String start_date,String end_date);
	public boolean getCheckStatusofRequestExistByFNameSNameDOB(String first_name,String sur_name,String date_of_birth);
	 ;
	
	
	public int updatePartADtls(HashMap partAInsertInputs); 
	public int partAWithoutProofCAReject(String aadharCardNo,String request_id,String requestflag, String rejectedremarks,String login_id);
	public int aproofPartAWithoutProofCA(String new_sadarem_id,String request_id,  String aadharCardNo, String loginid, String districtName);
	public String getRelationType(String genderID, String relationID);
	public HashMap getPartAFormDetailsByRequestId(String request_id);

}
