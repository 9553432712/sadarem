package org.bf.disability.action;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.bf.disability.Constants.CertificateConstants;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.CommonDAO;
import org.bf.disability.common.CommonDetails;
import org.bf.disability.dao.DoctorsInformationDAO;
import org.bf.disability.dao.PartADAO;
import org.bf.disability.dao.PrintDetailsDAO;
import org.bf.disability.dao.TerritoryDAO;
import org.bf.disability.dto.CertificateDTO;
import org.bf.disability.dto.DoctorsInformationDTO;
import org.bf.disability.dto.PartADTO;
import org.bf.disability.dto.TerritoryDTO;
import org.bf.disability.form.TerritoryForm;
import org.bf.disability.service.ReportService;
import org.bf.disability.serviceimpl.ReportServiceImpl;

import com.tcs.sadarem.common.DAO.CertificateDAO;
import com.tcs.sadarem.common.DAO.CertificateDAOImpl;
import com.tcs.sadarem.util.CommonUtility;

/**
 *
 * @description this action class is used to pulate the data related to generate the certificate and id card of the disability person based on the person code
 * @author bapinaidu.t
 * @version 1.0
 */
public class CertificateAction extends Action
{

	static final private Logger log = Logger.getLogger(CertificateAction.class);
	
    /**
     *
     *@description this method is used to pulate the data related to generate the certificate and id card of the disability person based on the person code
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws org.bf.disability.Exception.SADAREMException
     * @return ActionForward
     */
    public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws SADAREMDBException, SQLException
    {

        String target = "finish";
    	//log.info("in CertificateAction execute");
        try 
        {
        /*
         * Creating instance for  services
         */

            HttpSession session = request.getSession(); 
        	//log.info("in CertificateAction try start");
        TerritoryDTO territorydto = new TerritoryDTO();
        TerritoryDAO territorydao = new TerritoryDAO();

        String campId="",districtId="",logCampId="",logDistId="",logRoleId="";
        logCampId = CommonUtility.checkNullObj(session.getAttribute("campId"));
        logDistId = CommonUtility.checkNullObj(session.getAttribute("districtId"));
        logRoleId = CommonUtility.checkNullObj(session.getAttribute("roleId"));

        String loginId = CommonUtility.checkNullObj(session.getAttribute("loginid"));
        
    	//log.info("in CertificateAction execute---1");
        /*
         * Creating instance for form(TerritoryForm)
         */
        TerritoryForm territoryForm = (TerritoryForm) form;
        ActionMessages actionMessages = null;
        String flag;
        String disabilityid = null;
        String personcode = null;
        String personcodestatus = null;
        double totaldisability;
        String disabilityName = null;
        DataSource ds = null;

    	//log.info("in CertificateAction execute---2");
        String multipledisabilityid = null;
        String certificateType = null, category = "0";
        boolean railwaycertificate;
        String selectFlow = "OUTERPROCESS";

    	//log.info("in CertificateAction execute - 1");
        if (request.getParameter("selectFlow") != null) 
        {
            selectFlow =CommonUtility.checkNullObj(request.getParameter("selectFlow"));
        } 
        else if (request.getParameter("selectedValue") != null) 
        {
            selectFlow = CommonUtility.checkNullObj(request.getParameter("selectedValue"));
        }
    	//log.info("in CertificateAction execute - 2");
        request.setAttribute("selectedValue", selectFlow);
        ArrayList rejectedlist = new ArrayList();
        ArrayList reportlist = null;
        ArrayList Idcardlist = null; 
        ArrayList suggestionlist = null;
        String conditionlistCheckFlag = "false";
        String str = null;
        String districtName = null;
        boolean id = false;

    	CertificateDAO certObj = new CertificateDAOImpl();


        	//log.info("in CertificateAction execute - 3");
            request.getSession().removeAttribute("reailwaycertificate");
            //super.execute(mapping, form, request, response);
            request.getSession().removeAttribute("dr_exists"); 

            String print = CommonUtility.checkNullObj(request.getParameter("print"));
            flag = CommonUtility.checkNullObj(request.getParameter("flag"));

        	//log.info("in CertificateAction execute -4");
        	
            if (flag.equals("true")) 
            {
                session.removeAttribute("sadaremCodeAu");
            }


            str =CommonUtility.checkNullObj( request.getParameter("displaybackbuttonforAR"));

        	//log.info("in CertificateAction execute - 5");
            if ("null".equals(str)) 
            {
                request.setAttribute("displaybackbuttonforAR", "true");
            }

        	//log.info("in CertificateAction execute - 6");

            if (session.getAttribute("sadaremCodeAu") != null) 
            {
                personcode = CommonUtility.checkNullObj( session.getAttribute("sadaremCodeAu"));  
            } 
            else 
            {
                personcode = CommonUtility.checkNullObj(request.getParameter("personcode"));
                if (flag.equals("true")) 
                {

                    boolean exi = territorydao.isEligibleforCertificate(ds, personcode, request);
                    if (exi) 
                    {

                        return mapping.findForward("PersoncodeFailure");
                    }


                }
                else 
                {
                    personcode = CommonUtility.checkNullObj(session.getAttribute("personcode"));
                }

            }

          	HashMap sadaremDtls = (HashMap)certObj.getSADAREMDetailsByID(personcode);
          	System.out.println("sadaremDtls : "+sadaremDtls);
          //log.info("sadaremDtls : "+sadaremDtls);
          
        	//log.info("in CertificateAction execute - 7");
            if (personcode == null) 
            {
                personcode = CommonUtility.checkNullObj(request.getParameter("personcode"));
            }

        	//log.info("in CertificateAction execute - 8");
        	ReportService reportservice = null;
        	//log.info("in CertificateAction execute - 8 ol");
        	try
        	{
        	reportservice = new ReportServiceImpl();
        	}
        	catch(Exception e)
        	{
        		log.error(e);
        	}

        	
        	
        	if(sadaremDtls==null || sadaremDtls.size()==0 || !sadaremDtls.containsKey("disb_id"))
        	{
        		request.setAttribute("msg", "Invalid SADAREM ID.");
         	     return mapping.findForward("PersoncodeFailure");  
        	}
        	else if(CommonUtility.checkNullObj(sadaremDtls.get("disb_id")).equals(""))
        	{
        		request.setAttribute("msg", "Disability Details Not Available. Please fill Part-B Details for SADAREM ID "+personcode);
        	    return mapping.findForward("PersoncodeFailure");  
        	}
        	
        	 if
        	 (
    			 CommonUtility.checkNullObj(sadaremDtls.get("dis_form_status")).equalsIgnoreCase("View") && 
    			 CommonUtility.checkNullObj(sadaremDtls.get("person_status")).equalsIgnoreCase("Rejected")
        	 )
         	{
         		request.setAttribute("msg", "SADAREM ID "+personcode+" is Rejected.");
         	    return mapping.findForward("PersoncodeFailure");  
         	}
        	
        	//log.info("in CertificateAction execute - 8 afer");
            ArrayList reportDtls = new ArrayList();
            reportDtls =(ArrayList) certObj.getOnlyPartADetails(personcode);
 
            if(reportDtls.size()==0)
            {
            	 request.setAttribute("msg", "Invalid SADAREM ID.");
          	     return mapping.findForward("PersoncodeFailure");  
            }
            else
            { 
                request.setAttribute("basicdetails", CommonUtility.checkNullObj(reportDtls.get(0)));
            }

            territoryForm.setDifferenceFlag(print);
        	//log.info("in CertificateAction execute - 9");
        	
            //get whether person is alive or dead
            String reasonForstatus = certObj.getReasonForPersonStatus(personcode);
            if(reasonForstatus.equals("Dead"))
            {
             	 request.setAttribute("msg", "Certificate cannot be opened as person status is dead.");
            	 return mapping.findForward("PersoncodeFailure");
            }

        	//log.info("in CertificateAction execute - 9 after");
            ArrayList sadaremIdDtls = new ArrayList();
            
            sadaremIdDtls = certObj.getCampDistIds(personcode); 

        	//log.info("in CertificateAction execute - 10");
          //As per Instruction from Appellate on 7th Jul 2017 restrictions on the certificate!!!!!. Certificate not visible when it raised for Appellate Authority  
              
            if(certObj.checkForAppellateHasRaised(personcode)  &&   !logRoleId.equals(CommonConstants.ROLE_APPELLATE))
            {
            	request.setAttribute("msg", "Appellate Authority Only have access to view this certificate. ");
           	 	return mapping.findForward("PersoncodeFailure");
            }
            

        	//log.info("in CertificateAction execute - 10 after");
            
            
            
            if(sadaremIdDtls.size()>0 && logRoleId.equals(CommonConstants.ROLE_CAMPINCHARGE))
            {
            	sadaremIdDtls = (ArrayList)sadaremIdDtls.get(0);
            	if(sadaremIdDtls.size()>0)
            	{
            		campId = (String)sadaremIdDtls.get(0);
            		districtId = (String)sadaremIdDtls.get(1);
            		if(!(
            				/*logCampId.equals(campId) &&*/ 
            				logDistId.equals(districtId)
            			)
            		 )
            		{
            			 request.setAttribute("msg", "You are not authorized to view this certificate.");  
                    	 return mapping.findForward("PersoncodeFailure");
            		}
            	}
            } 
            else if(sadaremIdDtls.size()>0 && logRoleId.equals(CommonConstants.DPMLOGINROLEID))
            {
            	sadaremIdDtls = (ArrayList)sadaremIdDtls.get(0);
            	if(sadaremIdDtls.size()>0)
            	{
            		
            		districtId = (String)sadaremIdDtls.get(1);
            		if(!( logDistId.equals(districtId)))
            		{
            			 request.setAttribute("msg", "You are not authorized to view this certificate.As it belongs to other district");  
                    	 return mapping.findForward("PersoncodeFailure");
            		}
            	}
            }
        	//log.info("in CertificateAction execute - 11 ");
            
           ArrayList catList=certObj.getCatIdOfPerson(personcode);
           String status=certObj.getAppellateAuthorityTempStatus(personcode);
           String viewmode=certObj.getAppellateAuthorityViewEditStatus(personcode);
      //log.info("in CertificateAction execute - 11 after");
           
       	int categoryId = Integer.parseInt(CommonUtility.checkNullObj(catList.get(0)));
       	
       	
           if(categoryId==1 && (CommonUtility.checkNullObj(catList.get(1))).equals("Active") && (CommonUtility.checkNullObj(catList.get(2))).equals("Edit"))
           {
        	   request.setAttribute("msg", "PART B not filled under Regular category.");
        	   
          	   request.setAttribute("basicDetails", reportservice.getOnlyPartADetails(personcode).get(0));
          	 
        	   return mapping.findForward("PersoncodeFailure");  
          	   
          	   
          	   
           } 
           else if(categoryId==2 && status=="Active" && viewmode=="Edit") 
           {
        	    request.setAttribute("msg", "PART B not filled under Reassessment category.");
                return mapping.findForward("PersoncodeFailure");  
           } 
           else if(categoryId==3 && status=="Active" && viewmode=="Edit")
           {
        	   request.setAttribute("msg", "PART B not filled under Temporary category.");
        	   return mapping.findForward("PersoncodeFailure");   
           }

          	//log.info("in CertificateAction execute - 12");
            /*
             *getting status for particular personcode
             */
          	
          	
    
          	
          	//log.info("in CertificateAction execute - 12 after");
            if (sadaremDtls==null || sadaremDtls.size() == 0) 
            {
                target = "PersoncodeFailure";
            }
            else 
            { 
              	
             	//log.info("in CertificateAction execute - 12 if else");
                personcodestatus = CommonUtility.checkNullObj(sadaremDtls.get("person_status"));
                disabilityid =  CommonUtility.checkNullObj(sadaremDtls.get("disb_id")); 

               //log.info("personcodestatus : "+personcodestatus);
               //log.info("disabilityid : "+disabilityid);
                /*
                 * checking pwd Status
                 */
            	//log.info("in CertificateAction execute - 13 ");
                if ( !personcodestatus.equals("")) 
                {
                    if (personcodestatus.equalsIgnoreCase("Eligible")) 
                    {


                      	//log.info("in CertificateAction execute - 13 eligible case");
                        /*
                         * getting details of particular pwd
                         */
                        reportlist = new ArrayList();
                        reportlist = certObj.getDetailsForCertificate(ds, territoryForm, personcode);
                      	//log.info("in CertificateAction execute - 13 eligible case 1");
                        if (reportlist.size() == 0) 
                        {
                            target = "failure";
                        }
                        else
                        {
                          	//log.info("in CertificateAction execute - 14 eligible case 1");
                            String causeOfDisabilityFlag = "false";
                            Iterator iter = reportlist.iterator();
                            iter.hasNext();
                            territorydto = (TerritoryDTO) iter.next();
                            totaldisability = territorydto.getDisabilityvalue();
                            disabilityName = territorydto.getDisabilitytype();
                            multipledisabilityid = territorydto.getMultipleRadio();

                            railwaycertificate = territorydto.isRailwaycertificate();
                            districtName = territorydto.getDistrict_name();
                            request.setAttribute("categoryId", territorydto.getCategory());
                            

                         	//log.info("in CertificateAction execute - 14 eligible block 1");
                            /*
                             * checking for displaying names
                             *
                             */
                            if ((null != territorydto.getTotalcauseofdisabilities() && !"".equals(territorydto.getTotalcauseofdisabilities()))) 
                            {
                                causeOfDisabilityFlag = "true";
                            }
                        	//log.info("in CertificateAction execute - 14 eligible block 2");
                            
                            if ((null != territorydto.getPhysicalImpairmentPhysicalRequirementList() && !"".equals(territorydto.getPhysicalImpairmentPhysicalRequirementList()))
                                    || (null != territorydto.getVisualImpairmentPhysicalRequirementList() && !"".equals(territorydto.getVisualImpairmentPhysicalRequirementList()))
                                    || (null != territorydto.getHearingImpairmentPhysicalRequirementList() && !"".equals(territorydto.getHearingImpairmentPhysicalRequirementList()))
                                    || (null != territorydto.getMentalRetardationPhysicalRequirementList() && !"".equals(territorydto.getMentalRetardationPhysicalRequirementList()))) 
                            {
                                conditionlistCheckFlag = "true";
                            }
                        	//log.info("in CertificateAction execute - 14 eligible block 3");
                            /*
                             * getting telugu names for particular pwd
                             */
                            //territorydto = reportservice.getTeluguName(ds, personcode);
                            String telugupersonname = territorydto.getTelugupersonname();
                            String telugufathername = territorydto.getTelugufathername();
                            String disabilitytypeInTelugu = territorydto.getDisabilityTypeInTelugu();
                            String genderInTelugu = territorydto.getGenderInTelugu();
                            String disabilityduration = territorydto.getDisabilityduration();
                            String teluguDisablityName = territorydto.getTeluguDisabilityName();
                            String apflag = territorydto.getFlag();
                            request.setAttribute("apflag", apflag);

                        	//log.info("in CertificateAction execute - 14 eligible block 4");
                            
                            /*
                             *for telugu name we can not directly set Form to jsp
                             * so we have to put every telugu field in session
                             */
                            request.setAttribute("causeOfDisabilityFlag", causeOfDisabilityFlag);
                            request.setAttribute("disabilityduration", disabilityduration);
                            request.setAttribute("disabilitytypeInTelugu", disabilitytypeInTelugu);
                            session.setAttribute("personcode", personcode);
                            request.setAttribute("gender", genderInTelugu);
                            request.setAttribute("telugupersonname", telugupersonname);
                            request.setAttribute("telugufathername", telugufathername);
                            session.setAttribute("teluguDisablityName", teluguDisablityName);

                        	//log.info("in CertificateAction execute - 14 eligible block 5");
                            DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
                            Date date = new Date();
                            request.setAttribute("Todaydate", dateFormat.format(date));
                            /*
                             * getting details of particular pwd for Id Card
                             */
                            //Idcardlist = new ArrayList();

                            // Idcardlist = reportservic8e.getDetaiilsForIdCard(ds, territoryForm, personcode);
                            session.setAttribute("reportlist", reportlist);

                            CommonDAO commonDAO = new CommonDAO();
                            double eligiblePercentage = commonDAO.getDisabilityPercentage(ds, disabilityid);
                            //  request.setAttribute("Idcardlist", Idcardlist);
                            suggestionlist = new ArrayList();

                        	//log.info("in CertificateAction execute - 14 eligible block 6");
                            if (disabilityid.equals("1") && totaldisability < eligiblePercentage) 
                            {
                                suggestionlist = certObj.getLocomotorData(personcode);
                            } 
                            else if (disabilityid.equals("2") && totaldisability < eligiblePercentage) 
                            {
                                suggestionlist = certObj.getVisualData( personcode);
                            }
                            else if (disabilityid.equals("3") && totaldisability < eligiblePercentage) 
                            {
                                suggestionlist = certObj.getHearingData( personcode);
                            } 
                            else if (disabilityid.equals("4") && totaldisability < eligiblePercentage) 
                            {
                                suggestionlist = certObj.getMentalRetardationData( personcode);
                            } 
                            else if (disabilityid.equals("5") && totaldisability < eligiblePercentage)
                            {
                                suggestionlist = certObj.getMetalillnessData( personcode);
                            } 
                            else if (disabilityid.equals("6") && totaldisability < eligiblePercentage) 
                            {
                                suggestionlist = certObj.getMultipleData( personcode);
                            }

                        	//log.info("in CertificateAction execute - 14 eligible block 7");
                            request.setAttribute("certificatewithidcard", (String) request.getParameter("certificatewithidcard"));
                            request.setAttribute("conditionlistCheckFlag", conditionlistCheckFlag);
                            request.setAttribute("suggestionlist", suggestionlist);
                            // added  for displaying Raiway Concession Certificate in Report module
                            request.setAttribute("flag", flag);
                            if (print.equals("railwayform")) 
                            {
                                String doctor = request.getParameter("doctor");
                                request.setAttribute("doctor",doctor);
                                if (totaldisability < 40.0) 
                                {
                                    if (doctor != null && doctor.equalsIgnoreCase("doctor")) 
                                    {
                                        target = "doctorrailwayfailure";
                                    } 
                                    else 
                                    {
                                        target = "railwayfailure";
                                    }
                                }
                                else if (disabilityName != null && !"".equals(disabilityName)) 
                                {
                                    if (CertificateConstants.LOCOMOTOR_DISABILITYTYPE.equals(disabilityName)
                                            || CertificateConstants.LOCOMOTOR_DISABILED.equals(disabilityName)) 
                                    {
                                        if (doctor != null && doctor.equalsIgnoreCase("doctor")) 
                                        {
                                            target = "doctorrailwayconcessionOH";
                                        } 
                                        else 
                                        {
                                            target = "railwayconcessionOH";
                                        }
                                    } 
                                    else if (CertificateConstants.VISUAL_DISABILITYTYPE.equals(disabilityName)
                                            || CertificateConstants.VISUAL_DISABILED.equals(disabilityName))
                                    {

                                        if (totaldisability == 100)
                                        {
                                            if (doctor != null && doctor.equalsIgnoreCase("doctor")) 
                                            {
                                                target = "doctorrailwayconcessionVI";
                                            } 
                                            else 
                                            {
                                                target = "railwayconcessionVI";
                                            }
                                        } 
                                        else 
                                        {
                                            if (doctor != null && doctor.equalsIgnoreCase("doctor")) 
                                            {
                                                target = "doctorrailwayfailure";
                                            } 
                                            else 
                                            {
                                                target = "railwayfailure";
                                            }
                                        }
                                    }
                                    else if (CertificateConstants.HEARING_DISABILITYTYPE.equals(disabilityName))
                                    {
                                        if (totaldisability >= 100)
                                        {
                                            if (doctor != null && doctor.equalsIgnoreCase("doctor")) 
                                            {
                                                target = "doctorrailwayconcessionHI";
                                            }
                                            else
                                            {
                                                target = "railwayconcessionHI";
                                            }
                                        } 
                                        else 
                                        {
                                            if (doctor != null && doctor.equalsIgnoreCase("doctor")) 
                                            {
                                                target = "doctorrailwayfailure";
                                            } 
                                            else
                                            {
                                                target = "railwayfailure";
                                            }
                                        }
                                    }
                                    else if (CertificateConstants.MENTALRETARDATION_DISABILITYTYPE.equals(disabilityName))
                                    {
                                        if (totaldisability >= 50) 
                                        {
                                            if (doctor != null && doctor.equalsIgnoreCase("doctor"))
                                            {
                                                target = "doctorrailwayconcessionMR";
                                            }
                                            else 
                                            {
                                                target = "railwayconcessionMR";
                                            }

                                        } 
                                        else
                                        {
                                            if (doctor != null && doctor.equalsIgnoreCase("doctor")) 
                                            {
                                                target = "doctorrailwayfailure";
                                            }
                                            else 
                                            {
                                                target = "railwayfailure";
                                            }
                                        }
                                    } 
                                    else if (CertificateConstants.MULTIPLE_DISABILITYTYPE.equals(disabilityName)) 
                                    {
                                        id = true;

                                        territorydto = territorydao.getTotalValues(ds, personcode);
                                        double totalphysical = territorydto.getLocomotortotal();
                                        double visualimpairment = Double.parseDouble(territorydto.getVisual_Impairment());
                                        double hearingimpairment = Double.parseDouble(territorydto.getHearing_Percentage());
                                        double mentalretardation = Double.parseDouble(territorydto.getMental_Retardation_Total());

                                        if ("1".equals(multipledisabilityid)) {
                                            if (totalphysical >= 40) {
                                                if (doctor != null && doctor.equalsIgnoreCase("doctor")) 
                                                {
                                                    target = "doctorrailwayconcessionOH";
                                                }
                                                else
                                                {
                                                    target = "railwayconcessionOH";
                                                }
                                            } 
                                            else if (visualimpairment >= 100)
                                            {
                                                if (doctor != null && doctor.equalsIgnoreCase("doctor"))
                                                {
                                                    target = "doctorrailwayconcessionVI";
                                                }
                                                else
                                                {
                                                    target = "railwayconcessionVI";
                                                }
                                            } 
                                            else if (hearingimpairment >= 100) 
                                            {
                                                if (doctor != null && doctor.equalsIgnoreCase("doctor"))
                                                {
                                                    target = "doctorrailwayconcessionHI";
                                                } 
                                                else 
                                                {
                                                    target = "railwayconcessionHI";
                                                }
                                            } 
                                            else if (mentalretardation >= 50) 
                                            {
                                                if (doctor != null && doctor.equalsIgnoreCase("doctor")) 
                                                {
                                                    target = "doctorrailwayconcessionMR";
                                                } 
                                                else
                                                {
                                                    target = "railwayconcessionMR";
                                                }
                                            } 
                                            else
                                            {
                                                if (doctor != null && doctor.equalsIgnoreCase("doctor"))
                                                {
                                                    target = "doctorrailwayfailure";
                                                } 
                                                else
                                                {
                                                    target = "railwayfailure";
                                                }
                                            }

                                        } 
                                        else if ("2".equals(multipledisabilityid))
                                        {


                                            if (visualimpairment >= 100)
                                            {
                                                if (doctor != null && doctor.equalsIgnoreCase("doctor")) 
                                                {
                                                    target = "doctorrailwayconcessionVI";
                                                } 
                                                else 
                                                {
                                                    target = "railwayconcessionVI";
                                                }
                                            } 
                                            else if (hearingimpairment >= 100)
                                            {
                                                if (doctor != null && doctor.equalsIgnoreCase("doctor"))
                                                {
                                                    target = "doctorrailwayconcessionHI";
                                                } 
                                                else 
                                                {
                                                    target = "railwayconcessionHI";
                                                }
                                            } 
                                            else if (mentalretardation >= 50)//if (mentalretardation > totalphysical && mentalretardation > hearingimpairment)
                                            {
                                                if (mentalretardation >= 50) 
                                                {
                                                    if (doctor != null && doctor.equalsIgnoreCase("doctor")) 
                                                    {
                                                        target = "doctorrailwayconcessionMR";
                                                    } 
                                                    else 
                                                    {
                                                        target = "railwayconcessionMR";
                                                    }
                                                } 
                                                else if (totalphysical >= 40) 
                                                {
                                                    if (doctor != null && doctor.equalsIgnoreCase("doctor"))
                                                    {
                                                        target = "doctorrailwayconcessionOH";
                                                    } 
                                                    else 
                                                    {
                                                        target = "railwayconcessionOH";
                                                    }
                                                } 
                                                else 
                                                {
                                                    if (doctor != null && doctor.equalsIgnoreCase("doctor")) 
                                                    {
                                                        target = "doctorrailwayfailure";
                                                    } 
                                                    else 
                                                    {
                                                        target = "railwayfailure";
                                                    }
                                                }
                                            } 
                                            else if (totalphysical >= 40)
                                            {
                                                if (mentalretardation > totalphysical && mentalretardation >= 50)
                                                {
                                                    if (doctor != null && doctor.equalsIgnoreCase("doctor")) 
                                                    {
                                                        target = "doctorrailwayconcessionMR";
                                                    } 
                                                    else 
                                                    {
                                                        target = "railwayconcessionMR";
                                                    }
                                                } 
                                                else if (totalphysical >= 40) 
                                                {
                                                    if (doctor != null && doctor.equalsIgnoreCase("doctor")) 
                                                    {
                                                        target = "doctorrailwayconcessionOH";
                                                    } 
                                                    else 
                                                    {
                                                        target = "railwayconcessionOH";
                                                    }
                                                } 
                                                else
                                                {
                                                    if (doctor != null && doctor.equalsIgnoreCase("doctor")) 
                                                    {
                                                        target = "doctorrailwayfailure";
                                                    } 
                                                    else 
                                                    {
                                                        target = "railwayfailure";
                                                    }
                                                }
                                            } else
                                            {
                                                if (doctor != null && doctor.equalsIgnoreCase("doctor"))
                                                {
                                                    target = "doctorrailwayfailure";
                                                } 
                                                else 
                                                {
                                                    target = "railwayfailure";
                                                }
                                            }
                                        } 
                                        else if ("3".equals(multipledisabilityid)) 
                                        {
                                            if (hearingimpairment >= 100) 
                                            {
                                                if (doctor != null && doctor.equalsIgnoreCase("doctor"))
                                                {
                                                    target = "doctorrailwayconcessionHI";
                                                } 
                                                else 
                                                {
                                                    target = "railwayconcessionHI";
                                                }
                                            } 
                                            else if (visualimpairment >= 100)
                                            {
                                                if (doctor != null && doctor.equalsIgnoreCase("doctor"))
                                                {
                                                    target = "doctorrailwayconcessionVI";
                                                } 
                                                else
                                                {
                                                    target = "railwayconcessionVI";
                                                }
                                            } 
                                            else if (mentalretardation >= 50) 
                                            {
                                                if (doctor != null && doctor.equalsIgnoreCase("doctor")) 
                                                {
                                                    target = "doctorrailwayconcessionMR";
                                                } 
                                                else
                                                {
                                                    target = "railwayconcessionMR";
                                                }
                                            }
                                            else if (totalphysical >= 40) 
                                            {
                                                if (doctor != null && doctor.equalsIgnoreCase("doctor")) 
                                                {
                                                    target = "doctorrailwayconcessionOH";
                                                } 
                                                else 
                                                {
                                                    target = "railwayconcessionOH";
                                                }
                                            }
                                            else 
                                            {
                                                if (doctor != null && doctor.equalsIgnoreCase("doctor")) 
                                                {
                                                    target = "doctorrailwayfailure";
                                                } 
                                                else
                                                {
                                                    target = "railwayfailure";
                                                }
                                            }
                                        } 
                                        else if ("4".equals(multipledisabilityid)) 
                                        {
                                            if (mentalretardation >= 50) 
                                            {
                                                if (doctor != null && doctor.equalsIgnoreCase("doctor"))
                                                {
                                                    target = "doctorrailwayconcessionMR";
                                                } else {
                                                    target = "railwayconcessionMR";
                                                }
                                            }
                                            else if (visualimpairment >= 100) 
                                            {
                                                if (doctor != null && doctor.equalsIgnoreCase("doctor")) 
                                                {
                                                    target = "doctorrailwayconcessionVI";
                                                }
                                                else 
                                                {
                                                    target = "railwayconcessionVI";
                                                }
                                            } 
                                            else if (hearingimpairment >= 100) 
                                            {
                                                if (doctor != null && doctor.equalsIgnoreCase("doctor")) 
                                                {
                                                    target = "doctorrailwayconcessionHI";
                                                }
                                                else
                                                {
                                                    target = "railwayconcessionHI";
                                                }
                                            }
                                            else if (totalphysical >= 40) 
                                            {
                                                if (doctor != null && doctor.equalsIgnoreCase("doctor")) 
                                                {
                                                    target = "doctorrailwayconcessionOH";
                                                } 
                                                else
                                                {
                                                    target = "railwayconcessionOH";
                                                }
                                            } 
                                            else
                                            {
                                                if (doctor != null && doctor.equalsIgnoreCase("doctor")) 
                                                {
                                                    target = "doctorrailwayfailure";
                                                } 
                                                else
                                                {
                                                    target = "railwayfailure";
                                                }
                                            }
                                        } 
                                        else if ("5".equals(multipledisabilityid)) 
                                        {

                                            if (visualimpairment >= 100)
                                            {
                                                if (doctor != null && doctor.equalsIgnoreCase("doctor"))
                                                {
                                                    target = "doctorrailwayconcessionVI";
                                                }
                                                else 
                                                {
                                                    target = "railwayconcessionVI";
                                                }
                                            }
                                            else if (hearingimpairment >= 100)
                                            {
                                                if (doctor != null && doctor.equalsIgnoreCase("doctor")) 
                                                {
                                                    target = "doctorrailwayconcessionHI";
                                                } 
                                                else 
                                                {
                                                    target = "railwayconcessionHI";
                                                }
                                            } 
                                            else if (mentalretardation >= 50) 
                                            {
                                                if (doctor != null && doctor.equalsIgnoreCase("doctor"))
                                                {
                                                    target = "doctorrailwayconcessionMR";
                                                } 
                                                else 
                                                {
                                                    target = "railwayconcessionMR";
                                                }
                                            } 
                                            else if (totalphysical >= 40) 
                                            {
                                                if (doctor != null && doctor.equalsIgnoreCase("doctor")) 
                                                {
                                                    target = "doctorrailwayconcessionOH";
                                                } 
                                                else
                                                {
                                                    target = "railwayconcessionOH";
                                                }
                                            } 
                                            else 
                                            {
                                                if (doctor != null && doctor.equalsIgnoreCase("doctor")) 
                                                {
                                                    target = "doctorrailwayfailure";
                                                } 
                                                else
                                                {
                                                    target = "railwayfailure";
                                                }
                                            }
                                        } 
                                        else
                                        {
                                            if (doctor != null && doctor.equalsIgnoreCase("doctor"))
                                            {
                                                target = "doctorrailwayfailure";
                                            } 
                                            else
                                            {
                                                target = "railwayfailure";
                                            }
                                        }
                                    } 
                                    else
                                    {
                                        if (doctor != null && doctor.equalsIgnoreCase("doctor")) 
                                        {
                                            target = "doctorrailwayfailure";
                                        }
                                        else
                                        {
                                            target = "railwayfailure";
                                        }
                                    }
                                } else 
                                {
                                    if (doctor != null && doctor.equalsIgnoreCase("doctor")) 
                                    {
                                        target = "doctorrailwayfailure";
                                    } 
                                    else
                                    {
                                        target = "railwayfailure";
                                    }
                                }
                                DoctorsInformationDAO ddao = new DoctorsInformationDAO();

                                PartADAO pdao = new PartADAO();
                                DoctorsInformationDTO ddto = new DoctorsInformationDTO();
                                PartADTO pdto = new PartADTO();
                                String dn = null;
                                String de = null;
                                String rn = null;

                                if (!target.equalsIgnoreCase("railwayfailure") || !target.equalsIgnoreCase("doctorrailwayfailure")) 
                                {
                                    //**  Railway Certificate issued Doctor details comming from  the following method.
                                    pdto = pdao.getRailwayDoctorDetails(ds, personcode);
                                    if (pdto != null) {
                                        dn = pdto.getDoctor1name();
                                        rn = pdto.getDoctor1regnumber();
                                        de = pdto.getDoctor1designation();
                                        if (dn != null) {
                                            Iterator iter1 = reportlist.iterator();
                                            iter1.hasNext();
                                            territorydto = (TerritoryDTO) iter1.next();
                                            /*if(dn.startsWith("Dr.")|| dn.startsWith("dr."))
                                            dn=dn.replace("Dr.", " ");
                                            if((de!=null && de.equalsIgnoreCase("psychologist"))){


                                            target="doctorfailure";

                                            }*/


                                            if (!dn.contains("Dr.")) {


                                                target = "doctorfailure";

                                            } else {
                                                territorydto.setFirstdoctorname(dn);
                                                territorydto.setFirstdesgination(de);
                                                territorydto.setFirstdoctornumber(rn);
                                                territorydto.setDateofisse(pdto.getDateofissue());

                                                reportlist.remove(0);
                                                reportlist.add(territorydto);
                                                session.setAttribute("reportlist", reportlist);


                                            }
                                        }

                                    }
                                }




                            } else if (print.equals("railwayformprint"))
                            {

                                if (totaldisability < 40.0) 
                                {
                                    target = "railwayfailure";
                                }
                                else if (disabilityName != null && !"".equals(disabilityName))
                                {
                                    if (CertificateConstants.LOCOMOTOR_DISABILITYTYPE.equals(disabilityName)
                                            || CertificateConstants.LOCOMOTOR_DISABILED.equals(disabilityName)) 
                                    {
                                        target = "railwayconcessionOHprint";
                                    } 
                                    else if (CertificateConstants.VISUAL_DISABILITYTYPE.equals(disabilityName)
                                            || CertificateConstants.VISUAL_DISABILED.equals(disabilityName))
                                    {
                                        if (totaldisability == 100)
                                        {
                                            target = "railwayconcessionVIprint";
                                        } 
                                        else 
                                        {
                                            target = "railwayfailure";
                                        }
                                    } 
                                    else if (CertificateConstants.HEARING_DISABILITYTYPE.equals(disabilityName))
                                    {
                                        if (totaldisability >= 100)
                                        {
                                            target = "railwayconcessionHIprint";
                                        } 
                                        else 
                                        {
                                            target = "railwayfailure";
                                        }
                                    } 
                                    else if (CertificateConstants.MENTALRETARDATION_DISABILITYTYPE.equals(disabilityName)) 
                                    {
                                        if (totaldisability >= 50)
                                        {
                                            target = "railwayconcessionMRprint";
                                        } 
                                        else 
                                        {
                                            target = "railwayfailure";
                                        }
                                    }
                                    else if (CertificateConstants.MULTIPLE_DISABILITYTYPE.equals(disabilityName))
                                    {
                                        id = true;

                                        territorydto = territorydao.getTotalValues(ds, personcode);
                                        double totalphysical = territorydto.getLocomotortotal();
                                        double visualimpairment = Double.parseDouble(territorydto.getVisual_Impairment());
                                        double hearingimpairment = Double.parseDouble(territorydto.getHearing_Percentage());
                                        double mentalretardation = Double.parseDouble(territorydto.getMental_Retardation_Total());

                                        if ("1".equals(multipledisabilityid)) 
                                        {
                                            if (totalphysical >= 40) 
                                            {
                                                target = "railwayconcessionOHprint";
                                            } 
                                            else if (visualimpairment >= 100)
                                            {
                                                target = "railwayconcessionVIprint";
                                            }
                                            else if (hearingimpairment >= 100) 
                                            {
                                                target = "railwayconcessionHIprint";
                                            } 
                                            else if (mentalretardation >= 50)
                                            {
                                                target = "railwayconcessionMRprint";
                                            } 
                                            else 
                                            {
                                                target = "railwayfailure";
                                            }

                                        } 
                                        else if ("2".equals(multipledisabilityid)) 
                                        {

                                            if (visualimpairment >= 100) 
                                            {

                                                target = "railwayconcessionVIprint";
                                            } 
                                            else if (hearingimpairment >= 100) 
                                            {
                                                target = "railwayconcessionHIprint";
                                            }
                                            else if (mentalretardation >= 50)//if (mentalretardation > totalphysical && mentalretardation > hearingimpairment)
                                            {
                                                if (mentalretardation >= 50)
                                                {
                                                    target = "railwayconcessionMRprint";
                                                } 
                                                else if (totalphysical >= 40) 
                                                {
                                                    target = "railwayconcessionOHprint";
                                                } 
                                                else 
                                                {
                                                    target = "railwayfailure";
                                                }
                                            } else if (totalphysical >= 40)
                                            {
                                                if (mentalretardation > totalphysical && mentalretardation >= 50)
                                                {
                                                    target = "railwayconcessionMRprint";
                                                } 
                                                else if (totalphysical >= 40) 
                                                {
                                                    target = "railwayconcessionOHprint";
                                                } 
                                                else 
                                                {
                                                    target = "railwayfailure";
                                                }
                                            } 
                                            else 
                                            {
                                                target = "railwayfailure";
                                            }
                                        } 
                                        else if ("3".equals(multipledisabilityid)) 
                                        {
                                            if (hearingimpairment >= 100)
                                            {
                                                target = "railwayconcessionHIprint";
                                            } 
                                            else if (visualimpairment >= 100)
                                            {
                                                target = "railwayconcessionVIprint";
                                            }
                                            else if (mentalretardation >= 50)
                                            {
                                                target = "railwayconcessionMRprint";
                                            } 
                                            else if (totalphysical >= 40)
                                            {
                                                target = "railwayconcessionOHprint";
                                            }
                                            else 
                                            {
                                                target = "railwayfailure";
                                            }
                                        } 
                                        else if ("4".equals(multipledisabilityid)) 
                                        {
                                            if (mentalretardation >= 50) 
                                            {
                                                target = "railwayconcessionMRprint";
                                            } 
                                            else if (visualimpairment >= 100)
                                            {

                                                target = "railwayconcessionVIprint";
                                            } else if (hearingimpairment >= 100) {
                                                target = "railwayconcessionHIprint";
                                            } else if (totalphysical >= 40) {
                                                target = "railwayconcessionOHprint";
                                            } else {
                                                target = "railwayfailure";
                                            }
                                        } else if ("5".equals(multipledisabilityid)) {

                                            if (visualimpairment >= 100) {

                                                target = "railwayconcessionVIprint";
                                            } else if (hearingimpairment >= 100) {
                                                target = "railwayconcessionHI";
                                            } else if (mentalretardation >= 50) {
                                                target = "railwayconcessionMRprint";
                                            } else if (totalphysical >= 40) {
                                                target = "railwayconcessionOHprint";
                                            } else {
                                                target = "railwayfailure";
                                            }
                                        } else {
                                            target = "railwayfailure";
                                        }
                                    } else {
                                        target = "railwayfailure";
                                    }
                                } else {
                                    target = "railwayfailure";
                                }


                                DoctorsInformationDAO ddao = new DoctorsInformationDAO();

                                PartADAO pdao = new PartADAO();
                                DoctorsInformationDTO ddto = new DoctorsInformationDTO();
                                PartADTO pdto = new PartADTO();
                                String dn = null;
                                String de = null;
                                String rn = null;
                                if (!target.equalsIgnoreCase("railwayfailure")) 
                                {
                                    //** Railway Certificate issued Doctor details comming from  the following method in print also.
                                    pdto = pdao.getRailwayDoctorDetails(ds, personcode);
                                    if (pdto != null) 
                                    {
                                        dn = pdto.getDoctor1name();
                                        rn = pdto.getDoctor1regnumber();
                                        de = pdto.getDoctor1designation();
                                        if (dn != null) {
                                            Iterator iter1 = reportlist.iterator();
                                            iter1.hasNext();
                                            territorydto = (TerritoryDTO) iter1.next();
                                            //if(dn.startsWith("Dr.")|| dn.startsWith("dr."))
                                            // dn=dn.replace("Dr.", " ");
                                            territorydto.setFirstdoctorname(dn);
                                            territorydto.setFirstdesgination(de);
                                            territorydto.setFirstdoctornumber(rn);
                                            territorydto.setDateofisse(pdto.getDateofissue());
                                            reportlist.remove(0);
                                            reportlist.add(territorydto);
                                            session.setAttribute("reportlist", reportlist);
                                        }

                                    }



                                }


                            } 
                            else if (print.equals("certificate")) 
                            {
                                if (totaldisability < eligiblePercentage) 
                                {
                                    target = "rejectedcertificate";
                                }
                                else 
                                {
                                    if (disabilityName != null && !"".equals(disabilityName))
                                    {
                                        if (CertificateConstants.LOCOMOTOR_DISABILITYTYPE.equals(disabilityName) || CertificateConstants.LOCOMOTOR_DISABILED.equals(disabilityName)) 
                                        {
                                            target = "physicallimpairmentcertificate";
                                        } 
                                        else if (CertificateConstants.VISUAL_DISABILITYTYPE.equals(disabilityName) || CertificateConstants.VISUAL_DISABILED.equals(disabilityName))
                                        {
                                            target = "visualimpairmentcertificate";
                                        } 
                                        else if (CertificateConstants.HEARING_DISABILITYTYPE.equals(disabilityName)) 
                                        {
                                            target = "hearingimpairmentcertificate";
                                        } 
                                        else if (CertificateConstants.MENTALRETARDATION_DISABILITYTYPE.equals(disabilityName))
                                        {
                                            target = "mentalretardationcertificate";
                                        } 
                                        else if (CertificateConstants.MENTALILLNESS_DISABILITYTYPE.equals(disabilityName))
                                        {
                                            target = "mentalillnesscertificate";
                                        }
                                        else if (CertificateConstants.MULTIPLE_DISABILITYTYPE.equals(disabilityName)) 
                                        {
                                            target = "multipledisabilitycertificate";
                                        }
                                        else 
                                        {
                                            target = "certificate";
                                        }
                                        session.removeAttribute("sadaremCodeAu");
                                    }
                                }
                            } 
                            else if (print.equals("idcard"))
                            {
                                if (totaldisability >= eligiblePercentage)
                                {
                                    target = "idcard";
                                } 
                                else 
                                {
                                    target = "failure";
                                }
                            } 
                            else if (print.equals("certificateprint")) 
                            {
                                if (disabilityName != null && !"".equals(disabilityName))
                                {
                                    if (CertificateConstants.LOCOMOTOR_DISABILITYTYPE.equals(disabilityName) || CertificateConstants.LOCOMOTOR_DISABILED.equals(disabilityName)) 
                                    {
                                        target = "physicalcertificateprint";
                                    } 
                                    else if (CertificateConstants.VISUAL_DISABILITYTYPE.equals(disabilityName) || CertificateConstants.VISUAL_DISABILED.equals(disabilityName))
                                    {
                                        target = "visualcertificateprint";
                                    } 
                                    else if (CertificateConstants.HEARING_DISABILITYTYPE.equals(disabilityName))
                                    {
                                        target = "hearingcertificateprint";
                                    } 
                                    else if (CertificateConstants.MENTALRETARDATION_DISABILITYTYPE.equals(disabilityName))
                                    {
                                        target = "mentalretardationcertificateprint";
                                    }
                                    else if (CertificateConstants.MENTALILLNESS_DISABILITYTYPE.equals(disabilityName)) 
                                    {
                                        target = "mentalillnesscertificateprint";
                                    } 
                                    else if (CertificateConstants.MULTIPLE_DISABILITYTYPE.equals(disabilityName)) 
                                    {
                                        target = "multipledisabilitycertificateprint";
                                    } 
                                    else 
                                    {
                                        target = "certificate";
                                    }
                                    session.removeAttribute("sadaremCodeAu");
                                }
                            } 
                            else if (print.equals("rejectedcertificateprint")) 
                            {
                                target = "rejectedcertificateprint";
                            } 
                            else if (print.equals("idcardprint")) 
                            {
                                target = "idcardprint";
                            } 
                            else 
                            {
                                target = "failure";
                            }
                            
                            if (personcode != null && districtName != null) 
                            {
                                CommonDetails commonDetails = new CommonDetails();
                                String url = getServlet().getServletContext().getRealPath("/");
                                try
                                {
                                  //commonDetails.copyPhotoDtoRelativePath(personcode, districtName, request, url);
                                }
                                catch(Exception e)
                                {
                                	e.printStackTrace();
                                }
                            }
                        }
                    }
                    else 
                    {

                      	//log.info("in CertificateAction execute - 13 else");
                    	
                        /*
                         * getting rejected pwd details
                         */
                        rejectedlist = reportservice.getRejectedData(ds, personcode);
                        Iterator iter = rejectedlist.iterator();
                        iter.hasNext();
                        CertificateDTO certificatedto = new CertificateDTO();
                        certificatedto = (CertificateDTO) iter.next();
                        districtName = certificatedto.getDistrictname();
                        String apflag = territorydto.getFlag();
                        request.setAttribute("apflag", apflag);
                        session.setAttribute("rejectedlist", rejectedlist);
                        target = "rejectecertificatewithpersoncode";
                        if (print.equals("rejectedcertificatepersoncodeprint")) 
                        {
                            target = "rejectedcertificatepersoncodeprint";
                        }
                        if (print.equals("idcard"))
                        {
                            target = "failure";
                        }
                    }
                    if (personcode != null && districtName != null)
                    {
                        CommonDetails commonDetails = new CommonDetails();
                        String url = getServlet().getServletContext().getRealPath("/");
                        /*commonDetails.copyPhotoDtoRelativePath(personcode, districtName, request, url);*/
                        try
                        {
                        	//commonDetails.copyPhotoDtoRelativePath(personcode, districtName, request, url);
                        }
                        catch(Exception e)
                        {
                        	e.printStackTrace();
                        }
                    }
                } 
                else 
                {
                    target = "failure";
                }
            }

           	//log.info("in CertificateAction execute - 14 after");
            String printType = CommonUtility.checkNullObj(request.getParameter("print"));
            System.out.println("printType : "+printType);
            if (printType != null && (printType.equals("certificateprint") ||printType.equalsIgnoreCase("certificate") )) 
            {
                certificateType = "CT";
            } 
            else if (printType != null && printType.equals("railwayformprint"))
            {
                certificateType = "RC";
            } 
            else if (printType != null && printType.equals("rejectedcertificateprint")) 
            {
                certificateType = "CT";
            }


            System.out.println("certificateType : "+certificateType);
           	//log.info("in CertificateAction execute - 15");

            if (certificateType != null)
            {
                String campIdValue = session.getAttribute("campId").toString();
                String distId = session.getAttribute("districtId").toString();
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
System.out.println("category : "+category);
                PrintDetailsDAO printDetailsDAO = new PrintDetailsDAO();
                // PrintDetailsService printDetailsService = PrintDetailsServiceFactory.getPrintDetailsServiceImpl();
                printDetailsDAO.insertCertificatePrintDetails1(ds, personcode, category, certificateType, loginId, campIdValue, distId);
                category = "";
            }

           //	log.info("in CertificateAction execute - 13 after");
            /*
             * throwing our own exception
             */
        } 
        catch (SADAREMDBException sADAREMException) 
          {
        	sADAREMException.printStackTrace();
        	log.error(sADAREMException);
        	 request.setAttribute("msg", "Invalid SADAREM ID.");
      	     return mapping.findForward("PersoncodeFailure");  
          } 
        catch (Exception e)
          {
        	e.printStackTrace();
            log.error(e);
        	 request.setAttribute("msg", "Invalid SADAREM ID.");
      	     return mapping.findForward("PersoncodeFailure");  
        }
        System.out.println("target : "+target);
        
        return mapping.findForward(target);
    }
}
