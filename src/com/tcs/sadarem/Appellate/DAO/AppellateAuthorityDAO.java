package com.tcs.sadarem.Appellate.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.DoctorsInformationDTO;

public interface AppellateAuthorityDAO 
{
	public ArrayList getReassessmentDataList(String status);
	public int checkSADAREMIDFORAPPELLATE(String sadaremId);
	public int checkOtpForSadarem(String sadaremId,String reqId,String contactNo,String otp);
	public void updatePersonStatus(String personStatus, String sadaremId,String CategoruId, String Categorucount,int campId,String  loginid,Connection con);
	public void checkPersonStatusForAppealAuthority(String sadaremId, String personStatus ,Connection con);
	public int insertDisabilityDetailsAppleteDirectReject(String personcode, DoctorsInformationDTO DoctorsInfdto, String personStatus,String loginId,int disbID, String sysIP, int campId) throws SADAREMDBException, SQLException;
	public int updateHoldStatusofAppleteAuthority(String reqId,String remarks,String loginId,String ipaddress);
	public ArrayList getAppellateStatusTrackerReport();
}
