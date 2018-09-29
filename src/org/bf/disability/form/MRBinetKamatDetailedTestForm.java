/*
 * MRBinetKamatDetailedTestForm.java
 *
 */

package org.bf.disability.form;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;


/**
 * This class contains the fields, required to compute IQ using Binet kamat 
 * Detailed Test for calculating Mental Retardation
 * @author Deviprasad T
 */
public class MRBinetKamatDetailedTestForm extends ActionForm{
    
    private int ageLevel3_Item1;
    private int ageLevel3_Item2;
    private int ageLevel3_Item3;
    private int ageLevel3_Item4;
    private int ageLevel3_Item5;
    private int ageLevel3_Item6;
    private int ageLevel3_ItemAlt1;
    private int ageLevel3_ItemAlt2;
    private int ageLevel3_ItemAlt3;
    
    private int ageLevel4_Item1;
    private int ageLevel4_Item2;
    private int ageLevel4_Item3;
    private int ageLevel4_Item4;
    private int ageLevel4_Item5;
    private int ageLevel4_Item6;
    private int ageLevel4_ItemAlt1;
    private int ageLevel4_ItemAlt2;
    private int ageLevel4_ItemAlt3;
    
    private int ageLevel5_Item1;
    private int ageLevel5_Item2;
    private int ageLevel5_Item3;
    private int ageLevel5_Item4;
    private int ageLevel5_Item5;
    private int ageLevel5_Item6;
    private int ageLevel5_ItemAlt1;
    private int ageLevel5_ItemAlt2;
    private int ageLevel5_ItemAlt3;
    
    private int ageLevel6_Item1;
    private int ageLevel6_Item2;
    private int ageLevel6_Item3;
    private int ageLevel6_Item4;
    private int ageLevel6_Item5;
    private int ageLevel6_Item6;
    private int ageLevel6_ItemAlt1;
    private int ageLevel6_ItemAlt2;
    private int ageLevel6_ItemAlt3;
    
    private int ageLevel7_Item1;
    private int ageLevel7_Item2;
    private int ageLevel7_Item3;
    private int ageLevel7_Item4;
    private int ageLevel7_Item5;
    private int ageLevel7_Item6;
    private int ageLevel7_ItemAlt1;
    private int ageLevel7_ItemAlt2;
    private int ageLevel7_ItemAlt3;
    
    private int ageLevel8_Item1;
    private int ageLevel8_Item2;
    private int ageLevel8_Item3;
    private int ageLevel8_Item4;
    private int ageLevel8_Item5;
    private int ageLevel8_Item6;
    private int ageLevel8_ItemAlt1;
    private int ageLevel8_ItemAlt2;
    private int ageLevel8_ItemAlt3;
    
    private int ageLevel9_Item1;
    private int ageLevel9_Item2;
    private int ageLevel9_Item3;
    private int ageLevel9_Item4;
    private int ageLevel9_Item5;
    private int ageLevel9_Item6;
    private int ageLevel9_ItemAlt1;
    private int ageLevel9_ItemAlt2;
    private int ageLevel9_ItemAlt3;
    
    private int ageLevel10_Item1;
    private int ageLevel10_Item2;
    private int ageLevel10_Item3;
    private int ageLevel10_Item4;
    private int ageLevel10_Item5;
    private int ageLevel10_Item6;
    private int ageLevel10_ItemAlt1;
    private int ageLevel10_ItemAlt2;
    private int ageLevel10_ItemAlt3;
    
    private int ageLevel12_Item1;
    private int ageLevel12_Item2;
    private int ageLevel12_Item3;
    private int ageLevel12_Item4;
    private int ageLevel12_Item5;
    private int ageLevel12_Item6;
    private int ageLevel12_ItemAlt1;
    private int ageLevel12_ItemAlt2;
    private int ageLevel12_ItemAlt3;
    
    private int ageLevel14_Item1;
    private int ageLevel14_Item2;
    private int ageLevel14_Item3;
    private int ageLevel14_Item4;
    private int ageLevel14_Item5;
    private int ageLevel14_Item6;
    private int ageLevel14_ItemAlt1;
    private int ageLevel14_ItemAlt2;
    private int ageLevel14_ItemAlt3;
    
    private int ageLevel16_Item1;
    private int ageLevel16_Item2;
    private int ageLevel16_Item3;
    private int ageLevel16_Item4;
    private int ageLevel16_Item5;
    private int ageLevel16_Item6;
    private int ageLevel16_ItemAlt1;
    private int ageLevel16_ItemAlt2;
    private int ageLevel16_ItemAlt3;
    
    private int ageLevel19_Item1;
    private int ageLevel19_Item2;
    private int ageLevel19_Item3;
    private int ageLevel19_Item4;
    private int ageLevel19_Item5;
    private int ageLevel19_Item6;
    private int ageLevel19_ItemAlt1;
    private int ageLevel19_ItemAlt2;
    private int ageLevel19_ItemAlt3;
    
    private int ageLevel22_Item1;
    private int ageLevel22_Item2;
    private int ageLevel22_Item3;
    private int ageLevel22_Item4;
    private int ageLevel22_Item5;
    private int ageLevel22_Item6;
    private int ageLevel22_ItemAlt1;
    private int ageLevel22_ItemAlt2;
    private int ageLevel22_ItemAlt3;
    
