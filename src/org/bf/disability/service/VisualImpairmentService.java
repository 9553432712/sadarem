/*
 * VisualImpairmentService.java
 *
 * Created on June 18, 2008, 11:07 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.service;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.CardioPulmonaryDTO;
import org.bf.disability.dto.DwarfismDTO;
import org.bf.disability.dto.MentalIllnessDTO;

/**
 * This interface has abstract methods, whose implementation is to perform data
 * manipulation operations like insert, update, select and delete on Visual Impairment
 * @version 1.0
 * 
 */
public interface VisualImpairmentService {

    public int insertCardioPulmonary(DataSource ds, String personcode, float cardiopulmonaryvalue, String Systemip, String loginid, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public int insertCardioPulmonaryAU(DataSource ds, String personcode, float cardiopulmonaryvalue, String Systemip, String loginid, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public float getCardioPulmonaryDetails(DataSource ds, String personcode) throws SADAREMDBException, SQLException;

    public int updateCardioPolumonary(DataSource ds, String personcode, float cardiopulmonary, String systemip, String loginid, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public int insertDwarfism(DataSource ds, DwarfismDTO dto, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public int insertDwarfismAU(DataSource ds, DwarfismDTO dto, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public DwarfismDTO getDwarfismDetails(DataSource ds, String personcode) throws SADAREMDBException, SQLException;

    public int updateDwarfism(DataSource ds, DwarfismDTO dwarfismdto, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public String getGender(DataSource ds, String personid) throws SADAREMDBException, SQLException;

    public int insertMentalIllness(DataSource ds, MentalIllnessDTO dto, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public int insertMentalIllnessAU(DataSource ds, MentalIllnessDTO dto, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public MentalIllnessDTO getMentalIllnessDetails(DataSource ds, String personcode) throws SADAREMDBException, SQLException;

    public int updateMentalIllnesDetails(DataSource ds, MentalIllnessDTO millnessdto, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public int insertVisualImparment(DataSource ds, String personcode, float visualimparment, String systemip, String loginid, CardioPulmonaryDTO cardioPulmonarydto, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public int insertVisualImparmentAU(DataSource ds, String personcode, float visualimparment, String systemip, String loginid, CardioPulmonaryDTO cardioPulmonarydto, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public CardioPulmonaryDTO getVisualImpairment(DataSource ds, String personcode) throws SADAREMDBException, SQLException;

    public int updateVisualImparment(DataSource ds, String personcode, double visualimparment, String systemip, String loginid, CardioPulmonaryDTO cardioPulmonarydto, HttpServletRequest request) throws SADAREMDBException, SQLException;
}


/* public int insertCardioPulmonary(DataSource ds,String personcode,float cardiopulmonaryvalue,String Systemip,String loginid) throws SQLException
   {
      VisualImapairmentDAO dao=new VisualImapairmentDAO();
   return dao.insertCardioPulmonary(ds,personcode,cardiopulmonaryvalue,Systemip,loginid);
 
   }
 
    public int insertDwarfism(DataSource ds,DwarfismDTO dto) throws SQLException
    {
      VisualImapairmentDAO dao=new VisualImapairmentDAO();
      return dao.insertDwarfism(ds,dto);
    }
    public String getGender(DataSource ds,String personid)
    {
        VisualImapairmentDAO dao=new VisualImapairmentDAO();
        return dao.getGender(ds,personid);
    }
    public int insertMentalIllness(DataSource ds,MentalIllnessDTO dto)
    {
        VisualImapairmentDAO dao=new VisualImapairmentDAO();
        return dao.insertMentalIllness(ds,dto);
    }
 
    public float getCardioPulmonaryDetails(DataSource ds, String personcode) {
         VisualImapairmentDAO dao=new VisualImapairmentDAO();
         return dao.getCardioPulmonaryDetails(ds,personcode);
    }
 
    public int updateCardioPolumonary(DataSource ds, String personcode,float cardiopulmonary,String systemip,String loginid) throws SQLException
    {
       VisualImapairmentDAO dao=new VisualImapairmentDAO();
       return dao.updateCardioPolumonary(ds,personcode,cardiopulmonary,systemip,loginid);
    }
    public MentalIllnessDTO getMentalIllnessDetails(DataSource ds, String personcode)
    {
        VisualImapairmentDAO dao=new VisualImapairmentDAO();
        return dao.getMentalIllnessDetails(ds,personcode);
    }
 
    public int updateMentalIllnesDetails(DataSource ds, MentalIllnessDTO millnessdto) throws SQLException
    {
      VisualImapairmentDAO dao=new VisualImapairmentDAO();
      return dao.updateMentalIllnesDetails(ds,millnessdto);
    }
 
    public DwarfismDTO getDwarfismDetails(DataSource ds, String personcode) {
      VisualImapairmentDAO dao=new VisualImapairmentDAO();
      return dao.getDwarfismDetails(ds,personcode);
    }
 
    public int updateDwarfism(DataSource ds, DwarfismDTO dwarfismdto) throws SQLException {
        VisualImapairmentDAO dao=new VisualImapairmentDAO();
        return dao.updateDwarfism(ds,dwarfismdto);
    }
 
    public int insertVisualImparment(DataSource ds, String personcode, float visualimparment, String systemip,String loginid,CardioPulmonaryDTO cardioPulmonarydto) throws SQLException {
       VisualImapairmentDAO dao=new VisualImapairmentDAO();
       return dao.insertVisualImparment(ds,personcode,visualimparment,systemip,loginid, cardioPulmonarydto);
    }
 
    public CardioPulmonaryDTO getVisualImpairment(DataSource ds, String personcode) {
        VisualImapairmentDAO dao=new VisualImapairmentDAO();
        return dao.getVisualImpairment(ds,personcode);
    }
 
    public int updateVisualImparment(DataSource ds, String personcode, double visualimparment,String systemip,String loginid,CardioPulmonaryDTO cardioPulmonarydto) {
       VisualImapairmentDAO dao=new VisualImapairmentDAO();
        return dao.updateVisualImpairment(ds,personcode,visualimparment,systemip,loginid,cardioPulmonarydto);
    }
 
 
}
 */
