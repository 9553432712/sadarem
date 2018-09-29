/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.service;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.bf.disability.dto.UploadGosandActsDTO;
import org.bf.disability.form.UploadGosandActsForm;

/**
 *
 * @author 695048
 */
public interface UploadGosandActsService {

    public int insertDetails(DataSource ds, UploadGosandActsDTO dto, UploadGosandActsForm bdrform, HttpServletRequest request, String url) throws Exception;

    public ArrayList getGosUploadDetails(DataSource ds, UploadGosandActsDTO uploadGosandActsDTO) throws Exception;

    public ArrayList getSubject(DataSource ds, UploadGosandActsDTO uploadGosandActsDTO) throws Exception;

    public ArrayList getGosDetailsHomePage(DataSource ds, UploadGosandActsForm gosandActsForm) throws Exception;

    public int inActiveUploadGo(DataSource ds, UploadGosandActsDTO dto, UploadGosandActsForm bdrform, HttpServletRequest request, String url, String rowid) throws Exception;

    public int inActiveStatus(DataSource ds, String rowID) throws Exception;

    public ArrayList getDepartmentList(DataSource ds, UploadGosandActsForm form) throws Exception;

    public ArrayList getEditDetails(DataSource ds, String rowid1);

    public ArrayList getGosDetailsBySection(DataSource ds, UploadGosandActsForm contactsDetailsForm, String section);
}
