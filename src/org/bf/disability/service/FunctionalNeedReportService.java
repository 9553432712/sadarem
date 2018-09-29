/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.service;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.dto.FunctionalNeedReportDTO;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.form.CampDailyReportForm;

/**
 *
 * @author 509865
 */
public interface FunctionalNeedReportService {

    public ArrayList getMedicalIntervention(DataSource ds,
            FunctionalNeedReportDTO districtDTO) throws SADAREMDBException,SQLException;

    public ArrayList getAssistiveDevicesProthosis(DataSource ds,
            FunctionalNeedReportDTO districtDTO) throws SADAREMDBException,SQLException;

    public ArrayList getAssistiveDevicesOrthosis(DataSource ds,
            FunctionalNeedReportDTO districtDTO) throws SADAREMDBException,SQLException;

    public ArrayList getAssistiveDevicesWalkingAids(DataSource ds,
            FunctionalNeedReportDTO districtDTO) throws SADAREMDBException,SQLException;

    public ArrayList getAssistiveDevicesADLEquipments(DataSource ds,
            FunctionalNeedReportDTO districtDTO) throws SADAREMDBException,SQLException;

    public ArrayList getAssistiveDevicesMobility(DataSource ds,
            FunctionalNeedReportDTO districtDTO) throws SADAREMDBException,SQLException;

    public ArrayList getOtherNeeds(DataSource ds,
            FunctionalNeedReportDTO districtDTO) throws SADAREMDBException,SQLException;

    public ArrayList getMedicalInterventionVI(DataSource ds,
            FunctionalNeedReportDTO districtDTO) throws SADAREMDBException,SQLException;

    public ArrayList getAssistiveAugmentativeDevicesVI(DataSource ds,
            FunctionalNeedReportDTO districtDTO) throws SADAREMDBException,SQLException;

    public ArrayList getOtherNeedsVI(DataSource ds,
            FunctionalNeedReportDTO districtDTO) throws SADAREMDBException,SQLException;

    public ArrayList getMedicalInterventionHI(DataSource ds,
            FunctionalNeedReportDTO districtDTO) throws SADAREMDBException,SQLException;

    public ArrayList getAssistiveAugmentativeDevicesHI(DataSource ds,
            FunctionalNeedReportDTO districtDTO) throws SADAREMDBException,SQLException;

    public ArrayList getOtherNeedsHI(DataSource ds,
            FunctionalNeedReportDTO districtDTO) throws SADAREMDBException,SQLException;

    public ArrayList getMedicalInterventionMR(DataSource ds,
            FunctionalNeedReportDTO districtDTO) throws SADAREMDBException,SQLException;

    public ArrayList getAssistiveAugmentativeDevicesMR(DataSource ds,
            FunctionalNeedReportDTO districtDTO) throws SADAREMDBException,SQLException;

    public ArrayList getOtherNeedsMR(DataSource ds,
            FunctionalNeedReportDTO districtDTO) throws SADAREMDBException,SQLException;

    public ArrayList getMedicalInterventionMI(DataSource ds,
            FunctionalNeedReportDTO districtDTO) throws SADAREMDBException,SQLException;

    public ArrayList getOtherNeedsMI(DataSource ds,
            FunctionalNeedReportDTO districtDTO) throws SADAREMDBException,SQLException;

    public ArrayList getAllFunctionalNeeds(DataSource ds,
            FunctionalNeedReportDTO districtDTO) throws SADAREMDBException,SQLException;

    public ArrayList getCommonGeneralNeeds(DataSource ds,
            FunctionalNeedReportDTO districtDTO) throws SADAREMDBException,SQLException;

    public ArrayList getMRGeneralNeeds(DataSource ds,
            FunctionalNeedReportDTO districtDTO) throws SADAREMDBException,SQLException;

    public ArrayList getMIGeneralNeeds(DataSource ds,
            FunctionalNeedReportDTO districtDTO) throws SADAREMDBException,SQLException;

