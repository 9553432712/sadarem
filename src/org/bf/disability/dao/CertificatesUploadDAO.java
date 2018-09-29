/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.form.PartAForm;

import com.tcs.sadarem.common.DAO.CommonDAO;
import com.tcs.sadarem.common.DAO.CommonDAOImpl;
import com.tcs.sadarem.util.CommonUtility;
import com.tcs.sadarem.util.CommonValidators;
import com.tcs.sadarem.util.DataAccess;
import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author 747577
 */
public class CertificatesUploadDAO 
{
	static final private Logger log = Logger.getLogger(CertificatesUploadDAO.class);
	Connection lcon=null;
	String lStrQuery=null;
	
	
	//CommonConstants.SADAREM_DOCUMENTS_PATH
	
	public void uploadSADAREMCertificates(HttpServletRequest request,String sadaremId,HashMap formData,String category_id,String author)
	{
		lcon=null;
		try
		{
			CommonDAO comObj = new CommonDAOImpl();
			
			String clientIPAddress = CommonUtility.getClientIPAddress(request);
			
			FileItem certFile 	= null;
			FileItem idCardFile = null;
			
			String certFileName		= "";
			String idCardFileName	= "";
			
			String certFileType 	= "";
			String idCardFileType 	= "";
			
			String certRemarks 		= CommonUtility.getStringFromFileItem((FileItem)formData.get("upload_remarks"));

			if(formData.containsKey("cert_file"))
			{
				certFile = (FileItem)formData.get("cert_file");
				certFileName = certFile.getName();
				
				certFileType = certFileName.substring((certFileName.lastIndexOf(".")+1));
			}
			
			if(formData.containsKey("idcard_file"))
			{
				idCardFile 		= (FileItem)formData.get("idcard_file");
				idCardFileName  = idCardFile.getName();
				
				idCardFileType = idCardFileName.substring((idCardFileName.lastIndexOf(".")+1));
			}
			
			long fileMaxSize = 500*1024; // 500 KB
			
			if
			(
					certFile!=null && certFile.getSize()>fileMaxSize && 
					(certFile.getContentType()).trim().equalsIgnoreCase("application/pdf") && 
					certFileType.equalsIgnoreCase("pdf")
			)
			{
				 request.setAttribute("alert_msg", "Please upload valid certificate documnet (pdf type with less than 500KB of size.)");  
		    	 request.setAttribute("alert_css","alert-danger");
			}
			else if
			(
					idCardFile!=null && idCardFile.getSize()>fileMaxSize && 
					(idCardFile.getContentType()).trim().equalsIgnoreCase("application/pdf") && 
					idCardFileType.equalsIgnoreCase("pdf")
			)
			{
				 request.setAttribute("alert_msg", "Please upload valid ID Card documnet (pdf type with less than 500KB of size.)");  
		    	 request.setAttribute("alert_css","alert-danger");
			}
			else if(CommonValidators.htmlStrValidation(certRemarks)==false)
			{
				 request.setAttribute("alert_msg", "Please enter valid remarks");  
		    	 request.setAttribute("alert_css","alert-danger");
			}
			else
			{
				HashMap basicDetails = comObj.getSADAREMBasicDetailsByID(sadaremId);
				if(!CommonUtility.checkNullObj(basicDetails.get("dis_form_status")).equalsIgnoreCase("view"))
				{
					 request.setAttribute("alert_msg", "Please fill Part-B details for this SADAREM ID : "+sadaremId+" to upload certificates.");  
			    	 request.setAttribute("alert_css","alert-danger");
				} 
				else if(!CommonUtility.checkNullObj(basicDetails.get("person_live_status")).equalsIgnoreCase("live"))
				{
					 request.setAttribute("alert_msg", "Please change the person status to live for this SADAREM ID: "+sadaremId+" to upload certificates.");  
			    	 request.setAttribute("alert_css","alert-danger");
				} 
				else if(CommonValidators.AadhaarNumberValidator(CommonUtility.checkNullObj(basicDetails.get("proof_id")))==false)
				{
					 request.setAttribute("alert_msg", "Please update valid Aadhaar Id for this SADAREM ID: "+sadaremId+" to upload certificates.");  
			    	 request.setAttribute("alert_css","alert-danger");
				}
				else
				{
					lcon= DBConnection.getConnection();
					lcon.setAutoCommit(false);
					boolean logCertInsert		= false,
							logIDCardInsert		= false,
							
							updateInsertCert	= false,
							updateInsertIdCard	= false,
							
							backUpCertStatus	= false,
							backUpIDCardStatus	= false,
							
							newCertCreate		= false,
							newIDCardCreate		= false,
							
							statusUpdate		= false;
					
					String backUpfileName = CommonUtility.getDateTime("ddMMyyyy")+"_"+ CommonUtility.getDateTime("HHmmss");

					String certFilePath 		=  CommonConstants.SADAREM_DOCUMENTS_PATH+ sadaremId + "//" + CommonConstants.CERTIFICATE + "//"+CommonConstants.CERTIFICATE+"."+certFileType;
					String backUpCertFilePath 	=  CommonConstants.SADAREM_DOCUMENTS_PATH+ sadaremId + "//" + CommonConstants.CERTIFICATE + "//"+CommonConstants.CERTIFICATE+"_"+backUpfileName+"."+certFileType;
					
					
					String idCardFilePath   =  CommonConstants.SADAREM_DOCUMENTS_PATH+ sadaremId + "//" + CommonConstants.IDCARD + "//"+CommonConstants.IDCARD+"."+idCardFileType;
					String backUpIDFilePath	=  CommonConstants.SADAREM_DOCUMENTS_PATH+ sadaremId + "//" + CommonConstants.IDCARD + "//"+CommonConstants.IDCARD+"_"+backUpfileName+"."+certFileType;
					
					
					
					// Certificate Upload 
					if(!certFileType.equals(""))
					{
						
						logCertInsert 			= logCertificateUploadDetails(lcon,sadaremId,CommonConstants.CERTIFICATE); 
						updateInsertCert 		= mergeCertificateDetails(lcon, sadaremId, CommonConstants.CERTIFICATE, category_id.trim(),certFilePath, clientIPAddress, certRemarks, author);
						backUpCertStatus		= CommonUtility.copyFileAndDelete(new File(certFilePath), new File(backUpCertFilePath), true,false);
						
						if(backUpCertStatus==true)
						{
							newCertCreate =  uploadDocument(certFile,CommonConstants.SADAREM_DOCUMENTS_PATH+ sadaremId + "//" + CommonConstants.CERTIFICATE,CommonConstants.CERTIFICATE+"."+certFileType);
						}
						 
					}
					else
					{
						logCertInsert		= true;
						updateInsertCert	= true;
						backUpCertStatus	= true;
						newCertCreate		= true;
					}
					
					
					// ID Card Upload 
					if(!idCardFileType.equals(""))
					{
						
						logIDCardInsert 	= logCertificateUploadDetails(lcon,sadaremId,CommonConstants.IDCARD); 
						updateInsertIdCard 	= mergeCertificateDetails(lcon, sadaremId, CommonConstants.IDCARD, category_id.trim(),idCardFilePath, clientIPAddress, certRemarks, author);
						backUpIDCardStatus	= CommonUtility.copyFileAndDelete(new File(idCardFilePath), new File(backUpIDFilePath), true,false);
						
						if(backUpIDCardStatus==true)
						{
							newIDCardCreate =  uploadDocument(idCardFile,CommonConstants.SADAREM_DOCUMENTS_PATH+ sadaremId + "//" + CommonConstants.IDCARD,CommonConstants.IDCARD+"."+certFileType);
						}
						  
					}
					else
					{
						logIDCardInsert 	= true;
						updateInsertIdCard  = true;
						backUpIDCardStatus  = true;
						newIDCardCreate		= true;
					}
					
					statusUpdate = updateCertificateStatusforReport(lcon, sadaremId);
					
					if(
							logCertInsert	 	== true &&
							logIDCardInsert  	== true &&
							updateInsertCert 	== true &&
							updateInsertIdCard  == true &&
							statusUpdate	 	== true &&
							backUpIDCardStatus 	== true &&
							newCertCreate 		== true &&
							newIDCardCreate		== true &&
							backUpIDCardStatus 	== true && 
							newIDCardCreate 	== true
					  )
					{
						lcon.commit();

						 request.setAttribute("alert_msg", "Documents Uploaded Successfully for this SADAREM ID: "+sadaremId+".");  
				    	 request.setAttribute("alert_css","alert-success");
					}
					else
					{
						lcon.rollback();
						CommonUtility.copyFileAndDelete(new File(backUpCertFilePath), new File(certFilePath), true,true); 
						backUpIDCardStatus	= CommonUtility.copyFileAndDelete(new File(backUpIDFilePath),new File(idCardFilePath),true,true);
						
						 request.setAttribute("alert_msg", "Failed to Upload the Documents for this SADAREM ID: "+sadaremId+".");  
				    	 request.setAttribute("alert_css","alert-danger");
					}
					lcon.close();
				}
			 
			}
 
		} 
		catch(Exception e)
		{
			e.printStackTrace();
			 request.setAttribute("alert_msg", "Sorry we are not able to process your request. Please try again.!");  
	    	 request.setAttribute("alert_css","alert-danger");
		}
		finally
		{
			DBConnection.closeConnection(lcon);
		}
	}
	
