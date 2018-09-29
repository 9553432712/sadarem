/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.form;

import org.apache.struts.action.ActionForm;

/**
 *
 * @author 567999
 */
public class Report16Form extends ActionForm
{

    private String mode=null;
    private String fromdate = null;
    private String todate = null;

    public String getFromdate() 
    {
        return fromdate;
    }

    public void setFromdate(String fromdate) 
    {
        this.fromdate = fromdate;
    }

    public String getMode() 
    {
        return mode;
    }

    public void setMode(String mode) 
    {
        this.mode = mode;
    }

    public String getTodate() 
    {
        return todate;
    }

    public void setTodate(String todate)
    {
        this.todate = todate;
    }
    
}