    private String loginID;
    private String systemIP;
    private String personcode;
    
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        
        
        this.ageLevel3_Item1=0;
        this.ageLevel3_Item2=0;
        this.ageLevel3_Item3=0;
        this.ageLevel3_Item4=0;
        this.ageLevel3_Item5=0;
        this.ageLevel3_Item6=0;
        this.ageLevel3_ItemAlt1=0;
        this.ageLevel3_ItemAlt2=0;
        this.ageLevel3_ItemAlt3=0;
        
        this.ageLevel4_Item1=0;
        this.ageLevel4_Item2=0;
        this.ageLevel4_Item3=0;
        this.ageLevel4_Item4=0;
        this.ageLevel4_Item5=0;
        this.ageLevel4_Item6=0;
        this.ageLevel4_ItemAlt1=0;
        this.ageLevel4_ItemAlt2=0;
        this.ageLevel4_ItemAlt3=0;
        
        this.ageLevel5_Item1=0;
        this.ageLevel5_Item2=0;
        this.ageLevel5_Item3=0;
        this.ageLevel5_Item4=0;
        this.ageLevel5_Item5=0;
        this.ageLevel5_Item6=0;
        this.ageLevel5_ItemAlt1=0;
        this.ageLevel5_ItemAlt2=0;
        this.ageLevel5_ItemAlt3=0;
        
        this.ageLevel6_Item1=0;
        this.ageLevel6_Item2=0;
        this.ageLevel6_Item3=0;
        this.ageLevel6_Item4=0;
        this.ageLevel6_Item5=0;
        this.ageLevel6_Item6=0;
        this.ageLevel6_ItemAlt1=0;
        this.ageLevel6_ItemAlt2=0;
        this.ageLevel6_ItemAlt3=0;
        
        this.ageLevel7_Item1=0;
        this.ageLevel7_Item2=0;
        this.ageLevel7_Item3=0;
        this.ageLevel7_Item4=0;
        this.ageLevel7_Item5=0;
        this.ageLevel7_Item6=0;
        this.ageLevel7_ItemAlt1=0;
        this.ageLevel7_ItemAlt2=0;
        this.ageLevel7_ItemAlt3=0;
        
        this.ageLevel8_Item1=0;
        this.ageLevel8_Item2=0;
        this.ageLevel8_Item3=0;
        this.ageLevel8_Item4=0;
        this.ageLevel8_Item5=0;
        this.ageLevel8_Item6=0;
        this.ageLevel8_ItemAlt1=0;
        this.ageLevel8_ItemAlt2=0;
        this.ageLevel8_ItemAlt3=0;
        
        this.ageLevel9_Item1=0;
        this.ageLevel9_Item2=0;
        this.ageLevel9_Item3=0;
        this.ageLevel9_Item4=0;
        this.ageLevel9_Item5=0;
        this.ageLevel9_Item6=0;
        this.ageLevel9_ItemAlt1=0;
        this.ageLevel9_ItemAlt2=0;
        this.ageLevel9_ItemAlt3=0;
        
        this.ageLevel10_Item1=0;
        this.ageLevel10_Item2=0;
        this.ageLevel10_Item3=0;
        this.ageLevel10_Item4=0;
        this.ageLevel10_Item5=0;
        this.ageLevel10_Item6=0;
        this.ageLevel10_ItemAlt1=0;
        this.ageLevel10_ItemAlt2=0;
        this.ageLevel10_ItemAlt3=0;
        
        this.ageLevel12_Item1=0;
        this.ageLevel12_Item2=0;
        this.ageLevel12_Item3=0;
        this.ageLevel12_Item4=0;
        this.ageLevel12_Item5=0;
        this.ageLevel12_Item6=0;
        this.ageLevel12_ItemAlt1=0;
        this.ageLevel12_ItemAlt2=0;
        this.ageLevel12_ItemAlt3=0;
        
        this.ageLevel14_Item1=0;
        this.ageLevel14_Item2=0;
        this.ageLevel14_Item3=0;
        this.ageLevel14_Item4=0;
        this.ageLevel14_Item5=0;
        this.ageLevel14_Item6=0;
        this.ageLevel14_ItemAlt1=0;
        this.ageLevel14_ItemAlt2=0;
        this.ageLevel14_ItemAlt3=0;
        
        this.ageLevel16_Item1=0;
        this.ageLevel16_Item2=0;
        this.ageLevel16_Item3=0;
        this.ageLevel16_Item4=0;
        this.ageLevel16_Item5=0;
        this.ageLevel16_Item6=0;
        this.ageLevel16_ItemAlt1=0;
        this.ageLevel16_ItemAlt2=0;
        this.ageLevel16_ItemAlt3=0;
        
        this.ageLevel19_Item1=0;
        this.ageLevel19_Item2=0;
        this.ageLevel19_Item3=0;
        this.ageLevel19_Item4=0;
        this.ageLevel19_Item5=0;
        this.ageLevel19_Item6=0;
        this.ageLevel19_ItemAlt1=0;
        this.ageLevel19_ItemAlt2=0;
        this.ageLevel19_ItemAlt3=0;
        
