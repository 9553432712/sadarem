package com.tcs.sadare.issuetracksystem.DAO;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

public interface MultipleGrievanceDAO {
	public int multipleChangeInsertDtls(HashMap addressChangModifyDtls,Connection con);

	public ArrayList RestrictingToRaiseMultiIssue(String qry);

	public ArrayList getMultiIssueData(String reqid);

	public String getMultiIssuesTracker(String reqid);

	public ArrayList getSelectedIssueIdsFromtracker(String multiIssueTracker);

	public int updateMultiIssueDtls(String reqId, String flag,
			Connection con);


}
