/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.serviceimpl;

import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.dao.PercentageWiseReportDAO;
import org.bf.disability.form.PercentageWiseReportForm;
import org.bf.disability.service.PercentageWiseReportService;
import com.jsptags.navigation.pager.parser.ParseException;
import java.sql.SQLException;
import java.util.ArrayList;
import org.bf.disability.Exception.SADAREMDBException;

/**
 *
 * @author 842412
 */
public class PercentageWiseReportServiceImpl implements PercentageWiseReportService{

    
    public ArrayList getPecentageWiseData(PercentageWiseReportForm percentageWiseReportForm, javax.sql.DataSource ds) throws SADAREMDBException, SQLException, ParseException{
        ArrayList PecentageWiseData=null;
        try {
            PercentageWiseReportDAO percentageWiseReportDAO=new PercentageWiseReportDAO();
            PecentageWiseData=percentageWiseReportDAO.getPecentageWiseData(percentageWiseReportForm, ds);
        } catch (Exception e) {
            e.printStackTrace();
        }

                return PecentageWiseData;
    }
 public ArrayList getIndividualDetails(PercentageWiseReportForm percentageWiseReportForm, DataSource ds) throws SADAREMDBException, SQLException, ParseException{
        ArrayList PecentageWiseData=null;
        try {
            PercentageWiseReportDAO percentageWiseReportDAO=new PercentageWiseReportDAO();
            PecentageWiseData=percentageWiseReportDAO.getIndividualDetails(percentageWiseReportForm, ds);
        } catch (Exception e) {
            e.printStackTrace();
        }

                return PecentageWiseData;
    }
}
