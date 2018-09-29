/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Schedule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import org.apache.axis.message.MessageElement;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.meesevaWS.GetPaymentTransIdResponseGetPaymentTransIdResult;
import com.meesevaWS.MeeSevaWebServiceSoapProxy;
import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author 567999
 */
public class MeesevaSchedule implements Job
{

    public void execute(JobExecutionContext jec) throws JobExecutionException 
    {
        Connection con = null;
        try {

            ArrayList list = null;
        	con = DBConnection.getConnection();
        	
        	MeeSevaWebServiceSoapProxy proxy = new MeeSevaWebServiceSoapProxy();
            list = failTransactions(con);
            //System.out.println("list=> "+list);
            if (list != null) 
            {
                for (int i = 0; i < list.size(); i++) 
                {
                    String checksum1 = null;
                    String transId = null;
                    int updateMeesevaId = 0;
                    ArrayList tlist = (ArrayList) list.get(i);
                    checksum1 = GenerateCheckSum(tlist.get(2).toString(), tlist.get(9).toString(),
                            tlist.get(2).toString(), tlist.get(4).toString(), tlist.get(1).toString());


                    String[] arrPaymentDetails1 = new String[12];

                    arrPaymentDetails1[0] = tlist.get(1).toString(); //Unique Id
                    arrPaymentDetails1[1] = tlist.get(2).toString(); //SAU User id
                    arrPaymentDetails1[2] = "CA";
                    arrPaymentDetails1[3] = tlist.get(3).toString(); //Login Id
                    arrPaymentDetails1[4] = tlist.get(4).toString(); //Channel Id
                    arrPaymentDetails1[5] = tlist.get(0).toString(); //Application Number
                    arrPaymentDetails1[6] = tlist.get(5).toString(); //Request Id
                    arrPaymentDetails1[7] = tlist.get(6).toString(); //Service Id
                    arrPaymentDetails1[8] = tlist.get(7).toString(); //Transation Id
                    arrPaymentDetails1[9] = "00";
                    arrPaymentDetails1[10] = tlist.get(8).toString(); //SCA Password
                    arrPaymentDetails1[11] = tlist.get(9).toString(); //Meeseva Flag
                    String[] arrAmount1 = new String[5];
                    arrAmount1[0] = "25";
                    arrAmount1[1] = "0";
                    arrAmount1[2] = "0";
                    arrAmount1[3] = "0";
                    arrAmount1[4] = "0";

                    String[] arrTransParams1 = new String[9];
                    arrTransParams1[0] = "Applicant Name";
                    arrTransParams1[1] = "DistrictId";
                    arrTransParams1[2] = "MandalId";
                    arrTransParams1[3] = "VillageId";
                    arrTransParams1[4] = "SLA";
                    arrTransParams1[5] = "DeliveryType";
                    arrTransParams1[6] = "Total Amount";
                    arrTransParams1[7] = "Status";
                    arrTransParams1[8] = "SLAEndDate";

                    String[] arrTransDetails1 = new String[9];
                    arrTransDetails1[0] = tlist.get(10).toString(); //First Name
                    arrTransDetails1[1] = tlist.get(11).toString(); //District Id
                    arrTransDetails1[2] = tlist.get(12).toString(); //Mandal Id
                    arrTransDetails1[3] = tlist.get(13).toString(); //Village id
                    arrTransDetails1[4] = "0";
                    arrTransDetails1[5] = "Manual";
                    arrTransDetails1[6] = "25";
                    arrTransDetails1[7] = "02";
                    arrTransDetails1[8] = "";

                    GetPaymentTransIdResponseGetPaymentTransIdResult transResp = proxy.getPaymentTransId(arrPaymentDetails1, arrAmount1, arrTransParams1,arrTransDetails1, "MEESEVA", "MEESEVA", checksum1);
                    MessageElement[] transElements = transResp.get_any();
                    Iterator transIt = transElements[0].getChildElements();
                    String transResult = "";


                    while (transIt.hasNext()) 
                    {
                        MessageElement tranEle = (MessageElement) transIt.next();
                        ;
                        transResult += tranEle.getValue() + "#";
                    }

                    String[] transRespDtls = transResult.split("#");
                    if (transRespDtls[1].toString().equals("0")) 
                    {
                        transId = transRespDtls[2];
                    }
                    if (transId != null && !transId.equalsIgnoreCase(""))
                    {
                        updateMeesevaId = updateMEESEVATransctionId(con, tlist.get(0).toString(), transId);
                        updateFailsTransctionId(con, transId, transId);
                    }

                }

            }
            con.close();

        } catch (Exception e)
        {
            e.printStackTrace();
        } 
        finally
        {
            DBConnection.closeConnection(con);
        }
    }

