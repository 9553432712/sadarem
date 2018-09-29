/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.bf.disability.Exception.BaseAction;
import javax.sql.DataSource;
import javax.servlet.http.HttpSession;
import org.bf.disability.common.CommonDetails;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.form.RationCardServiceForm;
import org.bf.disability.service.RationCardReportService;
import org.bf.disability.servicefactory.RationCardReportServiceFactory;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.dao.PersonCodeCheckDAO;

/**
 *
 * @author 509862
 */
public class RationCardServiceReportAction extends BaseAction {
    /* forward name="success" path="" */

    private static String target = "success";

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        RationCardServiceForm rationForm = (RationCardServiceForm) form;
        HttpSession session = request.getSession();

        DataSource datasource = null;
        DataSource civilDs = null;
        ArrayList mandalList = null;
        ArrayList villageList = new ArrayList();
        ArrayList habitationList = new ArrayList();
        ArrayList personalDetails = null;
        ArrayList membersDetails = new ArrayList();
        String sql = null;
        Connection con = null;
        Statement st = null;
        int count =0;
        PersonCodeCheckDAO dao1 = new PersonCodeCheckDAO();

        try {
            datasource = getDataSource(request);
            if (datasource == null || "null".equals(datasource)) {
                datasource = JNDIDataSource.getConnection();
            }
            civilDs = getDataSource(request);
            if (civilDs == null || "null".equals(civilDs)) {
                // civilDs = JNDIDataSource.getCivilSuppliesConnection();
            }

            RationCardReportService rationCardReportService = RationCardReportServiceFactory.getRationCardServiceImpl();

            /* Getting the mandal Details based on district code */

            if (session.getAttribute("districtId") != null) {
                mandalList = rationCardReportService.getMandals(datasource, (String) session.getAttribute("districtId"));
                rationForm.setMandal_list(mandalList);
            }

            /* Getting the Village Details based on district code and mandal Code*/

            if ("getVillages".equalsIgnoreCase(rationForm.getMode())) {
                villageList = rationCardReportService.getVillageAll(datasource, (String) session.getAttribute("districtId"), rationForm.getMandal_id());
                rationForm.setVillagelist(villageList);
            }

            /* Getting the Habitation Details based on district code and mandal Code and villageCode*/

            if ("getHabDetails".equalsIgnoreCase(rationForm.getMode())) {
                villageList = rationCardReportService.getVillageAll(datasource, (String) session.getAttribute("districtId"), rationForm.getMandal_id());
                rationForm.setVillagelist(villageList);
                rationForm.setHabitation_id(rationForm.getHabitation_id());
                habitationList = rationCardReportService.getHabitation(datasource, (String) session.getAttribute("districtId"), rationForm.getMandal_id(), rationForm.getVillage_id());
                rationForm.setHabitationlist(habitationList);
            }

            /* Getting the personal details based on district Code and mandal code */

            if ("getPersonDetails".equalsIgnoreCase(rationForm.getMode())) {
                habitationList = rationCardReportService.getHabitation(datasource, (String) session.getAttribute("districtId"), rationForm.getMandal_id(), rationForm.getVillage_id());
                rationForm.setHabitationlist(habitationList);
                personalDetails = rationCardReportService.getRationPersonalReportDetails(datasource, (String) session.getAttribute("districtId"), rationForm.getMandal_id(), rationForm.getVillage_id(), rationForm.getHabitation_id(), rationForm.getPersonStatus());


                if (personalDetails.size() > 0) {
                    request.setAttribute("personalDetails", personalDetails);
                } else {
                    request.setAttribute("msg", "No Records Found");
                }

                villageList = rationCardReportService.getVillageAll(datasource, (String) session.getAttribute("districtId"), rationForm.getMandal_id());
                rationForm.setVillagelist(villageList);
                mandalList = rationCardReportService.getMandals(datasource, (String) session.getAttribute("districtId"));
                rationForm.setMandal_list(mandalList);
                rationForm.reset(mapping, request);
            }
            CommonDetails commondetails = new CommonDetails();



            if ("getDetails".equalsIgnoreCase(rationForm.getMode())) {


                if(rationForm.getRationCardNo()!=null){
                  
              count = dao1.getRactionCardCount(rationForm.getRationCardNo().substring(3, 5), datasource);
                }

                if (count > 0) {
                    //AponlineWSClient wsClient = new AponlineWSClient();

                    ArrayList getRationData = new ArrayList();
                    PersonCodeCheckDAO dao = new PersonCodeCheckDAO();

                    getRationData = dao.getDataFromCivilSuppliesDatabase(datasource, rationForm.getRationCardNo().toUpperCase(), request); // Get data from Civil supplies database
                            if (getRationData.size() == 0) {
                                List<String> dists = dao.getIssueRaisingRationCard(datasource, rationForm.getRationCardNo());
                                String oldDist = "";
                                if (dists.size() > 0 && !dists.get(0).equals("")) {
                                    oldDist = dists.get(0);
                                    getRationData = dao.getDataFromCivilDatabaseWithOld(datasource, rationForm.getRationCardNo().toUpperCase(), oldDist);

                                }

                            }

                            request.setAttribute("ration", rationForm.getRationCardNo());

                            if (getRationData.size() > 0) {
                                for (int y = 0; y < getRationData.size(); y++) {
                                    HashMap rationDetails = (HashMap) getRationData.get(y);
                                    HashMap m = new HashMap();
                                    m.put("slNo", rationDetails.get("relationSlno"));
                                    m.put("memberName", rationDetails.get("name"));
                                    m.put("age", rationDetails.get("Age"));
                                    membersDetails.add(m);

                                }
                            }

                            if (getRationData.size() == 0) {
                                request.setAttribute("msg", "Rationcard details not available");
                    } else {
                        request.setAttribute("RationCardMember", membersDetails);
                    }



                } else {
             request.setAttribute("msg", "Invalid RationCard Number!");
            }
        }
            /* Insert the ration card details */


            if ("getInsertPersonDetails".equalsIgnoreCase(rationForm.getMode())) {

                /* Getting the database connection */

                con = datasource.getConnection();
                st = con.createStatement();

                String[] rationCardDetails = rationForm.getRationCardDetails();
                String[] rationCardSerialNumberDetails = rationForm.getRationCardSerialNumbers();
                String[] rationType = rationForm.getRationCardTypes();
                String[] personCode = rationForm.getPersonCode();
                //added by kavya
                URL url1 = new URL(CommonConstants.Url1);
               // URL url2 = new URL(CommonConstants.Url2);
                String message = null;
                //AponlineWSClient wsClient = new AponlineWSClient();
                //ended

                if ((rationCardDetails != null
                        && rationCardDetails.length > 0
                        && !rationCardDetails.equals(null))
                        && (rationCardSerialNumberDetails != null
                        && rationCardSerialNumberDetails.length > 0
                        && !rationCardSerialNumberDetails.equals(null))
                        && (rationType != null
                        && rationType.length > 0
                        && !rationType.equals(null))) {

                    /* Checks the String lengths are equal */

                    if (rationCardDetails.length == rationCardSerialNumberDetails.length && rationCardDetails.length == rationType.length && rationCardDetails.length == personCode.length
                            && rationCardSerialNumberDetails.length == rationType.length && rationCardSerialNumberDetails.length == personCode.length
                            && rationType.length == personCode.length) {

                        for (int i = 0, j = 0, k = 0, l = 0; i < rationCardDetails.length && j < personCode.length && k < rationType.length && l < rationCardSerialNumberDetails.length; i++, j++, k++, l++) {
                            //added by kavya
                            message = RationcardDetails.getRationcardSerialNumbers(datasource, url1, null, rationCardDetails[i], rationCardSerialNumberDetails[l], request);

                            if (message.equalsIgnoreCase(CommonConstants.SUCCESS)) {
                            if (rationCardDetails[i] != "" && personCode[j] != "" && rationType[k] != "0" && rationCardSerialNumberDetails[l] != "") {
                                sql = "update tblperson_personal_details set rationcard_number='" + rationCardDetails[i] + "',rationcard_type='" + rationType[k] + "',rationcard_slno='" + rationCardSerialNumberDetails[l] + "' where person_code='" + personCode[j] + "'";
                                st.addBatch(sql);
                            } // end of if
                            }
                        }// End of For
                    
                        if (message.equalsIgnoreCase(CommonConstants.ERROR)) {
                            request.setAttribute("MSG", "You Have Entered Invalid Rationcard serial number ");
                            return mapping.findForward("exception");
                        } else if (!message.equalsIgnoreCase(CommonConstants.SUCCESS)) {
                            request.setAttribute("MSG", message);
                            return mapping.findForward("exception");
                        }
                        //ended
                        int[] personalDetailsInsert = st.executeBatch();
                        if (personalDetailsInsert.length > 0) {

                            request.setAttribute("msg", "RationCard Details Inserted Successfully");
                            rationForm.setMandal_id("0");
                            rationForm.setVillage_id("0");
                            rationForm.setPersonStatus("0");
                        } else {

                            request.setAttribute("msg", "Error in inserting");
                            rationForm.setMandal_id("0");
                            rationForm.setVillage_id("0");
                            rationForm.setPersonStatus("0");
                        }

                    } else {
                        request.setAttribute("msg", "Error in inserting");
                        rationForm.setMandal_id("0");
                        rationForm.setVillage_id("0");
                        rationForm.setPersonStatus("0");
                    } // end of if
                } else {
                    //  target = "exception";
                } // end of if
            }


        } catch (Exception sADAREMException) {
            sADAREMException.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (st != null) {
                    st.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mapping.findForward(target);
    }
}
