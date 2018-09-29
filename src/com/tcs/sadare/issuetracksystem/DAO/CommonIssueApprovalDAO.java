
package com.tcs.sadare.issuetracksystem.DAO;

import java.util.ArrayList;
import java.util.HashMap;

public interface CommonIssueApprovalDAO 
{
  
	public ArrayList getIssueType();
	public ArrayList getApprovalIssueType(int roleId);
	public ArrayList getPendingIssueDtlsList(String districtId,String mandalId,String tkt_type_id,int roleID,String issueReqStatus);

	public String updateApprovedStatusFlag(HashMap IssueApprovalDtls);
	public ArrayList getModifieddetails(String string);
	public String getFilePath(String reqId);
	public ArrayList getIssueStatusHistory(String reqId);
	public ArrayList getRelationIssueData(String reqid);
	public ArrayList getNameIssueData(String reqid);
	public ArrayList getAddressIssueData(String reqid);
	public String updateRejectedStatusFlag(HashMap IssueApprovalDtls);
	public ArrayList getCauseOfDisabilityIssueData(String reqid);
	public ArrayList getAadharIssueData(String reqId);
	public ArrayList getPhotoChangeIssueData(String reqId);
	public String getPhotoPath(String reqId);
	public ArrayList getFullNameIssueData(String reqid); 
	public ArrayList getManualPhotoChangeIssueData(String reqId);
	public ArrayList getPersonStatusIssueData(String reqid);  
	public ArrayList getGenderIssueData(String reqId);
	public ArrayList getAgeIssueData(String reqId);
	public ArrayList getOpenIssueApprovalIssueType(int roleId);
	public ArrayList getBasicdetailsOpenissue(String reqId,String sadaremId);
	public ArrayList getApprRejIssueDtlsList(String districtId,String mandalId,String tkt_type_id,int roleID,String issueReqStatus, String fromdate, String todate);
	public ArrayList getAppellateIssueData(String reqId);
	public ArrayList getQualificationIssueData(String reqid);
	public ArrayList getPartBreEntryIssueData(String reqid);
	public ArrayList getSadaremDeleteRequestDtls(String reqId);
	public ArrayList getDistrictAddressIssueData(String reqid);
	
}