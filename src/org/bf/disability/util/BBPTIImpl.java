/*
 * BBPTIImpl.java
 *
 * Created on January 5, 2009, 11:59 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.util;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.commons.beanutils.BeanUtils;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.bean.BBPTIBean;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.dao.MentalRetardationDAO;
import org.bf.disability.dto.MentalRetardationTestsDTO;
import org.bf.disability.serviceimpl.ShowCalcualationsServiceImpl;

/**
 *
 * @author kiran_h
 */
public class BBPTIImpl extends ShowCalcualationsServiceImpl {

    public void populateBBPTICalculations(DataSource dataSource, HttpServletRequest request, String personcode) throws SADAREMDBException, SQLException {

        try {
            //TrunkDAO trunkDAO=new TrunkDAO();
            MentalRetardationTestsDTO mentalRetardationTestsDTO = new MentalRetardationTestsDTO();
            BBPTIBean bBPTIBean = new BBPTIBean();
            MentalRetardationDAO mentalRetardationDAO = new MentalRetardationDAO();
            mentalRetardationTestsDTO = mentalRetardationDAO.getBhatiaTestDetails(dataSource, personcode);
            BeanUtils.copyProperties(bBPTIBean, mentalRetardationTestsDTO);
            bBPTIBean = bbptiCalculationsLogic(bBPTIBean);

            request.setAttribute("bBPTIBean", bBPTIBean);
        } //end of try block
        catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(dataSource, sqlEx.toString(), "populateBBPTICalculations", "BBPTIImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "BBPTIImpl", "populateBBPTICalculations");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(dataSource, sqlEx.toString(), "populateBBPTICalculations", "BBPTIImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "BBPTIImpl", "populateBBPTICalculations");
        } //end of catch block
    }

    public BBPTIBean bbptiCalculationsLogic(BBPTIBean bBPTIBean) throws SADAREMDBException, SQLException {
        try {

            if (bBPTIBean.getBbpti_KOHS_Block_Design_Test() == "") {
                bBPTIBean.setBbpti_KOHS_Block_Design_Test("0");
            }
            if (bBPTIBean.getBbpti_Passalong_Test() == "") {
                bBPTIBean.setBbpti_Passalong_Test("0");
            }
            if (bBPTIBean.getBbpti_PatternDrawing_Test() == "") {
                bBPTIBean.setBbpti_PatternDrawing_Test("0");
            }
            if (bBPTIBean.getBbpti_ImmediateMemory_Test() == "") {
                bBPTIBean.setBbpti_ImmediateMemory_Test("0");
            }
            if (bBPTIBean.getBbpti_PictureConstruction_Test() == "") {
                bBPTIBean.setBbpti_PictureConstruction_Test("0");
            }
            String iq = bBPTIBean.getBbpti_IQ();
            bBPTIBean.setBbpti_IQ(iq);


        } //end of try block
        catch (Exception sqlEx) {
            sqlEx.printStackTrace();
        } //end of catch block
        return bBPTIBean;
    }
}
