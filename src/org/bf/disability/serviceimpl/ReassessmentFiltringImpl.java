/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.serviceimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dao.ReassessmentFiltringDAO;
import org.bf.disability.service.ReassessmentFiltringService;

/**
 *
 * @author 484898
 */
public class ReassessmentFiltringImpl implements ReassessmentFiltringService {

    /** Implentation for getReassessmentDetails */
    public ArrayList getReassessmentDetails(DataSource ds, String districtId, String mandalId, String villageId, String habitationId) throws SADAREMDBException, SQLException {

        ArrayList reassessmentDetails = new ArrayList();
        ReassessmentFiltringDAO reassessmentDAO = new ReassessmentFiltringDAO();
        reassessmentDetails = reassessmentDAO.getReassessmentDetails(ds, districtId, mandalId, villageId, habitationId);

        return reassessmentDetails;
    }

    public int updateFlagForPersons(DataSource ds, String personIds) throws SADAREMDBException, SQLException {

        int deleteRecords = 0;
        ReassessmentFiltringDAO reassessmentDAO = new ReassessmentFiltringDAO();
        deleteRecords = reassessmentDAO.updateFlagForPersons(ds, personIds);

        return deleteRecords;
    }

    public ArrayList getHabitations(DataSource ds, String districtId, String mandalId, String villageId) throws SADAREMDBException, SQLException {
        ArrayList habitations = new ArrayList();
        ReassessmentFiltringDAO reassessmentDAO = new ReassessmentFiltringDAO();
        habitations = reassessmentDAO.getHabitations(ds, districtId, mandalId, villageId);
        return habitations;
    }
}
