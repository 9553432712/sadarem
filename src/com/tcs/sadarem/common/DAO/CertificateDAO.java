package com.tcs.sadarem.common.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.sql.DataSource;

import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.form.TerritoryForm;

public interface CertificateDAO 
{
	public  ArrayList getOnlyPartADetails(String personcode);
	public String getReasonForPersonStatus(String personcode);
	public ArrayList getCampDistIds(String personcode);
	public boolean checkForAppellateHasRaised(String personCode);
	public ArrayList getCatIdOfPerson(String personCode);
	public String getAppellateAuthorityTempStatus(String personcode);
	public String getAppellateAuthorityViewEditStatus(String personcode);
	public HashMap getSADAREMDetailsByID(String sadarem_id);
	public ArrayList getDetailsForCertificate(DataSource ds, TerritoryForm territoryForm, String personcode) throws SADAREMDBException, SQLException;
	public ArrayList getRejectedData( String personcode)throws SADAREMDBException, SQLException ;
	public ArrayList getLocomotorData(String personcode)throws SADAREMDBException, SQLException;
	public ArrayList getVisualData(String personcode)throws SADAREMDBException, SQLException;
	public ArrayList getHearingData(String personcode)throws SADAREMDBException, SQLException;
	public ArrayList getMetalillnessData(String personcode)throws SADAREMDBException, SQLException;
	public ArrayList getMentalRetardationData(String personcode)throws SADAREMDBException, SQLException;
	public ArrayList getRejectedLocomotorData(String personcode)throws SADAREMDBException, SQLException;
	public ArrayList getRejectedVisualData(String personcode)throws SADAREMDBException, SQLException;
	public ArrayList getRejectedHearingData(String personcode)throws SADAREMDBException, SQLException;
	public ArrayList getRejectedMetalillnessData(String personcode)throws SADAREMDBException, SQLException;
	public ArrayList getRejectedMentalRetardationData(String personcode)throws SADAREMDBException, SQLException;
	public ArrayList getLocomotorSubDetails(String personcode)throws SADAREMDBException, SQLException;
	public ArrayList getVisualSubDetails(String personcode)throws SADAREMDBException, SQLException;
	public ArrayList getHearingSubDetails(String personcode)throws SADAREMDBException, SQLException;
	public ArrayList getMRSubDetails(String personcode)throws SADAREMDBException, SQLException;
	public ArrayList getMISubDetails(  String personcode) throws SADAREMDBException, SQLException;
	public ArrayList getMultipleSubDetails(String personcode)throws SADAREMDBException, SQLException;
	public ArrayList getMultipleData(String personcode)throws SADAREMDBException, SQLException;
}