	private boolean logCertificateUploadDetails(Connection con,String sadarem_id,String docType)
	{
		boolean status = false;
		PreparedStatement preStrm = null;
		try
		{
			lStrQuery =  
					"INSERT INTO Certificateuploaddetails_log\n" +
					"(\n" + 
					"person_code,certificatetype,categoryid,filepath,systemip,status,logged_remarks,created_by,createddate,updated_by,updateddate\n" + 
					")\n" + 
					"SELECT person_code,certificatetype,categoryid,filepath,systemip,status,remarks,created_by,createddate,updated_by,updateddate \n" + 
					"FROM Certificateuploaddetails\n" + 
					"WHERE person_code=? AND\n" + 
					"LTRIM(RTRIM(UPPER(certificatetype)))=?";
			
		//	System.out.println("lStrQuery : \n"+lStrQuery);
			
			preStrm = con.prepareStatement(lStrQuery);
			preStrm.setString(1, sadarem_id.trim());
			preStrm.setString(2, docType.trim().toUpperCase());
			
			if(preStrm.executeUpdate()>=0)
			{
				status = true;
			}
			else
			{
				status = false;
			}
			
			preStrm.close();

		}
		catch(Exception e)
		{
			log.error(e);
			e.printStackTrace();
			status = false;
		}
		finally
		{
			DBConnection.closeStatement(preStrm);
		}
		
		return status;
	}

