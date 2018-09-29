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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.GrievancesRequestDetailsDTO;
import org.bf.disability.form.GrievancesRequestDetailsForm;
import org.bf.disability.form.RDCallCentreStatusForm;

import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author 728056
 */
public class GrievancesRequestDetailsDAO {

    Connection con = null;
    Statement st = null;
    String sql = null;
    ResultSet rs = null;
    HashMap map = null;
    PreparedStatement pstmt = null;

    public ArrayList getRequestName(DataSource ds, int currentRoleId) throws SADAREMDBException, SQLException {
        ArrayList RequestNames = new ArrayList();
        try {
            con = DBConnection.getConnection();
            st = con.createStatement();
            if (currentRoleId == 24) {
                sql = "select RequestId,SubRequestId,RequestName from dbo.tblRequestMaster where status='Active' and RequestId=5 order by RequestName";
            } else if (currentRoleId == 25) {
                sql = "select RequestId,SubRequestId,RequestName from dbo.tblRequestMaster where status='Active' and RequestId!=5 order by RequestName";
            } else {
                sql = "select RequestId,SubRequestId,RequestName from dbo.tblRequestMaster where status='Active' order by RequestName";
            }
            rs = st.executeQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    map = new HashMap();
                    map.put("requestId", rs.getString(1) + "&" + rs.getString(2));
                    map.put("requestName", rs.getString(3));
                    RequestNames.add(map);

                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRequestName", "GrievancesRequestDetailsDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsDAO", "getRequestName");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRequestName", "GrievancesRequestDetailsDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsDAO", "getRequestName");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(st);

        }
        return RequestNames;
    }

    public ArrayList getRequestDetails(DataSource ds, GrievancesRequestDetailsForm detailsForm, String districtid) throws SADAREMDBException, SQLException {
        ArrayList RequestDetails = new ArrayList();
        String requestIds = detailsForm.getRequestId();

        StringBuffer sb = new StringBuffer();
        try {
            con = DBConnection.getConnection();
            st = con.createStatement();

            sb.append("SELECT s.Beneficiary_Problem_ID,coalesce(nullif(s.person_code,''),'-'),"
                    + "coalesce(nullif(s.pensioncard_no,''),'-'),coalesce(nullif(s.RequestId,''),'-'),"
                    + "coalesce(nullif(s.SubRequestId,''),'-'),coalesce(nullif(s.Sur_Name,''),''),"
                    + "coalesce(nullif(s.First_Name,''),''),coalesce(nullif(s.Relation_Name,''),'-'),"
                    + "coalesce(nullif(s.House_No,''),'-'),coalesce(nullif(s.Cell_Number,''),'-'),"
                    + "coalesce(nullif(s.LandLine_Number,''),'-'),s.district_id,b.Mandal_Name,"
                    + "coalesce(nullif(s.Status,''),'-'), coalesce(nullif(s.Generated_SADAREMID,''),'-')"
                    + " from dbo.tblsadaremGreivances_Details s "
                    + "join tblmandal_Details b on (s.district_id =b.district_id) and (s.mandal_id =b.mandal_id) "
                    + " where flag='Active' and s.district_id=" + districtid);


            if (detailsForm.getStatus() != null && detailsForm.getStatus().equalsIgnoreCase("Pending")) {
                sb.append(" and s.Status not in ('Certificate Issued')");
            } else if (detailsForm.getStatus() != null && detailsForm.getStatus().equalsIgnoreCase("Closed")) {
                sb.append(" and s.Status='Certificate Issued'");
            }



            if (requestIds.equals("000")) {
                if (detailsForm.getRequestNumber() != null && !detailsForm.getRequestNumber().equals("")) {
                    sb.append(" and s.Beneficiary_Problem_ID='" + detailsForm.getRequestNumber() + "'");
                }

            } else if (!requestIds.equals("000")) {
                String[] reqData = requestIds.split("&");
                sb.append(" and s.RequestId='" + reqData[0] + "' and s.SubRequestId='" + reqData[1] + "'");
                if (detailsForm.getRequestNumber() != null && !detailsForm.getRequestNumber().equals("")) {
                    sb.append(" and s.Beneficiary_Problem_ID='" + detailsForm.getRequestNumber() + "'");
                }


            }
System.out.println("sb.toString() : "+sb.toString());
            rs = st.executeQuery(sb.toString());
            if (rs != null) {
                while (rs.next()) {
                    map = new HashMap();
                    map.put("requestNumber", rs.getString(1));
                    map.put("Person_Code", rs.getString(2));
                    map.put("pensioncard_no", rs.getString(3));
                    map.put("Name", rs.getString(7));
                    map.put("RelationName", rs.getString(8));
                    map.put("SadaremID", rs.getString(15));
                    map.put("SubRequestId", getRequestName(rs.getString(4), rs.getString(5), con));
                    map.put("DistrictId", rs.getString(12));
                    map.put("MandalId", rs.getString(13));
                    map.put("SaStatus", rs.getString(14));

                    RequestDetails.add(map);
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRequestDetails", "GrievancesRequestDetailsDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsDAO", "getRequestDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRequestDetails", "GrievancesRequestDetailsDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsDAO", "getRequestDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(st);

        }
        return RequestDetails;
    }

//kavya
    public ArrayList getRDCallCentreStatusReport(DataSource ds, RDCallCentreStatusForm rDCallCentreStatusForm) throws SADAREMDBException, SQLException {
        ArrayList reportDetails = new ArrayList();
        StringBuffer sb = new StringBuffer();
        CallableStatement cs = null;
        Date campdate;
        String startDate = "";
        String endDate = "";

        try {
            campdate = new SimpleDateFormat("dd/mm/yyyy").parse(rDCallCentreStatusForm.getFromdate());
            startDate = new SimpleDateFormat("mm/dd/yyyy").format(campdate);
            campdate = new SimpleDateFormat("dd/mm/yyyy").parse(rDCallCentreStatusForm.getTodate());
            endDate = new SimpleDateFormat("mm/dd/yyyy").format(campdate);
            con = DBConnection.getConnection();
            cs = con.prepareCall("{Call SP_RDCALLCENTER_REPORT(?,?,?)}");
            cs.setString(1, rDCallCentreStatusForm.getDistrict_id());
            cs.setString(2, startDate);
            cs.setString(3, endDate);

            rs = cs.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    map = new HashMap();
                    map.put("districtId", rs.getInt(1));
                    map.put("districtName", rs.getString(2));
                    map.put("totalRegistered", rs.getInt(3));
                    map.put("rdcallCenterPending", rs.getInt(4));
                    map.put("rdcallCenterClosed", rs.getInt(5));
                    map.put("sadaremPending", rs.getInt(6));
                    map.put("sadaremClosed", rs.getInt(7));
                    map.put("NewCertificate", rs.getInt(8));
                    map.put("NameChange", rs.getInt(9));
                    map.put("RelationName", rs.getInt(10));
                    map.put("DateOfBirth", rs.getInt(11));
                    map.put("SpellingMistake", rs.getInt(12));
                    map.put("IdentificationMarksChange", rs.getInt(13));
                    map.put("DuplicateCertificate", rs.getInt(14));
                    map.put("Reassessment", rs.getInt(15));
                    map.put("PhysicalRequirements", rs.getInt(16));
                    map.put("RenualCertificate", rs.getInt(17));
                    map.put("AssessementComptdNtGettingCertificate", rs.getInt(18));
                    map.put("NotGettingPenion", rs.getInt(19));
                    map.put("NotEligible", rs.getInt(20));
                    map.put("Abayastam", rs.getInt(21));
                    map.put("ICFSDelation", rs.getInt(22));
                    map.put("FingerPrintProblems", rs.getInt(23));
                    map.put("AliveButpensionDead", rs.getInt(24));
                    map.put("GettingDoublePension", rs.getInt(25));
                    map.put("Others", rs.getInt(26));


                    reportDetails.add(map);
                }
            }
        } catch (SQLException sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRDCallCentreStatusReport", "GrievancesRequestDetailsDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsDAO", "getRDCallCentreStatusReport");
        } catch (Exception sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRDCallCentreStatusReport", "GrievancesRequestDetailsDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsDAO", "getRDCallCentreStatusReport");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cs);

        }
        return reportDetails;
    }

    public ArrayList getDPMApprovalList(DataSource ds, GrievancesRequestDetailsForm detailsForm, String districtid) throws SADAREMDBException, SQLException {
        ArrayList approvalList = new ArrayList();
        String splitReq[];


        StringBuffer sb = new StringBuffer();
        try {
            con = DBConnection.getConnection();

            sb.append("SELECT s.Beneficiary_Problem_ID,s.person_code,s.RequestId,s.SubRequestId,"
                    + " s.Sur_Name,s.First_Name,s.Gender,s.Date_Of_Birth,s.Age,s.Relation_Name,s.House_No,"
                    + " s.Cell_Number,s.LandLine_Number,d.District_Name,b.Mandal_Name,"
                    + " p.Panchayat_Name,e.Village_Name,f.Habitation_Name,s.Status,s.Nature_Of_Beneficiary,s.Newsurname,"
                    + " s.Newname,s.Newrelationname,s.Newdob,s.Newmole1,s.Newmole2,s.Proofdoc_Type,s.Proof_Id,"
                    + " s.ProofDoc_FileName,s.Mole_One,s.Mole_Two,s.RelationType"
                    + ",s.district_id,s.mandal_id,s.village_id,s.Panchayat_ID,s.habitation_id,s.uploadFlag,s.proof_id,s.proofdoc_type"
                    + " from dbo.tblsadaremGreivances_Details s join tblDistrict_Details d on(s.district_id =d.district_id) "
                    + " join tblmandal_Details b on (s.district_id =b.district_id) and (s.mandal_id =b.mandal_id) "
                    + " join tblPanchayat_Details p on (s.district_id =p.district_id) and (s.mandal_id =p.mandal_id) and (s.Panchayat_ID =p.Panchayat_ID) "
                    + " join tblVillage_Details e on (s.district_id =e.district_id) and (s.mandal_id =e.mandal_id) and (s.village_id =e.village_id) "
                    + " join tblHabitation_Details f on ((s.district_id =f.district_id) and (s.mandal_id =f.mandal_id) and (s.village_id =f.village_id) and (s.Panchayat_ID =f.Panchayat_ID) and (s.habitation_id =f.Habitation_id) "
                    + " and s.habcode =f.Habitation_code)"
                    + " where flag='Active' ");

            if (districtid != null && !districtid.equalsIgnoreCase("")) {
                sb.append(" and s.district_id ='" + districtid + "' ");
            }

            if (detailsForm.getRequestId() != null && !detailsForm.getRequestId().equalsIgnoreCase("ALL")) {
                splitReq = detailsForm.getRequestId().split("&");
                sb.append(" and s.requestid='" + splitReq[0] + "' and s.SubRequestId like ('%" + splitReq[1] + "%')");

            }
            if (detailsForm.getBenificiaryProblemId() != null && !detailsForm.getBenificiaryProblemId().equalsIgnoreCase("")) {
                sb.append(" and s.Beneficiary_Problem_ID='" + detailsForm.getBenificiaryProblemId() + "'");

            }
            if (detailsForm.getStatus() != null) {
                if (detailsForm.getStatus().equalsIgnoreCase("ALL")) {
                    sb.append(" and s.status in ('Application Registered',"
                            + "'Documents Uploaded','MPDO Approved','PD Approved',"
                            + "'Scheduled Camp')");
                } else if (detailsForm.getStatus().equalsIgnoreCase("DUP")) {
                    sb.append(" and s.Nature_Of_Beneficiary='Voice' "
                            + " and s.status ='Application Registered'"
                            + " and "
                            + " s.uploadflag is null");

                } else if (detailsForm.getStatus().equalsIgnoreCase("DVP")) {
                    sb.append(" and ((s.status ='Application Registered' and (s.Nature_Of_Beneficiary='Web' "
                            + "or(s.Nature_Of_Beneficiary='Voice' and  s.requestid ='5' "
                            + " and s.uploadflag is not null))) "
                            + "  or (s.status ='Documents Uploaded'))");
                } else if (detailsForm.getStatus().equalsIgnoreCase("SCP")) {
                    sb.append(" and (s.status ='MPDO Approved' or s.status ='PD Approved'"
                            + " or (s.status='Documents Verified And Ok' "
                            + " and proofdoc_type='Adhaar Card'))");
                } else if (detailsForm.getStatus().equalsIgnoreCase("CIP")) {
                    sb.append(" and s.status ='Scheduled Camp'");
                }

            }
            sb = sb.append(" order by s.Beneficiary_Problem_ID asc");


            pstmt = con.prepareStatement(sb.toString());
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    map = new HashMap();
                    map.put("Beneficiary_Problem_ID", rs.getString(1));
                    map.put("BeneficiaryProblemId", rs.getString(1));
                    if (rs.getString(2) != null && !rs.getString(2).equalsIgnoreCase("")) {
                        map.put("SadaremId", rs.getString(2));
                    } else {
                        map.put("SadaremId", "--");
                    }
                    if (rs.getString(3) != null && !rs.getString(3).equalsIgnoreCase("")) {
                        map.put("RequestId", rs.getString(3));
                    } else {
                        map.put("RequestId", "--");
                    }
                    if (rs.getString(4) != null && !rs.getString(4).equalsIgnoreCase("")) {
                        map.put("SubRequestId", rs.getString(4));
                    } else {
                        map.put("SubRequestId", "--");
                    }
                    if (rs.getString(5) != null && !rs.getString(5).equalsIgnoreCase("") && !rs.getString(5).equalsIgnoreCase("-")) {
                        map.put("Sur_Name", rs.getString(5));
                    } else {
                        map.put("Sur_Name", "--");
                    }
                    if (rs.getString(6) != null && !rs.getString(6).equalsIgnoreCase("")) {
                        map.put("First_Name", rs.getString(6));
                    } else {
                        map.put("First_Name", "--");
                    }

                    if (rs.getString(5) != null && !rs.getString(5).equalsIgnoreCase("") && !rs.getString(5).equalsIgnoreCase("-")) {
                        map.put("Name", rs.getString(5) + " " + rs.getString(6));
                    } else {
                        if (rs.getString(6) != null && !rs.getString(6).equalsIgnoreCase("")) {
                            map.put("Name", rs.getString(6));
                        } else {
                            map.put("Name", "--");
                        }
                    }


                    if (rs.getString(7) != null && !rs.getString(7).equalsIgnoreCase("")) {
                        map.put("Gender", rs.getString(7));
                    } else {
                        map.put("Gender", "--");
                    }
                    if (rs.getString(8) != null && !rs.getString(8).equalsIgnoreCase("")) {
                        map.put("Dob", rs.getString(8));
                    } else {
                        map.put("Dob", "--");
                    }
                    if (rs.getString(9) != null && !rs.getString(9).equalsIgnoreCase("")) {
                        map.put("Age", rs.getString(9));
                    } else {
                        map.put("Age", "--");
                    }
                    if (rs.getString(10) != null && !rs.getString(10).equalsIgnoreCase("")) {
                        map.put("RelationName", rs.getString(10));
                    } else {
                        map.put("RelationName", "--");
                    }
                    if (rs.getString(11) != null && !rs.getString(11).equalsIgnoreCase("")) {
                        map.put("Address", rs.getString(11));
                    } else {
                        map.put("Address", "--");
                    }
                    if (rs.getString(12) != null && !rs.getString(12).equalsIgnoreCase("")) {
                        map.put("PhoneNumber", rs.getString(12));
                    } else {
                        map.put("PhoneNumber", "--");
                    }
                    if (rs.getString(13) != null && !rs.getString(13).equalsIgnoreCase("")) {
                        map.put("LandNumber", rs.getString(13));
                    } else {
                        map.put("LandNumber", "--");
                    }
                    if (rs.getString(14) != null && !rs.getString(14).equalsIgnoreCase("")) {
                        map.put("District", rs.getString(14));
                    } else {
                        map.put("District", "--");
                    }
                    if (rs.getString(15) != null && !rs.getString(15).equalsIgnoreCase("")) {
                        map.put("Mandal", rs.getString(15));
                    } else {
                        map.put("Mandal", "--");
                    }
                    if (rs.getString(16) != null && !rs.getString(16).equalsIgnoreCase("")) {
                        map.put("Panchayat", rs.getString(16));
                    } else {
                        map.put("Panchayat", "--");
                    }
                    if (rs.getString(17) != null && !rs.getString(17).equalsIgnoreCase("")) {
                        map.put("Village", rs.getString(17));
                    } else {
                        map.put("Village", "--");
                    }
                    if (rs.getString(18) != null && !rs.getString(18).equalsIgnoreCase("")) {
                        map.put("Habitation", rs.getString(18));
                    } else {
                        map.put("Habitation", "--");
                    }
                    if (rs.getString(19) != null && !rs.getString(19).equalsIgnoreCase("")) {
                        map.put("Status", rs.getString(19));
                    } else {
                        map.put("Status", "--");
                    }
                    if (rs.getString(20) != null && !rs.getString(20).equalsIgnoreCase("")) {
                        map.put("Nature_Of_Beneficiary", rs.getString(20));
                    } else {
                        map.put("Nature_Of_Beneficiary", "--");
                    }
                    if (rs.getString(21) != null && !rs.getString(21).equalsIgnoreCase("")) {
                        map.put("Newsurname", rs.getString(21));
                    } else {
                        map.put("Newsurname", "--");
                    }
                    if (rs.getString(22) != null && !rs.getString(22).equalsIgnoreCase("")) {
                        map.put("Newname", rs.getString(22));
                    } else {
                        map.put("Newname", "--");
                    }
                    if (rs.getString(23) != null && !rs.getString(23).equalsIgnoreCase("")) {
                        map.put("Newrelationname", rs.getString(23));
                    } else {
                        map.put("Newrelationname", "--");
                    }
                    if (rs.getString(24) != null && !rs.getString(24).equalsIgnoreCase("")) {
                        map.put("Newdob", rs.getString(24));
                    } else {
                        map.put("Newdob", "--");
                    }
                    if (rs.getString(25) != null && !rs.getString(25).equalsIgnoreCase("")) {
                        map.put("Newmole1", rs.getString(25));
                    } else {
                        map.put("Newmole1", "--");
                    }
                    if (rs.getString(26) != null && !rs.getString(26).equalsIgnoreCase("")) {
                        map.put("Newmole2", rs.getString(26));
                    } else {
                        map.put("Newmole2", "--");
                    }
                    if (rs.getString(27) != null && !rs.getString(27).equalsIgnoreCase("")) {
                        map.put("Proofdoc_Type", rs.getString(27));
                    } else {
                        map.put("Proofdoc_Type", "-");
                    }
                    if (rs.getString(28) != null) {//&& rs.getString(27).equalsIgnoreCase("Adhaar Card")) {
                        map.put("Proof_Id", rs.getString(28));
                    } else {
                        map.put("Proof_Id", "-");
                    }
                    map.put("ProofDoc_FileName", rs.getString(29));
                    if (rs.getString(30) != null && !rs.getString(30).equalsIgnoreCase("")) {
                        map.put("Mole_One", rs.getString(30));
                    } else {
                        map.put("Mole_One", "--");
                    }
                    if (rs.getString(31) != null && !rs.getString(31).equalsIgnoreCase("")) {
                        map.put("Mole_Two", rs.getString(31));
                    } else {
                        map.put("Mole_Two", "--");
                    }
                    if (rs.getString(32) != null && !rs.getString(32).equalsIgnoreCase("")) {
                        map.put("RelationType", rs.getString(32));
                    } else {
                        map.put("RelationType", "--");
                    }

                    map.put("DistrictId", rs.getString(33));
                    map.put("MandalId", rs.getString(34));
                    map.put("VillageId", rs.getString(35));
                    map.put("PanchayatId", rs.getString(36));
                    map.put("HabitationId", rs.getString(37));

                    map.put("requestName", getRequestName(rs.getString(3), rs.getString(4), con));

                    if (rs.getString(38) != null) {
                        map.put("uploadFlag", rs.getString(38));

                    } else {
                        map.put("uploadFlag", "--");

                    }
                    if (rs.getString(39) != null) {
                        map.put("proofId", rs.getString(39));

                    } else {
                        map.put("proofId", "--");

                    }
                    if (rs.getString(40) != null) {
                        map.put("proofdocType", rs.getString(40));

                    } else {
                        map.put("proofdocType", "--");

                    }

                    approvalList.add(map);
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDPMApprovalList", "GrievancesRequestDetailsDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsDAO", "getDPMApprovalList");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDPMApprovalList", "GrievancesRequestDetailsDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsDAO", "getDPMApprovalList");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }
        return approvalList;
    }

    public String getRequestName(String requestId, String subRequestId, Connection conn) throws SADAREMDBException, SQLException {

        String requestname = "";
        String sql = null;
        String subRequestIds[];
        ResultSet rs1 = null;
        try {

            subRequestIds = subRequestId.split(",");


            st = conn.createStatement();
            for (int i = 0; i < subRequestIds.length; i++) {
                sql = "select RequestName from dbo.tblRequestMaster where status='Active' and RequestId='" + requestId + "' and SubRequestId='" + subRequestIds[i] + "'";
                rs1 = st.executeQuery(sql);
                if (rs1 != null) {
                    while (rs1.next()) {
                        requestname = requestname + rs1.getString(1) + ", ";
                    }
                }

            }
            if (requestname != "") {
                requestname = requestname.substring(0, requestname.length() - 2);
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            // DBConnection.closeConnection(con);
            //DBConnection.closeResultSet(rs1);
            // DBConnection.closeStatement(st);
        }
        return requestname;
    }

    public ArrayList getAllRequestedDetails(DataSource ds, GrievancesRequestDetailsForm grievancesRequestDetailsForm, String districtid, int currentRoleId, HttpServletRequest request, String mandalId) throws SADAREMDBException, SQLException {
        ArrayList RequestDetails = new ArrayList();
        String requestIds = grievancesRequestDetailsForm.getRequestId();
        StringBuffer sb = new StringBuffer();
        String splitReq[];
        try {
            con = DBConnection.getConnection();
            st = con.createStatement();
            String beneficiaryProblemId = "0";

            if (request.getParameter("beneficiaryId") != null) {
                beneficiaryProblemId = request.getParameter("beneficiaryId");

            }
            sb.append("SELECT s.Beneficiary_Problem_ID,s.person_code,s.RequestId,s.SubRequestId,"
                    + "s.Sur_Name,s.First_Name,s.Gender,s.Date_Of_Birth,s.Age,s.Relation_Name,s.House_No,"
                    + "s.Cell_Number,s.LandLine_Number,d.District_Name,b.Mandal_Name,"
                    + "p.Panchayat_Name,e.Village_Name,f.Habitation_Name,s.Status,s.Nature_Of_Beneficiary,s.Newsurname,"
                    + "s.Newname,s.Newrelationname,s.Newdob,s.Newmole1,s.Newmole2,s.Proofdoc_Type,s.Proof_Id,"
                    + "s.ProofDoc_FileName,s.Mole_One,s.Mole_Two,s.RelationType"
                    + " FROM tblsadaremGreivances_Details s "
                    + " join tblDistrict_Details d on(s.district_id =d.district_id) "
                    + " join tblmandal_Details b on (s.district_id =b.district_id) and (s.mandal_id =b.mandal_id) "
                    + " join tblPanchayat_Details p on (s.district_id =p.district_id) and (s.mandal_id =p.mandal_id) and (s.Panchayat_ID =p.Panchayat_ID) "
                    + " join tblVillage_Details e on (s.district_id =e.district_id) and (s.mandal_id =e.mandal_id) and (s.village_id =e.village_id) "
                    + " join tblHabitation_Details f on ((s.district_id =f.district_id) and (s.mandal_id =f.mandal_id) and (s.village_id =f.village_id) and (s.Panchayat_ID =f.Panchayat_ID) "
                    + "and (s.habitation_id =f.Habitation_id)  and s.habcode =f.Habitation_code)"
                    + " where flag='Active'  and s.flag='Active' and s.district_id=" + districtid);
            if (currentRoleId == 24) {
                sb.append(" and ((s.status='Documents Verified And Ok' and proofdoc_type<>'Adhaar Card') or s.status='MPDO Pending') and s.RequestId=5 and s.mandal_id='" + mandalId + "' ");
            } else {
                sb.append(" and s.status='Documents Verified And Ok' and s.RequestId<>5 ");
            }
            if (grievancesRequestDetailsForm.getRequestId() != null && !grievancesRequestDetailsForm.getRequestId().equalsIgnoreCase("ALL")) {
                splitReq = grievancesRequestDetailsForm.getRequestId().split("&");
                sb.append(" and s.requestid='" + splitReq[0] + "' and s.SubRequestId like ('%" + splitReq[1] + "%')");

            }
            if (!beneficiaryProblemId.equals("0")) {
                sb.append(" and s.Beneficiary_Problem_ID='" + beneficiaryProblemId + "'");
            }
            sb = sb.append(" order by s.Beneficiary_Problem_ID asc");
            
            System.out.println("getAllRequestedDetails Query : \n"+sb.toString());
            
            
            rs = st.executeQuery(sb.toString());
            if (rs != null) {
                while (rs.next()) {
                    map = new HashMap();

                    map.put("BeneficiaryProblemId", rs.getString(1));
                    if (rs.getString(2) != null && !rs.getString(2).equalsIgnoreCase("")) {
                        map.put("SadaremId", rs.getString(2));
                    } else {
                        map.put("SadaremId", "--");
                    }
                    if (rs.getString(3) != null && !rs.getString(3).equalsIgnoreCase("")) {
                        map.put("RequestId", rs.getString(3));
                    } else {
                        map.put("RequestId", "--");
                    }
                    if (rs.getString(4) != null && !rs.getString(4).equalsIgnoreCase("")) {
                        map.put("SubRequestId", rs.getString(4));
                    } else {
                        map.put("SubRequestId", "--");
                    }
                    if (rs.getString(5) != null && !rs.getString(5).equalsIgnoreCase("")) {
                        map.put("Sur_Name", rs.getString(5));
                    } else {
                        map.put("Sur_Name", "--");
                    }
                    if (rs.getString(6) != null && !rs.getString(6).equalsIgnoreCase("")) {
                        map.put("First_Name", rs.getString(6));
                    } else {
                        map.put("First_Name", "--");
                    }

                    if (rs.getString(5) != null && !rs.getString(5).equalsIgnoreCase("") && !rs.getString(5).equalsIgnoreCase("-")) {
                        map.put("Name", rs.getString(5) + " " + rs.getString(6));
                    } else {
                        if (rs.getString(6) != null && !rs.getString(6).equalsIgnoreCase("")) {
                            map.put("Name", rs.getString(6));
                        } else {
                            map.put("Name", "--");
                        }
                    }
                    if (rs.getString(7) != null && !rs.getString(7).equalsIgnoreCase("")) {
                        map.put("Gender", rs.getString(7));
                    } else {
                        map.put("Gender", "--");
                    }
                    if (rs.getString(8) != null && !rs.getString(8).equalsIgnoreCase("")) {
                        map.put("Dob", rs.getString(8));
                    } else {
                        map.put("Dob", "--");
                    }
                    if (rs.getString(9) != null && !rs.getString(9).equalsIgnoreCase("")) {
                        map.put("Age", rs.getString(9));
                    } else {
                        map.put("Age", "--");
                    }
                    if (rs.getString(10) != null && !rs.getString(10).equalsIgnoreCase("")) {
                        map.put("RelationName", rs.getString(10));
                    } else {
                        map.put("RelationName", "--");
                    }
                    if (rs.getString(11) != null && !rs.getString(11).equalsIgnoreCase("")) {
                        map.put("Address", rs.getString(11));
                    } else {
                        map.put("Address", "--");
                    }
                    if (rs.getString(12) != null && !rs.getString(12).equalsIgnoreCase("")) {
                        map.put("PhoneNumber", rs.getString(12));
                    } else {
                        map.put("PhoneNumber", "--");
                    }
                    if (rs.getString(13) != null && !rs.getString(13).equalsIgnoreCase("")) {
                        map.put("LandNumber", rs.getString(13));
                    } else {
                        map.put("LandNumber", "--");
                    }
                    if (rs.getString(14) != null && !rs.getString(14).equalsIgnoreCase("")) {
                        map.put("District", rs.getString(14));
                    } else {
                        map.put("District", "--");
                    }
                    if (rs.getString(15) != null && !rs.getString(15).equalsIgnoreCase("")) {
                        map.put("Mandal", rs.getString(15));
                    } else {
                        map.put("Mandal", "--");
                    }
                    if (rs.getString(16) != null && !rs.getString(16).equalsIgnoreCase("")) {
                        map.put("Panchayat", rs.getString(16));
                    } else {
                        map.put("Panchayat", "--");
                    }
                    if (rs.getString(17) != null && !rs.getString(17).equalsIgnoreCase("")) {
                        map.put("Village", rs.getString(17));
                    } else {
                        map.put("Village", "--");
                    }
                    if (rs.getString(18) != null && !rs.getString(18).equalsIgnoreCase("")) {
                        map.put("Habitation", rs.getString(18));
                    } else {
                        map.put("Habitation", "--");
                    }
                    if (rs.getString(19) != null && !rs.getString(19).equalsIgnoreCase("")) {
                        map.put("Status", rs.getString(19));
                    } else {
                        map.put("Status", "--");
                    }
                    if (rs.getString(20) != null && !rs.getString(20).equalsIgnoreCase("")) {
                        map.put("Nature_Of_Beneficiary", rs.getString(20));
                    } else {
                        map.put("Nature_Of_Beneficiary", "--");
                    }
                    if (rs.getString(21) != null && !rs.getString(21).equalsIgnoreCase("")) {
                        map.put("Newsurname", rs.getString(21));
                    } else {
                        map.put("Newsurname", "--");
                    }
                    if (rs.getString(22) != null && !rs.getString(22).equalsIgnoreCase("")) {
                        map.put("Newname", rs.getString(22));
                    } else {
                        map.put("Newname", "--");
                    }
                    if (rs.getString(23) != null && !rs.getString(23).equalsIgnoreCase("")) {
                        map.put("Newrelationname", rs.getString(23));
                    } else {
                        map.put("Newrelationname", "--");
                    }
                    if (rs.getString(24) != null && !rs.getString(24).equalsIgnoreCase("")) {
                        map.put("Newdob", rs.getString(24));
                    } else {
                        map.put("Newdob", "--");
                    }
                    if (rs.getString(25) != null && !rs.getString(25).equalsIgnoreCase("")) {
                        map.put("Newmole1", rs.getString(25));
                    } else {
                        map.put("Newmole1", "--");
                    }
                    if (rs.getString(26) != null && !rs.getString(26).equalsIgnoreCase("")) {
                        map.put("Newmole2", rs.getString(26));
                    } else {
                        map.put("Newmole2", "--");
                    }
                    if (rs.getString(27) != null && !rs.getString(27).equalsIgnoreCase("")) {
                        map.put("Proofdoc_Type", rs.getString(27));
                    } else {
                        map.put("Proofdoc_Type", "-");
                    }
                    if (rs.getString(28) != null) {//&& rs.getString(27).equalsIgnoreCase("Adhaar Card")) {
                        map.put("Proof_Id", rs.getString(28));
                    } else {
                        map.put("Proof_Id", "-");
                    }
                    map.put("ProofDoc_FileName", rs.getString(29));
                    if (rs.getString(30) != null && !rs.getString(30).equalsIgnoreCase("")) {
                        map.put("Mole_One", rs.getString(30));
                    } else {
                        map.put("Mole_One", "--");
                    }
                    if (rs.getString(31) != null && !rs.getString(31).equalsIgnoreCase("")) {
                        map.put("Mole_Two", rs.getString(31));
                    } else {
                        map.put("Mole_Two", "--");
                    }
                    if (rs.getString(32) != null && !rs.getString(32).equalsIgnoreCase("")) {
                        map.put("RelationType", rs.getString(32));
                    } else {
                        map.put("RelationType", "--");
                    }

                    map.put("requestName", getRequestName(rs.getString(3), rs.getString(4), con));

                    RequestDetails.add(map);
                }
            }
        } catch (SQLException sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getAllRequestedDetails", "GrievancesRequestDetailsDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsDAO", "getAllRequestedDetails");
        } catch (Exception sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getAllRequestedDetails", "GrievancesRequestDetailsDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsDAO", "getAllRequestedDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(st);

        }

        return RequestDetails;
    }

    public int approveOrRejectRequests(DataSource ds, GrievancesRequestDetailsForm grievancesRequestDetailsForm, int currentRoleId, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int flag = 0;
        HttpSession session = request.getSession();
        String loginid = (String) session.getAttribute("useridData");
        try {
            con = DBConnection.getConnection();
            st = con.createStatement();
            StringBuffer sb = new StringBuffer();
            String status = "";
            String rdStatus = null;
            if (grievancesRequestDetailsForm.getButton() != null) {


                sb.append("update tblsadaremGreivances_Details set updated_date=getDate(),login_id='" + loginid + "',System_IP_Address='" + request.getRemoteAddr() + "',status='");
                if (currentRoleId == 24) {
                    if (grievancesRequestDetailsForm.getButton().equalsIgnoreCase("Approved")) {
                        String loginCheck = loginid.substring(0, 4);
                        if (loginCheck.equalsIgnoreCase(CommonConstants.MPLCMLOGIN)) {
                            status = "MPLCM Approved";
                            rdStatus = "APDCLOSE";
                        } else {
                            status = "MPDO Approved";
                            rdStatus = "APDCLOSE";
                        }
                    } else {
                        status = "MPDO Rejected";
                        rdStatus = "APDREJECT";
                    }

                } else {
                    if (grievancesRequestDetailsForm.getButton().equalsIgnoreCase("Approved")) {
                        String loginCheck = loginid.substring(0, 4);
                        if (loginCheck.equalsIgnoreCase(CommonConstants.MPLCMLOGIN)) {
                            status = "MPLCM Approved";
                            rdStatus = "APDCLOSE";
                        } else {
                            status = "PD Approved";
                            rdStatus = "PDCLOSE";
                        }
                    } else {
                        status = "PD Rejected";
                        rdStatus = "PDREJECT";
                    }

                }
                if (grievancesRequestDetailsForm.getBenificiaryProblemId() != null) {
                    sb.append(status + "' where Beneficiary_Problem_ID='" + grievancesRequestDetailsForm.getBenificiaryProblemId() + "'");
                    String login = "";
                    login = loginid.substring(0, 4);
                    if (login != "" && (login.equalsIgnoreCase(CommonConstants.MPDOLOGIN)
                            || login.equalsIgnoreCase(CommonConstants.DRDALOGIN)
                            || login.equalsIgnoreCase(CommonConstants.MPLCMLOGIN))) {
                        flag = st.executeUpdate(sb.toString());
                    }
                    if (flag == 1) {
                        pstmt = con.prepareStatement("INSERT INTO tblsadaremGreivances_Tracking_Details "
                                + "(Beneficiary_Problem_ID,Status,Flag,Login_ID,System_IP_Address,Created_Date,Updated_Date,Remarks,web_status,rd_Status)"
                                + " VALUES "
                                + "(?,?,'Active',?,?,getDate(),getDate(),?,?,?)");

                        pstmt.setString(1, grievancesRequestDetailsForm.getBenificiaryProblemId());
                        pstmt.setString(2, status);
                        pstmt.setString(3, loginid);
                        pstmt.setString(4, request.getRemoteAddr());
                        pstmt.setString(5, grievancesRequestDetailsForm.getRemarks());
                        pstmt.setString(6, null);
                        if (rdStatus != null) {
                            pstmt.setString(7, rdStatus);
                        } else {
                            pstmt.setString(7, null);
                        }
                        pstmt.executeUpdate();

                    }
                }
//                 else {
//                    String[] s = grievancesRequestDetailsForm.getBeneficiaryIds();
//                    sql = sb.toString();
//                    for (int i = 0; i < s.length; i++) {
//                        if (!s[i].equals("on")) {
//                            flag = st.executeUpdate(sql + status + "' where Beneficiary_Problem_ID='" + s[i] + "'");
//
//                            if (flag == 1) {
//                                pstmt = con.prepareStatement("INSERT INTO tblsadaremGreivances_Tracking_Details "
//                                        + "(Beneficiary_Problem_ID,Status,Flag,Login_ID,System_IP_Address,Created_Date,Updated_Date,Remarks,rd_Status)"
//                                        + " VALUES "
//                                        + "(?,?,'Active',?,?,getDate(),getDate(),?,?)");
//
//                                pstmt.setString(1, s[i]);
//                                pstmt.setString(2, status);
//                                pstmt.setString(3, loginid);
//                                pstmt.setString(4, request.getRemoteAddr());
//                                pstmt.setString(5, grievancesRequestDetailsForm.getRemarks());
//                                if (status.equals("PD Approved") || status.equals("MPDO Approved")) {
//                                    pstmt.setString(6, "APDClose");
//                                } else if (status.equals("MPDO Rejected") || status.equals("PD Rejected")) {
//                                    pstmt.setString(6, "APDREJECT");
//                                } else {
//                                    pstmt.setString(6, null);
//                                }
//
//                                pstmt.executeUpdate();
//
//                            }
//
//                        }
//                    }
//                }
            }
        } catch (SQLException sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "approveOrRejectRequests", "GrievancesRequestDetailsDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsDAO", "approveOrRejectRequests");
        } catch (Exception sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "approveOrRejectRequests", "GrievancesRequestDetailsDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsDAO", "approveOrRejectRequests");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(st);
            DBConnection.closeStatement(pstmt);

        }
        return flag;
    }

    public int approveOrRejectDPMRequests(DataSource ds, GrievancesRequestDetailsForm grievancesRequestDetailsForm, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int flag = 0;
        String status = "";
        HttpSession session = request.getSession();
        String loginid = (String) session.getAttribute("useridData");
        String date = null;
        String rdStatus = null;
        String flagData = "1";
        try {
            con = DBConnection.getConnection();
            st = con.createStatement();
            StringBuffer sb = new StringBuffer();
            if (grievancesRequestDetailsForm != null) {

                if (grievancesRequestDetailsForm.getButton() != null) {

                    if (grievancesRequestDetailsForm.getButton().equalsIgnoreCase("Approved")) {
                        if (grievancesRequestDetailsForm.getStatus() != null) {
                            if (grievancesRequestDetailsForm.getStatus().equalsIgnoreCase("Application Registered")) {
                                if (grievancesRequestDetailsForm.getNatureOfBeneficiary() != null
                                        && grievancesRequestDetailsForm.getNatureOfBeneficiary().equalsIgnoreCase("Voice")
                                        && grievancesRequestDetailsForm.getUploadFlag().equalsIgnoreCase("--")) {

                                    status = "Documents Uploaded";
                                } else {
                                    status = "Documents Verified And Ok";
                                    if (grievancesRequestDetailsForm.getProofAadharType() != null
                                            && grievancesRequestDetailsForm.getProofAadharType().equalsIgnoreCase("Adhaar Card")
                                            && grievancesRequestDetailsForm.getRequestId() != null
                                            && grievancesRequestDetailsForm.getRequestId().equalsIgnoreCase("5")) {
                                        rdStatus = "APDCLOSE";

                                    } else if (grievancesRequestDetailsForm.getRequestId().equalsIgnoreCase("5")) {

                                        rdStatus = "MPDOPENDING";

                                    } else {
                                        rdStatus = "PDAPPROVE";
                                    }
                                }
                                //===
                            } else if (grievancesRequestDetailsForm.getStatus().equalsIgnoreCase("Documents Uploaded")) {

                                status = "Documents Verified And Ok";
                                if (grievancesRequestDetailsForm.getProofAadharType() != null
                                        && grievancesRequestDetailsForm.getProofAadharType().equalsIgnoreCase("Adhaar Card")
                                        && grievancesRequestDetailsForm.getRequestId() != null
                                        && grievancesRequestDetailsForm.getRequestId().equalsIgnoreCase("5")) {

                                    rdStatus = "APDCLOSE";

                                } else if (grievancesRequestDetailsForm.getRequestId().equalsIgnoreCase("5")) {

                                    rdStatus = "MPDOPENDING";

                                } else {

                                    rdStatus = "PDAPPROVE";
                                }


                            } else if ((grievancesRequestDetailsForm.getStatus().equalsIgnoreCase("PD Approved")
                                    || grievancesRequestDetailsForm.getStatus().equalsIgnoreCase("MPDO Approved"))
                                    || (grievancesRequestDetailsForm.getStatus().equalsIgnoreCase("Documents Verified And Ok")
                                    && grievancesRequestDetailsForm.getProofType() != null
                                    //&& grievancesRequestDetailsForm.getProofAadharCardType().equalsIgnoreCase("Adhaar Card")
                                    && grievancesRequestDetailsForm.getRequestId() != null
                                    && grievancesRequestDetailsForm.getRequestId().equalsIgnoreCase("5"))) {


                                status = "Scheduled Camp";
                            } else if (grievancesRequestDetailsForm.getStatus().equalsIgnoreCase("Scheduled Camp")) {
                                status = "Certificate Issued";

                            }
                        }

                    } else {
                        if (grievancesRequestDetailsForm.getStatus() != null) {
                            if (grievancesRequestDetailsForm.getStatus().equalsIgnoreCase("Application Registered")) {
                                rdStatus = "APDREJECT";
                                if (grievancesRequestDetailsForm.getNatureOfBeneficiary() != null
                                        && grievancesRequestDetailsForm.getNatureOfBeneficiary().equalsIgnoreCase("Voice")) {
                                    status = "Documents Uploaded Rejected";
                                } else {
                                    status = "Documents Verified And Ok Rejected";
                                }
                            } else if (grievancesRequestDetailsForm.getStatus().equalsIgnoreCase("Documents Uploaded")) {
                                status = "Documents Verified And Ok Rejected";
                                rdStatus = "APDREJECT";
                            } else if ((grievancesRequestDetailsForm.getStatus().equalsIgnoreCase("PD Approved")
                                    || grievancesRequestDetailsForm.getStatus().equalsIgnoreCase("MPDO Approved"))
                                    || (grievancesRequestDetailsForm.getStatus().equalsIgnoreCase("Documents Verified And Ok")
                                    && grievancesRequestDetailsForm.getProofType() != null
                                    && grievancesRequestDetailsForm.getProofType().equalsIgnoreCase("Adhaar Card")
                                    && grievancesRequestDetailsForm.getRequestId() != null
                                    && grievancesRequestDetailsForm.getRequestId().equalsIgnoreCase("5"))) {
                                status = "Scheduled Camp Rejected";
                            } else if (grievancesRequestDetailsForm.getStatus().equalsIgnoreCase("Scheduled Camp")) {
                                status = "Certificate Issued Rejected";
                            }
                        }
                    }
                }
            }
            sb.append("update tblsadaremGreivances_Details set status='" + status + "',updated_date=getDate(),login_id='" + loginid + "',System_IP_Address='" + request.getRemoteAddr() + "'");

            if (grievancesRequestDetailsForm.getProofId() != null && !grievancesRequestDetailsForm.getProofId().equalsIgnoreCase("")) {
                sb.append(",Proof_Id='" + grievancesRequestDetailsForm.getProofId() + "'");

            }
            if (grievancesRequestDetailsForm.getProofType() != null && !grievancesRequestDetailsForm.getProofType().equalsIgnoreCase("")) {
                sb.append(",Proofdoc_Type='" + grievancesRequestDetailsForm.getProofType() + "'");

            }
            if (grievancesRequestDetailsForm.getUploadDocument() != null) {
                sb.append(",ProofDoc_FileName='" + grievancesRequestDetailsForm.getBenificiaryProblemId() + ".jpg'");

            }
            if (grievancesRequestDetailsForm.getSchDate() != null) {

                Date campdate = new SimpleDateFormat("dd/mm/yyyy").parse(grievancesRequestDetailsForm.getSchDate());
                date = new SimpleDateFormat("mm/dd/yyyy").format(campdate);
                sb.append(",schedule_camp_date='" + date + "'");

            }
            if (grievancesRequestDetailsForm.getBenificiaryProblemId() != null) {
                sb.append(" where Beneficiary_Problem_ID='" + grievancesRequestDetailsForm.getBenificiaryProblemId() + "'");
                String login = "";
                login = loginid.substring(0, 3);
                if (login != "" && login.equalsIgnoreCase("dpm")) {
                    flag = st.executeUpdate(sb.toString());
                }
            }


            if (flag == 1) {
                pstmt = con.prepareStatement("INSERT INTO tblsadaremGreivances_Tracking_Details "
                        + "(Beneficiary_Problem_ID,Status,Flag,Login_ID,System_IP_Address,Created_Date,Updated_Date,Remarks,web_status,rd_Status)"
                        + " VALUES "
                        + "(?,?,'Active',?,?,getDate(),getDate(),?,?,?)");

                pstmt.setString(1, grievancesRequestDetailsForm.getBenificiaryProblemId());
                pstmt.setString(2, status);
                pstmt.setString(3, loginid);
                pstmt.setString(4, request.getRemoteAddr());
                pstmt.setString(5, grievancesRequestDetailsForm.getRemarks());
                pstmt.setString(6, null);
                if (rdStatus != null && !grievancesRequestDetailsForm.getBenificiaryProblemId().startsWith("S")) {
                    pstmt.setString(7, rdStatus);
                } else {
                    pstmt.setString(7, null);
                }
                pstmt.executeUpdate();

            }
//            else {
//                String[] s = grievancesRequestDetailsForm.getBeneficiaryIds();
//                sql = sb.toString();
//                for (int i = 0; i < s.length; i++) {
//                    if (!s[i].equals("on")) {
//                        st.executeUpdate(sql + " where Beneficiary_Problem_ID='" + s[i] + "'");
//                    }
//                }
//            }

        } catch (SQLException sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "approveOrRejectDPMRequests", "GrievancesRequestDetailsDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsDAO", "approveOrRejectDPMRequests");
        } catch (Exception sqlEx) {
            //sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "approveOrRejectDPMRequests", "GrievancesRequestDetailsDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsDAO", "approveOrRejectDPMRequests");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(st);
            DBConnection.closeStatement(pstmt);

        }
        return flag;
    }

    public int getValidForNewCertificate(DataSource ds, String BenificiaryProblemId) throws SADAREMDBException, SQLException {
        int flag = 0;
        String query = null;
        try {
            con = DBConnection.getConnection();
            st = con.createStatement();


            if (BenificiaryProblemId != null) {
                query = "select count(*) from tblperson_personal_details p  with (nolock) ,(select proofdoc_type,proof_id,created_date"
                        + "  from tblsadaremGreivances_Details s where Beneficiary_Problem_ID='" + BenificiaryProblemId + "' )a  "
                        + " where replace(p.proof_id,' ','')=replace(a.proof_id,' ','') and a.proofdoc_type=p.proofdoc_type";


                rs = st.executeQuery(query);
                if (rs != null) {
                    while (rs.next()) {
                        flag = rs.getInt(1);
                    }

                }
                if (flag == 0) {
                    query = " select count(*) from tblsadaremGreivances_Details s, (select proofdoc_type,proof_id,created_date"
                            + "  from tblsadaremGreivances_Details s where Beneficiary_Problem_ID='" + BenificiaryProblemId + "' )a"
                            + "  where a.proofdoc_type=s.proofdoc_type and replace(a.proof_id,' ','')=replace(s.proof_id,' ','') and a.created_date>s.created_date and s.status not like '%Rejected%' and status not in ('') "
                            + " and s.requestid='5'";

                    rs = st.executeQuery(query);
                    if (rs != null) {
                        while (rs.next()) {
                            flag = rs.getInt(1);
                        }

                    }
                }
            }

        } catch (SQLException sqlEx) {
            // sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getValidForNewCertificate", "GrievancesRequestDetailsDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsDAO", "getValidForNewCertificate");
        } catch (Exception sqlEx) {
            //sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getValidForNewCertificate", "GrievancesRequestDetailsDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsDAO", "getValidForNewCertificate");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(st);

        }
        return flag;
    }

    public String getValidForNewCertificateMsg(DataSource ds, String BenificiaryProblemId) throws SADAREMDBException, SQLException {
        String msg = null;
        String query = null;
        try {
            con = DBConnection.getConnection();
            st = con.createStatement();


            if (BenificiaryProblemId != null) {
                query = "select p.person_code,a.proofdoc_type,a.proof_id from tblperson_personal_details p  with (nolock) ,(select proofdoc_type,proof_id,created_date"
                        + " from tblsadaremGreivances_Details  where Beneficiary_Problem_ID='" + BenificiaryProblemId + "' )a  "
                        + " where replace(p.proof_id,' ','')=replace(a.proof_id,' ','') and a.proofdoc_type=p.proofdoc_type";


                rs = st.executeQuery(query);
                if (rs != null) {
                    while (rs.next()) {
                        msg = "Already SADAREM ID " + rs.getString(1) + " is generated for the given Proof "
                                + rs.getString(2) + ":" + rs.getString(3) + ". Please reject the request";
                    }

                }
                if (msg == null) {
                    query = " select  top 1 s.Beneficiary_Problem_ID,a.proofdoc_type,a.proof_id,s.status from tblsadaremGreivances_Details s,"
                            + " (select proofdoc_type,proof_id,created_date from tblsadaremGreivances_Details s where Beneficiary_Problem_ID='" + BenificiaryProblemId + "' )a"
                            + "  where a.proofdoc_type=s.proofdoc_type and  replace(a.proof_id,' ','')= replace(s.proof_id,' ','') "
                            + "and a.created_date>s.created_date and s.status not like '%Rejected%' and status not in ('') and s.requestid='5'  order by s.created_date";

                    rs = st.executeQuery(query);
                    if (rs != null) {
                        while (rs.next()) {
                            msg = "Already New Certificate request " + rs.getString(1) + " raised for the given Proof "
                                    + rs.getString(2) + ":" + rs.getString(3) + " and current status is " + rs.getString(4) + ". Please reject the request";
                        }

                    }
                }
            }

        } catch (SQLException sqlEx) {
            // sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getValidForNewCertificateMsg", "GrievancesRequestDetailsDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsDAO", "getValidForNewCertificateMsg");
        } catch (Exception sqlEx) {
            //sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getValidForNewCertificateMsg", "GrievancesRequestDetailsDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsDAO", "getValidForNewCertificateMsg");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(st);

        }
        return msg;
    }

    public ArrayList getCountOfStatusesReport(DataSource ds, RDCallCentreStatusForm rDCallCentreStatusForm) throws SADAREMDBException, SQLException {
        ArrayList reportData = new ArrayList();
        CallableStatement cs = null;
        try {
            con = DBConnection.getConnection();
            st = con.createStatement();
            if (rDCallCentreStatusForm.getUrban_id() != null && rDCallCentreStatusForm.getUrban_id().equals("Rural")) {

                cs = con.prepareCall("{Call SP_SADAREMGRIEVANCES_STATUSWISE_REPORT_RURAL(?)}");
            } else if (rDCallCentreStatusForm.getUrban_id() != null && rDCallCentreStatusForm.getUrban_id().equals("Urban")) {

                cs = con.prepareCall("{Call SP_SADAREMGRIEVANCES_STATUSWISE_REPORT_URBAN(?)}");
            } else {

                cs = con.prepareCall("{Call SP_SADAREMGRIEVANCES_STATUSWISE_REPORT(?)}");
            }
            cs.setString(1, rDCallCentreStatusForm.getDistrict_id());
            rs = cs.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    map = new HashMap();
                    map.put("DistrictId", rs.getString(1));
                    map.put("District", rs.getString(2));
                    map.put("ApplicationRegistered", rs.getInt(3));
                    map.put("DocumentsToBeUploaded", rs.getInt(4));
                    map.put("DocumentsUploaded", rs.getInt(5));
                    map.put("DocumentsUploadedRejected", rs.getInt(17));
                    map.put("DocumentsVerifiedPending", rs.getInt(18));
                    map.put("DocumentsVerified", rs.getInt(6));
                    map.put("DocumentsVerifiedRejected", rs.getInt(19));
                    map.put("MPDOPending", rs.getInt(7));
                    map.put("MPDOApproved", rs.getInt(8));
                    map.put("MPDORejected", rs.getInt(9));
                    map.put("PDPending", rs.getInt(10));
                    map.put("PDApproved", rs.getInt(11));
                    map.put("PDRejected", rs.getInt(12));
                    map.put("ScheduleCampPending", rs.getInt(13));
                    map.put("ScheduleCampApproved", rs.getInt(14));
                    map.put("ScheduleCampRejected", rs.getInt(20));
                    map.put("certificateIssued", rs.getInt(15));
                    map.put("certificateRejected", rs.getInt(21));
                    map.put("ovrAllPending", rs.getInt(16));


                    reportData.add(map);
                }
            }

        } catch (SQLException sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCountOfStatusesReport", "GrievancesRequestDetailsDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsDAO", "getCountOfStatusesReport");
        } catch (Exception sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCountOfStatusesReport", "GrievancesRequestDetailsDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsDAO", "getCountOfStatusesReport");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(st);
            DBConnection.closeStatement(cs);

        }

        return reportData;
    }

    public ArrayList getIndividualDetailsOfStatusesReport(DataSource ds, RDCallCentreStatusForm rDCallCentreStatusForm, String status) throws SADAREMDBException, SQLException {
        ArrayList reportData = new ArrayList();
        CallableStatement cs = null;
        try {
            con = DBConnection.getConnection();
            st = con.createStatement();
            if (rDCallCentreStatusForm.getUrban_id() != null && rDCallCentreStatusForm.getUrban_id().equals("Rural")) {

                cs = con.prepareCall("{Call SP_SADAREMGRIEVANCES_STATUSWISE_INDIVIDUAL_REPORT_RURAL(?,?)}");
            } else if (rDCallCentreStatusForm.getUrban_id() != null && rDCallCentreStatusForm.getUrban_id().equals("Urban")) {

                cs = con.prepareCall("{Call SP_SADAREMGRIEVANCES_STATUSWISE_INDIVIDUAL_REPORT_URBAN(?,?)}");
            } else {

                cs = con.prepareCall("{Call SP_SADAREMGRIEVANCES_STATUSWISE_INDIVIDUAL_REPORT(?,?)}");
            }

            cs.setString(1, rDCallCentreStatusForm.getDistrict_id());
            cs.setString(2, status);
            rs = cs.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    map = new HashMap();
                    if (rs.getString(1) != null) {
                        map.put("requestNo", rs.getString(1));
                    } else {
                        map.put("requestNo", "-");
                    }
                    if (rs.getString(2) != null) {
                        map.put("sadaremId", rs.getString(2));
                    } else {
                        map.put("sadaremId", "-");
                    }
                    if (rs.getString(3) != null) {
                        map.put("pensionNo", rs.getString(3));
                    } else {
                        map.put("pensionNo", "-");
                    }
                    if (rs.getString(4) != null) {
                        map.put("surName", rs.getString(4));
                    } else {
                        map.put("surName", "-");
                    }
                    if (rs.getString(5) != null) {
                        map.put("firstName", rs.getString(5));
                    } else {
                        map.put("firstName", "-");
                    }
                    if (rs.getString(6) != null) {
                        map.put("mobileNo", rs.getString(6));
                    } else {
                        map.put("mobileNo", "-");
                    }
                    if (rs.getString(7) != null) {
                        map.put("district", rs.getString(7));
                    } else {
                        map.put("district", "-");
                    }
                    if (rs.getString(8) != null) {
                        map.put("mandal", rs.getString(8));
                    } else {
                        map.put("mandal", "-");
                    }
                    if (rs.getString(9) != null) {
                        map.put("raisedDate", rs.getString(9));
                    } else {
                        map.put("raisedDate", "-");
                    }
                    if (rs.getString(10) != null) {
                        map.put("scheduledCampdate", rs.getString(10));
                    } else {
                        map.put("scheduledCampdate", "-");
                    }
                    if (rs.getString(11) != null) {
                        map.put("generatedSadaremId", rs.getString(11));
                    } else {
                        map.put("generatedSadaremId", "-");
                    }
                    map.put("requestName", getRequestName(rs.getString(12), rs.getString(13), con));
                    reportData.add(map);
                }
            }

        } catch (SQLException sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getIndividualDetailsOfStatusesReport", "GrievancesRequestDetailsDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsDAO", "getIndividualDetailsOfStatusesReport");
        } catch (Exception sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getIndividualDetailsOfStatusesReport", "GrievancesRequestDetailsDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsDAO", "getIndividualDetailsOfStatusesReport");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(st);
            DBConnection.closeStatement(cs);

        }

        return reportData;
    }

    public ArrayList getMonthWiseCallCentreReport(DataSource ds, RDCallCentreStatusForm rDCallCentreStatusForm) throws SADAREMDBException, SQLException {
        ArrayList reportData = new ArrayList();
        StringBuffer sb = new StringBuffer();
        CallableStatement cs = null;
        try {
            con = DBConnection.getConnection();
            st = con.createStatement();
            if (rDCallCentreStatusForm.getMonth() != null) {
                if (rDCallCentreStatusForm.getUrban_id() != null && rDCallCentreStatusForm.getUrban_id().equals("Rural")) {
                    cs = con.prepareCall("{Call SP_SADAREMGRIEVANCES_MONTHWISE_REPORT_rural(?,?,?)}");
                } else if (rDCallCentreStatusForm.getUrban_id() != null && rDCallCentreStatusForm.getUrban_id().equals("Urban")) {
                    cs = con.prepareCall("{Call SP_SADAREMGRIEVANCES_MONTHWISE_REPORT_URBAN(?,?,?)}");
                } else {
                    cs = con.prepareCall("{Call SP_SADAREMGRIEVANCES_MONTHWISE_REPORT(?,?,?)}");
                }

                if (!rDCallCentreStatusForm.getMonth().equals("ALL") && !rDCallCentreStatusForm.getYear().equals("ALL")) {
                    cs.setString(1, rDCallCentreStatusForm.getDistrict_id());
                    cs.setInt(2, Integer.parseInt(rDCallCentreStatusForm.getMonth()));
                    cs.setInt(3, Integer.parseInt(rDCallCentreStatusForm.getYear()));
                }

                rs = cs.executeQuery();

                if (rs != null) {
                    while (rs.next()) {
                        map = new HashMap();
                        map.put("DistrictId", rs.getInt(1));
                        map.put("District", rs.getString(2));
                        map.put("registerdBeforethismonth", rs.getInt(3));
                        map.put("registerdthismonth", rs.getInt(4));
                        map.put("resolvdthismonth", rs.getInt(5));
                        map.put("pending", rs.getInt(6));
                        reportData.add(map);
                    }
                }
            } else {

                if (rDCallCentreStatusForm.getUrban_id() != null && rDCallCentreStatusForm.getUrban_id().equals("Rural")) {
                    cs = con.prepareCall("{Call SP_SADAREMGRIEVANCES_REQUESTTYPEWISE_REPORT_RURAL(?)}");
                } else if (rDCallCentreStatusForm.getUrban_id() != null && rDCallCentreStatusForm.getUrban_id().equals("Urban")) {
                    cs = con.prepareCall("{Call SP_SADAREMGRIEVANCES_REQUESTTYPEWISE_REPORT_URBAN(?)}");
                } else {
                    cs = con.prepareCall("{Call SP_SADAREMGRIEVANCES_REQUESTTYPEWISE_REPORT(?)}");
                }
                cs.setString(1, rDCallCentreStatusForm.getDistrict_id());
                rs = cs.executeQuery();

                if (rs != null) {
                    while (rs.next()) {
                        map = new HashMap();
                        if (rDCallCentreStatusForm.getDistrict_id().equals("ALL")) {
                            map.put("DistrictId", rs.getInt(1));
                            map.put("District", rs.getString(2));
                            map.put("AssessPendingNewComp", rs.getInt(3));
                            map.put("DOBPendingNewComp", rs.getInt(4));
                            map.put("DuplPendingNewComp", rs.getInt(5));
                            map.put("IDPendingNewComp", rs.getInt(6));
                            map.put("NamePendingNewComp", rs.getInt(7));
                            map.put("NCPendingNewComp", rs.getInt(8));
                            map.put("PhysiPendingNewComp", rs.getInt(9));
                            map.put("RessessPendingNewComp", rs.getInt(10));
                            map.put("RenewalPendingNewComp", rs.getInt(11));
                            map.put("RelationNamePendingNewComp", rs.getInt(12));
                        } else {
                            map.put("requestId", rs.getInt(1));
                            map.put("subRequestId", rs.getInt(2));
                            map.put("RequestName", rs.getString(3));
                            map.put("Pending", rs.getInt(4));
                        }
                        reportData.add(map);
                    }
                }
            }
        } catch (SQLException sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMonthWiseCallCentreReport", "GrievancesRequestDetailsDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsDAO", "getMonthWiseCallCentreReport");
        } catch (Exception sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMonthWiseCallCentreReport", "GrievancesRequestDetailsDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsDAO", "getMonthWiseCallCentreReport");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(st);
            DBConnection.closeStatement(cs);

        }
        return reportData;
    }

    public ArrayList getRequestDetailsBasedonRequestNumber(DataSource ds, GrievancesRequestDetailsForm grievancesRequestDetailsForm) throws SADAREMDBException, SQLException {
        ArrayList RequestDetails = new ArrayList();


        StringBuffer sb = new StringBuffer();
        String remarks = getLastRemarks(ds, grievancesRequestDetailsForm.getRequestNumber());
        try {
            con = DBConnection.getConnection();
            st = con.createStatement();

            sb.append("SELECT s.Beneficiary_Problem_ID,coalesce(nullif(s.RequestId,''),'-'),"
                    + "coalesce(nullif(s.SubRequestId,''),'-'),coalesce(nullif(s.Sur_Name,''),'-'),"
                    + "coalesce(nullif(s.First_Name,''),'-'),coalesce(nullif(s.Relation_Name,''),'-'),"
                    + "coalesce(nullif(s.House_No,''),'-'),s.district_id,"
                    + "e.Village_Name,b.Mandal_Name,d.District_Name,coalesce(nullif(s.Cell_Number,''),'-'),"
                    + "coalesce(nullif(s.Status,''),'-'),coalesce(nullif(s.Nature_Of_Beneficiary,''),'-'),"
                    + "coalesce(nullif(s.Generated_SADAREMID,''),'-'),coalesce(nullif(s.Proofdoc_Type,''),'-')  from dbo.tblsadaremGreivances_Details"
                    + " s join tblDistrict_Details d on(s.district_id =d.district_id) "
                    + " join tblmandal_Details b on (s.district_id =b.district_id) and "
                    + "(s.mandal_id =b.mandal_id) join tblPanchayat_Details p on (s.district_id =p.district_id)"
                    + " and (s.mandal_id =p.mandal_id) and (s.Panchayat_ID =p.Panchayat_ID) "
                    + "join tblVillage_Details e on (s.district_id =e.district_id) and "
                    + "(s.mandal_id =e.mandal_id) and (s.village_id =e.village_id)where flag='Active'");


            if (grievancesRequestDetailsForm.getRequestNumber() != null && !grievancesRequestDetailsForm.getRequestNumber().equals("")) {
                sb.append(" and s.Beneficiary_Problem_ID='" + grievancesRequestDetailsForm.getRequestNumber() + "'");
            }

            rs = st.executeQuery(sb.toString());
            if (rs != null) {
                while (rs.next()) {
                    map = new HashMap();
                    map.put("requestNumber", rs.getString(1));
                    map.put("Name", rs.getString(5));
                    map.put("RelationName", rs.getString(6));
                    StringBuffer address = new StringBuffer();
                    if (!rs.getString(7).equals("-")) {
                        address.append(rs.getString(7));

                    }
                    if (!rs.getString(9).equals("-")) {
                        if (address.length() > 0) {
                            address.append(",");
                        }
                        address.append(rs.getString(9));

                    }
                    if (!rs.getString(10).equals("-")) {
                        if (address.length() > 0) {
                            address.append(",");
                        }
                        address.append(rs.getString(10));

                    }
                    if (!rs.getString(11).equals("-")) {
                        if (address.length() > 0) {
                            address.append(",");
                        }
                        address.append(rs.getString(11));

                    }
                    if (!rs.getString(12).equals("-")) {
                        if (address.length() > 0) {
                            address.append(",");
                        }
                        address.append(rs.getString(12) + ".");
                    }
                    map.put("address", address.toString());
                    String status = rs.getString(13);
                    String Proofdoc_Type = rs.getString(16);
                    if (status.equals("Application Registered") && rs.getString(14).equals("Voice")) {
                        map.put("SaStatus", "Pending for Document Upload.Please Contact District Project Manager," + rs.getString(11) + ".");
                    } else if (status.equals("Application Registered") && rs.getString(14).equals("Web")) {

                        map.put("SaStatus", "Pending for Document Verification.Please Contact District Project Manager," + rs.getString(11) + ".");
                    } else if (status.equals("Documents Uploaded")) {
                        map.put("SaStatus", "Pending for Document Verification.Please Contact District Project Manager," + rs.getString(11) + ".");
                    } else if (status.equals("Documents Verified And Ok") && rs.getInt(2) != 5) {
                        map.put("SaStatus", "Pending for PD Approval.Please Contact Project Director " + rs.getString(11) + ".");
                    } else if (status.equals("Documents Verified And Ok") && rs.getInt(2) == 5 && !Proofdoc_Type.equalsIgnoreCase("Adhaar Card")) {
                        map.put("SaStatus", "Pending for MPDO Approval.Please Contact Mandal Parishad Development Officer," + rs.getString(10) + "," + rs.getString(11) + ".");
                    } else if (status.equals("PD Approved") || status.equals("MPDO Approved")
                            || (status.equals("Documents Verified And Ok") && Proofdoc_Type.equalsIgnoreCase("Adhaar Card"))) {
                        map.put("SaStatus", "Pending for CAMP Schedule.Please Contact District Project Manager," + rs.getString(11) + ".");
                    } else if (status.equals("Scheduled Camp")) {
                        map.put("SaStatus", "Pending for Certificate Issue.Please Contact District Project Manager," + rs.getString(11) + ".");
                    } else {
                        map.put("SaStatus", status);
                    }
                    map.put("GenaratdSadaremID", rs.getString(15));
                    map.put("RequestType", getRequestName(rs.getString(2), rs.getString(3), con));
                    map.put("CategoryName", "Disability");
                    map.put("SubCategoryName", "SADAREM");
                    map.put("remarks", remarks);

                    RequestDetails.add(map);
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRequestDetailsBasedonRequestNumber", "GrievancesRequestDetailsDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsDAO", "getRequestDetailsBasedonRequestNumber");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRequestDetailsBasedonRequestNumber", "GrievancesRequestDetailsDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsDAO", "getRequestDetailsBasedonRequestNumber");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(st);

        }
        return RequestDetails;
    }

    public String getDPMRemarks(DataSource ds, GrievancesRequestDetailsForm grievancesRequestDetailsForm) throws SADAREMDBException, SQLException {
        String remarks = "";

        try {

            sql = "select Remarks from dbo.tblsadaremGreivances_Tracking_Details where Beneficiary_Problem_ID='" + grievancesRequestDetailsForm.getBenificiaryProblemId() + "' and status='Documents Verified And Ok'";
            con = DBConnection.getConnection();
            pstmt = con.prepareStatement(sql);

            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    remarks = rs.getString(1);
                }

            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDPMRemarks", "GrievancesRequestDetailsDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsDAO", "getDPMRemarks");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDPMRemarks", "GrievancesRequestDetailsDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsDAO", "getDPMRemarks");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);
        }
        return remarks;
    }

    public ArrayList getUploadProofTypeList(DataSource ds, String requestId, String subRequestId) throws SQLException, SADAREMDBException {
        ArrayList proofsList = new ArrayList();
        try {
            con = DBConnection.getConnection();
            st = con.createStatement();

            sql = "select distinct DocumentType from  tblGreivancesDocumentTypes where ApplicationId=1 "
                    + " and RequestId=" + requestId + " and SubRequestId in (" + subRequestId + ") order by DocumentType";

            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    map = new HashMap();
                    map.put("proofType", rs.getString(1));
                    proofsList.add(map);

                }
            }
        } catch (SQLException sqlEx) {
            //sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getUploadProofTypeList", "GrievancesRequestDetailsDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsDAO", "getUploadProofTypeList");
        } catch (Exception sqlEx) {
            // sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getUploadProofTypeList", "GrievancesRequestDetailsDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsDAO", "getUploadProofTypeList");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }
        return proofsList;
    }

    public ArrayList getSADAREMCampReport(DataSource ds, String districtId, String mandalId, String villageId, String panchayatId, String habitationId) throws SQLException, SADAREMDBException {
        ArrayList proofsList = new ArrayList();
        CallableStatement cs = null;
        try {
            con = DBConnection.getConnection();


            cs = con.prepareCall("{Call SP_RDCALLCENTER_SADAREMDATA(?,?,?,?)}");
            cs.setString(1, districtId);
            cs.setString(2, mandalId);
            cs.setString(3, villageId);
            cs.setString(4, habitationId);
            rs = cs.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    map = new HashMap();

                    map.put("PensionID", rs.getString(9));
                    map.put("SADAREMID", rs.getString(8));

                    if (rs.getString(1) != null) {
                        map.put("Name", rs.getString(1));
                    } else {
                        map.put("Name", "-");
                    }
                    map.put("RelationName", rs.getString(7));
                    map.put("Age", rs.getString(2));
                    map.put("Qualification", rs.getString(3));
                    map.put("DisabiltyType", rs.getString(4));
                    map.put("DisabilityPercentage", rs.getString(5));
                    map.put("ContactNumber", rs.getString(6));
                    map.put("PWDStatus", rs.getString(10));
                    map.put("AssessmentStatus", rs.getString(11));
                    map.put("PensionPhase", rs.getString(12));


                    proofsList.add(map);

                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getSADAREMCampReport", "GrievancesRequestDetailsDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsDAO", "getSADAREMCampReport");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getSADAREMCampReport", "GrievancesRequestDetailsDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsDAO", "getSADAREMCampReport");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeStatement(cs);

        }
        return proofsList;
    }

    public String getLastRemarks(DataSource ds, String beneficiary_Problem_ID) throws SADAREMDBException, SQLException {
        String remarks = "-";

        try {

            sql = "select top 1 remarks from dbo.tblsadaremGreivances_Tracking_Details where Beneficiary_Problem_ID='" + beneficiary_Problem_ID + "' and remarks is not null order by Created_Date desc";
            con = DBConnection.getConnection();
            pstmt = con.prepareStatement(sql);

            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    remarks = rs.getString(1);
                }

            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getLastRemarks", "GrievancesRequestDetailsDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsDAO", "getLastRemarks");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getLastRemarks", "GrievancesRequestDetailsDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsDAO", "getLastRemarks");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);
        }
        return remarks;
    }

    public int submitMesevaNewCertificateDetails(DataSource ds, GrievancesRequestDetailsDTO grievancesRequestDetailsDTO) throws SADAREMDBException, SQLException {
        int success = 0;
        String surName = null;
        String name = null;
        String relationName = null;

        CallableStatement calstmt = null;
        try {
            con = DBConnection.getConnection();
            st = con.createStatement();


            calstmt = con.prepareCall("{Call grievancesRDCallCenterRequest_Insert_MeeSeva(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            calstmt.setString(1, grievancesRequestDetailsDTO.getApplicationNo());
            calstmt.setString(2, grievancesRequestDetailsDTO.getSurname());
            calstmt.setString(3, grievancesRequestDetailsDTO.getFirstname());
            calstmt.setString(4, grievancesRequestDetailsDTO.getRelationName());
            calstmt.setString(5, grievancesRequestDetailsDTO.getDistrict_id());
            calstmt.setString(6, grievancesRequestDetailsDTO.getMandal_id());
            calstmt.setString(7, grievancesRequestDetailsDTO.getVillage_id());
            calstmt.setString(8, grievancesRequestDetailsDTO.getPanchayat_id());
            calstmt.setString(9, grievancesRequestDetailsDTO.getHabitation_id());
            calstmt.setString(10, grievancesRequestDetailsDTO.getHouseno());
            calstmt.setString(11, grievancesRequestDetailsDTO.getPhoneno());
            calstmt.setString(12, null);
            calstmt.setString(13, "5");
            calstmt.setString(14, "1");
            calstmt.setString(15, grievancesRequestDetailsDTO.getProofType());
            calstmt.setString(16, grievancesRequestDetailsDTO.getProofId());
            calstmt.setString(17, grievancesRequestDetailsDTO.getApplicationNo() + ".jpg");
            calstmt.setString(18, grievancesRequestDetailsDTO.getAge());
            calstmt.setString(19, "Web");
            calstmt.setString(20, grievancesRequestDetailsDTO.getSystemIp());
            calstmt.setString(21, grievancesRequestDetailsDTO.getRelationType());
            calstmt.setString(22, grievancesRequestDetailsDTO.getPensioncardno());
            calstmt.setString(23, grievancesRequestDetailsDTO.getLoginId());
            success = calstmt.executeUpdate();

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "submitMesevaNewCertificateDetails", "GrievancesRequestDetailsDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsDAO", "submitMesevaNewCertificateDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "submitMesevaNewCertificateDetails", "GrievancesRequestDetailsDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsDAO", "submitMesevaNewCertificateDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(st);
            DBConnection.closeStatement(calstmt);

        }
        return success;
    }

    /**
     *
     * @param pensionCode
     * @param applicationId
     * @param datasource
     * @return
     * @throws SADAREMDBException,SQLException
     */
    public GrievancesRequestDetailsDTO getRequestDetails(DataSource ds, String applicationNo) throws SADAREMDBException, SQLException {

        Connection con = null;
        ResultSet rs = null;
        GrievancesRequestDetailsDTO grievancesRequestDetailsDTO = null;
        PreparedStatement pstmt = null;
        try {
            con = DBConnection.getConnection();
            if (applicationNo != null && !"".equals(applicationNo)) {
                pstmt = con.prepareStatement("select sur_name, first_name, age,coalesce( house_no,'-') house_no,D.District_Name,M.Mandal_Name,p.panchayat_name,V.Village_Name,H.Habitation_Name, convert(varchar,s.created_date,103) created_date,meeseva_transactionid"
                        + " from tblsadaremGreivances_Details s,tblDistrict_Details D,tblMandal_Details M,tblpanchayat_Details p,tblVillage_Details V,tblHabitation_Details H"
                        + " where s.flag='Active' and s.Village_ID = H.Village_ID and s.District_ID = H.District_ID and s.Mandal_ID = H.Mandal_ID and s.Habitation_ID = H.Habitation_ID"
                        + " and s.District_ID = D.District_ID and s.District_ID = M.District_ID and s.Mandal_ID = M.Mandal_ID and s.District_ID = p.District_ID "
                        + " and s.Mandal_ID = p.Mandal_ID and s.panchayat_ID = p.panchayat_ID and s.Village_ID = V.Village_ID and s.District_ID = V.District_ID"
                        + " and s.Mandal_ID = V.Mandal_ID and Beneficiary_Problem_ID=?");
                pstmt.setString(1, applicationNo);

                rs = pstmt.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        grievancesRequestDetailsDTO = new GrievancesRequestDetailsDTO();
                        grievancesRequestDetailsDTO.setSurname(rs.getString("sur_name"));
                        grievancesRequestDetailsDTO.setFirstname(rs.getString("first_name"));
                        grievancesRequestDetailsDTO.setAge(rs.getString("age"));
                        grievancesRequestDetailsDTO.setHouseno(rs.getString("house_no"));
                        grievancesRequestDetailsDTO.setDistrictName(rs.getString("District_Name"));
                        grievancesRequestDetailsDTO.setMandalName(rs.getString("Mandal_Name"));
                        grievancesRequestDetailsDTO.setPanchayatName(rs.getString("panchayat_name"));
                        grievancesRequestDetailsDTO.setVillageName(rs.getString("Village_Name"));
                        grievancesRequestDetailsDTO.setHabitationName(rs.getString("Habitation_Name"));
                        grievancesRequestDetailsDTO.setCreated_date(rs.getString("created_date"));
                        grievancesRequestDetailsDTO.setMeesevaId(rs.getString("meeseva_transactionid"));

                    }
                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRequestDetails", "GrievancesRequestDetailsDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsDAO", "getRequestDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRequestDetails", "GrievancesRequestDetailsDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsDAO", "getRequestDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }
        return grievancesRequestDetailsDTO;
    }

    public ArrayList getMeesevaRequestDetails(DataSource ds, String pensionNo, String districtId) throws SADAREMDBException, SQLException {
        ArrayList RequestNames = new ArrayList();
        PreparedStatement pstmt = null;
        try {
            con = DBConnection.getConnection();

            pstmt = con.prepareStatement("select top 1 Beneficiary_Problem_ID,status,convert(varchar,created_date,103) created_date from tblsadaremGreivances_Details "
                    + " where pensioncard_no=? and district_id=? and status not like '%Rejected%' and status not in ('')"
                    + " order by created_date asc");
            pstmt.setString(1, pensionNo);
            pstmt.setString(2, districtId);

            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    RequestNames.add(rs.getString(1));
                    RequestNames.add(rs.getString(2));
                    RequestNames.add(rs.getString(3));

                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMeesevaRequestDetails", "GrievancesRequestDetailsDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsDAO", "getMeesevaRequestDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMeesevaRequestDetails", "GrievancesRequestDetailsDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsDAO", "getMeesevaRequestDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }
        return RequestNames;
    }

    public String SADAREMTransctionId(DataSource ds, String applicationId) throws SADAREMDBException, SQLException {
        String transacId = "-";

        try {
            if (applicationId != null && !applicationId.equalsIgnoreCase("")) {
                sql = "select transistionId from tblsadaremGreivances_Details where Beneficiary_Problem_ID='" + applicationId + "'";
                con = DBConnection.getConnection();
                pstmt = con.prepareStatement(sql);

                rs = pstmt.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        transacId = rs.getString(1);
                    }

                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "SADAREMTransctionId", "GrievancesRequestDetailsDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsDAO", "SADAREMTransctionId");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "SADAREMTransctionId", "GrievancesRequestDetailsDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsDAO", "SADAREMTransctionId");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);
        }
        return transacId;
    }

    public int updateMEESEVATransctionId(DataSource ds, String applicationId, String meesevaId) throws SADAREMDBException, SQLException {
        int success = 0;

        try {
            if (applicationId != null && !applicationId.equalsIgnoreCase("")) {
                con = DBConnection.getConnection();
                st = con.createStatement();
                sql = "update tblsadaremGreivances_Details set meeseva_transactionid='" + meesevaId + "' where Beneficiary_Problem_ID='" + applicationId + "'";

                success = st.executeUpdate(sql);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "SADAREMTransctionId", "GrievancesRequestDetailsDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsDAO", "SADAREMTransctionId");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "SADAREMTransctionId", "GrievancesRequestDetailsDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsDAO", "SADAREMTransctionId");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(st);
        }
        return success;
    }

    public String getDistrictName(DataSource ds, String districtId) throws SADAREMDBException, SQLException {
        String districtName = null;

        try {
            if (districtId != null) {
                con = DBConnection.getConnection();
                st = con.createStatement();
                sql = "select district_name  from tbldistrict_Details where district_id ='" + districtId + "'";
                rs = st.executeQuery(sql);
                if (rs != null) {
                    while (rs.next()) {
                        districtName = rs.getString(1);
                    }
                }


            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDistrictName", "GrievancesRequestDetailsDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsDAO", "SADAREMTransctionId");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDistrictName", "GrievancesRequestDetailsDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsDAO", "SADAREMTransctionId");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(st);
        }
        return districtName;
    }

    public String getMandalName(DataSource ds, String districtId, String mandalId) throws SADAREMDBException, SQLException {
        String mandalName = null;

        try {
            if (districtId != null) {
                con = DBConnection.getConnection();
                st = con.createStatement();
                sql = "select mandal_name  from tblmandal_Details where district_id ='" + districtId + "' and mandal_id ='" + mandalId + "'";
                rs = st.executeQuery(sql);
                if (rs != null) {
                    while (rs.next()) {
                        mandalName = rs.getString(1);
                    }
                }


            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMandalName", "GrievancesRequestDetailsDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsDAO", "SADAREMTransctionId");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMandalName", "GrievancesRequestDetailsDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsDAO", "SADAREMTransctionId");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(st);
        }
        return mandalName;
    }

    public int submitMeesevaFialTransctionDetails(DataSource ds, String benid, String uniqueid, String scaid, String loginid, String channelid,
            String reqid, String serviceid, String sadaremtransid, String scapwd,
            String meesevaflag, String fname, String distid, String mandalid, String villageid, String systemip) throws SADAREMDBException, SQLException {
        int success = 0;
        PreparedStatement ps = null;

        try {
            con = DBConnection.getConnection();

            sql = "INSERT INTO TBLSADAREMGREIVANCES_FAIL_TRANSATIONS"
                    + " (BENEFICIARY_PROBLEM_ID,UNIQUEID,SCAUSERID,LOGINID,CHANNELID,"
                    + " REQUESTID,SERVICEID,SADAREMTRANSID,SCAPWD,"
                    + " MEESEVAFLAG,FIRSTNAME,DISTID,MANDALID,VILLAGEID,"
                    + " CREATEDDATE,UPDATEDDATE,STATUS,SYSTEMIP)"
                    + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,getDate(),getDate(),'Active',?)";

            ps = con.prepareStatement(sql);
            ps.setString(1, benid);
            ps.setString(2, uniqueid);
            ps.setString(3, scaid);
            ps.setString(4, loginid);
            ps.setString(5, channelid);

            ps.setString(6, reqid);
            ps.setString(7, serviceid);
            ps.setString(8, sadaremtransid);
            ps.setString(9, scapwd);

            ps.setString(10, meesevaflag);
            ps.setString(11, fname);
            ps.setString(12, distid);
            ps.setString(13, mandalid);
            ps.setString(14, villageid);
            ps.setString(15, systemip);

            success = ps.executeUpdate();


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "SADAREMTransctionId", "GrievancesRequestDetailsDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsDAO", "submittblsadaremGreivances_Details");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "SADAREMTransctionId", "GrievancesRequestDetailsDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsDAO", "submittblsadaremGreivances_Details");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(ps);
        }
        return success;
    }
}
