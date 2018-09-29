/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.BaseDispatchAction;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.CommonDetails;
import org.bf.disability.common.PDFPrinter;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dto.AmputationDto;
import org.bf.disability.dto.CardioPulmonaryDTO;
import org.bf.disability.dto.DwarfismDTO;
import org.bf.disability.dto.EvaluationDTO;
import org.bf.disability.dto.HearingImpairmentDto;
import org.bf.disability.dto.LocomotorneedsDTO;
import org.bf.disability.dto.LowerExtremityDTO;
import org.bf.disability.dto.MRBinetKamatDetailedTestDTO;
import org.bf.disability.dto.MRDevelopmentalScreeningTestDTO;
import org.bf.disability.dto.MentalIllnessDTO;
import org.bf.disability.dto.MentalRetardationDTO;
import org.bf.disability.dto.MentalRetardationTestsDTO;
import org.bf.disability.dto.PartADTO;
import org.bf.disability.dto.PartCUpdateDTO;
import org.bf.disability.dto.TerritoryDTO;
import org.bf.disability.dto.TransverseDTO;
import org.bf.disability.dto.TrunkDTO;
import org.bf.disability.dto.UpperExtrimityDto;
import org.bf.disability.dto.VsmsScreeningTestDTO;
import org.bf.disability.service.AmputationService;
import org.bf.disability.service.DataEnteredFieldsService;
import org.bf.disability.service.EvaluationService;
import org.bf.disability.service.HearingImpairmentService;
import org.bf.disability.service.LocomotorneedsService;
import org.bf.disability.service.LowerExtremityService;
import org.bf.disability.service.MRDevelopmentalScreeningTestService;
import org.bf.disability.service.MentalRetardationService;
import org.bf.disability.service.PartAService;
import org.bf.disability.service.PartCService;
import org.bf.disability.service.TerritoryService;
import org.bf.disability.service.TransverseService;
import org.bf.disability.service.TrunkService;
import org.bf.disability.service.UpperExtrimityService;
import org.bf.disability.service.VisualImpairmentService;
import org.bf.disability.service.VsmsScreeningTestService;
import org.bf.disability.servicefactory.AmputationServiceFactory;
import org.bf.disability.servicefactory.DataEnteredFieldsServiceFactory;
import org.bf.disability.servicefactory.EvaluationServiceFactory;
import org.bf.disability.servicefactory.HearingImpairmentServiceFactory;
import org.bf.disability.servicefactory.LocomotorNeedsServiceFactory;
import org.bf.disability.servicefactory.LowerExtremityServiceFactory;
import org.bf.disability.servicefactory.MentalRetardationServiceFactory;
import org.bf.disability.servicefactory.PartAServiceFactory;
import org.bf.disability.servicefactory.PartcServiceFactory;
import org.bf.disability.servicefactory.TerritoryServiceFactory;
import org.bf.disability.servicefactory.TransverseServiceFactory;
import org.bf.disability.servicefactory.TrunkServiceFactory;
import org.bf.disability.servicefactory.UpperExterimityServiceFactory;
import org.bf.disability.servicefactory.VisualImpairmentServiceFactory;
import org.bf.disability.servicefactory.VsmsServiceFactory;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.tcs.sadarem.util.CommonUtility;

/**
 *
 * @author t_bapinaidu
 */
public class DataEnteredFieldsAction extends BaseDispatchAction {

