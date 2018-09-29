/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.serviceimpl;

import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.dao.PwdsDisabilityPensionDAO;
import org.bf.disability.service.PwdsDisabilityPensionService;

/**
 *
 * @author 842412
 */
public class PwdsDisabilityPensionServiceImpl implements PwdsDisabilityPensionService{

    public ArrayList getDisabilityPensionsDetails(DataSource ds) {
        ArrayList pensionData=new ArrayList();
        try {
            PwdsDisabilityPensionDAO pwdsDisabilityPensionDAO = new PwdsDisabilityPensionDAO();
            pensionData=pwdsDisabilityPensionDAO.getDisabilityPensionsDetails(ds);
        } catch (Exception e) {
            e.printStackTrace();
        }
         return pensionData;
    }

    public ArrayList getPwdsPensionDetails(DataSource ds) {
        ArrayList pwdPensionDetails=new ArrayList();
        try {
            PwdsDisabilityPensionDAO pwdsDisabilityPensionDAO = new PwdsDisabilityPensionDAO();
            pwdPensionDetails=pwdsDisabilityPensionDAO.getPwdsPensionDetails(ds);
        } catch (Exception e) {
            e.printStackTrace();
        }
         return pwdPensionDetails;
    }

}
