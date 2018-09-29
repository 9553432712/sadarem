/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.service;
import org.bf.disability.Exception.SADAREMDBException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.sql.SQLException;
import org.bf.disability.dto.CMPUploadDTO;



/**
 *
 * @author 484898
 */
public interface CMPUploadsService {

    public int saveDetails(CMPUploadDTO cmpUploadDTO,HttpServletRequest request,DataSource ds) throws SADAREMDBException, SQLException;

    public int updateDetails(CMPUploadDTO cmpUploadDTO,HttpServletRequest request,DataSource ds) throws SADAREMDBException, SQLException;

    public int inactiveRecord(CMPUploadDTO cmpUploadDTO,DataSource ds) throws SADAREMDBException, SQLException;

    public ArrayList getCMPUploadsDetails(CMPUploadDTO cmpUploadDTO,DataSource ds) throws SADAREMDBException, SQLException;

    public ArrayList getCMPDetailsForEdit(CMPUploadDTO cmpUploadDTO,DataSource ds) throws SADAREMDBException, SQLException;

    public String generateCircularNumber(CMPUploadDTO cmpUploadDTO,DataSource ds) throws SADAREMDBException, SQLException;

}
