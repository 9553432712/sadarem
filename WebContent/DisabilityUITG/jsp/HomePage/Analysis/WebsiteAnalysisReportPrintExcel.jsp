<%-- 
    Document   : WebsiteAnalysisReport
    Created on : Dec 11, 2010, 3:41:07 PM
    Author     : 509865
--%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="org.bf.disability.dto.FunctionalNeedReportDTO" %>
<%@ page contentType="application/MyExcel.ms-excel" %>

<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<%         Iterator iter = null;

            String fromdate = (String) request.getParameter("fromdate");
            String todate = (String) request.getParameter("todate");
            String district_id = (String) request.getParameter("district_id");
            String mandal_id = (String) request.getParameter("mandal_id");
            String village_id = (String) request.getParameter("village_id");
            String habitation_id = (String) request.getParameter("habitation_id");
            String dName = (String) request.getParameter("districtName");
            String mName = (String) request.getParameter("mandalName");
            String vName = (String) request.getParameter("villageName");
            String hName = (String) request.getParameter("habitationName");
            String typeOfDisability = (String) request.getParameter("typeofdisability");
            String category = (String) request.getParameter("reportcategory");
            String subCategory = (String) request.getParameter("reportSubcategory");
            int otherNeedsTotal = 0;
            int sergeryVITotal = 0;
            int feedingTotal = 0, toiletingBathingTotal = 0, total = 0;
            int brushingTotal = 0, combingTotal = 0, dressingTotal = 0;
            int writingTotal = 0, drivingCyclingTotal = 0, bedTransferTotal = 0;
            int otherNeedsVITotal = 0;
            int speechTherapyBelowThreeYearsHITotal = 0, speechTherapyAboveThreeYearsHITotal = 0;
            int languageDevelopmentTotal = 0, surgeryHITotal = 0, cochlearImplantationTotal = 0;

            ArrayList assistiveDevicesVIList = new ArrayList();

            assistiveDevicesVIList = (ArrayList) request.getAttribute("assistiveDevicesVIList");
            int whiteCaneBlindStickTotal = 0, brailleEquipmentsTotal = 0;
            int arithmeticFramesAbacusTotal = 0, lowVisionAidsSpectaclesMagnifiersTotal = 0, speechSynthesizerTotal = 0;
            int brailleShortHandMachinesWritersTotal = 0, talkingWatchCalculatorTotal = 0, viADLEquipmentTotal = 0;
            if (request.getAttribute("assistiveDevicesVIList") != null) {
                iter = assistiveDevicesVIList.iterator();
                while (iter.hasNext()) {
                    FunctionalNeedReportDTO totalDTO = (FunctionalNeedReportDTO) iter.next();
                    whiteCaneBlindStickTotal = whiteCaneBlindStickTotal + totalDTO.getWhiteCaneBlindStick();
                    brailleEquipmentsTotal = brailleEquipmentsTotal + totalDTO.getBrailleEquipments();
                    arithmeticFramesAbacusTotal = arithmeticFramesAbacusTotal + totalDTO.getArithmeticFramesAbacus();
                    lowVisionAidsSpectaclesMagnifiersTotal = lowVisionAidsSpectaclesMagnifiersTotal
                            + totalDTO.getLowVisionAidsSpectaclesMagnifiers();
                    speechSynthesizerTotal = speechSynthesizerTotal + totalDTO.getSpeechSynthesizer();
                    brailleShortHandMachinesWritersTotal = brailleShortHandMachinesWritersTotal
                            + totalDTO.getBrailleShortHandMachinesWriters();
                    talkingWatchCalculatorTotal = talkingWatchCalculatorTotal + totalDTO.getTalkingWatchCalculator();
                    viADLEquipmentTotal = viADLEquipmentTotal + totalDTO.getViADLEquipment();
                    total = total + totalDTO.getTotal();
                }
            }
            ArrayList otherNeedsVIList = new ArrayList();
            if (request.getAttribute("otherNeedsVIList") != null) {
                otherNeedsVIList = (ArrayList) request.getAttribute("otherNeedsVIList");

                iter = otherNeedsVIList.iterator();
                while (iter.hasNext()) {
                    FunctionalNeedReportDTO totalDTO = (FunctionalNeedReportDTO) iter.next();
                    otherNeedsVITotal = otherNeedsVITotal + totalDTO.getOtherNeedsVI();
                }
            }
            ArrayList medicalInterventionHIList = new ArrayList();
            if (request.getAttribute("medicalInterventionHIList") != null) {

                medicalInterventionHIList = (ArrayList) request.getAttribute("medicalInterventionHIList");


                iter = medicalInterventionHIList.iterator();
                while (iter.hasNext()) {
                    FunctionalNeedReportDTO totalDTO = (FunctionalNeedReportDTO) iter.next();
                    speechTherapyBelowThreeYearsHITotal = speechTherapyBelowThreeYearsHITotal
                            + totalDTO.getSpeechTherapyBelowThreeYearsHI();
                    speechTherapyAboveThreeYearsHITotal = speechTherapyAboveThreeYearsHITotal
                            + totalDTO.getSpeechTherapyAboveThreeYearsHI();
                    languageDevelopmentTotal = languageDevelopmentTotal + totalDTO.getLanguageDevelopment();
                    surgeryHITotal = surgeryHITotal + totalDTO.getSurgeryHI();
                    cochlearImplantationTotal = cochlearImplantationTotal + totalDTO.getCochlearImplantation();
                    total = total + totalDTO.getTotal();
                }
            }

            ArrayList medicalInterventionVIList = new ArrayList();
            if (request.getAttribute("medicalInterventionVIList") != null) {
                medicalInterventionVIList = (ArrayList) request.getAttribute("medicalInterventionVIList");

                iter = medicalInterventionVIList.iterator();
                while (iter.hasNext()) {
                    FunctionalNeedReportDTO totalDTO = (FunctionalNeedReportDTO) iter.next();
                    sergeryVITotal = sergeryVITotal + totalDTO.getSugeryVI();
                }
            }

            ArrayList otherNeedsList = new ArrayList();
            if (request.getAttribute("otherNeedsList") != null) {
                otherNeedsList = (ArrayList) request.getAttribute("otherNeedsList");

                iter = otherNeedsList.iterator();
                while (iter.hasNext()) {
                    FunctionalNeedReportDTO totalDTO = (FunctionalNeedReportDTO) iter.next();
                    otherNeedsTotal = otherNeedsTotal + totalDTO.getOtherNeeds();
                }
            }
            ArrayList adlEquipmentsList = new ArrayList();
            if (request.getAttribute("adlEquipmentsList") != null) {
                adlEquipmentsList = (ArrayList) request.getAttribute("adlEquipmentsList");

                iter = adlEquipmentsList.iterator();
                while (iter.hasNext()) {
                    FunctionalNeedReportDTO totalDTO = (FunctionalNeedReportDTO) iter.next();
                    feedingTotal = feedingTotal + totalDTO.getFeeding();
                    toiletingBathingTotal = toiletingBathingTotal + totalDTO.getToiletingBathing();
                    brushingTotal = brushingTotal + totalDTO.getBrushing();
                    combingTotal = combingTotal + totalDTO.getCombing();
                    dressingTotal = dressingTotal + totalDTO.getDressing();
                    writingTotal = writingTotal + totalDTO.getWriting();
                    drivingCyclingTotal = drivingCyclingTotal + totalDTO.getDrivingCycling();
                    bedTransferTotal = bedTransferTotal + totalDTO.getBedTransfer();
                    total = total + totalDTO.getTotal();
                }
            }

            ArrayList assistiveDevicesWalkingAidsList = new ArrayList();
            assistiveDevicesWalkingAidsList = (ArrayList) request.getAttribute("walkingAidsList");

            int walkingStickSmallTotal = 0, walkingStickLargeTotal = 0;
            int axillarySmallTotal = 0, axillaryMediumTotal = 0, axillaryLargeTotal = 0, axillaryExtraLargeTotal = 0;
            int elbowSmallTotal = 0, elbowMediumTotal = 0, elbowLargeTotal = 0, elbowExtraLargeTotal = 0;
            int gutterSmallTotal = 0, gutterMediumTotal = 0, gutterLargeTotal = 0, gutterExtraLargeTotal = 0;
            int tripodSmallTotal = 0, tripodMediumTotal = 0, tripodLargeTotal = 0, tripodExtraLargeTotal = 0;
            if (request.getAttribute("walkingAidsList") != null) {
                iter = assistiveDevicesWalkingAidsList.iterator();
                while (iter.hasNext()) {
                    FunctionalNeedReportDTO totalDTO = (FunctionalNeedReportDTO) iter.next();
                    walkingStickSmallTotal = walkingStickSmallTotal + totalDTO.getWalkingStickSmall();
                    walkingStickLargeTotal = walkingStickLargeTotal + totalDTO.getWalkingStickLarge();
                    axillarySmallTotal = axillarySmallTotal + totalDTO.getAxillarySmall();
                    axillaryMediumTotal = axillaryMediumTotal + totalDTO.getAxillaryMedium();
                    axillaryLargeTotal = axillaryLargeTotal + totalDTO.getAxillaryLarge();
                    axillaryExtraLargeTotal = axillaryExtraLargeTotal + totalDTO.getAxillaryExtraLarge();
                    elbowSmallTotal = elbowSmallTotal + totalDTO.getElbowSmall();
                    elbowMediumTotal = elbowMediumTotal + totalDTO.getElbowMedium();
                    elbowLargeTotal = elbowLargeTotal + totalDTO.getElbowLarge();
                    elbowExtraLargeTotal = elbowExtraLargeTotal + totalDTO.getElbowExtraLarge();
                    gutterSmallTotal = gutterSmallTotal + totalDTO.getGutterSmall();
                    gutterMediumTotal = gutterMediumTotal + totalDTO.getGutterMedium();
                    gutterLargeTotal = gutterLargeTotal + totalDTO.getGutterLarge();
                    gutterExtraLargeTotal = gutterExtraLargeTotal + totalDTO.getGutterExtraLarge();
                    tripodSmallTotal = tripodSmallTotal + totalDTO.getTripodSmall();
                    tripodMediumTotal = tripodMediumTotal + totalDTO.getTripodMedium();
                    tripodLargeTotal = tripodLargeTotal + totalDTO.getTripodLarge();
                    tripodExtraLargeTotal = tripodExtraLargeTotal + totalDTO.getTripodExtraLarge();
                    total = total + totalDTO.getTotal();
                }
            }

            ArrayList mobilityList = new ArrayList();
            mobilityList = (ArrayList) request.getAttribute("mobilityList");
            int wheelChairSmallTotal = 0, wheelChairLargeTotal = 0, walkingFrameWalkerLargeTotal = 0;
            int tricycleSmallTotal = 0, tricycleLargeTotal = 0, walkingFrameWalkerSmallTotal = 0;
            if (request.getAttribute("mobilityList") != null) {
                iter = mobilityList.iterator();
                while (iter.hasNext()) {
                    FunctionalNeedReportDTO totalDTO = (FunctionalNeedReportDTO) iter.next();
                    wheelChairSmallTotal = wheelChairSmallTotal + totalDTO.getWheelChairSmall();
                    wheelChairLargeTotal = wheelChairLargeTotal + totalDTO.getWheelChairLarge();
                    tricycleSmallTotal = tricycleSmallTotal + totalDTO.getTricycleSmall();
                    tricycleLargeTotal = tricycleLargeTotal + totalDTO.getTricycleLarge();
                    walkingFrameWalkerSmallTotal = walkingFrameWalkerSmallTotal + totalDTO.getWalkingFrameWalkerSmall();
                    walkingFrameWalkerLargeTotal = walkingFrameWalkerLargeTotal + totalDTO.getWalkingFrameWalkerLarge();
                    total = total + totalDTO.getTotal();
                }
            }

            ArrayList assistiveDevicesOrthosisList = new ArrayList();
            assistiveDevicesOrthosisList = (ArrayList) request.getAttribute("orthosisList");
            int aeroplaneSplintTotal = 0, figureEightSplintTotal = 0;
            int forearmSplintTotal = 0, handSplintTotal = 0, hkafohTotal = 0;
            int kafoTotal = 0, afoTotal = 0, kneeOrthosisTotal = 0;
            int dbSplintTotal = 0, modifiedShoeTotal = 0, serialCastingCTEVTotal = 0;
            int cervicalCollarTotal = 0, lsBraceTotal = 0, tlsoBraceForScoliosisKyphosisTotal = 0;
            if (request.getAttribute("orthosisList") != null) {
                iter = assistiveDevicesOrthosisList.iterator();
                while (iter.hasNext()) {
                    FunctionalNeedReportDTO totalDTO = (FunctionalNeedReportDTO) iter.next();
                    aeroplaneSplintTotal = aeroplaneSplintTotal + totalDTO.getAeroplaneSplint();
                    figureEightSplintTotal = figureEightSplintTotal + totalDTO.getFigureEightSplint();
                    forearmSplintTotal = forearmSplintTotal + totalDTO.getForearmSplint();
                    handSplintTotal = handSplintTotal + totalDTO.getHandSplint();
                    hkafohTotal = hkafohTotal + totalDTO.getHkafoh();
                    kafoTotal = kafoTotal + totalDTO.getKafo();
                    afoTotal = afoTotal + totalDTO.getAfo();
                    kneeOrthosisTotal = kneeOrthosisTotal + totalDTO.getKneeOrthosis();
                    dbSplintTotal = dbSplintTotal + totalDTO.getDbSplint();
                    modifiedShoeTotal = modifiedShoeTotal + totalDTO.getModifiedShoe();
                    serialCastingCTEVTotal = serialCastingCTEVTotal + totalDTO.getSerialCastingCTEV();
                    cervicalCollarTotal = cervicalCollarTotal + totalDTO.getCervicalCollar();
                    lsBraceTotal = lsBraceTotal + totalDTO.getLsBrace();
                    tlsoBraceForScoliosisKyphosisTotal = tlsoBraceForScoliosisKyphosisTotal + totalDTO.getTlsoBraceForScoliosisKyphosis();
                    total = total + totalDTO.getTotal();
                }
            }


            ArrayList assistiveDevicesProthosisList = new ArrayList();
            assistiveDevicesProthosisList = (ArrayList) request.getAttribute("prothosisList");
            int shoulderTotal = 0, aboveElbowTotal = 0;
            int elbowDisarticulationTotal = 0, belowElbowTotal = 0, wristDisarticulationTotal = 0;
            int handTotal = 0, cosmeticFingerTotal = 0, hipDisarticulationTotal = 0;
            int aboveKneeTotal = 0, kneeDisarticulationTotal = 0, belowKneeTotal = 0;
            int symesTotal = 0, partialFootTotal = 0;
            if (request.getAttribute("prothosisList") != null) {
                iter = assistiveDevicesProthosisList.iterator();
                while (iter.hasNext()) {
                    FunctionalNeedReportDTO totalDTO = (FunctionalNeedReportDTO) iter.next();
                    shoulderTotal = shoulderTotal + totalDTO.getShoulder();
                    aboveElbowTotal = aboveElbowTotal + totalDTO.getAboveElbow();
                    elbowDisarticulationTotal = elbowDisarticulationTotal + totalDTO.getElbowDisarticulation();
                    belowElbowTotal = belowElbowTotal + totalDTO.getBelowElbow();
                    wristDisarticulationTotal = wristDisarticulationTotal + totalDTO.getWristDisarticulation();
                    handTotal = handTotal + totalDTO.getHand();
                    cosmeticFingerTotal = cosmeticFingerTotal + totalDTO.getCosmeticFinger();
                    hipDisarticulationTotal = hipDisarticulationTotal + totalDTO.getHipDisarticulation();
                    aboveKneeTotal = aboveKneeTotal + totalDTO.getAboveKnee();
                    kneeDisarticulationTotal = kneeDisarticulationTotal + totalDTO.getKneeDisarticulation();
                    belowKneeTotal = belowKneeTotal + totalDTO.getBelowKnee();
                    symesTotal = symesTotal + totalDTO.getSymes();
                    partialFootTotal = partialFootTotal + totalDTO.getPartialFoot();
                    total = total + totalDTO.getTotal();
                }
            }
            ArrayList medicalInterventionList = new ArrayList();
            medicalInterventionList = (ArrayList) request.getAttribute("medicalInterventionList");
            int phyBelow3Total = 0, phyAbove3Total = 0;
            int otBelow3Total = 0, otAbove3Total = 0, surgeryTotal = 0;
            if (request.getAttribute("medicalInterventionList") != null) {
                iter = medicalInterventionList.iterator();
                while (iter.hasNext()) {
                    FunctionalNeedReportDTO totalDTO = (FunctionalNeedReportDTO) iter.next();
                    phyBelow3Total = phyBelow3Total + totalDTO.getPhysiotherapyBelowThreeYears();
                    phyAbove3Total = phyAbove3Total + totalDTO.getPhysiotherapyAboveThreeYears();
                    otBelow3Total = otBelow3Total + totalDTO.getOccupationalTherapyBelowThreeYears();
                    otAbove3Total = otAbove3Total + totalDTO.getOccupationalTherapyAboveThreeYears();
                    surgeryTotal = surgeryTotal + totalDTO.getSurgeryOH();
                    total = total + totalDTO.getTotal();
                }
            }
            ArrayList assistiveDevicesHIList = new ArrayList();
            int lowIntensitySCordTotal = 0, lowIntensityVCordTotal = 0;
            int moderateIntensitySCordTotal = 0, moderateIntensityVCordTotal = 0, highIntensitySCordTotal = 0;
            int highIntensityVCordTotal = 0, implantableHearingAidTotal = 0;
            if (request.getAttribute("assistiveDevicesHIList") != null) {
                assistiveDevicesHIList = (ArrayList) request.getAttribute("assistiveDevicesHIList");
                iter = assistiveDevicesHIList.iterator();
                while (iter.hasNext()) {
                    FunctionalNeedReportDTO totalDTO = (FunctionalNeedReportDTO) iter.next();
                    lowIntensitySCordTotal = lowIntensitySCordTotal + totalDTO.getLowIntensitySCord();
                    lowIntensityVCordTotal = lowIntensityVCordTotal + totalDTO.getLowIntensityVCord();
                    moderateIntensitySCordTotal = moderateIntensitySCordTotal + totalDTO.getModerateIntensitySCord();
                    moderateIntensityVCordTotal = moderateIntensityVCordTotal + totalDTO.getModerateIntensityVCord();
                    highIntensitySCordTotal = highIntensitySCordTotal + totalDTO.getHighIntensitySCord();
                    highIntensityVCordTotal = highIntensityVCordTotal + totalDTO.getHighIntensityVCord();
                    implantableHearingAidTotal = implantableHearingAidTotal + totalDTO.getImplantableHearingAid();
                    total = total + totalDTO.getTotal();
                }

            }
            int otherNeedsHITotal = 0;
            ArrayList otherNeedsHIList = new ArrayList();
            if (request.getAttribute("otherNeedsHIList") != null) {
                otherNeedsHIList = (ArrayList) request.getAttribute("otherNeedsHIList");

                iter = otherNeedsHIList.iterator();
                while (iter.hasNext()) {
                    FunctionalNeedReportDTO totalDTO = (FunctionalNeedReportDTO) iter.next();
                    otherNeedsHITotal = otherNeedsHITotal + totalDTO.getOtherNeedsHI();
                }
            }
            ArrayList medicalInterventionMRList = new ArrayList();
            int speechTherapyBelowThreeYearsMRTotal = 0, speechTherapyAboveThreeYearsMRTotal = 0;
            int behaviourModificationPsychotherapyBelowTotal = 0, behaviourModificationPsychotherapyAboveTotal = 0;
            int sensoryIntegrationCognitiveDevelopmentTotal = 0, cognitiveBehaviourTherapyTotal = 0;
            int parentFamilyInterventionTotal = 0, physiotherapyMRTotal = 0, occupationalTherapyMRTotal = 0;
            if (request.getAttribute("medicalInterventionMRList") != null) {
                medicalInterventionMRList = (ArrayList) request.getAttribute("medicalInterventionMRList");
                iter = medicalInterventionMRList.iterator();
                while (iter.hasNext()) {
                    FunctionalNeedReportDTO totalDTO = (FunctionalNeedReportDTO) iter.next();
                    speechTherapyBelowThreeYearsMRTotal = speechTherapyBelowThreeYearsMRTotal
                            + totalDTO.getSpeechTherapyBelowThreeYearsMR();
                    speechTherapyAboveThreeYearsMRTotal = speechTherapyAboveThreeYearsMRTotal
                            + totalDTO.getSpeechTherapyAboveThreeYearsMR();
                    behaviourModificationPsychotherapyBelowTotal = behaviourModificationPsychotherapyBelowTotal
                            + totalDTO.getBehaviourModificationPsychotherapyBelow();
                    behaviourModificationPsychotherapyAboveTotal = behaviourModificationPsychotherapyAboveTotal
                            + totalDTO.getBehaviourModificationPsychotherapyAbove();
                    sensoryIntegrationCognitiveDevelopmentTotal = sensoryIntegrationCognitiveDevelopmentTotal
                            + totalDTO.getSensoryIntegrationCognitiveDevelopment();
                    cognitiveBehaviourTherapyTotal = cognitiveBehaviourTherapyTotal + totalDTO.getCognitiveBehaviourTherapy();
                    parentFamilyInterventionTotal = parentFamilyInterventionTotal + totalDTO.getParentFamilyIntervention();
                    physiotherapyMRTotal = physiotherapyMRTotal + totalDTO.getPhysiotherapyMR();
                    occupationalTherapyMRTotal = occupationalTherapyMRTotal + totalDTO.getOccupationalTherapyMR();
                    total = total + totalDTO.getTotal();
                }

            }

            ArrayList assistiveDevicesMRList = new ArrayList();
            assistiveDevicesMRList = (ArrayList) request.getAttribute("assistiveDevicesMRList");
            int learningMaterialsTotal = 0, specialSoftwareTotal = 0;
            int toysTotal = 0;
            if (request.getAttribute("assistiveDevicesMRList") != null) {
                iter = assistiveDevicesMRList.iterator();
                while (iter.hasNext()) {
                    FunctionalNeedReportDTO totalDTO = (FunctionalNeedReportDTO) iter.next();
                    learningMaterialsTotal = learningMaterialsTotal + totalDTO.getLearningMaterials();
                    specialSoftwareTotal = specialSoftwareTotal + totalDTO.getSpecialSoftware();
                    toysTotal = toysTotal + totalDTO.getToys();
                    total = total + totalDTO.getTotal();
                }
            }

            ArrayList otherNeedsMRList = new ArrayList();
            otherNeedsMRList = (ArrayList) request.getAttribute("otherNeedsMRList");
            int otherNeedsMRTotal = 0;
            if (request.getAttribute("otherNeedsMRList") != null) {
                iter = otherNeedsMRList.iterator();
                while (iter.hasNext()) {
                    FunctionalNeedReportDTO totalDTO = (FunctionalNeedReportDTO) iter.next();
                    otherNeedsMRTotal = otherNeedsMRTotal + totalDTO.getOtherNeedsMR();
                }
            }

            ArrayList medicalInterventionMIList = new ArrayList();
            int psychotherapyBehaviourBelowThreeYearsMITotal = 0, psychotherapyBehaviourAboveThreeYearsMITotal = 0;
            int surgeryMITotal = 0, admissionPsychiatricTotal = 0;
            if (request.getAttribute("medicalInterventionMIList") != null) {
                medicalInterventionMIList = (ArrayList) request.getAttribute("medicalInterventionMIList");
                iter = medicalInterventionMIList.iterator();
                while (iter.hasNext()) {
                    FunctionalNeedReportDTO totalDTO = (FunctionalNeedReportDTO) iter.next();
                    psychotherapyBehaviourBelowThreeYearsMITotal = psychotherapyBehaviourBelowThreeYearsMITotal
                            + totalDTO.getPsychotherapyBehaviourBelowThreeYearsMI();
                    psychotherapyBehaviourAboveThreeYearsMITotal = psychotherapyBehaviourAboveThreeYearsMITotal
                            + totalDTO.getPsychotherapyBehaviourAboveThreeYearsMI();
                    surgeryMITotal = surgeryMITotal + totalDTO.getSurgeryMI();
                    admissionPsychiatricTotal = admissionPsychiatricTotal + totalDTO.getAdmissionPsychiatric();

                    total = total + totalDTO.getTotal();
                }
            }
            int otherNeedsMITotal = 0;
            ArrayList otherNeedsMIList = new ArrayList();
            if (request.getAttribute("otherNeedsMIList") != null) {
                otherNeedsMIList = (ArrayList) request.getAttribute("otherNeedsMIList");
                iter = otherNeedsMIList.iterator();
                while (iter.hasNext()) {
                    FunctionalNeedReportDTO totalDTO = (FunctionalNeedReportDTO) iter.next();
                    otherNeedsMITotal = otherNeedsMITotal + totalDTO.getOtherNeedsMI();
                }
            }
            int i = 0;
