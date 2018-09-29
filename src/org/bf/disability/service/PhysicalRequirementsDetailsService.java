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
public interface PhysicalRequirementsDetailsService {

    public ArrayList getphysicalRequirementDetails(DataSource ds, String districtId, String mandalId, String villageId) throws SADAREMDBException, SQLException;

    public ArrayList getphysicalRequirementPersonalDetails(DataSource ds, String districtId, String mandalId, String villageId) throws SADAREMDBException, SQLException;
}
