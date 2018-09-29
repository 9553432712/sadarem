/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.dao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.SearchRequestStatusDTO;

import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author 310926
 */
public class SearchRequestStatusDAO {

    /**
     * this method fetches disabilitywise information from database
     * @param ds
     * @param SearchRequestStatusDTO
     * @throws org.bf.disability.Exception.SADAREMException
     * @return list
     */
    public ArrayList getDetails(DataSource ds,
            SearchRequestStatusDTO searchRequestStatusDTO)
            throws SADAREMDBException, SQLException {

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList getdetails = new ArrayList();
        String sql = null;
        try {
            con = DBConnection.getConnection();
            
            sql = "select r.requestid,r.name,r.relationname,r.houseno,b.mandal_name,d.district_name,e.village_name,"
                    + "f.habitation_name,r.person_code,convert(varchar,r.created_Date,103),m.requesttypename,"
                    + "case when updatedpersonstatus is not null then 'Updated' when  c.requeststatus='Approval' then 'Approved' else c.requeststatus end status  "
                    + "from RequestDetails r  "
                    + "join RequestTypeMapping c on (c.requestid =r.requestid) "
                    + "join tblDistrict_Details d on(r.districtid =d.district_id) "
                    + "join tblmandal_Details b on (r.districtid =b.district_id) and (r.mandalid =b.mandal_id) "
                    + "join tblVillage_Details e on (r.districtid =e.district_id) and (r.mandalid =e.mandal_id) "
                    + "and (r.villageid =e.village_id)"
                    + "join tblHabitation_Details f on (r.HabitationID =f.Habitation_id) and ( r.districtid =f.district_id) "
                    + "and (r.mandalid =f.mandal_id) and (r.villageid =f.village_id) "
                    + "join tblrequesttypemaster m on (c.requesttypeid = m.requesttypeid) "
                    + "where r.status='Active'";


            if (searchRequestStatusDTO.getType() != null && !searchRequestStatusDTO.getType().equalsIgnoreCase("")) {

                if (searchRequestStatusDTO.getSadaremId() != null && !searchRequestStatusDTO.getSadaremId().equalsIgnoreCase("")) {
                    if (searchRequestStatusDTO.getType().equalsIgnoreCase("1")) {
                        sql += " and r.person_code=?";
                    } else {
                        sql += " and r.requestid=?";
                    }
                }
            }
            pstmt = con.prepareStatement(sql);
            if (searchRequestStatusDTO.getType() != null && !searchRequestStatusDTO.getType().equalsIgnoreCase(""))
            {
                if (searchRequestStatusDTO.getSadaremId() != null && !searchRequestStatusDTO.getSadaremId().equalsIgnoreCase(""))
                {
                    if (searchRequestStatusDTO.getType().equalsIgnoreCase("1"))
                    {
                    	pstmt.setString(1, searchRequestStatusDTO.getSadaremId());                        
                    } 
                    else
                    {
                    	pstmt.setString(1, searchRequestStatusDTO.getSadaremId());
                    }
                }
            }
            

            rs = pstmt.executeQuery();

            while (rs.next()) {
                SearchRequestStatusDTO detailsDTO = new SearchRequestStatusDTO();
                detailsDTO.setReq_id(rs.getString(1));
                detailsDTO.setPerson_code(rs.getString(9));
                detailsDTO.setName(rs.getString(2));
                detailsDTO.setRelation_name(rs.getString(3));
                detailsDTO.setDistrict_name(rs.getString(6));
                detailsDTO.setMandal_name(rs.getString(5));
                detailsDTO.setVillage_name(rs.getString(7));
                detailsDTO.setHabitation_name(rs.getString(8));
                detailsDTO.setHouseno(rs.getString(4));
                detailsDTO.setCreated_date(rs.getString(10));
                detailsDTO.setRequestType(rs.getString(11));
                detailsDTO.setStatus(rs.getString(12));
                getdetails.add(detailsDTO);
            }


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDetails", "SearchRequestStatusDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "SearchRequestStatusDAO", "getDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDetails", "SearchRequestStatusDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "SearchRequestStatusDAO", "getDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }
        return getdetails;
    }
}
