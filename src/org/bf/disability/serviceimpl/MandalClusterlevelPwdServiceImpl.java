/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.serviceimpl;

import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.dao.MandalClusterlevelPwdDAO;
import org.bf.disability.form.MandalClusterlevelPwdForm;
import org.bf.disability.service.MandalClusterlevelPwdService;

/**
 *
 * @author 842412
 */
public class MandalClusterlevelPwdServiceImpl implements MandalClusterlevelPwdService{

    public ArrayList getPwdsPersonalCount(MandalClusterlevelPwdForm mandalClusterlevelPwdForm,DataSource ds) {
        ArrayList pensionData=new ArrayList();
        try {
            MandalClusterlevelPwdDAO mandalClusterlevelPwdDAO = new MandalClusterlevelPwdDAO();
            pensionData=mandalClusterlevelPwdDAO.getPwdsPersonalCount(mandalClusterlevelPwdForm,ds);
        } catch (Exception e) {
            e.printStackTrace();
        }
         return pensionData;
    }
    public ArrayList getPwdsIndividualCount(MandalClusterlevelPwdForm mandalClusterlevelPwdForm, DataSource ds) {
        ArrayList pwdIndividualDetails=new ArrayList();
        try {
            MandalClusterlevelPwdDAO mandalClusterlevelPwdDAO = new MandalClusterlevelPwdDAO();
            pwdIndividualDetails=mandalClusterlevelPwdDAO.getPwdsIndividualCount(mandalClusterlevelPwdForm,ds);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pwdIndividualDetails;
    }
}
