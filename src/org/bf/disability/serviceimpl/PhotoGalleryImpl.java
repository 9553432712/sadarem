/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.serviceimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dao.PhotoGalleryDAO;
import org.bf.disability.dto.PhotoGalleryDTO;
import org.bf.disability.service.PhotoGalleryService;

/**
 *
 * @author 484898
 */
public class PhotoGalleryImpl implements PhotoGalleryService {


    /**
     * This method is for saving the photo Galledy Details
     * @param photoGalleryDto
     * @param request
     * @return
     * @throws SADAREMDBException, SQLException
     */
    public String saveDetails(DataSource ds,PhotoGalleryDTO photoGalleryDto, HttpServletRequest request) throws SADAREMDBException, SQLException {
        String insertStatus = null;
        PhotoGalleryDAO photoGalleryDAO = new PhotoGalleryDAO();
        insertStatus = photoGalleryDAO.saveDetails(ds,photoGalleryDto, request);
        return insertStatus;
    }

    /**
     * This method is for getting the photo gallery details
     * @return
     * @throws SADAREMDBException, SQLException
     */
    public ArrayList getPhotoGalleryDetails(DataSource ds,PhotoGalleryDTO photoGalleryDto) throws SADAREMDBException, SQLException {
        ArrayList galleryDetails = new ArrayList();
        PhotoGalleryDAO photoGalleryDAO = new PhotoGalleryDAO();
        galleryDetails = photoGalleryDAO.getPhotoGalleryDetails(ds,photoGalleryDto);
        return galleryDetails;
    }

    /**
     * This method is for getting the photo gallery events
     * @return
     * @throws SADAREMDBException, SQLException
     */
    public ArrayList getPhotoGalleryEvents(DataSource ds) throws SADAREMDBException, SQLException {
        ArrayList galleryDetails = new ArrayList();
        PhotoGalleryDAO photoGalleryDAO = new PhotoGalleryDAO();
        try {
            galleryDetails = photoGalleryDAO.getPhotoGalleryEvents(ds);
        } catch (Exception ex) {
            Logger.getLogger(PhotoGalleryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return galleryDetails;
    }

    /**
     * This method is for getting the photos based on events
     * @return
     * @throws SADAREMDBException, SQLException
     */
    public String getPhotoGalleryForEvents(DataSource ds,String event) throws SADAREMDBException, SQLException {
        String photoString = null;
        PhotoGalleryDAO photoGalleryDAO = new PhotoGalleryDAO();
        photoString = photoGalleryDAO.getPhotoGalleryForEvents(ds,event);
        return photoString;
    }

    /**
     * This method is for inactive the photos
     * @param photoGalleryDTO
     * @return String
     * @throws SADAREMDBException, SQLException
     */
    public String inactiveRecord(DataSource ds,PhotoGalleryDTO photoGalleryDTO) throws SADAREMDBException, SQLException {
        String inactiveRecord = null;
        PhotoGalleryDAO photoGalleryDAO = new PhotoGalleryDAO();
        inactiveRecord = photoGalleryDAO.inactiveRecord(ds,photoGalleryDTO);
        return inactiveRecord;
    }
}
