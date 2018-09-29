package org.bf.disability.action;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
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
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dto.VsmsScreeningTestDTO;
import org.bf.disability.form.VsmsScreeningTestActionForm;
import org.bf.disability.service.VsmsScreeningTestService;
import org.bf.disability.servicefactory.VsmsServiceFactory;
import org.bf.disability.service.MentalRetardationService;
import org.bf.disability.servicefactory.MentalRetardationServiceFactory;

/**
 * 
 * @author Sunima.dila
 * @Purpose This class is use for Inserting,Selecting,Updating the VSMSScreeningTestDetails.
 */
public class VsmsScreeningTestAction extends BaseDispatchAction {

    // VsmsScreeningTestActionForm vsmstestform=new VsmsScreeningTestActionForm();
    String personcode;
    String loginid = "";
    String systemip;
    String target = "success";
    DataSource datasource = null;

    /**
     * 
     * @param mapping ActionMapping
     * @param form VsmsScreeningTestActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws org.bf.disability.Exception.SADAREMException 
     * @This Method is use for Inserting the VSMSScrenningTestDetails
     */
    public ActionForward MRVsmsscreeningTestdetailsinsert(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException {


        String target = "success";
        int vsmsyear;
        double vsmsmonth;
        ActionMessages actionMessages = null;
        //DataSource datasource=getDataSource(request);


        HttpSession session = request.getSession(true);
        VsmsScreeningTestActionForm vsmstestform = (VsmsScreeningTestActionForm) form;

        try {
            datasource = getDataSource(request);
            if (datasource == null || "null".equals(datasource)) {
                datasource = JNDIDataSource.getConnection();
            }
            VsmsScreeningTestDTO vsmsscreeningtestdto = new VsmsScreeningTestDTO();
            VsmsScreeningTestService vsmsscreeningtestservice = VsmsServiceFactory.getVsmsScreeningTestServiceImpl();
            // personcode=(String)session.getAttribute("personcode");

            if (session.getAttribute("sadaremCodeAu") != null) {
                personcode = (String) session.getAttribute("sadaremCodeAu");
            } else {
                personcode = (String) session.getAttribute("personcode");
            }

            String loginid = (String) session.getAttribute("loginid");
            vsmstestform.setPersoncode(personcode);
            vsmstestform.setSystemiP(request.getRemoteAddr());
            vsmstestform.setLoginid(loginid);
            boolean isPerosonCodeExist = vsmsscreeningtestservice.checkPersonCode(datasource, personcode);
            if (isPerosonCodeExist == false) {
                vsmstestform = insertAndUpdateCalculationvsmsscreening(form, request);
                if (vsmstestform.getYear() > 0 || (vsmstestform.getMonth() > 0)) {
                    vsmsyear = vsmstestform.getYear();
                    vsmsmonth = vsmstestform.getMonth();

                    request.setAttribute("vsmsyear", new Integer(vsmsyear));
                    request.setAttribute("vsmsmonth", new Double(vsmsmonth));
                    try {
                        BeanUtils.copyProperties(vsmsscreeningtestdto, vsmstestform);
                    } catch (InvocationTargetException ex) {
                        ex.printStackTrace();
                        //throw new SADAREMDBException();
                    } catch (IllegalAccessException ex) {
                        ex.printStackTrace();
                        //throw new SADAREMDBException();
                    }

                    //  int vsmscal=vsmsscreeningtestservice.insertVsmsScreeningTest(datasource,vsmsscreeningtestdto,request);


                    int vsmscal = vsmsscreeningtestservice.insertVsmsScreeningTest(datasource, vsmsscreeningtestdto, request);

                    target = "success";
                } else {
                    target = "noinput";
                }
            } else if (session.getAttribute("sadaremCodeAu") != null) {
                vsmstestform = insertAndUpdateCalculationvsmsscreening(form, request);
                if (vsmstestform.getYear() > 0 || (vsmstestform.getMonth() > 0)) {
                    vsmsyear = vsmstestform.getYear();
                    vsmsmonth = vsmstestform.getMonth();

                    request.setAttribute("vsmsyear", new Integer(vsmsyear));
                    request.setAttribute("vsmsmonth", new Double(vsmsmonth));
                    try {
                        BeanUtils.copyProperties(vsmsscreeningtestdto, vsmstestform);
                    } catch (InvocationTargetException ex) {
                        ex.printStackTrace();
                        //throw new SADAREMDBException();
                    } catch (IllegalAccessException ex) {
                        ex.printStackTrace();
                        //throw new SADAREMDBException();
                    }

                    int vsmscal = vsmsscreeningtestservice.insertVsmsScreeningTestAU(datasource, vsmsscreeningtestdto, request);
                    if (vsmscal == -1) {
                        target = "failure";
                    } else {
                        target = "success";
                    }
                } else {
                    target = "noinput";
                }
            } else {
                target = "failure";
            }
        } catch (SADAREMDBException sADAREMException) {
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
     * @param mapping ActionMapping
     * @param form VsmsScreeningTestActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws org.bf.disability.Exception.SADAREMException
     * @This Method is use for Inserting the VSMSScrenningTestDetails
     */
    public ActionForward MRVsmsscreeningTestdetailsinsertAU(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException {


        String target = "success";
        int vsmsyear;
        double vsmsmonth;
        ActionMessages actionMessages = null;
        //DataSource datasource=getDataSource(request);


        HttpSession session = request.getSession(true);
        VsmsScreeningTestActionForm vsmstestform = (VsmsScreeningTestActionForm) form;

        try {
            datasource = getDataSource(request);
            if (datasource == null || "null".equals(datasource)) {
                datasource = JNDIDataSource.getConnection();
            }
            VsmsScreeningTestDTO vsmsscreeningtestdto = new VsmsScreeningTestDTO();
            VsmsScreeningTestService vsmsscreeningtestservice = VsmsServiceFactory.getVsmsScreeningTestServiceImpl();
            personcode = (String) session.getAttribute("sadaremCodeAu");

            String loginid = (String) session.getAttribute("loginid");
            vsmstestform.setPersoncode(personcode);
            vsmstestform.setSystemiP(request.getRemoteAddr());
            vsmstestform.setLoginid(loginid);
            boolean isPerosonCodeExist = vsmsscreeningtestservice.checkPersonCode(datasource, personcode);
            if (isPerosonCodeExist == false) {
                vsmstestform = insertAndUpdateCalculationvsmsscreening(form, request);
                if (vsmstestform.getYear() > 0 || (vsmstestform.getMonth() > 0)) {
                    vsmsyear = vsmstestform.getYear();
                    vsmsmonth = vsmstestform.getMonth();

                    request.setAttribute("vsmsyear", new Integer(vsmsyear));
                    request.setAttribute("vsmsmonth", new Double(vsmsmonth));
                    try {
                        BeanUtils.copyProperties(vsmsscreeningtestdto, vsmstestform);
                    } catch (InvocationTargetException ex) {
                        ex.printStackTrace();
                        //throw new SADAREMDBException();
                    } catch (IllegalAccessException ex) {
                        ex.printStackTrace();
                        //throw new SADAREMDBException();
                    }

                    int vsmscal = vsmsscreeningtestservice.insertVsmsScreeningTest(datasource, vsmsscreeningtestdto, request);

                    target = "success";
                } else {
                    target = "noinput";
                }
            } else {
                target = "failure";
            }
        } catch (SADAREMDBException sADAREMException) {
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
     * @This Method is use for retreving the details of VSMSScreeningtestdetails.
     * @param mapping ActionMapping
     * @throws java.io.IOException 
     * @throws java.lang.Exception 
     */
    public ActionForward getVsmsScreeningTestDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException {
        VsmsScreeningTestActionForm vsmstestform = (VsmsScreeningTestActionForm) form;
        //DataSource datasource=getDataSource(request);
        ActionMessages actionMessages = null;


        HttpSession session = request.getSession(true);
        try {
            datasource = getDataSource(request);
            if (datasource == null || "null".equals(datasource)) {
                datasource = JNDIDataSource.getConnection();
            }
            VsmsScreeningTestDTO vsmsscreeningtestdto = new VsmsScreeningTestDTO();
            VsmsScreeningTestService vsmsscreeningtestservice = VsmsServiceFactory.getVsmsScreeningTestServiceImpl();
            vsmstestform.setSystemiP(request.getRemoteAddr());
            //personcode=(String)session.getAttribute("personcode");

            if (session.getAttribute("sadaremCodeAu") != null) {
                personcode = (String) session.getAttribute("sadaremCodeAu");
            } else {
                personcode = (String) session.getAttribute("personcode");
            }


            vsmsscreeningtestdto = vsmsscreeningtestservice.getVsmsScreeningTest(datasource, personcode);

            BeanUtils.copyProperties(vsmstestform, vsmsscreeningtestdto);
            saveToken(request);
            target = "success";
        } catch (SADAREMDBException sADAREMException) {
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
     * @ This Method is use for Updating the VSMSScreeningTestDetails.
     * @param mapping ActionMapping
     * @param form VsmsScreeningTestActionForm
     * @throws java.io.IOException 
     */
    public ActionForward updateVsmsScreeningTestDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException {
        //DataSource datasource=getDataSource(request);


        String target = "success";
        int vsmsyear;
        double vsmsmonth;
        ActionMessages actionMessages = null;
        try {
            datasource = getDataSource(request);
            if (datasource == null || "null".equals(datasource)) {
                datasource = JNDIDataSource.getConnection();
            }
            boolean flag = isTokenValid(request, true);
            if (flag) {


                HttpSession session = request.getSession(true);
                VsmsScreeningTestActionForm vsmstestform = (VsmsScreeningTestActionForm) form;
                VsmsScreeningTestService vsmsscreeningtestservice = VsmsServiceFactory.getVsmsScreeningTestServiceImpl();
                MentalRetardationService mentalretarservice = MentalRetardationServiceFactory.getMentalRetardationServiceImpl();

                VsmsScreeningTestDTO vsmsscreeningtestdto = new VsmsScreeningTestDTO();
                // personcode = (String) session.getAttribute("personcode");

                if (session.getAttribute("sadaremCodeAu") != null) {
                    personcode = (String) session.getAttribute("sadaremCodeAu");
                } else {
                    personcode = (String) session.getAttribute("personcode");
                }


                String loginid = (String) session.getAttribute("loginid");
                vsmstestform.setPersoncode(personcode);
                vsmstestform.setLoginid(loginid);
                vsmstestform.setSystemiP(request.getRemoteAddr());
                boolean isPerosonCodeExist = vsmsscreeningtestservice.checkPersonCode(datasource, personcode);
                if (isPerosonCodeExist == false) {
                    vsmstestform = insertAndUpdateCalculationvsmsscreening(form, request);
                    //  if(vsmstestform.getYear()>0 || (vsmstestform.getMonth()>0))
                    // {
                    vsmsyear = vsmstestform.getYear();
                    vsmsmonth = vsmstestform.getMonth();
                    vsmstestform.setLoginid(loginid);
                    vsmstestform.setSystemiP(request.getRemoteAddr());
                    vsmstestform.setPersoncode(personcode);
                    request.setAttribute("vsmsyear", new Integer(vsmsyear));
                    request.setAttribute("vsmsmonth", new Double(vsmsmonth));
                    try {
                        BeanUtils.copyProperties(vsmsscreeningtestdto, vsmstestform);
                    } catch (InvocationTargetException ex) {
                        ex.printStackTrace();
                        //throw new SADAREMDBException();
                    } catch (IllegalAccessException ex) {
                        ex.printStackTrace();
                        //throw new SADAREMDBException();
                    }

                    int vsmscalculation = vsmsscreeningtestservice.insertVsmsScreeningTest(datasource, vsmsscreeningtestdto, request);

                    //   ArrayList disableMentalItemsVSMS = vsmsscreeningtestservice.getMentalItems(datasource,personcode);

                    /*   if(disableMentalItemsVSMS.size()>0) {
                    request.setAttribute("disableMentalItemsVSMS", disableMentalItemsVSMS);
                    }
                    ArrayList disableMentalItems = mentalretarservice.getMentalItems(datasource,personcode);

                    if(disableMentalItems.size()>0) {
                    request.setAttribute("disableMentalItems", disableMentalItems);
                    }*/

                } //else{
                // target="noinput";
                //}
                // }
                else {
                    vsmstestform = insertAndUpdateCalculationvsmsscreening(form, request);

                    BeanUtils.copyProperties(vsmsscreeningtestdto, vsmstestform);

                    vsmsscreeningtestdto.setPersoncode(personcode);
                    vsmsscreeningtestdto.setLoginid(loginid);
                    vsmsscreeningtestdto.setSystemip(request.getRemoteAddr());
                    //   if(session.getAttribute("sadaremCodeAu")!=null) {
                    //       vsmsscreeningtestservice.insertVsmsScreeningTest(datasource, vsmsscreeningtestdto,request);
                    //   }else {
                    vsmsscreeningtestservice.updateVsmsScreeningTest(datasource, vsmsscreeningtestdto, request);
                    //    }



                    vsmsyear = vsmstestform.getYear();
                    vsmsmonth = vsmstestform.getMonth();
                    request.setAttribute("vsmsyear", new Integer(vsmsyear));
                    request.setAttribute("vsmsmonth", new Double(vsmsmonth));

                }
//            if(vsmstestform.getMonth()==0) {
//                target="noinput";
//            }

            }

            target = "success";

        } catch (SADAREMDBException sADAREMException) {
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
     * @param form ActionForm
     * @ This Method is use for calculating the VSMSScreeningTestDetails.
     */
    public VsmsScreeningTestActionForm insertAndUpdateCalculationvsmsscreening(ActionForm form, HttpServletRequest request) {


        VsmsScreeningTestActionForm vsmstestform = (VsmsScreeningTestActionForm) form;
        double month = 0.0;
        int year = 0;
        int count = 0;
        int Vsms0to1CoolsLaughs = vsmstestform.getVsms_0to1_CoolsLaughs();

        int vsms0to1Balenceshead = vsmstestform.getVsms_0to1_Balenceshead();
        int Vsms0to1Graphsobject = vsmstestform.getVsms_0to1_Graphsobject();
        int Reachesforfamiliarpersons = vsmstestform.getVsms_0to1_Reachesforfamiliarpersons();
        int Vsms0to1Rolls = vsmstestform.getVsms_0to1_Rolls();
        int Vsms0to1Reachesforobjects = vsmstestform.getVsms_0to1_Reachesforobjects();
        int Vsms0to1Occupies = vsmstestform.getVsms_0to1_Occupies();
        int Vsms0to1Sits = vsmstestform.getVsms_0to1_Sits();
        int Vsms0to1pulls = vsmstestform.getVsms_0to1_Pulls();
        int Vsms0to1Talks = vsmstestform.getVsms_0to1_Talks();
        int Vsms0to1Drinks = vsmstestform.getVsms_0to1_Drinks();
        int Vsms0to1Moves = vsmstestform.getVsms_0to1_Moves();
        int Vsms0to1Grasps = vsmstestform.getVsms_0to1_Grasps();
        int Vsms0to1Demands = vsmstestform.getVsms_0to1_Demands();
        int Vsms0to1Stands = vsmstestform.getVsms_0to1_Stands();
        int Vsms0to1Doesnotdrool = vsmstestform.getVsms_0to1_Doesnotdrool();


        int Vsms0to1Follows = vsmstestform.getVsms_0to1_Follows();

        int count0to1 = Vsms0to1CoolsLaughs + vsms0to1Balenceshead + Vsms0to1Graphsobject + Reachesforfamiliarpersons + Vsms0to1Rolls + Vsms0to1Reachesforobjects + Vsms0to1Occupies + Vsms0to1Sits + Vsms0to1pulls + Vsms0to1Talks + Vsms0to1Drinks + Vsms0to1Moves + Vsms0to1Grasps + Vsms0to1Demands + Vsms0to1Stands + Vsms0to1Doesnotdrool + Vsms0to1Follows;

        int vsms1to2Walks = vsmstestform.getVsms_1to2_Walks();

        int Vsms1to2Marks = vsmstestform.getVsms_1to2_Marks();
        int Vsms1to2Masticates = vsmstestform.getVsms_1to2_Masticates();
        int Vsms1to2Pulls = vsmstestform.getVsms_1to2_Pulls();
        int vsms1to2Transfers = vsmstestform.getVsms_1to2_Transfers();
        int Vsms1to2Overcomessimpleobstacles = vsmstestform.getVsms_1to2_Overcomessimpleobstacles();
        int Vsms1to2Fetches = vsmstestform.getVsms_1to2_Fetches();
        int Vsms1to2Drinks = vsmstestform.getVsms_1to2_Drinks();
        int vsms1to2Walkswithoutsupport = vsmstestform.getVsms_1to2_Walkswithoutsupport();
        int Vsms1to2Plays = vsmstestform.getVsms_1to2_Plays();
        int Vsms1to2Eats = vsmstestform.getVsms_1to2_Eats();

        int Vsms1to2Goes = vsmstestform.getVsms_1to2_Goes();

        int Vsms1to2Discriminates = vsmstestform.getVsms_1to2_Discriminates();






        int vsms1to2Usesnames = vsmstestform.getVsms_1to2_Usesnames();

        int vsms1to2Walksupstairs = vsmstestform.getVsms_1to2_Walksupstairs();

        int vsms1to2Unwarps = vsmstestform.getVsms_1to2_Unwarps();
        int vsms1to2Talks = vsmstestform.getVsms_1to2_Talks();
        int count1to2 = vsms1to2Walks + Vsms1to2Marks + Vsms1to2Masticates + Vsms1to2Pulls + vsms1to2Transfers + Vsms1to2Overcomessimpleobstacles + Vsms1to2Fetches + Vsms1to2Drinks + vsms1to2Walkswithoutsupport + Vsms1to2Plays + Vsms1to2Eats + Vsms1to2Goes + Vsms1to2Discriminates + vsms1to2Usesnames + vsms1to2Walksupstairs + vsms1to2Unwarps + vsms1to2Talks;

        int vsms2to3Signals = vsmstestform.getVsms_2to3_Signals();

        int vsms2to3Initiates = vsmstestform.getVsms_2to3_Initiates();
        int vsms2to3Removesshirt = vsmstestform.getVsms_2to3_Removesshirt();
        int vsms2to3Eatswithspoon = vsmstestform.getVsms_2to3_Eatswithspoon();
        int vsms2to3Getsdrink = vsmstestform.getVsms_2to3_Getsdrink();
        int vsms2to3Dries = vsmstestform.getVsms_2to3_Dries();
        int vsms2to3Avoids = vsmstestform.getVsms_2to3_Avoids();
        int vsms2to3Putsonshirt = vsmstestform.getVsms_2to3_Putsonshirt();
        int vsms2to3Candopaperfolding = vsmstestform.getVsms_2to3_Candopaperfolding();
        int vsms2to3Relates = vsmstestform.getVsms_2to3_Relates();

        int count2to3 = vsms2to3Signals + vsms2to3Initiates + vsms2to3Removesshirt + vsms2to3Eatswithspoon + vsms2to3Getsdrink + vsms2to3Dries + vsms2to3Avoids + vsms2to3Putsonshirt + vsms2to3Candopaperfolding + vsms2to3Relates;

        int vsms3to4Walksdownsstairs = vsmstestform.getVsms_3to4_Walksdownsstairs();
        int vsms3to4Playscooperatively = vsmstestform.getVsms_3to4_Playscooperatively();
        int vsms3to4Buttons = vsmstestform.getVsms_3to4_Buttons();
        int vsms3to4Helps = vsmstestform.getVsms_3to4_Helps();
        int vsms3to4Performs = vsmstestform.getVsms_3to4_Performs();
        int vsms3to4Washes = vsmstestform.getVsms_3to4_Washes();
        int count3to4 = vsms3to4Walksdownsstairs + vsms3to4Playscooperatively + vsms3to4Buttons + vsms3to4Helps + vsms3to4Performs + vsms3to4Washes;


        int vsms4to5Cares = vsmstestform.getVsms_4to5_Cares();
        int vsms4to5Prints = vsmstestform.getVsms_4to5_Prints();
        int vsms4to5Goesaboutneighbourhood = vsmstestform.getVsms_4to5_Goesaboutneighbourhood();
        int vsms4to5Dresses = vsmstestform.getVsms_4to5_Dresses();
        int vsms4to5Usespencil = vsmstestform.getVsms_4to5_Usespencil();
        int vsms4to5Playscompetitive = vsmstestform.getVsms_4to5_Playscompetitive();
        int count4to5 = vsms4to5Cares + vsms4to5Prints + vsms4to5Goesaboutneighbourhood + vsms4to5Dresses + vsms4to5Usespencil + vsms4to5Playscompetitive;

        int vsms5to6Useshoops = vsmstestform.getVsms_5to6_Useshoops();
        int vsms5to6Printssimplewords = vsmstestform.getVsms_5to6_Printssimplewords();
        int vsms5to6Playssimplegames = vsmstestform.getVsms_5to6_Playssimplegames();
        int vsms5to6trusted = vsmstestform.getVsms_5to6_trusted();
        int vsms5to6Goestoschool = vsmstestform.getVsms_5to6_Goestoschool();

        int count5to6 = vsms5to6Useshoops + vsms5to6Printssimplewords + vsms5to6Playssimplegames + vsms5to6trusted + vsms5to6Goestoschool;

        int vsms6to7Mixes = vsmstestform.getVsms_6to7_Mixes();
        int vsms6to7Usespencilorchalk = vsmstestform.getVsms_6to7_Usespencilorchalk();
        int vsms6to7Batches = vsmstestform.getVsms_6to7_Batches();
        int vsms6to7Goestobed = vsmstestform.getVsms_6to7_Goestobed();

        int count6to7 = vsms6to7Mixes + vsms6to7Usespencilorchalk + vsms6to7Batches + vsms6to7Goestobed;

        int vsms7to8Candifferentiatebetween = vsmstestform.getVsms_7to8_Candifferentiatebetween();
        int vsms7to8Helps = vsmstestform.getVsms_7to8_Helps();
        int vsms7to8Understands = vsmstestform.getVsms_7to8_Understands();
        int vsms7to8participates = vsmstestform.getVsms_7to8_Participates();
        int vsms7to8Combs = vsmstestform.getVsms_7to8_Combs();
        int count7to8 = vsms7to8Candifferentiatebetween + vsms7to8Helps + vsms7to8Understands + vsms7to8participates + vsms7to8Combs;

        int vsms8to9Usestools = vsmstestform.getVsms_8to9_Usestools();
        int vsms8to9routinehouseholdtasks = vsmstestform.getVsms_8to9_routinehouseholdtasks();
        int vsms8to9Readsonowninitiative = vsmstestform.getVsms_8to9_Readsonowninitiative();
        int vsms8to9Batchesselfunaided = vsmstestform.getVsms_8to9_Batchesselfunaided();
        int count8to9 = vsms8to9Usestools + vsms8to9routinehouseholdtasks + vsms8to9Readsonowninitiative + vsms8to9Batchesselfunaided;

        int vsms9to10Caresforself = vsmstestform.getVsms_9to10_Caresforself();
        int vsms9to10Makesminorpurchases = vsmstestform.getVsms_9to10_Makesminorpurchases();
        int vsms9to10Goesabouthome = vsmstestform.getVsms_9to10_Goesabouthome();
        int count9to10 = vsms9to10Caresforself + vsms9to10Makesminorpurchases + vsms9to10Goesabouthome;

        int vsms10to11Distinguishes = vsmstestform.getVsms_10to11_Distinguishes();
        int vsms10to11Makesindependentchoice = vsmstestform.getVsms_10to11_Makesindependentchoice();
        int vsms10to11smallremunerativework = vsmstestform.getVsms_10to11_smallremunerativework();
        int vsms10to11Follows = vsmstestform.getVsms_10to11_Follows();
        int count10to11 = vsms10to11Distinguishes + vsms10to11Makesindependentchoice + vsms10to11smallremunerativework + vsms10to11Follows;

        int vsms11to12simplecreative = vsmstestform.getVsms_11to12_simplecreative();
        int vsms11to12lefttocare = vsmstestform.getVsms_11to12_lefttocare();
        int vsms11to12Enjoys = vsmstestform.getVsms_11to12_Enjoys();
        int count11to12 = vsms11to12simplecreative + vsms11to12lefttocare + vsms11to12Enjoys;

        int vsms12to15Playsdifficult = vsmstestform.getVsms_12to15_Playsdifficult();
        int vsms12to15Exercisescomplete = vsmstestform.getVsms_12to15_Exercisescomplete();
        int vsms12to15Buys = vsmstestform.getVsms_12to15_Buys();
        int vsms12to15Engages = vsmstestform.getVsms_12to15_Engages();
        int vsms12to15Performs = vsmstestform.getVsms_12to15_Performs();
        int count12to15 = vsms12to15Playsdifficult + vsms12to15Exercisescomplete + vsms12to15Buys + vsms12to15Engages + vsms12to15Performs;


        count = count0to1 + count1to2 + count2to3 + count3to4 + count4to5 + count5to6 + count6to7 + count7to8 + count8to9 + count9to10 + count10to11 + count11to12 + count12to15;





        switch (count) {
            case 1:
                month = 0.7;
                break;
            case 2:
                month = 1.4;
                break;
            case 3:
                month = 2.1;
                break;
            case 4:
                month = 2.8;
                break;
            case 5:
                month = 3.5;
                break;
            case 6:
                month = 4.2;
                break;
            case 7:
                month = 4.9;
                break;
            case 8:
                month = 5.6;
                break;
            case 9:
                month = 6.3;
                break;
            case 10:
                month = 7.0;
                break;
            case 11:
                month = 7.7;
                break;
            case 12:
                month = 8.4;
                break;
            case 13:
                month = 9.1;
                break;
            case 14:
                month = 9.8;
                break;
            case 15:
                month = 10.6;
                break;
            case 16:
                month = 11.3;
                break;
            case 17:
                month = 12.0;
                break;

            case 18:
                year = 1;
                month = 0.4;
                break;

            case 19:
                year = 1;
                month = 1.4;
                break;
            case 20:
                year = 1;
                month = 2.1;
                break;
            case 21:
                year = 1;
                month = 2.8;
                break;
            case 22:
                year = 1;
                month = 3.5;
                break;
            case 23:
                year = 1;
                month = 4.2;
                break;
            case 24:
                year = 1;
                month = 4.9;
                break;
            case 25:
                year = 1;
                month = 5.6;
                break;
            case 26:
                year = 1;
                month = 6.3;
                break;
            case 27:
                year = 1;
                month = 7.0;
                break;
            case 28:
                year = 1;
                month = 7.7;
                break;
            case 29:
                year = 1;
                month = 8.4;
                break;
            case 30:
                year = 1;
                month = 9.2;
                break;
            case 31:
                year = 1;
                month = 9.8;
                break;
            case 32:
                year = 1;
                month = 10.6;
                break;
            case 33:
                year = 1;
                month = 11.3;
                break;
            case 34:
                year = 1;
                month = 12.0;
                break;
            case 35:
                year = 2;
                month = 1.2;
                break;
            case 36:
                year = 2;
                month = 2.4;
                break;
            case 37:
                year = 2;
                month = 3.6;
                break;
            case 38:
                year = 2;
                month = 4.8;
                break;
            case 39:
                year = 2;
                month = 6.0;
                break;
            case 40:
                year = 2;
                month = 7.2;
                break;
            case 41:
                year = 2;
                month = 8.4;
                break;
            case 42:
                year = 2;
                month = 9.6;
                break;
            case 43:
                year = 2;
                month = 10.8;
                break;
            case 44:
                year = 2;
                month = 12.0;
                break;
            case 45:
                year = 3;
                month = 2;
                break;
            case 46:
                year = 3;
                month = 4;
                break;
            case 47:
                year = 3;
                month = 6;
                break;
            case 48:
                year = 3;
                month = 8;
                break;
            case 49:
                year = 3;
                month = 10;
                break;
            case 50:
                year = 3;
                month = 12;
                break;
            case 51:
                year = 4;
                month = 2;
                break;
            case 52:
                year = 4;
                month = 4;
                break;
            case 53:
                year = 4;
                month = 6;
                break;
            case 54:
                year = 4;
                month = 8;
                break;
            case 55:
                year = 4;
                month = 10;
                break;
            case 56:
                year = 4;
                month = 12;
                break;
            case 57:
                year = 5;
                month = 2.4;
                break;
            case 58:
                year = 5;
                month = 4.8;
                break;
            case 59:
                year = 5;
                month = 7.2;
                break;
            case 60:
                year = 5;
                month = 9.6;
                break;
            case 61:
                year = 5;
                month = 12.0;
                break;
            case 62:
                year = 6;
                month = 3;
                break;
            case 63:
                year = 6;
                month = 6;
                break;
            case 64:
                year = 6;
                month = 9;
                break;
            case 65:
                year = 6;
                month = 12;
                break;
            case 66:
                year = 7;
                month = 2.4;
                break;
            case 67:
                year = 7;
                month = 4.8;
                break;
            case 68:
                year = 7;
                month = 7.2;
                break;
            case 69:
                year = 7;
                month = 9.6;
                break;
            case 70:
                year = 7;
                month = 12.0;
                break;
            case 71:
                year = 8;
                month = 3;
                break;
            case 72:
                year = 8;
                month = 6;
                break;
            case 73:
                year = 8;
                month = 9;
                break;
            case 74:
                year = 8;
                month = 12;
                break;
            case 75:
                year = 9;
                month = 4;
                break;
            case 76:
                year = 9;
                month = 8;
                break;
            case 77:
                year = 9;
                month = 12;
                break;
            case 78:
                year = 10;
                month = 3;
                break;
            case 79:
                year = 10;
                month = 6;
                break;
            case 80:
                year = 10;
                month = 9;
                break;
            case 81:
                year = 10;
                month = 12;
                break;
            case 82:
                year = 11;
                month = 4;
                break;
            case 83:
                year = 11;
                month = 8;
                break;
            case 84:
                year = 11;
                month = 12;
                break;
            case 85:
                year = 12;
                month = 7.2;
                break;
            case 86:
                year = 12;
                month = 14.4;
                break;
            case 87:
                year = 12;
                month = 21.6;
                break;
            case 88:
                year = 12;
                month = 28.8;
                break;
            case 89:
                year = 12;
                month = 36.0;
                break;

        }

        vsmstestform.setYear(year);
        vsmstestform.setMonth(month);

        return vsmstestform;


    }
}

