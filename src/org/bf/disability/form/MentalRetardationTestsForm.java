/*
 * MentalRetardationTestsForm.java
 *
 * Created on October 1, 2008, 11:13 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bf.disability.form;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.validator.ValidatorForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author svsganesh
 */
public class MentalRetardationTestsForm extends ValidatorForm {
    
//PAT variables starts here
    private String pat_Second_One;
    private String pat_Second_Two;
    private String pat_Second_Three;
    private String pat_Second_Four;
    private String pat_Second_Five;
    private String pat_Second_Six;
    private String pat_Second_Seven;
    private String pat_Second_Eight;
    private String pat_Second_Nine;
    private String pat_SA_One;
    private String pat_SA_Two;
    private String pat_SA_Three;
    private String pat_SA_Four;
    private String pat_SA_Five;
    private String pat_SA_Six;
    private String pat_SA_Seven;
    private String pat_SA_Eight;
    private String pat_SA_Nine;
    private String  pat_Remarks_One;
    private String  pat_Remarks_Two;
    private String  pat_Remarks_Three;
    private String  pat_Remarks_Four;
    private String  pat_Remarks_Five;
    private String  pat_Remarks_Six;
    private String  pat_Remarks_Seven;
    private String  pat_Remarks_Eight;
    private String  pat_Remarks_Nine;
    private String pat_Year;
    private String pat_Month;
    private double pat_IQ;
    private String loginID;
    private String systemIP;
    private String personcode;
//PAT variables Ends here
    
//SeguinTest varible Starts Here.
    private String sfbyear;
    private String sfbmonth;
    private String sfbtrialone;
    private String sfbtrialtwo;
    private String sfbtrialthree;
    private double sfbiq;
    //SeguinTest varible Ends Here.
    
    //MalinsTest varible Starts Here.
    private String systemip;
    private String loginid;
    private String misicinformationraw;
    private String misicinformationtq;
    private String misicpcrawscore;
    private String misicpctq;
    private String misiccomprehensionrawscore;
    private String misiccomprehensiontq;
    private String misicbdrawscore;
    private String misicbdtq;
    private String misicarithmeticrawscore;
    private String misicarithmetictq;
    private String misicoarawscore;
    private String misicoatq;
    private String misicsimilaritiesrawscore;
    private String misicsimilaritiestq;
    private String misiccodingrawscore;
    private String misiccodingtq;
    private String misicvocabularyrawscore;
    private String misicvocabularytq;
    private String misicmazesrawscore;
    private String misicmazestq;
    private String misicdigitspanrawscore;
    private String misicdigitspantq;
    private String digitspantqscore;
    private String sumofperformance;
    private double misiciq;
    
    //MalinsTest varible Ends Here.
    
    private String dstyear;
    private String dstmonth;
    private double dstdq;
    private String vsmsyear;
    private String vsmsmonth;
    private double vsmsvq;
    private String bictbasalage;
    private String bictterminalage;
    private String bictyear;
    private String bictmonth;
    private double bictiq;
    
    //Bhatia variables starts here
    private String bbpti_KOHS_Block_Design_Test;
    private String bbpti_Passalong_Test;
    private String bbpti_PatternDrawing_Test;
    private String bbpti_ImmediateMemory_Test;
    private String bbpti_PictureConstruction_Test;
    private String bbpti_IQ;
    //Bhatia variables Ends here
    
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        
        
