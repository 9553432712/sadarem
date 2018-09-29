/*
 *
 *
 * 
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bf.disability.common;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMException;
import org.bf.disability.form.ServiceBean;

/**
 *
 * @author sandeep & ganesh
 */
public class CommonService {
    
    

     //CommonDAO commondao = new CommonDAO();
    
     public  String getUsername(DataSource ds,String userid)throws SADAREMException
	{
         CommonDAO commondao = new CommonDAO();
           String username=null;
           try{
            username= commondao.getUsername(ds,userid);
            } catch (SADAREMException sADAREMException) {
            sADAREMException.printStackTrace();
            throw new SADAREMException();
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new SADAREMException();
        }
            return username;
        }
 public ArrayList getRoles(DataSource ds)throws SADAREMException
	     {
           CommonDAO commondao = new CommonDAO();
           ArrayList rolelist = new ArrayList();
           try{
			rolelist= commondao.getRoles(ds);
                        } catch (SADAREMException sADAREMException) {
            sADAREMException.printStackTrace();
            throw new SADAREMException();
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new SADAREMException();
        }
           return rolelist;
		 }
 public ArrayList getUsers(DataSource ds)throws SADAREMException
	     {
     CommonDAO commondao = new CommonDAO();
      ArrayList userlist = new ArrayList();
     try{
         userlist=commondao.getUsers(ds);
       } catch (SADAREMException sADAREMException) {
            sADAREMException.printStackTrace();
            throw new SADAREMException();
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new SADAREMException();
        }
			return userlist;
		 }
 
  ArrayList list1= new ArrayList();
  
    int depth=1; 
 
    public ArrayList sortServices(ArrayList servicelist) throws SADAREMException{
        try{
        mymethod(servicelist,1,0);
        } catch (SADAREMException sADAREMException) {
            sADAREMException.printStackTrace();
            throw new SADAREMException();
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new SADAREMException();
        }
        
        return list1;
    }
    
    
     public void mymethod(ArrayList list, int serviceid,int parentid)throws SADAREMException {
         try{
        int j=checkparentservice(list,serviceid,parentid);
        int k=0;
        for(int temp=0;temp<list.size();temp++) {
            ServiceBean servicebean = (ServiceBean)list.get(temp);
            int tempserid=Integer.parseInt(servicebean.getService_id());
            if(tempserid==serviceid) {
                k=temp;
                break;
            }
        }
        ServiceBean servicebean = (ServiceBean)list.get(k);
        
        if(j==0)
            return;
        else {
            int i=checkifparent(list,serviceid);
            if(servicebean.getAddflag().equals("false")) {
                servicebean.setAddflag("true");
				((ServiceBean)(list.get(k))).setAddflag("true");
				if(i!=0)
				{
                          servicebean.setIs_parent("true");
				         ((ServiceBean)(list.get(k))).setIs_parent("true");
				}
				else
				{
                          servicebean.setIs_parent("false");
				         ((ServiceBean)(list.get(k))).setIs_parent("false");
				}

                servicebean.setDepth(Integer.toString(depth));
                list1.add(servicebean);
            }
            if(i==0)
            {
              
                mymethod(list,serviceid+1,parentid);
               
            }
            else
            {
                ++depth;
                mymethod(list,i,serviceid);
                --depth;
                if(i==0)
                {
                    
                    mymethod(list,serviceid+1,parentid);
                   
                }
                else
                {
                  
                    mymethod(list,serviceid,parentid);
                    
                }
            }
        }
        } catch (SADAREMException sADAREMException) {
            sADAREMException.printStackTrace();
            throw new SADAREMException();
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new SADAREMException();
        }
    }
     
     
    public int checkparentservice(ArrayList list, int serviceid,int parentid) throws SADAREMException{
      
        for (int i=0;i<list.size();i++) {
            ServiceBean servicebean = (ServiceBean)list.get(i);
            int parid=Integer.parseInt(servicebean.getParent_id());
            int serid = Integer.parseInt(servicebean.getService_id());
           
            if(serid==serviceid) {
                if(parid==parentid)
                    return 1;
            }
        }
        
        return 0;
    }
    
    
    
    public int checkifparent(ArrayList list, int serviceid)throws SADAREMException {
      
        for (int i=0;i<list.size();i++) {
            ServiceBean servicebean = (ServiceBean)list.get(i);
            int parid=Integer.parseInt(servicebean.getParent_id());
            int serid = Integer.parseInt(servicebean.getService_id());
            if(servicebean.getAddflag().equals("false"))
            if(parid==serviceid) {
                return serid;
            }
        }
        
        return 0;
    }



    public boolean checkUploadPhoto(DataSource ds, String PersonCode) throws SADAREMException {

        CommonDAO commondao = null;
        boolean uploadPhotoFlag = false;
        try {
            commondao = new CommonDAO();
            uploadPhotoFlag = commondao.checkUploadPhoto(ds, PersonCode);
        } catch (SADAREMException sADAREMException) {
            sADAREMException.printStackTrace();
            throw new SADAREMException();
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new SADAREMException();
        }
        return uploadPhotoFlag;
    }
    
     public int insertUploadPhoto(DataSource ds, String PersonCode,String loginId) throws SADAREMException {

        CommonDAO commondao = null;
        int i = 0;
        try {
            commondao = new CommonDAO();
            i = commondao.insertUploadPhoto(ds, PersonCode,loginId);
        } catch (SADAREMException sADAREMException) {
            sADAREMException.printStackTrace();
            throw new SADAREMException();
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new SADAREMException();
        }
        return i;
    }






}