        this.ageLevel22_Item1=0;
        this.ageLevel22_Item2=0;
        this.ageLevel22_Item3=0;
        this.ageLevel22_Item4=0;
        this.ageLevel22_Item5=0;
        this.ageLevel22_Item6=0;
        this.ageLevel22_ItemAlt1=0;
        this.ageLevel22_ItemAlt2=0;
        this.ageLevel22_ItemAlt3=0;
        
    }
    
    public int getAgeLevel3_Item1() {
        return ageLevel3_Item1;
    }
    
    public void setAgeLevel3_Item1(int ageLevel3_Item1) {
        this.ageLevel3_Item1 = ageLevel3_Item1;
    }
    
    public int getAgeLevel3_Item2() {
        return ageLevel3_Item2;
    }
    
    public void setAgeLevel3_Item2(int ageLevel3_Item2) {
        this.ageLevel3_Item2 = ageLevel3_Item2;
    }
    
    public int getAgeLevel3_Item3() {
        return ageLevel3_Item3;
    }
    
    public void setAgeLevel3_Item3(int ageLevel3_Item3) {
        this.ageLevel3_Item3 = ageLevel3_Item3;
    }
    
    public int getAgeLevel3_Item4() {
        return ageLevel3_Item4;
    }
    
    public void setAgeLevel3_Item4(int ageLevel3_Item4) {
        this.ageLevel3_Item4 = ageLevel3_Item4;
    }
    
    public int getAgeLevel3_Item5() {
        return ageLevel3_Item5;
    }
    
    public void setAgeLevel3_Item5(int ageLevel3_Item5) {
        this.ageLevel3_Item5 = ageLevel3_Item5;
    }
    
    public int getAgeLevel3_Item6() {
        return ageLevel3_Item6;
    }
    
    public void setAgeLevel3_Item6(int ageLevel3_Item6) {
        this.ageLevel3_Item6 = ageLevel3_Item6;
    }
    
    public int getAgeLevel3_ItemAlt1() {
        return ageLevel3_ItemAlt1;
    }
    
    public void setAgeLevel3_ItemAlt1(int ageLevel3_ItemAlt1) {
        this.ageLevel3_ItemAlt1 = ageLevel3_ItemAlt1;
    }
    
    public int getAgeLevel3_ItemAlt2() {
        return ageLevel3_ItemAlt2;
    }
    
    public void setAgeLevel3_ItemAlt2(int ageLevel3_ItemAlt2) {
        this.ageLevel3_ItemAlt2 = ageLevel3_ItemAlt2;
    }
    
    public int getAgeLevel3_ItemAlt3() {
        return ageLevel3_ItemAlt3;
    }
    
    public void setAgeLevel3_ItemAlt3(int ageLevel3_ItemAlt3) {
        this.ageLevel3_ItemAlt3 = ageLevel3_ItemAlt3;
    }
    
    public int getAgeLevel4_Item1() {
        return ageLevel4_Item1;
    }
    
    public void setAgeLevel4_Item1(int ageLevel4_Item1) {
        this.ageLevel4_Item1 = ageLevel4_Item1;
    }
    
    public int getAgeLevel4_Item2() {
        return ageLevel4_Item2;
    }
    
    public void setAgeLevel4_Item2(int ageLevel4_Item2) {
        this.ageLevel4_Item2 = ageLevel4_Item2;
    }
    
    public int getAgeLevel4_Item3() {
        return ageLevel4_Item3;
    }
    
    public void setAgeLevel4_Item3(int ageLevel4_Item3) {
        this.ageLevel4_Item3 = ageLevel4_Item3;
    }
    
    public int getAgeLevel4_Item4() {
        return ageLevel4_Item4;
    }
    
    public void setAgeLevel4_Item4(int ageLevel4_Item4) {
        this.ageLevel4_Item4 = ageLevel4_Item4;
    }
    
    public int getAgeLevel4_Item5() {
        return ageLevel4_Item5;
    }
    
    public void setAgeLevel4_Item5(int ageLevel4_Item5) {
        this.ageLevel4_Item5 = ageLevel4_Item5;
    }
    
    public int getAgeLevel4_Item6() {
        return ageLevel4_Item6;
    }
    
    public void setAgeLevel4_Item6(int ageLevel4_Item6) {
        this.ageLevel4_Item6 = ageLevel4_Item6;
    }
    
    public int getAgeLevel4_ItemAlt1() {
        return ageLevel4_ItemAlt1;
    }
    
    public void setAgeLevel4_ItemAlt1(int ageLevel4_ItemAlt1) {
        this.ageLevel4_ItemAlt1 = ageLevel4_ItemAlt1;
    }
    
    public int getAgeLevel4_ItemAlt2() {
        return ageLevel4_ItemAlt2;
    }
    
    public void setAgeLevel4_ItemAlt2(int ageLevel4_ItemAlt2) {
        this.ageLevel4_ItemAlt2 = ageLevel4_ItemAlt2;
    }
    
    public int getAgeLevel4_ItemAlt3() {
        return ageLevel4_ItemAlt3;
    }
    
    public void setAgeLevel4_ItemAlt3(int ageLevel4_ItemAlt3) {
        this.ageLevel4_ItemAlt3 = ageLevel4_ItemAlt3;
    }
    
    public int getAgeLevel5_Item1() {
        return ageLevel5_Item1;
    }
    
    public void setAgeLevel5_Item1(int ageLevel5_Item1) {
        this.ageLevel5_Item1 = ageLevel5_Item1;
    }
    
    public int getAgeLevel5_Item2() {
        return ageLevel5_Item2;
    }
    
    public void setAgeLevel5_Item2(int ageLevel5_Item2) {
        this.ageLevel5_Item2 = ageLevel5_Item2;
    }
    
    public int getAgeLevel5_Item3() {
        return ageLevel5_Item3;
    }
    
    public void setAgeLevel5_Item3(int ageLevel5_Item3) {
        this.ageLevel5_Item3 = ageLevel5_Item3;
    }
    
    public int getAgeLevel5_Item4() {
        return ageLevel5_Item4;
    }
    
    public void setAgeLevel5_Item4(int ageLevel5_Item4) {
        this.ageLevel5_Item4 = ageLevel5_Item4;
    }
    
    public int getAgeLevel5_Item5() {
        return ageLevel5_Item5;
    }
    
    public void setAgeLevel5_Item5(int ageLevel5_Item5) {
        this.ageLevel5_Item5 = ageLevel5_Item5;
    }
    
    public int getAgeLevel5_Item6() {
        return ageLevel5_Item6;
    }
    
    public void setAgeLevel5_Item6(int ageLevel5_Item6) {
        this.ageLevel5_Item6 = ageLevel5_Item6;
    }
    
    public int getAgeLevel5_ItemAlt1() {
        return ageLevel5_ItemAlt1;
    }
    
    public void setAgeLevel5_ItemAlt1(int ageLevel5_ItemAlt1) {
        this.ageLevel5_ItemAlt1 = ageLevel5_ItemAlt1;
    }
    
    public int getAgeLevel5_ItemAlt2() {
        return ageLevel5_ItemAlt2;
    }
    
    public void setAgeLevel5_ItemAlt2(int ageLevel5_ItemAlt2) {
        this.ageLevel5_ItemAlt2 = ageLevel5_ItemAlt2;
    }
    
    public int getAgeLevel5_ItemAlt3() {
        return ageLevel5_ItemAlt3;
    }
    
    public void setAgeLevel5_ItemAlt3(int ageLevel5_ItemAlt3) {
        this.ageLevel5_ItemAlt3 = ageLevel5_ItemAlt3;
    }
    
    public int getAgeLevel6_Item1() {
        return ageLevel6_Item1;
    }
    
    public void setAgeLevel6_Item1(int ageLevel6_Item1) {
        this.ageLevel6_Item1 = ageLevel6_Item1;
    }
    
    public int getAgeLevel6_Item2() {
        return ageLevel6_Item2;
    }
    
    public void setAgeLevel6_Item2(int ageLevel6_Item2) {
        this.ageLevel6_Item2 = ageLevel6_Item2;
    }
    
    public int getAgeLevel6_Item3() {
        return ageLevel6_Item3;
    }
    
    public void setAgeLevel6_Item3(int ageLevel6_Item3) {
        this.ageLevel6_Item3 = ageLevel6_Item3;
    }
    
    public int getAgeLevel6_Item4() {
        return ageLevel6_Item4;
    }
    
    public void setAgeLevel6_Item4(int ageLevel6_Item4) {
        this.ageLevel6_Item4 = ageLevel6_Item4;
    }
    
    public int getAgeLevel6_Item5() {
        return ageLevel6_Item5;
    }
    
    public void setAgeLevel6_Item5(int ageLevel6_Item5) {
        this.ageLevel6_Item5 = ageLevel6_Item5;
    }
    
    public int getAgeLevel6_Item6() {
        return ageLevel6_Item6;
    }
    
    public void setAgeLevel6_Item6(int ageLevel6_Item6) {
        this.ageLevel6_Item6 = ageLevel6_Item6;
    }
    
    public int getAgeLevel6_ItemAlt1() {
        return ageLevel6_ItemAlt1;
    }
    
    public void setAgeLevel6_ItemAlt1(int ageLevel6_ItemAlt1) {
        this.ageLevel6_ItemAlt1 = ageLevel6_ItemAlt1;
    }
    
    public int getAgeLevel6_ItemAlt2() {
        return ageLevel6_ItemAlt2;
    }
    
    public void setAgeLevel6_ItemAlt2(int ageLevel6_ItemAlt2) {
        this.ageLevel6_ItemAlt2 = ageLevel6_ItemAlt2;
    }
    
    public int getAgeLevel6_ItemAlt3() {
        return ageLevel6_ItemAlt3;
    }
    
    public void setAgeLevel6_ItemAlt3(int ageLevel6_ItemAlt3) {
        this.ageLevel6_ItemAlt3 = ageLevel6_ItemAlt3;
    }
    
    public int getAgeLevel7_Item1() {
        return ageLevel7_Item1;
    }
    
    public void setAgeLevel7_Item1(int ageLevel7_Item1) {
        this.ageLevel7_Item1 = ageLevel7_Item1;
    }
    
    public int getAgeLevel7_Item2() {
        return ageLevel7_Item2;
    }
    
    public void setAgeLevel7_Item2(int ageLevel7_Item2) {
        this.ageLevel7_Item2 = ageLevel7_Item2;
    }
    
    public int getAgeLevel7_Item3() {
        return ageLevel7_Item3;
    }
    
    public void setAgeLevel7_Item3(int ageLevel7_Item3) {
        this.ageLevel7_Item3 = ageLevel7_Item3;
    }
    
    public int getAgeLevel7_Item4() {
        return ageLevel7_Item4;
    }
    
    public void setAgeLevel7_Item4(int ageLevel7_Item4) {
        this.ageLevel7_Item4 = ageLevel7_Item4;
    }
    
    public int getAgeLevel7_Item5() {
        return ageLevel7_Item5;
    }
    
    public void setAgeLevel7_Item5(int ageLevel7_Item5) {
        this.ageLevel7_Item5 = ageLevel7_Item5;
    }
    
    public int getAgeLevel7_Item6() {
        return ageLevel7_Item6;
    }
    
    public void setAgeLevel7_Item6(int ageLevel7_Item6) {
        this.ageLevel7_Item6 = ageLevel7_Item6;
    }
    
    public int getAgeLevel7_ItemAlt1() {
        return ageLevel7_ItemAlt1;
    }
    
    public void setAgeLevel7_ItemAlt1(int ageLevel7_ItemAlt1) {
        this.ageLevel7_ItemAlt1 = ageLevel7_ItemAlt1;
    }
    
    public int getAgeLevel7_ItemAlt2() {
        return ageLevel7_ItemAlt2;
    }
    
    public void setAgeLevel7_ItemAlt2(int ageLevel7_ItemAlt2) {
        this.ageLevel7_ItemAlt2 = ageLevel7_ItemAlt2;
    }
    
    public int getAgeLevel7_ItemAlt3() {
        return ageLevel7_ItemAlt3;
    }
    
    public void setAgeLevel7_ItemAlt3(int ageLevel7_ItemAlt3) {
        this.ageLevel7_ItemAlt3 = ageLevel7_ItemAlt3;
    }
    
    public int getAgeLevel8_Item1() {
        return ageLevel8_Item1;
    }
    
    public void setAgeLevel8_Item1(int ageLevel8_Item1) {
        this.ageLevel8_Item1 = ageLevel8_Item1;
    }
    
    public int getAgeLevel8_Item2() {
        return ageLevel8_Item2;
    }
    
    public void setAgeLevel8_Item2(int ageLevel8_Item2) {
        this.ageLevel8_Item2 = ageLevel8_Item2;
    }
    
    public int getAgeLevel8_Item3() {
        return ageLevel8_Item3;
    }
    
    public void setAgeLevel8_Item3(int ageLevel8_Item3) {
        this.ageLevel8_Item3 = ageLevel8_Item3;
    }
    
    public int getAgeLevel8_Item4() {
        return ageLevel8_Item4;
    }
    
    public void setAgeLevel8_Item4(int ageLevel8_Item4) {
        this.ageLevel8_Item4 = ageLevel8_Item4;
    }
    
    public int getAgeLevel8_Item5() {
        return ageLevel8_Item5;
    }
    
    public void setAgeLevel8_Item5(int ageLevel8_Item5) {
        this.ageLevel8_Item5 = ageLevel8_Item5;
    }
    
    public int getAgeLevel8_Item6() {
        return ageLevel8_Item6;
    }
    
    public void setAgeLevel8_Item6(int ageLevel8_Item6) {
        this.ageLevel8_Item6 = ageLevel8_Item6;
    }
    
    public int getAgeLevel8_ItemAlt1() {
        return ageLevel8_ItemAlt1;
    }
    
    public void setAgeLevel8_ItemAlt1(int ageLevel8_ItemAlt1) {
        this.ageLevel8_ItemAlt1 = ageLevel8_ItemAlt1;
    }
    
    public int getAgeLevel8_ItemAlt2() {
        return ageLevel8_ItemAlt2;
    }
    
    public void setAgeLevel8_ItemAlt2(int ageLevel8_ItemAlt2) {
        this.ageLevel8_ItemAlt2 = ageLevel8_ItemAlt2;
    }
    
    public int getAgeLevel8_ItemAlt3() {
        return ageLevel8_ItemAlt3;
    }
    
    public void setAgeLevel8_ItemAlt3(int ageLevel8_ItemAlt3) {
        this.ageLevel8_ItemAlt3 = ageLevel8_ItemAlt3;
    }
    
    public int getAgeLevel9_Item1() {
        return ageLevel9_Item1;
    }
    
    public void setAgeLevel9_Item1(int ageLevel9_Item1) {
        this.ageLevel9_Item1 = ageLevel9_Item1;
    }
    
    public int getAgeLevel9_Item2() {
        return ageLevel9_Item2;
    }
    
    public void setAgeLevel9_Item2(int ageLevel9_Item2) {
        this.ageLevel9_Item2 = ageLevel9_Item2;
    }
    
    public int getAgeLevel9_Item3() {
        return ageLevel9_Item3;
    }
    
    public void setAgeLevel9_Item3(int ageLevel9_Item3) {
        this.ageLevel9_Item3 = ageLevel9_Item3;
    }
    
    public int getAgeLevel9_Item4() {
        return ageLevel9_Item4;
    }
    
    public void setAgeLevel9_Item4(int ageLevel9_Item4) {
        this.ageLevel9_Item4 = ageLevel9_Item4;
    }
    
    public int getAgeLevel9_Item5() {
        return ageLevel9_Item5;
    }
    
    public void setAgeLevel9_Item5(int ageLevel9_Item5) {
        this.ageLevel9_Item5 = ageLevel9_Item5;
    }
    
    public int getAgeLevel9_Item6() {
        return ageLevel9_Item6;
    }
    
    public void setAgeLevel9_Item6(int ageLevel9_Item6) {
        this.ageLevel9_Item6 = ageLevel9_Item6;
    }
    
    public int getAgeLevel9_ItemAlt1() {
        return ageLevel9_ItemAlt1;
    }
    
    public void setAgeLevel9_ItemAlt1(int ageLevel9_ItemAlt1) {
        this.ageLevel9_ItemAlt1 = ageLevel9_ItemAlt1;
    }
    
    public int getAgeLevel9_ItemAlt2() {
        return ageLevel9_ItemAlt2;
    }
    
    public void setAgeLevel9_ItemAlt2(int ageLevel9_ItemAlt2) {
        this.ageLevel9_ItemAlt2 = ageLevel9_ItemAlt2;
    }
    
    public int getAgeLevel9_ItemAlt3() {
        return ageLevel9_ItemAlt3;
    }
    
    public void setAgeLevel9_ItemAlt3(int ageLevel9_ItemAlt3) {
        this.ageLevel9_ItemAlt3 = ageLevel9_ItemAlt3;
    }
    
    public int getAgeLevel10_Item1() {
        return ageLevel10_Item1;
    }
    
    public void setAgeLevel10_Item1(int ageLevel10_Item1) {
        this.ageLevel10_Item1 = ageLevel10_Item1;
    }
    
    public int getAgeLevel10_Item2() {
        return ageLevel10_Item2;
    }
    
    public void setAgeLevel10_Item2(int ageLevel10_Item2) {
        this.ageLevel10_Item2 = ageLevel10_Item2;
    }
    
    public int getAgeLevel10_Item3() {
        return ageLevel10_Item3;
    }
    
    public void setAgeLevel10_Item3(int ageLevel10_Item3) {
        this.ageLevel10_Item3 = ageLevel10_Item3;
    }
    
    public int getAgeLevel10_Item4() {
        return ageLevel10_Item4;
    }
    
    public void setAgeLevel10_Item4(int ageLevel10_Item4) {
        this.ageLevel10_Item4 = ageLevel10_Item4;
    }
    
    public int getAgeLevel10_Item5() {
        return ageLevel10_Item5;
    }
    
    public void setAgeLevel10_Item5(int ageLevel10_Item5) {
        this.ageLevel10_Item5 = ageLevel10_Item5;
    }
    
    public int getAgeLevel10_Item6() {
        return ageLevel10_Item6;
    }
    
    public void setAgeLevel10_Item6(int ageLevel10_Item6) {
        this.ageLevel10_Item6 = ageLevel10_Item6;
    }
    
    public int getAgeLevel10_ItemAlt1() {
        return ageLevel10_ItemAlt1;
    }
    
    public void setAgeLevel10_ItemAlt1(int ageLevel10_ItemAlt1) {
        this.ageLevel10_ItemAlt1 = ageLevel10_ItemAlt1;
    }
    
    public int getAgeLevel10_ItemAlt2() {
        return ageLevel10_ItemAlt2;
    }
    
    public void setAgeLevel10_ItemAlt2(int ageLevel10_ItemAlt2) {
        this.ageLevel10_ItemAlt2 = ageLevel10_ItemAlt2;
    }
    
    public int getAgeLevel10_ItemAlt3() {
        return ageLevel10_ItemAlt3;
    }
    
    public void setAgeLevel10_ItemAlt3(int ageLevel10_ItemAlt3) {
        this.ageLevel10_ItemAlt3 = ageLevel10_ItemAlt3;
    }
    
    public int getAgeLevel12_Item1() {
        return ageLevel12_Item1;
    }
    
    public void setAgeLevel12_Item1(int ageLevel12_Item1) {
        this.ageLevel12_Item1 = ageLevel12_Item1;
    }
    
    public int getAgeLevel12_Item2() {
        return ageLevel12_Item2;
    }
    
    public void setAgeLevel12_Item2(int ageLevel12_Item2) {
        this.ageLevel12_Item2 = ageLevel12_Item2;
    }
    
    public int getAgeLevel12_Item3() {
        return ageLevel12_Item3;
    }
    
    public void setAgeLevel12_Item3(int ageLevel12_Item3) {
        this.ageLevel12_Item3 = ageLevel12_Item3;
    }
    
    public int getAgeLevel12_Item4() {
        return ageLevel12_Item4;
    }
    
    public void setAgeLevel12_Item4(int ageLevel12_Item4) {
        this.ageLevel12_Item4 = ageLevel12_Item4;
    }
    
    public int getAgeLevel12_Item5() {
        return ageLevel12_Item5;
    }
    
    public void setAgeLevel12_Item5(int ageLevel12_Item5) {
        this.ageLevel12_Item5 = ageLevel12_Item5;
    }
    
    public int getAgeLevel12_Item6() {
        return ageLevel12_Item6;
    }
    
    public void setAgeLevel12_Item6(int ageLevel12_Item6) {
        this.ageLevel12_Item6 = ageLevel12_Item6;
    }
    
    public int getAgeLevel12_ItemAlt1() {
        return ageLevel12_ItemAlt1;
    }
    
    public void setAgeLevel12_ItemAlt1(int ageLevel12_ItemAlt1) {
        this.ageLevel12_ItemAlt1 = ageLevel12_ItemAlt1;
    }
    
    public int getAgeLevel12_ItemAlt2() {
        return ageLevel12_ItemAlt2;
    }
    
    public void setAgeLevel12_ItemAlt2(int ageLevel12_ItemAlt2) {
        this.ageLevel12_ItemAlt2 = ageLevel12_ItemAlt2;
    }
    
    public int getAgeLevel12_ItemAlt3() {
        return ageLevel12_ItemAlt3;
    }
    
    public void setAgeLevel12_ItemAlt3(int ageLevel12_ItemAlt3) {
        this.ageLevel12_ItemAlt3 = ageLevel12_ItemAlt3;
    }
    
    public int getAgeLevel14_Item1() {
        return ageLevel14_Item1;
    }
    
    public void setAgeLevel14_Item1(int ageLevel14_Item1) {
        this.ageLevel14_Item1 = ageLevel14_Item1;
    }
    
    public int getAgeLevel14_Item2() {
        return ageLevel14_Item2;
    }
    
    public void setAgeLevel14_Item2(int ageLevel14_Item2) {
        this.ageLevel14_Item2 = ageLevel14_Item2;
    }
    
    public int getAgeLevel14_Item3() {
        return ageLevel14_Item3;
    }
    
    public void setAgeLevel14_Item3(int ageLevel14_Item3) {
        this.ageLevel14_Item3 = ageLevel14_Item3;
    }
    
    public int getAgeLevel14_Item4() {
        return ageLevel14_Item4;
    }
    
    public void setAgeLevel14_Item4(int ageLevel14_Item4) {
        this.ageLevel14_Item4 = ageLevel14_Item4;
    }
    
    public int getAgeLevel14_Item5() {
        return ageLevel14_Item5;
    }
    
    public void setAgeLevel14_Item5(int ageLevel14_Item5) {
        this.ageLevel14_Item5 = ageLevel14_Item5;
    }
    
    public int getAgeLevel14_Item6() {
        return ageLevel14_Item6;
    }
    
    public void setAgeLevel14_Item6(int ageLevel14_Item6) {
        this.ageLevel14_Item6 = ageLevel14_Item6;
    }
    
    public int getAgeLevel14_ItemAlt1() {
        return ageLevel14_ItemAlt1;
    }
    
    public void setAgeLevel14_ItemAlt1(int ageLevel14_ItemAlt1) {
        this.ageLevel14_ItemAlt1 = ageLevel14_ItemAlt1;
    }
    
    public int getAgeLevel14_ItemAlt2() {
        return ageLevel14_ItemAlt2;
    }
    
    public void setAgeLevel14_ItemAlt2(int ageLevel14_ItemAlt2) {
        this.ageLevel14_ItemAlt2 = ageLevel14_ItemAlt2;
    }
    
    public int getAgeLevel14_ItemAlt3() {
        return ageLevel14_ItemAlt3;
    }
    
    public void setAgeLevel14_ItemAlt3(int ageLevel14_ItemAlt3) {
        this.ageLevel14_ItemAlt3 = ageLevel14_ItemAlt3;
    }
    
    public int getAgeLevel16_Item1() {
        return ageLevel16_Item1;
    }
    
    public void setAgeLevel16_Item1(int ageLevel16_Item1) {
        this.ageLevel16_Item1 = ageLevel16_Item1;
    }
    
    public int getAgeLevel16_Item2() {
        return ageLevel16_Item2;
    }
    
    public void setAgeLevel16_Item2(int ageLevel16_Item2) {
        this.ageLevel16_Item2 = ageLevel16_Item2;
    }
    
    public int getAgeLevel16_Item3() {
        return ageLevel16_Item3;
    }
    
    public void setAgeLevel16_Item3(int ageLevel16_Item3) {
        this.ageLevel16_Item3 = ageLevel16_Item3;
    }
    
    public int getAgeLevel16_Item4() {
        return ageLevel16_Item4;
    }
    
    public void setAgeLevel16_Item4(int ageLevel16_Item4) {
        this.ageLevel16_Item4 = ageLevel16_Item4;
    }
    
    public int getAgeLevel16_Item5() {
        return ageLevel16_Item5;
    }
    
    public void setAgeLevel16_Item5(int ageLevel16_Item5) {
        this.ageLevel16_Item5 = ageLevel16_Item5;
    }
    
    public int getAgeLevel16_Item6() {
        return ageLevel16_Item6;
    }
    
    public void setAgeLevel16_Item6(int ageLevel16_Item6) {
        this.ageLevel16_Item6 = ageLevel16_Item6;
    }
    
    public int getAgeLevel16_ItemAlt1() {
        return ageLevel16_ItemAlt1;
    }
    
    public void setAgeLevel16_ItemAlt1(int ageLevel16_ItemAlt1) {
        this.ageLevel16_ItemAlt1 = ageLevel16_ItemAlt1;
    }
    
    public int getAgeLevel16_ItemAlt2() {
        return ageLevel16_ItemAlt2;
    }
    
    public void setAgeLevel16_ItemAlt2(int ageLevel16_ItemAlt2) {
        this.ageLevel16_ItemAlt2 = ageLevel16_ItemAlt2;
    }
    
    public int getAgeLevel16_ItemAlt3() {
        return ageLevel16_ItemAlt3;
    }
    
    public void setAgeLevel16_ItemAlt3(int ageLevel16_ItemAlt3) {
        this.ageLevel16_ItemAlt3 = ageLevel16_ItemAlt3;
    }
    
    public int getAgeLevel19_Item1() {
        return ageLevel19_Item1;
    }
    
    public void setAgeLevel19_Item1(int ageLevel19_Item1) {
        this.ageLevel19_Item1 = ageLevel19_Item1;
    }
    
    public int getAgeLevel19_Item2() {
        return ageLevel19_Item2;
    }
    
    public void setAgeLevel19_Item2(int ageLevel19_Item2) {
        this.ageLevel19_Item2 = ageLevel19_Item2;
    }
    
    public int getAgeLevel19_Item3() {
        return ageLevel19_Item3;
    }
    
    public void setAgeLevel19_Item3(int ageLevel19_Item3) {
        this.ageLevel19_Item3 = ageLevel19_Item3;
    }
    
    public int getAgeLevel19_Item4() {
        return ageLevel19_Item4;
    }
    
    public void setAgeLevel19_Item4(int ageLevel19_Item4) {
        this.ageLevel19_Item4 = ageLevel19_Item4;
    }
    
    public int getAgeLevel19_Item5() {
        return ageLevel19_Item5;
    }
    
    public void setAgeLevel19_Item5(int ageLevel19_Item5) {
        this.ageLevel19_Item5 = ageLevel19_Item5;
    }
    
    public int getAgeLevel19_Item6() {
        return ageLevel19_Item6;
    }
    
    public void setAgeLevel19_Item6(int ageLevel19_Item6) {
        this.ageLevel19_Item6 = ageLevel19_Item6;
    }
    
    public int getAgeLevel19_ItemAlt1() {
        return ageLevel19_ItemAlt1;
    }
    
    public void setAgeLevel19_ItemAlt1(int ageLevel19_ItemAlt1) {
        this.ageLevel19_ItemAlt1 = ageLevel19_ItemAlt1;
    }
    
    public int getAgeLevel19_ItemAlt2() {
        return ageLevel19_ItemAlt2;
    }
    
    public void setAgeLevel19_ItemAlt2(int ageLevel19_ItemAlt2) {
        this.ageLevel19_ItemAlt2 = ageLevel19_ItemAlt2;
    }
    
    public int getAgeLevel19_ItemAlt3() {
        return ageLevel19_ItemAlt3;
    }
    
    public void setAgeLevel19_ItemAlt3(int ageLevel19_ItemAlt3) {
        this.ageLevel19_ItemAlt3 = ageLevel19_ItemAlt3;
    }
    
    public int getAgeLevel22_Item1() {
        return ageLevel22_Item1;
    }
    
    public void setAgeLevel22_Item1(int ageLevel22_Item1) {
        this.ageLevel22_Item1 = ageLevel22_Item1;
    }
    
    public int getAgeLevel22_Item2() {
        return ageLevel22_Item2;
    }
    
    public void setAgeLevel22_Item2(int ageLevel22_Item2) {
        this.ageLevel22_Item2 = ageLevel22_Item2;
    }
    
    public int getAgeLevel22_Item3() {
        return ageLevel22_Item3;
    }
    
    public void setAgeLevel22_Item3(int ageLevel22_Item3) {
        this.ageLevel22_Item3 = ageLevel22_Item3;
    }
    
    public int getAgeLevel22_Item4() {
        return ageLevel22_Item4;
    }
    
    public void setAgeLevel22_Item4(int ageLevel22_Item4) {
        this.ageLevel22_Item4 = ageLevel22_Item4;
    }
    
    public int getAgeLevel22_Item5() {
        return ageLevel22_Item5;
    }
    
    public void setAgeLevel22_Item5(int ageLevel22_Item5) {
        this.ageLevel22_Item5 = ageLevel22_Item5;
    }
    
    public int getAgeLevel22_Item6() {
        return ageLevel22_Item6;
    }
    
    public void setAgeLevel22_Item6(int ageLevel22_Item6) {
        this.ageLevel22_Item6 = ageLevel22_Item6;
    }
    
    public int getAgeLevel22_ItemAlt1() {
        return ageLevel22_ItemAlt1;
    }
    
    public void setAgeLevel22_ItemAlt1(int ageLevel22_ItemAlt1) {
        this.ageLevel22_ItemAlt1 = ageLevel22_ItemAlt1;
    }
    
    public int getAgeLevel22_ItemAlt2() {
        return ageLevel22_ItemAlt2;
    }
    
    public void setAgeLevel22_ItemAlt2(int ageLevel22_ItemAlt2) {
        this.ageLevel22_ItemAlt2 = ageLevel22_ItemAlt2;
    }
    
    public int getAgeLevel22_ItemAlt3() {
        return ageLevel22_ItemAlt3;
    }
    
    public void setAgeLevel22_ItemAlt3(int ageLevel22_ItemAlt3) {
        this.ageLevel22_ItemAlt3 = ageLevel22_ItemAlt3;
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
    
    
    
    
    
    
}
