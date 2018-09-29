/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

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
import org.bf.disability.Exception.BaseAction;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.CommonDetails;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.PartADAO;
import org.bf.disability.dto.AppealPersonalDataDTO;
import org.bf.disability.form.PartAForm;

import com.tcs.sadarem.util.CommonUtility;

/**
 *
 * @author 509862
 */
public class AppealAction extends BaseAction {
    /* forward name="success" path="" */

    private static String target = "success";

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws SADAREMDBException, SQLException 
    {
        HttpSession session = request.getSession();
        ArrayList list = new ArrayList();
        String loginid = (String) session.getAttribute("loginid");
        PartAForm partAForm = (PartAForm) form;
        AppealPersonalDataDTO dto = new AppealPersonalDataDTO();
        AppealPersonalDataDTO dto1 = new AppealPersonalDataDTO();
        PartADAO dao = new PartADAO();
        DataSource datasource = null;
       
        ActionMessages actionMessages = null;
        boolean districtLevelAccessFlag = false;
        boolean adminLevelAccessFlag = false;
        String districtid = null;
        try { datasource = getDataSource(request);
        if (datasource == null || "null".equals(datasource)) 
        {
            datasource = JNDIDataSource.getConnection();
        }

            String pid = request.getParameter("pensioncode");
            if (pid == null)
            {
                target = "success";
            } 
            else
            {
                String mode = (String) request.getParameter("mode");
                
                if (mode != null && mode.equalsIgnoreCase("mode")) 
                {
                    String msg = "Not Entered";//dto.setAge(msg);
                    dto.setPercentage(partAForm.getPercentage());
                    dto.setPersoncode(partAForm.getPersoncode());
                    dto.setDisability(partAForm.getDisability());
                    dto.setCategoryid(partAForm.getCategoryid());
                    dto.setStatus(partAForm.getStatus());
                    dto.setRationcard(partAForm.getRationcard());
                    dto.setSid(partAForm.getSid());
                    dto1 = dao.getPersonalDetailsForAppealAuthority(datasource, pid);
                    String errormsg = dto1.getError_msg();
                    if (errormsg != null && errormsg.equalsIgnoreCase("NO"))
                    {
                        int inserted = dao.insertPersonalDetailsForAppeal(datasource, dto, loginid);
                        if (inserted >= 1) 
                        {
                            msg = "Successfully Inserted Data";
                        }
                        else 
                        {
                            msg = "Not Entered";
                        }
                    }
                    request.setAttribute("msg", msg);
                    target = "success";
                } 
                else 
                {
                    request.setAttribute("value", "value");
                    request.setAttribute("per", pid);
                    HashMap h = new HashMap();
                    if (pid != null) 
                    {
                        CommonDetails commondetails = new CommonDetails();
                        int currentRoleId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("roleId")));
                        adminLevelAccessFlag = commondetails.campareRoleIds(String.valueOf(currentRoleId), CommonConstants.ADMIN_PERMISSION_IDS);
                        //    boolean adminLevelAccessFlag = commondetails.campareRoleIds(String.valueOf(currentRoleId), CommonConstants.ADMIN_PERMISSION_IDS);
                       
                        if (!adminLevelAccessFlag)
                        {
                            districtid = (String) session.getAttribute("districtId");
                            districtLevelAccessFlag = commondetails.checkDistrictFlag(pid, districtid);
                        }
                    }
                    if (districtLevelAccessFlag || adminLevelAccessFlag) 
                    {
                        dto = dao.getPersonalDetailsForAppealAuthority(datasource, pid);
                        String errormsg = dto.getError_msg();
                        if (errormsg != null && errormsg.equalsIgnoreCase("NO")) 
                        {
                            String type = dto.getDisability();
                            if (type != null)
                            {
                                if (type.equalsIgnoreCase("1"))
                                {
                                    type = "Locomotor/OH";
                                } 
                                else if (type.equalsIgnoreCase("2"))
                                {
                                    type = "Visual Impairment ";
                                }
                                else if (type.equalsIgnoreCase("3")) 
                                {
                                    type = "Hearing Impairment";
                                } 
                                else if (type.equalsIgnoreCase("4")) {
                                    type = "Mental Retardation ";
                                } else if (type.equalsIgnoreCase("5")) {
                                    type = "Mental Illness";
                                } else if (type.equalsIgnoreCase("6")) {
                                    type = "Multiple Disability";
                                }
                            }
                            partAForm.setDisabilitytype(type);
                            partAForm.setPersoncode(dto.getPersoncode());
                            partAForm.setName(dto.getName());
                            partAForm.setAge(dto.getAge());
                            partAForm.setMandal(dto.getMandal());
                            partAForm.setDisability(dto.getDisability());
                            partAForm.setDistrict(dto.getDistrict());
                            partAForm.setRelationname(dto.getRelationname());
                            partAForm.setPercentage(dto.getPercentage());
                            partAForm.setViewedit(dto.getViewedit());
                            partAForm.setVillage(dto.getVillage());
                            partAForm.setStatus(dto.getStatus());
                            partAForm.setGender(dto.getGender());
                            partAForm.setReasonforstatus(dto.getReasonforstatus());
                            partAForm.setCategoryid(dto.getCategoryid());
                            partAForm.setRationcard(dto.getRationcard());
                            partAForm.setSid(dto.getSid());
                            target = "data";
                        } else {
                            target = "fail";
                            request.setAttribute("msg", dto.getError_msg());
                        }
                    } else {
                        target = "fail";
                        request.setAttribute("msg", "Invalid personcode");
                    }
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
}
