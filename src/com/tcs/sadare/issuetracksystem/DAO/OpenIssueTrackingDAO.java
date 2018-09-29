package com.tcs.sadare.issuetracksystem.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.bf.disability.Constants.CommonConstants;

import com.tcs.sadarem.common.DAO.CommonDAO;
import com.tcs.sadarem.common.DAO.CommonDAOImpl;
import com.tcs.sadarem.util.DataAccess;
import com.tcs.sgv.common.util.DBConnection;

public interface OpenIssueTrackingDAO 
{
	public ArrayList getIssueType();
	public ArrayList getApprovalIssueType(int roleId);
	public ArrayList getPendingIssueDtlsList(String districtId,String mandalId,String tkt_type_id,int roleID,String issueReqStatus);
	public ArrayList getRelationIssueData(String reqId);
	public ArrayList getNameIssueData(String reqId);
	public ArrayList getAddressIssueData(String reqId);
	public ArrayList getGenderIssueData(String reqId);
	public ArrayList getAgeIssueData(String reqId);
	public String updateApprovedStatusFlag(HashMap IssueApprovalDtls);
	public String updateRejectedStatusFlag(HashMap IssueApprovalDtls);
	public ArrayList getModifieddetails(String reqId);
	public int updateFinalRelationIssueDtls(String reqId,String flag,Connection con,String teluguname);
	public int updateFinalAddressIssueDtls(String reqId,String flag,Connection con);
	public int updateFinalNameIssueDtls(String reqId,String flag,Connection con,String teluguname);
	public int updateFinalGenderIssueDtls(String reqId,String flag,Connection con);
	public int updateFinalAgeIssueDtls(String reqId,String flag,Connection con);
	public String getFilePath(String reqId);
	public ArrayList getIssueStatusHistory(String reqId);
	public ArrayList getOpenIssueApprovalIssueType(int roleId);
	public int smsInsertDtls(HashMap basicDtls,Connection lcon);
	public ArrayList getApprRejIssueDtlsList(String districtId,String mandalId,String tkt_type_id,int roleID,String issueReqStatus);
	public ArrayList getQualificationIssueData(String reqid);
	
}