	private boolean mergeCertificateDetails
	(
		Connection con,String sadarem_id,String certificatetype,
		String categoryId,String filePath,
		String userIPAddress,String remarks,String author
	)
	{
		boolean status = false;
		PreparedStatement preStrm = null;
		try
		{
			lStrQuery =   
						"MERGE Certificateuploaddetails a\n" +
						"USING (SELECT ? sadarem_id,? certificate_type,COUNT(*) mycount\n" + 
						" FROM Certificateuploaddetails WHERE person_code=? AND LTRIM(RTRIM(UPPER(certificatetype)))=?) b\n" + 
						"ON(a.person_code=b.sadarem_id AND LTRIM(RTRIM(UPPER(a.certificatetype))) = LTRIM(RTRIM(b.certificate_type)) AND b.mycount>0)\n" + 
						"WHEN MATCHED THEN\n" + 
						"UPDATE SET categoryid=?,filepath=?,systemip=?,remarks=?,updated_by=?,updateddate=CURRENT_TIMESTAMP,status='Active'\n" + 
						"WHEN NOT MATCHED THEN\n" + 
						"INSERT(person_code,certificatetype,categoryid,filepath,systemip,status,remarks,created_by,createddate,updated_by,updateddate)\n" + 
						"VALUES(?,?,?,?,?,'Active',?,?,current_timestamp,?,current_timestamp);";

			
						preStrm = con.prepareStatement(lStrQuery);
						int param=1;
						preStrm.setString(param++, sadarem_id.trim());
						preStrm.setString(param++, certificatetype.trim().toUpperCase());
						preStrm.setString(param++, sadarem_id.trim());
						preStrm.setString(param++, certificatetype.trim().toUpperCase());
						preStrm.setString(param++, categoryId);
						preStrm.setString(param++, filePath);
						preStrm.setString(param++, userIPAddress);
						preStrm.setString(param++, remarks);
						preStrm.setString(param++, author);
						preStrm.setString(param++, sadarem_id.trim());
						preStrm.setString(param++, certificatetype.trim());
						preStrm.setString(param++, categoryId);
						preStrm.setString(param++, filePath);
						preStrm.setString(param++, userIPAddress); 
						preStrm.setString(param++, remarks);
						preStrm.setString(param++, author);
						preStrm.setString(param++, author);
						
						if(preStrm.executeUpdate()>0)
						{
							status = true;
						}
						else
						{
							status = false;
						}
						
						preStrm.close();

		}
		catch(Exception e)
		{
			log.error(e);
			e.printStackTrace();
			status = false;
		}
		finally
		{
			DBConnection.closeStatement(preStrm);
		}
		
		return status; 
	}

