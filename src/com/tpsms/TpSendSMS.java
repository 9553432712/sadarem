package com.tpsms;


import java.util.ArrayList;

/*import org.apache.axis2.AxisFault;
import java.rmi.RemoteException;*/
import com.tcs.sadarem.util.SMSUtility;


public class TpSendSMS
{
    
    public static String sendSMS(String phoneNo,String message) /*throws AxisFault,RemoteException*/
    {
     
       /* TpSMSServiceStub stub =  new TpSMSServiceStub();
        TpSMSServiceStub.SendSADAREMSMS smsList =  new TpSMSServiceStub.SendSADAREMSMS();
        if(message.length()>160)
        {
            message=message.substring(0,160);                   
        }        
        smsList.setMessage(message);
        smsList.setPhoneNo(phoneNo);
        TpSMSServiceStub.AuthHeaderE authHeaderE = new TpSMSServiceStub.AuthHeaderE();
        TpSMSServiceStub.AuthHeader authHeader =  new TpSMSServiceStub.AuthHeader();
        authHeader.setUsername("SADAREM");
        authHeader.setPassword("SADAREM@321");
        authHeaderE.setAuthHeader(authHeader);
        TpSMSServiceStub.SendSADAREMSMSResponse response = stub.SendSADAREMSMS(smsList,authHeaderE);
        return response.getSendSADAREMSMSResult();*/
    	
    	String result = "";
    	
    	try
    	{
    		ArrayList dataList = (ArrayList)SMSUtility.sendmysms(phoneNo, message);
    		
    		if(dataList.size()>0)
    		{
    			if(dataList.get(0).toString().equalsIgnoreCase("Y"))
    			{
    				result="OK";
    			}
    			else
    			{
    				result="Failed";
    			}
    		}
    	}
    	catch(Exception e)
    	{
    		result="Exception";
    	}
    	   
    	return result;
    }

}
