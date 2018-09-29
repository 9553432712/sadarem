package com.tcs.sadare.issuetracksystem.DAO;

import java.util.ArrayList;
import java.util.HashMap;

public interface CommonIssueTrackingDAO 
{
  
	public ArrayList getIssueType(int roleId); 
	public ArrayList getEducationList();
	public String checkSADAREMLiveStatus(String sadaremid);
	public String checkSADAREMProofStatus(String sadaremid);
	public ArrayList getSadaremCommonData(String sadaremid);
	public ArrayList getRelationType(String gendername);
	public String generateIssueReqId(String issueTypeId);
	public   String getTktDecisionFlag(int roleID,String requestType,String requestId,String tkt_type_id);

	public  String getFlowFlag(int roleID,String requestType,String requestId,String tkt_type_id);
	//public int  insertBasicDtls(HashMap basicDtls); 
	//public String nameChangeInsertDtls(HashMap basicDtls);
	//public String relChangeInsertDtls(HashMap relnameChangModifyDtls);
	public String insertNameChangeDtls(HashMap basicDtls);
	public String insertRelationNameChangeDtls(HashMap basicDtls);
	public String insertAddressChangeDtls(HashMap basicDtls);
	public String getIsFinalFlag(int roleID,String requestType,String requestId,String tkt_type_id);
	public String getFlagDesc(String flagId);
	public String getRaisedIssuesCount(String sadaremId,String issueType);
	public String checkSADAREMStatus(String sadaremid);
	public ArrayList getmyReqList(String districtId, String mandalId,String tkt_type_id, int roleId, String reqType, String username);
	public String insertCauseOfDisabilityChangeDtls(HashMap causeofdisabilityChangModifyDtls);
	public String insertaadharChangeDtls(HashMap basicDtls);
	public String insertPhotoChangeDtls(HashMap basicDtls);
	public String insertFullNameChangeDtls(HashMap fullnameChangModifyDtls); 
	public String insertManualPhotoChangeDtls(HashMap basicDtls);
	public String insertPersonStatusChangeDtls(HashMap personStatusModifyDtls);
	public Object checkSADAREMPersonLiveStatus(String sadaremId);
	public String insertGenderChangeDtls(HashMap basicDtls); 
	public String insertAgeChangeDtls(HashMap basicDtls);
	public ArrayList searchForSadaremDetails(String sadaremID);
	public ArrayList getproofdoucments();
	public String generateopenIssueReqId(String issueTypeId);
	public String insertOpenNameChangeDtls(HashMap basicDtls);
	public String insertopenAgeChangeDtls(HashMap basicDtls);
	public String checkOTPforSadaremID(String sadaremId,String otp);
	public String insertOpenGenderChangeDtls(HashMap basicDtls);
	public String insertOpenAddressChangeDtls(HashMap basicDtls);
	public String insertOpenRelNameChangeDtls(HashMap basicDtls);
	public String insertOpenEduNameChangeDtls(HashMap educationChangModifyDtls);
	public String insertAppealChangeDtls(HashMap basicDtls);
	public String checkAppelleteFormStatus(String sadaremId);
	public String insertPartBReEntryStatusChangeDtls(
			HashMap personStatusModifyDtls);
	public String checkIsAssessedStatus(String sadaremId);
	public Object checkWhetherRaisedInPartBReentry(String sadaremId);
	public int getReassessmentStatus(String sadaremId,String catId);
	public String getEducationTypeName(String edid);
	public Object checkWhetherRaisedInMulti(String sadaremId);
	public Object checkIsRaisedStatus(String sadaremId);
	public ArrayList getSadaremCommonDataForSadaremDeletion(String sadaremid);
	public String insertSadaremIDDeletionDtls(HashMap addressChangModifyDtls);
	public ArrayList getSadaremCommonDatawithColumnName(String sadaremid);
	public String getIssueName(String issuetype);
	public String insertDistrictAddressChangeDtls(HashMap addressChangModifyDtls);
	public ArrayList checkProofStatusForMultiAdharMapping(String sadaremid);
}
