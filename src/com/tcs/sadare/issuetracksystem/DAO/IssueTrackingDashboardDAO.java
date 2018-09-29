package com.tcs.sadare.issuetracksystem.DAO;

import java.util.ArrayList;
import java.util.HashMap;

public interface IssueTrackingDashboardDAO
{
  public ArrayList getDashboardDetails(HashMap inputDtls);
  public ArrayList getApproverRolesList();
	
	
}
