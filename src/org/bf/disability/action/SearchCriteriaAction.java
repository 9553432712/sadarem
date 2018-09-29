/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dto.TerritoryDTO;
import org.bf.disability.form.SearchCriteriaForm;
import org.bf.disability.service.TerritoryService;
import org.bf.disability.servicefactory.TerritoryServiceFactory;

import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author 509862
 */
public class SearchCriteriaAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException {
        SearchCriteriaForm searchCriteriaForm = (SearchCriteriaForm) form;

        DataSource ds = null;
        Connection con = null;
        String sql = null;
        Statement st = null;
        ResultSet rs = null;
        ArrayList ageData = new ArrayList();
        ArrayList districtlist = new ArrayList();
        ArrayList mandallist = new ArrayList();
        ArrayList villagelist = new ArrayList();
        ArrayList habitationlist = new ArrayList();
        TerritoryService territoryservice =
                TerritoryServiceFactory.getTerritoryServiceImpl();
        TerritoryDTO territorySelect = null;
        HttpSession session = request.getSession();
        try {

            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            con = DBConnection.getConnection();
            //  con = DBConnection.getConnection(datasource);
            st = con.createStatement();

            String status = request.getParameter("status");
            if (status == null) {
                status = "status";
            }

            districtlist = territoryservice.getDistricts(ds);
            searchCriteriaForm.setDistrictlist(districtlist);
            
            if (status.equals("update")) {



                if (searchCriteriaForm.getDistrict_id() != null && searchCriteriaForm.getDistrict_id() != "") {
                    mandallist = territoryservice.getMandals(ds, searchCriteriaForm.getDistrict_id());
                    searchCriteriaForm.setMandallist(mandallist);
                }

                /** get village data based on mandal selection */
                if (searchCriteriaForm.getMandal_id() != null) {
                    String vi = null;
                    for (int j = 0; j < searchCriteriaForm.getMandal_id().length; j++) {
                        vi = searchCriteriaForm.getMandal_id()[j];
                    }

                   
                        if ( searchCriteriaForm.getMandal_id().length != 0 && !vi.equals("0")) {
                            String query = null;
                            String[] mandalData = searchCriteriaForm.getMandal_id();
                            for (int i = 0; i < mandalData.length; i++) {
                                query = "select village_id,village_name from tblVillage_Details where district_id='" + searchCriteriaForm.getDistrict_id() + "' and  mandal_id='" + mandalData[i] + "' order by village_name";

                                rs = st.executeQuery(query);
                                while (rs.next()) {
                                    TerritoryDTO territory = new TerritoryDTO();
                                    territory.setVillage_id(rs.getString("village_id"));
                                    territory.setVillage_name(rs.getString("village_name"));
                                    villagelist.add(territory);
                                }
                            }

                            searchCriteriaForm.setVillagelist(villagelist);
                        }
                    
                   
                        if (vi.equals("0")) {
                            String query = "select village_id,village_name from tblVillage_Details where district_id='" + searchCriteriaForm.getDistrict_id() + "' and village_name!='null' order by village_name";

                            rs = st.executeQuery(query);
                            while (rs.next()) {
                                TerritoryDTO territory = new TerritoryDTO();
                                territory.setVillage_id(rs.getString("village_id"));
                                territory.setVillage_name(rs.getString("village_name"));
                                villagelist.add(territory);
                            }
                            searchCriteriaForm.setVillagelist(villagelist);
                        
                    }
                }


                if (searchCriteriaForm.getVillage_id() != null) {
                    String hab = null;
                    for (int j = 0; j < searchCriteriaForm.getVillage_id().length; j++) {
                        hab = searchCriteriaForm.getVillage_id()[j];
                    }
                     
                        if (searchCriteriaForm.getVillage_id().length != 0 && !hab.equals("0")) {
                            String query = null;
                            String village = "0";
                            String mandal = "0";
                            String[] villageData = searchCriteriaForm.getVillage_id();
                            String[] mandalData = searchCriteriaForm.getMandal_id();

                            for (int i = 0; i < mandalData.length; i++) {
                                if (mandalData[i].length() == 1) {
                                    mandal = mandalData[i];
                                } else {
                                    mandal = mandal + "','" + mandalData[i];
                                }
                                if (mandalData[i].equals("0")) {
                                    for (Iterator iter = mandallist.iterator(); iter.hasNext();) {
                                        territorySelect = (TerritoryDTO) iter.next();
                                        mandal = mandal + "','" + territorySelect.getMandal_id();
                                    }
                                }
                            }

                            for (int i = 0; i < villageData.length; i++) {
                                if (villageData[i].length() == 1) {
                                    village = villageData[i];
                                } else {
                                    village = village + "','" + villageData[i];
                                }
                                if (villageData[i].equals("0")) {
                                    for (Iterator iter = villagelist.iterator(); iter.hasNext();) {
                                        territorySelect = (TerritoryDTO) iter.next();
                                        village = village + "','" + territorySelect.getVillage_id();
                                    }
                                }
                            }

                            query = "select distinct habitation_id,habitation_name from tblHabitation_Details where district_id='" + searchCriteriaForm.getDistrict_id() + "' and  mandal_id in('" + mandal + "')  and village_id in('" + village + "')  and status='Active' order by habitation_name";

                            rs = st.executeQuery(query);
                            while (rs.next()) {
                                TerritoryDTO territory = new TerritoryDTO();
                                territory.setHabitation_id(rs.getString("habitation_id"));
                                territory.setHabitation_name(rs.getString("habitation_name"));
                                habitationlist.add(territory);
                            }

                            searchCriteriaForm.setHabitationlist(habitationlist);
                        }
                    

                    /** if user select the ALL option in  mandals selection */
                     
                        if (hab.equals("0")) {
                            String query = "select distinct habitation_id,habitation_name from tblHabitation_Details where district_id='" + searchCriteriaForm.getDistrict_id() + "' and habitation_name!='null' and status='Active' order by habitation_name";

                            rs = st.executeQuery(query);
                            while (rs.next()) {
                                TerritoryDTO territory = new TerritoryDTO();
                                territory.setHabitation_id(rs.getString("habitation_id"));
                                territory.setHabitation_name(rs.getString("habitation_name"));
                                habitationlist.add(territory);
                            }

                            searchCriteriaForm.setHabitationlist(habitationlist);
                        }
                    
                }
                searchCriteriaForm.setDistrictlist(districtlist);
            }

            if ("getDetails".equalsIgnoreCase(searchCriteriaForm.getMode())) {

                String ageFrom = null;
                String ageTo = null;
                String gender = null;
                String qly = "17";
                String dis = "18";
                String districtId = null;
                String mandalId = "0";
                String villageId = "0";
                String habitationId = "0";

                /**if age is between the entered range */
                if (!searchCriteriaForm.getFrom().equals("") && !searchCriteriaForm.getTo().equals("")) {
                    ageFrom = searchCriteriaForm.getFrom();
                    ageTo = searchCriteriaForm.getTo();
                } else {
                    ageFrom = "0";
                    ageTo = "0";
                }

                /** if the user select the gender */
                if (!searchCriteriaForm.getGender().equals("select") && !searchCriteriaForm.getGender().equals("0")) {
                    gender = searchCriteriaForm.getGender();
                } else {
                    gender = "-";
                }
                if (searchCriteriaForm.getGender().equals("0")) {
                    gender = "1','2";
                }

                /** if the user select the disability */
                String[] typeDisability = searchCriteriaForm.getType_disability();

                if (searchCriteriaForm.getType_disability() != null) {
                    for (int i = 0; i < typeDisability.length; i++) {

                        if (typeDisability.length == 1) {
                            dis = typeDisability[i];
                        } else {
                            dis = dis + "','" + typeDisability[i];
                        }
                        if (typeDisability[i].equals("All")) {
                            dis = "1','2','3','4','5','6";
                        }
                    }
                }
                /** if the user select the disability */
                String[] qualification = searchCriteriaForm.getQualification();

                if (searchCriteriaForm.getQualification() != null) {
                    for (int i = 0; i < qualification.length; i++) {
                        if (qualification.length == 1) {
                            qly = qualification[i];
                        } else {
                            qly = qly + "','" + qualification[i];
                        }
                        if (qualification[i].equals("All")) {
                            qly = "2','3','4','5','6','7";
                        }
                    }
                }

                /** if the user select the District*/
                if (searchCriteriaForm.getDistrict_id() != null) {
                    districtId = searchCriteriaForm.getDistrict_id();
                }

                /** if the user select the Mandal */
                if (searchCriteriaForm.getMandal_id() != null) {
                    String[] madals = searchCriteriaForm.getMandal_id();
                    if (searchCriteriaForm.getMandal_id().length != 0) {
                        for (int i = 0; i < madals.length; i++) {
                            if (madals[i].length() == 1) {
                                mandalId = madals[i];
                            } else {
                                mandalId = mandalId + "','" + madals[i];
                            }
                            if (madals[i].equals("0")) {
                                for (Iterator iter = mandallist.iterator(); iter.hasNext();) {
                                    territorySelect = (TerritoryDTO) iter.next();
                                    mandalId = mandalId + "','" + territorySelect.getMandal_id();
                                }
                            }
                        }
                    }
                }

                /** if the user select the village */
                if (searchCriteriaForm.getVillage_id() != null) {
                    String[] village = searchCriteriaForm.getVillage_id();
                    if (village.length != 0) {
                        for (int i = 0; i < village.length; i++) {
                            if (village[i].length() == 1) {
                                villageId = village[i];
                            } else {
                                villageId = villageId + "','" + village[i];
                            }
                            if (village[i].equals("0")) {
                                for (Iterator iter = villagelist.iterator(); iter.hasNext();) {
                                    territorySelect = (TerritoryDTO) iter.next();
                                    villageId = villageId + "','" + territorySelect.getVillage_id();
                                }
                            }
                        }
                    }
                }

                /** if the user select the habitation */
                if (searchCriteriaForm.getHabitation_id() != null) {
                    String[] habitation = searchCriteriaForm.getHabitation_id();
                    if (habitation.length != 0) {
                        for (int i = 0; i < habitation.length; i++) {
                            if (habitation[i].length() == 1) {
                                habitationId = habitation[i];
                            } else {
                                habitationId = habitationId + "','" + habitation[i];
                            }
                            if (habitation[i].equals("0")) {
                                for (Iterator iter = habitationlist.iterator(); iter.hasNext();) {
                                    territorySelect = (TerritoryDTO) iter.next();
                                    habitationId = habitationId + "','" + territorySelect.getHabitation_id();
                                }
                            }
                        }
                    }
                }

                int a = 1;
                if (!searchCriteriaForm.getDistrict_id().equals("0")) {

                    districtId = searchCriteriaForm.getDistrict_id();
                    sql = "select a.Surname +' '+ a.First_Name as name,DATEDIFF(year,a.Date_of_Birth,GETDATE()) as age,"
                            + "case when a.Education=1 then 'Illiterate' when a.Education=2 then 'Below 10th' "
                            + "when a.Education=3 then '10th Class' when a.Education=4 then 'Intermediate' " 
                            + "when a.Education=5 then 'Diploma/ITI' when a.Education=6 then 'Graduate' "
                            + "when a.Education=7 then 'Post Graduate' else 'Illiterate' end as qly,"
                            + "c.disability_name as disability,"
                            + "coalesce(d.totaldisability,'0') as percentage,v.person_contactno as mobile,"
                            + "a.Person_Code,a.PensionCard_No,a.relation_name,v.district_name,v.mandal_name,v.village_name from dbo.tblPerson_Personal_Details a  with (nolock) "
                            + "join sadarem_view_complete_details v with (nolock) on (a.person_code = v.sadarem_id) "
                            + "join dbo.tblPerson_Disability_Details b on(v.sadarem_id=b.person_code)"
                            + "join dbo.tblDisability_Details c on(b.disability_id=c.disability_id) "
                            + "join dbo.tblPerson_Disability_TotalValue_Details d on(a.person_code=d.person_code) "
                            + "and a.status='Active' and b.status='Active' and d.status='Active' and  ";

                    if (!searchCriteriaForm.getFrom().equals("") && !searchCriteriaForm.getTo().equals("")) {
                        sql += "DATEDIFF(year,a.Date_of_Birth,GETDATE())  between '" + ageFrom + "' and '" + ageTo + "' and ";
                    }
                    if (!searchCriteriaForm.getGender().equals("select") && !searchCriteriaForm.getGender().equals("0")) {
                        sql += "a.Gender in ('" + gender + "') and ";
                    }
                    if (searchCriteriaForm.getGender().equals("0")) {
                        sql += "a.Gender in ('" + gender + "') and ";
                    }
                    if (searchCriteriaForm.getType_disability() != null) {
                        sql += " b.disability_id in('" + dis + "') and";
                    }
                    if (searchCriteriaForm.getQualification() != null) {
                        sql += " a.Education in('" + qly + "') and ";
                    }

                    sql += " a.District_ID='" + districtId + "'  ";

                    if (searchCriteriaForm.getMandal_id() != null && !searchCriteriaForm.getMandal_id().equals("")) {
                        sql += "and a.Mandal_ID in('" + mandalId + "')";
                    }
                    if (searchCriteriaForm.getVillage_id() != null && !searchCriteriaForm.getVillage_id().equals("")) {
                        sql += " and a.Village_ID in('" + villageId + "')";
                    }
                    if (searchCriteriaForm.getHabitation_id() != null && !searchCriteriaForm.getHabitation_id().equals("")) {
                        if (districtId.equals("16") && mandalId.contains("84") && villageId.contains("004") && habitationId.contains("01")) {
                            sql += " and a.Habitation_ID in('00','" + habitationId + "')";
                        } else {
                            sql += " and a.Habitation_ID in('" + habitationId + "')";
                        }
                    }
                    sql += "order by a.Age_Years,a.Education,a.PensionCard_No,a.Person_Code";


                } else if (searchCriteriaForm.getDistrict_id().equals("0")) {

                    sql = "select a.Surname +' '+ a.First_Name as name,DATEDIFF(year,a.Date_of_Birth,GETDATE()) as age,"
                            + "case when a.Education=1 then 'Illiterate' when a.Education=2 then 'Below 10th' "
                            + "when a.Education=3 then '10th Class' when a.Education=4 then 'Intermediate' "
                            + "when a.Education=5 then 'Diploma/ITI' when a.Education=6 then 'Graduate' "
                            + "when a.Education=7 then 'Post Graduate' else 'Illiterate' end as qly,"
                            + "c.disability_name as disability,"
                            + "coalesce(d.totaldisability,'0') as percentage,v.person_contactno as mobile,a.Person_Code,"
                            + "a.PensionCard_No,a.relation_name,v.district_name,v.mandal_name,v.village_name from dbo.tblPerson_Personal_Details a  with (nolock) "
                            + "join sadarem_view_complete_details v with (nolock) on (a.person_code = v.sadarem_id) "
                            + "join dbo.tblPerson_Disability_Details b on(v.sadarem_id=b.person_code)"
                            + "join dbo.tblDisability_Details c on(b.disability_id=c.disability_id) "
                            + "join dbo.tblPerson_Disability_TotalValue_Details d on(a.person_code=d.person_code) "
                            + "and a.status='Active' and b.status='Active' and d.status='Active' and ";

                    if (!searchCriteriaForm.getFrom().equals("") && !searchCriteriaForm.getTo().equals("")) {
                        sql += "DATEDIFF(year,a.Date_of_Birth,GETDATE()) between '" + ageFrom + "' and '" + ageTo + "'";
                        a = 2;
                    }
                    if (!searchCriteriaForm.getGender().equals("select") && !searchCriteriaForm.getGender().equals("0")) {
                        if (a == 2) {
                            sql += " and ";
                        }
                        sql += " a.Gender in ('" + gender + "')";
                        a = 3;
                    }
                    if (searchCriteriaForm.getGender().equals("0")) {
                        if (a == 2 || a == 3) {
                            sql += " and ";
                        }

                        sql += "a.Gender in ('" + gender + "')";
                        a = 4;
                    }
                    if (searchCriteriaForm.getType_disability() != null) {
                        if (a == 2 || a == 3 || a == 4) {
                            sql += " and ";
                        }
                        sql += "b.disability_id in('" + dis + "') ";
                        a = 5;
                    }
                    if (searchCriteriaForm.getQualification() != null) {
                        if (a == 2 || a == 3 || a == 4 || a == 5) {
                            sql += " and ";
                        }
                        sql += " a.Education in('" + qly + "') ";
                    }
                    sql += " order by a.Age_Years,a.Education,a.PensionCard_No,a.Person_Code";

                }
                rs = st.executeQuery(sql);
                while (rs.next()) {
                    HashMap ageDataMap = new HashMap();
                    ageDataMap.put("name", rs.getString(1));
                    ageDataMap.put("age", rs.getString(2));
                    ageDataMap.put("qly", rs.getString(3));
                    ageDataMap.put("disability", rs.getString(4));
                    ageDataMap.put("percentage", rs.getString(5));
                    ageDataMap.put("mobile", rs.getString(6));
                    ageDataMap.put("Person_Code", rs.getString(7));
                    ageDataMap.put("PensionCard_No", rs.getString(8));
                    ageDataMap.put("relation_name", rs.getString(9));
                    ageDataMap.put("district_name", rs.getString(10));
                    ageDataMap.put("mandal_name", rs.getString(11));
                    ageDataMap.put("village_name", rs.getString(12));
                    ageData.add(ageDataMap);
                }
                if (ageData.size() > 0) {
                    request.setAttribute("ageData", ageData);
                } else {
                    request.setAttribute("noData", "Details are not available of selected option");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                DBConnection.closeConnection(con);
                DBConnection.closeResultSet(rs);
                DBConnection.closeStatement(st);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return mapping.findForward("success");
    }
}
