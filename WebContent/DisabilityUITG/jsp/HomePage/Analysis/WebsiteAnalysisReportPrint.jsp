<%-- 
    Document   : WebsiteAnalysisReport
    Created on : Dec 11, 2010, 3:41:07 PM
    Author     : 509865
--%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="org.bf.disability.dto.FunctionalNeedReportDTO" %>


<%@page contentType="text/html"%>
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

    <body onload="window.print()">
        <input type="hidden" name="mode"/>
        <input type="hidden" name="districtName"/>
        <input type="hidden" name="mandalName"/>
        <input type="hidden" name="villageName"/>


        <table border="0" cellspacing="0" cellpadding="0" width="90%" align="center">
         
    <tr>
        <td align="center" id="printTable">
           
                <logic:present name="medicalInterventionList" scope="request">
                    <p>
                <logic:present name="level">${level}</logic:present></p>
                <p>Medical Intervention Report</p>
                
                      <p><logic:present name="areadescription">
                        ${areadescription}
                    </logic:present>
            </p>
          
                 
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
                            <td style="text-align:center"><%=++i%></td>
                            <logic:notEmpty name="modify" property="districtName">
                                <td  align="left">
                                    ${modify.districtName}
                                </td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="mandalName">
                                <td  align="left">${modify.mandalName}</td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="villageName">
                                <td  align="left">${modify.villageName}</td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="habitationName">
                                <td  align="left">${modify.habitationName}</td>
                            </logic:notEmpty>
                             <td style="text-align:center">
                               
                                    <bean:write name="modify" property="physiotherapyBelowThreeYears"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="physiotherapyAboveThreeYears"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="occupationalTherapyBelowThreeYears"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="occupationalTherapyAboveThreeYears"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="surgeryOH"/></td>
                             <td style="text-align:center"><bean:write name="modify" property="total"/></td>
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
                        <th align="center"  colspan="7">Prosthesis for Upper Extremity</th>
                        <th align="center"  colspan="6">Prosthesis for Lower Extremity</th>
                        <th align="center"  rowspan=2>Total</th>
                    </tr>
                    <tr>
                        <th  align="center">Shoulder</th>
                        <th  align="center">Above Elbow</th>
                        <th  align="center">Elbow Disarticulation</th>
                        <th  align="center">Below Elbow</th>
                        <th  align="center">Wrist Disarticulation</th>
                        <th  align="center">Hand</th>
                        <th  align="center">Cosmetic Finger</th>
                        <th  align="center">Hip Disarticulation</th>
                        <th  align="center">Above Knee</th>
                        <th  align="center">Knee Disarticulation</th>
                        <th  align="center">Below Knee</th>
                        <th  align="center">Syme's</th>
                        <th  align="center">Partial Foot</th>
                    </tr>
                    <logic:iterate id="modify" name="prothosisList" scope="request">
                        <tr>
                            <td style="text-align:center"><%=++i%></td>
                            <logic:notEmpty name="modify" property="districtName">
                                <td  align="left">
                                    ${modify.districtName}
                                </td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="mandalName">
                                <td  align="left">${modify.mandalName}</td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="villageName">
                                <td  align="left">${modify.villageName}</td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="habitationName">
                                <td  align="left">${modify.habitationName}</td>
                            </logic:notEmpty>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="shoulder"/></td>
                             <td style="text-align:center">
                               
                                    <bean:write name="modify" property="aboveElbow"/></td>
                             <td style="text-align:center">
                               
                                    <bean:write name="modify" property="elbowDisarticulation"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="belowElbow"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="wristDisarticulation"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="hand"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="cosmeticFinger"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="hipDisarticulation"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="aboveKnee"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="kneeDisarticulation"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="belowKnee"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="symes"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="partialFoot"/></td>
                             <td style="text-align:center"><bean:write name="modify" property="total"/></td>
                        </tr>

                    </logic:iterate>
                    <tr>
                        <td  colspan="2" align="center">Total</td>
                        <td style="text-align:center"><b><%=shoulderTotal%></b></td>
                        <td style="text-align:center"><b><%=aboveElbowTotal%></b></td>
                        <td style="text-align:center"><b><%=elbowDisarticulationTotal%></b></td>
                        <td style="text-align:center"><b><%=belowElbowTotal%></b></td>
                        <td style="text-align:center"><b><%=wristDisarticulationTotal%></b></td>
                        <td style="text-align:center"><b><%=handTotal%></b></td>
                        <td style="text-align:center"><b><%=cosmeticFingerTotal%></b></td>
                        <td style="text-align:center"><b><%=hipDisarticulationTotal%></b></td>
                        <td style="text-align:center"><b><%=aboveKneeTotal%></b></td>
                        <td style="text-align:center"><b><%=kneeDisarticulationTotal%></b></td>
                        <td style="text-align:center"><b><%=belowKneeTotal%></b></td>
                        <td style="text-align:center"><b><%=symesTotal%></b></td>
                        <td style="text-align:center"><b><%=partialFootTotal%></b></td>
                        <td style="text-align:center"><%= total%></td>
                    </tr>
                </table>
            </logic:present>






            <logic:present name="orthosisList" scope="request">
                <p>Assistive Devices Orthosis Report</p>
                <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">
                    <tr>
                        <th align="center"  rowspan=2>S.No</th>
                        <th rowspan="2"><logic:present name="regionHeading">${regionHeading}</logic:present></th>
                        <th align="center"  colspan="4">Orthosis/ Splint for Upper Extremirty</th>
                        <th align="center"  colspan="7">Orthosis/ Splint for Lower extremity</th>
                        <th align="center"  colspan="3">Spinal Orthosis</th>
                        <th align="center"  rowspan=2>Total</th>
                    </tr>
                    <tr>
                        <th  align="center">Aeroplane Splint</th>
                        <th  align="center">Figure 8 Splint</th>
                        <th  align="center">Forearm Splint</th>
                        <th  align="center">Hand Splint</th>
                        <th  align="center">HKAFO</th>
                        <th  align="center">KAFO</th>
                        <th  align="center">AFO</th>
                        <th  align="center">Knee orthosis</th>
                        <th  align="center">DB Splint</th>
                        <th  align="center">Modified Shoe</th>
                        <th  align="center">Serial Casting(CTEV)</th>
                        <th  align="center">Cervical Collar</th>
                        <th  align="center">LS Brace</th>
                        <th  align="center">TLSO Brace(For scoliosis/ Kyphosis)</th>
                    </tr>
                    <% i = 0;%>
                    <logic:iterate id="modify" name="orthosisList" scope="request">
                        <tr>
                            <td style="text-align:center"><%=++i%></td>
                            <logic:notEmpty name="modify" property="districtName">
                                <td  align="left">
                                    ${modify.districtName}
                                </td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="mandalName">
                                <td  align="left">${modify.mandalName}</td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="villageName">
                                <td  align="left">${modify.villageName}</td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="habitationName">
                                <td  align="left">${modify.habitationName}</td>
                            </logic:notEmpty>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="aeroplaneSplint"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="figureEightSplint"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="forearmSplint"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="handSplint"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="hkafoh"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="kafo"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="afo"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="kneeOrthosis"/></td>
                             <td style="text-align:center">
                              
                                    <bean:write name="modify" property="dbSplint"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="modifiedShoe"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="serialCastingCTEV"/></td>
                             <td style="text-align:center">

                                    <bean:write name="modify" property="cervicalCollar"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="lsBrace"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="tlsoBraceForScoliosisKyphosis"/></td>
                             <td style="text-align:center"><bean:write name="modify" property="total"/></td>
                        </tr>


                    </logic:iterate>
                    <tr>
                        <td  colspan="2" align="center">Total</td>
                        <td style="text-align:center"><b>${aeroplaneSplintTotal}</b></td>
                        <td style="text-align:center"><b><%=figureEightSplintTotal%></b></td>
                        <td style="text-align:center"><b><%=forearmSplintTotal%></b></td>
                        <td style="text-align:center"><b>${handSplintTotal}</b></td>
                        <td style="text-align:center"><b><%=hkafohTotal%></b></td>
                        <td style="text-align:center"><b><%=kafoTotal%></b></td>
                        <td style="text-align:center"><b><%=afoTotal%></b></td>
                        <td style="text-align:center"><b><%=kneeOrthosisTotal%></b></td>
                        <td style="text-align:center"><b><%=dbSplintTotal%></b></td>
                        <td style="text-align:center"><b><%=modifiedShoeTotal%></b></td>
                        <td style="text-align:center"><b><%=serialCastingCTEVTotal%></b></td>
                        <td style="text-align:center"><b><%=cervicalCollarTotal%></b></td>
                        <td style="text-align:center"><b><%=lsBraceTotal%></b></td>
                        <td style="text-align:center"><b><%=tlsoBraceForScoliosisKyphosisTotal%></b></td>
                        <td style="text-align:center"><%= total%></td>
                    </tr>
                </table><br>

            </logic:present>


            <logic:present name="mobilityList" scope="request">
                <p>Assistive Devices Mobility Aids Report</p>
                <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">

                    <tr>
                        <th align="center"  rowspan=2>S.No</th>
                        <th rowspan=2><logic:present name="regionHeading">${regionHeading}</logic:present></th>
                        <th align="center"  colspan="2">Wheel chair</th>
                        <th align="center"  colspan="2">Tricycle</th>
                        <th align="center"  colspan="2">Walking Frame/ Walker</th>
                        <th align="center"  rowspan=2>Total</th>
                    </tr>
                    <tr>
                        <th  align="center">Small</th>
                        <th  align="center">Large</th>
                        <th  align="center">Small</th>
                        <th  align="center">Large</th>
                        <th  align="center">Small</th>
                        <th  align="center">Large</th>
                    </tr>
                    <logic:iterate id="modify" name="mobilityList" scope="request">
                        <tr>
                            <td style="text-align:center"><%=++i%></td>
                            <logic:notEmpty name="modify" property="districtName">
                                <td  align="left">
                                    ${modify.districtName}
                                </td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="mandalName">
                                <td  align="left">${modify.mandalName}</td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="villageName">
                                <td  align="left">${modify.villageName}</td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="habitationName">
                                <td  align="left">${modify.habitationName}</td>
                            </logic:notEmpty>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="wheelChairSmall"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="wheelChairLarge"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="tricycleSmall"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="tricycleLarge"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="walkingFrameWalkerSmall"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="walkingFrameWalkerLarge"/></td>
                             <td style="text-align:center"><bean:write name="modify" property="total"/></td>
                        </tr>

                    </logic:iterate>
                    <tr>
                        <td  colspan="2" align="center">Total</td>
                        <td style="text-align:center"><%=wheelChairSmallTotal%></td>
                        <td style="text-align:center"><%=wheelChairLargeTotal%></td>
                        <td style="text-align:center"><%=tricycleSmallTotal%></td>
                        <td style="text-align:center"><%=tricycleLargeTotal%></td>
                        <td style="text-align:center"><%=walkingFrameWalkerSmallTotal%></td>
                        <td style="text-align:center"><%=walkingFrameWalkerLargeTotal%></td>
                        <td style="text-align:center"><%= total%></td>
                    </tr>
                </table>
            </logic:present>

            <logic:present name="walkingAidsList" scope="request">
                <p>Assistive Devices Walking Aids Report</p>
                <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">

                    <tr>
                        <th align="center"  rowspan=2>S.No</th>
                        <th rowspan=2><logic:present name="regionHeading">${regionHeading}</logic:present></th>
                        <th align="center"  colspan="2">Walking Stick</th>
                        <th align="center"  colspan="4">Axillary</th>
                        <th align="center"  colspan="4">Elbow</th>
                        <th align="center"  colspan="4">Gutter</th>
                        <th align="center"  colspan="4">Tripod</th>
                        <th align="center"  rowspan=2>Total</th>
                    </tr>
                    <tr>
                        <th  align="center">Small</th>
                        <th  align="center">Large</th>
                        <th  align="center">Small</th>
                        <th  align="center">Medium</th>
                        <th  align="center">Large</th>
                        <th  align="center">Extra Large</th>
                        <th  align="center">Small</th>
                        <th  align="center">Medium</th>
                        <th  align="center">Large</th>
                        <th  align="center">Extra Large</th>
                        <th  align="center">Small</th>
                        <th  align="center">Medium</th>
                        <th  align="center">Large</th>
                        <th  align="center">Extra Large</th>
                        <th  align="center">Small</th>
                        <th  align="center">Medium</th>
                        <th  align="center">Large</th>
                        <th  align="center">Extra Large</th>
                    </tr>

                    <logic:iterate id="modify" name="walkingAidsList" scope="request">

                        <tr>
                            <td style="text-align:center"><%=++i%></td>
                            <logic:notEmpty name="modify" property="districtName">
                                <td  align="left">
                                    ${modify.districtName}
                                </td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="mandalName">
                                <td  align="left">${modify.mandalName}</td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="villageName">
                                <td  align="left">${modify.villageName}</td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="habitationName">
                                <td  align="left">${modify.habitationName}</td>
                            </logic:notEmpty>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="walkingStickSmall"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="walkingStickLarge"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="axillarySmall"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="axillaryMedium"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="axillaryLarge"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="axillaryExtraLarge"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="elbowSmall"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="elbowMedium"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="elbowLarge"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="elbowExtraLarge"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="gutterSmall"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="gutterMedium"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="gutterLarge"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="gutterExtraLarge"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="tripodSmall"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="tripodMedium"/></td>
                             <td style="text-align:center">

                                    <bean:write name="modify" property="tripodLarge"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="tripodExtraLarge"/></td>
                             <td style="text-align:center"><bean:write name="modify" property="total"/></td>
                        </tr>

                    </logic:iterate>
                    <tr>
                        <td  colspan="2" align="center">Total</td>
                        <td style="text-align:center"><b><%=walkingStickSmallTotal%></b></td>
                        <td style="text-align:center"><b><%=walkingStickLargeTotal%></b></td>
                        <td style="text-align:center"><b><%=axillarySmallTotal%></b></td>
                        <td style="text-align:center"><b><%=axillaryMediumTotal%></b></td>
                        <td style="text-align:center"><b><%=axillaryLargeTotal%></b></td>
                        <td style="text-align:center"><b><%=axillaryExtraLargeTotal%></b></td>
                        <td style="text-align:center"><b><%=elbowSmallTotal%></b></td>
                        <td style="text-align:center"><b><%=elbowMediumTotal%></b></td>
                        <td style="text-align:center"><b><%=elbowLargeTotal%></b></td>
                        <td style="text-align:center"><b><%=elbowExtraLargeTotal%></b></td>
                        <td style="text-align:center"><b><%=gutterSmallTotal%></b></td>
                        <td style="text-align:center"><b><%=gutterMediumTotal%></b></td>
                        <td style="text-align:center"><b><%=gutterLargeTotal%></b></td>
                        <td style="text-align:center"><b><%=gutterExtraLargeTotal%></b></td>
                        <td style="text-align:center"><b><%=tripodSmallTotal%></b></td>
                        <td style="text-align:center"><b><%=tripodMediumTotal%></b></td>
                        <td style="text-align:center"><b><%=tripodLargeTotal%></b></td>
                        <td style="text-align:center"><b><%=tripodExtraLargeTotal%></b></td>
                        <td style="text-align:center"><%= total%></td>
                    </tr>
                </table>
              
            </logic:present>



            <logic:present name="adlEquipmentsList" scope="request">
                <p>Assistive Devices ADL Equipments Report</p>
                <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">

                    <tr>
                        <th align="center" >S.No</th>
                        <th ><logic:present name="regionHeading">${regionHeading}</logic:present></th>
                        <th align="center" >Feeding</th>
                        <th align="center" >Toileting/ Bathing</th>
                        <th align="center" >Brushing</th>
                        <th align="center" >Combing</th>
                        <th align="center" >Dressing</th>
                        <th align="center" >Writing</th>
                        <th align="center" >Driving/ Cycling</th>
                        <th align="center" >Bed Transfer</th>
                        <th align="center" >Total</th>
                    </tr>


                    <logic:iterate id="modify" name="adlEquipmentsList" scope="request">

                        <tr>
                            <td style="text-align:center"><%=++i%></td>
                            <logic:notEmpty name="modify" property="districtName">
                                <td  align="left">
                                    ${modify.districtName}
                                </td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="mandalName">
                                <td  align="left">${modify.mandalName}</td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="villageName">
                                <td  align="left">${modify.villageName}</td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="habitationName">
                                <td  align="left">${modify.habitationName}</td>
                            </logic:notEmpty>
                             <td style="text-align:center">
                               
                                    <bean:write name="modify" property="feeding"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="toiletingBathing"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="brushing"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="combing"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="dressing"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="writing"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="drivingCycling"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="bedTransfer"/></td>
                             <td style="text-align:center"><bean:write name="modify" property="total"/></td>
                        </tr>

                    </logic:iterate>
                    <tr>
                        <td  colspan="2" align="center">Total</td>
                        <td style="text-align:center"><%=feedingTotal%></td>
                        <td style="text-align:center"><%=toiletingBathingTotal%></td>
                        <td style="text-align:center"><%=brushingTotal%></td>
                        <td style="text-align:center"><%=combingTotal%></td>
                        <td style="text-align:center"><%=dressingTotal%></td>
                        <td style="text-align:center"><%=writingTotal%></td>
                        <td style="text-align:center"><%=drivingCyclingTotal%></td>
                        <td style="text-align:center"><%=bedTransferTotal%></td>
                        <td style="text-align:center"><%= total%></td>
                    </tr>
                </table>
            </logic:present>




            <logic:present name="otherNeedsList" scope="request">
                <p>Other Needs Report</p>
                <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">

                    <tr>
                        <th align="center" >S.No</th>
                        <th ><logic:present name="regionHeading">${regionHeading}</logic:present></th>
                        <th align="center" >Other Needs</th>
                    </tr>
                    <logic:iterate id="modify" name="otherNeedsList" scope="request">
                        <tr>
                            <td style="text-align:center"><%=++i%></td>
                            <logic:notEmpty name="modify" property="districtName">
                                <td  align="left">
                                    ${modify.districtName}
                                </td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="mandalName">
                                <td  align="left">${modify.mandalName}</td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="villageName">
                                <td  align="left">${modify.villageName}</td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="habitationName">
                                <td  align="left">${modify.habitationName}</td>
                            </logic:notEmpty>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="otherNeeds"/></td>
                        </tr>

                    </logic:iterate>
                    <tr>
                        <th  colspan="2" align="center">Total</th>
                        <th  align="center"><%=otherNeedsTotal%></th>

                    </tr>
                </table>
            </logic:present>



            <logic:present name="medicalInterventionVIList" scope="request">
                <p>Medical Intervention VI Report</p>
                <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">
                    <tr>
                        <th align="center" >S.No</th>
                        <th ><logic:present name="regionHeading">${regionHeading}</logic:present></th>
                        <th align="center" >Surgery</th>
                    </tr>
                    <logic:iterate id="modify" name="medicalInterventionVIList" scope="request">
                        <tr>
                            <td style="text-align:center"><%=++i%></td>
                            <logic:notEmpty name="modify" property="districtName">
                                <td  align="left">
                                    ${modify.districtName}
                                </td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="mandalName">
                                <td  align="left">${modify.mandalName}</td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="villageName">
                                <td  align="left">${modify.villageName}</td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="habitationName">
                                <td  align="left">${modify.habitationName}</td>
                            </logic:notEmpty>
                             <td style="text-align:center">
                               
                                    <bean:write name="modify" property="sugeryVI"/></td>
                        </tr>

                    </logic:iterate>
                    <tr>
                        <th  colspan="2" align="center">Total</th>
                        <th  align="center"><%=sergeryVITotal%></th>

                    </tr>
                </table>
            </logic:present>
            <logic:present name="assistiveDevicesVIList" scope="request">
                <p>Assistive Devices VI Report</p>
                <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">

                    <tr>
                        <th align="center" >S.No</th>
                        <th ><logic:present name="regionHeading">${regionHeading}</logic:present></th>
                        <th align="center" >White Cane/ Blind Stick</th>
                        <th align="center" >Braille Equipments</th>
                        <th align="center" >Arithmetic Frames/ Abacus</th>
                        <th align="center" >Low Vision Aids (Spectacles, Magnifiers)</th>
                        <th align="center" >Speech Synthesizer</th>
                        <th align="center" >Braille Short Hand Machines/ Type Writers</th>
                        <th align="center" >Talking Watch/ Calculator</th>
                        <th align="center" >ADL Equipment</th>
                        <th align="center" >Total</th>
                    </tr>

                    <logic:iterate id="modify" name="assistiveDevicesVIList" scope="request">
                        <tr>
                            <td style="text-align:center"><%=++i%></td>
                            <logic:notEmpty name="modify" property="districtName">
                                <td  align="left">
                                    ${modify.districtName}
                                </td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="mandalName">
                                <td  align="left">${modify.mandalName}</td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="villageName">
                                <td  align="left">${modify.villageName}</td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="habitationName">
                                <td  align="left">${modify.habitationName}</td>
                            </logic:notEmpty>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="whiteCaneBlindStick"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="brailleEquipments"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="arithmeticFramesAbacus"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="lowVisionAidsSpectaclesMagnifiers"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="speechSynthesizer"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="brailleShortHandMachinesWriters"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="talkingWatchCalculator"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="viADLEquipment"/></td>
                             <td style="text-align:center"><bean:write name="modify" property="total"/></td>
                        </tr>

                    </logic:iterate>
                    <tr>
                        <th  colspan="2" align="center">Total</th>
                        <th  align="center"><%=whiteCaneBlindStickTotal%></th>
                        <th  align="center"><%=brailleEquipmentsTotal%></th>
                        <th  align="center"><%=arithmeticFramesAbacusTotal%></th>
                        <th  align="center"><%=lowVisionAidsSpectaclesMagnifiersTotal%></th>
                        <th  align="center"><%=speechSynthesizerTotal%></th>
                        <th  align="center"><%=brailleShortHandMachinesWritersTotal%></th>
                        <th  align="center"><%=talkingWatchCalculatorTotal%></th>
                        <th  align="center"><%=viADLEquipmentTotal%></th>
                        <th  align="center"><%= total%></th>
                    </tr>
                </table>
            </logic:present>


            <logic:present name="otherNeedsVIList" scope="request">
                <p>Other Needs VI Report

                </p>
                <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">

                    <tr>
                        <th align="center" >S.No</th>
                        <th><logic:present name="regionHeading">${regionHeading}</logic:present></th>
                        <th align="center" >Other Needs</th>
                    </tr>
                    <logic:iterate id="modify" name="otherNeedsVIList" scope="request">
                        <tr>
                            <td style="text-align:center"><%=++i%></td>
                            <logic:notEmpty name="modify" property="districtName">
                                <td  align="left">
                                    ${modify.districtName}
                                </td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="mandalName">
                                <td  align="left">${modify.mandalName}</td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="villageName">
                                <td  align="left">${modify.villageName}</td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="habitationName">
                                <td  align="left">${modify.habitationName}</td>
                            </logic:notEmpty>
                             <td style="text-align:center">
                               
                                    <bean:write name="modify" property="otherNeedsVI"/></td>
                        </tr>

                    </logic:iterate>
                    <tr>
                        <th  colspan="2" align="center">Total</th>
                        <th  align="center"><%=otherNeedsVITotal%></th>

                    </tr>
                </table>
            </logic:present>

            <logic:present name="medicalInterventionHIList" scope="request">
                <p>Medical Intervention HI Report</p>
                <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">

                    <tr>
                        <th align="center"  rowspan=2>S.No</th>
                        <th  rowspan=2 ><logic:present name="regionHeading" >${regionHeading}</logic:present></th>
                        <th align="center"  colspan="2">Speech therapy</th>
                        <th align="center"  rowspan="2">Language Development</th>
                        <th align="center"  rowspan=2>Surgery</th>
                        <th align="center"  rowspan=2>Cochlear Implantation</th>
                        <th align="center"  rowspan=2>Total</th>
                    </tr>
                    <tr>
                        <th  align="center">Below 3 Years</th>
                        <th  align="center">Above 3 Years</th>
                    </tr>
                    <logic:iterate id="modify" name="medicalInterventionHIList" scope="request">
                        <tr>
                            <td style="text-align:center"><%=++i%></td>
                            <logic:notEmpty name="modify" property="districtName">
                                <td  align="left">
                                    ${modify.districtName}
                                </td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="mandalName">
                                <td  align="left">${modify.mandalName}</td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="villageName">
                                <td  align="left">${modify.villageName}</td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="habitationName">
                                <td  align="left">${modify.habitationName}</td>
                            </logic:notEmpty>
                             <td style="text-align:center">
                             
                                    <bean:write name="modify" property="speechTherapyBelowThreeYearsHI"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="speechTherapyAboveThreeYearsHI"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="languageDevelopment"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="surgeryHI"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="cochlearImplantation"/></td>
                             <td style="text-align:center"><bean:write name="modify" property="total"/></td>
                        </tr>

                    </logic:iterate>
                    <tr>
                        <td  colspan="2" align="center">Total</td>
                        <td style="text-align:center"><%=speechTherapyBelowThreeYearsHITotal%></td>
                        <td style="text-align:center"><%=speechTherapyAboveThreeYearsHITotal%></td>
                        <td style="text-align:center"><%=languageDevelopmentTotal%></td>
                        <td style="text-align:center"><%=surgeryHITotal%></td>
                        <td style="text-align:center"><%=cochlearImplantationTotal%></td>
                        <td style="text-align:center"><%= total%></td>
                    </tr>
                </table>
            </logic:present>

            <logic:present name="assistiveDevicesHIList" scope="request">
                <p>Assistive Devices HI Report</p>
                <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">
                    <tr>
                        <th align="center"  rowspan=2>S.No</th>
                        <th  rowspan=2 ><logic:present name="regionHeading" >${regionHeading}</logic:present></th>
                        <th align="center"  colspan="2">Low intensity</th>
                        <th align="center"  colspan="2">Moderate Intensity</th>
                        <th align="center"  colspan="2">High Intensity</th>
                        <th align="center"  rowspan="2">Implantable Hearing Aid</th>
                        <th align="center"  rowspan="2">Total</th>
                    </tr>
                    <tr>
                        <th  align="center">S-Cord</th>
                        <th  align="center">V-Cord</th>
                        <th  align="center">S-Cord</th>
                        <th  align="center">V-Cord</th>
                        <th  align="center">S-Cord</th>
                        <th  align="center">V-Cord</th>
                    </tr>
                    <logic:iterate id="modify" name="assistiveDevicesHIList" scope="request">
                        <tr>
                            <td style="text-align:center"><%=++i%></td>
                            <logic:notEmpty name="modify" property="districtName">
                                <td  align="left">
                                    ${modify.districtName}
                                </td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="mandalName">
                                <td  align="left">${modify.mandalName}</td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="villageName">
                                <td  align="left">${modify.villageName}</td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="habitationName">
                                <td  align="left">${modify.habitationName}</td>
                            </logic:notEmpty>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="lowIntensitySCord"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="lowIntensityVCord"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="moderateIntensitySCord"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="moderateIntensityVCord"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="highIntensitySCord"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="highIntensityVCord"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="implantableHearingAid"/></td>
                             <td style="text-align:center"><bean:write name="modify" property="total"/></td>
                        </tr>

                    </logic:iterate>
                    <tr>
                        <th  colspan="2" align="center">Total</th>
                        <th  align="center"><%=lowIntensitySCordTotal%></th>
                        <th  align="center"><%=lowIntensityVCordTotal%></th>
                        <th  align="center"><%=moderateIntensitySCordTotal%></th>
                        <th  align="center"><%=moderateIntensityVCordTotal%></th>
                        <th  align="center"><%=highIntensitySCordTotal%></th>
                        <th  align="center"><%=highIntensityVCordTotal%></th>
                        <th  align="center"><%=implantableHearingAidTotal%></th>
                        <th  align="center"><%= total%></th>
                    </tr>
                </table>
            </logic:present>

            <logic:present name="otherNeedsHIList" scope="request">

                <p>Other Needs HI Report</p>
                <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">

                    <tr>
                        <th align="center" >S.No</th>
                        <th  ><logic:present name="regionHeading" >${regionHeading}</logic:present></th>
                        <th align="center" >Other Needs</th>
                    </tr>
                    <logic:iterate id="modify" name="otherNeedsHIList" scope="request">
                        <tr>
                            <td style="text-align:center"><%=++i%></td>
                            <logic:notEmpty name="modify" property="districtName">
                                <td  align="left">
                                    ${modify.districtName}
                                </td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="mandalName">
                                <td  align="left">${modify.mandalName}</td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="villageName">
                                <td  align="left">${modify.villageName}</td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="habitationName">
                                <td  align="left">${modify.habitationName}</td>
                            </logic:notEmpty>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="otherNeedsHI"/></td>
                        </tr>

                    </logic:iterate>
                    <tr>
                        <th  colspan="2" align="center">Total</th>
                        <th  align="center"><%=otherNeedsHITotal%></th>

                    </tr>
                </table>
            </logic:present>



            <logic:present name="medicalInterventionMRList" scope="request">
                <p>Medical Intervention MR Report</p>

                <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">
                    <tr>
                        <th align="center"  rowspan=2>S.No</th>
                        <th  rowspan="2"><logic:present name="regionHeading" >${regionHeading}</logic:present></th>

                        <th align="center"  colspan="2">Speech Therapy & Language Development</th>
                        <th align="center"  colspan="2">Behaviour Modification/Psychotherapy</th>
                        <th align="center"  rowspan=2>Sensory Integration/ Cognitive Development</th>
                        <th align="center"  rowspan=2>Cognitive Behaviour Therapy</th>
                        <th align="center"  rowspan=2>Parent/ Family Intervention</th>
                        <th align="center"  rowspan=2>Physiotherapy</th>
                        <th align="center"  rowspan=2>Occupational Therapy</th>
                        <th align="center"  rowspan=2>Total</th>
                    </tr>
                    <tr>
                        <th  align="center">Below 3 Years</th>
                        <th  align="center">Above 3 Years</th>
                        <th  align="center">Below 3 Years</th>
                        <th  align="center">Above 3 Years</th>
                    </tr>
                    <logic:iterate id="modify" name="medicalInterventionMRList" scope="request">
                        <tr>
                            <td style="text-align:center"><%=++i%></td>
                            <logic:notEmpty name="modify" property="districtName">
                                <td  align="left">
                                    ${modify.districtName}
                                </td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="mandalName">
                                <td  align="left">${modify.mandalName}</td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="villageName">
                                <td  align="left">${modify.villageName}</td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="habitationName">
                                <td  align="left">${modify.habitationName}</td>
                            </logic:notEmpty>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="speechTherapyBelowThreeYearsMR"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="speechTherapyAboveThreeYearsMR"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="behaviourModificationPsychotherapyBelow"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="behaviourModificationPsychotherapyAbove"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="sensoryIntegrationCognitiveDevelopment"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="cognitiveBehaviourTherapy"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="parentFamilyIntervention"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="physiotherapyMR"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="occupationalTherapyMR"/></td>
                             <td style="text-align:center"><bean:write name="modify" property="total"/></td>
                        </tr>

                    </logic:iterate>
                    <tr>
                        <th  colspan="2" align="center">Total</th>
                        <th  align="center"><%=speechTherapyBelowThreeYearsMRTotal%></th>
                        <th  align="center"><%=speechTherapyAboveThreeYearsMRTotal%></th>
                        <th  align="center"><%=behaviourModificationPsychotherapyBelowTotal%></th>
                        <th  align="center"><%=behaviourModificationPsychotherapyAboveTotal%></th>
                        <th  align="center"><%=sensoryIntegrationCognitiveDevelopmentTotal%></th>
                        <th  align="center"><%=cognitiveBehaviourTherapyTotal%></th>
                        <th  align="center"><%=parentFamilyInterventionTotal%></th>
                        <th  align="center"><%=physiotherapyMRTotal%></th>
                        <th  align="center"><%=occupationalTherapyMRTotal%></th>
                        <th  align="center"><%= total%></th>
                    </tr>
                </table>
            </logic:present>


            <logic:present name="assistiveDevicesMRList" scope="request">
                <p>Assistive Devices MR Report</p>
                <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">
                    <tr>
                        <th align="center" >S.No</th>
                        <th ><logic:present name="regionHeading" >${regionHeading}</logic:present></th>

                        <th align="center" >Learning Materials</th>
                        <th align="center" >Special Software</th>
                        <th align="center" >Toys</th>
                        <th align="center" >Total</th>
                    </tr>

                    <logic:iterate id="modify" name="assistiveDevicesMRList" scope="request">
                        <tr>
                            <td style="text-align:center"><%=++i%></td>
                            <logic:notEmpty name="modify" property="districtName">
                                <td  align="left">
                                    ${modify.districtName}
                                </td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="mandalName">
                                <td  align="left">${modify.mandalName}</td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="villageName">
                                <td  align="left">${modify.villageName}</td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="habitationName">
                                <td  align="left">${modify.habitationName}</td>
                            </logic:notEmpty>

                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="learningMaterials"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="specialSoftware"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="toys"/></td>
                             <td style="text-align:center"><bean:write name="modify" property="total"/></td>
                        </tr>

                    </logic:iterate>
                    <tr>
                        <th  colspan="2" align="center">Total</th>
                        <th  align="center"><%=learningMaterialsTotal%></th>
                        <th  align="center"><%=specialSoftwareTotal%></th>
                        <th  align="center"><%=toysTotal%></th>
                        <th  align="center"><%= total%></th>
                    </tr>
                </table>
            </logic:present>



            <logic:present name="otherNeedsMRList" scope="request">
                <p>Other Needs MR Report</p>
                <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">
                    <tr>
                        <th align="center" >S.No</th>
                        <th ><logic:present name="regionHeading" >${regionHeading}</logic:present></th>
                        <th align="center" >Other Needs</th>
                    </tr>
                    <logic:iterate id="modify" name="otherNeedsMRList" scope="request">
                        <tr>
                            <td style="text-align:center"><%=++i%></td>
                            <logic:notEmpty name="modify" property="districtName">
                                <td  align="left">
                                    ${modify.districtName}
                                </td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="mandalName">
                                <td  align="left">${modify.mandalName}</td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="villageName">
                                <td  align="left">${modify.villageName}</td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="habitationName">
                                <td  align="left">${modify.habitationName}</td>
                            </logic:notEmpty>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="otherNeedsMR"/></td>
                        </tr>

                    </logic:iterate>
                    <tr>
                        <th  colspan="2" align="center">Total</th>
                        <th  align="center"><%=otherNeedsMRTotal%></th>

                    </tr>
                </table>
            </logic:present>

            <logic:present name="medicalInterventionMIList" scope="request">
                <p>Medical Intervention MI Report</p>
                <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">
                    <tr>
                        <th align="center"  rowspan=2>S.No</th>
                        <th rowspan=2><logic:present name="regionHeading" >${regionHeading}</logic:present></th>
                        <th align="center"  colspan="2">Psychotherapy/ Behaviour Modification</th>
                        <th align="center"  rowspan="2">Surgery</th>
                        <th align="center"  rowspan="2">Admission in Psychiatric Hospitals/ Nursing Homes</th>
                        <th align="center"  rowspan="2">Total</th>
                    </tr>
                    <tr>
                        <th  align="center">Below 3 Years</th>
                        <th  align="center">Above 3 Years</th>
                    </tr>
                    <logic:iterate id="modify" name="medicalInterventionMIList" scope="request">
                        <tr>
                            <td style="text-align:center"><%=++i%></td>
                            <logic:notEmpty name="modify" property="districtName">
                                <td  align="left">
                                    ${modify.districtName}
                                </td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="mandalName">
                                <td  align="left">${modify.mandalName}</td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="villageName">
                                <td  align="left">${modify.villageName}</td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="habitationName">
                                <td  align="left">${modify.habitationName}</td>
                            </logic:notEmpty>

                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="psychotherapyBehaviourBelowThreeYearsMI"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="psychotherapyBehaviourAboveThreeYearsMI"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="surgeryMI"/></td>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="admissionPsychiatric"/></td>

                             <td style="text-align:center"><bean:write name="modify" property="total"/></td>
                        </tr>

                    </logic:iterate>
                    <tr>
                        <th  colspan="2" align="center">Total</th>
                        <th  align="center"><%=psychotherapyBehaviourBelowThreeYearsMITotal%></th>
                        <th  align="center"><%=psychotherapyBehaviourAboveThreeYearsMITotal%></th>
                        <th  align="center"><%=surgeryMITotal%></th>
                        <th  align="center"><%=admissionPsychiatricTotal%></th>
                        <th  align="center"><%= total%></th>
                    </tr>
                </table>
            </logic:present>



            <logic:present name="otherNeedsMIList" scope="request">
                <p>Other Needs MI Report</p>
                <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">

                    <tr>
                        <th align="center" >S.No</th>
                        <th><logic:present name="regionHeading" >${regionHeading}</logic:present></th>
                        <th align="center" >Other Needs</th>
                    </tr>
                    <logic:iterate id="modify" name="otherNeedsMIList" scope="request">
                        <tr>
                            <td style="text-align:center"><%=++i%></td>
                            <logic:notEmpty name="modify" property="districtName">
                                <td  align="left">
                                    ${modify.districtName}
                                </td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="mandalName">
                                <td  align="left">${modify.mandalName}</td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="villageName">
                                <td  align="left">${modify.villageName}</td>
                            </logic:notEmpty>
                            <logic:notEmpty name="modify" property="habitationName">
                                <td  align="left">${modify.habitationName}</td>
                            </logic:notEmpty>
                             <td style="text-align:center">
                                
                                    <bean:write name="modify" property="otherNeedsMI"/></td>
                        </tr>

                    </logic:iterate>
                    <tr>
                        <th  colspan="2" align="center">Total</th>
                        <th  align="center"><%=otherNeedsMITotal%></th>

                    </tr>
                </table>
            </logic:present>
        </td>
    </tr>
</table>


</body>
</html:html>
