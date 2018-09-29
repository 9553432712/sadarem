/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.dao.CertificatesUploadDAO;

import com.tcs.sadarem.common.DAO.CommonDAOImpl;
import com.tcs.sadarem.util.CommonUtility;
import com.tcs.sadarem.util.RequestUtility;
 
public class CertificatesUploadAction extends DispatchAction 
{ 
	
    public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
    	 String targetPage ="success";
        HttpSession session = request.getSession(true);
        
        String sesUserId		= CommonUtility.checkNullObj(session.getAttribute("loginid"));
    	
        try
        {
        	 if(sesUserId.equals(""))
	    	   { 
		    	   request.setAttribute("errorMessage", "Sorry we are not able to process your request as session expired.<br>Please login again."); 
		    	   targetPage="exceptionPage";
	    	   }
        	 else
        	 {
        		 session.setAttribute("sesFormId", "sesFormId"+Math.random());
        	 }
        } 
        catch (Exception e) 
        {
           targetPage="exceptionPage";
    	   request.setAttribute("errorMessage", "Sorry we are not able to process your request. Please try again!.");
    	   e.printStackTrace();
        }
        return mapping.findForward(targetPage);
    
    }
    
    public ActionForward getuploadedcertificatebasedtls(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
    	 String targetPage ="success";
        HttpSession session = request.getSession(true);
        
        String sesUserId		= CommonUtility.checkNullObj(session.getAttribute("loginid"));
     
        try
        {
        	 if(!sesUserId.equals(""))
	    	 {
        		 String selSadaremId = CommonUtility.checkNullObj(request.getParameter("sadaremid"));
        		 String reqFormId	 = CommonUtility.checkNullObj(request.getParameter("reqFormId"));
        		 
        		 String sesFormId	 = CommonUtility.checkNullObj(session.getAttribute("sesFormId"));
        		 String sesDistId	 = CommonUtility.checkNullObj(session.getAttribute("districtId"));  
        		 String sesRoleId 	 = CommonUtility.checkNullObj(session.getAttribute("roleId"));

        		 session.setAttribute("sesFormId", "sesFormId"+Math.random());
        		 session.removeAttribute("sesCertSadaremId");
        		 
        		 if(sesFormId.equals(reqFormId))
        		 {
        			 CommonDAOImpl comObj = new CommonDAOImpl();
        			 
        			 HashMap sadaremGEODtls = comObj. getGEODetailsbySADAREMID(selSadaremId);
        			 
        			 if(sesDistId.equals(CommonUtility.checkNullObj(sadaremGEODtls.get("districtid"))) || sesRoleId.equals(CommonConstants.ROLE_APPELLATE))
        			 { 
	        			 request.setAttribute("SadaremBasicDtlsList", comObj.sadaremBasicDetails(selSadaremId, ""));
	        			 request.setAttribute("historyList",comObj.searchForSadaremHistory(selSadaremId, "", "", "", "", "", ""));
	        			 request.setAttribute("PartBStatusRmks",comObj.getPartBStatusRemarks(selSadaremId));
	        			 session.setAttribute("sesCertSadaremId", selSadaremId);
        			 }
        			 else
        			 { 
        		    	   request.setAttribute("alert_msg", "Your are not authorised to access the details of this SADAREM ID : "+selSadaremId);  
        		    	   request.setAttribute("alert_css","alert-danger");
        			 }
        		 } 
        		 else
        		 {
  		    	   request.setAttribute("alert_msg", "Please do not refresh the page.");  
  		    	   request.setAttribute("alert_css","alert-danger");
        		 }
        		 
	    	 }
        	 else
        	 {
		    	   request.setAttribute("errorMessage", "Sorry we are not able to process your request as session expired.<br>Please login again."); 
		    	   targetPage="exceptionPage";
        	 }
        } 
        catch (Exception e) 
        {
           targetPage="exceptionPage";
    	   request.setAttribute("errorMessage", "Sorry we are not able to process your request. Please try again!.");
    	   e.printStackTrace();
        }
        return mapping.findForward(targetPage);
    
    }
    

    public ActionForward uploadsadaremcerts(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
    {
    	 String targetPage ="success";
         HttpSession session = request.getSession(true);
         
         String sesUserId		= CommonUtility.checkNullObj(session.getAttribute("loginid"));
      
         try
         {
         	 if(!sesUserId.equals(""))
 	    	 {
         		 HashMap reqDataList = (HashMap)RequestUtility.getReqParamList(request, session, false, false, "");
 
         		 
         		 String sesCertSadaremId = CommonUtility.checkNullObj(session.getAttribute("sesCertSadaremId"));
         		 
         		 String sesFormId	 = CommonUtility.checkNullObj(session.getAttribute("sesFormId"));
         		 String sesDistId	 = CommonUtility.checkNullObj(session.getAttribute("districtId"));  
         		 String sesRoleId 	 = CommonUtility.checkNullObj(session.getAttribute("roleId"));
         		 
         		 String reqFormId	 = CommonUtility.getStringFromFileItem((FileItem)reqDataList.get("reqFormId")); 
         		  

         		 session.setAttribute("sesFormId", "sesFormId"+Math.random()); 
         		 
         		 if(sesFormId.equals(reqFormId))
         		 {
         			 CommonDAOImpl comObj = new CommonDAOImpl();
         			 
         			 HashMap sadaremBasicDtls = comObj.getSADAREMBasicDetailsByID(sesCertSadaremId);
         			 
         			 
         			 
         			 if(sesDistId.equals(CommonUtility.checkNullObj(sadaremBasicDtls.get("districtid"))) || sesRoleId.equals(CommonConstants.ROLE_APPELLATE))
         			 { 
         				CertificatesUploadDAO uploadObj = new CertificatesUploadDAO();
         				uploadObj.uploadSADAREMCertificates(request, sesCertSadaremId, reqDataList,CommonUtility.checkNullObj(sadaremBasicDtls.get("category_id")), sesUserId);
         				 
 	        			 request.setAttribute("SadaremBasicDtlsList", comObj.sadaremBasicDetails(sesCertSadaremId, ""));
 	        			 request.setAttribute("historyList",comObj.searchForSadaremHistory(sesCertSadaremId, "", "", "", "", "", ""));
 	        			 request.setAttribute("PartBStatusRmks",comObj.getPartBStatusRemarks(sesCertSadaremId));
 	        			 session.setAttribute("sesCertSadaremId", sesCertSadaremId);
         			 }
         			 else
         			 { 
         		    	   request.setAttribute("alert_msg", "Your are not authorised to access the details of this SADAREM ID : "+sesCertSadaremId);  
         		    	   request.setAttribute("alert_css","alert-danger");
         			 }
         		 } 
         		 else
         		 {
   		    	   request.setAttribute("alert_msg", "Please do not refresh the page.");  
   		    	   request.setAttribute("alert_css","alert-danger");
         		 }
         		 
 	    	 }
         	 else
         	 {
 		    	   request.setAttribute("errorMessage", "Sorry we are not able to process your request as session expired.<br>Please login again."); 
 		    	   targetPage="exceptionPage";
         	 }
         } 
         catch (Exception e) 
         {
            targetPage="exceptionPage";
     	   request.setAttribute("errorMessage", "Sorry we are not able to process your request. Please try again!.");
     	   e.printStackTrace();
         }
         return mapping.findForward(targetPage);
    
    }
    
    
  /*  public ActionForward getDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CertificatesUploadDAO certificatesUploadDAO = new CertificatesUploadDAO();
        PartAForm partAForm = (PartAForm) form;
        DataSource ds = null;
        String districtId = null;
        String personCode = null;
        boolean fileUploadStatus = false;
        HttpSession session = request.getSession();
        int disabiltyPercentage = 0;
        int countData = 0;
        String certificateCount = "";
        String idcardCount = "";
        String disabilityDetails = null;
        String roleId = CommonUtility.checkNullObj(session.getAttribute("roleId"));
        try 
        {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds))
            {
                ds = JNDIDataSource.getConnection();
            }
            if (session.getAttribute("districtId") != null)
            {
                districtId = session.getAttribute("districtId").toString();
            }
            
            if (request.getParameter("personcode") != null) 
            {
                personCode = request.getParameter("personcode");
                request.setAttribute("personCodeId", personCode);
            }
             
            HashMap GEODtls = new HashMap();
			GEODtls=comObj.getGEODetailsbySADAREMID(personCode);
			String distIdFromSadarem	=	GEODtls.get("districtid").toString();
            
            
			if (districtId.equals(distIdFromSadarem) || roleId.equals(CommonConstants.ROLE_APPELLATE))                   // provision to appellate given by sowjanya
            {
                partAForm.setSadaremId(personCode);
                countData = certificatesUploadDAO.countSADAREMID(ds, partAForm);
                if (countData > 0) 
                { 
                	 //request.setAttribute("submitdetails", "submitdetails");
                    partAForm = certificatesUploadDAO.getDetails(ds, partAForm);
                    ArrayList checkviewEditMode = certificatesUploadDAO.checkViewEditMode(ds, personCode);
                    if (checkviewEditMode.size() > 0) 
                    {
                        if (checkviewEditMode.get(0).toString() != null
                                && checkviewEditMode.get(0).toString().equalsIgnoreCase("Edit")) 
                        {
                            request.setAttribute("submitdetails", "submitdetails");
                            request.setAttribute("errorMsg", "Only PartA entered  for given SADAREMID");
                        }
                        else if (checkviewEditMode.get(0).toString() != null && checkviewEditMode.get(0).toString().equalsIgnoreCase("View")) 
                        {
                            request.setAttribute("Details", "Details");
                            if (checkviewEditMode.get(1).toString() != null && checkviewEditMode.get(1).toString().equalsIgnoreCase("Rejected"))
                            {
                                request.setAttribute("disabilitybelow", "disabilitybelow");
                            }
                            else if (checkviewEditMode.get(1).toString() != null  && checkviewEditMode.get(1).toString().equalsIgnoreCase("Eligible")) 
                            {
                                disabilityDetails = certificatesUploadDAO.checkPersondisabilitydetails(ds, personCode);
                                
                                int certificateStatus = certificatesUploadDAO.checkCategoryUpload(ds, partAForm.getCategoryid(), partAForm.getPersoncode(), CommonConstants.CERTIFICATE);
                                int idCardStatus 	 = certificatesUploadDAO.checkCategoryUpload(ds, partAForm.getCategoryid(), partAForm.getPersoncode(), CommonConstants.IDCARD);
                               
                                if (disabilityDetails.length() > 0 ) 
                                {                                	
                                    disabiltyPercentage = certificatesUploadDAO.chechinDisabilityPercentage(ds, personCode);
                                    if(partAForm.getTypeofdisability().equals("Hearing Impairment") )
                                	{
                                    	if(disabiltyPercentage>=51)
                                    	{
                                    		request.setAttribute("disabilitygreterthan", "disabilitygreterthan");
                                    	}
                                    	else
                                    	{
                                    		request.setAttribute("disabilitybelow", "disabilitybelow");
                                    	}
                                	}
                                    else if (disabiltyPercentage >= 40  && !partAForm.getTypeofdisability().equals("Hearing Impairment"))
                                    {
                                        request.setAttribute("disabilitygreterthan", "disabilitygreterthan");
                                    } 
                                    else if (disabiltyPercentage < 40)
                                    {
                                        request.setAttribute("disabilitybelow", "disabilitybelow");
                                    }
                                    else 
                                    {
                                        request.setAttribute("submitdetails", "submitdetails");
                                        request.setAttribute("errorMsg", "percentage empty");
                                    }
                                } 
                                else if(disabilityDetails.length() > 0 && certificateStatus>0 && idCardStatus>0)
                                {
                                    request.setAttribute("submitdetails", "submitdetails");
                                    request.setAttribute("errorMsg", "SADAREM ID Card and Certificate already Uploaded");
                                }
                                else
                                {
                                	request.setAttribute("submitdetails", "submitdetails");
                                    request.setAttribute("errorMsg", "Personcode is not available in tblPerson_Disability_Details");
                                }
                            } 
                            else
                            {
                                request.setAttribute("submitdetails", "submitdetails");
                                request.setAttribute("errorMsg", "!=reject and !=Eligible");
                            }
                        } 
                        else
                        {
                            request.setAttribute("submitdetails", "submitdetails");
                            request.setAttribute("errorMsg", "!=Edit and !=View ");
                        }
                    }
                    else
                    {
                        request.setAttribute("submitdetails", "submitdetails");
                        request.setAttribute("errorMsg", "view_edit_mode is empty");
                    }
                } 
                else
                {
                    request.setAttribute("submitdetails", "submitdetails");
                    request.setAttribute("errorMsg", "Invalid SADAREM ID");
                }
            } 
            else
            {
                request.setAttribute("submitdetails", "submitdetails"); 
                request.setAttribute("errorMsg", "Invalid SADAREM ID -OR- ID Doesn't belong to this district");
            }
        } 
        catch (Exception e) {
            request.setAttribute("submitdetails", "submitdetails");
            e.printStackTrace();
        }
        return mapping.findForward(success);
    }

    public ActionForward storeCertificateUploaddetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CertificatesUploadDAO certificatesUploadDAO = new CertificatesUploadDAO();
        PartAForm partAForm = (PartAForm) form;
        DataSource ds = null;
        String districtId = null;
        String personCode = null;
        boolean fileUploadStatus = false;
        HttpSession session = request.getSession();
        String sadaremId = null;
        int disabiltyPercentage = 0;
        String phoneNumber = null;
        String message = null;
        int check = 0;
        int storeStatus = 0;
        String result = null;

        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            if (session.getAttribute("districtId") != null) {
                districtId = session.getAttribute("districtId").toString();
            }
            if (request.getParameter("personIddd") != null) {
                personCode = request.getParameter("personIddd");
                request.setAttribute("personCodeId", personCode);
            }

            partAForm.setPersoncode(personCode);
            partAForm.setSadaremId(personCode);

            partAForm = certificatesUploadDAO.getDetails(ds, partAForm);

            if (request.getParameter("personIddd") != null) {
                sadaremId = request.getParameter("personIddd");
            } else {
                sadaremId = partAForm.getPersoncode();
            }
            partAForm.setSystemip(request.getRemoteAddr());
            String typeMsg = "";
            String eDrive = "E://";
            String dDrive = "D://";
            String certificatePath = null;
            String idcardfilePath = null;
            String spath = CommonConstants.SADAREM_DOCUMENTS_PATH+ personCode + "//" + CommonConstants.CERTIFICATE + "//";
            String idpath = CommonConstants.SADAREM_DOCUMENTS_PATH+ personCode + "//" + CommonConstants.IDCARD + "//";
            File fileDrive = new File(eDrive);
            if (fileDrive.exists()) {
                certificatePath = eDrive + spath;
                idcardfilePath = eDrive + idpath;
            } else {
                certificatePath = dDrive + spath;
                idcardfilePath = dDrive + idpath;
            }
//personCodeId
            //partAForm.setFilePath(certificatePath + personCode);
            partAForm.setLoginid(session.getAttribute("loginid").toString());
            
            
            
            String remarks = (String) request.getParameter("remarks");
            //System.out.println("remarks"+remarks);
            partAForm.setRemarks(remarks);
            
            
            
            
            disabiltyPercentage = certificatesUploadDAO.chechinDisabilityPercentage(ds, personCode);

            if (disabiltyPercentage >= 40) 
            {
                check = certificatesUploadDAO.checkCategoryUpload(ds, partAForm.getCategoryid(), partAForm.getPersoncode(), CommonConstants.CERTIFICATE);
                if (check > 0) 
                {
                    check = certificatesUploadDAO.checkCategoryUpload(ds, partAForm.getCategoryid(), partAForm.getPersoncode(), CommonConstants.IDCARD);
                }
                
                if (partAForm.getFormFile().getFileSize() >= 512000 && partAForm.getFormFile1().getFileSize() >= 512000) {
                    request.setAttribute("sucMsg", " Upload File size must be 500 kb");
                } 
                else
                {
//                    if (check > 0) {
//                        request.setAttribute("errorMsg", " Already Upload For " + personCode);
//                    } else {
                        storeStatus = certificatesUploadDAO.storeCertificateUploaddetails(ds, partAForm, CommonConstants.CERTIFICATE, certificatePath + "Certificate");
                        if (storeStatus > 0) 
                        {
                            fileUploadStatus = uploadDocument(partAForm.getFormFile(), certificatePath, "Certificate.pdf");
                            storeStatus = certificatesUploadDAO.storeCertificateUploaddetails(ds, partAForm, CommonConstants.IDCARD, idcardfilePath + "IDCard");
                        }
                        if (storeStatus > 0)
                        {
                            fileUploadStatus = uploadDocument(partAForm.getFormFile1(), idcardfilePath, "IDCard.pdf");
                        }
//                    }
                }
            } 
            else
            {
                check = certificatesUploadDAO.checkCategoryUpload(ds, CommonConstants.CERTIFICATE, partAForm.getPersoncode(), partAForm.getCertificateType());
                if (partAForm.getFormFile().getFileSize() >= 512000) {
                    request.setAttribute("sucMsg", " Upload File size must be 500 kb");
                } else {
                    if (check > 0) {
                        request.setAttribute("errorMsg", "Already Upload For " + personCode);
                    } else {
                        storeStatus = certificatesUploadDAO.storeCertificateUploaddetails(ds, partAForm, CommonConstants.CERTIFICATE, certificatePath +  "Certificate");
                        if (storeStatus > 0) {
                            fileUploadStatus = uploadDocument(partAForm.getFormFile(), certificatePath,  "Certificate.pdf");
                        }
                    }
                }
            }
            if (fileUploadStatus == true) 
            {
                partAForm = certificatesUploadDAO.getDetails(ds, partAForm);
                request.setAttribute("sucMsg", " Upload SuccessFully");
                request.setAttribute("Details", "Details");
                //
                message = "Your SADAREM (ID : " + partAForm.getPersoncode() + ") Disability Certificate has been uploaded in MEESEVA. Kindly collect the duplicate copy for Rs.25/- after 7days- SERP,TG.";
                phoneNumber = certificatesUploadDAO.getMobileNumber(ds, partAForm.getPersoncode());
                if (phoneNumber != null && !phoneNumber.equals("") && phoneNumber.length() > 0) {
                    try 
                    {
                        result = TpSendSMS.sendSMS(phoneNumber, message); 
                        if (result.equals("OK")) 
                        {
                            certificatesUploadDAO.insertSmsLogDetails(ds, message, phoneNumber, "Success", CommonConstants.CERTIFICATEUPLOAD, partAForm.getLoginid(),request.getRemoteAddr());
                        } 
                        else
                        {
                            certificatesUploadDAO.insertSmsLogDetails(ds, message, phoneNumber, "Fail", CommonConstants.CERTIFICATEUPLOAD, partAForm.getLoginid(),request.getRemoteAddr());
                        } 
                    } 
                    catch (Exception e) 
                    {
                        request.setAttribute("Details", "Details");
                        certificatesUploadDAO.insertSmsLogDetails(ds, message, phoneNumber, "Exception while sending sms", CommonConstants.CERTIFICATEUPLOAD, partAForm.getLoginid(),request.getRemoteAddr());
                        partAForm = certificatesUploadDAO.getDetails(ds, partAForm);
                       
                        if (disabiltyPercentage >= 40)
                        {
                            request.setAttribute("disabilitygreterthan", "disabilitygreterthan");
                        } 
                        else
                        {
                            request.setAttribute("disabilitybelow", "disabilitybelow");
                        }
                    }
                }
                if (disabiltyPercentage >= 40) 
                {
                    request.setAttribute("disabilitygreterthan", "disabilitygreterthan");
                } else {
                    request.setAttribute("disabilitybelow", "disabilitybelow");
                }
                request.setAttribute("Details", "Details");
            } 
            else 
            {
                if (check > 0) 
                {
                    //System.out.println("hiii");
                    //request.setAttribute("sucMsg", partAForm.getCertificateType() + " already Uploded.");
                    //request.setAttribute("errorMsg", partAForm.getCertificateType() + " Already Upload For " + personCode);
                }
                else 
                {
                    request.setAttribute("errorMsg", " Already Upload For " + personCode);
                }

                partAForm = certificatesUploadDAO.getDetails(ds, partAForm);
                if (disabiltyPercentage >= 40) {
                    request.setAttribute("disabilitygreterthan", "disabilitygreterthan");
                } else {
                    request.setAttribute("disabilitybelow", "disabilitybelow");
                }
            }
            partAForm.setSadaremId(request.getParameter("personIddd"));
            request.setAttribute("Details", "Details");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(success);
    }

    public boolean uploadDocument(FormFile uploadFileName, String uploadPath, String fileName) {
        String strDirectoytemp = null;
        boolean flag = false;
        try 
        {
            strDirectoytemp = uploadPath;

            if (strDirectoytemp != null && !"".equals(strDirectoytemp) && strDirectoytemp.length() > 0) // If directory is not exists it will create
            { 
                File directorytemp = new File(strDirectoytemp);
                if (!directorytemp.exists()) {
                    directorytemp.mkdirs();
                }

                File fileToCreatetemp = new File(strDirectoytemp, fileName); // Copy the file into directory
                FileOutputStream fileOutStreamtemp = new FileOutputStream(fileToCreatetemp); // Write the file content into buffer

                if (uploadFileName.getFileSize() > 0) 
                {
                    fileOutStreamtemp.write(uploadFileName.getFileData());
                    fileOutStreamtemp.flush();
                    fileOutStreamtemp.close();
                    flag = true;
                } 
                else
                {
                    flag = false;
                }
            } 
            else 
            {
                flag = false;
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return flag;
    }*/
}
