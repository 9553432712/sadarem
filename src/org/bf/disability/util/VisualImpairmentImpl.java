/*
 * VisualImpairmentImpl.java
 *
 * Created on January 2, 2009, 5:31 PM
 *
 */
package org.bf.disability.util;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.apache.commons.beanutils.BeanUtils;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.bean.VisualImpairmentBean;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.dao.VisualImapairmentDAO;
import org.bf.disability.dto.CardioPulmonaryDTO;
import org.bf.disability.form.CardioPulmonaryActionForm;
import org.bf.disability.service.VisualImpairmentService;
import org.bf.disability.servicefactory.VisualImpairmentServiceFactory;
import org.bf.disability.serviceimpl.ShowCalcualationsServiceImpl;

/**
 *
 * @author Deviprasad_t
 */
public class VisualImpairmentImpl extends ShowCalcualationsServiceImpl {

    VisualImpairmentBean visualImpairmentBean = new VisualImpairmentBean();

    public void populateVICalculations(DataSource dataSource, HttpServletRequest request, String personcode) throws SADAREMDBException, SQLException {
        try {
            //VisualImpairmentBean visualImpairmentBean=new VisualImpairmentBean();
            CardioPulmonaryDTO cardioPulmonaryDTO = new CardioPulmonaryDTO();
            VisualImapairmentDAO dao = new VisualImapairmentDAO();
            cardioPulmonaryDTO = dao.getVisualImpairment(dataSource, personcode);

            BeanUtils.copyProperties(visualImpairmentBean, cardioPulmonaryDTO);
            visualImpairmentBean = VICalculationLogic(visualImpairmentBean);

            request.setAttribute("visualimpairmentbean", visualImpairmentBean);
        } //end of try block
        catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(dataSource,sqlEx.toString(), "populateVICalculations", "VisualImpairmentImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VisualImpairmentImpl", "populateVICalculations");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(dataSource,sqlEx.toString(), "populateVICalculations", "VisualImpairmentImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VisualImpairmentImpl", "populateVICalculations");
        }//end of catch block
    }

    public VisualImpairmentBean VICalculationLogic(VisualImpairmentBean visualImpairmentBean) throws SADAREMDBException, SQLException {


        //String visualvalue=visualImpairmentBean.getVisualimpairment();
//        double visualvalue=(Double.parseDouble(visualImpairmentBean.getVisualimpairment()));
//          if(visualvalue==101.0)
//          {
//              //visualImpairmentBean.setVisualimpairment("100.0");
//              visualImpairmentBean.setCategory(true);
//          }
//         
        return visualImpairmentBean;
    }
}
