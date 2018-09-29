/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.service;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;

/**
 *
 * @author 693461
 */
public interface FileUploadDownloadService {

    public String getDistrictLoginNameDetails(DataSource ds, String distid) throws SADAREMDBException,SQLException;

    public int insertFileUpdateDetails(DataSource ds, String districtId, String fileName) throws SADAREMDBException,SQLException;

    public ArrayList getDistEmailIds(DataSource ds, String districtId) throws SADAREMDBException,SQLException;

    public String basedOnSessionDetails(DataSource ds, String userid) throws SADAREMDBException,SQLException;

    public ArrayList getUserDetailsForMail(DataSource ds, String distId) throws SADAREMDBException,SQLException;

    public String getFileDeleteDetials(DataSource ds) throws SADAREMDBException,SQLException;

    public ArrayList getDistrictCCMails(DataSource ds, String district) throws SADAREMDBException,SQLException;
}
