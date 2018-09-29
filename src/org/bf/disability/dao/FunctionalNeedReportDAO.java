/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.FunctionalNeedReportDTO;

import com.tcs.sadarem.common.DAO.CommonDAO;
import com.tcs.sadarem.common.DAO.CommonDAOImpl;
import com.tcs.sadarem.util.DataAccess;
import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author 509865
 */
public class FunctionalNeedReportDAO {

    /**
     * this method fetches medical intervention information from database
     * @param ds
     * @param dailydisabilityandpercentagedto
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
	private String quryStr = null;
	
    public ArrayList getMedicalIntervention(DataSource ds, FunctionalNeedReportDTO functionalNeedReportDTO)  throws SADAREMDBException, SQLException 
    {

        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        String district_id = null;
        String mandal_id = null;
        String village_id = null;
        String habitation_id = null;
        ArrayList<FunctionalNeedReportDTO> miList = null;
        district_id = functionalNeedReportDTO.getDistrict_id();
        mandal_id = functionalNeedReportDTO.getMandal_id();
        village_id = functionalNeedReportDTO.getVillage_id();
        habitation_id = functionalNeedReportDTO.getHabitation_id();
        miList = new ArrayList<FunctionalNeedReportDTO>();
        
        int total = 0;
        try 
        {
            Date fdate = new SimpleDateFormat("dd/MM/yyyy").parse(functionalNeedReportDTO.getFromdate());
            String fromdate = new SimpleDateFormat("MM/dd/yyyy").format(fdate);

            Date tdate = new SimpleDateFormat("dd/MM/yyyy").parse(functionalNeedReportDTO.getTodate());
            String todate = new SimpleDateFormat("MM/dd/yyyy").format(tdate);

            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            if (district_id.equals("0")) 
            {

                cstmt = con.prepareCall("{Call SP_OHMI_DISTRICTWISE_SN_ANALASISREPORT(?,?)}");
                cstmt.setString(1, fromdate);
                cstmt.setString(2, todate);
                rs = cstmt.executeQuery();
                while (rs.next())
                {
                    FunctionalNeedReportDTO districtMIOHDTO =
                            new FunctionalNeedReportDTO();
                    districtMIOHDTO.setDistrict_id(rs.getString("DISTRICT_ID"));
                    districtMIOHDTO.setMandal_id("a");
                    districtMIOHDTO.setVillage_id("a");
                    districtMIOHDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport()) 
                    {
                        districtMIOHDTO.setDistrictName("<a href='./analysisReport.do?mode=mandalLevelReport&district_id=" + rs.getString("DISTRICT_ID") + 

"&typeofdisability=" + functionalNeedReportDTO.getTypeofdisability() + "&reportcategory=" + functionalNeedReportDTO.getReportcategory() + "&fromdate=" + 

functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate() + "'>" + rs.getString("DISTRICT_NAME") + "</a>");
                    } 
                    else 
                    {
                        districtMIOHDTO.setDistrictName(rs.getString("DISTRICT_NAME"));
                    }
                    districtMIOHDTO.setPhysiotherapyBelowThreeYears(rs.getInt("PBELOW3"));
                    districtMIOHDTO.setPhysiotherapyAboveThreeYears(rs.getInt("PABOVE3"));
                    districtMIOHDTO.setOccupationalTherapyBelowThreeYears(rs.getInt("OBELOW3"));
                    districtMIOHDTO.setOccupationalTherapyAboveThreeYears(rs.getInt("OABOVE3"));
                    districtMIOHDTO.setSurgeryOH(rs.getInt("SURGERY"));
                    total = rs.getInt("PBELOW3") + rs.getInt("PABOVE3") + rs.getInt("OBELOW3") + rs.getInt("OABOVE3")+ rs.getInt("SURGERY");
                    districtMIOHDTO.setTotal(total);
                    miList.add(districtMIOHDTO);

                }


            }
            else if (mandal_id.equals("0")) 
            {
                cstmt = con.prepareCall("{Call SP_OHMI_MANDALWISE_SN_ANALASISREPORT(?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, fromdate);
                cstmt.setString(3, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) 
                {
                    FunctionalNeedReportDTO mandalMIOHDTO =
                            new FunctionalNeedReportDTO();
                    mandalMIOHDTO.setDistrict_id(district_id);
                    mandalMIOHDTO.setMandal_id(rs.getString("MANDALID"));
                    mandalMIOHDTO.setVillage_id("a");
                    mandalMIOHDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport()) 
                    {
                        mandalMIOHDTO.setMandalName("<a href='./analysisReport.do?mode=villageLevelReport&district_id=" + district_id + "&mandal_id=" + rs.getString

("MANDALID") + "&typeofdisability=" + functionalNeedReportDTO.getTypeofdisability() + "&reportcategory=" + functionalNeedReportDTO.getReportcategory() + "&fromdate=" + 

functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate() + "'>" + rs.getString("MANDALNAME") + "</a>");
                    } 
                    else 
                    {
                        mandalMIOHDTO.setMandalName(rs.getString("MANDALNAME"));
                    }
                    mandalMIOHDTO.setPhysiotherapyBelowThreeYears(rs.getInt("PBELOW3"));
                    mandalMIOHDTO.setPhysiotherapyAboveThreeYears(rs.getInt("PABOVE3"));
                    mandalMIOHDTO.setOccupationalTherapyBelowThreeYears(rs.getInt("OBELOW3"));
                    mandalMIOHDTO.setOccupationalTherapyAboveThreeYears(rs.getInt("OABOVE3"));
                    mandalMIOHDTO.setSurgeryOH(rs.getInt("SURGERY"));
                    total = rs.getInt("PBELOW3") + rs.getInt("PABOVE3") + rs.getInt("OBELOW3") + rs.getInt("OABOVE3")+ rs.getInt("SURGERY");
                    mandalMIOHDTO.setTotal(total);
                    miList.add(mandalMIOHDTO);

                }
            } 
            else if (village_id.equals("0")) 
            {
                cstmt = con.prepareCall("{Call SP_OHMI_VILLAGEWISE_SN_ANALASISREPORT(?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setString(3, fromdate);
                cstmt.setString(4, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) 
                {
                    FunctionalNeedReportDTO villageMIDTO =
                            new FunctionalNeedReportDTO();
                    villageMIDTO.setDistrict_id(district_id);
                    villageMIDTO.setMandal_id(mandal_id);
                    villageMIDTO.setVillage_id(rs.getString("VILLAGEID"));
                    villageMIDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport()) 
                    {
                        villageMIDTO.setVillageName("<a href='./analysisReport.do?mode=habitationLevelReport&district_id=" + district_id + "&mandal_id=" + mandal_id + 

"&village_id=" + rs.getString("VILLAGEID") + "&typeofdisability=" + functionalNeedReportDTO.getTypeofdisability() + "&reportcategory=" + 

functionalNeedReportDTO.getReportcategory() + "&fromdate=" + functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate() + "'>" + 

rs.getString("VILLAGENAME") + "</a>");
                    } 
                    else 
                    {
                        villageMIDTO.setVillageName(rs.getString("VILLAGENAME"));
                    }
                    villageMIDTO.setPhysiotherapyBelowThreeYears(rs.getInt("PBELOW3"));
                    villageMIDTO.setPhysiotherapyAboveThreeYears(rs.getInt("PABOVE3"));
                    villageMIDTO.setOccupationalTherapyBelowThreeYears(rs.getInt("OBELOW3"));
                    villageMIDTO.setOccupationalTherapyAboveThreeYears(rs.getInt("OABOVE3"));
                    villageMIDTO.setSurgeryOH(rs.getInt("SURGERY"));
                    total = rs.getInt("PBELOW3") + rs.getInt("PABOVE3") + rs.getInt("OBELOW3") + rs.getInt("OABOVE3")+ rs.getInt("SURGERY");
                    villageMIDTO.setTotal(total);
                    miList.add(villageMIDTO);

                }
            } 
            else if (!village_id.equals("0")) 
            {
                int pBThree = 0;
                int pAThree = 0;
                int oTBThree = 0;
                int oTAThree = 0;
                int sOh = 0;
                int dtoTotal = 0;
                cstmt = con.prepareCall("{Call SP_OHMI_HABITATIONWISE_SN_ANALASISREPORT(?,?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setString(3, village_id);
                cstmt.setString(4, fromdate);
                cstmt.setString(5, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) 
                {
                    FunctionalNeedReportDTO habitationMIDTO = new FunctionalNeedReportDTO();
                    habitationMIDTO.setDistrict_id(district_id);
                    habitationMIDTO.setMandal_id(mandal_id);
                    habitationMIDTO.setVillage_id(village_id);
                    habitationMIDTO.setHabitation_id(rs.getString("HABITATIONCODE").substring(10, 12));
                    habitationMIDTO.setHabitationName(rs.getString("HABITATIONNAME"));
                    habitationMIDTO.setPhysiotherapyBelowThreeYears(rs.getInt("PBELOW3"));
                    habitationMIDTO.setPhysiotherapyAboveThreeYears(rs.getInt("PABOVE3"));
                    habitationMIDTO.setOccupationalTherapyBelowThreeYears(rs.getInt("OBELOW3"));
                    habitationMIDTO.setOccupationalTherapyAboveThreeYears(rs.getInt("OABOVE3"));
                    habitationMIDTO.setSurgeryOH(rs.getInt("SURGERY"));
                    total = rs.getInt("PBELOW3") + rs.getInt("PABOVE3") + rs.getInt("OBELOW3") + rs.getInt("OABOVE3")+ rs.getInt("SURGERY");
                    habitationMIDTO.setTotal(total);

                    if ((district_id.equals("16") && mandal_id.equals("84") && village_id.equals("004")) && (rs.getString("HABITATIONCODE").substring(10, 12).equals("00") || rs.getString("HABITATIONCODE").substring(10, 12).equals("01")))
                    {

                        if (rs.getString("HABITATIONCODE").substring(10, 12).equals("00"))
                        {

                            pBThree = habitationMIDTO.getPhysiotherapyBelowThreeYears();
                            pAThree = habitationMIDTO.getPhysiotherapyAboveThreeYears();
                            oTBThree = habitationMIDTO.getOccupationalTherapyBelowThreeYears();
                            oTAThree = habitationMIDTO.getOccupationalTherapyAboveThreeYears();
                            sOh = habitationMIDTO.getSurgeryOH();
                            dtoTotal = habitationMIDTO.getTotal();
                        }
                        if (rs.getString("HABITATIONCODE").substring(10, 12).equals("01")) 
                        {

                            habitationMIDTO.setPhysiotherapyBelowThreeYears(pBThree + habitationMIDTO.getPhysiotherapyBelowThreeYears());
                            habitationMIDTO.setPhysiotherapyAboveThreeYears(pAThree + habitationMIDTO.getPhysiotherapyAboveThreeYears());
                            habitationMIDTO.setOccupationalTherapyBelowThreeYears(oTBThree + habitationMIDTO.getOccupationalTherapyBelowThreeYears());
                            habitationMIDTO.setOccupationalTherapyAboveThreeYears(oTAThree + habitationMIDTO.getOccupationalTherapyAboveThreeYears());
                            habitationMIDTO.setSurgeryOH(sOh + habitationMIDTO.getSurgeryOH());
                            habitationMIDTO.setTotal(dtoTotal + habitationMIDTO.getTotal());
                            miList.add(habitationMIDTO);
                        }
                    } 
                    else 
                    {
                        miList.add(habitationMIDTO);
                    }

                }
            }
        } catch (SQLException sqlEx) 
        {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMedicalIntervention", "FunctionalNeedReportDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO","getMedicalIntervention");
        } 
        catch (Exception sqlEx) 
        {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMedicalIntervention", "FunctionalNeedReportDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO","getMedicalIntervention");
        } 
        finally
        {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }
        return miList;
    }

    public ArrayList getPersonalempWiseDetails(DataSource ds, String district_id, String mandal_id, String village_id, String habCode, String emp_id, String urbanId,String fromDate, String todate)throws SADAREMDBException, SQLException 
    {
        ArrayList data = new ArrayList(); 

        try
        {
            Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(fromDate);

            String fromdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);

            Date toDate = new SimpleDateFormat("dd/mm/yyyy").parse(todate);

            String toDates = new SimpleDateFormat("mm/dd/yyyy").format(toDate);
            
            ArrayList paramList = new ArrayList();
            ArrayList tempList  = new ArrayList();
            
           /* results.put("district_id", district_id);
            results.put("mandal_id", mandal_id);
            results.put("village_id", village_id);
            results.put("PERSON_CODE", rs.getString(1));
            results.put("PERSONNAME", rs.getString(2));
            results.put("Gender", rs.getString(3));
            results.put("age_years", rs.getString(4));
            results.put("relation_name", rs.getString(5));
            results.put("EDUCATION", rs.getString(6));
            results.put("CASTE", rs.getString(7));
            results.put("Address", rs.getString(8));
            results.put("phone_no", rs.getString(9));
            results.put("fromdate", fromDate);
            results.put("todate", todate);*/
            

            quryStr = "SELECT DISTINCT ? district_id,? mandal_id,? village_id,"
                    + "P.PERSON_CODE AS PERSON_CODE,P.SURNAME + SPACE(2) + P.FIRST_NAME AS PERSONNAME,\n"
                    + "'Gender' = CASE  WHEN Gender = 1 THEN 'Male' WHEN Gender = 2 THEN 'Female' ELSE 'Not Entered' END ,"
                    + "DATEDIFF(year,P.Date_of_Birth,GETDATE()) age_years,p.relation_name relation_name,'EDUCATION' =CASE WHEN P.EDUCATION = 1 THEN 'Illiterate'  \n"
                    + " WHEN P.EDUCATION = 2 THEN 'Below 10th'  WHEN P.EDUCATION = 3 THEN '10th Class'  WHEN P.EDUCATION = 4 THEN 'Intermediate'  \n"
                    + "WHEN P.EDUCATION = 5 THEN 'Diploma/I.T.I'  WHEN P.EDUCATION = 6 THEN 'Graduate'  WHEN P.EDUCATION = 7 THEN 'Postgraduate'  \n"
                    + "ELSE 'Not Entered'  END,\n"
                    + " 'CASTE' =   CASE  WHEN P.CASTE = 1 THEN 'ST'  WHEN P.CASTE = 2 THEN 'SC'  WHEN P.CASTE = 3 THEN 'BC'  WHEN P.CASTE = 4 THEN 'OC'  \n"
                    + " WHEN P.CASTE = 5 THEN 'Minority'  WHEN P.CASTE = 6 THEN 'NA'  ELSE 'Not Entered'  END, \n"
                    + " house_number+','+habitation_name+','+e.village_name+', '+d.mandal_name+','+s.district_name Address,\n"
                    + " coalesce(phone_no,'-') as phone_no,? fromDate,?  todate\n"
                    + "FROM \n"
                    + "  DBO.TBLPERSON_PERSONAL_DETAILS P  with (nolock) ,   DBO.TBLPERSON_DISABILITY_DETAILS PD with(nolock) ,   \n"
                    + "DBO.TBLPERSON_DISABILITY_TOTALVALUE_DETAILS PT with(nolock)  ,  tbldistrict_details s with(nolock) ,  tblmandal_details d with(nolock) ,\n"
                    + "  tblvillage_details e with(nolock) , tblhabitation_details h with(nolock)  WHERE   PD.PERSON_CODE      = P.PERSON_CODE  AND  \n"
                    + " PT.PERSON_CODE      = P.PERSON_CODE  AND  PD.PERSON_CODE      = PT.PERSON_CODE AND  P.STATUS= 'ACTIVE' \n"
                    + "  AND  PD.STATUS= 'ACTIVE'  AND  PT.STATUS= 'ACTIVE' AND   VIEW_EDIT_MODE = 'View' AND  p.districtid = s.district_id and  p.districtid = d.district_id and  p.mandalid = d.mandal_id and  p.districtid = e.district_id and  p.mandalid = e.mandal_id and  p.villageid = e.village_id and    p.habcode =  h.habitation_code   and \n"
                    + "   (DATEADD(DD,0,DATEDIFF(DD,0,PD.UPDATED_DATE))) between  ? and ?  and totaldisability > = 40 and employment=?\n ";


            tempList  = new ArrayList();
            tempList.add("S");
            tempList.add(district_id);
            paramList.add(tempList);
            
            tempList  = new ArrayList();
            tempList.add("S");
            tempList.add(mandal_id);
            paramList.add(tempList);

            tempList  = new ArrayList();
            tempList.add("S");
            tempList.add(village_id);
            paramList.add(tempList);
            
            tempList  = new ArrayList();
            tempList.add("S");
            tempList.add(fromdate);
            paramList.add(tempList);
            
            tempList  = new ArrayList();
            tempList.add("S");
            tempList.add(toDates);
            paramList.add(tempList);            
            
            
            tempList  = new ArrayList();
            tempList.add("S");
            tempList.add(fromdate);
            paramList.add(tempList);
            
            tempList  = new ArrayList();
            tempList.add("S");
            tempList.add(toDates);
            paramList.add(tempList);
            
            tempList  = new ArrayList();
            tempList.add("S");
            tempList.add(emp_id);
            paramList.add(tempList);
            
        
            if (!district_id.equals("0") && mandal_id.equals("0") && village_id.equals("0")) 
            {

            	quryStr+=" AND p.district_id=?\n ";
                
                tempList  = new ArrayList();
                tempList.add("S");
                tempList.add(district_id);
                paramList.add(tempList);
            	
            } 
            else if (!district_id.equals("0") && !mandal_id.equals("0") && village_id.equals("0"))
            {
            	quryStr+="  AND p.district_id=? AND p.mandal_id=?" ;
                
                tempList  = new ArrayList();
                tempList.add("S");
                tempList.add(district_id);
                paramList.add(tempList);
                
                tempList  = new ArrayList();
                tempList.add("S");
                tempList.add(mandal_id);
                paramList.add(tempList);


            } 
            else if (!district_id.equals("0") && !mandal_id.equals("0") && !village_id.equals("0"))
            {
            	quryStr+=" AND p.district_id=? AND p.mandal_id=? AND p.village_id=? \n" ;
            	
            	 tempList  = new ArrayList();
                 tempList.add("S");
                 tempList.add(district_id);
                 paramList.add(tempList);
                 
                 tempList  = new ArrayList();
                 tempList.add("S");
                 tempList.add(mandal_id);
                 paramList.add(tempList);
                 
                 tempList  = new ArrayList();
                 tempList.add("S");
                 tempList.add(village_id);
                 paramList.add(tempList);

                if (!habCode.equals("0") && !habCode.equals("")) 
                {
                    if (district_id.equals("16") && mandal_id.equals("84") && village_id.equals("004") && habCode.equals("01"))
                    {
                    	quryStr+=" and p.habitation_id in('00','" + habCode + "')"; 
                    	
                    	 
                    } 
                    else 
                    {
                    	quryStr+=" and p. habitation_id=?";
                        
                        tempList  = new ArrayList();
                        tempList.add("S");
                        tempList.add(habCode);
                        paramList.add(tempList);
                    }
                } 

            }

            if (urbanId.equals("Urban")) 
            {
            	quryStr+=" and p.mandal_id > 79 ";
            } 
            else if (urbanId.equals("Rural"))
            {
            	quryStr+=" and p.mandal_id < 80 ";
            }
 
            
            data = DataAccess.pickDataByPrepareStmtArrayListHashMap(quryStr, paramList);
     
            
        } 
        catch (SQLException sqlEx) 
        {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalempWiseDetails", "FunctionalNeedReportDAO", "DataBase");
            
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO","getPersonalempWiseDetails");
        } 
        catch (Exception sqlEx) 
        {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalempWiseDetails", "FunctionalNeedReportDAO", "Code");
          
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO","getPersonalempWiseDetails");
        }  
        return data;
    }

    public ArrayList getAssistiveDevicesProthosis(DataSource ds, FunctionalNeedReportDTO functionalNeedReportDTO)  throws SADAREMDBException, SQLException 
    {

        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        String district_id = null;
        String mandal_id = null;
        String village_id = null;
        String habitation_id = null;
        ArrayList<FunctionalNeedReportDTO> prothesisList = null;
        
        district_id 	= functionalNeedReportDTO.getDistrict_id();
        mandal_id 		= functionalNeedReportDTO.getMandal_id();
        village_id 		= functionalNeedReportDTO.getVillage_id();
        habitation_id 	= functionalNeedReportDTO.getHabitation_id();
        
        prothesisList = new ArrayList<FunctionalNeedReportDTO>();
        
        int total = 0;
        try {
            Date fdate = new SimpleDateFormat("dd/MM/yyyy").parse(functionalNeedReportDTO.getFromdate());
            String fromdate = new SimpleDateFormat("MM/dd/yyyy").format(fdate);

            Date tdate = new SimpleDateFormat("dd/MM/yyyy").parse(functionalNeedReportDTO.getTodate());
            String todate = new SimpleDateFormat("MM/dd/yyyy").format(tdate);
            con = DBConnection.getConnection();
            if (district_id.equals("0")) {

                cstmt = con.prepareCall("{Call SP_OHAD_PROSTHESIS_DISTRICTWISE_SN_ANALASISREPORT(?,?)}");
                cstmt.setString(1, fromdate);
                cstmt.setString(2, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO districtProthesisDTO =
                            new FunctionalNeedReportDTO();

                    districtProthesisDTO.setDistrict_id(rs.getString("DISTRICT_ID"));
                    districtProthesisDTO.setMandal_id("a");
                    districtProthesisDTO.setVillage_id("a");
                    districtProthesisDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport()) {
                        districtProthesisDTO.setDistrictName("<a href='./analysisReport.do?mode=mandalLevelReport&district_id=" + rs.getString("DISTRICT_ID") + 

"&typeofdisability=" + functionalNeedReportDTO.getTypeofdisability() + "&reportcategory=" + functionalNeedReportDTO.getReportcategory() + "&fromdate=" + 

functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate() + "&reportSubcategory=" + functionalNeedReportDTO.getReportSubcategory() + 

"'>" + rs.getString("DISTRICT_NAME") + "</a>");
                    } else {
                        districtProthesisDTO.setDistrictName(rs.getString("DISTRICT_NAME"));
                    }
                    districtProthesisDTO.setShoulder(rs.getInt("SDP"));
                    districtProthesisDTO.setAboveElbow(rs.getInt("AEP"));
                    districtProthesisDTO.setElbowDisarticulation(rs.getInt("EDP"));
                    districtProthesisDTO.setBelowElbow(rs.getInt("BEP"));
                    districtProthesisDTO.setWristDisarticulation(rs.getInt("WDP"));
                    districtProthesisDTO.setHand(rs.getInt("HP"));
                    districtProthesisDTO.setCosmeticFinger(rs.getInt("CFP"));
                    districtProthesisDTO.setHipDisarticulation(rs.getInt("HDP"));
                    districtProthesisDTO.setAboveKnee(rs.getInt("AKP"));
                    districtProthesisDTO.setKneeDisarticulation(rs.getInt("KDP"));
                    districtProthesisDTO.setBelowKnee(rs.getInt("BKP"));
                    districtProthesisDTO.setSymes(rs.getInt("SYMES"));
                    districtProthesisDTO.setPartialFoot(rs.getInt("PFP"));
                    total = rs.getInt("SDP") + rs.getInt("AEP") + rs.getInt("EDP") + rs.getInt("BEP") + rs.getInt("WDP")
                            + rs.getInt("HP") + rs.getInt("CFP") + rs.getInt("HDP") + rs.getInt("AKP") + rs.getInt("KDP")
                            + rs.getInt("BKP") + rs.getInt("SYMES") + rs.getInt("PFP");
                    districtProthesisDTO.setTotal(total);
                    prothesisList.add(districtProthesisDTO);

                }


            } else if (mandal_id.equals("0")) {
                cstmt = con.prepareCall("{Call SP_OHAD_PROSTHESIS_MANDALWISE_SN_ANALASISREPORT(?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, fromdate);
                cstmt.setString(3, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO mandalProthesisDTO =
                            new FunctionalNeedReportDTO();

                    mandalProthesisDTO.setDistrict_id(district_id);
                    mandalProthesisDTO.setMandal_id(rs.getString("MANDAL_ID"));
                    mandalProthesisDTO.setVillage_id("a");
                    mandalProthesisDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport()) {
                        mandalProthesisDTO.setMandalName("<a href='./analysisReport.do?mode=villageLevelReport&district_id=" + district_id + "&mandal_id=" + 

rs.getString("MANDAL_ID") + "&typeofdisability=" + functionalNeedReportDTO.getTypeofdisability() + "&reportcategory=" + functionalNeedReportDTO.getReportcategory() + 

"&fromdate=" + functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate() + "&reportSubcategory=" + 

functionalNeedReportDTO.getReportSubcategory() + "'>" + rs.getString("MANDAL_NAME") + "</a>");
                    } else {
                        mandalProthesisDTO.setMandalName(rs.getString("MANDAL_NAME"));
                    }
                    mandalProthesisDTO.setShoulder(rs.getInt("SDP"));
                    mandalProthesisDTO.setAboveElbow(rs.getInt("AEP"));
                    mandalProthesisDTO.setElbowDisarticulation(rs.getInt("EDP"));
                    mandalProthesisDTO.setBelowElbow(rs.getInt("BEP"));
                    mandalProthesisDTO.setWristDisarticulation(rs.getInt("WDP"));
                    mandalProthesisDTO.setHand(rs.getInt("HP"));
                    mandalProthesisDTO.setCosmeticFinger(rs.getInt("CFP"));
                    mandalProthesisDTO.setHipDisarticulation(rs.getInt("HDP"));
                    mandalProthesisDTO.setAboveKnee(rs.getInt("AKP"));
                    mandalProthesisDTO.setKneeDisarticulation(rs.getInt("KDP"));
                    mandalProthesisDTO.setBelowKnee(rs.getInt("BKP"));
                    mandalProthesisDTO.setSymes(rs.getInt("SYMES"));
                    mandalProthesisDTO.setPartialFoot(rs.getInt("PFP"));
                    total = rs.getInt("SDP") + rs.getInt("AEP") + rs.getInt("EDP") + rs.getInt("BEP") + rs.getInt("WDP")
                            + rs.getInt("HP") + rs.getInt("CFP") + rs.getInt("HDP") + rs.getInt("AKP") + rs.getInt("KDP")
                            + rs.getInt("BKP") + rs.getInt("SYMES") + rs.getInt("PFP");
                    mandalProthesisDTO.setTotal(total);
                    prothesisList.add(mandalProthesisDTO);

                }
            } else if (village_id.equals("0")) {
                cstmt = con.prepareCall("{Call SP_OHAD_PROSTHESIS_VILLAGEWISE_SN_ANALASISREPORT(?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setString(3, fromdate);
                cstmt.setString(4, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO villageProthesisDTO =
                            new FunctionalNeedReportDTO();

                    villageProthesisDTO.setDistrict_id(district_id);
                    villageProthesisDTO.setMandal_id(mandal_id);
                    villageProthesisDTO.setVillage_id(rs.getString("VILLAGEID"));
                    villageProthesisDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport()) {
                        villageProthesisDTO.setVillageName("<a href='./analysisReport.do?mode=habitationLevelReport&district_id=" + district_id + "&mandal_id=" + 

mandal_id + "&village_id=" + rs.getString("VILLAGEID") + "&typeofdisability=" + functionalNeedReportDTO.getTypeofdisability() + "&reportcategory=" + 

functionalNeedReportDTO.getReportcategory() + "&fromdate=" + functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate() + 

"&reportSubcategory=" + functionalNeedReportDTO.getReportSubcategory() + "'>" + rs.getString("VILLAGENAME") + "</a>");
                    } else {
                        villageProthesisDTO.setVillageName(rs.getString("VILLAGENAME"));
                    }
                    villageProthesisDTO.setShoulder(rs.getInt("SDP"));
                    villageProthesisDTO.setAboveElbow(rs.getInt("AEP"));
                    villageProthesisDTO.setElbowDisarticulation(rs.getInt("EDP"));
                    villageProthesisDTO.setBelowElbow(rs.getInt("BEP"));
                    villageProthesisDTO.setWristDisarticulation(rs.getInt("WDP"));
                    villageProthesisDTO.setHand(rs.getInt("HP"));
                    villageProthesisDTO.setCosmeticFinger(rs.getInt("CFP"));
                    villageProthesisDTO.setHipDisarticulation(rs.getInt("HDP"));
                    villageProthesisDTO.setAboveKnee(rs.getInt("AKP"));
                    villageProthesisDTO.setKneeDisarticulation(rs.getInt("KDP"));
                    villageProthesisDTO.setBelowKnee(rs.getInt("BKP"));
                    villageProthesisDTO.setSymes(rs.getInt("SYMES"));
                    villageProthesisDTO.setPartialFoot(rs.getInt("PFP"));
                    total = rs.getInt("SDP") + rs.getInt("AEP") + rs.getInt("EDP") + rs.getInt("BEP") + rs.getInt("WDP")
                            + rs.getInt("HP") + rs.getInt("CFP") + rs.getInt("HDP") + rs.getInt("AKP") + rs.getInt("KDP")
                            + rs.getInt("BKP") + rs.getInt("SYMES") + rs.getInt("PFP");
                    villageProthesisDTO.setTotal(total);
                    prothesisList.add(villageProthesisDTO);

                }
            } else if (!village_id.equals("0")) {
                int sholder = 0;
                int aboveElbow = 0;
                int elbowDisarticulation = 0;
                int belowElbow = 0;
                int wristDisarticulation = 0;
                int hand = 0;
                int cosmeticFinger = 0;
                int hipDisarticulation = 0;
                int aboveKnee = 0;
                int kneeDisarticulation = 0;
                int belowKnee = 0;
                int symes = 0;
                int partialFoot = 0;
                int dtoTotal = 0;

                cstmt = con.prepareCall("{Call SP_OHAD_PROSTHESIS_HABITATIONWISE_SN_ANALASISREPORT(?,?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setString(3, village_id);
                cstmt.setString(4, fromdate);
                cstmt.setString(5, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO habitationProthesisDTO =
                            new FunctionalNeedReportDTO();

                    habitationProthesisDTO.setDistrict_id(district_id);
                    habitationProthesisDTO.setMandal_id(mandal_id);
                    habitationProthesisDTO.setVillage_id(village_id);
                    habitationProthesisDTO.setHabitation_id(rs.getString("HABITATIONCODE").substring(10, 12));
                    habitationProthesisDTO.setHabitationName(rs.getString("HABITATIONNAME"));
                    habitationProthesisDTO.setShoulder(rs.getInt("SDP"));
                    habitationProthesisDTO.setAboveElbow(rs.getInt("AEP"));
                    habitationProthesisDTO.setElbowDisarticulation(rs.getInt("EDP"));
                    habitationProthesisDTO.setBelowElbow(rs.getInt("BEP"));
                    habitationProthesisDTO.setWristDisarticulation(rs.getInt("WDP"));
                    habitationProthesisDTO.setHand(rs.getInt("HP"));
                    habitationProthesisDTO.setCosmeticFinger(rs.getInt("CFP"));
                    habitationProthesisDTO.setHipDisarticulation(rs.getInt("HDP"));
                    habitationProthesisDTO.setAboveKnee(rs.getInt("AKP"));
                    habitationProthesisDTO.setKneeDisarticulation(rs.getInt("KDP"));
                    habitationProthesisDTO.setBelowKnee(rs.getInt("BKP"));
                    habitationProthesisDTO.setSymes(rs.getInt("SYMES"));
                    habitationProthesisDTO.setPartialFoot(rs.getInt("PFP"));
                    total = rs.getInt("SDP") + rs.getInt("AEP") + rs.getInt("EDP") + rs.getInt("BEP") + rs.getInt("WDP")
                            + rs.getInt("HP") + rs.getInt("CFP") + rs.getInt("HDP") + rs.getInt("AKP") + rs.getInt("KDP")
                            + rs.getInt("BKP") + rs.getInt("SYMES") + rs.getInt("PFP");
                    habitationProthesisDTO.setTotal(total);
                    if ((district_id.equals("16") && mandal_id.equals("84") && village_id.equals("004")) && (rs.getString("HABITATIONCODE").substring(10, 12).equals

("00") || rs.getString("HABITATIONCODE").substring(10, 12).equals("01"))) {

                        if (rs.getString("HABITATIONCODE").substring(10, 12).equals("00")) {

                            sholder = habitationProthesisDTO.getShoulder();
                            aboveElbow = habitationProthesisDTO.getAboveElbow();
                            elbowDisarticulation = habitationProthesisDTO.getElbowDisarticulation();
                            belowElbow = habitationProthesisDTO.getBelowElbow();
                            wristDisarticulation = habitationProthesisDTO.getWristDisarticulation();
                            hand = habitationProthesisDTO.getHand();
                            cosmeticFinger = habitationProthesisDTO.getCosmeticFinger();
                            hipDisarticulation = habitationProthesisDTO.getHipDisarticulation();
                            aboveKnee = habitationProthesisDTO.getAboveKnee();
                            kneeDisarticulation = habitationProthesisDTO.getKneeDisarticulation();
                            belowKnee = habitationProthesisDTO.getBelowKnee();
                            symes = habitationProthesisDTO.getSymes();
                            partialFoot = habitationProthesisDTO.getPartialFoot();
                            dtoTotal = habitationProthesisDTO.getTotal();


                        }
                        if (rs.getString("HABITATIONCODE").substring(10, 12).equals("01")) {

                            habitationProthesisDTO.setShoulder(sholder + habitationProthesisDTO.getShoulder());
                            habitationProthesisDTO.setAboveElbow(aboveElbow + habitationProthesisDTO.getAboveElbow());
                            habitationProthesisDTO.setElbowDisarticulation(elbowDisarticulation + habitationProthesisDTO.getElbowDisarticulation());
                            habitationProthesisDTO.setBelowElbow(belowElbow + habitationProthesisDTO.getBelowElbow());
                            habitationProthesisDTO.setWristDisarticulation(wristDisarticulation + habitationProthesisDTO.getWristDisarticulation());
                            habitationProthesisDTO.setHand(hand + habitationProthesisDTO.getHand());
                            habitationProthesisDTO.setCosmeticFinger(cosmeticFinger + habitationProthesisDTO.getCosmeticFinger());
                            habitationProthesisDTO.setHipDisarticulation(hipDisarticulation + habitationProthesisDTO.getHipDisarticulation());
                            habitationProthesisDTO.setAboveKnee(aboveKnee + habitationProthesisDTO.getAboveKnee());
                            habitationProthesisDTO.setKneeDisarticulation(kneeDisarticulation + habitationProthesisDTO.getKneeDisarticulation());
                            habitationProthesisDTO.setBelowKnee(belowKnee + habitationProthesisDTO.getBelowKnee());
                            habitationProthesisDTO.setSymes(symes + habitationProthesisDTO.getSymes());
                            habitationProthesisDTO.setPartialFoot(partialFoot + habitationProthesisDTO.getPartialFoot());
                            habitationProthesisDTO.setTotal(dtoTotal + habitationProthesisDTO.getTotal());

                            prothesisList.add(habitationProthesisDTO);
                        }
                    } else {

                        prothesisList.add(habitationProthesisDTO);
                    }

                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getAssistiveDevicesProthosis", "FunctionalNeedReportDAO", "DataBase");
           //con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getAssistiveDevicesProthosis");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getAssistiveDevicesProthosis", "FunctionalNeedReportDAO", "Code");
           //con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getAssistiveDevicesProthosis");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }
        return prothesisList;
    }

    public ArrayList getAssistiveDevicesOrthosis(DataSource ds, FunctionalNeedReportDTO functionalNeedReportDTO) throws SADAREMDBException, SQLException
    {

        Connection con 			= null;
        CallableStatement cstmt = null;
        ResultSet rs 			= null;
        String district_id 		= null;
        String mandal_id 		= null;
        String village_id 		= null;
        String habitation_id 	= null;
        
        ArrayList<FunctionalNeedReportDTO> prothesisList = null;
        
        district_id 	= functionalNeedReportDTO.getDistrict_id();
        mandal_id 		= functionalNeedReportDTO.getMandal_id();
        village_id 		= functionalNeedReportDTO.getVillage_id();
        habitation_id 	= functionalNeedReportDTO.getHabitation_id();
        
        prothesisList = new ArrayList<FunctionalNeedReportDTO>();
        
        int total = 0;
        try 
        {
            Date fdate = new SimpleDateFormat("dd/MM/yyyy").parse(functionalNeedReportDTO.getFromdate());
            String fromdate = new SimpleDateFormat("MM/dd/yyyy").format(fdate);

            Date tdate = new SimpleDateFormat("dd/MM/yyyy").parse(functionalNeedReportDTO.getTodate());
            String todate = new SimpleDateFormat("MM/dd/yyyy").format(tdate);
            
            con = DBConnection.getConnection();
            
            if (district_id.equals("0")) 
            {

                cstmt = con.prepareCall("{Call SP_OHAD_ORTHOSIS_DISTRICTWISE_SN_ANALASISREPORT(?,?)}");
                cstmt.setString(1, fromdate);
                cstmt.setString(2, todate);
                rs = cstmt.executeQuery();
                
                while (rs.next())
                {
                    FunctionalNeedReportDTO districtOrthosisDTO =
                            new FunctionalNeedReportDTO();

                    districtOrthosisDTO.setDistrict_id(rs.getString("DISTRICT_ID"));
                    districtOrthosisDTO.setMandal_id("a");
                    districtOrthosisDTO.setVillage_id("a");
                    districtOrthosisDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport()) {
                        districtOrthosisDTO.setDistrictName("<a href='./analysisReport.do?mode=mandalLevelReport&district_id=" + rs.getString("DISTRICT_ID") + 

"&typeofdisability=" + functionalNeedReportDTO.getTypeofdisability() + "&reportcategory=" + functionalNeedReportDTO.getReportcategory() + "&fromdate=" + 

functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate() + "&reportSubcategory=" + functionalNeedReportDTO.getReportSubcategory() + 

"'>" + rs.getString("DISTRICT_NAME") + "</a>");
                    } else {
                        districtOrthosisDTO.setDistrictName(rs.getString("DISTRICT_NAME"));
                    }
                    districtOrthosisDTO.setAeroplaneSplint(rs.getInt("APS"));
                    districtOrthosisDTO.setFigureEightSplint(rs.getInt("FES"));
                    districtOrthosisDTO.setForearmSplint(rs.getInt("FAS"));
                    districtOrthosisDTO.setHandSplint(rs.getInt("HS"));
                    districtOrthosisDTO.setHkafoh(rs.getInt("HKAFO"));
                    districtOrthosisDTO.setKafo(rs.getInt("KAFO"));
                    districtOrthosisDTO.setAfo(rs.getInt("AFO"));
                    districtOrthosisDTO.setKneeOrthosis(rs.getInt("KO"));
                    districtOrthosisDTO.setDbSplint(rs.getInt("DBS"));
                    districtOrthosisDTO.setModifiedShoe(rs.getInt("MS"));
                    districtOrthosisDTO.setSerialCastingCTEV(rs.getInt("SC"));
                    districtOrthosisDTO.setCervicalCollar(rs.getInt("CC"));
                    districtOrthosisDTO.setLsBrace(rs.getInt("LB"));
                    districtOrthosisDTO.setTlsoBraceForScoliosisKyphosis(rs.getInt("TLSOB"));

                    total = rs.getInt("APS") + rs.getInt("FES") + rs.getInt("FAS") + rs.getInt("HS") + rs.getInt("HKAFO")
                            + rs.getInt("KAFO") + rs.getInt("AFO") + rs.getInt("KO") + rs.getInt("DBS") + rs.getInt("MS")
                            + rs.getInt("SC") + rs.getInt("CC") + rs.getInt("LB") + +rs.getInt("TLSOB");
                    districtOrthosisDTO.setTotal(total);
                    prothesisList.add(districtOrthosisDTO);

                }


            } else if (mandal_id.equals("0"))
            {
                cstmt = con.prepareCall("{Call SP_OHAD_ORTHOSIS_MANDALWISE_SN_ANALASISREPORT(?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, fromdate);
                cstmt.setString(3, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO mandalOrthosisDTO =
                            new FunctionalNeedReportDTO();

                    mandalOrthosisDTO.setDistrict_id(district_id);
                    mandalOrthosisDTO.setMandal_id(rs.getString("MANDALID"));
                    mandalOrthosisDTO.setVillage_id("a");
                    mandalOrthosisDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport()) {
                        mandalOrthosisDTO.setMandalName("<a href='./analysisReport.do?mode=villageLevelReport&district_id=" + district_id + "&mandal_id=" + 

rs.getString("MANDALID") + "&typeofdisability=" + functionalNeedReportDTO.getTypeofdisability() + "&reportcategory=" + functionalNeedReportDTO.getReportcategory() + 

"&fromdate=" + functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate() + "&reportSubcategory=" + 

functionalNeedReportDTO.getReportSubcategory() + "'>" + rs.getString("MANDALNAME") + "</a>");
                    } else {
                        mandalOrthosisDTO.setMandalName(rs.getString("MANDALNAME"));
                    }
                    mandalOrthosisDTO.setAeroplaneSplint(rs.getInt("APS"));
                    mandalOrthosisDTO.setFigureEightSplint(rs.getInt("FES"));
                    mandalOrthosisDTO.setForearmSplint(rs.getInt("FAS"));
                    mandalOrthosisDTO.setHandSplint(rs.getInt("HS"));
                    mandalOrthosisDTO.setHkafoh(rs.getInt("HKAFO"));
                    mandalOrthosisDTO.setKafo(rs.getInt("KAFO"));
                    mandalOrthosisDTO.setAfo(rs.getInt("AFO"));
                    mandalOrthosisDTO.setKneeOrthosis(rs.getInt("KO"));
                    mandalOrthosisDTO.setDbSplint(rs.getInt("DBS"));
                    mandalOrthosisDTO.setModifiedShoe(rs.getInt("MS"));
                    mandalOrthosisDTO.setSerialCastingCTEV(rs.getInt("SC"));
                    mandalOrthosisDTO.setCervicalCollar(rs.getInt("CC"));
                    mandalOrthosisDTO.setLsBrace(rs.getInt("LB"));
                    mandalOrthosisDTO.setTlsoBraceForScoliosisKyphosis(rs.getInt("TLSOB"));

                    total = rs.getInt("APS") + rs.getInt("FES") + rs.getInt("FAS") + rs.getInt("HS") + rs.getInt("HKAFO")
                            + rs.getInt("KAFO") + rs.getInt("AFO") + rs.getInt("KO") + rs.getInt("DBS") + rs.getInt("MS")
                            + rs.getInt("SC") + rs.getInt("CC") + rs.getInt("LB") + +rs.getInt("TLSOB");
                    mandalOrthosisDTO.setTotal(total);
                    prothesisList.add(mandalOrthosisDTO);

                }
            } else if (village_id.equals("0")) {
                cstmt = con.prepareCall("{Call SP_OHAD_ORTHOSIS_VILLAGEWISE_SN_ANALASISREPORT(?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setString(3, fromdate);
                cstmt.setString(4, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO villageOrthosisDTO =
                            new FunctionalNeedReportDTO();

                    villageOrthosisDTO.setDistrict_id(district_id);
                    villageOrthosisDTO.setMandal_id(mandal_id);
                    villageOrthosisDTO.setVillage_id(rs.getString("VILLAGEID"));
                    villageOrthosisDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport()) {
                        villageOrthosisDTO.setVillageName("<a href='./analysisReport.do?mode=habitationLevelReport&district_id=" + district_id + "&mandal_id=" + 

mandal_id + "&village_id=" + rs.getString("VILLAGEID") + "&typeofdisability=" + functionalNeedReportDTO.getTypeofdisability() + "&reportcategory=" + 

functionalNeedReportDTO.getReportcategory() + "&fromdate=" + functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate() + 

"&reportSubcategory=" + functionalNeedReportDTO.getReportSubcategory() + "'>" + rs.getString("VILLAGENAME") + "</a>");
                    } else {
                        villageOrthosisDTO.setVillageName(rs.getString("VILLAGENAME"));
                    }
                    villageOrthosisDTO.setAeroplaneSplint(rs.getInt("APS"));
                    villageOrthosisDTO.setFigureEightSplint(rs.getInt("FES"));
                    villageOrthosisDTO.setForearmSplint(rs.getInt("FAS"));
                    villageOrthosisDTO.setHandSplint(rs.getInt("HS"));
                    villageOrthosisDTO.setHkafoh(rs.getInt("HKAFO"));
                    villageOrthosisDTO.setKafo(rs.getInt("KAFO"));
                    villageOrthosisDTO.setAfo(rs.getInt("AFO"));
                    villageOrthosisDTO.setKneeOrthosis(rs.getInt("KO"));
                    villageOrthosisDTO.setDbSplint(rs.getInt("DBS"));
                    villageOrthosisDTO.setModifiedShoe(rs.getInt("MS"));
                    villageOrthosisDTO.setSerialCastingCTEV(rs.getInt("SC"));
                    villageOrthosisDTO.setCervicalCollar(rs.getInt("CC"));
                    villageOrthosisDTO.setLsBrace(rs.getInt("LB"));
                    villageOrthosisDTO.setTlsoBraceForScoliosisKyphosis(rs.getInt("TLSOB"));

                    total = rs.getInt("APS") + rs.getInt("FES") + rs.getInt("FAS") + rs.getInt("HS") + rs.getInt("HKAFO")
                            + rs.getInt("KAFO") + rs.getInt("AFO") + rs.getInt("KO") + rs.getInt("DBS") + rs.getInt("MS")
                            + rs.getInt("SC") + rs.getInt("CC") + rs.getInt("LB") + +rs.getInt("TLSOB");
                    villageOrthosisDTO.setTotal(total);
                    prothesisList.add(villageOrthosisDTO);

                }
            } else if (!village_id.equals("0")) {
                int aeroplaneSplint = 0;
                int figureEightSplint = 0;
                int forearmSplint = 0;
                int handSplint = 0;
                int hkafoh = 0;
                int kafo = 0;
                int afo = 0;
                int kneeOrthosis = 0;
                int dbSplint = 0;
                int modifiedShoe = 0;
                int serialCastingCTEV = 0;
                int cervicalCollar = 0;
                int lsBrace = 0;
                int kyphosis = 0;
                int getTotal = 0;
                cstmt = con.prepareCall("{Call SP_OHAD_ORTHOSIS_HABITATIONWISE_SN_ANALASISREPORT(?,?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setString(3, village_id);
                cstmt.setString(4, fromdate);
                cstmt.setString(5, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO habitationProthesisDTO =
                            new FunctionalNeedReportDTO();

                    habitationProthesisDTO.setDistrict_id(district_id);
                    habitationProthesisDTO.setMandal_id(mandal_id);
                    habitationProthesisDTO.setVillage_id(village_id);
                    habitationProthesisDTO.setHabitation_id(rs.getString("HABITATIONCODE").substring(10, 12));
                    habitationProthesisDTO.setHabitationName(rs.getString("HABITATIONNAME"));
                    habitationProthesisDTO.setAeroplaneSplint(rs.getInt("APS"));
                    habitationProthesisDTO.setFigureEightSplint(rs.getInt("FES"));
                    habitationProthesisDTO.setForearmSplint(rs.getInt("FAS"));
                    habitationProthesisDTO.setHandSplint(rs.getInt("HS"));
                    habitationProthesisDTO.setHkafoh(rs.getInt("HKAFO"));
                    habitationProthesisDTO.setKafo(rs.getInt("KAFO"));
                    habitationProthesisDTO.setAfo(rs.getInt("AFO"));
                    habitationProthesisDTO.setKneeOrthosis(rs.getInt("KO"));
                    habitationProthesisDTO.setDbSplint(rs.getInt("DBS"));
                    habitationProthesisDTO.setModifiedShoe(rs.getInt("MS"));
                    habitationProthesisDTO.setSerialCastingCTEV(rs.getInt("SC"));
                    habitationProthesisDTO.setCervicalCollar(rs.getInt("CC"));
                    habitationProthesisDTO.setLsBrace(rs.getInt("LB"));
                    habitationProthesisDTO.setTlsoBraceForScoliosisKyphosis(rs.getInt("TLSOB"));

                    total = rs.getInt("APS") + rs.getInt("FES") + rs.getInt("FAS") + rs.getInt("HS") + rs.getInt("HKAFO")
                            + rs.getInt("KAFO") + rs.getInt("AFO") + rs.getInt("KO") + rs.getInt("DBS") + rs.getInt("MS")
                            + rs.getInt("SC") + rs.getInt("CC") + rs.getInt("LB") + +rs.getInt("TLSOB");
                    habitationProthesisDTO.setTotal(total);
                    if ((district_id.equals("16") && mandal_id.equals("84") && village_id.equals("004")) && (rs.getString("HABITATIONCODE").substring(10, 12).equals

("00") || rs.getString("HABITATIONCODE").substring(10, 12).equals("01"))) {

                        if (rs.getString("HABITATIONCODE").substring(10, 12).equals("00")) {

                            aeroplaneSplint = habitationProthesisDTO.getAeroplaneSplint();
                            figureEightSplint = habitationProthesisDTO.getFigureEightSplint();
                            forearmSplint = habitationProthesisDTO.getForearmSplint();
                            handSplint = habitationProthesisDTO.getHandSplint();
                            hkafoh = habitationProthesisDTO.getHkafoh();
                            kafo = habitationProthesisDTO.getKafo();
                            afo = habitationProthesisDTO.getAfo();
                            kneeOrthosis = habitationProthesisDTO.getKneeOrthosis();
                            dbSplint = habitationProthesisDTO.getDbSplint();
                            modifiedShoe = habitationProthesisDTO.getModifiedShoe();
                            serialCastingCTEV = habitationProthesisDTO.getSerialCastingCTEV();
                            cervicalCollar = habitationProthesisDTO.getCervicalCollar();
                            lsBrace = habitationProthesisDTO.getLsBrace();
                            kyphosis = habitationProthesisDTO.getTlsoBraceForScoliosisKyphosis();
                            getTotal = habitationProthesisDTO.getTotal();

                        }
                        if (rs.getString("HABITATIONCODE").substring(10, 12).equals("01")) {

                            habitationProthesisDTO.setAeroplaneSplint(aeroplaneSplint + habitationProthesisDTO.getAeroplaneSplint());
                            habitationProthesisDTO.setFigureEightSplint(figureEightSplint + habitationProthesisDTO.getFigureEightSplint());
                            habitationProthesisDTO.setForearmSplint(forearmSplint + habitationProthesisDTO.getForearmSplint());
                            habitationProthesisDTO.setHandSplint(handSplint + habitationProthesisDTO.getHandSplint());
                            habitationProthesisDTO.setHkafoh(hkafoh + habitationProthesisDTO.getHkafoh());
                            habitationProthesisDTO.setKafo(kafo + habitationProthesisDTO.getKafo());
                            habitationProthesisDTO.setAfo(afo + habitationProthesisDTO.getAfo());
                            habitationProthesisDTO.setKneeOrthosis(kneeOrthosis + habitationProthesisDTO.getKneeOrthosis());
                            habitationProthesisDTO.setDbSplint(dbSplint + habitationProthesisDTO.getDbSplint());
                            habitationProthesisDTO.setModifiedShoe(modifiedShoe + habitationProthesisDTO.getModifiedShoe());
                            habitationProthesisDTO.setSerialCastingCTEV(serialCastingCTEV + habitationProthesisDTO.getSerialCastingCTEV());
                            habitationProthesisDTO.setCervicalCollar(cervicalCollar + habitationProthesisDTO.getCervicalCollar());
                            habitationProthesisDTO.setLsBrace(lsBrace + habitationProthesisDTO.getLsBrace());
                            habitationProthesisDTO.setTlsoBraceForScoliosisKyphosis(kyphosis + habitationProthesisDTO.getTlsoBraceForScoliosisKyphosis());
                            habitationProthesisDTO.setTotal(getTotal + habitationProthesisDTO.getTotal());

                            prothesisList.add(habitationProthesisDTO);
                        }
                    } else {


                        prothesisList.add(habitationProthesisDTO);
                    }

                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getAssistiveDevicesOrthosis", "FunctionalNeedReportDAO", "DataBase");
            //DBConnection.rollbackConnection(con);
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getAssistiveDevicesOrthosis");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getAssistiveDevicesOrthosis", "FunctionalNeedReportDAO", "Code");
            //DBConnection.rollbackConnection(con);
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getAssistiveDevicesOrthosis");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }
        return prothesisList;
    }

    public ArrayList getAssistiveDevicesWalkingAids(DataSource ds,
            FunctionalNeedReportDTO functionalNeedReportDTO)
            throws SADAREMDBException, SQLException {

        Connection con 			= null;
        CallableStatement cstmt = null;
        ResultSet rs 			= null;
        
        String district_id 		= null;
        String mandal_id 		= null;
        String village_id 		= null;
        String habitation_id 	= null;
        
        ArrayList<FunctionalNeedReportDTO> prothesisList = null;
        
        district_id 	= functionalNeedReportDTO.getDistrict_id();
        mandal_id 		= functionalNeedReportDTO.getMandal_id();
        village_id 		= functionalNeedReportDTO.getVillage_id();
        habitation_id 	= functionalNeedReportDTO.getHabitation_id();
        
        prothesisList = new ArrayList<FunctionalNeedReportDTO>();
        
        int total = 0;
        try 
        {
            Date fdate = new SimpleDateFormat("dd/MM/yyyy").parse(functionalNeedReportDTO.getFromdate());
            String fromdate = new SimpleDateFormat("MM/dd/yyyy").format(fdate);

            Date tdate = new SimpleDateFormat("dd/MM/yyyy").parse(functionalNeedReportDTO.getTodate());
            String todate = new SimpleDateFormat("MM/dd/yyyy").format(tdate);
            con = DBConnection.getConnection();
            
            if (district_id.equals("0")) 
            {

                cstmt = con.prepareCall("{Call SP_OHAD_WALKINGAIDS_DISTRICTWISE_SN_ANALASISREPORT(?,?)}");
                cstmt.setString(1, fromdate);
                cstmt.setString(2, todate);
                rs = cstmt.executeQuery();
                
                while (rs.next()) 
                {
                    FunctionalNeedReportDTO districtWalkingAidsDTO = new FunctionalNeedReportDTO();

                    districtWalkingAidsDTO.setDistrict_id(rs.getString("DISTRICT_ID"));
                    districtWalkingAidsDTO.setMandal_id("a");
                    districtWalkingAidsDTO.setVillage_id("a");
                    districtWalkingAidsDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport())
                    {
                        districtWalkingAidsDTO.setDistrictName("<a href='./analysisReport.do?mode=mandalLevelReport&district_id=" + rs.getString("DISTRICT_ID") + 

"&typeofdisability=" + functionalNeedReportDTO.getTypeofdisability() + "&reportcategory=" + functionalNeedReportDTO.getReportcategory() + "&fromdate=" + 

functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate() + "&reportSubcategory=" + functionalNeedReportDTO.getReportSubcategory() + 

"'>" + rs.getString("DISTRICT_NAME") + "</a>");
                    } 
                    else 
                    {
                        districtWalkingAidsDTO.setDistrictName(rs.getString("DISTRICT_NAME"));
                    }
                    districtWalkingAidsDTO.setWalkingStickSmall(rs.getInt("WALKINGSTICSMALL"));
                    districtWalkingAidsDTO.setWalkingStickLarge(rs.getInt("WALKINGSTICLARGE"));
                    districtWalkingAidsDTO.setAxillarySmall(rs.getInt("AXILLARYSMALL"));
                    districtWalkingAidsDTO.setAxillaryMedium(rs.getInt("AXILLARYMEDIUM"));
                    districtWalkingAidsDTO.setAxillaryLarge(rs.getInt("AXILLARYLARGE"));
                    districtWalkingAidsDTO.setAxillaryExtraLarge(rs.getInt("AXILLARYEXTRA"));
                    districtWalkingAidsDTO.setElbowSmall(rs.getInt("ELBOWSMALL"));
                    districtWalkingAidsDTO.setElbowMedium(rs.getInt("ELBOWMEDIUM"));
                    districtWalkingAidsDTO.setElbowLarge(rs.getInt("ELBOWLARGE"));
                    districtWalkingAidsDTO.setElbowExtraLarge(rs.getInt("ELBOWEXTRA"));
                    districtWalkingAidsDTO.setGutterSmall(rs.getInt("GUTTERSMALL"));
                    districtWalkingAidsDTO.setGutterMedium(rs.getInt("GUTTERMEDIUM"));
                    districtWalkingAidsDTO.setGutterLarge(rs.getInt("GUTTERLARGE"));
                    districtWalkingAidsDTO.setGutterExtraLarge(rs.getInt("GUTTEREXTRA"));
                    districtWalkingAidsDTO.setTripodSmall(rs.getInt("TRIPODSMALL"));
                    districtWalkingAidsDTO.setTripodMedium(rs.getInt("TRIPODMEDIUM"));
                    districtWalkingAidsDTO.setTripodLarge(rs.getInt("TRIPODLARGE"));
                    districtWalkingAidsDTO.setTripodExtraLarge(rs.getInt("TRIPODEXTRA"));

                    total = rs.getInt("WALKINGSTICSMALL") + rs.getInt("WALKINGSTICLARGE") + rs.getInt("AXILLARYSMALL")
                            + rs.getInt("AXILLARYMEDIUM") + rs.getInt("AXILLARYLARGE")
                            + rs.getInt("AXILLARYEXTRA") + rs.getInt("ELBOWSMALL") + rs.getInt("ELBOWMEDIUM")
                            + rs.getInt("ELBOWLARGE") + rs.getInt("ELBOWEXTRA")
                            + rs.getInt("GUTTERSMALL") + rs.getInt("GUTTERMEDIUM") + rs.getInt("GUTTERLARGE")
                            + rs.getInt("GUTTEREXTRA") + rs.getInt("TRIPODSMALL")
                            + +rs.getInt("TRIPODMEDIUM") + rs.getInt("TRIPODLARGE") + +rs.getInt("TRIPODEXTRA");
                    districtWalkingAidsDTO.setTotal(total);
                    prothesisList.add(districtWalkingAidsDTO);

                }


            } 
            else if (mandal_id.equals("0"))
            {
                cstmt = con.prepareCall("{Call SP_OHAD_WALKINGAIDS_MANDALWISE_SN_ANALASISREPORT(?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, fromdate);
                cstmt.setString(3, todate);
                rs = cstmt.executeQuery();
                FunctionalNeedReportDTO mandalWalkingAidsDTO = new FunctionalNeedReportDTO();
                
                while (rs.next()) 
                {
                	mandalWalkingAidsDTO = new FunctionalNeedReportDTO();

                    mandalWalkingAidsDTO.setDistrict_id(district_id);
                    mandalWalkingAidsDTO.setMandal_id(rs.getString("MANDALID"));
                    mandalWalkingAidsDTO.setVillage_id("a");
                    mandalWalkingAidsDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport())
                    {
                        mandalWalkingAidsDTO.setMandalName("<a href='./analysisReport.do?mode=villageLevelReport&district_id=" + district_id + "&mandal_id=" + 

rs.getString("MANDALID") + "&typeofdisability=" + functionalNeedReportDTO.getTypeofdisability() + "&reportcategory=" + functionalNeedReportDTO.getReportcategory() + 

"&fromdate=" + functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate() + "&reportSubcategory=" + 

functionalNeedReportDTO.getReportSubcategory() + "'>" + rs.getString("MANDALNAME") + "</a>");
                    } 
                    else 
                    {
                        mandalWalkingAidsDTO.setMandalName(rs.getString("MANDALNAME"));
                    }
                    mandalWalkingAidsDTO.setWalkingStickSmall(rs.getInt("WALKINGSTICSMALL"));
                    mandalWalkingAidsDTO.setWalkingStickLarge(rs.getInt("WALKINGSTICLARGE"));
                    mandalWalkingAidsDTO.setAxillarySmall(rs.getInt("AXILLARYSMALL"));
                    mandalWalkingAidsDTO.setAxillaryMedium(rs.getInt("AXILLARYMEDIUM"));
                    mandalWalkingAidsDTO.setAxillaryLarge(rs.getInt("AXILLARYLARGE"));
                    mandalWalkingAidsDTO.setAxillaryExtraLarge(rs.getInt("AXILLARYEXTRA"));
                    mandalWalkingAidsDTO.setElbowSmall(rs.getInt("ELBOWSMALL"));
                    mandalWalkingAidsDTO.setElbowMedium(rs.getInt("ELBOWMEDIUM"));
                    mandalWalkingAidsDTO.setElbowLarge(rs.getInt("ELBOWLARGE"));
                    mandalWalkingAidsDTO.setElbowExtraLarge(rs.getInt("ELBOWEXTRA"));
                    mandalWalkingAidsDTO.setGutterSmall(rs.getInt("GUTTERSMALL"));
                    mandalWalkingAidsDTO.setGutterMedium(rs.getInt("GUTTERMEDIUM"));
                    mandalWalkingAidsDTO.setGutterLarge(rs.getInt("GUTTERLARGE"));
                    mandalWalkingAidsDTO.setGutterExtraLarge(rs.getInt("GUTTEREXTRA"));
                    mandalWalkingAidsDTO.setTripodSmall(rs.getInt("TRIPODSMALL"));
                    mandalWalkingAidsDTO.setTripodMedium(rs.getInt("TRIPODMEDIUM"));
                    mandalWalkingAidsDTO.setTripodLarge(rs.getInt("TRIPODLARGE"));
                    mandalWalkingAidsDTO.setTripodExtraLarge(rs.getInt("TRIPODEXTRA"));

                    total = rs.getInt("WALKINGSTICSMALL") + rs.getInt("WALKINGSTICLARGE") + rs.getInt("AXILLARYSMALL")
                            + rs.getInt("AXILLARYMEDIUM") + rs.getInt("AXILLARYLARGE")
                            + rs.getInt("AXILLARYEXTRA") + rs.getInt("ELBOWSMALL") + rs.getInt("ELBOWMEDIUM")
                            + rs.getInt("ELBOWLARGE") + rs.getInt("ELBOWEXTRA")
                            + rs.getInt("GUTTERSMALL") + rs.getInt("GUTTERMEDIUM") + rs.getInt("GUTTERLARGE")
                            + rs.getInt("GUTTEREXTRA") + rs.getInt("TRIPODSMALL")
                            + +rs.getInt("TRIPODMEDIUM") + rs.getInt("TRIPODLARGE") + +rs.getInt("TRIPODEXTRA");
                    mandalWalkingAidsDTO.setTotal(total);
                    prothesisList.add(mandalWalkingAidsDTO);

                }
            } 
            else if (village_id.equals("0")) 
            {
                cstmt = con.prepareCall("{Call SP_OHAD_WALKINGAIDS_VILLAGEWISE_SN_ANALASISREPORT(?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setString(3, fromdate);
                cstmt.setString(4, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO villageWalkingAidsDTO =
                            new FunctionalNeedReportDTO();

                    villageWalkingAidsDTO.setDistrict_id(district_id);
                    villageWalkingAidsDTO.setMandal_id(mandal_id);
                    villageWalkingAidsDTO.setVillage_id(rs.getString("VILLAGEID"));
                    villageWalkingAidsDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport()) {
                        villageWalkingAidsDTO.setVillageName("<a href='./analysisReport.do?mode=habitationLevelReport&district_id=" + district_id + "&mandal_id=" + 

mandal_id + "&village_id=" + rs.getString("VILLAGEID") + "&typeofdisability=" + functionalNeedReportDTO.getTypeofdisability() + "&reportcategory=" + 

functionalNeedReportDTO.getReportcategory() + "&fromdate=" + functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate() + 

"&reportSubcategory=" + functionalNeedReportDTO.getReportSubcategory() + "'>" + rs.getString("VILLAGENAME") + "</a>");
                    } else {
                        villageWalkingAidsDTO.setVillageName(rs.getString("VILLAGENAME"));
                    }
                    villageWalkingAidsDTO.setWalkingStickSmall(rs.getInt("WALKINGSTICSMALL"));
                    villageWalkingAidsDTO.setWalkingStickLarge(rs.getInt("WALKINGSTICLARGE"));
                    villageWalkingAidsDTO.setAxillarySmall(rs.getInt("AXILLARYSMALL"));
                    villageWalkingAidsDTO.setAxillaryMedium(rs.getInt("AXILLARYMEDIUM"));
                    villageWalkingAidsDTO.setAxillaryLarge(rs.getInt("AXILLARYLARGE"));
                    villageWalkingAidsDTO.setAxillaryExtraLarge(rs.getInt("AXILLARYEXTRA"));
                    villageWalkingAidsDTO.setElbowSmall(rs.getInt("ELBOWSMALL"));
                    villageWalkingAidsDTO.setElbowMedium(rs.getInt("ELBOWMEDIUM"));
                    villageWalkingAidsDTO.setElbowLarge(rs.getInt("ELBOWLARGE"));
                    villageWalkingAidsDTO.setElbowExtraLarge(rs.getInt("ELBOWEXTRA"));
                    villageWalkingAidsDTO.setGutterSmall(rs.getInt("GUTTERSMALL"));
                    villageWalkingAidsDTO.setGutterMedium(rs.getInt("GUTTERMEDIUM"));
                    villageWalkingAidsDTO.setGutterLarge(rs.getInt("GUTTERLARGE"));
                    villageWalkingAidsDTO.setGutterExtraLarge(rs.getInt("GUTTEREXTRA"));
                    villageWalkingAidsDTO.setTripodSmall(rs.getInt("TRIPODSMALL"));
                    villageWalkingAidsDTO.setTripodMedium(rs.getInt("TRIPODMEDIUM"));
                    villageWalkingAidsDTO.setTripodLarge(rs.getInt("TRIPODLARGE"));
                    villageWalkingAidsDTO.setTripodExtraLarge(rs.getInt("TRIPODEXTRA"));

                    total = rs.getInt("WALKINGSTICSMALL") + rs.getInt("WALKINGSTICLARGE") + rs.getInt("AXILLARYSMALL")
                            + rs.getInt("AXILLARYMEDIUM") + rs.getInt("AXILLARYLARGE")
                            + rs.getInt("AXILLARYEXTRA") + rs.getInt("ELBOWSMALL") + rs.getInt("ELBOWMEDIUM")
                            + rs.getInt("ELBOWLARGE") + rs.getInt("ELBOWEXTRA")
                            + rs.getInt("GUTTERSMALL") + rs.getInt("GUTTERMEDIUM") + rs.getInt("GUTTERLARGE")
                            + rs.getInt("GUTTEREXTRA") + rs.getInt("TRIPODSMALL")
                            + +rs.getInt("TRIPODMEDIUM") + rs.getInt("TRIPODLARGE") + +rs.getInt("TRIPODEXTRA");
                    villageWalkingAidsDTO.setTotal(total);
                    prothesisList.add(villageWalkingAidsDTO);

                }
            } else if (!village_id.equals("0")) {
                int walkingStickSmall = 0;
                int walkingStickLarge = 0;
                int axillarySmall = 0;
                int axillaryMedium = 0;
                int axillaryLarge = 0;
                int axillaryExtraLarge = 0;
                int elbowSmall = 0;
                int elbowMedium = 0;
                int elbowLarge = 0;
                int elbowExtraLarge = 0;
                int gutterSmall = 0;
                int gutterMedium = 0;
                int gutterLarge = 0;
                int gutterExtraLarge = 0;
                int tripodSmall = 0;
                int tripodMedium = 0;
                int tripodLarge = 0;
                int tripodExtraLarge = 0;
                int totl = 0;
                cstmt = con.prepareCall("{Call SP_OHAD_WALKINGAIDS_HABITATIONWISE_SN_ANALASISREPORT(?,?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setString(3, village_id);
                cstmt.setString(4, fromdate);
                cstmt.setString(5, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO habitationWalkingAidsDTO =
                            new FunctionalNeedReportDTO();

                    habitationWalkingAidsDTO.setDistrict_id(district_id);
                    habitationWalkingAidsDTO.setMandal_id(mandal_id);
                    habitationWalkingAidsDTO.setVillage_id(village_id);
                    habitationWalkingAidsDTO.setHabitation_id(rs.getString("HABITATIONCODE").substring(10, 12));
                    habitationWalkingAidsDTO.setHabitationName(rs.getString("HABITATIONNAME"));
                    habitationWalkingAidsDTO.setWalkingStickSmall(rs.getInt("WALKINGSTICSMALL"));
                    habitationWalkingAidsDTO.setWalkingStickLarge(rs.getInt("WALKINGSTICLARGE"));
                    habitationWalkingAidsDTO.setAxillarySmall(rs.getInt("AXILLARYSMALL"));
                    habitationWalkingAidsDTO.setAxillaryMedium(rs.getInt("AXILLARYMEDIUM"));
                    habitationWalkingAidsDTO.setAxillaryLarge(rs.getInt("AXILLARYLARGE"));
                    habitationWalkingAidsDTO.setAxillaryExtraLarge(rs.getInt("AXILLARYEXTRA"));
                    habitationWalkingAidsDTO.setElbowSmall(rs.getInt("ELBOWSMALL"));
                    habitationWalkingAidsDTO.setElbowMedium(rs.getInt("ELBOWMEDIUM"));
                    habitationWalkingAidsDTO.setElbowLarge(rs.getInt("ELBOWLARGE"));
                    habitationWalkingAidsDTO.setElbowExtraLarge(rs.getInt("ELBOWEXTRA"));
                    habitationWalkingAidsDTO.setGutterSmall(rs.getInt("GUTTERSMALL"));
                    habitationWalkingAidsDTO.setGutterMedium(rs.getInt("GUTTERMEDIUM"));
                    habitationWalkingAidsDTO.setGutterLarge(rs.getInt("GUTTERLARGE"));
                    habitationWalkingAidsDTO.setGutterExtraLarge(rs.getInt("GUTTEREXTRA"));
                    habitationWalkingAidsDTO.setTripodSmall(rs.getInt("TRIPODSMALL"));
                    habitationWalkingAidsDTO.setTripodMedium(rs.getInt("TRIPODMEDIUM"));
                    habitationWalkingAidsDTO.setTripodLarge(rs.getInt("TRIPODLARGE"));
                    habitationWalkingAidsDTO.setTripodExtraLarge(rs.getInt("TRIPODEXTRA"));

                    total = rs.getInt("WALKINGSTICSMALL") + rs.getInt("WALKINGSTICLARGE") + rs.getInt("AXILLARYSMALL")
                            + rs.getInt("AXILLARYMEDIUM") + rs.getInt("AXILLARYLARGE")
                            + rs.getInt("AXILLARYEXTRA") + rs.getInt("ELBOWSMALL") + rs.getInt("ELBOWMEDIUM")
                            + rs.getInt("ELBOWLARGE") + rs.getInt("ELBOWEXTRA")
                            + rs.getInt("GUTTERSMALL") + rs.getInt("GUTTERMEDIUM") + rs.getInt("GUTTERLARGE")
                            + rs.getInt("GUTTEREXTRA") + rs.getInt("TRIPODSMALL")
                            + +rs.getInt("TRIPODMEDIUM") + rs.getInt("TRIPODLARGE") + +rs.getInt("TRIPODEXTRA");
                    habitationWalkingAidsDTO.setTotal(total);
                    if ((district_id.equals("16") && mandal_id.equals("84") && village_id.equals("004")) && (rs.getString("HABITATIONCODE").substring(10, 12).equals

("00") || rs.getString("HABITATIONCODE").substring(10, 12).equals("01"))) {

                        if (rs.getString("HABITATIONCODE").substring(10, 12).equals("00")) {

                            walkingStickSmall = habitationWalkingAidsDTO.getWalkingStickSmall();
                            walkingStickLarge = habitationWalkingAidsDTO.getWalkingStickLarge();
                            axillarySmall = habitationWalkingAidsDTO.getAxillarySmall();
                            axillaryMedium = habitationWalkingAidsDTO.getAxillaryMedium();
                            axillaryLarge = habitationWalkingAidsDTO.getAxillaryLarge();
                            axillaryExtraLarge = habitationWalkingAidsDTO.getAxillaryExtraLarge();
                            elbowSmall = habitationWalkingAidsDTO.getElbowSmall();
                            elbowMedium = habitationWalkingAidsDTO.getElbowMedium();
                            elbowLarge = habitationWalkingAidsDTO.getElbowLarge();
                            elbowExtraLarge = habitationWalkingAidsDTO.getElbowExtraLarge();
                            gutterSmall = habitationWalkingAidsDTO.getGutterSmall();
                            gutterMedium = habitationWalkingAidsDTO.getGutterMedium();
                            gutterLarge = habitationWalkingAidsDTO.getGutterLarge();
                            gutterExtraLarge = habitationWalkingAidsDTO.getGutterExtraLarge();
                            tripodSmall = habitationWalkingAidsDTO.getTripodSmall();
                            tripodMedium = habitationWalkingAidsDTO.getTripodMedium();
                            tripodLarge = habitationWalkingAidsDTO.getTripodLarge();
                            tripodExtraLarge = habitationWalkingAidsDTO.getTripodExtraLarge();
                            totl = habitationWalkingAidsDTO.getTotal();

                        }
                        if (rs.getString("HABITATIONCODE").substring(10, 12).equals("01")) {

                            habitationWalkingAidsDTO.setWalkingStickSmall(walkingStickSmall + habitationWalkingAidsDTO.getWalkingStickSmall());
                            habitationWalkingAidsDTO.setWalkingStickLarge(walkingStickLarge + habitationWalkingAidsDTO.getWalkingStickLarge());
                            habitationWalkingAidsDTO.setAxillarySmall(axillarySmall + habitationWalkingAidsDTO.getAxillarySmall());
                            habitationWalkingAidsDTO.setAxillaryMedium(axillaryMedium + habitationWalkingAidsDTO.getAxillaryMedium());
                            habitationWalkingAidsDTO.setAxillaryLarge(axillaryLarge + habitationWalkingAidsDTO.getAxillaryLarge());
                            habitationWalkingAidsDTO.setAxillaryExtraLarge(axillaryExtraLarge + habitationWalkingAidsDTO.getAxillaryExtraLarge());
                            habitationWalkingAidsDTO.setElbowSmall(elbowSmall + habitationWalkingAidsDTO.getElbowSmall());
                            habitationWalkingAidsDTO.setElbowMedium(elbowMedium + habitationWalkingAidsDTO.getElbowMedium());
                            habitationWalkingAidsDTO.setElbowLarge(elbowLarge + habitationWalkingAidsDTO.getElbowLarge());
                            habitationWalkingAidsDTO.setElbowExtraLarge(elbowExtraLarge + habitationWalkingAidsDTO.getElbowExtraLarge());
                            habitationWalkingAidsDTO.setGutterSmall(gutterSmall + habitationWalkingAidsDTO.getGutterSmall());
                            habitationWalkingAidsDTO.setGutterMedium(gutterMedium + habitationWalkingAidsDTO.getGutterMedium());
                            habitationWalkingAidsDTO.setGutterLarge(gutterLarge + habitationWalkingAidsDTO.getGutterLarge());
                            habitationWalkingAidsDTO.setGutterExtraLarge(gutterExtraLarge + habitationWalkingAidsDTO.getGutterExtraLarge());
                            habitationWalkingAidsDTO.setTripodSmall(tripodSmall + habitationWalkingAidsDTO.getTripodSmall());
                            habitationWalkingAidsDTO.setTripodMedium(tripodMedium + habitationWalkingAidsDTO.getTripodMedium());
                            habitationWalkingAidsDTO.setTripodLarge(tripodLarge + habitationWalkingAidsDTO.getTripodLarge());
                            habitationWalkingAidsDTO.setTripodExtraLarge(tripodExtraLarge + habitationWalkingAidsDTO.getTripodExtraLarge());
                            habitationWalkingAidsDTO.setTotal(totl + habitationWalkingAidsDTO.getTotal());

                            prothesisList.add(habitationWalkingAidsDTO);
                        }
                    } else {

                        prothesisList.add(habitationWalkingAidsDTO);
                    }

                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getAssistiveDevicesWalkingAids", "FunctionalNeedReportDAO", "DataBase");
            //DBConnection.rollbackConnection(con);
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getAssistiveDevicesWalkingAids");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getAssistiveDevicesWalkingAids", "FunctionalNeedReportDAO", "Code");
            //DBConnection.rollbackConnection(con);
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getAssistiveDevicesWalkingAids");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }
        return prothesisList;
    }

    public ArrayList getAssistiveDevicesMobility(DataSource ds,
            FunctionalNeedReportDTO functionalNeedReportDTO)
            throws SADAREMDBException, SQLException {

        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        String district_id = null;
        String mandal_id = null;
        String village_id = null;
        String habitation_id = null;
        ArrayList<FunctionalNeedReportDTO> prothesisList = null;
        district_id = functionalNeedReportDTO.getDistrict_id();
        mandal_id = functionalNeedReportDTO.getMandal_id();
        village_id = functionalNeedReportDTO.getVillage_id();
        habitation_id = functionalNeedReportDTO.getHabitation_id();
        prothesisList = new ArrayList<FunctionalNeedReportDTO>();
        int total = 0;
        try {
            Date fdate = new SimpleDateFormat("dd/MM/yyyy").parse(functionalNeedReportDTO.getFromdate());
            String fromdate = new SimpleDateFormat("MM/dd/yyyy").format(fdate);

            Date tdate = new SimpleDateFormat("dd/MM/yyyy").parse(functionalNeedReportDTO.getTodate());
            String todate = new SimpleDateFormat("MM/dd/yyyy").format(tdate);
            con = DBConnection.getConnection();
            if (district_id.equals("0")) {

                cstmt = con.prepareCall("{Call SP_OHAD_MOBILITYAIDS_DISTRICTWISE_SN_ANALASISREPORT(?,?)}");
                cstmt.setString(1, fromdate);
                cstmt.setString(2, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO districtMobilityDTO =
                            new FunctionalNeedReportDTO();

                    districtMobilityDTO.setDistrict_id(rs.getString("DISTRICT_ID"));
                    districtMobilityDTO.setMandal_id("a");
                    districtMobilityDTO.setVillage_id("a");
                    districtMobilityDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport()) {
                        districtMobilityDTO.setDistrictName("<a href='./analysisReport.do?mode=mandalLevelReport&district_id=" + rs.getString("DISTRICT_ID") + 

"&typeofdisability=" + functionalNeedReportDTO.getTypeofdisability() + "&reportcategory=" + functionalNeedReportDTO.getReportcategory() + "&fromdate=" + 

functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate() + "&reportSubcategory=" + functionalNeedReportDTO.getReportSubcategory() + 

"'>" + rs.getString("DISTRICT_NAME") + "</a>");
                    } else {
                        districtMobilityDTO.setDistrictName(rs.getString("DISTRICT_NAME"));
                    }
                    districtMobilityDTO.setWheelChairSmall(rs.getInt("WHEELCHAIRSMALL"));
                    districtMobilityDTO.setWheelChairLarge(rs.getInt("WHEELCHAIRLARGE"));
                    districtMobilityDTO.setTricycleSmall(rs.getInt("TRICYCLESMALL"));
                    districtMobilityDTO.setTricycleLarge(rs.getInt("TRICYCLELARGE"));
                    districtMobilityDTO.setWalkingFrameWalkerSmall(rs.getInt("WALKINGFRAMESMALL"));
                    districtMobilityDTO.setWalkingFrameWalkerLarge(rs.getInt("WALKINGFRAMELARGE"));

                    total = rs.getInt("WHEELCHAIRSMALL") + rs.getInt("WHEELCHAIRLARGE") + rs.getInt("TRICYCLESMALL")
                            + rs.getInt("TRICYCLELARGE") + rs.getInt("WALKINGFRAMESMALL") + rs.getInt("WALKINGFRAMELARGE");
                    districtMobilityDTO.setTotal(total);
                    prothesisList.add(districtMobilityDTO);

                }


            } else if (mandal_id.equals("0")) {
                cstmt = con.prepareCall("{Call SP_OHAD_MOBILITYAIDS_MANDALWISE_SN_ANALASISREPORT(?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, fromdate);
                cstmt.setString(3, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO mandalMobilityDTO =
                            new FunctionalNeedReportDTO();

                    mandalMobilityDTO.setDistrict_id(district_id);
                    mandalMobilityDTO.setMandal_id(rs.getString("MANDALID"));
                    mandalMobilityDTO.setVillage_id("a");
                    mandalMobilityDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport()) {
                        mandalMobilityDTO.setMandalName("<a href='./analysisReport.do?mode=villageLevelReport&district_id=" + district_id + "&mandal_id=" + 

rs.getString("MANDALID") + "&typeofdisability=" + functionalNeedReportDTO.getTypeofdisability() + "&reportcategory=" + functionalNeedReportDTO.getReportcategory() + 

"&fromdate=" + functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate() + "&reportSubcategory=" + 

functionalNeedReportDTO.getReportSubcategory() + "'>" + rs.getString("MANDALNAME") + "</a>");
                    } else {
                        mandalMobilityDTO.setMandalName(rs.getString("MANDALNAME"));
                    }
                    mandalMobilityDTO.setWheelChairSmall(rs.getInt("WHEELCHAIRSMALL"));
                    mandalMobilityDTO.setWheelChairLarge(rs.getInt("WHEELCHAIRLARGE"));
                    mandalMobilityDTO.setTricycleSmall(rs.getInt("TRICYCLESMALL"));
                    mandalMobilityDTO.setTricycleLarge(rs.getInt("TRICYCLELARGE"));
                    mandalMobilityDTO.setWalkingFrameWalkerSmall(rs.getInt("WALKINGFRAMESMALL"));
                    mandalMobilityDTO.setWalkingFrameWalkerLarge(rs.getInt("WALKINGFRAMELARGE"));

                    total = rs.getInt("WHEELCHAIRSMALL") + rs.getInt("WHEELCHAIRLARGE") + rs.getInt("TRICYCLESMALL")
                            + rs.getInt("TRICYCLELARGE") + rs.getInt("WALKINGFRAMESMALL") + rs.getInt("WALKINGFRAMELARGE");
                    mandalMobilityDTO.setTotal(total);
                    prothesisList.add(mandalMobilityDTO);

                }
            } else if (village_id.equals("0")) {
                cstmt = con.prepareCall("{Call SP_OHAD_MOBILITYAIDS_VILLAGEWISE_SN_ANALASISREPORT(?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setString(3, fromdate);
                cstmt.setString(4, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO villageMobilityDTO =
                            new FunctionalNeedReportDTO();

                    villageMobilityDTO.setDistrict_id(district_id);
                    villageMobilityDTO.setMandal_id(mandal_id);
                    villageMobilityDTO.setVillage_id(rs.getString("VILLAGEID"));
                    villageMobilityDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport()) {
                        villageMobilityDTO.setVillageName("<a href='./analysisReport.do?mode=habitationLevelReport&district_id=" + district_id + "&mandal_id=" + 

mandal_id + "&village_id=" + rs.getString("VILLAGEID") + "&typeofdisability=" + functionalNeedReportDTO.getTypeofdisability() + "&reportcategory=" + 

functionalNeedReportDTO.getReportcategory() + "&fromdate=" + functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate() + 

"&reportSubcategory=" + functionalNeedReportDTO.getReportSubcategory() + "'>" + rs.getString("VILLAGENAME") + "</a>");
                    } else {
                        villageMobilityDTO.setVillageName(rs.getString("VILLAGENAME"));
                    }
                    villageMobilityDTO.setWheelChairSmall(rs.getInt("WHEELCHAIRSMALL"));
                    villageMobilityDTO.setWheelChairLarge(rs.getInt("WHEELCHAIRLARGE"));
                    villageMobilityDTO.setTricycleSmall(rs.getInt("TRICYCLESMALL"));
                    villageMobilityDTO.setTricycleLarge(rs.getInt("TRICYCLELARGE"));
                    villageMobilityDTO.setWalkingFrameWalkerSmall(rs.getInt("WALKINGFRAMESMALL"));
                    villageMobilityDTO.setWalkingFrameWalkerLarge(rs.getInt("WALKINGFRAMELARGE"));

                    total = rs.getInt("WHEELCHAIRSMALL") + rs.getInt("WHEELCHAIRLARGE") + rs.getInt("TRICYCLESMALL")
                            + rs.getInt("TRICYCLELARGE") + rs.getInt("WALKINGFRAMESMALL") + rs.getInt("WALKINGFRAMELARGE");
                    villageMobilityDTO.setTotal(total);
                    prothesisList.add(villageMobilityDTO);

                }
            } else if (!village_id.equals("0")) {
                int wcSmall = 0;
                int wcLarge = 0;
                int tcSmall = 0;
                int tcLarge = 0;
                int walkerSmall = 0;
                int walkerLarge = 0;
                int wTotal = 0;
                cstmt = con.prepareCall("{Call SP_OHAD_MOBILITYAIDS_HABITATIONWISE_SN_ANALASISREPORT(?,?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setString(3, village_id);
                cstmt.setString(4, fromdate);
                cstmt.setString(5, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO habitationMobilityDTO =
                            new FunctionalNeedReportDTO();

                    habitationMobilityDTO.setDistrict_id(district_id);
                    habitationMobilityDTO.setMandal_id(mandal_id);
                    habitationMobilityDTO.setVillage_id(village_id);
                    habitationMobilityDTO.setHabitation_id(rs.getString("HABITATIONCODE").substring(10, 12));
                    habitationMobilityDTO.setHabitationName(rs.getString("HABITATIONNAME"));
                    habitationMobilityDTO.setWheelChairSmall(rs.getInt("WHEELCHAIRSMALL"));
                    habitationMobilityDTO.setWheelChairLarge(rs.getInt("WHEELCHAIRLARGE"));
                    habitationMobilityDTO.setTricycleSmall(rs.getInt("TRICYCLESMALL"));
                    habitationMobilityDTO.setTricycleLarge(rs.getInt("TRICYCLELARGE"));
                    habitationMobilityDTO.setWalkingFrameWalkerSmall(rs.getInt("WALKINGFRAMESMALL"));
                    habitationMobilityDTO.setWalkingFrameWalkerLarge(rs.getInt("WALKINGFRAMELARGE"));

                    total = rs.getInt("WHEELCHAIRSMALL") + rs.getInt("WHEELCHAIRLARGE") + rs.getInt("TRICYCLESMALL")
                            + rs.getInt("TRICYCLELARGE") + rs.getInt("WALKINGFRAMESMALL") + rs.getInt("WALKINGFRAMELARGE");
                    habitationMobilityDTO.setTotal(total);


                    if ((district_id.equals("16") && mandal_id.equals("84") && village_id.equals("004")) && (rs.getString("HABITATIONCODE").substring(10, 12).equals

("00") || rs.getString("HABITATIONCODE").substring(10, 12).equals("01"))) {

                        if (rs.getString("HABITATIONCODE").substring(10, 12).equals("00")) {

                            wcSmall = habitationMobilityDTO.getWheelChairSmall();
                            wcLarge = habitationMobilityDTO.getWheelChairLarge();
                            tcSmall = habitationMobilityDTO.getTricycleSmall();
                            tcLarge = habitationMobilityDTO.getTricycleLarge();
                            walkerSmall = habitationMobilityDTO.getWalkingFrameWalkerSmall();
                            walkerLarge = habitationMobilityDTO.getWalkingFrameWalkerLarge();
                            wTotal = habitationMobilityDTO.getTotal();

                        }
                        if (rs.getString("HABITATIONCODE").substring(10, 12).equals("01")) {

                            habitationMobilityDTO.setWheelChairSmall(wcSmall + habitationMobilityDTO.getWheelChairSmall());
                            habitationMobilityDTO.setWheelChairLarge(wcLarge + habitationMobilityDTO.getWheelChairLarge());
                            habitationMobilityDTO.setTricycleSmall(tcSmall + habitationMobilityDTO.getTricycleSmall());
                            habitationMobilityDTO.setTricycleLarge(tcLarge + habitationMobilityDTO.getTricycleLarge());
                            habitationMobilityDTO.setWalkingFrameWalkerSmall(walkerSmall + habitationMobilityDTO.getWalkingFrameWalkerSmall());
                            habitationMobilityDTO.setWalkingFrameWalkerLarge(walkerLarge + habitationMobilityDTO.getWalkingFrameWalkerLarge());
                            habitationMobilityDTO.setTotal(wTotal + habitationMobilityDTO.getTotal());

                            prothesisList.add(habitationMobilityDTO);
                        }
                    } else {

                        prothesisList.add(habitationMobilityDTO);
                    }

                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getAssistiveDevicesMobility", "FunctionalNeedReportDAO", "DataBase");
            //DBConnection.rollbackConnection(con);
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getAssistiveDevicesMobility");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getAssistiveDevicesMobility", "FunctionalNeedReportDAO", "Code");
            //DBConnection.rollbackConnection(con);
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getAssistiveDevicesMobility");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }
        return prothesisList;
    }

    public ArrayList getAssistiveDevicesADLEquipments(DataSource ds,
            FunctionalNeedReportDTO functionalNeedReportDTO)
            throws SADAREMDBException, SQLException {

        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        String district_id = null;
        String mandal_id = null;
        String village_id = null;
        String habitation_id = null;
        ArrayList<FunctionalNeedReportDTO> prothesisList = null;
        district_id = functionalNeedReportDTO.getDistrict_id();
        mandal_id = functionalNeedReportDTO.getMandal_id();
        village_id = functionalNeedReportDTO.getVillage_id();
        habitation_id = functionalNeedReportDTO.getHabitation_id();
        prothesisList = new ArrayList<FunctionalNeedReportDTO>();
        int total = 0;
        try {
            Date fdate = new SimpleDateFormat("dd/MM/yyyy").parse(functionalNeedReportDTO.getFromdate());
            String fromdate = new SimpleDateFormat("MM/dd/yyyy").format(fdate);

            Date tdate = new SimpleDateFormat("dd/MM/yyyy").parse(functionalNeedReportDTO.getTodate());
            String todate = new SimpleDateFormat("MM/dd/yyyy").format(tdate);
            con = DBConnection.getConnection();
            if (district_id.equals("0")) {

                cstmt = con.prepareCall("{Call SP_OHAD_ADLEQUIPMENT_DISTRICTWISE_SN_ANALASISREPORT(?,?)}");
                cstmt.setString(1, fromdate);
                cstmt.setString(2, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO districtADLDTO =
                            new FunctionalNeedReportDTO();

                    districtADLDTO.setDistrict_id(rs.getString("DISTRICT_ID"));
                    districtADLDTO.setMandal_id("a");
                    districtADLDTO.setVillage_id("a");
                    districtADLDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport()) {
                        districtADLDTO.setDistrictName("<a href='./analysisReport.do?mode=mandalLevelReport&district_id=" + rs.getString("DISTRICT_ID") + 

"&typeofdisability=" + functionalNeedReportDTO.getTypeofdisability() + "&reportcategory=" + functionalNeedReportDTO.getReportcategory() + "&fromdate=" + 

functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate() + "&reportSubcategory=" + functionalNeedReportDTO.getReportSubcategory() + 

"'>" + rs.getString("DISTRICT_NAME") + "</a>");
                    } else {
                        districtADLDTO.setDistrictName(rs.getString("DISTRICT_NAME"));
                    }
                    districtADLDTO.setFeeding(rs.getInt("FEEDING"));
                    districtADLDTO.setToiletingBathing(rs.getInt("TOILETING"));
                    districtADLDTO.setBrushing(rs.getInt("BRUSHING"));
                    districtADLDTO.setCombing(rs.getInt("COMBING"));
                    districtADLDTO.setDressing(rs.getInt("DRESSING"));
                    districtADLDTO.setWriting(rs.getInt("WRITING"));
                    districtADLDTO.setDrivingCycling(rs.getInt("DRIVING"));
                    districtADLDTO.setBedTransfer(rs.getInt("BEDTRANSFER"));

                    total = rs.getInt("FEEDING") + rs.getInt("TOILETING") + rs.getInt("BRUSHING") + rs.getInt("COMBING")
                            + rs.getInt("DRESSING") + rs.getInt("WRITING") + rs.getInt("DRIVING") + rs.getInt("BEDTRANSFER");
                    districtADLDTO.setTotal(total);
                    prothesisList.add(districtADLDTO);

                }


            } else if (mandal_id.equals("0")) {
                cstmt = con.prepareCall("{Call SP_OHAD_ADLEQUIPMENT_MANDALWISE_SN_ANALASISREPORT(?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, fromdate);
                cstmt.setString(3, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO mandalADLDTO =
                            new FunctionalNeedReportDTO();

                    mandalADLDTO.setDistrict_id(district_id);
                    mandalADLDTO.setMandal_id(rs.getString("MANDALID"));
                    mandalADLDTO.setVillage_id("a");
                    mandalADLDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport()) {
                        mandalADLDTO.setMandalName("<a href='./analysisReport.do?mode=villageLevelReport&district_id=" + district_id + "&mandal_id=" + rs.getString

("MANDALID") + "&typeofdisability=" + functionalNeedReportDTO.getTypeofdisability() + "&reportcategory=" + functionalNeedReportDTO.getReportcategory() + "&fromdate=" + 

functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate() + "&reportSubcategory=" + functionalNeedReportDTO.getReportSubcategory() + 

"'>" + rs.getString("MANDALNAME") + "</a>");
                    } else {
                        mandalADLDTO.setMandalName(rs.getString("MANDALNAME"));
                    }
                    mandalADLDTO.setFeeding(rs.getInt("FEEDING"));
                    mandalADLDTO.setToiletingBathing(rs.getInt("TOILETING"));
                    mandalADLDTO.setBrushing(rs.getInt("BRUSHING"));
                    mandalADLDTO.setCombing(rs.getInt("COMBING"));
                    mandalADLDTO.setDressing(rs.getInt("DRESSING"));
                    mandalADLDTO.setWriting(rs.getInt("WRITING"));
                    mandalADLDTO.setDrivingCycling(rs.getInt("DRIVING"));
                    mandalADLDTO.setBedTransfer(rs.getInt("BEDTRANSFER"));

                    total = rs.getInt("FEEDING") + rs.getInt("TOILETING") + rs.getInt("BRUSHING") + rs.getInt("COMBING")
                            + rs.getInt("DRESSING") + rs.getInt("WRITING") + rs.getInt("DRIVING") + rs.getInt("BEDTRANSFER");
                    mandalADLDTO.setTotal(total);
                    prothesisList.add(mandalADLDTO);

                }
            } else if (village_id.equals("0")) {
                cstmt = con.prepareCall("{Call SP_OHAD_ADLEQUIPMENT_VILLAGEWISE_SN_ANALASISREPORT(?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setString(3, fromdate);
                cstmt.setString(4, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO villageADLDTO =
                            new FunctionalNeedReportDTO();

                    villageADLDTO.setDistrict_id(district_id);
                    villageADLDTO.setMandal_id(mandal_id);
                    villageADLDTO.setVillage_id(rs.getString("VILLAGEID"));
                    villageADLDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport()) {
                        villageADLDTO.setVillageName("<a href='./analysisReport.do?mode=habitationLevelReport&district_id=" + district_id + "&mandal_id=" + mandal_id + 

"&village_id=" + rs.getString("VILLAGEID") + "&typeofdisability=" + functionalNeedReportDTO.getTypeofdisability() + "&reportcategory=" + 

functionalNeedReportDTO.getReportcategory() + "&fromdate=" + functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate() + 

"&reportSubcategory=" + functionalNeedReportDTO.getReportSubcategory() + "'>" + rs.getString("VILLAGENAME") + "</a>");
                    } else {
                        villageADLDTO.setVillageName(rs.getString("VILLAGENAME"));
                    }
                    villageADLDTO.setFeeding(rs.getInt("FEEDING"));
                    villageADLDTO.setToiletingBathing(rs.getInt("TOILETING"));
                    villageADLDTO.setBrushing(rs.getInt("BRUSHING"));
                    villageADLDTO.setCombing(rs.getInt("COMBING"));
                    villageADLDTO.setDressing(rs.getInt("DRESSING"));
                    villageADLDTO.setWriting(rs.getInt("WRITING"));
                    villageADLDTO.setDrivingCycling(rs.getInt("DRIVING"));
                    villageADLDTO.setBedTransfer(rs.getInt("BEDTRANSFER"));

                    total = rs.getInt("FEEDING") + rs.getInt("TOILETING") + rs.getInt("BRUSHING") + rs.getInt("COMBING")
                            + rs.getInt("DRESSING") + rs.getInt("WRITING") + rs.getInt("DRIVING") + rs.getInt("BEDTRANSFER");
                    villageADLDTO.setTotal(total);
                    prothesisList.add(villageADLDTO);

                }
            } else if (!village_id.equals("0")) {
                int getFeeding = 0;
                int getToiletingBathing = 0;
                int getBrushing = 0;
                int getCombing = 0;
                int getDressing = 0;
                int getWriting = 0;
                int getDrivingCycling = 0;
                int getBedTransfer = 0;
                int gTotal = 0;
                cstmt = con.prepareCall("{Call SP_OHAD_ADLEQUIPMENT_HABITATIONWISE_SN_ANALASISREPORT(?,?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setString(3, village_id);
                cstmt.setString(4, fromdate);
                cstmt.setString(5, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO habitationADLDTO =
                            new FunctionalNeedReportDTO();

                    habitationADLDTO.setDistrict_id(district_id);
                    habitationADLDTO.setMandal_id(mandal_id);
                    habitationADLDTO.setVillage_id(village_id);
                    habitationADLDTO.setHabitation_id(rs.getString("HABITATIONCODE").substring(10, 12));
                    habitationADLDTO.setHabitationName(rs.getString("HABITATIONNAME"));
                    habitationADLDTO.setFeeding(rs.getInt("FEEDING"));
                    habitationADLDTO.setToiletingBathing(rs.getInt("TOILETING"));
                    habitationADLDTO.setBrushing(rs.getInt("BRUSHING"));
                    habitationADLDTO.setCombing(rs.getInt("COMBING"));
                    habitationADLDTO.setDressing(rs.getInt("DRESSING"));
                    habitationADLDTO.setWriting(rs.getInt("WRITING"));
                    habitationADLDTO.setDrivingCycling(rs.getInt("DRIVING"));
                    habitationADLDTO.setBedTransfer(rs.getInt("BEDTRANSFER"));

                    total = rs.getInt("FEEDING") + rs.getInt("TOILETING") + rs.getInt("BRUSHING") + rs.getInt("COMBING")
                            + rs.getInt("DRESSING") + rs.getInt("WRITING") + rs.getInt("DRIVING") + rs.getInt("BEDTRANSFER");
                    habitationADLDTO.setTotal(total);
                    if ((district_id.equals("16") && mandal_id.equals("84") && village_id.equals("004")) && (rs.getString("HABITATIONCODE").substring(10, 12).equals

("00") || rs.getString("HABITATIONCODE").substring(10, 12).equals("01"))) {

                        if (rs.getString("HABITATIONCODE").substring(10, 12).equals("00")) {

                            getFeeding = habitationADLDTO.getFeeding();
                            getToiletingBathing = habitationADLDTO.getToiletingBathing();
                            getBrushing = habitationADLDTO.getBrushing();
                            getCombing = habitationADLDTO.getCombing();
                            getDressing = habitationADLDTO.getDressing();
                            getWriting = habitationADLDTO.getWriting();
                            getDrivingCycling = habitationADLDTO.getDrivingCycling();
                            getBedTransfer = habitationADLDTO.getBedTransfer();
                            gTotal = habitationADLDTO.getTotal();

                        }
                        if (rs.getString("HABITATIONCODE").substring(10, 12).equals("01")) {

                            habitationADLDTO.setFeeding(getFeeding + habitationADLDTO.getFeeding());
                            habitationADLDTO.setToiletingBathing(getToiletingBathing + habitationADLDTO.getToiletingBathing());
                            habitationADLDTO.setBrushing(getBrushing + habitationADLDTO.getBrushing());
                            habitationADLDTO.setCombing(getCombing + habitationADLDTO.getCombing());
                            habitationADLDTO.setDressing(getDressing + habitationADLDTO.getDressing());
                            habitationADLDTO.setWriting(getWriting + habitationADLDTO.getWriting());
                            habitationADLDTO.setDrivingCycling(getDrivingCycling + habitationADLDTO.getDrivingCycling());
                            habitationADLDTO.setBedTransfer(getBedTransfer + habitationADLDTO.getBedTransfer());
                            habitationADLDTO.setTotal(gTotal + habitationADLDTO.getTotal());
                            prothesisList.add(habitationADLDTO);
                        }
                    } else {
                        prothesisList.add(habitationADLDTO);
                    }

                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getAssistiveDevicesADLEquipments", "FunctionalNeedReportDAO", "DataBase");
            //DBConnection.rollbackConnection(con);
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getAssistiveDevicesADLEquipments");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getAssistiveDevicesADLEquipments", "FunctionalNeedReportDAO", "Code");
            //DBConnection.rollbackConnection(con);
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getAssistiveDevicesADLEquipments");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }
        return prothesisList;
    }

    public ArrayList getOtherNeeds(DataSource ds,
            FunctionalNeedReportDTO functionalNeedReportDTO)
            throws SADAREMDBException, SQLException {

        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        String district_id = null;
        String mandal_id = null;
        String village_id = null;
        String habitation_id = null;
        ArrayList<FunctionalNeedReportDTO> otherNeesList = null;
        district_id = functionalNeedReportDTO.getDistrict_id();
        mandal_id = functionalNeedReportDTO.getMandal_id();
        village_id = functionalNeedReportDTO.getVillage_id();
        habitation_id = functionalNeedReportDTO.getHabitation_id();
        otherNeesList = new ArrayList<FunctionalNeedReportDTO>();
        try {
            Date fdate = new SimpleDateFormat("dd/MM/yyyy").parse(functionalNeedReportDTO.getFromdate());
            String fromdate = new SimpleDateFormat("MM/dd/yyyy").format(fdate);

            Date tdate = new SimpleDateFormat("dd/MM/yyyy").parse(functionalNeedReportDTO.getTodate());
            String todate = new SimpleDateFormat("MM/dd/yyyy").format(tdate);
            con = DBConnection.getConnection();
            if (district_id.equals("0")) {

                cstmt = con.prepareCall("{Call SP_OH_OTHERNEEDS_DISTRICTWISE_SN_ANALASISREPORT(?,?)}");
                cstmt.setString(1, fromdate);
                cstmt.setString(2, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO districtOHOtherNeedsDTO =
                            new FunctionalNeedReportDTO();
                    districtOHOtherNeedsDTO.setDistrict_id(rs.getString("DISTRICT_ID"));
                    districtOHOtherNeedsDTO.setMandal_id("a");
                    districtOHOtherNeedsDTO.setVillage_id("a");
                    districtOHOtherNeedsDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport()) {
                        districtOHOtherNeedsDTO.setDistrictName("<a href='./analysisReport.do?mode=mandalLevelReport&district_id=" + rs.getString("DISTRICT_ID") + 

"&typeofdisability=" + functionalNeedReportDTO.getTypeofdisability() + "&reportcategory=" + functionalNeedReportDTO.getReportcategory() + "&fromdate=" + 

functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate() + "'>" + rs.getString("DISTRICT_NAME") + "</a>");
                    } else {
                        districtOHOtherNeedsDTO.setDistrictName(rs.getString("DISTRICT_NAME"));
                    }
                    districtOHOtherNeedsDTO.setOtherNeeds(rs.getInt("OTHERNEEDS"));
                    otherNeesList.add(districtOHOtherNeedsDTO);

                }


            } else if (mandal_id.equals("0")) {
                cstmt = con.prepareCall("{Call SP_OH_OTHERNEEDS_MANDALWISE_SN_ANALASISREPORT(?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, fromdate);
                cstmt.setString(3, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO mandalOHOtherNeedsDTO =
                            new FunctionalNeedReportDTO();
                    mandalOHOtherNeedsDTO.setDistrict_id(district_id);
                    mandalOHOtherNeedsDTO.setMandal_id(rs.getString("MANDALID"));
                    mandalOHOtherNeedsDTO.setVillage_id("a");
                    mandalOHOtherNeedsDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport()) {
                        mandalOHOtherNeedsDTO.setMandalName("<a href='./analysisReport.do?mode=villageLevelReport&district_id=" + district_id + "&mandal_id=" + 

rs.getString("MANDALID") + "&typeofdisability=" + functionalNeedReportDTO.getTypeofdisability() + "&reportcategory=" + functionalNeedReportDTO.getReportcategory() + 

"&fromdate=" + functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate() + "'>" + rs.getString("MANDALNAME") + "</a>");
                    } else {
                        mandalOHOtherNeedsDTO.setMandalName(rs.getString("MANDALNAME"));
                    }
                    mandalOHOtherNeedsDTO.setOtherNeeds(rs.getInt("OTHERNEEDS"));
                    otherNeesList.add(mandalOHOtherNeedsDTO);

                }
            } else if (village_id.equals("0")) {
                cstmt = con.prepareCall("{Call SP_OH_OTHERNEEDS_VILLAGEWISE_SN_ANALASISREPORT(?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setString(3, fromdate);
                cstmt.setString(4, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO villageOHOtherNeedsDTO =
                            new FunctionalNeedReportDTO();
                    villageOHOtherNeedsDTO.setDistrict_id(district_id);
                    villageOHOtherNeedsDTO.setMandal_id(mandal_id);
                    villageOHOtherNeedsDTO.setVillage_id(rs.getString("VILLAGEID"));
                    villageOHOtherNeedsDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport()) {
                        villageOHOtherNeedsDTO.setVillageName("<a href='./analysisReport.do?mode=habitationLevelReport&district_id=" + district_id + "&mandal_id=" + 

mandal_id + "&village_id=" + rs.getString("VILLAGEID") + "&typeofdisability=" + functionalNeedReportDTO.getTypeofdisability() + "&reportcategory=" + 

functionalNeedReportDTO.getReportcategory() + "&fromdate=" + functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate() + "'>" + 

rs.getString("VILLAGENAME") + "</a>");
                    } else {
                        villageOHOtherNeedsDTO.setVillageName(rs.getString("VILLAGENAME"));
                    }
                    villageOHOtherNeedsDTO.setOtherNeeds(rs.getInt("OTHERNEEDS"));
                    otherNeesList.add(villageOHOtherNeedsDTO);

                }
            } else if (!village_id.equals("0")) {
                int getOtherNeeds = 0;
                cstmt = con.prepareCall("{Call SP_OH_OTHERNEEDS_HABITATIONWISE_SN_ANALASISREPORT(?,?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setString(3, village_id);
                cstmt.setString(4, fromdate);
                cstmt.setString(5, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO habitationOHOtherNeedsDTO =
                            new FunctionalNeedReportDTO();
                    habitationOHOtherNeedsDTO.setDistrict_id(district_id);
                    habitationOHOtherNeedsDTO.setMandal_id(mandal_id);
                    habitationOHOtherNeedsDTO.setVillage_id(village_id);
                    habitationOHOtherNeedsDTO.setHabitation_id(rs.getString("HABITATIONCODE").substring(10, 12));
                    habitationOHOtherNeedsDTO.setHabitationName(rs.getString("HABITATIONNAME"));
                    habitationOHOtherNeedsDTO.setOtherNeeds(rs.getInt("OTHERNEEDS"));
                    if ((district_id.equals("16") && mandal_id.equals("84") && village_id.equals("004")) && (rs.getString("HABITATIONCODE").substring(10, 12).equals

("00") || rs.getString("HABITATIONCODE").substring(10, 12).equals("01"))) {

                        if (rs.getString("HABITATIONCODE").substring(10, 12).equals("00")) {

                            getOtherNeeds = habitationOHOtherNeedsDTO.getOtherNeeds();

                        }
                        if (rs.getString("HABITATIONCODE").substring(10, 12).equals("01")) {
                            habitationOHOtherNeedsDTO.setOtherNeeds(getOtherNeeds + habitationOHOtherNeedsDTO.getOtherNeeds());
                            otherNeesList.add(habitationOHOtherNeedsDTO);
                        }
                    } else {
                        otherNeesList.add(habitationOHOtherNeedsDTO);
                    }

                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getOtherNeeds", "FunctionalNeedReportDAO", "DataBase");
            //DBConnection.rollbackConnection(con);
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getOtherNeeds");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getOtherNeeds", "FunctionalNeedReportDAO", "Code");
            //DBConnection.rollbackConnection(con);
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getOtherNeeds");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }
        return otherNeesList;
    }

    public ArrayList getMedicalInterventionVI(DataSource ds,FunctionalNeedReportDTO functionalNeedReportDTO)throws SADAREMDBException, SQLException 
    {

        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        String district_id = null;
        String mandal_id = null;
        String village_id = null;
        String habitation_id = null;
        ArrayList<FunctionalNeedReportDTO> miList = null;
        district_id = functionalNeedReportDTO.getDistrict_id();
        mandal_id = functionalNeedReportDTO.getMandal_id();
        village_id = functionalNeedReportDTO.getVillage_id();
        habitation_id = functionalNeedReportDTO.getHabitation_id();
        miList = new ArrayList<FunctionalNeedReportDTO>();
        int total = 0;
        try {
            Date fdate = new SimpleDateFormat("dd/MM/yyyy").parse(functionalNeedReportDTO.getFromdate());
            String fromdate = new SimpleDateFormat("MM/dd/yyyy").format(fdate);

            Date tdate = new SimpleDateFormat("dd/MM/yyyy").parse(functionalNeedReportDTO.getTodate());
            String todate = new SimpleDateFormat("MM/dd/yyyy").format(tdate);
            con = DBConnection.getConnection();
            if (district_id.equals("0")) {

                cstmt = con.prepareCall("{Call SP_VIMI_DISTRICTWISE_SN_ANALASISREPORT(?,?)}");
                cstmt.setString(1, fromdate);
                cstmt.setString(2, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO districtVIMIDTO =
                            new FunctionalNeedReportDTO();
                    districtVIMIDTO.setDistrict_id(rs.getString("DISTRICT_ID"));
                    districtVIMIDTO.setMandal_id("a");
                    districtVIMIDTO.setVillage_id("a");
                    districtVIMIDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport())
                    {
                        districtVIMIDTO.setDistrictName("<a href='./analysisReport.do?mode=mandalLevelReport&district_id=" + rs.getString("DISTRICT_ID") + "&typeofdisability=" + functionalNeedReportDTO.getTypeofdisability() + "&reportcategory=" + functionalNeedReportDTO.getReportcategory() + "&fromdate=" + 
                        		functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate() + "'>" + rs.getString("DISTRICT_NAME") + "</a>");
                    } 
                    else 
                    {
                        districtVIMIDTO.setDistrictName(rs.getString("DISTRICT_NAME"));
                    }
                    districtVIMIDTO.setSugeryVI(rs.getInt("SURGERY"));

                    miList.add(districtVIMIDTO);

                }


            } else if (mandal_id.equals("0")) {
                cstmt = con.prepareCall("{Call SP_VIMI_MANDALWISE_SN_ANALASISREPORT(?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, fromdate);
                cstmt.setString(3, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO mandalVIMIDTO =
                            new FunctionalNeedReportDTO();
                    mandalVIMIDTO.setDistrict_id(district_id);
                    mandalVIMIDTO.setMandal_id(rs.getString("MANDALID"));
                    mandalVIMIDTO.setVillage_id("a");
                    mandalVIMIDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport()) {
                        mandalVIMIDTO.setMandalName("<a href='./analysisReport.do?mode=villageLevelReport&district_id=" + district_id + "&mandal_id=" + rs.getString

("MANDALID") + "&typeofdisability=" + functionalNeedReportDTO.getTypeofdisability() + "&reportcategory=" + functionalNeedReportDTO.getReportcategory() + "&fromdate=" + 

functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate() + "'>" + rs.getString("MANDALNAME") + "</a>");
                    } else {
                        mandalVIMIDTO.setMandalName(rs.getString("MANDALNAME"));
                    }
                    mandalVIMIDTO.setSugeryVI(rs.getInt("SURGERY"));

                    miList.add(mandalVIMIDTO);

                }
            } else if (village_id.equals("0")) {
                cstmt = con.prepareCall("{Call SP_VIMI_VILLAGEWISE_SN_ANALASISREPORT(?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setString(3, fromdate);
                cstmt.setString(4, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO villageVIMIDTO =
                            new FunctionalNeedReportDTO();
                    villageVIMIDTO.setDistrict_id(district_id);
                    villageVIMIDTO.setMandal_id(mandal_id);
                    villageVIMIDTO.setVillage_id(rs.getString("VILLAGEID"));
                    villageVIMIDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport()) {
                        villageVIMIDTO.setVillageName("<a href='./analysisReport.do?mode=habitationLevelReport&district_id=" + district_id + "&mandal_id=" + mandal_id 

+ "&village_id=" + rs.getString("VILLAGEID") + "&typeofdisability=" + functionalNeedReportDTO.getTypeofdisability() + "&reportcategory=" + 

functionalNeedReportDTO.getReportcategory() + "&fromdate=" + functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate() + "'>" + 

rs.getString("VILLAGENAME") + "</a>");
                    } else {
                        villageVIMIDTO.setVillageName(rs.getString("VILLAGENAME"));
                    }

                    villageVIMIDTO.setSugeryVI(rs.getInt("SURGERY"));

                    miList.add(villageVIMIDTO);
                }
            } else if (!village_id.equals("0")) {
                int setSugeryVI = 0;
                cstmt = con.prepareCall("{Call SP_VIMI_HABITATIONWISE_SN_ANALASISREPORT(?,?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setString(3, village_id);
                cstmt.setString(4, fromdate);
                cstmt.setString(5, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO habitationVIMIDTO =
                            new FunctionalNeedReportDTO();
                    habitationVIMIDTO.setDistrict_id(district_id);
                    habitationVIMIDTO.setMandal_id(mandal_id);
                    habitationVIMIDTO.setVillage_id(village_id);
                    habitationVIMIDTO.setHabitation_id(rs.getString("HABITATIONCODE").substring(10, 12));
                    habitationVIMIDTO.setHabitationName(rs.getString("HABITATIONNAME"));

                    habitationVIMIDTO.setSugeryVI(rs.getInt("SURGERY"));

                    if ((district_id.equals("16") && mandal_id.equals("84") && village_id.equals("004")) && (rs.getString("HABITATIONCODE").substring(10, 12).equals("00") || rs.getString("HABITATIONCODE").substring(10, 12).equals("01"))) 
                    {

                        if (rs.getString("HABITATIONCODE").substring(10, 12).equals("00"))
                        {

                            setSugeryVI = habitationVIMIDTO.getSugeryVI();

                        }
                        if (rs.getString("HABITATIONCODE").substring(10, 12).equals("01")) 
                        {
                            habitationVIMIDTO.setSugeryVI(setSugeryVI + habitationVIMIDTO.getSugeryVI());
                            miList.add(habitationVIMIDTO);
                        }
                    } 
                    else 
                    {
                        miList.add(habitationVIMIDTO);
                    }

                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMedicalInterventionVI", "FunctionalNeedReportDAO", "DataBase");
           //con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getMedicalInterventionVI");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMedicalInterventionVI", "FunctionalNeedReportDAO", "Code");
           //con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getMedicalInterventionVI");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }
        return miList;
    }

    public ArrayList getAssistiveAugmentativeDevicesVI(DataSource ds,
            FunctionalNeedReportDTO functionalNeedReportDTO)
            throws SADAREMDBException, SQLException {

        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        String district_id = null;
        String mandal_id = null;
        String village_id = null;
        String habitation_id = null;
        ArrayList<FunctionalNeedReportDTO> prothesisList = null;
        district_id = functionalNeedReportDTO.getDistrict_id();
        mandal_id = functionalNeedReportDTO.getMandal_id();
        village_id = functionalNeedReportDTO.getVillage_id();
        habitation_id = functionalNeedReportDTO.getHabitation_id();
        prothesisList = new ArrayList<FunctionalNeedReportDTO>();
        int total = 0;
        try {
            Date fdate = new SimpleDateFormat("dd/MM/yyyy").parse(functionalNeedReportDTO.getFromdate());
            String fromdate = new SimpleDateFormat("MM/dd/yyyy").format(fdate);

            Date tdate = new SimpleDateFormat("dd/MM/yyyy").parse(functionalNeedReportDTO.getTodate());
            String todate = new SimpleDateFormat("MM/dd/yyyy").format(tdate);
            con = DBConnection.getConnection();
            if (district_id.equals("0")) {

                cstmt = con.prepareCall("{Call SP_VIAD_DISTRICTWISE_SN_ANALASISREPORT(?,?)}");
                cstmt.setString(1, fromdate);
                cstmt.setString(2, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO districtVIADDTO =
                            new FunctionalNeedReportDTO();

                    districtVIADDTO.setDistrict_id(rs.getString("DISTRICT_ID"));
                    districtVIADDTO.setMandal_id("a");
                    districtVIADDTO.setVillage_id("a");
                    districtVIADDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport()) {
                        districtVIADDTO.setDistrictName("<a href='./analysisReport.do?mode=mandalLevelReport&district_id=" + rs.getString("DISTRICT_ID") + 

"&typeofdisability=" + functionalNeedReportDTO.getTypeofdisability() + "&reportcategory=" + functionalNeedReportDTO.getReportcategory() + "&fromdate=" + 

functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate() + "'>" + rs.getString("DISTRICT_NAME") + "</a>");
                    } else {
                        districtVIADDTO.setDistrictName(rs.getString("DISTRICT_NAME"));
                    }
                    districtVIADDTO.setWhiteCaneBlindStick(rs.getInt("WC"));
                    districtVIADDTO.setBrailleEquipments(rs.getInt("BE"));
                    districtVIADDTO.setArithmeticFramesAbacus(rs.getInt("AF"));
                    districtVIADDTO.setLowVisionAidsSpectaclesMagnifiers(rs.getInt("LVA"));
                    districtVIADDTO.setSpeechSynthesizer(rs.getInt("SS"));
                    districtVIADDTO.setBrailleShortHandMachinesWriters(rs.getInt("SH"));
                    districtVIADDTO.setTalkingWatchCalculator(rs.getInt("TW"));
                    districtVIADDTO.setViADLEquipment(rs.getInt("AA"));

                    total = rs.getInt("WC") + rs.getInt("BE") + rs.getInt("AF") + rs.getInt("LVA") + rs.getInt("SS")
                            + rs.getInt("SH") + rs.getInt("TW") + rs.getInt("AA");
                    districtVIADDTO.setTotal(total);
                    prothesisList.add(districtVIADDTO);

                }


            } else if (mandal_id.equals("0")) {
                cstmt = con.prepareCall("{Call SP_VIAD_MANDALWISE_SN_ANALASISREPORT(?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, fromdate);
                cstmt.setString(3, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO mandalVIADDTO =
                            new FunctionalNeedReportDTO();

                    mandalVIADDTO.setDistrict_id(district_id);
                    mandalVIADDTO.setMandal_id(rs.getString("MANDALID"));
                    mandalVIADDTO.setVillage_id("a");
                    mandalVIADDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport()) {
                        mandalVIADDTO.setMandalName("<a href='./analysisReport.do?mode=villageLevelReport&district_id=" + district_id + "&mandal_id=" + rs.getString

("MANDALID") + "&typeofdisability=" + functionalNeedReportDTO.getTypeofdisability() + "&reportcategory=" + functionalNeedReportDTO.getReportcategory() + "&fromdate=" + 

functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate() + "'>" + rs.getString("MANDALNAME") + "</a>");
                    } else {
                        mandalVIADDTO.setMandalName(rs.getString("MANDALNAME"));
                    }
                    mandalVIADDTO.setWhiteCaneBlindStick(rs.getInt("WC"));
                    mandalVIADDTO.setBrailleEquipments(rs.getInt("BE"));
                    mandalVIADDTO.setArithmeticFramesAbacus(rs.getInt("AF"));
                    mandalVIADDTO.setLowVisionAidsSpectaclesMagnifiers(rs.getInt("LVA"));
                    mandalVIADDTO.setSpeechSynthesizer(rs.getInt("SS"));
                    mandalVIADDTO.setBrailleShortHandMachinesWriters(rs.getInt("SH"));
                    mandalVIADDTO.setTalkingWatchCalculator(rs.getInt("TW"));
                    mandalVIADDTO.setViADLEquipment(rs.getInt("AA"));

                    total = rs.getInt("WC") + rs.getInt("BE") + rs.getInt("AF") + rs.getInt("LVA") + rs.getInt("SS")
                            + rs.getInt("SH") + rs.getInt("TW") + rs.getInt("AA");
                    mandalVIADDTO.setTotal(total);
                    prothesisList.add(mandalVIADDTO);

                }
            } else if (village_id.equals("0")) {
                cstmt = con.prepareCall("{Call SP_VIAD_VILLAGEWISE_SN_ANALASISREPORT(?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setString(3, fromdate);
                cstmt.setString(4, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO villageVIADDTO =
                            new FunctionalNeedReportDTO();

                    villageVIADDTO.setDistrict_id(district_id);
                    villageVIADDTO.setMandal_id(mandal_id);
                    villageVIADDTO.setVillage_id(rs.getString("VILLAGEID"));
                    villageVIADDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport()) {
                        villageVIADDTO.setVillageName("<a href='./analysisReport.do?mode=habitationLevelReport&district_id=" + district_id + "&mandal_id=" + mandal_id 

+ "&village_id=" + rs.getString("VILLAGEID") + "&typeofdisability=" + functionalNeedReportDTO.getTypeofdisability() + "&reportcategory=" + 

functionalNeedReportDTO.getReportcategory() + "&fromdate=" + functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate() + "'>" + 

rs.getString("VILLAGENAME") + "</a>");
                    } else {
                        villageVIADDTO.setVillageName(rs.getString("VILLAGENAME"));
                    }
                    villageVIADDTO.setWhiteCaneBlindStick(rs.getInt("WC"));
                    villageVIADDTO.setBrailleEquipments(rs.getInt("BE"));
                    villageVIADDTO.setArithmeticFramesAbacus(rs.getInt("AF"));
                    villageVIADDTO.setLowVisionAidsSpectaclesMagnifiers(rs.getInt("LVA"));
                    villageVIADDTO.setSpeechSynthesizer(rs.getInt("SS"));
                    villageVIADDTO.setBrailleShortHandMachinesWriters(rs.getInt("SH"));
                    villageVIADDTO.setTalkingWatchCalculator(rs.getInt("TW"));
                    villageVIADDTO.setViADLEquipment(rs.getInt("AA"));

                    total = rs.getInt("WC") + rs.getInt("BE") + rs.getInt("AF") + rs.getInt("LVA") + rs.getInt("SS")
                            + rs.getInt("SH") + rs.getInt("TW") + rs.getInt("AA");
                    villageVIADDTO.setTotal(total);
                    prothesisList.add(villageVIADDTO);

                }
            } else if (!village_id.equals("0")) {
                int getWhiteCaneBlindStick = 0;
                int getBrailleEquipments = 0;
                int getArithmeticFramesAbacus = 0;
                int getLowers = 0;
                int getSpeechSynthesizer = 0;
                int getBraWriters = 0;
                int getTalkiculator = 0;
                int getViADLEquipment = 0;
                int getTotal = 0;
                cstmt = con.prepareCall("{Call SP_VIAD_HABITATIONWISE_SN_ANALASISREPORT(?,?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setString(3, village_id);
                cstmt.setString(4, fromdate);
                cstmt.setString(5, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO habitationVIADDTO =
                            new FunctionalNeedReportDTO();

                    habitationVIADDTO.setDistrict_id(district_id);
                    habitationVIADDTO.setMandal_id(mandal_id);
                    habitationVIADDTO.setVillage_id(village_id);
                    habitationVIADDTO.setHabitation_id(rs.getString("HABITATIONCODE").substring(10, 12));
                    habitationVIADDTO.setHabitationName(rs.getString("HABITATIONNAME"));
                    habitationVIADDTO.setWhiteCaneBlindStick(rs.getInt("WC"));
                    habitationVIADDTO.setBrailleEquipments(rs.getInt("BE"));
                    habitationVIADDTO.setArithmeticFramesAbacus(rs.getInt("AF"));
                    habitationVIADDTO.setLowVisionAidsSpectaclesMagnifiers(rs.getInt("LVA"));
                    habitationVIADDTO.setSpeechSynthesizer(rs.getInt("SS"));
                    habitationVIADDTO.setBrailleShortHandMachinesWriters(rs.getInt("SH"));
                    habitationVIADDTO.setTalkingWatchCalculator(rs.getInt("TW"));
                    habitationVIADDTO.setViADLEquipment(rs.getInt("AA"));

                    total = rs.getInt("WC") + rs.getInt("BE") + rs.getInt("AF") + rs.getInt("LVA") + rs.getInt("SS")
                            + rs.getInt("SH") + rs.getInt("TW") + rs.getInt("AA");
                    habitationVIADDTO.setTotal(total);
                    if ((district_id.equals("16") && mandal_id.equals("84") && village_id.equals("004")) && (rs.getString("HABITATIONCODE").substring(10, 12).equals

("00") || rs.getString("HABITATIONCODE").substring(10, 12).equals("01"))) {

                        if (rs.getString("HABITATIONCODE").substring(10, 12).equals("00")) {

                            getWhiteCaneBlindStick = habitationVIADDTO.getWhiteCaneBlindStick();
                            getBrailleEquipments = habitationVIADDTO.getBrailleEquipments();
                            getArithmeticFramesAbacus = habitationVIADDTO.getArithmeticFramesAbacus();
                            getLowers = habitationVIADDTO.getLowVisionAidsSpectaclesMagnifiers();
                            getSpeechSynthesizer = habitationVIADDTO.getSpeechSynthesizer();
                            getBraWriters = habitationVIADDTO.getBrailleShortHandMachinesWriters();
                            getTalkiculator = habitationVIADDTO.getTalkingWatchCalculator();
                            getViADLEquipment = habitationVIADDTO.getViADLEquipment();
                            getTotal = habitationVIADDTO.getTotal();

                        }
                        if (rs.getString("HABITATIONCODE").substring(10, 12).equals("01")) {
                            habitationVIADDTO.setWhiteCaneBlindStick(getWhiteCaneBlindStick + habitationVIADDTO.getWhiteCaneBlindStick());
                            habitationVIADDTO.setBrailleEquipments(getBrailleEquipments + habitationVIADDTO.getBrailleEquipments());
                            habitationVIADDTO.setArithmeticFramesAbacus(getArithmeticFramesAbacus + habitationVIADDTO.getArithmeticFramesAbacus());
                            habitationVIADDTO.setLowVisionAidsSpectaclesMagnifiers(getLowers + habitationVIADDTO.getLowVisionAidsSpectaclesMagnifiers());
                            habitationVIADDTO.setSpeechSynthesizer(getSpeechSynthesizer + habitationVIADDTO.getSpeechSynthesizer());
                            habitationVIADDTO.setBrailleShortHandMachinesWriters(getBraWriters + habitationVIADDTO.getBrailleShortHandMachinesWriters());
                            habitationVIADDTO.setTalkingWatchCalculator(getTalkiculator + habitationVIADDTO.getTalkingWatchCalculator());
                            habitationVIADDTO.setViADLEquipment(getViADLEquipment + habitationVIADDTO.getViADLEquipment());
                            habitationVIADDTO.setTotal(getTotal + habitationVIADDTO.getTotal());

                            prothesisList.add(habitationVIADDTO);
                        }
                    } else {
                        prothesisList.add(habitationVIADDTO);
                    }

                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getAssistiveAugmentativeDevicesVI", "FunctionalNeedReportDAO", "DataBase");
            //DBConnection.rollbackConnection(con);
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getAssistiveAugmentativeDevicesVI");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getAssistiveAugmentativeDevicesVI", "FunctionalNeedReportDAO", "Code");
            //DBConnection.rollbackConnection(con);
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getAssistiveAugmentativeDevicesVI");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }
        return prothesisList;
    }

    public ArrayList getOtherNeedsVI(DataSource ds,
            FunctionalNeedReportDTO functionalNeedReportDTO)
            throws SADAREMDBException, SQLException {

        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        String district_id = null;
        String mandal_id = null;
        String village_id = null;
        String habitation_id = null;
        ArrayList<FunctionalNeedReportDTO> otherNeesList = null;
        district_id = functionalNeedReportDTO.getDistrict_id();
        mandal_id = functionalNeedReportDTO.getMandal_id();
        village_id = functionalNeedReportDTO.getVillage_id();
        habitation_id = functionalNeedReportDTO.getHabitation_id();
        otherNeesList = new ArrayList<FunctionalNeedReportDTO>();
        try {
            Date fdate = new SimpleDateFormat("dd/MM/yyyy").parse(functionalNeedReportDTO.getFromdate());
            String fromdate = new SimpleDateFormat("MM/dd/yyyy").format(fdate);

            Date tdate = new SimpleDateFormat("dd/MM/yyyy").parse(functionalNeedReportDTO.getTodate());
            String todate = new SimpleDateFormat("MM/dd/yyyy").format(tdate);
            con = DBConnection.getConnection();
            if (district_id.equals("0")) {

                cstmt = con.prepareCall("{Call SP_VI_OTHERNEEDS_DISTRICTWISE_SN_ANALASISREPORT(?,?)}");
                cstmt.setString(1, fromdate);
                cstmt.setString(2, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO districtVIOtherNeedsDTO =
                            new FunctionalNeedReportDTO();
                    districtVIOtherNeedsDTO.setDistrict_id(rs.getString("DISTRICT_ID"));
                    districtVIOtherNeedsDTO.setMandal_id("a");
                    districtVIOtherNeedsDTO.setVillage_id("a");
                    districtVIOtherNeedsDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport()) {
                        districtVIOtherNeedsDTO.setDistrictName("<a href='./analysisReport.do?mode=mandalLevelReport&district_id=" + rs.getString("DISTRICT_ID") + 

"&typeofdisability=" + functionalNeedReportDTO.getTypeofdisability() + "&reportcategory=" + functionalNeedReportDTO.getReportcategory() + "&fromdate=" + 

functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate() + "'>" + rs.getString("DISTRICT_NAME") + "</a>");
                    } else {
                        districtVIOtherNeedsDTO.setDistrictName(rs.getString("DISTRICT_NAME"));
                    }
                    districtVIOtherNeedsDTO.setOtherNeedsVI(rs.getInt("ANYOTHER"));
                    otherNeesList.add(districtVIOtherNeedsDTO);

                }


            } else if (mandal_id.equals("0")) {
                cstmt = con.prepareCall("{Call SP_VI_OTHERNEEDS_MANDALWISE_SN_ANALASISREPORT(?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, fromdate);
                cstmt.setString(3, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO mandalVIOtherNeedsDTO =
                            new FunctionalNeedReportDTO();
                    mandalVIOtherNeedsDTO.setDistrict_id(district_id);
                    mandalVIOtherNeedsDTO.setMandal_id(rs.getString("MANDALID"));
                    mandalVIOtherNeedsDTO.setVillage_id("a");
                    mandalVIOtherNeedsDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport()) {
                        mandalVIOtherNeedsDTO.setMandalName("<a href='./analysisReport.do?mode=villageLevelReport&district_id=" + district_id + "&mandal_id=" + 

rs.getString("MANDALID") + "&typeofdisability=" + functionalNeedReportDTO.getTypeofdisability() + "&reportcategory=" + functionalNeedReportDTO.getReportcategory() + 

"&fromdate=" + functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate() + "'>" + rs.getString("MANDALNAME") + "</a>");
                    } else {
                        mandalVIOtherNeedsDTO.setMandalName(rs.getString("MANDALNAME"));
                    }
                    mandalVIOtherNeedsDTO.setOtherNeedsVI(rs.getInt("ANYOTHER"));
                    otherNeesList.add(mandalVIOtherNeedsDTO);

                }
            } else if (village_id.equals("0")) {
                cstmt = con.prepareCall("{Call SP_VI_OTHERNEEDS_VILLAGEWISE_SN_ANALASISREPORT(?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setString(3, fromdate);
                cstmt.setString(4, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO villageVIOtherNeedsDTO =
                            new FunctionalNeedReportDTO();
                    villageVIOtherNeedsDTO.setDistrict_id(district_id);
                    villageVIOtherNeedsDTO.setMandal_id(mandal_id);
                    villageVIOtherNeedsDTO.setVillage_id(rs.getString("VILLAGEID"));
                    villageVIOtherNeedsDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport()) {
                        villageVIOtherNeedsDTO.setVillageName("<a href='./analysisReport.do?mode=habitationLevelReport&district_id=" + district_id + "&mandal_id=" + 

mandal_id + "&village_id=" + rs.getString("VILLAGEID") + "&typeofdisability=" + functionalNeedReportDTO.getTypeofdisability() + "&reportcategory=" + 

functionalNeedReportDTO.getReportcategory() + "&fromdate=" + functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate() + "'>" + 

rs.getString("VILLAGENAME") + "</a>");
                    } else {
                        villageVIOtherNeedsDTO.setVillageName(rs.getString("VILLAGENAME"));
                    }
                    villageVIOtherNeedsDTO.setOtherNeedsVI(rs.getInt("ANYOTHER"));
                    otherNeesList.add(villageVIOtherNeedsDTO);

                }
            } else if (!village_id.equals("0")) {
                int getOtherNeedsVI = 0;
                cstmt = con.prepareCall("{Call SP_VI_OTHERNEEDS_HABITATIONWISE_SN_ANALASISREPORT(?,?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setString(3, village_id);
                cstmt.setString(4, fromdate);
                cstmt.setString(5, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO habitationVIOtherNeedsDTO =
                            new FunctionalNeedReportDTO();
                    habitationVIOtherNeedsDTO.setDistrict_id(district_id);
                    habitationVIOtherNeedsDTO.setMandal_id(mandal_id);
                    habitationVIOtherNeedsDTO.setVillage_id(village_id);
                    habitationVIOtherNeedsDTO.setHabitation_id(rs.getString("HABITATIONCODE").substring(10, 12));
                    habitationVIOtherNeedsDTO.setHabitationName(rs.getString("HABITATIONNAME"));
                    habitationVIOtherNeedsDTO.setOtherNeedsVI(rs.getInt("ANYOTHER"));


                    if ((district_id.equals("16") && mandal_id.equals("84") && village_id.equals("004")) && (rs.getString("HABITATIONCODE").substring(10, 12).equals

("00") || rs.getString("HABITATIONCODE").substring(10, 12).equals("01"))) {

                        if (rs.getString("HABITATIONCODE").substring(10, 12).equals("00")) {

                            getOtherNeedsVI = habitationVIOtherNeedsDTO.getOtherNeedsVI();

                        }
                        if (rs.getString("HABITATIONCODE").substring(10, 12).equals("01")) {
                            habitationVIOtherNeedsDTO.setOtherNeedsVI(getOtherNeedsVI + habitationVIOtherNeedsDTO.getOtherNeedsVI());

                            otherNeesList.add(habitationVIOtherNeedsDTO);
                        }
                    } else {

                        otherNeesList.add(habitationVIOtherNeedsDTO);
                    }

                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getOtherNeedsVI", "FunctionalNeedReportDAO", "DataBase");
           //con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getOtherNeedsVI");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getOtherNeedsVI", "FunctionalNeedReportDAO", "Code");
            //DBConnection.rollbackConnection(con);
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getOtherNeedsVI");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }
        return otherNeesList;
    }

    /**
     * this method fetches medical intervention information from database
     * @param ds
     * @param dailydisabilityandpercentagedto
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public ArrayList getMedicalInterventionHI(DataSource ds,
            FunctionalNeedReportDTO functionalNeedReportDTO)
            throws SADAREMDBException, SQLException {

        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        String district_id = null;
        String mandal_id = null;
        String village_id = null;
        String habitation_id = null;
        ArrayList<FunctionalNeedReportDTO> miList = null;
        district_id = functionalNeedReportDTO.getDistrict_id();
        mandal_id = functionalNeedReportDTO.getMandal_id();
        village_id = functionalNeedReportDTO.getVillage_id();
        habitation_id = functionalNeedReportDTO.getHabitation_id();
        miList = new ArrayList<FunctionalNeedReportDTO>();
        int total = 0;
        try {
            Date fdate = new SimpleDateFormat("dd/MM/yyyy").parse(functionalNeedReportDTO.getFromdate());
            String fromdate = new SimpleDateFormat("MM/dd/yyyy").format(fdate);

            Date tdate = new SimpleDateFormat("dd/MM/yyyy").parse(functionalNeedReportDTO.getTodate());
            String todate = new SimpleDateFormat("MM/dd/yyyy").format(tdate);
            con = DBConnection.getConnection();
            if (district_id.equals("0")) {

                cstmt = con.prepareCall("{Call SP_HIMI_DISTRICTWISE_SN_ANALASISREPORT(?,?)}");
                cstmt.setString(1, fromdate);
                cstmt.setString(2, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO districtMIDTO =
                            new FunctionalNeedReportDTO();
                    districtMIDTO.setDistrict_id(rs.getString("DISTRICT_ID"));
                    districtMIDTO.setMandal_id("a");
                    districtMIDTO.setVillage_id("a");
                    districtMIDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport()) {
                        districtMIDTO.setDistrictName("<a href='./analysisReport.do?mode=mandalLevelReport&district_id=" + rs.getString("DISTRICT_ID") + 

"&typeofdisability=" + functionalNeedReportDTO.getTypeofdisability() + "&reportcategory=" + functionalNeedReportDTO.getReportcategory() + "&fromdate=" + 

functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate() + "'>" + rs.getString("DISTRICT_NAME") + "</a>");
                    } else {
                        districtMIDTO.setDistrictName(rs.getString("DISTRICT_NAME"));
                    }
                    districtMIDTO.setSpeechTherapyBelowThreeYearsHI(rs.getInt("SBELOW3"));
                    districtMIDTO.setSpeechTherapyAboveThreeYearsHI(rs.getInt("SABOVE3"));
                    districtMIDTO.setLanguageDevelopment(rs.getInt("LD"));
                    districtMIDTO.setSurgeryHI(rs.getInt("SURGERY"));
                    districtMIDTO.setCochlearImplantation(rs.getInt("CI"));

                    total = rs.getInt("SBELOW3") + rs.getInt("SABOVE3") + rs.getInt("LD")
                            + rs.getInt("SURGERY") + rs.getInt("CI");
                    districtMIDTO.setTotal(total);
                    miList.add(districtMIDTO);

                }


            } else if (mandal_id.equals("0")) {
                cstmt = con.prepareCall("{Call SP_HIMI_MANDALWISE_SN_ANALASISREPORT(?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, fromdate);
                cstmt.setString(3, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO mandalMIDTO =
                            new FunctionalNeedReportDTO();
                    mandalMIDTO.setDistrict_id(district_id);
                    mandalMIDTO.setMandal_id(rs.getString("MANDALID"));
                    mandalMIDTO.setVillage_id("a");
                    mandalMIDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport()) {
                        mandalMIDTO.setMandalName("<a href='./analysisReport.do?mode=villageLevelReport&district_id=" + district_id + "&mandal_id=" + rs.getString

("MANDALID") + "&typeofdisability=" + functionalNeedReportDTO.getTypeofdisability() + "&reportcategory=" + functionalNeedReportDTO.getReportcategory() + "&fromdate=" + 

functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate() + "'>" + rs.getString("MANDALNAME") + "</a>");
                    } else {
                        mandalMIDTO.setMandalName(rs.getString("MANDALNAME"));
                    }
                    mandalMIDTO.setSpeechTherapyBelowThreeYearsHI(rs.getInt("SBELOW3"));
                    mandalMIDTO.setSpeechTherapyAboveThreeYearsHI(rs.getInt("SABOVE3"));
                    mandalMIDTO.setLanguageDevelopment(rs.getInt("LD"));
                    mandalMIDTO.setSurgeryHI(rs.getInt("SURGERY"));
                    mandalMIDTO.setCochlearImplantation(rs.getInt("CI"));

                    total = rs.getInt("SBELOW3") + rs.getInt("SABOVE3") + rs.getInt("LD")
                            + rs.getInt("SURGERY") + rs.getInt("CI");
                    mandalMIDTO.setTotal(total);
                    miList.add(mandalMIDTO);

                }
            } else if (village_id.equals("0")) {
                cstmt = con.prepareCall("{Call SP_HIMI_VILLAGEWISE_SN_ANALASISREPORT(?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setString(3, fromdate);
                cstmt.setString(4, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO villageMIDTO =
                            new FunctionalNeedReportDTO();
                    villageMIDTO.setDistrict_id(district_id);
                    villageMIDTO.setMandal_id(mandal_id);
                    villageMIDTO.setVillage_id(rs.getString("VILLAGEID"));
                    villageMIDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport()) {
                        villageMIDTO.setVillageName("<a href='./analysisReport.do?mode=habitationLevelReport&district_id=" + district_id + "&mandal_id=" + mandal_id + 

"&village_id=" + rs.getString("VILLAGEID") + "&typeofdisability=" + functionalNeedReportDTO.getTypeofdisability() + "&reportcategory=" + 

functionalNeedReportDTO.getReportcategory() + "&fromdate=" + functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate() + "'>" + 

rs.getString("VILLAGENAME") + "</a>");
                    } else {
                        villageMIDTO.setVillageName(rs.getString("VILLAGENAME"));
                    }
                    villageMIDTO.setSpeechTherapyBelowThreeYearsHI(rs.getInt("SBELOW3"));
                    villageMIDTO.setSpeechTherapyAboveThreeYearsHI(rs.getInt("SABOVE3"));
                    villageMIDTO.setLanguageDevelopment(rs.getInt("LD"));
                    villageMIDTO.setSurgeryHI(rs.getInt("SURGERY"));
                    villageMIDTO.setCochlearImplantation(rs.getInt("CI"));

                    total = rs.getInt("SBELOW3") + rs.getInt("SABOVE3") + rs.getInt("LD")
                            + rs.getInt("SURGERY") + rs.getInt("CI");
                    villageMIDTO.setTotal(total);
                    miList.add(villageMIDTO);
                }
            } else if (!village_id.equals("0")) {
                int speechBelowThree = 0;
                int speechAboveThree = 0;
                int languageDevelopment = 0;
                int surgeryHI = 0;
                int cochlearImplantation = 0;
                int totl = 0;
                cstmt = con.prepareCall("{Call SP_HIMI_HABITATIONWISE_SN_ANALASISREPORT(?,?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setString(3, village_id);
                cstmt.setString(4, fromdate);
                cstmt.setString(5, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO habitationMIDTO =
                            new FunctionalNeedReportDTO();
                    habitationMIDTO.setDistrict_id(district_id);
                    habitationMIDTO.setMandal_id(mandal_id);
                    habitationMIDTO.setVillage_id(village_id);
                    habitationMIDTO.setHabitation_id(rs.getString("HABITATIONCODE").substring(10, 12));
                    habitationMIDTO.setHabitationName(rs.getString("HABITATIONNAME"));
                    habitationMIDTO.setSpeechTherapyBelowThreeYearsHI(rs.getInt("SBELOW3"));
                    habitationMIDTO.setSpeechTherapyAboveThreeYearsHI(rs.getInt("SABOVE3"));
                    habitationMIDTO.setLanguageDevelopment(rs.getInt("LD"));
                    habitationMIDTO.setSurgeryHI(rs.getInt("SURGERY"));
                    habitationMIDTO.setCochlearImplantation(rs.getInt("CI"));

                    total = rs.getInt("SBELOW3") + rs.getInt("SABOVE3") + rs.getInt("LD")
                            + rs.getInt("SURGERY") + rs.getInt("CI");
                    habitationMIDTO.setTotal(total);

                    if ((district_id.equals("16") && mandal_id.equals("84") && village_id.equals("004")) && (rs.getString("HABITATIONCODE").substring(10, 12).equals

("00") || rs.getString("HABITATIONCODE").substring(10, 12).equals("01"))) {

                        if (rs.getString("HABITATIONCODE").substring(10, 12).equals("00")) {

                            speechBelowThree = habitationMIDTO.getSpeechTherapyBelowThreeYearsHI();
                            speechAboveThree = habitationMIDTO.getSpeechTherapyAboveThreeYearsHI();
                            languageDevelopment = habitationMIDTO.getLanguageDevelopment();
                            surgeryHI = habitationMIDTO.getSurgeryHI();
                            cochlearImplantation = habitationMIDTO.getCochlearImplantation();
                            totl = habitationMIDTO.getTotal();

                        }
                        if (rs.getString("HABITATIONCODE").substring(10, 12).equals("01")) {

                            habitationMIDTO.setSpeechTherapyBelowThreeYearsHI(speechBelowThree + habitationMIDTO.getSpeechTherapyBelowThreeYearsHI());
                            habitationMIDTO.setSpeechTherapyAboveThreeYearsHI(speechAboveThree + habitationMIDTO.getSpeechTherapyAboveThreeYearsHI());
                            habitationMIDTO.setLanguageDevelopment(languageDevelopment + habitationMIDTO.getLanguageDevelopment());
                            habitationMIDTO.setSurgeryHI(surgeryHI + habitationMIDTO.getSurgeryHI());
                            habitationMIDTO.setCochlearImplantation(cochlearImplantation + habitationMIDTO.getCochlearImplantation());
                            habitationMIDTO.setTotal(totl + habitationMIDTO.getTotal());

                            miList.add(habitationMIDTO);
                        }
                    } else {

                        miList.add(habitationMIDTO);
                    }

                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMedicalInterventionHI", "FunctionalNeedReportDAO", "DataBase");
           //con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getMedicalInterventionHI");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMedicalInterventionHI", "FunctionalNeedReportDAO", "Code");
            //DBConnection.rollbackConnection(con);
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getMedicalInterventionHI");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }
        return miList;
    }

    public ArrayList getAssistiveAugmentativeDevicesHI(DataSource ds,
            FunctionalNeedReportDTO functionalNeedReportDTO)
            throws SADAREMDBException, SQLException {

        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        String district_id = null;
        String mandal_id = null;
        String village_id = null;
        String habitation_id = null;
        ArrayList<FunctionalNeedReportDTO> prothesisList = null;
        district_id = functionalNeedReportDTO.getDistrict_id();
        mandal_id = functionalNeedReportDTO.getMandal_id();
        village_id = functionalNeedReportDTO.getVillage_id();
        habitation_id = functionalNeedReportDTO.getHabitation_id();
        prothesisList = new ArrayList<FunctionalNeedReportDTO>();
        int total = 0;
        try {
            Date fdate = new SimpleDateFormat("dd/MM/yyyy").parse(functionalNeedReportDTO.getFromdate());
            String fromdate = new SimpleDateFormat("MM/dd/yyyy").format(fdate);

            Date tdate = new SimpleDateFormat("dd/MM/yyyy").parse(functionalNeedReportDTO.getTodate());
            String todate = new SimpleDateFormat("MM/dd/yyyy").format(tdate);
            con = DBConnection.getConnection();
            if (district_id.equals("0")) {

                cstmt = con.prepareCall("{Call SP_HIAD_DISTRICTWISE_SN_ANALASISREPORT(?,?)}");
                cstmt.setString(1, fromdate);
                cstmt.setString(2, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO districtHIADDTO =
                            new FunctionalNeedReportDTO();

                    districtHIADDTO.setDistrict_id(rs.getString("DISTRICT_ID"));
                    districtHIADDTO.setMandal_id("a");
                    districtHIADDTO.setVillage_id("a");
                    districtHIADDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport()) {
                        districtHIADDTO.setDistrictName("<a href='./analysisReport.do?mode=mandalLevelReport&district_id=" + rs.getString("DISTRICT_ID") + 

"&typeofdisability=" + functionalNeedReportDTO.getTypeofdisability() + "&reportcategory=" + functionalNeedReportDTO.getReportcategory() + "&fromdate=" + 

functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate() + "'>" + rs.getString("DISTRICT_NAME") + "</a>");
                    } else {
                        districtHIADDTO.setDistrictName(rs.getString("DISTRICT_NAME"));
                    }
                    districtHIADDTO.setLowIntensitySCord(rs.getInt("LIS"));
                    districtHIADDTO.setLowIntensityVCord(rs.getInt("LIV"));
                    districtHIADDTO.setModerateIntensitySCord(rs.getInt("MIS"));
                    districtHIADDTO.setModerateIntensityVCord(rs.getInt("MIV"));
                    districtHIADDTO.setHighIntensitySCord(rs.getInt("HIS"));
                    districtHIADDTO.setHighIntensityVCord(rs.getInt("HIV"));
                    districtHIADDTO.setImplantableHearingAid(rs.getInt("IHA"));

                    total = rs.getInt("LIS") + rs.getInt("LIV") + rs.getInt("MIS") + rs.getInt("MIV") + rs.getInt("HIS")
                            + rs.getInt("HIV") + rs.getInt("IHA");
                    districtHIADDTO.setTotal(total);
                    prothesisList.add(districtHIADDTO);

                }


            } else if (mandal_id.equals("0")) {
                cstmt = con.prepareCall("{Call SP_HIAD_MANDALWISE_SN_ANALASISREPORT(?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, fromdate);
                cstmt.setString(3, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO mandalHIADDTO =
                            new FunctionalNeedReportDTO();

                    mandalHIADDTO.setDistrict_id(district_id);
                    mandalHIADDTO.setMandal_id(rs.getString("MANDALID"));
                    mandalHIADDTO.setVillage_id("a");
                    mandalHIADDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport()) {
                        mandalHIADDTO.setMandalName("<a href='./analysisReport.do?mode=villageLevelReport&district_id=" + district_id + "&mandal_id=" + rs.getString

("MANDALID") + "&typeofdisability=" + functionalNeedReportDTO.getTypeofdisability() + "&reportcategory=" + functionalNeedReportDTO.getReportcategory() + "&fromdate=" + 

functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate() + "'>" + rs.getString("MANDALNAME") + "</a>");
                    } else {
                        mandalHIADDTO.setMandalName(rs.getString("MANDALNAME"));
                    }
                    mandalHIADDTO.setLowIntensitySCord(rs.getInt("LIS"));
                    mandalHIADDTO.setLowIntensityVCord(rs.getInt("LIV"));
                    mandalHIADDTO.setModerateIntensitySCord(rs.getInt("MIS"));
                    mandalHIADDTO.setModerateIntensityVCord(rs.getInt("MIV"));
                    mandalHIADDTO.setHighIntensitySCord(rs.getInt("HIS"));
                    mandalHIADDTO.setHighIntensityVCord(rs.getInt("HIV"));
                    mandalHIADDTO.setImplantableHearingAid(rs.getInt("IHA"));

                    total = rs.getInt("LIS") + rs.getInt("LIV") + rs.getInt("MIS") + rs.getInt("MIV") + rs.getInt("HIS")
                            + rs.getInt("HIV") + rs.getInt("IHA");
                    mandalHIADDTO.setTotal(total);
                    prothesisList.add(mandalHIADDTO);

                }
            } else if (village_id.equals("0")) {
                cstmt = con.prepareCall("{Call SP_HIAD_VILLAGEWISE_SN_ANALASISREPORT(?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setString(3, fromdate);
                cstmt.setString(4, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO villageHIADDTO =
                            new FunctionalNeedReportDTO();

                    villageHIADDTO.setDistrict_id(district_id);
                    villageHIADDTO.setMandal_id(mandal_id);
                    if (functionalNeedReportDTO.isReport()) {
                        villageHIADDTO.setVillage_id("<a href='./analysisReport.do?mode=habitationLevelReport&district_id=" + district_id + "&mandal_id=" + mandal_id + 

"&village_id=" + rs.getString("VILLAGEID") + "&typeofdisability=" + functionalNeedReportDTO.getTypeofdisability() + "&reportcategory=" + 

functionalNeedReportDTO.getReportcategory() + "&fromdate=" + functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate() + "'>" + 

rs.getString("VILLAGENAME") + "</a>");

                    } else {
                        villageHIADDTO.setVillage_id(rs.getString("VILLAGENAME"));
                    }
                    villageHIADDTO.setHabitation_id("a");
                    villageHIADDTO.setVillageName(rs.getString("VILLAGENAME"));
                    villageHIADDTO.setLowIntensitySCord(rs.getInt("LIS"));
                    villageHIADDTO.setLowIntensityVCord(rs.getInt("LIV"));
                    villageHIADDTO.setModerateIntensitySCord(rs.getInt("MIS"));
                    villageHIADDTO.setModerateIntensityVCord(rs.getInt("MIV"));
                    villageHIADDTO.setHighIntensitySCord(rs.getInt("HIS"));
                    villageHIADDTO.setHighIntensityVCord(rs.getInt("HIV"));
                    villageHIADDTO.setImplantableHearingAid(rs.getInt("IHA"));

                    total = rs.getInt("LIS") + rs.getInt("LIV") + rs.getInt("MIS") + rs.getInt("MIV") + rs.getInt("HIS")
                            + rs.getInt("HIV") + rs.getInt("IHA");
                    villageHIADDTO.setTotal(total);
                    prothesisList.add(villageHIADDTO);

                }
            } else if (!village_id.equals("0")) {
                int sCord = 0;
                int vCord = 0;
                int mCord = 0;
                int mVCord = 0;
                int hSCord = 0;
                int hVCord = 0;
                int hAid = 0;
                int totl = 0;
                cstmt = con.prepareCall("{Call SP_HIAD_HABITATIONWISE_SN_ANALASISREPORT(?,?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setString(3, village_id);
                cstmt.setString(4, fromdate);
                cstmt.setString(5, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO villageHIADDTO =
                            new FunctionalNeedReportDTO();

                    villageHIADDTO.setDistrict_id(district_id);
                    villageHIADDTO.setMandal_id(mandal_id);
                    villageHIADDTO.setVillage_id(village_id);
                    villageHIADDTO.setHabitation_id(rs.getString("HABITATIONCODE").substring(10, 12));
                    villageHIADDTO.setHabitationName(rs.getString("HABITATIONNAME"));
                    villageHIADDTO.setLowIntensitySCord(rs.getInt("LIS"));
                    villageHIADDTO.setLowIntensityVCord(rs.getInt("LIV"));
                    villageHIADDTO.setModerateIntensitySCord(rs.getInt("MIS"));
                    villageHIADDTO.setModerateIntensityVCord(rs.getInt("MIV"));
                    villageHIADDTO.setHighIntensitySCord(rs.getInt("HIS"));
                    villageHIADDTO.setHighIntensityVCord(rs.getInt("HIV"));
                    villageHIADDTO.setImplantableHearingAid(rs.getInt("IHA"));

                    total = rs.getInt("LIS") + rs.getInt("LIV") + rs.getInt("MIS") + rs.getInt("MIV") + rs.getInt("HIS")
                            + rs.getInt("HIV") + rs.getInt("IHA");
                    villageHIADDTO.setTotal(total);
                    if ((district_id.equals("16") && mandal_id.equals("84") && village_id.equals("004")) && (rs.getString("HABITATIONCODE").substring(10, 12).equals

("00") || rs.getString("HABITATIONCODE").substring(10, 12).equals("01"))) {

                        if (rs.getString("HABITATIONCODE").substring(10, 12).equals("00")) {
                            sCord = villageHIADDTO.getLowIntensitySCord();
                            vCord = villageHIADDTO.getLowIntensityVCord();
                            mCord = villageHIADDTO.getModerateIntensitySCord();
                            mVCord = villageHIADDTO.getModerateIntensityVCord();
                            hSCord = villageHIADDTO.getHighIntensitySCord();
                            hVCord = villageHIADDTO.getHighIntensityVCord();
                            hAid = villageHIADDTO.getImplantableHearingAid();
                            totl = villageHIADDTO.getTotal();

                        }
                        if (rs.getString("HABITATIONCODE").substring(10, 12).equals("01")) {

                            villageHIADDTO.setLowIntensitySCord(sCord + villageHIADDTO.getLowIntensitySCord());
                            villageHIADDTO.setLowIntensityVCord(vCord + villageHIADDTO.getLowIntensityVCord());
                            villageHIADDTO.setModerateIntensitySCord(mCord + villageHIADDTO.getModerateIntensitySCord());
                            villageHIADDTO.setModerateIntensityVCord(mVCord + villageHIADDTO.getModerateIntensityVCord());
                            villageHIADDTO.setHighIntensitySCord(hSCord + villageHIADDTO.getHighIntensitySCord());
                            villageHIADDTO.setHighIntensityVCord(hVCord + villageHIADDTO.getHighIntensityVCord());
                            villageHIADDTO.setImplantableHearingAid(hAid + villageHIADDTO.getImplantableHearingAid());
                            villageHIADDTO.setTotal(totl + villageHIADDTO.getTotal());

                            prothesisList.add(villageHIADDTO);
                        }
                    } else {

                        prothesisList.add(villageHIADDTO);
                    }

                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getAssistiveAugmentativeDevicesHI", "FunctionalNeedReportDAO", "DataBase");
            //DBConnection.rollbackConnection(con);
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getAssistiveAugmentativeDevicesHI");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getAssistiveAugmentativeDevicesHI", "FunctionalNeedReportDAO", "Code");
            //DBConnection.rollbackConnection(con);
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getAssistiveAugmentativeDevicesHI");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }
        return prothesisList;
    }

    public ArrayList getOtherNeedsHI(DataSource ds,
            FunctionalNeedReportDTO functionalNeedReportDTO)
            throws SADAREMDBException, SQLException {

        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        String district_id = null;
        String mandal_id = null;
        String village_id = null;
        String habitation_id = null;
        ArrayList<FunctionalNeedReportDTO> otherNeesList = null;
        district_id = functionalNeedReportDTO.getDistrict_id();
        mandal_id = functionalNeedReportDTO.getMandal_id();
        village_id = functionalNeedReportDTO.getVillage_id();
        habitation_id = functionalNeedReportDTO.getHabitation_id();
        otherNeesList = new ArrayList<FunctionalNeedReportDTO>();
        try {
            Date fdate = new SimpleDateFormat("dd/MM/yyyy").parse(functionalNeedReportDTO.getFromdate());
            String fromdate = new SimpleDateFormat("MM/dd/yyyy").format(fdate);

            Date tdate = new SimpleDateFormat("dd/MM/yyyy").parse(functionalNeedReportDTO.getTodate());
            String todate = new SimpleDateFormat("MM/dd/yyyy").format(tdate);
            con = DBConnection.getConnection();
            if (district_id.equals("0")) {

                cstmt = con.prepareCall("{Call SP_HI_OTHERNEEDS_DISTRICTWISE_SN_ANALASISREPORT(?,?)}");
                cstmt.setString(1, fromdate);
                cstmt.setString(2, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO districtHIOtherNeedsDTO =
                            new FunctionalNeedReportDTO();
                    districtHIOtherNeedsDTO.setDistrict_id(rs.getString("DISTRICT_ID"));
                    districtHIOtherNeedsDTO.setMandal_id("a");
                    districtHIOtherNeedsDTO.setVillage_id("a");
                    districtHIOtherNeedsDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport()) {
                        districtHIOtherNeedsDTO.setDistrictName("<a href='./analysisReport.do?mode=mandalLevelReport&district_id=" + rs.getString("DISTRICT_ID") + 

"&typeofdisability=" + functionalNeedReportDTO.getTypeofdisability() + "&reportcategory=" + functionalNeedReportDTO.getReportcategory() + "&fromdate=" + 

functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate() + "'>" + rs.getString("DISTRICT_NAME") + "</a>");
                    } else {
                        districtHIOtherNeedsDTO.setDistrictName(rs.getString("DISTRICT_NAME"));
                    }
                    districtHIOtherNeedsDTO.setOtherNeedsHI(rs.getInt("OTHERNEEDS"));
                    otherNeesList.add(districtHIOtherNeedsDTO);

                }


            } else if (mandal_id.equals("0")) {
                cstmt = con.prepareCall("{Call SP_HI_OTHERNEEDS_MANDALWISE_SN_ANALASISREPORT(?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, fromdate);
                cstmt.setString(3, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO mandalHIOtherNeedsDTO =
                            new FunctionalNeedReportDTO();
                    mandalHIOtherNeedsDTO.setDistrict_id(district_id);
                    mandalHIOtherNeedsDTO.setMandal_id(rs.getString("MANDALID"));
                    mandalHIOtherNeedsDTO.setVillage_id("a");
                    mandalHIOtherNeedsDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport()) {
                        mandalHIOtherNeedsDTO.setMandalName("<a href='./analysisReport.do?mode=villageLevelReport&district_id=" + district_id + "&mandal_id=" + 

rs.getString("MANDALID") + "&typeofdisability=" + functionalNeedReportDTO.getTypeofdisability() + "&reportcategory=" + functionalNeedReportDTO.getReportcategory() + 

"&fromdate=" + functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate() + "'>" + rs.getString("MANDALNAME") + "</a>");
                    } else {
                        mandalHIOtherNeedsDTO.setMandalName(rs.getString("MANDALNAME"));
                    }
                    mandalHIOtherNeedsDTO.setOtherNeedsHI(rs.getInt("OTHERNEEDS"));
                    otherNeesList.add(mandalHIOtherNeedsDTO);

                }
            } else if (village_id.equals("0")) {
                cstmt = con.prepareCall("{Call SP_HI_OTHERNEEDS_VILLAGEWISE_SN_ANALASISREPORT(?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setString(3, fromdate);
                cstmt.setString(4, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO villageHIOtherNeedsDTO =
                            new FunctionalNeedReportDTO();
                    villageHIOtherNeedsDTO.setDistrict_id(district_id);
                    villageHIOtherNeedsDTO.setMandal_id(mandal_id);
                    villageHIOtherNeedsDTO.setVillage_id(rs.getString("VILLAGEID"));
                    villageHIOtherNeedsDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport()) {
                        villageHIOtherNeedsDTO.setVillageName("<a href='./analysisReport.do?mode=habitationLevelReport&district_id=" + district_id + "&mandal_id=" + 

mandal_id + "&village_id=" + rs.getString("VILLAGEID") + "&typeofdisability=" + functionalNeedReportDTO.getTypeofdisability() + "&reportcategory=" + 

functionalNeedReportDTO.getReportcategory() + "&fromdate=" + functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate() + "'>" + 

rs.getString("VILLAGENAME") + "</a>");
                    } else {
                        villageHIOtherNeedsDTO.setVillageName(rs.getString("VILLAGENAME"));
                    }
                    villageHIOtherNeedsDTO.setOtherNeedsHI(rs.getInt("OTHERNEEDS"));
                    otherNeesList.add(villageHIOtherNeedsDTO);

                }
            } else if (!village_id.equals("0")) {
                int getOtherNeedsHI = 0;
                cstmt = con.prepareCall("{Call SP_HI_OTHERNEEDS_HABITATIONWISE_SN_ANALASISREPORT(?,?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setString(3, village_id);
                cstmt.setString(4, fromdate);
                cstmt.setString(5, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO habitationHIOtherNeedsDTO =
                            new FunctionalNeedReportDTO();
                    habitationHIOtherNeedsDTO.setDistrict_id(district_id);
                    habitationHIOtherNeedsDTO.setMandal_id(mandal_id);
                    habitationHIOtherNeedsDTO.setVillage_id(village_id);
                    habitationHIOtherNeedsDTO.setHabitation_id(rs.getString("HABITATIONCODE").substring(10, 12));
                    habitationHIOtherNeedsDTO.setHabitationName(rs.getString("HABITATIONNAME"));
                    habitationHIOtherNeedsDTO.setOtherNeedsHI(rs.getInt("OTHERNEEDS"));
                    if ((district_id.equals("16") && mandal_id.equals("84") && village_id.equals("004")) && (rs.getString("HABITATIONCODE").substring(10, 12).equals

("00") || rs.getString("HABITATIONCODE").substring(10, 12).equals("01"))) {

                        if (rs.getString("HABITATIONCODE").substring(10, 12).equals("00")) {
                            getOtherNeedsHI = habitationHIOtherNeedsDTO.getOtherNeedsHI();

                        }
                        if (rs.getString("HABITATIONCODE").substring(10, 12).equals("01")) {

                            habitationHIOtherNeedsDTO.setOtherNeedsHI(getOtherNeedsHI + habitationHIOtherNeedsDTO.getOtherNeedsHI());

                            otherNeesList.add(habitationHIOtherNeedsDTO);
                        }
                    } else {
                        otherNeesList.add(habitationHIOtherNeedsDTO);
                    }

                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getOtherNeedsHI", "FunctionalNeedReportDAO", "DataBase");
            //DBConnection.rollbackConnection(con);
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getOtherNeedsHI");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getOtherNeedsHI", "FunctionalNeedReportDAO", "Code");
            //DBConnection.rollbackConnection(con);
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getOtherNeedsHI");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }
        return otherNeesList;
    }

    /**
     * this method fetches medical intervention information from database
     * @param ds
     * @param dailydisabilityandpercentagedto
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public ArrayList getMedicalInterventionMR(DataSource ds,
            FunctionalNeedReportDTO functionalNeedReportDTO)
            throws SADAREMDBException, SQLException {

        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        String district_id = null;
        String mandal_id = null;
        String village_id = null;
        String habitation_id = null;
        ArrayList<FunctionalNeedReportDTO> miList = null;
        district_id = functionalNeedReportDTO.getDistrict_id();
        mandal_id = functionalNeedReportDTO.getMandal_id();
        village_id = functionalNeedReportDTO.getVillage_id();
        habitation_id = functionalNeedReportDTO.getHabitation_id();
        miList = new ArrayList<FunctionalNeedReportDTO>();
        int total = 0;
        try {
            Date fdate = new SimpleDateFormat("dd/MM/yyyy").parse(functionalNeedReportDTO.getFromdate());
            String fromdate = new SimpleDateFormat("MM/dd/yyyy").format(fdate);

            Date tdate = new SimpleDateFormat("dd/MM/yyyy").parse(functionalNeedReportDTO.getTodate());
            String todate = new SimpleDateFormat("MM/dd/yyyy").format(tdate);
            con = DBConnection.getConnection();
            if (district_id.equals("0")) {

                cstmt = con.prepareCall("{Call SP_MRMI_DISTRICTWISE_SN_ANALASISREPORT(?,?)}");
                cstmt.setString(1, fromdate);
                cstmt.setString(2, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO districtMIDTO =
                            new FunctionalNeedReportDTO();
                    districtMIDTO.setDistrict_id(rs.getString("DISTRICT_ID"));
                    districtMIDTO.setMandal_id("a");
                    districtMIDTO.setVillage_id("a");
                    districtMIDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport()) {
                        districtMIDTO.setDistrictName("<a href='./analysisReport.do?mode=mandalLevelReport&district_id=" + rs.getString("DISTRICT_ID") + 

"&typeofdisability=" + functionalNeedReportDTO.getTypeofdisability() + "&reportcategory=" + functionalNeedReportDTO.getReportcategory() + "&fromdate=" + 

functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate() + "'>" + rs.getString("DISTRICT_NAME") + "</a>");
                    } else {
                        districtMIDTO.setDistrictName(rs.getString("DISTRICT_NAME"));
                    }
                    districtMIDTO.setSpeechTherapyBelowThreeYearsMR(rs.getInt("STBELOW3"));
                    districtMIDTO.setSpeechTherapyAboveThreeYearsMR(rs.getInt("STABOVE3"));
                    districtMIDTO.setBehaviourModificationPsychotherapyBelow(rs.getInt("BMBELOW3"));
                    districtMIDTO.setBehaviourModificationPsychotherapyAbove(rs.getInt("BMABOVE3"));
                    districtMIDTO.setSensoryIntegrationCognitiveDevelopment(rs.getInt("SI"));
                    districtMIDTO.setCognitiveBehaviourTherapy(rs.getInt("CBT"));
                    districtMIDTO.setParentFamilyIntervention(rs.getInt("PFI"));
                    districtMIDTO.setPhysiotherapyMR(rs.getInt("PT"));
                    districtMIDTO.setOccupationalTherapyMR(rs.getInt("OT"));

                    total = rs.getInt("STBELOW3") + rs.getInt("STABOVE3") + rs.getInt("BMBELOW3") + rs.getInt("BMABOVE3")
                            + rs.getInt("SI") + rs.getInt("CBT") + rs.getInt("PFI") + rs.getInt("PT") + rs.getInt("OT");
                    districtMIDTO.setTotal(total);
                    miList.add(districtMIDTO);

                }


            } else if (mandal_id.equals("0")) {
                cstmt = con.prepareCall("{Call SP_MRMI_MANDALWISE_SN_ANALASISREPORT(?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, fromdate);
                cstmt.setString(3, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO mandalMRMIDTO =
                            new FunctionalNeedReportDTO();
                    mandalMRMIDTO.setDistrict_id(district_id);
                    mandalMRMIDTO.setMandal_id(rs.getString("MANDALID"));
                    mandalMRMIDTO.setVillage_id("a");
                    mandalMRMIDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport()) {
                        mandalMRMIDTO.setMandalName("<a href='./analysisReport.do?mode=villageLevelReport&district_id=" + district_id + "&mandal_id=" + rs.getString

("MANDALID") + "&typeofdisability=" + functionalNeedReportDTO.getTypeofdisability() + "&reportcategory=" + functionalNeedReportDTO.getReportcategory() + "&fromdate=" + 

functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate() + "'>" + rs.getString("MANDALNAME") + "</a>");
                    } else {
                        mandalMRMIDTO.setMandalName(rs.getString("MANDALNAME"));
                    }
                    mandalMRMIDTO.setSpeechTherapyBelowThreeYearsMR(rs.getInt("STBELOW3"));
                    mandalMRMIDTO.setSpeechTherapyAboveThreeYearsMR(rs.getInt("STABOVE3"));
                    mandalMRMIDTO.setBehaviourModificationPsychotherapyBelow(rs.getInt("BMBELOW3"));
                    mandalMRMIDTO.setBehaviourModificationPsychotherapyAbove(rs.getInt("BMABOVE3"));
                    mandalMRMIDTO.setSensoryIntegrationCognitiveDevelopment(rs.getInt("SI"));
                    mandalMRMIDTO.setCognitiveBehaviourTherapy(rs.getInt("CBT"));
                    mandalMRMIDTO.setParentFamilyIntervention(rs.getInt("PFI"));
                    mandalMRMIDTO.setPhysiotherapyMR(rs.getInt("PT"));
                    mandalMRMIDTO.setOccupationalTherapyMR(rs.getInt("OT"));

                    total = rs.getInt("STBELOW3") + rs.getInt("STABOVE3") + rs.getInt("BMBELOW3") + rs.getInt("BMABOVE3")
                            + rs.getInt("SI") + rs.getInt("CBT") + rs.getInt("PFI") + rs.getInt("PT") + rs.getInt("OT");
                    mandalMRMIDTO.setTotal(total);
                    miList.add(mandalMRMIDTO);

                }
            } else if (village_id.equals("0")) {
                cstmt = con.prepareCall("{Call SP_MRMI_VILLAGEWISE_SN_ANALASISREPORT(?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setString(3, fromdate);
                cstmt.setString(4, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO villageMRMIDTO =
                            new FunctionalNeedReportDTO();
                    villageMRMIDTO.setDistrict_id(district_id);
                    villageMRMIDTO.setMandal_id(mandal_id);
                    villageMRMIDTO.setVillage_id(rs.getString("VILLAGEID"));
                    villageMRMIDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport()) {
                        villageMRMIDTO.setVillageName("<a href='./analysisReport.do?mode=habitationLevelReport&district_id=" + district_id + "&mandal_id=" + mandal_id 

+ "&village_id=" + rs.getString("VILLAGEID") + "&typeofdisability=" + functionalNeedReportDTO.getTypeofdisability() + "&reportcategory=" + 

functionalNeedReportDTO.getReportcategory() + "&fromdate=" + functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate() + "'>" + 

rs.getString("VILLAGENAME") + "</a>");
                    } else {
                        villageMRMIDTO.setVillageName(rs.getString("VILLAGENAME"));
                    }
                    villageMRMIDTO.setSpeechTherapyBelowThreeYearsMR(rs.getInt("STBELOW3"));
                    villageMRMIDTO.setSpeechTherapyAboveThreeYearsMR(rs.getInt("STABOVE3"));
                    villageMRMIDTO.setBehaviourModificationPsychotherapyBelow(rs.getInt("BMBELOW3"));
                    villageMRMIDTO.setBehaviourModificationPsychotherapyAbove(rs.getInt("BMABOVE3"));
                    villageMRMIDTO.setSensoryIntegrationCognitiveDevelopment(rs.getInt("SI"));
                    villageMRMIDTO.setCognitiveBehaviourTherapy(rs.getInt("CBT"));
                    villageMRMIDTO.setParentFamilyIntervention(rs.getInt("PFI"));
                    villageMRMIDTO.setPhysiotherapyMR(rs.getInt("PT"));
                    villageMRMIDTO.setOccupationalTherapyMR(rs.getInt("OT"));

                    total = rs.getInt("STBELOW3") + rs.getInt("STABOVE3") + rs.getInt("BMBELOW3") + rs.getInt("BMABOVE3")
                            + rs.getInt("SI") + rs.getInt("CBT") + rs.getInt("PFI") + rs.getInt("PT") + rs.getInt("OT");
                    villageMRMIDTO.setTotal(total);
                    miList.add(villageMRMIDTO);

                }
            } else if (!village_id.equals("0")) {
                int speechBelowThree = 0;
                int speechAboveThree = 0;
                int behaviourBelow = 0;
                int behaviourAbove = 0;
                int sensoryDevelopment = 0;
                int cognitiveBehaviourTherapy = 0;
                int parentFamilyIntervention = 0;
                int physiotherapyMR = 0;
                int occupationalMR = 0;
                int totl = 0;
                cstmt = con.prepareCall("{Call SP_MRMI_HABITATIONWISE_SN_ANALASISREPORT(?,?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setString(3, village_id);
                cstmt.setString(4, fromdate);
                cstmt.setString(5, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO habitationMRMIDTO =
                            new FunctionalNeedReportDTO();
                    habitationMRMIDTO.setDistrict_id(district_id);
                    habitationMRMIDTO.setMandal_id(mandal_id);
                    habitationMRMIDTO.setVillage_id(village_id);
                    habitationMRMIDTO.setHabitation_id(rs.getString("HABITATIONCODE").substring(10, 12));
                    habitationMRMIDTO.setHabitationName(rs.getString("HABITATIONNAME"));
                    habitationMRMIDTO.setSpeechTherapyBelowThreeYearsMR(rs.getInt("STBELOW3"));
                    habitationMRMIDTO.setSpeechTherapyAboveThreeYearsMR(rs.getInt("STABOVE3"));
                    habitationMRMIDTO.setBehaviourModificationPsychotherapyBelow(rs.getInt("BMBELOW3"));
                    habitationMRMIDTO.setBehaviourModificationPsychotherapyAbove(rs.getInt("BMABOVE3"));
                    habitationMRMIDTO.setSensoryIntegrationCognitiveDevelopment(rs.getInt("SI"));
                    habitationMRMIDTO.setCognitiveBehaviourTherapy(rs.getInt("CBT"));
                    habitationMRMIDTO.setParentFamilyIntervention(rs.getInt("PFI"));
                    habitationMRMIDTO.setPhysiotherapyMR(rs.getInt("PT"));
                    habitationMRMIDTO.setOccupationalTherapyMR(rs.getInt("OT"));

                    total = rs.getInt("STBELOW3") + rs.getInt("STABOVE3") + rs.getInt("BMBELOW3") + rs.getInt("BMABOVE3")
                            + rs.getInt("SI") + rs.getInt("CBT") + rs.getInt("PFI") + rs.getInt("PT") + rs.getInt("OT");
                    habitationMRMIDTO.setTotal(total);

                    if ((district_id.equals("16") && mandal_id.equals("84") && village_id.equals("004")) && (rs.getString("HABITATIONCODE").substring(10, 12).equals

("00") || rs.getString("HABITATIONCODE").substring(10, 12).equals("01"))) {

                        if (rs.getString("HABITATIONCODE").substring(10, 12).equals("00")) {
                            speechBelowThree = habitationMRMIDTO.getSpeechTherapyBelowThreeYearsMR();
                            speechAboveThree = habitationMRMIDTO.getSpeechTherapyAboveThreeYearsMR();
                            behaviourBelow = habitationMRMIDTO.getBehaviourModificationPsychotherapyBelow();
                            behaviourAbove = habitationMRMIDTO.getBehaviourModificationPsychotherapyAbove();
                            sensoryDevelopment = habitationMRMIDTO.getSensoryIntegrationCognitiveDevelopment();
                            cognitiveBehaviourTherapy = habitationMRMIDTO.getCognitiveBehaviourTherapy();
                            parentFamilyIntervention = habitationMRMIDTO.getParentFamilyIntervention();
                            physiotherapyMR = habitationMRMIDTO.getPhysiotherapyMR();
                            occupationalMR = habitationMRMIDTO.getOccupationalTherapyMR();
                            totl = habitationMRMIDTO.getTotal();

                        }
                        if (rs.getString("HABITATIONCODE").substring(10, 12).equals("01")) {

                            habitationMRMIDTO.setSpeechTherapyBelowThreeYearsMR(speechBelowThree + habitationMRMIDTO.getSpeechTherapyBelowThreeYearsMR());
                            habitationMRMIDTO.setSpeechTherapyAboveThreeYearsMR(speechAboveThree + habitationMRMIDTO.getSpeechTherapyAboveThreeYearsMR());
                            habitationMRMIDTO.setBehaviourModificationPsychotherapyBelow(behaviourBelow + habitationMRMIDTO.getBehaviourModificationPsychotherapyBelow

());
                            habitationMRMIDTO.setBehaviourModificationPsychotherapyAbove(behaviourAbove + habitationMRMIDTO.getBehaviourModificationPsychotherapyAbove

());
                            habitationMRMIDTO.setSensoryIntegrationCognitiveDevelopment(sensoryDevelopment + 

habitationMRMIDTO.getSensoryIntegrationCognitiveDevelopment());
                            habitationMRMIDTO.setCognitiveBehaviourTherapy(cognitiveBehaviourTherapy + habitationMRMIDTO.getCognitiveBehaviourTherapy());
                            habitationMRMIDTO.setParentFamilyIntervention(parentFamilyIntervention + habitationMRMIDTO.getParentFamilyIntervention());
                            habitationMRMIDTO.setPhysiotherapyMR(physiotherapyMR + habitationMRMIDTO.getPhysiotherapyMR());
                            habitationMRMIDTO.setOccupationalTherapyMR(occupationalMR + habitationMRMIDTO.getOccupationalTherapyMR());
                            habitationMRMIDTO.setTotal(totl + habitationMRMIDTO.getTotal());

                            miList.add(habitationMRMIDTO);
                        }
                    } else {

                        miList.add(habitationMRMIDTO);
                    }

                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMedicalInterventionMR", "FunctionalNeedReportDAO", "DataBase");
            //DBConnection.rollbackConnection(con);
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getMedicalInterventionMR");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMedicalInterventionMR", "FunctionalNeedReportDAO", "Code");
            //DBConnection.rollbackConnection(con);
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getMedicalInterventionMR");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }
        return miList;
    }

    public ArrayList getAssistiveAugmentativeDevicesMR(DataSource ds,
            FunctionalNeedReportDTO functionalNeedReportDTO)
            throws SADAREMDBException, SQLException {

        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        String district_id = null;
        String mandal_id = null;
        String village_id = null;
        String habitation_id = null;
        ArrayList<FunctionalNeedReportDTO> prothesisList = null;
        district_id = functionalNeedReportDTO.getDistrict_id();
        mandal_id = functionalNeedReportDTO.getMandal_id();
        village_id = functionalNeedReportDTO.getVillage_id();
        habitation_id = functionalNeedReportDTO.getHabitation_id();
        prothesisList = new ArrayList<FunctionalNeedReportDTO>();
        int total = 0;
        try {
            Date fdate = new SimpleDateFormat("dd/MM/yyyy").parse(functionalNeedReportDTO.getFromdate());
            String fromdate = new SimpleDateFormat("MM/dd/yyyy").format(fdate);

            Date tdate = new SimpleDateFormat("dd/MM/yyyy").parse(functionalNeedReportDTO.getTodate());
            String todate = new SimpleDateFormat("MM/dd/yyyy").format(tdate);
            con = DBConnection.getConnection();
            if (district_id.equals("0")) {

                cstmt = con.prepareCall("{Call SP_MRAD_DISTRICTWISE_SN_ANALASISREPORT(?,?)}");
                cstmt.setString(1, fromdate);
                cstmt.setString(2, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO districtMRADDTO =
                            new FunctionalNeedReportDTO();

                    districtMRADDTO.setDistrict_id(rs.getString("DISTRICT_ID"));
                    districtMRADDTO.setMandal_id("a");
                    districtMRADDTO.setVillage_id("a");
                    districtMRADDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport()) {
                        districtMRADDTO.setDistrictName("<a href='./analysisReport.do?mode=mandalLevelReport&district_id=" + rs.getString("DISTRICT_ID") + 

"&typeofdisability=" + functionalNeedReportDTO.getTypeofdisability() + "&reportcategory=" + functionalNeedReportDTO.getReportcategory() + "&fromdate=" + 

functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate() + "'>" + rs.getString("DISTRICT_NAME") + "</a>");
                    } else {
                        districtMRADDTO.setDistrictName(rs.getString("DISTRICT_NAME"));
                    }
                    districtMRADDTO.setLearningMaterials(rs.getInt("LM"));
                    districtMRADDTO.setSpecialSoftware(rs.getInt("SS"));
                    districtMRADDTO.setToys(rs.getInt("TOYS"));

                    total = rs.getInt("LM") + rs.getInt("SS") + rs.getInt("TOYS");
                    districtMRADDTO.setTotal(total);
                    prothesisList.add(districtMRADDTO);

                }


            } else if (mandal_id.equals("0")) {
                cstmt = con.prepareCall("{Call SP_MRAD_MANDALWISE_SN_ANALASISREPORT(?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, fromdate);
                cstmt.setString(3, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO mandalMRADDTO =
                            new FunctionalNeedReportDTO();

                    mandalMRADDTO.setDistrict_id(district_id);
                    mandalMRADDTO.setMandal_id(rs.getString("MANDALID"));
                    mandalMRADDTO.setVillage_id("a");
                    mandalMRADDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport()) {
                        mandalMRADDTO.setMandalName("<a href='./analysisReport.do?mode=villageLevelReport&district_id=" + district_id + "&mandal_id=" + rs.getString

("MANDALID") + "&typeofdisability=" + functionalNeedReportDTO.getTypeofdisability() + "&reportcategory=" + functionalNeedReportDTO.getReportcategory() + "&fromdate=" + 

functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate() + "'>" + rs.getString("MANDALNAME") + "</a>");
                    } else {
                        mandalMRADDTO.setMandalName(rs.getString("MANDALNAME"));

                    }
                    mandalMRADDTO.setLearningMaterials(rs.getInt("LM"));
                    mandalMRADDTO.setSpecialSoftware(rs.getInt("SS"));
                    mandalMRADDTO.setToys(rs.getInt("TOYS"));

                    total = rs.getInt("LM") + rs.getInt("SS") + rs.getInt("TOYS");
                    mandalMRADDTO.setTotal(total);
                    prothesisList.add(mandalMRADDTO);

                }
            } else if (village_id.equals("0")) {
                cstmt = con.prepareCall("{Call SP_MRAD_VILLAGEWISE_SN_ANALASISREPORT(?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setString(3, fromdate);
                cstmt.setString(4, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO villageMRADDTO =
                            new FunctionalNeedReportDTO();

                    villageMRADDTO.setDistrict_id(district_id);
                    villageMRADDTO.setMandal_id(mandal_id);
                    villageMRADDTO.setVillage_id(rs.getString("VILLAGEID"));
                    villageMRADDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport()) {
                        villageMRADDTO.setVillageName("<a href='./analysisReport.do?mode=habitationLevelReport&district_id=" + district_id + "&mandal_id=" + mandal_id 

+ "&village_id=" + rs.getString("VILLAGEID") + "&typeofdisability=" + functionalNeedReportDTO.getTypeofdisability() + "&reportcategory=" + 

functionalNeedReportDTO.getReportcategory() + "&fromdate=" + functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate() + "'>" + 

rs.getString("VILLAGENAME") + "</a>");
                    } else {
                        villageMRADDTO.setVillageName(rs.getString("VILLAGENAME"));
                    }
                    villageMRADDTO.setLearningMaterials(rs.getInt("LM"));
                    villageMRADDTO.setSpecialSoftware(rs.getInt("SS"));
                    villageMRADDTO.setToys(rs.getInt("TOYS"));

                    total = rs.getInt("LM") + rs.getInt("SS") + rs.getInt("TOYS");
                    villageMRADDTO.setTotal(total);
                    prothesisList.add(villageMRADDTO);

                }
            } else if (!village_id.equals("0")) {
                int learningMaterials = 0;
                int specialSoftware = 0;
                int toys = 0;
                int totl = 0;
                cstmt = con.prepareCall("{Call SP_MRAD_HABITATIONWISE_SN_ANALASISREPORT(?,?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setString(3, village_id);
                cstmt.setString(4, fromdate);
                cstmt.setString(5, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO habitationMRADDTO =
                            new FunctionalNeedReportDTO();

                    habitationMRADDTO.setDistrict_id(district_id);
                    habitationMRADDTO.setMandal_id(mandal_id);
                    habitationMRADDTO.setVillage_id(village_id);
                    habitationMRADDTO.setHabitation_id(rs.getString("HABITATIONCODE").substring(10, 12));
                    habitationMRADDTO.setHabitationName(rs.getString("HABITATIONNAME"));
                    habitationMRADDTO.setLearningMaterials(rs.getInt("LM"));
                    habitationMRADDTO.setSpecialSoftware(rs.getInt("SS"));
                    habitationMRADDTO.setToys(rs.getInt("TOYS"));

                    total = rs.getInt("LM") + rs.getInt("SS") + rs.getInt("TOYS");
                    habitationMRADDTO.setTotal(total);
                    if ((district_id.equals("16") && mandal_id.equals("84") && village_id.equals("004")) && (rs.getString("HABITATIONCODE").substring(10, 12).equals

("00") || rs.getString("HABITATIONCODE").substring(10, 12).equals("01"))) {

                        if (rs.getString("HABITATIONCODE").substring(10, 12).equals("00")) {
                            learningMaterials = habitationMRADDTO.getLearningMaterials();
                            specialSoftware = habitationMRADDTO.getSpecialSoftware();
                            toys = habitationMRADDTO.getToys();
                            totl = habitationMRADDTO.getTotal();

                        }
                        if (rs.getString("HABITATIONCODE").substring(10, 12).equals("01")) {

                            habitationMRADDTO.setLearningMaterials(learningMaterials + habitationMRADDTO.getLearningMaterials());
                            habitationMRADDTO.setSpecialSoftware(specialSoftware + habitationMRADDTO.getSpecialSoftware());
                            habitationMRADDTO.setToys(toys + habitationMRADDTO.getToys());
                            habitationMRADDTO.setTotal(totl + habitationMRADDTO.getTotal());

                            prothesisList.add(habitationMRADDTO);
                        }
                    } else {
                        prothesisList.add(habitationMRADDTO);
                    }

                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getAssistiveAugmentativeDevicesMR", "FunctionalNeedReportDAO", "DataBase");
           //con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getAssistiveAugmentativeDevicesMR");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getAssistiveAugmentativeDevicesMR", "FunctionalNeedReportDAO", "Code");
           //con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getAssistiveAugmentativeDevicesMR");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }
        return prothesisList;
    }

    public ArrayList getOtherNeedsMR(DataSource ds,  FunctionalNeedReportDTO functionalNeedReportDTO)  throws SADAREMDBException, SQLException 
    {

        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        String district_id = null;
        String mandal_id = null;
        String village_id = null;
        String habitation_id = null;
        ArrayList<FunctionalNeedReportDTO> otherNeesList = null;
        district_id = functionalNeedReportDTO.getDistrict_id();
        mandal_id = functionalNeedReportDTO.getMandal_id();
        village_id = functionalNeedReportDTO.getVillage_id();
        habitation_id = functionalNeedReportDTO.getHabitation_id();
        otherNeesList = new ArrayList<FunctionalNeedReportDTO>();
        try {
            Date fdate = new SimpleDateFormat("dd/MM/yyyy").parse(functionalNeedReportDTO.getFromdate());
            String fromdate = new SimpleDateFormat("MM/dd/yyyy").format(fdate);

            Date tdate = new SimpleDateFormat("dd/MM/yyyy").parse(functionalNeedReportDTO.getTodate());
            String todate = new SimpleDateFormat("MM/dd/yyyy").format(tdate);
            con = DBConnection.getConnection();
            if (district_id.equals("0")) {

                cstmt = con.prepareCall("{Call SP_MR_OTHERNEEDS_DISTRICTWISE_SN_ANALASISREPORT(?,?)}");
                cstmt.setString(1, fromdate);
                cstmt.setString(2, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO districtMROtherNeedsDTO =
                            new FunctionalNeedReportDTO();
                    districtMROtherNeedsDTO.setDistrict_id(rs.getString("DISTRICT_ID"));
                    districtMROtherNeedsDTO.setMandal_id("a");
                    districtMROtherNeedsDTO.setVillage_id("a");
                    districtMROtherNeedsDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport()) {
                        districtMROtherNeedsDTO.setDistrictName("<a href='./analysisReport.do?mode=mandalLevelReport&district_id=" + rs.getString("DISTRICT_ID") + 

"&typeofdisability=" + functionalNeedReportDTO.getTypeofdisability() + "&reportcategory=" + functionalNeedReportDTO.getReportcategory() + "&fromdate=" + 

functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate() + "'>" + rs.getString("DISTRICT_NAME") + "</a>");
                    } else {
                        districtMROtherNeedsDTO.setDistrictName(rs.getString("DISTRICT_NAME"));
                    }
                    districtMROtherNeedsDTO.setOtherNeedsMR(rs.getInt("OTHERNEEDS"));
                    otherNeesList.add(districtMROtherNeedsDTO);

                }


            } else if (mandal_id.equals("0")) {
                cstmt = con.prepareCall("{Call SP_MR_OTHERNEEDS_MANDALWISE_SN_ANALASISREPORT(?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, fromdate);
                cstmt.setString(3, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO mandalMROtherNeedsDTO =
                            new FunctionalNeedReportDTO();
                    mandalMROtherNeedsDTO.setDistrict_id(district_id);
                    mandalMROtherNeedsDTO.setMandal_id(rs.getString("MANDALID"));
                    mandalMROtherNeedsDTO.setVillage_id("a");
                    mandalMROtherNeedsDTO.setHabitation_id("a");
                    mandalMROtherNeedsDTO.setMandalName("<a href='./analysisReport.do?mode=villageLevelReport&district_id=" + district_id + "&mandal_id=" + 

rs.getString("MANDALID") + "&typeofdisability=" + functionalNeedReportDTO.getTypeofdisability() + "&reportcategory=" + functionalNeedReportDTO.getReportcategory() + 

"&fromdate=" + functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate() + "'>" + rs.getString("MANDALNAME") + "</a>");
                    mandalMROtherNeedsDTO.setOtherNeedsMR(rs.getInt("OTHERNEEDS"));
                    otherNeesList.add(mandalMROtherNeedsDTO);

                }
            } else if (village_id.equals("0")) {
                cstmt = con.prepareCall("{Call SP_MR_OTHERNEEDS_VILLAGEWISE_SN_ANALASISREPORT(?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setString(3, fromdate);
                cstmt.setString(4, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO villageMROtherNeedsDTO =
                            new FunctionalNeedReportDTO();
                    villageMROtherNeedsDTO.setDistrict_id(district_id);
                    villageMROtherNeedsDTO.setMandal_id(mandal_id);
                    villageMROtherNeedsDTO.setVillage_id(rs.getString("VILLAGEID"));
                    villageMROtherNeedsDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport()) {
                        villageMROtherNeedsDTO.setVillageName("<a href='./analysisReport.do?mode=habitationLevelReport&district_id=" + district_id + "&mandal_id=" + 

mandal_id + "&village_id=" + rs.getString("VILLAGEID") + "&typeofdisability=" + functionalNeedReportDTO.getTypeofdisability() + "&reportcategory=" + 

functionalNeedReportDTO.getReportcategory() + "&fromdate=" + functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate() + "'>" + 

rs.getString("VILLAGENAME") + "</a>");
                    } else {
                        villageMROtherNeedsDTO.setVillageName(rs.getString("VILLAGENAME"));
                    }
                    villageMROtherNeedsDTO.setOtherNeedsMR(rs.getInt("OTHERNEEDS"));
                    otherNeesList.add(villageMROtherNeedsDTO);

                }
            } else if (!village_id.equals("0")) {
                int otherNeedsMR = 0;
                cstmt = con.prepareCall("{Call SP_MR_OTHERNEEDS_HABITATIONWISE_SN_ANALASISREPORT(?,?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setString(3, village_id);
                cstmt.setString(4, fromdate);
                cstmt.setString(5, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO habitationMROtherNeedsDTO =
                            new FunctionalNeedReportDTO();
                    habitationMROtherNeedsDTO.setDistrict_id(district_id);
                    habitationMROtherNeedsDTO.setMandal_id(mandal_id);
                    habitationMROtherNeedsDTO.setVillage_id(village_id);
                    habitationMROtherNeedsDTO.setHabitation_id(rs.getString("HABITATIONCODE").substring(10, 12));
                    habitationMROtherNeedsDTO.setHabitationName(rs.getString("HABITATIONNAME"));
                    habitationMROtherNeedsDTO.setOtherNeedsMR(rs.getInt("OTHERNEEDS"));
                    if ((district_id.equals("16") && mandal_id.equals("84") && village_id.equals("004")) && (rs.getString("HABITATIONCODE").substring(10, 12).equals

("00") || rs.getString("HABITATIONCODE").substring(10, 12).equals("01"))) {

                        if (rs.getString("HABITATIONCODE").substring(10, 12).equals("00")) {
                            otherNeedsMR = habitationMROtherNeedsDTO.getOtherNeedsMR();

                        }
                        if (rs.getString("HABITATIONCODE").substring(10, 12).equals("01")) {

                            habitationMROtherNeedsDTO.setOtherNeedsMR(otherNeedsMR + habitationMROtherNeedsDTO.getOtherNeedsMR());

                            otherNeesList.add(habitationMROtherNeedsDTO);
                        }
                    } else {

                        otherNeesList.add(habitationMROtherNeedsDTO);
                    }

                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getOtherNeedsMR", "FunctionalNeedReportDAO", "DataBase");
           //con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getOtherNeedsMR");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getOtherNeedsMR", "FunctionalNeedReportDAO", "Code");
            //DBConnection.rollbackConnection(con);
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getOtherNeedsMR");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }
        return otherNeesList;
    }

    /**
     * this method fetches medical intervention information from database
     * @param ds
     * @param dailydisabilityandpercentagedto
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public ArrayList getMedicalInterventionMI(DataSource ds, FunctionalNeedReportDTO functionalNeedReportDTO) throws SADAREMDBException, SQLException
    {

        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        String district_id = null;
        String mandal_id = null;
        String village_id = null;
        String habitation_id = null;
        ArrayList<FunctionalNeedReportDTO> miList = null;
        district_id = functionalNeedReportDTO.getDistrict_id();
        mandal_id = functionalNeedReportDTO.getMandal_id();
        village_id = functionalNeedReportDTO.getVillage_id();
        habitation_id = functionalNeedReportDTO.getHabitation_id();
        miList = new ArrayList<FunctionalNeedReportDTO>();
        int total = 0;
        try {
            Date fdate = new SimpleDateFormat("dd/MM/yyyy").parse(functionalNeedReportDTO.getFromdate());
            String fromdate = new SimpleDateFormat("MM/dd/yyyy").format(fdate);

            Date tdate = new SimpleDateFormat("dd/MM/yyyy").parse(functionalNeedReportDTO.getTodate());
            String todate = new SimpleDateFormat("MM/dd/yyyy").format(tdate);
            con = DBConnection.getConnection();
            if (district_id.equals("0")) {

                cstmt = con.prepareCall("{Call SP_MIMI_DISTRICTWISE_SN_ANALASISREPORT(?,?)}");
                cstmt.setString(1, fromdate);
                cstmt.setString(2, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO districtMIDTO =
                            new FunctionalNeedReportDTO();
                    districtMIDTO.setDistrict_id(rs.getString("DISTRICT_ID"));
                    districtMIDTO.setMandal_id("a");
                    districtMIDTO.setVillage_id("a");
                    districtMIDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport()) {
                        districtMIDTO.setDistrictName("<a href='./analysisReport.do?mode=mandalLevelReport&district_id=" + rs.getString("DISTRICT_ID") + 

"&typeofdisability=" + functionalNeedReportDTO.getTypeofdisability() + "&reportcategory=" + functionalNeedReportDTO.getReportcategory() + "&fromdate=" + 

functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate() + "'>" + rs.getString("DISTRICT_NAME") + "</a>");
                    } else {
                        districtMIDTO.setDistrictName(rs.getString("DISTRICT_NAME"));
                    }
                    districtMIDTO.setPsychotherapyBehaviourBelowThreeYearsMI(rs.getInt("PBELOW3"));
                    districtMIDTO.setPsychotherapyBehaviourAboveThreeYearsMI(rs.getInt("PABOVE3"));
                    districtMIDTO.setSurgeryMI(rs.getInt("SURGERY"));
                    districtMIDTO.setAdmissionPsychiatric(rs.getInt("AP"));


                    total = rs.getInt("PBELOW3") + rs.getInt("PABOVE3") + rs.getInt("SURGERY") + rs.getInt("AP");
                    districtMIDTO.setTotal(total);
                    miList.add(districtMIDTO);

                }


            } else if (mandal_id.equals("0")) {
                cstmt = con.prepareCall("{Call SP_MIMI_MANDALWISE_SN_ANALASISREPORT(?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, fromdate);
                cstmt.setString(3, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO mandalMIDTO =
                            new FunctionalNeedReportDTO();
                    mandalMIDTO.setDistrict_id(district_id);
                    mandalMIDTO.setMandal_id(rs.getString("MANDALID"));
                    mandalMIDTO.setVillage_id("a");
                    mandalMIDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport()) {
                        mandalMIDTO.setMandalName("<a href='./analysisReport.do?mode=villageLevelReport&district_id=" + district_id + "&mandal_id=" + rs.getString

("MANDALID") + "&typeofdisability=" + functionalNeedReportDTO.getTypeofdisability() + "&reportcategory=" + functionalNeedReportDTO.getReportcategory() + "&fromdate=" + 

functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate() + "'>" + rs.getString("MANDALNAME") + "</a>");
                    } else {
                        mandalMIDTO.setMandalName(rs.getString("MANDALNAME"));
                    }
                    mandalMIDTO.setPsychotherapyBehaviourBelowThreeYearsMI(rs.getInt("PBELOW3"));
                    mandalMIDTO.setPsychotherapyBehaviourAboveThreeYearsMI(rs.getInt("PABOVE3"));
                    mandalMIDTO.setSurgeryMI(rs.getInt("SURGERY"));
                    mandalMIDTO.setAdmissionPsychiatric(rs.getInt("AP"));


                    total = rs.getInt("PBELOW3") + rs.getInt("PABOVE3") + rs.getInt("SURGERY") + rs.getInt("AP");
                    mandalMIDTO.setTotal(total);
                    miList.add(mandalMIDTO);

                }
            } else if (village_id.equals("0")) {
                cstmt = con.prepareCall("{Call SP_MIMI_VILLAGEWISE_SN_ANALASISREPORT(?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setString(3, fromdate);
                cstmt.setString(4, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO villageMIDTO =
                            new FunctionalNeedReportDTO();
                    villageMIDTO.setDistrict_id(district_id);
                    villageMIDTO.setMandal_id(mandal_id);
                    villageMIDTO.setVillage_id(rs.getString("VILLAGEID"));
                    villageMIDTO.setHabitation_id("a");

                    if (functionalNeedReportDTO.isReport()) {
                        villageMIDTO.setVillageName("<a href='./analysisReport.do?mode=habitationLevelReport&district_id=" + district_id + "&mandal_id=" + mandal_id + 

"&village_id=" + rs.getString("VILLAGEID") + "&typeofdisability=" + functionalNeedReportDTO.getTypeofdisability() + "&reportcategory=" + 

functionalNeedReportDTO.getReportcategory() + "&fromdate=" + functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate() + "'>" + 

rs.getString("VILLAGENAME") + "</a>");
                    } else {
                        villageMIDTO.setVillageName(rs.getString("VILLAGENAME"));
                    }
                    villageMIDTO.setPsychotherapyBehaviourBelowThreeYearsMI(rs.getInt("PBELOW3"));
                    villageMIDTO.setPsychotherapyBehaviourAboveThreeYearsMI(rs.getInt("PABOVE3"));
                    villageMIDTO.setSurgeryMI(rs.getInt("SURGERY"));
                    villageMIDTO.setAdmissionPsychiatric(rs.getInt("AP"));

                    total = rs.getInt("PBELOW3") + rs.getInt("PABOVE3") + rs.getInt("SURGERY") + rs.getInt("AP");
                    villageMIDTO.setTotal(total);
                    miList.add(villageMIDTO);

                }
            } else if (!village_id.equals("0")) {
                int pBelowThreeYearsMI = 0;
                int pAboveThreeYearsMI = 0;
                int surgeryMI = 0;
                int admissionPsychiatric = 0;
                int Totl = 0;
                cstmt = con.prepareCall("{Call SP_MIMI_HABITATIONWISE_SN_ANALASISREPORT(?,?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setString(3, village_id);
                cstmt.setString(4, fromdate);
                cstmt.setString(5, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO habitationMIDTO =
                            new FunctionalNeedReportDTO();
                    habitationMIDTO.setDistrict_id(district_id);
                    habitationMIDTO.setMandal_id(mandal_id);
                    habitationMIDTO.setVillage_id(village_id);
                    habitationMIDTO.setHabitation_id(rs.getString("HABITATIONCODE").substring(10, 12));
                    habitationMIDTO.setHabitationName(rs.getString("HABITATIONNAME"));
                    habitationMIDTO.setPsychotherapyBehaviourBelowThreeYearsMI(rs.getInt("PBELOW3"));
                    habitationMIDTO.setPsychotherapyBehaviourAboveThreeYearsMI(rs.getInt("PABOVE3"));
                    habitationMIDTO.setSurgeryMI(rs.getInt("SURGERY"));
                    habitationMIDTO.setAdmissionPsychiatric(rs.getInt("AP"));

                    total = rs.getInt("PBELOW3") + rs.getInt("PABOVE3") + rs.getInt("SURGERY") + rs.getInt("AP");
                    habitationMIDTO.setTotal(total);
                    if ((district_id.equals("16") && mandal_id.equals("84") && village_id.equals("004")) && (rs.getString("HABITATIONCODE").substring(10, 12).equals

("00") || rs.getString("HABITATIONCODE").substring(10, 12).equals("01"))) {

                        if (rs.getString("HABITATIONCODE").substring(10, 12).equals("00")) {
                            pBelowThreeYearsMI = habitationMIDTO.getPsychotherapyBehaviourBelowThreeYearsMI();
                            pAboveThreeYearsMI = habitationMIDTO.getPsychotherapyBehaviourAboveThreeYearsMI();
                            surgeryMI = habitationMIDTO.getSurgeryMI();
                            admissionPsychiatric = habitationMIDTO.getAdmissionPsychiatric();
                            Totl = habitationMIDTO.getTotal();

                        }
                        if (rs.getString("HABITATIONCODE").substring(10, 12).equals("01")) {

                            habitationMIDTO.setPsychotherapyBehaviourBelowThreeYearsMI(pBelowThreeYearsMI + habitationMIDTO.getPsychotherapyBehaviourBelowThreeYearsMI

());
                            habitationMIDTO.setPsychotherapyBehaviourAboveThreeYearsMI(pAboveThreeYearsMI + habitationMIDTO.getPsychotherapyBehaviourAboveThreeYearsMI

());
                            habitationMIDTO.setSurgeryMI(surgeryMI + habitationMIDTO.getSurgeryMI());
                            habitationMIDTO.setAdmissionPsychiatric(admissionPsychiatric + habitationMIDTO.getAdmissionPsychiatric());
                            habitationMIDTO.setTotal(Totl + habitationMIDTO.getTotal());

                            miList.add(habitationMIDTO);
                        }
                    } else {
                        miList.add(habitationMIDTO);
                    }

                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMedicalInterventionMI", "FunctionalNeedReportDAO", "DataBase");
          // con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getMedicalInterventionMI");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMedicalInterventionMI", "FunctionalNeedReportDAO", "Code");
           //con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getMedicalInterventionMI");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }
        return miList;
    }

    public ArrayList getOtherNeedsMI(DataSource ds, FunctionalNeedReportDTO functionalNeedReportDTO)  throws SADAREMDBException, SQLException
    {

        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        String district_id = null;
        String mandal_id = null;
        String village_id = null;
        String habitation_id = null;
        ArrayList<FunctionalNeedReportDTO> otherNeesList = null;
        district_id = functionalNeedReportDTO.getDistrict_id();
        mandal_id = functionalNeedReportDTO.getMandal_id();
        village_id = functionalNeedReportDTO.getVillage_id();
        habitation_id = functionalNeedReportDTO.getHabitation_id();
        otherNeesList = new ArrayList<FunctionalNeedReportDTO>();
        try 
        {
            Date fdate = new SimpleDateFormat("dd/MM/yyyy").parse(functionalNeedReportDTO.getFromdate());
            String fromdate = new SimpleDateFormat("MM/dd/yyyy").format(fdate);

            Date tdate = new SimpleDateFormat("dd/MM/yyyy").parse(functionalNeedReportDTO.getTodate());
            String todate = new SimpleDateFormat("MM/dd/yyyy").format(tdate);
            con = DBConnection.getConnection();
            if (district_id.equals("0")) {

                cstmt = con.prepareCall("{Call SP_MI_OTHERNEEDS_DISTRICTWISE_SN_ANALASISREPORT(?,?)}");
                cstmt.setString(1, fromdate);
                cstmt.setString(2, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO districtMIOtherNeedsDTO =
                            new FunctionalNeedReportDTO();
                    districtMIOtherNeedsDTO.setDistrict_id(rs.getString("DISTRICT_ID"));
                    districtMIOtherNeedsDTO.setMandal_id("a");
                    districtMIOtherNeedsDTO.setVillage_id("a");
                    districtMIOtherNeedsDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport()) {
                        districtMIOtherNeedsDTO.setDistrictName("<a href='./analysisReport.do?mode=mandalLevelReport&district_id=" + rs.getString("DISTRICT_ID") + 

"&typeofdisability=" + functionalNeedReportDTO.getTypeofdisability() + "&reportcategory=" + functionalNeedReportDTO.getReportcategory() + "&fromdate=" + 

functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate() + "'>" + rs.getString("DISTRICT_NAME") + "</a>");
                    } else {
                        districtMIOtherNeedsDTO.setDistrictName(rs.getString("DISTRICT_NAME"));
                    }
                    districtMIOtherNeedsDTO.setOtherNeedsMI(rs.getInt("OTHERNEEDS"));
                    otherNeesList.add(districtMIOtherNeedsDTO);

                }


            } else if (mandal_id.equals("0")) {
                cstmt = con.prepareCall("{Call SP_MI_OTHERNEEDS_MANDALWISE_SN_ANALASISREPORT(?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, fromdate);
                cstmt.setString(3, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO mandalMIOtherNeedsDTO =
                            new FunctionalNeedReportDTO();
                    mandalMIOtherNeedsDTO.setDistrict_id(district_id);
                    mandalMIOtherNeedsDTO.setMandal_id(rs.getString("MANDALID"));
                    mandalMIOtherNeedsDTO.setVillage_id("a");
                    mandalMIOtherNeedsDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport()) {
                        mandalMIOtherNeedsDTO.setMandalName("<a href='./analysisReport.do?mode=villageLevelReport&district_id=" + district_id + "&mandal_id=" + 

rs.getString("MANDALID") + "&typeofdisability=" + functionalNeedReportDTO.getTypeofdisability() + "&reportcategory=" + functionalNeedReportDTO.getReportcategory() + 

"&fromdate=" + functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate() + "'>" + rs.getString("MANDALNAME") + "</a>");
                    } else {
                        mandalMIOtherNeedsDTO.setMandalName(rs.getString("MANDALNAME"));
                    }
                    mandalMIOtherNeedsDTO.setOtherNeedsMI(rs.getInt("OTHERNEEDS"));
                    otherNeesList.add(mandalMIOtherNeedsDTO);

                }
            } else if (village_id.equals("0")) {
                cstmt = con.prepareCall("{Call SP_MI_OTHERNEEDS_VILLAGEWISE_SN_ANALASISREPORT(?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setString(3, fromdate);
                cstmt.setString(4, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO villageMIOtherNeedsDTO =
                            new FunctionalNeedReportDTO();
                    villageMIOtherNeedsDTO.setDistrict_id(district_id);
                    villageMIOtherNeedsDTO.setMandal_id(mandal_id);
                    villageMIOtherNeedsDTO.setVillage_id(rs.getString("VILLAGEID"));
                    villageMIOtherNeedsDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport()) {
                        villageMIOtherNeedsDTO.setVillageName("<a href='./analysisReport.do?mode=habitationLevelReport&district_id=" + district_id + "&mandal_id=" + 

mandal_id + "&village_id=" + rs.getString("VILLAGEID") + "&typeofdisability=" + functionalNeedReportDTO.getTypeofdisability() + "&reportcategory=" + 

functionalNeedReportDTO.getReportcategory() + "&fromdate=" + functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate() + "'>" + 

rs.getString("VILLAGENAME") + "</a>");
                    } else {
                        villageMIOtherNeedsDTO.setVillageName(rs.getString("VILLAGENAME"));
                    }
                    villageMIOtherNeedsDTO.setOtherNeedsMI(rs.getInt("OTHERNEEDS"));
                    otherNeesList.add(villageMIOtherNeedsDTO);

                }
            } else if (!village_id.equals("0")) {
                int otherNeedsMI = 0;
                cstmt = con.prepareCall("{Call SP_MI_OTHERNEEDS_HABITATIONWISE_SN_ANALASISREPORT(?,?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setString(3, village_id);
                cstmt.setString(4, fromdate);
                cstmt.setString(5, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO habitationMIOtherNeedsDTO =
                            new FunctionalNeedReportDTO();
                    habitationMIOtherNeedsDTO.setDistrict_id(district_id);
                    habitationMIOtherNeedsDTO.setMandal_id(mandal_id);
                    habitationMIOtherNeedsDTO.setVillage_id(village_id);
                    habitationMIOtherNeedsDTO.setHabitation_id(rs.getString("HABITATIONCODE").substring(10, 12));
                    habitationMIOtherNeedsDTO.setHabitationName(rs.getString("HABITATIONNAME"));
                    habitationMIOtherNeedsDTO.setOtherNeedsMI(rs.getInt("OTHERNEEDS"));

                    if ((district_id.equals("16") && mandal_id.equals("84") && village_id.equals("004")) && (rs.getString("HABITATIONCODE").substring(10, 12).equals

("00") || rs.getString("HABITATIONCODE").substring(10, 12).equals("01"))) {

                        if (rs.getString("HABITATIONCODE").substring(10, 12).equals("00")) {
                            otherNeedsMI = habitationMIOtherNeedsDTO.getOtherNeedsMI();

                        }
                        if (rs.getString("HABITATIONCODE").substring(10, 12).equals("01")) {

                            habitationMIOtherNeedsDTO.setOtherNeedsMI(otherNeedsMI + habitationMIOtherNeedsDTO.getOtherNeedsMI());
                            otherNeesList.add(habitationMIOtherNeedsDTO);
                        }
                    } else {
                        otherNeesList.add(habitationMIOtherNeedsDTO);
                    }

                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getOtherNeedsMI", "FunctionalNeedReportDAO", "DataBase");
            //DBConnection.rollbackConnection(con);
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getOtherNeedsMI");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getOtherNeedsMI", "FunctionalNeedReportDAO", "Code");
            //DBConnection.rollbackConnection(con);
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getOtherNeedsMI");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }
        return otherNeesList;
    }

    public ArrayList getCommonGeneralNeeds(DataSource ds,
            FunctionalNeedReportDTO functionalNeedReportDTO)
            throws SADAREMDBException, SQLException {

        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        String district_id = null;
        String mandal_id = null;
        String village_id = null;
        String habitation_id = null;
        ArrayList<FunctionalNeedReportDTO> commonGeneralNeedsList = null;
        district_id = functionalNeedReportDTO.getDistrict_id();
        mandal_id = functionalNeedReportDTO.getMandal_id();
        village_id = functionalNeedReportDTO.getVillage_id();
        habitation_id = functionalNeedReportDTO.getHabitation_id();
        int disabilityID = functionalNeedReportDTO.getTypeofdisability();
        int total = 0;
        commonGeneralNeedsList = new ArrayList<FunctionalNeedReportDTO>();
        try {
            Date fdate = new SimpleDateFormat("dd/MM/yyyy").parse(functionalNeedReportDTO.getFromdate());
            String fromdate = new SimpleDateFormat("MM/dd/yyyy").format(fdate);

            Date tdate = new SimpleDateFormat("dd/MM/yyyy").parse(functionalNeedReportDTO.getTodate());
            String todate = new SimpleDateFormat("MM/dd/yyyy").format(tdate);
            con = DBConnection.getConnection();
            if (district_id.equals("0")) {

                cstmt = con.prepareCall("{Call SP_DISTRICTWISE_GENERALNEEDSREPORT(?,?,?)}");
                cstmt.setInt(1, disabilityID);
                cstmt.setString(2, fromdate);
                cstmt.setString(3, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO commonDistrictGeneralNeedsDTO =
                            new FunctionalNeedReportDTO();
                    commonDistrictGeneralNeedsDTO.setDistrict_id(rs.getString("DISTRICT_ID"));
                    commonDistrictGeneralNeedsDTO.setMandal_id("a");
                    commonDistrictGeneralNeedsDTO.setVillage_id("a");
                    commonDistrictGeneralNeedsDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport()) {
                        commonDistrictGeneralNeedsDTO.setDistrictName("<a href='./generalNeedsReport.do?mode=mandalLevelReport&district_id=" + rs.getString

("DISTRICT_ID") + "&typeofdisability=" + disabilityID + "&fromdate=" + functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate() + "'>" 

+ rs.getString("DISTRICT_NAME") + "</a>");
                    } else {
                        commonDistrictGeneralNeedsDTO.setDistrictName(rs.getString("DISTRICT_NAME"));
                    }
                    commonDistrictGeneralNeedsDTO.setEarlyEducationService(rs.getInt("EARLYEDUCATION"));
                    commonDistrictGeneralNeedsDTO.setHomeBased(rs.getInt("HOMEBASED"));
                    commonDistrictGeneralNeedsDTO.setSpecialEducation(rs.getInt("SPECIALEDUCATION"));
                    commonDistrictGeneralNeedsDTO.setInclusiveEducation(rs.getInt("INCLUSIVEEDUCATION"));
                    commonDistrictGeneralNeedsDTO.setEmploymentPublicPvtSector(rs.getInt("PUBLICEMPLOYMENT"));
                    commonDistrictGeneralNeedsDTO.setSelfEmployment(rs.getInt("SELFEMPLOYMENT"));
                    commonDistrictGeneralNeedsDTO.setIndividual(rs.getInt("INDIVIDUAL"));
                    commonDistrictGeneralNeedsDTO.setFamily(rs.getInt("FAMILY"));
                    commonDistrictGeneralNeedsDTO.setOtherGeneralNeeds(rs.getInt("OTHERNEEDS"));
                    total = rs.getInt("EARLYEDUCATION") + rs.getInt("HOMEBASED") + rs.getInt("SPECIALEDUCATION")
                            + rs.getInt("INCLUSIVEEDUCATION") + rs.getInt("PUBLICEMPLOYMENT") + rs.getInt("SELFEMPLOYMENT")
                            + rs.getInt("INDIVIDUAL") + rs.getInt("FAMILY") + rs.getInt("OTHERNEEDS");
                    commonDistrictGeneralNeedsDTO.setTotal(total);
                    commonGeneralNeedsList.add(commonDistrictGeneralNeedsDTO);

                }


            } else if (mandal_id.equals("0")) {
                cstmt = con.prepareCall("{Call SP_MANDALWISE_GENERALNEEDSREPORT(?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setInt(2, disabilityID);
                cstmt.setString(3, fromdate);
                cstmt.setString(4, todate);

                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO commonMandalGeneralNeedsDTO =
                            new FunctionalNeedReportDTO();
                    commonMandalGeneralNeedsDTO.setDistrict_id(district_id);
                    commonMandalGeneralNeedsDTO.setMandal_id(rs.getString("MANDALID"));
                    commonMandalGeneralNeedsDTO.setVillage_id("a");
                    commonMandalGeneralNeedsDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport()) {
                        commonMandalGeneralNeedsDTO.setMandalName("<a href='./generalNeedsReport.do?mode=villageLevelReport&district_id=" + district_id + "&mandal_id=" 

+ rs.getString("MANDALID") + "&typeofdisability=" + disabilityID + "&fromdate=" + functionalNeedReportDTO.getFromdate() + "&todate=" + 

functionalNeedReportDTO.getTodate() + "'>" + rs.getString("MANDALNAME") + "</a>");
                    } else {
                        commonMandalGeneralNeedsDTO.setMandalName(rs.getString("MANDALNAME"));
                    }
                    commonMandalGeneralNeedsDTO.setEarlyEducationService(rs.getInt("EARLYEDUCATION"));
                    commonMandalGeneralNeedsDTO.setHomeBased(rs.getInt("HOMEBASED"));
                    commonMandalGeneralNeedsDTO.setSpecialEducation(rs.getInt("SPECIALEDUCATION"));
                    commonMandalGeneralNeedsDTO.setInclusiveEducation(rs.getInt("INCLUSIVEEDUCATION"));
                    commonMandalGeneralNeedsDTO.setEmploymentPublicPvtSector(rs.getInt("PUBLICEMPLOYMENT"));
                    commonMandalGeneralNeedsDTO.setSelfEmployment(rs.getInt("SELFEMPLOYMENT"));
                    commonMandalGeneralNeedsDTO.setIndividual(rs.getInt("INDIVIDUAL"));
                    commonMandalGeneralNeedsDTO.setFamily(rs.getInt("FAMILY"));
                    commonMandalGeneralNeedsDTO.setOtherGeneralNeeds(rs.getInt("OTHERNEEDS"));
                    total = rs.getInt("EARLYEDUCATION") + rs.getInt("HOMEBASED") + rs.getInt("SPECIALEDUCATION")
                            + rs.getInt("INCLUSIVEEDUCATION") + rs.getInt("PUBLICEMPLOYMENT") + rs.getInt("SELFEMPLOYMENT")
                            + rs.getInt("INDIVIDUAL") + rs.getInt("FAMILY") + rs.getInt("OTHERNEEDS");
                    commonMandalGeneralNeedsDTO.setTotal(total);
                    commonGeneralNeedsList.add(commonMandalGeneralNeedsDTO);

                }
            } else if (village_id.equals("0")) {
                cstmt = con.prepareCall("{Call SP_VILLAGEWISE_GENERALNEEDSREPORT(?,?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setInt(3, disabilityID);
                cstmt.setString(4, fromdate);
                cstmt.setString(5, todate);

                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO commonVillageGeneralNeedsDTO =
                            new FunctionalNeedReportDTO();
                    commonVillageGeneralNeedsDTO.setDistrict_id(district_id);
                    commonVillageGeneralNeedsDTO.setMandal_id(mandal_id);
                    commonVillageGeneralNeedsDTO.setVillage_id(rs.getString("VILLAGEID"));
                    commonVillageGeneralNeedsDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport()) {
                        commonVillageGeneralNeedsDTO.setVillageName("<a href='./generalNeedsReport.do?mode=habitationLevelReport&district_id=" + district_id + 

"&mandal_id=" + mandal_id + "&village_id=" + rs.getString("VILLAGEID") + "&typeofdisability=" + disabilityID + "&fromdate=" + functionalNeedReportDTO.getFromdate() + 

"&todate=" + functionalNeedReportDTO.getTodate() + "'>" + rs.getString("VILLAGENAME") + "</a>");
                    } else {
                        commonVillageGeneralNeedsDTO.setVillageName(rs.getString("VILLAGENAME"));
                    }
                    commonVillageGeneralNeedsDTO.setEarlyEducationService(rs.getInt("EARLYEDUCATION"));
                    commonVillageGeneralNeedsDTO.setHomeBased(rs.getInt("HOMEBASED"));
                    commonVillageGeneralNeedsDTO.setSpecialEducation(rs.getInt("SPECIALEDUCATION"));
                    commonVillageGeneralNeedsDTO.setInclusiveEducation(rs.getInt("INCLUSIVEEDUCATION"));
                    commonVillageGeneralNeedsDTO.setEmploymentPublicPvtSector(rs.getInt("PUBLICEMPLOYMENT"));
                    commonVillageGeneralNeedsDTO.setSelfEmployment(rs.getInt("SELFEMPLOYMENT"));
                    commonVillageGeneralNeedsDTO.setIndividual(rs.getInt("INDIVIDUAL"));
                    commonVillageGeneralNeedsDTO.setFamily(rs.getInt("FAMILY"));
                    commonVillageGeneralNeedsDTO.setOtherGeneralNeeds(rs.getInt("OTHERNEEDS"));
                    total = rs.getInt("EARLYEDUCATION") + rs.getInt("HOMEBASED") + rs.getInt("SPECIALEDUCATION")
                            + rs.getInt("INCLUSIVEEDUCATION") + rs.getInt("PUBLICEMPLOYMENT") + rs.getInt("SELFEMPLOYMENT")
                            + rs.getInt("INDIVIDUAL") + rs.getInt("FAMILY") + rs.getInt("OTHERNEEDS");
                    commonVillageGeneralNeedsDTO.setTotal(total);
                    commonGeneralNeedsList.add(commonVillageGeneralNeedsDTO);

                }
            } else if (!village_id.equals("0")) {
                int educationService = 0;
                int homeBased = 0;
                int specialEducation = 0;
                int inclusiveEducation = 0;
                int employmentPublicPvtSector = 0;
                int selfEmployment = 0;
                int individual = 0;
                int family = 0;
                int otherGeneralNeeds = 0;
                int totl = 0;
                cstmt = con.prepareCall("{Call SP_HABITATIONWISE_GENERALNEEDSREPORT(?,?,?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setString(3, village_id);
                cstmt.setInt(4, disabilityID);
                cstmt.setString(5, fromdate);
                cstmt.setString(6, todate);

                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO commonHabitationGeneralNeedsDTO =
                            new FunctionalNeedReportDTO();
                    commonHabitationGeneralNeedsDTO.setDistrict_id(district_id);
                    commonHabitationGeneralNeedsDTO.setMandal_id(mandal_id);
                    commonHabitationGeneralNeedsDTO.setVillage_id(village_id);
                    commonHabitationGeneralNeedsDTO.setHabitation_id(rs.getString("HABITATIONCODE").substring(10, 12));
                    commonHabitationGeneralNeedsDTO.setHabitationName(rs.getString("HABITATIONNAME"));
                    commonHabitationGeneralNeedsDTO.setEarlyEducationService(rs.getInt("EARLYEDUCATION"));
                    commonHabitationGeneralNeedsDTO.setHomeBased(rs.getInt("HOMEBASED"));
                    commonHabitationGeneralNeedsDTO.setSpecialEducation(rs.getInt("SPECIALEDUCATION"));
                    commonHabitationGeneralNeedsDTO.setInclusiveEducation(rs.getInt("INCLUSIVEEDUCATION"));
                    commonHabitationGeneralNeedsDTO.setEmploymentPublicPvtSector(rs.getInt("PUBLICEMPLOYMENT"));
                    commonHabitationGeneralNeedsDTO.setSelfEmployment(rs.getInt("SELFEMPLOYMENT"));
                    commonHabitationGeneralNeedsDTO.setIndividual(rs.getInt("INDIVIDUAL"));
                    commonHabitationGeneralNeedsDTO.setFamily(rs.getInt("FAMILY"));
                    commonHabitationGeneralNeedsDTO.setOtherGeneralNeeds(rs.getInt("OTHERNEEDS"));
                    total = rs.getInt("EARLYEDUCATION") + rs.getInt("HOMEBASED") + rs.getInt("SPECIALEDUCATION")
                            + rs.getInt("INCLUSIVEEDUCATION") + rs.getInt("PUBLICEMPLOYMENT") + rs.getInt("SELFEMPLOYMENT")
                            + rs.getInt("INDIVIDUAL") + rs.getInt("FAMILY") + rs.getInt("OTHERNEEDS");
                    commonHabitationGeneralNeedsDTO.setTotal(total);
                    if ((district_id.equals("16") && mandal_id.equals("84") && village_id.equals("004")) && (rs.getString("HABITATIONCODE").substring(10, 12).equals

("00") || rs.getString("HABITATIONCODE").substring(10, 12).equals("01"))) {

                        if (rs.getString("HABITATIONCODE").substring(10, 12).equals("00")) {
                            educationService = commonHabitationGeneralNeedsDTO.getEarlyEducationService();
                            homeBased = commonHabitationGeneralNeedsDTO.getHomeBased();
                            specialEducation = commonHabitationGeneralNeedsDTO.getSpecialEducation();
                            inclusiveEducation = commonHabitationGeneralNeedsDTO.getInclusiveEducation();
                            employmentPublicPvtSector = commonHabitationGeneralNeedsDTO.getEmploymentPublicPvtSector();
                            selfEmployment = commonHabitationGeneralNeedsDTO.getSelfEmployment();
                            individual = commonHabitationGeneralNeedsDTO.getIndividual();
                            family = commonHabitationGeneralNeedsDTO.getFamily();
                            otherGeneralNeeds = commonHabitationGeneralNeedsDTO.getOtherGeneralNeeds();
                            totl = commonHabitationGeneralNeedsDTO.getTotal();

                        }
                        if (rs.getString("HABITATIONCODE").substring(10, 12).equals("01")) {

                            commonHabitationGeneralNeedsDTO.setEarlyEducationService(educationService + commonHabitationGeneralNeedsDTO.getEarlyEducationService());
                            commonHabitationGeneralNeedsDTO.setHomeBased(homeBased + commonHabitationGeneralNeedsDTO.getHomeBased());
                            commonHabitationGeneralNeedsDTO.setSpecialEducation(specialEducation + commonHabitationGeneralNeedsDTO.getSpecialEducation());
                            commonHabitationGeneralNeedsDTO.setInclusiveEducation(inclusiveEducation + commonHabitationGeneralNeedsDTO.getInclusiveEducation());
                            commonHabitationGeneralNeedsDTO.setEmploymentPublicPvtSector(employmentPublicPvtSector + 

commonHabitationGeneralNeedsDTO.getEmploymentPublicPvtSector());
                            commonHabitationGeneralNeedsDTO.setSelfEmployment(selfEmployment + commonHabitationGeneralNeedsDTO.getSelfEmployment());
                            commonHabitationGeneralNeedsDTO.setIndividual(individual + commonHabitationGeneralNeedsDTO.getIndividual());
                            commonHabitationGeneralNeedsDTO.setFamily(family + commonHabitationGeneralNeedsDTO.getFamily());
                            commonHabitationGeneralNeedsDTO.setOtherGeneralNeeds(otherGeneralNeeds + commonHabitationGeneralNeedsDTO.getOtherGeneralNeeds());
                            commonHabitationGeneralNeedsDTO.setTotal(totl + commonHabitationGeneralNeedsDTO.getTotal());

                            commonGeneralNeedsList.add(commonHabitationGeneralNeedsDTO);
                        }
                    } else {

                        commonGeneralNeedsList.add(commonHabitationGeneralNeedsDTO);
                    }

                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCommonGeneralNeeds", "FunctionalNeedReportDAO", "DataBase");
            //DBConnection.rollbackConnection(con);
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getCommonGeneralNeeds");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCommonGeneralNeeds", "FunctionalNeedReportDAO", "Code");
            //DBConnection.rollbackConnection(con);
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getCommonGeneralNeeds");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }
        return commonGeneralNeedsList;
    }

    public ArrayList getMRGeneralNeeds(DataSource ds,
            FunctionalNeedReportDTO functionalNeedReportDTO)
            throws SADAREMDBException, SQLException {

        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        String district_id = null;
        String mandal_id = null;
        String village_id = null;
        String habitation_id = null;
        ArrayList<FunctionalNeedReportDTO> mrGeneralNeedsList = null;
        district_id = functionalNeedReportDTO.getDistrict_id();
        mandal_id = functionalNeedReportDTO.getMandal_id();
        village_id = functionalNeedReportDTO.getVillage_id();
        habitation_id = functionalNeedReportDTO.getHabitation_id();
        int disabilityID = functionalNeedReportDTO.getTypeofdisability();
        int total = 0;
        mrGeneralNeedsList = new ArrayList<FunctionalNeedReportDTO>();
        try {
            Date fdate = new SimpleDateFormat("dd/MM/yyyy").parse(functionalNeedReportDTO.getFromdate());
            String fromdate = new SimpleDateFormat("MM/dd/yyyy").format(fdate);

            Date tdate = new SimpleDateFormat("dd/MM/yyyy").parse(functionalNeedReportDTO.getTodate());
            String todate = new SimpleDateFormat("MM/dd/yyyy").format(tdate);
            con = DBConnection.getConnection();
            if (district_id.equals("0")) {

                cstmt = con.prepareCall("{Call SP_DISTRICTWISE_GENERALNEEDSREPORT(?,?,?)}");
                cstmt.setInt(1, disabilityID);
                cstmt.setString(2, fromdate);
                cstmt.setString(3, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO mrDistrictGeneralNeedsDTO =
                            new FunctionalNeedReportDTO();
                    mrDistrictGeneralNeedsDTO.setDistrict_id(rs.getString("DISTRICT_ID"));
                    mrDistrictGeneralNeedsDTO.setMandal_id("a");
                    mrDistrictGeneralNeedsDTO.setVillage_id("a");
                    mrDistrictGeneralNeedsDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport()) {
                        mrDistrictGeneralNeedsDTO.setDistrictName("<a href='./generalNeedsReport.do?mode=mandalLevelReport&district_id=" + rs.getString("DISTRICT_ID") 

+ "&typeofdisability=" + disabilityID + "&fromdate=" + functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate() + "'>" + rs.getString

("DISTRICT_NAME") + "</a>");
                    } else {
                        mrDistrictGeneralNeedsDTO.setDistrictName(rs.getString("DISTRICT_NAME"));
                    }
                    mrDistrictGeneralNeedsDTO.setEarlyEducationService(rs.getInt("EARLYEDUCATION"));
                    mrDistrictGeneralNeedsDTO.setHomeBased(rs.getInt("HOMEBASED"));
                    mrDistrictGeneralNeedsDTO.setSpecialEducation(rs.getInt("SPECIALEDUCATION"));
                    mrDistrictGeneralNeedsDTO.setInclusiveEducation(rs.getInt("INCLUSIVEEDUCATION"));
                    mrDistrictGeneralNeedsDTO.setEmploymentPublicPvtSector(rs.getInt("PUBLICEMPLOYMENT"));
                    mrDistrictGeneralNeedsDTO.setSelfEmployment(rs.getInt("SELFEMPLOYMENT"));
                    mrDistrictGeneralNeedsDTO.setIndividual(rs.getInt("INDIVIDUAL"));
                    mrDistrictGeneralNeedsDTO.setFamily(rs.getInt("FAMILY"));
                    mrDistrictGeneralNeedsDTO.setLegalGurdian(rs.getInt("LEGALGUARDIAN"));
                    mrDistrictGeneralNeedsDTO.setOtherGeneralNeeds(rs.getInt("OTHERNEEDS"));
                    total = rs.getInt("EARLYEDUCATION") + rs.getInt("HOMEBASED") + rs.getInt("SPECIALEDUCATION")
                            + rs.getInt("INCLUSIVEEDUCATION") + rs.getInt("PUBLICEMPLOYMENT") + rs.getInt("SELFEMPLOYMENT")
                            + rs.getInt("INDIVIDUAL") + rs.getInt("FAMILY") + rs.getInt("LEGALGUARDIAN")
                            + rs.getInt("OTHERNEEDS");
                    mrDistrictGeneralNeedsDTO.setTotal(total);
                    mrGeneralNeedsList.add(mrDistrictGeneralNeedsDTO);

                }


            } else if (mandal_id.equals("0")) {
                cstmt = con.prepareCall("{Call SP_MANDALWISE_GENERALNEEDSREPORT(?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setInt(2, disabilityID);
                cstmt.setString(3, fromdate);
                cstmt.setString(4, todate);

                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO mrMandalGeneralNeedsDTO =
                            new FunctionalNeedReportDTO();
                    mrMandalGeneralNeedsDTO.setDistrict_id(district_id);
                    mrMandalGeneralNeedsDTO.setMandal_id(rs.getString("MANDALID"));
                    mrMandalGeneralNeedsDTO.setVillage_id("a");
                    mrMandalGeneralNeedsDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport()) {
                        mrMandalGeneralNeedsDTO.setMandalName("<a href='./generalNeedsReport.do?mode=villageLevelReport&district_id=" + district_id + "&mandal_id=" + 

rs.getString("MANDALID") + "&typeofdisability=" + disabilityID + "&fromdate=" + functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate

() + "'>" + rs.getString("MANDALNAME") + "</a>");
                    } else {
                        mrMandalGeneralNeedsDTO.setMandalName(rs.getString("MANDALNAME"));
                    }
                    mrMandalGeneralNeedsDTO.setEarlyEducationService(rs.getInt("EARLYEDUCATION"));
                    mrMandalGeneralNeedsDTO.setHomeBased(rs.getInt("HOMEBASED"));
                    mrMandalGeneralNeedsDTO.setSpecialEducation(rs.getInt("SPECIALEDUCATION"));
                    mrMandalGeneralNeedsDTO.setInclusiveEducation(rs.getInt("INCLUSIVEEDUCATION"));
                    mrMandalGeneralNeedsDTO.setEmploymentPublicPvtSector(rs.getInt("PUBLICEMPLOYMENT"));
                    mrMandalGeneralNeedsDTO.setSelfEmployment(rs.getInt("SELFEMPLOYMENT"));
                    mrMandalGeneralNeedsDTO.setIndividual(rs.getInt("INDIVIDUAL"));
                    mrMandalGeneralNeedsDTO.setFamily(rs.getInt("FAMILY"));
                    mrMandalGeneralNeedsDTO.setLegalGurdian(rs.getInt("LEGALGUARDIAN"));
                    mrMandalGeneralNeedsDTO.setOtherGeneralNeeds(rs.getInt("OTHERNEEDS"));
                    total = rs.getInt("EARLYEDUCATION") + rs.getInt("HOMEBASED") + rs.getInt("SPECIALEDUCATION")
                            + rs.getInt("INCLUSIVEEDUCATION") + rs.getInt("PUBLICEMPLOYMENT") + rs.getInt("SELFEMPLOYMENT")
                            + rs.getInt("INDIVIDUAL") + rs.getInt("FAMILY") + rs.getInt("LEGALGUARDIAN")
                            + rs.getInt("OTHERNEEDS");
                    mrMandalGeneralNeedsDTO.setTotal(total);
                    mrGeneralNeedsList.add(mrMandalGeneralNeedsDTO);

                }
            } else if (village_id.equals("0")) {
                cstmt = con.prepareCall("{Call SP_VILLAGEWISE_GENERALNEEDSREPORT(?,?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setInt(3, disabilityID);
                cstmt.setString(4, fromdate);
                cstmt.setString(5, todate);

                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO mrVillageGeneralNeedsDTO =
                            new FunctionalNeedReportDTO();
                    mrVillageGeneralNeedsDTO.setDistrict_id(district_id);
                    mrVillageGeneralNeedsDTO.setMandal_id(mandal_id);
                    mrVillageGeneralNeedsDTO.setVillage_id(rs.getString("VILLAGEID"));
                    mrVillageGeneralNeedsDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport()) {
                        mrVillageGeneralNeedsDTO.setVillageName("<a href='./generalNeedsReport.do?mode=habitationLevelReport&district_id=" + district_id + 

"&mandal_id=" + mandal_id + "&village_id=" + rs.getString("VILLAGEID") + "&typeofdisability=" + disabilityID + "&fromdate=" + functionalNeedReportDTO.getFromdate() + 

"&todate=" + functionalNeedReportDTO.getTodate() + "'>" + rs.getString("VILLAGENAME") + "</a>");
                    } else {
                        mrVillageGeneralNeedsDTO.setVillageName(rs.getString("VILLAGENAME"));
                    }
                    mrVillageGeneralNeedsDTO.setEarlyEducationService(rs.getInt("EARLYEDUCATION"));
                    mrVillageGeneralNeedsDTO.setHomeBased(rs.getInt("HOMEBASED"));
                    mrVillageGeneralNeedsDTO.setSpecialEducation(rs.getInt("SPECIALEDUCATION"));
                    mrVillageGeneralNeedsDTO.setInclusiveEducation(rs.getInt("INCLUSIVEEDUCATION"));
                    mrVillageGeneralNeedsDTO.setEmploymentPublicPvtSector(rs.getInt("PUBLICEMPLOYMENT"));
                    mrVillageGeneralNeedsDTO.setSelfEmployment(rs.getInt("SELFEMPLOYMENT"));
                    mrVillageGeneralNeedsDTO.setIndividual(rs.getInt("INDIVIDUAL"));
                    mrVillageGeneralNeedsDTO.setFamily(rs.getInt("FAMILY"));
                    mrVillageGeneralNeedsDTO.setLegalGurdian(rs.getInt("LEGALGUARDIAN"));
                    mrVillageGeneralNeedsDTO.setOtherGeneralNeeds(rs.getInt("OTHERNEEDS"));
                    total = rs.getInt("EARLYEDUCATION") + rs.getInt("HOMEBASED") + rs.getInt("SPECIALEDUCATION")
                            + rs.getInt("INCLUSIVEEDUCATION") + rs.getInt("PUBLICEMPLOYMENT") + rs.getInt("SELFEMPLOYMENT")
                            + rs.getInt("INDIVIDUAL") + rs.getInt("FAMILY") + rs.getInt("LEGALGUARDIAN")
                            + rs.getInt("OTHERNEEDS");
                    mrVillageGeneralNeedsDTO.setTotal(total);
                    mrGeneralNeedsList.add(mrVillageGeneralNeedsDTO);

                }
            } else if (!village_id.equals("0")) {
                int educationService = 0;
                int homeBased = 0;
                int specialEducation = 0;
                int inclusiveEducation = 0;
                int employmentPublicPvtSector = 0;
                int selfEmployment = 0;
                int individual = 0;
                int family = 0;
                int legalGurdian = 0;
                int otherGeneralNeeds = 0;
                int totl = 0;
                cstmt = con.prepareCall("{Call SP_HABITATIONWISE_GENERALNEEDSREPORT(?,?,?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setString(3, village_id);
                cstmt.setInt(4, disabilityID);
                cstmt.setString(5, fromdate);
                cstmt.setString(6, todate);

                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO mrHabitationGeneralNeedsDTO =
                            new FunctionalNeedReportDTO();
                    mrHabitationGeneralNeedsDTO.setDistrict_id(district_id);
                    mrHabitationGeneralNeedsDTO.setMandal_id(mandal_id);
                    mrHabitationGeneralNeedsDTO.setVillage_id(village_id);
                    mrHabitationGeneralNeedsDTO.setHabitation_id(rs.getString("HABITATIONCODE").substring(10, 12));
                    mrHabitationGeneralNeedsDTO.setHabitationName(rs.getString("HABITATIONNAME"));
                    mrHabitationGeneralNeedsDTO.setEarlyEducationService(rs.getInt("EARLYEDUCATION"));
                    mrHabitationGeneralNeedsDTO.setHomeBased(rs.getInt("HOMEBASED"));
                    mrHabitationGeneralNeedsDTO.setSpecialEducation(rs.getInt("SPECIALEDUCATION"));
                    mrHabitationGeneralNeedsDTO.setInclusiveEducation(rs.getInt("INCLUSIVEEDUCATION"));
                    mrHabitationGeneralNeedsDTO.setEmploymentPublicPvtSector(rs.getInt("PUBLICEMPLOYMENT"));
                    mrHabitationGeneralNeedsDTO.setSelfEmployment(rs.getInt("SELFEMPLOYMENT"));
                    mrHabitationGeneralNeedsDTO.setIndividual(rs.getInt("INDIVIDUAL"));
                    mrHabitationGeneralNeedsDTO.setFamily(rs.getInt("FAMILY"));
                    mrHabitationGeneralNeedsDTO.setLegalGurdian(rs.getInt("LEGALGUARDIAN"));
                    mrHabitationGeneralNeedsDTO.setOtherGeneralNeeds(rs.getInt("OTHERNEEDS"));
                    total = rs.getInt("EARLYEDUCATION") + rs.getInt("HOMEBASED") + rs.getInt("SPECIALEDUCATION")
                            + rs.getInt("INCLUSIVEEDUCATION") + rs.getInt("PUBLICEMPLOYMENT") + rs.getInt("SELFEMPLOYMENT")
                            + rs.getInt("INDIVIDUAL") + rs.getInt("FAMILY") + rs.getInt("LEGALGUARDIAN")
                            + rs.getInt("OTHERNEEDS");
                    mrHabitationGeneralNeedsDTO.setTotal(total);

                    if ((district_id.equals("16") && mandal_id.equals("84") && village_id.equals("004")) && (rs.getString("HABITATIONCODE").substring(10, 12).equals

("00") || rs.getString("HABITATIONCODE").substring(10, 12).equals("01"))) {

                        if (rs.getString("HABITATIONCODE").substring(10, 12).equals("00")) {
                            educationService = mrHabitationGeneralNeedsDTO.getEarlyEducationService();
                            homeBased = mrHabitationGeneralNeedsDTO.getHomeBased();
                            specialEducation = mrHabitationGeneralNeedsDTO.getSpecialEducation();
                            inclusiveEducation = mrHabitationGeneralNeedsDTO.getInclusiveEducation();
                            employmentPublicPvtSector = mrHabitationGeneralNeedsDTO.getEmploymentPublicPvtSector();
                            selfEmployment = mrHabitationGeneralNeedsDTO.getSelfEmployment();
                            individual = mrHabitationGeneralNeedsDTO.getIndividual();
                            family = mrHabitationGeneralNeedsDTO.getFamily();
                            legalGurdian = mrHabitationGeneralNeedsDTO.getLegalGurdian();
                            otherGeneralNeeds = mrHabitationGeneralNeedsDTO.getOtherGeneralNeeds();
                            totl = mrHabitationGeneralNeedsDTO.getTotal();

                        }
                        if (rs.getString("HABITATIONCODE").substring(10, 12).equals("01")) {

                            mrHabitationGeneralNeedsDTO.setEarlyEducationService(educationService + mrHabitationGeneralNeedsDTO.getEarlyEducationService());
                            mrHabitationGeneralNeedsDTO.setHomeBased(homeBased + mrHabitationGeneralNeedsDTO.getHomeBased());
                            mrHabitationGeneralNeedsDTO.setSpecialEducation(specialEducation + mrHabitationGeneralNeedsDTO.getSpecialEducation());
                            mrHabitationGeneralNeedsDTO.setInclusiveEducation(inclusiveEducation + mrHabitationGeneralNeedsDTO.getInclusiveEducation());
                            mrHabitationGeneralNeedsDTO.setEmploymentPublicPvtSector(employmentPublicPvtSector + 

mrHabitationGeneralNeedsDTO.getEmploymentPublicPvtSector());
                            mrHabitationGeneralNeedsDTO.setSelfEmployment(selfEmployment + mrHabitationGeneralNeedsDTO.getSelfEmployment());
                            mrHabitationGeneralNeedsDTO.setIndividual(individual + mrHabitationGeneralNeedsDTO.getIndividual());
                            mrHabitationGeneralNeedsDTO.setFamily(family + mrHabitationGeneralNeedsDTO.getFamily());
                            mrHabitationGeneralNeedsDTO.setLegalGurdian(legalGurdian + mrHabitationGeneralNeedsDTO.getLegalGurdian());
                            mrHabitationGeneralNeedsDTO.setOtherGeneralNeeds(otherGeneralNeeds + mrHabitationGeneralNeedsDTO.getOtherGeneralNeeds());
                            mrHabitationGeneralNeedsDTO.setTotal(totl + mrHabitationGeneralNeedsDTO.getTotal());

                            mrGeneralNeedsList.add(mrHabitationGeneralNeedsDTO);
                        }
                    } else {
                        mrGeneralNeedsList.add(mrHabitationGeneralNeedsDTO);
                    }

                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMRGeneralNeeds", "FunctionalNeedReportDAO", "DataBase");
            //DBConnection.rollbackConnection(con);
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getMRGeneralNeeds");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMRGeneralNeeds", "FunctionalNeedReportDAO", "Code");
            //DBConnection.rollbackConnection(con);
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getMRGeneralNeeds");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }
        return mrGeneralNeedsList;
    }

    public ArrayList getMIGeneralNeeds(DataSource ds,
            FunctionalNeedReportDTO functionalNeedReportDTO)
            throws SADAREMDBException, SQLException {

        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        String district_id = null;
        String mandal_id = null;
        String village_id = null;
        String habitation_id = null;
        ArrayList<FunctionalNeedReportDTO> miGeneralNeedsList = null;
        district_id = functionalNeedReportDTO.getDistrict_id();
        mandal_id = functionalNeedReportDTO.getMandal_id();
        village_id = functionalNeedReportDTO.getVillage_id();
        habitation_id = functionalNeedReportDTO.getHabitation_id();
        int disabilityID = functionalNeedReportDTO.getTypeofdisability();
        int total = 0;
        miGeneralNeedsList = new ArrayList<FunctionalNeedReportDTO>();
        try {
            Date fdate = new SimpleDateFormat("dd/MM/yyyy").parse(functionalNeedReportDTO.getFromdate());
            String fromdate = new SimpleDateFormat("MM/dd/yyyy").format(fdate);

            Date tdate = new SimpleDateFormat("dd/MM/yyyy").parse(functionalNeedReportDTO.getTodate());
            String todate = new SimpleDateFormat("MM/dd/yyyy").format(tdate);
            con = DBConnection.getConnection();
            if (district_id.equals("0")) {

                cstmt = con.prepareCall("{Call SP_DISTRICTWISE_GENERALNEEDSREPORT(?,?,?)}");
                cstmt.setInt(1, disabilityID);
                cstmt.setString(2, fromdate);
                cstmt.setString(3, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO miDistrictGeneralNeedsDTO =
                            new FunctionalNeedReportDTO();
                    miDistrictGeneralNeedsDTO.setDistrict_id(rs.getString("DISTRICT_ID"));
                    miDistrictGeneralNeedsDTO.setMandal_id("a");
                    miDistrictGeneralNeedsDTO.setVillage_id("a");
                    miDistrictGeneralNeedsDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport()) {
                        miDistrictGeneralNeedsDTO.setDistrictName("<a href='./generalNeedsReport.do?mode=mandalLevelReport&district_id=" + rs.getString("DISTRICT_ID") 

+ "&typeofdisability=" + disabilityID + "&fromdate=" + functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate() + "'>" + rs.getString

("DISTRICT_NAME") + "</a>");
                    } else {
                        miDistrictGeneralNeedsDTO.setDistrictName(rs.getString("DISTRICT_NAME"));
                    }
                    miDistrictGeneralNeedsDTO.setEarlyEducationService(rs.getInt("EARLYEDUCATION"));
                    miDistrictGeneralNeedsDTO.setHomeBased(rs.getInt("HOMEBASED"));
                    miDistrictGeneralNeedsDTO.setSpecialEducation(rs.getInt("SPECIALEDUCATION"));
                    miDistrictGeneralNeedsDTO.setInclusiveEducation(rs.getInt("INCLUSIVEEDUCATION"));
                    miDistrictGeneralNeedsDTO.setEmploymentPublicPvtSector(rs.getInt("PUBLICEMPLOYMENT"));
                    miDistrictGeneralNeedsDTO.setSelfEmployment(rs.getInt("SELFEMPLOYMENT"));
                    miDistrictGeneralNeedsDTO.setIndividual(rs.getInt("INDIVIDUAL"));
                    miDistrictGeneralNeedsDTO.setFamily(rs.getInt("FAMILY"));
                    miDistrictGeneralNeedsDTO.setManagerToTakeCareProperties(rs.getInt("MANAGER"));
                    miDistrictGeneralNeedsDTO.setOtherGeneralNeeds(rs.getInt("OTHERNEEDS"));
                    total = rs.getInt("EARLYEDUCATION") + rs.getInt("HOMEBASED") + rs.getInt("SPECIALEDUCATION")
                            + rs.getInt("INCLUSIVEEDUCATION") + rs.getInt("PUBLICEMPLOYMENT") + rs.getInt("SELFEMPLOYMENT")
                            + rs.getInt("INDIVIDUAL") + rs.getInt("FAMILY") + rs.getInt("MANAGER")
                            + rs.getInt("OTHERNEEDS");
                    miDistrictGeneralNeedsDTO.setTotal(total);
                    miGeneralNeedsList.add(miDistrictGeneralNeedsDTO);

                }


            } else if (mandal_id.equals("0")) {
                cstmt = con.prepareCall("{Call SP_MANDALWISE_GENERALNEEDSREPORT(?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setInt(2, disabilityID);
                cstmt.setString(3, fromdate);
                cstmt.setString(4, todate);

                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO miMandalGeneralNeedsDTO =
                            new FunctionalNeedReportDTO();
                    miMandalGeneralNeedsDTO.setDistrict_id(district_id);
                    miMandalGeneralNeedsDTO.setMandal_id(rs.getString("MANDALID"));
                    miMandalGeneralNeedsDTO.setVillage_id("a");
                    miMandalGeneralNeedsDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport()) {
                        miMandalGeneralNeedsDTO.setMandalName("<a href='./generalNeedsReport.do?mode=villageLevelReport&district_id=" + district_id + "&mandal_id=" + 

rs.getString("MANDALID") + "&typeofdisability=" + disabilityID + "&fromdate=" + functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate

() + "'>" + rs.getString("MANDALNAME") + "</a>");
                    } else {
                        miMandalGeneralNeedsDTO.setMandalName(rs.getString("MANDALNAME"));
                    }
                    miMandalGeneralNeedsDTO.setEarlyEducationService(rs.getInt("EARLYEDUCATION"));
                    miMandalGeneralNeedsDTO.setHomeBased(rs.getInt("HOMEBASED"));
                    miMandalGeneralNeedsDTO.setSpecialEducation(rs.getInt("SPECIALEDUCATION"));
                    miMandalGeneralNeedsDTO.setInclusiveEducation(rs.getInt("INCLUSIVEEDUCATION"));
                    miMandalGeneralNeedsDTO.setEmploymentPublicPvtSector(rs.getInt("PUBLICEMPLOYMENT"));
                    miMandalGeneralNeedsDTO.setSelfEmployment(rs.getInt("SELFEMPLOYMENT"));
                    miMandalGeneralNeedsDTO.setIndividual(rs.getInt("INDIVIDUAL"));
                    miMandalGeneralNeedsDTO.setFamily(rs.getInt("FAMILY"));
                    miMandalGeneralNeedsDTO.setManagerToTakeCareProperties(rs.getInt("MANAGER"));
                    miMandalGeneralNeedsDTO.setOtherGeneralNeeds(rs.getInt("OTHERNEEDS"));
                    total = rs.getInt("EARLYEDUCATION") + rs.getInt("HOMEBASED") + rs.getInt("SPECIALEDUCATION")
                            + rs.getInt("INCLUSIVEEDUCATION") + rs.getInt("PUBLICEMPLOYMENT") + rs.getInt("SELFEMPLOYMENT")
                            + rs.getInt("INDIVIDUAL") + rs.getInt("FAMILY") + rs.getInt("MANAGER")
                            + rs.getInt("OTHERNEEDS");
                    miMandalGeneralNeedsDTO.setTotal(total);
                    miGeneralNeedsList.add(miMandalGeneralNeedsDTO);

                }
            } else if (village_id.equals("0")) {
                cstmt = con.prepareCall("{Call SP_VILLAGEWISE_GENERALNEEDSREPORT(?,?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setInt(3, disabilityID);
                cstmt.setString(4, fromdate);
                cstmt.setString(5, todate);

                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO miVillageGeneralNeedsDTO =
                            new FunctionalNeedReportDTO();
                    miVillageGeneralNeedsDTO.setDistrict_id(district_id);
                    miVillageGeneralNeedsDTO.setMandal_id(mandal_id);
                    miVillageGeneralNeedsDTO.setVillage_id(rs.getString("VILLAGEID"));
                    miVillageGeneralNeedsDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport()) {
                        miVillageGeneralNeedsDTO.setVillageName("<a href='./generalNeedsReport.do?mode=habitationLevelReport&district_id=" + district_id + 

"&mandal_id=" + mandal_id + "&village_id=" + rs.getString("VILLAGEID") + "&typeofdisability=" + disabilityID + "&fromdate=" + fromdate + "&todate=" + todate + "'>" + 

rs.getString("VILLAGENAME") + "</a>");
                    } else {
                        miVillageGeneralNeedsDTO.setVillageName(rs.getString("VILLAGENAME"));
                    }
                    miVillageGeneralNeedsDTO.setEarlyEducationService(rs.getInt("EARLYEDUCATION"));
                    miVillageGeneralNeedsDTO.setHomeBased(rs.getInt("HOMEBASED"));
                    miVillageGeneralNeedsDTO.setSpecialEducation(rs.getInt("SPECIALEDUCATION"));
                    miVillageGeneralNeedsDTO.setInclusiveEducation(rs.getInt("INCLUSIVEEDUCATION"));
                    miVillageGeneralNeedsDTO.setEmploymentPublicPvtSector(rs.getInt("PUBLICEMPLOYMENT"));
                    miVillageGeneralNeedsDTO.setSelfEmployment(rs.getInt("SELFEMPLOYMENT"));
                    miVillageGeneralNeedsDTO.setIndividual(rs.getInt("INDIVIDUAL"));
                    miVillageGeneralNeedsDTO.setFamily(rs.getInt("FAMILY"));
                    miVillageGeneralNeedsDTO.setManagerToTakeCareProperties(rs.getInt("MANAGER"));
                    miVillageGeneralNeedsDTO.setOtherGeneralNeeds(rs.getInt("OTHERNEEDS"));
                    total = rs.getInt("EARLYEDUCATION") + rs.getInt("HOMEBASED") + rs.getInt("SPECIALEDUCATION")
                            + rs.getInt("INCLUSIVEEDUCATION") + rs.getInt("PUBLICEMPLOYMENT") + rs.getInt("SELFEMPLOYMENT")
                            + rs.getInt("INDIVIDUAL") + rs.getInt("FAMILY") + rs.getInt("MANAGER")
                            + rs.getInt("OTHERNEEDS");
                    miVillageGeneralNeedsDTO.setTotal(total);
                    miGeneralNeedsList.add(miVillageGeneralNeedsDTO);

                }
            } else if (!village_id.equals("0")) {
                int educationService = 0;
                int homeBased = 0;
                int specialEducation = 0;
                int inclusiveEducation = 0;
                int publicPvtSector = 0;
                int selfEmployment = 0;
                int individual = 0;
                int family = 0;
                int properties = 0;
                int otherGeneralNeeds = 0;
                int totl = 0;

                cstmt = con.prepareCall("{Call SP_HABITATIONWISE_GENERALNEEDSREPORT(?,?,?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setString(3, village_id);
                cstmt.setInt(4, disabilityID);
                cstmt.setString(5, fromdate);
                cstmt.setString(6, todate);

                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO miHabitationGeneralNeedsDTO =
                            new FunctionalNeedReportDTO();
                    miHabitationGeneralNeedsDTO.setDistrict_id(district_id);
                    miHabitationGeneralNeedsDTO.setMandal_id(mandal_id);
                    miHabitationGeneralNeedsDTO.setVillage_id(village_id);
                    miHabitationGeneralNeedsDTO.setHabitation_id(rs.getString("HABITATIONCODE").substring(10, 12));
                    miHabitationGeneralNeedsDTO.setHabitationName(rs.getString("HABITATIONNAME"));
                    miHabitationGeneralNeedsDTO.setEarlyEducationService(rs.getInt("EARLYEDUCATION"));
                    miHabitationGeneralNeedsDTO.setHomeBased(rs.getInt("HOMEBASED"));
                    miHabitationGeneralNeedsDTO.setSpecialEducation(rs.getInt("SPECIALEDUCATION"));
                    miHabitationGeneralNeedsDTO.setInclusiveEducation(rs.getInt("INCLUSIVEEDUCATION"));
                    miHabitationGeneralNeedsDTO.setEmploymentPublicPvtSector(rs.getInt("PUBLICEMPLOYMENT"));
                    miHabitationGeneralNeedsDTO.setSelfEmployment(rs.getInt("SELFEMPLOYMENT"));
                    miHabitationGeneralNeedsDTO.setIndividual(rs.getInt("INDIVIDUAL"));
                    miHabitationGeneralNeedsDTO.setFamily(rs.getInt("FAMILY"));
                    miHabitationGeneralNeedsDTO.setManagerToTakeCareProperties(rs.getInt("MANAGER"));
                    miHabitationGeneralNeedsDTO.setOtherGeneralNeeds(rs.getInt("OTHERNEEDS"));
                    total = rs.getInt("EARLYEDUCATION") + rs.getInt("HOMEBASED") + rs.getInt("SPECIALEDUCATION")
                            + rs.getInt("INCLUSIVEEDUCATION") + rs.getInt("PUBLICEMPLOYMENT") + rs.getInt("SELFEMPLOYMENT")
                            + rs.getInt("INDIVIDUAL") + rs.getInt("FAMILY") + rs.getInt("MANAGER")
                            + rs.getInt("OTHERNEEDS");
                    miHabitationGeneralNeedsDTO.setTotal(total);
                    if ((district_id.equals("16") && mandal_id.equals("84") && village_id.equals("004")) && (rs.getString("HABITATIONCODE").substring(10, 12).equals

("00") || rs.getString("HABITATIONCODE").substring(10, 12).equals("01"))) {

                        if (rs.getString("HABITATIONCODE").substring(10, 12).equals("00")) {
                            educationService = miHabitationGeneralNeedsDTO.getEarlyEducationService();
                            homeBased = miHabitationGeneralNeedsDTO.getHomeBased();
                            specialEducation = miHabitationGeneralNeedsDTO.getSpecialEducation();
                            inclusiveEducation = miHabitationGeneralNeedsDTO.getInclusiveEducation();
                            publicPvtSector = miHabitationGeneralNeedsDTO.getEmploymentPublicPvtSector();
                            selfEmployment = miHabitationGeneralNeedsDTO.getSelfEmployment();
                            individual = miHabitationGeneralNeedsDTO.getIndividual();
                            family = miHabitationGeneralNeedsDTO.getFamily();
                            properties = miHabitationGeneralNeedsDTO.getManagerToTakeCareProperties();
                            otherGeneralNeeds = miHabitationGeneralNeedsDTO.getOtherGeneralNeeds();
                            totl = miHabitationGeneralNeedsDTO.getTotal();

                        }
                        if (rs.getString("HABITATIONCODE").substring(10, 12).equals("01")) {

                            miHabitationGeneralNeedsDTO.setEarlyEducationService(educationService + miHabitationGeneralNeedsDTO.getEarlyEducationService());
                            miHabitationGeneralNeedsDTO.setHomeBased(homeBased + miHabitationGeneralNeedsDTO.getHomeBased());
                            miHabitationGeneralNeedsDTO.setSpecialEducation(specialEducation + miHabitationGeneralNeedsDTO.getSpecialEducation());
                            miHabitationGeneralNeedsDTO.setInclusiveEducation(inclusiveEducation + miHabitationGeneralNeedsDTO.getInclusiveEducation());
                            miHabitationGeneralNeedsDTO.setEmploymentPublicPvtSector(publicPvtSector + miHabitationGeneralNeedsDTO.getEmploymentPublicPvtSector());
                            miHabitationGeneralNeedsDTO.setSelfEmployment(selfEmployment + miHabitationGeneralNeedsDTO.getSelfEmployment());
                            miHabitationGeneralNeedsDTO.setIndividual(individual + miHabitationGeneralNeedsDTO.getIndividual());
                            miHabitationGeneralNeedsDTO.setFamily(family + miHabitationGeneralNeedsDTO.getFamily());
                            miHabitationGeneralNeedsDTO.setManagerToTakeCareProperties(properties + miHabitationGeneralNeedsDTO.getManagerToTakeCareProperties());
                            miHabitationGeneralNeedsDTO.setOtherGeneralNeeds(otherGeneralNeeds + miHabitationGeneralNeedsDTO.getOtherGeneralNeeds());
                            miHabitationGeneralNeedsDTO.setTotal(totl + miHabitationGeneralNeedsDTO.getTotal());

                            miGeneralNeedsList.add(miHabitationGeneralNeedsDTO);
                        }
                    } else {

                        miGeneralNeedsList.add(miHabitationGeneralNeedsDTO);
                    }

                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMIGeneralNeeds", "FunctionalNeedReportDAO", "DataBase");
            //DBConnection.rollbackConnection(con);
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getMIGeneralNeeds");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMIGeneralNeeds", "FunctionalNeedReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getMIGeneralNeeds");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }
        return miGeneralNeedsList;
    }

    public ArrayList getMultipleGeneralNeeds(DataSource ds,
            FunctionalNeedReportDTO functionalNeedReportDTO)
            throws SADAREMDBException, SQLException {

        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        String district_id = null;
        String mandal_id = null;
        String village_id = null;
        String habitation_id = null;
        ArrayList<FunctionalNeedReportDTO> multipleGeneralNeedsList = null;
        district_id = functionalNeedReportDTO.getDistrict_id();
        mandal_id = functionalNeedReportDTO.getMandal_id();
        village_id = functionalNeedReportDTO.getVillage_id();
        habitation_id = functionalNeedReportDTO.getHabitation_id();
        int disabilityID = functionalNeedReportDTO.getTypeofdisability();
        int total = 0;
        multipleGeneralNeedsList = new ArrayList<FunctionalNeedReportDTO>();
        try {
            Date fdate = new SimpleDateFormat("dd/MM/yyyy").parse(functionalNeedReportDTO.getFromdate());
            String fromdate = new SimpleDateFormat("MM/dd/yyyy").format(fdate);

            Date tdate = new SimpleDateFormat("dd/MM/yyyy").parse(functionalNeedReportDTO.getTodate());
            String todate = new SimpleDateFormat("MM/dd/yyyy").format(tdate);
            con = DBConnection.getConnection();
            if (district_id.equals("0")) {

                cstmt = con.prepareCall("{Call SP_DISTRICTWISE_GENERALNEEDSREPORT(?,?,?)}");
                cstmt.setInt(1, disabilityID);
                cstmt.setString(2, fromdate);
                cstmt.setString(3, todate);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO miDistrictGeneralNeedsDTO =
                            new FunctionalNeedReportDTO();
                    miDistrictGeneralNeedsDTO.setDistrict_id(rs.getString("DISTRICT_ID"));
                    miDistrictGeneralNeedsDTO.setMandal_id("a");
                    miDistrictGeneralNeedsDTO.setVillage_id("a");
                    miDistrictGeneralNeedsDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport()) {
                        miDistrictGeneralNeedsDTO.setDistrictName("<a href='./generalNeedsReport.do?mode=mandalLevelReport&district_id=" + rs.getString("DISTRICT_ID") 

+ "&typeofdisability=" + disabilityID + "&fromdate=" + functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate() + "'>" + rs.getString

("DISTRICT_NAME") + "</a>");
                    } else {
                        miDistrictGeneralNeedsDTO.setDistrictName(rs.getString("DISTRICT_NAME"));
                    }
                    miDistrictGeneralNeedsDTO.setEarlyEducationService(rs.getInt("EARLYEDUCATION"));
                    miDistrictGeneralNeedsDTO.setHomeBased(rs.getInt("HOMEBASED"));
                    miDistrictGeneralNeedsDTO.setSpecialEducation(rs.getInt("SPECIALEDUCATION"));
                    miDistrictGeneralNeedsDTO.setInclusiveEducation(rs.getInt("INCLUSIVEEDUCATION"));
                    miDistrictGeneralNeedsDTO.setEmploymentPublicPvtSector(rs.getInt("PUBLICEMPLOYMENT"));
                    miDistrictGeneralNeedsDTO.setSelfEmployment(rs.getInt("SELFEMPLOYMENT"));
                    miDistrictGeneralNeedsDTO.setIndividual(rs.getInt("INDIVIDUAL"));
                    miDistrictGeneralNeedsDTO.setFamily(rs.getInt("FAMILY"));
                    miDistrictGeneralNeedsDTO.setLegalGurdian(rs.getInt("LEGALGUARDIAN"));
                    miDistrictGeneralNeedsDTO.setManagerToTakeCareProperties(rs.getInt("MANAGER"));
                    miDistrictGeneralNeedsDTO.setOtherGeneralNeeds(rs.getInt("OTHERNEEDS"));
                    total = rs.getInt("EARLYEDUCATION") + rs.getInt("HOMEBASED") + rs.getInt("SPECIALEDUCATION")
                            + rs.getInt("INCLUSIVEEDUCATION") + rs.getInt("PUBLICEMPLOYMENT") + rs.getInt("SELFEMPLOYMENT")
                            + rs.getInt("INDIVIDUAL") + rs.getInt("FAMILY") + rs.getInt("MANAGER")
                            + rs.getInt("LEGALGUARDIAN") + rs.getInt("OTHERNEEDS");
                    miDistrictGeneralNeedsDTO.setTotal(total);
                    multipleGeneralNeedsList.add(miDistrictGeneralNeedsDTO);

                }


            } else if (mandal_id.equals("0")) {
                cstmt = con.prepareCall("{Call SP_MANDALWISE_GENERALNEEDSREPORT(?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setInt(2, disabilityID);
                cstmt.setString(3, fromdate);
                cstmt.setString(4, todate);

                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO miMandalGeneralNeedsDTO =
                            new FunctionalNeedReportDTO();
                    miMandalGeneralNeedsDTO.setDistrict_id(district_id);
                    miMandalGeneralNeedsDTO.setMandal_id(rs.getString("MANDALID"));
                    miMandalGeneralNeedsDTO.setVillage_id("a");
                    miMandalGeneralNeedsDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport()) {
                        miMandalGeneralNeedsDTO.setMandalName("<a href='./generalNeedsReport.do?mode=villageLevelReport&district_id=" + district_id + "&mandal_id=" + 

rs.getString("MANDALID") + "&typeofdisability=" + disabilityID + "&fromdate=" + functionalNeedReportDTO.getFromdate() + "&todate=" + functionalNeedReportDTO.getTodate

() + "'>" + rs.getString("MANDALNAME") + "</a>");
                    } else {
                        miMandalGeneralNeedsDTO.setMandalName(rs.getString("MANDALNAME"));
                    }
                    miMandalGeneralNeedsDTO.setEarlyEducationService(rs.getInt("EARLYEDUCATION"));
                    miMandalGeneralNeedsDTO.setHomeBased(rs.getInt("HOMEBASED"));
                    miMandalGeneralNeedsDTO.setSpecialEducation(rs.getInt("SPECIALEDUCATION"));
                    miMandalGeneralNeedsDTO.setInclusiveEducation(rs.getInt("INCLUSIVEEDUCATION"));
                    miMandalGeneralNeedsDTO.setEmploymentPublicPvtSector(rs.getInt("PUBLICEMPLOYMENT"));
                    miMandalGeneralNeedsDTO.setSelfEmployment(rs.getInt("SELFEMPLOYMENT"));
                    miMandalGeneralNeedsDTO.setIndividual(rs.getInt("INDIVIDUAL"));
                    miMandalGeneralNeedsDTO.setFamily(rs.getInt("FAMILY"));
                    miMandalGeneralNeedsDTO.setLegalGurdian(rs.getInt("LEGALGUARDIAN"));
                    miMandalGeneralNeedsDTO.setManagerToTakeCareProperties(rs.getInt("MANAGER"));
                    miMandalGeneralNeedsDTO.setOtherGeneralNeeds(rs.getInt("OTHERNEEDS"));
                    total = rs.getInt("EARLYEDUCATION") + rs.getInt("HOMEBASED") + rs.getInt("SPECIALEDUCATION")
                            + rs.getInt("INCLUSIVEEDUCATION") + rs.getInt("PUBLICEMPLOYMENT") + rs.getInt("SELFEMPLOYMENT")
                            + rs.getInt("INDIVIDUAL") + rs.getInt("FAMILY") + rs.getInt("MANAGER")
                            + rs.getInt("LEGALGUARDIAN") + rs.getInt("OTHERNEEDS");
                    miMandalGeneralNeedsDTO.setTotal(total);
                    multipleGeneralNeedsList.add(miMandalGeneralNeedsDTO);

                }
            } else if (village_id.equals("0")) {
                cstmt = con.prepareCall("{Call SP_VILLAGEWISE_GENERALNEEDSREPORT(?,?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setInt(3, disabilityID);
                cstmt.setString(4, fromdate);
                cstmt.setString(5, todate);

                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO miVillageGeneralNeedsDTO =
                            new FunctionalNeedReportDTO();
                    miVillageGeneralNeedsDTO.setDistrict_id(district_id);
                    miVillageGeneralNeedsDTO.setMandal_id(mandal_id);
                    miVillageGeneralNeedsDTO.setVillage_id(rs.getString("VILLAGEID"));
                    miVillageGeneralNeedsDTO.setHabitation_id("a");
                    if (functionalNeedReportDTO.isReport()) {
                        miVillageGeneralNeedsDTO.setVillageName("<a href='./generalNeedsReport.do?mode=habitationLevelReport&district_id=" + district_id + 

"&mandal_id=" + mandal_id + "&village_id=" + rs.getString("VILLAGEID") + "&typeofdisability=" + disabilityID + "&fromdate=" + functionalNeedReportDTO.getFromdate() + 

"&todate=" + functionalNeedReportDTO.getTodate() + "'>" + rs.getString("VILLAGENAME") + "</a>");
                    } else {
                        miVillageGeneralNeedsDTO.setVillageName(rs.getString("VILLAGENAME"));
                    }
                    miVillageGeneralNeedsDTO.setEarlyEducationService(rs.getInt("EARLYEDUCATION"));
                    miVillageGeneralNeedsDTO.setHomeBased(rs.getInt("HOMEBASED"));
                    miVillageGeneralNeedsDTO.setSpecialEducation(rs.getInt("SPECIALEDUCATION"));
                    miVillageGeneralNeedsDTO.setInclusiveEducation(rs.getInt("INCLUSIVEEDUCATION"));
                    miVillageGeneralNeedsDTO.setEmploymentPublicPvtSector(rs.getInt("PUBLICEMPLOYMENT"));
                    miVillageGeneralNeedsDTO.setSelfEmployment(rs.getInt("SELFEMPLOYMENT"));
                    miVillageGeneralNeedsDTO.setIndividual(rs.getInt("INDIVIDUAL"));
                    miVillageGeneralNeedsDTO.setFamily(rs.getInt("FAMILY"));
                    miVillageGeneralNeedsDTO.setLegalGurdian(rs.getInt("LEGALGUARDIAN"));
                    miVillageGeneralNeedsDTO.setManagerToTakeCareProperties(rs.getInt("MANAGER"));
                    miVillageGeneralNeedsDTO.setOtherGeneralNeeds(rs.getInt("OTHERNEEDS"));
                    total = rs.getInt("EARLYEDUCATION") + rs.getInt("HOMEBASED") + rs.getInt("SPECIALEDUCATION")
                            + rs.getInt("INCLUSIVEEDUCATION") + rs.getInt("PUBLICEMPLOYMENT") + rs.getInt("SELFEMPLOYMENT")
                            + rs.getInt("INDIVIDUAL") + rs.getInt("FAMILY") + rs.getInt("MANAGER")
                            + rs.getInt("LEGALGUARDIAN") + rs.getInt("OTHERNEEDS");
                    miVillageGeneralNeedsDTO.setTotal(total);
                    multipleGeneralNeedsList.add(miVillageGeneralNeedsDTO);

                }
            } else if (!village_id.equals("0")) {
                int educationService = 0;
                int homeBased = 0;
                int specialEducation = 0;
                int inclusiveEducation = 0;
                int pvtSector = 0;
                int selfEmployment = 0;
                int individual = 0;
                int family = 0;
                int legalGurdian = 0;
                int properties = 0;
                int otherGeneralNeeds = 0;
                int totl = 0;
                cstmt = con.prepareCall("{Call SP_HABITATIONWISE_GENERALNEEDSREPORT(?,?,?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setString(3, village_id);
                cstmt.setInt(4, disabilityID);
                cstmt.setString(5, fromdate);
                cstmt.setString(6, todate);

                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO miHabitationGeneralNeedsDTO =
                            new FunctionalNeedReportDTO();
                    miHabitationGeneralNeedsDTO.setDistrict_id(district_id);
                    miHabitationGeneralNeedsDTO.setMandal_id(mandal_id);
                    miHabitationGeneralNeedsDTO.setVillage_id(village_id);
                    miHabitationGeneralNeedsDTO.setHabitation_id(rs.getString("HABITATIONCODE").substring(10, 12));
                    miHabitationGeneralNeedsDTO.setHabitationName(rs.getString("HABITATIONNAME"));
                    miHabitationGeneralNeedsDTO.setEarlyEducationService(rs.getInt("EARLYEDUCATION"));
                    miHabitationGeneralNeedsDTO.setHomeBased(rs.getInt("HOMEBASED"));
                    miHabitationGeneralNeedsDTO.setSpecialEducation(rs.getInt("SPECIALEDUCATION"));
                    miHabitationGeneralNeedsDTO.setInclusiveEducation(rs.getInt("INCLUSIVEEDUCATION"));
                    miHabitationGeneralNeedsDTO.setEmploymentPublicPvtSector(rs.getInt("PUBLICEMPLOYMENT"));
                    miHabitationGeneralNeedsDTO.setSelfEmployment(rs.getInt("SELFEMPLOYMENT"));
                    miHabitationGeneralNeedsDTO.setIndividual(rs.getInt("INDIVIDUAL"));
                    miHabitationGeneralNeedsDTO.setFamily(rs.getInt("FAMILY"));
                    miHabitationGeneralNeedsDTO.setLegalGurdian(rs.getInt("LEGALGUARDIAN"));
                    miHabitationGeneralNeedsDTO.setManagerToTakeCareProperties(rs.getInt("MANAGER"));
                    miHabitationGeneralNeedsDTO.setOtherGeneralNeeds(rs.getInt("OTHERNEEDS"));
                    total = rs.getInt("EARLYEDUCATION") + rs.getInt("HOMEBASED") + rs.getInt("SPECIALEDUCATION")
                            + rs.getInt("INCLUSIVEEDUCATION") + rs.getInt("PUBLICEMPLOYMENT") + rs.getInt("SELFEMPLOYMENT")
                            + rs.getInt("INDIVIDUAL") + rs.getInt("FAMILY") + rs.getInt("MANAGER")
                            + rs.getInt("LEGALGUARDIAN") + rs.getInt("OTHERNEEDS");
                    miHabitationGeneralNeedsDTO.setTotal(total);
                    if ((district_id.equals("16") && mandal_id.equals("84") && village_id.equals("004")) && (rs.getString("HABITATIONCODE").substring(10, 12).equals

("00") || rs.getString("HABITATIONCODE").substring(10, 12).equals("01"))) {

                        if (rs.getString("HABITATIONCODE").substring(10, 12).equals("00")) {

                            educationService = miHabitationGeneralNeedsDTO.getEarlyEducationService();
                            homeBased = miHabitationGeneralNeedsDTO.getHomeBased();
                            specialEducation = miHabitationGeneralNeedsDTO.getSpecialEducation();
                            inclusiveEducation = miHabitationGeneralNeedsDTO.getInclusiveEducation();
                            pvtSector = miHabitationGeneralNeedsDTO.getEmploymentPublicPvtSector();
                            selfEmployment = miHabitationGeneralNeedsDTO.getSelfEmployment();
                            individual = miHabitationGeneralNeedsDTO.getIndividual();
                            family = miHabitationGeneralNeedsDTO.getFamily();
                            legalGurdian = miHabitationGeneralNeedsDTO.getLegalGurdian();
                            properties = miHabitationGeneralNeedsDTO.getManagerToTakeCareProperties();
                            otherGeneralNeeds = miHabitationGeneralNeedsDTO.getOtherGeneralNeeds();
                            totl = miHabitationGeneralNeedsDTO.getTotal();

                        }
                        if (rs.getString("HABITATIONCODE").substring(10, 12).equals("01")) {

                            miHabitationGeneralNeedsDTO.setEarlyEducationService(educationService + miHabitationGeneralNeedsDTO.getEarlyEducationService());
                            miHabitationGeneralNeedsDTO.setHomeBased(homeBased + miHabitationGeneralNeedsDTO.getHomeBased());
                            miHabitationGeneralNeedsDTO.setSpecialEducation(specialEducation + miHabitationGeneralNeedsDTO.getSpecialEducation());
                            miHabitationGeneralNeedsDTO.setInclusiveEducation(inclusiveEducation + miHabitationGeneralNeedsDTO.getInclusiveEducation());
                            miHabitationGeneralNeedsDTO.setEmploymentPublicPvtSector(pvtSector + miHabitationGeneralNeedsDTO.getEmploymentPublicPvtSector());
                            miHabitationGeneralNeedsDTO.setSelfEmployment(selfEmployment + miHabitationGeneralNeedsDTO.getSelfEmployment());
                            miHabitationGeneralNeedsDTO.setIndividual(individual + miHabitationGeneralNeedsDTO.getIndividual());
                            miHabitationGeneralNeedsDTO.setFamily(family + miHabitationGeneralNeedsDTO.getFamily());
                            miHabitationGeneralNeedsDTO.setLegalGurdian(legalGurdian + miHabitationGeneralNeedsDTO.getLegalGurdian());
                            miHabitationGeneralNeedsDTO.setManagerToTakeCareProperties(properties + miHabitationGeneralNeedsDTO.getManagerToTakeCareProperties());
                            miHabitationGeneralNeedsDTO.setOtherGeneralNeeds(otherGeneralNeeds + miHabitationGeneralNeedsDTO.getOtherGeneralNeeds());
                            miHabitationGeneralNeedsDTO.setTotal(totl + miHabitationGeneralNeedsDTO.getTotal());

                            multipleGeneralNeedsList.add(miHabitationGeneralNeedsDTO);
                        }
                    } else {
                        multipleGeneralNeedsList.add(miHabitationGeneralNeedsDTO);
                    }

                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMultipleGeneralNeeds", "FunctionalNeedReportDAO", "DataBase");
            //DBConnection.rollbackConnection(con);
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getMultipleGeneralNeeds");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMultipleGeneralNeeds", "FunctionalNeedReportDAO", "Code");
            //DBConnection.rollbackConnection(con);
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getMultipleGeneralNeeds");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }
        return multipleGeneralNeedsList;
    }

    public ArrayList getPersonalDetailsAnalysis(DataSource ds,
            FunctionalNeedReportDTO functionalNeedReportDTO, int start, int end, String columnName,
            String columnValue, String tableName)
            throws SADAREMDBException, SQLException {

        Connection con = null;
        CallableStatement cstmt = null;
        Statement stmt = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        String district_id = null;
        String mandal_id = null;
        String village_id = null;
        String habitation_id = null;
        String subdisability = null;
        String subsubdisability = null;
        ArrayList<FunctionalNeedReportDTO> personalList = null;
        district_id = functionalNeedReportDTO.getDistrict_id();
        mandal_id = functionalNeedReportDTO.getMandal_id();
        village_id = functionalNeedReportDTO.getVillage_id();
        habitation_id = functionalNeedReportDTO.getHabitation_id();
        personalList = new ArrayList<FunctionalNeedReportDTO>();

        try {
            Date fdate = new SimpleDateFormat("dd/MM/yyyy").parse(functionalNeedReportDTO.getFromdate());
            String fromdate = new SimpleDateFormat("MM/dd/yyyy").format(fdate);

            Date tdate = new SimpleDateFormat("dd/MM/yyyy").parse(functionalNeedReportDTO.getTodate());
            String todate = new SimpleDateFormat("MM/dd/yyyy").format(tdate);

            int a[] = new int[20];
            int b[] = new int[20];
            con = DBConnection.getConnection();


            if (!district_id.equals("0") && mandal_id.equals("a")) {

                cstmt = con.prepareCall("{Call SP_SelectPersonDetails_for_Reports_Districtwise(?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, tableName);
                cstmt.setString(3, columnName);
                cstmt.setString(4, columnValue);
                cstmt.setString(5, fromdate);
                cstmt.setString(6, todate);
                cstmt.setInt(7, start);
                cstmt.setInt(8, end);

                stmt = con.createStatement();
                rs = cstmt.executeQuery();

                while (rs.next()) {
                    FunctionalNeedReportDTO personalReportDTO = new FunctionalNeedReportDTO();
                    personalReportDTO.setPersoncode(rs.getString("Person_Code"));
                    personalReportDTO.setName(rs.getString("PersonName"));
                    personalReportDTO.setRalationName(rs.getString("Relation_Name"));
                    personalReportDTO.setAge(rs.getString("Age_Years"));
                    if (rs.getString("Gender").equals("1")) {
                        personalReportDTO.setGender("Male");
                    } else if (rs.getString("Gender").equals("2")) {
                        personalReportDTO.setGender("Female");
                    }


                    personalReportDTO.setHouseno(rs.getString("House_Number"));
                    personalReportDTO.setDistrictName(rs.getString("District_Name"));
                    personalReportDTO.setMandalName(rs.getString("Mandal_Name"));
                    personalReportDTO.setVillageName(rs.getString("Village_Name"));
                    personalReportDTO.setHabitationName(rs.getString("Habitation_Name"));
                    personalReportDTO.setDisabilityType(rs.getString("Disability_Name").substring(2));


                    if (rs.getString("Sub_Disability_ID") != null) {
                        a = convertToIntArray(rs.getString("Sub_Disability_ID"));
                        stmt = con.createStatement();

                        subdisability = null;

                        for (int i = 0; i < a.length; i++) {
                            rs2 = stmt.executeQuery("select Sub_Disability_Name from tblSubDisability_Details  with(nolock) "
                                    + "where Sub_Disability_ID=" + a[i] + "");
                            while (rs2.next()) {
                                if (subdisability == null) {
                                    subdisability = rs2.getString("Sub_Disability_Name").substring(2);
                                } else {
                                    subdisability = subdisability + "," + rs2.getString("Sub_Disability_Name").substring(2);
                                }

                            }
                        }
                        personalReportDTO.setSubtypeofDisability(subdisability);
                    }



                    if (rs.getString("Sub_Sub_Disability_ID") != null) {

                        b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                        stmt = con.createStatement();
                        subsubdisability = null;
                        for (int i = 0; i < b.length; i++) {
                            rs2 = stmt.executeQuery("select Sub_Sub_Disability_Name from tblSub_Sub_Disabilities with(nolock)  "
                                    + "where Sub_Sub_Disability_ID=" + b[i] + "");
                            while (rs2.next()) {
                                if (subsubdisability == null) {
                                    subsubdisability = rs2.getString("Sub_Sub_Disability_Name").substring(2);
                                } else {
                                    subsubdisability = subsubdisability + ","
                                            + rs2.getString("Sub_Sub_Disability_Name").substring(2);
                                }
                            }
                        }
                        personalReportDTO.setPartsInvolved(subsubdisability);
                    }
                    int number = new Float((rs.getString("TotalDisability"))).intValue();
                    String percentage = Integer.toString(number);
                    personalReportDTO.setTotalpercent(percentage);


                    if (rs.getString("Caste").equals("1")) {
                        personalReportDTO.setCaste("ST");
                    } else if (rs.getString("Caste").equals("2")) {
                        personalReportDTO.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        personalReportDTO.setCaste("BC");
                    } else if (rs.getString("Caste").equals("4")) {
                        personalReportDTO.setCaste("OC");
                    } else if (rs.getString("Caste").equals("5")) {
                        personalReportDTO.setCaste("Minority");
                    } else if (rs.getString("Caste").equals("6")) {
                        personalReportDTO.setCaste("NA");
                    }



                    if (rs.getString("Employment").equals("1")) {
                        personalReportDTO.setOccupation("Govt");
                    } else if (rs.getString("Employment").equals("2")) {
                        personalReportDTO.setOccupation("Private");
                    } else if (rs.getString("Employment").equals("3")) {
                        personalReportDTO.setOccupation("Self-Employed");
                    } else if (rs.getString("Employment").equals("4")) {
                        personalReportDTO.setOccupation("Un-Employed");
                    } else if (rs.getString("Employment").equals("5")) {
                        personalReportDTO.setOccupation("Wage-Employed");
                    }

                    personalList.add(personalReportDTO);

                }



            } else if (!mandal_id.equals("0") && village_id.equals("a")) {
                cstmt = con.prepareCall("{Call SP_SelectPersonDetails_for_Reports_Mandalwise(?,?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setString(3, tableName);
                cstmt.setString(4, columnName);
                cstmt.setString(5, columnValue);
                cstmt.setString(6, fromdate);
                cstmt.setString(7, todate);
                cstmt.setInt(8, start);
                cstmt.setInt(9, end);

                stmt = con.createStatement();
                rs = cstmt.executeQuery();

                while (rs.next()) {
                    FunctionalNeedReportDTO personalReportDTO = new FunctionalNeedReportDTO();
                    personalReportDTO.setPersoncode(rs.getString("Person_Code"));
                    personalReportDTO.setName(rs.getString("PersonName"));
                    personalReportDTO.setAge(rs.getString("Age_Years"));
                    personalReportDTO.setRalationName(rs.getString("Relation_Name"));

                    if (rs.getString("Gender").equals("1")) {
                        personalReportDTO.setGender("Male");
                    } else if (rs.getString("Gender").equals("2")) {
                        personalReportDTO.setGender("Female");
                    }


                    personalReportDTO.setHouseno(rs.getString("House_Number"));
                    personalReportDTO.setDistrictName(rs.getString("District_Name"));
                    personalReportDTO.setMandalName(rs.getString("Mandal_Name"));
                    personalReportDTO.setVillageName(rs.getString("Village_Name"));
                    personalReportDTO.setHabitationName(rs.getString("Habitation_Name"));
                    personalReportDTO.setDisabilityType(rs.getString("Disability_Name").substring(2));


                    if (rs.getString("Sub_Disability_ID") != null) {
                        a = convertToIntArray(rs.getString("Sub_Disability_ID"));
                        stmt = con.createStatement();

                        subdisability = null;

                        for (int i = 0; i < a.length; i++) {
                            rs2 = stmt.executeQuery("select Sub_Disability_Name from tblSubDisability_Details with(nolock)  "
                                    + "where Sub_Disability_ID=" + a[i] + "");
                            while (rs2.next()) {
                                if (subdisability == null) {
                                    subdisability = rs2.getString("Sub_Disability_Name").substring(2);
                                } else {
                                    subdisability = subdisability + "," + rs2.getString("Sub_Disability_Name").substring(2);
                                }

                            }
                        }
                        personalReportDTO.setSubtypeofDisability(subdisability);
                    }



                    if (rs.getString("Sub_Sub_Disability_ID") != null) {

                        b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                        stmt = con.createStatement();
                        subsubdisability = null;
                        for (int i = 0; i < b.length; i++) {
                            rs2 = stmt.executeQuery("select Sub_Sub_Disability_Name from tblSub_Sub_Disabilities with(nolock)  "
                                    + "where Sub_Sub_Disability_ID=" + b[i] + "");
                            while (rs2.next()) {
                                if (subsubdisability == null) {
                                    subsubdisability = rs2.getString("Sub_Sub_Disability_Name").substring(2);
                                } else {
                                    subsubdisability = subsubdisability + ","
                                            + rs2.getString("Sub_Sub_Disability_Name").substring(2);
                                }
                            }
                        }
                        personalReportDTO.setPartsInvolved(subsubdisability);
                    }

                    int number = new Float((rs.getString("TotalDisability"))).intValue();
                    String percentage = Integer.toString(number);
                    personalReportDTO.setTotalpercent(percentage);


                    if (rs.getString("Caste").equals("1")) {
                        personalReportDTO.setCaste("ST");
                    } else if (rs.getString("Caste").equals("2")) {
                        personalReportDTO.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        personalReportDTO.setCaste("BC");
                    } else if (rs.getString("Caste").equals("4")) {
                        personalReportDTO.setCaste("OC");
                    } else if (rs.getString("Caste").equals("5")) {
                        personalReportDTO.setCaste("Minority");
                    } else if (rs.getString("Caste").equals("6")) {
                        personalReportDTO.setCaste("NA");
                    }



                    if (rs.getString("Employment").equals("1")) {
                        personalReportDTO.setOccupation("Govt");
                    } else if (rs.getString("Employment").equals("2")) {
                        personalReportDTO.setOccupation("Private");
                    } else if (rs.getString("Employment").equals("3")) {
                        personalReportDTO.setOccupation("Self-Employed");
                    } else if (rs.getString("Employment").equals("4")) {
                        personalReportDTO.setOccupation("Un-Employed");
                    } else if (rs.getString("Employment").equals("5")) {
                        personalReportDTO.setOccupation("Wage-Employed");
                    }

                    personalList.add(personalReportDTO);

                }
            } else if (!village_id.equals("0") && habitation_id.equals("a")) {
                cstmt = con.prepareCall("{Call SP_SelectPersonDetails_for_Reports_Villagewise(?,?,?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setString(3, village_id);
                cstmt.setString(4, tableName);
                cstmt.setString(5, columnName);
                cstmt.setString(6, columnValue);
                cstmt.setString(7, fromdate);
                cstmt.setString(8, todate);
                cstmt.setInt(9, start);
                cstmt.setInt(10, end);

                stmt = con.createStatement();
                rs = cstmt.executeQuery();

                while (rs.next()) {
                    FunctionalNeedReportDTO personalReportDTO = new FunctionalNeedReportDTO();
                    personalReportDTO.setPersoncode(rs.getString("Person_Code"));
                    personalReportDTO.setName(rs.getString("PersonName"));
                    personalReportDTO.setAge(rs.getString("Age_Years"));
                    personalReportDTO.setRalationName(rs.getString("Relation_Name"));
                    if (rs.getString("Gender").equals("1")) {
                        personalReportDTO.setGender("Male");
                    } else if (rs.getString("Gender").equals("2")) {
                        personalReportDTO.setGender("Female");
                    }


                    personalReportDTO.setHouseno(rs.getString("House_Number"));
                    personalReportDTO.setDistrictName(rs.getString("District_Name"));
                    personalReportDTO.setMandalName(rs.getString("Mandal_Name"));
                    personalReportDTO.setVillageName(rs.getString("Village_Name"));
                    personalReportDTO.setHabitationName(rs.getString("Habitation_Name"));
                    personalReportDTO.setDisabilityType(rs.getString("Disability_Name").substring(2));


                    if (rs.getString("Sub_Disability_ID") != null) {
                        a = convertToIntArray(rs.getString("Sub_Disability_ID"));
                        stmt = con.createStatement();

                        subdisability = null;

                        for (int i = 0; i < a.length; i++) {
                            rs2 = stmt.executeQuery("select Sub_Disability_Name from tblSubDisability_Details with(nolock) "
                                    + "where Sub_Disability_ID=" + a[i] + "");
                            while (rs2.next()) {
                                if (subdisability == null) {
                                    subdisability = rs2.getString("Sub_Disability_Name").substring(2);
                                } else {
                                    subdisability = subdisability + "," + rs2.getString("Sub_Disability_Name").substring(2);
                                }

                            }
                        }
                        personalReportDTO.setSubtypeofDisability(subdisability);
                    }



                    if (rs.getString("Sub_Sub_Disability_ID") != null) {

                        b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                        stmt = con.createStatement();
                        subsubdisability = null;
                        for (int i = 0; i < b.length; i++) {
                            rs2 = stmt.executeQuery("select Sub_Sub_Disability_Name from tblSub_Sub_Disabilities with(nolock)  "
                                    + "where Sub_Sub_Disability_ID=" + b[i] + "");
                            while (rs2.next()) {
                                if (subsubdisability == null) {
                                    subsubdisability = rs2.getString("Sub_Sub_Disability_Name").substring(2);
                                } else {
                                    subsubdisability = subsubdisability + ","
                                            + rs2.getString("Sub_Sub_Disability_Name").substring(2);
                                }
                            }
                        }
                        personalReportDTO.setPartsInvolved(subsubdisability);
                    }

                    int number = new Float((rs.getString("TotalDisability"))).intValue();
                    String percentage = Integer.toString(number);
                    personalReportDTO.setTotalpercent(percentage);


                    if (rs.getString("Caste").equals("1")) {
                        personalReportDTO.setCaste("ST");
                    } else if (rs.getString("Caste").equals("2")) {
                        personalReportDTO.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        personalReportDTO.setCaste("BC");
                    } else if (rs.getString("Caste").equals("4")) {
                        personalReportDTO.setCaste("OC");
                    } else if (rs.getString("Caste").equals("5")) {
                        personalReportDTO.setCaste("Minority");
                    } else if (rs.getString("Caste").equals("6")) {
                        personalReportDTO.setCaste("NA");
                    }



                    if (rs.getString("Employment").equals("1")) {
                        personalReportDTO.setOccupation("Govt");
                    } else if (rs.getString("Employment").equals("2")) {
                        personalReportDTO.setOccupation("Private");
                    } else if (rs.getString("Employment").equals("3")) {
                        personalReportDTO.setOccupation("Self-Employed");
                    } else if (rs.getString("Employment").equals("4")) {
                        personalReportDTO.setOccupation("Un-Employed");
                    } else if (rs.getString("Employment").equals("5")) {
                        personalReportDTO.setOccupation("Wage-Employed");
                    }

                    personalList.add(personalReportDTO);

                }
            } else if (!habitation_id.equals("a")) {
                cstmt = con.prepareCall("{Call SP_SelectPersonDetails_for_Reports_Habitationwise(?,?,?,?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setString(3, village_id);
                cstmt.setString(4, habitation_id);
                cstmt.setString(5, tableName);
                cstmt.setString(6, columnName);
                cstmt.setString(7, columnValue);
                cstmt.setString(8, fromdate);
                cstmt.setString(9, todate);
                cstmt.setInt(10, start);
                cstmt.setInt(11, end);

                stmt = con.createStatement();
                rs = cstmt.executeQuery();

                while (rs.next()) {
                    FunctionalNeedReportDTO personalReportDTO = new FunctionalNeedReportDTO();
                    personalReportDTO.setPersoncode(rs.getString("Person_Code"));
                    personalReportDTO.setName(rs.getString("PersonName"));
                    personalReportDTO.setAge(rs.getString("Age_Years"));
                    personalReportDTO.setRalationName(rs.getString("Relation_Name"));
                    if (rs.getString("Gender").equals("1")) {
                        personalReportDTO.setGender("Male");
                    } else if (rs.getString("Gender").equals("2")) {
                        personalReportDTO.setGender("Female");
                    }


                    personalReportDTO.setHouseno(rs.getString("House_Number"));
                    personalReportDTO.setDistrictName(rs.getString("District_Name"));
                    personalReportDTO.setMandalName(rs.getString("Mandal_Name"));
                    personalReportDTO.setVillageName(rs.getString("Village_Name"));
                    personalReportDTO.setHabitationName(rs.getString("Habitation_Name"));
                    personalReportDTO.setDisabilityType(rs.getString("Disability_Name").substring(2));


                    if (rs.getString("Sub_Disability_ID") != null) {
                        a = convertToIntArray(rs.getString("Sub_Disability_ID"));
                        stmt = con.createStatement();

                        subdisability = null;

                        for (int i = 0; i < a.length; i++) {
                            rs2 = stmt.executeQuery("select Sub_Disability_Name from tblSubDisability_Details with(nolock)  "
                                    + "where Sub_Disability_ID=" + a[i] + "");
                            while (rs2.next()) {
                                if (subdisability == null) {
                                    subdisability = rs2.getString("Sub_Disability_Name").substring(2);
                                } else {
                                    subdisability = subdisability + "," + rs2.getString("Sub_Disability_Name").substring(2);
                                }

                            }
                        }
                        personalReportDTO.setSubtypeofDisability(subdisability);
                    }



                    if (rs.getString("Sub_Sub_Disability_ID") != null) {

                        b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                        stmt = con.createStatement();
                        subsubdisability = null;
                        for (int i = 0; i < b.length; i++) {
                            rs2 = stmt.executeQuery("select Sub_Sub_Disability_Name from tblSub_Sub_Disabilities  with(nolock) "
                                    + "where Sub_Sub_Disability_ID=" + b[i] + "");
                            while (rs2.next()) {
                                if (subsubdisability == null) {
                                    subsubdisability = rs2.getString("Sub_Sub_Disability_Name").substring(2);
                                } else {
                                    subsubdisability = subsubdisability + ","
                                            + rs2.getString("Sub_Sub_Disability_Name").substring(2);
                                }
                            }
                        }
                        personalReportDTO.setPartsInvolved(subsubdisability);
                    }

                    int number = new Float((rs.getString("TotalDisability"))).intValue();
                    String percentage = Integer.toString(number);
                    personalReportDTO.setTotalpercent(percentage);


                    if (rs.getString("Caste").equals("1")) {
                        personalReportDTO.setCaste("ST");
                    } else if (rs.getString("Caste").equals("2")) {
                        personalReportDTO.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        personalReportDTO.setCaste("BC");
                    } else if (rs.getString("Caste").equals("4")) {
                        personalReportDTO.setCaste("OC");
                    } else if (rs.getString("Caste").equals("5")) {
                        personalReportDTO.setCaste("Minority");
                    } else if (rs.getString("Caste").equals("6")) {
                        personalReportDTO.setCaste("NA");
                    }



                    if (rs.getString("Employment").equals("1")) {
                        personalReportDTO.setOccupation("Govt");
                    } else if (rs.getString("Employment").equals("2")) {
                        personalReportDTO.setOccupation("Private");
                    } else if (rs.getString("Employment").equals("3")) {
                        personalReportDTO.setOccupation("Self-Employed");
                    } else if (rs.getString("Employment").equals("4")) {
                        personalReportDTO.setOccupation("Un-Employed");
                    } else if (rs.getString("Employment").equals("5")) {
                        personalReportDTO.setOccupation("Wage-Employed");
                    }

                    personalList.add(personalReportDTO);

                }
            } else if (habitation_id != null && !habitation_id.equals("0")) {
                cstmt = con.prepareCall("{Call SP_SelectPersonDetails_for_Reports_Habitationwise(?,?,?,?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setString(3, village_id);
                cstmt.setString(4, habitation_id);
                cstmt.setString(5, tableName);
                cstmt.setString(6, columnName);
                cstmt.setString(7, columnValue);
                cstmt.setString(8, fromdate);
                cstmt.setString(9, todate);
                cstmt.setInt(10, start);
                cstmt.setInt(11, end);

                stmt = con.createStatement();
                rs = cstmt.executeQuery();

                while (rs.next()) {
                    FunctionalNeedReportDTO personalReportDTO = new FunctionalNeedReportDTO();
                    personalReportDTO.setPersoncode(rs.getString("Person_Code"));
                    personalReportDTO.setName(rs.getString("PersonName"));
                    personalReportDTO.setAge(rs.getString("Age_Years"));
                    personalReportDTO.setRalationName(rs.getString("Relation_Name"));
                    if (rs.getString("Gender").equals("1")) {
                        personalReportDTO.setGender("Male");
                    } else if (rs.getString("Gender").equals("2")) {
                        personalReportDTO.setGender("Female");
                    }

                    personalReportDTO.setHouseno(rs.getString("House_Number"));
                    personalReportDTO.setDistrictName(rs.getString("District_Name"));
                    personalReportDTO.setMandalName(rs.getString("Mandal_Name"));
                    personalReportDTO.setVillageName(rs.getString("Village_Name"));
                    personalReportDTO.setHabitationName(rs.getString("Habitation_Name"));
                    personalReportDTO.setDisabilityType(rs.getString("Disability_Name").substring(2));


                    if (rs.getString("Sub_Disability_ID") != null) {
                        a = convertToIntArray(rs.getString("Sub_Disability_ID"));
                        stmt = con.createStatement();

                        subdisability = null;

                        for (int i = 0; i < a.length; i++) {
                            rs2 = stmt.executeQuery("select Sub_Disability_Name from tblSubDisability_Details  with(nolock) "
                                    + "where Sub_Disability_ID=" + a[i] + "");
                            while (rs2.next()) {
                                if (subdisability == null) {
                                    subdisability = rs2.getString("Sub_Disability_Name").substring(2);
                                } else {
                                    subdisability = subdisability + "," + rs2.getString("Sub_Disability_Name").substring(2);
                                }

                            }
                        }
                        personalReportDTO.setSubtypeofDisability(subdisability);
                    }



                    if (rs.getString("Sub_Sub_Disability_ID") != null) {

                        b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                        stmt = con.createStatement();
                        subsubdisability = null;
                        for (int i = 0; i < b.length; i++) {
                            rs2 = stmt.executeQuery("select Sub_Sub_Disability_Name from tblSub_Sub_Disabilities with(nolock)  "
                                    + "where Sub_Sub_Disability_ID=" + b[i] + "");
                            while (rs2.next()) {
                                if (subsubdisability == null) {
                                    subsubdisability = rs2.getString("Sub_Sub_Disability_Name").substring(2);
                                } else {
                                    subsubdisability = subsubdisability + "," + rs2.getString("Sub_Sub_Disability_Name").substring(2);
                                }
                            }
                        }
                        personalReportDTO.setPartsInvolved(subsubdisability);
                    }

                    int number = new Float((rs.getString("TotalDisability"))).intValue();
                    String percentage = Integer.toString(number);
                    personalReportDTO.setTotalpercent(percentage);


                    if (rs.getString("Caste").equals("1")) {
                        personalReportDTO.setCaste("ST");
                    } else if (rs.getString("Caste").equals("2")) {
                        personalReportDTO.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        personalReportDTO.setCaste("BC");
                    } else if (rs.getString("Caste").equals("4")) {
                        personalReportDTO.setCaste("OC");
                    } else if (rs.getString("Caste").equals("5")) {
                        personalReportDTO.setCaste("Minority");
                    } else if (rs.getString("Caste").equals("6")) {
                        personalReportDTO.setCaste("NA");
                    }



                    if (rs.getString("Employment").equals("1")) {
                        personalReportDTO.setOccupation("Govt");
                    } else if (rs.getString("Employment").equals("2")) {
                        personalReportDTO.setOccupation("Private");
                    } else if (rs.getString("Employment").equals("3")) {
                        personalReportDTO.setOccupation("Self-Employed");
                    } else if (rs.getString("Employment").equals("4")) {
                        personalReportDTO.setOccupation("Un-Employed");
                    } else if (rs.getString("Employment").equals("5")) {
                        personalReportDTO.setOccupation("Wage-Employed");
                    }

                    personalList.add(personalReportDTO);

                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalDetailsAnalysis", "FunctionalNeedReportDAO", "DataBase");
           //con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getPersonalDetailsAnalysis");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalDetailsAnalysis", "FunctionalNeedReportDAO", "Code");
           //con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getPersonalDetailsAnalysis");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeResultSet(rs2);
            DBConnection.closeStatement(stmt);
            DBConnection.closeStatement(cstmt);

        }
        return personalList;
    }

    public ArrayList getPersonalDetailsTwoColumns(DataSource ds,
            FunctionalNeedReportDTO functionalNeedReportDTO, int start, int end, String columnNameOne,
            String columnValueOne, String columnNameTwo, String columnValueTwo, String tableName)
            throws SADAREMDBException, SQLException {

        Connection con = null;
        CallableStatement cstmt = null;
        Statement stmt = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        String district_id = null;
        String mandal_id = null;
        String village_id = null;
        String habitation_id = null;
        String subdisability = null;
        String subsubdisability = null;
        ArrayList<FunctionalNeedReportDTO> personalList = null;
        district_id = functionalNeedReportDTO.getDistrict_id();
        mandal_id = functionalNeedReportDTO.getMandal_id();
        village_id = functionalNeedReportDTO.getVillage_id();
        habitation_id = functionalNeedReportDTO.getHabitation_id();
        personalList = new ArrayList<FunctionalNeedReportDTO>();

        try {
            Date fdate = new SimpleDateFormat("dd/MM/yyyy").parse(functionalNeedReportDTO.getFromdate());
            String fromdate = new SimpleDateFormat("MM/dd/yyyy").format(fdate);

            Date tdate = new SimpleDateFormat("dd/MM/yyyy").parse(functionalNeedReportDTO.getTodate());
            String todate = new SimpleDateFormat("MM/dd/yyyy").format(tdate);

            int a[] = new int[20];
            int b[] = new int[20];
            con = DBConnection.getConnection();
            if (!district_id.equals("0") && mandal_id.equals("a")) {


                cstmt = con.prepareCall("{Call SP_SelectPersonDetails_from_MultipleColumns_for_Reports_Districtwise"
                        + "(?,?,?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, tableName);
                cstmt.setString(3, columnNameOne);
                cstmt.setString(4, columnValueOne);
                cstmt.setString(5, columnNameTwo);
                cstmt.setString(6, columnValueTwo);
                cstmt.setString(7, fromdate);
                cstmt.setString(8, todate);
                cstmt.setInt(9, start);
                cstmt.setInt(10, end);

                stmt = con.createStatement();
                rs = cstmt.executeQuery();

                while (rs.next()) {
                    FunctionalNeedReportDTO personalReportDTO = new FunctionalNeedReportDTO();
                    personalReportDTO.setPersoncode(rs.getString("Person_Code"));
                    personalReportDTO.setName(rs.getString("PersonName"));
                    personalReportDTO.setRalationName(rs.getString("Relation_Name"));
                    personalReportDTO.setAge(rs.getString("Age_Years"));

                    if (rs.getString("Gender").equals("1")) {
                        personalReportDTO.setGender("Male");
                    } else if (rs.getString("Gender").equals("2")) {
                        personalReportDTO.setGender("Female");
                    }


                    personalReportDTO.setHouseno(rs.getString("House_Number"));
                    personalReportDTO.setDistrictName(rs.getString("District_Name"));
                    personalReportDTO.setMandalName(rs.getString("Mandal_Name"));
                    personalReportDTO.setVillageName(rs.getString("Village_Name"));
                    personalReportDTO.setHabitationName(rs.getString("Habitation_Name"));
                    personalReportDTO.setDisabilityType(rs.getString("Disability_Name").substring(2));


                    if (rs.getString("Sub_Disability_ID") != null) {
                        a = convertToIntArray(rs.getString("Sub_Disability_ID"));
                        stmt = con.createStatement();

                        subdisability = null;

                        for (int i = 0; i < a.length; i++) {
                            rs2 = stmt.executeQuery("select Sub_Disability_Name from tblSubDisability_Details with(nolock) "
                                    + "where Sub_Disability_ID=" + a[i] + "");
                            while (rs2.next()) {
                                if (subdisability == null) {
                                    subdisability = rs2.getString("Sub_Disability_Name").substring(2);
                                } else {
                                    subdisability = subdisability + "," + rs2.getString("Sub_Disability_Name").substring(2);
                                }

                            }
                        }
                        personalReportDTO.setSubtypeofDisability(subdisability);
                    }



                    if (rs.getString("Sub_Sub_Disability_ID") != null) {

                        b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                        stmt = con.createStatement();
                        subsubdisability = null;
                        for (int i = 0; i < b.length; i++) {
                            rs2 = stmt.executeQuery("select Sub_Sub_Disability_Name from tblSub_Sub_Disabilities with(nolock) "
                                    + "where Sub_Sub_Disability_ID=" + b[i] + "");
                            while (rs2.next()) {
                                if (subsubdisability == null) {
                                    subsubdisability = rs2.getString("Sub_Sub_Disability_Name").substring(2);
                                } else {
                                    subsubdisability = subsubdisability + "," + rs2.getString("Sub_Sub_Disability_Name").substring(2);
                                }
                            }
                        }
                        personalReportDTO.setPartsInvolved(subsubdisability);
                    }

                    int number = new Float((rs.getString("TotalDisability"))).intValue();
                    String percentage = Integer.toString(number);
                    personalReportDTO.setTotalpercent(percentage);


                    if (rs.getString("Caste").equals("1")) {
                        personalReportDTO.setCaste("ST");
                    } else if (rs.getString("Caste").equals("2")) {
                        personalReportDTO.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        personalReportDTO.setCaste("BC");
                    } else if (rs.getString("Caste").equals("4")) {
                        personalReportDTO.setCaste("OC");
                    } else if (rs.getString("Caste").equals("5")) {
                        personalReportDTO.setCaste("Minority");
                    } else if (rs.getString("Caste").equals("6")) {
                        personalReportDTO.setCaste("NA");
                    }



                    if (rs.getString("Employment").equals("1")) {
                        personalReportDTO.setOccupation("Govt");
                    } else if (rs.getString("Employment").equals("2")) {
                        personalReportDTO.setOccupation("Private");
                    } else if (rs.getString("Employment").equals("3")) {
                        personalReportDTO.setOccupation("Self-Employed");
                    } else if (rs.getString("Employment").equals("4")) {
                        personalReportDTO.setOccupation("Un-Employed");
                    } else if (rs.getString("Employment").equals("5")) {
                        personalReportDTO.setOccupation("Wage-Employed");
                    }

                    personalList.add(personalReportDTO);

                }



            } else if (!mandal_id.equals("0") && village_id.equals("a")) {
                cstmt = con.prepareCall("{Call SP_SelectPersonDetails_from_MultipleColumns_for_Reports_Mandalwise"
                        + "(?,?,?,?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setString(3, tableName);
                cstmt.setString(4, columnNameOne);
                cstmt.setString(5, columnValueOne);
                cstmt.setString(6, columnNameTwo);
                cstmt.setString(7, columnValueTwo);
                cstmt.setString(8, fromdate);
                cstmt.setString(9, todate);
                cstmt.setInt(10, start);
                cstmt.setInt(11, end);

                stmt = con.createStatement();
                rs = cstmt.executeQuery();

                while (rs.next()) {
                    FunctionalNeedReportDTO personalReportDTO = new FunctionalNeedReportDTO();
                    personalReportDTO.setPersoncode(rs.getString("Person_Code"));
                    personalReportDTO.setName(rs.getString("PersonName"));
                    personalReportDTO.setRalationName(rs.getString("Relation_Name"));
                    personalReportDTO.setAge(rs.getString("Age_Years"));

                    if (rs.getString("Gender").equals("1")) {
                        personalReportDTO.setGender("Male");
                    } else if (rs.getString("Gender").equals("2")) {
                        personalReportDTO.setGender("Female");
                    }


                    personalReportDTO.setHouseno(rs.getString("House_Number"));
                    personalReportDTO.setDistrictName(rs.getString("District_Name"));
                    personalReportDTO.setMandalName(rs.getString("Mandal_Name"));
                    personalReportDTO.setVillageName(rs.getString("Village_Name"));
                    personalReportDTO.setHabitationName(rs.getString("Habitation_Name"));
                    personalReportDTO.setDisabilityType(rs.getString("Disability_Name").substring(2));


                    if (rs.getString("Sub_Disability_ID") != null) {
                        a = convertToIntArray(rs.getString("Sub_Disability_ID"));
                        stmt = con.createStatement();

                        subdisability = null;

                        for (int i = 0; i < a.length; i++) {
                            rs2 = stmt.executeQuery("select Sub_Disability_Name from tblSubDisability_Details with(nolock) "
                                    + "where Sub_Disability_ID=" + a[i] + "");
                            while (rs2.next()) {
                                if (subdisability == null) {
                                    subdisability = rs2.getString("Sub_Disability_Name").substring(2);
                                } else {
                                    subdisability = subdisability + "," + rs2.getString("Sub_Disability_Name").substring(2);
                                }

                            }
                        }
                        personalReportDTO.setSubtypeofDisability(subdisability);
                    }



                    if (rs.getString("Sub_Sub_Disability_ID") != null) {

                        b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                        stmt = con.createStatement();
                        subsubdisability = null;
                        for (int i = 0; i < b.length; i++) {
                            rs2 = stmt.executeQuery("select Sub_Sub_Disability_Name from tblSub_Sub_Disabilities with(nolock)  "
                                    + "where Sub_Sub_Disability_ID=" + b[i] + "");
                            while (rs2.next()) {
                                if (subsubdisability == null) {
                                    subsubdisability = rs2.getString("Sub_Sub_Disability_Name").substring(2);
                                } else {
                                    subsubdisability = subsubdisability + "," + rs2.getString("Sub_Sub_Disability_Name").substring(2);
                                }
                            }
                        }
                        personalReportDTO.setPartsInvolved(subsubdisability);
                    }

                    int number = new Float((rs.getString("TotalDisability"))).intValue();
                    String percentage = Integer.toString(number);
                    personalReportDTO.setTotalpercent(percentage);


                    if (rs.getString("Caste").equals("1")) {
                        personalReportDTO.setCaste("ST");
                    } else if (rs.getString("Caste").equals("2")) {
                        personalReportDTO.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        personalReportDTO.setCaste("BC");
                    } else if (rs.getString("Caste").equals("4")) {
                        personalReportDTO.setCaste("OC");
                    } else if (rs.getString("Caste").equals("5")) {
                        personalReportDTO.setCaste("Minority");
                    } else if (rs.getString("Caste").equals("6")) {
                        personalReportDTO.setCaste("NA");
                    }



                    if (rs.getString("Employment").equals("1")) {
                        personalReportDTO.setOccupation("Govt");
                    } else if (rs.getString("Employment").equals("2")) {
                        personalReportDTO.setOccupation("Private");
                    } else if (rs.getString("Employment").equals("3")) {
                        personalReportDTO.setOccupation("Self-Employed");
                    } else if (rs.getString("Employment").equals("4")) {
                        personalReportDTO.setOccupation("Un-Employed");
                    } else if (rs.getString("Employment").equals("5")) {
                        personalReportDTO.setOccupation("Wage-Employed");
                    }

                    personalList.add(personalReportDTO);

                }
            } else if (village_id.equals("0")) {
                cstmt = con.prepareCall("{Call SP_SelectPersonDetails_from_MultipleColumns_for_Reports_Villagewise"
                        + "(?,?,?,?,?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setString(3, village_id);
                cstmt.setString(4, tableName);
                cstmt.setString(5, columnNameOne);
                cstmt.setString(6, columnValueOne);
                cstmt.setString(7, columnNameTwo);
                cstmt.setString(8, columnValueTwo);
                cstmt.setString(9, fromdate);
                cstmt.setString(10, todate);
                cstmt.setInt(11, start);
                cstmt.setInt(12, end);

                stmt = con.createStatement();
                rs = cstmt.executeQuery();

                while (rs.next()) {
                    FunctionalNeedReportDTO personalReportDTO = new FunctionalNeedReportDTO();
                    personalReportDTO.setPersoncode(rs.getString("Person_Code"));
                    personalReportDTO.setName(rs.getString("PersonName"));
                    personalReportDTO.setRalationName(rs.getString("Relation_Name"));
                    personalReportDTO.setAge(rs.getString("Age_Years"));

                    if (rs.getString("Gender").equals("1")) {
                        personalReportDTO.setGender("Male");
                    } else if (rs.getString("Gender").equals("2")) {
                        personalReportDTO.setGender("Female");
                    }


                    personalReportDTO.setHouseno(rs.getString("House_Number"));
                    personalReportDTO.setDistrictName(rs.getString("District_Name"));
                    personalReportDTO.setMandalName(rs.getString("Mandal_Name"));
                    personalReportDTO.setVillageName(rs.getString("Village_Name"));
                    personalReportDTO.setHabitationName(rs.getString("Habitation_Name"));
                    personalReportDTO.setDisabilityType(rs.getString("Disability_Name").substring(2));


                    if (rs.getString("Sub_Disability_ID") != null) {
                        a = convertToIntArray(rs.getString("Sub_Disability_ID"));
                        stmt = con.createStatement();

                        subdisability = null;

                        for (int i = 0; i < a.length; i++) {
                            rs2 = stmt.executeQuery("select Sub_Disability_Name from tblSubDisability_Details with(nolock) "
                                    + "where Sub_Disability_ID=" + a[i] + "");
                            while (rs2.next()) {
                                if (subdisability == null) {
                                    subdisability = rs2.getString("Sub_Disability_Name").substring(2);
                                } else {
                                    subdisability = subdisability + "," + rs2.getString("Sub_Disability_Name").substring(2);
                                }

                            }
                        }
                        personalReportDTO.setSubtypeofDisability(subdisability);
                    }



                    if (rs.getString("Sub_Sub_Disability_ID") != null) {

                        b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                        stmt = con.createStatement();
                        subsubdisability = null;
                        for (int i = 0; i < b.length; i++) {
                            rs2 = stmt.executeQuery("select Sub_Sub_Disability_Name from tblSub_Sub_Disabilities  with(nolock) "
                                    + "where Sub_Sub_Disability_ID=" + b[i] + "");
                            while (rs2.next()) {
                                if (subsubdisability == null) {
                                    subsubdisability = rs2.getString("Sub_Sub_Disability_Name").substring(2);
                                } else {
                                    subsubdisability = subsubdisability + "," + rs2.getString("Sub_Sub_Disability_Name").substring(2);
                                }
                            }
                        }
                        personalReportDTO.setPartsInvolved(subsubdisability);
                    }

                    int number = new Float((rs.getString("TotalDisability"))).intValue();
                    String percentage = Integer.toString(number);
                    personalReportDTO.setTotalpercent(percentage);


                    if (rs.getString("Caste").equals("1")) {
                        personalReportDTO.setCaste("ST");
                    } else if (rs.getString("Caste").equals("2")) {
                        personalReportDTO.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        personalReportDTO.setCaste("BC");
                    } else if (rs.getString("Caste").equals("4")) {
                        personalReportDTO.setCaste("OC");
                    } else if (rs.getString("Caste").equals("5")) {
                        personalReportDTO.setCaste("Minority");
                    } else if (rs.getString("Caste").equals("6")) {
                        personalReportDTO.setCaste("NA");
                    }



                    if (rs.getString("Employment").equals("1")) {
                        personalReportDTO.setOccupation("Govt");
                    } else if (rs.getString("Employment").equals("2")) {
                        personalReportDTO.setOccupation("Private");
                    } else if (rs.getString("Employment").equals("3")) {
                        personalReportDTO.setOccupation("Self-Employed");
                    } else if (rs.getString("Employment").equals("4")) {
                        personalReportDTO.setOccupation("Un-Employed");
                    } else if (rs.getString("Employment").equals("5")) {
                        personalReportDTO.setOccupation("Wage-Employed");
                    }

                    personalList.add(personalReportDTO);

                }
            } else if (habitation_id.equals("0")) {
                cstmt = con.prepareCall("{Call SP_SelectPersonDetails_from_MultipleColumns_for_Reports_Habitationwise"
                        + "(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);
                cstmt.setString(3, village_id);
                cstmt.setString(4, habitation_id);
                cstmt.setString(5, tableName);
                cstmt.setString(6, columnNameOne);
                cstmt.setString(7, columnValueOne);
                cstmt.setString(8, columnNameTwo);
                cstmt.setString(9, columnValueTwo);
                cstmt.setString(10, fromdate);
                cstmt.setString(11, todate);
                cstmt.setInt(12, start);
                cstmt.setInt(13, end);

                stmt = con.createStatement();
                rs = cstmt.executeQuery();

                while (rs.next()) {
                    FunctionalNeedReportDTO personalReportDTO = new FunctionalNeedReportDTO();
                    personalReportDTO.setPersoncode(rs.getString("Person_Code"));
                    personalReportDTO.setName(rs.getString("PersonName"));
                    personalReportDTO.setRalationName(rs.getString("Relation_Name"));
                    personalReportDTO.setAge(rs.getString("Age_Years"));

                    if (rs.getString("Gender").equals("1")) {
                        personalReportDTO.setGender("Male");
                    } else if (rs.getString("Gender").equals("2")) {
                        personalReportDTO.setGender("Female");
                    }


                    personalReportDTO.setHouseno(rs.getString("House_Number"));
                    personalReportDTO.setDistrictName(rs.getString("District_Name"));
                    personalReportDTO.setMandalName(rs.getString("Mandal_Name"));
                    personalReportDTO.setVillageName(rs.getString("Village_Name"));
                    personalReportDTO.setHabitationName(rs.getString("Habitation_Name"));
                    personalReportDTO.setDisabilityType(rs.getString("Disability_Name").substring(2));


                    if (rs.getString("Sub_Disability_ID") != null) {
                        a = convertToIntArray(rs.getString("Sub_Disability_ID"));
                        stmt = con.createStatement();

                        subdisability = null;

                        for (int i = 0; i < a.length; i++) {
                            rs2 = stmt.executeQuery("select Sub_Disability_Name from tblSubDisability_Details  with(nolock) "
                                    + "where Sub_Disability_ID=" + a[i] + "");
                            while (rs2.next()) {
                                if (subdisability == null) {
                                    subdisability = rs2.getString("Sub_Disability_Name").substring(2);
                                } else {
                                    subdisability = subdisability + "," + rs2.getString("Sub_Disability_Name").substring(2);
                                }

                            }
                        }
                        personalReportDTO.setSubtypeofDisability(subdisability);
                    }



                    if (rs.getString("Sub_Sub_Disability_ID") != null) {

                        b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                        stmt = con.createStatement();
                        subsubdisability = null;
                        for (int i = 0; i < b.length; i++) {
                            rs2 = stmt.executeQuery("select Sub_Sub_Disability_Name from tblSub_Sub_Disabilities with(nolock) "
                                    + "where Sub_Sub_Disability_ID=" + b[i] + "");
                            while (rs2.next()) {
                                if (subsubdisability == null) {
                                    subsubdisability = rs2.getString("Sub_Sub_Disability_Name").substring(2);
                                } else {
                                    subsubdisability = subsubdisability + "," + rs2.getString("Sub_Sub_Disability_Name").substring(2);
                                }
                            }
                        }
                        personalReportDTO.setPartsInvolved(subsubdisability);
                    }

                    int number = new Float((rs.getString("TotalDisability"))).intValue();
                    String percentage = Integer.toString(number);
                    personalReportDTO.setTotalpercent(percentage);


                    if (rs.getString("Caste").equals("1")) {
                        personalReportDTO.setCaste("ST");
                    } else if (rs.getString("Caste").equals("2")) {
                        personalReportDTO.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        personalReportDTO.setCaste("BC");
                    } else if (rs.getString("Caste").equals("4")) {
                        personalReportDTO.setCaste("OC");
                    } else if (rs.getString("Caste").equals("5")) {
                        personalReportDTO.setCaste("Minority");
                    } else if (rs.getString("Caste").equals("6")) {
                        personalReportDTO.setCaste("NA");
                    }



                    if (rs.getString("Employment").equals("1")) {
                        personalReportDTO.setOccupation("Govt");
                    } else if (rs.getString("Employment").equals("2")) {
                        personalReportDTO.setOccupation("Private");
                    } else if (rs.getString("Employment").equals("3")) {
                        personalReportDTO.setOccupation("Self-Employed");
                    } else if (rs.getString("Employment").equals("4")) {
                        personalReportDTO.setOccupation("Un-Employed");
                    } else if (rs.getString("Employment").equals("5")) {
                        personalReportDTO.setOccupation("Wage-Employed");
                    }

                    personalList.add(personalReportDTO);

                }
            } else if (habitation_id != null && !habitation_id.equals("0")) {
                cstmt = con.prepareCall("{Call SP_SelectPersonDetails_for_Reports_Habitationwise(?,?,?,?,?,?,?,?,?,?,?)}");
                cstmt = con.prepareCall("{Call SP_SelectPersonDetails_from_MultipleColumns_for_Reports_Districtwise"
                        + "(?,?,?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, district_id);
                cstmt.setString(2, tableName);
                cstmt.setString(3, columnNameOne);
                cstmt.setString(4, columnValueOne);
                cstmt.setString(5, columnNameTwo);
                cstmt.setString(6, columnValueTwo);
                cstmt.setString(7, fromdate);
                cstmt.setString(8, todate);
                cstmt.setInt(9, start);
                cstmt.setInt(10, end);

                stmt = con.createStatement();
                rs = cstmt.executeQuery();

                while (rs.next()) {
                    FunctionalNeedReportDTO personalReportDTO = new FunctionalNeedReportDTO();
                    personalReportDTO.setPersoncode(rs.getString("Person_Code"));
                    personalReportDTO.setName(rs.getString("PersonName"));
                    personalReportDTO.setRalationName(rs.getString("Relation_Name"));
                    personalReportDTO.setAge(rs.getString("Age_Years"));

                    if (rs.getString("Gender").equals("1")) {
                        personalReportDTO.setGender("Male");
                    } else if (rs.getString("Gender").equals("2")) {
                        personalReportDTO.setGender("Female");
                    }

                    personalReportDTO.setHouseno(rs.getString("House_Number"));
                    personalReportDTO.setDistrictName(rs.getString("District_Name"));
                    personalReportDTO.setMandalName(rs.getString("Mandal_Name"));
                    personalReportDTO.setVillageName(rs.getString("Village_Name"));
                    personalReportDTO.setHabitationName(rs.getString("Habitation_Name"));
                    personalReportDTO.setDisabilityType(rs.getString("Disability_Name").substring(2));


                    if (rs.getString("Sub_Disability_ID") != null) {
                        a = convertToIntArray(rs.getString("Sub_Disability_ID"));
                        stmt = con.createStatement();

                        subdisability = null;

                        for (int i = 0; i < a.length; i++) {
                            rs2 = stmt.executeQuery("select Sub_Disability_Name from tblSubDisability_Details with(nolock) "
                                    + "where Sub_Disability_ID=" + a[i] + "");
                            while (rs2.next()) {
                                if (subdisability == null) {
                                    subdisability = rs2.getString("Sub_Disability_Name").substring(2);
                                } else {
                                    subdisability = subdisability + "," + rs2.getString("Sub_Disability_Name").substring(2);
                                }

                            }
                        }
                        personalReportDTO.setSubtypeofDisability(subdisability);
                    }



                    if (rs.getString("Sub_Sub_Disability_ID") != null) {

                        b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                        stmt = con.createStatement();
                        subsubdisability = null;
                        for (int i = 0; i < b.length; i++) {
                            rs2 = stmt.executeQuery("select Sub_Sub_Disability_Name from tblSub_Sub_Disabilities  with(nolock) "
                                    + "where Sub_Sub_Disability_ID=" + b[i] + "");
                            while (rs2.next()) {
                                if (subsubdisability == null) {
                                    subsubdisability = rs2.getString("Sub_Sub_Disability_Name").substring(2);
                                } else {
                                    subsubdisability = subsubdisability + "," + rs2.getString("Sub_Sub_Disability_Name").substring(2);
                                }
                            }
                        }
                        personalReportDTO.setPartsInvolved(subsubdisability);
                    }

                    int number = new Float((rs.getString("TotalDisability"))).intValue();
                    String percentage = Integer.toString(number);
                    personalReportDTO.setTotalpercent(percentage);


                    if (rs.getString("Caste").equals("1")) {
                        personalReportDTO.setCaste("ST");
                    } else if (rs.getString("Caste").equals("2")) {
                        personalReportDTO.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        personalReportDTO.setCaste("BC");
                    } else if (rs.getString("Caste").equals("4")) {
                        personalReportDTO.setCaste("OC");
                    } else if (rs.getString("Caste").equals("5")) {
                        personalReportDTO.setCaste("Minority");
                    } else if (rs.getString("Caste").equals("6")) {
                        personalReportDTO.setCaste("NA");
                    }



                    if (rs.getString("Employment").equals("1")) {
                        personalReportDTO.setOccupation("Govt");
                    } else if (rs.getString("Employment").equals("2")) {
                        personalReportDTO.setOccupation("Private");
                    } else if (rs.getString("Employment").equals("3")) {
                        personalReportDTO.setOccupation("Self-Employed");
                    } else if (rs.getString("Employment").equals("4")) {
                        personalReportDTO.setOccupation("Un-Employed");
                    } else if (rs.getString("Employment").equals("5")) {
                        personalReportDTO.setOccupation("Wage-Employed");
                    }

                    personalList.add(personalReportDTO);

                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalDetailsTwoColumns", "FunctionalNeedReportDAO", "DataBase");
         // con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getPersonalDetailsTwoColumns");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalDetailsTwoColumns", "FunctionalNeedReportDAO", "Code");
           //con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getPersonalDetailsTwoColumns");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

            DBConnection.closeResultSet(rs2);
            DBConnection.closeStatement(stmt);
        }
        return personalList;
    }

    /**
     * this method fetches Who are not Come to SADAREM Camp information from database
     * @param ds
     * @param dailydisabilityandpercentagedto
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public ArrayList getNotComeToSadaremCamp(DataSource ds,
            String districtID, String phaseName)
            throws SADAREMDBException, SQLException {

        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        ArrayList<FunctionalNeedReportDTO> notCometoSadaremList = null;
        notCometoSadaremList = new ArrayList<FunctionalNeedReportDTO>();
        try {

            con = DBConnection.getConnection();
            cstmt = con.prepareCall("{Call dbo.SP_WHOARENOTCAMETOSADAREMCAMP_MANDALWISE_ABSTRACT_new(?,?)}");

            cstmt.setString(1, districtID);
            if (phaseName != null && (phaseName.equalsIgnoreCase("RachaBandaI") || phaseName.equalsIgnoreCase("RachaBandaII") || phaseName.equalsIgnoreCase("After RachaBandaI") || phaseName.equalsIgnoreCase("After RachaBandaII") || phaseName.equalsIgnoreCase("After RachaBandaIII") || phaseName.equalsIgnoreCase("After RachaBandaIV"))) {
                cstmt.setString(2, "RachaBanda");
            } else if (phaseName != null && phaseName.equalsIgnoreCase("Reassment")) {
                cstmt.setString(2, "Reassment");
            } else {
                cstmt.setString(2, phaseName);
            }

            rs = cstmt.executeQuery();

            if (phaseName != null && phaseName.equalsIgnoreCase("RachaBandaI")) {

                while (rs.next()) {
                    FunctionalNeedReportDTO notCometoSadaremCampDTO =
                            new FunctionalNeedReportDTO();
                    notCometoSadaremCampDTO.setMandal_id(rs.getString("MANDALID"));
                    notCometoSadaremCampDTO.setMandalName(rs.getString("MANDALNAME"));
                    notCometoSadaremCampDTO.setOnlyPartA(rs.getInt("PARTARBI"));
                    notCometoSadaremCampDTO.setNotComrtoCamp(rs.getInt("EXISTINGPENSIONERS"));
                    notCometoSadaremList.add(notCometoSadaremCampDTO);


                }
            } else if (phaseName != null && phaseName.equalsIgnoreCase("RachaBandaII")) {

                while (rs.next()) {
                    FunctionalNeedReportDTO notCometoSadaremCampDTO =
                            new FunctionalNeedReportDTO();
                    notCometoSadaremCampDTO.setMandal_id(rs.getString("MANDALID"));
                    notCometoSadaremCampDTO.setMandalName(rs.getString("MANDALNAME"));
                    notCometoSadaremCampDTO.setOnlyPartA(rs.getInt("PARTARBII"));
                    notCometoSadaremCampDTO.setNotComrtoCamp(rs.getInt("RACHHABANDAII"));
                    notCometoSadaremList.add(notCometoSadaremCampDTO);


                }
            } else if (phaseName != null && phaseName.equalsIgnoreCase("After RachaBandaI")) {

                while (rs.next()) {
                    FunctionalNeedReportDTO notCometoSadaremCampDTO =
                            new FunctionalNeedReportDTO();
                    notCometoSadaremCampDTO.setMandal_id(rs.getString("MANDALID"));
                    notCometoSadaremCampDTO.setMandalName(rs.getString("MANDALNAME"));
                    notCometoSadaremCampDTO.setOnlyPartA(rs.getInt("PARTARBIII"));
                    notCometoSadaremCampDTO.setNotComrtoCamp(rs.getInt("RACHHABANDAIII"));
                    notCometoSadaremList.add(notCometoSadaremCampDTO);


                }

            } else if (phaseName != null && phaseName.equalsIgnoreCase("After RachaBandaII")) {

                while (rs.next()) {
                    FunctionalNeedReportDTO notCometoSadaremCampDTO =
                            new FunctionalNeedReportDTO();
                    notCometoSadaremCampDTO.setMandal_id(rs.getString("MANDALID"));
                    notCometoSadaremCampDTO.setMandalName(rs.getString("MANDALNAME"));
                    notCometoSadaremCampDTO.setOnlyPartA(rs.getInt("PARTARBIV"));
                    notCometoSadaremCampDTO.setNotComrtoCamp(rs.getInt("RACHHABANDAIV"));
                    notCometoSadaremList.add(notCometoSadaremCampDTO);


                }

            } else if (phaseName != null && phaseName.equalsIgnoreCase("After RachaBandaIII")) {

                while (rs.next()) {
                    FunctionalNeedReportDTO notCometoSadaremCampDTO =
                            new FunctionalNeedReportDTO();
                    notCometoSadaremCampDTO.setMandal_id(rs.getString("MANDALID"));
                    notCometoSadaremCampDTO.setMandalName(rs.getString("MANDALNAME"));
                    notCometoSadaremCampDTO.setOnlyPartA(rs.getInt("PARTARBV"));
                    notCometoSadaremCampDTO.setNotComrtoCamp(rs.getInt("RACHHABANDAV"));
                    notCometoSadaremList.add(notCometoSadaremCampDTO);

                }
            } else if (phaseName != null && phaseName.equalsIgnoreCase("After RachaBandaIV")) {

                while (rs.next()) {
                    FunctionalNeedReportDTO notCometoSadaremCampDTO =
                            new FunctionalNeedReportDTO();
                    notCometoSadaremCampDTO.setMandal_id(rs.getString("MANDALID"));
                    notCometoSadaremCampDTO.setMandalName(rs.getString("MANDALNAME"));
                    notCometoSadaremCampDTO.setOnlyPartA(rs.getInt("PARTARBVI"));
                    notCometoSadaremCampDTO.setNotComrtoCamp(rs.getInt("RACHHABANDAVI"));
                    notCometoSadaremList.add(notCometoSadaremCampDTO);


                }

            } else if (phaseName != null && (phaseName.equalsIgnoreCase("Renual") || phaseName.equalsIgnoreCase("Reassment"))) {

                while (rs.next()) {
                    FunctionalNeedReportDTO notCometoSadaremCampDTO =
                            new FunctionalNeedReportDTO();
                    notCometoSadaremCampDTO.setMandal_id(rs.getString("MANDALID"));
                    notCometoSadaremCampDTO.setMandalName(rs.getString("MANDALNAME"));
                    notCometoSadaremCampDTO.setOnlyPartA(rs.getInt("PARTA"));
                    //notCometoSadaremCampDTO.setNotComrtoCamp(rs.getInt("EXISTINGPENSIONERS"));
                    notCometoSadaremList.add(notCometoSadaremCampDTO);


                }
            } else {
                while (rs.next()) {
                    FunctionalNeedReportDTO notCometoSadaremCampDTO =new FunctionalNeedReportDTO();
                    notCometoSadaremCampDTO.setMandal_id(rs.getString("MANDALID"));
                    notCometoSadaremCampDTO.setMandalName(rs.getString("MANDALNAME"));
                    notCometoSadaremCampDTO.setOnlyPartA(rs.getInt("PARTA"));
                    notCometoSadaremCampDTO.setNotComrtoCamp(rs.getInt("EXISTINGPENSIONERS"));//EXISTINGPENSIONERS
                    notCometoSadaremList.add(notCometoSadaremCampDTO);
                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getNotComeToSadaremCamp", "FunctionalNeedReportDAO", "DataBase");
          // con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getNotComeToSadaremCamp");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getNotComeToSadaremCamp", "FunctionalNeedReportDAO", "Code");
           //con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getNotComeToSadaremCamp");
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }
        return notCometoSadaremList;
    }

    /**
     * this method fetches Who are not Come to SADAREM Camp personal information from database
     * @param ds
     * @param dailydisabilityandpercentagedto
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public ArrayList getNotComeToCampPersonalDetails(DataSource ds,
            String districtID, String mandalID, String phaseName, String reportType)
            throws SADAREMDBException, SQLException {

        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        ArrayList<FunctionalNeedReportDTO> notCometoSadaremPersonalList = null;
        notCometoSadaremPersonalList = new ArrayList<FunctionalNeedReportDTO>();
        try {
            con = DBConnection.getConnection();
            if (reportType.equals("parta")) {
                cstmt = con.prepareCall("{Call SP_PERSONALDETAILS_ONLYFORPARTAPERSONS_NEW(?,?,?)}");
                cstmt.setString(1, districtID);
                cstmt.setString(2, mandalID);
                if (phaseName != null && phaseName.equalsIgnoreCase("Reassment")) {
                    cstmt.setString(3, "Reassement");
                } else {
                    cstmt.setString(3, phaseName);
                }

                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO onlyPartAPersonalDTO =
                            new FunctionalNeedReportDTO();
                    onlyPartAPersonalDTO.setPensionid(rs.getString("PENSIONCARD_NO"));
                    onlyPartAPersonalDTO.setPersoncode(rs.getString("PERSON_CODE"));
                    onlyPartAPersonalDTO.setName(rs.getString("PERSONNAME"));
                    onlyPartAPersonalDTO.setRalationName(rs.getString("RELATION_NAME"));
                    onlyPartAPersonalDTO.setGender(rs.getString("Gender"));
                    onlyPartAPersonalDTO.setAge(rs.getString("AGE_YEARS"));
                    onlyPartAPersonalDTO.setRationcard(rs.getString("RATIONCARD_NUMBER"));
                    onlyPartAPersonalDTO.setMandalName(rs.getString("MANDAL_NAME"));
                    onlyPartAPersonalDTO.setVillageName(rs.getString("VILLAGE_NAME"));
                    onlyPartAPersonalDTO.setHabitationName(rs.getString("HABITATION_NAME"));
                    onlyPartAPersonalDTO.setDisabilityType(rs.getString("TYPEOF_DISABILITY"));
                    onlyPartAPersonalDTO.setPhase(rs.getString("PENSIONPHASE"));
                    onlyPartAPersonalDTO.setHouseno(rs.getString("HOUSE_NUMBER"));
                    onlyPartAPersonalDTO.setCategoryID(rs.getString("Catid"));
                    notCometoSadaremPersonalList.add(onlyPartAPersonalDTO);
                }

            } else if (reportType.equals("notcome")) {
                cstmt = con.prepareCall("{Call SP_PERSONALDETAILS_NOTCOMETOSADAREM(?,?,?)}");
                cstmt.setString(1, districtID);
                cstmt.setString(2, mandalID);
                cstmt.setString(3, phaseName);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    FunctionalNeedReportDTO ontCometoCampPersonalDTO =
                            new FunctionalNeedReportDTO();
                    ontCometoCampPersonalDTO.setPensionid(rs.getString("PENSIONID"));
                    ontCometoCampPersonalDTO.setFirstName(rs.getString("FIRSTNAME"));
                    ontCometoCampPersonalDTO.setMiddleName(rs.getString("MID_NAME"));
                    ontCometoCampPersonalDTO.setLastName(rs.getString("LASTNAME"));
                    ontCometoCampPersonalDTO.setRalationName(rs.getString("FNAME"));
                    ontCometoCampPersonalDTO.setGender(rs.getString("SEX"));
                    ontCometoCampPersonalDTO.setAge(rs.getString("AGE"));
                    ontCometoCampPersonalDTO.setRationcard(rs.getString("RATIONCARDNO"));
                    ontCometoCampPersonalDTO.setMandalName(rs.getString("MANDAL_NAME"));
                    ontCometoCampPersonalDTO.setVillageName(rs.getString("VILLAGE"));
                    ontCometoCampPersonalDTO.setHabitationName(rs.getString("HABITATION_NAME"));
                    ontCometoCampPersonalDTO.setPhase(rs.getString("PENSIONPHASE"));
                    ontCometoCampPersonalDTO.setHouseno(rs.getString("HNO"));
                    ontCometoCampPersonalDTO.setSubtypeofDisability(rs.getString("DISABILITY"));
                    ontCometoCampPersonalDTO.setCategoryID("Not Assessed");
                    ontCometoCampPersonalDTO.setDisabilityType(rs.getString("Phc"));
                    ontCometoCampPersonalDTO.setDataReceivedDate(rs.getString("DATARECEIVEDDATE"));
                    notCometoSadaremPersonalList.add(ontCometoCampPersonalDTO);
                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getNotComeToCampPersonalDetails", "FunctionalNeedReportDAO", "DataBase");
           //con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getNotComeToCampPersonalDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getNotComeToCampPersonalDetails", "FunctionalNeedReportDAO", "Code");
            //DBConnection.rollbackConnection(con);
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getNotComeToCampPersonalDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }
        return notCometoSadaremPersonalList;
    }

    public ArrayList getPersonalDetailsSurgery(DataSource ds,
            FunctionalNeedReportDTO functionalNeedReportDTO, String columnName,
            String tableName)
            throws SADAREMDBException, SQLException {

        Connection con = null;
        CallableStatement cstmt = null;
        Statement stmt = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        String district_id = null;
        String mandal_id = null;
        String village_id = null;
        String habitation_id = null;
        String subdisability = null;
        String subsubdisability = null;
        String typeofDisability = null;
        typeofDisability = functionalNeedReportDTO.getDisabilityType();
        ArrayList<FunctionalNeedReportDTO> personalList = null;
        district_id = functionalNeedReportDTO.getDistrict_id();
        mandal_id = functionalNeedReportDTO.getMandal_id();
        village_id = functionalNeedReportDTO.getVillage_id();
        habitation_id = functionalNeedReportDTO.getHabitation_id();
        if (mandal_id.equals("a")) {
            mandal_id = "ALL";
        }
        if (village_id.equals("a")) {
            village_id = "ALL";
        }
        if (habitation_id.equals("a")) {
            habitation_id = "ALL";
        }
        personalList = new ArrayList<FunctionalNeedReportDTO>();

        try {
            Date fdate = new SimpleDateFormat("dd/MM/yyyy").parse(functionalNeedReportDTO.getFromdate());
            String fromdate = new SimpleDateFormat("MM/dd/yyyy").format(fdate);

            Date tdate = new SimpleDateFormat("dd/MM/yyyy").parse(functionalNeedReportDTO.getTodate());
            String todate = new SimpleDateFormat("MM/dd/yyyy").format(tdate);

            int a[] = new int[20];
            int b[] = new int[20];
            con = DBConnection.getConnection();


            cstmt = con.prepareCall("{Call SP_PERSONALDETAILSFOR_SURGERY_OTHERNEEDS(?,?,?,?,?,?,?,?)}");
            cstmt.setString(1, district_id);
            cstmt.setString(2, mandal_id);
            cstmt.setString(3, village_id);
            cstmt.setString(4, habitation_id);
            cstmt.setString(5, fromdate);
            cstmt.setString(6, todate);
            cstmt.setString(7, tableName);
            cstmt.setString(8, columnName);

            stmt = con.createStatement();
            rs = cstmt.executeQuery();

            while (rs.next()) {
                FunctionalNeedReportDTO personalReportDTO = new FunctionalNeedReportDTO();
                personalReportDTO.setPersoncode(rs.getString("PERSON_CODE"));
                personalReportDTO.setName(rs.getString("PERSONNAME"));
                personalReportDTO.setAge(rs.getString("AGE_YEARS"));

                if (rs.getString("GENDER").equals("1")) {
                    personalReportDTO.setGender("Male");
                } else if (rs.getString("Gender").equals("2")) {
                    personalReportDTO.setGender("Female");
                } else {
                    personalReportDTO.setGender("Not Entered");
                }


                personalReportDTO.setHouseno(rs.getString("HOUSE_NUMBER"));
                personalReportDTO.setDistrictName(rs.getString("DISTRICT_NAME"));
                personalReportDTO.setMandalName(rs.getString("MANDAL_NAME"));
                personalReportDTO.setVillageName(rs.getString("VILLAGE_NAME"));
                personalReportDTO.setHabitationName(rs.getString("HABITATION_NAME"));
                personalReportDTO.setDisabilityType(rs.getString("DISABILITY_NAME").substring(2));


                if (rs.getString("SUB_DISABILITY_ID") != null) {
                    a = convertToIntArray(rs.getString("Sub_Disability_ID"));
                    stmt = con.createStatement();

                    subdisability = null;

                    for (int i = 0; i < a.length; i++) {
                        rs2 = stmt.executeQuery("select Sub_Disability_Name from tblSubDisability_Details with(nolock) "
                                + "where Sub_Disability_ID=" + a[i] + "");
                        while (rs2.next()) {
                            if (subdisability == null) {
                                if (typeofDisability.equals("1")) {
                                    subdisability = rs2.getString("Sub_Disability_Name").substring(2);
                                } else {
                                    subdisability = rs2.getString("Sub_Disability_Name");
                                }
                            } else {
                                if (typeofDisability.equals("1")) {
                                    subdisability = subdisability + "," + rs2.getString("Sub_Disability_Name").substring(2);
                                } else {
                                    subdisability = subdisability + "," + rs2.getString("Sub_Disability_Name");
                                }

                            }

                        }
                    }
                    personalReportDTO.setSubtypeofDisability(subdisability);
                }



                if (rs.getString("SUB_SUB_DISABILITY_ID") != null) {

                    b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                    stmt = con.createStatement();
                    subsubdisability = null;
                    for (int i = 0; i < b.length; i++) {
                        rs2 = stmt.executeQuery("select Sub_Sub_Disability_Name from tblSub_Sub_Disabilities with(nolock) "
                                + "where Sub_Sub_Disability_ID=" + b[i] + "");
                        while (rs2.next()) {
                            if (subsubdisability == null) {
                                subsubdisability = rs2.getString("Sub_Sub_Disability_Name").substring(2);
                            } else {
                                subsubdisability = subsubdisability + ","
                                        + rs2.getString("Sub_Sub_Disability_Name").substring(2);
                            }
                        }
                    }
                    personalReportDTO.setPartsInvolved(subsubdisability);
                }
                int number = new Float((rs.getString("TOTALDISABILITY"))).intValue();
                String percentage = Integer.toString(number);
                personalReportDTO.setTotalpercent(percentage);
                personalReportDTO.setSurgeryType(rs.getString(columnName));

                if (rs.getString("CASTE").equals("1")) {
                    personalReportDTO.setCaste("ST");
                } else if (rs.getString("Caste").equals("2")) {
                    personalReportDTO.setCaste("SC");
                } else if (rs.getString("Caste").equals("3")) {
                    personalReportDTO.setCaste("BC");
                } else if (rs.getString("Caste").equals("4")) {
                    personalReportDTO.setCaste("OC");
                } else if (rs.getString("Caste").equals("5")) {
                    personalReportDTO.setCaste("Minority");
                } else if (rs.getString("Caste").equals("6")) {
                    personalReportDTO.setCaste("NA");
                }



                if (rs.getString("EMPLOYMENT").equals("1")) {
                    personalReportDTO.setOccupation("Govt");
                } else if (rs.getString("Employment").equals("2")) {
                    personalReportDTO.setOccupation("Private");
                } else if (rs.getString("Employment").equals("3")) {
                    personalReportDTO.setOccupation("Self-Employed");
                } else if (rs.getString("Employment").equals("4")) {
                    personalReportDTO.setOccupation("Un-Employed");
                } else if (rs.getString("Employment").equals("5")) {
                    personalReportDTO.setOccupation("Wage-Employed");
                } else {
                    personalReportDTO.setOccupation("Not Entered");
                }

                personalList.add(personalReportDTO);

            }




        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalDetailsSurgery", "FunctionalNeedReportDAO", "DataBase");
           //con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getPersonalDetailsSurgery");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalDetailsSurgery", "FunctionalNeedReportDAO", "Code");
            //DBConnection.rollbackConnection(con);
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getPersonalDetailsSurgery");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

            DBConnection.closeResultSet(rs2);
            DBConnection.closeStatement(stmt);
        }
        return personalList;
    }

    public ArrayList getpersonalGeneralNeeds(DataSource ds,
            FunctionalNeedReportDTO functionalNeedReportDTO, int start, int end, String columnName,
            String columnValue, String tableName)
            throws SADAREMDBException, SQLException {

        Connection con = null;
        CallableStatement cstmt = null;
        Statement stmt = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        String district_id = null;
        String mandal_id = null;
        String village_id = null;
        String habitation_id = null;
        String subdisability = null;
        String subsubdisability = null;
        String typeofDisability = null;
        typeofDisability = functionalNeedReportDTO.getDisabilityType();
        ArrayList<FunctionalNeedReportDTO> personalList = null;
        district_id = functionalNeedReportDTO.getDistrict_id();
        mandal_id = functionalNeedReportDTO.getMandal_id();
        village_id = functionalNeedReportDTO.getVillage_id();
        habitation_id = functionalNeedReportDTO.getHabitation_id();

        if (mandal_id.equals("a")) {
            mandal_id = "ALL";
        }
        if (village_id.equals("a")) {
            village_id = "ALL";
        }
        if (habitation_id.equals("a")) {
            habitation_id = "ALL";
        }
        personalList = new ArrayList<FunctionalNeedReportDTO>();

        try {
            Date fdate = new SimpleDateFormat("dd/MM/yyyy").parse(functionalNeedReportDTO.getFromdate());
            String fromdate = new SimpleDateFormat("MM/dd/yyyy").format(fdate);

            Date tdate = new SimpleDateFormat("dd/MM/yyyy").parse(functionalNeedReportDTO.getTodate());
            String todate = new SimpleDateFormat("MM/dd/yyyy").format(tdate);

            int a[] = new int[20];
            int b[] = new int[20];
            con = DBConnection.getConnection();

            if (columnValue.equals("1")) {

                cstmt = con.prepareCall("{Call SP_PERSONALDETAILSFOR_GENERALNEEDS_ANYOTHER(?,?,?,?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, typeofDisability);
                cstmt.setString(2, district_id);
                cstmt.setString(3, mandal_id);
                cstmt.setString(4, village_id);
                cstmt.setString(5, habitation_id);
                cstmt.setString(6, fromdate);
                cstmt.setString(7, todate);
                cstmt.setString(8, tableName);
                cstmt.setString(9, columnName);
                cstmt.setString(10, Integer.toString(start));
                cstmt.setString(11, Integer.toString(end));

                stmt = con.createStatement();
                rs = cstmt.executeQuery();

                while (rs.next()) {
                    FunctionalNeedReportDTO personalReportDTO = new FunctionalNeedReportDTO();
                    personalReportDTO.setPersoncode(rs.getString("PERSON_CODE"));
                    personalReportDTO.setName(rs.getString("PERSONNAME"));
                    personalReportDTO.setRalationName(rs.getString("RELATION_NAME"));
                    personalReportDTO.setAge(rs.getString("AGE_YEARS"));

                    if (rs.getString("GENDER").equals("1")) {
                        personalReportDTO.setGender("Male");
                    } else if (rs.getString("Gender").equals("2")) {
                        personalReportDTO.setGender("Female");
                    } else {
                        personalReportDTO.setGender("Not Entered");
                    }


                    personalReportDTO.setHouseno(rs.getString("HOUSE_NUMBER"));
                    personalReportDTO.setDistrictName(rs.getString("DISTRICT_NAME"));
                    personalReportDTO.setMandalName(rs.getString("MANDAL_NAME"));
                    personalReportDTO.setVillageName(rs.getString("VILLAGE_NAME"));
                    personalReportDTO.setHabitationName(rs.getString("HABITATION_NAME"));
                    personalReportDTO.setDisabilityType(rs.getString("DISABILITY_NAME").substring(2));


                    if (rs.getString("SUB_DISABILITY_ID") != null) {
                        a = convertToIntArray(rs.getString("Sub_Disability_ID"));
                        stmt = con.createStatement();

                        subdisability = null;

                        for (int i = 0; i < a.length; i++) {
                            rs2 = stmt.executeQuery("select Sub_Disability_Name from tblSubDisability_Details with(nolock) "
                                    + "where Sub_Disability_ID=" + a[i] + "");
                            while (rs2.next()) {
                                if (subdisability == null) {
                                    if (typeofDisability.equals("1")) {
                                        subdisability = rs2.getString("Sub_Disability_Name").substring(2);
                                    } else {
                                        subdisability = rs2.getString("Sub_Disability_Name");
                                    }
                                } else {
                                    if (typeofDisability.equals("1")) {
                                        subdisability = subdisability + "," + rs2.getString("Sub_Disability_Name").substring(2);
                                    } else {
                                        subdisability = subdisability + "," + rs2.getString("Sub_Disability_Name");
                                    }

                                }

                            }
                        }
                        personalReportDTO.setSubtypeofDisability(subdisability);
                    }



                    if (rs.getString("SUB_SUB_DISABILITY_ID") != null) {

                        b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                        stmt = con.createStatement();
                        subsubdisability = null;
                        for (int i = 0; i < b.length; i++) {
                            rs2 = stmt.executeQuery("select Sub_Sub_Disability_Name from tblSub_Sub_Disabilities with(nolock) "
                                    + "where Sub_Sub_Disability_ID=" + b[i] + "");
                            while (rs2.next()) {
                                if (subsubdisability == null) {
                                    subsubdisability = rs2.getString("Sub_Sub_Disability_Name").substring(2);
                                } else {
                                    subsubdisability = subsubdisability + ","
                                            + rs2.getString("Sub_Sub_Disability_Name").substring(2);
                                }
                            }
                        }
                        personalReportDTO.setPartsInvolved(subsubdisability);
                    }

                    int number = new Float((rs.getString("TOTALDISABILITY"))).intValue();
                    String percentage = Integer.toString(number);
                    personalReportDTO.setTotalpercent(percentage);
                    personalReportDTO.setSurgeryType(rs.getString(columnName));

                    if (rs.getString("CASTE").equals("1")) {
                        personalReportDTO.setCaste("ST");
                    } else if (rs.getString("Caste").equals("2")) {
                        personalReportDTO.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        personalReportDTO.setCaste("BC");
                    } else if (rs.getString("Caste").equals("4")) {
                        personalReportDTO.setCaste("OC");
                    } else if (rs.getString("Caste").equals("5")) {
                        personalReportDTO.setCaste("Minority");
                    } else if (rs.getString("Caste").equals("6")) {
                        personalReportDTO.setCaste("NA");
                    }



                    if (rs.getString("EMPLOYMENT").equals("1")) {
                        personalReportDTO.setOccupation("Govt");
                    } else if (rs.getString("Employment").equals("2")) {
                        personalReportDTO.setOccupation("Private");
                    } else if (rs.getString("Employment").equals("3")) {
                        personalReportDTO.setOccupation("Self-Employed");
                    } else if (rs.getString("Employment").equals("4")) {
                        personalReportDTO.setOccupation("Un-Employed");
                    } else if (rs.getString("Employment").equals("5")) {
                        personalReportDTO.setOccupation("Wage-Employed");
                    } else {
                        personalReportDTO.setOccupation("Not Entered");
                    }

                    personalList.add(personalReportDTO);

                }
            } else {

                cstmt = con.prepareCall("{Call SP_PERSONALDETAILSFOR_GENERALNEEDS(?,?,?,?,?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, typeofDisability);
                cstmt.setString(2, district_id);
                cstmt.setString(3, mandal_id);
                cstmt.setString(4, village_id);
                cstmt.setString(5, habitation_id);
                cstmt.setString(6, fromdate);
                cstmt.setString(7, todate);
                cstmt.setString(8, tableName);
                cstmt.setString(9, columnName);
                cstmt.setString(10, columnValue);
                cstmt.setString(11, Integer.toString(start));
                cstmt.setString(12, Integer.toString(end));

                stmt = con.createStatement();
                rs = cstmt.executeQuery();

                while (rs.next()) {
                    FunctionalNeedReportDTO personalReportDTO = new FunctionalNeedReportDTO();
                    personalReportDTO.setPersoncode(rs.getString("PERSON_CODE"));
                    personalReportDTO.setName(rs.getString("PERSONNAME"));
                    personalReportDTO.setRalationName(rs.getString("RELATION_NAME"));
                    personalReportDTO.setAge(rs.getString("AGE_YEARS"));

                    if (rs.getString("GENDER").equals("1")) {
                        personalReportDTO.setGender("Male");
                    } else if (rs.getString("Gender").equals("2")) {
                        personalReportDTO.setGender("Female");
                    } else {
                        personalReportDTO.setGender("Not Entered");
                    }


                    personalReportDTO.setHouseno(rs.getString("HOUSE_NUMBER"));
                    personalReportDTO.setDistrictName(rs.getString("DISTRICT_NAME"));
                    personalReportDTO.setMandalName(rs.getString("MANDAL_NAME"));
                    personalReportDTO.setVillageName(rs.getString("VILLAGE_NAME"));
                    personalReportDTO.setHabitationName(rs.getString("HABITATION_NAME"));
                    personalReportDTO.setDisabilityType(rs.getString("DISABILITY_NAME").substring(2));


                    if (rs.getString("SUB_DISABILITY_ID") != null) {
                        a = convertToIntArray(rs.getString("Sub_Disability_ID"));
                        stmt = con.createStatement();

                        subdisability = null;

                        for (int i = 0; i < a.length; i++) {
                            rs2 = stmt.executeQuery("select Sub_Disability_Name from tblSubDisability_Details "
                                    + "where Sub_Disability_ID=" + a[i] + "");
                            while (rs2.next()) {
                                if (subdisability == null) {
                                    if (typeofDisability.equals("1")) {
                                        subdisability = rs2.getString("Sub_Disability_Name").substring(2);
                                    } else {
                                        subdisability = rs2.getString("Sub_Disability_Name");
                                    }
                                } else {
                                    if (typeofDisability.equals("1")) {
                                        subdisability = subdisability + "," + rs2.getString("Sub_Disability_Name").substring(2);
                                    } else {
                                        subdisability = subdisability + "," + rs2.getString("Sub_Disability_Name");
                                    }
                                }
                            }
                        }
                        personalReportDTO.setSubtypeofDisability(subdisability);
                    }



                    if (rs.getString("SUB_SUB_DISABILITY_ID") != null) {

                        b = convertToIntArray(rs.getString("Sub_Sub_Disability_ID"));
                        stmt = con.createStatement();
                        subsubdisability = null;
                        for (int i = 0; i < b.length; i++) {
                            rs2 = stmt.executeQuery("select Sub_Sub_Disability_Name from tblSub_Sub_Disabilities with(nolock) "
                                    + "where Sub_Sub_Disability_ID=" + b[i] + "");
                            while (rs2.next()) {
                                if (subsubdisability == null) {
                                    subsubdisability = rs2.getString("Sub_Sub_Disability_Name").substring(2);
                                } else {
                                    subsubdisability = subsubdisability + ","
                                            + rs2.getString("Sub_Sub_Disability_Name").substring(2);
                                }
                            }
                        }
                        personalReportDTO.setPartsInvolved(subsubdisability);
                    }

                    int number = new Float((rs.getString("TOTALDISABILITY"))).intValue();
                    String percentage = Integer.toString(number);
                    personalReportDTO.setTotalpercent(percentage);
                    //   personalReportDTO.setSurgeryType(rs.getString(columnName));

                    if (rs.getString("CASTE").equals("1")) {
                        personalReportDTO.setCaste("ST");
                    } else if (rs.getString("Caste").equals("2")) {
                        personalReportDTO.setCaste("SC");
                    } else if (rs.getString("Caste").equals("3")) {
                        personalReportDTO.setCaste("BC");
                    } else if (rs.getString("Caste").equals("4")) {
                        personalReportDTO.setCaste("OC");
                    } else if (rs.getString("Caste").equals("5")) {
                        personalReportDTO.setCaste("Minority");
                    } else if (rs.getString("Caste").equals("6")) {
                        personalReportDTO.setCaste("NA");
                    }


                    if (rs.getString("EMPLOYMENT").equals("1")) {
                        personalReportDTO.setOccupation("Govt");
                    } else if (rs.getString("Employment").equals("2")) {
                        personalReportDTO.setOccupation("Private");
                    } else if (rs.getString("Employment").equals("3")) {
                        personalReportDTO.setOccupation("Self-Employed");
                    } else if (rs.getString("Employment").equals("4")) {
                        personalReportDTO.setOccupation("Un-Employed");
                    } else if (rs.getString("Employment").equals("5")) {
                        personalReportDTO.setOccupation("Wage-Employed");
                    } else {
                        personalReportDTO.setOccupation("Not Entered");
                    }

                    personalList.add(personalReportDTO);

                }

            }

        } 
        catch (SQLException sqlEx)
        {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getpersonalGeneralNeeds", "FunctionalNeedReportDAO", "DataBase");
           //con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO",  "getpersonalGeneralNeeds");
        } 
        catch (Exception sqlEx)
        {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getpersonalGeneralNeeds", "FunctionalNeedReportDAO", "Code");
            //DBConnection.rollbackConnection(con);
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", "getpersonalGeneralNeeds");
        } finally 
        {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

            DBConnection.closeResultSet(rs2);
            DBConnection.closeStatement(stmt);
        }
        return personalList;
    }

    /**
     *
     * @param inputString
     * @return int
     */
    public int[] convertToIntArray(String inputString) {

        if (inputString != null) {
            String[] stringArray = inputString.split(",");
            int[] intArray = new int[stringArray.length];
            for (int i = 0; i < stringArray.length; i++) {
                intArray[i] = Integer.valueOf(stringArray[i]).intValue();

            }
            return intArray;
        } else {
            return null;
        }

    }

    /**
     * this method fetches getEducationWiseReport from database
     * @param ds
     * @param dailydisabilityandpercentagedto
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public ArrayList getPersonEducationWiseReport(DataSource ds, String district_id, String mandal_id, String village_id, String hab_id, String education, String urbanId) throws SADAREMDBException, SQLException {

        Connection con = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        String sql = null;
        ArrayList data = new ArrayList();

        try 
        {
        	ArrayList paramList = new ArrayList();
        	ArrayList tempList	= new ArrayList();
        	
        	
            con = DBConnection.getConnection(); 
            sql = "select p.person_code,surname +space(2)+first_name fullname,DATEDIFF(year,P.Date_of_Birth,GETDATE()) age, 'GENDER' = CASE WHEN Gender = 1 THEN  'Male' WHEN Gender = 2 THEN 'Female'ELSE 'Not Entered'END, 'CASTE' = CASE WHEN P.CASTE = 1 THEN 'ST'WHEN P.CASTE = 2 THEN 'SC'WHEN P.CASTE = 3 THEN 'BC' WHEN P.CASTE = 4 THEN 'OC' WHEN P.CASTE = 5 THEN 'Minority' WHEN P.CASTE = 6 THEN 'NA' ELSE 'Not Entered' END,"
                    + " 'MARITALSTATUS' =CASE WHEN P.MARITAL_STATUS = 1 THEN 'MARRIED'WHEN P.MARITAL_STATUS = 2 THEN 'UNMARRIED'WHEN P.MARITAL_STATUS = 3 THEN 'DIVORCEE'WHEN P.MARITAL_STATUS = 4 THEN 'WIDOW'WHEN P.MARITAL_STATUS = 5 THEN 'WIDOWER'ELSE 'Not Entered'END, 'EDUCATION' = CASE WHEN P.EDUCATION = 1 THEN 'Illiterate'WHEN P.EDUCATION = 2 THEN 'Below 10th'WHEN P.EDUCATION = 3 THEN '10th Class' WHEN P.EDUCATION = 4 THEN 'Intermediate' WHEN P.EDUCATION = 5 THEN 'Diploma/I.T.I' WHEN P.EDUCATION = 6 THEN 'Graduate' WHEN P.EDUCATION = 7 THEN 'Postgraduate'ELSE 'Not Entered' END,"
                    + " isnull(phone_No,'-') contact, relation_name,(p.house_number+',' +n.district_name+','+ m.mandal_name+','+v.village_name+','+x.habitation_name ) adress  from dbo.tblperson_personal_details P  with (nolock) ,"
                    + " TBLPERSON_DISABILITY_DETAILS PD with (nolock),TBLPERSON_DISABILITY_TOTALVALUE_DETAILS PT with (nolock),tbldistrict_details n,TBLMANDAL_DETAILS  M with(nolock) ,TBLVILLAGE_DETAILS V with(nolock) ,dbo.tblHabitation_Details x with(nolock)  where P.PERSON_CODE=PD.PERSON_CODE    AND"
                    + " P.PERSON_CODE=PT.PERSON_CODE    AND PD.PERSON_CODE = PT.PERSON_CODE  AND PT.TOTALDISABILITy > = 40 AND P.STATUS='ACTIVE' AND PD.STATUS='ACTIVE' AND PT.STATUS='ACTIVE'  AND" 
                    + " VIEW_EDIT_MODE = 'View' and p.districtid = n.district_id and P.DISTRICTID = M.DISTRICT_ID AND P.MANDALID = M.MANDAL_ID ANd P.DISTRICTID = V.DISTRICT_ID AND P.MANDALID = V.MANDAL_ID AND P.VILLAGEID = V.VILLAGE_ID and p.habcode = x.habitation_code  ";


            if (district_id != null && !district_id.equalsIgnoreCase("")) 
            {
                sql = sql + " and p.district_id=?";
                
                tempList	= new ArrayList();
                tempList.add("S");
                tempList.add(district_id);
                paramList.add(tempList);
            }
            
            if (mandal_id != null && !mandal_id.equalsIgnoreCase("")) 
            {
                sql = sql + " and p.mandal_id=?";
                
                tempList	= new ArrayList();
                tempList.add("S");
                tempList.add(mandal_id);
                paramList.add(tempList);
            } 

            if (village_id != null && !village_id.equalsIgnoreCase("") && !village_id.equalsIgnoreCase("a")) 
            {
                sql = sql + " and p.village_id=? ";
                
                tempList	= new ArrayList();
                tempList.add("S");
                tempList.add(village_id);
                paramList.add(tempList);
            }

            if (hab_id != null && !hab_id.equalsIgnoreCase("") && !hab_id.equalsIgnoreCase("a"))
            {
               /* if (district_id.equals("16") && mandal_id.equals("84") && village_id.equals("004") && hab_id.equals("01")) 
                {
                    sql = sql + " and p.habitation_id in('00','" + hab_id + "')";
                } 
                else*/ 
                {
                    sql = sql + " and p.habitation_id=?";
                    
                    tempList	= new ArrayList();
                    tempList.add("S");
                    tempList.add(hab_id);
                    paramList.add(tempList);
                }
            }
            
            if (education != null && !education.equalsIgnoreCase("")) 
            {
                if (!education.equalsIgnoreCase("12"))
                {
                    sql = sql + " and p.education=?";

                    tempList	= new ArrayList();
                    tempList.add("S");
                    tempList.add(education);
                    paramList.add(tempList);
                }
               /* else 
                {
                    sql = sql + " and p.education in('0','1','2','3','4','5','6','7')";
                }*/
            }


          /*  if (urbanId.equals("Urban")) 
            {
                sql = sql + " and p.mandal_id > 79 ";

            }
            else if (urbanId.equals("Rural")) 
            {
                sql = sql + " and p.mandal_id < 80 ";

            } */
            
            preStmt = con.prepareStatement(sql);
            
            if(paramList.size()>0)
            {
            	for(int loop=0;loop<paramList.size();loop++)
            	{
            		tempList = (ArrayList)paramList.get(loop);
            		
            		if(tempList.get(0).toString().equals("S"))
            		{
            			preStmt.setString(loop, tempList.get(1).toString().trim());
            		}
            		else if(tempList.get(0).toString().equals("I"))
            		{
            			preStmt.setInt(loop, Integer.parseInt(tempList.get(1).toString().trim()));
            		}
            	}
            }
            
            rs = preStmt.executeQuery();
            
            while (rs.next()) 
            {
                HashMap results = new HashMap();
                results.put("person_code", rs.getString(1));
                results.put("surname", rs.getString(2));
                results.put("age_years", rs.getString(3));
                results.put("gender", rs.getString(4));
                results.put("cast", rs.getString(5));
                results.put("marital_status", rs.getString(6));
                results.put("education", rs.getString(7));
                results.put("contact", rs.getString(8));
                results.put("relation", rs.getString(9));
                results.put("address", rs.getString(10));
                data.add(results);
            }
            
            rs.close();
            preStmt.close();
        } 
        catch (SQLException sqlEx) 
        {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonEducationWiseReport", "FunctionalNeedReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO","getPersonEducationWiseReport");
        }
        catch (Exception sqlEx) 
        {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonEducationWiseReport", "FunctionalNeedReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO","getPersonEducationWiseReport");
        }
        finally 
        {
             DBConnection.closeConnection(con);
             DBConnection.closeResultSet(rs);
             DBConnection.closeStatement(preStmt);
        }

        return data;
    }

    /**
     * this method fetches getEducationWiseReport from database
     * @param ds
     * @param dailydisabilityandpercentagedto
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public ArrayList getPersonalCasteWiseDetails(DataSource ds, String district_id, String mandal_id, String village_id, String habCode, String caste, String urbanId, 

String fromDate, String todate)
            throws SADAREMDBException, SQLException {

   
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = null;
        ArrayList data = new ArrayList();


        try {
            con = DBConnection.getConnection();
            stmt = con.createStatement();

            Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(fromDate);

            String fromdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);

            Date toDate = new SimpleDateFormat("dd/mm/yyyy").parse(todate);

            String toDates = new SimpleDateFormat("mm/dd/yyyy").format(toDate);

            sql = "SELECT DISTINCT P.PERSON_CODE AS SADAREMCODE,P.SURNAME + SPACE(2) + P.FIRST_NAME AS PERSONNAME,"
                    + " 'GENDER' = CASE   WHEN Gender = 1 THEN 'Male' WHEN Gender = 2 THEN 'Female' ELSE 'Not Entered' END,DATEDIFF(year,P.Date_of_Birth,GETDATE ()),p.relation_name ,'EDUCATION' =CASE WHEN P.EDUCATION = 1 THEN 'Illiterate' "
                    + " WHEN P.EDUCATION = 2 THEN 'Below 10th'  WHEN P.EDUCATION = 3 THEN '10th Class'  WHEN P.EDUCATION = 4   THEN 'Intermediate'  WHEN P.EDUCATION =5 THEN 'Diploma/I.T.I'  WHEN P.EDUCATION = 6 THEN 'Graduate' "
                    + " WHEN P.EDUCATION = 7 THEN 'Postgraduate'  ELSE 'Not Entered'  END, 'CASTE' =   CASE  WHEN P.CASTE = 1"
                    + "    THEN 'ST'  WHEN P.CASTE = 2 THEN 'SC'  WHEN P.CASTE = 3 THEN 'BC'  WHEN P.CASTE = 4 THEN 'OC'     "
                    + " WHEN P.CASTE = 5 THEN 'Minority'  WHEN P.CASTE = 6 THEN 'NA'  ELSE 'Not Entered'  END,     "
                    + " house_number+','+habitation_name+','+e.village_name+', '+d.mandal_name+','+s.district_name "
                    + " Address, coalesce(phone_no,'-') as phone_no FROM   DBO.TBLPERSON_PERSONAL_DETAILS P  with (nolock) ,   "
                    + " DBO.TBLPERSON_DISABILITY_DETAILS PD with(nolock) ,   DBO.TBLPERSON_DISABILITY_TOTALVALUE_DETAILS PT  with(nolock) ,  "
                    + "     tbldistrict_details s with(nolock) ,  tblmandal_details d with(nolock) ,  tblvillage_details e with(nolock)  , tblhabitation_details h with(nolock)   "
                    + "     WHERE   PD.PERSON_CODE      = P.PERSON_CODE  "
                    + " AND   PT.PERSON_CODE      = P.PERSON_CODE  AND  PD.PERSON_CODE      = PT.PERSON_CODE AND"
                    + "  P.STATUS= 'ACTIVE'   AND  PD.STATUS= 'ACTIVE'  AND  PT.STATUS= 'ACTIVE' AND   "
                    + " VIEW_EDIT_MODE = 'View' AND  p.districtid = s.district_id and "
                    + "  p.districtid = d.district_id and  p.mandalid = d.mandal_id and "
                    + " p.districtid = e.district_id and  p.mandalid = e.mandal_id and "
                    + " p.villageid = e.village_id and    p.habcode = h.habitation_code   and "
                    + "  (DATEADD(DD,0,DATEDIFF(DD,0,PD.UPDATED_DATE))) between  '" + fromdate + "' and '" + toDates + "'   and totaldisability > = 40  ";

            if (!district_id.equals("0")) {


                sql += "  and p.district_id='" + district_id + "'";


            }
            if (!mandal_id.equals("0")) {

                sql += " and p.mandal_id='" + mandal_id + "'";


            }
            if (!village_id.equals("0")) {

    sql += " and p.village_id='" + village_id + "'";

                if (!habCode.equals("0")) {
                    if (district_id.equals("16") && mandal_id.equals("84") && village_id.equals("004") && habCode.equals("01")) {
                        sql += " and p.habitation_id in('00','" + habCode + "')";
                    } else {
                        sql += " and p.habitation_id='" + habCode + "'";
                    }
                }
            }

            if (caste.equalsIgnoreCase("6")) {
                sql += " and  p.caste in ('0','6') ";
            } else {
                sql += " and p.caste='" + caste + "' ";
            }

            if (urbanId.equals("Urban")) {
                sql = sql + " and p.mandal_id > 79 ";
            } else if (urbanId.equals("Rural")) {
                sql = sql + " and p.mandal_id < 80 ";
            }

      

            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                HashMap results = new HashMap();

                results.put("district_id", district_id);
                results.put("mandal_id", mandal_id);
                results.put("village_id", village_id);
                results.put("PERSON_CODE", rs.getString(1));
                results.put("PERSONNAME", rs.getString(2));
                results.put("Gender", rs.getString(3));
                results.put("age_years", rs.getString(4));
                results.put("relation_name", rs.getString(5));
                results.put("EDUCATION", rs.getString(6));
                results.put("CASTE", rs.getString(7));
                results.put("Address", rs.getString(8));
                results.put("phone_no", rs.getString(9));
                results.put("fromdate", fromDate);
                results.put("todate", todate);

                data.add(results);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalCasteWiseDetails", "FunctionalNeedReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getPersonalCasteWiseDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalCasteWiseDetails", "FunctionalNeedReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getPersonalCasteWiseDetails");
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                DBConnection.closeResultSet(rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return data;
    }

    /**
     * this method fetches getEducationWiseReport from database
     * @param ds
     * @param dailydisabilityandpercentagedto
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public ArrayList getAreaDetails(DataSource ds, String district_id, String mandal_id, String village_id, String hab_id)
            throws SADAREMDBException, SQLException {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sqlDistrict = null;
        String sqlMandal = null;
        String sqlVillage = null;
        String sqlHab = null;
        ArrayList data = new ArrayList();

        try {
            con = DBConnection.getConnection();
            stmt = con.createStatement();
            HashMap results = new HashMap();
            String district = null;
            if (district_id.equals("0")) {
                data.add("ALL");
            } else {
                sqlDistrict = "select distinct district_name from dbo.tblDistrict_Details with(nolock) where district_id='" + district_id + "'";

                rs = stmt.executeQuery(sqlDistrict);
                while (rs.next()) {

                    data.add(rs.getString(1));

                }
            }
            if (mandal_id.equals("0")) {
                data.add("ALL");
            } else {
                sqlMandal = "select distinct mandal_name from dbo.tblMandal_Details with(nolock) where district_id='" + district_id + "' and mandal_id='" + mandal_id + "'";

                rs = stmt.executeQuery(sqlMandal);
                while (rs.next()) {

                    data.add(rs.getString(1));

                }
            }

            if (village_id.equals("0")) {
                data.add("ALL");
            } else {
                sqlVillage = "select distinct village_name from dbo.tblVillage_Details with(nolock) where district_id='" + district_id + "' and mandal_id='" + mandal_id + "' and village_id='" + village_id + "'";

                rs = stmt.executeQuery(sqlVillage);
                while (rs.next()) {
                    data.add(rs.getString(1));

                }

            }

            if (hab_id.equals("0")) {
                data.add("ALL");
            } else {
                sqlHab = "select distinct habitation_name from dbo.tblHabitation_Details with(nolock)  where district_id='" + district_id + "' and mandal_id='" + mandal_id + "' and village_id='" + village_id + "' and habitation_id='" + hab_id + "'";

                rs = stmt.executeQuery(sqlHab);
                while (rs.next()) {
                    data.add(rs.getString(1));

                }

            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getAreaDetails", "FunctionalNeedReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getAreaDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getAreaDetails", "FunctionalNeedReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getAreaDetails");
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                DBConnection.closeResultSet(rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return data;
    }

    /**

    /**
     * this method fetches getCasteWiseDetails from database
     * @param ds
     * @param dailydisabilityandpercentagedto
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    /*
    public ArrayList getCasteWiseSingleDetails(DataSource ds, String district_id, String mandal_id, String village_id, String caste)
    throws SADAREMDBException,SQLException {

    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    String sqlDistrict = null;
    ArrayList data = new ArrayList();


    try {
    con = DBConnection.getConnection();
    stmt = con.createStatement();


    if (district_id == null) {
    district_id = "0";
    }
    if (mandal_id == null) {
    mandal_id = "0";
    }
    if (village_id == null) {
    village_id = "0";
    }
    if (caste == null) {
    caste = "0";
    }




    if (district_id.equals("0")) {
    sqlDistrict = "select b.district_id,b.district_name,"
    + "(select count(caste) from tblperson_personal_details where caste='" + caste + "' and district_id=a.district_id and status='Active') as st"
    + " from dbo.tblperson_personal_details a right join dbo.tblDistrict_details b on (a.district_id=b.district_id and a.caste='" + caste + "') "
    + "group by b.district_id,b.district_name,a.district_id,a.caste order by b.district_id";
    rs = stmt.executeQuery(sqlDistrict);
    while (rs.next()) {
    HashMap results = new HashMap();
    results.put("district_id", rs.getString(1));
    results.put("name", rs.getString(2));
    results.put("caste", rs.getString(3));
    data.add(results);

    }
    }



    if (village_id.equals("0") && !district_id.equals("0") && mandal_id.equals("0")) {
    sqlDistrict = "select b.district_id,b.mandal_id,b.mandal_name,"
    + "(select count(caste) from tblperson_personal_details where caste='" + caste + "' and district_id=a.district_id and mandal_id=a.mandal_id and status='Active') as 

st"
    + " from dbo.tblperson_personal_details a "
    + "inner join dbo.tblMandal_details b on (a.district_id=b.district_id and a.mandal_id=b.mandal_id and "
    + "a.district_id='" + district_id + "' and a.caste='" + caste + "') group by b.district_id,a.district_id,b.mandal_name,b.mandal_id,a.mandal_id,a.caste order by 

b.district_id";


    rs = stmt.executeQuery(sqlDistrict);
    while (rs.next()) {
    HashMap results = new HashMap();
    results.put("district_id", rs.getString(1));
    results.put("mandal_id", rs.getString(2));
    results.put("name", rs.getString(3));
    results.put("caste", rs.getString(4));
    data.add(results);

    }

    }


    if (village_id.equals("0") && !district_id.equals("0") && !mandal_id.equals("0")) {
    sqlDistrict = "select b.district_id,b.mandal_id,b.village_id,coalesce(b.village_name,'-') as name, "
    + "(select count(caste) from tblperson_personal_details where caste='" + caste + "' and district_id=a.district_id and mandal_id=a.mandal_id and 

village_id=a.village_id and status='Active') as st "
    + "from dbo.tblperson_personal_details a "
    + "inner join dbo.tblVillage_details b on (a.district_id=b.district_id and a.mandal_id=b.mandal_id and a.village_id=b.village_id and a.district_id='" + district_id 

+ "' and a.mandal_id='" + mandal_id + "' and a.caste='" + caste + "') "
    + "group by b.district_id,a.district_id,b.village_name,b.mandal_id,b.village_id,a.mandal_id,a.village_id,a.caste order by b.district_id";
    rs = stmt.executeQuery(sqlDistrict);
    while (rs.next()) {
    HashMap results = new HashMap();
    results.put("district_id", rs.getString(1));
    results.put("mandal_id", rs.getString(2));
    results.put("village_id", rs.getString(3));
    results.put("name", rs.getString(4));
    results.put("caste", rs.getString(5));

    data.add(results);
    }

    }


    if (!village_id.equals("0") && !district_id.equals("0") && !mandal_id.equals("0")) {
    sqlDistrict = "select b.district_id,b.mandal_id,b.village_id,b.habitation_id,b.habitation_name,"
    + "(select count(caste) from tblperson_personal_details where caste='" + caste + "' and district_id=a.district_id and mandal_id=a.mandal_id and 

village_id=a.village_id and habitation_id=a.habitation_id and status='Active') as st"
    + " from dbo.tblperson_personal_details a "
    + "inner join dbo.tblHabitation_details b on "
    + "(a.district_id=b.district_id and a.mandal_id=b.mandal_id and a.village_id=b.village_id and "
    + "a.habitation_id=b.habitation_id and a.district_id='" + district_id + "' and a.mandal_id='" + mandal_id + "' and a.village_id='" + village_id + "' and a.caste='" 

+ caste + "')"
    + "group by b.district_id,a.district_id,b.habitation_name,b.mandal_id,b.village_id,b.habitation_id,a.mandal_id,a.village_id,a.habitation_id,a.caste order by 

b.district_id";
    rs = stmt.executeQuery(sqlDistrict);
    while (rs.next()) {
    HashMap results = new HashMap();
    results.put("district_id", rs.getString(1));
    results.put("mandal_id", rs.getString(2));
    results.put("village_id", rs.getString(3));
    results.put("hab_id", rs.getString(4));
    results.put("name", rs.getString(5));
    results.put("caste", rs.getString(6));
    data.add(results);
    }
    }

    } catch (Exception e) {
    e.printStackTrace();
    } finally {
    try {
    if (con != null) {
    con.close();
    }
    if (stmt != null) {
    stmt.close();
    }
    DBConnection.closeResultSet(rs);
    } catch (Exception e) {
    e.printStackTrace();
    }
    }

    return data;
    }
     */
    /**
     * this method fetches getAgeDetails from database
     * @param ds
     * @param dailydisabilityandpercentagedto
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public ArrayList getAgeDetails(DataSource ds, String district_id, String mandal_id, String village_id, String fromAge, String toAge, FunctionalNeedReportDTO 

functionalNeedReportDTO)
            throws SADAREMDBException, SQLException {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = null;
        ArrayList data = new ArrayList();
        HashMap results = null;
        try {
            con = DBConnection.getConnection();
            stmt = con.createStatement();

            Date fdate = new SimpleDateFormat("dd/MM/yyyy").parse(functionalNeedReportDTO.getFromdate());
            String fromdate = new SimpleDateFormat("MM/dd/yyyy").format(fdate);

            Date tdate = new SimpleDateFormat("dd/MM/yyyy").parse(functionalNeedReportDTO.getTodate());
            String todate = new SimpleDateFormat("MM/dd/yyyy").format(tdate);

            if (district_id.equals("0")) {
                data.clear();
                sql = "select b.district_id,b.district_name,count(a.gender) as total,"
                        + "(select count(*) from tblperson_personal_details  with (nolock) where gender='1' and a.district_id=b.district_id and district_id=a.district_id and age_years between '" + fromAge + "' and '" + toAge + "' and status='Active') as male,"
                        + "(select count(*) from tblperson_personal_details  with (nolock) where gender='2' and a.district_id=b.district_id and district_id=a.district_id and age_years between '" + fromAge + "' and '" + toAge + "' and status='Active') as female "
                        + "from dbo.tblperson_personal_details a  with (nolock) right join dbo.tblDistrict_Details b with(nolock)  "
                        + "on(a.district_id=b.district_id and a.age_years between '" + fromAge + "' and '" + toAge + "' and b.district_name!='sa' and a.status='Active') and (DATEADD(DD,0,DATEDIFF(DD,0,a.UPDATED_DATE))) BETWEEN  '" + fromdate + "' AND '" + todate + "' group by b.district_id,b.district_name,"
                        + "a.district_id order by district_name";
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    results = new HashMap();
                    results.put("district_id", rs.getString(1));
                    results.put("name", rs.getString(2));
                    results.put("total", rs.getString(3));
                    results.put("male", rs.getString(4));
                    results.put("female", rs.getString(5));
                    results.put("fromdate", fromdate);
                    results.put("todate", todate);
                    results.put("fromage", fromAge);
                    results.put("toage", toAge);
                    data.add(results);
                }

            }

            if (!district_id.equals("0") && mandal_id.equals("0")) {
                data.clear();
                sql = "select a.district_id,a.mandal_id,b.mandal_name,count(a.gender) as total,"
                        + "(select count(*) from tblperson_personal_details  with (nolock) where gender='1' and a.district_id=b.district_id and district_id=a.district_id and mandal_id=a.mandal_id and a.mandal_id=b.mandal_id and age_years between '" + fromAge + "' and '" + toAge + "' and status='Active') as male,"
                        + "(select count(*) from tblperson_personal_details  with (nolock) where gender='2' and a.district_id=b.district_id and district_id=a.district_id and mandal_id=a.mandal_id and a.mandal_id=b.mandal_id and age_years between '" + fromAge + "' and '" + toAge + "' and status='Active') as female "
                        + "from dbo.tblPerson_personal_details  a with (nolock)  right join dbo.tblMandal_Details b with(nolock) on(a.district_id=b.district_id and a.mandal_id=b.mandal_id and a.age_years between '" + fromAge + "' and '" + toAge + "' and a.status='Active') "
                        + "where a.district_id='" + district_id + "' and (DATEADD(DD,0,DATEDIFF(DD,0,a.UPDATED_DATE))) BETWEEN  '" + fromdate + "' AND '" + todate + "' group by b.district_id,b.mandal_name,a.district_id,a.mandal_id,b.mandal_id order by mandal_name ";
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    results = new HashMap();
                    results.put("district_id", rs.getString(1));
                    results.put("mandal_id", rs.getString(2));
                    results.put("name", rs.getString(3));
                    results.put("total", rs.getString(4));
                    results.put("male", rs.getString(5));
                    results.put("female", rs.getString(6));
                    results.put("fromdate", fromdate);
                    results.put("todate", todate);
                    results.put("fromage", fromAge);
                    results.put("toage", toAge);
                    data.add(results);
                }

            }

            if (!district_id.equals("0") && !mandal_id.equals("0")) {
                data.clear();
                sql = "select a.district_id,a.mandal_id,a.village_id,b.village_name,count(a.gender) as total,"
                        + "(select count(*) from tblperson_personal_details with (nolock)  where gender='1' and a.district_id=b.district_id and district_id=a.district_id and mandal_id=a.mandal_id and a.mandal_id=b.mandal_id and a.village_id=b.village_id and district_id=b.district_id and mandal_id=b.mandal_id and village_id=b.village_id and age_years between '" + fromAge + "' and '" + toAge + "' and status='Active') as male,"
                        + "(select count(*) from tblperson_personal_details with (nolock)  where gender='2' and a.district_id=b.district_id and district_id=a.district_id and mandal_id=a.mandal_id and a.mandal_id=b.mandal_id and a.village_id=b.village_id and district_id=b.district_id and mandal_id=b.mandal_id and village_id=b.village_id and age_years between '" + fromAge + "' and '" + toAge + "' and status='Active') as female "
                        + "from dbo.tblPerson_personal_details a with (nolock)  right join dbo.tblVillage_Details b with(nolock)  on(a.district_id=b.district_id and a.mandal_id=b.mandal_id and a.village_id=b.village_id and a.age_years between '" + fromAge + "' and '" + toAge + "' and a.status='Active') where a.district_id='" + district_id + "' and a.mandal_id='" + mandal_id + "' and (DATEADD(DD,0,DATEDIFF(DD,0,a.UPDATED_DATE))) BETWEEN  '" + fromdate + "' AND '" + todate + "' "
                        + "group by b.district_id,b.village_name,a.district_id,a.mandal_id,b.mandal_id,a.village_id,b.village_id order by village_name";
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    results = new HashMap();
                    results.put("district_id", rs.getString(1));
                    results.put("mandal_id", rs.getString(2));
                    results.put("village_id", rs.getString(3));
                    results.put("name", rs.getString(4));
                    results.put("total", rs.getString(5));
                    results.put("male", rs.getString(6));
                    results.put("female", rs.getString(7));
                    results.put("fromdate", fromdate);
                    results.put("todate", todate);
                    results.put("fromage", fromAge);
                    results.put("toage", toAge);
                    data.add(results);
                }

            }

            if (!district_id.equals("0") && !mandal_id.equals("0") && !village_id.equals("0")) {
                data.clear();
                sql = "select a.district_id,a.mandal_id,a.village_id,a.habitation_id,b.habitation_name,count(a.gender) as total,"
                        + "(select count(*) from tblperson_personal_details with (nolock) where gender='1' and a.district_id=b.district_id and district_id=a.district_id and mandal_id=a.mandal_id and a.mandal_id=b.mandal_id and a.village_id=b.village_id and a.habitation_id=b.habitation_id and district_id=b.district_id and mandal_id=b.mandal_id and village_id=b.village_id and habitation_id=b.habitation_id and age_years between '" + fromAge + "' and '" + toAge + "' and status='Active') as male,"
                        + "(select count(*) from tblperson_personal_details with (nolock) where gender='2' and a.district_id=b.district_id and district_id=a.district_id and mandal_id=a.mandal_id and a.mandal_id=b.mandal_id and a.village_id=b.village_id and a.habitation_id=b.habitation_id and district_id=b.district_id and mandal_id=b.mandal_id and village_id=b.village_id and habitation_id=b.habitation_id and age_years between '" + fromAge + "' and '" + toAge + "' and status='Active') as female "
                        + "from dbo.tblPerson_personal_details a with (nolock)  right join dbo.tblHabitation_Details b with(nolock) on(a.district_id=b.district_id and a.mandal_id=b.mandal_id and a.village_id=b.village_id and a.habitation_id=b.habitation_id and a.age_years between '" + fromAge + "' and '" + toAge + "' and a.status='Active') "
                        + "where a.district_id='" + district_id + "' and a.mandal_id='" + mandal_id + "' and a.village_id='" + village_id + "' and (DATEADD (DD,0,DATEDIFF(DD,0,a.UPDATED_DATE))) BETWEEN  '" + fromdate + "' AND '" + todate + "' group by b.district_id,b.habitation_name,a.district_id,a.mandal_id,b.mandal_id,a.village_id,b.village_id,a.habitation_id,b.habitation_id order by habitation_name";
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    results = new HashMap();
                    results.put("district_id", rs.getString(1));
                    results.put("mandal_id", rs.getString(2));
                    results.put("village_id", rs.getString(3));
                    results.put("habitation_id", rs.getString(4));
                    results.put("name", rs.getString(5));
                    results.put("total", rs.getString(6));
                    results.put("male", rs.getString(7));
                    results.put("female", rs.getString(8));
                    results.put("fromdate", fromdate);
                    results.put("todate", todate);
                    results.put("fromage", fromAge);
                    results.put("toage", toAge);
                    data.add(results);
                }

            }



        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getAgeDetails", "FunctionalNeedReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getAgeDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getAgeDetails", "FunctionalNeedReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getAgeDetails");
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                DBConnection.closeResultSet(rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return data;
    }

    /**
     * this method fetches getPersonalAgeWiseDetails from database
     * @param ds
     * @param dailydisabilityandpercentagedto
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public ArrayList getPersonalAgeWiseDetails(DataSource ds, String district_id, String mandal_id, String village_id, String habitation, String gender, String 

fromAge, String toAge, String urbanId, String fromDate, String todate)
            throws SADAREMDBException, SQLException {



        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        StringBuilder sql = new StringBuilder();
        ArrayList data = new ArrayList();
        HashMap results = null;
        try {
            con = DBConnection.getConnection();
            stmt = con.createStatement();

            Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(fromDate);

            String fromdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);

            Date toDate = new SimpleDateFormat("dd/mm/yyyy").parse(todate);

            String toDates = new SimpleDateFormat("mm/dd/yyyy").format(toDate);
            sql.append("SELECT DISTINCT "
                    + "P.PERSON_CODE AS SADAREMCODE,P.SURNAME + SPACE(2) + P.FIRST_NAME AS PERSONNAME,'GENDER' = CASE  "
                    + " WHEN Gender = 1 THEN 'Male' WHEN Gender = 2 THEN 'Female' ELSE 'Not Entered' END,DATEDIFF(year,P.Date_of_Birth,GETDATE()),p.relation_name ,'EDUCATION' =CASE WHEN P.EDUCATION = 1 THEN 'Illiterate'  "
                    + " WHEN P.EDUCATION = 2 THEN 'Below 10th'  WHEN P.EDUCATION = 3 THEN '10th Class'  WHEN P.EDUCATION = 4 THEN 'Intermediate'  "
                    + "WHEN P.EDUCATION = 5 THEN 'Diploma/I.T.I'  WHEN P.EDUCATION = 6 THEN 'Graduate'  WHEN P.EDUCATION = 7 THEN 'Postgraduate'  "
                    + "ELSE 'Not Entered'  END,"
                    + " 'CASTE' =   CASE  WHEN P.CASTE = 1 THEN 'ST'  WHEN P.CASTE = 2 THEN 'SC'  WHEN P.CASTE = 3 THEN 'BC'  WHEN P.CASTE = 4 THEN 'OC'  "
                    + " WHEN P.CASTE = 5 THEN 'Minority'  WHEN P.CASTE = 6 THEN 'NA'  ELSE 'Not Entered'  END,     house_number+','+habitation_name+','+e.village_name +', '+d.mandal_name+','+s.district_name Address, coalesce(phone_no,'-') as phone_no FROM "
                    + "  DBO.TBLPERSON_PERSONAL_DETAILS P with (nolock)  ,   DBO.TBLPERSON_DISABILITY_DETAILS PD with(nolock) ,   "
                    + "DBO.TBLPERSON_DISABILITY_TOTALVALUE_DETAILS PT with(nolock) ,  tbldistrict_details s with(nolock) ,  tblmandal_details d with(nolock) ,"
                    + "  tblvillage_details e with(nolock) , tblhabitation_details h with(nolock)  WHERE   PD.PERSON_CODE      = P.PERSON_CODE  AND  "
                    + " PT.PERSON_CODE      = P.PERSON_CODE  AND  PD.PERSON_CODE      = PT.PERSON_CODE AND  P.STATUS= 'ACTIVE' "
                    + "  AND  PD.STATUS= 'ACTIVE'  AND  PT.STATUS= 'ACTIVE' AND   VIEW_EDIT_MODE = 'View' AND  p.districtid = s.district_id and  p.districtid = d.district_id and  p.mandalid = d.mandal_id and  p.districtid = e.district_id and  p.mandalid = e.mandal_id and  p.villageid = e.village_id and    p.habcode = h.habitation_code   and"
                    + "   (DATEADD(DD,0,DATEDIFF(DD,0,PD.UPDATED_DATE))) between  '" + fromdate + "' and '" + toDates + "'   and totaldisability > = 40 and DATEDIFF (year,P.Date_of_Birth,GETDATE()) between '" + fromAge + "' and '" + toAge + "' ");
            if (!district_id.equals("0")) {
                sql.append(" and p.district_id='" + district_id + "' ");

            }  if (!mandal_id.equals("0")) {
                sql.append("  and p.mandal_id='" + mandal_id + "' ");

            }  if (!village_id.equals("0")) {
                sql.append(" and p.village_id='" + village_id + "'");
                if (!habitation.equals("0")) {
                    if (district_id.equals("16") && mandal_id.equals("84") && village_id.equals("004") && habitation.equals("01")) {
                        sql.append(" and p.habitation_id in('00','" + habitation + "')");
                    } else {
                        sql.append(" and p. habitation_id='" + habitation + "'");
                    }
                }

            }

            if (gender.equals("12")) {
                sql.append(" and (gender='1' or gender='2')");
            } else {
                sql.append(" and gender='" + gender + "'");
            }

            if (urbanId.equals("Urban")) {
                sql.append(" and p.mandal_id > 79 ");
            } else if (urbanId.equals("Rural")) {
                sql.append(" and p.mandal_id < 80 ");
            }


          
         
            rs = stmt.executeQuery(sql.toString());
            while (rs.next()) {
                results = new HashMap();

                results.put("district_id", district_id);
                results.put("mandal_id", mandal_id);
                results.put("village_id", village_id);
                results.put("PERSON_CODE", rs.getString(1));
                results.put("PERSONNAME", rs.getString(2));
                results.put("Gender", rs.getString(3));
                results.put("age_years", rs.getString(4));
                results.put("relation_name", rs.getString(5));
                results.put("EDUCATION", rs.getString(6));
                results.put("CASTE", rs.getString(7));
                results.put("Address", rs.getString(8));
                results.put("phone_no", rs.getString(9));

                results.put("fromdate", fromDate);
                results.put("todate", todate);
                data.add(results);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalAgeWiseDetails", "FunctionalNeedReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getPersonalAgeWiseDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalAgeWiseDetails", "FunctionalNeedReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getPersonalAgeWiseDetails");
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                DBConnection.closeResultSet(rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return data;
    }

    /**
     *
     * @param datasource
     * @param districtid
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public ArrayList getMandals(DataSource datasource, String districtid, String urbanId)
            throws SADAREMDBException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        Statement stmt = null;

        ArrayList mandallist = new ArrayList();
        StringBuffer sb = new StringBuffer();


        try {
            con = DBConnection.getConnection();
            // this query gets cluster id and cluster name
            if (urbanId != null && urbanId.equals("Rural")) {
                sb.append("select mandal_id,mandal_name from tblMandal_Details with(nolock) where status='Active' and District_ID='" + districtid + "' and mandal_id<80 order by Mandal_Name");
            } else if (urbanId != null && urbanId.equals("Urban")) {
                sb.append("select mandal_id,mandal_name from tblMandal_Details with(nolock) where status='Active' and District_ID='" + districtid + "' and mandal_id>79 order by Mandal_Name");
            } else {
                sb.append("select mandal_id,mandal_name from tblMandal_Details with(nolock) where status='Active' and district_id='" + districtid + "' order by mandal_name");
            }
            stmt = con.createStatement();

            rs = stmt.executeQuery(sb.toString());

            while (rs.next()) {
                FunctionalNeedReportDTO territory = new FunctionalNeedReportDTO();
                territory.setMandal_id(rs.getString("mandal_id"));
                territory.setMandal_name(rs.getString("mandal_name"));
                mandallist.add(territory);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "getMandals", "FunctionalNeedReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getMandals");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "getMandals", "FunctionalNeedReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getMandals");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);


        }

        return mandallist;
    }

    /**
     *
     * @param datasource
     * @param districtid
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public ArrayList getMandalsScheduleMandals(DataSource datasource, String districtid)
            throws SADAREMDBException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        Statement stmt = null;
        String query = null;
        ArrayList mandallist = new ArrayList();



        try {
            con = DBConnection.getConnection();
            // this query gets cluster id and cluster name
            // query = "select mandal_id,mandal_name from tblMandal_Details where district_id='" + districtid + "' order by mandal_name";
            query = "select distinct b.mandal_id,b.mandal_name from dbo.AppellateAuthorityandTemporary_RegistrationDetails a with(nolock)  "
		    + "CROSS APPLY DATA_PERSON_CODE(A.PERSON_CODE) DPC \n"
                    + "join dbo.tblMandal_Details b with(nolock) on(DPC.DISTRICT_ID=b.district_id and DPC.MANDAL_ID=b.mandal_id) "
                    + "where b.district_id='" + districtid + "' order by b.mandal_name";

            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                FunctionalNeedReportDTO territory = new FunctionalNeedReportDTO();
                territory.setMandal_id(rs.getString("mandal_id"));
                territory.setMandal_name(rs.getString("mandal_name"));
                mandallist.add(territory);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "getMandalsScheduleMandals", "FunctionalNeedReportDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getMandalsScheduleMandals");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "getMandalsScheduleMandals", "FunctionalNeedReportDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getMandalsScheduleMandals");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);


        }

        return mandallist;
    }

    /**
     *
     * @param datasource
     * @param districtid
     * @param mandalid
     * @param panchayatid
     * @param villageid
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public ArrayList getVillageAll(DataSource datasource, String districtid, String mandalid)
            throws SADAREMDBException, SQLException {
        Connection con = null;
        Statement stmt = null;
        ArrayList villagelist = new ArrayList();
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();
            // this query gets cluster id and cluster name
            String query = "select village_id,village_name from tblVillage_Details with(nolock)  where district_id='" + districtid + "' and  mandal_id='" + mandalid + "'   order by village_name";

            stmt = con.createStatement();

            rs = stmt.executeQuery(query);

            while (rs.next()) {
                FunctionalNeedReportDTO territory = new FunctionalNeedReportDTO();
                territory.setVillage_id(rs.getString("village_id"));
                territory.setVillage_name(rs.getString("village_name"));

                villagelist.add(territory);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "getVillageAll", "FunctionalNeedReportDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getVillageAll");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "getVillageAll", "FunctionalNeedReportDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getVillageAll");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);


        }
        return villagelist;
    }

    /**
     *
     * @param datasource
     * @param districtid
     * @param mandalid
     * @param panchayatid
     * @param villageid
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public ArrayList getScheduleVillages(DataSource datasource, String districtid, String mandalid)
            throws SADAREMDBException, SQLException {
        Connection con = null;
        Statement stmt = null;
        ArrayList villagelist = new ArrayList();
        ResultSet rs = null;
        String query = null;

        try {
            con = DBConnection.getConnection();
            // this query gets cluster id and cluster name
            // query = "select village_id,village_name from tblVillage_Details where district_id='" + districtid + "' and  mandal_id='" + mandalid + "'   order by village_name";

            query = "select distinct b.village_id,b.village_name from dbo.AppellateAuthorityandTemporary_RegistrationDetails a  with(nolock) "
		    + "CROSS APPLY DATA_PERSON_CODE(A.PERSON_CODE) DPC "
                    + "join dbo.tblVillage_Details b with(nolock)  on(DPC.District_id=b.district_id and DPC.MANDAL_ID=b.mandal_id "
                    + "and DPC.VILLAGE_ID=b.village_id) where b.district_id='" + districtid + "' and b.mandal_id='" + mandalid + "' order by b.village_name";

            stmt = con.createStatement();

            rs = stmt.executeQuery(query);

            while (rs.next()) {
                FunctionalNeedReportDTO territory = new FunctionalNeedReportDTO();
                territory.setVillage_id(rs.getString("village_id"));
                territory.setVillage_name(rs.getString("village_name"));

                villagelist.add(territory);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "getScheduleVillages", "FunctionalNeedReportDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getScheduleVillages");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "getScheduleVillages", "FunctionalNeedReportDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getScheduleVillages");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);


        }
        return villagelist;
    }

    /**
     *
     * @param datasource
     * @param districtid
     * @param mandalid
     * @param panchayatid
     * @param villageid
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public ArrayList alreadyEnteredVillages(DataSource datasource, String districtid, String mandalid, String villageid, String date)
            throws SADAREMDBException, SQLException {
        Connection con = null;
        Statement stmt = null;
        ArrayList enteredVillages = new ArrayList();
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();

            //  Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(date);
            //  String fromdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);


            // this query gets cluster id and cluster name
            String query = "select village_id from Appeal_Authority_Schedule with(nolock) where district_id='" + districtid + "' and  mandal_id='" + mandalid + "' and village_id='" 

+ villageid + "' and schedule_date = '" + date + "'";

            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                enteredVillages.add(rs.getString(1));

            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "alreadyEnteredVillages", "FunctionalNeedReportDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"alreadyEnteredVillages");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "alreadyEnteredVillages", "FunctionalNeedReportDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"alreadyEnteredVillages");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);


        }
        return enteredVillages;
    }

    /**
     *
     * @param datasource
     * @param districtid
     * @param mandalid
     * @param panchayatid
     * @param villageid
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public String checkConditions(DataSource datasource, String districtid, String mandalid, String villageid, String date)
            throws SADAREMDBException, SQLException {
        Connection con = null;
        Statement stmt = null;
        String enteredVillages = null;
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();

            Date fdate = new SimpleDateFormat("MM/dd/yyyy").parse(date);
            String fromDate = new SimpleDateFormat("MM/dd/yyyy").format(fdate);

            // this query gets cluster id and cluster name
            String query = "SELECT DATEDIFF(day,getDate(),'" + fromDate + "') AS DiffDate";

            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                enteredVillages = rs.getString(1);

            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "checkConditions", "FunctionalNeedReportDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"checkConditions");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "checkConditions", "FunctionalNeedReportDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"checkConditions");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);


        }
        return enteredVillages;
    }

    /**
     *
     * @param datasource
     * @param districtid
     * @param mandalid
     * @param panchayatid
     * @param villageid
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public ArrayList getAppealVillageAll(DataSource datasource, String districtid, String mandalid)
            throws SADAREMDBException, SQLException {
        Connection con = null;
        Statement stmt = null;
        ArrayList villagelist = new ArrayList();
        ResultSet rs = null;
        String sql = null;
        try {
            con = DBConnection.getConnection();
            // this query gets cluster id and cluster name

            sql = "select village_id,village_name from tblVillage_Details with(nolock)  where district_id='" + districtid + "' and  mandal_id='" + mandalid + "' and village_id "
                    + "not in(select village_id from dbo.Appeal_Authority_Schedule with(nolock)  where district_id='" + districtid + "' and mandal_id='" + mandalid + "' "
                    + "and CONVERT(VARCHAR(10), schedule_date, 105)=CONVERT(VARCHAR(10), getDate(), 105)) order by village_name";

            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                FunctionalNeedReportDTO territory = new FunctionalNeedReportDTO();
                territory.setVillage_id(rs.getString("village_id"));
                territory.setVillage_name(rs.getString("village_name"));
                villagelist.add(territory);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "getAppealVillageAll", "FunctionalNeedReportDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getAppealVillageAll");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "getAppealVillageAll", "FunctionalNeedReportDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getAppealVillageAll");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);


        }
        return villagelist;
    }

    /**
     *
     * @param datasource
     * @param districtid
     * @param mandalid
     * @param panchayatid
     * @param villageid
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public ArrayList alreadyEnteredVillagesAll(DataSource datasource, ArrayList villages, String districtid, String mandalid, String date)
            throws SADAREMDBException, SQLException {
        Connection con = null;
        Statement stmt = null;
        ArrayList villagelist = new ArrayList();
        ResultSet rs = null;
        String sql = null;
        try {
            con = DBConnection.getConnection();
            stmt = con.createStatement();
            //  Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(date);
            //  String fromdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);


            for (Iterator it = villages.iterator(); it.hasNext();) {
                FunctionalNeedReportDTO a = (FunctionalNeedReportDTO) it.next();

                sql = "select village_id from Appeal_Authority_Schedule with(nolock)  where district_id='" + districtid + "' and  mandal_id!='" + mandalid + "' and village_id!='" + 

a.getVillage_id() + "' and schedule_date='" + date + "'";
                rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    villagelist.add(rs.getString(1));
                }
            }



        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "alreadyEnteredVillagesAll", "FunctionalNeedReportDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"alreadyEnteredVillagesAll");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "alreadyEnteredVillagesAll", "FunctionalNeedReportDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"alreadyEnteredVillagesAll");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);


        }
        return villagelist;
    }

    /**
     *
     * @param datasource
     * @param districtid
     * @param mandalid
     * @param panchayatid
     * @param villageid
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public int insertVillages(DataSource datasource, ArrayList villages, String districtId, String mandalId, String fromDate, String loginID, String systemIP)
            throws SADAREMDBException, SQLException {
        Connection con = null;
        int village = 0;
        CallableStatement cstmt = null;
        String sql = null;
        Statement stmt = null;
        ResultSet rs = null;
        String checkStatusVillage = null;
        String checkStatusMandal = null;

        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            stmt = con.createStatement();



            for (Iterator it = villages.iterator(); it.hasNext();) {
                FunctionalNeedReportDTO a = (FunctionalNeedReportDTO) it.next();

                sql = "select mandal_id,village_id from Appeal_Authority_Schedule with(nolock) where district_id='" + districtId + "' and mandal_id='" + mandalId + "' "
                        + "and village_id='" + a.getVillage_id() + "' and schedule_date='" + fromDate + "'";
                rs = stmt.executeQuery(sql);

                if (rs != null) {
                    while (rs.next()) {
                        checkStatusMandal = rs.getString(1);
                        checkStatusVillage = rs.getString(2);
                    }
                }
                if (a.getVillage_id() != checkStatusVillage && !a.getVillage_id().equals(checkStatusVillage)) {
                    cstmt = con.prepareCall("{Call SP_Appeal_Authority_Schedule_Insert(?,?,?,?,?,?)}");
                    cstmt.setString(1, districtId);
                    cstmt.setString(2, mandalId);
                    cstmt.setString(3, a.getVillage_id());
                    cstmt.setString(4, fromDate);
                    cstmt.setString(5, loginID);
                    cstmt.setString(6, systemIP);
                    village = cstmt.executeUpdate();
                }

            }

            con.commit();
            con.setAutoCommit(true);

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "insertVillages", "FunctionalNeedReportDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"insertVillages");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "insertVillages", "FunctionalNeedReportDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"insertVillages");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);


        }
        return village;
    }

    /**
     *
     * @param datasource
     * @param districtid
     * @param mandalid
     * @param panchayatid
     * @param villageid
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public int insertVillage(DataSource datasource, String districtId, String mandalId, String villageId, String fromDate, String loginID, String systemIP)
            throws SADAREMDBException, SQLException {
        Connection con = null;
        int village = 0;
        CallableStatement cstmt = null;

        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);

            cstmt = con.prepareCall("{Call SP_Appeal_Authority_Schedule_Insert(?,?,?,?,?,?)}");
            cstmt.setString(1, districtId);
            cstmt.setString(2, mandalId);
            cstmt.setString(3, villageId);
            cstmt.setString(4, fromDate);
            cstmt.setString(5, loginID);
            cstmt.setString(6, systemIP);
            village = cstmt.executeUpdate();

            con.commit();
            con.setAutoCommit(true);

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "insertVillages", "FunctionalNeedReportDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"insertVillages");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "insertVillages", "FunctionalNeedReportDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"insertVillages");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(cstmt);

        }
        return village;
    }

    /**
     *
     * @param datasource
     * @param districtid
     * @param mandalid
     * @param villageid
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public ArrayList getMaritalStatusPersonalDetails(DataSource datasource, String districtId, String mandalId, String villageId, String habCode, String mStatus, 

String urbanId, String fromDate, String todate)
            throws SADAREMDBException, SQLException {

        Connection con = null;
        Statement stmt = null;
        ArrayList maritalList = new ArrayList();
        ResultSet rs = null;
        StringBuilder sql = new StringBuilder();
        HashMap results = null;
        try {
            con = DBConnection.getConnection();
            stmt = con.createStatement();

             Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(fromDate);
           
            String fromdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);
           
            Date toDate = new SimpleDateFormat("dd/mm/yyyy").parse(todate); 
       
            String toDates = new SimpleDateFormat("mm/dd/yyyy").format(toDate);
       


            sql.append("SELECT DISTINCT "
                    + "P.PERSON_CODE AS SADAREMCODE,P.SURNAME + SPACE(2) + P.FIRST_NAME AS PERSONNAME,'GENDER' = CASE  "
                    + " WHEN Gender = 1 THEN 'Male' WHEN Gender = 2 THEN 'Female' ELSE 'Not Entered' END,DATEDIFF(year,P.Date_of_Birth,GETDATE()),p.relation_name ,'EDUCATION' =CASE WHEN P.EDUCATION = 1 THEN 'Illiterate'  "
                    + " WHEN P.EDUCATION = 2 THEN 'Below 10th'  WHEN P.EDUCATION = 3 THEN '10th Class'  WHEN P.EDUCATION = 4 THEN 'Intermediate'  "
                    + "WHEN P.EDUCATION = 5 THEN 'Diploma/I.T.I'  WHEN P.EDUCATION = 6 THEN 'Graduate'  WHEN P.EDUCATION = 7 THEN 'Postgraduate'  "
                    + "ELSE 'Not Entered'  END,"
                    + " 'CASTE' =   CASE  WHEN P.CASTE = 1 THEN 'ST'  WHEN P.CASTE = 2 THEN 'SC'  WHEN P.CASTE = 3 THEN 'BC'  WHEN P.CASTE = 4 THEN 'OC'  "
                    + " WHEN P.CASTE = 5 THEN 'Minority'  WHEN P.CASTE = 6 THEN 'NA'  ELSE 'Not Entered'  END,     house_number+','+habitation_name+','+e.village_name +', '+d.mandal_name+','+s.district_name Address, coalesce(phone_no,'-') as phone_no FROM "
                    + "  DBO.TBLPERSON_PERSONAL_DETAILS P  with (nolock) ,   DBO.TBLPERSON_DISABILITY_DETAILS PD with(nolock) ,   "
                    + "DBO.TBLPERSON_DISABILITY_TOTALVALUE_DETAILS PT with(nolock) ,  tbldistrict_details s with(nolock) ,  tblmandal_details d with(nolock) ,"
                    + "  tblvillage_details e with(nolock) , tblhabitation_details h with(nolock)  WHERE   PD.PERSON_CODE = P.PERSON_CODE  AND  "
                    + " PT.PERSON_CODE      = P.PERSON_CODE  AND  PD.PERSON_CODE = PT.PERSON_CODE AND  P.STATUS= 'ACTIVE' "
                    + "  AND  PD.STATUS= 'ACTIVE'  AND  PT.STATUS= 'ACTIVE' AND VIEW_EDIT_MODE = 'View' AND  p.districtid = s.district_id and  p.districtid = d.district_id and  p.mandalid = d.mandal_id and  p.districtid = e.district_id and  p.mandalid = e.mandal_id and  p.villageid = e.village_id and    p.habcode = h.habitation_code and "
                    + "   (DATEADD(DD,0,DATEDIFF(DD,0,PD.UPDATED_DATE))) between  '" + fromdate + "' and '" + toDates + "'   and totaldisability > = 40  and p.marital_status='" + mStatus + "'");
            if (!districtId.equals("")) {
                sql.append("and p.district_id='" + districtId + "'");
            }
            if (!mandalId.equals("")) {
                sql.append(" and p.mandal_id='" + mandalId + "'");
            }
            if (!villageId.equals("")) {
                sql.append(" and p.village_id='" + villageId + "'");

                if (!habCode.equals("")) {
                    if (districtId.equals("16") && mandalId.equals("84") && villageId.equals("004") && habCode.equals("01")) {
                        sql.append(" and p.habitation_id in('00','" + habCode + "')");
                    } else {
                        sql.append(" and p.habitation_id='" + habCode + "'");
                    }
                }
            }
            if (urbanId.equals("Urban")) {
                sql.append(" and p.mandal_id > 79 ");
            } else if (urbanId.equals("Rural")) {
                sql.append(" and p.mandal_id < 80 ");
            }
            sql.append(" order by p.person_code");
            rs = stmt.executeQuery(sql.toString());
            while (rs.next()) {
                results = new HashMap();
                results.put("district_id", districtId);
                results.put("mandal_id", mandalId);
                results.put("village_id", villageId);
                results.put("PERSON_CODE", rs.getString(1));
                results.put("PERSONNAME", rs.getString(2));
                results.put("Gender", rs.getString(3));
                results.put("age_years", rs.getString(4));
                results.put("relation_name", rs.getString(5));
                results.put("EDUCATION", rs.getString(6));
                results.put("CASTE", rs.getString(7));
                results.put("Address", rs.getString(8));
                results.put("phone_no", rs.getString(9));
                 results.put("fromdate", fromDate);
                results.put("todate", todate);
                maritalList.add(results);
            }


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "getMaritalStatusPersonalDetails", "FunctionalNeedReportDAO", "DataBase");
           
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getMaritalStatusPersonalDetails");
        } catch (Exception sqlEx) 
        {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "getMaritalStatusPersonalDetails", "FunctionalNeedReportDAO", "Code");
              throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO","getMaritalStatusPersonalDetails");
        }
        finally
        {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);


        }
        return maritalList;
    }

    /**
     *
     * @param datasource
     * @param districtid
     * @param mandalid
     * @param villageid
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public ArrayList getParentsMaritalStatusDetails(DataSource datasource, String districtId, String mandalId, String villageId, String habCode, 

FunctionalNeedReportDTO functionalNeedReportDTO)
            throws SADAREMDBException, SQLException {
        Connection con = null;
        Statement stmt = null;
        ArrayList maritalList = new ArrayList();
        ResultSet rs = null;
        CallableStatement cstmt = null;
        HashMap results = null;
        try {
            con = DBConnection.getConnection();
            stmt = con.createStatement();

            Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(functionalNeedReportDTO.getFromdate());
            String fromdate = new SimpleDateFormat("dd/mm/yyyy").format(fdate);
            Date fdates = new SimpleDateFormat("dd/mm/yyyy").parse(functionalNeedReportDTO.getFromdate());
            String fromdates = new SimpleDateFormat("mm/dd/yyyy").format(fdates);
            Date tdate = new SimpleDateFormat("dd/mm/yyyy").parse(functionalNeedReportDTO.getTodate());
            String todate = new SimpleDateFormat("dd/mm/yyyy").format(tdate);
            Date tdates = new SimpleDateFormat("dd/mm/yyyy").parse(functionalNeedReportDTO.getTodate());
            String todates = new SimpleDateFormat("mm/dd/yyyy").format(tdates);

            if (districtId.equals("0") && mandalId.equals("0") && villageId.equals("0")) {

                cstmt = con.prepareCall("{Call [SP_Report3.6_Parentsmarraige_Districtwise_report](?,?)}");
                cstmt.setString(1, fromdates);
                cstmt.setString(2, todates);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    results = new HashMap();
                    results.put("district_id", rs.getString(1));
                    results.put("name", rs.getString(2));
                    results.put("no", rs.getString(3));
                    results.put("yes", rs.getString(4));
                    results.put("none", rs.getString(5));
                    results.put("fromdate", fromdate);
                    results.put("todate", todate);
                    maritalList.add(results);
                }

            } else if (!districtId.equals("0") && mandalId.equals("0") && villageId.equals("0")) {

                cstmt = con.prepareCall("{Call [SP_Report3.6_Parentsmarraige_Mandalwise_report](?,?,?)}");

                cstmt.setString(1, districtId);
                cstmt.setString(2, fromdates);
                cstmt.setString(3, todates);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    results = new HashMap();
                    results.put("district_id", rs.getString(1));
                    results.put("mandal_id", rs.getString(2));
                    results.put("name", rs.getString(3));
                    results.put("no", rs.getString(4));
                    results.put("yes", rs.getString(5));
                    results.put("none", rs.getString(6));
                    results.put("fromdate", fromdate);
                    results.put("todate", todate);
                    maritalList.add(results);
                }

            } else if (!districtId.equals("0") && !mandalId.equals("0") && villageId.equals("0")) {

                cstmt = con.prepareCall("{Call [SP_Report3.6_Parentsmarraige_Villagewise_report](?,?,?,?)}");
                cstmt.setString(1, districtId);
                cstmt.setString(2, mandalId);
                cstmt.setString(3, fromdates);
                cstmt.setString(4, todates);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    results = new HashMap();
                    results.put("district_id", rs.getString(1));
                    results.put("mandal_id", rs.getString(2));
                    results.put("village_id", rs.getString(3));
                    results.put("name", rs.getString(4));
                    results.put("no", rs.getString(5));
                    results.put("yes", rs.getString(6));
                    results.put("none", rs.getString(7));
                    results.put("fromdate", fromdate);
                    results.put("todate", todate);
                    maritalList.add(results);
                }

            } else if (!districtId.equals("0") && !mandalId.equals("0") && !villageId.equals("0")) {
                int no = 0;
                int yes = 0;
                int none = 0;
                cstmt = con.prepareCall("{Call [SP_Report3.6_Parentsmarraige_Habitationwise_report](?,?,?,?,?)}");
                cstmt.setString(1, districtId);
                cstmt.setString(2, mandalId);
                cstmt.setString(3, villageId);
                cstmt.setString(4, fromdates);
                cstmt.setString(5, todates);
                rs = cstmt.executeQuery();
                while (rs.next()) {
                    results = new HashMap();
                    results.put("district_id", rs.getString(1));
                    results.put("mandal_id", rs.getString(2));
                    results.put("village_id", rs.getString(3));
                    results.put("habitation_id", rs.getString(4));
                    results.put("name", rs.getString(5));
                    results.put("no", rs.getString(6));
                    results.put("yes", rs.getString(7));
                    results.put("none", rs.getString(8));
                    results.put("fromdate", fromdate);
                    results.put("todate", todate);
                    if ((rs.getString(1).equals("16") && rs.getString(2).equals("84") && rs.getString(3).equals("004")) && (rs.getString(4).equals("00") || 

rs.getString(4).equals("01"))) {

                        if (rs.getString(4).equals("00")) {

                            no = rs.getInt(6);
                            yes = rs.getInt(7);
                            none = rs.getInt(8);
                        }
                        if (rs.getString(4).equals("01")) {

                            no = no + rs.getInt(6);
                            yes = yes + rs.getInt(7);
                            none = none + rs.getInt(8);

                            results.put("no", no);
                            results.put("yes", yes);
                            results.put("none", none);
                            results.put("fromdate", fromdate);
                            results.put("todate", todate);
                            maritalList.add(results);
                        }
                    } else {
                        maritalList.add(results);
                    }
                }

            }



        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "getMaritalStatusPersonalDetails", "FunctionalNeedReportDAO", "DataBase");
            //DBConnection.rollbackConnection(con);
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getParentsMaritalStatusDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "getParentsMaritalStatusDetails", "FunctionalNeedReportDAO", "Code");
            //DBConnection.rollbackConnection(con);
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getParentsMaritalStatusDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);


        }
        return maritalList;
    }

    /**  Need change this method
     *
     * @param datasource
     * @param districtid
     * @param mandalid
     * @param villageid
     * @param pId
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public ArrayList getParentsMaritalIndivdualDetails(DataSource datasource, String districtId, String mandalId, String villageId, String habCode, String  parentStatus,String fromDate,String todate)
            throws SADAREMDBException, SQLException {
        Connection con = null;
        Statement stmt = null;
        ArrayList maritalList = new ArrayList();
        ResultSet rs = null;
        StringBuilder sql = new StringBuilder();
        HashMap results = null;
        try {
            con = DBConnection.getConnection();
            stmt = con.createStatement();
            // this query gets cluster id and cluster name

           // sql.append("select distinct person_id,person_code,first_name + space(2) + surname as name,relation_name,age_years from tblPerson_Personal_Details a ");

           Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(fromDate);

            String fromdates = new SimpleDateFormat("mm/dd/yyyy").format(fdate);

            Date toDate = new SimpleDateFormat("dd/mm/yyyy").parse(todate);

            String toDates = new SimpleDateFormat("mm/dd/yyyy").format(toDate);

                  sql.append("SELECT DISTINCT "
                    + "P.PERSON_CODE AS SADAREMCODE,P.SURNAME + SPACE(2) + P.FIRST_NAME AS PERSONNAME,'GENDER' = CASE  "
                    + " WHEN Gender = 1 THEN 'Male' WHEN Gender = 2 THEN 'Female' ELSE 'Not Entered' END,DATEDIFF(year,P.Date_of_Birth,GETDATE()),p.relation_name ,'EDUCATION' =CASE WHEN P.EDUCATION = 1 THEN 'Illiterate'  "
                    + " WHEN P.EDUCATION = 2 THEN 'Below 10th'  WHEN P.EDUCATION = 3 THEN '10th Class'  WHEN P.EDUCATION = 4 THEN 'Intermediate'  "
                    + "WHEN P.EDUCATION = 5 THEN 'Diploma/I.T.I'  WHEN P.EDUCATION = 6 THEN 'Graduate'  WHEN P.EDUCATION = 7 THEN 'Postgraduate'  "
                    + "ELSE 'Not Entered'  END,"
                    + " 'CASTE' =   CASE  WHEN P.CASTE = 1 THEN 'ST'  WHEN P.CASTE = 2 THEN 'SC'  WHEN P.CASTE = 3 THEN 'BC'  WHEN P.CASTE = 4 THEN 'OC'  "
                    + " WHEN P.CASTE = 5 THEN 'Minority'  WHEN P.CASTE = 6 THEN 'NA'  ELSE 'Not Entered'  END,     house_number+','+habitation_name+','+e.village_name +', '+d.mandal_name+','+s.district_name Address, coalesce(phone_no,'-') as phone_no FROM "
                    + "  DBO.TBLPERSON_PERSONAL_DETAILS P  with (nolock) ,   DBO.TBLPERSON_DISABILITY_DETAILS PD with(nolock) ,   "
                    + "DBO.TBLPERSON_DISABILITY_TOTALVALUE_DETAILS PT with(nolock) ,  tbldistrict_details s with(nolock) ,  tblmandal_details d with(nolock) ,"
                    + "  tblvillage_details e with(nolock) , tblhabitation_details h with(nolock)   WHERE   PD.PERSON_CODE      = P.PERSON_CODE  AND  "
                    + " PT.PERSON_CODE      = P.PERSON_CODE  AND  PD.PERSON_CODE      = PT.PERSON_CODE AND  P.STATUS= 'ACTIVE' "
                    + "  AND  PD.STATUS= 'ACTIVE'  AND  PT.STATUS= 'ACTIVE' AND   VIEW_EDIT_MODE = 'View' AND  p.districtid = s.district_id and  p.districtid = d.district_id and  p.mandalid = d.mandal_id and  p.districtid = e.district_id and  p.mandalid = e.mandal_id and  p.villageid = e.village_id and    p.habcode = h.habitation_code   and"
                    + "   (DATEADD(DD,0,DATEDIFF(DD,0,PD.UPDATED_DATE))) between  '" + fromdates + "' and '" + toDates + "'   and totaldisability > = 40  ");

            if (parentStatus == "0" || parentStatus.equals("0")) 
            {
                sql.append(" and p.parents_marriage='0' ");
            } 
            else if (parentStatus == "1" || parentStatus.equals("1")) 
            {
                sql.append(" and p.parents_marriage='1' ");
            }
            else 
            {
                sql.append(" and p.parents_marriage is null ");
            }


            if (districtId != "" && mandalId == "" && villageId == "")
            {
                sql.append("and p.district_id='" + districtId + "'");
            }
            if (districtId != "" && mandalId != "" && villageId == "") 
            {
                sql.append(" and p.district_id='" + districtId + "' and p.mandal_id='" + mandalId + "'");
            }
            if (districtId != "" && mandalId != "" && villageId != "") 
            {
                sql.append(" and p.district_id='" + districtId + "' and p.mandal_id='" + mandalId + "' and p.village_id='" + villageId + "'");
                if (habCode != "")
                {
                    if (districtId.equals("16") && mandalId.equals("84") && villageId.equals("004") && habCode.equals("01")) 
                    {
                        sql.append("and p.habitation_id in ('00','" + habCode + "')");
                    }
                    else 
                    {
                        sql.append("and p.habitation_id='" + habCode + "'");
                    }
                }
            }

            sql.append(" order by p.person_code");
        
            rs = stmt.executeQuery(sql.toString());
            while (rs.next()) 
            {
                results = new HashMap();
               results.put("district_id", districtId);
                results.put("mandal_id", mandalId);
                results.put("village_id", villageId);
                results.put("PERSON_CODE", rs.getString(1));
                results.put("PERSONNAME", rs.getString(2));
                results.put("Gender", rs.getString(3));
                results.put("age_years", rs.getString(4));
                results.put("relation_name", rs.getString(5));
                results.put("EDUCATION", rs.getString(6));
                results.put("CASTE", rs.getString(7));
                results.put("Address", rs.getString(8));
                results.put("phone_no", rs.getString(9));
                results.put("fromdate", fromDate);
                results.put("todate", todate);
                maritalList.add(results);
            }



        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "getParentsMaritalIndivdualDetails", "FunctionalNeedReportDAO", "DataBase");
            //DBConnection.rollbackConnection(con);
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getParentsMaritalIndivdualDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "getParentsMaritalIndivdualDetails", "FunctionalNeedReportDAO", "Code");
           //con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getParentsMaritalIndivdualDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);


        }
        return maritalList;
    }

    public ArrayList getPersonalDisWiseDetails(DataSource ds, String district_id, String mandal_id, String village_id, String disabilityid) throws SADAREMDBException, SQLException 
    {

        Connection con = null; 
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        String sql = null;
        ArrayList data = new ArrayList();
        String gender = null;
        String type = null;
        if (disabilityid != null) {
            if (disabilityid.equalsIgnoreCase("1"))
            {
                type = "Locomotor/OH";
            } 
            else if (disabilityid.equalsIgnoreCase("2")) 
            {
                type = "Visual Impairment ";
            } 
            else if (disabilityid.equalsIgnoreCase("3"))
            {
                type = "Hearing Impairment";
            } 
            else if (disabilityid.equalsIgnoreCase("4")) 
            {
                type = "Mental Retardation ";
            } 
            else if (disabilityid.equalsIgnoreCase("5"))
            {
                type = "Mental Illness";
            } 
            else if (disabilityid.equalsIgnoreCase("6"))
            {
                type = "Multiple Disability";
            }
        }

        try
        {
            con = DBConnection.getConnection(); 

            if (!district_id.equals("0") && mandal_id.equals("0") && village_id.equals("0")) 
            {
                sql = 	"select p.person_code,p.surname,p.age_years,p.First_Name , "
                        + " p.relation_name,p.gender , p.house_number,(select district_name from tbldistrict_details with(nolock)  "
                        + " where district_id=p.district_id)as districtname, "
                        + " (select mandal_name from tblmandal_details with(nolock)  "
                        + " where district_id=p.district_id and mandal_id=p.mandal_id)as mandalname, "
                        + " (select village_name from tblvillage_details with(nolock)  "
                        + " where district_id=p.district_id and mandal_id=p.mandal_id and p.village_id=village_id)as villagename, "
                        + " (select habitation_name from tblhabitation_details with(nolock) "
                        + " where district_id=p.district_id and mandal_id=p.mandal_id and p.village_id=village_id "
                        + " and habitation_id=p.habitation_id )as habitationname "
                        + " from dbo.tblperson_personal_details p  with (nolock)  "
                        + " where p.person_code in " 
                        + " (select person_code from AppellateAuthorityandTemporary_RegistrationDetails A with(nolock)  "
                        + " CROSS APPLY DATA_PERSON_CODE(A.PERSON_CODE) DPC"
                        + " where  p.district_id=DPC.DISTRICT_ID  "
                        + " and disabilityid=?)";
            
                preStmt = con.prepareStatement(sql);
                preStmt.setString(1, disabilityid);
            
            } 
            else if (!district_id.equals("0") && district_id != null && !mandal_id.equals("0") && village_id.equals("0")) 
            {
                sql = "select p.person_code,p.surname,p.age_years,p.First_Name , "
                        + "p.relation_name,p.gender , p.house_number,(select district_name from tbldistrict_details with(nolock)  "
                        + "where district_id=p.district_id)as districtname, "
                        + "(select mandal_name from tblmandal_details with(nolock)  "
                        + "where district_id=p.district_id and mandal_id=p.mandal_id)as mandalname, "
                        + "(select village_name from tblvillage_details with(nolock)  "
                        + "where district_id=p.district_id and mandal_id=p.mandal_id and p.village_id=village_id)as villagename, "
                        + "(select habitation_name from tblhabitation_details with(nolock)  "
                        + "where district_id=p.district_id and mandal_id=p.mandal_id and p.village_id=village_id "
                        + "and habitation_id=p.habitation_id )as habitationname "
                        + "from dbo.tblperson_personal_details p  with (nolock)  "
                        + "where p.person_code in "
                        + "(select person_code from AppellateAuthorityandTemporary_RegistrationDetails A with(nolock)  "
			+ "CROSS APPLY DATA_PERSON_CODE(A.PERSON_CODE) DPC"
                        + "where  p.district_id=DPC.DISTRICT_ID and "
                        + "p.mandal_id=DPC.MANDAL_ID and disabilityid=?)";

                
                preStmt = con.prepareStatement(sql);
                preStmt.setString(1, disabilityid);
                
                
            } 
            else if (!district_id.equals("0") && !mandal_id.equals("0") && !village_id.equals("0")) 
            {
                sql = "select p.person_code,p.surname,p.age_years,p.First_Name , "
                        + "p.relation_name,p.gender , p.house_number,(select district_name from tbldistrict_details with(nolock)  "
                        + "where district_id=p.district_id)as districtname,  "
                        + "(select mandal_name from tblmandal_details with(nolock)  "
                        + "where district_id=p.district_id and mandal_id=p.mandal_id)as mandalname, "
                        + "(select village_name from tblvillage_details with(nolock)  "
                        + "where district_id=p.district_id and mandal_id=p.mandal_id and p.village_id=village_id)as villagename, "
                        + "(select habitation_name from tblhabitation_details with(nolock)  "
                        + "where district_id=p.district_id and mandal_id=p.mandal_id and p.village_id=village_id "
                        + "and habitation_id=p.habitation_id )as habitationname "
                        + "from dbo.tblperson_personal_details p  with (nolock) "
                        + "where p.person_code in "
                        + "(select person_code from AppellateAuthorityandTemporary_RegistrationDetails A with(nolock)  "
			+ "CROSS APPLY DATA_PERSON_CODE(A.PERSON_CODE) DPC"
                        + "where  p.district_id=DPC.DISTRICT_ID and "
                        + "p.mandal_id=DPC.MANDAL_ID and p.village_id=DPC.VILLAGE_ID and disabilityid=?) ";
                

                
                preStmt = con.prepareStatement(sql);
                preStmt.setString(1, disabilityid);

            }
            
            rs = preStmt.executeQuery();
 
            while (rs.next()) 
            {

                gender = rs.getString(6);
                if (gender != null && gender.equalsIgnoreCase("2")) {
                    gender = "Female";

                } else if (gender != null && gender.equalsIgnoreCase("1")) {
                    gender = "Male";

                } else {
                    gender = "NE";
                }
                HashMap results = new HashMap();
                results.put("person_code", rs.getString(1));
                results.put("surname", rs.getString(2));
                results.put("age", rs.getString(3));
                results.put("name", rs.getString(2) + " " + rs.getString(4));
                results.put("rname", rs.getString(5));
                results.put("gender", gender);
                results.put("disability", type);
                results.put("houseno", rs.getString(7));
                results.put("dname", rs.getString(8));
                results.put("mname", rs.getString(9));
                results.put("vname", rs.getString(10));
                results.put("hname", rs.getString(11));
                data.add(results);
            }
            
            rs.close();
            preStmt.close();
            con.close();
        } 
        catch (SQLException sqlEx)
        {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalDisWiseDetails", "FunctionalNeedReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", "getPersonalDisWiseDetails");
        } 
        catch (Exception sqlEx) 
        {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalDisWiseDetails", "FunctionalNeedReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", "getPersonalDisWiseDetails");
        } 
        finally
        {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(preStmt);
        }

        return data;
    }

    public ArrayList getPersonalDisWiseDetails(DataSource ds, String district_id, String mandal_id, String village_id, String disabilityid, String hab, String fdate,String tdate)throws SADAREMDBException, SQLException 
    {

        Connection con = null; 
        ResultSet rs = null;
        String sql = null;
        PreparedStatement preStmt = null;
        
        ArrayList data = new ArrayList();
        String gender = null;
        String type = null;
        if (disabilityid != null) 
        {
            if (disabilityid.equalsIgnoreCase("1")) 
            {
                type = "Locomotor/OH";
            } 
            else if (disabilityid.equalsIgnoreCase("2")) 
            {
                type = "Visual Impairment ";
            }
            else if (disabilityid.equalsIgnoreCase("3"))
            {
                type = "Hearing Impairment";
            } 
            else if (disabilityid.equalsIgnoreCase("4")) 
            {
                type = "Mental Retardation ";
            } 
            else if (disabilityid.equalsIgnoreCase("5")) 
            {
                type = "Mental Illness";
            } 
            else if (disabilityid.equalsIgnoreCase("6")) 
            {
                type = "Multiple Disability";
            }
        }


        try 
        {

//fdate=new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(fdate));
            //  tdate=new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(tdate));
            con = DBConnection.getConnection(); 
            
            if (fdate != null && !fdate.contains("-")) 
            {
            	  fdate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(fdate));
            } 
            
            if (tdate != null && !tdate.contains("-")) 
            {
            	tdate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(tdate));
            }  
            
            if (!district_id.equals("0") && mandal_id.equals("0") && village_id.equals("0")) 
            {

                sql = "select  p.person_code,p.surname,DATEDIFF(year,p.Date_of_Birth,GETDATE()),p.First_Name ,p.relation_name,p.gender,p.house_number, "
                        + " d.district_name as  district_name,m.mandal_name as mandal_name,"
                        + " v.village_name as village_name,h.habitation_name as habName"
                        + " from dbo.tblperson_personal_details p  with (nolock) "
                        + " CROSS APPLY DATA_PERSON_CODE(P.PERSON_CODE) DPC"
                        + " join tbldistrict_details D with(nolock)  on(p.district_id=d.district_id)"
                        + " join tblmandal_details M with(nolock)  on(p.mandal_id=M.mandal_id and m.district_id=d.district_id)"
                        + " join tblvillage_details V with(nolock) on(p.village_id=v.village_id and m.district_id=v.district_id and m.mandal_id=v.mandal_id and v.district_id=d.district_id)"
                        + " join tblhabitation_details h with(nolock)  on(p.habitation_id=h.habitation_id and "
                        + " v.district_id=h.district_id and v.mandal_id=h.mandal_id and "
                        + " v.village_id=h.village_id and d.district_id=h.district_id and"
                        + " m.district_id=h.district_id and m.mandal_id=h.mandal_id and"
                        + " v.village_id=h.village_id and DPC.ASSEMBLY_ID = h.assembly_id)"
                        + " JOIN AppellateAuthorityandTemporary_RegistrationDetails ATR with(nolock) "
                        + " on(p.person_code=ATR.person_code and ATR.disabilityid=? and DPC.DISTRICT_ID=? and "
                        + " (DATEADD(DD,0,DATEDIFF(DD,0,ATR.updateddate))) BETWEEN  ? AND ? ) order by p.person_code";


		                preStmt = con.prepareStatement(sql);
		                preStmt.setString(1, disabilityid);
		                preStmt.setString(2, district_id);
		                preStmt.setString(3, fdate);
		                preStmt.setString(4, tdate);
                 
                
            } 
            else if (!district_id.equals("0") && district_id != null && !mandal_id.equals("0") && village_id.equals("0")) 
            {
	                /* sql = "select p.person_code,p.surname,p.age_years,p.First_Name , "
	                + "p.relation_name,p.gender , p.house_number,(select district_name from tbldistrict_details "
	                + "where district_id=p.district_id)as districtname, "
	                + "(select mandal_name from tblmandal_details "
	                + "where district_id=p.district_id and mandal_id=p.mandal_id)as mandalname, "
	                + "(select village_name from tblvillage_details "
	                + "where district_id=p.district_id and mandal_id=p.mandal_id and p.village_id=village_id)as villagename, "
	                + "(select habitation_name from tblhabitation_details "
	                + "where district_id=p.district_id and mandal_id=p.mandal_id and p.village_id=village_id "
	                + "and habitation_id=p.habitation_id )as habitationname "
	                + "from dbo.tblperson_personal_details p  "
	                + "where p.person_code in "
	                + "(select person_code from AppellateAuthorityandTemporary_RegistrationDetails A"
			+ "CROSS APPLY DATA_PERSON_CODE(A.PERSON_CODE) DPC "
	                + "where   "
	                + " disabilityid='" + disabilityid + "' and DPC.DISTRICT_ID='"+district_id+"' and DPC.MANDAL_ID='"+mandal_id+"' and (DATEADD(DD,0,DATEDIFF
	
	(DD,0,updateddate))) BETWEEN  '"+fdate+"' AND '"+tdate+"')";*/


                sql = "select  p.person_code,p.surname,DATEDIFF(year,P.Date_of_Birth,GETDATE()),p.First_Name ,p.relation_name,p.gender,p.house_number, "
                        + " d.district_name as  district_name,m.mandal_name as mandal_name,"
                        + " v.village_name as village_name,h.habitation_name as habName"
                        + " from dbo.tblperson_personal_details p  with (nolock) "
			+ " CROSS APPLY DATA_PERSON_CODE(P.PERSON_CODE) DPC"
                        + " join tbldistrict_details D with(nolock)  on(p.district_id=d.district_id)"
                        + " join tblmandal_details M with(nolock)  on(p.mandal_id=M.mandal_id and m.district_id=d.district_id)"
                        + " join tblvillage_details V with(nolock)  on(p.village_id=v.village_id and m.district_id=v.district_id and m.mandal_id=v.mandal_id and v.district_id=d.district_id)"
                        + " join tblhabitation_details h with(nolock)  on(p.habitation_id=h.habitation_id and "
                        + " v.district_id=h.district_id and v.mandal_id=h.mandal_id and "
                        + " v.village_id=h.village_id and d.district_id=h.district_id and"
                        + " m.district_id=h.district_id and m.mandal_id=h.mandal_id and"
                        + " v.village_id=h.village_id and DPC.ASSEMBLY_ID = h.assembly_id)"
                        + " JOIN AppellateAuthorityandTemporary_RegistrationDetails ATR with(nolock) "
                        + " on(p.person_code=ATR.person_code and ATR.disabilityid=? and DPC.DISTRICT_ID=? and DPC.MANDAL_ID =? and  "
                        + " (DATEADD(DD,0,DATEDIFF(DD,0,ATR.updateddate))) BETWEEN  ? AND ? ) order by p.person_code";

                
		                preStmt = con.prepareStatement(sql);
		                preStmt.setString(1, disabilityid);
		                preStmt.setString(2, district_id);
		                preStmt.setString(3, mandal_id);
		                preStmt.setString(4, fdate);
		                preStmt.setString(5, tdate);
                
                
                
            } 
            else if (!district_id.equals("0") && !mandal_id.equals("0") && !village_id.equals("0") && hab.equals("0")) 
            {
                /* sql = "select p.person_code,p.surname,p.age_years,p.First_Name , "
                + "p.relation_name,p.gender , p.house_number,(select district_name from tbldistrict_details  "
                + "where district_id=p.district_id)as districtname,  "
                + "(select mandal_name from tblmandal_details  "
                + "where district_id=p.district_id and mandal_id=p.mandal_id)as mandalname, "
                + "(select village_name from tblvillage_details  "
                + "where district_id=p.district_id and mandal_id=p.mandal_id and p.village_id=village_id)as villagename, "
                + "(select habitation_name from tblhabitation_details  "
                + "where district_id=p.district_id and mandal_id=p.mandal_id and p.village_id=village_id "
                + "and habitation_id=p.habitation_id )as habitationname "
                + "from dbo.tblperson_personal_details p "
                + "where p.person_code in "
                + "(select person_code from AppellateAuthorityandTemporary_RegistrationDetails A "
		+ " CROSS APPLY DATA_PERSON_CODE(A.PERSON_CODE) DPC"
                + "where   "
                + "disabilityid='" + disabilityid + "' and DPC.DISTRICT_ID='"+district_id+"' and DPC.MANDAL_ID='"+mandal_id+"' and DPC.VILLAGE_ID='"+village_id+"' and 

(DATEADD(DD,0,DATEDIFF(DD,0,updateddate))) BETWEEN  '"+fdate+"' AND '"+tdate+"') ";*/
                sql = "select  p.person_code,p.surname,DATEDIFF(year,P.Date_of_Birth,GETDATE()),p.First_Name ,p.relation_name,p.gender,p.house_number, "
                        + " d.district_name as  district_name,m.mandal_name as mandal_name,"
                        + " v.village_name as village_name,h.habitation_name as habName"
                        + " from dbo.tblperson_personal_details p  with (nolock) "
			+ " CROSS APPLY DATA_PERSON_CODE(P.PERSON_CODE) DPC "
                        + " join tbldistrict_details D with(nolock)  on(p.district_id=d.district_id)"
                        + " join tblmandal_details M with(nolock)  on(p.mandal_id=M.mandal_id and m.district_id=d.district_id)"
                        + " join tblvillage_details V with(nolock)  on(p.village_id=v.village_id and m.district_id=v.district_id and m.mandal_id=v.mandal_id and v.district_id=d.district_id)"
                        + " join tblhabitation_details h with(nolock)  on(p.habitation_id=h.habitation_id and "
                        + " v.district_id=h.district_id and v.mandal_id=h.mandal_id and "
                        + " v.village_id=h.village_id and d.district_id=h.district_id and"
                        + " m.district_id=h.district_id and m.mandal_id=h.mandal_id and"
                        + " v.village_id=h.village_id and DPC.assembly_id = h.assembly_id)"
                        + " JOIN AppellateAuthorityandTemporary_RegistrationDetails ATR with(nolock) "
                        + " on(p.person_code=ATR.person_code and ATR.disabilityid=? and DPC.DISTRICT_ID=? and DPC.MANDAL_ID=? and DPC.VILLAGE_ID=? and  "
                        + " (DATEADD(DD,0,DATEDIFF(DD,0,ATR.updateddate))) BETWEEN  ? AND ?) order by p.person_code";


                preStmt = con.prepareStatement(sql);
                preStmt.setString(1, disabilityid);
                preStmt.setString(2, district_id);
                preStmt.setString(3, mandal_id);
                preStmt.setString(4, village_id);
                preStmt.setString(5, fdate);
                preStmt.setString(6, tdate);


            } 
            else if (!district_id.equals("0") && !mandal_id.equals("0") && !village_id.equals("0") && !hab.equals("0")) 
            {
                /* sql = "select p.person_code,p.surname,p.age_years,p.First_Name , "
                + "p.relation_name,p.gender , p.house_number,(select district_name from tbldistrict_details  "
                + "where district_id=p.district_id)as districtname,  "
                + "(select mandal_name from tblmandal_details  "
                + "where district_id=p.district_id and mandal_id=p.mandal_id)as mandalname, "
                + "(select village_name from tblvillage_details  "
                + "where district_id=p.district_id and mandal_id=p.mandal_id and p.village_id=village_id)as villagename, "
                + "(select habitation_name from tblhabitation_details  "
                + "where district_id=p.district_id and mandal_id=p.mandal_id and p.village_id=village_id "
                + "and habitation_id=p.habitation_id )as habitationname "
                + "from dbo.tblperson_personal_details p "
                + "where p.person_code in "
                + "(select person_code from AppellateAuthorityandTemporary_RegistrationDetails A"
		+ " CROSS APPLY DATA_PERSON_CODE(A.PERSON_CODE) DPC"
                + "where   "
                + "disabilityid='" + disabilityid + "' and DPC.DISTRICT_ID='"+district_id+"' and DPC.MANDAL_ID='"+mandal_id+"' " +
                "and DPC.VILLAGE_ID='"+village_id+"' "+" and DPC.HABITATION_ID='"+hab+"' and (DATEADD(DD,0,DATEDIFF(DD,0,updateddate))) BETWEEN  '"+fdate+"' AND 

'"+tdate+"') ";*/
                sql = "select  p.person_code,p.surname,p.age_years,p.First_Name ,p.relation_name,p.gender,p.house_number, "
                        + " d.district_name as  district_name,m.mandal_name as mandal_name,"
                        + " v.village_name as village_name,h.habitation_name as habName"
                        + " from dbo.tblperson_personal_details p  with (nolock) "
			+ " CROSS APPLY DATA_PERSON_CODE(P.PERSON_CODE) DPC"
                        + " join tbldistrict_details D with(nolock) on(p.district_id=d.district_id)"
                        + " join tblmandal_details M with(nolock) on(p.mandal_id=M.mandal_id and m.district_id=d.district_id)"
                        + " join tblvillage_details V with(nolock)  on(p.village_id=v.village_id and m.district_id=v.district_id and m.mandal_id=v.mandal_id and v.district_id=d.district_id)"
                        + " join tblhabitation_details h with(nolock)  on(p.habitation_id=h.habitation_id and "
                        + " v.district_id=h.district_id and v.mandal_id=h.mandal_id and "
                        + " v.village_id=h.village_id and d.district_id=h.district_id and"
                        + " m.district_id=h.district_id and m.mandal_id=h.mandal_id and"
                        + " v.village_id=h.village_id and DPC.assembly_id = h.assembly_id)"
                        + " JOIN AppellateAuthorityandTemporary_RegistrationDetails ATR with(nolock)  "
                        + " on(p.person_code=ATR.person_code and ATR.disabilityid=? and DPC.district_id=? and DPC.Mandal_ID=? and DPC.VILLAGE_ID=? and  "
                        + "  DPC.HABITATION_ID=? and (DATEADD(DD,0,DATEDIFF(DD,0,ATR.updateddate))) BETWEEN  ? AND ?) order by p.person_code";
                
                
		                preStmt = con.prepareStatement(sql);
		                preStmt.setString(1, disabilityid);
		                preStmt.setString(2, district_id);
		                preStmt.setString(3, mandal_id);
		                preStmt.setString(4, village_id);
		                preStmt.setString(5, hab);
		                preStmt.setString(6, fdate);
		                preStmt.setString(7, tdate);
                
                
                
            }
 
            rs=preStmt.executeQuery();
            
            while (rs.next()) 
            {

                gender = rs.getString(6);
                if (gender != null && gender.equalsIgnoreCase("2"))
                {
                    gender = "Female";

                } else if (gender != null && gender.equalsIgnoreCase("1")) {
                    gender = "Male";

                } else {
                    gender = "NE";
                }
                HashMap results = new HashMap();
                results.put("person_code", "SID" + rs.getString(1));
                results.put("surname", rs.getString(2));
                results.put("age", rs.getString(3));
                results.put("name", rs.getString(2) + " " + rs.getString(4));
                results.put("rname", rs.getString(5));
                results.put("gender", gender);
                results.put("disability", type);
                results.put("houseno", rs.getString(7));
                results.put("dname", rs.getString(8));
                results.put("mname", rs.getString(9));
                results.put("vname", rs.getString(10));
                results.put("hname", rs.getString(11));
                results.put("fDate", fdate);
                results.put("tDate", tdate);

                data.add(results);
            }
            
            rs.close();
            preStmt.close();
            con.close();
            
        } 
        catch (SQLException sqlEx)
        {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalDisWiseDetails", "FunctionalNeedReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", "getPersonalDisWiseDetails");
        }
        catch (Exception sqlEx) 
        {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPersonalDisWiseDetails", "FunctionalNeedReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO","getPersonalDisWiseDetails");
        }
        finally
        {
        	DBConnection.closeConnection(con);
        	DBConnection.closeResultSet(rs);
        	DBConnection.closeStatement(preStmt);
        }

        return data;
    }

    //Appleat Authority
    public ArrayList getApealAuthorityCount(DataSource ds, String district_id, String mandal_id, String village_id, String fdate, String tdate) throws SADAREMDBException, SQLException 
    {
        Connection con = null; 
        ResultSet rs = null;
        String sqlDistrict = null;
        String sqlMandal = null;
        String sqlVillage = null;
        String sqlHab = null;
        ArrayList data = new ArrayList();
        PreparedStatement ps = null;//dd/MM/yyyy, yyyy-MM-dd 
        ArrayList casteDetails = new ArrayList();

        try 
        {


            if (fdate != null && !fdate.contains("-")) 
            {
            	fdate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(fdate));

            } 

            if (tdate != null && !tdate.contains("-")) 
            {
            	tdate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(tdate));
            }
            
            con = DBConnection.getConnection(); 
            
            if (district_id == null)
            {
                district_id = "0";
            }
            
            if (mandal_id == null) 
            {
                mandal_id = "0";
            }
            
            if (village_id == null) 
            {
                village_id = "0";
            }
            
            //and createddate <='2011-08-29' and createddate>= '2011-08-28
            //(DATEADD(DD,0,DATEDIFF(DD,0,updateddate))) BETWEEN  '08/28/2011' AND '08/28/2011' 
            
            if (district_id.equals("0")) 
            {
                sqlDistrict = 
                		" select b.district_id, b.district_name ,(select count(disabilityid) from \n"+
                		" AppellateAuthorityandTemporary_RegistrationDetails t with(nolock)  inner join tblperson_personal_details p with(nolock)  on p.person_code=t.Person_Code  where disabilityid=1 and \n"+
                		" p.DistrictID =b.district_id and (DATEADD(DD,0,DATEDIFF(DD,0,updateddate))) \n"+
                		" BETWEEN  ? AND ? ) as oh,(select count(disabilityid) \n"+
                		" from AppellateAuthorityandTemporary_RegistrationDetails t with(nolock)  inner join tblperson_personal_details p with(nolock)  on p.person_code=t.Person_Code  where disabilityid=2 and \n"+
                		" p.DistrictID =b.district_id and (DATEADD(DD,0,DATEDIFF(DD,0,updateddate))) \n"+
                		" BETWEEN  ? AND ?) as vi ,(select count(disabilityid) from \n"+
                		" AppellateAuthorityandTemporary_RegistrationDetails t with(nolock)  inner join tblperson_personal_details p with(nolock) on p.person_code=t.Person_Code  where disabilityid=3 and \n"+
                		" p.DistrictID =b.district_id and (DATEADD(DD,0,DATEDIFF(DD,0,updateddate))) \n"+
                		" BETWEEN  ? AND ?) as hi,(select count(disabilityid) from \n"+
                		" AppellateAuthorityandTemporary_RegistrationDetails t with(nolock)  inner join tblperson_personal_details p with(nolock)  on p.person_code=t.Person_Code  where disabilityid=4 and \n"+
                		" p.DistrictID =b.district_id and (DATEADD(DD,0,DATEDIFF(DD,0,updateddate)))\n"+
                		" BETWEEN  ? AND ?) as mr,(select count(disabilityid) from \n"+
                		" AppellateAuthorityandTemporary_RegistrationDetails t with(nolock)  inner join tblperson_personal_details p with(nolock)  on p.person_code=t.Person_Code  where disabilityid=5 and \n"+
                		" p.DistrictID =b.district_id and (DATEADD(DD,0,DATEDIFF(DD,0,updateddate)))\n"+
                		" BETWEEN  ? AND ?) as mi,(select count(disabilityid) from \n"+
                		" AppellateAuthorityandTemporary_RegistrationDetails t with(nolock)  inner join tblperson_personal_details p with(nolock)  on p.person_code=t.Person_Code  where disabilityid=6 and \n"+
                		" p.DistrictID =b.district_id and (DATEADD(DD,0,DATEDIFF(DD,0,updateddate))) \n"+
                		" BETWEEN  ? AND ?) as multi  \n"+
                		" from AppellateAuthorityandTemporary_RegistrationDetails a with(nolock)  join tblperson_personal_details p   \n"+
                		" with (nolock) on (p.person_code=a.person_code)  inner join tblDistrict_details b on \n"+
                		" p.DistrictID =b.district_id and (DATEADD(DD,0,DATEDIFF(DD,0,a.updateddate)))\n"+
                		" BETWEEN  ? AND ? group by\n"+
                		" p.districtid, b.district_id, b.district_name order by b.district_name";

                ps = con.prepareStatement(sqlDistrict);

                ps.setString(1, fdate);
                ps.setString(2, tdate);
                ps.setString(3, fdate);
                ps.setString(4, tdate);
                ps.setString(5, fdate);
                ps.setString(6, tdate);
                ps.setString(7, fdate);
                ps.setString(8, tdate);
                ps.setString(9, fdate);
                ps.setString(10, tdate);
                ps.setString(11, fdate);
                ps.setString(12, tdate);
                ps.setString(13, fdate);
                ps.setString(14, tdate);
                
                rs = ps.executeQuery();
                while (rs.next()) 
                {

                    HashMap results = new HashMap();
                    results.put("district_id", rs.getString(1));
                    results.put("name", rs.getString(2));
                    results.put("oh", rs.getString(3));
                    results.put("vi", rs.getString(4));
                    results.put("hi", rs.getString(5));
                    results.put("mr", rs.getString(6));
                    results.put("mi", rs.getString(7));
                    results.put("multi", rs.getString(8));
                    results.put("fDate", fdate);
                    results.put("tDate", tdate);
                    results.put("names", rs.getString(2));
                    data.add(results);
                }
            }
            /** Getting the details from mandal wise and details */
            if (village_id.equals("0") && !district_id.equals("0") && mandal_id.equals("0")) 
            {
                sqlDistrict = "select b.district_id,b.mandal_id,b.mandal_name,"
                		 + "(select count(disabilityid) from AppellateAuthorityandTemporary_RegistrationDetails t with(nolock)  inner join tblperson_personal_details p with(nolock)  on p.person_code=t.Person_Code where disabilityid=1 and p.DistrictID =b.district_id and p.mandalid =b.mandal_id and (DATEADD(DD,0,DATEDIFF(DD,0,updateddate))) BETWEEN  '"+ fdate + "' AND '" + tdate + "') as oh,"
                		 + "(select count(disabilityid) from AppellateAuthorityandTemporary_RegistrationDetails t with(nolock)  inner join tblperson_personal_details p with(nolock)  on p.person_code=t.Person_Code where disabilityid=2 and p.DistrictID =b.district_id and p.mandalid =b.mandal_id and (DATEADD(DD,0,DATEDIFF(DD,0,updateddate))) BETWEEN  '" + fdate + "' AND '" + tdate + "') as vi,"
                		 + "(select count(disabilityid) from AppellateAuthorityandTemporary_RegistrationDetails t with(nolock)  inner join tblperson_personal_details p with(nolock)  on p.person_code=t.Person_Code where disabilityid=3 and p.DistrictID =b.district_id and p.mandalid =b.mandal_id and (DATEADD(DD,0,DATEDIFF(DD,0,updateddate))) BETWEEN  '"+ fdate + "' AND '" + tdate + "') as hi,"
                		 + "(select count(disabilityid) from AppellateAuthorityandTemporary_RegistrationDetails t with(nolock)  inner join tblperson_personal_details p with(nolock)  on p.person_code=t.Person_Code where disabilityid=4 and p.DistrictID =b.district_id and p.mandalid =b.mandal_id and (DATEADD(DD,0,DATEDIFF(DD,0,updateddate))) BETWEEN  '"+ fdate + "' AND '" + tdate + "') as mr,"
                		 + "(select count(disabilityid) from AppellateAuthorityandTemporary_RegistrationDetails t with(nolock)  inner join tblperson_personal_details p with(nolock)  on p.person_code=t.Person_Code where disabilityid=5 and p.DistrictID =b.district_id and p.mandalid =b.mandal_id and (DATEADD(DD,0,DATEDIFF(DD,0,updateddate))) BETWEEN  '"+ fdate + "' AND '" + tdate + "') as mi,"
                		 + "(select count(disabilityid) from AppellateAuthorityandTemporary_RegistrationDetails t with(nolock)  inner join tblperson_personal_details p with(nolock)  on p.person_code=t.Person_Code where disabilityid=6 and p.DistrictID =b.district_id and p.mandalid =b.mandal_id and (DATEADD(DD,0,DATEDIFF(DD,0,updateddate))) BETWEEN  '"+ fdate + "' AND '" + tdate + "') as multi "
                		  + " from AppellateAuthorityandTemporary_RegistrationDetails a with(nolock)  "
                		  + " CROSS APPLY DATA_PERSON_CODE(A.PERSON_CODE) DPC "
                		  + " join tblperson_personal_details p   with (nolock) on (p.person_code=a.person_code and p.district_id=?)"
                		  + " inner join tblMandal_details b with(nolock)  on (p.districtid=b.district_id and p.mandalid=b.mandal_id and "
                		  + " b.district_id=? and (DATEADD(DD,0,DATEDIFF(DD,0,a.updateddate))) BETWEEN  ? AND ? ) \n"
                		  + " group by b.district_id,DPC.DISTRICT_ID,b.mandal_name,b.mandal_id,DPC.MANDAL_ID order by b.mandal_name;";
                
		
		                ps = con.prepareStatement(sqlDistrict);
		
		                ps.setString(1, district_id);
		                ps.setString(2, district_id);
		                ps.setString(3, fdate);
		                ps.setString(4, tdate); 
		                
		                rs = ps.executeQuery();
                
		                while (rs.next()) 
		                {
		                    HashMap results = new HashMap();
		                    results.put("district_id", rs.getString(1));
		                    results.put("mandal_id", rs.getString(2));
		                    results.put("name", rs.getString(3));
		                    results.put("oh", rs.getString(4));
		                    results.put("vi", rs.getString(5));
		                    results.put("hi", rs.getString(6));
		                    results.put("mr", rs.getString(7));
		                    results.put("mi", rs.getString(8));
		                    results.put("multi", rs.getString(9));
		                    results.put("fDate", fdate);
		                    results.put("tDate", tdate);
		
		                    data.add(results);
		                }
            }
            /** Getting the details from village wise and details */
            if (village_id.equals("0") && !district_id.equals("0") && !mandal_id.equals("0")) 
            {
                sqlDistrict = 
                				" SELECT b.district_id,b.mandal_id,b.village_id,coalesce(b.village_name,'-') as name,"
                				+ "(SELECT count(disabilityid) from AppellateAuthorityandTemporary_RegistrationDetails t inner join tblperson_personal_details p on p.person_code=t.Person_Code where disabilityid=1 and p.DistrictID =b.district_id and p.mandalid =b.mandal_id and p.villageid =b.village_id and \n"
                				+ " (DATEADD(DD,0,DATEDIFF(DD,0,updateddate))) BETWEEN  ? AND ?) as oh,"
                				+ "(SELECT count(disabilityid) from AppellateAuthorityandTemporary_RegistrationDetails t inner join tblperson_personal_details p on p.person_code=t.Person_Code where disabilityid=2 and p.DistrictID =b.district_id and p.mandalid =b.mandal_id and p.villageid=b.village_id and \n"
                				+ " (DATEADD(DD,0,DATEDIFF(DD,0,updateddate))) BETWEEN  ? AND ?) as vi ,"
                				+ "(select count(disabilityid) from AppellateAuthorityandTemporary_RegistrationDetails t inner join tblperson_personal_details p on p.person_code=t.Person_Code where disabilityid=3 and p.DistrictID =b.district_id and p.mandalid =b.mandal_id and p.villageid =b.village_id and \n"
                				+ " (DATEADD(DD,0,DATEDIFF(DD,0,updateddate))) BETWEEN  ? AND ?) as hi,"
                				+ "(select count(disabilityid) from AppellateAuthorityandTemporary_RegistrationDetails t inner join tblperson_personal_details p on p.person_code=t.Person_Code where disabilityid=4 and p.DistrictID =b.district_id and p.mandalid =b.mandal_id and p.villageid=b.village_id and \n"
                				+ " (DATEADD(DD,0,DATEDIFF(DD,0,updateddate))) BETWEEN  ? AND ?) as mr,"
                				+ "(select count(disabilityid) from AppellateAuthorityandTemporary_RegistrationDetails t inner join tblperson_personal_details p on p.person_code=t.Person_Code where disabilityid=5 and p.DistrictID =b.district_id and p.mandalid =b.mandal_id and p.villageid=b.village_id and \n"
                				+ " (DATEADD(DD,0,DATEDIFF(DD,0,updateddate))) BETWEEN  ? AND ?) as mi,"
                				+ "(select count(disabilityid) from AppellateAuthorityandTemporary_RegistrationDetails t inner join tblperson_personal_details p on p.person_code=t.Person_Code where disabilityid=6 and p.DistrictID =b.district_id and p.mandalid =b.mandal_id and p.villageid=b.village_id and \n"
                				+ " (DATEADD(DD,0,DATEDIFF(DD,0,updateddate))) BETWEEN  ? AND ?) as multi "
                				+ " from dbo.AppellateAuthorityandTemporary_RegistrationDetails a "
                				+ " join tblperson_personal_details p   with (nolock) on (p.person_code=a.person_code and p.district_id=? and p.mandal_id=?)"
                				+ " inner join dbo.tblVillage_details b on (p.districtid=b.district_id and "
                				+ " p.mandalid=b.mandal_id and p.villageid=b.village_id and "
                				+ " p.districtid=? and p.mandalid=? and (DATEADD(DD,0,DATEDIFF (DD,0,a.updateddate))) BETWEEN  ? AND ?)"
                				+ " group by b.district_id,p.districtid,b.village_name,b.mandal_id,b.village_id,p.mandalid,p.villageid order by b.village_name;";

                ps = con.prepareStatement(sqlDistrict);
                
                
                ps.setString(1, fdate);
                ps.setString(2, tdate);
                ps.setString(3, fdate);
                ps.setString(4, tdate);
                ps.setString(5, fdate);
                ps.setString(6, tdate);
                ps.setString(7, fdate);
                ps.setString(8, tdate);
                ps.setString(9, fdate);
                ps.setString(10, tdate);
                ps.setString(11, fdate);
                ps.setString(12, tdate);
                ps.setString(13, district_id);
                ps.setString(14, mandal_id);
                ps.setString(15, district_id);
                ps.setString(16, mandal_id);
                ps.setString(17, fdate);
                ps.setString(18, tdate); 
                
                rs = ps.executeQuery();
                  
                  
                while (rs.next())
                {
                    HashMap results = new HashMap();
                    results.put("district_id", rs.getString(1));
                    results.put("mandal_id", rs.getString(2));
                    results.put("village_id", rs.getString(3));
                    results.put("name", rs.getString(4));
                    results.put("oh", rs.getString(5));
                    results.put("vi", rs.getString(6));
                    results.put("hi", rs.getString(7));
                    results.put("mr", rs.getString(8));
                    results.put("mi", rs.getString(9));
                    results.put("multi", rs.getString(10));
                    results.put("fDate", fdate);
                    results.put("tDate", tdate);
                    data.add(results);
                }
            }
            /**Get Habitation details */
            if (!village_id.equals("0") && !district_id.equals("0") && !mandal_id.equals("0")) 
            {
                sqlDistrict = "select b.district_id,b.mandal_id,b.village_id,b.habitation_id,b.habitation_name,"
                        + "(select count(disabilityid) from dbo.AppellateAuthorityandTemporary_RegistrationDetails t inner join tblperson_personal_details p on p.person_code=t.Person_Code where disabilityid=1 and p.DistrictID =b.district_id and p.mandalid =b.mandal_id and p.villageid =b.village_id and  p.habitationid =b.habitation_id and \n"
                        + " (DATEADD(DD,0,DATEDIFF(DD,0,updateddate))) BETWEEN  ? AND ?) as oh,"
                        + "(select count(disabilityid) from dbo.AppellateAuthorityandTemporary_RegistrationDetails t inner join tblperson_personal_details p on p.person_code=t.Person_Code where disabilityid=2 and p.DistrictID =b.district_id and p.mandalid =b.mandal_id and p.villageid =b.village_id and  p.habitationid =b.habitation_id and \n"
                        + " (DATEADD(DD,0,DATEDIFF(DD,0,updateddate))) BETWEEN  ? AND ?) as vi,"
                        + "(select count(disabilityid) from dbo.AppellateAuthorityandTemporary_RegistrationDetails t inner join tblperson_personal_details p on p.person_code=t.Person_Code where disabilityid=3 and p.DistrictID =b.district_id and p.mandalid =b.mandal_id and p.villageid =b.village_id and  p.habitationid =b.habitation_id and \n"
                        + " (DATEADD(DD,0,DATEDIFF(DD,0,updateddate))) BETWEEN  ? AND ?) as hi,"
                        + "(select count(disabilityid) from dbo.AppellateAuthorityandTemporary_RegistrationDetails t inner join tblperson_personal_details p on p.person_code=t.Person_Code where disabilityid=4 and p.DistrictID =b.district_id and p.mandalid =b.mandal_id and p.villageid =b.village_id and  p.habitationid =b.habitation_id and \n"
                        + " (DATEADD(DD,0,DATEDIFF(DD,0,updateddate))) BETWEEN  ? AND ?) as mr,"
                        + "(select count(disabilityid) from dbo.AppellateAuthorityandTemporary_RegistrationDetails t inner join tblperson_personal_details p on p.person_code=t.Person_Code where disabilityid=5 and p.DistrictID =b.district_id and p.mandalid =b.mandal_id and p.villageid =b.village_id and  p.habitationid =b.habitation_id and \n"
                        + " (DATEADD(DD,0,DATEDIFF(DD,0,updateddate))) BETWEEN  ? AND ?) as mi,"
                        + "(select count(disabilityid) from dbo.AppellateAuthorityandTemporary_RegistrationDetails t inner join tblperson_personal_details p on p.person_code=t.Person_Code where disabilityid=6 and p.DistrictID =b.district_id and p.mandalid =b.mandal_id and p.villageid =b.village_id and  p.habitationid =b.habitation_id and \n"
                        + " (DATEADD(DD,0,DATEDIFF(DD,0,updateddate))) BETWEEN  ? AND ?) as multi "
                        + "from dbo.AppellateAuthorityandTemporary_RegistrationDetails a "
                        + " join tblperson_personal_details p   with (nolock) on (p.person_code=a.person_code and \n"
                        + " p.district_id=? and P.mandal_id=? and P.village_id=?) "
                        + "inner join dbo.tblHabitation_details b on "
                        + "(p.districtid=b.district_id and p.mandalid=b.mandal_id and p.villageid=b.village_id and "
                        + " p.habitationid=b.habitation_id and p.districtid=? and p.mandalid=? and p.villageid=? and \n"
                        + " (DATEADD(DD,0,DATEDIFF(DD,0,a.updateddate))) BETWEEN  ? AND ?)"
                        + " group by b.district_id,p.districtid,b.habitation_name,b.mandal_id,b.village_id,b.habitation_id, "
                        + " p.mandalid,p.villageid,p.habitationid order by b.habitation_name";
                
	                ps = con.prepareStatement(sqlDistrict); 
	                
	                ps.setString(1, fdate);
	                ps.setString(2, tdate);
	                ps.setString(3, fdate);
	                ps.setString(4, tdate);
	                ps.setString(5, fdate);
	                ps.setString(6, tdate);
	                ps.setString(7, fdate);
	                ps.setString(8, tdate);
	                ps.setString(9, fdate);
	                ps.setString(10, tdate);
	                ps.setString(11, fdate);
	                ps.setString(12, tdate);
	                ps.setString(13, district_id);
	                ps.setString(14, mandal_id);
	                ps.setString(15, village_id);
	                ps.setString(16, district_id);
	                ps.setString(17, mandal_id);
	                ps.setString(18, village_id); 
	                ps.setString(19, fdate);
	                ps.setString(20, tdate);
	                
	                rs = ps.executeQuery();
	                
                while (rs.next()) 
                {
                    HashMap results = new HashMap();
                    results.put("district_id", rs.getString(1));
                    results.put("mandal_id", rs.getString(2));
                    results.put("village_id", rs.getString(3));
                    results.put("hab_id", rs.getString(4));
                    results.put("name", rs.getString(5));
                    results.put("oh", rs.getString(6));
                    results.put("vi", rs.getString(7));
                    results.put("hi", rs.getString(8));
                    results.put("mr", rs.getString(9));
                    results.put("mi", rs.getString(10));
                    results.put("multi", rs.getString(11));
                    results.put("fDate", fdate);
                    results.put("tDate", tdate);
                    data.add(results);
                }
            }
            
            rs.close();
            ps.close();
            con.close();
        } 
        catch (SQLException sqlEx) 
        {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getApealAuthorityCount", "FunctionalNeedReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO","getApealAuthorityCount");
        }
        catch (Exception sqlEx) 
        {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getApealAuthorityCount", "FunctionalNeedReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO","getApealAuthorityCount");
        }
        finally 
        {
           DBConnection.closeConnection(con);
           DBConnection.closeResultSet(rs);
           DBConnection.closeStatement(ps);
        }
        return data;
    }

    public ArrayList getAppellateCount(DataSource ds, String district_id, String mandal_id, String village_id, String fdate, String tdate, String pensionPhase)  throws SADAREMDBException, SQLException 
    {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList data = new ArrayList();
        PreparedStatement ps = null;//dd/MM/yyyy, yyyy-MM-dd
        CallableStatement cstmt = null;
        String phaseId = pensionPhase;
        if (pensionPhase.equals("1")) {
            pensionPhase = "PhaseI";
        } else if (pensionPhase.equals("2")) {
            pensionPhase = "PhaseII";
        } else if (pensionPhase.equals("4")) {
            pensionPhase = "PhaseIV";
        } else {
            pensionPhase = "ALL";
        }

        try {


            if (fdate != null && fdate.contains("-")) {
                //fdate=new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(fdate));
                ;

            } else {
                fdate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(fdate));
            }

            if (tdate != null && tdate.contains("-")) {
            } else {
                tdate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(tdate));
            }
            con = DBConnection.getConnection();
            stmt = con.createStatement();
            if (district_id == null) {
                district_id = "0";
            }
            if (mandal_id == null) {
                mandal_id = "0";
            }
            if (village_id == null) {
                village_id = "0";
            }//and createddate <='2011-08-29' and createddate>= '2011-08-28
//(DATEADD(DD,0,DATEDIFF(DD,0,updateddate))) BETWEEN  '08/28/2011' AND '08/28/2011'

            if (district_id.equals("0")) {
                cstmt = con.prepareCall("{Call REPORTFORAPPEALAUTHORITYRESULTSTATUSNEW(?,?,?)}");


                cstmt.setString(1, fdate);
                cstmt.setString(2, tdate);
                cstmt.setString(3, pensionPhase);
                rs = cstmt.executeQuery();
            }
            /** Getting the details from mandal wise and details */
            if (village_id.equals("0") && !district_id.equals("0") && mandal_id.equals("0")) {
                cstmt = con.prepareCall("{Call REPORTFORAPPEALAUTHORITYDISTRIRESULTSTATUSNEW(?,?,?,?)}");

                cstmt.setString(1, district_id);
                cstmt.setString(2, fdate);
                cstmt.setString(3, tdate);
                cstmt.setString(4, pensionPhase);
                rs = cstmt.executeQuery();
            }
            /** Getting the details from village wise and details */
            if (village_id.equals("0") && !district_id.equals("0") && !mandal_id.equals("0")) {
                cstmt = con.prepareCall("{Call REPORTFORAPPEALAUTHORITYDISTRIRESULTSTATUSVILLAGENEW(?,?,?,?,?)}");

                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);

                cstmt.setString(3, fdate);
                cstmt.setString(4, tdate);
                cstmt.setString(5, pensionPhase);
                rs = cstmt.executeQuery();
            }

            /**Get Habitation details */
            if (!village_id.equals("0") && !district_id.equals("0") && !mandal_id.equals("0")) {
                cstmt = con.prepareCall("{Call REPORTFORAPPEALAUTHORITYHABRESULTSTATUS(?,?,?,?,?,?)}");

                cstmt.setString(1, district_id);
                cstmt.setString(2, mandal_id);

                cstmt.setString(3, village_id);
                cstmt.setString(4, fdate);
                cstmt.setString(5, tdate);
                cstmt.setString(6, pensionPhase);
                rs = cstmt.executeQuery();
            }
            while (rs.next())
            {

                HashMap results = new HashMap();
                if (district_id.equals("0"))
                {
                    results.put("district_id", rs.getString(1));
                }

                if (village_id.equals("0") && !district_id.equals("0") && mandal_id.equals("0")) 
                {
                    results.put("district_id", district_id);
                    results.put("mandal_id", rs.getString(1));
                }

                if (village_id.equals("0") && !district_id.equals("0") && !mandal_id.equals("0")) 
                {
                    results.put("district_id", district_id);
                    results.put("mandal_id", mandal_id);
                    results.put("village_id", rs.getString(1));
                }

                if (!village_id.equals("0") && !district_id.equals("0") && !mandal_id.equals("0"))
                {
                    results.put("district_id", district_id);
                    results.put("mandal_id", mandal_id);
                    results.put("village_id", village_id);
                    results.put("hab_id", rs.getString(1));
                }

                results.put("name", rs.getString(2));
                results.put("treg", rs.getString(3));
                results.put("tass", rs.getString(4));
                results.put("teli", rs.getString(5));
                results.put("trej", rs.getString(6));
                results.put("loe", rs.getString(7));
                results.put("lor", rs.getString(8));
                results.put("ve", rs.getString(9));
                results.put("vr", rs.getString(10));
                results.put("he", rs.getString(11));
                results.put("hr", rs.getString(12));
                results.put("me", rs.getString(13));
                results.put("mr", rs.getString(14));
                results.put("mie", rs.getString(15));
                results.put("mir", rs.getString(16));
                results.put("mue", rs.getString(17));
                results.put("mur", rs.getString(18));
                results.put("fDate", fdate);
                results.put("tDate", tdate);
                results.put("pensionPhase", phaseId);

                data.add(results);
            }

//            /** Getting the details from mandal wise and details */
//            if (village_id.equals("0") && !district_id.equals("0") && mandal_id.equals("0")) {
//                cstmt = con.prepareCall("{Call REPORTFORAPPEALAUTHORITYDISTRIRESULTSTATUS(?,?,?)}");
//
//                cstmt.setString(1, district_id);
//                cstmt.setString(2, fdate);
//                cstmt.setString(3, tdate);
//                rs = cstmt.executeQuery();
//                while (rs.next()) {
//                    
//                    HashMap results = new HashMap();
//                    results.put("district_id", district_id);
//                    results.put("mandal_id", rs.getString(2));
//                    results.put("name", rs.getString(3));
//                    results.put("treg", rs.getString(4));
//                    results.put("tass", rs.getString(5));
//                    results.put("teli", rs.getString(6));
//                    results.put("trej", rs.getString(7));
//                    results.put("loe", rs.getString(8));
//                    results.put("lor", rs.getString(9));
//                    results.put("ve", rs.getString(10));
//                    results.put("vr", rs.getString(11));
//                    results.put("he", rs.getString(12));
//                    results.put("hr", rs.getString(13));
//                    results.put("me", rs.getString(14));
//                    results.put("mr", rs.getString(15));
//                    results.put("mie", rs.getString(16));
//                    results.put("mir", rs.getString(17));
//                    results.put("mue", rs.getString(18));
//                    results.put("mur", rs.getString(19));
//                    results.put("fDate", fdate);
//                    results.put("tDate", tdate);
//
//                    data.add(results);
//                }
//            }
//            /** Getting the details from village wise and details */
//            if (village_id.equals("0") && !district_id.equals("0") && !mandal_id.equals("0")) {
//                cstmt = con.prepareCall("{Call REPORTFORAPPEALAUTHORITYVILLAGERESULTSTATUS(?,?,?,?)}");
//
//                cstmt.setString(1, district_id);
//                cstmt.setString(2, mandal_id);
//
//                cstmt.setString(3, fdate);
//                cstmt.setString(4, tdate);
//                rs = cstmt.executeQuery();
//
//                while (rs.next()) {
//
//                    HashMap results = new HashMap();
//                    results.put("district_id", district_id);
//                    results.put("mandal_id", mandal_id);
//                    results.put("village_id", rs.getString(2));
//                    results.put("name", rs.getString(3));
//                    results.put("treg", rs.getString(4));
//                    results.put("tass", rs.getString(5));
//                    results.put("teli", rs.getString(6));
//                    results.put("trej", rs.getString(7));
//                    results.put("loe", rs.getString(8));
//                    results.put("lor", rs.getString(9));
//                    results.put("ve", rs.getString(10));
//                    results.put("vr", rs.getString(11));
//                    results.put("he", rs.getString(12));
//                    results.put("hr", rs.getString(13));
//                    results.put("me", rs.getString(14));
//                    results.put("mr", rs.getString(15));
//                    results.put("mie", rs.getString(16));
//                    results.put("mir", rs.getString(17));
//                    results.put("mue", rs.getString(18));
//                    results.put("mur", rs.getString(19));
//                    results.put("fDate", fdate);
//                    results.put("tDate", tdate);
//
//                    data.add(results);
//                }
//            }
//            /**Get Habitation details */
//            if (!village_id.equals("0") && !district_id.equals("0") && !mandal_id.equals("0")) {
//                cstmt = con.prepareCall("{Call REPORTFORAPPEALAUTHORITYHABRESULTSTATUS(?,?,?,?,?)}");
//
//                cstmt.setString(1, district_id);
//                cstmt.setString(2, mandal_id);
//
//                cstmt.setString(3, village_id);
//                cstmt.setString(4, fdate);
//                cstmt.setString(5, tdate);
//                rs = cstmt.executeQuery();
//
//                while (rs.next()) {
//
//                    HashMap results = new HashMap();
//                    results.put("district_id", district_id);
//                    results.put("mandal_id", mandal_id);
//                    results.put("village_id", village_id);
//                    results.put("hab_id", rs.getString(2));
//
//                    results.put("name", rs.getString(3));
//                    results.put("treg", rs.getString(4));
//                    results.put("tass", rs.getString(5));
//                    results.put("teli", rs.getString(6));
//                    results.put("trej", rs.getString(7));
//                    results.put("loe", rs.getString(8));
//                    results.put("lor", rs.getString(9));
//                    results.put("ve", rs.getString(10));
//                    results.put("vr", rs.getString(11));
//                    results.put("he", rs.getString(12));
//                    results.put("hr", rs.getString(13));
//                    results.put("me", rs.getString(14));
//                    results.put("mr", rs.getString(15));
//                    results.put("mie", rs.getString(16));
//                    results.put("mir", rs.getString(17));
//                    results.put("mue", rs.getString(18));
//                    results.put("mur", rs.getString(19));
//                    results.put("fDate", fdate);
//                    results.put("tDate", tdate);
//
//                    data.add(results);
//                }
//            }*/
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMaritalStatusPersonalDetails", "FunctionalNeedReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getMaritalStatusPersonalDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMaritalStatusPersonalDetails", "FunctionalNeedReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getMaritalStatusPersonalDetails");
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                DBConnection.closeResultSet(rs);
                DBConnection.closeStatement(ps);
                DBConnection.closeStatement(cstmt);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    /* public String getDistMandVilHabname(DataSource ds, String district_id, String mandal_id, String village_id, String hab)
    throws SADAREMDBException,SQLException {

    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    String sql = null;
    ArrayList data = new ArrayList();
    String gender = null;
    String type = null;

    if (district_id == null) {
    district_id = "0";
    }
    if (mandal_id == null) {
    mandal_id = "0";
    }
    if (village_id == null) {
    village_id = "0";
    }
    if (hab == null) {
    hab = "0";
    }


    try {
    con = DBConnection.getConnection();
    stmt = con.createStatement();
    if (!district_id.equals("0") && mandal_id.equals("0") && village_id.equals("0")) {
    sql = "select district_name from tbldistrict_details where district_id='" + district_id + "'";
    rs = stmt.executeQuery(sql);
    if (rs.next()) {
    type = "DistrictName: " + rs.getString(1);




    }
    } else if (!district_id.equals("0") && district_id != null && !mandal_id.equals("0") && village_id.equals("0")) {

    sql = "select m.mandal_name, p.district_name from tblmandal_details m, tbldistrict_details p "
    + " where m.district_id=p.district_id and m.mandal_id='" + mandal_id + "' and p.district_id='" + district_id + "'";
    rs = stmt.executeQuery(sql);
    if (rs.next()) {
    type = "DistrictName: " + rs.getString(2) + " MandalName:" + rs.getString(1);




    }

    } else if (!district_id.equals("0") && !mandal_id.equals("0") && !village_id.equals("0") && hab.equals("0")) {
    sql = "select m.mandal_name, p.district_name from tblmandal_details m, tbldistrict_details p "
    + " where m.district_id=p.district_id and m.mandal_id='" + mandal_id + "' and p.district_id='" + district_id + "'";
    rs = stmt.executeQuery(sql);
    if (rs.next()) {
    type = "DistrictName: " + rs.getString(2) + " MandalName:" + rs.getString(1);




    }


    sql = " select village_name from tblvillage_details where district_id='" + district_id
    + "' and mandal_id='" + mandal_id + "' and village_id='" + village_id + "'";

    rs = stmt.executeQuery(sql);


    if (rs.next()) {
    type = type + " VillageName: " + rs.getString(1);




    }

    } else if (!district_id.equals("0") && !mandal_id.equals("0") && !village_id.equals("0") && !hab.equals("0")) {

    sql = "select m.mandal_name, p.district_name from tblmandal_details m, tbldistrict_details p "
    + " where m.district_id=p.district_id and m.mandal_id='" + mandal_id + "' and p.district_id='" + district_id + "'";
    rs = stmt.executeQuery(sql);
    if (rs.next()) {
    type = "DistrictName: " + rs.getString(2) + "MandalName:" + rs.getString(1);




    }


    sql = " select village_name from tblvillage_details where district_id='" + district_id
    + "' and mandal_id='" + mandal_id + "' and village_id='" + village_id + "'";

    rs = stmt.executeQuery(sql);


    if (rs.next()) {
    type = type + " VillageName: " + rs.getString(1);




    }
    sql = "select habitation_name from tblhabitation_details where district_id='" + district_id
    + "' and mandal_id='" + mandal_id + "' and village_id='" + village_id + "' and habitation_id='" + hab + "'";




    rs = stmt.executeQuery(sql);
    if (rs.next()) {
    type = type + " HabitatioName: " + rs.getString(1);




    }
    }



    } catch (Exception e) {
    e.printStackTrace();
    } finally {
    try {
    if (con != null) {
    con.close();
    }
    if (stmt != null) {
    stmt.close();
    }
    } catch (Exception e) {
    e.printStackTrace();
    }
    }

    return type;
    }*/
    /** Added by rekha */
    public ArrayList getPersonalDetails(DataSource ds, String district_id, String mandal_id, String village_id, String hab, String fdate, String tdate, String em, 

String edu, String caste, String ma, String religion, String disabilityid, String gender, String total, String pmarige, String ration, String agee)
            throws SADAREMDBException, SQLException {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = null;
        CallableStatement cstmt = null;
        ArrayList data = new ArrayList();
        // String gender = null;
        String type = null;
        String subquery = null;

        if (gender != null) {
            subquery = "p.gender=" + gender;

            if (em != null) {

                subquery = subquery + " and p.employment=" + em;//+" and";
            } else if (edu != null) {

                subquery = subquery + " and p.education=" + edu;//+" and";
            } else if (caste != null) {

                subquery = subquery + " and p.caste=" + caste;//+" and";
            } else if (religion != null) {
                subquery = subquery + " and p.religion=" + religion;//+" and";
            } else if (ma != null) {
                subquery = subquery + " and p.marital_status=" + ma;//+" and";
            } else if (disabilityid != null) { //p.typeof_disability
                subquery = subquery + " and pdd.disability_id=" + disabilityid;//+" and";
            } else if (pmarige != null) {
                if (pmarige.equalsIgnoreCase("2")) {
                    subquery = subquery + " and p.Parents_Marriage is null ";
                } else {
                    subquery = subquery + " and p.Parents_Marriage=" + pmarige;
                }
            } else if (ration != null) {
                if (ration.equalsIgnoreCase("0")) {
                    subquery = subquery + " and p.RationCard_Type is null ";
                } else {
                    subquery = subquery + " and p.RationCard_Type=" + ration;
                }
            } else if (agee != null) {
                int n = Integer.parseInt(agee);
                if (n == 1) {
                    subquery = subquery + " and  p.age_years+datediff(year, p.created_date,GETDATE()) between 0 and 3 ";
                } else if (n == 2) {
                    subquery = subquery + " and  p.age_years+datediff(year, p.created_date,GETDATE()) between 4 and 5 ";
                } else if (n == 3) {
                    subquery = subquery + " and  p.age_years+datediff(year, p.created_date,GETDATE()) between 6 and 14 ";
                } else if (n == 4) {
                    subquery = subquery + " and p.age_years+datediff(year, p.created_date,GETDATE()) between 15 and 18 ";
                } else if (n == 5) {
                    subquery = subquery + " and p.age_years+datediff(year, p.created_date,GETDATE()) between 19 and 25 ";
                } else if (n == 6) {
                    subquery = subquery + " and p.age_years+datediff(year, p.created_date,GETDATE()) between 26 and 35 ";
                } else if (n == 7) {
                    subquery = subquery + " and p.age_years+datediff(year, p.created_date,GETDATE()) between 36 and 50 ";
                } else if (n == 8) {
                    subquery = subquery + " and p.age_years+datediff(year, p.created_date,GETDATE()) between 51 and 60 ";
                } else if (n == 9) {
                    subquery = subquery + " and p.age_years+datediff(year, p.created_date,GETDATE()) between 61 and 70 ";
                } else if (n == 10) {
                    subquery = subquery + " and p.age_years+datediff(year, p.created_date,GETDATE()) between 71 and 120 ";
                }

            }


        } else {
            if (em != null) {

                subquery = "p.employment=" + em;//+" and";
            } else if (edu != null) {

                subquery = " p.education=" + edu;//+" and";
            } else if (caste != null) {

                subquery = " p.caste=" + caste;//+" and";
            } else if (religion != null) {
                subquery = " p.religion=" + religion;//+" and";
            } else if (ma != null) {
                subquery = " p.marital_status=" + ma;//+" and";
            } else if (disabilityid != null) {
                subquery = " p.typeof_disability=" + disabilityid;//+" and";
            } else if (pmarige != null) {
                if (pmarige.equalsIgnoreCase("2")) {
                    subquery = "  p.Parents_Marriage is null ";
                } else {
                    subquery = " p.Parents_Marriage=" + pmarige;
                }
            } else if (ration != null) {
                if (ration.equalsIgnoreCase("0")) {
                    subquery = "  p.RationCard_Type is null ";
                } else {
                    subquery = "  p.RationCard_Type=" + ration;
                }
            } else if (agee != null) {
                int n = Integer.parseInt(agee);
                if (n == 1) {
                    subquery = "  p.age_years+datediff(year, p.created_date,GETDATE()) between 0 and 3 ";
                } else if (n == 2) {
                    subquery = "  p.age_years+datediff(year, p.created_date,GETDATE()) between 4 and 5 ";
                } else if (n == 3) {
                    subquery = "  p.age_years+datediff(year, p.created_date,GETDATE()) between 6 and 14 ";
                } else if (n == 4) {
                    subquery = "  p.age_years+datediff(year, p.created_date,GETDATE()) between 15 and 18 ";
                } else if (n == 5) {
                    subquery = "  p.age_years+datediff(year, p.created_date,GETDATE()) between 19 and 25 ";
                } else if (n == 6) {
                    subquery = "  p.age_years+datediff(year, p.created_date,GETDATE()) between 26 and 35 ";
                } else if (n == 7) {
                    subquery = "  p.age_years+datediff(year, p.created_date,GETDATE()) between 36 and 50 ";
                } else if (n == 8) {
                    subquery = "  p.age_years+datediff(year, p.created_date,GETDATE()) between 51 and 60 ";
                } else if (n == 9) {
                    subquery = "  p.age_years+datediff(year, p.created_date,GETDATE()) between 61 and 70 ";
                } else if (n == 10) {
                    subquery = "   p.age_years+datediff(year, p.created_date,GETDATE()) between 71 and 120 ";
                }
            }
        }
        try {

//fdate=new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(fdate));
            //  tdate=new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(tdate));
            con = DBConnection.getConnection();
            stmt = con.createStatement();
            if (fdate != null && fdate.contains("-")) {
                //fdate=new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(fdate));
                ;

            } else {
                fdate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(fdate));
            }

            if (tdate != null && tdate.contains("-")) {
            } else {
                tdate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(tdate));
            }



            if (district_id.equals("0") && mandal_id.equals("0") && village_id.equals("0")) {
                cstmt = con.prepareCall("{Call GETTOTALREPORTDETAILS(?,?,?)}");
                cstmt.setString(1, subquery);

                cstmt.setString(2, fdate);
                cstmt.setString(3, tdate);
                rs = cstmt.executeQuery();
            } else if (!district_id.equals("0") && mandal_id.equals("0") && village_id.equals("0")) {
                cstmt = con.prepareCall("{Call GETDISTRICTREPORTDETAILS(?,?,?,?)}");
                cstmt.setString(1, subquery);
                cstmt.setString(2, district_id);
                cstmt.setString(3, fdate);
                cstmt.setString(4, tdate);
                rs = cstmt.executeQuery();




            } else if (!district_id.equals("0") && district_id != null && !mandal_id.equals("0") && village_id.equals("0")) {
                //  GETDISTRICT_MANDAL_REPORTDETAILS

                cstmt = con.prepareCall("{Call GETDISTRICT_MANDAL_REPORTDETAILS(?,?,?,?,?)}");

                cstmt.setString(1, subquery);
                cstmt.setString(2, district_id);
                cstmt.setString(3, mandal_id);
                cstmt.setString(4, fdate);
                cstmt.setString(5, tdate);
                rs = cstmt.executeQuery();

            } else if (!district_id.equals("0") && !mandal_id.equals("0") && !village_id.equals("0") && hab.equals("0")) {
                cstmt = con.prepareCall("{Call GETDISTRICT_MANDAL_VILLAGE_REPORTDETAILS(?,?,?,?,?,?)}");
                cstmt.setString(1, subquery);
                cstmt.setString(2, district_id);
                cstmt.setString(3, mandal_id);
                cstmt.setString(4, village_id);
                cstmt.setString(5, fdate);
                cstmt.setString(6, tdate);
                rs = cstmt.executeQuery();


            } else if (!district_id.equals("0") && !mandal_id.equals("0") && !village_id.equals("0") && !hab.equals("0")) {
                cstmt = con.prepareCall("{Call GETDISTRI_MANDAL_VILLAGE_HAB_REPORTDETAILS(?,?,?,?,?,?,?)}");
                cstmt.setString(1, subquery);
                cstmt.setString(2, district_id);
                cstmt.setString(3, mandal_id);
                cstmt.setString(4, village_id);
                cstmt.setString(5, hab);
                cstmt.setString(6, fdate);
                cstmt.setString(7, tdate);
                rs = cstmt.executeQuery();
            }
            while (rs.next()) {

                gender = rs.getString(6);
                if (gender != null && gender.equalsIgnoreCase("2")) {
                    gender = "Female";

                } else if (gender != null && gender.equalsIgnoreCase("1")) {
                    gender = "Male";

                } else {
                    gender = "NE";
                }
                HashMap results = new HashMap();
                results.put("person_code", "SID" + rs.getString(1));
                results.put("surname", rs.getString(2));
                results.put("age", rs.getString(3));
                results.put("name", rs.getString(2) + " " + rs.getString(4));
                results.put("rname", rs.getString(5));
                results.put("gender", gender);
                results.put("disability", type);
                results.put("houseno", rs.getString(7) + ".");
                results.put("dname", rs.getString(8));
                results.put("mname", rs.getString(9));
                results.put("vname", rs.getString(10));
                results.put("hname", rs.getString(11));
                results.put("fDate", fdate);
                results.put("tDate", tdate);
                results.put("pension_no", rs.getString(12));

                data.add(results);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMaritalStatusPersonalDetails", "FunctionalNeedReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getMaritalStatusPersonalDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMaritalStatusPersonalDetails", "FunctionalNeedReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getMaritalStatusPersonalDetails");
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                DBConnection.closeResultSet(rs);
                DBConnection.closeStatement(cstmt);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return data;

    }

    public ArrayList getPersonalDetailsAllQuery(DataSource ds, String district_id, String mandal_id, String village_id, String hab, String fdate, String tdate, String disabilityid, String subquery, String columns) throws SADAREMDBException, SQLException 
    { 
        Connection con = null;
        ResultSet rs = null;
        String query = "";
        PreparedStatement pstmt = null;
        ArrayList data = new ArrayList();
        String gender = null;

        try
        {
            //fdate=new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(fdate));
            //  tdate=new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(tdate));
            con = DBConnection.getConnection();
            //  stmt = con.createStatement();
            if (fdate != null && fdate.contains("-"))
            {
                //fdate=new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(fdate));
                ;

            } 
            else 
            {
                fdate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(fdate));
            }

            if (tdate != null && tdate.contains("-"))
            {
            	
            }
            else
            {
                tdate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(tdate));
            }

            query = query + "select  p.person_code,p.surname,p.age_years,p.First_Name ,"
                    + "p.relation_name,p.gender,p.house_number,  d.district_name as "
                    + " district_name,m.mandal_name as mandal_name, v.village_name as village_name,h.habitation_name "
                    + "as habName, p.pensioncard_no ";
            query = query + " from dbo.tblperson_personal_details p  "
					+"with (nolock) join tblPerson_Disability_Details pdd with(nolock)  "
					+ " CROSS APPLY DATA_PERSON_CODE(PDD.PERSON_CODE) DPC" 
                    + " on(p.person_code=pdd.person_code )"
                    + "join tbldistrict_details D with(nolock)  on(DPC.DISTRICT_ID=d.district_id)"
                    + "join tblmandal_details M with(nolock)  on(DPC.MANDAL_ID=M.mandal_id and "
                    + "DPC.DISTRICT_ID=m.district_id)join  tblPanchayat_Details Pa with(nolock)  on(DPC.PANCHAYAT_ID=pa.Panchayat_ID "
                    + "and DPC.MANDAL_ID=pa.mandal_id  and  DPC.DISTRICT_ID=pa.district_id)"
                    + " join tblvillage_details V with(nolock)  on(DPC.VILLAGE_ID=v.village_id  and"
                    + " DPC.MANDAL_ID=v.mandal_id and DPC.DISTRICT_ID=v.district_id )"
                    + " join tblhabitation_details h with(nolock)  on(DPC.HABITATION_ID=h.habitation_id and  "
                    + "DPC.VILLAGE_ID=h.village_id and DPC.PANCHAYAT_ID=h.Panchayat_ID and "
                    + "DPC.MANDAL_ID=h.mandal_id  and  DPC.DISTRICT_ID=h.district_id and "
                    + "DPC.ASSEMBLY_ID = h.assembly_id) "
                    + "and pdd.status='Active' and p.status='Active' "
                    + " and pdd.disability_id=" + disabilityid + "and " + columns;



            if (district_id.equals("0") && mandal_id.equals("0") && village_id.equals("0")) 
            {
                query = query + " and DATEADD(DD,0,DATEDIFF(DD,0,pdd.updated_date)) BETWEEN '" + fdate + "' and '" + tdate + "'";
                pstmt = con.prepareStatement(query);

                rs = pstmt.executeQuery();
            } 
            else if (!district_id.equals("0") && mandal_id.equals("0") && village_id.equals("0")) 
            {
                query = query + " and DPC.DISTRICT_ID='" + district_id + "' ";
                query = query + " and DATEADD(DD,0,DATEDIFF(DD,0,pdd.updated_date)) BETWEEN '" + fdate + "' and '" + tdate + "'";
                pstmt = con.prepareStatement(query);

                rs = pstmt.executeQuery();

            } 
            else if (!district_id.equals("0") && district_id != null && !mandal_id.equals("0") && village_id.equals("0"))
            {
                //  GETDISTRICT_MANDAL_REPORTDETAILS
                query = query + "  and DPC.DISTRICT_ID='" + district_id + "' and  DPC.MANDAL_ID='" + mandal_id + "' ";
                query = query + " and DATEADD(DD,0,DATEDIFF(DD,0,pdd.updated_date)) BETWEEN '" + fdate + "' and '" + tdate + "'";
                pstmt = con.prepareStatement(query);


                rs = pstmt.executeQuery();

            } 
            else if (!district_id.equals("0") && !mandal_id.equals("0") && !village_id.equals("0") && hab.equals("0")) 
            {
                query = query + "  and DPC.DISTRICT_ID='" + district_id + "' and  DPC.MANDAL_ID='" + mandal_id
                        + " ' and  DPC.VILLAGE_ID='" + village_id + "' ";
                query = query + " and DATEADD(DD,0,DATEDIFF(DD,0,pdd.updated_date)) BETWEEN '" + fdate + "' and '" + tdate + "'";

                pstmt = con.prepareStatement(query);
                rs = pstmt.executeQuery();


            }
            while (rs.next())
            {

                gender = rs.getString(6);
                if (gender != null && gender.equalsIgnoreCase("2")) {
                    gender = "Female";

                } else if (gender != null && gender.equalsIgnoreCase("1")) {
                    gender = "Male";

                } else {
                    gender = "NE";
                }

                HashMap results = new HashMap();
                results.put("person_code", "SID" + rs.getString(1));
                results.put("surname", rs.getString(2));
                results.put("age", rs.getString(3));
                results.put("name", rs.getString(2) + " " + rs.getString(4));
                results.put("rname", rs.getString(5));
                results.put("gender", gender);
                // results.put("disability", type);
                results.put("houseno", rs.getString(7) + ".");
                results.put("dname", rs.getString(8));
                results.put("mname", rs.getString(9));
                results.put("vname", rs.getString(10));
                results.put("hname", rs.getString(11));
                results.put("fDate", fdate);
                results.put("tDate", tdate);
                results.put("pension_no", rs.getString(12));

                data.add(results);
                
               // CommonUtility.getHashMapfromArrayListWithcolumnName(dataList)
                
                
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMaritalStatusPersonalDetails", "FunctionalNeedReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getMaritalStatusPersonalDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMaritalStatusPersonalDetails", "FunctionalNeedReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getMaritalStatusPersonalDetails");
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                DBConnection.closeResultSet(rs);
                DBConnection.closeStatement(pstmt);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return data;

    }

    public ArrayList getPersonalDetailsAll(DataSource ds, String district_id, String mandal_id, String village_id, String hab, String fdate, String tdate, String 

disabilityid, String subquery, String columns)
            throws SADAREMDBException, SQLException {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = null;
        CallableStatement cstmt = null;
        ArrayList data = new ArrayList();

        String gender = null;


        try {

//fdate=new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(fdate));
            //  tdate=new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(tdate));
            con = DBConnection.getConnection();
            stmt = con.createStatement();
            if (fdate != null && fdate.contains("-")) {
                //fdate=new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(fdate));
                ;

            } else {
                fdate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(fdate));
            }

            if (tdate != null && tdate.contains("-")) {
            } else {
                tdate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(tdate));
            }



            if (district_id.equals("0") && mandal_id.equals("0") && village_id.equals("0")) {
                cstmt = con.prepareCall("{Call SP_PERSONAL_DETAILSFOR_CAUSESPERFORMANCEPERCENTAGESNEW(?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, subquery);
                cstmt.setString(2, columns);
                cstmt.setString(3, disabilityid);
                cstmt.setString(4, district_id);
                cstmt.setString(5, mandal_id);
                cstmt.setString(6, village_id);
                cstmt.setString(7, fdate);
                cstmt.setString(8, tdate);

                rs = cstmt.executeQuery();
            } else if (!district_id.equals("0") && mandal_id.equals("0") && village_id.equals("0")) {
                cstmt = con.prepareCall("{Call SP_PERSONAL_DETAILSFOR_CAUSESPERFORMANCEPERCENTAGESNEW(?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, subquery);
                cstmt.setString(2, columns);
                cstmt.setString(3, disabilityid);
                cstmt.setString(4, district_id);
                cstmt.setString(5, mandal_id);
                cstmt.setString(6, village_id);
                cstmt.setString(7, fdate);
                cstmt.setString(8, tdate);
                rs = cstmt.executeQuery();




            } else if (!district_id.equals("0") && district_id != null && !mandal_id.equals("0") && village_id.equals("0")) {
                //  GETDISTRICT_MANDAL_REPORTDETAILS

                cstmt = con.prepareCall("{Call SP_PERSONAL_DETAILSFOR_CAUSESPERFORMANCEPERCENTAGESNEW(?,?,?,?,?,?,?,?)}");

                cstmt.setString(1, subquery);
                cstmt.setString(2, columns);
                cstmt.setString(3, disabilityid);
                cstmt.setString(4, district_id);
                cstmt.setString(5, mandal_id);
                cstmt.setString(6, village_id);
                cstmt.setString(7, fdate);
                cstmt.setString(8, tdate);
                rs = cstmt.executeQuery();

            } else if (!district_id.equals("0") && !mandal_id.equals("0") && !village_id.equals("0") && hab.equals("0")) {
                cstmt = con.prepareCall("{Call SP_PERSONAL_DETAILSFOR_CAUSESPERFORMANCEPERCENTAGESNEW(?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, subquery);
                cstmt.setString(2, columns);
                cstmt.setString(3, disabilityid);
                cstmt.setString(4, district_id);
                cstmt.setString(5, mandal_id);
                cstmt.setString(6, village_id);
                cstmt.setString(7, fdate);
                cstmt.setString(8, tdate);
                rs = cstmt.executeQuery();


            }
            while (rs.next()) {

                gender = rs.getString(6);
                if (gender != null && gender.equalsIgnoreCase("2")) {
                    gender = "Female";

                } else if (gender != null && gender.equalsIgnoreCase("1")) {
                    gender = "Male";

                } else {
                    gender = "NE";
                }

                HashMap results = new HashMap();
                results.put("person_code", "SID" + rs.getString(1));
                results.put("surname", rs.getString(2));
                results.put("age", rs.getString(3));
                results.put("name", rs.getString(2) + " " + rs.getString(4));
                results.put("rname", rs.getString(5));
                results.put("gender", gender);
                // results.put("disability", type);
                results.put("houseno", rs.getString(7) + ".");
                results.put("dname", rs.getString(8));
                results.put("mname", rs.getString(9));
                results.put("vname", rs.getString(10));
                results.put("hname", rs.getString(11));
                results.put("fDate", fdate);
                results.put("tDate", tdate);
                results.put("pension_no", rs.getString(12));

                data.add(results);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMaritalStatusPersonalDetails", "FunctionalNeedReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getMaritalStatusPersonalDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMaritalStatusPersonalDetails", "FunctionalNeedReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getMaritalStatusPersonalDetails");
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                DBConnection.closeResultSet(rs);
                DBConnection.closeStatement(cstmt);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return data;

    }

    public String getDistMandVilHabname(DataSource ds, String district_id, String mandal_id, String village_id, String hab) throws SADAREMDBException, SQLException 
    {

        String result ="";
    	
        if (district_id == null) 
        {
            district_id = "0";
        }
        
        if (mandal_id == null)
        {
            mandal_id = "0";
        }
        
        if (village_id == null)
        {
            village_id = "0";
        }
        
        if (hab == null) 
        {
            hab = "0";
        }


        try
        { 

            CommonDAO comObj = new CommonDAOImpl();
            
            if (district_id.equals("0") && mandal_id.equals("0") && village_id.equals("0") && hab.equals("0")) 
            {
            	result = "District:ALL , Mandal:ALL, Village:ALL"; 
            }
            else
            {
            	result ="";
            	
            	  if (!district_id.equals("0"))
            	  {
            		  result  =" District : "+  comObj.getDistrictName(district_id);
            	  }
            	  
            	  if (!mandal_id.equals("0"))
            	  {
            		  result+=" ,Mandal : "+  comObj.getMandalName(district_id, mandal_id);
            	  }
            	  
            	  if (!village_id.equals("0"))
            	  {
            		  result+=" ,Village : "+  comObj.getvillageNameByDistIdMandalIdVillageId(district_id, mandal_id, village_id);
            	  }
            	  
            	  if (!hab.equals("0"))
            	  {
            		  result+=" ,Habitation : "+  comObj.getHabitatName(district_id, mandal_id, village_id, hab);
            	  }
            	  
            }
             
        } 
        catch (Exception sqlEx) 
        {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDistMandVilHabname", "FunctionalNeedReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO","getDistMandVilHabname");
        } 
        return result;
 }

    public ArrayList totalReport(DataSource datasource, String district_id, String mandal_id, String village_id, String fdate, String tdate) throws SADAREMDBException, SQLException {
        ArrayList disabilityList = new ArrayList();
        ResultSet rs = null;
        Statement stmt = null;
        CallableStatement cstmt = null;
        Connection con = null;
        String sql = "";
        ArrayList data = new ArrayList();
        ArrayList data1 = new ArrayList();
        ArrayList data2 = new ArrayList();
        ArrayList data3 = new ArrayList();
        ArrayList dat = new ArrayList();
        double totalmale = 0, totalfemale = 0;
        double tmaleper = 0, tfemaleper = 0;
        int maleto = 0, femato = 0;
        NumberFormat form = NumberFormat.getInstance();
        form.setMinimumIntegerDigits(2);
        form.setMinimumFractionDigits(2);
        form.setMaximumFractionDigits(2);
        double raf = 0, ram = 0, pmm = 0, pmf = 0, am = 0, af = 0, cf = 0, cm = 0, ref = 0, rem = 0, ef = 0, em = 0, edm = 0, edf = 0, dm = 0, df = 0, tf = 0, tm = 0, 

mm = 0, mf = 0;
        try {

            if (fdate != null && fdate.contains("-")) {
                //fdate=new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(fdate));
                ;

            } else {
                fdate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(fdate));
            }

            if (tdate != null && tdate.contains("-")) {
                ;
            } else {
                tdate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(tdate));
            }

            if (district_id == null) {
                district_id = "0";
            }
            if (mandal_id == null) {
                mandal_id = "0";
            }
            if (village_id == null) {
                village_id = "0";
            }
            int count = 1;
            int count1 = 1;
            int count2 = 1;
            int count3 = 1;
            con = DBConnection.getConnection();
            if (village_id.equals("0") && district_id.equals("0") && mandal_id.equals("0")) {
                cstmt = con.prepareCall("{Call TotalReport(?,?)}");
                cstmt.setString(1, fdate);
                cstmt.setString(2, tdate);

                rs = cstmt.executeQuery();
                HashMap results = new HashMap();
                HashMap results1 = new HashMap();
                HashMap results2 = new HashMap();
                HashMap results3 = new HashMap();//HashMap results1 = new HashMap();
                while (rs.next()) {

                    if (rs.getString(1).equalsIgnoreCase("Ration Card") || rs.getString(1).equalsIgnoreCase("Parents_Marriage") || rs.getString(1).equalsIgnoreCase

("AGE")) {
                        results3.put("r" + count3++, rs.getString(4));
                        results3.put("r" + count3++, rs.getString(5));
                        results3.put("r" + count3++, rs.getString(6));
                        results3.put("r" + count3++, rs.getString(7));


                    } else if (rs.getString(1).equalsIgnoreCase("religion") || rs.getString(1).equalsIgnoreCase("Caste")) {
                        results1.put("r" + count++, rs.getString(4));
                        results1.put("r" + count++, rs.getString(5));
                        results1.put("r" + count++, rs.getString(6));
                        results1.put("r" + count++, rs.getString(7));

                    } else if (rs.getString(1).equalsIgnoreCase("Employment") || rs.getString(1).equalsIgnoreCase("Marital_status")) {
                        results2.put("r" + count++, rs.getString(4));
                        results2.put("r" + count++, rs.getString(5));
                        results2.put("r" + count++, rs.getString(6));
                        results2.put("r" + count++, rs.getString(7));

                    } else if (rs.getString(1).equalsIgnoreCase("TOTALCATEGORY") || rs.getString(1).equalsIgnoreCase("Disability_ID") || rs.getString

(1).equalsIgnoreCase("Education")) {
                        results.put("r" + count++, rs.getString(4));
                        results.put("r" + count++, rs.getString(5));
                        results.put("r" + count++, rs.getString(6));
                        results.put("r" + count++, rs.getString(7));
                        if (rs.getString(1).equalsIgnoreCase("TOTALCATEGORY")) {
                            maleto = Integer.parseInt(rs.getString(4));
                            femato = Integer.parseInt(rs.getString(6));

                        }


                    }



                    results.put("district_id", district_id);
                    results.put("mandal_id", mandal_id);
                    results.put("village_id", village_id);
                    results.put("fDate", fdate);
                    results.put("tDate", tdate);


                    results1.put("district_id", district_id);
                    results1.put("mandal_id", mandal_id);
                    results1.put("village_id", village_id);
                    results1.put("fDate", fdate);
                    results1.put("tDate", tdate);


                    results2.put("district_id", district_id);
                    results2.put("mandal_id", mandal_id);
                    results2.put("village_id", village_id);
                    results2.put("fDate", fdate);
                    results2.put("tDate", tdate);



                    results3.put("district_id", district_id);
                    results3.put("mandal_id", mandal_id);
                    results3.put("village_id", village_id);
                    results3.put("fDate", fdate);
                    results3.put("tDate", tdate);

                    data3.add(results3);
                    data1.add(results1);
                    data2.add(results2);
                    data.add(results);
                }
            }

            if (village_id.equals("0") && !district_id.equals("0") && mandal_id.equals("0")) {

                cstmt = con.prepareCall("{Call TotaldistrictReportAll(?,?,?)}");
                cstmt.setString(1, fdate);
                cstmt.setString(2, tdate);
                cstmt.setString(3, district_id);
                rs = cstmt.executeQuery();
                HashMap results = new HashMap();
                HashMap results1 = new HashMap();
                HashMap results2 = new HashMap();
                HashMap results3 = new HashMap();
                while (rs.next()) {
                    if (rs.getString(1).equalsIgnoreCase("Ration Card") || rs.getString(1).equalsIgnoreCase("Parents_Marriage") || rs.getString(1).equalsIgnoreCase

("AGE")) {
                        results3.put("r" + count3++, rs.getString(4));
                        results3.put("r" + count3++, rs.getString(5));
                        results3.put("r" + count3++, rs.getString(6));
                        results3.put("r" + count3++, rs.getString(7));
                        if (rs.getString(1).equalsIgnoreCase("Ration Card")) {
                            ram = ram + Double.parseDouble(rs.getString(4));
                            raf = raf + Double.parseDouble(rs.getString(6));
                        }
                        if (rs.getString(1).equalsIgnoreCase("Parents_Marriage")) {
                            pmm = pmm + Double.parseDouble(rs.getString(4));
                            pmf = pmf + Double.parseDouble(rs.getString(6));
                        }
                        if (rs.getString(1).equalsIgnoreCase("AGE")) {
                            am = am + Double.parseDouble(rs.getString(4));
                            af = af + Double.parseDouble(rs.getString(6));
                        }
                    } else if (rs.getString(1).equalsIgnoreCase("religion") || rs.getString(1).equalsIgnoreCase("Caste")) {
                        results1.put("r" + count1++, rs.getString(4));
                        results1.put("r" + count1++, rs.getString(5));
                        results1.put("r" + count1++, rs.getString(6));
                        results1.put("r" + count1++, rs.getString(7));

                        if (rs.getString(1).equalsIgnoreCase("religion")) {
                            rem = rem + Double.parseDouble(rs.getString(4));
                            ref = ref + Double.parseDouble(rs.getString(6));
                        }
                        if (rs.getString(1).equalsIgnoreCase("Caste")) {
                            cm = cm + Double.parseDouble(rs.getString(4));
                            cf = cf + Double.parseDouble(rs.getString(6));
                        }

                    } else if (rs.getString(1).equalsIgnoreCase("Employment") || rs.getString(1).equalsIgnoreCase("Marital_status")) {
                        results2.put("r" + count2++, rs.getString(4));
                        results2.put("r" + count2++, rs.getString(5));
                        results2.put("r" + count2++, rs.getString(6));
                        results2.put("r" + count2++, rs.getString(7));
                        if (rs.getString(1).equalsIgnoreCase("Employment")) {
                            em = em + Double.parseDouble(rs.getString(4));
                            ef = ef + Double.parseDouble(rs.getString(6));
                        }
                        if (rs.getString(1).equalsIgnoreCase("Marital_status")) {
                            mm = mm + Double.parseDouble(rs.getString(4));
                            mf = mf + Double.parseDouble(rs.getString(6));
                        }

                    } else if (rs.getString(1).equalsIgnoreCase("TOTALCATEGORY") || rs.getString(1).equalsIgnoreCase("Disability_ID") || rs.getString

(1).equalsIgnoreCase("Education")) {
                        results.put("r" + count++, rs.getString(4));
                        results.put("r" + count++, rs.getString(5));
                        results.put("r" + count++, rs.getString(6));
                        results.put("r" + count++, rs.getString(7));
                        if (rs.getString(1) != null && rs.getString(1).equalsIgnoreCase("TOTALCATEGORY")) {
                            if (rs.getString(4) != null) {
                                tm = tm + Double.parseDouble(rs.getString(4));
                            }
                            if (rs.getString(6) != null) {
                                tf = tf + Double.parseDouble(rs.getString(6));
                            }
                        }
                        if (rs.getString(1).equalsIgnoreCase("Disability_ID")) {
                            dm = dm + Double.parseDouble(rs.getString(4));
                            df = df + Double.parseDouble(rs.getString(6));
                        }
                        if (rs.getString(1).equalsIgnoreCase("Education")) {
                            edm = edm + Double.parseDouble(rs.getString(4));
                            edf = edf + Double.parseDouble(rs.getString(6));
                        }

                    }
                }

                results3.put("totrm", (int) ram);
                results3.put("totrf", (int) raf);
                results3.put("totpm", (int) pmm);
                results3.put("totpf", (int) pmf);
                results3.put("toagm", (int) am);
                results3.put("toagf", (int) af);

                if ((raf + ram) != 0) {

                    results3.put("trm", form.format(100 * (ram / (ram + raf))) + "");

                    results3.put("trf", form.format(100 * (raf / (ram + raf))) + "");

                } else if ((totalmale + totalfemale) == 0) {
                    results3.put("trm", "0");
                    results3.put("trf", "0");
                }

                if ((pmf + pmm) != 0) {

                    results3.put("tpm", form.format(100 * (pmm / (pmm + pmf))) + "");

                    results3.put("tpf", form.format(100 * (pmf / (pmm + pmf))) + "");

                } else if ((pmf + pmm) == 0) {
                    results3.put("tpm", "0");
                    results3.put("tpf", "0");
                }

                if ((af + am) != 0) {

                    results3.put("tagm", form.format(100 * (am / (am + af))) + "");

                    results3.put("tagf", form.format(100 * (af / (am + af))) + "");

                } else if ((af + am) == 0) {
                    results3.put("tagm", "0");
                    results3.put("tagf", "0");
                }


                if ((tf + tm) != 0) {

                    results.put("tom", form.format(100 * (tm / (tm + tf))) + "");

                    results.put("tof", form.format(100 * (tf / (tm + tf))) + "");

                } else if ((tf + tm) == 0) {
                    results.put("tom", "0");
                    results.put("tof", "0");
                }

                results.put("todm", (int) dm);
                results.put("todf", (int) df);

                if ((df + dm) != 0) {

                    results.put("tdm", form.format(100 * (dm / (dm + df))) + "");

                    results.put("tdf", form.format(100 * (df / (dm + df))) + "");

                } else if ((df + dm) == 0) {
                    results.put("tdm", "0");
                    results.put("tdf", "0");
                }
                results.put("toedm", (int) edm);
                results.put("toedf", (int) edf);
                if ((edf + edm) != 0) {

                    results.put("tedm", form.format(100 * (edm / (edm + edf))) + "");

                    results.put("tedf", form.format(100 * (edf / (edm + edf))) + "");

                } else if ((edf + edm) == 0) {
                    results.put("tedm", "0");
                    results.put("tedf", "0");
                }
//emp, marital

                results2.put("toem", (int) em);
                results2.put("toef", (int) ef);
                results2.put("tomm", (int) mm);
                results2.put("tomf", (int) mf);

                if ((em + ef) != 0) {

                    results2.put("tem", form.format(100 * (em / (em + ef))) + "");

                    results2.put("tef", form.format(100 * (ef / (em + ef))) + "");

                } else if ((ef + em) == 0) {
                    results2.put("tem", "0");
                    results2.put("tef", "0");
                }
                if ((mf + mm) != 0) {

                    results2.put("tmm", form.format(100 * (mm / (mm + mf))) + "");

                    results2.put("tmf", form.format(100 * (mf / (mm + mf))) + "");

                } else if ((mf + mm) == 0) {
                    results2.put("tmm", "0");
                    results2.put("tmf", "0");
                }


//caste religon
                results1.put("tocm", (int) cm);
                results1.put("tocf", (int) cf);
                results1.put("torem", (int) rem);
                results1.put("toref", (int) ref);

                if ((cf + cm) != 0) {

                    results1.put("tcm", form.format(100 * (cm / (cm + cf))) + "");

                    results1.put("tcf", form.format(100 * (cf / (cm + cf))) + "");

                } else if ((cf + cm) == 0) {
                    results1.put("tcm", "0");
                    results1.put("tcf", "0");
                }
                if ((ref + rem) != 0) {

                    results1.put("trem", form.format(100 * (rem / (rem + ref))) + "");

                    results1.put("tref", form.format(100 * (ref / (rem + ref))) + "");

                } else if ((ref + rem) == 0) {
                    results1.put("trem", "0");
                    results1.put("tref", "0");
                }

                results.put("district_id", district_id);
                results.put("mandal_id", mandal_id);
                results.put("village_id", village_id);
                results.put("fDate", fdate);
                results.put("tDate", tdate);


                results1.put("district_id", district_id);
                results1.put("mandal_id", mandal_id);
                results1.put("village_id", village_id);
                results1.put("fDate", fdate);
                results1.put("tDate", tdate);


                results2.put("district_id", district_id);
                results2.put("mandal_id", mandal_id);
                results2.put("village_id", village_id);
                results2.put("fDate", fdate);
                results2.put("tDate", tdate);



                results3.put("district_id", district_id);
                results3.put("mandal_id", mandal_id);
                results3.put("village_id", village_id);
                results3.put("fDate", fdate);
                results3.put("tDate", tdate);
                data.add(results);
                data2.add(results2);
                data1.add(results1);
                data3.add(results3);
                dat.add(data3);
                dat.add(data1);
                dat.add(data2);
                dat.add(data);

            } else if (village_id.equals("0") && !district_id.equals("0") && !mandal_id.equals("0")) {

                cstmt = con.prepareCall("{Call TotalMandalReport(?,?,?,?)}");


                cstmt.setString(1, fdate);
                cstmt.setString(2, tdate);
                cstmt.setString(3, district_id);
                cstmt.setString(4, mandal_id);

                rs = cstmt.executeQuery();
                HashMap results = new HashMap();
                while (rs.next()) {

                    results.put("r" + count++, rs.getString(4));
                    results.put("r" + count++, rs.getString(5));
                    results.put("r" + count++, rs.getString(6));
                    results.put("r" + count++, rs.getString(7));

                    //   if (!rs.getString(1).equalsIgnoreCase("Parents_Marriage") && !rs.getString(2).equalsIgnoreCase("0")) {
                    if (rs.getString(1).equalsIgnoreCase("Education")) {
                        totalmale = totalmale + Double.parseDouble(rs.getString(4));
                        totalfemale = totalfemale + Double.parseDouble(rs.getString(6));
                    }

                }
                if ((totalmale + totalfemale) != 0) {
                    tmaleper = 100 * (totalmale / (totalmale + totalfemale));
                    results.put("Tmaleper", form.format(tmaleper) + "");
                    tfemaleper = 100 * (totalfemale / (totalmale + totalfemale));
                    results.put("Tfemaleper", form.format(tfemaleper) + "");

                } else if ((totalmale + totalfemale) == 0) {
                    results.put("Tmaleper", "0");
                    results.put("Tfemaleper", "0");
                }
                results.put("total", form.format(tmaleper + tfemaleper) + "%");
                results.put("Tmale", (int) totalmale);
                results.put("Tfemale", (int) totalfemale);
                results.put("district_id", district_id);
                results.put("mandal_id", mandal_id);
                results.put("village_id", village_id);
                Iterator i = data.iterator();


                results.put("fDate", fdate);
                results.put("tDate", tdate);
                data.add(results);


            } else if (!village_id.equals("0") && !district_id.equals("0") && !mandal_id.equals("0")) {
                cstmt = con.prepareCall("{Call TotalVillageReport(?,?,?,?,?)}");
                cstmt.setString(1, fdate);
                cstmt.setString(2, tdate);
                cstmt.setString(3, district_id);
                cstmt.setString(4, mandal_id);
                cstmt.setString(5, village_id);

                rs = cstmt.executeQuery();
                HashMap results = new HashMap();
                while (rs.next()) {

                    results.put("r" + count++, rs.getString(4));
                    results.put("r" + count++, rs.getString(5));
                    results.put("r" + count++, rs.getString(6));
                    results.put("r" + count++, rs.getString(7));
                    //  if (!rs.getString(1).equalsIgnoreCase("Parents_Marriage") && !rs.getString(2).equalsIgnoreCase("0")) {
                    if (rs.getString(1).equalsIgnoreCase("Education")) {
                        totalmale = totalmale + Double.parseDouble(rs.getString(4));
                        totalfemale = totalfemale + Double.parseDouble(rs.getString(6));
                    }

                }
                if ((totalmale + totalfemale) != 0) {
                    tmaleper = 100 * (totalmale / (totalmale + totalfemale));
                    results.put("Tmaleper", form.format(tmaleper) + "");
                    tfemaleper = 100 * (totalfemale / (totalmale + totalfemale));
                    results.put("Tfemaleper", form.format(tfemaleper) + "");


                } else if ((totalmale + totalfemale) == 0) {
                    results.put("Tmaleper", "0");
                    results.put("Tfemaleper", "0");


                }
                results.put("total", form.format(tmaleper + tfemaleper) + "%");
                results.put("Tmale", (int) totalmale);
                results.put("Tfemale", (int) totalfemale);
                results.put("district_id", district_id);
                results.put("mandal_id", mandal_id);
                results.put("village_id", village_id);
                Iterator i = data.iterator();
                results.put("fDate", fdate);
                results.put("tDate", tdate);
                data.add(results);

            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "getMaritalStatusPersonalDetails", "FunctionalNeedReportDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getMaritalStatusPersonalDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "getMaritalStatusPersonalDetails", "FunctionalNeedReportDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getMaritalStatusPersonalDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);

            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);
        }
        return dat;
    }
    //report column wise

    public ArrayList totalReportCol(DataSource datasource, String district_id, String mandal_id, String village_id, String fdate, String tdate) throws SADAREMDBException, SQLException 
{
        ArrayList disabilityList = new ArrayList();
        ResultSet rs = null;
        Statement stmt = null;
        CallableStatement cstmt = null;
        Connection con = null;
        String sql = "";
        ArrayList data = new ArrayList();
        ArrayList data1 = new ArrayList();
        ArrayList data2 = new ArrayList();
        ArrayList data3 = new ArrayList();
        ArrayList dat = new ArrayList();
        double totalmale = 0, totalfemale = 0;
        double tmaleper = 0, tfemaleper = 0;
        int maleto = 0, femato = 0;
        NumberFormat form = NumberFormat.getInstance();
        form.setMinimumIntegerDigits(2);
        form.setMinimumFractionDigits(2);
        form.setMaximumFractionDigits(2);
        double a2 = 0, a4 = 0, a5 = 0;
        int a1 = 0, a3 = 0;
        int t1 = 1, t2 = 1, t3 = 1, t = 1;
        double d = 0, e = 0, e1 = 0, e2 = 0, a = 0, r = 0, m = 0, p = 0, c = 0;
        double raf = 0, ram = 0, pmm = 0, pmf = 0, am = 0, af = 0, cf = 0, cm = 0, ref = 0, rem = 0, ef = 0, em = 0, edm = 0, edf = 0, dm = 0, df = 0, tf = 0, tm = 0, 

mm = 0, mf = 0;
        try {

            if (fdate != null && fdate.contains("-")) {
                ;

            } else {
                fdate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(fdate));
            }

            if (tdate != null && tdate.contains("-")) {
                ;
            } else {
                tdate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(tdate));
            }

            if (district_id == null) {
                district_id = "0";
            }
            if (mandal_id == null) {
                mandal_id = "0";
            }
            if (village_id == null) {
                village_id = "0";
            }
            int count = 1;
            int count1 = 1;
            int count2 = 1;
            int count3 = 1;
            con = DBConnection.getConnection();
            if (village_id.equals("0") && district_id.equals("0") && mandal_id.equals("0")) {
                cstmt = con.prepareCall("{Call TotalReport(?,?)}");
                cstmt.setString(1, fdate);
                cstmt.setString(2, tdate);

                rs = cstmt.executeQuery();
                HashMap results = new HashMap();
                HashMap results1 = new HashMap();
                HashMap results2 = new HashMap();
                HashMap results3 = new HashMap();
                while (rs.next()) {

                    if (rs.getString(1).equalsIgnoreCase("Ration Card") || rs.getString(1).equalsIgnoreCase("Parents_Marriage") || rs.getString(1).equalsIgnoreCase

("AGE")) {


                        if (rs.getString(1).equalsIgnoreCase("Ration Card")) {
                            if (rs.getString(2) != null && (rs.getString(2).equalsIgnoreCase("3") || rs.getString(2).equalsIgnoreCase("4") || rs.getString

(2).equalsIgnoreCase("5"))) {
                                a1 = a1 + rs.getInt(4);
                                a2 = a2 + rs.getDouble(5);
                                a3 = a3 + rs.getInt(6);
                                a4 = a4 + rs.getDouble(7);
                                a5 = a5 + rs.getDouble(8);



                            } else {
                                results3.put("r" + count3++, rs.getString(4));
                                results3.put("r" + count3++, form.format(rs.getFloat(5)));
                                results3.put("r" + count3++, rs.getString(6));
                                results3.put("r" + count3++, form.format(rs.getFloat(7)));
                                results3.put("s" + t3++, form.format(rs.getFloat(8)));
                            }

                        } else {

                            results3.put("r" + count3++, rs.getString(4));
                            results3.put("r" + count3++, form.format(rs.getFloat(5)));
                            results3.put("r" + count3++, rs.getString(6));
                            results3.put("r" + count3++, form.format(rs.getFloat(7)));
                            results3.put("s" + t3++, form.format(rs.getFloat(8)));
                        }


                        if (rs.getString(1).equalsIgnoreCase("Ration Card")) {
                            ram = ram + Double.parseDouble(rs.getString(4));
                            raf = raf + Double.parseDouble(rs.getString(6));
                            r = r + Double.parseDouble(rs.getString(8));
                        }
                        if (rs.getString(1).equalsIgnoreCase("Parents_Marriage")) {
                            pmm = pmm + Double.parseDouble(rs.getString(4));
                            pmf = pmf + Double.parseDouble(rs.getString(6));
                            p = p + Double.parseDouble(rs.getString(8));

                        }
                        if (rs.getString(1).equalsIgnoreCase("AGE")) {
                            if (rs.getString(4) != null) {
                                am = am + Double.parseDouble(rs.getString(4));
                            }
                            if (rs.getString(6) != null) {
                                af = af + Double.parseDouble(rs.getString(6));
                            }
                            a = a + Double.parseDouble(rs.getString(8));

                        }




                    } else if (rs.getString(1).equalsIgnoreCase("religion") || rs.getString(1).equalsIgnoreCase("Caste")) {
                        results1.put("r" + count1++, rs.getString(4));
                        results1.put("r" + count1++, form.format(rs.getFloat(5)));
                        results1.put("r" + count1++, rs.getString(6));
                        results1.put("r" + count1++, form.format(rs.getFloat(7)));
                        results1.put("s" + t1++, form.format(rs.getFloat(8)));

                        if (rs.getString(1).equalsIgnoreCase("religion")) {
                            rem = rem + Double.parseDouble(rs.getString(4));
                            ref = ref + Double.parseDouble(rs.getString(6));
                            e1 = e1 + Double.parseDouble(rs.getString(8));
                        }
                        if (rs.getString(1).equalsIgnoreCase("Caste")) {
                            cm = cm + Double.parseDouble(rs.getString(4));
                            cf = cf + Double.parseDouble(rs.getString(6));
                            c = c + Double.parseDouble(rs.getString(8));
                        }



                    } else if (rs.getString(1).equalsIgnoreCase("Employment") || rs.getString(1).equalsIgnoreCase("Marital_status")) {
                        results2.put("r" + count2++, rs.getString(4));
                        results2.put("r" + count2++, form.format(rs.getFloat(5)));
                        results2.put("r" + count2++, rs.getString(6));
                        results2.put("r" + count2++, form.format(rs.getFloat(7)));
                        results2.put("s" + t2++, form.format(rs.getFloat(8)));
                        if (rs.getString(1).equalsIgnoreCase("Employment")) {
                            em = em + Double.parseDouble(rs.getString(4));
                            ef = ef + Double.parseDouble(rs.getString(6));
                            e2 = e2 + Double.parseDouble(rs.getString(8));
                        }
                        if (rs.getString(1).equalsIgnoreCase("Marital_status")) {
                            mm = mm + Double.parseDouble(rs.getString(4));
                            mf = mf + Double.parseDouble(rs.getString(6));
                            m = m + Double.parseDouble(rs.getString(8));
                        }


                    } else if (rs.getString(1).equalsIgnoreCase("TOTALCATEGORY") || rs.getString(1).equalsIgnoreCase("Disability_ID") || rs.getString

(1).equalsIgnoreCase("Education")) {
                        results.put("r" + count++, rs.getString(4));
                        results.put("r" + count++, form.format(rs.getFloat(5)));
                        results.put("r" + count++, rs.getString(6));
                        results.put("r" + count++, form.format(rs.getFloat(7)));
                        results.put("s" + t++, form.format(rs.getFloat(8)));
                        if (rs.getString(1).equalsIgnoreCase("TOTALCATEGORY")) {
                            if (rs.getString(4) != null) {
                                tm = tm + Double.parseDouble(rs.getString(4));
                            }
                            if (rs.getString(6) != null) {
                                tf = tf + Double.parseDouble(rs.getString(6));
                            }
                            if (rs.getString(4) != null) {
                                maleto = Integer.parseInt(rs.getString(4));
                            }
                            if (rs.getString(4) != null) {
                                femato = Integer.parseInt(rs.getString(6));
                            }
                        }
                        if (rs.getString(1).equalsIgnoreCase("Disability_ID")) {


                            dm = dm + Double.parseDouble(rs.getString(4));
                            df = df + Double.parseDouble(rs.getString(6));
                            d = d + Double.parseDouble(rs.getString(8));
                        }
                        if (rs.getString(1).equalsIgnoreCase("Education")) {
                            edm = edm + Double.parseDouble(rs.getString(4));
                            edf = edf + Double.parseDouble(rs.getString(6));
                            e = e + Double.parseDouble(rs.getString(8));
                        }

                    }
                }
                if (e >= 99) {
                    e = 100.00;
                }
                if (e1 >= 99) {
                    e1 = 100.00;
                }
                if (e2 >= 99) {
                    e2 = 100.00;
                }
                if (c >= 99) {
                    c = 100.00;
                }
                if (d >= 99) {
                    d = 100.00;
                }
                if (m >= 99) {
                    m = 100.00;
                }
                if (r >= 99) {
                    r = 100.00;
                }
                if (p >= 99) {
                    p = 100.00;
                }
                if (a >= 99) {
                    a = 100.00;
                }

                results3.put("totrm", (int) ram);
                results3.put("totrf", (int) raf);
                results3.put("totpm", (int) pmm);
                results3.put("totpf", (int) pmf);
                results3.put("toagm", (int) am);
                results3.put("toagf", (int) af);

                results.put("e", form.format(e));
                results.put("d", form.format(d));
                results1.put("e1", form.format(e1));
                results1.put("c", form.format(c));
                results2.put("e2", form.format(e2));
                results2.put("m", form.format(m));
                results3.put("r", form.format(r));
                results3.put("p", form.format(p));
                results3.put("a", form.format(a));

                results3.put("a1", a1);
                results3.put("a2", form.format(a2));
                results3.put("a3", a3);
                results3.put("a4", form.format(a4));
                results3.put("a5", form.format(a5));


                if ((raf + ram) != 0) {
                    if (ram != 0) {
                        results3.put("trm", form.format(100 * (ram / maleto)) + "");
                    } else {
                        results3.put("trm", 0);
                    }
                    if (raf != 0) {
                        results3.put("trf", form.format(100 * (raf / femato)) + "");
                    } else {
                        results3.put("trf", 0);
                    }

                } else if ((totalmale + totalfemale) == 0) {
                    results3.put("trm", "0");
                    results3.put("trf", "0");
                }

                if ((pmf + pmm) != 0) {
                    if (pmm != 0) {
                        results3.put("tpm", form.format(100 * (pmm / maleto)) + "");
                    } else {
                        results3.put("tpm", 0);
                    }

                    if (pmf != 0) {
                        results3.put("tpf", form.format(100 * (pmf / femato)) + "");
                    } else {
                        results3.put("tpf", 0);
                    }

                } else if ((pmf + pmm) == 0) {
                    results3.put("tpm", "0");
                    results3.put("tpf", "0");
                }

                if ((af + am) != 0) {
                    if (am != 0) {
                        results3.put("tagm", form.format(100 * (am / maleto)) + "");
                    } else {
                        results3.put("tagm", 0);
                    }
                    if (af != 0) {
                        results3.put("tagf", form.format(100 * (af / femato)) + "");
                    } else {
                        results3.put("tagf", 0);
                    }

                } else if ((af + am) == 0) {
                    results3.put("tagm", "0");
                    results3.put("tagf", "0");
                }


                //total dis edu

                if ((maleto + femato) != 0) {
                    if (maleto != 0) {
                        results.put("tom", form.format(((double) (maleto * 100) / (double) (maleto + femato))) + "");
                    } else {
                        results.put("tom", 0);
                    }
                    if (femato != 0) {
                        results.put("tof", form.format(((double) (femato * 100) / (double) (maleto + femato))) + "");
                    } else {
                        results.put("tof", 0);
                    }

                } else if ((femato + maleto) == 0) {
                    results.put("tom", "0");
                    results.put("tof", "0");
                }

                results.put("todm", (int) dm);
                results.put("todf", (int) df);

                if ((df + dm) != 0) {
                    if (dm != 0) {
                        results.put("tdm", form.format(100 * (dm / maleto)) + "");
                    } else {
                        results.put("tdm", 0);
                    }
                    if (df != 0) {
                        results.put("tdf", form.format(100 * (df / femato)) + "");
                    } else {
                        results.put("tdf", 0);
                    }

                } else if ((df + dm) == 0) {
                    results.put("tdm", "0");
                    results.put("tdf", "0");
                }
                results.put("toedm", (int) edm);
                results.put("toedf", (int) edf);
                if ((edf + edm) != 0) {
                    if (edm != 0) {
                        results.put("tedm", form.format(100 * (edm / maleto)) + "");
                    } else {
                        results.put("tedm", 0);
                    }
                    if (edf != 0) {
                        results.put("tedf", form.format(100 * (edf / femato)) + "");
                    } else {
                        results.put("tedf", 0);
                    }

                } else if ((edf + edm) == 0) {
                    results.put("tedm", "0");
                    results.put("tedf", "0");
                }
//emp, marital

                results2.put("toem", (int) em);
                results2.put("toef", (int) ef);
                results2.put("tomm", (int) mm);
                results2.put("tomf", (int) mf);

                if ((em + ef) != 0) {
                    if (em != 0) {
                        results2.put("tem", form.format(100 * (em / maleto)) + "");
                    } else {
                        results2.put("tem", 0);
                    }
                    if (ef != 0) {
                        results2.put("tef", form.format(100 * (ef / femato)) + "");
                    } else {
                        results2.put("tef", 0);
                    }

                } else if ((ef + em) == 0) {
                    results2.put("tem", "0");
                    results2.put("tef", "0");
                }
                if ((mf + mm) != 0) {
                    if (mm != 0) {
                        results2.put("tmm", form.format(100 * (mm / maleto)));
                    } else {
                        results2.put("tmm", 0);
                    }
                    if (mf != 0) {
                        results2.put("tmf", form.format(100 * (mf / femato)));
                    } else {
                        results2.put("tmf", 0);
                    }
                } else if ((mf + mm) == 0) {
                    results2.put("tmm", "0");
                    results2.put("tmf", "0");
                }


//caste religon
                results1.put("tocm", (int) cm);
                results1.put("tocf", (int) cf);
                results1.put("torem", (int) rem);
                results1.put("toref", (int) ref);

                if ((cf + cm) != 0) {
                    if (cm != 0) {
                        results1.put("tcm", form.format(100 * (cm / maleto)) + "");
                    } else {
                        results1.put("tcm", 0);
                    }
                    if (cf != 0) {
                        results1.put("tcf", form.format(100 * (cf / femato)) + "");
                    } else {
                        results1.put("tcf", 0);
                    }

                } else if ((cf + cm) == 0) {
                    results1.put("tcm", "0");
                    results1.put("tcf", "0");
                }
                if ((ref + rem) != 0) {
                    if (rem != 0) {
                        results1.put("trem", form.format(100 * (rem / maleto)) + "");
                    } else {
                        results1.put("trem", 0);
                    }
                    if (ref != 0) {
                        results1.put("tref", form.format(100 * (ref / femato)) + "");
                    } else {
                        results1.put("tref", 0);
                    }

                } else if ((ref + rem) == 0) {
                    results1.put("trem", "0");
                    results1.put("tref", "0");
                }



                results.put("district_id", district_id);
                results.put("mandal_id", mandal_id);
                results.put("village_id", village_id);
                results.put("fDate", fdate);
                results.put("tDate", tdate);


                results1.put("district_id", district_id);
                results1.put("mandal_id", mandal_id);
                results1.put("village_id", village_id);
                results1.put("fDate", fdate);
                results1.put("tDate", tdate);


                results2.put("district_id", district_id);
                results2.put("mandal_id", mandal_id);
                results2.put("village_id", village_id);
                results2.put("fDate", fdate);
                results2.put("tDate", tdate);



                results3.put("district_id", district_id);
                results3.put("mandal_id", mandal_id);
                results3.put("village_id", village_id);
                results3.put("fDate", fdate);
                results3.put("tDate", tdate);
                data.add(results);
                data2.add(results2);
                data1.add(results1);
                data3.add(results3);


                dat.add(data3);
                dat.add(data1);
                dat.add(data2);
                dat.add(data);
            }



            if (village_id.equals("0") && !district_id.equals("0") && mandal_id.equals("0")) {

                cstmt = con.prepareCall("{Call TotaldistrictReportPercentageALL(?,?,?)}");
                cstmt.setString(1, fdate);
                cstmt.setString(2, tdate);
                cstmt.setString(3, district_id);

                rs = cstmt.executeQuery();
                HashMap results = new HashMap();
                HashMap results1 = new HashMap();
                HashMap results2 = new HashMap();
                HashMap results3 = new HashMap();
                while (rs.next()) {

                    if (rs.getString(1).equalsIgnoreCase("Ration Card") || rs.getString(1).equalsIgnoreCase("Parents_Marriage") || rs.getString(1).equalsIgnoreCase

("AGE")) {
                        if (rs.getString(1).equalsIgnoreCase("Ration Card")) {
                            if (rs.getString(2) != null && (rs.getString(2).equalsIgnoreCase("3") || rs.getString(2).equalsIgnoreCase("4") || rs.getString

(2).equalsIgnoreCase("5"))) {
                                a1 = a1 + rs.getInt(4);
                                a2 = a2 + rs.getDouble(5);
                                a3 = a3 + rs.getInt(6);
                                a4 = a4 + rs.getDouble(7);
                                a5 = a5 + rs.getDouble(8);

                            } else {
                                results3.put("r" + count3++, rs.getString(4));
                                results3.put("r" + count3++, form.format(rs.getFloat(5)));
                                results3.put("r" + count3++, rs.getString(6));
                                results3.put("r" + count3++, form.format(rs.getFloat(7)));
                                results3.put("s" + t3++, form.format(rs.getFloat(8)));
                            }

                        } else {

                            results3.put("r" + count3++, rs.getString(4));
                            results3.put("r" + count3++, form.format(rs.getFloat(5)));
                            results3.put("r" + count3++, rs.getString(6));
                            results3.put("r" + count3++, form.format(rs.getFloat(7)));
                            results3.put("s" + t3++, form.format(rs.getFloat(8)));
                        }


                        if (rs.getString(1).equalsIgnoreCase("Ration Card")) {
                            ram = ram + Double.parseDouble(rs.getString(4));
                            raf = raf + Double.parseDouble(rs.getString(6));
                            r = r + Double.parseDouble(rs.getString(8));
                        }
                        if (rs.getString(1).equalsIgnoreCase("Parents_Marriage")) {
                            pmm = pmm + Double.parseDouble(rs.getString(4));
                            pmf = pmf + Double.parseDouble(rs.getString(6));
                            p = p + Double.parseDouble(rs.getString(8));

                        }
                        if (rs.getString(1).equalsIgnoreCase("AGE")) {
                            if (rs.getString(4) != null) {
                                am = am + Double.parseDouble(rs.getString(4));
                            }
                            if (rs.getString(6) != null) {
                                af = af + Double.parseDouble(rs.getString(6));
                            }
                            a = a + Double.parseDouble(rs.getString(8));
                        }




                    } else if (rs.getString(1).equalsIgnoreCase("religion") || rs.getString(1).equalsIgnoreCase("Caste")) {
                        results1.put("r" + count1++, rs.getString(4));
                        results1.put("r" + count1++, form.format(rs.getFloat(5)));
                        results1.put("r" + count1++, rs.getString(6));
                        results1.put("r" + count1++, form.format(rs.getFloat(7)));
                        results1.put("s" + t1++, form.format(rs.getFloat(8)));

                        if (rs.getString(1).equalsIgnoreCase("religion")) {
                            rem = rem + Double.parseDouble(rs.getString(4));
                            ref = ref + Double.parseDouble(rs.getString(6));
                            e1 = e1 + Double.parseDouble(rs.getString(8));
                        }
                        if (rs.getString(1).equalsIgnoreCase("Caste")) {
                            cm = cm + Double.parseDouble(rs.getString(4));
                            cf = cf + Double.parseDouble(rs.getString(6));
                            c = c + Double.parseDouble(rs.getString(8));
                        }



                    } else if (rs.getString(1).equalsIgnoreCase("Employment") || rs.getString(1).equalsIgnoreCase("Marital_status")) {
                        results2.put("r" + count2++, rs.getString(4));
                        results2.put("r" + count2++, form.format(rs.getFloat(5)));
                        results2.put("r" + count2++, rs.getString(6));
                        results2.put("r" + count2++, form.format(rs.getFloat(7)));
                        results2.put("s" + t2++, form.format(rs.getFloat(8)));
                        if (rs.getString(1).equalsIgnoreCase("Employment")) {
                            em = em + Double.parseDouble(rs.getString(4));
                            ef = ef + Double.parseDouble(rs.getString(6));
                            e2 = e2 + Double.parseDouble(rs.getString(8));
                        }
                        if (rs.getString(1).equalsIgnoreCase("Marital_status")) {
                            mm = mm + Double.parseDouble(rs.getString(4));
                            mf = mf + Double.parseDouble(rs.getString(6));
                            m = m + Double.parseDouble(rs.getString(8));
                        }


                    } else if (rs.getString(1).equalsIgnoreCase("TOTALCATEGORY") || rs.getString(1).equalsIgnoreCase("Disability_ID") || rs.getString

(1).equalsIgnoreCase("Education")) {
                        results.put("r" + count++, rs.getString(4));
                        results.put("r" + count++, form.format(rs.getFloat(5)));
                        results.put("r" + count++, rs.getString(6));
                        results.put("r" + count++, form.format(rs.getFloat(7)));
                        results.put("s" + t++, form.format(rs.getFloat(8)));
                        if (rs.getString(1).equalsIgnoreCase("TOTALCATEGORY")) {
                            if (rs.getString(4) != null) {
                                tm = tm + Double.parseDouble(rs.getString(4));
                            }
                            if (rs.getString(6) != null) {
                                tf = tf + Double.parseDouble(rs.getString(6));
                            }
                            if (rs.getString(4) != null) {
                                maleto = Integer.parseInt(rs.getString(4));
                            }
                            if (rs.getString(4) != null) {
                                femato = Integer.parseInt(rs.getString(6));
                            }
                        }
                        if (rs.getString(1).equalsIgnoreCase("Disability_ID")) {


                            dm = dm + Double.parseDouble(rs.getString(4));
                            df = df + Double.parseDouble(rs.getString(6));
                            d = d + Double.parseDouble(rs.getString(8));
                        }
                        if (rs.getString(1).equalsIgnoreCase("Education")) {
                            edm = edm + Double.parseDouble(rs.getString(4));
                            edf = edf + Double.parseDouble(rs.getString(6));
                            e = e + Double.parseDouble(rs.getString(8));
                        }

                    }
                }





                if (e >= 99) {
                    e = 100.00;
                }
                if (e1 >= 99) {
                    e1 = 100.00;
                }
                if (e2 >= 99) {
                    e2 = 100.00;
                }
                if (c >= 99) {
                    c = 100.00;
                }
                if (d >= 99) {
                    d = 100.00;
                }
                if (m >= 99) {
                    m = 100.00;
                }
                if (r >= 99) {
                    r = 100.00;
                }
                if (p >= 99) {
                    p = 100.00;
                }
                if (a >= 99) {
                    a = 100.00;
                }

                results3.put("totrm", (int) ram);
                results3.put("totrf", (int) raf);
                results3.put("totpm", (int) pmm);
                results3.put("totpf", (int) pmf);
                results3.put("toagm", (int) am);
                results3.put("toagf", (int) af);
                results.put("e", form.format(e));
                results.put("d", form.format(d));
                results1.put("e1", form.format(e1));
                results1.put("c", form.format(c));
                results2.put("e2", form.format(e2));
                results2.put("m", form.format(m));
                results3.put("r", form.format(r));
                results3.put("p", form.format(p));
                results3.put("a", form.format(a));

                results3.put("a1", a1);
                results3.put("a2", form.format(a2));
                results3.put("a3", a3);
                results3.put("a4", form.format(a4));
                results3.put("a5", form.format(a5));


                if ((raf + ram) != 0) {
                    if (ram != 0) {
                        results3.put("trm", form.format(100 * (ram / maleto)) + "");
                    } else {
                        results3.put("trm", 0);
                    }
                    if (raf != 0) {
                        results3.put("trf", form.format(100 * (raf / femato)) + "");
                    } else {
                        results3.put("trf", 0);
                    }

                } else if ((totalmale + totalfemale) == 0) {
                    results3.put("trm", "0");
                    results3.put("trf", "0");
                }

                if ((pmf + pmm) != 0) {
                    if (pmm != 0) {
                        results3.put("tpm", form.format(100 * (pmm / maleto)) + "");
                    } else {
                        results3.put("tpm", 0);
                    }

                    if (pmf != 0) {
                        results3.put("tpf", form.format(100 * (pmf / femato)) + "");
                    } else {
                        results3.put("tpf", 0);
                    }

                } else if ((pmf + pmm) == 0) {
                    results3.put("tpm", "0");
                    results3.put("tpf", "0");
                }

                if ((af + am) != 0) {
                    if (am != 0) {
                        results3.put("tagm", form.format(100 * (am / maleto)) + "");
                    } else {
                        results3.put("tagm", 0);
                    }
                    if (af != 0) {
                        results3.put("tagf", form.format(100 * (af / femato)) + "");
                    } else {
                        results3.put("tagf", 0);
                    }

                } else if ((af + am) == 0) {
                    results3.put("tagm", "0");
                    results3.put("tagf", "0");
                }
                //total dis edu
                if ((maleto + femato) != 0) {
                    if (maleto != 0) {
                        results.put("tom", form.format(((double) (maleto * 100) / (double) (maleto + femato))) + "");
                    } else {
                        results.put("tom", 0);
                    }
                    if (femato != 0) {
                        results.put("tof", form.format(((double) (femato * 100) / (double) (maleto + femato))) + "");
                    } else {
                        results.put("tof", 0);
                    }

                } else if ((femato + maleto) == 0) {
                    results.put("tom", "0");
                    results.put("tof", "0");
                }

                results.put("todm", (int) dm);
                results.put("todf", (int) df);

                if ((df + dm) != 0) {
                    if (dm != 0) {
                        results.put("tdm", form.format(100 * (dm / maleto)) + "");
                    } else {
                        results.put("tdm", 0);
                    }
                    if (df != 0) {
                        results.put("tdf", form.format(100 * (df / femato)) + "");
                    } else {
                        results.put("tdf", 0);
                    }

                } else if ((df + dm) == 0) {
                    results.put("tdm", "0");
                    results.put("tdf", "0");
                }
                results.put("toedm", (int) edm);
                results.put("toedf", (int) edf);
                if ((edf + edm) != 0) {
                    if (edm != 0) {
                        results.put("tedm", form.format(100 * (edm / maleto)) + "");
                    } else {
                        results.put("tedm", 0);
                    }
                    if (edf != 0) {
                        results.put("tedf", form.format(100 * (edf / femato)) + "");
                    } else {
                        results.put("tedf", 0);
                    }

                } else if ((edf + edm) == 0) {
                    results.put("tedm", "0");
                    results.put("tedf", "0");
                }
//emp, marital

                results2.put("toem", (int) em);
                results2.put("toef", (int) ef);
                results2.put("tomm", (int) mm);
                results2.put("tomf", (int) mf);

                if ((em + ef) != 0) {
                    if (em != 0) {
                        results2.put("tem", form.format(100 * (em / maleto)) + "");
                    } else {
                        results2.put("tem", 0);
                    }
                    if (ef != 0) {
                        results2.put("tef", form.format(100 * (ef / femato)) + "");
                    } else {
                        results2.put("tef", 0);
                    }

                } else if ((ef + em) == 0) {
                    results2.put("tem", "0");
                    results2.put("tef", "0");
                }
                if ((mf + mm) != 0) {
                    if (mm != 0) {
                        results2.put("tmm", form.format(100 * (mm / maleto)));
                    } else {
                        results2.put("tmm", 0);
                    }
                    if (mf != 0) {
                        results2.put("tmf", form.format(100 * (mf / femato)));
                    } else {
                        results2.put("tmf", 0);
                    }
                } else if ((mf + mm) == 0) {
                    results2.put("tmm", "0");
                    results2.put("tmf", "0");
                }


//caste religon
                results1.put("tocm", (int) cm);
                results1.put("tocf", (int) cf);
                results1.put("torem", (int) rem);
                results1.put("toref", (int) ref);

                if ((cf + cm) != 0) {
                    if (cm != 0) {
                        results1.put("tcm", form.format(100 * (cm / maleto)) + "");
                    } else {
                        results1.put("tcm", 0);
                    }
                    if (cf != 0) {
                        results1.put("tcf", form.format(100 * (cf / femato)) + "");
                    } else {
                        results1.put("tcf", 0);
                    }

                } else if ((cf + cm) == 0) {
                    results1.put("tcm", "0");
                    results1.put("tcf", "0");
                }
                if ((ref + rem) != 0) {
                    if (rem != 0) {
                        results1.put("trem", form.format(100 * (rem / maleto)) + "");
                    } else {
                        results1.put("trem", 0);
                    }
                    if (ref != 0) {
                        results1.put("tref", form.format(100 * (ref / femato)) + "");
                    } else {
                        results1.put("tref", 0);
                    }

                } else if ((ref + rem) == 0) {
                    results1.put("trem", "0");
                    results1.put("tref", "0");
                }
                results.put("district_id", district_id);
                results.put("mandal_id", mandal_id);
                results.put("village_id", village_id);
                results.put("fDate", fdate);
                results.put("tDate", tdate);
                results1.put("district_id", district_id);
                results1.put("mandal_id", mandal_id);
                results1.put("village_id", village_id);
                results1.put("fDate", fdate);
                results1.put("tDate", tdate);
                results2.put("district_id", district_id);
                results2.put("mandal_id", mandal_id);
                results2.put("village_id", village_id);
                results2.put("fDate", fdate);
                results2.put("tDate", tdate);
                results3.put("district_id", district_id);
                results3.put("mandal_id", mandal_id);
                results3.put("village_id", village_id);
                results3.put("fDate", fdate);
                results3.put("tDate", tdate);
                data.add(results);
                data2.add(results2);
                data1.add(results1);
                data3.add(results3);
                dat.add(data3);
                dat.add(data1);
                dat.add(data2);
                dat.add(data);
            } else if (village_id.equals("0") && !district_id.equals("0") && !mandal_id.equals("0")) {

                cstmt = con.prepareCall("{Call TotalMandalReport(?,?,?,?)}");
                cstmt.setString(1, fdate);
                cstmt.setString(2, tdate);
                cstmt.setString(3, district_id);
                cstmt.setString(4, mandal_id);
                rs = cstmt.executeQuery();
                HashMap results = new HashMap();
                HashMap results1 = new HashMap();
                HashMap results2 = new HashMap();
                HashMap results3 = new HashMap();
                while (rs.next()) {

                    if (rs.getString(1).equalsIgnoreCase("Ration Card") || rs.getString(1).equalsIgnoreCase("Parents_Marriage") || rs.getString(1).equalsIgnoreCase

("AGE")) {

                        if (rs.getString(1).equalsIgnoreCase("Ration Card")) {
                            if (rs.getString(2) != null && (rs.getString(2).equalsIgnoreCase("3") || rs.getString(2).equalsIgnoreCase("4") || rs.getString

(2).equalsIgnoreCase("5"))) {
                                a1 = a1 + rs.getInt(4);
                                a2 = a2 + rs.getDouble(5);
                                a3 = a3 + rs.getInt(6);
                                a4 = a4 + rs.getDouble(7);
                                a5 = a5 + rs.getDouble(8);
                            } else {
                                results3.put("r" + count3++, rs.getString(4));
                                results3.put("r" + count3++, form.format(rs.getFloat(5)));
                                results3.put("r" + count3++, rs.getString(6));
                                results3.put("r" + count3++, form.format(rs.getFloat(7)));
                                results3.put("s" + t3++, form.format(rs.getFloat(8)));
                            }

                        } else {
                            results3.put("r" + count3++, rs.getString(4));
                            results3.put("r" + count3++, form.format(rs.getFloat(5)));
                            results3.put("r" + count3++, rs.getString(6));
                            results3.put("r" + count3++, form.format(rs.getFloat(7)));
                            results3.put("s" + t3++, form.format(rs.getFloat(8)));
                        }
                        if (rs.getString(1).equalsIgnoreCase("Ration Card")) {
                            ram = ram + Double.parseDouble(rs.getString(4));
                            raf = raf + Double.parseDouble(rs.getString(6));
                            r = r + Double.parseDouble(rs.getString(8));
                        }
                        if (rs.getString(1).equalsIgnoreCase("Parents_Marriage")) {
                            pmm = pmm + Double.parseDouble(rs.getString(4));
                            pmf = pmf + Double.parseDouble(rs.getString(6));
                            p = p + Double.parseDouble(rs.getString(8));

                        }
                        if (rs.getString(1).equalsIgnoreCase("AGE")) {
                            if (rs.getString(4) != null) {
                                am = am + Double.parseDouble(rs.getString(4));
                            }
                            if (rs.getString(6) != null) {
                                af = af + Double.parseDouble(rs.getString(6));
                            }
                            a = a + Double.parseDouble(rs.getString(8));

                        }
                    } else if (rs.getString(1).equalsIgnoreCase("religion") || rs.getString(1).equalsIgnoreCase("Caste")) {
                        results1.put("r" + count1++, rs.getString(4));
                        results1.put("r" + count1++, form.format(rs.getFloat(5)));
                        results1.put("r" + count1++, rs.getString(6));
                        results1.put("r" + count1++, form.format(rs.getFloat(7)));
                        results1.put("s" + t1++, form.format(rs.getFloat(8)));

                        if (rs.getString(1).equalsIgnoreCase("religion")) {
                            rem = rem + Double.parseDouble(rs.getString(4));
                            ref = ref + Double.parseDouble(rs.getString(6));
                            e1 = e1 + Double.parseDouble(rs.getString(8));
                        }
                        if (rs.getString(1).equalsIgnoreCase("Caste")) {
                            cm = cm + Double.parseDouble(rs.getString(4));
                            cf = cf + Double.parseDouble(rs.getString(6));
                            c = c + Double.parseDouble(rs.getString(8));
                        }
                    } else if (rs.getString(1).equalsIgnoreCase("Employment") || rs.getString(1).equalsIgnoreCase("Marital_status")) {
                        results2.put("r" + count2++, rs.getString(4));
                        results2.put("r" + count2++, form.format(rs.getFloat(5)));
                        results2.put("r" + count2++, rs.getString(6));
                        results2.put("r" + count2++, form.format(rs.getFloat(7)));
                        results2.put("s" + t2++, form.format(rs.getFloat(8)));
                        if (rs.getString(1).equalsIgnoreCase("Employment")) {
                            em = em + Double.parseDouble(rs.getString(4));
                            ef = ef + Double.parseDouble(rs.getString(6));
                            e2 = e2 + Double.parseDouble(rs.getString(8));
                        }
                        if (rs.getString(1).equalsIgnoreCase("Marital_status")) {
                            mm = mm + Double.parseDouble(rs.getString(4));
                            mf = mf + Double.parseDouble(rs.getString(6));
                            m = m + Double.parseDouble(rs.getString(8));
                        }
                    } else if (rs.getString(1).equalsIgnoreCase("TOTALCATEGORY") || rs.getString(1).equalsIgnoreCase("Disability_ID") || rs.getString

(1).equalsIgnoreCase("Education")) {
                        results.put("r" + count++, rs.getString(4));
                        results.put("r" + count++, form.format(rs.getFloat(5)));
                        results.put("r" + count++, rs.getString(6));
                        results.put("r" + count++, form.format(rs.getFloat(7)));
                        results.put("s" + t++, form.format(rs.getFloat(8)));
                        if (rs.getString(1).equalsIgnoreCase("TOTALCATEGORY")) {
                            if (rs.getString(4) != null) {
                                tm = tm + Double.parseDouble(rs.getString(4));
                            }
                            if (rs.getString(6) != null) {
                                tf = tf + Double.parseDouble(rs.getString(6));
                            }
                            if (rs.getString(4) != null) {
                                maleto = Integer.parseInt(rs.getString(4));
                            }
                            if (rs.getString(4) != null) {
                                femato = Integer.parseInt(rs.getString(6));
                            }
                        }
                        if (rs.getString(1).equalsIgnoreCase("Disability_ID")) {
                            dm = dm + Double.parseDouble(rs.getString(4));
                            df = df + Double.parseDouble(rs.getString(6));
                            d = d + Double.parseDouble(rs.getString(8));
                        }
                        if (rs.getString(1).equalsIgnoreCase("Education")) {
                            edm = edm + Double.parseDouble(rs.getString(4));
                            edf = edf + Double.parseDouble(rs.getString(6));
                            e = e + Double.parseDouble(rs.getString(8));
                        }

                    }
                }

                if (e >= 99) {
                    e = 100.00;
                }
                if (e1 >= 99) {
                    e1 = 100.00;
                }
                if (e2 >= 99) {
                    e2 = 100.00;
                }
                if (c >= 99) {
                    c = 100.00;
                }
                if (d >= 99) {
                    d = 100.00;
                }
                if (m >= 99) {
                    m = 100.00;
                }
                if (r >= 99) {
                    r = 100.00;
                }
                if (p >= 99) {
                    p = 100.00;
                }
                if (a >= 99) {
                    a = 100.00;
                }

                results3.put("totrm", (int) ram);
                results3.put("totrf", (int) raf);
                results3.put("totpm", (int) pmm);
                results3.put("totpf", (int) pmf);
                results3.put("toagm", (int) am);
                results3.put("toagf", (int) af);
                results.put("e", form.format(e));
                results.put("d", form.format(d));
                results1.put("e1", form.format(e1));
                results1.put("c", form.format(c));
                results2.put("e2", form.format(e2));
                results2.put("m", form.format(m));
                results3.put("r", form.format(r));
                results3.put("p", form.format(p));
                results3.put("a", form.format(a));

                results3.put("a1", a1);
                results3.put("a2", form.format(a2));
                results3.put("a3", a3);
                results3.put("a4", form.format(a4));
                results3.put("a5", form.format(a5));

                if ((raf + ram) != 0) {
                    if (ram != 0) {
                        results3.put("trm", form.format(100 * (ram / maleto)) + "");
                    } else {
                        results3.put("trm", 0);
                    }
                    if (raf != 0) {
                        results3.put("trf", form.format(100 * (raf / femato)) + "");
                    } else {
                        results3.put("trf", 0);
                    }

                } else if ((totalmale + totalfemale) == 0) {
                    results3.put("trm", "0");
                    results3.put("trf", "0");
                }

                if ((pmf + pmm) != 0) {
                    if (pmm != 0) {
                        results3.put("tpm", form.format(100 * (pmm / maleto)) + "");
                    } else {
                        results3.put("tpm", 0);
                    }

                    if (pmf != 0) {
                        results3.put("tpf", form.format(100 * (pmf / femato)) + "");
                    } else {
                        results3.put("tpf", 0);
                    }

                } else if ((pmf + pmm) == 0) {
                    results3.put("tpm", "0");
                    results3.put("tpf", "0");
                }

                if ((af + am) != 0) {
                    if (am != 0) {
                        results3.put("tagm", form.format(100 * (am / maleto)) + "");
                    } else {
                        results3.put("tagm", 0);
                    }
                    if (af != 0) {
                        results3.put("tagf", form.format(100 * (af / femato)) + "");
                    } else {
                        results3.put("tagf", 0);
                    }

                } else if ((af + am) == 0) {
                    results3.put("tagm", "0");
                    results3.put("tagf", "0");
                }
                //total dis edu
                if ((maleto + femato) != 0) {
                    if (maleto != 0) {
                        results.put("tom", form.format(((double) (maleto * 100) / (double) (maleto + femato))) + "");
                    } else {
                        results.put("tom", 0);
                    }
                    if (femato != 0) {
                        results.put("tof", form.format(((double) (femato * 100) / (double) (maleto + femato))) + "");
                    } else {
                        results.put("tof", 0);
                    }

                } else if ((femato + maleto) == 0) {
                    results.put("tom", "0");
                    results.put("tof", "0");
                }

                results.put("todm", (int) dm);
                results.put("todf", (int) df);

                if ((df + dm) != 0) {
                    if (dm != 0) {
                        results.put("tdm", form.format(100 * (dm / maleto)) + "");
                    } else {
                        results.put("tdm", 0);
                    }
                    if (df != 0) {
                        results.put("tdf", form.format(100 * (df / femato)) + "");
                    } else {
                        results.put("tdf", 0);
                    }

                } else if ((df + dm) == 0) {
                    results.put("tdm", "0");
                    results.put("tdf", "0");
                }
                results.put("toedm", (int) edm);
                results.put("toedf", (int) edf);
                if ((edf + edm) != 0) {
                    if (edm != 0) {
                        results.put("tedm", form.format(100 * (edm / maleto)) + "");
                    } else {
                        results.put("tedm", 0);
                    }
                    if (edf != 0) {
                        results.put("tedf", form.format(100 * (edf / femato)) + "");
                    } else {
                        results.put("tedf", 0);
                    }

                } else if ((edf + edm) == 0) {
                    results.put("tedm", "0");
                    results.put("tedf", "0");
                }
//emp, marital

                results2.put("toem", (int) em);
                results2.put("toef", (int) ef);
                results2.put("tomm", (int) mm);
                results2.put("tomf", (int) mf);

                if ((em + ef) != 0) {
                    if (em != 0) {
                        results2.put("tem", form.format(100 * (em / maleto)) + "");
                    } else {
                        results2.put("tem", 0);
                    }
                    if (ef != 0) {
                        results2.put("tef", form.format(100 * (ef / femato)) + "");
                    } else {
                        results2.put("tef", 0);
                    }

                } else if ((ef + em) == 0) {
                    results2.put("tem", "0");
                    results2.put("tef", "0");
                }
                if ((mf + mm) != 0) {
                    if (mm != 0) {
                        results2.put("tmm", form.format(100 * (mm / maleto)));
                    } else {
                        results2.put("tmm", 0);
                    }
                    if (mf != 0) {
                        results2.put("tmf", form.format(100 * (mf / femato)));
                    } else {
                        results2.put("tmf", 0);
                    }
                } else if ((mf + mm) == 0) {
                    results2.put("tmm", "0");
                    results2.put("tmf", "0");
                }


//caste religon
                results1.put("tocm", (int) cm);
                results1.put("tocf", (int) cf);
                results1.put("torem", (int) rem);
                results1.put("toref", (int) ref);

                if ((cf + cm) != 0) {
                    if (cm != 0) {
                        results1.put("tcm", form.format(100 * (cm / maleto)) + "");
                    } else {
                        results1.put("tcm", 0);
                    }
                    if (cf != 0) {
                        results1.put("tcf", form.format(100 * (cf / femato)) + "");
                    } else {
                        results1.put("tcf", 0);
                    }

                } else if ((cf + cm) == 0) {
                    results1.put("tcm", "0");
                    results1.put("tcf", "0");
                }
                if ((ref + rem) != 0) {
                    if (rem != 0) {
                        results1.put("trem", form.format(100 * (rem / maleto)) + "");
                    } else {
                        results1.put("trem", 0);
                    }
                    if (ref != 0) {
                        results1.put("tref", form.format(100 * (ref / femato)) + "");
                    } else {
                        results1.put("tref", 0);
                    }

                } else if ((ref + rem) == 0) {
                    results1.put("trem", "0");
                    results1.put("tref", "0");
                }

                results.put("district_id", district_id);
                results.put("mandal_id", mandal_id);
                results.put("village_id", village_id);
                results.put("fDate", fdate);
                results.put("tDate", tdate);
                results1.put("district_id", district_id);
                results1.put("mandal_id", mandal_id);
                results1.put("village_id", village_id);
                results1.put("fDate", fdate);
                results1.put("tDate", tdate);
                results2.put("district_id", district_id);
                results2.put("mandal_id", mandal_id);
                results2.put("village_id", village_id);
                results2.put("fDate", fdate);
                results2.put("tDate", tdate);
                results3.put("district_id", district_id);
                results3.put("mandal_id", mandal_id);
                results3.put("village_id", village_id);
                results3.put("fDate", fdate);
                results3.put("tDate", tdate);
                data.add(results);
                data2.add(results2);
                data1.add(results1);
                data3.add(results3);
                dat.add(data3);
                dat.add(data1);
                dat.add(data2);
                dat.add(data);

            } else if (!village_id.equals("0") && !district_id.equals("0") && !mandal_id.equals("0")) {
                cstmt = con.prepareCall("{Call TotalVillageReport(?,?,?,?,?)}");
                cstmt.setString(1, fdate);
                cstmt.setString(2, tdate);
                cstmt.setString(3, district_id);
                cstmt.setString(4, mandal_id);
                cstmt.setString(5, village_id);

                rs = cstmt.executeQuery();
                HashMap results = new HashMap();
                HashMap results1 = new HashMap();
                HashMap results2 = new HashMap();
                HashMap results3 = new HashMap();
                while (rs.next()) {

                    if (rs.getString(1).equalsIgnoreCase("Ration Card") || rs.getString(1).equalsIgnoreCase("Parents_Marriage") || rs.getString(1).equalsIgnoreCase

("AGE")) {

                        if (rs.getString(1).equalsIgnoreCase("Ration Card")) {
                            if (rs.getString(2) != null && (rs.getString(2).equalsIgnoreCase("3") || rs.getString(2).equalsIgnoreCase("4") || rs.getString

(2).equalsIgnoreCase("5"))) {
                                a1 = a1 + rs.getInt(4);
                                a2 = a2 + rs.getDouble(5);
                                a3 = a3 + rs.getInt(6);
                                a4 = a4 + rs.getDouble(7);
                                a5 = a5 + rs.getDouble(8);

                            } else {
                                results3.put("r" + count3++, rs.getString(4));
                                results3.put("r" + count3++, form.format(rs.getFloat(5)));
                                results3.put("r" + count3++, rs.getString(6));
                                results3.put("r" + count3++, form.format(rs.getFloat(7)));
                                results3.put("s" + t3++, form.format(rs.getFloat(8)));
                            }

                        } else {

                            results3.put("r" + count3++, rs.getString(4));
                            results3.put("r" + count3++, form.format(rs.getFloat(5)));
                            results3.put("r" + count3++, rs.getString(6));
                            results3.put("r" + count3++, form.format(rs.getFloat(7)));
                            results3.put("s" + t3++, form.format(rs.getFloat(8)));
                        }


                        if (rs.getString(1).equalsIgnoreCase("Ration Card")) {
                            ram = ram + Double.parseDouble(rs.getString(4));
                            raf = raf + Double.parseDouble(rs.getString(6));
                            r = r + Double.parseDouble(rs.getString(8));
                        }
                        if (rs.getString(1).equalsIgnoreCase("Parents_Marriage")) {
                            pmm = pmm + Double.parseDouble(rs.getString(4));
                            pmf = pmf + Double.parseDouble(rs.getString(6));
                            p = p + Double.parseDouble(rs.getString(8));

                        }
                        if (rs.getString(1).equalsIgnoreCase("AGE")) {
                            if (rs.getString(4) != null) {
                                am = am + Double.parseDouble(rs.getString(4));
                            }
                            if (rs.getString(6) != null) {
                                af = af + Double.parseDouble(rs.getString(6));
                            }
                            a = a + Double.parseDouble(rs.getString(8));

                        }

                    } else if (rs.getString(1).equalsIgnoreCase("religion") || rs.getString(1).equalsIgnoreCase("Caste")) {
                        results1.put("r" + count1++, rs.getString(4));
                        results1.put("r" + count1++, form.format(rs.getFloat(5)));
                        results1.put("r" + count1++, rs.getString(6));
                        results1.put("r" + count1++, form.format(rs.getFloat(7)));
                        results1.put("s" + t1++, form.format(rs.getFloat(8)));

                        if (rs.getString(1).equalsIgnoreCase("religion")) {
                            rem = rem + Double.parseDouble(rs.getString(4));
                            ref = ref + Double.parseDouble(rs.getString(6));
                            e1 = e1 + Double.parseDouble(rs.getString(8));
                        }
                        if (rs.getString(1).equalsIgnoreCase("Caste")) {
                            cm = cm + Double.parseDouble(rs.getString(4));
                            cf = cf + Double.parseDouble(rs.getString(6));
                            c = c + Double.parseDouble(rs.getString(8));
                        }
                    } else if (rs.getString(1).equalsIgnoreCase("Employment") || rs.getString(1).equalsIgnoreCase("Marital_status")) {
                        results2.put("r" + count2++, rs.getString(4));
                        results2.put("r" + count2++, form.format(rs.getFloat(5)));
                        results2.put("r" + count2++, rs.getString(6));
                        results2.put("r" + count2++, form.format(rs.getFloat(7)));
                        results2.put("s" + t2++, form.format(rs.getFloat(8)));
                        if (rs.getString(1).equalsIgnoreCase("Employment")) {
                            em = em + Double.parseDouble(rs.getString(4));
                            ef = ef + Double.parseDouble(rs.getString(6));
                            e2 = e2 + Double.parseDouble(rs.getString(8));
                        }
                        if (rs.getString(1).equalsIgnoreCase("Marital_status")) {
                            mm = mm + Double.parseDouble(rs.getString(4));
                            mf = mf + Double.parseDouble(rs.getString(6));
                            m = m + Double.parseDouble(rs.getString(8));
                        }


                    } else if (rs.getString(1).equalsIgnoreCase("TOTALCATEGORY") || rs.getString(1).equalsIgnoreCase("Disability_ID") || rs.getString

(1).equalsIgnoreCase("Education")) {
                        results.put("r" + count++, rs.getString(4));
                        results.put("r" + count++, form.format(rs.getFloat(5)));
                        results.put("r" + count++, rs.getString(6));
                        results.put("r" + count++, form.format(rs.getFloat(7)));
                        results.put("s" + t++, form.format(rs.getFloat(8)));
                        if (rs.getString(1).equalsIgnoreCase("TOTALCATEGORY")) {
                            if (rs.getString(4) != null) {
                                tm = tm + Double.parseDouble(rs.getString(4));
                            }
                            if (rs.getString(6) != null) {
                                tf = tf + Double.parseDouble(rs.getString(6));
                            }
                            if (rs.getString(4) != null) {
                                maleto = Integer.parseInt(rs.getString(4));
                            }
                            if (rs.getString(4) != null) {
                                femato = Integer.parseInt(rs.getString(6));
                            }
                        }
                        if (rs.getString(1).equalsIgnoreCase("Disability_ID")) {


                            dm = dm + Double.parseDouble(rs.getString(4));
                            df = df + Double.parseDouble(rs.getString(6));
                            d = d + Double.parseDouble(rs.getString(8));
                        }
                        if (rs.getString(1).equalsIgnoreCase("Education")) {
                            edm = edm + Double.parseDouble(rs.getString(4));
                            edf = edf + Double.parseDouble(rs.getString(6));
                            e = e + Double.parseDouble(rs.getString(8));
                        }

                    }
                }

                if (e >= 99) {
                    e = 100.00;
                }
                if (e1 >= 99) {
                    e1 = 100.00;
                }
                if (e2 >= 99) {
                    e2 = 100.00;
                }
                if (c >= 99) {
                    c = 100.00;
                }
                if (d >= 99) {
                    d = 100.00;
                }
                if (m >= 99) {
                    m = 100.00;
                }
                if (r >= 99) {
                    r = 100.00;
                }
                if (p >= 99) {
                    p = 100.00;
                }
                if (a >= 99) {
                    a = 100.00;
                }

                results3.put("totrm", (int) ram);
                results3.put("totrf", (int) raf);
                results3.put("totpm", (int) pmm);
                results3.put("totpf", (int) pmf);
                results3.put("toagm", (int) am);
                results3.put("toagf", (int) af);
                results.put("e", form.format(e));
                results.put("d", form.format(d));
                results1.put("e1", form.format(e1));
                results1.put("c", form.format(c));
                results2.put("e2", form.format(e2));
                results2.put("m", form.format(m));
                results3.put("r", form.format(r));
                results3.put("p", form.format(p));
                results3.put("a", form.format(a));

                results3.put("a1", a1);
                results3.put("a2", form.format(a2));
                results3.put("a3", a3);
                results3.put("a4", form.format(a4));
                results3.put("a5", form.format(a5));



                if ((raf + ram) != 0) {
                    if (ram != 0) {
                        results3.put("trm", form.format(100 * (ram / maleto)) + "");
                    } else {
                        results3.put("trm", 0);
                    }
                    if (raf != 0) {
                        results3.put("trf", form.format(100 * (raf / femato)) + "");
                    } else {
                        results3.put("trf", 0);
                    }

                } else if ((totalmale + totalfemale) == 0) {
                    results3.put("trm", "0");
                    results3.put("trf", "0");
                }

                if ((pmf + pmm) != 0) {
                    if (pmm != 0) {
                        results3.put("tpm", form.format(100 * (pmm / maleto)) + "");
                    } else {
                        results3.put("tpm", 0);
                    }

                    if (pmf != 0) {
                        results3.put("tpf", form.format(100 * (pmf / femato)) + "");
                    } else {
                        results3.put("tpf", 0);
                    }

                } else if ((pmf + pmm) == 0) {
                    results3.put("tpm", "0");
                    results3.put("tpf", "0");
                }

                if ((af + am) != 0) {
                    if (am != 0) {
                        results3.put("tagm", form.format(100 * (am / maleto)) + "");
                    } else {
                        results3.put("tagm", 0);
                    }
                    if (af != 0) {
                        results3.put("tagf", form.format(100 * (af / femato)) + "");
                    } else {
                        results3.put("tagf", 0);
                    }

                } else if ((af + am) == 0) {
                    results3.put("tagm", "0");
                    results3.put("tagf", "0");
                }


                //total dis edu





                if ((maleto + femato) != 0) {
                    if (maleto != 0) {
                        results.put("tom", form.format(((double) (maleto * 100) / (double) (maleto + femato))) + "");
                    } else {
                        results.put("tom", 0);
                    }
                    if (femato != 0) {
                        results.put("tof", form.format(((double) (femato * 100) / (double) (maleto + femato))) + "");
                    } else {
                        results.put("tof", 0);
                    }

                } else if ((femato + maleto) == 0) {
                    results.put("tom", "0");
                    results.put("tof", "0");
                }

                results.put("todm", (int) dm);
                results.put("todf", (int) df);

                if ((df + dm) != 0) {
                    if (dm != 0) {
                        results.put("tdm", form.format(100 * (dm / maleto)) + "");
                    } else {
                        results.put("tdm", 0);
                    }
                    if (df != 0) {
                        results.put("tdf", form.format(100 * (df / femato)) + "");
                    } else {
                        results.put("tdf", 0);
                    }

                } else if ((df + dm) == 0) {
                    results.put("tdm", "0");
                    results.put("tdf", "0");
                }
                results.put("toedm", (int) edm);
                results.put("toedf", (int) edf);
                if ((edf + edm) != 0) {
                    if (edm != 0) {
                        results.put("tedm", form.format(100 * (edm / maleto)) + "");
                    } else {
                        results.put("tedm", 0);
                    }
                    if (edf != 0) {
                        results.put("tedf", form.format(100 * (edf / femato)) + "");
                    } else {
                        results.put("tedf", 0);
                    }

                } else if ((edf + edm) == 0) {
                    results.put("tedm", "0");
                    results.put("tedf", "0");
                }
//emp, marital

                results2.put("toem", (int) em);
                results2.put("toef", (int) ef);
                results2.put("tomm", (int) mm);
                results2.put("tomf", (int) mf);

                if ((em + ef) != 0) {
                    if (em != 0) {
                        results2.put("tem", form.format(100 * (em / maleto)) + "");
                    } else {
                        results2.put("tem", 0);
                    }
                    if (ef != 0) {
                        results2.put("tef", form.format(100 * (ef / femato)) + "");
                    } else {
                        results2.put("tef", 0);
                    }

                } else if ((ef + em) == 0) {
                    results2.put("tem", "0");
                    results2.put("tef", "0");
                }
                if ((mf + mm) != 0) {
                    if (mm != 0) {
                        results2.put("tmm", form.format(100 * (mm / maleto)));
                    } else {
                        results2.put("tmm", 0);
                    }
                    if (mf != 0) {
                        results2.put("tmf", form.format(100 * (mf / femato)));
                    } else {
                        results2.put("tmf", 0);
                    }
                } else if ((mf + mm) == 0) {
                    results2.put("tmm", "0");
                    results2.put("tmf", "0");
                }


//caste religon
                results1.put("tocm", (int) cm);
                results1.put("tocf", (int) cf);
                results1.put("torem", (int) rem);
                results1.put("toref", (int) ref);

                if ((cf + cm) != 0) {
                    if (cm != 0) {
                        results1.put("tcm", form.format(100 * (cm / maleto)) + "");
                    } else {
                        results1.put("tcm", 0);
                    }
                    if (cf != 0) {
                        results1.put("tcf", form.format(100 * (cf / femato)) + "");
                    } else {
                        results1.put("tcf", 0);
                    }


                } else if ((cf + cm) == 0) {
                    results1.put("tcm", "0");
                    results1.put("tcf", "0");
                }
                if ((ref + rem) != 0) {
                    if (rem != 0) {
                        results1.put("trem", form.format(100 * (rem / maleto)) + "");
                    } else {
                        results1.put("trem", 0);
                    }
                    if (ref != 0) {
                        results1.put("tref", form.format(100 * (ref / femato)) + "");
                    } else {
                        results1.put("tref", 0);
                    }

                } else if ((ref + rem) == 0) {
                    results1.put("trem", "0");
                    results1.put("tref", "0");
                }



                results.put("district_id", district_id);
                results.put("mandal_id", mandal_id);
                results.put("village_id", village_id);
                results.put("fDate", fdate);
                results.put("tDate", tdate);


                results1.put("district_id", district_id);
                results1.put("mandal_id", mandal_id);
                results1.put("village_id", village_id);
                results1.put("fDate", fdate);
                results1.put("tDate", tdate);


                results2.put("district_id", district_id);
                results2.put("mandal_id", mandal_id);
                results2.put("village_id", village_id);
                results2.put("fDate", fdate);
                results2.put("tDate", tdate);



                results3.put("district_id", district_id);
                results3.put("mandal_id", mandal_id);
                results3.put("village_id", village_id);
                results3.put("fDate", fdate);
                results3.put("tDate", tdate);
                data.add(results);
                data2.add(results2);
                data1.add(results1);
                data3.add(results3);


                dat.add(data3);
                dat.add(data1);
                dat.add(data2);
                dat.add(data);


            }


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "getMaritalStatusPersonalDetails", "FunctionalNeedReportDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getMaritalStatusPersonalDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "getMaritalStatusPersonalDetails", "FunctionalNeedReportDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getMaritalStatusPersonalDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);

            DBConnection.closeStatement(cstmt);
        }
        return dat;
    }

    /** Added by mohan on 28/10/2011 **/
    public ArrayList rachaBandaData(DataSource ds, String district_id) throws SADAREMDBException, SQLException {

        ArrayList rachaBandaData = new ArrayList();
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        HashMap rachaBanda = null;
        try {

            con = DBConnection.getConnection();

            if (district_id.equals("0")) {
                cstmt = con.prepareCall("{Call SP_RACHHABANDADATAREPORT_STATE()}");
                rs = cstmt.executeQuery();

                while (rs.next()) {
                    rachaBanda = new HashMap();
                    rachaBanda.put("id", rs.getString(2));
                    rachaBanda.put("district", rs.getString(3));
                    rachaBanda.put("totalPensioners", rs.getString(4));
                    rachaBanda.put("partAEntered", rs.getString(5));
                    rachaBanda.put("ortho", rs.getString(6));
                    rachaBanda.put("visual", rs.getString(7));
                    rachaBanda.put("hearing", rs.getString(8));
                    rachaBanda.put("mr", rs.getString(9));
                    rachaBanda.put("mi", rs.getString(10));
                    rachaBanda.put("md", rs.getString(11));
                    rachaBanda.put("total", rs.getString(12));
                    rachaBanda.put("dr", rs.getString(13));
                    rachaBanda.put("ar", rs.getString(14));
                    rachaBanda.put("totalRe", rs.getString(15));
                    rachaBandaData.add(rachaBanda);
                }
            } else {
                cstmt = con.prepareCall("{Call SP_RACHHABANDADATAREPORT(?)}");
                cstmt.setString(1, district_id);
                rs = cstmt.executeQuery();

                while (rs.next()) {
                    rachaBanda = new HashMap();
                    rachaBanda.put("id", rs.getString(2));
                    rachaBanda.put("district", rs.getString(3));
                    rachaBanda.put("mandal", rs.getString(3));
                    rachaBanda.put("totalPensioners", rs.getString(4));
                    rachaBanda.put("partAEntered", rs.getString(5));
                    rachaBanda.put("ortho", rs.getString(6));
                    rachaBanda.put("visual", rs.getString(7));
                    rachaBanda.put("hearing", rs.getString(8));
                    rachaBanda.put("mr", rs.getString(9));
                    rachaBanda.put("mi", rs.getString(10));
                    rachaBanda.put("md", rs.getString(11));
                    rachaBanda.put("total", rs.getString(12));
                    rachaBanda.put("dr", rs.getString(13));
                    rachaBanda.put("ar", rs.getString(14));
                    rachaBanda.put("totalRe", rs.getString(15));
                    rachaBandaData.add(rachaBanda);
                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMaritalStatusPersonalDetails", "FunctionalNeedReportDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getMaritalStatusPersonalDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMaritalStatusPersonalDetails", "FunctionalNeedReportDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getMaritalStatusPersonalDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }

        return rachaBandaData;
    }

    public ArrayList getRachaBandaPersonalDetails(DataSource ds, String district_id, String mandal_id, String modeStatus) throws SADAREMDBException, SQLException {

        ArrayList resultDataList = new ArrayList(); 
        String disabilityId = null; 
        try 
        {
 
            if (modeStatus.equals("Locomotor")) 
            {
                disabilityId = "1";
            } 
            else if (modeStatus.equals("Visual")) 
            {
                disabilityId = "2";
            }
            else if (modeStatus.equals("Hearing")) 
            {
                disabilityId = "3";
            } 
            else if (modeStatus.equals("MentalRetradation")) 
            {
                disabilityId = "4";
            } 
            else if (modeStatus.equals("MentalIllness")) 
            {
                disabilityId = "5";
            } 
            else
            {
                disabilityId = "6";
            }
            
            ArrayList paramList = new ArrayList();
            ArrayList tempList 	= new ArrayList();

            /*map = new HashMap();
            map.put("personCode", rs.getString(1));
            map.put("name", rs.getString(2));
            map.put("relationName", rs.getString(3));
            map.put("age", rs.getString(4));
            map.put("gender", rs.getString(5));
            map.put("disability", rs.getString(6));
            map.put("district", rs.getString(7));
            map.put("mandal", rs.getString(8));
            map.put("village", rs.getString(9)); */
            
            
            if (modeStatus.equals("dr")) 
            {
            	
            	   paramList = new ArrayList();
                   tempList 	= new ArrayList();
                 
            	quryStr="SELECT P.PERSON_CODE personCode,P.SURNAME+SPACE(2)+P.FIRST_NAME AS name,P.RELATION_NAME relationName, "
                        + " P.AGE_YEARS age,CASE WHEN P.GENDER='1' THEN 'Male' ELSE 'Female' END AS gender,"
                        + " case when P.TYPEOF_DISABILITY=1 then 'Locomotor' when P.TYPEOF_DISABILITY=2 then 'Visual' "
                        + " when P.TYPEOF_DISABILITY=3 then 'Hearing' when P.TYPEOF_DISABILITY=4 then 'Mental Retradation' "
                        + " when P.TYPEOF_DISABILITY=5 then 'Mental Illness' else 'MultipleDisability'  end as disability, \n"
                        + " MD.DISTRICT_NAME district,MM.MANDAL_NAME mandal,MV.VILLAGE_NAME village"
                        + " FROM DISABLED_PENSION D with(nolock)  ,TBLPERSON_PERSONAL_DETAILS P with (nolock) ,TBLDISTRICT_DETAILS MD with(nolock) ,"
                        + " TBLMANDAL_DETAILS MM with(nolock) ,TBLVILLAGE_DETAILS  MV with(nolock) ,TBLREJECTPERSON_DETAILS R  with(nolock) \n";
                
                if (district_id.equals("0")) 
                {
                	quryStr+=" WHERE D.DISTCODE = ?";
                	
                	tempList 	= new ArrayList();
                	tempList.add("S");
                	tempList.add(mandal_id);                	
                	paramList.add(tempList);
                } 
                else 
                {
                	quryStr+=" WHERE D.DISTCODE =? AND D.MNDCODE =?";
                	
                	tempList 	= new ArrayList();
                	tempList.add("S");
                	tempList.add(district_id);                	
                	paramList.add(tempList);
                	
                	tempList 	= new ArrayList();
                	tempList.add("S");
                	tempList.add(mandal_id);                	
                	paramList.add(tempList);
                }
                quryStr+="AND P.DISTRICT_ID = D.DISTCODE  AND P.DISTRICT_ID = MD.DISTRICT_ID"
                        + " AND P.DISTRICT_ID = MM.DISTRICT_ID AND P.MANDAL_ID = MM.MANDAL_ID AND "
                        + "P.DISTRICT_ID = MV.DISTRICT_ID AND P.MANDAL_ID = MV.MANDAL_ID AND "
                        + "P.VILLAGE_ID = MV.VILLAGE_ID  AND D.PENSIONPHASE = 'PhaseIV' AND "
                        + "D.INTEGRATEDDATE = '10/05/2011' AND D.PENSIONID  = P.PENSIONCARD_NO AND "
                        + "P.person_code=r.person_code";

            } 
            else if (modeStatus.equals("partAEntered")) 
            {
            	quryStr ="SELECT P.PERSON_CODE personCode,P.SURNAME+SPACE(2)+P.FIRST_NAME AS name,P.RELATION_NAME relationName, "
                        + "P.AGE_YEARS age,CASE WHEN P.GENDER='1' THEN 'Male' ELSE 'Female' END AS gender,"
                        + "case when P.TYPEOF_DISABILITY=1 then 'Locomotor' \n"
                        + "when p.TYPEOF_DISABILITY=2 then 'Visual' when p.TYPEOF_DISABILITY=3 then 'Hearing' \n"
                        + "when p.TYPEOF_DISABILITY=4 then 'Mental Retradation' when p.TYPEOF_DISABILITY=5 \n"
                        + "then 'Mental Illness' else 'MultipleDisability'  end as disability,MD.DISTRICT_NAME district,MM.MANDAL_NAME mandal,MV.VILLAGE_NAME village \n"
                        + "FROM DISABLED_PENSION D with(nolock) ,TBLPERSON_PERSONAL_DETAILS P with (nolock) ,TBLDISTRICT_DETAILS MD with(nolock) ,"
                        + "TBLMANDAL_DETAILS MM with(nolock) ,TBLVILLAGE_DETAILS  MV with(nolock) ";
            	
                if (district_id.equals("0"))
                {
                	quryStr+=" WHERE D.DISTCODE = ?";
                	
                	tempList 	= new ArrayList();
                	tempList.add("S");
                	tempList.add(mandal_id);                	
                	paramList.add(tempList);
                	
                } 
                else
                {
                	quryStr+=" WHERE D.DISTCODE = ? AND D.MNDCODE = ?";
                	
                	tempList 	= new ArrayList();
                	tempList.add("S");
                	tempList.add(district_id);                	
                	paramList.add(tempList);
                	
                	tempList 	= new ArrayList();
                	tempList.add("S");
                	tempList.add(mandal_id);                	
                	paramList.add(tempList);

                }

                quryStr+="AND P.DISTRICT_ID = D.DISTCODE  AND P.DISTRICT_ID = MD.DISTRICT_ID AND "
                        + "P.DISTRICT_ID = MM.DISTRICT_ID AND P.MANDAL_ID = MM.MANDAL_ID AND "
                        + "P.DISTRICT_ID = MV.DISTRICT_ID AND P.MANDAL_ID = MV.MANDAL_ID AND "
                        + "P.VILLAGE_ID = MV.VILLAGE_ID  AND D.PENSIONPHASE = 'PhaseIV' AND "
                        + "D.INTEGRATEDDATE = '10/05/2011' AND D.PENSIONID  = P.PENSIONCARD_NO AND "
                        + "P.PENSIONCARD_NO IN (SELECT PENSIONID FROM DISABLED_PENSION WHERE DISTCODE = '10'  "
                        + "AND PENSIONPHASE = 'PhaseIV' AND INTEGRATEDDATE = '10/05/2011')";
            } 
            else 
            {

            	quryStr="SELECT P.PERSON_CODE personCode,P.SURNAME+SPACE(2)+P.FIRST_NAME AS name,P.RELATION_NAME relationName, "
                        + "P.AGE_YEARS age,CASE WHEN P.GENDER='1' THEN 'Male' ELSE 'Female' END AS gender,"
                        + "case when PD.DISABILITY_ID=1 then 'Locomotor' when PD.DISABILITY_ID=2 "
                        + "then 'Visual' when PD.DISABILITY_ID=3 then 'Hearing' when "
                        + "PD.DISABILITY_ID=4 then 'Mental Retradation' when PD.DISABILITY_ID=5 "
                        + "then 'Mental Illness' else 'MultipleDisability'  end as disability,"
                        + "MD.DISTRICT_NAME district,MM.MANDAL_NAME mandal,MV.VILLAGE_NAME village \n"
                        + "FROM DISABLED_PENSION D,TBLPERSON_PERSONAL_DETAILS P with (nolock) ,TBLPERSON_DISABILITY_DETAILS PD with(nolock) ,"
                        + "TBLPERSON_DISABILITY_TOTALVALUE_DETAILS PT with(nolock) ,TBLDISTRICT_DETAILS MD with(nolock) ,TBLMANDAL_DETAILS MM with(nolock) ,"
                        + "TBLVILLAGE_DETAILS  MV with(nolock) " ;

                if (district_id.equals("0"))
                {
                	quryStr+=" WHERE D.DISTCODE =?";
                	
                	
                	tempList 	= new ArrayList();
                	tempList.add("S");
                	tempList.add(mandal_id);                	
                	paramList.add(tempList);
                } 
                else 
                {
                	quryStr+=" WHERE D.DISTCODE = ? AND D.MNDCODE =?";
                	

                	tempList 	= new ArrayList();
                	tempList.add("S");
                	tempList.add(district_id);                	
                	paramList.add(tempList);
                	
                	tempList 	= new ArrayList();
                	tempList.add("S");
                	tempList.add(mandal_id);                	
                	paramList.add(tempList);

                }

                if (modeStatus.equals("ar")) 
                {
                	quryStr+=" AND PT.TOTALDISABILITY < 40";
                }

                quryStr+=" AND P.DISTRICT_ID = D.DISTCODE  AND P.DISTRICT_ID = MD.DISTRICT_ID AND P.DISTRICT_ID = MM.DISTRICT_ID "
                        + "AND P.MANDAL_ID = MM.MANDAL_ID AND P.DISTRICT_ID = MV.DISTRICT_ID AND P.MANDAL_ID = MV.MANDAL_ID AND "
                        + "P.VILLAGE_ID = MV.VILLAGE_ID  AND D.PENSIONPHASE = 'PhaseIV' AND D.INTEGRATEDDATE = '10/05/2011' AND "
                        + "D.PENSIONID  = P.PENSIONCARD_NO AND P.PERSON_CODE = PD.PERSON_CODE AND P.PERSON_CODE = PT.PERSON_CODE "
                        + "AND PD.PERSON_CODE  = PT.PERSON_CODE ";
                
		                if (!modeStatus.equals("ar") && !modeStatus.equals("dr") && !modeStatus.equals("partAEntered") && !modeStatus.equals("total") && !

modeStatus.equals("totalRe")) 
		                {
		                	quryStr+=" AND PD.DISABILITY_ID  =?" ;
		                	

		                	tempList 	= new ArrayList();
		                	tempList.add("S");
		                	tempList.add(disabilityId);                	
		                	paramList.add(tempList); 
		                } 
		                else if (modeStatus.equals("total")) 
		                {
		                	quryStr+=" AND PD.DISABILITY_ID in('1','2','3','4','5','6')";
		                } 
		                else if (modeStatus.equals("totalRe"))
		                {
		                	
		                }
            }
 
                
            resultDataList = DataAccess.pickDataByPrepareStmtArrayListHashMap(quryStr, paramList);
                
                

        } catch (SQLException sqlEx) 
        {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMaritalStatusPersonalDetails", "FunctionalNeedReportDAO", "DataBase");
           
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getMaritalStatusPersonalDetails");
        } 
        catch (Exception sqlEx) 
        {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMaritalStatusPersonalDetails", "FunctionalNeedReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FunctionalNeedReportDAO", 

"getMaritalStatusPersonalDetails");
        } 
        

        return resultDataList;
    }
    /** End */
}
