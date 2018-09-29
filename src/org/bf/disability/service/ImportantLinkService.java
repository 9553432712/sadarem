/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.service;

import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.form.ImportantLinkForm;

/**
 *
 * @author 693461
 */
public interface  ImportantLinkService {

  public int insertImpLinkDetails(DataSource ds,ImportantLinkForm importantLinkForm) throws Exception;

  public ArrayList getImpLinkDetails(DataSource ds) throws Exception;

  public ArrayList getEditLinkDetails(DataSource ds,String rowId) throws Exception;

  public int updateImpLinkDetails(DataSource ds,ImportantLinkForm importantLinkForm,String webSiteId) throws Exception;

  public int deleteImpLinkDetails(DataSource ds,String webSiteId) throws Exception;

  public ArrayList getReportDetails(DataSource ds)throws Exception;


}