    public ActionForward getDataEnteredFieldsDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException {

        String target = null;
        ActionMessages actionMessages = null;
        DataSource datasource = null;
        String personCode = null;
        Map<String, List<String>> dataEnteredListMap = null;
        try {
            datasource = getDataSource(request);
            if (datasource == null || "null".equals(datasource)) {
                datasource = JNDIDataSource.getConnection();
            }
            personCode = (String) request.getParameter("personcode");
            CommonDetails commondetails = new CommonDetails();
            HttpSession session = request.getSession(true);
            String districtid = CommonUtility.checkNullObj(session.getAttribute("districtId"));
          //  String role_id=(String) session.getAttribute("roleId");
            int currentRoleId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("roleId")));
            boolean districtFlag = commondetails.checkDistrictFlag(personCode, districtid);
            boolean accessRoleFlag = commondetails.campareRoleIds(String.valueOf(currentRoleId), CommonConstants.ALL_EDIT_PERMISSION_IDS);
            if (districtFlag || accessRoleFlag || Integer.parseInt(CommonConstants.ROLE_APPELLATE)==currentRoleId || Integer.parseInt(CommonConstants.SERPLOGIN_ROLEID)==currentRoleId) 
            
             
            {  
                DataEnteredFieldsService dataEnteredFieldsService = DataEnteredFieldsServiceFactory.getDataEnteredFieldsServiceImpl();
                dataEnteredListMap = dataEnteredFieldsService.getDataEnteredFieldsDetails(datasource, personCode);
                if (dataEnteredListMap != null && !dataEnteredListMap.isEmpty()) {
                    request.setAttribute("dataEnteredListMap", dataEnteredListMap);
                    request.setAttribute("personcode", personCode);
                    String doctor = request.getParameter("doctor");
                    request.setAttribute("doctor", doctor);
                    if (doctor != null && doctor.equalsIgnoreCase("doctor")) {
                        target = "doctorsuccess";
                    } else {
                        target = "success";
                    }

                } else {
                    request.setAttribute("msg", "Entered PersonCode does not have details.");
                    target = "personcode";
                }
            } else {
                request.setAttribute("msg", "Please Enter Valid PersonCode.");
                target = "personcode";
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

    public ActionForward getDataEnteredFieldDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException {
        String target = null;
        ActionMessages actionMessages = null;
        DataSource datasource = null;
        String personCode = null;
        CommonEnums dataEntredSection = null;
        ArrayList personStatusList = null;
        String personstatus = null;
        String districtname = null;
        PartADTO partADTO = null;
        Map dataEnteredSectionsMap = null;
        // varibles for PDF Generation
        PdfPTable mainPdfPTable = null;
        Map sectionTableMap = null;
        PdfPTable headerPdfPTable = null;
        try {
            datasource = getDataSource(request);
            if (datasource == null || "null".equals(datasource)) {
                datasource = JNDIDataSource.getConnection();
            }
            personCode = (String) request.getParameter("personcode");
            HttpSession session = request.getSession(true);
            int campId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("campId")));
            if (null != personCode && !"".equals(personCode)) {
                personStatusList = new ArrayList();
                TerritoryService territoryservice = TerritoryServiceFactory.getTerritoryServiceImpl();
                personStatusList = territoryservice.getPersonStatus(datasource, personCode);
                if (null != personStatusList && personStatusList.size() > 0) {
                    Iterator iterator = personStatusList.iterator();
                    iterator.hasNext();
                    TerritoryDTO territoryDTO = (TerritoryDTO) iterator.next();
                    personstatus = territoryDTO.getPersonstatus();
                    districtname = territoryDTO.getDistrict_name();
                    //partADTO = partAService.getPersonalDetails(personcode, personstatus, ds, campId);


                    String[] dataenterednames = request.getParameterValues("dataenteredname");
                    if (dataenterednames != null && dataenterednames.length > 0) {
                        dataEnteredSectionsMap = new LinkedHashMap();
                        for (int i = 0; i < dataenterednames.length; i++) {
                            String dataenteredname = dataenterednames[i];
                            dataEntredSection = CommonEnums.valueOf(dataenteredname);
                            if (null != dataEntredSection && !"".equals(dataEntredSection)) {
                                switch (dataEntredSection) 
                                {
                                    case PersonalDetails:
                                        PartAService partAService = PartAServiceFactory.getPartAServiceImpl();
                                        partADTO = new PartADTO();
                                        partADTO = partAService.getPersonalDetails(personCode, personstatus, datasource, campId);
                                        partADTO.setHabitation_name(territoryDTO.getHabitation_name());
                                        partADTO.setVillage_name(territoryDTO.getVillage_name());
                                        partADTO.setDistrict(districtname);
                                        dataEnteredSectionsMap.put(dataenteredname, partADTO);
                                        request.setAttribute("formdate", partADTO.getFromdate());
                                        break;
                                    case DisabilityDetails:
                                        String pwdPhotoDrt = getImagePath(request, "uploadedphotos\\" + personCode + "\\Profilepic.JPG");
                                        if (pwdPhotoDrt != null && !"".equals(pwdPhotoDrt)) {
                                            request.setAttribute("pwdPhotoDrt", pwdPhotoDrt);
                                        }
                                        //  if (partADTO == null || "".equals(partADTO)) {
                                        PartAService partAServicetemp = PartAServiceFactory.getPartAServiceImpl();
                                        partADTO = new PartADTO();
                                        partADTO = partAServicetemp.getDesabilityDetails(personCode, personstatus, datasource, campId);
                                        //  }
                                        dataEnteredSectionsMap.put(dataenteredname, partADTO);
                                        break;
                                    case RejectedDetails:
                                        String pwdRejectPhoto = getImagePath(request, "uploadedphotos\\" + personCode + "\\Profilepic.JPG");
                                        if (pwdRejectPhoto != null && !"".equals(pwdRejectPhoto)) {
                                            request.setAttribute("pwdPhotoDrt", pwdRejectPhoto);
                                        }
                                        if (partADTO == null || "".equals(partADTO)) {
                                            PartAService partAServicetemp1 = PartAServiceFactory.getPartAServiceImpl();
                                            partADTO = new PartADTO();
                                            partADTO = partAServicetemp1.getDesabilityDetails(personCode, personstatus, datasource, campId);
                                        }
                                        dataEnteredSectionsMap.put(dataenteredname, partADTO);
                                        break;
                                    case VisualImpairment:
                                        VisualImpairmentService visulaImpairmentService = VisualImpairmentServiceFactory.getVisualImparmentImpl();
                                        CardioPulmonaryDTO cardioPulmonarydto = new CardioPulmonaryDTO();
                                        cardioPulmonarydto = visulaImpairmentService.getVisualImpairment(datasource, personCode);
                                        dataEnteredSectionsMap.put(dataenteredname, cardioPulmonarydto);
                                        break;
                                    case HearingImpairment:
                                        HearingImpairmentService hearingservice = HearingImpairmentServiceFactory.getHearingServiceImpl();
                                        HearingImpairmentDto hearingdto = new HearingImpairmentDto();
                                        hearingdto = hearingservice.getHearingDetails(datasource, personCode);
                                        dataEnteredSectionsMap.put(dataenteredname, hearingdto);
                                        break;

                                    case Locomotor:
                                        String[] locomotorsubnames = request.getParameterValues("locomotorsubname");
                                        dataEnteredSectionsMap = locomotorDetails(locomotorsubnames, dataEnteredSectionsMap, datasource, personCode);
                                        break;

                                    case MentalRetardation:
                                        String[] mentalretardationtests = request.getParameterValues("mentalretardationtest");
                                        dataEnteredSectionsMap = mentalRetardationTests(mentalretardationtests, dataEnteredSectionsMap, datasource, personCode, request);
                                        break;

                                    case GeneralNeeds:
                                        PartCUpdateDTO partcdto = new PartCUpdateDTO();
                                        PartCService partcservice = PartcServiceFactory.getPartcServiceImpl();
                                        partcdto = partcservice.getLocomotorneeds(datasource, personCode);
                                        dataEnteredSectionsMap.put(dataenteredname, partcdto);
                                        break;
                                    case MentalIllness:
                                        MentalIllnessDTO millnessdto = new MentalIllnessDTO();
                                        VisualImpairmentService mentalIllService = VisualImpairmentServiceFactory.getVisualImparmentImpl();
                                        millnessdto = (MentalIllnessDTO) mentalIllService.getMentalIllnessDetails(datasource, personCode);
                                        dataEnteredSectionsMap.put(dataenteredname, millnessdto);
                                        break;


                                    default:
                                        break;
                                }
                            }
                        }

                        //Write the code for PDF Generation

                        if (null != dataEnteredSectionsMap && !dataEnteredSectionsMap.isEmpty()) {
                            request.setAttribute("dataEnteredSectionsMap", dataEnteredSectionsMap);
                            request.setAttribute("personcode", personCode);
                            float[] widths1 = {1.0f};
                            sectionTableMap = new LinkedHashMap();
                            mainPdfPTable = new PdfPTable(widths1);
                            mainPdfPTable.setSplitLate(false);
                            mainPdfPTable.setExtendLastRow(false);
                            PDFPrinter pDFPrinter = new PDFPrinter();

                            PartAService partAServicetemp = PartAServiceFactory.getPartAServiceImpl();
                            partADTO = new PartADTO();
                            partADTO = partAServicetemp.getPersonalDetails(personCode, personstatus, datasource, campId);

//                            String strDirectoy = getImagePath(request, "images\\apgovtlogo.JPG");
//                            if (strDirectoy != null) {
//                                request.setAttribute("strDirectoy", strDirectoy);
//                            }
//                            String disabilitylogoDirectory = getImagePath(request, "images\\WheelChair.gif");
//                            if (disabilitylogoDirectory != null) {
//                                request.setAttribute("disabilitylogoDirectory", disabilitylogoDirectory);
//                            }

                            sectionTableMap = pDFPrinter.prepareBody(request, mainPdfPTable);
                            headerPdfPTable = pDFPrinter.prepareHeader(request, partADTO);
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            Document document = pDFPrinter.prepareDocument(baos, headerPdfPTable);
                            document = pDFPrinter.setBodyToDocument(document, sectionTableMap,
                                    headerPdfPTable);
                            partADTO = partAServicetemp.getDesabilityDetails(personCode, personstatus, datasource, campId);
                            document = pDFPrinter.setHeaderFooterToDocument(request, document, baos, partADTO);
                            pDFPrinter.print(request, baos);
                            getPDFPopUp(request, response);



                        }
                    }
                    target = "success";
                } else {
                    request.setAttribute("msg", "Details are Not Available.");
                    target = "personcode";
                }

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

    private void getPDFPopUp(HttpServletRequest request, HttpServletResponse response) throws Exception {


        try {
            log.info("Method getPDFPopUp() starts");

            String contentType = (String) request.getAttribute("CONTENT_TYPE");
            String personCode = (String) request.getAttribute("personcode");
            String fileType = (String) request.getAttribute("FILE_TYPE");
            String formName = "SADAREM";



            personCode = (personCode == null) ? "" : personCode;
            fileType = (fileType == null) ? "" : fileType;
            byte[] content = (byte[]) request.getAttribute("CONTENT_BYTES");
            OutputStream outputStream = response.getOutputStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            if (contentType != null && !"".equals(contentType)) {
                response.setContentType(contentType);
            }

            response.setHeader("pragma", "public");
            response.setHeader("Cache-Control", "public");

            response.addHeader("Content-Disposition", "attachment;filename=" + formName + "_" + personCode + fileType);

            if (null != outputStream && null != content) {
                outputStream.write(content);
                outputStream.close();
            }

            log.info("Method getPDFPopUp() ends");
        } catch (Exception e) {
            log.error("Exception occured in getPDFPopUp():" + e);
            e.printStackTrace();
            throw e;
        }

    }

    public String getImagePath(HttpServletRequest request, String imageName) throws Exception {

        String strDirectoy = null;
        try {
            String url = getServlet().getServletContext().getRealPath("/");
            int urlLength = url.length();
            int portLocal = request.getLocalPort();
            if (portLocal == 8084) {
                strDirectoy = url.substring(0, urlLength - 11) + "\\web\\DisabilityUITG\\" + imageName + "";
                // strDirectoy=url.substring(0,urlLength-11)+"\\web\\DisabilityUITG\\uploadedphotos";
            } else if (portLocal == 8011 || portLocal == 8080 || portLocal == 80) {
                strDirectoy = url + "DisabilityUITG\\" + imageName + "";
                // strDirectoy=url+"DisabilityUITG\\uploadedphotos";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return strDirectoy;
    }

    private Map locomotorDetails(String[] locomotorsubnames, Map dataEnteredSectionsMap, DataSource datasource, String personCode) throws Exception {

        CommonEnums dataEntredSection = null;
        UpperExtrimityDto upperExtrimitydto = null;
        LowerExtremityDTO lowerextremitydto = null;
        DwarfismDTO dwarfismdto = null;
        AmputationDto ampdto = null;
        TrunkDTO trunkdto = null;
        LocomotorneedsDTO locomotorneedsdto = null;
        TransverseDTO transversedto = null;
        EvaluationDTO evaluationdto = null;
        try {

            if (locomotorsubnames != null && locomotorsubnames.length > 0) {
                for (int i = 0; i < locomotorsubnames.length; i++) {
                    String dataenteredname = locomotorsubnames[i];
                    dataEntredSection = CommonEnums.valueOf(dataenteredname);
                    if (null != dataEntredSection && !"".equals(dataEntredSection)) {
                        switch (dataEntredSection) {
                            case UpperExtremity:
                                UpperExtrimityService upperextrimityservice = UpperExterimityServiceFactory.getUpperExterimityServiceImpl();
                                upperExtrimitydto = upperextrimityservice.selectUpperExterimityData(datasource, personCode);
                                dataEnteredSectionsMap.put(dataenteredname, upperExtrimitydto);
                                break;
                            case LowerExtremity:
                                LowerExtremityService lowerextremityservice = LowerExtremityServiceFactory.getLowerExtremityServiceImpl();
                                lowerextremitydto = lowerextremityservice.getLowerExtremityDetails(datasource, personCode);
                                dataEnteredSectionsMap.put(dataenteredname, lowerextremitydto);
                                break;
                            case Amputation:
                                AmputationService amputationService = AmputationServiceFactory.getAmputationServiceImpl();
                                ampdto = amputationService.getAmputationDetails(personCode, datasource);
                                dataEnteredSectionsMap.put(dataenteredname, ampdto);
                                break;
                            case CongentialDeficiencies:
                                TransverseService transverseservice = TransverseServiceFactory.getTransverseServiceImpl();
                                transversedto = transverseservice.getTransverseDetails(datasource, personCode);
                                dataEnteredSectionsMap.put(dataenteredname, transversedto);
                                break;
                            case Trunk:
                                TrunkService trunkservice = TrunkServiceFactory.getTrunkServiceImpl();
                                trunkdto = trunkservice.getTrunkDetails(datasource, personCode);
                                dataEnteredSectionsMap.put(dataenteredname, trunkdto);
                                break;
                            case NeurologicalConditions:
                                EvaluationService evaluationservice = EvaluationServiceFactory.getEvaluationServiceImpl();
                                evaluationdto = evaluationservice.getEvaluationDetails(datasource, personCode);
                                dataEnteredSectionsMap.put(dataenteredname, evaluationdto);
                                break;

                            case CardiopulmonaryDiseases:
                                VisualImpairmentService visulaImpairmentService = VisualImpairmentServiceFactory.getVisualImparmentImpl();
                                float cardiopulmonary = visulaImpairmentService.getCardioPulmonaryDetails(datasource, personCode);
                                dataEnteredSectionsMap.put(dataenteredname, String.valueOf(cardiopulmonary));
                                break;
                            case Dwarfism:
                                VisualImpairmentService dwarfismService = VisualImpairmentServiceFactory.getVisualImparmentImpl();
                                dwarfismdto = dwarfismService.getDwarfismDetails(datasource, personCode);
                                dataEnteredSectionsMap.put(dataenteredname, dwarfismdto);
                                break;
                            default:
                                break;
                        }
                    }
                }
                LocomotorneedsService locomotorneedsservice = LocomotorNeedsServiceFactory.getLocomotorNeedsServiceImpl();
                locomotorneedsdto = locomotorneedsservice.getLocomotorneeds(datasource, personCode);
                dataEnteredSectionsMap.put("LocomotorNeeds", locomotorneedsdto);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return dataEnteredSectionsMap;
    }

    private Map mentalRetardationTests(String[] mentalretardationtests, Map dataEnteredSectionsMap, DataSource datasource, String personCode, HttpServletRequest request) throws Exception {

        CommonEnums dataEntredSection = null;
        MRDevelopmentalScreeningTestDTO mrDevelopmentalScreeningTestDTO = null;
        VsmsScreeningTestDTO vsmsscreeningtestdto = null;
        MentalRetardationTestsDTO mentalretardationtestdto = null;
        MRBinetKamatDetailedTestDTO MRBKDTestDTO = null;
        MentalRetardationDTO mentalRetardationDTO = null;
        try {

            if (mentalretardationtests != null && mentalretardationtests.length > 0) {
                MentalRetardationService mentalretarservice = MentalRetardationServiceFactory.getMentalRetardationServiceImpl();
                for (int i = 0; i < mentalretardationtests.length; i++) {
                    String dataenteredname = mentalretardationtests[i];
                    dataEntredSection = CommonEnums.valueOf(dataenteredname);
                    if (null != dataEntredSection && !"".equals(dataEntredSection)) {
                        switch (dataEntredSection) {
                            case DevelopmentalScreeningTest:
                                MRDevelopmentalScreeningTestService mrDevelopmentalScreeningTestService = new MRDevelopmentalScreeningTestService();
                                mrDevelopmentalScreeningTestDTO = new MRDevelopmentalScreeningTestDTO();
                                mrDevelopmentalScreeningTestDTO.setPersoncode(personCode);
                                mrDevelopmentalScreeningTestDTO = mrDevelopmentalScreeningTestService.selectMRDevelopmentalScreeningTestDetails(datasource, mrDevelopmentalScreeningTestDTO);
                                mentalretardationtestdto = mentalretarservice.getDevelopmentalData(datasource, personCode);
                                request.setAttribute("DSTIQDetails", mentalretardationtestdto);
                                dataEnteredSectionsMap.put(dataenteredname, mrDevelopmentalScreeningTestDTO);
                                break;
                            case VinelandSocialMaturityScaleRecordSheet:
                                VsmsScreeningTestService vsmsscreeningtestservice = VsmsServiceFactory.getVsmsScreeningTestServiceImpl();
                                vsmsscreeningtestdto = vsmsscreeningtestservice.getVsmsScreeningTest(datasource, personCode);
                                mentalretardationtestdto = mentalretarservice.getVinelandData(datasource, personCode);
                                request.setAttribute("VSMSIQDetails", mentalretardationtestdto);
                                dataEnteredSectionsMap.put(dataenteredname, vsmsscreeningtestdto);
                                break;
                            case BinetKamatTestofIntelligence:
                                MRBKDTestDTO = mentalretarservice.getMRBinetKamatDetailedTestDetails(datasource, personCode);
                                mentalretardationtestdto = mentalretarservice.gettBinetKamaldata(datasource, personCode);
                                request.setAttribute("BKTIQDetails", mentalretardationtestdto);
                                dataEnteredSectionsMap.put(dataenteredname, MRBKDTestDTO);
                                break;
                            case MalinsIntelligenceScaleforIndianChildren:
                                mentalretardationtestdto = mentalretarservice.getMRmalinstestDetails(datasource, personCode);
                                dataEnteredSectionsMap.put(dataenteredname, mentalretardationtestdto);
                                break;
                            case SeguinFormBoard:
                                mentalretardationtestdto = mentalretarservice.getMRseguintestDetails(datasource, personCode);
                                dataEnteredSectionsMap.put(dataenteredname, mentalretardationtestdto);
                                break;
                            case AlexanderPassAlongTest:
                                mentalretardationtestdto = mentalretarservice.getMRAlexanderTestDetails(datasource, personCode);
                                dataEnteredSectionsMap.put(dataenteredname, mentalretardationtestdto);
                                break;
                            case BhatiasBatteryofIntelligenceTests:
                                mentalretardationtestdto = mentalretarservice.getBhatiaTestDetails(datasource, personCode);
                                dataEnteredSectionsMap.put(dataenteredname, mentalretardationtestdto);
                                break;

                            default:
                                break;
                        }
                    }
                }

                mentalRetardationDTO = mentalretarservice.getMentalDetails(datasource, personCode);
                dataEnteredSectionsMap.put("MentalRetardationNeeds", mentalRetardationDTO);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return dataEnteredSectionsMap;
    }

    public enum CommonEnums {

        PersonalDetails, DisabilityDetails, VisualImpairment, HearingImpairment,
        Locomotor, UpperExtremity, LowerExtremity, Amputation, CongentialDeficiencies,
        Trunk, NeurologicalConditions, CardiopulmonaryDiseases, Dwarfism, LocomotorNeeds,
        MentalRetardation, DevelopmentalScreeningTest, VinelandSocialMaturityScaleRecordSheet,
        MalinsIntelligenceScaleforIndianChildren, SeguinFormBoard, AlexanderPassAlongTest,
        BhatiasBatteryofIntelligenceTests, BinetKamatTestofIntelligence, MentalRetardationNeeds,
        GeneralNeeds, MentalIllness, RejectedDetails;
    }
}
