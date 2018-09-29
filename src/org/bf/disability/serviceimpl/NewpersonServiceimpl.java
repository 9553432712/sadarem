/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.serviceimpl;

import java.sql.SQLException;
import javax.sql.DataSource;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.dao.NewpersonDAO;
import org.bf.disability.form.NewPersonDetailsForm;
import org.bf.disability.service.NewpersonService;

/**
 *
 * @author 695536
 */
public class NewpersonServiceimpl implements NewpersonService{
    public String getPhysicalImpairmentDetails(DataSource ds, NewPersonDetailsForm newPersonDetailsForm) throws SADAREMDBException,SQLException {
       String physicalData = null;
        NewpersonDAO newpersonDao = new NewpersonDAO();
        try {
            physicalData = newpersonDao.getPhysicalImpairmentDetails(ds, newPersonDetailsForm);

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getPhysicalImpairmentDetails", "NewpersonServiceimpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NewpersonServiceimpl", "getPhysicalImpairmentDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getPhysicalImpairmentDetails", "NewpersonServiceimpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NewpersonServiceimpl", "getPhysicalImpairmentDetails");
        }
        return physicalData;
    }
    public int updatedetails(DataSource ds,NewPersonDetailsForm newPersonDetailsForm,int requestId)throws SADAREMDBException,SQLException{
    int updatedata=0;
       NewpersonDAO newpersonDao=new NewpersonDAO();
        try {
             updatedata = newpersonDao.updatedetails(ds, newPersonDetailsForm,requestId);

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "updatedetails", "NewpersonServiceimpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NewpersonServiceimpl", "updatedetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "updatedetails", "NewpersonServiceimpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "NewpersonServiceimpl", "updatedetails");
        }

        return updatedata;
    }

}
