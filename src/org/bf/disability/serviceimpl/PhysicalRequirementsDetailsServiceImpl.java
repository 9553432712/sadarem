/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.serviceimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.dao.PhysicalRequirementsDetailsDAO;
import org.bf.disability.dto.PhysicalRequirementsDetailsDTO;
import org.bf.disability.service.PhysicalRequirementsDetailsService;

/**
 *
 * @author 693461
 */
public class PhysicalRequirementsDetailsServiceImpl implements PhysicalRequirementsDetailsService{

   public ArrayList getphysicalRequirementDetails(DataSource ds,String districtId,String mandalId,String villageId)throws SADAREMDBException,SQLException {

   ArrayList physicalRequiremenList =new ArrayList();
   PhysicalRequirementsDetailsDAO physicalRequirementsDetailsDAO = new PhysicalRequirementsDetailsDAO();

       try {

       physicalRequiremenList = physicalRequirementsDetailsDAO.getphysicalRequirementDetails(ds, districtId, mandalId, villageId);
       } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getphysicalRequirementDetails", "PhysicalRequirementsDetailsServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PhysicalRequirementsDetailsServiceImpl", "getphysicalRequirementDetails");
        }

     return physicalRequiremenList;
   }

       public ArrayList getphysicalRequirementPersonalDetails(DataSource ds,String districtId,String mandalId,String villageId)throws SADAREMDBException,SQLException {

          ArrayList personalRequirementList =new ArrayList();
          PhysicalRequirementsDetailsDTO physicalRequirementsDetailsDTO = new PhysicalRequirementsDetailsDTO();
          PhysicalRequirementsDetailsDAO physicalRequirementsDetailsDAO = new PhysicalRequirementsDetailsDAO();
          try {
              personalRequirementList = physicalRequirementsDetailsDAO.getphysicalRequirementPersonalDetails(ds, districtId, mandalId, villageId);

          } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getphysicalRequirementPersonalDetails", "PhysicalRequirementsDetailsServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PhysicalRequirementsDetailsServiceImpl", "getphysicalRequirementPersonalDetails");
        }

          return personalRequirementList;
      }

}