    public ArrayList getMultipleGeneralNeeds(DataSource ds,
            FunctionalNeedReportDTO districtDTO) throws SADAREMDBException,SQLException;

    public ArrayList getPersonalDetailsAnalysis(DataSource ds,
            FunctionalNeedReportDTO districtDTO, int start, int end,
            String columnName, String columnValue, String tableName)
            throws SADAREMDBException,SQLException;

    public ArrayList getPersonalDetailsTwoColumns(DataSource ds,
            FunctionalNeedReportDTO districtDTO, int start, int end,
            String columnNameOne, String columnValueOne, String columnNameTwo,
            String columnValueTwo, String tableName)
            throws SADAREMDBException,SQLException;

    public ArrayList getNotComeToSadaremCamp(DataSource ds,
            String districtID, String phase) throws SADAREMDBException,SQLException;

    public ArrayList getNotComeToCampPersonalDetails(DataSource ds,
            String districtID, String mandalID, String phase, String reportType)
            throws SADAREMDBException,SQLException;

    public ArrayList getPersonalDetailsSurgery(DataSource ds,
            FunctionalNeedReportDTO districtDTO, String columnName, String tableName)
            throws SADAREMDBException,SQLException;

    public ArrayList getpersonalGeneralNeeds(DataSource ds,
            FunctionalNeedReportDTO districtDTO, int start, int end,
            String columnName, String columnValue, String tableName)
            throws SADAREMDBException,SQLException;

    public ArrayList getpersonalGeneralNeedsTotal(DataSource ds,
            FunctionalNeedReportDTO districtDTO, int start, int end,
            String columnName, String columnValue, String tableName)
            throws SADAREMDBException,SQLException;

    public ArrayList getEmpWiseDetails(DataSource ds, String districtId, String mandalId, String villageId, FunctionalNeedReportDTO functionalNeedReportDTO)throws SADAREMDBException,SQLException;

    public ArrayList getPersonalempWiseDetails(DataSource ds, String district_id, String mandal_id, String village_id, String habCode, String caste,String urbanId,String fromDate, String todate) throws SADAREMDBException,SQLException;

    public ArrayList getEducationWiseReport(DataSource ds,
            FunctionalNeedReportDTO educationwiseDTO) throws SADAREMDBException,SQLException;

    public ArrayList getPersonalEducationWiseReport(DataSource ds, String district_id, String mandal_id, String village_id, String hab_id, String education,String urbanId) throws SADAREMDBException,SQLException;

    public ArrayList getAreaDetails(DataSource ds, String district_id, String mandal_id, String village_id, String hab_id) throws SADAREMDBException,SQLException;

    public ArrayList getCasteWiseDetails(DataSource ds, String district_id, String mandal_id, String village_id, String caste, FunctionalNeedReportDTO functionalNeedReportDTO) throws SADAREMDBException,SQLException;

    public ArrayList getPersonalCasteWiseDetails(DataSource ds, String district_id, String mandal_id, String village_id, String habCode, String caste,String urbanId,String fromDate,String todate) throws SADAREMDBException,SQLException;

    public ArrayList getAgeDetails(DataSource ds, String district_id, String mandal_id, String village, String fromAge, String toAge, FunctionalNeedReportDTO functionalNeedReportDTO) throws SADAREMDBException,SQLException;

    public ArrayList getPersonalAgeWiseDetails(DataSource ds, String district_id, String mandal_id, String village, String habCode, String age, String fromAge, String toAge,String urbanId,String fromDate, String todate) throws SADAREMDBException,SQLException;

    public ArrayList getMandals(DataSource datasource, String districtid,String urbanId) throws SADAREMDBException,SQLException;

    public ArrayList getMandalsScheduleMandals(DataSource datasource, String districtid) throws SADAREMDBException,SQLException;

    public ArrayList getVillageAll(DataSource datasource, String districtid, String mandalid) throws SADAREMDBException,SQLException;

    public ArrayList getScheduleVillages(DataSource datasource, String districtid, String mandalid) throws SADAREMDBException,SQLException;

