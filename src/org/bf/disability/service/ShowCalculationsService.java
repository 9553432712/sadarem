package org.bf.disability.service;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.bean.AmputationBean;
import org.bf.disability.bean.BBPTIBean;
import org.bf.disability.bean.BKTBean;
import org.bf.disability.bean.CardioPulmonaryBean;
import org.bf.disability.bean.DSTBean;
import org.bf.disability.bean.DwarfismBean;
import org.bf.disability.bean.HearingImpairmentBean;
import org.bf.disability.bean.LowerExtremityBean;
import org.bf.disability.bean.MISICBean;
import org.bf.disability.bean.MentalIllnessBean;
import org.bf.disability.bean.MentalRetardationBean;
import org.bf.disability.bean.PATBean;
import org.bf.disability.bean.PIDuetoNeurologicalConditionsBean;
import org.bf.disability.bean.SFBBean;
import org.bf.disability.bean.TransverseBean;
import org.bf.disability.bean.TrunkBean;
import org.bf.disability.bean.UpperExtremityBean;
import org.bf.disability.bean.VSMSBean;
import org.bf.disability.bean.VisualImpairmentBean;

public interface ShowCalculationsService {

    public void decideOHTypeofDisability(DataSource dataSource, HttpServletRequest request, String personcode) throws SADAREMDBException, SQLException;

    public void populateVICalculations(DataSource dataSource, HttpServletRequest request, String personcode) throws SADAREMDBException, SQLException;

    public void populateHICalculations(DataSource dataSource, HttpServletRequest request, String personcode) throws SADAREMDBException, SQLException;

    public void decideMRTypeofDisability(DataSource dataSource, HttpServletRequest request, String personcode) throws SADAREMDBException, SQLException;

    public void populateMICalculations(DataSource dataSource, HttpServletRequest request, String personcode) throws SADAREMDBException, SQLException;

    public void decideMultipleTypeofDisability(DataSource dataSource, HttpServletRequest request, String personcode) throws SADAREMDBException, SQLException;

    public VisualImpairmentBean VICalculationLogic(VisualImpairmentBean visualImpairmentBean) throws SADAREMDBException, SQLException;

    public HearingImpairmentBean HICalculationLogic(HearingImpairmentBean hearingImpairmentBean) throws SADAREMDBException, SQLException;

    public MentalIllnessBean MICalculationLogic(MentalIllnessBean mentalIllnessBean) throws SADAREMDBException, SQLException;

    public void populateUpperExtremityCalculations(DataSource dataSource, HttpServletRequest request, String personcode) throws SADAREMDBException, SQLException;

    public UpperExtremityBean upperExtremityCalculationsLogic(UpperExtremityBean upperExtremityBean) throws SADAREMDBException, SQLException;

    public void populateLowerExtremityCalculations(DataSource dataSource, HttpServletRequest request, String personcode) throws SADAREMDBException, SQLException;

    public LowerExtremityBean lowerExtremityCalculationsLogic(LowerExtremityBean lowerExtremityBean) throws SADAREMDBException, SQLException;

    public void populateAmputationCalculations(DataSource dataSource, HttpServletRequest request, String personcode) throws SADAREMDBException, SQLException;

    public AmputationBean amputationCalculationsLogic(AmputationBean amputationBean) throws SADAREMDBException, SQLException;

    public void populateTransverseCalculations(DataSource dataSource, HttpServletRequest request, String personcode) throws SADAREMDBException, SQLException;

    public TransverseBean transverseCalculationsLogic(TransverseBean transverseBean) throws SADAREMDBException, SQLException;

    public void populateTrunkCalculations(DataSource dataSource, HttpServletRequest request, String personcode) throws SADAREMDBException, SQLException;

    public TrunkBean trunkCalculationsLogic(TrunkBean trunkBean) throws SADAREMDBException, SQLException;

    public void populatePIDuetoNeurologicalConditionsCalculations(DataSource dataSource, HttpServletRequest request, String personcode) throws SADAREMDBException, SQLException;

    public PIDuetoNeurologicalConditionsBean PIDuetoNeurologicalConditionsCalcualtionsLogic(PIDuetoNeurologicalConditionsBean pIDuetoNeurologicalConditionsBean) throws SADAREMDBException, SQLException;

    public void populateCardioPulmonaryCalculations(DataSource dataSource, HttpServletRequest request, String personcode) throws SADAREMDBException, SQLException;

    public CardioPulmonaryBean cardioPulmonaryCalculationsLogic(CardioPulmonaryBean cardioPulmonaryBean) throws SADAREMDBException, SQLException;

    public void populateDwarfismCalculations(DataSource dataSource, HttpServletRequest request, String personcode) throws SADAREMDBException, SQLException;

    public DwarfismBean dwarfismCalculationsLogic(DwarfismBean dwarfismBean) throws SADAREMDBException, SQLException;

    //MR Test related abstract methods
    public void populateDSTCalculations(DataSource dataSource, HttpServletRequest request, String personcode) throws SADAREMDBException, SQLException;

    public DSTBean dstCalculationsLogic(DSTBean dSTBean) throws SADAREMDBException, SQLException;

    public void populateVSMSCalculations(DataSource dataSource, HttpServletRequest request, String personcode) throws SADAREMDBException, SQLException;

    public VSMSBean vsmsCalculationsLogic(VSMSBean vSMSBean) throws SADAREMDBException, SQLException;

    public void populateBKTCalculations(DataSource dataSource, HttpServletRequest request, String personcode) throws SADAREMDBException, SQLException;

    public BKTBean bktCalculationsLogic(BKTBean bKTBean) throws SADAREMDBException, SQLException;

    public void populateMISICCalculations(DataSource dataSource, HttpServletRequest request, String personcode) throws SADAREMDBException, SQLException;

    public MISICBean misicCalculationsLogic(MISICBean mISICBean) throws SADAREMDBException, SQLException;

    public void populateSFBCalculations(DataSource dataSource, HttpServletRequest request, String personcode) throws SADAREMDBException, SQLException;

    public SFBBean sfbCalculationsLogic(SFBBean sFBBean) throws SADAREMDBException, SQLException;

    public void populatePATCalculations(DataSource dataSource, HttpServletRequest request, String personcode) throws SADAREMDBException, SQLException;

    public PATBean patCalculationsLogic(PATBean pATBean) throws SADAREMDBException, SQLException;

    public void populateBBPTICalculations(DataSource dataSource, HttpServletRequest request, String personcode) throws SADAREMDBException, SQLException;

    public BBPTIBean bbptiCalculationsLogic(BBPTIBean bBPTIBean) throws SADAREMDBException, SQLException;

    public void populateMRCalculations(DataSource dataSource, HttpServletRequest request, String personcode) throws SADAREMDBException, SQLException;

    public MentalRetardationBean mrCalculationsLogic(MentalRetardationBean mentalRetardationBean) throws SADAREMDBException, SQLException;
}
