package com.tcs.sadarem.withoutproofparta.DAO;

import java.util.ArrayList;

public interface PartAWithoutProofWorklistDAO
{
	public ArrayList getPendingWithoutProofPartaList(String distID,String reqStatus,String fromdate, String todate);

}
