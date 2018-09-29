/*
 * StatusForPWDReportService.java
 *
 * Created on September 13, 2008, 2:09 PM
 */
package org.bf.disability.service;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.TerritoryDTO;

/**
 * This interface has abstract methods, whose implementation is useful in generating
 * Status for Person with Disability report.
 * @author Raghavendra T
 * @version 1.0
 */
public interface StatusForPWDReportService {

    public ArrayList getDistricts(DataSource datasource,
            TerritoryDTO territorydto) throws SADAREMDBException, SQLException;

    public ArrayList getMandals(DataSource datasource,
            TerritoryDTO territorydto) throws SADAREMDBException, SQLException;

    public ArrayList getVillages(DataSource datasource,
            TerritoryDTO territorydto) throws SADAREMDBException, SQLException;

    public ArrayList getHabitations(DataSource datasource,
            TerritoryDTO territorydto) throws SADAREMDBException, SQLException;
}
