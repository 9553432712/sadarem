package com.tcs.sadarem.openreports.DAO;

import java.text.ParseException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.bf.disability.Exception.SADAREMDBException;

import com.tcs.sadarem.openreports.actions.EditPwdDetailsActionForm;

public interface UpdateCaptureDeadDetailsDAO
{

	ArrayList getPWDdata(String distid, String mandalid, String villageid,String habitationid, String sadaremid,String roleid);
	ArrayList getPwdDetaildata(String distid, String mandalid, String villageid,String habitationid, String sadaremid);
	ArrayList getSadaremPWDdata(String selSadaremId);
	
    public 	int getMappingCount(String sadaremID) throws Exception; 
    public ArrayList getVoList(String distId,String mandalId,String pancId) throws Exception;
    
    public String getSelVoId(String distId,String shgid) throws Exception;
    public ArrayList getShgList(String distId,String voId) throws Exception;
    public ArrayList getVoNameShgMappingDtls(String shgid,String distId);
    String submitConfirmedDetails(String sadaremid, String loginid,String roleid);
    public String updateRejectedpwdDetails(String sadaremid,String aadharid,String serveid1,String serveDoneBy1,String designation1,String recivedDate1,String loginId,String aadharexist);
    public String insertRejectedpwdDetails(DataSource ds,String sadaremid,String aadharid,String serveid1,String serveDoneBy1,String designation1,String recivedDate1,String loginId,String aadharexist);
	
    public ArrayList getNewRejectDetails(String selSadaremId) ;
	ArrayList getEligibleDetails(String selSadaremId);

	//String submitRejectedpwdDetails(String sadaremid,String aadharid, String loginid);

	ArrayList getRejectDetails(String selSadaremId);
	
	public String isOldOrNew(String sadaremId);
	
	public ArrayList getNewSadaremPWDdata(String selSadaremId) ;

	public int insertOldPwdDetails(DataSource ds,EditPwdDetailsActionForm form,String loginid) throws ParseException;
	

	public int updateOldPwdDetails(DataSource ds,EditPwdDetailsActionForm form,String loginid) throws ParseException;

	public ArrayList getReqSadaremDetails(String sadaremid);

	public ArrayList getRejectedSadaremDetails(String sadaremid);

	public ArrayList getBisicDetails(String selSadaremId);

	public int getAadharCount(String sadaremid,String aadharId);
	
	
	
	public ArrayList getDetailedLatestPWDdata(String distid, String mandalid,String villageid, String habitationid,String sadaremid,String roleid);
	ArrayList getpwdupdatedvalAbstReport(String fromdate, String todate,
			String distId, String mandalId, String villageId, String areatype);

}
