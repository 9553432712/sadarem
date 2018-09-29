<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@page import="org.bf.disability.bean.UpperExtremityBean" %>
<%@page import="org.bf.disability.util.UECalculationsImpl.*,java.util.*,org.bf.disability.util.UpperExtremityCalculation" %>
<%
String personcode=(String)session.getAttribute("personcode");
String name=(String)session.getAttribute("Name");
%>
<html:html locale="true">
      <body  onload="window.print()">

      <form>
<%
  int index;
   int middle;
     int ring;
     int little;
     int keyholding;
     int senthumb;
     int cylarge;
     int cysmall;
     int splarge;
     int spsmall;
     int senindex;
     int senmiddle;
     int senring;
     int senlittle;
     int hook;
      int strgrip;
   int strpinch;
        int prehention;
     int sensation;
     int strength;
     int opposition;
     int cylinder;
     int spherical;
UpperExtremityCalculation ue = new UpperExtremityCalculation();
    UpperExtremityBean upperExtremityBean = new UpperExtremityBean();
    upperExtremityBean =(UpperExtremityBean)request.getAttribute("upperExtremityBean");
    int Ms_sf_right = Integer.parseInt(upperExtremityBean.getMs_sf_right());
    int Ms_se_right = Integer.parseInt(upperExtremityBean.getMs_se_right());
    int Ms_sab_right = Integer.parseInt(upperExtremityBean.getMs_sab_right());
    int Ms_sad_right = Integer.parseInt(upperExtremityBean.getMs_sad_right());
    int Ms_sext_right = Integer.parseInt(upperExtremityBean.getMs_sext_right());
    int Ms_sint_right = Integer.parseInt(upperExtremityBean.getMs_sint_right());
    int Ms_sf_left = Integer.parseInt(upperExtremityBean.getMs_sf_left());
    int Ms_se_left = Integer.parseInt(upperExtremityBean.getMs_se_left());
    int Ms_sab_left = Integer.parseInt(upperExtremityBean.getMs_sab_left());
    int Ms_sad_left = Integer.parseInt(upperExtremityBean.getMs_sad_left());
    int Ms_sext_left = Integer.parseInt(upperExtremityBean.getMs_sext_left());
    int Ms_sint_left = Integer.parseInt(upperExtremityBean.getMs_sint_left());
    int Ms_ef_right = Integer.parseInt(upperExtremityBean.getMs_ef_right());
    int Ms_ee_right = Integer.parseInt(upperExtremityBean.getMs_ee_right());
    int Ms_ep_right = Integer.parseInt(upperExtremityBean.getMs_ep_right());
    int Ms_es_right = Integer.parseInt(upperExtremityBean.getMs_es_right());
    int Ms_ef_left = Integer.parseInt(upperExtremityBean.getMs_ef_left());
    int Ms_ee_left = Integer.parseInt(upperExtremityBean.getMs_ee_left());
    int Ms_ep_left =Integer.parseInt(upperExtremityBean.getMs_ep_left());
    int Ms_es_left = Integer.parseInt(upperExtremityBean.getMs_es_left());
    int Ms_wd_right = Integer.parseInt(upperExtremityBean.getMs_wd_right());
    int Ms_wp_right = Integer.parseInt(upperExtremityBean.getMs_wp_right());
    int Ms_wr_right = Integer.parseInt(upperExtremityBean.getMs_wr_right());
    int Ms_wu_right = Integer.parseInt(upperExtremityBean.getMs_wu_right());
    int Ms_wd_left = Integer.parseInt(upperExtremityBean.getMs_wd_left());
    int Ms_wp_left = Integer.parseInt(upperExtremityBean.getMs_wp_left());
    int Ms_wr_left = Integer.parseInt(upperExtremityBean.getMs_wr_left());
    int Ms_wu_left = Integer.parseInt(upperExtremityBean.getMs_wu_left());
    int ms_sf_right_per= ue.msMethod(Ms_sf_right);
    int ms_se_right_per= ue.msMethod(Ms_se_right);
    int ms_sab_right_per= ue.msMethod(Ms_sab_right);
    int ms_sad_right_per= ue.msMethod(Ms_sad_right);
    int ms_sext_right_per= ue.msMethod(Ms_sext_right);
    int ms_sint_right_per= ue.msMethod(Ms_sint_right);
    int ms_sf_left_per= ue.msMethod(Ms_sf_left);
    int ms_se_left_per= ue.msMethod(Ms_se_left);
    int ms_sab_left_per= ue.msMethod(Ms_sab_left);
    int ms_sad_left_per= ue.msMethod(Ms_sad_left);
    int ms_sext_left_per= ue.msMethod(Ms_sext_left);
    int ms_sint_left_per=ue.msMethod(Ms_sint_left);
    int ms_ef_right_per= ue.msMethod(Ms_ef_right);
    int ms_ee_right_per= ue.msMethod(Ms_ee_right);
    int ms_ep_right_per=ue.msMethod(Ms_ep_right);
    int ms_es_right_per=ue.msMethod(Ms_es_right);
    int ms_ef_left_per= ue.msMethod(Ms_ef_left);
    int ms_ee_left_per= ue.msMethod(Ms_ee_left);
    int ms_ep_left_per=ue.msMethod(Ms_ep_left);
    int ms_es_left_per=ue.msMethod(Ms_es_left);
    int ms_wd_right_per= ue.msMethod(Ms_wd_right);
    int ms_wp_right_per= ue.msMethod(Ms_wp_right);
    int ms_wr_right_per= ue.msMethod(Ms_wr_right);
    int ms_wu_right_per= ue.msMethod(Ms_wu_right);
    int ms_wd_left_per= ue.msMethod(Ms_wd_left);
    int ms_wp_left_per= ue.msMethod(Ms_wp_left);
    int ms_wr_left_per= ue.msMethod(Ms_wr_left);
    int ms_wu_left_per= ue.msMethod(Ms_wu_left);
    double hipright = (((ms_sf_right_per+ ms_se_right_per+ms_sab_right_per+
            ms_sad_right_per+ms_sext_right_per+
            ms_sint_right_per)/6)*0.3);
    double kneeright=(((ms_ef_right_per+ms_ee_right_per+ms_ep_right_per+
            ms_es_right_per)/4)*0.3);
    double ankleright=(((ms_wd_right_per+ms_wp_right_per+ms_wr_right_per+
            ms_wu_right_per)/4)*0.3);
    double msright = hipright+kneeright+ankleright;
    double hipleft= (((ms_sf_left_per+ ms_se_left_per+ms_sab_left_per+
            ms_sad_left_per+ms_sext_left_per+
            ms_sint_left_per)/6)*0.3);
    double kneeleft=(((ms_ef_left_per+ms_ee_left_per+ms_ep_left_per+
            ms_es_left_per)/4)*0.3);
    double ankleleft=(((ms_wd_left_per+ms_wp_left_per+ms_wr_left_per+
            ms_wu_left_per)/4)*0.3);
    double msleft = hipleft+kneeleft+ankleleft;

