/*
 * ServiceBean.java
 *
 * Created on February 5, 2007, 5:08 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bf.disability.form;
/**
 *
 * @author suresh.s
 */
public class ServiceBean {
    
    /** Creates a new instance of ServiceBean */
    public ServiceBean() {
    }

      public String service_id;
      public String service_name;
       public String target_url;
        public String parent_id;
         public String priority;
         public String addflag;
         public String depth;
		 public String is_parent;
		 public String select;


public String getIs_parent() {
        return is_parent;
    }

    public void setIs_parent(String is_parent) {
        this.is_parent = is_parent;
    }


	public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }


    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public String getTarget_url() {
        return target_url;
    }

    public void setTarget_url(String target_url) {
        this.target_url = target_url;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getAddflag() {
        return addflag;
    }

    public void setAddflag(String addflag) {
        this.addflag = addflag;
    }

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }
 
      
}
    
    
    

