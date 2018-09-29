/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.sql.DataSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.Action;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.form.FunctionalNeedReportForm;
import org.bf.disability.service.FunctionalNeedReportService;
import org.bf.disability.servicefactory.FunctionalNeedServiceFactory;
import org.bf.disability.dao.FunctionalNeedReportDAO;

/**
 *
 * @author 490058
 */
public class TotalReport extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException {
        String target = "success";
        String districtId = null;
        String mandalId = null;
        String villageId = null;
        String emp = null;
        DataSource ds = null;
        int cou = 1;
        String edu = null, em = null, ma = null, caste = null, religion = null, disability = null, gender = null, total = null, pmarige = null;
        FunctionalNeedReportForm functionalNeedForm = (FunctionalNeedReportForm) form;
        FunctionalNeedReportDAO dao = new FunctionalNeedReportDAO();
        ArrayList habitationlist = new ArrayList();
        String rationcard = null, agee = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            String fdate = (String) request.getParameter("fromdate");
            String tdate = (String) request.getParameter("todate");

            functionalNeedForm.setFromdate(fdate);
            functionalNeedForm.setTodate(tdate);
            Iterator itr = null;

            FunctionalNeedReportService functionalNeedService =
                    FunctionalNeedServiceFactory.getFunctionalNeedServiceImpl();
            ArrayList empWiseDetails = new ArrayList();
            ArrayList empWiseSingleDetails = new ArrayList();

            ArrayList mandallist = new ArrayList();
            ArrayList villagelist = new ArrayList();
            //getDistMandVilHabname

            // Get District list and mandal List

            if (request.getParameter("district_id") != null && !request.getParameter("district_id").equals("")) {
                mandallist = functionalNeedService.getMandals(ds, request.getParameter("district_id"),"");
                functionalNeedForm.setMandallist(mandallist);
            }
            if (request.getParameter("mandal_id") != null && !request.getParameter("mandal_id").equals("")) {
                habitationlist = functionalNeedService.getVillageAll(ds, request.getParameter("district_id"), request.getParameter("mandal_id"));
                functionalNeedForm.setVillagelist(habitationlist);
            }


            if (request.getParameter("district_id") == null) {
                districtId = "0";
            } else {
                districtId = request.getParameter("district_id");
            }
            if (request.getParameter("mandal_id") == null) {
                mandalId = "0";
            } else {
                mandalId = request.getParameter("mandal_id");
            }
            if (request.getParameter("village_id") == null) {
                villageId = "0";
            } else {
                villageId = request.getParameter("village_id");
            }
            if (request.getParameter("emp") == null) {
                emp = "0";
            } else {
                emp = request.getParameter("emp");
            }

