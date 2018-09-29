/*
 * RejectedCertificateAction.java
 *
 * Created on September 17, 2008, 6:00 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.BaseAction;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.CommonDetails;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.PrintDetailsDAO;
import org.bf.disability.dto.CertificateDTO;
import org.bf.disability.dto.TerritoryDTO;

import com.tcs.sadarem.common.DAO.CertificateDAO;
import com.tcs.sadarem.common.DAO.CertificateDAOImpl;
import com.tcs.sadarem.util.CommonUtility;

/**
 * this action  class will manipulate the rejected person certificate details
 * @author svsganesh
 * @version 1.0
 */
public class RejectedCertificateAction extends BaseAction 
{

    DataSource ds = null;
    static final private Logger log = Logger.getLogger(RejectedCertificateAction.class);
    /**
     * this method will manipulate the rejected person's certificate details
     * @param mapping 
     * @param form 
     * @param request 
     * @param response 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return Action Forward
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException 
    {
        String target = "success";
        String flag;
        String personcode = null;
        HttpSession session = request.getSession(true);
        TerritoryDTO territorydto = new TerritoryDTO();
        CertificateDTO certificatedto = new CertificateDTO();
        ArrayList rejectedlist = new ArrayList();
        ArrayList rejectlist = new ArrayList(); 
       //log.info("step -1");
        String selectFlow = "OUTERPROCESS";
        if (request.getParameter("selectFlow") != null) 
        {
            selectFlow = CommonUtility.checkNullObj(request.getParameter("selectFlow"));
        } 
        else if (request.getParameter("selectedValue") != null) 
        {
            selectFlow = CommonUtility.checkNullObj(request.getParameter("selectedValue"));
        }
        request.setAttribute("selectedValue", selectFlow);

        ActionMessages actionMessages = null;
        String disabilityid = null;
        CommonDetails commonDetails = null;
        String districtName = null; 

       //log.info("step -2");
        try
        {
        	CertificateDAO certObj = new CertificateDAOImpl();
            disabilityid = territorydto.getDisabilitytype();

           //log.info("step -3");
        	
            ds = getDataSource(request);
            
            if (ds == null || "null".equals(ds)) 
            {
                ds = JNDIDataSource.getConnection();
            }

            super.execute(mapping, form, request, response);
            flag = CommonUtility.checkNullObj(request.getParameter("flag"));

           //log.info("step -4");
            if (flag.equals("true")) 
            { 
                personcode = CommonUtility.checkNullObj(request.getParameter("personcode"));
            } 
            else 
            {
                personcode = CommonUtility.checkNullObj(session.getAttribute("personcode"));
            }

            if (session.getAttribute("sadaremCodeAu") != null) 
            {
                personcode = CommonUtility.checkNullObj(session.getAttribute("sadaremCodeAu"));

            }
            System.out.println("personcode : "+personcode);

           //log.info("step -5");
            String print = CommonUtility.checkNullObj(request.getParameter("print"));
            rejectedlist = certObj.getRejectedData(personcode);

           //log.info("step -6");
            if (rejectedlist.size() == 0)
            {
                target = "personcodeFailure";
            } 
            else 
            {
            	
            	
            	 ArrayList sadaremIdDtls = new ArrayList();
                 String campId="",districtId="";
                 
                 String logCampId = CommonUtility.checkNullObj(session.getAttribute("campId"));
                 String logDistId = CommonUtility.checkNullObj(session.getAttribute("districtId"));
                 String logRoleId = CommonUtility.checkNullObj(session.getAttribute("roleId"));
                 sadaremIdDtls = certObj.getCampDistIds(personcode);
                 
                 if(sadaremIdDtls!=null && sadaremIdDtls.size()>0 && logRoleId.equals(CommonConstants.ROLE_CAMPINCHARGE))
                 {
                 	sadaremIdDtls = (ArrayList)sadaremIdDtls.get(0);
                 	if(sadaremIdDtls.size()>0)
                 	{
                 		campId = CommonUtility.checkNullObj(sadaremIdDtls.get(0));
                 		districtId = CommonUtility.checkNullObj(sadaremIdDtls.get(1));
                 		if(!( logDistId.equals(districtId)))
                 		{
                 			 request.setAttribute("msg", "You are not authorized to view this certificate.");
                         	 return mapping.findForward("personcodeFailure");
                 		}
                 		
                 		
                 	}
                 } 
                 else if(sadaremIdDtls.size()>0 && logRoleId.equals(CommonConstants.DPMLOGINROLEID))
                 {
                 	sadaremIdDtls = (ArrayList)sadaremIdDtls.get(0);
                 	if(sadaremIdDtls.size()>0)
                 	{
                 	
                 		districtId = CommonUtility.checkNullObj(sadaremIdDtls.get(1));
                 		if(!( logDistId.equals(districtId)))
                 		{
                 			 request.setAttribute("msg", "You are not authorized to view this certificate.As it belongs to other district");  
                         	 return mapping.findForward("PersoncodeFailure");
                 		}
                 		
                 		
                 	}
                 }
                 
            	
                Iterator iterator = rejectedlist.iterator();
                iterator.hasNext();
                certificatedto = (CertificateDTO) iterator.next();
                disabilityid = certificatedto.getDisabilityidforrejecte();
                districtName = certificatedto.getDistrictname();
                
                if (disabilityid.equals("1"))
                {
                    rejectlist = certObj.getRejectedLocomotorData(personcode);
                }
                else if (disabilityid.equals("2"))
                {
                    rejectlist = certObj.getRejectedVisualData(personcode);
                }
                else if (disabilityid.equals("3"))
                {
                    rejectlist = certObj.getRejectedHearingData(personcode);
                } 
                else if (disabilityid.equals("4")) 
                {
                    rejectlist = certObj.getRejectedMentalRetardationData(personcode);
                }
                else if (disabilityid.equals("5"))
                {
                    rejectlist = certObj.getRejectedMetalillnessData(personcode);
                }

                if (personcode != null && districtName != null) 
                {
                    commonDetails = new CommonDetails();
                    String url = getServlet().getServletContext().getRealPath("/");
                    commonDetails.copyPhotoDtoRelativePath(personcode, districtName, request, url);
                }
                String apflag = certificatedto.getFlag();
                
                DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
                Date date = new Date();
                request.setAttribute("Todaydate", dateFormat.format(date));
                
                request.setAttribute("apflag", apflag);
                session.setAttribute("rejectedlist", rejectedlist);
                session.setAttribute("rejectlist", rejectlist);
                if (print != null) 
                {
                    if (print.equals("certificate")) 
                    {
                        target = "success";
                    }
                    if (print.equals("certificateprint"))
                    {
                        target = "certificateprint";
                    }
                }
            }
            String category = "";
            if (request.getParameter("print") != null && CommonUtility.checkNullObj(request.getParameter("print")).equals("certificateprint")) 
            {
                String loginId = (String) session.getAttribute("loginid");
                String campIdValue = session.getAttribute("campId").toString();
                String distId = session.getAttribute("districtId").toString();
                String certificateType = "CT";
                category = "1";
                if (request.getParameter("selectedValue") != null)
                {
                    category = CommonUtility.checkNullObj(request.getParameter("selectedValue"));
                    
                    if (category.equals("appellate")) 
                    {
                        category = "2";
                    }
                    else if (category.equals("temporay")) 
                    {
                        category = "3";
                    }
                    else if (category.equals("OUTERPROCESS")) 
                    {
                        category = "0";
                    }
                    else
                    {
                        category = "1";
                    }
                } 
                else
                {
                    category = "0";
                }

                PrintDetailsDAO printDetailsDAO = new PrintDetailsDAO();
                printDetailsDAO.insertCertificatePrintDetails1(ds, personcode, category, certificateType, loginId, campIdValue, distId);
                category = "";
                // session.removeAttribute("selectedValue");
                session.removeAttribute("categoryIds");
            }
        } 
        catch (SADAREMDBException e) 
        {
        	e.printStackTrace();
        	log.error(e);
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }
        catch (Exception e) 
        {
        	e.printStackTrace();
        	log.error(e);
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }
        return mapping.findForward(target);


    }
}
