/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.Exception.SADAREMException;

import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author t_bapinaidu
 */
public class DataEnteredFieldsServiceDAO {

    public Map<String, List<String>> getDataEnteredFieldsDetails(DataSource datasource, String personCode) throws SADAREMDBException,SQLException {
        ResultSet rs = null;
        Connection con = null;
        List<String> dataEnteredList = null;
        Map<String, List<String>> dataEnteredListMap = null;
        List<String> locomotorSubList = null;
        List<String> mentalretardationtests = null;
        CallableStatement cstmt = null;

        try {

            con = DBConnection.getConnection();
            cstmt = con.prepareCall("{Call SP_REPORTFORDOCTORENTERFIELDS(?)}");
            cstmt.setString(1, personCode);
            rs = cstmt.executeQuery();
            while (rs.next()) {
                dataEnteredListMap = new LinkedHashMap<String, List<String>>();

                if (null != rs.getString("PERSONAL")) {
                    dataEnteredList = new LinkedList<String>();

                    dataEnteredList.add("PersonalDetails");
                    if ("Eligible".equals(rs.getString("PERSONSTATUS"))) {
                        if (!"0".equals(rs.getString("DISABILITYID"))) {
                            locomotorSubList = new LinkedList<String>();
                            mentalretardationtests = new LinkedList<String>();
                            if (null != rs.getString("DISABILITY")) {
                                dataEnteredList.add("DisabilityDetails");
                            }

                            if ("1".equals(rs.getString("DISABILITYID"))) {
                                if (rs.getFloat("UPPEREXTRIMITY") > 0) {
                                    locomotorSubList.add("UpperExtremity");
                                }
                                if (rs.getFloat("LOWEREXTRIMITY") > 0) {
                                    locomotorSubList.add("LowerExtremity");
                                }
                                if (rs.getFloat("AMPUTATION") > 0) {
                                    locomotorSubList.add("Amputation");
                                }
                                if (rs.getFloat("TRANSVERSE") > 0) {
                                    locomotorSubList.add("CongentialDeficiencies");
                                }

                                if (rs.getFloat("TRUNK") > 0) {
                                    locomotorSubList.add("Trunk");
                                }

                                if (rs.getFloat("EVALUATION") > 0) {
                                    locomotorSubList.add("NeurologicalConditions");
                                }

                                if (rs.getFloat("CARDIOPULMONARY") > 0) {
                                    locomotorSubList.add("CardiopulmonaryDiseases");
                                }

                                if (rs.getFloat("DWARFISM") > 0) {
                                    locomotorSubList.add("Dwarfism");
                                }
                                if (rs.getFloat("UPPEREXTRIMITY") > 0 || rs.getFloat("LOWEREXTRIMITY") > 0
                                        || rs.getFloat("AMPUTATION") > 0 || rs.getFloat("TRANSVERSE") > 0
                                        || rs.getFloat("TRUNK") > 0 || rs.getFloat("EVALUATION") > 0
                                        || rs.getFloat("CARDIOPULMONARY") > 0 || rs.getFloat("DWARFISM") > 0) {
                                    dataEnteredList.add("Locomotor");
                                }
                            }

                            if ("2".equals(rs.getString("DISABILITYID"))) {
                                if (rs.getFloat("VISUALIMPAIRMENT") > 0) {
                                    dataEnteredList.add("VisualImpairment");
                                }
                            }

                            if ("3".equals(rs.getString("DISABILITYID"))) {
                                if (rs.getFloat("HEARINGIMPAIRMENT") > 0) {
                                    dataEnteredList.add("HearingImpairment");
                                }
                            }
                            if ("4".equals(rs.getString("DISABILITYID"))) {

                                if (rs.getFloat("DST") > 0) {
                                    mentalretardationtests.add("DevelopmentalScreeningTest");
                                }

                                if (rs.getFloat("VSMS") > 0) {
                                    mentalretardationtests.add("VinelandSocialMaturityScaleRecordSheet");
                                }

                                if (rs.getFloat("BKT") > 0) {
                                    mentalretardationtests.add("BinetKamatTestofIntelligence");
                                }

                                if (rs.getFloat("MISIC") > 0) {
                                    mentalretardationtests.add("MalinsIntelligenceScaleforIndianChildren");
                                }

                                if (rs.getFloat("SFB") > 0) {
                                    mentalretardationtests.add("SeguinFormBoard");
                                }

                                if (rs.getFloat("PAT") > 0) {
                                    mentalretardationtests.add("AlexanderPassAlongTest");
                                }

                                if (rs.getFloat("BBPTI") > 0) {
                                    mentalretardationtests.add("BhatiasBatteryofIntelligenceTests");
                                }

                                if (rs.getFloat("DST") > 0 || rs.getFloat("VSMS") > 0
                                        || rs.getFloat("BKT") > 0 || rs.getFloat("MISIC") > 0
                                        || rs.getFloat("SFB") > 0 || rs.getFloat("PAT") > 0
                                        || rs.getFloat("BBPTI") > 0) {
                                    dataEnteredList.add("MentalRetardation");
                                }
                            }
                            if ("5".equals(rs.getString("DISABILITYID"))) {
                                if (rs.getFloat("MENTALILLNESS") > 0) {
                                    dataEnteredList.add("MentalIllness");
                                }
                            }
                            if ("6".equals(rs.getString("DISABILITYID"))) {


                                if (rs.getFloat("UPPEREXTRIMITY") > 0) {
                                    locomotorSubList.add("UpperExtremity");
                                }
                                if (rs.getFloat("LOWEREXTRIMITY") > 0) {
                                    locomotorSubList.add("LowerExtremity");
                                }
                                if (rs.getFloat("AMPUTATION") > 0) {
                                    locomotorSubList.add("Amputation");
                                }
                                if (rs.getFloat("TRANSVERSE") > 0) {
                                    locomotorSubList.add("CongentialDeficiencies");
                                }

                                if (rs.getFloat("TRUNK") > 0) {
                                    locomotorSubList.add("Trunk");
                                }

                                if (rs.getFloat("EVALUATION") > 0) {
                                    locomotorSubList.add("NeurologicalConditions");
                                }

                                if (rs.getFloat("CARDIOPULMONARY") > 0) {
                                    locomotorSubList.add("CardiopulmonaryDiseases");
                                }

                                if (rs.getFloat("DWARFISM") > 0) {
                                    locomotorSubList.add("Dwarfism");
                                }
                                if (rs.getFloat("UPPEREXTRIMITY") > 0 || rs.getFloat("LOWEREXTRIMITY") > 0
                                        || rs.getFloat("AMPUTATION") > 0 || rs.getFloat("TRANSVERSE") > 0
                                        || rs.getFloat("TRUNK") > 0 || rs.getFloat("EVALUATION") > 0
                                        || rs.getFloat("CARDIOPULMONARY") > 0 || rs.getFloat("DWARFISM") > 0) {
                                    dataEnteredList.add("Locomotor");
                                }

                                if (rs.getFloat("VISUALIMPAIRMENT") > 0) {
                                    dataEnteredList.add("VisualImpairment");
                                }
                                if (rs.getFloat("HEARINGIMPAIRMENT") > 0) {
                                    dataEnteredList.add("HearingImpairment");
                                }

                                if (rs.getFloat("DST") > 0) {
                                    mentalretardationtests.add("DevelopmentalScreeningTest");
                                }

                                if (rs.getFloat("VSMS") > 0) {
                                    mentalretardationtests.add("VinelandSocialMaturityScaleRecordSheet");
                                }

                                if (rs.getFloat("BKT") > 0) {
                                    mentalretardationtests.add("BinetKamatTestofIntelligence");
                                }

                                if (rs.getFloat("MISIC") > 0) {
                                    mentalretardationtests.add("MalinsIntelligenceScaleforIndianChildren");
                                }

                                if (rs.getFloat("SFB") > 0) {
                                    mentalretardationtests.add("SeguinFormBoard");
                                }

                                if (rs.getFloat("PAT") > 0) {
                                    mentalretardationtests.add("AlexanderPassAlongTest");
                                }

                                if (rs.getFloat("BBPTI") > 0) {
                                    mentalretardationtests.add("BhatiasBatteryofIntelligenceTests");
                                }

                                if (rs.getFloat("DST") > 0 || rs.getFloat("VSMS") > 0
                                        || rs.getFloat("BKT") > 0 || rs.getFloat("MISIC") > 0
                                        || rs.getFloat("SFB") > 0 || rs.getFloat("PAT") > 0
                                        || rs.getFloat("BBPTI") > 0) {
                                    dataEnteredList.add("MentalRetardation");
                                }

                                if (rs.getFloat("MENTALILLNESS") > 0) {
                                    dataEnteredList.add("MentalIllness");
                                }

                            }
                            dataEnteredList.add("GeneralNeeds");
                        }
                    } else if ("Rejected".equals(rs.getString("PERSONSTATUS"))) {
                        if (!"0".equals(rs.getString("DISABILITYID"))) {
                            if (null != rs.getString("REJECTEDDETAILS")) {
                                dataEnteredList.add("RejectedDetails");

                            }
                        }

                    }

                    dataEnteredListMap.put("locomotorsublist", locomotorSubList);
                    dataEnteredListMap.put("mentalretardationtests", mentalretardationtests);
                    dataEnteredListMap.put("mainsectionslist", dataEnteredList);
                }



            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getDataEnteredFieldsDetails", "DataEnteredFieldsServiceDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DataEnteredFieldsServiceDAO", "getDataEnteredFieldsDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getDataEnteredFieldsDetails", "DataEnteredFieldsServiceDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DataEnteredFieldsServiceDAO", "getDataEnteredFieldsDetails");
        } finally {

           DBConnection.closeResultSet(rs);
           DBConnection.closeStatement(cstmt);
           DBConnection.closeConnection(con);
        }
        return dataEnteredListMap;
    }

    public Map<String, List<String>> getDataEnteredFieldsDetailsAU(DataSource datasource, String personCode) throws SADAREMDBException,SQLException{
        ResultSet rs = null;
        Connection con = null;
        List<String> dataEnteredList = null;
        Map<String, List<String>> dataEnteredListMap = null;
        List<String> locomotorSubList = null;
        List<String> mentalretardationtests = null;
        CallableStatement cstmt = null;

        try {

            con = DBConnection.getConnection();
            cstmt = con.prepareCall("{Call SP_REPORTFORDOCTORENTERFIELDS(?)}");
            cstmt.setString(1, personCode);
            rs = cstmt.executeQuery();
            while (rs.next()) {
                dataEnteredListMap = new LinkedHashMap<String, List<String>>();

                if (null != rs.getString("PERSONAL")) {
                    dataEnteredList = new LinkedList<String>();

                    //  dataEnteredList.add("PersonalDetails");
                    if ("Eligible".equals(rs.getString("PERSONSTATUS"))) {
                        if (!"0".equals(rs.getString("DISABILITYID"))) {
                            locomotorSubList = new LinkedList<String>();
                            mentalretardationtests = new LinkedList<String>();
                            if (null != rs.getString("DISABILITY")) {
                                dataEnteredList.add("DisabilityDetails");
                            }

                            if ("1".equals(rs.getString("DISABILITYID"))) {
                                if (rs.getFloat("UPPEREXTRIMITY") > 0) {
                                    locomotorSubList.add("UpperExtremity");
                                }
                                if (rs.getFloat("LOWEREXTRIMITY") > 0) {
                                    locomotorSubList.add("LowerExtremity");
                                }
                                if (rs.getFloat("AMPUTATION") > 0) {
                                    locomotorSubList.add("Amputation");
                                }
                                if (rs.getFloat("TRANSVERSE") > 0) {
                                    locomotorSubList.add("CongentialDeficiencies");
                                }

                                if (rs.getFloat("TRUNK") > 0) {
                                    locomotorSubList.add("Trunk");
                                }

                                if (rs.getFloat("EVALUATION") > 0) {
                                    locomotorSubList.add("NeurologicalConditions");
                                }

                                if (rs.getFloat("CARDIOPULMONARY") > 0) {
                                    locomotorSubList.add("CardiopulmonaryDiseases");
                                }

                                if (rs.getFloat("DWARFISM") > 0) {
                                    locomotorSubList.add("Dwarfism");
                                }
                                if (rs.getFloat("UPPEREXTRIMITY") > 0 || rs.getFloat("LOWEREXTRIMITY") > 0
                                        || rs.getFloat("AMPUTATION") > 0 || rs.getFloat("TRANSVERSE") > 0
                                        || rs.getFloat("TRUNK") > 0 || rs.getFloat("EVALUATION") > 0
                                        || rs.getFloat("CARDIOPULMONARY") > 0 || rs.getFloat("DWARFISM") > 0) {
                                    dataEnteredList.add("Locomotor");
                                }
                            }

                            if ("2".equals(rs.getString("DISABILITYID"))) {
                                if (rs.getFloat("VISUALIMPAIRMENT") > 0) {
                                    dataEnteredList.add("VisualImpairment");
                                }
                            }

                            if ("3".equals(rs.getString("DISABILITYID"))) {
                                if (rs.getFloat("HEARINGIMPAIRMENT") > 0) {
                                    dataEnteredList.add("HearingImpairment");
                                }
                            }
                            if ("4".equals(rs.getString("DISABILITYID"))) {

                                if (rs.getFloat("DST") > 0) {
                                    mentalretardationtests.add("DevelopmentalScreeningTest");
                                }

                                if (rs.getFloat("VSMS") > 0) {
                                    mentalretardationtests.add("VinelandSocialMaturityScaleRecordSheet");
                                }

                                if (rs.getFloat("BKT") > 0) {
                                    mentalretardationtests.add("BinetKamatTestofIntelligence");
                                }

                                if (rs.getFloat("MISIC") > 0) {
                                    mentalretardationtests.add("MalinsIntelligenceScaleforIndianChildren");
                                }

                                if (rs.getFloat("SFB") > 0) {
                                    mentalretardationtests.add("SeguinFormBoard");
                                }

                                if (rs.getFloat("PAT") > 0) {
                                    mentalretardationtests.add("AlexanderPassAlongTest");
                                }

                                if (rs.getFloat("BBPTI") > 0) {
                                    mentalretardationtests.add("BhatiasBatteryofIntelligenceTests");
                                }

                                if (rs.getFloat("DST") > 0 || rs.getFloat("VSMS") > 0
                                        || rs.getFloat("BKT") > 0 || rs.getFloat("MISIC") > 0
                                        || rs.getFloat("SFB") > 0 || rs.getFloat("PAT") > 0
                                        || rs.getFloat("BBPTI") > 0) {
                                    dataEnteredList.add("MentalRetardation");
                                }
                            }
                            if ("5".equals(rs.getString("DISABILITYID"))) {
                                if (rs.getFloat("MENTALILLNESS") > 0) {
                                    dataEnteredList.add("MentalIllness");
                                }
                            }
                            if ("6".equals(rs.getString("DISABILITYID"))) {


                                if (rs.getFloat("UPPEREXTRIMITY") > 0) {
                                    locomotorSubList.add("UpperExtremity");
                                }
                                if (rs.getFloat("LOWEREXTRIMITY") > 0) {
                                    locomotorSubList.add("LowerExtremity");
                                }
                                if (rs.getFloat("AMPUTATION") > 0) {
                                    locomotorSubList.add("Amputation");
                                }
                                if (rs.getFloat("TRANSVERSE") > 0) {
                                    locomotorSubList.add("CongentialDeficiencies");
                                }

                                if (rs.getFloat("TRUNK") > 0) {
                                    locomotorSubList.add("Trunk");
                                }

                                if (rs.getFloat("EVALUATION") > 0) {
                                    locomotorSubList.add("NeurologicalConditions");
                                }

                                if (rs.getFloat("CARDIOPULMONARY") > 0) {
                                    locomotorSubList.add("CardiopulmonaryDiseases");
                                }

                                if (rs.getFloat("DWARFISM") > 0) {
                                    locomotorSubList.add("Dwarfism");
                                }
                                if (rs.getFloat("UPPEREXTRIMITY") > 0 || rs.getFloat("LOWEREXTRIMITY") > 0
                                        || rs.getFloat("AMPUTATION") > 0 || rs.getFloat("TRANSVERSE") > 0
                                        || rs.getFloat("TRUNK") > 0 || rs.getFloat("EVALUATION") > 0
                                        || rs.getFloat("CARDIOPULMONARY") > 0 || rs.getFloat("DWARFISM") > 0) {
                                    dataEnteredList.add("Locomotor");
                                }

                                if (rs.getFloat("VISUALIMPAIRMENT") > 0) {
                                    dataEnteredList.add("VisualImpairment");
                                }
                                if (rs.getFloat("HEARINGIMPAIRMENT") > 0) {
                                    dataEnteredList.add("HearingImpairment");
                                }

                                if (rs.getFloat("DST") > 0) {
                                    mentalretardationtests.add("DevelopmentalScreeningTest");
                                }

                                if (rs.getFloat("VSMS") > 0) {
                                    mentalretardationtests.add("VinelandSocialMaturityScaleRecordSheet");
                                }

                                if (rs.getFloat("BKT") > 0) {
                                    mentalretardationtests.add("BinetKamatTestofIntelligence");
                                }

                                if (rs.getFloat("MISIC") > 0) {
                                    mentalretardationtests.add("MalinsIntelligenceScaleforIndianChildren");
                                }

                                if (rs.getFloat("SFB") > 0) {
                                    mentalretardationtests.add("SeguinFormBoard");
                                }

                                if (rs.getFloat("PAT") > 0) {
                                    mentalretardationtests.add("AlexanderPassAlongTest");
                                }

                                if (rs.getFloat("BBPTI") > 0) {
                                    mentalretardationtests.add("BhatiasBatteryofIntelligenceTests");
                                }

                                if (rs.getFloat("DST") > 0 || rs.getFloat("VSMS") > 0
                                        || rs.getFloat("BKT") > 0 || rs.getFloat("MISIC") > 0
                                        || rs.getFloat("SFB") > 0 || rs.getFloat("PAT") > 0
                                        || rs.getFloat("BBPTI") > 0) {
                                    dataEnteredList.add("MentalRetardation");
                                }

                                if (rs.getFloat("MENTALILLNESS") > 0) {
                                    dataEnteredList.add("MentalIllness");
                                }

                            }
                            dataEnteredList.add("GeneralNeeds");
                        }
                    } else if ("Rejected".equals(rs.getString("PERSONSTATUS"))) {
                        if (!"0".equals(rs.getString("DISABILITYID"))) {
                            if (null != rs.getString("REJECTEDDETAILS")) {
                                dataEnteredList.add("RejectedDetails");

                            }
                        }

                    }

                    dataEnteredListMap.put("locomotorsublist", locomotorSubList);
                    dataEnteredListMap.put("mentalretardationtests", mentalretardationtests);
                    dataEnteredListMap.put("mainsectionslist", dataEnteredList);
                }



            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getDataEnteredFieldsDetailsAU", "DataEnteredFieldsServiceDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DataEnteredFieldsServiceDAO", "getDataEnteredFieldsDetailsAU");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getDataEnteredFieldsDetailsAU", "DataEnteredFieldsServiceDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DataEnteredFieldsServiceDAO", "getDataEnteredFieldsDetailsAU");
        }  finally {
            DBConnection.closeResultSet(rs);
               DBConnection.closeStatement(cstmt);
               DBConnection.closeConnection(con);
        }
        return dataEnteredListMap;
    }
}