    private String GenerateCheckSum(String scaUserId, String scaPassword, String centerId, String operatorId, String uniqueNo) {
        String checkSum = "";
        try {
            String str1 = centerId + uniqueNo;
            String str2 = operatorId;
            String l3 = (str1 + scaUserId) + (str2 + scaPassword);
            int n1 = str1.length();
            int n2 = str2.length();
            String l1 = str1.substring(0, n1 - 3);
            String l2 = str2.substring(0, n2 - 2);
            Random random = new Random();
            int intl4 = random.nextInt(9);
            String l4 = Integer.toString(intl4);
            checkSum = l4 + l3 + l2 + uniqueNo + l1 + l4;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return checkSum;
    }

    private ArrayList failTransactions(Connection con) {
        ArrayList list = new ArrayList();
        String sql = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList tlist = null;
        try {

            sql = "SELECT "
                    + " BENEFICIARY_PROBLEM_ID,UNIQUEID,SCAUSERID,LOGINID,CHANNELID,"
                    + " REQUESTID,SERVICEID,SADAREMTRANSID,SCAPWD,"
                    + " MEESEVAFLAG,FIRSTNAME,DISTID,MANDALID,VILLAGEID  "
                    + " FROM TBLSADAREMGREIVANCES_FAIL_TRANSATIONS "
                    + " WHERE MEESEVA_TRANSACTIONID IS NULL AND STATUS='Active'";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    tlist = new ArrayList();
                    tlist.add(rs.getString(1));
                    tlist.add(rs.getString(2));
                    tlist.add(rs.getString(3));
                    tlist.add(rs.getString(4));
                    tlist.add(rs.getString(5));
                    tlist.add(rs.getString(6));
                    tlist.add(rs.getString(7));
                    tlist.add(rs.getString(8));
                    tlist.add(rs.getString(9));
                    tlist.add(rs.getString(10));
                    tlist.add(rs.getString(11));
                    tlist.add(rs.getString(12));
                    tlist.add(rs.getString(13));
                    tlist.add(rs.getString(14));
                    list.add(tlist);

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public int updateMEESEVATransctionId(Connection con, String applicationId, String meesevaId) {
        int success = 0;
        String sql = null;
        PreparedStatement ps = null;
        try {
            if (applicationId != null && !applicationId.equalsIgnoreCase("")) {
                sql = "update tblsadaremGreivances_Details set meeseva_transactionid='" + meesevaId + "' where Beneficiary_Problem_ID='" + applicationId + "'";
                ps = con.prepareCall(sql);
                success = ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    public int updateFailsTransctionId(Connection con, String applicationId, String meesevaId) {
        int success = 0;
        String sql = null;
        PreparedStatement ps = null;
        try {
            if (applicationId != null && !applicationId.equalsIgnoreCase("")) {
                sql = "UPDATE TBLSADAREMGREIVANCES_FAIL_TRANSATIONS SET MEESEVA_TRANSACTIONID='" + meesevaId + "' WHERE BENEFICIARY_PROBLEM_ID='" + applicationId + "'";
                ps = con.prepareCall(sql);
                success = ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }
}
