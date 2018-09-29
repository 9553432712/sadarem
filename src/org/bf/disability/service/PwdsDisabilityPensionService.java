/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.service;

import java.util.ArrayList;
import javax.sql.DataSource;

/**
 *
 * @author 842412
 */
public interface PwdsDisabilityPensionService {

    public ArrayList getDisabilityPensionsDetails(DataSource ds);

    public ArrayList getPwdsPensionDetails(DataSource ds);

}
