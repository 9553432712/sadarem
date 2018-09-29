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
<%@page session="true"%>


<%         Iterator iter = null;

            String fromdate = (String) request.getAttribute("fromdate");
            String todate = (String) request.getAttribute("todate");
            String typeOfDisability = "" + request.getAttribute("typeofdisability");
            String category = "" + request.getAttribute("reportcategory");
            String subCategory = "" + request.getAttribute("reportSubcategory");
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
        <script src="./DisabilityUITG/js/Validation.js"></script>
        <script language="JavaScript" src="./DisabilityUITG/js/date-picker"></script>

        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script >


            function removeLists(start,end)
            {
                for(k=start;k<=end;k++)
                {
                    var x1=document.getElementById(k).length;
                    for(i=x1;i>1;i--)
                        document.getElementById(k).options[i]=null;
                    document.getElementById(k).value="";
                }
            } 

       
         

            function selectedNames()
            {
                var slcBx1 = document.getElementById("1");
                var slcBx2 = document.getElementById("2");
                var slcBx3 = document.getElementById("3");
                
                document.getElementById("districtName").value = slcBx1.options[slcBx1.selectedIndex].text;
                document.getElementById("mandalName").value = slcBx2.options[slcBx2.selectedIndex].text;
                document.getElementById("villageName").value = slcBx3.options[slcBx3.selectedIndex].text;
                
            }

            function addoption(result1,result2,name)
            {
                var opt=document.createElement("OPTION");
                opt.text=result1;
                opt.value=result2;

                try{
                    document.getElementById(name).add(opt);
                }catch(ex)
                {
                    if(name=="district_id") {
                        document.forms[0].district_id.appendChild(opt,null);
                    }else  if(name=="mandal_id") {
                        document.forms[0].mandal_id.appendChild(opt,null);
                    }else  if(name=="village_id") {
                        document.forms[0].village_id.appendChild(opt,null);
                    }


                }
            }
            function removeall(name)
            {
                if(name=="mandal_id") {
                    var x1=document.forms[0].mandal_id.options.length;
                }
                else if(name=="village_id") {
                    var x1=document.forms[0].village_id.options.length;
                } else if(name=="district_id") {
                    var x1=document.forms[0].district_id.options.length;
                }

                for(i=x1;i>0;i--) {
                    if(name=="mandal_id") {
                        document.forms[0].mandal_id.options[i]=null;
                    }else if(name=="village_id") {
                        document.forms[0].village_id.options[i]=null;
                    } else if(name=="district_id") {
                        document.forms[0].district_id.options[i]=null;
                    }
                }

            }

            function GetXmlHttpObject()
            {
                var objXmlHttp=null
                if(window.XMLHttpRequest)
                {
                    objXmlHttp=new XMLHttpRequest();
                }
                else if(window.ActiveXObject)
                {
                    objXmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
                }
                return objXmlHttp;
            }
            function validate_required(field,alerttxt)
            {
                
                with (field)
                {                    
                    if (value==null||value=="")
                    {
                        alert(alerttxt);
                        return false
                    }
                    else
                    {
                        return true
                    }
                }
            }
            function validate_form(thisform)
            {
                with (thisform)
                {
                    if (validate_required(typeofdisability,"Select Type Of Disability")==false)
                    {
                        typeofdisability.focus();
                        return false
                    }
                    if (validate_required(reportcategory,"Select Category")==false)
                    {
                        reportcategory.focus();
                        return false
                    }                    
                    if (validate_required(fromdate,"From Date must be selected!")==false)
                    {                        
                        fromdate.focus();
                        return false
                    }
                    if (validate_required(todate,"To Date must be selected!")==false)
                    {
                        todate.focus();
                        return false
                    }
                    var fromDate= document.forms[0].fromdate.value;
                    var toDate= document.forms[0].todate.value;
                    var yye=fromDate.substr(6,4);
                    var mme=fromDate.substr(3,2);
                    var dde=fromDate.substr(0,2);
                    var yyb=toDate.substr(6,4);
                    var mmb=toDate.substr(3,2);
                    var ddb=toDate.substr(0,2);
                    var dob = new  Date();
                    var etd = new  Date();
                    var today=new Date();
                    etd.setFullYear(yye,mme-1,dde);
                    dob.setFullYear(yyb,mmb-1,ddb)
                    var y1=today.getYear();
                    var y2= dob.getYear();
                    if (today < etd)
                    {
                        alert("From date is after Todays Date");
                        document.forms[0].fromdate.value="";
                        return false;
                    }
                    if (today < dob)
                    {
                        alert("To date is after Todays Date");
                        document.forms[0].todate.value="";
                        return false;
                    }
                    if (dob < etd)
                    {
                        alert("From date is greater than To date");
                        document.forms[0].fromdate.value="";
                        return false;
                    }
                    var disability=document.forms[0].typeofdisability.value;
                    var category=document.forms[0].reportcategory.value;
                    if((disability == 1 || disability == 6) && category == 2)
                    {
                        if (validate_required(reportSubcategory,"Selecte Sub Category")==false)
                        {
                            reportSubcategory.focus();
                            return false
                        }
                    }
                }
            }

            function disabileSubCategory()
            {
                var disability=document.forms[0].typeofdisability.value;
                var category=document.forms[0].reportcategory.value;

                if((disability == 1 && category == 2) || (disability == 6 &&category == 2))
                {
                    document.getElementById("divsubcategory").style.visibility = "Visible";
                    document.getElementById("divsubcategory").style.display = "Inline";
                }else{
                    document.getElementById("divsubcategory").style.visibility = "hidden";
                    document.getElementById("divsubcategory").style.display = "none";
                }
            }

         

           
            function droupDownOptions()
            {

                var z=document.forms[0].typeofdisability.value;
                var select=document.forms[0].reportcategory;
                var options=select.getElementsByTagName("option");
                var i;
               

                {
                    select.removeChild(options[i]);
                }

             
                disabileSubCategory();

            }

            function droupDownSubCategoryOptions()
            {
                var z=document.forms[0].typeofdisability.value;
                var select=document.forms[0].reportSubcategory;

                var options=select.getElementsByTagName("option");
                var i;
                for (i=options.length-1; i>0; i--)

                {
                    select.removeChild(options[i]);
                }

                if(z == 1 )
                {
                    var newOption1 = document.createElement('option');
                    newOption1.value="1";
                    newOption1.text = "Prosthesis";
                    try
                    {
                        // for IE earlier than version 8
                        document.forms[0].reportSubcategory.add(newOption1,select.options[null]);
                    }
                    catch (e)
                    {
                        document.forms[0].reportSubcategory.add(newOption1,null);
                    }
                    var newOption2 = document.createElement('option');
                    newOption2.value="2";
                    newOption2.text = "Orthosis";
                    try
                    {
                        // for IE earlier than version 8
                        document.forms[0].reportSubcategory.add(newOption2,select.options[null]);
                    }
                    catch (e)
                    {
                        document.forms[0].reportSubcategory.add(newOption2,null);
                    }
                    var newOption3 =document.createElement('option');
                    newOption3.value="3";
                    newOption3.text = "Mobility Aids";
                    try
                    {
                        // for IE earlier than version 8
                        document.forms[0].reportSubcategory.add(newOption3,select.options[null]);
                    }
                    catch (e)
                    {
                        document.forms[0].reportSubcategory.add(newOption3,null);
                    }
                    var newOption4 = document.createElement('option');
                    newOption4.value="4";
                    newOption4.text = "Walking Aids";
                    try
                    {
                        // for IE earlier than version 8
                        document.forms[0].reportSubcategory.add(newOption4,select.options[null]);
                    }
                    catch (e)
                    {
                        document.forms[0].reportSubcategory.add(newOption4,null);
                    }
                    var newOption5 = document.createElement('option');
                    newOption5.value="5";
                    newOption5.text = "ADL Equipments";
                    try
                    {
                        // for IE earlier than version 8
                        document.forms[0].reportSubcategory.add(newOption5,select.options[null]);
                    }
                    catch (e)
                    {
                        document.forms[0].reportSubcategory.add(newOption5,null);
                    }
                }
            }
           
        </script>
        <script>
            function getOtherReports(){
                if(document.getElementById("8").value==null||document.getElementById("8").value==""){
                    alert('Please select the To Date');
                    document.getElementById("8").focus();
                }else{
                    document.forms[0].mode.value="getOtherReports";
                    document.forms[0].submit();
                }
            }
            function printData()
            {
                var divToPrint=document.getElementById("printTable");
                newWin= window.open("");
                newWin.document.write(divToPrint.outerHTML);
                newWin.print();
                newWin.close();
            }
        </script>
    </head>

    <body>

        <html:form action="analysisReport.do" method="post" onsubmit="return selectedNames(),validate_form(this)"   >

            <table border="0" cellspacing="0" cellpadding="0" width="90%" align="center" class="inputform">

                <tr>
                    <td  align="center"  valign="top">
                        <input type="hidden" name="mode"/>
                        <input type="hidden" name="districtName"/>
                        <input type="hidden" name="mandalName"/>
                        <input type="hidden" name="villageName"/>

                        <table border="1" cellspacing="0" cellpadding="0" width="90%" align="center" class="inputform">
                            <tr><th colspan="2">A2.1 Specific Needs</th></tr>
                            <tr>
                                <td>
                                    <table border="0" cellspacing="0" cellpadding="0" width="100%" align="center" class="inputform">
                                        <tr>
                                            <td >Type Of Disability<font color="red"><b>*</b></font></td>
                                            <td >
                                                <html:select styleId="5" property="typeofdisability" onchange="disabileSubCategory(),droupDownOptions(this.value),droupDownSubCategoryOptions()" style="height:25px;">
                                                    <html:option value="1">Locomotor</html:option>
                                                    <html:option value="2">Visual</html:option>
                                                    <html:option value="3">Hearing</html:option>
                                                    <html:option value="4">Mental Retardation</html:option>
                                                    <html:option value="5">Mental Illness</html:option>
                                                </html:select>
                                            </td>
                                            <td >Category<font color="red"><b>*</b></font></td>
                                            <td >
                                                <html:select styleId="6" property="reportcategory" onchange="disabileSubCategory(),droupDownSubCategoryOptions()" >
                                                    <html:option value="1">Medical Intervention</html:option>
                                                    <html:option value="2" styleId="assistive">Assistive Devices</html:option>
                                                    <html:option value="3">Other Needs</html:option>
                                                </html:select>
                                            </td>

                                            <td >
                                                <div id="divsubcategory" style="visibility:hidden">
                                                    <table>
                                                        <tr>
                                                            <td>
                                                                SubCategory<font color="red"><b>*</b></font> </td>
                                                            <td>  <html:select styleId="7" property="reportSubcategory"  style="height:25px;">

                                                                </html:select>
                                                            </td>
                                                        </tr>
                                                    </table>

                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td  colspan="2" width="25%">&nbsp;&nbsp;&nbsp;&nbsp; From Date<font color="red"><b>*</b></font>
                                                <html:text property="fromdate" styleId="10" readonly="true" />
                                                <a  href="javascript:show_calendar('forms[0].fromdate');"
                                                    onmouseover="window.status='Date Picker';return true;"
                                                    onmouseout="window.status='';return true;">
                                                    <img src="./DisabilityUITG/images/calendar.png" border="0"></a>
                                            </td>
                                            <td  colspan="4" width="25%">&nbsp;&nbsp;&nbsp;&nbsp; To Date<font color="red"><b>*</b></font>
                                                <html:text property="todate" styleId="8"  readonly="true"  />
                                                <a  href="javascript:show_calendar('forms[0].todate');"
                                                    onmouseover="window.status='Date Picker';return true;"
                                                    onmouseout="window.status='';return true;">
                                                    <img src="./DisabilityUITG/images/calendar.png" border="0"></a>
                                            </td>
                                        </tr>

                                    </table>

                                </td>
                            </tr>
                            <tr>
                                <th  align="center"><input type="button" name="card" value="Submit" class="button" onclick="getOtherReports()"> </th>
                            </tr>
                        </table>
                    </td>
                </tr>
                <script>

                    if(document.forms[0].typeofdisability.value=='1'&&document.forms[0].reportcategory.value=='2')
                    {
                        droupDownSubCategoryOptions();
                        document.getElementById("divsubcategory").style.visibility = "Visible";
                        document.getElementById("divsubcategory").style.display = "Inline";
                    }


                </script>
                <%
                            String msg = (String) request.getAttribute("msg");

                %>
                <% if (msg != null) {%><table align="center"><tr><td><%=msg%></td></tr></table> <% }%>
                <tr>
                    <td align="center" id="printTable">
                       <table align="center">
                            <tr>
                                <td style="text-align: left">
                                     Specific Needs Report
                                </td>
                            </tr>
                        </table>
                         <table align="center">
                            <tr>
                                <td style="text-align: left">
                                     <logic:present name="level">${level}</logic:present>
                                </td>
                            </tr>
                        </table>
                        <table align="center">
                            <tr>
                                <td style="text-align: left">
                                    <logic:present name="back">${back}</logic:present>
                                </td>

                                <td style="text-align: right">  <logic:present name="areadescription"> ${areadescription}</logic:present></td>
                            </tr>
                        </table>


                        <logic:present name="medicalInterventionList" scope="request">

                            <table  align="center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">
                                <tr><th colspan="8">Medical Intervention Report</th></tr>
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
                                        <td  style="text-align: center" class="formbg" ><%=++i%></td>
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
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.xls?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=b3&D=<%=typeOfDisability%>&cName=Physiotherapyforthreeyears&cValue=physiotheraphy&count=<bean:write name="modify" property="physiotherapyBelowThreeYears"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="physiotherapyBelowThreeYears"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.xls?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=a3&D=<%=typeOfDisability%>&cName=Physiotherapy&cValue=physiotherapy&count=<bean:write name="modify" property="physiotherapyAboveThreeYears"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="physiotherapyAboveThreeYears"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.xls?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=b3&D=<%=typeOfDisability%>&cName=OccupationalTherapyforthreeyears&cValue=occupationaltheraphy&count=<bean:write name="modify" property="occupationalTherapyBelowThreeYears"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="occupationalTherapyBelowThreeYears"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.xls?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=a3&D=<%=typeOfDisability%>&cName=OccupationalTherapy&cValue=occupationaltherapy&count=<bean:write name="modify" property="occupationalTherapyAboveThreeYears"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="occupationalTherapyAboveThreeYears"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailSurgery.xls?nexxt=start&back=start&getPersonalDetailsSurgery=getPersonalDetailsSurgery&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=b3&D=<%=typeOfDisability%>&cName=Surgery&count=<bean:write name="modify" property="surgeryOH"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="surgeryOH"/></a></td>
                                        <td class="formbg" style="text-align: center"><bean:write name="modify" property="total"/></td>
                                    </tr>

                                </logic:iterate>
                                <tr>
                                    <th  colspan="2" style="text-align: center">Total</th>
                                    <th  style="text-align: center"><%= phyBelow3Total%></th>
                                    <th  style="text-align: center"><%= phyAbove3Total%></th>
                                    <th  style="text-align: center"><%= otBelow3Total%></th>
                                    <th  style="text-align: center"><%= otAbove3Total%></th>
                                    <th  style="text-align: center"><%= surgeryTotal%></th>
                                    <th  style="text-align: center"><%= total%></th>
                                </tr>
                            </table><br>
                            <table align="center">
                                <tr>
                                    <td>
                                        <a href="./analysisReport.xls?mode=excelWebsiteAnalysis&district_id=${district_id}&mandal_id=${mandal_id}&village_id=${village_id}&typeofdisability=${typeofdisability}&reportcategory=${reportcategory}&reportSubcategory=${reportSubcategory}&fromdate=${fromdate}&todate=${todate}" target="_blank">
                                            Export To Excel <img src="DisabilityUITG/images/excel.jpg"
                                                                 height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;

                                        <a href="./analysisReport.do?mode=printWebsiteAnalysis&district_id=${district_id}&mandal_id=${mandal_id}&village_id=${village_id}&typeofdisability=${typeofdisability}&reportcategory=${reportcategory}&reportSubcategory=${reportSubcategory}&fromdate=${fromdate}&todate=${todate}" target="_blank">
                                            Print<img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a>
                                    </td>
                                </tr>
                            </table>

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
                                        <td  style="text-align: center"  class="formbg" ><%=++i%></td>
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
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=ShoulderProsthesis&cValue=Shoulder Prosthesis&count=<bean:write name="modify" property="shoulder"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="shoulder"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=AboveElbowProsthesis&cValue=Above Elbow  Prosthesis&count=<bean:write name="modify" property="aboveElbow"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="aboveElbow"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=ElbowDisarticulationProsthesis&cValue=Elbow Disarticulation Prosthesis&count=<bean:write name="modify" property="elbowDisarticulation"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="elbowDisarticulation"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=BelowElbowProsthesis&cValue=Below Elbow  Prosthesis&count=<bean:write name="modify" property="belowElbow"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="belowElbow"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=WristDisarticulationProsthesis&cValue=Wrist Disarticulation Prosthesis&count=<bean:write name="modify" property="wristDisarticulation"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="wristDisarticulation"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=HandProsthesis&cValue=Hand Prosthesis&count=<bean:write name="modify" property="hand"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="hand"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=CosmeticFingerProsthesis&cValue=Cosmetic Finger Prosthesis&count=<bean:write name="modify" property="cosmeticFinger"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="cosmeticFinger"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=HipDisarticulationProsthesis&cValue=Hip Disarticulation Prosthesis&count=<bean:write name="modify" property="hipDisarticulation"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="hipDisarticulation"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=AboveKneeProsthesis&cValue=Above Knee Prosthesis&count=<bean:write name="modify" property="aboveKnee"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="aboveKnee"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=KneeDisarticulationProsthesis&cValue=Knee Disarticulation Prosthesis&count=<bean:write name="modify" property="kneeDisarticulation"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="kneeDisarticulation"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=BelowKneeProsthesis&cValue=Below Knee Prosthesis&count=<bean:write name="modify" property="belowKnee"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="belowKnee"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=SymesProsthesis&cValue=Symes Prosthesis&count=<bean:write name="modify" property="symes"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="symes"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=PartialFootProsthesis&cValue=Partial Foot Prosthesis&count=<bean:write name="modify" property="partialFoot"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="partialFoot"/></a></td>
                                        <td class="formbg" style="text-align: center"><bean:write name="modify" property="total"/></td>
                                    </tr>

                                </logic:iterate>
                                <tr>
                                    <td class="formhdbg" colspan="2" style="text-align: center">Total</td>
                                    <td class="formhdbg" style="text-align: center"><b><%=shoulderTotal%></b></td>
                                    <td class="formhdbg" style="text-align: center"><b><%=aboveElbowTotal%></b></td>
                                    <td class="formhdbg" style="text-align: center"><b><%=elbowDisarticulationTotal%></b></td>
                                    <td class="formhdbg" style="text-align: center"><b><%=belowElbowTotal%></b></td>
                                    <td class="formhdbg" style="text-align: center"><b><%=wristDisarticulationTotal%></b></td>
                                    <td class="formhdbg" style="text-align: center"><b><%=handTotal%></b></td>
                                    <td class="formhdbg" style="text-align: center"><b><%=cosmeticFingerTotal%></b></td>
                                    <td class="formhdbg" style="text-align: center"><b><%=hipDisarticulationTotal%></b></td>
                                    <td class="formhdbg" style="text-align: center"><b><%=aboveKneeTotal%></b></td>
                                    <td class="formhdbg" style="text-align: center"><b><%=kneeDisarticulationTotal%></b></td>
                                    <td class="formhdbg" style="text-align: center"><b><%=belowKneeTotal%></b></td>
                                    <td class="formhdbg" style="text-align: center"><b><%=symesTotal%></b></td>
                                    <td class="formhdbg" style="text-align: center"><b><%=partialFootTotal%></b></td>
                                    <td class="formhdbg" style="text-align: center"><%= total%></td>
                                </tr>
                            </table><br>
                            <table align="center">
                                <tr>
                                    <td>
                                        <a href="./analysisReport.xls?mode=excelWebsiteAnalysis&district_id=${district_id}&mandal_id=${mandal_id}&village_id=${village_id}&typeofdisability=${typeofdisability}&reportcategory=${reportcategory}&reportSubcategory=${reportSubcategory}&fromdate=${fromdate}&todate=${todate}" target="_blank">
                                            Export To Excel <img src="DisabilityUITG/images/excel.jpg"
                                                                 height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;

                                        <a href="./analysisReport.do?mode=printWebsiteAnalysis&district_id=${district_id}&mandal_id=${mandal_id}&village_id=${village_id}&typeofdisability=${typeofdisability}&reportcategory=${reportcategory}&reportSubcategory=${reportSubcategory}&fromdate=${fromdate}&todate=${todate}" target="_blank">
                                            Print<img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a>
                                    </td>
                                </tr>
                            </table>
                            <script>
                                document.getElementById("7").value="<%=request.getParameter("reportSubcategory")%>";
                            </script>
                        </logic:present>






                        <logic:present name="orthosisList" scope="request">
                            <p>Assistive Devices Orthosis Report</p>
                            <table  style="text-align: center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">
                                <tr>
                                    <th style="text-align: center" class="formhdbg" rowspan=2>S.No</th>
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
                                        <td  style="text-align: center"  class="formbg" ><%=++i%></td>
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
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=${modify.district_id}&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=AeroplaneSplint&cValue=Aeroplane Splint&count=<bean:write name="modify" property="aeroplaneSplint"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="aeroplaneSplint"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=FigureEightSplint&cValue=Figure-8 Splint&count=<bean:write name="modify" property="figureEightSplint"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="figureEightSplint"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=ForeArmSplint&cValue=ForeArm Splint&count=<bean:write name="modify" property="forearmSplint"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="forearmSplint"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=HandSplint&cValue=Hand Splint&count=<bean:write name="modify" property="handSplint"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="handSplint"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=HKAFO&cValue=HKAFO&count=<bean:write name="modify" property="hkafoh"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="hkafoh"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=KAFO&cValue=KAFO&count=<bean:write name="modify" property="kafo"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="kafo"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=AFO&cValue=AFO&count=<bean:write name="modify" property="afo"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="afo"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=KneeOrthosis&cValue=Knee Orthosis&count=<bean:write name="modify" property="kneeOrthosis"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="kneeOrthosis"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=DBSplint&cValue=DB Splint&count=<bean:write name="modify" property="dbSplint"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="dbSplint"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=ModifiedShoe&cValue=Modified Shoe&count=<bean:write name="modify" property="modifiedShoe"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="modifiedShoe"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=SerialCasting&cValue=Serial Casting&count=<bean:write name="modify" property="serialCastingCTEV"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="serialCastingCTEV"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=CervicalCollar&cValue=Cervical Collar&count=<bean:write name="modify" property="cervicalCollar"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="cervicalCollar"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=LBBrace&cValue=LS Brace&count=<bean:write name="modify" property="lsBrace"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="lsBrace"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=TLSOBrace&cValue=TLSO Brace&count=<bean:write name="modify" property="tlsoBraceForScoliosisKyphosis"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="tlsoBraceForScoliosisKyphosis"/></a></td>
                                        <td class="formbg" style="text-align: center"><bean:write name="modify" property="total"/></td>
                                    </tr>


                                </logic:iterate>
                                <tr>
                                    <td class="formhdbg" colspan="2" style="text-align: center">Total</td>
                                    <td class="formhdbg" style="text-align: center"><b>${aeroplaneSplintTotal}</b></td>
                                    <td class="formhdbg" style="text-align: center"><b><%=figureEightSplintTotal%></b></td>
                                    <td class="formhdbg" style="text-align: center"><b><%=forearmSplintTotal%></b></td>
                                    <td class="formhdbg" style="text-align: center"><b>${handSplintTotal}</b></td>
                                    <td class="formhdbg" style="text-align: center"><b><%=hkafohTotal%></b></td>
                                    <td class="formhdbg" style="text-align: center"><b><%=kafoTotal%></b></td>
                                    <td class="formhdbg" style="text-align: center"><b><%=afoTotal%></b></td>
                                    <td class="formhdbg" style="text-align: center"><b><%=kneeOrthosisTotal%></b></td>
                                    <td class="formhdbg" style="text-align: center"><b><%=dbSplintTotal%></b></td>
                                    <td class="formhdbg" style="text-align: center"><b><%=modifiedShoeTotal%></b></td>
                                    <td class="formhdbg" style="text-align: center"><b><%=serialCastingCTEVTotal%></b></td>
                                    <td class="formhdbg" style="text-align: center"><b><%=cervicalCollarTotal%></b></td>
                                    <td class="formhdbg" style="text-align: center"><b><%=lsBraceTotal%></b></td>
                                    <td class="formhdbg" style="text-align: center"><b><%=tlsoBraceForScoliosisKyphosisTotal%></b></td>
                                    <td class="formhdbg" style="text-align: center"><%= total%></td>
                                </tr>
                            </table><br>
                            <table align="center">
                                <tr>
                                    <td>
                                        <a href="./analysisReport.xls?mode=excelWebsiteAnalysis&district_id=${district_id}&mandal_id=${mandal_id}&village_id=${village_id}&typeofdisability=${typeofdisability}&reportcategory=${reportcategory}&reportSubcategory=${reportSubcategory}&fromdate=${fromdate}&todate=${todate}" target="_blank">
                                            Export To Excel <img src="DisabilityUITG/images/excel.jpg"
                                                                 height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;

                                        <a href="./analysisReport.do?mode=printWebsiteAnalysis&district_id=${district_id}&mandal_id=${mandal_id}&village_id=${village_id}&typeofdisability=${typeofdisability}&reportcategory=${reportcategory}&reportSubcategory=${reportSubcategory}&fromdate=${fromdate}&todate=${todate}" target="_blank">
                                            Print<img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a>
                                    </td>
                                </tr>
                            </table>
                            <script>
                                document.getElementById("7").value="<%=request.getParameter("reportSubcategory")%>";
                            </script>
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
                                        <td  style="text-align: center"  class="formbg" ><%=++i%></td>
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
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=WheelChair&cValue=Small Wheel Chair&count=<bean:write name="modify" property="wheelChairSmall"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="wheelChairSmall"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=WheelChair&cValue=Large Wheel Chair&count=<bean:write name="modify" property="wheelChairLarge"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="wheelChairLarge"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=Tricycle&cValue=Small Tricycle&count=<bean:write name="modify" property="tricycleSmall"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="tricycleSmall"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=Tricycle&cValue=Large Tricycle&count=<bean:write name="modify" property="tricycleLarge"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="tricycleLarge"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=WalkingFrame&cValue=Small WalkingFrame&count=<bean:write name="modify" property="walkingFrameWalkerSmall"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="walkingFrameWalkerSmall"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=WalkingFrame&cValue=Large WalkingFrame&count=<bean:write name="modify" property="walkingFrameWalkerLarge"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="walkingFrameWalkerLarge"/></a></td>
                                        <td class="formbg" style="text-align: center"><bean:write name="modify" property="total"/></td>
                                    </tr>

                                </logic:iterate>
                                <tr>
                                    <td class="formhdbg" colspan="2" style="text-align: center">Total</td>
                                    <td class="formhdbg" style="text-align: center"><%=wheelChairSmallTotal%></td>
                                    <td class="formhdbg" style="text-align: center"><%=wheelChairLargeTotal%></td>
                                    <td class="formhdbg" style="text-align: center"><%=tricycleSmallTotal%></td>
                                    <td class="formhdbg" style="text-align: center"><%=tricycleLargeTotal%></td>
                                    <td class="formhdbg" style="text-align: center"><%=walkingFrameWalkerSmallTotal%></td>
                                    <td class="formhdbg" style="text-align: center"><%=walkingFrameWalkerLargeTotal%></td>
                                    <td class="formhdbg" style="text-align: center"><%= total%></td>
                                </tr>
                            </table><br>
                            <table align="center">
                                <tr>
                                    <td>
                                        <a href="./analysisReport.xls?mode=excelWebsiteAnalysis&district_id=${district_id}&mandal_id=${mandal_id}&village_id=${village_id}&typeofdisability=${typeofdisability}&reportcategory=${reportcategory}&reportSubcategory=${reportSubcategory}&fromdate=${fromdate}&todate=${todate}" target="_blank">
                                            Export To Excel <img src="DisabilityUITG/images/excel.jpg"
                                                                 height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;

                                        <a href="./analysisReport.do?mode=printWebsiteAnalysis&district_id=${district_id}&mandal_id=${mandal_id}&village_id=${village_id}&typeofdisability=${typeofdisability}&reportcategory=${reportcategory}&reportSubcategory=${reportSubcategory}&fromdate=${fromdate}&todate=${todate}" target="_blank">
                                            Print<img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a>
                                    </td>
                                </tr>
                            </table>
                            <script>
                                document.getElementById("7").value="<%=request.getParameter("reportSubcategory")%>";
                            </script>
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
                                        <td  style="text-align: center"  class="formbg" ><%=++i%></td>
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
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=WalkingStick&cValue=Small Walking Stick&count=<bean:write name="modify" property="walkingStickSmall"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="walkingStickSmall"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=WalkingStick&cValue=Large Walking Stick&count=<bean:write name="modify" property="walkingStickLarge"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="walkingStickLarge"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetails.do?nexxt=start&back=start&getPersonalDetailsTwoColumns=getPersonalDetailsTwoColumns&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=CrutchesSubType&cValue=Axillary&cn2=CrutchesType&cv2=Small Crutches&count=<bean:write name="modify" property="axillarySmall"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="axillarySmall"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetails.do?nexxt=start&back=start&getPersonalDetailsTwoColumns=getPersonalDetailsTwoColumns&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=CrutchesSubType&cValue=Axillary&cn2=CrutchesType&cv2=Medium Crutches&count=<bean:write name="modify" property="axillaryMedium"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="axillaryMedium"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetails.do?nexxt=start&back=start&getPersonalDetailsTwoColumns=getPersonalDetailsTwoColumns&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=CrutchesSubType&cValue=Axillary&cn2=CrutchesType&cv2=Large Crutches&count=<bean:write name="modify" property="axillaryLarge"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="axillaryLarge"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetails.do?nexxt=start&back=start&getPersonalDetailsTwoColumns=getPersonalDetailsTwoColumns&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=CrutchesSubType&cValue=Axillary&cn2=CrutchesType&cv2=Extra Large Crutches&count=<bean:write name="modify" property="axillaryExtraLarge"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="axillaryExtraLarge"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetails.do?nexxt=start&back=start&getPersonalDetailsTwoColumns=getPersonalDetailsTwoColumns&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=CrutchesSubType&cValue=Elbow&cn2=CrutchesType&cv2=Small Crutches&count=<bean:write name="modify" property="elbowSmall"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="elbowSmall"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetails.do?nexxt=start&back=start&getPersonalDetailsTwoColumns=getPersonalDetailsTwoColumns&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=CrutchesSubType&cValue=Elbow&cn2=CrutchesType&cv2=Medium Crutches&count=<bean:write name="modify" property="elbowMedium"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="elbowMedium"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetails.do?nexxt=start&back=start&getPersonalDetailsTwoColumns=getPersonalDetailsTwoColumns&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=CrutchesSubType&cValue=Elbow&cn2=CrutchesType&cv2=Large Crutches&count=<bean:write name="modify" property="elbowLarge"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="elbowLarge"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetails.do?nexxt=start&back=start&getPersonalDetailsTwoColumns=getPersonalDetailsTwoColumns&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=CrutchesSubType&cValue=Elbow&cn2=CrutchesType&cv2=Extra Large Crutches&count=<bean:write name="modify" property="elbowExtraLarge"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="elbowExtraLarge"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetails.do?nexxt=start&back=start&getPersonalDetailsTwoColumns=getPersonalDetailsTwoColumns&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=CrutchesSubType&cValue=Gutter&cn2=CrutchesType&cv2=Small Crutches&count=<bean:write name="modify" property="gutterSmall"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="gutterSmall"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetails.do?nexxt=start&back=start&getPersonalDetailsTwoColumns=getPersonalDetailsTwoColumns&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=CrutchesSubType&cValue=Gutter&cn2=CrutchesType&cv2=Medium Crutches&count=<bean:write name="modify" property="gutterMedium"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="gutterMedium"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetails.do?nexxt=start&back=start&getPersonalDetailsTwoColumns=getPersonalDetailsTwoColumns&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=CrutchesSubType&cValue=Gutter&cn2=CrutchesType&cv2=Large Crutches&count=<bean:write name="modify" property="gutterLarge"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="gutterLarge"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetails.do?nexxt=start&back=start&getPersonalDetailsTwoColumns=getPersonalDetailsTwoColumns&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=CrutchesSubType&cValue=Gutter&cn2=CrutchesType&cv2=Extra Large Crutches&count=<bean:write name="modify" property="gutterExtraLarge"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="gutterExtraLarge"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetails.do?nexxt=start&back=start&getPersonalDetailsTwoColumns=getPersonalDetailsTwoColumns&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=CrutchesSubType&cValue=Tripod&cn2=CrutchesType&cv2=Small Crutches&count=<bean:write name="modify" property="tripodSmall"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="tripodSmall"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetails.do?nexxt=start&back=start&getPersonalDetailsTwoColumns=getPersonalDetailsTwoColumns&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=CrutchesSubType&cValue=Tripod&cn2=CrutchesType&cv2=Medium Crutches&count=<bean:write name="modify" property="tripodMedium"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="tripodMedium"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetails.do?nexxt=start&back=start&getPersonalDetailsTwoColumns=getPersonalDetailsTwoColumns&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=CrutchesSubType&cValue=Tripod&cn2=CrutchesType&cv2=Large Crutches&count=<bean:write name="modify" property="tripodLarge"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="tripodLarge"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetails.do?nexxt=start&back=start&getPersonalDetailsTwoColumns=getPersonalDetailsTwoColumns&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=CrutchesSubType&cValue=Tripod&cn2=CrutchesType&cv2=Extra Large Crutches&count=<bean:write name="modify" property="tripodExtraLarge"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="tripodExtraLarge"/></a></td>
                                        <td class="formbg" style="text-align: center"><bean:write name="modify" property="total"/></td>
                                    </tr>

                                </logic:iterate>
                                <tr>
                                    <td class="formhdbg" colspan="2" style="text-align: center">Total</td>
                                    <td class="formhdbg" style="text-align: center"><b><%=walkingStickSmallTotal%></b></td>
                                    <td class="formhdbg" style="text-align: center"><b><%=walkingStickLargeTotal%></b></td>
                                    <td class="formhdbg" style="text-align: center"><b><%=axillarySmallTotal%></b></td>
                                    <td class="formhdbg" style="text-align: center"><b><%=axillaryMediumTotal%></b></td>
                                    <td class="formhdbg" style="text-align: center"><b><%=axillaryLargeTotal%></b></td>
                                    <td class="formhdbg" style="text-align: center"><b><%=axillaryExtraLargeTotal%></b></td>
                                    <td class="formhdbg" style="text-align: center"><b><%=elbowSmallTotal%></b></td>
                                    <td class="formhdbg" style="text-align: center"><b><%=elbowMediumTotal%></b></td>
                                    <td class="formhdbg" style="text-align: center"><b><%=elbowLargeTotal%></b></td>
                                    <td class="formhdbg" style="text-align: center"><b><%=elbowExtraLargeTotal%></b></td>
                                    <td class="formhdbg" style="text-align: center"><b><%=gutterSmallTotal%></b></td>
                                    <td class="formhdbg" style="text-align: center"><b><%=gutterMediumTotal%></b></td>
                                    <td class="formhdbg" style="text-align: center"><b><%=gutterLargeTotal%></b></td>
                                    <td class="formhdbg" style="text-align: center"><b><%=gutterExtraLargeTotal%></b></td>
                                    <td class="formhdbg" style="text-align: center"><b><%=tripodSmallTotal%></b></td>
                                    <td class="formhdbg" style="text-align: center"><b><%=tripodMediumTotal%></b></td>
                                    <td class="formhdbg" style="text-align: center"><b><%=tripodLargeTotal%></b></td>
                                    <td class="formhdbg" style="text-align: center"><b><%=tripodExtraLargeTotal%></b></td>
                                    <td class="formhdbg" style="text-align: center"><%= total%></td>
                                </tr>
                            </table><br>
                            <table align="center">
                                <tr>
                                    <td>
                                        <a href="./analysisReport.xls?mode=excelWebsiteAnalysis&district_id=${district_id}&mandal_id=${mandal_id}&village_id=${village_id}&typeofdisability=${typeofdisability}&reportcategory=${reportcategory}&reportSubcategory=${reportSubcategory}&fromdate=${fromdate}&todate=${todate}" target="_blank">
                                            Export To Excel <img src="DisabilityUITG/images/excel.jpg"
                                                                 height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;

                                        <a href="./analysisReport.do?mode=printWebsiteAnalysis&district_id=${district_id}&mandal_id=${mandal_id}&village_id=${village_id}&typeofdisability=${typeofdisability}&reportcategory=${reportcategory}&reportSubcategory=${reportSubcategory}&fromdate=${fromdate}&todate=${todate}" target="_blank">
                                            Print<img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a>
                                    </td>
                                </tr>
                            </table>
                            <script>
                                document.getElementById("7").value="<%=request.getParameter("reportSubcategory")%>";
                            </script>
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
                                        <td  style="text-align: center"  class="formbg" ><%=++i%></td>
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
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=Feeding&cValue=Feeding&count=<bean:write name="modify" property="feeding"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="feeding"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=Toileting&cValue=Toileting/Bathing&count=<bean:write name="modify" property="toiletingBathing"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="toiletingBathing"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=Brushing&cValue=Brushing&count=<bean:write name="modify" property="brushing"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="brushing"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=Combing&cValue=Combing&count=<bean:write name="modify" property="combing"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="combing"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=Dressing&cValue=Dressing&count=<bean:write name="modify" property="dressing"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="dressing"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=Writing&cValue=Writing&count=<bean:write name="modify" property="writing"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="writing"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=Driving&cValue=Driving/Cycling&count=<bean:write name="modify" property="drivingCycling"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="drivingCycling"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=BedTransfer&cValue=Bed Transfer&count=<bean:write name="modify" property="bedTransfer"/>&c=<%=category%>&s=<%=subCategory%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="bedTransfer"/></a></td>
                                        <td class="formbg" style="text-align: center"><bean:write name="modify" property="total"/></td>
                                    </tr>

                                </logic:iterate>
                                <tr>
                                    <td class="formhdbg" colspan="2" style="text-align: center">Total</td>
                                    <td class="formhdbg" style="text-align: center"><%=feedingTotal%></td>
                                    <td class="formhdbg" style="text-align: center"><%=toiletingBathingTotal%></td>
                                    <td class="formhdbg" style="text-align: center"><%=brushingTotal%></td>
                                    <td class="formhdbg" style="text-align: center"><%=combingTotal%></td>
                                    <td class="formhdbg" style="text-align: center"><%=dressingTotal%></td>
                                    <td class="formhdbg" style="text-align: center"><%=writingTotal%></td>
                                    <td class="formhdbg" style="text-align: center"><%=drivingCyclingTotal%></td>
                                    <td class="formhdbg" style="text-align: center"><%=bedTransferTotal%></td>
                                    <td class="formhdbg" style="text-align: center"><%= total%></td>
                                </tr>
                            </table><br>
                            <table align="center">
                                <tr>
                                    <td>
                                        <a href="./analysisReport.xls?mode=excelWebsiteAnalysis&district_id=${district_id}&mandal_id=${mandal_id}&village_id=${village_id}&typeofdisability=${typeofdisability}&reportcategory=${reportcategory}&reportSubcategory=${reportSubcategory}&fromdate=${fromdate}&todate=${todate}" target="_blank">
                                            Export To Excel <img src="DisabilityUITG/images/excel.jpg"
                                                                 height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;

                                        <a href="./analysisReport.do?mode=printWebsiteAnalysis&district_id=${district_id}&mandal_id=${mandal_id}&village_id=${village_id}&typeofdisability=${typeofdisability}&reportcategory=${reportcategory}&reportSubcategory=${reportSubcategory}&fromdate=${fromdate}&todate=${todate}" target="_blank">
                                            Print<img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a>
                                    </td>
                                </tr>
                            </table>
                            <script>
                                document.getElementById("7").value="<%=request.getParameter("reportSubcategory")%>";
                            </script>
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
                                        <td  style="text-align: center"  class="formbg" ><%=++i%></td>
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
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailSurgery.xls?nexxt=start&back=start&getPersonalDetailsSurgery=getPersonalDetailsSurgery&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=b3&D=<%=typeOfDisability%>&cName=AnyOther&count=<bean:write name="modify" property="otherNeeds"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="otherNeeds"/></a></td>
                                    </tr>

                                </logic:iterate>
                                <tr>
                                    <th class="formhdbg" colspan="2" style="text-align: center">Total</th>
                                    <th class="formhdbg" style="text-align: center"><%=otherNeedsTotal%></th>

                                </tr>
                            </table><br>
                            <table align="center">
                                <tr>
                                    <td>
                                        <a href="./analysisReport.xls?mode=excelWebsiteAnalysis&district_id=${district_id}&mandal_id=${mandal_id}&village_id=${village_id}&typeofdisability=${typeofdisability}&reportcategory=${reportcategory}&reportSubcategory=${reportSubcategory}&fromdate=${fromdate}&todate=${todate}" target="_blank">
                                            Export To Excel <img src="DisabilityUITG/images/excel.jpg"
                                                                 height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;

                                        <a href="./analysisReport.do?mode=printWebsiteAnalysis&district_id=${district_id}&mandal_id=${mandal_id}&village_id=${village_id}&typeofdisability=${typeofdisability}&reportcategory=${reportcategory}&reportSubcategory=${reportSubcategory}&fromdate=${fromdate}&todate=${todate}" target="_blank">
                                            Print<img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a>
                                    </td>
                                </tr>
                            </table>
                            <script>
                                document.getElementById("7").value="<%=request.getParameter("reportSubcategory")%>";
                            </script>
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
                                        <td  style="text-align: center"  class="formbg" ><%=++i%></td>
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
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailSurgery.xls?nexxt=start&back=start&getPersonalDetailsSurgery=getPersonalDetailsSurgery&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=b3&D=<%=typeOfDisability%>&cName=Surgery&count=<bean:write name="modify" property="sugeryVI"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="sugeryVI"/></a></td>
                                    </tr>

                                </logic:iterate>
                                <tr>
                                    <th class="formhdbg" colspan="2" style="text-align: center">Total</th>
                                    <th class="formhdbg" style="text-align: center"><%=sergeryVITotal%></th>

                                </tr>
                            </table><br>
                            <table align="center">
                                <tr>
                                    <td>
                                        <a href="./analysisReport.xls?mode=excelWebsiteAnalysis&district_id=${district_id}&mandal_id=${mandal_id}&village_id=${village_id}&typeofdisability=${typeofdisability}&reportcategory=${reportcategory}&reportSubcategory=${reportSubcategory}&fromdate=${fromdate}&todate=${todate}" target="_blank">
                                            Export To Excel <img src="DisabilityUITG/images/excel.jpg"
                                                                 height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;

                                        <a href="./analysisReport.do?mode=printWebsiteAnalysis&district_id=${district_id}&mandal_id=${mandal_id}&village_id=${village_id}&typeofdisability=${typeofdisability}&reportcategory=${reportcategory}&reportSubcategory=${reportSubcategory}&fromdate=${fromdate}&todate=${todate}" target="_blank">
                                            Print<img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a>
                                    </td>
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
                                        <td  style="text-align: center"  class="formbg" ><%=++i%></td>
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
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=WhiteCane&cValue=White Cane/Blind Stick&count=<bean:write name="modify" property="whiteCaneBlindStick"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="whiteCaneBlindStick"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=BrailleEquipments&cValue=Braille Equipments&count=<bean:write name="modify" property="brailleEquipments"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="brailleEquipments"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=ArithmeticFrames&cValue=Arithmetic Frames/Abacus&count=<bean:write name="modify" property="arithmeticFramesAbacus"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="arithmeticFramesAbacus"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=LowVisionAids&cValue=Low Vision Aids&count=<bean:write name="modify" property="lowVisionAidsSpectaclesMagnifiers"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="lowVisionAidsSpectaclesMagnifiers"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=SpeechSynthesizer&cValue=Speech Synthesizer&count=<bean:write name="modify" property="speechSynthesizer"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="speechSynthesizer"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=ShortHand&cValue=Braille Short Hand Machines&count=<bean:write name="modify" property="brailleShortHandMachinesWriters"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="brailleShortHandMachinesWriters"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=TalkingWatch&cValue=Talking Watch/Calculator&count=<bean:write name="modify" property="talkingWatchCalculator"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="talkingWatchCalculator"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=AnyADL&cValue=Any ADL Equipment&count=<bean:write name="modify" property="viADLEquipment"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="viADLEquipment"/></a></td>
                                        <td class="formbg" style="text-align: center"><bean:write name="modify" property="total"/></td>
                                    </tr>

                                </logic:iterate>
                                <tr>
                                    <th class="formhdbg" colspan="2" style="text-align: center">Total</th>
                                    <th class="formhdbg" style="text-align: center"><%=whiteCaneBlindStickTotal%></th>
                                    <th class="formhdbg" style="text-align: center"><%=brailleEquipmentsTotal%></th>
                                    <th class="formhdbg" style="text-align: center"><%=arithmeticFramesAbacusTotal%></th>
                                    <th class="formhdbg" style="text-align: center"><%=lowVisionAidsSpectaclesMagnifiersTotal%></th>
                                    <th class="formhdbg" style="text-align: center"><%=speechSynthesizerTotal%></th>
                                    <th class="formhdbg" style="text-align: center"><%=brailleShortHandMachinesWritersTotal%></th>
                                    <th class="formhdbg" style="text-align: center"><%=talkingWatchCalculatorTotal%></th>
                                    <th class="formhdbg" style="text-align: center"><%=viADLEquipmentTotal%></th>
                                    <th class="formhdbg" style="text-align: center"><%= total%></th>
                                </tr>
                            </table><br>
                            <table align="center">
                                <tr>
                                    <td>
                                        <a href="./analysisReport.xls?mode=excelWebsiteAnalysis&district_id=${district_id}&mandal_id=${mandal_id}&village_id=${village_id}&typeofdisability=${typeofdisability}&reportcategory=${reportcategory}&reportSubcategory=${reportSubcategory}&fromdate=${fromdate}&todate=${todate}" target="_blank">
                                            Export To Excel <img src="DisabilityUITG/images/excel.jpg"
                                                                 height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;

                                        <a href="./analysisReport.do?mode=printWebsiteAnalysis&district_id=${district_id}&mandal_id=${mandal_id}&village_id=${village_id}&typeofdisability=${typeofdisability}&reportcategory=${reportcategory}&reportSubcategory=${reportSubcategory}&fromdate=${fromdate}&todate=${todate}" target="_blank">
                                            Print<img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a>
                                    </td>
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
                                        <td  style="text-align: center"  class="formbg" ><%=++i%></td>
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
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailSurgery.xls?nexxt=start&back=start&getPersonalDetailsSurgery=getPersonalDetailsSurgery&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=b3&D=<%=typeOfDisability%>&cName=AnyOther&count=<bean:write name="modify" property="otherNeedsVI"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="otherNeedsVI"/></a></td>
                                    </tr>

                                </logic:iterate>
                                <tr>
                                    <th class="formhdbg" colspan="2" style="text-align: center">Total</th>
                                    <th class="formhdbg" style="text-align: center"><%=otherNeedsVITotal%></th>

                                </tr>
                            </table><br>

                            <a href="./analysisReport.xls?mode=excelWebsiteAnalysis&district_id=${district_id}&mandal_id=${mandal_id}&village_id=${village_id}&typeofdisability=${typeofdisability}&reportcategory=${reportcategory}&reportSubcategory=${reportSubcategory}&fromdate=${fromdate}&todate=${todate}" target="_blank">
                                Export To Excel <img src="DisabilityUITG/images/excel.jpg"
                                                     height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;

                            <a href="./analysisReport.do?mode=printWebsiteAnalysis&district_id=${district_id}&mandal_id=${mandal_id}&village_id=${village_id}&typeofdisability=${typeofdisability}&reportcategory=${reportcategory}&reportSubcategory=${reportSubcategory}&fromdate=${fromdate}&todate=${todate}" target="_blank">
                                Print<img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a>

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
                                        <td  style="text-align: center"  class="formbg" ><%=++i%></td>
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
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=SpeechTherapyforthreeyears&cValue= EarlyIntervention Speech Therapy&count=<bean:write name="modify" property="speechTherapyBelowThreeYearsHI"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="speechTherapyBelowThreeYearsHI"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=SpeechTherapy&cValue=Speech Therapy&count=<bean:write name="modify" property="speechTherapyAboveThreeYearsHI"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="speechTherapyAboveThreeYearsHI"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=LanguageDevelopment&cValue=Language Development&count=<bean:write name="modify" property="languageDevelopment"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="languageDevelopment"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailSurgery.xls?nexxt=start&back=start&getPersonalDetailsSurgery=getPersonalDetailsSurgery&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=b3&D=<%=typeOfDisability%>&cName=Surgerey&count=<bean:write name="modify" property="surgeryHI"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="surgeryHI"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=CochlearImplantation&cValue=Cochlear Implantation&count=<bean:write name="modify" property="cochlearImplantation"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="cochlearImplantation"/></a></td>
                                        <td class="formbg" style="text-align: center"><bean:write name="modify" property="total"/></td>
                                    </tr>

                                </logic:iterate>
                                <tr>
                                    <td class="formhdbg" colspan="2" style="text-align: center">Total</td>
                                    <td class="formhdbg" style="text-align: center"><%=speechTherapyBelowThreeYearsHITotal%></td>
                                    <td class="formhdbg" style="text-align: center"><%=speechTherapyAboveThreeYearsHITotal%></td>
                                    <td class="formhdbg" style="text-align: center"><%=languageDevelopmentTotal%></td>
                                    <td class="formhdbg" style="text-align: center"><%=surgeryHITotal%></td>
                                    <td class="formhdbg" style="text-align: center"><%=cochlearImplantationTotal%></td>
                                    <td class="formhdbg" style="text-align: center"><%= total%></td>
                                </tr>
                            </table><br>

                            <a href="./analysisReport.xls?mode=excelWebsiteAnalysis&district_id=${district_id}&mandal_id=${mandal_id}&village_id=${village_id}&typeofdisability=${typeofdisability}&reportcategory=${reportcategory}&reportSubcategory=${reportSubcategory}&fromdate=${fromdate}&todate=${todate}" target="_blank">
                                Export To Excel <img src="DisabilityUITG/images/excel.jpg"
                                                     height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;

                            <a href="./analysisReport.do?mode=printWebsiteAnalysis&district_id=${district_id}&mandal_id=${mandal_id}&village_id=${village_id}&typeofdisability=${typeofdisability}&reportcategory=${reportcategory}&reportSubcategory=${reportSubcategory}&fromdate=${fromdate}&todate=${todate}" target="_blank">
                                Print<img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a>

                        </logic:present>

                        <logic:present name="assistiveDevicesHIList" scope="request">
                            <p>Assistive Devices HI Report</p>
                            <table  style="text-align: center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">
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
                                        <td  style="text-align: center"  class="formbg" ><%=++i%></td>
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
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetails.do?nexxt=start&back=start&getPersonalDetailsTwoColumns=getPersonalDetailsTwoColumns&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=HearingAidType&cValue=Low Intensity&cn2=HearingAidSubType&cv2=S-Cord&count=<bean:write name="modify" property="lowIntensitySCord"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="lowIntensitySCord"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetails.do?nexxt=start&back=start&getPersonalDetailsTwoColumns=getPersonalDetailsTwoColumns&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=HearingAidType&cValue=Low Intensity&cn2=HearingAidSubType&cv2=V-Cord&count=<bean:write name="modify" property="lowIntensityVCord"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="lowIntensityVCord"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetails.do?nexxt=start&back=start&getPersonalDetailsTwoColumns=getPersonalDetailsTwoColumns&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=HearingAidType&cValue=Moderate Intensity&cn2=HearingAidSubType&cv2=S-Cord&count=<bean:write name="modify" property="moderateIntensitySCord"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="moderateIntensitySCord"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetails.do?nexxt=start&back=start&getPersonalDetailsTwoColumns=getPersonalDetailsTwoColumns&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=HearingAidType&cValue=Moderate Intensity&cn2=HearingAidSubType&cv2=V-Cord&count=<bean:write name="modify" property="moderateIntensityVCord"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="moderateIntensityVCord"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetails.do?nexxt=start&back=start&getPersonalDetailsTwoColumns=getPersonalDetailsTwoColumns&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=HearingAidType&cValue=High Intensity&cn2=HearingAidSubType&cv2=S-Cord&count=<bean:write name="modify" property="highIntensitySCord"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="highIntensitySCord"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetails.do?nexxt=start&back=start&getPersonalDetailsTwoColumns=getPersonalDetailsTwoColumns&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=HearingAidType&cValue=High Intensity&cn2=HearingAidSubType&cv2=V-Cord&count=<bean:write name="modify" property="highIntensityVCord"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="highIntensityVCord"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=ImplantableHearingAid&cValue=Implantable Hearing Aid&count=<bean:write name="modify" property="implantableHearingAid"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="implantableHearingAid"/></a></td>
                                        <td class="formbg" style="text-align: center"><bean:write name="modify" property="total"/></td>
                                    </tr>

                                </logic:iterate>
                                <tr>
                                    <th class="formhdbg" colspan="2" style="text-align: center">Total</th>
                                    <th class="formhdbg" style="text-align: center"><%=lowIntensitySCordTotal%></th>
                                    <th class="formhdbg" style="text-align: center"><%=lowIntensityVCordTotal%></th>
                                    <th class="formhdbg" style="text-align: center"><%=moderateIntensitySCordTotal%></th>
                                    <th class="formhdbg" style="text-align: center"><%=moderateIntensityVCordTotal%></th>
                                    <th class="formhdbg" style="text-align: center"><%=highIntensitySCordTotal%></th>
                                    <th class="formhdbg" style="text-align: center"><%=highIntensityVCordTotal%></th>
                                    <th class="formhdbg" style="text-align: center"><%=implantableHearingAidTotal%></th>
                                    <th class="formhdbg" style="text-align: center"><%= total%></th>
                                </tr>
                            </table><br>

                            <a href="./analysisReport.xls?mode=excelWebsiteAnalysis&district_id=${district_id}&mandal_id=${mandal_id}&village_id=${village_id}&typeofdisability=${typeofdisability}&reportcategory=${reportcategory}&reportSubcategory=${reportSubcategory}&fromdate=${fromdate}&todate=${todate}" target="_blank">
                                Export To Excel <img src="DisabilityUITG/images/excel.jpg"
                                                     height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;

                            <a href="./analysisReport.do?mode=printWebsiteAnalysis&district_id=${district_id}&mandal_id=${mandal_id}&village_id=${village_id}&typeofdisability=${typeofdisability}&reportcategory=${reportcategory}&reportSubcategory=${reportSubcategory}&fromdate=${fromdate}&todate=${todate}" target="_blank">
                                Print<img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a>

                        </logic:present>

                        <logic:present name="otherNeedsHIList" scope="request">

                            <p>Other Needs HI Report</p>
                            <table  style="text-align: center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">

                                <tr>
                                    <th style="text-align: center" class="formhdbg">S.No</th>
                                    <th  ><logic:present name="regionHeading" >${regionHeading}</logic:present></th>
                                    <th style="text-align: center" class="formhdbg">Other Needs</th>
                                </tr>
                                <logic:iterate id="modify" name="otherNeedsHIList" scope="request">
                                    <tr>
                                        <td  style="text-align: center"  class="formbg" ><%=++i%></td>
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
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailSurgery.xls?nexxt=start&back=start&getPersonalDetailsSurgery=getPersonalDetailsSurgery&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=b3&D=<%=typeOfDisability%>&cName=AnyOther&count=<bean:write name="modify" property="otherNeedsHI"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="otherNeedsHI"/></a></td>
                                    </tr>

                                </logic:iterate>
                                <tr>
                                    <th class="formhdbg" colspan="2" style="text-align: center">Total</th>
                                    <th class="formhdbg" style="text-align: center"><%=otherNeedsHITotal%></th>

                                </tr>
                            </table><br>

                            <a href="./analysisReport.xls?mode=excelWebsiteAnalysis&district_id=${district_id}&mandal_id=${mandal_id}&village_id=${village_id}&typeofdisability=${typeofdisability}&reportcategory=${reportcategory}&reportSubcategory=${reportSubcategory}&fromdate=${fromdate}&todate=${todate}" target="_blank">
                                Export To Excel <img src="DisabilityUITG/images/excel.jpg"
                                                     height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;

                            <a href="./analysisReport.do?mode=printWebsiteAnalysis&district_id=${district_id}&mandal_id=${mandal_id}&village_id=${village_id}&typeofdisability=${typeofdisability}&reportcategory=${reportcategory}&reportSubcategory=${reportSubcategory}&fromdate=${fromdate}&todate=${todate}" target="_blank">
                                Print<img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a>

                        </logic:present>



                        <logic:present name="medicalInterventionMRList" scope="request">
                            <p>Medical Intervention MR Report</p>

                            <table  style="text-align: center" cellspacing="1" border="0" cellpadding="0" class="inputform" width="90%">
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
                                        <td  style="text-align: center"  class="formbg" ><%=++i%></td>
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
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=b3&D=<%=typeOfDisability%>&cName=SpeechTherapyforthreeyears&cValue= EarlyIntervention Speech Therapy&count=<bean:write name="modify" property="speechTherapyBelowThreeYearsMR"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="speechTherapyBelowThreeYearsMR"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=a3&D=<%=typeOfDisability%>&cName=SpeechTherapy&cValue=Speech Therapy&count=<bean:write name="modify" property="speechTherapyAboveThreeYearsMR"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="speechTherapyAboveThreeYearsMR"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=b3&D=<%=typeOfDisability%>&cName=BehaviourModificationforthreeyears&cValue=Behavior Modification&count=<bean:write name="modify" property="behaviourModificationPsychotherapyBelow"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="behaviourModificationPsychotherapyBelow"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=a3&D=<%=typeOfDisability%>&cName=PsycotherapyBehaviour&cValue=Psycotherapy/Behaviour Modification&count=<bean:write name="modify" property="behaviourModificationPsychotherapyAbove"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="behaviourModificationPsychotherapyAbove"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=SensoryIntegrationCognitive&cValue=SensoryIntegrationcognitive&count=<bean:write name="modify" property="sensoryIntegrationCognitiveDevelopment"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="sensoryIntegrationCognitiveDevelopment"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=CognitiveBehaviourTherapy&cValue=CognitiveBehaviourTherapy&count=<bean:write name="modify" property="cognitiveBehaviourTherapy"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="cognitiveBehaviourTherapy"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=ParentFamilyIntervention&cValue=ParientFamilyintervention&count=<bean:write name="modify" property="parentFamilyIntervention"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="parentFamilyIntervention"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=Physiotherapy&cValue=Physiotherapy&count=<bean:write name="modify" property="physiotherapyMR"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="physiotherapyMR"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=OccupationalTherapy&cValue=OccupationalTherapy&count=<bean:write name="modify" property="occupationalTherapyMR"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="occupationalTherapyMR"/></a></td>
                                        <td class="formbg" style="text-align: center"><bean:write name="modify" property="total"/></td>
                                    </tr>

                                </logic:iterate>
                                <tr>
                                    <th class="formhdbg" colspan="2" style="text-align: center">Total</th>
                                    <th class="formhdbg" style="text-align: center"><%=speechTherapyBelowThreeYearsMRTotal%></th>
                                    <th class="formhdbg" style="text-align: center"><%=speechTherapyAboveThreeYearsMRTotal%></th>
                                    <th class="formhdbg" style="text-align: center"><%=behaviourModificationPsychotherapyBelowTotal%></th>
                                    <th class="formhdbg" style="text-align: center"><%=behaviourModificationPsychotherapyAboveTotal%></th>
                                    <th class="formhdbg" style="text-align: center"><%=sensoryIntegrationCognitiveDevelopmentTotal%></th>
                                    <th class="formhdbg" style="text-align: center"><%=cognitiveBehaviourTherapyTotal%></th>
                                    <th class="formhdbg" style="text-align: center"><%=parentFamilyInterventionTotal%></th>
                                    <th class="formhdbg" style="text-align: center"><%=physiotherapyMRTotal%></th>
                                    <th class="formhdbg" style="text-align: center"><%=occupationalTherapyMRTotal%></th>
                                    <th class="formhdbg" style="text-align: center"><%= total%></th>
                                </tr>
                            </table><br>

                            <a href="./analysisReport.xls?mode=excelWebsiteAnalysis&district_id=${district_id}&mandal_id=${mandal_id}&village_id=${village_id}&typeofdisability=${typeofdisability}&reportcategory=${reportcategory}&reportSubcategory=${reportSubcategory}&fromdate=${fromdate}&todate=${todate}" target="_blank">
                                Export To Excel <img src="DisabilityUITG/images/excel.jpg"
                                                     height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;

                            <a href="./analysisReport.do?mode=printWebsiteAnalysis&district_id=${district_id}&mandal_id=${mandal_id}&village_id=${village_id}&typeofdisability=${typeofdisability}&reportcategory=${reportcategory}&reportSubcategory=${reportSubcategory}&fromdate=${fromdate}&todate=${todate}" target="_blank">
                                Print<img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a>

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
                                        <td  style="text-align: center"  class="formbg" ><%=++i%></td>
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

                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=LearningMaterial&cValue=Learning Materials For M.R&count=<bean:write name="modify" property="learningMaterials"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="learningMaterials"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=SpecialSoftware&cValue=Special Software&count=<bean:write name="modify" property="specialSoftware"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="specialSoftware"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=Toys&cValue=Toys&count=<bean:write name="modify" property="toys"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="toys"/></a></td>
                                        <td class="formbg" style="text-align: center"><bean:write name="modify" property="total"/></td>
                                    </tr>

                                </logic:iterate>
                                <tr>
                                    <th class="formhdbg" colspan="2" style="text-align: center">Total</th>
                                    <th class="formhdbg" style="text-align: center"><%=learningMaterialsTotal%></th>
                                    <th class="formhdbg" style="text-align: center"><%=specialSoftwareTotal%></th>
                                    <th class="formhdbg" style="text-align: center"><%=toysTotal%></th>
                                    <th class="formhdbg" style="text-align: center"><%= total%></th>
                                </tr>
                            </table><br>

                            <a href="./analysisReport.xls?mode=excelWebsiteAnalysis&district_id=${district_id}&mandal_id=${mandal_id}&village_id=${village_id}&typeofdisability=${typeofdisability}&reportcategory=${reportcategory}&reportSubcategory=${reportSubcategory}&fromdate=${fromdate}&todate=${todate}" target="_blank">
                                Export To Excel <img src="DisabilityUITG/images/excel.jpg"
                                                     height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;

                            <a href="./analysisReport.do?mode=printWebsiteAnalysis&district_id=${district_id}&mandal_id=${mandal_id}&village_id=${village_id}&typeofdisability=${typeofdisability}&reportcategory=${reportcategory}&reportSubcategory=${reportSubcategory}&fromdate=${fromdate}&todate=${todate}" target="_blank">
                                Print<img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a>

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
                                        <td  style="text-align: center" class="formbg" ><%=++i%></td>
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
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailSurgery.xls?nexxt=start&back=start&getPersonalDetailsSurgery=getPersonalDetailsSurgery&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=b3&D=<%=typeOfDisability%>&cName=AnyOther&count=<bean:write name="modify" property="otherNeedsMR"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="otherNeedsMR"/></a></td>
                                    </tr>

                                </logic:iterate>
                                <tr>
                                    <th class="formhdbg" colspan="2" style="text-align: center">Total</th>
                                    <th class="formhdbg" style="text-align: center"><%=otherNeedsMRTotal%></th>

                                </tr>
                            </table><br>

                            <a href="./analysisReport.xls?mode=excelWebsiteAnalysis&district_id=${district_id}&mandal_id=${mandal_id}&village_id=${village_id}&typeofdisability=${typeofdisability}&reportcategory=${reportcategory}&reportSubcategory=${reportSubcategory}&fromdate=${fromdate}&todate=${todate}" target="_blank">
                                Export To Excel <img src="DisabilityUITG/images/excel.jpg"
                                                     height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;

                            <a href="./analysisReport.do?mode=printWebsiteAnalysis&district_id=${district_id}&mandal_id=${mandal_id}&village_id=${village_id}&typeofdisability=${typeofdisability}&reportcategory=${reportcategory}&reportSubcategory=${reportSubcategory}&fromdate=${fromdate}&todate=${todate}" target="_blank">
                                Print<img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a>

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
                                        <td style="text-align: center"  class="formbg" ><%=++i%></td>
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

                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=b3&D=<%=typeOfDisability%>&cName=Behaviour&cValue=Behavior Modification&count=<bean:write name="modify" property="psychotherapyBehaviourBelowThreeYearsMI"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="psychotherapyBehaviourBelowThreeYearsMI"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=a3&D=<%=typeOfDisability%>&cName=Psycotherapy_Behaviour&cValue=Psycotherapy/Behaviour Modification&count=<bean:write name="modify" property="psychotherapyBehaviourAboveThreeYearsMI"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="psychotherapyBehaviourAboveThreeYearsMI"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailSurgery.xls?nexxt=start&back=start&getPersonalDetailsSurgery=getPersonalDetailsSurgery&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=b3&D=<%=typeOfDisability%>&cName=Surgery&count=<bean:write name="modify" property="surgeryMI"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="surgeryMI"/></a></td>
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailsAnalysis.do?nexxt=start&back=start&getPersonalDetailsAnalysis=getPersonalDetailsAnalysis&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&D=<%=typeOfDisability%>&cName=NursingHomes&cValue=Psychiatric Hospitals/ Nursing Homes&count=<bean:write name="modify" property="admissionPsychiatric"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="admissionPsychiatric"/></a></td>

                                        <td class="formbg" style="text-align: center"><bean:write name="modify" property="total"/></td>
                                    </tr>

                                </logic:iterate>
                                <tr>
                                    <th class="formhdbg" colspan="2" style="text-align: center">Total</th>
                                    <th class="formhdbg" style="text-align: center"><%=psychotherapyBehaviourBelowThreeYearsMITotal%></th>
                                    <th class="formhdbg" style="text-align: center"><%=psychotherapyBehaviourAboveThreeYearsMITotal%></th>
                                    <th class="formhdbg" style="text-align: center"><%=surgeryMITotal%></th>
                                    <th class="formhdbg" style="text-align: center"><%=admissionPsychiatricTotal%></th>
                                    <th class="formhdbg" style="text-align: center"><%= total%></th>
                                </tr>
                            </table><br>

                            <a href="./analysisReport.xls?mode=excelWebsiteAnalysis&district_id=${district_id}&mandal_id=${mandal_id}&village_id=${village_id}&typeofdisability=${typeofdisability}&reportcategory=${reportcategory}&reportSubcategory=${reportSubcategory}&fromdate=${fromdate}&todate=${todate}" target="_blank">
                                Export To Excel <img src="DisabilityUITG/images/excel.jpg"
                                                     height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;

                            <a href="./analysisReport.do?mode=printWebsiteAnalysis&district_id=${district_id}&mandal_id=${mandal_id}&village_id=${village_id}&typeofdisability=${typeofdisability}&reportcategory=${reportcategory}&reportSubcategory=${reportSubcategory}&fromdate=${fromdate}&todate=${todate}" target="_blank">
                                Print<img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a>

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
                                        <td  style="text-align: center"  class="formbg" ><%=++i%></td>
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
                                        <td class="formbg" style="text-align: center">
                                            <a href="javascript:void(0);" onclick ="window.open('personalDetailSurgery.xls?nexxt=start&back=start&getPersonalDetailsSurgery=getPersonalDetailsSurgery&dID=<bean:write name="modify" property="district_id"/>&mID=<bean:write name="modify" property="mandal_id"/>&vID=<bean:write name="modify" property="village_id"/>&hID=<bean:write name="modify" property="habitation_id"/>&fDate=<%=fromdate%>&toDate=<%=todate%>&B=b3&D=<%=typeOfDisability%>&cName=AnyOther&count=<bean:write name="modify" property="otherNeedsMI"/>&c=<%=category%>', 'Ratting','resizable=yes, scrollbars=yes,')">
                                                <bean:write name="modify" property="otherNeedsMI"/></a>
                                        </td>
                                    </tr>

                                </logic:iterate>
                                <tr>
                                    <th class="formhdbg" colspan="2" style="text-align: center">Total</th>
                                    <th class="formhdbg" style="text-align: center"><%=otherNeedsMITotal%></th>

                                </tr>
                            </table><br>

                            <a href="./analysisReport.xls?mode=excelWebsiteAnalysis&district_id=${district_id}&mandal_id=${mandal_id}&village_id=${village_id}&typeofdisability=${typeofdisability}&reportcategory=${reportcategory}&reportSubcategory=${reportSubcategory}&fromdate=${fromdate}&todate=${todate}" target="_blank">
                                Export To Excel <img src="DisabilityUITG/images/excel.jpg"
                                                     height="25" width="25"/></a> &nbsp; &nbsp; &nbsp;

                            <a href="./analysisReport.do?mode=printWebsiteAnalysis&district_id=${district_id}&mandal_id=${mandal_id}&village_id=${village_id}&typeofdisability=${typeofdisability}&reportcategory=${reportcategory}&reportSubcategory=${reportSubcategory}&fromdate=${fromdate}&todate=${todate}" target="_blank">
                                Print<img src="DisabilityUITG/images/print.gif"  height="25" width="25"/></a>


                        </logic:present>
                    </td>
                </tr>
            </table>


        </html:form>
    </body>
</html:html>
