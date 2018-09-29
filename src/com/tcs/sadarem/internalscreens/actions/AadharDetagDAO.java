package com.tcs.sadarem.internalscreens.actions;

import java.util.ArrayList;

public interface AadharDetagDAO
{
	

	public ArrayList getSadaremCommonData(String sadaremID);
	
	public int RemarksLogInsert(String sadaremID,String aadharId,String remark,String victimeName,String createdBy);
	public int AadharDetagqry(String sadaremId);
	

}