	private boolean updateCertificateStatusforReport(Connection con,String sadarem_id)
	{
		boolean status = false;
		PreparedStatement preStrm = null;
		
		try
		{
			 lStrQuery =  
					"UPDATE sadarem_complete_details_sharing_reports\n" +
					" SET  cert_upload_status=(CASE WHEN c.Certificate IS NOT NULL THEN 'Y' ELSE 'N' END),\n" + 
					"cert_upload_date=c.Certificate,\n" + 
					"idcard_upload_status=(CASE WHEN c.IDCard IS NOT NULL THEN 'Y' ELSE 'N' END),\n" + 
					"idcard_updated_date=c.IDCard\n" + 
					" FROM (\n" + 
					" select *\n" + 
					"from\n" + 
					"(\n" + 
					"select person_code,certificatetype,MAX(updateddate) uploaded_date\n" + 
					"from Certificateuploaddetails a WITH(NOLOCK)\n" + 
					"GROUP BY person_code,certificatetype\n" + 
					") a\n" + 
					"pivot\n" + 
					"(\n" + 
					"  MAX(uploaded_date)\n" + 
					"for certificatetype in ([Certificate],[IDCard])\n" + 
					") as pvt\n" + 
					" ) c\n" + 
					" WHERE sadarem_complete_details_sharing_reports.sadarem_id=c.person_code\n" + 
					" AND sadarem_id=?";

			// System.out.println("lStrQuery : \n"+lStrQuery);
			 
			 preStrm = con.prepareStatement(lStrQuery);
			 preStrm.setString(1, sadarem_id);
			 
			 if(preStrm.executeUpdate()>0)
			 {
				 status=true;
			 }
			 else
			 {
				 status=false;
			 }
			 preStrm.close();
		}
		catch(Exception e)
		{
			log.error(e);
			e.printStackTrace();
			 status=false;
		}
		finally
		{
			DBConnection.closeStatement(preStrm);
		}
		
		return status;
	}
	
    public String checkFileUploadCount(DataSource ds, String personcode, String certificateType)
    { 
        String count = null;
        try
        { 
            ArrayList paramList = new ArrayList();
            ArrayList tempList  = new ArrayList();
            
            lStrQuery = " SELECT count(*) mycount FROM CertificateUploadDetails WITH(NOLOCK) where person_code=? and certificatetype=?";

            tempList  = new ArrayList();
            tempList.add("S");
            tempList.add(personcode);
            paramList.add(tempList);
            
            tempList  = new ArrayList();
            tempList.add("S");
            tempList.add(certificateType);
            paramList.add(tempList);
            
            count = DataAccess.getReturnResultByPstmt(lStrQuery, paramList);
//         
        }  
        catch(Exception e)
        {
        	e.printStackTrace();
        	log.error(e);
        }
        return count;
    }

