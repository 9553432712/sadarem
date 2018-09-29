/*
 * MRBinetKamatDetailedTestAction.java
 *
 * Created on October 15, 2008, 4:48 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.bf.disability.Exception.BaseDispatchAction;
import org.bf.disability.Exception.SADAREMException;
import org.bf.disability.common.CommonDAO;
import org.bf.disability.dto.MRBinetKamatDetailedTestDTO;
import org.bf.disability.form.MRBinetKamatDetailedTestForm;
import org.bf.disability.service.MentalRetardationService;
import org.bf.disability.servicefactory.MentalRetardationServiceFactory;
import org.bf.disability.common.DataBase.JNDIDataSource;

/**
 *
 * @author Deviprasad_t
 * @ This class is use for Inserting,Selecting,Updating the BinetKamatTestDetails.
 */
public class MRBinetKamatDetailedTestAction extends BaseDispatchAction {

    ActionMessages actionMessages = null;
    MentalRetardationService mentalRetardationService = MentalRetardationServiceFactory.getMentalRetardationServiceImpl();

    /**
     *
     * @ This Method is use for inserting the BinetKamatTestDetails.
     * @param mapping ActionMapping
     * @param form MRBinetKamatDetailedTestForm
     * @throws java.lang.SADAREMException
     */
    public ActionForward insertMRBinetKamatDetailedTestDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String target = null;
        DataSource ds = null;
        ds = getDataSource(request);
        if (ds == null || "null".equals(ds)) {
            ds = JNDIDataSource.getConnection();
        }

        MRBinetKamatDetailedTestForm MForm = (MRBinetKamatDetailedTestForm) form;

        int[] totalOneDimArry = new int[]{MForm.getAgeLevel3_Item1(), MForm.getAgeLevel4_Item1(), MForm.getAgeLevel5_Item1(), MForm.getAgeLevel6_Item1(), MForm.getAgeLevel7_Item1(), MForm.getAgeLevel8_Item1(), MForm.getAgeLevel9_Item1(), MForm.getAgeLevel10_Item1(), MForm.getAgeLevel12_Item1(), MForm.getAgeLevel14_Item1(), MForm.getAgeLevel16_Item1(), MForm.getAgeLevel19_Item1(), MForm.getAgeLevel22_Item1(),
            MForm.getAgeLevel3_Item2(), MForm.getAgeLevel4_Item2(), MForm.getAgeLevel5_Item2(), MForm.getAgeLevel6_Item2(), MForm.getAgeLevel7_Item2(), MForm.getAgeLevel8_Item2(), MForm.getAgeLevel9_Item2(), MForm.getAgeLevel10_Item2(), MForm.getAgeLevel12_Item2(), MForm.getAgeLevel14_Item2(), MForm.getAgeLevel16_Item2(), MForm.getAgeLevel19_Item2(), MForm.getAgeLevel22_Item2(),
            MForm.getAgeLevel3_Item3(), MForm.getAgeLevel4_Item3(), MForm.getAgeLevel5_Item3(), MForm.getAgeLevel6_Item3(), MForm.getAgeLevel7_Item3(), MForm.getAgeLevel8_Item3(), MForm.getAgeLevel9_Item3(), MForm.getAgeLevel10_Item3(), MForm.getAgeLevel12_Item3(), MForm.getAgeLevel14_Item3(), MForm.getAgeLevel16_Item3(), MForm.getAgeLevel19_Item3(), MForm.getAgeLevel22_Item3(),
            MForm.getAgeLevel3_Item4(), MForm.getAgeLevel4_Item4(), MForm.getAgeLevel5_Item4(), MForm.getAgeLevel6_Item4(), MForm.getAgeLevel7_Item4(), MForm.getAgeLevel8_Item4(), MForm.getAgeLevel9_Item4(), MForm.getAgeLevel10_Item4(), MForm.getAgeLevel12_Item4(), MForm.getAgeLevel14_Item4(), MForm.getAgeLevel16_Item4(), MForm.getAgeLevel19_Item4(), MForm.getAgeLevel22_Item4(),
            MForm.getAgeLevel3_Item5(), MForm.getAgeLevel4_Item5(), MForm.getAgeLevel5_Item5(), MForm.getAgeLevel6_Item5(), MForm.getAgeLevel7_Item5(), MForm.getAgeLevel8_Item5(), MForm.getAgeLevel9_Item5(), MForm.getAgeLevel10_Item5(), MForm.getAgeLevel12_Item5(), MForm.getAgeLevel14_Item5(), MForm.getAgeLevel16_Item5(), MForm.getAgeLevel19_Item5(), MForm.getAgeLevel22_Item5(),
            MForm.getAgeLevel3_Item6(), MForm.getAgeLevel4_Item6(), MForm.getAgeLevel5_Item6(), MForm.getAgeLevel6_Item6(), MForm.getAgeLevel7_Item6(), MForm.getAgeLevel8_Item6(), MForm.getAgeLevel9_Item6(), MForm.getAgeLevel10_Item6(), MForm.getAgeLevel12_Item6(), MForm.getAgeLevel14_Item6(), MForm.getAgeLevel16_Item6(), MForm.getAgeLevel19_Item6(), MForm.getAgeLevel22_Item6(),
            MForm.getAgeLevel3_ItemAlt1(), MForm.getAgeLevel4_ItemAlt1(), MForm.getAgeLevel5_ItemAlt1(), MForm.getAgeLevel6_ItemAlt1(), MForm.getAgeLevel7_ItemAlt1(), MForm.getAgeLevel8_ItemAlt1(), MForm.getAgeLevel9_ItemAlt1(), MForm.getAgeLevel10_ItemAlt1(), MForm.getAgeLevel12_ItemAlt1(), MForm.getAgeLevel14_ItemAlt1(), MForm.getAgeLevel16_ItemAlt1(), MForm.getAgeLevel19_ItemAlt1(), MForm.getAgeLevel22_ItemAlt1(),
            MForm.getAgeLevel3_ItemAlt2(), MForm.getAgeLevel4_ItemAlt2(), MForm.getAgeLevel5_ItemAlt2(), MForm.getAgeLevel6_ItemAlt2(), MForm.getAgeLevel7_ItemAlt2(), MForm.getAgeLevel8_ItemAlt2(), MForm.getAgeLevel9_ItemAlt2(), MForm.getAgeLevel10_ItemAlt2(), MForm.getAgeLevel12_ItemAlt2(), MForm.getAgeLevel14_ItemAlt2(), MForm.getAgeLevel16_ItemAlt2(), MForm.getAgeLevel19_ItemAlt2(), MForm.getAgeLevel22_ItemAlt2(),
            MForm.getAgeLevel3_ItemAlt3(), MForm.getAgeLevel4_ItemAlt3(), MForm.getAgeLevel5_ItemAlt3(), MForm.getAgeLevel6_ItemAlt3(), MForm.getAgeLevel7_ItemAlt3(), MForm.getAgeLevel8_ItemAlt3(), MForm.getAgeLevel9_ItemAlt3(), MForm.getAgeLevel10_ItemAlt3(), MForm.getAgeLevel12_ItemAlt3(), MForm.getAgeLevel14_ItemAlt3(), MForm.getAgeLevel16_ItemAlt3(), MForm.getAgeLevel19_ItemAlt3(), MForm.getAgeLevel22_ItemAlt3()};