    public ArrayList alreadyEnteredVillages(DataSource datasource, String districtid, String mandalid, String village_id, String date) throws SADAREMDBException,SQLException;

    public String checkConditions(DataSource datasource, String districtid, String mandalid, String village_id, String date) throws SADAREMDBException,SQLException;

    // public ArrayList getAppealVillageAll(DataSource datasource, String districtid, String mandalid) throws SADAREMDBException,SQLException;
    public int insertVillages(DataSource datasource, ArrayList villages, String districtid, String mandalid, String fromDate, String loginID, String systemIP) throws SADAREMDBException,SQLException;

    public ArrayList alreadyEnteredVillagesAll(DataSource datasource, ArrayList villages, String districtid, String mandalid, String fromDate) throws SADAREMDBException,SQLException;

    public int insertVillage(DataSource datasource, String districtid, String mandalid, String villageId, String fromDate, String loginID, String systemIP) throws SADAREMDBException,SQLException;

    // public ArrayList getCasteWiseSingleDetails(DataSource ds, String district_id, String mandal_id, String village_id, String caste) throws SADAREMDBException,SQLException;
    public ArrayList getMaritalStatusDetails(DataSource ds, String district_id, String mandal_id, String village_id, String habCode, FunctionalNeedReportDTO functionalNeedReportDTO) throws SADAREMDBException,SQLException;

    public ArrayList getMaritalStatusPersonalDetails(DataSource ds, String district_id, String mandal_id, String village_id, String habCode, String mStatus,String urbanId,String fromDate, String todate) throws SADAREMDBException,SQLException;

    public ArrayList getParentsMaritalStatusDetails(DataSource ds, String district_id, String mandal_id, String village_id, String habCode, FunctionalNeedReportDTO functionalNeedReportDTO) throws SADAREMDBException,SQLException;

    public ArrayList getParentsMaritalIndivdualDetails(DataSource ds, String district_id, String mandal_id, String village_id, String habCode, String parentStatus,String fromDate,String todate) throws SADAREMDBException,SQLException;

    /** Added by Mohan on 28/10/2011 */
    public ArrayList getRachaBandaData(DataSource ds, String district_id) throws SADAREMDBException,SQLException;

    public ArrayList getRachaBandaPersonalDetails(DataSource ds, String district_id, String mandal_id, String modeStatus) throws SADAREMDBException,SQLException;

    //public ArrayList getAppealPersonalDetails(DataSource ds,String district_id, String mandal_id,String village_id,String fromDate,String toDate,String disabilityStatus,String disabiltity,String baseDistrict,String baseMandal,String baseVillage) throws  SADAREMException;
    public ArrayList getAppealPersonalDetails(DataSource ds, String district_id, String mandal_id, String village_id, String fromDate, String toDate, String disabilityStatus, String disabiltity, String baseDistrict, String baseMandal, String baseVillage, String basehab, String pensionPhase) throws SADAREMDBException,SQLException;

    public ArrayList getRationPersonalReportDetails(DataSource ds, String district_id, String mandal_id, String village_id) throws SADAREMDBException,SQLException;

    public ArrayList campDetails(DataSource ds, String district_id) throws SADAREMDBException,SQLException;

    /** End of Mohan Code **/

    /* Added by Abhi **/
    // CampDailyAssessment Report
    public ArrayList getCampDailyReportDetails(DataSource ds, String district_id) throws SADAREMDBException,SQLException;

    public ArrayList getDisabilityDetails(DataSource ds) throws SADAREMDBException,SQLException;

    public int insertPwdAssessementDetails(DataSource ds, CampDailyReportForm campDailyReportForm, String districtid, String loginID, String systemIP) throws SADAREMDBException,SQLException;
    /** End */


    public ArrayList getNotComeToCampPersonalDetailsTotal(DataSource ds,String districtID, String phaseName, String reportType) throws SADAREMDBException,SQLException;

    public String getAreaName(DataSource ds,String districtid, String mandalid, String villageid);
}
