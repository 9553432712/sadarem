/*
 * MentalIllnessImpl.java
 *
 * Created on January 2, 2009, 5:34 PM
 *
 */
package org.bf.disability.util;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.apache.commons.beanutils.BeanUtils;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.bean.MentalIllnessBean;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.dao.VisualImapairmentDAO;
import org.bf.disability.dto.MentalIllnessDTO;
import org.bf.disability.serviceimpl.ShowCalcualationsServiceImpl;

/**
 *
 * @author Deviprasad_t
 */
public class MentalIllnessImpl extends ShowCalcualationsServiceImpl {

    public void populateMICalculations(DataSource dataSource, HttpServletRequest request, String personcode) throws SADAREMDBException, SQLException {
        try {
            MentalIllnessDTO mentalIllnessDTO = new MentalIllnessDTO();
            VisualImapairmentDAO visualImapairmentDAO = new VisualImapairmentDAO();
            MentalIllnessBean mentalIllnessBean = new MentalIllnessBean();
            mentalIllnessDTO = visualImapairmentDAO.getMentalIllnessDetails(dataSource, personcode);

            BeanUtils.copyProperties(mentalIllnessBean, mentalIllnessDTO);

            mentalIllnessBean = MICalculationLogic(mentalIllnessBean);


            request.setAttribute("mentalIllnessBean", mentalIllnessBean);
        } //end of try block
        catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(dataSource, sqlEx.toString(), "populateMICalculations", "MentalIllnessImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalIllnessImpl", "populateMICalculations");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(dataSource, sqlEx.toString(), "populateMICalculations", "MentalIllnessImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalIllnessImpl", "populateMICalculations");
        }//end of catch block

    }

    public MentalIllnessBean MICalculationLogic(MentalIllnessBean mentalIllnessBean) throws SADAREMDBException, SQLException {
        try {
            int globalScore = 0, i = 0, range = 0, mentaldisability = 0;
            int selfcare = 0, personalactivities = 0, work = 0, communication = 0, duration = 0, globaldisabilityscore = 0, finalpercentage = 0;


//         MentalIllnessActionForm mform=(MentalIllnessActionForm)form;      
//         MentalIllnessDTO mdto=new MentalIllnessDTO();

//         mentalIllnessBean.setSelfcare(selfcare);
//         mentalIllnessBean.setPersonalactivities(personalactivities);
//         mentalIllnessBean.setWork(work);
//         mentalIllnessBean.setCommunication(communication);
//         mentalIllnessBean.setDuration(duration);
//         mentalIllnessBean.setGlobaldisabilityscore(globaldisabilityscore);
//         mentalIllnessBean.setFinalpercentage(finalpercentage);
//         mentalIllnessBean.setGlobalscore(String.valueOf(globalScore));
            if (mentalIllnessBean.getGlobalscore() != null || mentalIllnessBean.getGlobalscore() != "") {
                globalScore = Integer.parseInt(mentalIllnessBean.getGlobalscore());
            }

            if (globalScore != 0) {

                //These values are hard coaded.based on Table no 3.in Mental Illness
                switch (globalScore) {
                    case 0:
                        mentaldisability = 0;
                        break;
                    case 1:
                        mentaldisability = 7;
                        break;
                    case 2:
                        mentaldisability = 13;
                        break;
                    case 3:
                        mentaldisability = 20;
                        break;
                    case 4:
                        mentaldisability = 26;
                        break;
                    case 5:
                        mentaldisability = 33;
                        break;
                    case 6:
                        mentaldisability = 39;
                        break;
                    case 7:
                        mentaldisability = 40;
                        break;
                    case 8:
                        mentaldisability = 45;
                        break;
                    case 9:
                        mentaldisability = 50;
                        break;
                    case 10:
                        mentaldisability = 55;
                        break;
                    case 11:
                        mentaldisability = 60;
                        break;
                    case 12:
                        mentaldisability = 65;
                        break;
                    case 13:
                        mentaldisability = 70;
                        break;
                    case 14:
                        mentaldisability = 71;
                        break;
                    case 15:
                        mentaldisability = 77;
                        break;
                    case 16:
                        mentaldisability = 83;
                        break;
                    case 17:
                        mentaldisability = 89;
                        break;
                    case 18:
                        mentaldisability = 95;
                        break;
                    case 19:
                        mentaldisability = 99;
                        break;
                    case 20:
                        mentaldisability = 100;
                        break;
                }



            }
            mentalIllnessBean.setMentaldisability(mentaldisability);

        } //end of try block
        catch (Exception sqlEx) {
            sqlEx.printStackTrace();
        }//end of catch block



        return mentalIllnessBean;
    }
}
