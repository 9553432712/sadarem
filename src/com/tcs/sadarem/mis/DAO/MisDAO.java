package com.tcs.sadarem.mis.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;



public interface MisDAO {
	
	public ArrayList getActiveActivityList();	
	public ArrayList getActSubactList();
	public ArrayList getSPMUleveltargets(String distID, String activeactID, String activesubactID);
	public ArrayList getDistrictWisePopulation();
	public String  instpopulatio(HashMap insertdtls);
	public String confirmDtls(HashMap insertdtls,String flag);
	public ArrayList getMandalListByDistrictIDPopulation(String distID);
	public String getDistrictName(String distID);
	public ArrayList getNewDistrictList();
	public Object getSubActName(String activesubactID);
	public ArrayList getSPMUleveltargetsaftersaving(String distID, String year,String activeactID, String activesubactID);
	public String inserttargetsatspmulevel(HashMap insertdtls);
	public ArrayList getActiveSubActivityList(String activeactID);
	public String getMisTargetIDcheckMethod(String year, String distID,String activeactID, String activesubactID);
	public String confirmtargetsatspmulevel(HashMap insertdtls);
	public ArrayList getSPMUleveldistricttargets(String year);
	public ArrayList getNewMandalList(String distID );
	public ArrayList getMonths(int parseInt);
	public String getNewdistrictID(String distID, String mandalid);
	public ArrayList getDPMlevelMandalTarAchBalValues(String distid,String mandalid, String year, String monthyear);
	public String saveupdateAchvssatspmulevel(HashMap insertdtls);
	public String confirmupdateAchvssatspmulevel(HashMap inputList);
	
	public String getMonthForInt(int parseInt);
	public ArrayList getMandalTargetAchieveBal(String string, String distID,String mandalid, String string2);
	public String updateMandalTarAchieve(HashMap inputList);
	public String confirmMandalTarAchieve(HashMap inputList);
	
	////////////////////
	public ArrayList getSPMUDistWiseRanks(String finyear, String month) throws Exception;
	public ArrayList getRanks(String finyear, String monthyear) throws Exception;
	public String getMonthName(String monthyear);
	public ArrayList getMasterRanksActivityWise(String finyear, String monthyear) throws SQLException;
	
}
