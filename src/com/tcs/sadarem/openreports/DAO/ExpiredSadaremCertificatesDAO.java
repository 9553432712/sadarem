
package com.tcs.sadarem.openreports.DAO;

import java.util.ArrayList;


public interface ExpiredSadaremCertificatesDAO  
{
		

	public ArrayList getExpireddata(String fromdate, String todate,String distId,String mandalId,String villageId);
	public ArrayList getExpireddistrictdata(String fromdate, String todate,String distId,String mandalId,String villageId);

	

	
}

