    /*
 * IssuecertificateAction.java
 *
 * Created on June 18, 2008, 4:05 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.CommonDAO;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.form.TerritoryForm;
import org.bf.disability.service.TerritoryService;
import org.bf.disability.servicefactory.CommonServiceFactory;
import org.bf.disability.servicefactory.TerritoryServiceFactory;
import org.bf.disability.serviceimpl.CommonServiceImpl;

import com.tcs.sgv.common.util.DBConnection;

//import org.bf.disability.servicefactory.DoctorsInformationServiceFactory;
/**
 * this action class will populate the territory screen for territory select to add the personal details
 * @author deviprasad t
 * @version 1.0
 */
public class PensionForSearchAction extends Action {
    // ArrayList districtlist=new ArrayList();

    String target = "success";

    /**
     * this method will populate the territory select screen for personal details adding
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws org.bf.disability.Exception.SADAREMException
     * @return Action Forward
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException {

        TerritoryForm territoryForm = (TerritoryForm) form;
        TerritoryService territoryservice = TerritoryServiceFactory.getTerritoryServiceImpl();
        CommonServiceImpl commonservice = CommonServiceFactory.getCommonServiceImpl();
        ArrayList mandallist = new ArrayList();
        ArrayList villagelist = new ArrayList();
        ArrayList habitationlist = new ArrayList();
        ArrayList districtList = new ArrayList();
        DataSource ds = null;

        ResultSet rs = null;
        Connection con = null;
        Statement st = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            con = DBConnection.getConnection();
            st = con.createStatement();

            // Get District list





            districtList = territoryservice.getDistricts(ds);
            if (districtList != null && districtList.size() > 0) {
                territoryForm.setDistrictList(districtList);
            }

            if ("getResults".equalsIgnoreCase(territoryForm.getStatus())) 
            {
                //String target = "success";
                String[] personCode = new String[2];
                String[] aadhraCard = new String[2];
                String disId = request.getParameter("district_id");
                CommonDAO cdao = new CommonDAO();
                String sql = null;
                String sql1 = "";
                String sql2 = "";
                String sql3 = "";
                ArrayList areaWise = new ArrayList();
                // Modified by mohan on 13/09/2011

                sql = " select distinct a.Surname +' '+ a.First_Name as name,DATEDIFF(year,a.Date_of_Birth,GETDATE())  as age," 
                        + " case when a.Education=1 then 'Illiterate' when a.Education=2 then 'Below 10th'"
                        + " when a.Education=3 then '10th Class'when a.Education=4 then 'Intermediate'"
                        + " when a.Education=5 then 'Diploma/ITI'when a.Education=6 then 'Graduate'"
                        + " when a.Education=7 then 'Post Graduate' else 'Illiterate' end as qly,case "
                        + " when c.disability_id='1' then 'Locomotor/OH'"
                        + " when c.disability_id='2' then 'Visual Impairment'"
                        + " when c.disability_id='3' then 'Hearing Impairment'"
                        + " when c.disability_id='4' then 'Mental Retardation'"
                        + " when c.disability_id='5' then 'Mental Illness'"
                        + " when c.disability_id='6' then 'Multiple Disability' end as disability,"
                        + " cast(d.totaldisability as varchar) totaldisability,coalesce(a.Phone_No,'0') as mobile,"
                        + " a.relation_name,a.person_code,a.pensioncard_no,a.reasonforpersonstatus,"
                        + "  case when a.categoryid=2 then 'Reassessed' when a.categoryid in(1,3) "
                        + " and a.view_edit_mode ='View' then 'Regular'"
                        + "  when a.categoryid in(1,3) and a.view_edit_mode ='Edit' then 'partA Entered'Else '*****'"
                        + "  end as categoryId,a.pensionphase,CONVERT(varchar(10),d.created_date,103) issuedate ,"
                        +"	CASE WHEN condition_of_disability IN (1,2) AND temp.years_for_temparary=0 THEN 'Regular' ELSE 'Temporary' END certificate_type, "
                        +" CASE WHEN condition_of_disability=3 AND temp.years_for_temparary<>0 THEN CONVERT(varchar(10),DATEADD(year,temp.years_for_temparary,d.created_date),103) ELSE 'NA'  END cert_expire_date,"
                        +" CASE WHEN proof_id IS NOT NULL THEN proofdoc_type+':'+proof_id ELSE '-' END idproff_details "
                        + "  FROM dbo.tblPerson_Personal_Details a with (nolock) "
                        + "  left join tblPerson_disability_details c with(nolock) on(a.person_code=c.person_code)"
                        + "  left join dbo.tblPerson_Disability_TotalValue_Details d with(nolock)"
                        + "  on(a.person_code=d.person_code and d.person_code=c.person_code) "
                        + "	 LEFT JOIN dbo.tblPerson_Causes_of_Disability_Details temp with(nolock) "
                        + " ON (a.person_code=temp.person_code AND d.person_code = temp.person_code AND CONVERT(varchar(10),d.created_date,103)=CONVERT(varchar(10),temp.created_date,103)) "
                        + "  where a.status='Active' and c.status='Active' and d.status='Active'  "
                        + "   and d.created_date =  (select max (created_date) from dbo.tblPerson_Disability_TotalValue_Details with(nolock) where person_code = a.person_code) "
                        + "  ~replaceStr "
                        + "   union "
                        + " select distinct a.Surname +' '+ a.First_Name as name,DATEDIFF(year,a.Date_of_Birth,GETDATE()) as age,"
                        + "   case when a.Education=1 then 'Illiterate' when a.Education=2 then 'Below 10th'"
                        + "   when a.Education=3 then '10th Class'when a.Education=4 then 'Intermediate'"
                        + "   when a.Education=5 then 'Diploma/ITI'when a.Education=6 then 'Graduate'" 
                        + "   when a.Education=7 then 'Post Graduate' else 'Illiterate' end as qly,case "
                        + "   when c.disability_id='1' then 'Locomotor/OH'"
                        + "   when c.disability_id='2' then 'Visual Impairment'"
                        + "   when c.disability_id='3' then 'Hearing Impairment'"
                        + "  when c.disability_id='4' then 'Mental Retardation'"
                        + "  when c.disability_id='5' then 'Mental Illness'"
                        + "  when c.disability_id='6' then 'Multiple Disability' end as disability,"
                        + "   cast(d.totaldisability as varchar) totaldisability,coalesce(a.Phone_No,'0') as mobile,"
                        + "   a.relation_name,a.person_code,a.pensioncard_no,a.reasonforpersonstatus,"
                        + "   case when c.categoryid=1 then 'Regular' when a.categoryid in(1,3) "
                        + "  and a.view_edit_mode ='View' then 'Regular'"
                        + "   when a.categoryid in(1,3) and a.view_edit_mode ='Edit' then 'partA Entered'Else '*****'"
                        + "   end as categoryId,a.pensionphase,CONVERT(varchar(10),d.created_date,103)  issuedate, "
                        + "	CASE WHEN condition_of_disability IN (1,2) AND temp.years_for_temparary=0 THEN 'Regular' ELSE 'Temporary' END certificate_type, "
                        + "  CASE WHEN condition_of_disability=3 AND temp.years_for_temparary<>0 THEN CONVERT(varchar(10),DATEADD(year,temp.years_for_temparary,d.created_date),103) ELSE 'NA'  END cert_expire_date,"
                        + "  CASE WHEN proof_id IS NOT NULL THEN proofdoc_type+':'+proof_id ELSE '-' END  idproff_details"
                        + "	  FROM dbo.tblPerson_Personal_Details a with(nolock) "
                        + "   left join tblPerson_disability_details c with(nolock) on(a.person_code=c.person_code)"
                        + "   left join dbo.tblPerson_Disability_TotalValue_Details d with(nolock) "
                        + "   on(a.person_code=d.person_code and d.person_code=c.person_code) "
                        + "	 LEFT JOIN dbo.tblPerson_Causes_of_Disability_Details temp with(nolock) "
                        + " ON (a.person_code=temp.person_code AND d.person_code = temp.person_code AND CONVERT(varchar(10),d.created_date,103)=CONVERT(varchar(10),temp.created_date,103)) "
                        + "   where  c.status='Inactive' and d.status='Inactive'  and  a.status = 'Active' and a.categoryid=2 and c.categoryid in ('1','3') and d.categoryid in ('1','3')"
                        + "   and d.created_date =  (select max (created_date) from dbo.tblPerson_Disability_TotalValue_Details where person_code = a.person_code and status = 'Inactive' and categoryid = '1') "
                        + "            ~replaceStr"
                        + "  UNION "
                        + "  select distinct a.Surname +' '+ a.First_Name as name,DATEDIFF(year,a.Date_of_Birth,GETDATE()) as age,"
                        + "   case when a.Education=1 then 'Illiterate' when a.Education=2 then 'Below 10th'"
                        + "  when a.Education=3 then '10th Class'when a.Education=4 then 'Intermediate'"
                        + "  when a.Education=5 then 'Diploma/ITI'when a.Education=6 then 'Graduate'" 
                        + " when a.Education=7 then 'Post Graduate' else 'Illiterate' end as qly,case "
                        + " when c.disability_id='1' then 'Locomotor/OH'"
                        + " when c.disability_id='2' then 'Visual Impairment'"
                        + " when c.disability_id='3' then 'Hearing Impairment'"
                        + "  when c.disability_id='4' then 'Mental Retardation'"
                        + "  when c.disability_id='5' then 'Mental Illness'"
                        + "  when c.disability_id='6' then 'Multiple Disability' end as disability,"
                        + " cast(d.totaldisability as varchar) totaldisability,coalesce(a.Phone_No,'0') as mobile,"
                        + " a.relation_name,a.person_code,a.pensioncard_no,a.reasonforpersonstatus,"
                        + " case when a.categoryid=2 then 'Reassessed' when a.categoryid in(1,3) "
                        + "  and a.view_edit_mode ='View' then 'Regular'"
                        + " when a.categoryid in(1,3) and a.view_edit_mode ='Edit' then 'partA Entered'Else '*****'"
                        + "  end as categoryId,a.pensionphase,CONVERT(varchar(10),d.created_date,103)  issuedate,"
                        + " CASE WHEN condition_of_disability IN (1, 2) AND temp.years_for_temparary = 0 THEN 'Regular'  ELSE 'Temporary' END certificate_type,"
                        + " CASE WHEN condition_of_disability = 3 AND temp.years_for_temparary <> 0 THEN CONVERT(varchar(10), DATEADD(year, temp.years_for_temparary, d.created_date),103) ELSE 'NA' END cert_expire_date,"
                        + " CASE WHEN proof_id IS NOT NULL THEN proofdoc_type+':'+proof_id ELSE '-' END  idproff_details"
                        + "  FROM dbo.tblPerson_Personal_Details a with (nolock) "
                        + "  left join tblPerson_disability_details c with(nolock) on(a.person_code=c.person_code)"
                        + "   left join dbo.tblPerson_Disability_TotalValue_Details d with(nolock) on(a.person_code=d.person_code and d.person_code=c.person_code) "
                        + "  LEFT JOIN dbo.tblPerson_Causes_of_Disability_Details temp with(nolock)  "
                        + " ON (a.person_code=temp.person_code AND d.person_code = temp.person_code AND CONVERT(varchar(10),d.created_date,103)=CONVERT(varchar(10),temp.created_date,103)) "
                        + "  where a.status='Inactive' and c.status='Inactive' and d.status='Inactive' and a.reasonforpersonstatus in ('Dead','Permanent Migration') "
                        + "   and d.created_date =  (select max (created_date) FROM dbo.tblPerson_Disability_TotalValue_Details with(nolock) where person_code = a.person_code) "
                        + "  ~replaceStr "
                        + "  UNION "
                        + "   select distinct a.Surname +' '+ a.First_Name as name,DATEDIFF(year,a.Date_of_Birth,GETDATE()) as age,"
                        + "    case when a.Education=1 then 'Illiterate' when a.Education=2 then 'Below 10th'" 
                        + "    when a.Education=3 then '10th Class'when a.Education=4 then 'Intermediate'"
                        + "   when a.Education=5 then 'Diploma/ITI'when a.Education=6 then 'Graduate'"
                        + "  when a.Education=7 then 'Post Graduate' else 'Illiterate' end as qly,'NA' disability,"
                        + "  'NA'  totaldisability,coalesce(a.Phone_No,'0') as mobile,"
                        + "  a.relation_name,a.person_code,a.pensioncard_no,a.reasonforpersonstatus reasonforpersonstatus,"
                        + "   case when a.categoryid=2 then 'Reassessed' when a.categoryid in(1,3) "
                        + "   and a.view_edit_mode ='View' then 'Regular' when a.categoryid in(1,3) and a.view_edit_mode ='Edit' then 'partA Entered' Else '*****' "
                        + "    end as categoryId,a.pensionphase,CONVERT(varchar(10),r.created_date,103) issuedate ,"
                        + " CASE  WHEN condition_of_disability IN (1, 2) AND  temp.years_for_temparary = 0 THEN 'Regular'  ELSE 'Temporary' END certificate_type,"
                        + " CASE WHEN condition_of_disability = 3 AND temp.years_for_temparary <> 0 THEN CONVERT(varchar(10), DATEADD(year,temp.years_for_temparary, r.created_date),103)  ELSE 'NA' END cert_expire_date,"
                        + " CASE WHEN proof_id IS NOT NULL THEN proofdoc_type+':'+proof_id ELSE '-' END  idproff_details"
                        + "   FROM dbo.tblPerson_Personal_Details a with (nolock) join tblRejectPerson_Details r with(nolock) on(a.person_code=r.person_code) "
                        + "   LEFT JOIN dbo.tblPerson_Causes_of_Disability_Details temp with(nolock) "
                        + " ON (a.person_code=temp.person_code AND r.person_code = temp.person_code AND CONVERT(varchar(10),r.created_date,103)=CONVERT(varchar(10),temp.created_date,103)) "
                        + "   where a.status='Active'  and a.view_edit_mode='View' and r.status='Active'"
                        + "    ~replaceStr "
                        + " union"
                        + " SELECT DISTINCT P.firstname + SPACE(2) + P.mid_name AS name ,p.age as age,"
                        + " '-' as qly,'NA' disability,  'NA'  totaldisability,'-' as mobile,  p.fname as relation_name,'-' AS person_code,P.PENSIONID AS pensioncard_no,"
                        + " p.reasonforpersonstatus reasonforpersonstatus,   'Assessment Not Done' as categoryId,p.pensionphase as pensionphase,CONVERT(varchar(10),p.integrateddate,103) issuedate, 'NA','NA','-' "
                        + "  FROM DISABLED_PENSION p with(nolock) where  '1'='1'   "
                        + " ~replacesql2 and pensionid not in "
                        + " (select pensioncard_no from TBLPERSON_PERSONAL_DETAILS  with(nolock) where  pensioncard_no is not null ~replacesql3 )";


                if (!territoryForm.getSadaremId().equals("")) {
                    sql1 += " and a.person_code = '" + territoryForm.getSadaremId() + "'  ";
                    sql2 += " and PENSIONID ='" + territoryForm.getSadaremId() + "' ";
                    territoryForm.setSadaremId(territoryForm.getSadaremId());
                }
                if (territoryForm.getPersoncode()!=null && !territoryForm.getPersoncode().equals("")) {
                    sql1 += " and a.pensioncard_no='" + territoryForm.getPersoncode() + "' ";
                    sql2 += " and PENSIONID ='" + territoryForm.getPersoncode() + "' ";
                }

                 if (!territoryForm.getAadharcardNumber().equals("")) {
                    sql1 += " and a.proof_id='" + territoryForm.getAadharcardNumber() + "' and proofdoc_Type in ('Adhaar Card','Aadhaar Card','Aadhaar ID','Aadhar Card') ";
                    sql2 += " and aadharcardno ='" + territoryForm.getAadharcardNumber() + "' "; 
                    territoryForm.setAadharcardNumber(territoryForm.getAadharcardNumber());
                }

                if (!territoryForm.getName().equals("")) {
                    sql1 += " and a.first_name like '%" + territoryForm.getName().trim() + "%' ";
                    sql2 += " and (firstname like '%" + territoryForm.getName().trim() + "%' or mid_name like '%" + territoryForm.getName().trim() + "%')";
                }
                if (!territoryForm.getParentsName().equals("")) {
                    sql1 += " and a.relation_name like '%" + territoryForm.getParentsName().trim() + "%' ";
                    sql2 += " and fname like '%" + territoryForm.getParentsName().trim() + "%' ";
                }
                if (!territoryForm.getDistrict_id().equalsIgnoreCase("0")) {
                    sql1 += " and a.district_Id='" + territoryForm.getDistrict_id() + "' ";
                    sql2 += " and substring(habcode,1,2)='" + territoryForm.getDistrict_id() + "'";
                    sql3 += " and districtid = '" + territoryForm.getDistrict_id() + "' ";
                }
                if (!territoryForm.getMandal_id().equalsIgnoreCase("0")) {
                    sql1 += " and a.mandal_id='" + territoryForm.getMandal_id() + "' ";
                    sql2 += " and substring(habcode,6,2)='" + territoryForm.getMandal_id() + "' ";
                    sql3 += " and mandalid = '" + territoryForm.getMandal_id() + "' ";
                }
                if (!territoryForm.getVillage_id().equalsIgnoreCase("0")) {
                    sql1 += " and a.village_id='" + territoryForm.getVillage_id() + "' ";
                    sql2 += " and substring(habcode,8,3)='" + territoryForm.getVillage_id() + "' ";
                    sql3 += " and villageid = '" + territoryForm.getVillage_id() + "' ";
                }

                if (!territoryForm.getRationCardNo().equalsIgnoreCase("")) {
                    sql1 += " and a.rationcard_number='" + territoryForm.getRationCardNo() + "' ";
                    sql2 += " and rationcardno ='" + territoryForm.getRationCardNo() + "'";
                    sql3 += " and districtid = (select distinct distcode from DISABLED_PENSION where rationcardno ='" + territoryForm.getRationCardNo() + "') ";
                }

                sql = sql.replaceAll("~replaceStr", sql1);
                sql = sql.replaceAll("~replacesql2", sql2);
                sql = sql.replaceAll("~replacesql3", sql3);
                System.out.println("sql \n"+sql);
                int i = 1;
                try
                {
                if (sql != null) 
                {
                    rs = st.executeQuery(sql);
                    while (rs.next()) 
                    {
                        HashMap<String, Object> areaData = new HashMap<String, Object>();
                        areaData.put("name", rs.getString(1));
                        areaData.put("age", rs.getString(2));
                        areaData.put("qly", rs.getString(3));
                        areaData.put("disability", rs.getString(4));
                        areaData.put("percentage", rs.getString(5));
                        areaData.put("mobile", rs.getString(6));
                        areaData.put("relation_name", rs.getString(7));
                        areaData.put("person_code", rs.getString(8));
                        areaData.put("pensioncard_no", rs.getString(9));
                        areaData.put("personStatus", rs.getString(10));
                        areaData.put("assesmentStatus", rs.getString(11));
                        areaData.put("pensionPhase", rs.getString(12));
                        areaData.put("issuedate", rs.getString(13));
                        areaData.put("certificate_type", rs.getString(14));
                        areaData.put("certificate_expire_date", rs.getString(15));
                        areaData.put("idproff_details", rs.getString(16));
                        
                        request.setAttribute("personStatusDet" + i, rs.getString(10));
                        request.setAttribute("person_code" + i, rs.getString(8));
                        areaWise.add(areaData);
                        i++;

                    }
                    if (areaWise.size() > 0) {
                        request.setAttribute("areaWise", areaWise);
                    } else {

                        if (!territoryForm.getSadaremId().equals("") || (territoryForm.getPersoncode()!=null && !territoryForm.getPersoncode().equals("") )|| !territoryForm.getAadharcardNumber().equals("")) {


                          
                            personCode = cdao.getPersionCode(ds, territoryForm.getPersoncode(), disId, territoryForm.getSadaremId(),territoryForm.getAadharcardNumber());

                          
                            if (personCode[0] != null && personCode[1].toString().trim().equalsIgnoreCase("Edit")) {
                                request.setAttribute("msg", "Part A entered");
                            } else {
                                request.setAttribute("msg", "Data Not Available");
                            }
                        }else {

                       
                            request.setAttribute("msg", "Data Not Available");
                        }
                    }
                }
                }
                catch(Exception e)
                {
                	e.printStackTrace();
                }

                if (!territoryForm.getDistrict_id().equalsIgnoreCase("0")) {
                    mandallist = territoryservice.getMandals(ds, territoryForm.getDistrict_id());
                    territoryForm.setMandallist(mandallist);
                }

                if (!territoryForm.getMandal_id().equalsIgnoreCase("0")) {
                    habitationlist = territoryservice.getVillageAll(ds, territoryForm.getDistrict_id(), territoryForm.getMandal_id());
                    territoryForm.setMandallist(mandallist);
                    territoryForm.setVillagelist(habitationlist);
                    territoryForm.setMandal_id(territoryForm.getMandal_id());
                }


                if (!territoryForm.getVillage_id().equalsIgnoreCase("0")) {
                    territoryForm.setVillage_id(territoryForm.getVillage_id());
                }





                //          }


            } else if ("getMandal".equalsIgnoreCase(territoryForm.getStatus())) {

                if (request.getParameter("district_id") != null) {
                    if (!request.getParameter("district_id").equals("0")) {
                        mandallist = territoryservice.getMandals(ds, request.getParameter("district_id"));
                        territoryForm.setMandallist(mandallist);
                        territoryForm.setMandal_id(null);
                    }
                }
            } else if ("getVillage".equalsIgnoreCase(territoryForm.getStatus())) {

                if (request.getParameter("mandal_id") != null) {
                    if (!request.getParameter("mandal_id").equals("0")) {
                        mandallist = territoryservice.getMandals(ds, request.getParameter("district_id"));
                        habitationlist = territoryservice.getVillageAll(ds, request.getParameter("district_id"), request.getParameter("mandal_id"));
                        territoryForm.setMandallist(mandallist);
                        territoryForm.setVillagelist(habitationlist);
                        territoryForm.setVillage_id(null);
                    }
                }
            }


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "excute", "PensionForSearchAction", "DataBase");
           throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PensionForSearchAction", "excute");
        } catch (Exception sqlEx) {
           
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "excute", "PensionForSearchAction", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PensionForSearchAction", "excute");
        }//end of catch block
        finally {
            try {
                if (con != null) {
                    con.close();
                    con = null;
                }
                if (st != null) {
                    st.close();
                    st = null;
                }
                if (rs != null) {
                    rs.close(); 
                    rs = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
        return mapping.findForward(target);

    }
}
