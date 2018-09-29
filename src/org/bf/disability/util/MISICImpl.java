/*
 * MISICImpl.java
 *
 * Created on January 5, 2009, 11:56 AM
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
import org.bf.disability.bean.MISICBean;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.dao.MentalRetardationDAO;
import org.bf.disability.dto.MentalRetardationTestsDTO;
import org.bf.disability.serviceimpl.ShowCalcualationsServiceImpl;

/**
 *
 * @author kiran_h
 */
public class MISICImpl extends ShowCalcualationsServiceImpl {

    MISICBean MISICBean = new MISICBean();
    HttpSession session = null;
    MentalRetardationTestsDTO mentalRetardationTestsDTO = new MentalRetardationTestsDTO();
    MentalRetardationDAO mentalRetardationDAO = new MentalRetardationDAO();

    public void populateMISICCalculations(DataSource dataSource, HttpServletRequest request, String personcode) throws SADAREMDBException, SQLException {
        try {
            session = request.getSession(true);
            mentalRetardationTestsDTO = mentalRetardationDAO.getMRmalinstestDetails(dataSource, personcode);
            BeanUtils.copyProperties(MISICBean, mentalRetardationTestsDTO);
            MISICBean = misicCalculationsLogic(MISICBean);

            request.setAttribute("MISICBean", MISICBean);
        } //end of try block
        catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(dataSource, sqlEx.toString(), "populateMISICCalculations", "MISICImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MISICImpl", "populateMISICCalculations");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(dataSource, sqlEx.toString(), "populateMISICCalculations", "MISICImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MISICImpl", "populateMISICCalculations");
        }//end of catch block
    }

    public MISICBean misicCalculationsLogic(MISICBean MISICBean) throws SADAREMDBException, SQLException {
        try {
            // Double ca=(Double)session.getAttribute("chronologicalage");
            //double caage=ca.doubleValue();
            if (MISICBean.getMisicinformationraw() == "") {
                MISICBean.setMisicinformationraw("0");
            }
            if (MISICBean.getMisicinformationtq() == "") {
                MISICBean.setMisicinformationtq("0");
            }
            if (MISICBean.getMisiccomprehensionrawscore() == "") {
                MISICBean.setMisiccomprehensionrawscore("0");
            }
            if (MISICBean.getMisiccomprehensiontq() == "") {
                MISICBean.setMisiccomprehensiontq("0");
            }
            if (MISICBean.getMisicarithmeticrawscore() == "") {
                MISICBean.setMisicarithmeticrawscore("0");
            }
            if (MISICBean.getMisicarithmetictq() == "") {
                MISICBean.setMisicarithmetictq("0");
            }
            if (MISICBean.getMisicsimilaritiesrawscore() == "") {
                MISICBean.setMisicsimilaritiesrawscore("0");
            }
            if (MISICBean.getMisicsimilaritiestq() == "") {
                MISICBean.setMisicsimilaritiestq("0");
            }
            if (MISICBean.getMisicvocabularyrawscore() == "") {
                MISICBean.setMisicvocabularyrawscore("0");
            }
            if (MISICBean.getMisicvocabularytq() == "") {
                MISICBean.setMisicvocabularytq("0");
            }
            if (MISICBean.getMisicdigitspanrawscore() == "") {
                MISICBean.setMisicdigitspanrawscore("0");
            }
            if (MISICBean.getMisicdigitspantq() == "") {
                MISICBean.setMisicdigitspantq("0");
            }
            if (MISICBean.getMisicpcrawscore() == "") {
                MISICBean.setMisicpcrawscore("0");
            }
            if (MISICBean.getMisicpctq() == "") {
                MISICBean.setMisicpctq("0");
            }
            if (MISICBean.getMisicbdrawscore() == "") {
                MISICBean.setMisicbdrawscore("0");
            }
            if (MISICBean.getMisicbdtq() == "") {
                MISICBean.setMisicbdtq("0");
            }
            if (MISICBean.getMisicoarawscore() == "") {
                MISICBean.setMisicoarawscore("0");
            }
            if (MISICBean.getMisicoatq() == "") {
                MISICBean.setMisicoatq("0");
            }
            if (MISICBean.getMisiccodingrawscore() == "") {
                MISICBean.setMisiccodingrawscore("0");
            }
            if (MISICBean.getMisiccodingtq() == "") {
                MISICBean.setMisiccodingtq("0");
            }
            if (MISICBean.getMisicmazesrawscore() == "") {
                MISICBean.setMisicmazesrawscore("0");
            }
            if (MISICBean.getMisicmazestq() == "") {
                MISICBean.setMisicmazestq("0");
            }

            int misicinformation = Integer.parseInt(MISICBean.getMisicinformationraw());
            int informationtq = Integer.parseInt(MISICBean.getMisicinformationtq());
            int comprehensionscore = Integer.parseInt(MISICBean.getMisiccomprehensionrawscore());
            int comprehensiontq = Integer.parseInt(MISICBean.getMisiccomprehensiontq());
            int arithmeticscore = Integer.parseInt(MISICBean.getMisicarithmeticrawscore());
            int arithmetictq = Integer.parseInt(MISICBean.getMisicarithmetictq());
            int similartiesscore = Integer.parseInt(MISICBean.getMisicsimilaritiesrawscore());
            int similartiestq = Integer.parseInt(MISICBean.getMisicsimilaritiestq());

            int vocabularyrawscore = Integer.parseInt(MISICBean.getMisicvocabularyrawscore());
            int vocabularytq = Integer.parseInt(MISICBean.getMisicvocabularytq());

            int digitspanscore = Integer.parseInt(MISICBean.getMisicdigitspanrawscore());
            int digitspantq = Integer.parseInt(MISICBean.getMisicdigitspantq());

            int pcrawscore = Integer.parseInt(MISICBean.getMisicpcrawscore());
            int pctq = Integer.parseInt(MISICBean.getMisicpctq());
            int bdrawscore = Integer.parseInt(MISICBean.getMisicbdrawscore());
            int bdtq = Integer.parseInt(MISICBean.getMisicbdtq());
            int misicoascore = Integer.parseInt(MISICBean.getMisicoarawscore());
            int misicoatq = Integer.parseInt(MISICBean.getMisicoatq());
            int codingrawscore = Integer.parseInt(MISICBean.getMisiccodingrawscore());
            int codingtq = Integer.parseInt(MISICBean.getMisiccodingtq());
            int mazesrawscore = Integer.parseInt(MISICBean.getMisicmazesrawscore());
            int mazestq = Integer.parseInt(MISICBean.getMisicmazestq());
            double avergeverbalquotient = (informationtq + comprehensiontq + arithmetictq + similartiestq + vocabularytq + digitspantq) / 5;
            double avergeperformancequotient = (pctq + bdtq + misicoatq + codingtq + mazestq) / 5;


            double iqrangeformisic = (avergeverbalquotient + avergeperformancequotient) / 2;
            MISICBean.setMisiciq(iqrangeformisic);
            MISICBean.setAvergeperformancequotient(avergeperformancequotient);

            MISICBean.setAvergeverbalquotient(avergeverbalquotient);

            double misiciq = MISICBean.getMisiciq();

            MISICBean.setMisiciq(iqrangeformisic);
        } //end of try block
        catch (Exception sqlEx) {
            sqlEx.printStackTrace();
        }//end of catch block
        return MISICBean;
    }
}
