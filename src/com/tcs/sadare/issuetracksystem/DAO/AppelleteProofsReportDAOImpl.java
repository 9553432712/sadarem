package com.tcs.sadare.issuetracksystem.DAO;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.tcs.sadarem.util.DataAccess;


public class AppelleteProofsReportDAOImpl implements AppelleteProofsReportDAO{
	
	static final private Logger log = Logger.getLogger(AppelleteProofsReportDAOImpl.class);
	String lStrQuery;
	
	public ArrayList getRecords()
	{
		ArrayList records = new ArrayList();
		String qry = "";
		try
		{
			qry = 
				"select  v.sadarem_id,t.tkt_req_id,\n"+
				"case when m.APELATE_CATEGORY='D' then 'Doubtful Case' when m.APELATE_CATEGORY='F' then 'Fraud Case'\n"+
				"	 when m.APELATE_CATEGORY='T' then 'Third time Reassessment' end  category,  \n"+
				"person_surname+''+v.person_name,district_name,mandal_name,village_name ,\n"+
				"case when v.dis_form_status='APA' then 'N' when v.dis_form_status='View' then 'Y' end status,\n"+
				"(select proof_doc_path+',' from tkt_request_master(nolock) b,tkt_req_proof_details(nolock) c \n"+
				"where b.tkt_req_id = c.tkt_req_id and b.tkt_req_id = t.tkt_req_id FOR XML PATH(''))\n"+
				"from sadarem_view_complete_details v,\n"+
				"tkt_request_master t,tkt_request_tobe_modify_dtls m\n"+
				"where v.sadarem_id = t.sadarem_id and t.tkt_req_id=m.tkt_req_id and v.sadarem_id = m.sadarem_id\n"+
				" and  Category_ID=4 and t.tkt_type_id='S018'   \n";
			
			
			records = DataAccess.getData(qry);
			//System.out.println("records-->"+records); 
		}
		catch(Exception e)
		{
			log.info("Exception raised in AppelleteProofsReportDAOImpl @ getRecords of "+e);
		}
		return records;
	}
	
	

}
