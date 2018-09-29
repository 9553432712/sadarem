/*
 * PATBean.java
 *
 * Created on January 5, 2009, 11:43 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bf.disability.bean;

/**
 *
 * @author kiran_h
 */
public class PATBean {
    
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
    
    private double chronologicalage;
    private boolean chronologicalageflag;
    private StringBuffer mentalagebuffer;
    private StringBuffer iqbuffer;
    private String pat_Year;
    private String pat_Month;
    private double pat_IQ;
    
    private boolean pat_one_flag;
    private boolean pat_two_flag;
    private boolean pat_three_flag;
    private boolean pat_four_flag;
    private boolean pat_five_flag;
    private boolean pat_six_flag;
    private boolean pat_seven_flag;
    private boolean pat_eight_flag;
    private boolean pat_nine_flag;
    
    private boolean pat_total_flag;
    
    private String dobstring;
    private String todaystring;
    
    
    public double getChronologicalage() {
        return chronologicalage;
    }
    
    public void setChronologicalage(double chronologicalage) {
        this.chronologicalage = chronologicalage;
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
    
    public boolean isChronologicalageflag() {
        return chronologicalageflag;
    }
    
    public void setChronologicalageflag(boolean chronologicalageflag) {
        this.chronologicalageflag = chronologicalageflag;
    }
    
    public StringBuffer getMentalagebuffer() {
        return mentalagebuffer;
    }
    
    public void setMentalagebuffer(StringBuffer mentalagebuffer) {
        this.mentalagebuffer = mentalagebuffer;
    }
    
    public StringBuffer getIqbuffer() {
        return iqbuffer;
    }
    
    public void setIqbuffer(StringBuffer iqbuffer) {
        this.iqbuffer = iqbuffer;
    }
    
    public String getPat_Second_One() {
        return pat_Second_One;
    }
    
    public void setPat_Second_One(String pat_Second_One) {
        this.pat_Second_One = pat_Second_One;
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
    
    public boolean getPat_one_flag() {
        return pat_one_flag;
    }
    
    public void setPat_one_flag(boolean pat_one_flag) {
        this.pat_one_flag = pat_one_flag;
    }
    
    public boolean isPat_two_flag() {
        return pat_two_flag;
    }
    
    public void setPat_two_flag(boolean pat_two_flag) {
        this.pat_two_flag = pat_two_flag;
    }
    
    public boolean isPat_three_flag() {
        return pat_three_flag;
    }
    
    public void setPat_three_flag(boolean pat_three_flag) {
        this.pat_three_flag = pat_three_flag;
    }
    
    public boolean isPat_four_flag() {
        return pat_four_flag;
    }
    
    public void setPat_four_flag(boolean pat_four_flag) {
        this.pat_four_flag = pat_four_flag;
    }
    
    public boolean isPat_five_flag() {
        return pat_five_flag;
    }
    
    public void setPat_five_flag(boolean pat_five_flag) {
        this.pat_five_flag = pat_five_flag;
    }
    
    public boolean isPat_six_flag() {
        return pat_six_flag;
    }
    
    public void setPat_six_flag(boolean pat_six_flag) {
        this.pat_six_flag = pat_six_flag;
    }
    
    public boolean isPat_seven_flag() {
        return pat_seven_flag;
    }
    
    public void setPat_seven_flag(boolean pat_seven_flag) {
        this.pat_seven_flag = pat_seven_flag;
    }
    
    public boolean isPat_eight_flag() {
        return pat_eight_flag;
    }
    
    public void setPat_eight_flag(boolean pat_eight_flag) {
        this.pat_eight_flag = pat_eight_flag;
    }
    
    public boolean isPat_nine_flag() {
        return pat_nine_flag;
    }
    
    public void setPat_nine_flag(boolean pat_nine_flag) {
        this.pat_nine_flag = pat_nine_flag;
    }
    
    public boolean isPat_total_flag() {
        return pat_total_flag;
    }
    
    public void setPat_total_flag(boolean pat_total_flag) {
        this.pat_total_flag = pat_total_flag;
    }

    public String getDobstring() {
        return dobstring;
    }

    public void setDobstring(String dobstring) {
        this.dobstring = dobstring;
    }

    public String getTodaystring() {
        return todaystring;
    }

    public void setTodaystring(String todaystring) {
        this.todaystring = todaystring;
    }

    
   
    
    
}