// Hand Component
     int Hand_opindex_right = Integer.parseInt(upperExtremityBean.getHand_opindex_right());
        int Hand_opmiddle_right = Integer.parseInt(upperExtremityBean.getHand_opmiddle_right());
        int Hand_opring_right = Integer.parseInt(upperExtremityBean.getHand_opring_right());
        int Hand_oplittle_right = Integer.parseInt(upperExtremityBean.getHand_oplittle_right());
        int Hand_opindex_left = Integer.parseInt(upperExtremityBean.getHand_opindex_left());
        int Hand_opmiddle_left = Integer.parseInt(upperExtremityBean.getHand_opmiddle_left());
        int Hand_opring_left = Integer.parseInt(upperExtremityBean.getHand_opring_left());
        int Hand_oplittle_left = Integer.parseInt(upperExtremityBean.getHand_oplittle_left());
        int Hand_lakey_right = Integer.parseInt(upperExtremityBean.getHand_lakey_right());
        int Hand_lakey_left = Integer.parseInt(upperExtremityBean.getHand_lakey_left());
        int Hand_cylarge_right = Integer.parseInt(upperExtremityBean.getHand_cylarge_right());
        int Hand_cysmall_right = Integer.parseInt(upperExtremityBean.getHand_cysmall_right());
        int Hand_cylarge_left = Integer.parseInt(upperExtremityBean.getHand_cylarge_left());
        int Hand_cysmall_left = Integer.parseInt(upperExtremityBean.getHand_cysmall_left());
        int Hand_splarge_right = Integer.parseInt(upperExtremityBean.getHand_splarge_right());
        int Hand_spsmall_right = Integer.parseInt(upperExtremityBean.getHand_spsmall_right());
        int Hand_splarge_left = Integer.parseInt(upperExtremityBean.getHand_splarge_left());
        int Hand_spsmall_left = Integer.parseInt(upperExtremityBean.getHand_spsmall_left());
        int Hand_hook_right = Integer.parseInt(upperExtremityBean.getHand_hook_right());
        int Hand_hook_left = Integer.parseInt(upperExtremityBean.getHand_hook_left());
        int Hand_sethumb_right = Integer.parseInt(upperExtremityBean.getHand_sethumb_right());
        int Hand_seindex_right = Integer.parseInt(upperExtremityBean.getHand_seindex_right());
        int Hand_semiddle_right = Integer.parseInt(upperExtremityBean.getHand_semiddle_right());
        int Hand_sering_right = Integer.parseInt(upperExtremityBean.getHand_sering_right());
        int Hand_selittle_right = Integer.parseInt(upperExtremityBean.getHand_selittle_right());
        int Hand_sethumb_left = Integer.parseInt(upperExtremityBean.getHand_sethumb_left());
        int Hand_seindex_left = Integer.parseInt(upperExtremityBean.getHand_seindex_left());
        int Hand_semiddle_left = Integer.parseInt(upperExtremityBean.getHand_semiddle_left());
        int Hand_sering_left = Integer.parseInt(upperExtremityBean.getHand_sering_left());
        int Hand_selittle_left = Integer.parseInt(upperExtremityBean.getHand_selittle_left());
        int Hand_stgrip_right= Integer.parseInt(upperExtremityBean.getHand_stgrip_right());
        int Hand_stpinch_right= Integer.parseInt(upperExtremityBean.getHand_stpinch_right());
        int Hand_stgrip_left= Integer.parseInt(upperExtremityBean.getHand_stgrip_left());
        int Hand_stpinch_left= Integer.parseInt(upperExtremityBean.getHand_stpinch_left());
 index= ue.count(Hand_opindex_right+ Hand_opindex_left);
        middle=ue.count(Hand_opmiddle_right+Hand_opmiddle_left);
        ring=ue.count(Hand_opring_right+Hand_opring_left);
        little=ue.count(Hand_oplittle_right+Hand_oplittle_left);
        keyholding=ue.count1(Hand_lakey_right+Hand_lakey_left);
        cylarge = ue.count2(Hand_cylarge_right+Hand_cylarge_left);
        cysmall =ue. count2(Hand_cysmall_right+Hand_cysmall_left);
        splarge =ue. count2(Hand_splarge_right+Hand_splarge_left);
        spsmall =ue. count2(Hand_spsmall_right+Hand_spsmall_left);
        hook =ue. count1(Hand_hook_right+Hand_hook_left);
        opposition =(index+middle+ring+little);
        cylinder =cylarge+cysmall;
        spherical=splarge+spsmall;
        prehention=index+middle+ring+little+keyholding+cylarge+cysmall+
                splarge+spsmall+hook;
        senthumb=ue. count3(Hand_sethumb_right+Hand_sethumb_left);
        senindex=ue. count6(Hand_seindex_right+Hand_seindex_left);
        senmiddle=ue. count1(Hand_semiddle_right+Hand_semiddle_left);
        senring =ue.count1(Hand_sering_right+Hand_sering_left);
        senlittle=ue. count1(Hand_selittle_right+Hand_selittle_left);
        sensation=senthumb+senmiddle+senindex+senring+senlittle;
        strgrip=ue. count4(Hand_stgrip_right+Hand_stgrip_left);
        strpinch = ue.count5(Hand_stpinch_right+Hand_stpinch_left);
        strength=strgrip+strpinch;
        int hand = prehention+ sensation+strength;

%>


 <LINK REL="stylesheet" HREF="./DisabilityUITG/css/printeformatecss.css">
