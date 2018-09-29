/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.service;

import com.jsptags.navigation.pager.parser.ParseException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.form.PercentageWiseReportForm;

/**
 *
 * @author 842412
 */
public interface PercentageWiseReportService {
    public ArrayList getPecentageWiseData(PercentageWiseReportForm percentageWiseReportForm, DataSource ds)  throws SADAREMDBException, SQLException, ParseException;

    public ArrayList getIndividualDetails(PercentageWiseReportForm percentageWiseReportForm, DataSource ds) throws SADAREMDBException, SQLException, ParseException;
}
