/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.service;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.PhotoGalleryDTO;

/**
 *
 * @author 484898
 */
public interface PhotoGalleryService {

    public String saveDetails(DataSource ds,PhotoGalleryDTO photoGalleryDto,HttpServletRequest request) throws SADAREMDBException, SQLException;

    public ArrayList getPhotoGalleryDetails(DataSource ds,PhotoGalleryDTO photoGalleryDto) throws SADAREMDBException, SQLException;

    public ArrayList getPhotoGalleryEvents(DataSource ds) throws SADAREMDBException, SQLException;

    public String getPhotoGalleryForEvents(DataSource ds,String event) throws SADAREMDBException, SQLException;

    public String inactiveRecord(DataSource ds,PhotoGalleryDTO photoGalleryDto) throws SADAREMDBException, SQLException;

}