        this.setSfbiq(0);
        
    }
    
    
    public String getPat_Second_Two() {
        return pat_Second_Two;
    }
    
    public void setPat_Second_Two(String pat_Second_Two) {
        this.pat_Second_Two = pat_Second_Two;
    }
    
    public String getPat_Second_Three() {
        return pat_Second_Three;
    }
    
    public void setPat_Second_Three(String pat_Second_Three) {
        this.pat_Second_Three = pat_Second_Three;
    }
    
    public String getPat_Second_Four() {
        return pat_Second_Four;
    }
    
    public void setPat_Second_Four(String pat_Second_Four) {
        this.pat_Second_Four = pat_Second_Four;
    }
    
    public String getPat_Second_Five() {
        return pat_Second_Five;
    }
    
    public void setPat_Second_Five(String pat_Second_Five) {
        this.pat_Second_Five = pat_Second_Five;
    }
    
    public String getPat_Second_Six() {
        return pat_Second_Six;
    }
    
    public void setPat_Second_Six(String pat_Second_Six) {
        this.pat_Second_Six = pat_Second_Six;
    }
    
    public String getPat_Second_Seven() {
        return pat_Second_Seven;
    }
    
    public void setPat_Second_Seven(String pat_Second_Seven) {
        this.pat_Second_Seven = pat_Second_Seven;
    }
    
    public String getPat_Second_Eight() {
        return pat_Second_Eight;
    }
    
    public void setPat_Second_Eight(String pat_Second_Eight) {
        this.pat_Second_Eight = pat_Second_Eight;
    }
    
    public String getPat_Second_Nine() {
        return pat_Second_Nine;
    }
    
    public void setPat_Second_Nine(String pat_Second_Nine) {
        this.pat_Second_Nine = pat_Second_Nine;
    }
    
    public String getPat_SA_One() {
        return pat_SA_One;
    }
    
    public void setPat_SA_One(String pat_SA_One) {
        this.pat_SA_One = pat_SA_One;
    }
    
    public String getPat_SA_Two() {
        return pat_SA_Two;
    }
    
    public void setPat_SA_Two(String pat_SA_Two) {
        this.pat_SA_Two = pat_SA_Two;
    }
    
    public String getPat_SA_Three() {
        return pat_SA_Three;
    }
    
    public void setPat_SA_Three(String pat_SA_Three) {
        this.pat_SA_Three = pat_SA_Three;
    }
    
    public String getPat_SA_Four() {
        return pat_SA_Four;
    }
    
    public void setPat_SA_Four(String pat_SA_Four) {
        this.pat_SA_Four = pat_SA_Four;
    }
    
    public String getPat_SA_Five() {
        return pat_SA_Five;
    }
    
    public void setPat_SA_Five(String pat_SA_Five) {
        this.pat_SA_Five = pat_SA_Five;
    }
    
    public String getPat_SA_Six() {
        return pat_SA_Six;
    }
    
    public void setPat_SA_Six(String pat_SA_Six) {
        this.pat_SA_Six = pat_SA_Six;
    }
    
    public String getPat_SA_Seven() {
        return pat_SA_Seven;
    }
    
    public void setPat_SA_Seven(String pat_SA_Seven) {
        this.pat_SA_Seven = pat_SA_Seven;
    }
    
    public String getPat_SA_Eight() {
        return pat_SA_Eight;
    }
    
    public void setPat_SA_Eight(String pat_SA_Eight) {
        this.pat_SA_Eight = pat_SA_Eight;
    }
    
    public String getPat_SA_Nine() {
        return pat_SA_Nine;
    }
    
    public void setPat_SA_Nine(String pat_SA_Nine) {
        this.pat_SA_Nine = pat_SA_Nine;
    }
    
    public String getPat_Remarks_One() {
        return pat_Remarks_One;
    }
    
    public void setPat_Remarks_One(String pat_Remarks_One) {
        this.pat_Remarks_One = pat_Remarks_One;
    }
    
    public String getPat_Remarks_Two() {
        return pat_Remarks_Two;
    }
    
    public void setPat_Remarks_Two(String pat_Remarks_Two) {
        this.pat_Remarks_Two = pat_Remarks_Two;
    }
    
    public String getPat_Remarks_Three() {
        return pat_Remarks_Three;
    }
    
    public void setPat_Remarks_Three(String pat_Remarks_Three) {
        this.pat_Remarks_Three = pat_Remarks_Three;
    }
    
    public String getPat_Remarks_Four() {
        return pat_Remarks_Four;
    }
    
    public void setPat_Remarks_Four(String pat_Remarks_Four) {
        this.pat_Remarks_Four = pat_Remarks_Four;
    }
    
    public String getPat_Remarks_Five() {
        return pat_Remarks_Five;
    }
    
    public void setPat_Remarks_Five(String pat_Remarks_Five) {
        this.pat_Remarks_Five = pat_Remarks_Five;
    }
    
    public String getPat_Remarks_Six() {
        return pat_Remarks_Six;
    }
    
    public void setPat_Remarks_Six(String pat_Remarks_Six) {
        this.pat_Remarks_Six = pat_Remarks_Six;
    }
    
    public String getPat_Remarks_Seven() {
        return pat_Remarks_Seven;
    }
    
    public void setPat_Remarks_Seven(String pat_Remarks_Seven) {
        this.pat_Remarks_Seven = pat_Remarks_Seven;
    }
    
    public String getPat_Remarks_Eight() {
        return pat_Remarks_Eight;
    }
    
    public void setPat_Remarks_Eight(String pat_Remarks_Eight) {
        this.pat_Remarks_Eight = pat_Remarks_Eight;
    }
    
    public String getPat_Remarks_Nine() {
        return pat_Remarks_Nine;
    }
    
    public void setPat_Remarks_Nine(String pat_Remarks_Nine) {
        this.pat_Remarks_Nine = pat_Remarks_Nine;
    }
    
    public String getPat_Year() {
        return pat_Year;
    }
    
    public void setPat_Year(String pat_Year) {
        this.pat_Year = pat_Year;
    }
    
    public String getPat_Month() {
        return pat_Month;
    }
    
    public void setPat_Month(String pat_Month) {
        this.pat_Month = pat_Month;
    }
    
    public double getPat_IQ() {
        return pat_IQ;
    }
    
    public void setPat_IQ(double pat_IQ) {
        this.pat_IQ = pat_IQ;
    }
    
    public String getLoginID() {
        return loginID;
    }
    
    public void setLoginID(String loginID) {
        this.loginID = loginID;
    }
    
    public String getSystemIP() {
        return systemIP;
    }
    
    public void setSystemIP(String systemIP) {
        this.systemIP = systemIP;
    }
    
    public String getPersoncode() {
        return personcode;
    }
    
    public void setPersoncode(String personcode) {
        this.personcode = personcode;
    }
    
    public String getPat_Second_One() {
        return pat_Second_One;
    }
    
    public void setPat_Second_One(String pat_Second_One) {
        this.pat_Second_One = pat_Second_One;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public double getSfbiq() {
        return sfbiq;
    }
    
    public void setSfbiq(double sfbiq) {
        this.sfbiq = sfbiq;
    }
    
    
    
    public String getSystemip() {
        return systemip;
    }
    
    public void setSystemip(String systemip) {
        this.systemip = systemip;
    }
    
    public String getLoginid() {
        return loginid;
    }
    
    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }
    
    
    
    
    public double getMisiciq() {
        return misiciq;
    }
    
    public void setMisiciq(double misiciq) {
        this.misiciq = misiciq;
    }
    
    public String getDstyear() {
        return dstyear;
    }
    
    public void setDstyear(String dstyear) {
        this.dstyear = dstyear;
    }
    
    public String getDstmonth() {
        return dstmonth;
    }
    
    public void setDstmonth(String dstmonth) {
        this.dstmonth = dstmonth;
    }
    
    public double getDstdq() {
        return dstdq;
    }
    
    public void setDstdq(double dstdq) {
        this.dstdq = dstdq;
    }
    
    public String getVsmsyear() {
        return vsmsyear;
    }
    
    public void setVsmsyear(String vsmsyear) {
        this.vsmsyear = vsmsyear;
    }
    
    public String getVsmsmonth() {
        return vsmsmonth;
    }
    
    public void setVsmsmonth(String vsmsmonth) {
        this.vsmsmonth = vsmsmonth;
    }
    
    public double getVsmsvq() {
        return vsmsvq;
    }
    
    public void setVsmsvq(double vsmsvq) {
        this.vsmsvq = vsmsvq;
    }
    
    public String getBictbasalage() {
        return bictbasalage;
    }
    
    public void setBictbasalage(String bictbasalage) {
        this.bictbasalage = bictbasalage;
    }
    
    public String getBictterminalage() {
        return bictterminalage;
    }
    
    public void setBictterminalage(String bictterminalage) {
        this.bictterminalage = bictterminalage;
    }
    
    public String getBictyear() {
        return bictyear;
    }
    
    public void setBictyear(String bictyear) {
        this.bictyear = bictyear;
    }
    
    public String getBictmonth() {
        return bictmonth;
    }
    
    public void setBictmonth(String bictmonth) {
        this.bictmonth = bictmonth;
    }
    
    public double getBictiq() {
        return bictiq;
    }
    
    public void setBictiq(double bictiq) {
        this.bictiq = bictiq;
    }
    
    public String getBbpti_KOHS_Block_Design_Test() {
        return bbpti_KOHS_Block_Design_Test;
    }
    
    public void setBbpti_KOHS_Block_Design_Test(String bbpti_KOHS_Block_Design_Test) {
        this.bbpti_KOHS_Block_Design_Test = bbpti_KOHS_Block_Design_Test;
    }
    
    public String getBbpti_Passalong_Test() {
        return bbpti_Passalong_Test;
    }
    
    public void setBbpti_Passalong_Test(String bbpti_Passalong_Test) {
        this.bbpti_Passalong_Test = bbpti_Passalong_Test;
    }
    
    public String getBbpti_PatternDrawing_Test() {
        return bbpti_PatternDrawing_Test;
    }
    
    public void setBbpti_PatternDrawing_Test(String bbpti_PatternDrawing_Test) {
        this.bbpti_PatternDrawing_Test = bbpti_PatternDrawing_Test;
    }
    
    public String getBbpti_ImmediateMemory_Test() {
        return bbpti_ImmediateMemory_Test;
    }
    
    public void setBbpti_ImmediateMemory_Test(String bbpti_ImmediateMemory_Test) {
        this.bbpti_ImmediateMemory_Test = bbpti_ImmediateMemory_Test;
    }
    
    public String getBbpti_PictureConstruction_Test() {
        return bbpti_PictureConstruction_Test;
    }
    
    public void setBbpti_PictureConstruction_Test(String bbpti_PictureConstruction_Test) {
        this.bbpti_PictureConstruction_Test = bbpti_PictureConstruction_Test;
    }
    
    public String getBbpti_IQ() {
        return bbpti_IQ;
    }
    
    public void setBbpti_IQ(String bbpti_IQ) {
        this.bbpti_IQ = bbpti_IQ;
    }
    
    public String getSfbyear() {
        return sfbyear;
    }
    
    public void setSfbyear(String sfbyear) {
        this.sfbyear = sfbyear;
    }
    
    public String getSfbmonth() {
        return sfbmonth;
    }
    
    public void setSfbmonth(String sfbmonth) {
        this.sfbmonth = sfbmonth;
    }
    
    public String getSfbtrialone() {
        return sfbtrialone;
    }
    
    public void setSfbtrialone(String sfbtrialone) {
        this.sfbtrialone = sfbtrialone;
    }
    
    public String getSfbtrialtwo() {
        return sfbtrialtwo;
    }
    
    public void setSfbtrialtwo(String sfbtrialtwo) {
        this.sfbtrialtwo = sfbtrialtwo;
    }
    
    public String getSfbtrialthree() {
        return sfbtrialthree;
    }
    
    public void setSfbtrialthree(String sfbtrialthree) {
        this.sfbtrialthree = sfbtrialthree;
    }
    
    public String getMisicinformationraw() {
        return misicinformationraw;
    }
    
    public void setMisicinformationraw(String misicinformationraw) {
        this.misicinformationraw = misicinformationraw;
    }
    
    public String getMisicinformationtq() {
        return misicinformationtq;
    }
    
    public void setMisicinformationtq(String misicinformationtq) {
        this.misicinformationtq = misicinformationtq;
    }
    
    public String getMisicpcrawscore() {
        return misicpcrawscore;
    }
    
    public void setMisicpcrawscore(String misicpcrawscore) {
        this.misicpcrawscore = misicpcrawscore;
    }
    
    public String getMisicpctq() {
        return misicpctq;
    }
    
    public void setMisicpctq(String misicpctq) {
        this.misicpctq = misicpctq;
    }
    
    public String getMisiccomprehensionrawscore() {
        return misiccomprehensionrawscore;
    }
    
    public void setMisiccomprehensionrawscore(String misiccomprehensionrawscore) {
        this.misiccomprehensionrawscore = misiccomprehensionrawscore;
    }
    
    public String getMisiccomprehensiontq() {
        return misiccomprehensiontq;
    }
    
    public void setMisiccomprehensiontq(String misiccomprehensiontq) {
        this.misiccomprehensiontq = misiccomprehensiontq;
    }
    
    public String getMisicbdrawscore() {
        return misicbdrawscore;
    }
    
    public void setMisicbdrawscore(String misicbdrawscore) {
        this.misicbdrawscore = misicbdrawscore;
    }
    
    public String getMisicbdtq() {
        return misicbdtq;
    }
    
    public void setMisicbdtq(String misicbdtq) {
        this.misicbdtq = misicbdtq;
    }
    
    public String getMisicarithmeticrawscore() {
        return misicarithmeticrawscore;
    }
    
    public void setMisicarithmeticrawscore(String misicarithmeticrawscore) {
        this.misicarithmeticrawscore = misicarithmeticrawscore;
    }
    
    public String getMisicarithmetictq() {
        return misicarithmetictq;
    }
    
    public void setMisicarithmetictq(String misicarithmetictq) {
        this.misicarithmetictq = misicarithmetictq;
    }
    
    public String getMisicoarawscore() {
        return misicoarawscore;
    }
    
    public void setMisicoarawscore(String misicoarawscore) {
        this.misicoarawscore = misicoarawscore;
    }
    
    public String getMisicoatq() {
        return misicoatq;
    }
    
    public void setMisicoatq(String misicoatq) {
        this.misicoatq = misicoatq;
    }
    
    public String getMisicsimilaritiesrawscore() {
        return misicsimilaritiesrawscore;
    }
    
    public void setMisicsimilaritiesrawscore(String misicsimilaritiesrawscore) {
        this.misicsimilaritiesrawscore = misicsimilaritiesrawscore;
    }
    
    public String getMisicsimilaritiestq() {
        return misicsimilaritiestq;
    }
    
    public void setMisicsimilaritiestq(String misicsimilaritiestq) {
        this.misicsimilaritiestq = misicsimilaritiestq;
    }
    
    public String getMisiccodingrawscore() {
        return misiccodingrawscore;
    }
    
    public void setMisiccodingrawscore(String misiccodingrawscore) {
        this.misiccodingrawscore = misiccodingrawscore;
    }
    
    public String getMisiccodingtq() {
        return misiccodingtq;
    }
    
    public void setMisiccodingtq(String misiccodingtq) {
        this.misiccodingtq = misiccodingtq;
    }
    
    public String getMisicvocabularyrawscore() {
        return misicvocabularyrawscore;
    }
    
    public void setMisicvocabularyrawscore(String misicvocabularyrawscore) {
        this.misicvocabularyrawscore = misicvocabularyrawscore;
    }
    
    public String getMisicvocabularytq() {
        return misicvocabularytq;
    }
    
    public void setMisicvocabularytq(String misicvocabularytq) {
        this.misicvocabularytq = misicvocabularytq;
    }
    
    public String getMisicmazesrawscore() {
        return misicmazesrawscore;
    }
    
    public void setMisicmazesrawscore(String misicmazesrawscore) {
        this.misicmazesrawscore = misicmazesrawscore;
    }
    
    public String getMisicmazestq() {
        return misicmazestq;
    }
    
    public void setMisicmazestq(String misicmazestq) {
        this.misicmazestq = misicmazestq;
    }
    
    public String getMisicdigitspanrawscore() {
        return misicdigitspanrawscore;
    }
    
    public void setMisicdigitspanrawscore(String misicdigitspanrawscore) {
        this.misicdigitspanrawscore = misicdigitspanrawscore;
    }
    
    public String getMisicdigitspantq() {
        return misicdigitspantq;
    }
    
    public void setMisicdigitspantq(String misicdigitspantq) {
        this.misicdigitspantq = misicdigitspantq;
    }
    
    public String getDigitspantqscore() {
        return digitspantqscore;
    }
    
    public void setDigitspantqscore(String digitspantqscore) {
        this.digitspantqscore = digitspantqscore;
    }
    
    public String getSumofperformance() {
        return sumofperformance;
    }
    
    public void setSumofperformance(String sumofperformance) {
        this.sumofperformance = sumofperformance;
    }
    
    
    
    
    
    
    
    
    
    
    
}
