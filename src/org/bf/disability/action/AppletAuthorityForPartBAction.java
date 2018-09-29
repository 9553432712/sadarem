/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.CommonDetails;
import org.bf.disability.common.CommonService;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.AppletAuthorityDAO;
import org.bf.disability.dao.PartADAO;
import org.bf.disability.dao.TerritoryDAO;
import org.bf.disability.form.AppletAuthorityForPartBForm;
import org.bf.disability.service.AppletAuthorityService;
import org.bf.disability.service.TerritoryService;
import org.bf.disability.service.TransactionService;
import org.bf.disability.servicefactory.AppletAuthorityServiceFactory;
import org.bf.disability.servicefactory.TerritoryServiceFactory;
import org.bf.disability.servicefactory.TransactionFactory;

import com.tcs.sadarem.common.DAO.CommonDAOImpl;

/**
 *
 * @author 484898
 */
public class AppletAuthorityForPartBAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException {

        AppletAuthorityForPartBForm partAForm = (AppletAuthorityForPartBForm) form;
        DataSource ds = null;

        String target = "target";
        ArrayList personalDetails = new ArrayList();
        HttpSession session = request.getSession();
        String selectFlow = "OUTERPROCESS";
        if (request.getParameter("selectFlow") != null) {
            selectFlow = request.getParameter("selectFlow");
        } else if (request.getParameter("selectedValue") != null) {
            selectFlow = request.getParameter("selectedValue");
        }
        request.setAttribute("selectedValue", selectFlow);
        int updatePersonStatus = 0;
        int updatePersonStatusAppeal = 0;
        int rationCardDetailsUpdate = 0;
        String personStatusViewEdit = null;
        int updatePersonStatusForAppleate = 0;
        String categoryId = null;
        String categoryCount = null;
        String ageAU = null;
        int checkStatusAppellateAuthority = 0;
        String districtName = null;
        String extension = null;
        String strDirectoy = null;
        String upDistrictName = null;
        String powerCutIds = null;
        String selectflow = null;



