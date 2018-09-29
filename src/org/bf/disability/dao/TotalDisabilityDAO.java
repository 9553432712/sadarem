/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.TotalPercentageDTO;

import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author sivakumar_g
 */
public class TotalDisabilityDAO {

    public TotalPercentageDTO getTotalpercentage(DataSource ds, String personcode) throws SADAREMDBException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        TotalPercentageDTO totalpercentagedto = new TotalPercentageDTO();

        try {

            con = DBConnection.getConnection();
            String query = "select UpperExtrimity,LowerExtrimity,Amputation,Transverse,Trunk,Evaluation,Cardiopulmonary,Dwarfism,HearingImpairment,VisualImpairment,MentalRetardation,MentalIllness,TotalLocomotor from tblPerson_Disability_TotalValue_Details where Person_Code=? and status='Active'";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personcode);
            rs = pstmt.executeQuery();
            while (rs.next()) {

                totalpercentagedto.setUpperExtrimity(rs.getFloat("UpperExtrimity"));
                totalpercentagedto.setLowerExtrimity(rs.getFloat("LowerExtrimity"));
                totalpercentagedto.setAmputation(rs.getFloat("Amputation"));
                totalpercentagedto.setTransverse(rs.getFloat("Transverse"));
                totalpercentagedto.setTrunk(rs.getLong("Trunk"));
                totalpercentagedto.setEvaluation(rs.getFloat("Evaluation"));
                totalpercentagedto.setCardiopulmonary(rs.getFloat("Cardiopulmonary"));
                totalpercentagedto.setDwarfism(rs.getFloat("Dwarfism"));
                totalpercentagedto.setHearingimpairment(rs.getFloat("HearingImpairment"));
                totalpercentagedto.setVisualimpairment(rs.getFloat("VisualImpairment"));
                totalpercentagedto.setMentalretardation(rs.getFloat("MentalRetardation"));
                totalpercentagedto.setMentalillness(rs.getFloat("MentalIllness"));
                totalpercentagedto.setTotalphysical(rs.getFloat("TotalLocomotor"));



            }
        } //end of try block
        catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getTotalpercentage", "TotalDisabilityDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TotalDisabilityDAO", "getTotalpercentage");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getTotalpercentage", "TotalDisabilityDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TotalDisabilityDAO", "getTotalpercentage");
        }//end of catch block
        finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }//end of finally block
        return totalpercentagedto;
    }
}