        int[][] totalTwoDimArr = new int[9][13];
        int row = 0;
        int col = 0;


        for (int i = 0; i < totalOneDimArry.length; i++) {
            if (i == 0) {
            } else if (i % 13 == 0) {
                row++;
                col = 0;
            } else {
                col++;
            }
            totalTwoDimArr[row][col] = totalOneDimArry[i];
        }
//        for(int i=0;i<9;i++){
//            for(int j=0;j<13;j++)


//        }

        int mentalAgeFromChecks = 0;
        int mentalAgeTotalInMonths = 0;
        int mentalAgeYears = 0;
        int mentalAgeMonths = 0;
        for (int i = 0; i < totalOneDimArry.length; i++) {
            mentalAgeFromChecks = mentalAgeFromChecks + totalOneDimArry[i];
        }
        if (mentalAgeFromChecks == 0) {
            target = "noinput";
        } else {
            mentalAgeTotalInMonths = 24 + mentalAgeFromChecks;
            mentalAgeYears = mentalAgeTotalInMonths / 12;
            mentalAgeMonths = mentalAgeTotalInMonths % 12;

            int indexForBasalAge = 0;
            int flag = 0;
            for (int i = 0; i <= 13; i++) {
                if (i == 13) {
                    indexForBasalAge = i;
                    break;
                } else {
                    flag = checkCloumnForAllChecked(i, totalTwoDimArr);
                }
                if (flag == 0) {
                    indexForBasalAge = i;
                    break;
                }
            }
            int basalAge = 0;
            switch (indexForBasalAge) {
                case 1:
                    basalAge = 3;
                    break;
                case 2:
                    basalAge = 4;
                    break;
                case 3:
                    basalAge = 5;
                    break;
                case 4:
                    basalAge = 6;
                    break;
                case 5:
                    basalAge = 7;
                    break;
                case 6:
                    basalAge = 8;
                    break;
                case 7:
                    basalAge = 9;
                    break;
                case 8:
                    basalAge = 10;
                    break;
                case 9:
                    basalAge = 12;
                    break;
                case 10:
                    basalAge = 14;
                    break;
                case 11:
                    basalAge = 16;
                    break;
                case 12:
                    basalAge = 19;
                    break;
                case 13:
                    basalAge = 22;
                    break;
            }


            int indexForTerminalAge = -1;
            for (int i = 0; i <= 13; i++) {
                if (i == 13) {
                    indexForTerminalAge = i;
                    break;
                } else {
                    flag = checkCloumnForAllUnChecked(i, totalTwoDimArr);
                }
                if (flag == 1 && i == 0) {
                    break;
                } else if (flag == 1) {
                    indexForTerminalAge = i;
                    break;
                }

            }
            int terminalAge = 0;
            switch (indexForTerminalAge) {
                case 0:
                    terminalAge = 3;
                    break;
                case 1:
                    terminalAge = 4;
                    break;
                case 2:
                    terminalAge = 5;
                    break;
                case 3:
                    terminalAge = 6;
                    break;
                case 4:
                    terminalAge = 7;
                    break;
                case 5:
                    terminalAge = 8;
                    break;
                case 6:
                    terminalAge = 9;
                    break;
                case 7:
                    terminalAge = 10;
                    break;
                case 8:
                    terminalAge = 12;
                    break;
                case 9:
                    terminalAge = 14;
                    break;
                case 10:
                    terminalAge = 16;
                    break;
                case 11:
                    terminalAge = 19;
                    break;
                case 12:
                    terminalAge = 22;
                    break;
            }



            request.setAttribute("basalAge", String.valueOf(basalAge));
            request.setAttribute("terminalAge", String.valueOf(terminalAge));
            request.setAttribute("mentalAgeYears", String.valueOf(mentalAgeYears));
            request.setAttribute("mentalAgeMonths", String.valueOf(mentalAgeMonths));

            MRBinetKamatDetailedTestDTO mRBinetKamatDetailedTestDTO = new MRBinetKamatDetailedTestDTO();
            HttpSession session = request.getSession(true);
            String personcode = null;

            if (session.getAttribute("sadaremCodeAu") != null) {
                personcode = (String) session.getAttribute("sadaremCodeAu");
            } else {
                personcode = (String) session.getAttribute("personcode");
            }

            MForm.setPersoncode(personcode);
            MForm.setSystemIP(request.getRemoteAddr());
            MForm.setLoginID((String) session.getAttribute("loginid"));
            //DataSource ds=getDataSource(request);


            boolean isPerosonCodeExist = mentalRetardationService.checkForPersonCode(ds, personcode, "tblPerson_MR_BKT_Detailed_Test_Details");

            if (isPerosonCodeExist) {
                if (session.getAttribute("sadaremCodeAu") != null) {
                    BeanUtils.copyProperties(mRBinetKamatDetailedTestDTO, MForm);
                    int i = mentalRetardationService.insertMRBinetKamatDetailedTestDetailsAU(ds, mRBinetKamatDetailedTestDTO, request);
                    if (i == -1) {
                        target = "failure";
                    } else {
                        target = "success";
                    }
                } else {
                    target = "failure";
                }

            } else {
                BeanUtils.copyProperties(mRBinetKamatDetailedTestDTO, MForm);
                mentalRetardationService.insertMRBinetKamatDetailedTestDetails(ds, mRBinetKamatDetailedTestDTO, request);
                target = "success";
            }


        }
        return mapping.findForward(target);
    }

    /**
     *
     * @ This Method is use for inserting the BinetKamatTestDetails.
     * @param mapping ActionMapping
     * @param form MRBinetKamatDetailedTestForm
     * @throws java.lang.SADAREMException
     */
    public ActionForward insertMRBinetKamatDetailedTestDetailsAU(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String target = null;
        DataSource ds = null;
        ds = getDataSource(request);
        if (ds == null || "null".equals(ds)) {
            ds = JNDIDataSource.getConnection();
        }

        MRBinetKamatDetailedTestForm MForm = (MRBinetKamatDetailedTestForm) form;

        int[] totalOneDimArry = new int[]{MForm.getAgeLevel3_Item1(), MForm.getAgeLevel4_Item1(), MForm.getAgeLevel5_Item1(), MForm.getAgeLevel6_Item1(), MForm.getAgeLevel7_Item1(), MForm.getAgeLevel8_Item1(), MForm.getAgeLevel9_Item1(), MForm.getAgeLevel10_Item1(), MForm.getAgeLevel12_Item1(), MForm.getAgeLevel14_Item1(), MForm.getAgeLevel16_Item1(), MForm.getAgeLevel19_Item1(), MForm.getAgeLevel22_Item1(),
            MForm.getAgeLevel3_Item2(), MForm.getAgeLevel4_Item2(), MForm.getAgeLevel5_Item2(), MForm.getAgeLevel6_Item2(), MForm.getAgeLevel7_Item2(), MForm.getAgeLevel8_Item2(), MForm.getAgeLevel9_Item2(), MForm.getAgeLevel10_Item2(), MForm.getAgeLevel12_Item2(), MForm.getAgeLevel14_Item2(), MForm.getAgeLevel16_Item2(), MForm.getAgeLevel19_Item2(), MForm.getAgeLevel22_Item2(),
            MForm.getAgeLevel3_Item3(), MForm.getAgeLevel4_Item3(), MForm.getAgeLevel5_Item3(), MForm.getAgeLevel6_Item3(), MForm.getAgeLevel7_Item3(), MForm.getAgeLevel8_Item3(), MForm.getAgeLevel9_Item3(), MForm.getAgeLevel10_Item3(), MForm.getAgeLevel12_Item3(), MForm.getAgeLevel14_Item3(), MForm.getAgeLevel16_Item3(), MForm.getAgeLevel19_Item3(), MForm.getAgeLevel22_Item3(),
            MForm.getAgeLevel3_Item4(), MForm.getAgeLevel4_Item4(), MForm.getAgeLevel5_Item4(), MForm.getAgeLevel6_Item4(), MForm.getAgeLevel7_Item4(), MForm.getAgeLevel8_Item4(), MForm.getAgeLevel9_Item4(), MForm.getAgeLevel10_Item4(), MForm.getAgeLevel12_Item4(), MForm.getAgeLevel14_Item4(), MForm.getAgeLevel16_Item4(), MForm.getAgeLevel19_Item4(), MForm.getAgeLevel22_Item4(),
            MForm.getAgeLevel3_Item5(), MForm.getAgeLevel4_Item5(), MForm.getAgeLevel5_Item5(), MForm.getAgeLevel6_Item5(), MForm.getAgeLevel7_Item5(), MForm.getAgeLevel8_Item5(), MForm.getAgeLevel9_Item5(), MForm.getAgeLevel10_Item5(), MForm.getAgeLevel12_Item5(), MForm.getAgeLevel14_Item5(), MForm.getAgeLevel16_Item5(), MForm.getAgeLevel19_Item5(), MForm.getAgeLevel22_Item5(),
            MForm.getAgeLevel3_Item6(), MForm.getAgeLevel4_Item6(), MForm.getAgeLevel5_Item6(), MForm.getAgeLevel6_Item6(), MForm.getAgeLevel7_Item6(), MForm.getAgeLevel8_Item6(), MForm.getAgeLevel9_Item6(), MForm.getAgeLevel10_Item6(), MForm.getAgeLevel12_Item6(), MForm.getAgeLevel14_Item6(), MForm.getAgeLevel16_Item6(), MForm.getAgeLevel19_Item6(), MForm.getAgeLevel22_Item6(),
            MForm.getAgeLevel3_ItemAlt1(), MForm.getAgeLevel4_ItemAlt1(), MForm.getAgeLevel5_ItemAlt1(), MForm.getAgeLevel6_ItemAlt1(), MForm.getAgeLevel7_ItemAlt1(), MForm.getAgeLevel8_ItemAlt1(), MForm.getAgeLevel9_ItemAlt1(), MForm.getAgeLevel10_ItemAlt1(), MForm.getAgeLevel12_ItemAlt1(), MForm.getAgeLevel14_ItemAlt1(), MForm.getAgeLevel16_ItemAlt1(), MForm.getAgeLevel19_ItemAlt1(), MForm.getAgeLevel22_ItemAlt1(),
            MForm.getAgeLevel3_ItemAlt2(), MForm.getAgeLevel4_ItemAlt2(), MForm.getAgeLevel5_ItemAlt2(), MForm.getAgeLevel6_ItemAlt2(), MForm.getAgeLevel7_ItemAlt2(), MForm.getAgeLevel8_ItemAlt2(), MForm.getAgeLevel9_ItemAlt2(), MForm.getAgeLevel10_ItemAlt2(), MForm.getAgeLevel12_ItemAlt2(), MForm.getAgeLevel14_ItemAlt2(), MForm.getAgeLevel16_ItemAlt2(), MForm.getAgeLevel19_ItemAlt2(), MForm.getAgeLevel22_ItemAlt2(),
            MForm.getAgeLevel3_ItemAlt3(), MForm.getAgeLevel4_ItemAlt3(), MForm.getAgeLevel5_ItemAlt3(), MForm.getAgeLevel6_ItemAlt3(), MForm.getAgeLevel7_ItemAlt3(), MForm.getAgeLevel8_ItemAlt3(), MForm.getAgeLevel9_ItemAlt3(), MForm.getAgeLevel10_ItemAlt3(), MForm.getAgeLevel12_ItemAlt3(), MForm.getAgeLevel14_ItemAlt3(), MForm.getAgeLevel16_ItemAlt3(), MForm.getAgeLevel19_ItemAlt3(), MForm.getAgeLevel22_ItemAlt3()};

        int[][] totalTwoDimArr = new int[9][13];
        int row = 0;
        int col = 0;


        for (int i = 0; i < totalOneDimArry.length; i++) {
            if (i == 0) {
            } else if (i % 13 == 0) {
                row++;
                col = 0;
            } else {
                col++;
            }
            totalTwoDimArr[row][col] = totalOneDimArry[i];
        }
//        for(int i=0;i<9;i++){
//            for(int j=0;j<13;j++)


//        }

        int mentalAgeFromChecks = 0;
        int mentalAgeTotalInMonths = 0;
        int mentalAgeYears = 0;
        int mentalAgeMonths = 0;
        for (int i = 0; i < totalOneDimArry.length; i++) {
            mentalAgeFromChecks = mentalAgeFromChecks + totalOneDimArry[i];
        }
        if (mentalAgeFromChecks == 0) {
            target = "noinput";
        } else {
            mentalAgeTotalInMonths = 24 + mentalAgeFromChecks;
            mentalAgeYears = mentalAgeTotalInMonths / 12;
            mentalAgeMonths = mentalAgeTotalInMonths % 12;

            int indexForBasalAge = 0;
            int flag = 0;
            for (int i = 0; i <= 13; i++) {
                if (i == 13) {
                    indexForBasalAge = i;
                    break;
                } else {
                    flag = checkCloumnForAllChecked(i, totalTwoDimArr);
                }
                if (flag == 0) {
                    indexForBasalAge = i;
                    break;
                }
            }
            int basalAge = 0;
            switch (indexForBasalAge) {
                case 1:
                    basalAge = 3;
                    break;
                case 2:
                    basalAge = 4;
                    break;
                case 3:
                    basalAge = 5;
                    break;
                case 4:
                    basalAge = 6;
                    break;
                case 5:
                    basalAge = 7;
                    break;
                case 6:
                    basalAge = 8;
                    break;
                case 7:
                    basalAge = 9;
                    break;
                case 8:
                    basalAge = 10;
                    break;
                case 9:
                    basalAge = 12;
                    break;
                case 10:
                    basalAge = 14;
                    break;
                case 11:
                    basalAge = 16;
                    break;
                case 12:
                    basalAge = 19;
                    break;
                case 13:
                    basalAge = 22;
                    break;
            }


            int indexForTerminalAge = -1;
            for (int i = 0; i <= 13; i++) {
                if (i == 13) {
                    indexForTerminalAge = i;
                    break;
                } else {
                    flag = checkCloumnForAllUnChecked(i, totalTwoDimArr);
                }
                if (flag == 1 && i == 0) {
                    break;
                } else if (flag == 1) {
                    indexForTerminalAge = i;
                    break;
                }

            }
            int terminalAge = 0;
            switch (indexForTerminalAge) {
                case 0:
                    terminalAge = 3;
                    break;
                case 1:
                    terminalAge = 4;
                    break;
                case 2:
                    terminalAge = 5;
                    break;
                case 3:
                    terminalAge = 6;
                    break;
                case 4:
                    terminalAge = 7;
                    break;
                case 5:
                    terminalAge = 8;
                    break;
                case 6:
                    terminalAge = 9;
                    break;
                case 7:
                    terminalAge = 10;
                    break;
                case 8:
                    terminalAge = 12;
                    break;
                case 9:
                    terminalAge = 14;
                    break;
                case 10:
                    terminalAge = 16;
                    break;
                case 11:
                    terminalAge = 19;
                    break;
                case 12:
                    terminalAge = 22;
                    break;
            }



            request.setAttribute("basalAge", String.valueOf(basalAge));
            request.setAttribute("terminalAge", String.valueOf(terminalAge));
            request.setAttribute("mentalAgeYears", String.valueOf(mentalAgeYears));
            request.setAttribute("mentalAgeMonths", String.valueOf(mentalAgeMonths));

            MRBinetKamatDetailedTestDTO mRBinetKamatDetailedTestDTO = new MRBinetKamatDetailedTestDTO();
            HttpSession session = request.getSession(true);
            String personcode = (String) session.getAttribute("sadaremCodeAu");
            MForm.setPersoncode(personcode);
            MForm.setSystemIP(request.getRemoteAddr());
            MForm.setLoginID((String) session.getAttribute("loginid"));
            //DataSource ds=getDataSource(request);

            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                // //ds = JNDIDataSource.getConnection();
            }

            boolean isPerosonCodeExist = mentalRetardationService.checkForPersonCode(ds, personcode, "tblPerson_MR_BKT_Detailed_Test_Details");

            if (isPerosonCodeExist) {
                target = "failure";

            } else {
                BeanUtils.copyProperties(mRBinetKamatDetailedTestDTO, MForm);
                mentalRetardationService.insertMRBinetKamatDetailedTestDetails(ds, mRBinetKamatDetailedTestDTO, request);
                target = "success";
            }


        }
        return mapping.findForward(target);
    }

    /**
     *
     * @ This Method is use for getting the BinetKamatTestDetails.
     * @param mapping ActionMapping
     * @param form MRBinetKamatDetailedTestForm
     * @throws java.lang.SADAREMException
     */
    public ActionForward getMRBinetKamatDetailedTestDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String target = null;
        DataSource ds = null;
        ds = getDataSource(request);
        if (ds == null || "null".equals(ds)) {
            ds = JNDIDataSource.getConnection();
        }
        try {
            HttpSession session = request.getSession(true);
            MRBinetKamatDetailedTestForm MForm = (MRBinetKamatDetailedTestForm) form;
            //DataSource ds=getDataSource(request);



            String personcode = null;//(String) session.getAttribute("personcode");

            if (session.getAttribute("sadaremCodeAu") != null) {
                personcode = (String) session.getAttribute("sadaremCodeAu");
            } else {
                personcode = (String) session.getAttribute("personcode");
            }


            MRBinetKamatDetailedTestDTO mRBinetKamatDetailedTestDTO = new MRBinetKamatDetailedTestDTO();
            mRBinetKamatDetailedTestDTO = mentalRetardationService.getMRBinetKamatDetailedTestDetails(ds, personcode);
            BeanUtils.copyProperties(MForm, mRBinetKamatDetailedTestDTO);
            saveToken(request);
            target = "success";
        } catch (SADAREMException sADAREMException) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        } catch (Exception sADAREMException) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }

        return mapping.findForward(target);
    }

    /**
     *
     * @ This Method is use for Updating the BinetKamatTestDetails.
     * @param mapping ActionMapping
     * @param form MRBinetKamatDetailedTestForm
     * @throws java.lang.SADAREMException
     */
    public ActionForward updateMRBinetKamatDetailedTestDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String target = null;
        DataSource ds = null;
        ds = getDataSource(request);
        if (ds == null || "null".equals(ds)) {
            ds = JNDIDataSource.getConnection();
        }

        MRBinetKamatDetailedTestForm MForm = (MRBinetKamatDetailedTestForm) form;
        HttpSession session = request.getSession(true);
        String personcode = null;//(String) session.getAttribute("personcode");
        //DataSource ds=getDataSource(request);

        if (session.getAttribute("sadaremCodeAu") != null) {
            personcode = (String) session.getAttribute("sadaremCodeAu");
        } else {
            personcode = (String) session.getAttribute("personcode");
        }



        MRBinetKamatDetailedTestDTO mRBinetKamatDetailedTestDTO = new MRBinetKamatDetailedTestDTO();

        int[] totalOneDimArry = new int[]{MForm.getAgeLevel3_Item1(), MForm.getAgeLevel4_Item1(), MForm.getAgeLevel5_Item1(), MForm.getAgeLevel6_Item1(), MForm.getAgeLevel7_Item1(), MForm.getAgeLevel8_Item1(), MForm.getAgeLevel9_Item1(), MForm.getAgeLevel10_Item1(), MForm.getAgeLevel12_Item1(), MForm.getAgeLevel14_Item1(), MForm.getAgeLevel16_Item1(), MForm.getAgeLevel19_Item1(), MForm.getAgeLevel22_Item1(),
            MForm.getAgeLevel3_Item2(), MForm.getAgeLevel4_Item2(), MForm.getAgeLevel5_Item2(), MForm.getAgeLevel6_Item2(), MForm.getAgeLevel7_Item2(), MForm.getAgeLevel8_Item2(), MForm.getAgeLevel9_Item2(), MForm.getAgeLevel10_Item2(), MForm.getAgeLevel12_Item2(), MForm.getAgeLevel14_Item2(), MForm.getAgeLevel16_Item2(), MForm.getAgeLevel19_Item2(), MForm.getAgeLevel22_Item2(),
            MForm.getAgeLevel3_Item3(), MForm.getAgeLevel4_Item3(), MForm.getAgeLevel5_Item3(), MForm.getAgeLevel6_Item3(), MForm.getAgeLevel7_Item3(), MForm.getAgeLevel8_Item3(), MForm.getAgeLevel9_Item3(), MForm.getAgeLevel10_Item3(), MForm.getAgeLevel12_Item3(), MForm.getAgeLevel14_Item3(), MForm.getAgeLevel16_Item3(), MForm.getAgeLevel19_Item3(), MForm.getAgeLevel22_Item3(),
            MForm.getAgeLevel3_Item4(), MForm.getAgeLevel4_Item4(), MForm.getAgeLevel5_Item4(), MForm.getAgeLevel6_Item4(), MForm.getAgeLevel7_Item4(), MForm.getAgeLevel8_Item4(), MForm.getAgeLevel9_Item4(), MForm.getAgeLevel10_Item4(), MForm.getAgeLevel12_Item4(), MForm.getAgeLevel14_Item4(), MForm.getAgeLevel16_Item4(), MForm.getAgeLevel19_Item4(), MForm.getAgeLevel22_Item4(),
            MForm.getAgeLevel3_Item5(), MForm.getAgeLevel4_Item5(), MForm.getAgeLevel5_Item5(), MForm.getAgeLevel6_Item5(), MForm.getAgeLevel7_Item5(), MForm.getAgeLevel8_Item5(), MForm.getAgeLevel9_Item5(), MForm.getAgeLevel10_Item5(), MForm.getAgeLevel12_Item5(), MForm.getAgeLevel14_Item5(), MForm.getAgeLevel16_Item5(), MForm.getAgeLevel19_Item5(), MForm.getAgeLevel22_Item5(),
            MForm.getAgeLevel3_Item6(), MForm.getAgeLevel4_Item6(), MForm.getAgeLevel5_Item6(), MForm.getAgeLevel6_Item6(), MForm.getAgeLevel7_Item6(), MForm.getAgeLevel8_Item6(), MForm.getAgeLevel9_Item6(), MForm.getAgeLevel10_Item6(), MForm.getAgeLevel12_Item6(), MForm.getAgeLevel14_Item6(), MForm.getAgeLevel16_Item6(), MForm.getAgeLevel19_Item6(), MForm.getAgeLevel22_Item6(),
            MForm.getAgeLevel3_ItemAlt1(), MForm.getAgeLevel4_ItemAlt1(), MForm.getAgeLevel5_ItemAlt1(), MForm.getAgeLevel6_ItemAlt1(), MForm.getAgeLevel7_ItemAlt1(), MForm.getAgeLevel8_ItemAlt1(), MForm.getAgeLevel9_ItemAlt1(), MForm.getAgeLevel10_ItemAlt1(), MForm.getAgeLevel12_ItemAlt1(), MForm.getAgeLevel14_ItemAlt1(), MForm.getAgeLevel16_ItemAlt1(), MForm.getAgeLevel19_ItemAlt1(), MForm.getAgeLevel22_ItemAlt1(),
            MForm.getAgeLevel3_ItemAlt2(), MForm.getAgeLevel4_ItemAlt2(), MForm.getAgeLevel5_ItemAlt2(), MForm.getAgeLevel6_ItemAlt2(), MForm.getAgeLevel7_ItemAlt2(), MForm.getAgeLevel8_ItemAlt2(), MForm.getAgeLevel9_ItemAlt2(), MForm.getAgeLevel10_ItemAlt2(), MForm.getAgeLevel12_ItemAlt2(), MForm.getAgeLevel14_ItemAlt2(), MForm.getAgeLevel16_ItemAlt2(), MForm.getAgeLevel19_ItemAlt2(), MForm.getAgeLevel22_ItemAlt2(),
            MForm.getAgeLevel3_ItemAlt3(), MForm.getAgeLevel4_ItemAlt3(), MForm.getAgeLevel5_ItemAlt3(), MForm.getAgeLevel6_ItemAlt3(), MForm.getAgeLevel7_ItemAlt3(), MForm.getAgeLevel8_ItemAlt3(), MForm.getAgeLevel9_ItemAlt3(), MForm.getAgeLevel10_ItemAlt3(), MForm.getAgeLevel12_ItemAlt3(), MForm.getAgeLevel14_ItemAlt3(), MForm.getAgeLevel16_ItemAlt3(), MForm.getAgeLevel19_ItemAlt3(), MForm.getAgeLevel22_ItemAlt3()};

        int[][] totalTwoDimArr = new int[9][13];
        int row = 0;
        int col = 0;


        for (int i = 0; i < totalOneDimArry.length; i++) {
            if (i == 0) {
            } else if (i % 13 == 0) {
                row++;
                col = 0;
            } else {
                col++;
            }
            totalTwoDimArr[row][col] = totalOneDimArry[i];
        }
