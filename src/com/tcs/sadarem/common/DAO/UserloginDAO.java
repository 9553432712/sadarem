package com.tcs.sadarem.common.DAO;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

public interface UserloginDAO 
{
	 public ArrayList getUserStatusList();
	 public ArrayList getUserRolesList();
	 public ArrayList getDistrictListbyRoleId(String roleId);
	 public ArrayList getCampListbyRoleIdDistId(String roleId,String distId);
	 public ArrayList getMandalListbyRoleIdDistId(String roleId,String distId);
	 public ArrayList getUserLoginDetailsforSearch(String statusFlag,String roleId,String distId,String campId,String mandalId,String username,boolean likeUserFlag);
	 public void updateUserLoginDetails(HttpServletRequest request,String username,HashMap userDataList,String author);
	 public ArrayList getLoginUserDetailsByRoleDistCampId(String role_id,String district_id,String camp_id,String mandal_id,String search_user_id,String search_email_id,String search_contact_no);
	 public void lognewuserlogin(HttpServletRequest request,HashMap formData,String author);
}