%>

<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SADAREM</title>

    </head>
   
    <body >
        <input type="hidden" name="mode"/>
        <input type="hidden" name="districtName"/>
        <input type="hidden" name="mandalName"/>
        <input type="hidden" name="villageName"/>


        <table border="0" cellspacing="0" cellpadding="0" width="90%" align="center">
           
            <tr>
                <td align="center" id="printTable">
                    <p>
                        <logic:present name="level">${level}</logic:present></p>
                        <logic:present name="medicalInterventionList" scope="request">
                        <p>Medical Intervention Report</p>
                        <p> <logic:present name="areadescription"> ${areadescription}</logic:present></p>
                        <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">
                            <tr>
                                <th align="center"  rowspan=2>S.No</th>
                                <th rowspan="2"><logic:present name="regionHeading">${regionHeading}</logic:present></th>
                                <th align="center"  colspan="2">Physiotherapy</th>
                                <th align="center"  colspan="2">Occupational Therapy</th>
                                <th align="center"  rowspan=2>Surgery</th>
                                <th align="center"  rowspan=2>Total</th>
                            </tr>
                            <tr>
                                <th  align="center">Below 3 Years</th>
                                <th  align="center">Above 3 Years</th>
                                <th  align="center">Below 3 Years</th>
                                <th  align="center">Above 3 Years</th>
                            </tr>
                            <logic:iterate id="modify" name="medicalInterventionList" scope="request">
                                <tr>
                                    <td  align="center"  class="formbg" ><%=++i%></td>
                                    <logic:notEmpty name="modify" property="districtName">
                                        <td  class="formbg" align="left">
                                            ${modify.districtName}
                                        </td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="mandalName">
                                        <td  class="formbg" align="left">${modify.mandalName}</td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="villageName">
                                        <td  class="formbg" align="left">${modify.villageName}</td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="habitationName">
                                        <td  class="formbg" align="left">${modify.habitationName}</td>
                                    </logic:notEmpty>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="physiotherapyBelowThreeYears"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="physiotherapyAboveThreeYears"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="occupationalTherapyBelowThreeYears"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="occupationalTherapyAboveThreeYears"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="surgeryOH"/></td>
                                    <td class="formbg" align="center"><bean:write name="modify" property="total"/></td>
                                </tr>

                            </logic:iterate>
                            <tr>
                                <th  colspan="2" align="center">Total</th>
                                <th  align="center"><%= phyBelow3Total%></th>
                                <th  align="center"><%= phyAbove3Total%></th>
                                <th  align="center"><%= otBelow3Total%></th>
                                <th  align="center"><%= otAbove3Total%></th>
                                <th  align="center"><%= surgeryTotal%></th>
                                <th  align="center"><%= total%></th>
                            </tr>
                        </table><br>

                    </logic:present>


                    <logic:present name="prothosisList" scope="request">
                        <p>Assistive Devices Prosthesis Report</p>
                        <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">
                            <tr>
                                <th align="center"  rowspan=2>S.No</th>
                                <th rowspan="2"><logic:present name="regionHeading">${regionHeading}</logic:present></th>
                                <th align="center" class="formhdbg" colspan="7">Prosthesis for Upper Extremity</th>
                                <th align="center" class="formhdbg" colspan="6">Prosthesis for Lower Extremity</th>
                                <th align="center" class="formhdbg" rowspan=2>Total</th>
                            </tr>
                            <tr>
                                <th class="formhdbg" align="center">Shoulder</th>
                                <th class="formhdbg" align="center">Above Elbow</th>
                                <th class="formhdbg" align="center">Elbow Disarticulation</th>
                                <th class="formhdbg" align="center">Below Elbow</th>
                                <th class="formhdbg" align="center">Wrist Disarticulation</th>
                                <th class="formhdbg" align="center">Hand</th>
                                <th class="formhdbg" align="center">Cosmetic Finger</th>
                                <th class="formhdbg" align="center">Hip Disarticulation</th>
                                <th class="formhdbg" align="center">Above Knee</th>
                                <th class="formhdbg" align="center">Knee Disarticulation</th>
                                <th class="formhdbg" align="center">Below Knee</th>
                                <th class="formhdbg" align="center">Syme's</th>
                                <th class="formhdbg" align="center">Partial Foot</th>
                            </tr>
                            <logic:iterate id="modify" name="prothosisList" scope="request">
                                <tr>
                                    <td  align="center"  class="formbg" ><%=++i%></td>
                                    <logic:notEmpty name="modify" property="districtName">
                                        <td  class="formbg" align="left">
                                            ${modify.districtName}
                                        </td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="mandalName">
                                        <td  class="formbg" align="left">${modify.mandalName}</td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="villageName">
                                        <td  class="formbg" align="left">${modify.villageName}</td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="habitationName">
                                        <td  class="formbg" align="left">${modify.habitationName}</td>
                                    </logic:notEmpty>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="shoulder"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="aboveElbow"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="elbowDisarticulation"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="belowElbow"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="wristDisarticulation"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="hand"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="cosmeticFinger"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="hipDisarticulation"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="aboveKnee"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="kneeDisarticulation"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="belowKnee"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="symes"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="partialFoot"/></td>
                                    <td class="formbg" align="center"><bean:write name="modify" property="total"/></td>
                                </tr>

                            </logic:iterate>
                            <tr>
                                <td class="formhdbg" colspan="2" align="center">Total</td>
                                <td class="formhdbg" align="center"><b><%=shoulderTotal%></b></td>
                                <td class="formhdbg" align="center"><b><%=aboveElbowTotal%></b></td>
                                <td class="formhdbg" align="center"><b><%=elbowDisarticulationTotal%></b></td>
                                <td class="formhdbg" align="center"><b><%=belowElbowTotal%></b></td>
                                <td class="formhdbg" align="center"><b><%=wristDisarticulationTotal%></b></td>
                                <td class="formhdbg" align="center"><b><%=handTotal%></b></td>
                                <td class="formhdbg" align="center"><b><%=cosmeticFingerTotal%></b></td>
                                <td class="formhdbg" align="center"><b><%=hipDisarticulationTotal%></b></td>
                                <td class="formhdbg" align="center"><b><%=aboveKneeTotal%></b></td>
                                <td class="formhdbg" align="center"><b><%=kneeDisarticulationTotal%></b></td>
                                <td class="formhdbg" align="center"><b><%=belowKneeTotal%></b></td>
                                <td class="formhdbg" align="center"><b><%=symesTotal%></b></td>
                                <td class="formhdbg" align="center"><b><%=partialFootTotal%></b></td>
                                <td class="formhdbg" align="center"><%= total%></td>
                            </tr>
                        </table>
                    </logic:present>






                    <logic:present name="orthosisList" scope="request">
                        <p>Assistive Devices Orthosis Report</p>
                        <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">
                            <tr>
                                <th align="center" class="formhdbg" rowspan=2>S.No</th>
                                <th rowspan="2"><logic:present name="regionHeading">${regionHeading}</logic:present></th>
                                <th align="center" class="formhdbg" colspan="4">Orthosis/ Splint for Upper Extremirty</th>
                                <th align="center" class="formhdbg" colspan="7">Orthosis/ Splint for Lower extremity</th>
                                <th align="center" class="formhdbg" colspan="3">Spinal Orthosis</th>
                                <th align="center" class="formhdbg" rowspan=2>Total</th>
                            </tr>
                            <tr>
                                <th class="formhdbg" align="center">Aeroplane Splint</th>
                                <th class="formhdbg" align="center">Figure 8 Splint</th>
                                <th class="formhdbg" align="center">Forearm Splint</th>
                                <th class="formhdbg" align="center">Hand Splint</th>
                                <th class="formhdbg" align="center">HKAFO</th>
                                <th class="formhdbg" align="center">KAFO</th>
                                <th class="formhdbg" align="center">AFO</th>
                                <th class="formhdbg" align="center">Knee orthosis</th>
                                <th class="formhdbg" align="center">DB Splint</th>
                                <th class="formhdbg" align="center">Modified Shoe</th>
                                <th class="formhdbg" align="center">Serial Casting(CTEV)</th>
                                <th class="formhdbg" align="center">Cervical Collar</th>
                                <th class="formhdbg" align="center">LS Brace</th>
                                <th class="formhdbg" align="center">TLSO Brace(For scoliosis/ Kyphosis)</th>
                            </tr>
                            <% i = 0;%>
                            <logic:iterate id="modify" name="orthosisList" scope="request">
                                <tr>
                                    <td  align="center"  class="formbg" ><%=++i%></td>
                                    <logic:notEmpty name="modify" property="districtName">
                                        <td  class="formbg" align="left">
                                            ${modify.districtName}
                                        </td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="mandalName">
                                        <td  class="formbg" align="left">${modify.mandalName}</td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="villageName">
                                        <td  class="formbg" align="left">${modify.villageName}</td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="habitationName">
                                        <td  class="formbg" align="left">${modify.habitationName}</td>
                                    </logic:notEmpty>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="aeroplaneSplint"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="figureEightSplint"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="forearmSplint"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="handSplint"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="hkafoh"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="kafo"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="afo"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="kneeOrthosis"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="dbSplint"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="modifiedShoe"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="serialCastingCTEV"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="cervicalCollar"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="lsBrace"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="tlsoBraceForScoliosisKyphosis"/></td>
                                    <td class="formbg" align="center"><bean:write name="modify" property="total"/></td>
                                </tr>


                            </logic:iterate>
                            <tr>
                                <td class="formhdbg" colspan="2" align="center">Total</td>
                                <td class="formhdbg" align="center"><b>${aeroplaneSplintTotal}</b></td>
                                <td class="formhdbg" align="center"><b><%=figureEightSplintTotal%></b></td>
                                <td class="formhdbg" align="center"><b><%=forearmSplintTotal%></b></td>
                                <td class="formhdbg" align="center"><b>${handSplintTotal}</b></td>
                                <td class="formhdbg" align="center"><b><%=hkafohTotal%></b></td>
                                <td class="formhdbg" align="center"><b><%=kafoTotal%></b></td>
                                <td class="formhdbg" align="center"><b><%=afoTotal%></b></td>
                                <td class="formhdbg" align="center"><b><%=kneeOrthosisTotal%></b></td>
                                <td class="formhdbg" align="center"><b><%=dbSplintTotal%></b></td>
                                <td class="formhdbg" align="center"><b><%=modifiedShoeTotal%></b></td>
                                <td class="formhdbg" align="center"><b><%=serialCastingCTEVTotal%></b></td>
                                <td class="formhdbg" align="center"><b><%=cervicalCollarTotal%></b></td>
                                <td class="formhdbg" align="center"><b><%=lsBraceTotal%></b></td>
                                <td class="formhdbg" align="center"><b><%=tlsoBraceForScoliosisKyphosisTotal%></b></td>
                                <td class="formhdbg" align="center"><%= total%></td>
                            </tr>
                        </table><br>

                    </logic:present>


                    <logic:present name="mobilityList" scope="request">
                        <p>Assistive Devices Mobility Aids Report</p>
                        <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">

                            <tr>
                                <th align="center" class="formhdbg" rowspan=2>S.No</th>
                                <th rowspan=2><logic:present name="regionHeading">${regionHeading}</logic:present></th>
                                <th align="center" class="formhdbg" colspan="2">Wheel chair</th>
                                <th align="center" class="formhdbg" colspan="2">Tricycle</th>
                                <th align="center" class="formhdbg" colspan="2">Walking Frame/ Walker</th>
                                <th align="center" class="formhdbg" rowspan=2>Total</th>
                            </tr>
                            <tr>
                                <th class="formhdbg" align="center">Small</th>
                                <th class="formhdbg" align="center">Large</th>
                                <th class="formhdbg" align="center">Small</th>
                                <th class="formhdbg" align="center">Large</th>
                                <th class="formhdbg" align="center">Small</th>
                                <th class="formhdbg" align="center">Large</th>
                            </tr>
                            <logic:iterate id="modify" name="mobilityList" scope="request">
                                <tr>
                                    <td  align="center"  class="formbg" ><%=++i%></td>
                                    <logic:notEmpty name="modify" property="districtName">
                                        <td  class="formbg" align="left">
                                            ${modify.districtName}
                                        </td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="mandalName">
                                        <td  class="formbg" align="left">${modify.mandalName}</td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="villageName">
                                        <td  class="formbg" align="left">${modify.villageName}</td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="habitationName">
                                        <td  class="formbg" align="left">${modify.habitationName}</td>
                                    </logic:notEmpty>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="wheelChairSmall"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="wheelChairLarge"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="tricycleSmall"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="tricycleLarge"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="walkingFrameWalkerSmall"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="walkingFrameWalkerLarge"/></td>
                                    <td class="formbg" align="center"><bean:write name="modify" property="total"/></td>
                                </tr>

                            </logic:iterate>
                            <tr>
                                <td class="formhdbg" colspan="2" align="center">Total</td>
                                <td class="formhdbg" align="center"><%=wheelChairSmallTotal%></td>
                                <td class="formhdbg" align="center"><%=wheelChairLargeTotal%></td>
                                <td class="formhdbg" align="center"><%=tricycleSmallTotal%></td>
                                <td class="formhdbg" align="center"><%=tricycleLargeTotal%></td>
                                <td class="formhdbg" align="center"><%=walkingFrameWalkerSmallTotal%></td>
                                <td class="formhdbg" align="center"><%=walkingFrameWalkerLargeTotal%></td>
                                <td class="formhdbg" align="center"><%= total%></td>
                            </tr>
                        </table>
                    </logic:present>

                    <logic:present name="walkingAidsList" scope="request">
                        <p>Assistive Devices Walking Aids Report</p>
                        <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">

                            <tr>
                                <th align="center" class="formhdbg" rowspan=2>S.No</th>
                                <th rowspan=2><logic:present name="regionHeading">${regionHeading}</logic:present></th>
                                <th align="center" class="formhdbg" colspan="2">Walking Stick</th>
                                <th align="center" class="formhdbg" colspan="4">Axillary</th>
                                <th align="center" class="formhdbg" colspan="4">Elbow</th>
                                <th align="center" class="formhdbg" colspan="4">Gutter</th>
                                <th align="center" class="formhdbg" colspan="4">Tripod</th>
                                <th align="center" class="formhdbg" rowspan=2>Total</th>
                            </tr>
                            <tr>
                                <th class="formhdbg" align="center">Small</th>
                                <th class="formhdbg" align="center">Large</th>
                                <th class="formhdbg" align="center">Small</th>
                                <th class="formhdbg" align="center">Medium</th>
                                <th class="formhdbg" align="center">Large</th>
                                <th class="formhdbg" align="center">Extra Large</th>
                                <th class="formhdbg" align="center">Small</th>
                                <th class="formhdbg" align="center">Medium</th>
                                <th class="formhdbg" align="center">Large</th>
                                <th class="formhdbg" align="center">Extra Large</th>
                                <th class="formhdbg" align="center">Small</th>
                                <th class="formhdbg" align="center">Medium</th>
                                <th class="formhdbg" align="center">Large</th>
                                <th class="formhdbg" align="center">Extra Large</th>
                                <th class="formhdbg" align="center">Small</th>
                                <th class="formhdbg" align="center">Medium</th>
                                <th class="formhdbg" align="center">Large</th>
                                <th class="formhdbg" align="center">Extra Large</th>
                            </tr>

                            <logic:iterate id="modify" name="walkingAidsList" scope="request">

                                <tr>
                                    <td  align="center"  class="formbg" ><%=++i%></td>
                                    <logic:notEmpty name="modify" property="districtName">
                                        <td  class="formbg" align="left">
                                            ${modify.districtName}
                                        </td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="mandalName">
                                        <td  class="formbg" align="left">${modify.mandalName}</td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="villageName">
                                        <td  class="formbg" align="left">${modify.villageName}</td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="habitationName">
                                        <td  class="formbg" align="left">${modify.habitationName}</td>
                                    </logic:notEmpty>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="walkingStickSmall"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="walkingStickLarge"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="axillarySmall"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="axillaryMedium"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="axillaryLarge"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="axillaryExtraLarge"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="elbowSmall"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="elbowMedium"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="elbowLarge"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="elbowExtraLarge"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="gutterSmall"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="gutterMedium"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="gutterLarge"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="gutterExtraLarge"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="tripodSmall"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="tripodMedium"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="tripodLarge"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="tripodExtraLarge"/></td>
                                    <td class="formbg" align="center"><bean:write name="modify" property="total"/></td>
                                </tr>

                            </logic:iterate>
                            <tr>
                                <td class="formhdbg" colspan="2" align="center">Total</td>
                                <td class="formhdbg" align="center"><b><%=walkingStickSmallTotal%></b></td>
                                <td class="formhdbg" align="center"><b><%=walkingStickLargeTotal%></b></td>
                                <td class="formhdbg" align="center"><b><%=axillarySmallTotal%></b></td>
                                <td class="formhdbg" align="center"><b><%=axillaryMediumTotal%></b></td>
                                <td class="formhdbg" align="center"><b><%=axillaryLargeTotal%></b></td>
                                <td class="formhdbg" align="center"><b><%=axillaryExtraLargeTotal%></b></td>
                                <td class="formhdbg" align="center"><b><%=elbowSmallTotal%></b></td>
                                <td class="formhdbg" align="center"><b><%=elbowMediumTotal%></b></td>
                                <td class="formhdbg" align="center"><b><%=elbowLargeTotal%></b></td>
                                <td class="formhdbg" align="center"><b><%=elbowExtraLargeTotal%></b></td>
                                <td class="formhdbg" align="center"><b><%=gutterSmallTotal%></b></td>
                                <td class="formhdbg" align="center"><b><%=gutterMediumTotal%></b></td>
                                <td class="formhdbg" align="center"><b><%=gutterLargeTotal%></b></td>
                                <td class="formhdbg" align="center"><b><%=gutterExtraLargeTotal%></b></td>
                                <td class="formhdbg" align="center"><b><%=tripodSmallTotal%></b></td>
                                <td class="formhdbg" align="center"><b><%=tripodMediumTotal%></b></td>
                                <td class="formhdbg" align="center"><b><%=tripodLargeTotal%></b></td>
                                <td class="formhdbg" align="center"><b><%=tripodExtraLargeTotal%></b></td>
                                <td class="formhdbg" align="center"><%= total%></td>
                            </tr>
                        </table>

                    </logic:present>



                    <logic:present name="adlEquipmentsList" scope="request">
                        <p>Assistive Devices ADL Equipments Report</p>
                        <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">

                            <tr>
                                <th align="center" class="formhdbg">S.No</th>
                                <th ><logic:present name="regionHeading">${regionHeading}</logic:present></th>
                                <th align="center" class="formhdbg">Feeding</th>
                                <th align="center" class="formhdbg">Toileting/ Bathing</th>
                                <th align="center" class="formhdbg">Brushing</th>
                                <th align="center" class="formhdbg">Combing</th>
                                <th align="center" class="formhdbg">Dressing</th>
                                <th align="center" class="formhdbg">Writing</th>
                                <th align="center" class="formhdbg">Driving/ Cycling</th>
                                <th align="center" class="formhdbg">Bed Transfer</th>
                                <th align="center" class="formhdbg">Total</th>
                            </tr>


                            <logic:iterate id="modify" name="adlEquipmentsList" scope="request">

                                <tr>
                                    <td  align="center"  class="formbg" ><%=++i%></td>
                                    <logic:notEmpty name="modify" property="districtName">
                                        <td  class="formbg" align="left">
                                            ${modify.districtName}
                                        </td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="mandalName">
                                        <td  class="formbg" align="left">${modify.mandalName}</td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="villageName">
                                        <td  class="formbg" align="left">${modify.villageName}</td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="habitationName">
                                        <td  class="formbg" align="left">${modify.habitationName}</td>
                                    </logic:notEmpty>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="feeding"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="toiletingBathing"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="brushing"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="combing"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="dressing"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="writing"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="drivingCycling"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="bedTransfer"/></td>
                                    <td class="formbg" align="center"><bean:write name="modify" property="total"/></td>
                                </tr>

                            </logic:iterate>
                            <tr>
                                <td class="formhdbg" colspan="2" align="center">Total</td>
                                <td class="formhdbg" align="center"><%=feedingTotal%></td>
                                <td class="formhdbg" align="center"><%=toiletingBathingTotal%></td>
                                <td class="formhdbg" align="center"><%=brushingTotal%></td>
                                <td class="formhdbg" align="center"><%=combingTotal%></td>
                                <td class="formhdbg" align="center"><%=dressingTotal%></td>
                                <td class="formhdbg" align="center"><%=writingTotal%></td>
                                <td class="formhdbg" align="center"><%=drivingCyclingTotal%></td>
                                <td class="formhdbg" align="center"><%=bedTransferTotal%></td>
                                <td class="formhdbg" align="center"><%= total%></td>
                            </tr>
                        </table>
                    </logic:present>




                    <logic:present name="otherNeedsList" scope="request">
                        <p>Other Needs Report</p>
                        <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">

                            <tr>
                                <th align="center" class="formhdbg">S.No</th>
                                <th ><logic:present name="regionHeading">${regionHeading}</logic:present></th>
                                <th align="center" class="formhdbg">Other Needs</th>
                            </tr>
                            <logic:iterate id="modify" name="otherNeedsList" scope="request">
                                <tr>
                                    <td  align="center"  class="formbg" ><%=++i%></td>
                                    <logic:notEmpty name="modify" property="districtName">
                                        <td  class="formbg" align="left">
                                            ${modify.districtName}
                                        </td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="mandalName">
                                        <td  class="formbg" align="left">${modify.mandalName}</td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="villageName">
                                        <td  class="formbg" align="left">${modify.villageName}</td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="habitationName">
                                        <td  class="formbg" align="left">${modify.habitationName}</td>
                                    </logic:notEmpty>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="otherNeeds"/></td>
                                </tr>

                            </logic:iterate>
                            <tr>
                                <th class="formhdbg" colspan="2" align="center">Total</th>
                                <th class="formhdbg" align="center"><%=otherNeedsTotal%></th>

                            </tr>
                        </table>
                    </logic:present>



                    <logic:present name="medicalInterventionVIList" scope="request">
                        <p>Medical Intervention VI Report</p>
                        <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">
                            <tr>
                                <th align="center" class="formhdbg">S.No</th>
                                <th ><logic:present name="regionHeading">${regionHeading}</logic:present></th>
                                <th align="center" class="formhdbg">Surgery</th>
                            </tr>
                            <logic:iterate id="modify" name="medicalInterventionVIList" scope="request">
                                <tr>
                                    <td  align="center"  class="formbg" ><%=++i%></td>
                                    <logic:notEmpty name="modify" property="districtName">
                                        <td  class="formbg" align="left">
                                            ${modify.districtName}
                                        </td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="mandalName">
                                        <td  class="formbg" align="left">${modify.mandalName}</td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="villageName">
                                        <td  class="formbg" align="left">${modify.villageName}</td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="habitationName">
                                        <td  class="formbg" align="left">${modify.habitationName}</td>
                                    </logic:notEmpty>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="sugeryVI"/></td>
                                </tr>

                            </logic:iterate>
                            <tr>
                                <th class="formhdbg" colspan="2" align="center">Total</th>
                                <th class="formhdbg" align="center"><%=sergeryVITotal%></th>

                            </tr>
                        </table>
                    </logic:present>
                    <logic:present name="assistiveDevicesVIList" scope="request">
                        <p>Assistive Devices VI Report</p>
                        <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">

                            <tr>
                                <th align="center" class="formhdbg">S.No</th>
                                <th ><logic:present name="regionHeading">${regionHeading}</logic:present></th>
                                <th align="center" class="formhdbg">White Cane/ Blind Stick</th>
                                <th align="center" class="formhdbg">Braille Equipments</th>
                                <th align="center" class="formhdbg">Arithmetic Frames/ Abacus</th>
                                <th align="center" class="formhdbg">Low Vision Aids (Spectacles, Magnifiers)</th>
                                <th align="center" class="formhdbg">Speech Synthesizer</th>
                                <th align="center" class="formhdbg">Braille Short Hand Machines/ Type Writers</th>
                                <th align="center" class="formhdbg">Talking Watch/ Calculator</th>
                                <th align="center" class="formhdbg">ADL Equipment</th>
                                <th align="center" class="formhdbg">Total</th>
                            </tr>

                            <logic:iterate id="modify" name="assistiveDevicesVIList" scope="request">
                                <tr>
                                    <td  align="center"  class="formbg" ><%=++i%></td>
                                    <logic:notEmpty name="modify" property="districtName">
                                        <td  class="formbg" align="left">
                                            ${modify.districtName}
                                        </td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="mandalName">
                                        <td  class="formbg" align="left">${modify.mandalName}</td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="villageName">
                                        <td  class="formbg" align="left">${modify.villageName}</td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="habitationName">
                                        <td  class="formbg" align="left">${modify.habitationName}</td>
                                    </logic:notEmpty>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="whiteCaneBlindStick"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="brailleEquipments"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="arithmeticFramesAbacus"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="lowVisionAidsSpectaclesMagnifiers"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="speechSynthesizer"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="brailleShortHandMachinesWriters"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="talkingWatchCalculator"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="viADLEquipment"/></td>
                                    <td class="formbg" align="center"><bean:write name="modify" property="total"/></td>
                                </tr>

                            </logic:iterate>
                            <tr>
                                <th class="formhdbg" colspan="2" align="center">Total</th>
                                <th class="formhdbg" align="center"><%=whiteCaneBlindStickTotal%></th>
                                <th class="formhdbg" align="center"><%=brailleEquipmentsTotal%></th>
                                <th class="formhdbg" align="center"><%=arithmeticFramesAbacusTotal%></th>
                                <th class="formhdbg" align="center"><%=lowVisionAidsSpectaclesMagnifiersTotal%></th>
                                <th class="formhdbg" align="center"><%=speechSynthesizerTotal%></th>
                                <th class="formhdbg" align="center"><%=brailleShortHandMachinesWritersTotal%></th>
                                <th class="formhdbg" align="center"><%=talkingWatchCalculatorTotal%></th>
                                <th class="formhdbg" align="center"><%=viADLEquipmentTotal%></th>
                                <th class="formhdbg" align="center"><%= total%></th>
                            </tr>
                        </table>
                    </logic:present>


                    <logic:present name="otherNeedsVIList" scope="request">
                        <p>Other Needs VI Report

                        </p>
                        <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">

                            <tr>
                                <th align="center" class="formhdbg">S.No</th>
                                <th><logic:present name="regionHeading">${regionHeading}</logic:present></th>
                                <th align="center" class="formhdbg">Other Needs</th>
                            </tr>
                            <logic:iterate id="modify" name="otherNeedsVIList" scope="request">
                                <tr>
                                    <td  align="center"  class="formbg" ><%=++i%></td>
                                    <logic:notEmpty name="modify" property="districtName">
                                        <td  class="formbg" align="left">
                                            ${modify.districtName}
                                        </td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="mandalName">
                                        <td  class="formbg" align="left">${modify.mandalName}</td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="villageName">
                                        <td  class="formbg" align="left">${modify.villageName}</td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="habitationName">
                                        <td  class="formbg" align="left">${modify.habitationName}</td>
                                    </logic:notEmpty>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="otherNeedsVI"/></td>
                                </tr>

                            </logic:iterate>
                            <tr>
                                <th class="formhdbg" colspan="2" align="center">Total</th>
                                <th class="formhdbg" align="center"><%=otherNeedsVITotal%></th>

                            </tr>
                        </table>
                    </logic:present>

                    <logic:present name="medicalInterventionHIList" scope="request">
                        <p>Medical Intervention HI Report</p>
                        <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">

                            <tr>
                                <th align="center" class="formhdbg" rowspan=2>S.No</th>
                                <th  rowspan=2 ><logic:present name="regionHeading" >${regionHeading}</logic:present></th>
                                <th align="center" class="formhdbg" colspan="2">Speech therapy</th>
                                <th align="center" class="formhdbg" rowspan="2">Language Development</th>
                                <th align="center" class="formhdbg" rowspan=2>Surgery</th>
                                <th align="center" class="formhdbg" rowspan=2>Cochlear Implantation</th>
                                <th align="center" class="formhdbg" rowspan=2>Total</th>
                            </tr>
                            <tr>
                                <th class="formhdbg" align="center">Below 3 Years</th>
                                <th class="formhdbg" align="center">Above 3 Years</th>
                            </tr>
                            <logic:iterate id="modify" name="medicalInterventionHIList" scope="request">
                                <tr>
                                    <td  align="center"  class="formbg" ><%=++i%></td>
                                    <logic:notEmpty name="modify" property="districtName">
                                        <td  class="formbg" align="left">
                                            ${modify.districtName}
                                        </td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="mandalName">
                                        <td  class="formbg" align="left">${modify.mandalName}</td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="villageName">
                                        <td  class="formbg" align="left">${modify.villageName}</td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="habitationName">
                                        <td  class="formbg" align="left">${modify.habitationName}</td>
                                    </logic:notEmpty>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="speechTherapyBelowThreeYearsHI"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="speechTherapyAboveThreeYearsHI"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="languageDevelopment"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="surgeryHI"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="cochlearImplantation"/></td>
                                    <td class="formbg" align="center"><bean:write name="modify" property="total"/></td>
                                </tr>

                            </logic:iterate>
                            <tr>
                                <td class="formhdbg" colspan="2" align="center">Total</td>
                                <td class="formhdbg" align="center"><%=speechTherapyBelowThreeYearsHITotal%></td>
                                <td class="formhdbg" align="center"><%=speechTherapyAboveThreeYearsHITotal%></td>
                                <td class="formhdbg" align="center"><%=languageDevelopmentTotal%></td>
                                <td class="formhdbg" align="center"><%=surgeryHITotal%></td>
                                <td class="formhdbg" align="center"><%=cochlearImplantationTotal%></td>
                                <td class="formhdbg" align="center"><%= total%></td>
                            </tr>
                        </table>
                    </logic:present>

                    <logic:present name="assistiveDevicesHIList" scope="request">
                        <p>Assistive Devices HI Report</p>
                        <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">
                            <tr>
                                <th align="center" class="formhdbg" rowspan=2>S.No</th>
                                <th  rowspan=2 ><logic:present name="regionHeading" >${regionHeading}</logic:present></th>
                                <th align="center" class="formhdbg" colspan="2">Low intensity</th>
                                <th align="center" class="formhdbg" colspan="2">Moderate Intensity</th>
                                <th align="center" class="formhdbg" colspan="2">High Intensity</th>
                                <th align="center" class="formhdbg" rowspan="2">Implantable Hearing Aid</th>
                                <th align="center" class="formhdbg" rowspan="2">Total</th>
                            </tr>
                            <tr>
                                <th class="formhdbg" align="center">S-Cord</th>
                                <th class="formhdbg" align="center">V-Cord</th>
                                <th class="formhdbg" align="center">S-Cord</th>
                                <th class="formhdbg" align="center">V-Cord</th>
                                <th class="formhdbg" align="center">S-Cord</th>
                                <th class="formhdbg" align="center">V-Cord</th>
                            </tr>
                            <logic:iterate id="modify" name="assistiveDevicesHIList" scope="request">
                                <tr>
                                    <td  align="center"  class="formbg" ><%=++i%></td>
                                    <logic:notEmpty name="modify" property="districtName">
                                        <td  class="formbg" align="left">
                                            ${modify.districtName}
                                        </td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="mandalName">
                                        <td  class="formbg" align="left">${modify.mandalName}</td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="villageName">
                                        <td  class="formbg" align="left">${modify.villageName}</td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="habitationName">
                                        <td  class="formbg" align="left">${modify.habitationName}</td>
                                    </logic:notEmpty>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="lowIntensitySCord"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="lowIntensityVCord"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="moderateIntensitySCord"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="moderateIntensityVCord"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="highIntensitySCord"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="highIntensityVCord"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="implantableHearingAid"/></td>
                                    <td class="formbg" align="center"><bean:write name="modify" property="total"/></td>
                                </tr>

                            </logic:iterate>
                            <tr>
                                <th class="formhdbg" colspan="2" align="center">Total</th>
                                <th class="formhdbg" align="center"><%=lowIntensitySCordTotal%></th>
                                <th class="formhdbg" align="center"><%=lowIntensityVCordTotal%></th>
                                <th class="formhdbg" align="center"><%=moderateIntensitySCordTotal%></th>
                                <th class="formhdbg" align="center"><%=moderateIntensityVCordTotal%></th>
                                <th class="formhdbg" align="center"><%=highIntensitySCordTotal%></th>
                                <th class="formhdbg" align="center"><%=highIntensityVCordTotal%></th>
                                <th class="formhdbg" align="center"><%=implantableHearingAidTotal%></th>
                                <th class="formhdbg" align="center"><%= total%></th>
                            </tr>
                        </table>
                    </logic:present>

                    <logic:present name="otherNeedsHIList" scope="request">

                        <p>Other Needs HI Report</p>
                        <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">

                            <tr>
                                <th align="center" class="formhdbg">S.No</th>
                                <th  ><logic:present name="regionHeading" >${regionHeading}</logic:present></th>
                                <th align="center" class="formhdbg">Other Needs</th>
                            </tr>
                            <logic:iterate id="modify" name="otherNeedsHIList" scope="request">
                                <tr>
                                    <td  align="center"  class="formbg" ><%=++i%></td>
                                    <logic:notEmpty name="modify" property="districtName">
                                        <td  class="formbg" align="left">
                                            ${modify.districtName}
                                        </td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="mandalName">
                                        <td  class="formbg" align="left">${modify.mandalName}</td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="villageName">
                                        <td  class="formbg" align="left">${modify.villageName}</td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="habitationName">
                                        <td  class="formbg" align="left">${modify.habitationName}</td>
                                    </logic:notEmpty>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="otherNeedsHI"/></td>
                                </tr>

                            </logic:iterate>
                            <tr>
                                <th class="formhdbg" colspan="2" align="center">Total</th>
                                <th class="formhdbg" align="center"><%=otherNeedsHITotal%></th>

                            </tr>
                        </table>
                    </logic:present>



                    <logic:present name="medicalInterventionMRList" scope="request">
                        <p>Medical Intervention MR Report</p>

                        <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">
                            <tr>
                                <th align="center" class="formhdbg" rowspan=2>S.No</th>
                                <th  rowspan="2"><logic:present name="regionHeading" >${regionHeading}</logic:present></th>

                                <th align="center" class="formhdbg" colspan="2">Speech Therapy & Language Development</th>
                                <th align="center" class="formhdbg" colspan="2">Behaviour Modification/Psychotherapy</th>
                                <th align="center" class="formhdbg" rowspan=2>Sensory Integration/ Cognitive Development</th>
                                <th align="center" class="formhdbg" rowspan=2>Cognitive Behaviour Therapy</th>
                                <th align="center" class="formhdbg" rowspan=2>Parent/ Family Intervention</th>
                                <th align="center" class="formhdbg" rowspan=2>Physiotherapy</th>
                                <th align="center" class="formhdbg" rowspan=2>Occupational Therapy</th>
                                <th align="center" class="formhdbg" rowspan=2>Total</th>
                            </tr>
                            <tr>
                                <th class="formhdbg" align="center">Below 3 Years</th>
                                <th class="formhdbg" align="center">Above 3 Years</th>
                                <th class="formhdbg" align="center">Below 3 Years</th>
                                <th class="formhdbg" align="center">Above 3 Years</th>
                            </tr>
                            <logic:iterate id="modify" name="medicalInterventionMRList" scope="request">
                                <tr>
                                    <td  align="center"  class="formbg" ><%=++i%></td>
                                    <logic:notEmpty name="modify" property="districtName">
                                        <td  class="formbg" align="left">
                                            ${modify.districtName}
                                        </td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="mandalName">
                                        <td  class="formbg" align="left">${modify.mandalName}</td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="villageName">
                                        <td  class="formbg" align="left">${modify.villageName}</td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="habitationName">
                                        <td  class="formbg" align="left">${modify.habitationName}</td>
                                    </logic:notEmpty>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="speechTherapyBelowThreeYearsMR"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="speechTherapyAboveThreeYearsMR"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="behaviourModificationPsychotherapyBelow"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="behaviourModificationPsychotherapyAbove"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="sensoryIntegrationCognitiveDevelopment"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="cognitiveBehaviourTherapy"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="parentFamilyIntervention"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="physiotherapyMR"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="occupationalTherapyMR"/></td>
                                    <td class="formbg" align="center"><bean:write name="modify" property="total"/></td>
                                </tr>

                            </logic:iterate>
                            <tr>
                                <th class="formhdbg" colspan="2" align="center">Total</th>
                                <th class="formhdbg" align="center"><%=speechTherapyBelowThreeYearsMRTotal%></th>
                                <th class="formhdbg" align="center"><%=speechTherapyAboveThreeYearsMRTotal%></th>
                                <th class="formhdbg" align="center"><%=behaviourModificationPsychotherapyBelowTotal%></th>
                                <th class="formhdbg" align="center"><%=behaviourModificationPsychotherapyAboveTotal%></th>
                                <th class="formhdbg" align="center"><%=sensoryIntegrationCognitiveDevelopmentTotal%></th>
                                <th class="formhdbg" align="center"><%=cognitiveBehaviourTherapyTotal%></th>
                                <th class="formhdbg" align="center"><%=parentFamilyInterventionTotal%></th>
                                <th class="formhdbg" align="center"><%=physiotherapyMRTotal%></th>
                                <th class="formhdbg" align="center"><%=occupationalTherapyMRTotal%></th>
                                <th class="formhdbg" align="center"><%= total%></th>
                            </tr>
                        </table>
                    </logic:present>


                    <logic:present name="assistiveDevicesMRList" scope="request">
                        <p>Assistive Devices MR Report</p>
                        <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">
                            <tr>
                                <th align="center" class="formhdbg">S.No</th>
                                <th ><logic:present name="regionHeading" >${regionHeading}</logic:present></th>

                                <th align="center" class="formhdbg">Learning Materials</th>
                                <th align="center" class="formhdbg">Special Software</th>
                                <th align="center" class="formhdbg">Toys</th>
                                <th align="center" class="formhdbg">Total</th>
                            </tr>

                            <logic:iterate id="modify" name="assistiveDevicesMRList" scope="request">
                                <tr>
                                    <td  align="center"  class="formbg" ><%=++i%></td>
                                    <logic:notEmpty name="modify" property="districtName">
                                        <td  class="formbg" align="left">
                                            ${modify.districtName}
                                        </td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="mandalName">
                                        <td  class="formbg" align="left">${modify.mandalName}</td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="villageName">
                                        <td  class="formbg" align="left">${modify.villageName}</td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="habitationName">
                                        <td  class="formbg" align="left">${modify.habitationName}</td>
                                    </logic:notEmpty>

                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="learningMaterials"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="specialSoftware"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="toys"/></td>
                                    <td class="formbg" align="center"><bean:write name="modify" property="total"/></td>
                                </tr>

                            </logic:iterate>
                            <tr>
                                <th class="formhdbg" colspan="2" align="center">Total</th>
                                <th class="formhdbg" align="center"><%=learningMaterialsTotal%></th>
                                <th class="formhdbg" align="center"><%=specialSoftwareTotal%></th>
                                <th class="formhdbg" align="center"><%=toysTotal%></th>
                                <th class="formhdbg" align="center"><%= total%></th>
                            </tr>
                        </table>
                    </logic:present>



                    <logic:present name="otherNeedsMRList" scope="request">
                        <p>Other Needs MR Report</p>
                        <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">
                            <tr>
                                <th align="center" class="formhdbg">S.No</th>
                                <th ><logic:present name="regionHeading" >${regionHeading}</logic:present></th>
                                <th align="center" class="formhdbg">Other Needs</th>
                            </tr>
                            <logic:iterate id="modify" name="otherNeedsMRList" scope="request">
                                <tr>
                                    <td  align="center"  class="formbg" ><%=++i%></td>
                                    <logic:notEmpty name="modify" property="districtName">
                                        <td  class="formbg" align="left">
                                            ${modify.districtName}
                                        </td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="mandalName">
                                        <td  class="formbg" align="left">${modify.mandalName}</td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="villageName">
                                        <td  class="formbg" align="left">${modify.villageName}</td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="habitationName">
                                        <td  class="formbg" align="left">${modify.habitationName}</td>
                                    </logic:notEmpty>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="otherNeedsMR"/></td>
                                </tr>

                            </logic:iterate>
                            <tr>
                                <th class="formhdbg" colspan="2" align="center">Total</th>
                                <th class="formhdbg" align="center"><%=otherNeedsMRTotal%></th>

                            </tr>
                        </table>
                    </logic:present>

                    <logic:present name="medicalInterventionMIList" scope="request">
                        <p>Medical Intervention MI Report</p>
                        <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">
                            <tr>
                                <th align="center" class="formhdbg" rowspan=2>S.No</th>
                                <th rowspan=2><logic:present name="regionHeading" >${regionHeading}</logic:present></th>
                                <th align="center" class="formhdbg" colspan="2">Psychotherapy/ Behaviour Modification</th>
                                <th align="center" class="formhdbg" rowspan="2">Surgery</th>
                                <th align="center" class="formhdbg" rowspan="2">Admission in Psychiatric Hospitals/ Nursing Homes</th>
                                <th align="center" class="formhdbg" rowspan="2">Total</th>
                            </tr>
                            <tr>
                                <th class="formhdbg" align="center">Below 3 Years</th>
                                <th class="formhdbg" align="center">Above 3 Years</th>
                            </tr>
                            <logic:iterate id="modify" name="medicalInterventionMIList" scope="request">
                                <tr>
                                    <td  align="center"  class="formbg" ><%=++i%></td>
                                    <logic:notEmpty name="modify" property="districtName">
                                        <td  class="formbg" align="left">
                                            ${modify.districtName}
                                        </td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="mandalName">
                                        <td  class="formbg" align="left">${modify.mandalName}</td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="villageName">
                                        <td  class="formbg" align="left">${modify.villageName}</td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="habitationName">
                                        <td  class="formbg" align="left">${modify.habitationName}</td>
                                    </logic:notEmpty>

                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="psychotherapyBehaviourBelowThreeYearsMI"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="psychotherapyBehaviourAboveThreeYearsMI"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="surgeryMI"/></td>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="admissionPsychiatric"/></td>

                                    <td class="formbg" align="center"><bean:write name="modify" property="total"/></td>
                                </tr>

                            </logic:iterate>
                            <tr>
                                <th class="formhdbg" colspan="2" align="center">Total</th>
                                <th class="formhdbg" align="center"><%=psychotherapyBehaviourBelowThreeYearsMITotal%></th>
                                <th class="formhdbg" align="center"><%=psychotherapyBehaviourAboveThreeYearsMITotal%></th>
                                <th class="formhdbg" align="center"><%=surgeryMITotal%></th>
                                <th class="formhdbg" align="center"><%=admissionPsychiatricTotal%></th>
                                <th class="formhdbg" align="center"><%= total%></th>
                            </tr>
                        </table>
                    </logic:present>



                    <logic:present name="otherNeedsMIList" scope="request">
                        <p>Other Needs MI Report</p>
                        <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">

                            <tr>
                                <th align="center" class="formhdbg">S.No</th>
                                <th><logic:present name="regionHeading" >${regionHeading}</logic:present></th>
                                <th align="center" class="formhdbg">Other Needs</th>
                            </tr>
                            <logic:iterate id="modify" name="otherNeedsMIList" scope="request">
                                <tr>
                                    <td  align="center"  class="formbg" ><%=++i%></td>
                                    <logic:notEmpty name="modify" property="districtName">
                                        <td  class="formbg" align="left">
                                            ${modify.districtName}
                                        </td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="mandalName">
                                        <td  class="formbg" align="left">${modify.mandalName}</td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="villageName">
                                        <td  class="formbg" align="left">${modify.villageName}</td>
                                    </logic:notEmpty>
                                    <logic:notEmpty name="modify" property="habitationName">
                                        <td  class="formbg" align="left">${modify.habitationName}</td>
                                    </logic:notEmpty>
                                    <td class="formbg" align="center">

                                        <bean:write name="modify" property="otherNeedsMI"/></td>
                                </tr>

                            </logic:iterate>
                            <tr>
                                <th class="formhdbg" colspan="2" align="center">Total</th>
                                <th class="formhdbg" align="center"><%=otherNeedsMITotal%></th>

                            </tr>
                        </table>
                    </logic:present>
                </td>
            </tr>
        </table>


    </body>
</html:html>
