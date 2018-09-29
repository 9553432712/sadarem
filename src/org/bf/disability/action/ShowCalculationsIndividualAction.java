package org.bf.disability.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.ShowCalculationsDAO;
import org.bf.disability.dto.TerritoryDTO;
import org.bf.disability.util.AmputationImpl;
import org.bf.disability.util.BBPTIImpl;
import org.bf.disability.util.BKTImpl;
import org.bf.disability.util.CardioPulmonaryImpl;
import org.bf.disability.util.DSTImpl;
import org.bf.disability.util.DwarfismImpl;
import org.bf.disability.util.HearingImpairmentImpl;
import org.bf.disability.util.LECalculationsImpl;
import org.bf.disability.util.MISICImpl;
import org.bf.disability.util.MentalIllnessImpl;
import org.bf.disability.util.MentalRetardationImpl;
import org.bf.disability.util.PATImpl;
import org.bf.disability.util.PIDuetoNeurologicalConditionsImpl;
import org.bf.disability.util.SFBImpl;
import org.bf.disability.util.TotalDisabilityImpl;
import org.bf.disability.util.TotalOhImpl;
import org.bf.disability.util.TransverseImpl;
import org.bf.disability.util.TrunkImpl;
import org.bf.disability.util.UECalculationsImpl;
import org.bf.disability.util.VSMSImpl;
import org.bf.disability.util.VisualImpairmentImpl;

public class ShowCalculationsIndividualAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String target = null;
        DataSource dataSource = null;
        //DataSource dataSource = getDataSource(request);



        HttpSession session = request.getSession(true);
        String personCode = (String) session.getAttribute("personcode");

        String falgPrint = null;
        TerritoryDTO territoryDTO = new TerritoryDTO();
        if (request.getParameter("flagPrint") != null) {
            falgPrint = request.getParameter("flagPrint");
        }

        ShowCalculationsDAO showCalculationsDAO = new ShowCalculationsDAO();
        HearingImpairmentImpl hearingImpairmentImpl = new HearingImpairmentImpl();
        VisualImpairmentImpl visualImpairmentImpl = new VisualImpairmentImpl();
        MentalIllnessImpl mentalIllnessImpl = new MentalIllnessImpl();


        UECalculationsImpl uECalculationsImpl = new UECalculationsImpl();
        LECalculationsImpl lECalculationsImpl = new LECalculationsImpl();
        AmputationImpl amputationImpl = new AmputationImpl();
        TransverseImpl transverseImpl = new TransverseImpl();
        TrunkImpl trunkImpl = new TrunkImpl();
        PIDuetoNeurologicalConditionsImpl pIDuetoNeurologicalConditionsImpl = new PIDuetoNeurologicalConditionsImpl();
        CardioPulmonaryImpl cardioPulmonaryImpl = new CardioPulmonaryImpl();
        DwarfismImpl dwarfismImpl = new DwarfismImpl();

        DSTImpl dstImpl = new DSTImpl();
        VSMSImpl vsmsImpl = new VSMSImpl();
        SFBImpl sfbImpl = new SFBImpl();
        BKTImpl bktImpl = new BKTImpl();
        PATImpl patImpl = new PATImpl();
        MISICImpl misicImpl = new MISICImpl();
        BBPTIImpl bbptiImpl = new BBPTIImpl();
        MentalRetardationImpl mentalRetardationImpl = new MentalRetardationImpl();

        TotalOhImpl totalOhImpl = new TotalOhImpl();
        TotalDisabilityImpl totalDisabilityImpl = new TotalDisabilityImpl();

        String printflag = (String) request.getParameter("flagPrint");

        try {
            dataSource = getDataSource(request);
            if (dataSource == null || "null".equals(dataSource)) {
                dataSource = JNDIDataSource.getConnection();
            }
            int typeofDisabilityFlag = Integer.parseInt(request.getParameter("typeofDisabilityFlag"));
            String doctor = request.getParameter("doctor");
            request.setAttribute("doctor", doctor);
            switch (typeofDisabilityFlag) {

                case 1:
                    uECalculationsImpl.populateUpperExtremityCalculations(dataSource, request, personCode);
                    if (falgPrint == null) {
                        if (doctor != null && doctor.equalsIgnoreCase("doctor")) {
                            target = "ohUedoctor";
                        } else {
                            target = "ohUe";
                        }
                    } else {
                        target = "ohUePrint";
                    }
                    break;
                case 2:
                    lECalculationsImpl.populateLowerExtremityCalculations(dataSource, request, personCode);
                    if (falgPrint == null) {
                        if (doctor != null && doctor.equalsIgnoreCase("doctor")) {
                            target = "ohLedoctor";
                        } else {
                            target = "ohLe";
                        }
                    } else {
                        target = "ohLePrint";
                    }
                    break;
                case 3:
                    amputationImpl.populateAmputationCalculations(dataSource, request, personCode);
                    if (falgPrint == null) {
                        if (doctor != null && doctor.equalsIgnoreCase("doctor")) {
                            target = "ohAmpdoctor";
                        } else {
                            target = "ohAmp";
                        }

                    } else {
                        target = "ohAmpPrint";
                    }
                    break;
                case 4:
                    transverseImpl.populateTransverseCalculations(dataSource, request, personCode);
                    if (falgPrint == null) {
                        if (doctor != null && doctor.equalsIgnoreCase("doctor")) {
                            target = "ohTransdoctor";
                        } else {
                            target = "ohTrans";
                        }

                    } else {
                        target = "ohTransPrint";
                    }
                    break;
                case 5:
                    trunkImpl.populateTrunkCalculations(dataSource, request, personCode);
                    if (falgPrint == null) {
                        if (doctor != null && doctor.equalsIgnoreCase("doctor")) {
                            target = "ohTrunkdoctor";
                        } else {
                            target = "ohTrunk";
                        }
                    } else {
                        target = "ohTrunkPrint";
                    }
                    break;
                case 6:
                    pIDuetoNeurologicalConditionsImpl.populatePIDuetoNeurologicalConditionsCalculations(dataSource, request, personCode);
                    if (falgPrint == null) {
                        if (doctor != null && doctor.equalsIgnoreCase("doctor")) {
                            target = "ohPhysicaldoctor";
                        } else {
                            target = "ohPhysical";
                        }
                    } else {
                        target = "ohPhysicalPrint";
                    }
                    break;
                case 7:
                    cardioPulmonaryImpl.populateCardioPulmonaryCalculations(dataSource, request, personCode);
                    if (falgPrint == null) {
                        if (doctor != null && doctor.equalsIgnoreCase("doctor")) {
                            target = "ohCardiodoctor";
                        } else {
                            target = "ohCardio";
                        }

                    } else {
                        target = "ohCardioPrint";
                    }
                    break;
                case 8:
                    dwarfismImpl.populateDwarfismCalculations(dataSource, request, personCode);
                    if (falgPrint == null) {
                        if (doctor != null && doctor.equalsIgnoreCase("doctor")) {
                            target = "ohDwarfdoctor";
                        } else {
                            target = "ohDwarf";
                        }

                    } else {
                        target = "ohDwarfPrint";
                    }
                    break;
                case 10:
                    visualImpairmentImpl.populateVICalculations(dataSource, request, personCode);
                    if (falgPrint == null) {
                        if (doctor != null && doctor.equalsIgnoreCase("doctor")) {
                            target = "vidoctor";
                        } else {
                            target = "vi";
                        }

                    } else {
                        target = "viPrint";
                    }
                    break;

                case 19:
                    hearingImpairmentImpl.populateHICalculations(dataSource, request, personCode);
                    if (falgPrint == null) {
                        if (doctor != null && doctor.equalsIgnoreCase("doctor")) {
                            target = "hidoctor";
                        } else {
                            target = "hi";
                        }

                    } else {
                        target = "hiPrint";
                    }
                    break;


                case 9:
                    mentalRetardationImpl.populateMRCalculations(dataSource, request, personCode);
                    if (falgPrint == null) {
                        if (doctor != null && doctor.equalsIgnoreCase("doctor")) {
                            target = "MRdoctor";
                        } else {
                            target = "MR";
                        }

                    } else {
                        target = "mrPrint";
                    }
                    break;
                case 11:
                    dstImpl.populateDSTCalculations(dataSource, request, personCode);
                    if (falgPrint == null) {
                        target = "DSTdoctor";
                    } else {
                        target = "dstPrint";
                    }
                    break;
                case 12:
                    vsmsImpl.populateVSMSCalculations(dataSource, request, personCode);
                    if (falgPrint == null) {
                        if (doctor != null && doctor.equalsIgnoreCase("doctor")) {
                            target = "VSMSdoctor";
                        } else {
                            target = "VSMS";
                        }

                    } else {
                        target = "vsmsPrint";
                    }
                    break;
                case 13:
                    bktImpl.populateBKTCalculations(dataSource, request, personCode);
                    if (falgPrint == null) {
                        if (doctor != null && doctor.equalsIgnoreCase("doctor")) {
                            target = "MRBKTdoctor";
                        } else {
                            target = "MRBKT";
                        }

                    } else {
                        target = "bktPrint";
                    }
                    break;
                case 14:
                    misicImpl.populateMISICCalculations(dataSource, request, personCode);
                    if (falgPrint == null) {
                        if (doctor != null && doctor.equalsIgnoreCase("doctor")) {
                            target = "MISICdoctor";
                        } else {
                            target = "MISIC";
                        }

                    } else {
                        target = "misicPrint";
                    }
                    break;
                case 15:
                    sfbImpl.populateSFBCalculations(dataSource, request, personCode);
                    if (falgPrint == null) {
                        if (doctor != null && doctor.equalsIgnoreCase("doctor")) {
                            target = "SFBdoctor";
                        } else {
                            target = "SFB";
                        }

                    } else {
                        target = "sfbPrint";
                    }
                    break;
                case 16:
                    patImpl.populatePATCalculations(dataSource, request, personCode);
                    if (falgPrint == null) {
                        if (doctor != null && doctor.equalsIgnoreCase("doctor")) {
                            target = "MRPATdoctor";
                        } else {
                            target = "MRPAT";
                        }

                    } else {
                        target = "patPrint";
                    }
                    break;
                case 17:
                    bbptiImpl.populateBBPTICalculations(dataSource, request, personCode);
                    if (falgPrint == null) {
                        if (doctor != null && doctor.equalsIgnoreCase("doctor")) {
                            target = "BBPTIdoctor";
                        } else {
                            target = "BBPTI";
                        }

                    } else {
                        target = "bbptiPrint";
                    }
                    break;

                case 18:
                    mentalIllnessImpl.populateMICalculations(dataSource, request, personCode);
                    if (falgPrint == null) {
                        if (doctor != null && doctor.equalsIgnoreCase("doctor")) {
                            target = "midoctor";
                        } else {
                            target = "mi";
                        }

                    } else {
                        target = "miPrint";
                    }
                    break;
                case 20:
                    totalOhImpl.populateTotalOhCalculations(dataSource, request, personCode);
                    if (falgPrint == null) {
                        if (doctor != null && doctor.equalsIgnoreCase("doctor")) {
                            target = "ohdoctor";
                        } else {
                            target = "oh";
                        }

                    } else {
                        target = "ohPrint";
                    }
                    break;

                case 21:
                    totalDisabilityImpl.populateTotalDisabilityCalculations(dataSource, request, personCode);
                    if (falgPrint == null) {
                        target = "totaldisability";
                    } else {
                        target = "totaldisabilityPrint";
                    }
                    break;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }
}   
    
    
    


    

