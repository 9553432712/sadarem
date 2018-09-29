/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.serviceimpl;

import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.dao.ImportantLinkDAO;
import org.bf.disability.form.ImportantLinkForm;
import org.bf.disability.service.ImportantLinkService;

/**
 *
 * @author 693461
 */
public class ImportantLinkServiceImpl  implements  ImportantLinkService{

    public int insertImpLinkDetails(DataSource ds,ImportantLinkForm importantLinkForm) throws Exception {

    int impLinkDetails =0;
    ImportantLinkDAO  importantLinkDAO  = new ImportantLinkDAO();

        try {
            impLinkDetails = importantLinkDAO.insertImpLinkDetails(ds,importantLinkForm);
        } catch (Exception e) {
         e.printStackTrace();
        }

    return  impLinkDetails;

    }

     public ArrayList getImpLinkDetails(DataSource ds) throws Exception {

     ArrayList impList = new ArrayList();
     ImportantLinkDAO  importantLinkDAO  = new ImportantLinkDAO();

         try {
             impList = importantLinkDAO.getImpLinkDetails(ds);

         } catch (Exception e) {
          e.printStackTrace();
         }

     return  impList;
     }

     public ArrayList getEditLinkDetails(DataSource ds,String rowId) throws Exception {

     ArrayList editData = new ArrayList();
     ImportantLinkDAO  importantLinkDAO  = new ImportantLinkDAO();

         try {
             editData = importantLinkDAO.getEditLinkDetails(ds,rowId);
         } catch (Exception e) {
          e.printStackTrace();
         }
     return  editData;



     }

     public int updateImpLinkDetails(DataSource ds,ImportantLinkForm importantLinkForm,String webSiteId) throws Exception {

         int updateLinkDetails =0;
    ImportantLinkDAO  importantLinkDAO  = new ImportantLinkDAO();

        try {
            updateLinkDetails = importantLinkDAO.updateImpLinkDetails(ds,importantLinkForm, webSiteId);
        } catch (Exception e) {
         e.printStackTrace();
        }

    return  updateLinkDetails;

     }

     public int deleteImpLinkDetails(DataSource ds,String webSiteId) throws Exception {

      int deleteImpLinkDetails =0;
    ImportantLinkDAO  importantLinkDAO  = new ImportantLinkDAO();

        try {
            deleteImpLinkDetails = importantLinkDAO.deleteImpLinkDetails(ds,webSiteId);
        } catch (Exception e) {
         e.printStackTrace();
        }

    return  deleteImpLinkDetails;

     }

     public ArrayList getReportDetails(DataSource ds)throws Exception {

     ArrayList reportList = new ArrayList();
       ImportantLinkDAO  importantLinkDAO  = new ImportantLinkDAO();
      try {
            reportList = importantLinkDAO.getReportDetails(ds);
        } catch (Exception e) {
         e.printStackTrace();
        }


     return  reportList;

     }
}
