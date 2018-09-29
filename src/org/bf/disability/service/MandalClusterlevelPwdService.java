/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.service;

import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.form.MandalClusterlevelPwdForm;

/**
 *
 * @author 842412
 */
public interface MandalClusterlevelPwdService {

    public ArrayList getPwdsPersonalCount(MandalClusterlevelPwdForm mandalClusterlevelPwdForm,DataSource ds);

    public ArrayList getPwdsIndividualCount(MandalClusterlevelPwdForm mandalClusterlevelPwdForm, DataSource ds);

}