            if ("getDetails".equalsIgnoreCase(request.getParameter("mode"))) {

                String name = dao.getDistMandVilHabname(ds, districtId, mandalId, villageId, "0");
                request.setAttribute("names", name);


                empWiseDetails = dao.totalReport(ds, districtId, mandalId, villageId, fdate, tdate);





                if (empWiseDetails.size() == 0) {
                    request.setAttribute("msg", "No Data Found");

                } else {
                    itr = empWiseDetails.iterator();
                    while (itr.hasNext()) {

                        ArrayList a = (ArrayList) itr.next();
                        request.setAttribute("Totalcount" + cou++, a);

                    }


                }
                /*if (empWiseSingleDetails.size() == 0) {
                request.setAttribute("msg", "No Data Found");
                }*/

            } else if ("getempWiseReport".equalsIgnoreCase(request.getParameter("status"))) {
                String dist = null;
                String mandal = null;
                String village = null;

                String vi = request.getParameter("villagesId");
                if (request.getParameter("districtId") == null) {
                    dist = "0";
                } else {
                    dist = request.getParameter("districtId");
                }
                if (request.getParameter("mandalId") == null) {
                    mandal = "0";
                } else {
                    mandal = request.getParameter("mandalId");
                }
                if (!mandal.equals("0") && !dist.equals("0")) {
                    if (request.getParameter("villagesId") == null) {
                        village = "0";
                    } else {
                        village = request.getParameter("villagesId");
                    }
                } else {
                    village = "0";
                }
                if (request.getParameter("emp") == null) {
                    emp = "0";
                } else {
                    emp = request.getParameter("emp");
                }
                if (emp.equals("0")) {
                    // empWiseDetails = functionalNeedService.getEmpWiseDetails(ds, dist, mandal, village);
                    String name = dao.getDistMandVilHabname(ds, dist, mandal, village, "0");
                    request.setAttribute("names", name);
                    empWiseDetails = dao.totalReport(ds, dist, mandal, village, fdate, tdate);


                    //  request.setAttribute("Totalcount", empWiseDetails);

                    itr = empWiseDetails.iterator();
                    while (itr.hasNext()) {

                        ArrayList a = (ArrayList) itr.next();
                        request.setAttribute("Totalcount" + cou++, a);
                    }
                    target = "excel";
                }
            } else if ("getempWiseReportPrint".equalsIgnoreCase(request.getParameter("status"))) {
                String dist = null;
                String mandal = null;
                String village = null;
                String cast = null;
                String hab = null;
                if (request.getParameter("hid") == null) {
                    hab = "0";
                } else {
                    hab = request.getParameter("hid");
                }

                if (request.getParameter("districtId") == null) {
                    dist = "0";
                } else {
                    dist = request.getParameter("districtId");
                }
                if (request.getParameter("mandalId") == null) {
                    mandal = "0";
                } else {
                    mandal = request.getParameter("mandalId");
                }
                if (!mandal.equals("0") && !dist.equals("0")) {
                    if (request.getParameter("villagesId") == null) {
                        village = "0";
                    } else {
                        village = request.getParameter("villagesId");
                    }
                } else {
                    village = "0";
                }
                if (request.getParameter("emp") == null) {
                    emp = "0";
                } else {
                    emp = request.getParameter("emp");
                }


                if (emp.equals("0")) {
                    // empWiseDetails = functionalNeedService.getEmpWiseDetails(ds, dist, mandal, village);
                    String name = dao.getDistMandVilHabname(ds, dist, mandal, village, "0");
                    request.setAttribute("names", name);

                    empWiseDetails = dao.totalReport(ds, dist, mandal, village, fdate, tdate);
                    //request.setAttribute("Totalcount", empWiseDetails);
                    itr = empWiseDetails.iterator();
                    while (itr.hasNext()) {

                        ArrayList a = (ArrayList) itr.next();
                        request.setAttribute("Totalcount" + cou++, a);
                    }
                    target = "print";
                }
            } else if ("getDetails".equalsIgnoreCase(request.getParameter("details"))) {
                String dist = null;
                String mandal = null;
                String village = null;
                String refId = null;
                if (request.getParameter("dID") == null) {
                    dist = "0";
                } else {
                    dist = request.getParameter("dID");
                }
                if (request.getParameter("mID") == null || request.getParameter("mID") == "") {
                    mandal = "0";
                } else {
                    mandal = request.getParameter("mID");
                }
                if (!mandal.equals("0") && !dist.equals("0")) {
                    if (request.getParameter("vID") == null || request.getParameter("vID") == "") {
                        village = "0";
                    } else {
                        village = request.getParameter("vID");
                    }
                } else {
                    village = "0";
                }
                /* if (request.getParameter("emp") == null) {
                emp = "0";
                } else {
                emp = request.getParameter("emp");
                }
                if (request.getParameter("refID") == null) {
                refId = "0";
                } else {
                refId = request.getParameter("refID");
                }
                if (request.getParameter("getSingle") == null) {
                refId = "0";
                } else {
                refId = request.getParameter("getSingle");
                }*/
                String hab = null;
                if (request.getParameter("hid") == null || request.getParameter("hid") == "") {
                    hab = "0";
                } else {
                    hab = request.getParameter("hid");
                }


                String name = dao.getDistMandVilHabname(ds, dist, mandal, village, "0");
                request.setAttribute("names", name);



                em = request.getParameter("Employment");
                edu = request.getParameter("education");
                caste = request.getParameter("caste");
                ma = request.getParameter("marrtialstatus");
                religion = request.getParameter("religion");
                disability = request.getParameter("disability");
                gender = request.getParameter("gender");

                pmarige = request.getParameter("pmarige");
                rationcard = request.getParameter("ration");
                agee = request.getParameter("agee");


                total = request.getParameter("total");
                String n = null;

                //   request.setAttribute("check", "value");

                String type = null;
                int typeof = 0;
                if (gender != null) {

                    n = "gender=" + gender + "&";
                    if (em != null) {
                        typeof = Integer.parseInt(em);
                        n = n + "Employment=" + em;
                        if (typeof == 1) {
                            type = "Employment Wise:Govt";
                        } else if (typeof == 2) {
                            type = "Employment Wise-Private";
                        } else if (typeof == 3) {
                            type = "Employment Wise-Self Employed";
                        } else if (typeof == 4) {
                            type = "Employment Wise-Un Empoloyed";
                        } else if (typeof == 5) {
                            type = "Employment Wise-Wage Employee";
                        } else if (typeof == 0) {
                            type = "Employment Not Entered";
                        }






                    } else if (edu != null) {
                        n = n + "education=" + edu;
                        typeof = Integer.parseInt(edu);

                        if (typeof == 1) {
                            type = "Education Wise-Illeterate";
                        } else if (typeof == 2) {
                            type = "Education Wise-Belowtenth";
                        } else if (typeof == 3) {
                            type = "Education Wise-10th";
                        } else if (typeof == 4) {
                            type = "Education Wise-Intermediate";
                        } else if (typeof == 5) {
                            type = "Education Wise-Diploma";
                        } else if (typeof == 6) {
                            type = "Education Wise-Graduate";
                        } else if (typeof == 7) {
                            type = "Education Wise-PG";
                        } else if (typeof == 0) {
                            type = "Education Not Entered";
                        }





                    } else if (caste != null) {
                        n = n + "caste=" + caste;
                        typeof = Integer.parseInt(caste);
                        if (typeof == 1) {
                            type = "Caste Wise-ST";
                        } else if (typeof == 2) {
                            type = "Caste Wise-SC";
                        } else if (typeof == 3) {
                            type = "Caste Wise-BC";
                        } else if (typeof == 4) {
                            type = "Caste Wise-OC";
                        } else if (typeof == 5) {
                            type = "Caste Wise-Minarity";
                        } else if (typeof == 6) {
                            type = "Caste Wise-NA";
                        } else if (typeof == 0) {
                            type = "Caste Not Entered";
                        }


                    } else if (ma != null) {
                        n = n + "marrtialstatus=" + ma;
                        typeof = Integer.parseInt(ma);
                        if (typeof == 1) {
                            type = "Marital Status Wise-Married";
                        } else if (typeof == 2) {
                            type = "Marital Status Wise-Un Married";
                        } else if (typeof == 3) {
                            type = "Marital Status Wise-Divorcee";
                        } else if (typeof == 4) {
                            type = "Marital Status Wise-Widow";
                        } else if (typeof == 5) {
                            type = "Marital Status Wise-Widower";
                        }


                    } else if (religion != null) {
                        n = n + "religion=" + religion;
                        typeof = Integer.parseInt(religion);
                        if (typeof == 1) {
                            type = "Religion Wise-Muslim-Hindhu";
                        } else if (typeof == 2) {
                            type = "Religion Wise-Muslim";
                        } else if (typeof == 3) {
                            type = "Religion Wise-Christian";
                        } else if (typeof == 4) {
                            type = "Religion Wise-Sikh";
                        } else if (typeof == 5) {
                            type = "Religion Wise-Jain";
                        } else if (typeof == 6) {
                            type = "Religion Wise-Budhist";
                        } else if (typeof == 7) {
                            type = "Religion Wise-Others";
                        } else if (typeof == 0) {
                            type = "Religion Not Entered";
                        }



                    } else if (disability != null) {
                        n = n + "disability=" + disability;
                        typeof = Integer.parseInt(disability);
                        if (typeof == 1) {
                            type = "Disability Wise-Locomotor Disability";
                        } else if (typeof == 2) {
                            type = "Disability Wise-Visual Imapirment";
                        } else if (typeof == 3) {
                            type = "Disability Wise-Hearing Impairment";
                        } else if (typeof == 4) {
                            type = "Disability Wise-Mental Retardation";
                        } else if (typeof == 5) {
                            type = "Disability Wise-Mental Illness";
                        } else if (typeof == 6) {
                            type = "Disability Wise-Multiple Disabilities ";
                        }



                    } else if (pmarige != null) {
                        n = n + "pmarige=" + pmarige;
                        typeof = Integer.parseInt(pmarige);
                        if (typeof == 1) {
                            type = "Consanguineous Marriage Parents Wise-Yes";
                        } else if (typeof == 2) {
                            type = "Consanguineous Marriage Parents Wise-No";
                        } else if (typeof == 0) {
                            type = "Consanguineous Marriage Parents - Not Entered";
                        }
                    } else if (rationcard != null) {
                        n = "ration=" + rationcard;
                        typeof = Integer.parseInt(rationcard);

                        if (typeof == 1) {
                            type = "Rationcard Wise-White";
                        } else if (typeof == 2) {
                            type = "Rationcard Wise-Pink";
                        } else if (typeof == 3) {
                            type = "Rationcard Wise-AAY";
                        } else if (typeof == 0) {
                            type = "Rationcard Not Entered";
                        }



                    } else if (agee != null) {
                        n = "agee=" + agee;
                        typeof = Integer.parseInt(agee);
                        if (typeof == 1) {
                            type = "AGE Wise-0-3";
                        } else if (typeof == 2) {
                            type = "AGE Wise-3-5";
                        } else if (typeof == 3) {
                            type = "AGE Wise-5-14";
                        } else if (typeof == 4) {
                            type = "AGE Wise-14-18";
                        } else if (typeof == 5) {
                            type = "AGE Wise-18-25";
                        } else if (typeof == 6) {
                            type = "AGE Wise-25-35";
                        } else if (typeof == 7) {
                            type = "AGE Wise-35-50";
                        } else if (typeof == 8) {
                            type = "AGE Wise-50-60";
                        } else if (typeof == 9) {
                            type = "AGE Wise-60-70";
                        } else if (typeof == 10) {
                            type = "AGE Wise-above 70";
                        }

                    }






                } else {

                    if (em != null) {
                        n = "Employment=" + em;
                        typeof = Integer.parseInt(em);
                        if (typeof == 1) {
                            type = "Employment Wise-Govt";
                        } else if (typeof == 2) {
                            type = "Employment Wise-Private";
                        } else if (typeof == 3) {
                            type = "Employment Wise-Self Employed";
                        } else if (typeof == 4) {
                            type = "Employment Wise-Un Empoloyed";
                        } else if (typeof == 5) {
                            type = "Employment Wise-Wage Employee";
                        } else if (typeof == 0) {
                            type = "Employment Not Entered";
                        }


                    } else if (edu != null) {
                        n = "education=" + edu;
                        typeof = Integer.parseInt(edu);
                        if (typeof == 1) {
                            type = "Education Wise-Illeterate";
                        } else if (typeof == 2) {
                            type = "Education Wise-Belowtenth";
                        } else if (typeof == 3) {
                            type = "Education Wise-10th";
                        } else if (typeof == 4) {
                            type = "Education Wise-Intermediate";
                        } else if (typeof == 5) {
                            type = "Education Wise-Diploma";
                        } else if (typeof == 6) {
                            type = "Education Wise-Graduate";
                        } else if (typeof == 7) {
                            type = "Education Wise-PG";
                        } else if (typeof == 0) {
                            type = "Education Not Entered";
                        }

                    } else if (caste != null) {
                        n = "caste=" + caste;
                        typeof = Integer.parseInt(caste);
                        if (typeof == 1) {
                            type = "Caste Wise-ST";
                        } else if (typeof == 2) {
                            type = "Caste Wise-SC";
                        } else if (typeof == 3) {
                            type = "Caste Wise-BC";
                        } else if (typeof == 4) {
                            type = "Caste Wise-OC";
                        } else if (typeof == 5) {
                            type = "Caste Wise-Minarity";
                        } else if (typeof == 6) {
                            type = "Caste Wise-NA";
                        } else if (typeof == 0) {
                            type = "Caste Not Entered";
                        }
                    } else if (ma != null) {
                        n = "marrtialstatus=" + ma;
                        typeof = Integer.parseInt(ma);
                        if (typeof == 1) {
                            type = "Marital Status Wise-Married";
                        } else if (typeof == 2) {
                            type = "Marital Status Wise-Un Married";
                        } else if (typeof == 3) {
                            type = "Marital Status Wise-Divorcee";
                        } else if (typeof == 4) {
                            type = "Marital Status Wise-Widow";
                        } else if (typeof == 5) {
                            type = "Marital Status Wise-Widower";
                        }
                    } else if (religion != null) {
                        n = "religion=" + religion;
                        typeof = Integer.parseInt(religion);
                        if (typeof == 1) {
                            type = "Religion Wise-Hindhu";
                        } else if (typeof == 2) {
                            type = "Religion Wise-Muslim";
                        } else if (typeof == 3) {
                            type = "Religion Wise-Christian";
                        } else if (typeof == 4) {
                            type = "Religion Wise-Sikh";
                        } else if (typeof == 5) {
                            type = "Religion Wise-Jain";
                        } else if (typeof == 6) {
                            type = "Religion Wise-Budhist";
                        } else if (typeof == 7) {
                            type = "Religion Wise-Others";
                        } else if (typeof == 0) {
                            type = "Religion Not Entered";
                        }
                    } else if (disability != null) {
                        n = "disability=" + disability;
                        typeof = Integer.parseInt(disability);
                        if (typeof == 1) {
                            type = "Disability Wise-Locomotor Disability";
                        } else if (typeof == 2) {
                            type = "Disability Wise-Visual Imapirment";
                        } else if (typeof == 3) {
                            type = "Disability Wise-Hearing Impairment";
                        } else if (typeof == 4) {
                            type = "Disability Wise-Mental Retardation";
                        } else if (typeof == 5) {
                            type = "Disability Wise-Mental Illness";
                        } else if (typeof == 6) {
                            type = "Disability Wise-Multiple Disabilities ";
                        }
                    } else if (pmarige != null) {
                        n = "pmarige=" + pmarige;
                        typeof = Integer.parseInt(pmarige);
                        if (typeof == 1) {
                            type = "Consanguineous Marriage Parents Wise";
                        } else if (typeof == 0) {
                            type = "Consanguineous Marriage Parents Wise-No";
                        } else if (typeof == 2) {
                            type = "Consanguineous Marriage Parents - Not Entered";
                        }
                    } else if (rationcard != null) {
                        n = "ration=" + rationcard;
                        typeof = Integer.parseInt(rationcard);

                        if (typeof == 1) {
                            type = "Rationcard Wise-White";
                        } else if (typeof == 2) {
                            type = "Rationcard Wise-Pink";
                        } else if (typeof == 3) {
                            type = "Rationcard Wise-AAY";
                        } else if (typeof == 0) {
                            type = "Rationcard Not Entered";
                        }



                    } else if (agee != null) {
                        n = "agee=" + agee;
                        typeof = Integer.parseInt(agee);
                        if (typeof == 1) {
                            type = "AGE Wise-0-3";
                        } else if (typeof == 2) {
                            type = "AGE Wise-3-5";
                        } else if (typeof == 3) {
                            type = "AGE Wise-5-14";
                        } else if (typeof == 4) {
                            type = "AGE Wise-14-18";
                        } else if (typeof == 5) {
                            type = "AGE Wise-18-25";
                        } else if (typeof == 6) {
                            type = "AGE Wise-25-35";
                        } else if (typeof == 7) {
                            type = "AGE Wise-35-50";
                        } else if (typeof == 8) {
                            type = "AGE Wise-50-60";
                        } else if (typeof == 9) {
                            type = "AGE Wise-60-70";
                        } else if (typeof == 10) {
                            type = "AGE Wise-above 70";
                        }

                    }


                }


                request.setAttribute("values", n);
                request.setAttribute("type", type);





                empWiseDetails = dao.getPersonalDetails(ds, dist, mandal, village, hab, fdate, tdate, em, edu, caste, ma, religion, disability, gender, total, pmarige, rationcard, agee);
                request.setAttribute("empWiseDetails", empWiseDetails);


                /*  if (refId.equals("single")) {
                target = "personalSingle";
                } else {
                target = "personal";
                }*/
                target = "personal";
            } else if ("getempDetailsAction".equalsIgnoreCase(request.getParameter("empStatus"))) {
                String dist = null;
                String mandal = null;
                String village = null;
                String refId = null;
                if (request.getParameter("dID") == null) {
                    dist = "0";
                } else {
                    dist = request.getParameter("dID");
                }
                if (request.getParameter("mID") == null || request.getParameter("mID") == "") {
                    mandal = "0";
                } else {
                    mandal = request.getParameter("mID");
                }
                if (!mandal.equals("0") && !dist.equals("0")) {
                    if (request.getParameter("vID") == null || request.getParameter("vID") == "") {
                        village = "0";
                    } else {
                        village = request.getParameter("vID");
                    }
                } else {
                    village = "0";
                }
                /*  if (request.getParameter("emp") == null) {
                emp = "0";
                } else {
                emp = request.getParameter("emp");
                }
                if (request.getParameter("refID") == null) {
                refId = "0";
                } else {
                refId = request.getParameter("refID");
                }*/
                String hab = null;
                if (request.getParameter("hid") == null || request.getParameter("hid") == "") {
                    hab = "0";
                } else {
                    hab = request.getParameter("hid");
                }
                // empWiseDetails = functionalNeedService.getPersonalempWiseDetails(ds, dist, mandal, village, emp);

                String name = dao.getDistMandVilHabname(ds, dist, mandal, village, "0");
                request.setAttribute("names", name);

                em = request.getParameter("Employment");
                edu = request.getParameter("education");
                caste = request.getParameter("caste");
                ma = request.getParameter("marrtialstatus");
                religion = request.getParameter("religion");
                disability = request.getParameter("disability");
                gender = request.getParameter("gender");
                total = request.getParameter("total");
                pmarige = request.getParameter("pmarige");
                rationcard = request.getParameter("ration");
                agee = request.getParameter("agee");
                String n = null;

                //empWiseDetails = dao.getPersonalDisWiseDetails(ds, dist, mandal, village, emp,hab, fdate,tdate);
                empWiseDetails = dao.getPersonalDetails(ds, dist, mandal, village, hab, fdate, tdate, em, edu, caste, ma, religion, disability, gender, total, pmarige, rationcard, agee);
                request.setAttribute("empWiseDetails", empWiseDetails);
                // request.setAttribute("empWiseDetails", empWiseDetails);

                target = "EmpWisePersonalDetailsExcel";
            }
        } catch (SQLException sqlEx) {
            target = "exception";
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "execute", "TotalReport", "DataBase");
            //throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TotalReport", "execute");
        } catch (Exception sqlEx) {
            target = "exception";
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "execute", "TotalReport", "Code");
            //throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TotalReport", "execute");
        }
        return mapping.findForward(target);
    }
}
