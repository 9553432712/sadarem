package org.bf.disability.action;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.BaseAction;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.CommonDetails;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dto.PartADTO;
import org.bf.disability.service.AppletAuthorityService;
import org.bf.disability.servicefactory.AppletAuthorityServiceFactory;

import com.tcs.sadarem.util.CommonUtility;

/**
 *
 * @author 484898
 */
public class registrationPhysicalRequirements extends BaseAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException {
        String target = "success";

        HttpSession session = request.getSession();
        DataSource datasource = null;
        boolean districtLevelAccessFlag = false;
        boolean adminLevelAccessFlag = false;
        String districtid = null;
        PartADTO dto = new PartADTO();
        AppletAuthorityService appletAuthorityService = AppletAuthorityServiceFactory.getAppletAuthorityServiceImpl();
        try { datasource = getDataSource(request);
            if (datasource == null || "null".equals(datasource)) {
                datasource = JNDIDataSource.getConnection();
            }

            String pid = request.getParameter("pensioncode");
            if (pid == null) {
                target = "success";
            } else {
                if (pid != null) {
                    CommonDetails commondetails = new CommonDetails();
                    int currentRoleId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("roleId")));
                    adminLevelAccessFlag = commondetails.campareRoleIds(String.valueOf(currentRoleId), CommonConstants.ADMIN_PERMISSION_IDS);
                    //    boolean adminLevelAccessFlag = commondetails.campareRoleIds(String.valueOf(currentRoleId), CommonConstants.ADMIN_PERMISSION_IDS);
                    if (!adminLevelAccessFlag) {
                        districtid = (String) session.getAttribute("districtId");
                        districtLevelAccessFlag = commondetails.checkDistrictFlag(pid, districtid);
                    }
                }

                if (districtLevelAccessFlag || adminLevelAccessFlag) {
                    boolean exist = appletAuthorityService.isEligibleforPhysicalRequirements(datasource, pid, request);
                    if (exist) {
                        appletAuthorityService.insertPhysicalRequirements(datasource, pid, request);
                    } else {
                        request.setAttribute("msg1", "Personcode is not eligible for physical Requirements!!!");
                    }
                } else {
                    request.setAttribute("msg1", "Please enter valid personcode!!!");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(target);
    }
}
