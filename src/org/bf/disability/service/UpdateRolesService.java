package org.bf.disability.service;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dao.AddServicestoRoleDAO;

import org.bf.disability.dao.DeleteServicestoRoleDAO;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.form.ServiceBean;
import org.bf.disability.servicefactory.GetServicesServiceFactory;

/**
 * This class contains the logic to update the services assigned to a role
 * @author Deviprasad
 * @version 1.0
 */
public class UpdateRolesService {

    public int updateServicestoRole(DataSource ds, String roleid, ArrayList servicelist_modified) throws SADAREMDBException, SQLException {
        try {
            AddServicestoRoleDAO addservicestoroledao = new AddServicestoRoleDAO();
            DeleteServicestoRoleDAO deleteservicestoroledao = new DeleteServicestoRoleDAO();
            String serviceid;
            ServiceBean servicebean = null;
            ServiceBean servicebean1 = null;
            ArrayList servicelist_old = new ArrayList();
            GetServicesService getservicesservice = GetServicesServiceFactory.getServicesServiceImpl();

            servicelist_old = getservicesservice.getService(ds, roleid);
            for (int i = 0; i < servicelist_modified.size(); i++) {
                servicebean = (ServiceBean) servicelist_modified.get(i);
                for (int j = 0; j < servicelist_old.size(); j++) {
                    servicebean1 = (ServiceBean) servicelist_old.get(j);
                    if ((servicebean1.getService_id()).equals((servicebean.getService_id()))) {
                        break;
                    }
                }
                if (servicebean.getSelect() != null) {
                    if (servicebean.getSelect().equals("on")) {
                        //modified is checked
                        if (servicebean1.getSelect() != null) {
                            //previously was checked
                        } else {
                            //previously not checked
                            serviceid = servicebean.getService_id();
                            int j = addservicestoroledao.addServicestoRole(ds, roleid, serviceid);
                            if (j == 0) {
                                return j;
                            }
                        }
                    } else {
                        if (servicebean1.getSelect() != null) {
                            //.println("something2");
                            if (servicebean1.getSelect().equals("on")) {
                                //.println("something3");
                                //previously was checked
                                serviceid = servicebean.getService_id();
                                int j = deleteservicestoroledao.deleteServicestoRole(ds, roleid, serviceid);
                                if (j == 0) {
                                    return j;
                                }
                            }
                        }
                    }
                } else {
                    //modified is unchecked
                    if (servicebean1.getSelect() != null) {

                        if (servicebean1.getSelect().equals("on")) {

                            //previously was checked
                            serviceid = servicebean.getService_id();
                            int j = deleteservicestoroledao.deleteServicestoRole(ds, roleid, serviceid);
                            if (j == 0) {
                                return j;
                            }
                        }
                    } else {
                        //previously was unchecked
                    }
                }
            }
        } catch (Exception exception) {
            int exResult = ExceptionDAO.saveException(ds,exception.toString(), "updateServicestoRole", "UpdateRolesService", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, exception.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "UpdateRolesService", "updateServicestoRole");

        }
        return 1;
    }
}