    public ArrayList checkViewEditMode(DataSource ds, String personcode) throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;
        ArrayList viewEditMode = new ArrayList();
        try {
            con = DBConnection.getConnection();
            
            query = " select View_Edit_Mode,Person_Status from dbo.tblPerson_Personal_Details  with (nolock) where  person_code=? and status='Active'";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personcode);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    viewEditMode.add(rs.getString(1));
                    viewEditMode.add(rs.getString(2));
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkViewEditMode", "CertificatesUploadDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CertificatesUploadDAO", "checkViewEditMode");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkViewEditMode", "CertificatesUploadDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CertificatesUploadDAO", "checkViewEditMode");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);
        }
        return viewEditMode;
    }

    public String checkPersondisabilitydetails(DataSource ds, String personcode) throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;
        String count = null;
        try {
            con = DBConnection.getConnection();
            
            query = "select count(*) from tblPerson_Disability_Details  where  person_code=? and status ='Active' ";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personcode);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    if (rs.getString(1) != null) {
                        count = rs.getString(1);

                    } else {
                        count = "Invalid";


                    }
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkPersondisabilitydetails", "CertificatesUploadDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CertificatesUploadDAO", "checkPersondisabilitydetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkPersondisabilitydetails", "CertificatesUploadDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CertificatesUploadDAO", "checkPersondisabilitydetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);
        }
        return count;
    }

    public int chechinDisabilityPercentage(DataSource ds, String personcode) throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;
        int count = 0;
        try {
            con = DBConnection.getConnection();
            
            query = "select a.TotalDisability from dbo.tblPerson_Disability_TotalValue_Details a join"
                    + " tblPerson_Disability_Details b on(a.person_code=b.person_code)  where  a.person_code=? and a.status ='Active' ";
            //System.out.println("query==" + query);
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personcode);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    if (rs.getString(1) != null) {
                        count = rs.getInt(1);

                    }
                }
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "chechinDisabilityPercentage", "CertificatesUploadDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CertificatesUploadDAO", "chechinDisabilityPercentage");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "chechinDisabilityPercentage", "CertificatesUploadDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CertificatesUploadDAO", "chechinDisabilityPercentage");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);
        }
        return count;
    }

    public PartAForm getDetails(DataSource ds, PartAForm partAForm) throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;
        try {
            con = DBConnection.getConnection();
            

            query = "select distinct p.person_code,p.surname+' '+first_name,p.relation_name,p.age_years, "
            		+ " case when dd.Disability_ID='1' then 'Locomotor/OH'       when dd.Disability_ID='2' then 'Visual Impairment'  when dd.Disability_ID='3' then 'Hearing Impairment'    "
            		+ "  when dd.Disability_ID='4' then 'Mental Retardation'  when dd.Disability_ID='5' then 'Mental Illness' when dd.Disability_ID='6' then 'Multiple Disability' end  "
                    + "  ,('House Number :'+p.house_number+', Habitation : '+h.habitation_name+', Village :'+v.village_name+', Mandal :'+m.mandal_name+', District :'+d.district_name)"
                    + "  as address, case when p.gender='1' then 'Male' when p.gender='2' then 'FeMale' end,dp.totaldisability,d.district_name,m.mandal_name  "
                    + "  ,p.categoryid from dbo.tblPerson_Personal_Details p   with (nolock) "
                    + " left join tblPerson_Disability_TotalValue_Details dp on(dp.person_code=p.person_code) "
                    + "   left join tblPerson_Disability_Details dd on(dd.person_code=p.person_code) "
                    + "  left join dbo.tblDistrict_Details d on(d.district_id=p.district_id)"
                    + "  left join dbo.tblMandal_Details m on(m.district_id=p.district_id and m.mandal_id=p.mandal_id )  join dbo.tblVillage_Details v on(v.district_id=p.district_id and v.mandal_id=p.mandal_id and v.village_id=p.village_id)"
                    + " left join dbo.tblHabitation_Details h on(p.habcode = h.habitation_code )  where p.person_code=?";
            //System.out.println("query: "+query);
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,partAForm.getSadaremId());
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    partAForm.setPersoncode(rs.getString(1));
                    partAForm.setName(rs.getString(2));
                    partAForm.setRelationname(rs.getString(3));
                    partAForm.setAge(rs.getString(4));
                    partAForm.setTypeofdisability(rs.getString(5));
                    partAForm.setHouseno(rs.getString(6));
                    partAForm.setGender(rs.getString(7));
                    partAForm.setDisability(rs.getString(8));
                    partAForm.setDistrict(rs.getString(9));
                    partAForm.setMandal(rs.getString(10));
                    partAForm.setCategoryid(rs.getString(11));



                }

            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDetails", "CertificatesUploadDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CertificatesUploadDAO", "getDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDetails", "CertificatesUploadDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CertificatesUploadDAO", "getDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);
        }
        return partAForm;
    }

    public int storeCertificateUploaddetails(DataSource ds, PartAForm partAForm, String uploadtype, String filepath) throws SADAREMDBException, SQLException {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String query = null;
        String catid = null;
        int count = 0;
        int i = 0;
        int logInsertStatus=0;
        PreparedStatement ps = null;
        try {
            con = DBConnection.getConnection();
            stmt = con.createStatement();
            count = checkCategoryUpload(ds, partAForm.getCategoryid(), partAForm.getPersoncode(), uploadtype);
            catid = getCategoryid(ds, partAForm.getPersoncode(), uploadtype);
            if (catid != null) 
            {
            	
            	query ="insert into certificateuploaddetails_log(person_code,certificatetype,filepath,createddate,updateddate,systemip,loginid,categoryid,status,logged_date,logged_remarks)\n"+
            			"select person_code,certificatetype,filepath,createddate,updateddate,systemip,loginid,categoryid,status,current_timestamp,?  from certificateuploaddetails where person_code=? and certificatetype=? and status='Active'";
            	
            	 ps = con.prepareStatement(query);
            	 ps.setString(1, partAForm.getRemarks());
                 ps.setString(2, partAForm.getPersoncode());
                 ps.setString(3, uploadtype);
                 logInsertStatus = ps.executeUpdate();
            	
                 if(logInsertStatus>0)
                 {
                	query = "delete from certificateuploaddetails where person_code=? and certificatetype=? and status='Active'";
                	 ps = con.prepareStatement(query);
                	 ps.setString(1, partAForm.getPersoncode());
                     ps.setString(2, uploadtype);
                     logInsertStatus = ps.executeUpdate();
                	 
                     if(logInsertStatus>0)
                     { 
                	    query = "insert into certificateuploaddetails (person_code,certificatetype,filepath,createddate,updateddate,systemip,loginid,categoryid,status ) "
                         + "values('" + partAForm.getPersoncode() + "','" + uploadtype + "','" + filepath + "',getDate(),getDate(),'" +partAForm.getSystemip() + "','" + partAForm.getLoginid() + "','" + partAForm.getCategoryid() + "','Active')";
                       i = stmt.executeUpdate(query);
                     }
                     else
                     {
                    	 i=0;
                     }
                 }
                 else
                 {
                	 i=0;
                 }
            	
//                if (catid.equals("1") && partAForm.getCategoryid().equals("2")) 
//                {
//                    getInactiveAlreadyUpload(ds, partAForm.getPersoncode(), uploadtype);
//                    query = "insert into certificateuploaddetails (person_code,certificatetype,filepath,createddate,updateddate,systemip,loginid,categoryid,status ) "
//                            + "values('" + partAForm.getPersoncode() + "','" + uploadtype + "','" + filepath + "',getDate(),getDate(),'" +partAForm.getSystemip() + "','" + partAForm.getLoginid() + "','" + partAForm.getCategoryid() + "','Active')";
//                    i = stmt.executeUpdate(query);
//                } 
//                else if (catid.equals("1") && partAForm.getCategoryid().equals("3")) 
//                {
//                    getInactiveAlreadyUpload(ds, partAForm.getPersoncode(), uploadtype);
//                    query = "insert into certificateuploaddetails (person_code,certificatetype,filepath,createddate,updateddate,systemip,loginid,categoryid,status ) "
//                            + "values('" + partAForm.getPersoncode() + "','" + uploadtype + "','" + filepath + "',getDate(),getDate(),'" + partAForm.getSystemip() + "','" + partAForm.getLoginid() + "','" + partAForm.getCategoryid() + "','Active')";
//                    i = stmt.executeUpdate(query);
//                }
//                else if (catid.equals("2") && partAForm.getCategoryid().equals("3")) 
//                {
//                    getInactiveAlreadyUpload(ds, partAForm.getPersoncode(), uploadtype);
//                    query = "insert into certificateuploaddetails (person_code,certificatetype,filepath,createddate,updateddate,systemip,loginid,categoryid,status ) "
//                            + "values('" + partAForm.getPersoncode() + "','" + uploadtype + "','" + filepath + "',getDate(),getDate(),'" + partAForm.getSystemip() + "','" + partAForm.getLoginid() + "','" + partAForm.getCategoryid() + "','Active')";
//                    i = stmt.executeUpdate(query);
//                } 
//                else if (catid.equals("2") && partAForm.getCategoryid().equals("2")) 
//                {
//                    getInactiveAlreadyUpload(ds, partAForm.getPersoncode(), uploadtype);
//                    query = "insert into certificateuploaddetails (person_code,certificatetype,filepath,createddate,updateddate,systemip,loginid,categoryid,status ) "
//                            + "values('" + partAForm.getPersoncode() + "','" + uploadtype + "','" + filepath + "',getDate(),getDate(),'" + partAForm.getSystemip() + "','" + partAForm.getLoginid() + "','" + partAForm.getCategoryid() + "','Active')";
//                    i = stmt.executeUpdate(query);
//                } 
//                else 
//                {
//                    i = 0;
//                }
            } 
            else 
            {
                if (count > 0)
                {
                    //System.out.println("if");
                } else {
                    query = "insert into certificateuploaddetails (person_code,certificatetype,filepath,createddate,updateddate,systemip,loginid,categoryid,status ) "
                            + "values('" + partAForm.getPersoncode() + "','" + uploadtype + "','" + filepath + "',getDate(),getDate(),'" + partAForm.getSystemip() + "','" + partAForm.getLoginid() + "','" + partAForm.getCategoryid() + "','Active')";
                    i = stmt.executeUpdate(query);
                }
            }


        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "storeCertificateUploaddetails", "CertificatesUploadDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CertificatesUploadDAO", "storeCertificateUploaddetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "storeCertificateUploaddetails", "CertificatesUploadDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CertificatesUploadDAO", "storeCertificateUploaddetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);
        }
        return i;
    }

    public int countSADAREMID(DataSource ds, PartAForm partAForm) throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;
        int count = 0;
        try {
            con = DBConnection.getConnection();
            
            query = "select count(*) from tblPerson_Personal_Details  with (nolock) where person_code =? and status='Active'";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, partAForm.getPersoncode());
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    count = rs.getInt(1);
                }
            } else {
                count = 0;
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "storeCertificateUploaddetails", "CertificatesUploadDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CertificatesUploadDAO", "storeCertificateUploaddetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "storeCertificateUploaddetails", "CertificatesUploadDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CertificatesUploadDAO", "storeCertificateUploaddetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);
        }
        return count;
    }

    public int checkCategoryUpload(DataSource ds, String cateid, String sadaremid, String type) throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = null;
        int count = 0;
        try {
            con = DBConnection.getConnection();
            query = "select count(*) from Certificateuploaddetails "
                    + "where person_code=? "
                    + "and status='Active' and categoryid=? and certificatetype=?";
            ps = con.prepareStatement(query);
            ps.setString(1, sadaremid);
            ps.setString(2, cateid);
            ps.setString(3, type);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    count = rs.getInt(1);
                }
            } else {
                count = 0;
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkCategoryUpload", "CertificatesUploadDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CertificatesUploadDAO", "checkCategoryUpload");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkCategoryUpload", "CertificatesUploadDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CertificatesUploadDAO", "checkCategoryUpload");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(ps);
        }
        return count;
    }

    public String getCategoryid(DataSource ds, String sadaremid, String type) throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = null;
        String categoryid = null;
        try {
            con = DBConnection.getConnection();
            query = "select categoryid from Certificateuploaddetails "
                    + "where person_code=? "
                    + "and status='Active' and certificatetype=?";
            ps = con.prepareStatement(query);
            ps.setString(1, sadaremid);
            ps.setString(2, type);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    categoryid = rs.getString(1);
                }
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCategoryid", "CertificatesUploadDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CertificatesUploadDAO", "getCategoryid");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCategoryid", "CertificatesUploadDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CertificatesUploadDAO", "getCategoryid");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(ps);
        }
        return categoryid;
    }

    public int getInactiveAlreadyUpload(DataSource ds, String sadaremid, String type) throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = null;
        int success = 0;
        try {
            con = DBConnection.getConnection();
            query = "update certificateuploaddetails set status='Inactive' where person_code=? and certificatetype=? and status='Active'";
            ps = con.prepareStatement(query);
            ps.setString(1, sadaremid);
            //ps.setString(2, cateid);
            ps.setString(2, type);
            success = ps.executeUpdate();

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkCategoryUpload", "CertificatesUploadDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CertificatesUploadDAO", "checkCategoryUpload");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkCategoryUpload", "CertificatesUploadDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CertificatesUploadDAO", "checkCategoryUpload");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(ps);
        }
        return success;
    }

    public String getMobileNumber(DataSource ds, String sadaremid) throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = null;
        String mobile = null;
        try {
            con = DBConnection.getConnection();
            query = "select phone_no from dbo.tblPerson_Personal_Details  with (nolock) where phone_no is not null and person_code=?";
            ps = con.prepareStatement(query);
            ps.setString(1, sadaremid);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    mobile = rs.getString(1);
                }
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMobileNumber", "CertificatesUploadDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CertificatesUploadDAO", "getMobileNumber");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMobileNumber", "CertificatesUploadDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CertificatesUploadDAO", "getMobileNumber");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(ps);
        }
        return mobile;
    }

    public int insertSmsLogDetails(DataSource ds, String message, String Phoneno,String smsStatus,String service,String loginid,String systemip) throws SADAREMDBException, Exception {
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        int success = 0;
        try {
            con = DBConnection.getConnection();
            
            query = "insert into SmsLogs(MessageDescription,Messageto,LoginId,Systemip,createddate,updateddate,status,Service) values(?,"
                    + "?,?,?,getDate(),getDate(),"
                    + "?,?) ";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, message);
            pstmt.setString(2, Phoneno);
            pstmt.setString(3, loginid);
            pstmt.setString(4, systemip);
            pstmt.setString(5, smsStatus);
            pstmt.setString(6, service);
            success = pstmt.executeUpdate();
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertSmsLogDetails", "CertificatesUploadDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CertificatesUploadDAO", "insertSmsLogDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertSmsLogDetails", "CertificatesUploadDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CertificatesUploadDAO", "insertSmsLogDetails");
        } 
        finally 
        {
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }
        return success;
    }

    public ArrayList certificateUploadDetailsReport(DataSource ds, PartAForm partAForm, String fromdate1, String todate1) throws SADAREMDBException, SQLException {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        CallableStatement cstmt = null;
        HashMap map = new HashMap();

        ArrayList list = new ArrayList();
        int a1 = 0;
        int a2 = 0;
        int a3 = 0;
        int a21 = 0;
        int a22 = 0;
        int a23 = 0;
        try {
            con = DBConnection.getConnection();
            stmt = con.createStatement();
            Date fdate = new SimpleDateFormat("dd/MM/yyyy").parse(fromdate1);
            String fromdate = new SimpleDateFormat("MM/dd/yyyy").format(fdate);
            Date tdate = new SimpleDateFormat("dd/MM/yyyy").parse(todate1);
            String todate = new SimpleDateFormat("MM/dd/yyyy").format(tdate);
            cstmt = con.prepareCall("{Call [CertificateUploadReport](?,?)}");
            cstmt.setString(1, fromdate);
            cstmt.setString(2, todate);
            rs = cstmt.executeQuery();
            int sno = 1;
            if (rs != null) {
                while (rs.next()) {
                    map = new HashMap();
                    map.put("sno", sno);
                    map.put("district_id", rs.getString(1));
                    map.put("district_name", rs.getString(2));
                    map.put("assessed", rs.getString(3));
                    map.put("validationdone", rs.getString(4));
                    map.put("fromdate", fromdate1);
                    map.put("todate", todate1);
                    int a12 = (rs.getInt(3) - rs.getInt(4));
                    map.put("pending", a12);
                    a21 = a21 + rs.getInt(3);
                    a22 = a22 + rs.getInt(4);
                    a23 = a23 + a12;
                    map.put("totalassessed", a21);
                    map.put("totalvalidationdone", a22);
                    map.put("totalpending", a23);
                    list.add(map);
                    sno++;

                }
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "certificateUploadDetailsReport", "CertificatesUploadDAO", "DataBase");
             throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CertificatesUploadDAO", "certificateUploadDetailsReport");
        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
             int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "certificateUploadDetailsReport", "CertificatesUploadDAO", "Code");
             throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CertificatesUploadDAO", "certificateUploadDetailsReport");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);
        }
        return list;
    }
    
    public boolean uploadDocument(FileItem uploadFileName, String uploadPath, String fileName) 
    {
        String strDirectoytemp = null;
        boolean flag = false;
        try 
        {
            strDirectoytemp = uploadPath;

            if (strDirectoytemp != null && !"".equals(strDirectoytemp) && strDirectoytemp.length() > 0) // If directory is not exists it will create
            { 
                File directorytemp = new File(strDirectoytemp);
                if (!directorytemp.exists())
                {
                    directorytemp.mkdirs();
                }

                File fileToCreatetemp = new File(strDirectoytemp, fileName); // Copy the file into directory
                FileOutputStream fileOutStreamtemp = new FileOutputStream(fileToCreatetemp); // Write the file content into buffer

                if (uploadFileName.getSize() > 0) 
                {
                    fileOutStreamtemp.write(uploadFileName.get());
                    fileOutStreamtemp.flush();
                    fileOutStreamtemp.close();
                    flag = true;
                } 
                else
                {
                    flag = false;
                }
            } 
            else 
            {
                flag = false;
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return flag;
    }
    
}
