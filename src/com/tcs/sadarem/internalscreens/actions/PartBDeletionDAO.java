package com.tcs.sadarem.internalscreens.actions;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PartBDeletionDAO{

	public int updatedeleterecord(String sadaremID,
			String aadhaarflag, String reasonremarks) throws SQLException ;
}