//        for(int i=0;i<9;i++){
//            for(int j=0;j<13;j++)
//        }

        int mentalAgeFromChecks = 0;
        int mentalAgeTotalInMonths = 0;
        int mentalAgeYears = 0;
        int mentalAgeMonths = 0;
        for (int i = 0; i < totalOneDimArry.length; i++) {
            mentalAgeFromChecks = mentalAgeFromChecks + totalOneDimArry[i];
        }
        if (mentalAgeFromChecks == 0) {
            boolean isPerosonCodeExist = mentalRetardationService.checkForPersonCode(ds, personcode, "tblPerson_MR_BKT_Detailed_Test_Details");
            if (isPerosonCodeExist) {
                //change the state to inactive
                CommonDAO.changeStatusToInactiveForResetInUpdate(ds, personcode, "tblPerson_MR_BKT_Test_Details");
                CommonDAO.changeStatusToInactiveForResetInUpdate(ds, personcode, "tblPerson_MR_BKT_Detailed_Test_Details");
                session.setAttribute("binetkamalresult", new Double(0));
            }
            target = "noinput";
        } else {
            boolean duplicateFormFlag = isTokenValid(request, true);
            if (duplicateFormFlag) {
                mentalAgeTotalInMonths = 24 + mentalAgeFromChecks;
                mentalAgeYears = mentalAgeTotalInMonths / 12;
                mentalAgeMonths = mentalAgeTotalInMonths % 12;

                int indexForBasalAge = 0;
                int flag = 0;
                for (int i = 0; i <= 13; i++) {
                    if (i == 13) {
                        indexForBasalAge = i;
                        break;
                    } else {
                        flag = checkCloumnForAllChecked(i, totalTwoDimArr);
                    }
                    if (flag == 0) {
                        indexForBasalAge = i;
                        break;
                    }
                }
                int basalAge = 0;
                switch (indexForBasalAge) {
                    case 1:
                        basalAge = 3;
                        break;
                    case 2:
                        basalAge = 4;
                        break;
                    case 3:
                        basalAge = 5;
                        break;
                    case 4:
                        basalAge = 6;
                        break;
                    case 5:
                        basalAge = 7;
                        break;
                    case 6:
                        basalAge = 8;
                        break;
                    case 7:
                        basalAge = 9;
                        break;
                    case 8:
                        basalAge = 10;
                        break;
                    case 9:
                        basalAge = 12;
                        break;
                    case 10:
                        basalAge = 14;
                        break;
                    case 11:
                        basalAge = 16;
                        break;
                    case 12:
                        basalAge = 19;
                        break;
                    case 13:
                        basalAge = 22;
                        break;
                }


                int indexForTerminalAge = -1;
                for (int i = 0; i <= 13; i++) {
                    if (i == 13) {
                        indexForTerminalAge = i;
                        break;
                    } else {
                        flag = checkCloumnForAllUnChecked(i, totalTwoDimArr);
                    }
                    if (flag == 1 && i == 0) {
                        break;
                    } else if (flag == 1) {
                        indexForTerminalAge = i;
                        break;
                    }

                }
                int terminalAge = 0;
                switch (indexForTerminalAge) {
                    case 0:
                        terminalAge = 3;
                        break;
                    case 1:
                        terminalAge = 4;
                        break;
                    case 2:
                        terminalAge = 5;
                        break;
                    case 3:
                        terminalAge = 6;
                        break;
                    case 4:
                        terminalAge = 7;
                        break;
                    case 5:
                        terminalAge = 8;
                        break;
                    case 6:
                        terminalAge = 9;
                        break;
                    case 7:
                        terminalAge = 10;
                        break;
                    case 8:
                        terminalAge = 12;
                        break;
                    case 9:
                        terminalAge = 14;
                        break;
                    case 10:
                        terminalAge = 16;
                        break;
                    case 11:
                        terminalAge = 19;
                        break;
                    case 12:
                        terminalAge = 22;
                        break;
                }


                request.setAttribute("basalAge", String.valueOf(basalAge));
                request.setAttribute("terminalAge", String.valueOf(terminalAge));
                request.setAttribute("mentalAgeYears", String.valueOf(mentalAgeYears));
                request.setAttribute("mentalAgeMonths", String.valueOf(mentalAgeMonths));


                MForm.setPersoncode(personcode);
                MForm.setSystemIP(request.getRemoteAddr());
                MForm.setLoginID((String) session.getAttribute("loginid"));

                BeanUtils.copyProperties(mRBinetKamatDetailedTestDTO, MForm);

                boolean isPerosonCodeExist = mentalRetardationService.checkForPersonCode(ds, personcode, "tblPerson_MR_BKT_Detailed_Test_Details");
                if (isPerosonCodeExist) {
                    //  if(session.getAttribute("sadaremCodeAu")!=null) {
                    //        mentalRetardationService.insertMRBinetKamatDetailedTestDetails(ds, mRBinetKamatDetailedTestDTO,request);
                    //   }else {
                    mentalRetardationService.updateMRBinetKamatDetailedTestDetails(ds, mRBinetKamatDetailedTestDTO, request);
                    //   }
                } else {
                    mentalRetardationService.insertMRBinetKamatDetailedTestDetails(ds, mRBinetKamatDetailedTestDTO, request);
                }
            }
            target = "success";
        }
        return mapping.findForward(target);
    }

    public int checkCloumnForAllChecked(int column, int[][] totalTwoDimArr) {
        int countForAllChecked = 0;
        int flag = 0;

        for (int i = 0; i < 9; i++) {
            countForAllChecked = countForAllChecked + totalTwoDimArr[i][column];
        }
        if (countForAllChecked == 12) {
            flag = 1;
        }
        if (countForAllChecked == 24) {
            flag = 1;
        }
        if (countForAllChecked == 36) {
            flag = 1;
        }
        return flag;
    }

    public int checkCloumnForAllUnChecked(int column, int[][] totalTwoDimArr) {
        int countForAllUnChecked = 0;
        int flag = 0;

        for (int i = 0; i < 9; i++) {
            countForAllUnChecked = countForAllUnChecked + totalTwoDimArr[i][column];
        }
        if (countForAllUnChecked == 0) {
            flag = 1;
        }

        return flag;
    }
}
