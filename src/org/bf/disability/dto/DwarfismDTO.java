
package org.bf.disability.dto;
/**
 * This class contains the fields, required to compute Dwarfism
 * @author Kiran
 * @version 1.0
 */
public class DwarfismDTO
{
    private int ageyears;
    private int agemonths;
    private float height;   
  
    private float dwarfism;
    private String systemip;
    private String loginid;
 private String personcode;
    public int getAgeyears() {
        return ageyears;
    }

    public void setAgeyears(int ageyears) {
        this.ageyears = ageyears;
    }

    public int getAgemonths() {
        return agemonths;
    }

    public void setAgemonths(int agemonths) {
        this.agemonths = agemonths;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

  

    public float getDwarfism() {
        return dwarfism;
    }

    public void setDwarfism(float dwarfism) {
        this.dwarfism = dwarfism;
    }

    public String getSystemip() {
        return systemip;
    }

    public void setSystemip(String systemip) {
        this.systemip = systemip;
    }

    public String getPersoncode() {
        return personcode;
    }

    public void setPersoncode(String personcode) {
        this.personcode = personcode;
    }

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }
   
}