<logic:notEmpty name="upperExtremityBean" scope="request">

        <TABLE border="0" align="center" cellspacing="1" cellpadding="5" class="tbl_bg2" width="100%">
            <tr><td colspan="3"  align="right"><b>ID No.&nbsp;<%=personcode%></b></td></tr>
        <tr><td colspan="3"  align="right"><b>Name.&nbsp;<%=name%></b></td></tr>

            <tr class="tbl_bg2_content_hdr">
                <th align="center"><font size="4">1.&nbsp;&nbsp;UPPER EXTREMITY</font></th>
            </tr>
             <logic:notEqual name="upperExtremityBean" property="totalArm" value="">
            <tr class="tbl_bg2_content_hdr">
                <th align="left"><font size="3">1.1 &nbsp;&nbsp;Arm Component :</font>&nbsp;&nbsp;<font size="1"> ( Total Value=90% )</font></th>
            </tr>
            <logic:notEqual name="upperExtremityBean" property="rom" value="1" >
                <tr class="tbl_bg2_content_hdr">
                    <th align="left"><font size="2">1.1.1 &nbsp;&nbsp;Active Range of Motion (ROM) ARC :</font><font size="1">&nbsp;&nbsp; (in degrees)</font></th>
                </tr>
            </logic:notEqual >
        </TABLE>
        <logic:notEqual name="upperExtremityBean" property="rom" value="1" >
            <TABLE  border="0" align="center" cellspacing="1" cellpadding="3" class="tbl_bg2" width="100%">
            <tr class="tbl_bg2_content">
                <td rowspan=2><b>Joint </b></td><td rowspan=2><b>Component</b></td>
                <td colspan=2><b>Active ROM</b></td><td colspan=2><b>Active ROM Calculations</b></td>
                <td colspan=2><b> Calculations</b></td>
            </tr>
            <tr class="tbl_bg2_content">
                <td><b>Right</td><td><b>Left</td> <td><b>Right Calculation</td><td><b>Left Calculation</td>
                <td><b>Total Right Calculation</td><td><b>Total Left Calculation</td>
            </tr>
            <logic:notEqual name="upperExtremityBean" property="romshoulder" value="1" >
                <tr class="tbl_bg2_content">

                    <td><B>1.&nbsp;Shoulder Joint</B></td>
                    <td><logic:notEqual name="upperExtremityBean"  property="romflexion"  value="1" >1. &nbsp;Flexion-Extension</logic:notEqual> <br><BR><logic:notEqual name="upperExtremityBean"  property="romabduction"  value="1" >2. &nbsp;Abduction-Adduction </logic:notEqual><br><BR><logic:notEqual name="upperExtremityBean"  property="romrotation"  value="1" >3.&nbsp;Rotation </logic:notEqual></td>
                    <td><logic:notEqual name="upperExtremityBean"  property="romflexion"  value="1" ><logic:notEqual name="upperExtremityBean"  property="rom_sf_right" value="220" ><bean:write name="upperExtremityBean"  property="rom_sf_right" /></logic:notEqual></logic:notEqual><br><br><logic:notEqual name="upperExtremityBean"  property="romabduction"  value="1" ><logic:notEqual name="upperExtremityBean"  property="rom_sa_right" value="180" ><bean:write  name="upperExtremityBean" property="rom_sa_right" /></logic:notEqual></logic:notEqual><br><br><logic:notEqual name="upperExtremityBean"  property="romrotation"  value="1" ><logic:notEqual name="upperExtremityBean"  property="rom_sr_right" value="180" ><bean:write   name="upperExtremityBean" property="rom_sr_right" /></logic:notEqual></logic:notEqual></td>
                    <td><logic:notEqual name="upperExtremityBean"  property="romflexion"  value="1" ><logic:notEqual name="upperExtremityBean"  property="rom_sf_left" value="220" > <bean:write name="upperExtremityBean"  property="rom_sf_left" /></logic:notEqual></logic:notEqual><br><br><logic:notEqual name="upperExtremityBean"  property="romabduction"  value="1" ><logic:notEqual name="upperExtremityBean"  property="rom_sa_left" value="180" ><bean:write name="upperExtremityBean" property="rom_sa_left" /></logic:notEqual></logic:notEqual><br><br><logic:notEqual name="upperExtremityBean"  property="romrotation"  value="1" ><logic:notEqual name="upperExtremityBean"  property="rom_sr_left" value="180" ><bean:write   name="upperExtremityBean" property="rom_sr_left" /></logic:notEqual></logic:notEqual></td>
                    <td>
                        <logic:notEqual name="upperExtremityBean"  property="romflexion"  value="1" >
                        <logic:notEqual name="upperExtremityBean"  property="hrom_sf_right" value="0" >(((220-<bean:write name="upperExtremityBean"  property="rom_sf_right" />)*100)/220)=<bean:write name="upperExtremityBean" property="hrom_sf_right"/></logic:notEqual ></logic:notEqual>
                        <br><br>
                        <logic:notEqual name="upperExtremityBean"  property="romabduction"  value="1" >
                        <logic:notEqual name="upperExtremityBean"  property="hrom_sa_right" value="0" >(((180-<bean:write name="upperExtremityBean"  property="rom_sa_right" />)*100)/180)=<bean:write name="upperExtremityBean" property="hrom_sa_right"/></logic:notEqual ></logic:notEqual >
                        <br><br>
                        <logic:notEqual name="upperExtremityBean"  property="romrotation"  value="1" >
                        <logic:notEqual name="upperExtremityBean"  property="hrom_sr_right" value="0" >(((180-<bean:write name="upperExtremityBean"  property="rom_sr_right" />)*100)/180)=<bean:write name="upperExtremityBean" property="hrom_sr_right"/></logic:notEqual ></logic:notEqual >
                    </td>

                    <td>
                        <logic:notEqual name="upperExtremityBean"  property="romflexion"  value="1" >
                        <logic:notEqual name="upperExtremityBean"  property="hrom_sf_left" value="0" >(((220-<bean:write name="upperExtremityBean"  property="rom_sf_left" />)*100)/220)=<bean:write name="upperExtremityBean" property="hrom_sf_left"/></logic:notEqual ></logic:notEqual >
                        <br><br>
                        <logic:notEqual name="upperExtremityBean"  property="romabduction"  value="1" >
                        <logic:notEqual name="upperExtremityBean"  property="hrom_sa_left" value="0" >(((180-<bean:write name="upperExtremityBean"  property="rom_sa_left" />)*100)/180)=<bean:write name="upperExtremityBean" property="hrom_sa_left"/></logic:notEqual ></logic:notEqual >
                        <br><br>
                        <logic:notEqual name="upperExtremityBean"  property="romrotation"  value="1" >
                        <logic:notEqual name="upperExtremityBean"  property="hrom_sr_left" value="0" >(((180-<bean:write name="upperExtremityBean"  property="rom_sr_left" />)*100)/180)=<bean:write name="upperExtremityBean" property="hrom_sr_left"/></logic:notEqual ></logic:notEqual >
                    </td>

                    <td>
                        <logic:notEqual name="upperExtremityBean"  property="romshoulderright"  value="1">

                            (((<logic:notEqual name="upperExtremityBean"  property="hrom_sf_right" value="0"><bean:write name="upperExtremityBean" property="hrom_sf_right"/></logic:notEqual><logic:notEqual name="upperExtremityBean"  property="hrom_sa_right" value="0" >+<bean:write name="upperExtremityBean" property="hrom_sa_right"/></logic:notEqual><logic:notEqual name="upperExtremityBean"  property="hrom_sr_right" value="0" >+<bean:write name="upperExtremityBean" property="hrom_sr_right"/></logic:notEqual>)/3)*3)=<bean:write name="upperExtremityBean" property="romhipr"/>


                        </logic:notEqual >
                    </td> <td>
                        <logic:notEqual name="upperExtremityBean"  property="romshoulderleft"  value="1">


                            (((<logic:notEqual name="upperExtremityBean"  property="hrom_sf_left" value="0"><bean:write name="upperExtremityBean" property="hrom_sf_left"/></logic:notEqual><logic:notEqual name="upperExtremityBean"  property="hrom_sa_left" value="0" >+<bean:write name="upperExtremityBean" property="hrom_sa_left"/></logic:notEqual><logic:notEqual name="upperExtremityBean"  property="hrom_sr_left" value="0" >+<bean:write name="upperExtremityBean" property="hrom_sr_left"/></logic:notEqual>)/3)*3)=<bean:write name="upperExtremityBean" property="romhipl"/>


                        </logic:notEqual >
                    </td>
                </tr>
            </logic:notEqual>

            <logic:notEqual name="upperExtremityBean" property="romkeen" value="1" >
                <tr class="tbl_bg2_content">
                    <td><B>2.&nbsp;Elbow Joint</B></td>
                    <td><logic:notEqual name="upperExtremityBean"  property="romkneeflexion"  value="1" >1.&nbsp; Flexion-Extension</logic:notEqual>  <br><BR><logic:notEqual name="upperExtremityBean"  property="romkneesupination"  value="1" >2. &nbsp;Supination-Pronation </logic:notEqual></td>
                    <td><logic:notEqual name="upperExtremityBean"  property="romkneeflexion"  value="1" ><logic:notEqual name="upperExtremityBean"  property="rom_ef_right" value="150" ><bean:write  name="upperExtremityBean" property="rom_ef_right" /></logic:notEqual></logic:notEqual><br><br><logic:notEqual name="upperExtremityBean"  property="romkneesupination"  value="1" ><logic:notEqual name="upperExtremityBean"  property="rom_es_right" value="180" ><bean:write name="upperExtremityBean" property="rom_es_right" /></logic:notEqual></logic:notEqual></td>
                    <td><logic:notEqual name="upperExtremityBean"  property="romkneeflexion"  value="1" ><logic:notEqual name="upperExtremityBean"  property="rom_ef_left" value="150" ><bean:write  name="upperExtremityBean" property="rom_ef_left" /></logic:notEqual></logic:notEqual><br><br><logic:notEqual name="upperExtremityBean"  property="romkneesupination"  value="1" ><logic:notEqual name="upperExtremityBean"  property="rom_es_left" value="180" ><bean:write name="upperExtremityBean" property="rom_es_left" /></logic:notEqual></logic:notEqual></td>
                    <td><logic:notEqual name="upperExtremityBean"  property="romkneeright"  value="1" >
                        <logic:notEqual name="upperExtremityBean"  property="romknee21r"  value="0" >
                            (((150-<bean:write  name="upperExtremityBean" property="rom_ef_right" />)*100)/150)=<bean:write  name="upperExtremityBean" property="romknee21r" />
                        </logic:notEqual>
                    </logic:notEqual>
                        <br><br>
                        <logic:notEqual name="upperExtremityBean"  property="romkneeright"  value="1" >
                            <logic:notEqual name="upperExtremityBean"  property="romknee22r"  value="0" >
                                (((180-<bean:write  name="upperExtremityBean" property="rom_es_right" />)*100)/180)=<bean:write  name="upperExtremityBean" property="romknee22r" />
                            </logic:notEqual>
                        </logic:notEqual>
                    </td>

                    <td><logic:notEqual name="upperExtremityBean"  property="romkneeleft"  value="1" >
                        <logic:notEqual name="upperExtremityBean"  property="romknee21l"  value="0" >
                            (((150-<bean:write  name="upperExtremityBean" property="rom_ef_left" />)*100)/150)=<bean:write  name="upperExtremityBean" property="romknee21l" />
                        </logic:notEqual>
                    </logic:notEqual>
                        <br><br>
                        <logic:notEqual name="upperExtremityBean"  property="romkneeleft"  value="1" >
                            <logic:notEqual name="upperExtremityBean"  property="romknee22l"  value="0" >
                                (((180-<bean:write  name="upperExtremityBean" property="rom_es_left" />)*100)/180)=<bean:write  name="upperExtremityBean" property="romknee22l" />
                            </logic:notEqual>
                        </logic:notEqual>
                    </td>

                    <td>
                    <logic:notEqual name="upperExtremityBean"  property="romkneeright"  value="1">

                        (((<logic:notEqual name="upperExtremityBean"  property="romknee21r" value="0"><bean:write name="upperExtremityBean" property="romknee21r"/></logic:notEqual><logic:notEqual name="upperExtremityBean"  property="romknee22r" value="0" >+<bean:write name="upperExtremityBean" property="romknee22r"/></logic:notEqual>)/2)*0.3)=<bean:write name="upperExtremityBean" property="romkneer"/>


                    </logic:notEqual > </td>
                    <td>
                        <logic:notEqual name="upperExtremityBean"  property="romkneeleft"  value="1">

                            (((<logic:notEqual name="upperExtremityBean"  property="romknee21l" value="0"><bean:write name="upperExtremityBean" property="romknee21l"/></logic:notEqual><logic:notEqual name="upperExtremityBean"  property="romknee22l" value="0" >+<bean:write name="upperExtremityBean" property="romknee22l"/></logic:notEqual>)/2)*0.3)=<bean:write name="upperExtremityBean" property="romkneel"/>


                        </logic:notEqual >
                    </td>
                </tr>
            </logic:notEqual>
            <logic:notEqual name="upperExtremityBean" property="wrist" value="1" >
                <tr class="tbl_bg2_content">
                    <td><B>3.&nbsp;Wrist Joint</B></td>
                    <td><logic:notEqual name="upperExtremityBean"  property="wristpalmar"  value="1" >1.&nbsp; Dorsiflexion-Palmar flexion </logic:notEqual> <br><BR><logic:notEqual name="upperExtremityBean"  property="wristulnar"  value="1" >2.&nbsp; Radial- Ulnar deviation</logic:notEqual> </td>
                    <td><logic:notEqual name="upperExtremityBean"  property="wristpalmar"  value="1" ><logic:notEqual name="upperExtremityBean"  property="rom_wd_right" value="160" ><bean:write  name="upperExtremityBean"  property="rom_wd_right" /></logic:notEqual></logic:notEqual></br><br><logic:notEqual name="upperExtremityBean"  property="wristulnar"  value="1" ><logic:notEqual name="upperExtremityBean"  property="rom_wr_right" value="55" ><bean:write name="upperExtremityBean"  property="rom_wr_right" /></logic:notEqual></logic:notEqual></td>
                    <td><logic:notEqual name="upperExtremityBean"  property="wristpalmar"  value="1" ><logic:notEqual name="upperExtremityBean"  property="rom_wd_left" value="160" ><bean:write name="upperExtremityBean"  property="rom_wd_left" /></logic:notEqual></logic:notEqual><br><br><logic:notEqual name="upperExtremityBean"  property="wristulnar"  value="1" ><logic:notEqual name="upperExtremityBean"  property="rom_wr_left" value="55" ><bean:write name="upperExtremityBean"  property="rom_wr_left" /></logic:notEqual></logic:notEqual></td>
                    <td>
                        <logic:notEqual name="upperExtremityBean" property="arom_wd_right" value="0" > (((160-<bean:write  name="upperExtremityBean"  property="rom_wd_right" />)*100)/160)=<bean:write name="upperExtremityBean" property="arom_wd_right"/></logic:notEqual>
                        <br><br>
                        <logic:notEqual name="upperExtremityBean" property="arom_wr_right" value="0" > (((55-<bean:write  name="upperExtremityBean"  property="rom_wr_right" />)*100)/55)=<bean:write name="upperExtremityBean" property="arom_wr_right"/></logic:notEqual>
                    </td>

                    <td>
                        <logic:notEqual name="upperExtremityBean" property="arom_wd_left" value="0" > (((160-<bean:write  name="upperExtremityBean"  property="rom_wd_left" />)*100)/160)=<bean:write name="upperExtremityBean" property="arom_wd_left"/></logic:notEqual>
                        <br><br>
                        <logic:notEqual name="upperExtremityBean" property="arom_wr_left" value="0" > (((55-<bean:write  name="upperExtremityBean"  property="rom_wr_left" />)*100)/55)=<bean:write name="upperExtremityBean" property="arom_wr_left"/></logic:notEqual>
                    </td>
                    <td>
                        <logic:notEqual name="upperExtremityBean" property="wristright" value="1" >
                            ((<bean:write name="upperExtremityBean" property="arom_wd_right"/>+<bean:write name="upperExtremityBean" property="arom_wr_right"/>)/2)*0.3 =<bean:write name="upperExtremityBean" property="rowankler"/>
                        </logic:notEqual>
                    </td>

                    <td>
                        <logic:notEqual name="upperExtremityBean" property="wristleft" value="1" >
                            ((<bean:write name="upperExtremityBean" property="arom_wd_left"/>+<bean:write name="upperExtremityBean" property="arom_wr_left"/>)/2)*0.3 =<bean:write name="upperExtremityBean" property="rowanklel"/>
                        </logic:notEqual>
                    </td>
                </tr>
            </logic:notEqual>
            </table>
            <TABLE  border="0" align="center" cellspacing="1" cellpadding="3" class="tbl_bg2" width="100%">

            <logic:notEqual name="upperExtremityBean" property="romright" value="0">
                <tr class="tbl_bg2_content">
                    <td><b>RomTotalRight : (Shoulder Joint Right + Elbow Join Right + Wrist Joint Right ) = </b>
                    (<bean:write name="upperExtremityBean" property="romhipr"/>+<bean:write name="upperExtremityBean" property="romkneer"/>+<bean:write name="upperExtremityBean" property="rowankler"/>)=<bean:write name="upperExtremityBean" property="romright"/>
                </tr>
            </logic:notEqual>
            <logic:notEqual name="upperExtremityBean" property="romleft" value="0">
                <tr class="tbl_bg2_content">
                    <td><b>RomTotalLeft : (Shoulder Joint Left + Elbow Join Left + Wrist Joint Left ) =   </b>
                    (<bean:write name="upperExtremityBean" property="romhipl"/>+<bean:write name="upperExtremityBean" property="romkneel"/>+<bean:write name="upperExtremityBean" property="rowanklel"/>)=<bean:write name="upperExtremityBean" property="romleft"/>
                </tr>
            </logic:notEqual>
            </table>
        </logic:notEqual >

        <% if(msright!=0.0 || msleft!=0.0) { %>
         <TABLE border="0" align="center" cellspacing="1" cellpadding="5" class="tbl_bg2" width="100%">

                <tr class="tbl_bg2_content_hdr">
                    <th align="left"><font size="2">1.1.2 &nbsp;&nbsp;Muscle Strength:</font> <font size="1">&nbsp;&nbsp; (Normal Value = Grade 5)</font></font></th>
                </tr>
            </TABLE>
            <TABLE border="0" align="center" cellspacing="1" cellpadding="3" class="tbl_bg2" width="100%">
                <tr class="tbl_bg2_content">
                <td rowspan=2><b>Joint </b></td><td rowspan=2><b>Component</b></td>
                <td colspan=2><b>Active Muscle Grade </b></td>
                <td colspan=2><b>Muscle strenght Calculations</b></td>
                <td colspan=2><b> Calculations</b></td>
                </tr>
                <tr class="tbl_bg2_content">
                    <td><b>Right </td><td><b>Left </td>
                    <td><b>Right calculations </td><td><b>Left calculations </td>
                    <td><b>Total Right calculations </td><td><b>Total Left calculations</td>
                </tr>

                <% if(hipright!=0.0 || hipleft!=0.0) { %>
                <tr class="tbl_bg2_content">
                    <td class="tbl_bg2_content">
                <B>1.&nbsp;Shoulder Joint</B>
                <br><BR>Muscle Strength <br>Value 90%
                    </td>
                    <td>
            <% if(ms_sf_right_per!=0.0 || ms_sf_left_per!=0.0)   {  %>   1. &nbsp;Flexion <% } %>
           <br><BR>  <% if(ms_se_right_per!=0.0 || ms_se_left_per!=0.0)   {  %>        2. &nbsp;Extension<% } %>
         <br><BR>    <% if(ms_sab_right_per!=0.0 || ms_sab_left_per!=0.0)   {  %>   3.&nbsp;Abduction<% } %>
        <br><BR>     <% if(ms_sad_right_per!=0.0 || ms_sad_left_per!=0.0)   {  %>        4.&nbsp;Adduction<% } %>
        <br><BR>      <% if(ms_sext_right_per!=0.0 || ms_sext_left_per!=0.0)   {  %>       5.&nbsp;Ext-Rotation<% } %>
        <br><BR>      <% if(ms_sint_right_per!=0.0 || ms_sint_left_per!=0.0)   {  %>       6.&nbsp;Int-Rotation<% } %>
                    </td>

                   <td>

              <%  if(Ms_sf_right!=5) { %> <bean:write name="upperExtremityBean" property="ms_sf_right" /> <% } %>
        <br><BR>   <%  if(Ms_se_right!=5) { %>      <bean:write name="upperExtremityBean"property="ms_se_right" /><% } %>
               <br><BR>  <%  if(Ms_sab_right!=5) { %> <bean:write name="upperExtremityBean" property="ms_sab_right" /><% } %>
               <br><BR> <%  if(Ms_sad_right!=5) { %> <bean:write name="upperExtremityBean" property="ms_sad_right" /><% } %>
               <br><BR> <%  if(Ms_sext_right!=5) { %> <bean:write name="upperExtremityBean" property="ms_sext_right" /><% } %>
               <br><BR>  <%  if(Ms_sint_right!=5) { %> <bean:write name="upperExtremityBean"property="ms_sint_right" /><% } %>

                   </td>
               <td>

              <%  if(Ms_sf_left!=5) { %> <bean:write name="upperExtremityBean" property="ms_sf_left" /> <% } %>
                  <br><BR>   <%  if(Ms_se_left!=5) { %>      <bean:write name="upperExtremityBean"property="ms_se_left" /><% } %>
               <br><BR>  <%  if(Ms_sab_left!=5) { %> <bean:write name="upperExtremityBean" property="ms_sab_left" /><% } %>
               <br><BR> <%  if(Ms_sad_left!=5) { %> <bean:write name="upperExtremityBean" property="ms_sad_left" /><% } %>
               <br><BR> <%  if(Ms_sext_left!=5) { %> <bean:write name="upperExtremityBean" property="ms_sext_left" /><% } %>
               <br><BR>  <%  if(Ms_sint_left!=5) { %> <bean:write name="upperExtremityBean"property="ms_sint_left" /><% } %>
               </td>
                 <td>
                    <%  if(ms_sf_right_per!=0.0) { out.println(ms_sf_right_per); } %>
                  <br><BR>   <%  if(ms_se_right_per!=0.0) { out.println(ms_se_right_per); } %>
               <br><BR>  <%  if(ms_sab_right_per!=0.0) {  out.println(ms_sab_right_per); } %>
               <br><BR> <%  if(ms_sad_right_per!=0.0) {  out.println(ms_sad_right_per); } %>
               <br><BR> <%  if(ms_sext_right_per!=0.0) {  out.println(ms_sext_right_per); } %>
               <br><BR>  <%  if(ms_sint_right_per!=0.0) {  out.println(ms_sint_right_per); } %>

                  </td>

                   <td>
                     <%  if(ms_sf_left_per!=0.0) { out.println(ms_sf_left_per); } %>
                  <br><BR>   <%  if(ms_se_left_per!=0.0) { out.println(ms_se_left_per); } %>
               <br><BR>  <%  if(ms_sab_left_per!=0.0) {  out.println(ms_sab_left_per); } %>
               <br><BR> <%  if(ms_sad_left_per!=0.0) {  out.println(ms_sad_left_per); } %>
               <br><BR> <%  if(ms_sext_left_per!=0.0) {  out.println(ms_sext_left_per); } %>
               <br><BR>  <%  if(ms_sint_left_per!=0.0) {  out.println(ms_sint_left_per); } %>
                  </td>
                  <td>
              <% if(hipright!=0.0) { %> (( <%out.println(ms_sf_right_per); %> +<% out.println(ms_se_right_per);%>+<%out.println(ms_sab_right_per);%>+<%
               out.println( ms_sad_right_per);%>+<%out.println(ms_sext_right_per);%>+<%out.println(ms_sint_right_per);%>/6)*0.3)=<%out.println(hipright);  %>
                 <%}%>
                  </td>

                   <td>
              <% if(hipleft!=0.0) { %>   (( <%out.println(ms_sf_left_per); %> +<% out.println(ms_se_left_per);%>+<%out.println(ms_sab_left_per);%>+<%
               out.println( ms_sad_left_per);%>+<%out.println(ms_sext_left_per);%>+<%out.println(ms_sint_left_per);%>/6)*0.3)=<%out.println(hipleft);  %>
                  <% } %>
                   </td>
                </tr>
        <%}%>

     <% if(kneeright!=0.0 || kneeleft!=0.0) { %>



                <tr class="tbl_bg2_content">
                    <td><B>2.&nbsp;Elbow Joint</B>
<br><BR>Muscle Strength <br>
Value 90%
     </td>
                    <td>
            <% if(ms_ef_right_per!=0.0 || ms_ef_left_per!=0.0)   {  %>   1. &nbsp;Flexion <% } %>
           <br><BR>  <% if(ms_ee_right_per!=0.0 || ms_ee_left_per!=0.0)   {  %>        2. &nbsp;Extension<% } %>
         <br><BR>    <% if(ms_ep_right_per!=0.0 || ms_ep_left_per!=0.0)   {  %>   3.&nbsp;Pronation<% } %>
        <br><BR>     <% if(ms_es_right_per!=0.0 || ms_es_left_per!=0.0)   {  %>        4.&nbsp;Supination<% } %>
                    </td>

                   <td>

              <%  if(Ms_ef_right!=5) { %> <bean:write name="upperExtremityBean" property="ms_ef_right" /> <% } %>
        <br><BR>   <%  if(Ms_ee_right!=5) { %>      <bean:write name="upperExtremityBean"property="ms_ee_right" /><% } %>
               <br><BR>  <%  if(Ms_ep_right!=5) { %> <bean:write name="upperExtremityBean" property="ms_ep_right" /><% } %>
               <br><BR> <%  if(Ms_es_right!=5) { %> <bean:write name="upperExtremityBean" property="ms_es_right" /><% } %>


                   </td>
               <td>

              <%  if(Ms_ef_left!=5) { %> <bean:write name="upperExtremityBean" property="ms_ef_left" /> <% } %>
                  <br><BR>   <%  if(Ms_ee_left!=5) { %>      <bean:write name="upperExtremityBean"property="ms_ee_left" /><% } %>
               <br><BR>  <%  if(Ms_ep_left!=5) { %> <bean:write name="upperExtremityBean" property="ms_ep_left" /><% } %>
               <br><BR> <%  if(Ms_es_left!=5) { %> <bean:write name="upperExtremityBean" property="ms_es_left" /><% } %>

               </td>
                 <td>
                    <%  if(ms_ef_right_per!=0.0) { out.println(ms_ef_right_per); } %>
                  <br><BR>   <%  if(ms_ee_right_per!=0.0) { out.println(ms_ee_right_per); } %>
               <br><BR>  <%  if(ms_ep_right_per!=0.0) {  out.println(ms_ep_right_per); } %>
               <br><BR> <%  if(ms_es_right_per!=0.0) {  out.println(ms_es_right_per); } %>


                  </td>

                   <td>
                     <%  if(ms_ef_left_per!=0.0) { out.println(ms_ef_left_per); } %>
                  <br><BR>   <%  if(ms_ee_left_per!=0.0) { out.println(ms_ee_left_per); } %>
               <br><BR>  <%  if(ms_ep_left_per!=0.0) {  out.println(ms_ep_left_per); } %>
               <br><BR> <%  if(ms_es_left_per!=0.0) {  out.println(ms_es_left_per); } %>

                  </td>
                  <td>
              <% if(kneeright!=0.0) { %> (( <%out.println(ms_ef_right_per); %> +<% out.println(ms_ee_right_per);%>+<%out.println(ms_ep_right_per);%>+<%
               out.println( ms_es_right_per);%>/4)*0.3)=<%out.println(kneeright);  %>
                 <%}%>
                  </td>

                   <td>
              <% if(kneeleft!=0.0) { %>   (( <%out.println(ms_ef_left_per); %> +<% out.println(ms_ee_left_per);%>+<%out.println(ms_ep_left_per);%>+<%
               out.println( ms_es_left_per);%>/4)*0.3)=<%out.println(kneeleft);  %>
                  <% } %>
                   </td>
                </tr>
        <%}%>



         <% if(ankleright!=0.0 || ankleleft!=0.0) { %>





                <tr class="tbl_bg2_content">
                      <td><B>3.&nbsp;Wrist Joint</B>
            <br><BR>Muscle Strength
            <br>Value 90%</td>
                    <td>
            <% if(ms_wd_right_per!=0.0 || ms_wd_left_per!=0.0)   {  %>   1. &nbsp;Dorsiflexion <% } %>
           <br><BR>  <% if(ms_wp_right_per!=0.0 || ms_wp_left_per!=0.0)   {  %>        2. &nbsp;Palmar Flexion<% } %>
         <br><BR>    <% if(ms_wr_right_per!=0.0 || ms_wr_left_per!=0.0)   {  %>   3.&nbsp;Radial Deviation<% } %>
        <br><BR>     <% if(ms_wu_right_per!=0.0 || ms_wu_left_per!=0.0)   {  %>        4.&nbsp;Ulnar Deviation<% } %>
                    </td>

                   <td>

              <%  if(Ms_wd_right!=5) { %> <bean:write name="upperExtremityBean" property="ms_wd_right" /> <% } %>
        <br><BR>   <%  if(Ms_wp_right!=5) { %>      <bean:write name="upperExtremityBean"property="ms_wp_right" /><% } %>
               <br><BR>  <%  if(Ms_wr_right!=5) { %> <bean:write name="upperExtremityBean" property="ms_wr_right" /><% } %>
               <br><BR> <%  if(Ms_wu_right!=5) { %> <bean:write name="upperExtremityBean" property="ms_wu_right" /><% } %>


                   </td>
               <td>

              <%  if(Ms_wd_left!=5) { %> <bean:write name="upperExtremityBean" property="ms_wd_left" /> <% } %>
                  <br><BR>   <%  if(Ms_wp_left!=5) { %>      <bean:write name="upperExtremityBean"property="ms_wp_left" /><% } %>
               <br><BR>  <%  if(Ms_wr_left!=5) { %> <bean:write name="upperExtremityBean" property="ms_wr_left" /><% } %>
               <br><BR> <%  if(Ms_wu_left!=5) { %> <bean:write name="upperExtremityBean" property="ms_wu_left" /><% } %>

               </td>
                 <td>
                    <%  if(ms_wd_right_per!=0.0) { out.println(ms_wd_right_per); } %>
                  <br><BR>   <%  if(ms_wp_right_per!=0.0) { out.println(ms_wp_right_per); } %>
               <br><BR>  <%  if(ms_wr_right_per!=0.0) {  out.println(ms_wr_right_per); } %>
               <br><BR> <%  if(ms_wu_right_per!=0.0) {  out.println(ms_wu_right_per); } %>


                  </td>

                   <td>
                     <%  if(ms_wd_left_per!=0.0) { out.println(ms_wd_left_per); } %>
                  <br><BR>   <%  if(ms_wp_left_per!=0.0) { out.println(ms_wp_left_per); } %>
               <br><BR>  <%  if(ms_wr_left_per!=0.0) {  out.println(ms_wr_left_per); } %>
               <br><BR> <%  if(ms_wu_left_per!=0.0) {  out.println(ms_wu_left_per); } %>

                  </td>
                  <td>
              <% if(ankleright!=0.0) { %> (( <%out.println(ms_wd_right_per); %> +<% out.println(ms_wp_right_per);%>+<%out.println(ms_wr_right_per);%>+<%
               out.println( ms_wu_right_per);%>/4)*0.3)=<%out.println(ankleright);  %>
                 <%}%>
                  </td>

                   <td>
              <% if(ankleleft!=0.0) { %>   (( <%out.println(ms_wd_left_per); %> +<% out.println(ms_wp_left_per);%>+<%out.println(ms_wr_left_per);%>+<%
               out.println( ms_wu_left_per);%>/4)*0.3)=<%out.println(ankleleft);  %>
                  <% } %>
                   </td>
                </tr>
        <%}%>
        </table>
         <TABLE border="0" align="center" cellspacing="1" cellpadding="3" class="tbl_bg2" width="100%">
<% if(msright!=0.0) { %>
           <tr class="tbl_bg2_content">
            <td>
         <b>  MSTotalRight : (Shoulder Joint Right + Elbow Join Right + Wrist Joint Right )  </b>(<% out.println( hipright); %> + <% out.println (kneeright); %>+<% out.println(ankleright);%>)=<%out.println(msright);%>
            </td>
            </tr>
            <% } %>
            <% if(msleft!=0.0) { %>
           <tr class="tbl_bg2_content">
            <td>
         <b>  MsTotalLeft : (Shoulder Joint Left + Elbow Join Left + Wrist Joint Left )  </b>(<% out.println( hipleft); %> + <% out.println (kneeleft); %>+<% out.println(ankleleft);%>)=<%out.println(msleft);%>
            </td>
            </tr>
            <% } %>
        </table>
        <% } %>

          <logic:notEqual name="upperExtremityBean" property="coordinate" value="0">
         <TABLE border="0" align="center" cellspacing="1" cellpadding="5" class="tbl_bg2" width="100%">

                <tr class="tbl_bg2_content_hdr">
                    <th align="left"><font size="2">1.1.3 &nbsp;&nbsp;Coordinated Activities:</font></th>
                </tr>
            </TABLE>

               <TABLE  border="0" align="center" cellspacing="1" cellpadding="6"  width="100%" class="tbl_bg2">
                <tr class="tbl_bg2_content">
                    <td rowspan=3><b>Coordinated Activities</b></td>
                    <td rowspan=3><b>Components&nbsp; (each 90%)</b></td>
                    <td colspan=2><b>Loss of Coordinated Activities</b></td>

                </tr>
                <tr class="tbl_bg2_content">
                </tr>
                <tr >
                </tr>
                <tr class="tbl_bg2_content">
                    <th rowspan=11> value 90% </th>
                </tr><logic:notEqual name="upperExtremityBean" property="coordinate_lifting" value="0">
                <tr class="tbl_bg2_content">
                    <td>
                    A.&nbsp;Lifting overhead objects,remove and <br>&nbsp;&nbsp;&nbsp;&nbsp;place at the same place(9%)<td align ="center"><bean:write name="upperExtremityBean"  property="coordinate_lifting" /></td>
                </tr>
                </logic:notEqual >
                <logic:notEqual name="upperExtremityBean" property="coordinate_touching" value="0">
                <tr class="tbl_bg2_content">
                    <td>B.&nbsp;Touching nose with end of the extremity(9%)<td align ="center"><bean:write name="upperExtremityBean" property="coordinate_touching"  /></td>
                </tr>
                </logic:notEqual >
                <logic:notEqual name="upperExtremityBean" property="coordinate_eating" value="0">
                <tr class="tbl_bg2_content">
                    <td>
                    C.&nbsp;Eating Indian style(9%)<td align ="center"><bean:write name="upperExtremityBean" property="coordinate_eating"  /></td>
                </tr>
                </logic:notEqual >
                <logic:notEqual name="upperExtremityBean" property="coordinate_combing" value="0">
                <tr class="tbl_bg2_content">
                    <td>
                    D.&nbsp;Combing and plating (9%)<td align ="center"><bean:write name="upperExtremityBean" property="coordinate_combing"  /></td>
                </tr>
                </logic:notEqual >
                <logic:notEqual name="upperExtremityBean" property="coordinate_putting" value="0">
                <tr class="tbl_bg2_content">
                    <td>
                   E.&nbsp;Putting on a shirt/kurtha(9%)<td align ="center"><bean:write name="upperExtremityBean" property="coordinate_putting"  /></td>
                </tr>
                </logic:notEqual >
                <logic:notEqual name="upperExtremityBean" property="coordinate_ablution" value="0">
                <tr class="tbl_bg2_content">
                    <td>
                    F.&nbsp;Ablution glass of water(9%)<td align ="center"><bean:write name="upperExtremityBean" property="coordinate_ablution"  /></td>
                </tr>
                </logic:notEqual >
                <logic:notEqual name="upperExtremityBean" property="coordinate_buttoning" value="0">
                <tr class="tbl_bg2_content">
                    <td>
                        G.&nbsp;Buttoning(9%)<td align ="center"><bean:write name="upperExtremityBean" property="coordinate_buttoning" /></td>

                </tr>
                </logic:notEqual >
                <logic:notEqual name="upperExtremityBean" property="coordinate_tie" value="0">
                <tr class="tbl_bg2_content">
                    <td>H.&nbsp;Tie Nara Dhoti(9%)<td align ="center"><bean:write name="upperExtremityBean" property="coordinate_tie" /></td>
                </tr>
                </logic:notEqual >
                <logic:notEqual name="upperExtremityBean" property="coordinate_writing" value="0">
                <tr class="tbl_bg2_content">
                    <td>I.&nbsp;Writing(9%)<td align ="center"><bean:write name="upperExtremityBean" property="coordinate_writing" /></td>

                </tr>
                 </logic:notEqual >
                 <logic:notEqual name="upperExtremityBean" property="coordinate_drinking" value="0">
                <tr class="tbl_bg2_content">
                    <td>I.&nbsp;Drinking Glass of Water(9%)<td align ="center"><bean:write name="upperExtremityBean" property="coordinate_drinking" /></td>

                </tr>
                 </logic:notEqual >
            </TABLE>
            <TABLE  border="0" align="center" cellspacing="1" cellpadding="6"  width="100%" class="tbl_bg2">
                <tr class="tbl_bg2_content">
                <td>
                <b>Coordinated Activities Total :</b> (<bean:write name="upperExtremityBean"  property="coordinate_lifting" />+
                                                <bean:write name="upperExtremityBean" property="coordinate_touching"  />+
                                                <bean:write name="upperExtremityBean" property="coordinate_eating"  />+
                                                <bean:write name="upperExtremityBean" property="coordinate_combing"  />+
                                                <bean:write name="upperExtremityBean" property="coordinate_putting"  />+
                                                <bean:write name="upperExtremityBean" property="coordinate_ablution"  />+
                                                <bean:write name="upperExtremityBean" property="coordinate_buttoning" />+
                                                <bean:write name="upperExtremityBean" property="coordinate_tie" />+
                                                <bean:write name="upperExtremityBean" property="coordinate_writing" />+
                                                <bean:write name="upperExtremityBean" property="coordinate_drinking" />)=
                                                <bean:write name="upperExtremityBean" property="coordinate" />

                </td>
                </tr>

            </table>
          </logic:notEqual >
           <TABLE border="0" align="center" cellspacing="1" cellpadding="5" class="tbl_bg2" width="100%">
                <logic:notEqual name="upperExtremityBean" property="totalArm" value="">
            <tr class="tbl_bg2_content_hdr">
                <th align="left"><font size="2">Arm Component Calculations:</font>&nbsp;&nbsp;<font size="1"></font></th>
            </tr>
             </logic:notEqual >
                <logic:notEqual name="upperExtremityBean" property="totalArmRight" value="">
         <tr class="tbl_bg2_content">
               <td>
     <b>   ArmRight : </b> <pre><bean:write name="upperExtremityBean"property="totalArmRight" /></pre>
         </td>
              </tr>
                </logic:notEqual >
                <logic:notEqual name="upperExtremityBean" property="totalArmLeft" value="">
             <tr class="tbl_bg2_content">
          <td>
    <b>  ArmLeft : </b>  <pre><bean:write name="upperExtremityBean"property="totalArmLeft" /></pre>
         </td>
         </tr>
          </logic:notEqual >
                <logic:notEqual name="upperExtremityBean" property="armRightLeft" value="">
             <tr class="tbl_bg2_content">
          <td>
       <b> Total (ArmRight+ArmLeft) :</b> <pre><bean:write name="upperExtremityBean" property="armRightLeft" /></pre>
         </td>
              </tr>
               </logic:notEqual >
               <logic:notEqual name="upperExtremityBean" property="totalArm" value="">
             <tr class="tbl_bg2_content">
          <td>
      <b>  Arm : </b> <pre><bean:write name="upperExtremityBean" property="totalArm" /></pre>
         </td>
             </tr>
               </logic:notEqual >
         <tr class="tbl_bg2_content">
         <td>
         <b>Arm component</b> = <bean:write name="upperExtremityBean" property="arm" />
         </td>
              </tr>

          </table>
           </logic:notEqual >
          <% if(hand!=0){ %>
           <TABLE border="0" align="center" cellspacing="1" cellpadding="5" class="tbl_bg2" width="100%">

                <tr class="tbl_bg2_content_hdr">
                    <th align="left"><font size="3">1.2 &nbsp;&nbsp;Hand Component :</font>&nbsp;&nbsp;<font size="1"> (Total Value=90%)</font></th>
                </tr>
            </TABLE>
            <TABLE  border="0" align="center" cellspacing="1" cellpadding="6"  width="100%" class="tbl_bg2">
                <tr class="tbl_bg2_content">
                    <td rowspan=2 align="center"><b>Activities<br>(30% for each component below)</b></td>
                    <td rowspan=2 align="center"><b>Movement</b></td>
                    <td colspan=2 align="center"><b>Loss in %</b></td>
                    <td colspan=1 align="center"><b>(Right+Left)Calculation</b></td>

                </tr>
                <tr class="tbl_bg2_content">
                    <td align="center"><b>Right </td>
                    <td align="center"><b>Left </td>
                        <td></td>
              <% if(prehention!=0) { %>
                </tr>
                <tr class="tbl_bg2_content">
                    <td><B>1.2.1 Prehension (30%)</B></td>
                    <td></td>
                    <td></td>
                    <td></td>
                 <td></td>

                </tr>

        <% if(opposition!=0) { %>
                <tr class="tbl_bg2_content">
                    <td rowspan=5 align="left">A.&nbsp;Opposition (8%) </td>
                </tr>
          <tr class="tbl_bg2_content">
        <td>  <% if(index!=0){ %>
                    1.&nbsp;&nbsp;Index<% } %></td>
             <td align ="center">    <% if(Hand_opindex_right!=0){ %>   <bean:write name="upperExtremityBean" property="hand_opindex_right"  /><% } %></td>
             <td align ="center">    <% if(Hand_opindex_left!=0){ %>    <bean:write name="upperExtremityBean" property="hand_opindex_left"  /><% } %></td>
          <td align ="center"> <% if(index!=0){ out.println(index); }%></td>

          </tr>
      <tr class="tbl_bg2_content">
        <td>  <% if(middle!=0){ %>
                    2.&nbsp;&nbsp;Middle<% } %></td>
             <td align ="center">    <% if(Hand_opmiddle_right!=0){ %>   <bean:write name="upperExtremityBean" property="hand_opmiddle_right"  /><% } %></td>
             <td align ="center">    <% if(Hand_opmiddle_left!=0){ %>    <bean:write name="upperExtremityBean" property="hand_opmiddle_left"  /><% } %></td>
         <td align ="center"> <% if(middle!=0){ out.println(middle); }%></td>
      </tr>
         <tr class="tbl_bg2_content">
        <td>  <% if(ring!=0){ %>
                    3.&nbsp;&nbsp;Ring<% } %></td>
             <td align ="center">    <% if(Hand_opring_right!=0){ %>   <bean:write name="upperExtremityBean" property="hand_opring_right"  /><% } %></td>
             <td align ="center">    <% if(Hand_opring_left!=0){ %>    <bean:write name="upperExtremityBean" property="hand_opring_left"  /><% } %></td>
         <td align ="center"> <% if(ring!=0){ out.println(ring); }%></td>
         </tr>
           <tr class="tbl_bg2_content">
        <td>  <% if(little!=0){ %>
                    4.&nbsp;&nbsp;Little<% } %></td>
             <td align ="center">    <% if(Hand_oplittle_right!=0){ %>   <bean:write name="upperExtremityBean" property="hand_oplittle_right"  /><% } %></td>
             <td align ="center">    <% if(Hand_oplittle_left!=0){ %>    <bean:write name="upperExtremityBean" property="hand_oplittle_left"  /><% } %></td>
           <td align ="center"> <% if(little!=0){ out.println(little); }%></td>
           </tr>
           <%}%>
           <% if(keyholding!=0){ %>
           <tr class="tbl_bg2_content">
                    <td rowspan=2 align ="left">B.&nbsp;Lateral Pinch (5%) </td>
                </tr>
                <tr class="tbl_bg2_content">
                    <td>Key Holding</td>

                    <td align ="center"> <% if(Hand_lakey_right!=0){ %><bean:write name="upperExtremityBean" property="hand_lakey_right" /><% } %></td>
                    <td align ="center"><% if(Hand_lakey_left!=0){ %><bean:write name="upperExtremityBean" property="hand_lakey_left" /><% } %></td>
               <td align ="center"> <% if(keyholding!=0){ out.println(keyholding); }%></td>
                </tr>
                 <%}%>
                 <% if(cylinder!=0){ %>
                  <tr class="tbl_bg2_content">
                    <td rowspan=3 align ="left">C.&nbsp;Cylindrical Grasp (6%) </td>
                </tr>
                <tr class="tbl_bg2_content">
                    <td> <% if(cylarge!=0){ %> Large Object(4") <%}%></td>

                    <td align ="center"><% if(Hand_cylarge_right!=0){ %>  <bean:write name="upperExtremityBean" property="hand_cylarge_right" /><%}%></td>
                    <td align ="center"><% if(Hand_cylarge_left!=0){ %> <bean:write name="upperExtremityBean" property="hand_cylarge_left" /><%}%></td>
            <td align ="center"> <% if(cylarge!=0){ out.println(cylarge); } %></td>
                </tr>

                <tr class="tbl_bg2_content">
                    <td><% if(cysmall!=0){ %>Small Object(1") <%}%></td>

                    <td align ="center"><% if(Hand_cysmall_right!=0){ %><bean:write name="upperExtremityBean" property="hand_cysmall_right" /><%}%></td>
                    <td align ="center"><% if(Hand_cysmall_left!=0){ %><bean:write name="upperExtremityBean" property="hand_cysmall_left" /><%}%></td>
                <td align ="center"> <% if(cysmall!=0){ out.println(cysmall); } %></td>
                </tr>
        <%}%>

         <% if(spherical!=0){ %>
                  <tr class="tbl_bg2_content">
                    <td rowspan=3 align ="left">D.&nbsp;Spherical Grasp (6%) </td>
                </tr>
                <tr class="tbl_bg2_content">
                    <td> <% if(splarge!=0){ %> Large Object(4") <%}%></td>

                    <td align ="center"><% if(Hand_splarge_right!=0){ %>  <bean:write name="upperExtremityBean" property="hand_splarge_right" /><%}%></td>
                    <td align ="center"><% if(Hand_splarge_left!=0){ %> <bean:write name="upperExtremityBean" property="hand_splarge_left" /><%}%></td>
            <td align ="center"> <% if(cylarge!=0){ out.println(cylarge); } %></td>
                </tr>

                <tr class="tbl_bg2_content">
                    <td><% if(spsmall!=0){ %>Small Object(1") <%}%></td>

                    <td align ="center"><% if(Hand_spsmall_right!=0){ %><bean:write name="upperExtremityBean" property="hand_spsmall_right" /><%}%></td>
                    <td align ="center"><% if(Hand_spsmall_left!=0){ %><bean:write name="upperExtremityBean" property="hand_spsmall_left" /><%}%></td>
                <td align ="center"> <% if(spsmall!=0){ out.println(spsmall); } %></td>
                </tr>
        <%}%>


            <% if(hook!=0){ %>
           <tr class="tbl_bg2_content">
                    <td rowspan=2 align ="left">E.&nbsp;Hook Grasp (5%) </td>
                </tr>
                <tr class="tbl_bg2_content">
                    <td>Lifting bag</td>

                    <td align ="center"> <% if(Hand_hook_right!=0){ %><bean:write name="upperExtremityBean" property="hand_hook_right" /><% } %></td>
                    <td align ="center"><% if(Hand_hook_left!=0){ %><bean:write name="upperExtremityBean" property="hand_hook_left" /><% } %></td>
               <td align ="center"> <% if(hook!=0){ out.println(hook); }%></td>
                </tr>
                 <%} }%>
                   <% if(sensation!=0){ %>
        <tr class="tbl_bg2_content">
                    <th rowspan=6 align="left"><B>1.2.2&nbsp;&nbsp;Sensation(30%)<B></th>
                </tr>
                        <tr class="tbl_bg2_content">
                    <td><% if(senthumb!=0){ %>1.&nbsp;&nbsp;Thumb ray <% } %></td>
                    <td align ="center"> <% if(Hand_sethumb_right!=0){ %><bean:write name="upperExtremityBean"property="hand_sethumb_right" /><% } %></td>
                    <td align ="center"><% if(Hand_sethumb_left!=0){ %><bean:write name="upperExtremityBean" property="hand_sethumb_left" /><% } %></td>
                 <td align ="center"> <% if(senthumb!=0){ out.println(senthumb); } %></td>
                        </tr>
                         <tr class="tbl_bg2_content">
                    <td><% if(senindex!=0){ %>2.&nbsp;&nbsp;Index finger <% } %></td>
                    <td align ="center"> <% if(Hand_seindex_right!=0){ %><bean:write name="upperExtremityBean"property="hand_seindex_right" /><% } %></td>
                    <td align ="center"><% if(Hand_seindex_left!=0){ %><bean:write name="upperExtremityBean" property="hand_seindex_left" /><% } %></td>
                 <td align ="center"> <% if(senindex!=0){ out.println(senindex); } %></td>
                        </tr>
                   <tr class="tbl_bg2_content">
                    <td><% if(senmiddle!=0){ %>3.&nbsp;&nbsp;Middle finger <% } %></td>
                    <td align ="center"> <% if(Hand_semiddle_right!=0){ %><bean:write name="upperExtremityBean"property="hand_semiddle_right" /><% } %></td>
                    <td align ="center"><% if(Hand_semiddle_left!=0){ %><bean:write name="upperExtremityBean" property="hand_semiddle_left" /><% } %></td>
                 <td align ="center"> <% if(senmiddle!=0){ out.println(senmiddle); } %></td>
                        </tr>

                        <tr class="tbl_bg2_content">
                    <td><% if(senring!=0){ %>4.&nbsp;&nbsp;Ring finger <% } %></td>
                    <td align ="center"> <% if(Hand_sering_right!=0){ %><bean:write name="upperExtremityBean"property="hand_sering_right" /><% } %></td>
                    <td align ="center"><% if(Hand_sering_left!=0){ %><bean:write name="upperExtremityBean" property="hand_sering_left" /><% } %></td>
                 <td align ="center"> <% if(senring!=0){ out.println(senring); } %></td>
                        </tr>

                 <tr class="tbl_bg2_content">
                    <td><% if(senlittle!=0){ %>5.&nbsp;&nbsp;Little finger <% } %></td>
                    <td align ="center"> <% if(Hand_selittle_right!=0){ %><bean:write name="upperExtremityBean"property="hand_selittle_right" /><% } %></td>
                    <td align ="center"><% if(Hand_selittle_left!=0){ %><bean:write name="upperExtremityBean" property="hand_selittle_left" /><% } %></td>
                 <td align ="center"> <% if(senlittle!=0){ out.println(senlittle); } %></td>
                        </tr>
 <% } %>
   <% if(strength!=0){ %>
 <tr class="tbl_bg2_content">
                    <th rowspan=3 align="left"><B>1.2.3&nbsp;&nbsp;Strength(30%)<B></th>
                </tr>
                <tr class="tbl_bg2_content">
                    <td><% if(strgrip!=0){ %>1.&nbsp;&nbsp;Grip strength<% } %></td>

                    <td align ="center"><% if(Hand_stgrip_right!=0){ %><bean:write name="upperExtremityBean" property="hand_stgrip_right"/><% } %></td>
                    <td align ="center"><% if(Hand_stgrip_left!=0){ %><bean:write name="upperExtremityBean" property="hand_stgrip_left" /><% } %></td>
                <td align ="center"> <% if(strgrip!=0){ out.println(strgrip); } %></td>
                </tr>
                <tr class="tbl_bg2_content">
                    <td><% if(strpinch!=0){ %>2.&nbsp;&nbsp;Pinch strength <% } %></td>

                    <td align ="center"><% if(Hand_stpinch_right!=0){ %><bean:write name="upperExtremityBean"property="hand_stpinch_right" /><% } %></td>
                    <td align ="center"><% if(Hand_stpinch_left!=0){ %><bean:write name="upperExtremityBean" property="hand_stpinch_left" /><% } %></td>
                <td align ="center"> <% if(strpinch!=0){ out.println(strpinch); } %></td>
                </tr>
                <% } %>
            </TABLE>

         <TABLE  border="0" align="center" cellspacing="1" cellpadding="6"  width="100%" class="tbl_bg2">

            <tr class="tbl_bg2_content_hdr">
                <th align="left"><font size="2">Hand Component Calculations:</font>&nbsp;&nbsp;<font size="1"></font></th>
            </tr>

                <tr class="tbl_bg2_content">
             <td>
             <b>Prehension</b> =(Opposition+Lateral Pinch+Cylindrical Grasp+Spherical Grasp+Hook Grasp)=((<%=opposition %>+<%=keyholding%>+<%=cylinder%>+<%=spherical %>+<%=hook%>)=<%=prehention%>)
             </td>
             </tr>
             <tr class="tbl_bg2_content">
             <td>
             <b>Sensation</b>=(Thumb ray+Index finger +Middle finger+Ring finger+Little finger )=(<%=senthumb %>+<%=senindex %>+<%=senmiddle %>+<%=senring %>+<%=senlittle %> =<%=sensation %>)
             </td>
             </tr>
            <tr class="tbl_bg2_content">
             <td>
             <b>Strength</b>=(Grip strength+Pinch strength )=(<%=strgrip %>+<%=strpinch %> =<%=strength %>)
             </td>
             </tr>
             <tr class="tbl_bg2_content">
             <td>
             <b>Hand Component</b>=(Prehension+Sensation+Strength )=(<%=prehention %>+<%=sensation %>+<%=strength %>) =<%=hand %>
             </td>
             </tr>
         </table>
         <%}%>
      <logic:notEqual name="upperExtremityBean" property="extraCoomplication" value="0">
         <TABLE border="0" align="center" cellspacing="1" cellpadding="5" class="tbl_bg2" width="100%">

                <tr class="tbl_bg2_content_hdr">
                    <td><B>1.3 &nbsp;Complications</B></td>


                    <td ><b> <font size="1">Percentage Of Complications</font></b></td>

                </tr>

 <logic:notEqual name="upperExtremityBean" property="com_inflection" value="0">
                <tr class="tbl_bg2_content">
                    <td>1.&nbsp; Infections </td>
                    <td><bean:write name="upperExtremityBean"property="com_inflection" /></td>
                    </tr>
 </logic:notEqual>
 <logic:notEqual name="upperExtremityBean" property="com_Deformity" value="0">
      <tr class="tbl_bg2_content">
                 <td>2.&nbsp; Deformity
                    </td>
                    <td><bean:write name="upperExtremityBean"property="com_Deformity" /></td>
                    </tr>
 </logic:notEqual>
  <logic:notEqual name="upperExtremityBean" property="com_Misalignment" value="0">
       <tr class="tbl_bg2_content">
                 <td>3.&nbsp; Mislalignment
                    </td>
                    <td><bean:write name="upperExtremityBean"property="com_Misalignment" /></td>
                    </tr>
 </logic:notEqual>
 <logic:notEqual name="upperExtremityBean" property="com_Contracture" value="0">
      <tr class="tbl_bg2_content">
                 <td>4.&nbsp; Contracture
                    </td>
                    <td><bean:write name="upperExtremityBean"property="com_Contracture" /></td>
                    </tr>
 </logic:notEqual>
 <logic:notEqual name="upperExtremityBean" property="com_LossofCosmeticappearance" value="0">
      <tr class="tbl_bg2_content">
                 <td>5.&nbsp;Loss of Cosmetic appearance
                    </td>
                    <td><bean:write name="upperExtremityBean" property="com_LossofCosmeticappearance" /></td>
                    </tr>
 </logic:notEqual>
  <logic:notEqual name="upperExtremityBean" property="com_domionantupperextremity" value="0">
      <tr class="tbl_bg2_content">
                 <td>6.&nbsp;  Whether dominant upper extremity involved
                    </td>
                    <td><bean:write name="upperExtremityBean"property="com_domionantupperextremity" /></td>
                    </tr>
 </logic:notEqual>


                <tr class="tbl_bg2_content">
     <td>
     Complications =(Infections +Deformity +Misalignment +Contracture +Loss of Cosmetic appearance )=
   <td> ( <bean:write name="upperExtremityBean"property="com_inflection" />+
     <bean:write name="upperExtremityBean"property="com_Deformity" />+
     <bean:write name="upperExtremityBean"property="com_Misalignment" />+
     <bean:write name="upperExtremityBean"property="com_Contracture" />+
     <bean:write name="upperExtremityBean" property="com_LossofCosmeticappearance" />
     )=
     <bean:write name="upperExtremityBean"property="complication" /></td>
     </td>
     </tr>

                    </table>
      </logic:notEqual>
                    <logic:notEqual name="upperExtremityBean" property="inches" value="0">
               <TABLE border="0" align="center" cellspacing="1" cellpadding="5" class="tbl_bg2" width="100%">

                <tr class="tbl_bg2_content_hdr">
                    <td><B>1.4 &nbsp;Shortening:</B></td><td></td>
                </tr><tr class="tbl_bg2_content">
                    <td width="61%"><ul>a.Mention in inches </td>
                    <td><bean:write name="upperExtremityBean" property="inches" /><font size="1"></font>
                    </td>
                </tr>
                     <tr class="tbl_bg2_content">
                    <td>
                    if inches is less than are equal to 1 then shortening will be zero else
                    </td>
                    <td>
                    =((<bean:write name="upperExtremityBean" property="inches" />-1)*2)=<bean:write name="upperExtremityBean" property="shortining" />
                    </td>
                    </ul>
                </tr>
            </TABLE>
             </logic:notEqual>
   <logic:notEqual name="upperExtremityBean" property="extra" value="0">
      <TABLE border="0" align="center" cellspacing="1" cellpadding="5" class="tbl_bg2" width="100%">
 <tr class="tbl_bg2_content_hdr">
                <th align="left"><font size="2">Extra Component Calculations:</font>&nbsp;&nbsp;<font size="1"></font></th>
            </tr>
         <tr class="tbl_bg2_content">
         <td>
          if  complication >10 then Extra component will be  (10+ Whether dominant upper extremity involved+shortining)
else Extra component will be (complication+Whether dominant upper extremity involved+Shortening)

            <br>
        <b> Extra components : </b>
        =
        <bean:write name="upperExtremityBean"property="extra" />
         </td>
         </tr>
     </table>
      </logic:notEqual>
      <TABLE border="0" align="center" cellspacing="1" cellpadding="5" class="tbl_bg2" width="100%">
 <tr class="tbl_bg2_content_hdr">
                <th align="left"><font size="2">UpperExtremity Calculations:</font>&nbsp;&nbsp;<font size="1"></font></th>
            </tr>
             <logic:notEqual name="upperExtremityBean" property="upper" value="">
         <tr class="tbl_bg2_content">
               <td>
       <b> Adding Arm Component and Hand Component :</b> <pre><bean:write name="upperExtremityBean"property="upper" /></pre>
         </td>
              </tr>
                </logic:notEqual >
                <logic:notEqual name="upperExtremityBean" property="upperExtremity" value="">
         <tr class="tbl_bg2_content">
               <td>
       <b> Total Upper Extremity ( (Arm+Hand)+Extra) :</b> <pre><bean:write name="upperExtremityBean"property="upperExtremity" /></pre>


         </td>
              </tr>
                </logic:notEqual >

</logic:notEmpty>
</table>

        </form>
</body>
</html:html>
