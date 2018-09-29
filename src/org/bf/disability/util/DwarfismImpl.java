/*
 * DwarfismImpl.java
 *
 * Created on January 2, 2009, 5:16 PM
 *
 */
package org.bf.disability.util;

import javax.servlet.http.HttpServletRequest;
import org.bf.disability.bean.DwarfismBean;
import org.bf.disability.dto.DwarfismDTO;
import org.bf.disability.service.VisualImpairmentService;
import org.bf.disability.servicefactory.VisualImpairmentServiceFactory;
import org.bf.disability.serviceimpl.ShowCalcualationsServiceImpl;
import javax.sql.DataSource;
import org.apache.commons.beanutils.BeanUtils;

import java.sql.SQLException;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dao.ExceptionDAO;

/**
 *
 * @author Deviprasad_t
 */
public class DwarfismImpl extends ShowCalcualationsServiceImpl {

    public void populateDwarfismCalculations(DataSource dataSource, HttpServletRequest request, String personcode) throws SADAREMDBException, SQLException {
        try {

            DwarfismBean dwarfismBean = new DwarfismBean();
            String gender = null;
            VisualImpairmentService visulaImpairmentService =
                    VisualImpairmentServiceFactory.getVisualImparmentImpl();

            DwarfismDTO dwarfismdto = new DwarfismDTO();

            dwarfismdto = visulaImpairmentService.getDwarfismDetails(dataSource, personcode);
            gender = visulaImpairmentService.getGender(dataSource, personcode);
            BeanUtils.copyProperties(dwarfismBean, dwarfismdto);

            dwarfismBean.setGender(gender);
            dwarfismBean = dwarfismCalculationsLogic(dwarfismBean);
            request.setAttribute("dwarfismBean", dwarfismBean);
        } //end of try block
        catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(dataSource,sqlEx.toString(), "populateDwarfismCalculations", "DwarfismImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DwarfismImpl", "populateDwarfismCalculations");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(dataSource,sqlEx.toString(), "populateDwarfismCalculations", "DwarfismImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "DwarfismImpl", "populateDwarfismCalculations");
        } //end of catch block
    }