        try { ds = getDataSource(request);
        if (ds == null || "null".equals(ds)) {
            ds = JNDIDataSource.getConnection();
        }


            // Created by mohan on 07/02/2012
            TransactionService transactionService = TransactionFactory.getTransaction();
            AppletAuthorityService appletAuthorityService = AppletAuthorityServiceFactory.getAppletAuthorityServiceImpl();


            TerritoryDAO terrority = new TerritoryDAO();
            AppletAuthorityDAO apDAO = new AppletAuthorityDAO();
            CommonService commonservice = new CommonService(); 

            if ("getValues".equalsIgnoreCase(partAForm.getMode())) 
            {

                selectflow = partAForm.getSelectFlow();
                selectflow = request.getParameter("selectFlow");
                if (selectflow != null && selectflow.equalsIgnoreCase("appellate"))
                {
                    categoryId = "2";
                }
                else if (selectflow != null && selectflow.equalsIgnoreCase("temporay"))
                {
                    categoryId = "3";
                }





                checkStatusAppellateAuthority = appletAuthorityService.checkStatusForAppealCheck(ds, partAForm.getSadaremCode(), categoryId);
                districtName = terrority.getDistrictsName(ds, (String) session.getAttribute("districtId"));
                
                CommonDAOImpl comObj = new CommonDAOImpl();
                HashMap GEODtls = new HashMap();
                
				GEODtls=comObj.getGEODetailsbySADAREMID(partAForm.getSadaremCode()); 
				String distIdFromSadarem="";
				
			//	System.out.println(GEODtls+"==="+GEODtls.get("districtid")+" "+partAForm.getSadaremCode());
				
			
				
				if(GEODtls.get("districtid")!=null){
					
				 distIdFromSadarem	=	GEODtls.get("districtid").toString();
				
				}
	
				
				if (distIdFromSadarem.equals(session.getAttribute("districtId")))
				{

                    CommonDetails commonDetails = new CommonDetails();
                    String url = getServlet().getServletContext().getRealPath("/");
                    commonDetails.copyPhotoDtoRelativePath(partAForm.getSadaremCode(), districtName, request, url);


                    if (checkStatusAppellateAuthority != 0) 
                    {

                        session.setAttribute("sadaremCodeAu", partAForm.getSadaremCode());
                        
                        //HERE WE NEED TO CHECK FOR THE POWER CUT PROBLEMS OR TRANSACTION IN-COMPLETE
                        powerCutIds = appletAuthorityService.getPowerCutIds(ds, "Completed", partAForm.getSadaremCode(), request);
                        
                        if (powerCutIds != null && powerCutIds.length() > 0) 
                        { 
                            appletAuthorityService.powerCutIds(ds, powerCutIds, request); 
                        }
                        
                        
                        /** Check personCode in appellate temporary details*/
                        personStatusViewEdit = appletAuthorityService.checkPersonForEligibility(ds, partAForm.getSadaremCode(), categoryId);

                        if (Integer.parseInt(session.getAttribute("roleId").toString()) == 5) 
                        {
                            personStatusViewEdit = "Edit";
                        }

                        if (personStatusViewEdit != null) 
                        {
                        	 
                        	
                           if(personStatusViewEdit.equals("Edit") )  
                            {

                                personalDetails = appletAuthorityService.getAppletAuthorityDetails(ds, partAForm.getSadaremCode(), request);

                                if (personalDetails.size() > 0) 
                                {
                                    //addded by kavya
                                    Map m = (Map) personalDetails.get(0);

                                    if (m.get("rationcard") != null && m.get("rationcard").toString().trim().length() > 0) {
                                        request.setAttribute("RationReadOnly", "true");
                                    }
                                    if (m.get("rationcard_slno") != null && m.get("rationcard_slno").toString().trim().length() > 0) {
                                        request.setAttribute("SlNoReadOnly", "true");
                                    }
                                    //ended
                                    request.setAttribute("personDetails", personalDetails);

                                    /** Getting the age from person_personal_details */
                                    ageAU = appletAuthorityService.getAge(ds, partAForm.getSadaremCode());
                                    session.setAttribute("ageAU", ageAU);

                                    /** method for updating the view_edit_mode status edit to view in person_personal_details*/
                                    //   personStatus = appletAuthorityService.checkPersonCode(ds, partAForm.getSadaremCode());
                                    /** Method for getting the categoryId,categoryCount based on entered id */
                                    //    categoryId = appletAuthorityService.getCategoryId(ds, partAForm.getSadaremCode());
                                    //For both Temporary and Appellate flow ,added categoryid to get maximum cagegorycount
                                    categoryCount = appletAuthorityService.getCategoryCount(ds, partAForm.getSadaremCode(), categoryId);

                                    session.setAttribute("categoryIdAu", categoryId);
                                    session.setAttribute("categoryCountAu", categoryCount);

                                    target = "success";

                                } 
                                else 
                                {
                                    if (categoryId != null && categoryId.equalsIgnoreCase("2")) 
                                    {
                                        request.setAttribute("msg", "You are not permitted for the Second Time Re-Assessment");
                                    }
                                    else 
                                    {
                                        request.setAttribute("msg", "Please Enter Valid Number");
                                    }
                                    target = "failure";
                                }
                            } 
                            else
                            {

                                request.setAttribute("msg", "Data Already Entered");
                                target = "failure";
                            }
                            
                        } 
                        else 
                        {
                            request.setAttribute("msg", "Invalid Person Code");
                            target = "failure";
                        }
                        
                    } 
                    else
                    {
                        request.setAttribute("msg", "You Have not been registrred for Second Time Re-Assessment");
                        //request.setAttribute("msg", "Please Enter Valid Number");
                        target = "failure";
                    }
                } 
				else 
                {
                    request.setAttribute("msg", "Your are not authorized to access other district SADAREM ID");
                    target = "failure";
                }
            } 
            else if ("goForPartB".equalsIgnoreCase(partAForm.getMode()))
            {

                //added by kavya

                if (partAForm.getRationCardNo() != null && partAForm.getRationCardSlNo() != null && request.getParameter("readonly") != null) 
                {
                    URL url1 = new URL(CommonConstants.Url1);
                    PartADAO partADAO = new PartADAO();
                   // AponlineWSClient wsClient = new AponlineWSClient();
                    String message = null;
                    String rationcardtype = partAForm.getRationCardNo().substring(0, 3);
                    String districtid = (String) session.getAttribute("districtId");
//                    if (rationcardtype.equalsIgnoreCase("RAP") || rationcardtype.equalsIgnoreCase("TAP")) {
                        message = partADAO.getRAPTAPRationCardSerialNumberStatus(ds, partAForm.getRationCardNo().toString(), partAForm.getRationCardSlNo().toString(), districtid);
//                    } else {
                        //message = wsClient.getRationcardSerialNumbers(ds,url1, url1, partAForm.getRationCardNo().toString(), partAForm.getRationCardSlNo().toString(), request);
//                    }


                    if (message != null && message.equalsIgnoreCase(CommonConstants.SUCCESS)) {
                        message = partADAO.getAssesmentStatusRationcardAndSlnoStatus(ds, partAForm.getRationCardNo().toString(), partAForm.getRationCardSlNo().toString(), session.getAttribute("sadaremCodeAu").toString());

                    }
                    if (message != null && message.equalsIgnoreCase(CommonConstants.ERROR)) {
                        request.setAttribute("MSG", "You Have Entered Invalid Rationcard serial number ");

                        return mapping.findForward("exception");
                    } else if (message != null && !message.equalsIgnoreCase(CommonConstants.SUCCESS)) {
                        request.setAttribute("MSG", message);

                        return mapping.findForward("exception");
                    }
                }
//ended by kavya
                session.setAttribute("personStatusForAU", partAForm.getPersonStatusForAU());
                //partAdto.setPersonstatus(partAForm.getPersonStatusForAU());

                /** Inactive the all the fields in previous assement data, when user click on next button */
                apDAO.deletePreviousDisabilityDetailsForUpdateDisability(ds, session.getAttribute("sadaremCodeAu").toString());

                appletAuthorityService.getAge(ds, session.getAttribute("sadaremCodeAu").toString(), partAForm);

                //Chrononical age  calculation for Mental Retardation tests.
                Date d1 = new GregorianCalendar(Integer.parseInt(partAForm.getYear()), Integer.parseInt(partAForm.getMonth()), Integer.parseInt(partAForm.getDay())).getTime();
                Date today = new Date();
                long diff = today.getTime() - d1.getTime();
                long days = diff / (1000 * 60 * 60 * 24);
                double years = (double) days / 365;

                session.setAttribute("chronologicalageAU", new Double(years));
             
                if (partAForm.getPersonStatusForAU().equals("Eligible"))
                {

                    /** Updating the person Status Eligible / Rejected Based on selection and update categoryid and categorycount in tblperson_personal_details table*/
                    updatePersonStatus = appletAuthorityService.updatePersonStatus(ds, partAForm.getPersonStatusForAU(), session.getAttribute("sadaremCodeAu").toString(), session.getAttribute("categoryIdAu").toString(), session.getAttribute("categoryCountAu").toString(), partAForm.getRationCardNo().toString(), partAForm.getRationCardSlNo().toString(), request);

                    /** method for updating the view_edit_mode,updated_date,edit to view  and Active to Inactive in AppellateAuthorityandTemporary_RegistrationDetails*/
                    updatePersonStatusForAppleate = appletAuthorityService.checkPersonStatusForAppealAuthority(ds, session.getAttribute("sadaremCodeAu").toString(), partAForm.getPersonStatusForAU());

                    if (updatePersonStatusForAppleate != 0) {

                        transactionService.insertTransactionalDetails(ds, "Appeal Personal Details Entered", session.getAttribute("sadaremCodeAu").toString(), request);
                    }

                    /** Updating the person Status Eligible / Rejected Based on selection in AppellateAuthorityandTemporary_RegistrationDetails table*/
                    // updatePersonStatusAppeal = appletAuthorityService.updatePersonStatusAppeal(ds, partAForm.getPersonStatusForAU(), session.getAttribute("sadaremCodeAu").toString());
                    /** Updating the RationCard Details */
                    // rationCardDetailsUpdate = appletAuthorityService.updateRationCardDetails(ds, session.getAttribute("sadaremCodeAu").toString(), partAForm.getRationCardNo().toString(), partAForm.getRationCardSlNo().toString(), request);
                    /** Photo Upload **/
                    try {

                        TerritoryService territoryservice = TerritoryServiceFactory.getTerritoryServiceImpl();
                        upDistrictName = territoryservice.getDistrictsName(ds, (String) session.getAttribute("districtId"));

                        commonservice.insertUploadPhoto(ds, session.getAttribute("sadaremCodeAu").toString(), (String) session.getAttribute("loginid"));
                        FormFile myFile = partAForm.getPhotoUpload();
                        String fileName = myFile.getFileName();

                        // Added by mohan
                        long filesizeInKB = myFile.getFileSize() / 1024;
                        if (!fileName.equals("")) {
                            if (filesizeInKB < 150) {
                                //End of Added Mohan
//                                int dotPos = fileName.lastIndexOf(".");
//                                fileName = session.getAttribute("sadaremCodeAu").toString() + ".JPG";
//
//                                String url = getServlet().getServletContext().getRealPath("/");
//                                String strDirectoytemp = "D:\\SADAREMTG\\SADAREMPHOTOS_APPELLATE\\" + upDistrictName;
//                                int urlLength = url.length();
//                                int portLocal = request.getLocalPort();
//                                if (portLocal == 8084) {
//                                } else if (portLocal == 8011 || portLocal == 8080 || portLocal == 80) {
//                                    strDirectoy = url + "DisabilityUITG\\uploadedphotos\\" + upDistrictName + "";
//                                    // strDirectoy=url+"DisabilityUITG\\uploadedphotos";
//                                }
//
//
//                                File directory = new File(strDirectoy);
//                                if (!directory.exists()) {
//                                    directory.mkdirs();
//                                }
//
//                                File fileToCreate = new File(strDirectoy, fileName);
//                                //If file does not exists create file
//                                FileOutputStream fileOutStream = new FileOutputStream(fileToCreate);
//                                fileOutStream.write(myFile.getFileData());
//                                fileOutStream.flush();
//                                fileOutStream.close();
//                                if (strDirectoytemp != null && !"".equals(strDirectoytemp)) {
//                                    File directorytemp = new File(strDirectoytemp);
//                                    if (!directorytemp.exists()) {
//                                        directorytemp.mkdirs();
//                                    }
//                                    File fileToCreatetemp = new File(strDirectoytemp, fileName);
//                                    //If file does not exists create file
//                                    FileOutputStream fileOutStreamtemp = new FileOutputStream(fileToCreatetemp);
//                                    fileOutStreamtemp.write(myFile.getFileData());
//                                    fileOutStreamtemp.flush();
//                                    fileOutStreamtemp.close();
//                                }
                                int dotPos = fileName.lastIndexOf(".");
                                extension = fileName.substring(dotPos);
                                fileName = session.getAttribute("sadaremCodeAu").toString() + ".JPG";

                                String url = getServlet().getServletContext().getRealPath("/");
                                String strDirectoytemp = "D:\\SADAREMTG\\SADAREMPHOTOS_APPELLATE";


                                File dir = null;
                                File subdir = null;
                                File subdir1 = null;
                                File distdir = null;


                                subdir = new File(getServlet().getServletContext().getRealPath("/") + "DisabilityUITG");
                                if (!subdir.exists()) {
                                    subdir.mkdir();
                                }
                                subdir1 = new File(subdir + "/uploadedphotos");
                                if (!subdir1.exists()) {
                                    subdir1.mkdir();
                                }

                                distdir = new File(subdir1 + "/" + upDistrictName);
                                if (!distdir.exists()) {
                                    distdir.mkdir();
                                }

                                CommonDetails.uploadingFile(partAForm.getPhotoUpload(), "" + distdir, fileName);

                                File tempdir = new File(strDirectoytemp);
                                if (!tempdir.exists()) {
                                    tempdir.mkdir();
                                }
                                File tempdistdir = new File(tempdir + "/" + upDistrictName);
                                if (!tempdistdir.exists()) 
                                {
                                    tempdistdir.mkdir();
                                }
                                CommonDetails.uploadingFile(partAForm.getPhotoUpload(), "" + tempdistdir, fileName);



                            } else {
                                request.setAttribute("msg", "Photo Size Must be Less Than 150 KB");
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    /** End **/
                    if (updatePersonStatus > 0) {

                        target = "finish";

                    } else {

                        target = "failure";

                    }
                } else {


                    /** Updating the person Status Eligible / Rejected Based on selection and update categoryid and categorycount in tblPerson_personal_details*/
                    updatePersonStatus = appletAuthorityService.updatePersonStatus(ds, partAForm.getPersonStatusForAU(), session.getAttribute("sadaremCodeAu").toString(), session.getAttribute("categoryIdAu").toString(), session.getAttribute("categoryCountAu").toString(), partAForm.getRationCardNo().toString(), partAForm.getRationCardSlNo().toString(), request);

                    /** method for updating the view_edit_mode,updated_date,edit to view ,Active to Inactive in AppellateAuthorityandTemporary_RegistrationDetails*/
                    updatePersonStatusForAppleate = appletAuthorityService.checkPersonStatusForAppealAuthority(ds, session.getAttribute("sadaremCodeAu").toString(), partAForm.getPersonStatusForAU());
                    if (updatePersonStatusForAppleate != 0) {
                        transactionService.insertTransactionalDetails(ds, "Error in Appeal Personal Details Entered", session.getAttribute("sadaremCodeAu").toString(), request);
                    }

                    /** Updating the person Status Eligible / Rejected Based on selection in AppellateAuthorityandTemporary_RegistrationDetails table*/
                    //   updatePersonStatusAppeal = appletAuthorityService.updatePersonStatusAppeal(ds, partAForm.getPersonStatusForAU(), session.getAttribute("sadaremCodeAu").toString());
                    /** Updating the RationCard Details */
                    // rationCardDetailsUpdate = appletAuthorityService.updateRationCardDetails(ds, session.getAttribute("sadaremCodeAu").toString(), partAForm.getRationCardNo().toString(), partAForm.getRationCardSlNo().toString(), request);
                    /** Photo Upload **/
                    try {

                        TerritoryService territoryservice = TerritoryServiceFactory.getTerritoryServiceImpl();
                        upDistrictName = territoryservice.getDistrictsName(ds, (String) session.getAttribute("districtId"));

                        commonservice.insertUploadPhoto(ds, session.getAttribute("sadaremCodeAu").toString(), (String) session.getAttribute("loginid"));
                        FormFile myFile = partAForm.getPhotoUpload();
                        String fileName = myFile.getFileName();

                        // Added by mohan
                        long filesizeInKB = myFile.getFileSize() / 1024;
                        if (!fileName.equals("")) {
                            if (filesizeInKB < 150) {
                                //End of Added Mohan
//                                int dotPos = fileName.lastIndexOf(".");
//                                fileName = session.getAttribute("sadaremCodeAu").toString() + ".JPG";
//
//                                String url = getServlet().getServletContext().getRealPath("/");
//                                String strDirectoytemp = "D:\\SADAREMTG\\SADAREMPHOTOS_APPELLATE\\" + upDistrictName;
//                                int urlLength = url.length();
//                                int portLocal = request.getLocalPort();
//                                } else if (portLocal == 8011 || portLocal == 8080 || portLocal == 80) {
//                                    strDirectoy = url + "DisabilityUITG\\uploadedphotos\\" + upDistrictName + "";
//                                    // strDirectoy=url+"DisabilityUITG\\uploadedphotos";
//                                }
//
//
//                                File directory = new File(strDirectoy);
//                                if (!directory.exists()) {
//                                    directory.mkdirs();
//                                }
//
//                                File fileToCreate = new File(strDirectoy, fileName);
//                                //If file does not exists create file
//                                FileOutputStream fileOutStream = new FileOutputStream(fileToCreate);
//                                fileOutStream.write(myFile.getFileData());
//                                fileOutStream.flush();
//                                fileOutStream.close();
//                                if (strDirectoytemp != null && !"".equals(strDirectoytemp)) {
//                                    File directorytemp = new File(strDirectoytemp);
//                                    if (!directorytemp.exists()) {
//                                        directorytemp.mkdirs();
//                                    }
//                                    File fileToCreatetemp = new File(strDirectoytemp, fileName);
//                                    //If file does not exists create file
//                                    FileOutputStream fileOutStreamtemp = new FileOutputStream(fileToCreatetemp);
//                                    fileOutStreamtemp.write(myFile.getFileData());
//                                    fileOutStreamtemp.flush();
//                                    fileOutStreamtemp.close();
//                                }
                                int dotPos = fileName.lastIndexOf(".");
                                extension = fileName.substring(dotPos);
                                fileName = session.getAttribute("sadaremCodeAu").toString() + ".JPG";

                                String url = getServlet().getServletContext().getRealPath("/");
                                String strDirectoytemp = "D:\\SADAREMTG\\SADAREMPHOTOS_APPELLATE";


                                File dir = null;
                                File subdir = null;
                                File subdir1 = null;
                                File distdir = null;


                                subdir = new File(getServlet().getServletContext().getRealPath("/") + "DisabilityUITG");
                                if (!subdir.exists()) {
                                    subdir.mkdir();
                                }
                                subdir1 = new File(subdir + "/uploadedphotos");
                                if (!subdir1.exists()) {
                                    subdir1.mkdir();
                                }

                                distdir = new File(subdir1 + "/" + upDistrictName);
                                if (!distdir.exists()) {
                                    distdir.mkdir();
                                }

                                CommonDetails.uploadingFile(partAForm.getPhotoUpload(), "" + distdir, fileName);

                                File tempdir = new File(strDirectoytemp);
                                if (!tempdir.exists()) {
                                    tempdir.mkdir();
                                }
                                File tempdistdir = new File(tempdir + "/" + upDistrictName);
                                if (!tempdistdir.exists()) {
                                    tempdistdir.mkdir();
                                }
                                CommonDetails.uploadingFile(partAForm.getPhotoUpload(), "" + tempdistdir, fileName);


                            } else {
                                request.setAttribute("msg", "Photo Size Must be Less Than 150 KB");
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }




                    /** End **/
                    // request.setAttribute("msg", "Person is Rejected");
                    target = "rejected";

                }
            }

            // session.removeAttribute("categoryIdAu");
            // session.removeAttribute("categoryCountAu");

        } catch (Exception e) {
            e.printStackTrace();


        }
        return mapping.findForward(target);

    }
}
