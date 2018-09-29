/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.serviceimpl;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.bf.disability.dao.UploadGosandActsDAO;
import org.bf.disability.dto.UploadGosandActsDTO;
import org.bf.disability.form.UploadGosandActsForm;
import org.bf.disability.service.UploadGosandActsService;

/**
 *
 * @author 695048
 */
public class UploadGosandActsServiceImpl implements UploadGosandActsService {

    public int insertDetails(DataSource ds,UploadGosandActsDTO dto, UploadGosandActsForm bdrform, HttpServletRequest request, String url) throws Exception {
        int details = 0;
        UploadGosandActsDAO bdrdao = new UploadGosandActsDAO();
        try {
            details = bdrdao.insertDetails(ds,dto, bdrform, request, url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return details;
    }

    public ArrayList getGosUploadDetails(DataSource ds,UploadGosandActsDTO uploadGosandActsDTO) throws Exception {

        ArrayList gosAndActs = new ArrayList();
        UploadGosandActsDAO uploadGosandActsDAO = new UploadGosandActsDAO();
        try {
            gosAndActs = uploadGosandActsDAO.getGosUploadDetails(ds,uploadGosandActsDTO);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return gosAndActs;

    }


    public ArrayList getEditDetails(UploadGosandActsDTO uploadGosandActsDTO) throws Exception {
        ArrayList  getEditDetails=new ArrayList();
        UploadGosandActsDAO uploadGosandActsDAO = new UploadGosandActsDAO();
        try {
//            getEditDetails = uploadGosandActsDAO.getEditDetails(uploadGosandActsDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getEditDetails;
    }

    public ArrayList getSubject(DataSource ds,UploadGosandActsDTO uploadGosandActsDTO) throws Exception {

        ArrayList gosAndActs = new ArrayList();
        UploadGosandActsDAO uploadGosandActsDAO = new UploadGosandActsDAO();
        try {
            gosAndActs = uploadGosandActsDAO.getSubject(ds,uploadGosandActsDTO);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return gosAndActs;
    }

    public ArrayList getGosDetailsHomePage( DataSource ds,UploadGosandActsForm gosandActsForm ) throws Exception {
        ArrayList gosAndActs = new ArrayList();
        UploadGosandActsDAO uploadGosandActsDAO = new UploadGosandActsDAO();
        try {
            gosAndActs = uploadGosandActsDAO.getGosDetailsHomePage(ds,gosandActsForm);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return gosAndActs;

    }

    public int inActiveUploadGo(DataSource ds,UploadGosandActsDTO dto, UploadGosandActsForm bdrform, HttpServletRequest request, String url,String rowid) throws Exception {
        int details = 0;
        UploadGosandActsDAO gosandActsDAO = new UploadGosandActsDAO();
        try {
            details = gosandActsDAO.inupdateUploadGo(ds,dto, bdrform, request, url,rowid);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return details;
    }

    public int inActiveStatus(DataSource ds,String rowID) throws Exception {
        int details = 0;
        UploadGosandActsDAO gosandActsDAO = new UploadGosandActsDAO();
        try {
            details = gosandActsDAO.inActiveStatus(ds,rowID);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return details;
    }

    public ArrayList getDepartmentList(DataSource ds,UploadGosandActsForm form) throws Exception {
        ArrayList departmentList = new ArrayList();
        UploadGosandActsDAO uploadGosandActsDAO = new UploadGosandActsDAO();
        try {
            departmentList = uploadGosandActsDAO.getDepartmentList(ds,form);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return departmentList;
    }

    public ArrayList getEditDetails(DataSource ds,String rowid1) {
        ArrayList  getEditDetails=new ArrayList();
        UploadGosandActsDAO uploadGosandActsDAO = new UploadGosandActsDAO();
        try {
            getEditDetails = uploadGosandActsDAO.getEditDetails(ds,rowid1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getEditDetails;
    }
     public ArrayList getGosDetailsBySection(DataSource ds, UploadGosandActsForm gosandActsForm, String section) {
        ArrayList gosAndActs = new ArrayList();
        UploadGosandActsDAO uploadGosandActsDAO = new UploadGosandActsDAO();
        try {
            gosAndActs = uploadGosandActsDAO.getGosDetailsBySection(ds,gosandActsForm,section);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return gosAndActs;

    }

}