    /* -------------THIS IS CALCULATION LOGIC------ */
    public DwarfismBean dwarfismCalculationsLogic(DwarfismBean dwarfismBean) {
        int age = 0;
        float heighthavetobe = 0;
        float actualheight = 0;
        float heightloss = 0;
        float dwarfism = 0;

        if (dwarfismBean.getAgeyears() != 0) {
            age = (dwarfismBean.getAgeyears());
        }

        if (age >= 21) //After 21 years height will not increased
        {
            age = 21;
        }


        if (dwarfismBean.getAgeyears() != 0 && dwarfismBean.getHeight() != 0) {
            String gender = dwarfismBean.getGender();


            if (gender.equalsIgnoreCase("1")) {
                gender = "Male";
            }
            if (gender.equalsIgnoreCase("2")) {
                gender = "FeMale";
            }

            //This Switch case defines Normal heights of the persons according to their age
            switch (age) {
                case 0:
                    if (((dwarfismBean.getAgemonths()) >= 1)
                            && ((dwarfismBean.getAgemonths()) < 3)) {
                        if (gender.equalsIgnoreCase("Male")) {
                            heighthavetobe = 17.49f;
                        }
                        if (gender.equalsIgnoreCase("Female")) {
                            heighthavetobe = 17.39f;
                        }
                    }

                    if ((dwarfismBean.getAgemonths()) >= 3
                            && (dwarfismBean.getAgemonths()) < 6) {
                        if (gender.equalsIgnoreCase("Male")) {
                            heighthavetobe = 21.52f;
                        }
                        if (gender.equalsIgnoreCase("Female")) {
                            heighthavetobe = 21.80f;
                        }
                    }

                    if ((dwarfismBean.getAgemonths()) >= 6
                            && (dwarfismBean.getAgemonths()) < 9) {
                        if (gender.equalsIgnoreCase("Male")) {
                            heighthavetobe = 19.17f;
                        }
                        if (gender.equalsIgnoreCase("Female")) {
                            heighthavetobe = 22.49f;
                        }
                    }
                    if ((dwarfismBean.getAgemonths()) >= 9
                            && (dwarfismBean.getAgemonths()) < 12) {
                        if (gender.equalsIgnoreCase("Male")) {
                            heighthavetobe = 23.82f;
                        }
                        if (gender.equalsIgnoreCase("Female")) {
                            heighthavetobe = 23.22f;
                        }
                    }
                    break;
                case 1:
                    if (gender.equalsIgnoreCase("Male")) {
                        heighthavetobe = 24.95f;
                    }
                    if (gender.equalsIgnoreCase("Female")) {
                        heighthavetobe = 24.46f;
                    }
                    break;
                case 2:
                    if (gender.equalsIgnoreCase("Male")) {
                        heighthavetobe = 27.93f;
                    }
                    if (gender.equalsIgnoreCase("Female")) {
                        heighthavetobe = 26.97f;
                    }
                    break;
                case 3:
                    if (gender.equalsIgnoreCase("Male")) {
                        heighthavetobe = 29.80f;
                    }
                    if (gender.equalsIgnoreCase("Female")) {
                        heighthavetobe = 29.33f;
                    }
                    break;
                case 4:
                    if (gender.equalsIgnoreCase("Male")) {
                        heighthavetobe = 32.50f;
                    }
                    if (gender.equalsIgnoreCase("Female")) {
                        heighthavetobe = 32.20f;
                    }
                    break;
                case 5:
                    if (gender.equalsIgnoreCase("Male")) {
                        heighthavetobe = 33.84f;
                    }
                    if (gender.equalsIgnoreCase("Female")) {
                        heighthavetobe = 34.12f;
                    }
                    break;
                case 6:
                    if (gender.equalsIgnoreCase("Male")) {
                        heighthavetobe = 37.09f;
                    }
                    if (gender.equalsIgnoreCase("Female")) {
                        heighthavetobe = 35.46f;
                    }
                    break;
                case 7:
                    if (gender.equalsIgnoreCase("Male")) {
                        heighthavetobe = 38.02f;
                    }
                    if (gender.equalsIgnoreCase("Female")) {
                        heighthavetobe = 37.72f;
                    }
                    break;
                case 8:
                    if (gender.equalsIgnoreCase("Male")) {
                        heighthavetobe = 41.18f;
                    }
                    if (gender.equalsIgnoreCase("Female")) {
                        heighthavetobe = 40.47f;
                    }
                    break;
                case 9:
                    if (gender.equalsIgnoreCase("Male")) {
                        heighthavetobe = 41.40f;
                    }
                    if (gender.equalsIgnoreCase("Female")) {
                        heighthavetobe = 42.46f;
                    }
                    break;
                case 10:
                    if (gender.equalsIgnoreCase("Male")) {
                        heighthavetobe = 41.11f;
                    }
                    if (gender.equalsIgnoreCase("Female")) {
                        heighthavetobe = 44.25f;
                    }
                    break;
                case 11:
                    if (gender.equalsIgnoreCase("Male")) {
                        heighthavetobe = 44.86f;
                    }
                    if (gender.equalsIgnoreCase("Female")) {
                        heighthavetobe = 45.14f;
                    }
                    break;
                case 12:
                    if (gender.equalsIgnoreCase("Male")) {
                        heighthavetobe = 46.47f;
                    }
                    if (gender.equalsIgnoreCase("Female")) {
                        heighthavetobe = 46.74f;
                    }
                    break;
                case 13:
                    if (gender.equalsIgnoreCase("Male")) {
                        heighthavetobe = 49.25f;
                    }
                    if (gender.equalsIgnoreCase("Female")) {
                        heighthavetobe = 49.39f;
                    }
                    break;
                case 14:
                    if (gender.equalsIgnoreCase("Male")) {
                        heighthavetobe = 51.20f;
                    }
                    if (gender.equalsIgnoreCase("Female")) {
                        heighthavetobe = 50.43f;
                    }
                    break;
                case 15:
                    if (gender.equalsIgnoreCase("Male")) {
                        heighthavetobe = 53.34f;
                    }
                    if (gender.equalsIgnoreCase("Female")) {
                        heighthavetobe = 52.35f;
                    }
                    break;
                case 16:
                    if (gender.equalsIgnoreCase("Male")) {
                        heighthavetobe = 55.11f;
                    }
                    if (gender.equalsIgnoreCase("Female")) {
                        heighthavetobe = 53.84f;
                    }
                    break;
                case 17:
                    if (gender.equalsIgnoreCase("Male")) {
                        heighthavetobe = 55.32f;
                    }
                    if (gender.equalsIgnoreCase("Female")) {
                        heighthavetobe = 53.74f;
                    }
                    break;
                case 18:
                    if (gender.equalsIgnoreCase("Male")) {
                        heighthavetobe = 56.69f;
                    }
                    if (gender.equalsIgnoreCase("Female")) {
                        heighthavetobe = 55.10f;
                    }
                    break;
                case 19:
                    if (gender.equalsIgnoreCase("Male")) {
                        heighthavetobe = 56.79f;
                    }
                    if (gender.equalsIgnoreCase("Female")) {
                        heighthavetobe = 55.10f;
                    }
                    break;
                case 20:
                    if (gender.equalsIgnoreCase("Male")) {
                        heighthavetobe = 59.10f;
                    }
                    if (gender.equalsIgnoreCase("Female")) {
                        heighthavetobe = 55.08f;
                    }
                    break;
                case 21:
                    if (gender.equalsIgnoreCase("Male")) {
                        heighthavetobe = 59.84f;
                    }
                    if (gender.equalsIgnoreCase("Female")) {
                        heighthavetobe = 55.76f;
                    }
                    break;
            }


            if (dwarfismBean.getHeight() != 0) {
                actualheight = (dwarfismBean.getHeight());
            }


            if (heighthavetobe > actualheight) {
                heightloss = heighthavetobe - actualheight;

                dwarfism = 4 * heightloss;  //This is the formula.
                if (dwarfism > 100) {
                    dwarfism = 100;
                }
            }
            dwarfismBean.setHeighthavetobe(heighthavetobe);

            dwarfismBean.setDwarfism(dwarfism);

            dwarfismBean.setHeightloss(heightloss);




        }
        return dwarfismBean;
    }   /* END OF CALCULATION LOGIC METHOD  */

}
