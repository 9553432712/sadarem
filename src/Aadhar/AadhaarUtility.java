package Aadhar;

import java.io.StringReader;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.ecentric.servicemodels.AadhaarProfile;
import com.tcs.aadhaar.services.ServicesStub;
import com.tcs.aadhaar.services.ServicesStub.GetAadhaarInfo;
import com.tcs.aadhaar.services.ServicesStub.GetAadhaarInfoResponse;
import com.tcs.sadarem.util.CommonUtility;


public class AadhaarUtility 
{
	
	
	public static AadhaarProfile getAadhaarProfileByUID(String strUID)
	{
		AadhaarProfile resultHashMap = new AadhaarProfile() ;
		try
		{
			String strUIDDetails = getAadharDetailsByUid(strUID,resultHashMap);
			if(strUIDDetails!=null)
			{
					HashMap aadharData =(HashMap)readStrXMLDataReturnHashMap(strUIDDetails);
					
					try
					{
					
					resultHashMap.setBase64file(CommonUtility.checkNullObj(aadharData.get("BASE64FILE")));
					resultHashMap.setBuildingName(CommonUtility.checkNullObj(aadharData.get("BUILDINGNAME")));
					resultHashMap.setCareof(CommonUtility.checkNullObj(aadharData.get("CAREOF")));
					resultHashMap.setDistrict(CommonUtility.checkNullObj(aadharData.get("DISTRICT")));
					resultHashMap.setDistrict_name(CommonUtility.checkNullObj(aadharData.get("DISTRICT_NAME")));
					resultHashMap.setDob(CommonUtility.checkNullObj(aadharData.get("DOB")));
					resultHashMap.setEid(CommonUtility.checkNullObj(aadharData.get("EID")));
					resultHashMap.setGender(CommonUtility.checkNullObj(aadharData.get("GENDER")));
					resultHashMap.setMandal(CommonUtility.checkNullObj(aadharData.get("MANDAL")));
					resultHashMap.setMandal_name(CommonUtility.checkNullObj(aadharData.get("MANDAL_NAME")));
					resultHashMap.setName(CommonUtility.checkNullObj(aadharData.get("NAME")));
					resultHashMap.setPhoneNo(CommonUtility.checkNullObj(aadharData.get("PHONENO")));
					resultHashMap.setPincode(CommonUtility.checkNullObj(aadharData.get("PINCODE")));
					resultHashMap.setStatecode(CommonUtility.checkNullObj(aadharData.get("STATECODE")));
					resultHashMap.setStatus(CommonUtility.checkNullObj(aadharData.get("STATUS")));
					resultHashMap.setStreet(CommonUtility.checkNullObj(aadharData.get("STREET")));
					resultHashMap.setUid(CommonUtility.checkNullObj(aadharData.get("UID")));
					resultHashMap.setVillage(CommonUtility.checkNullObj(aadharData.get("VILLAGE")));
					resultHashMap.setVillage_name(CommonUtility.checkNullObj(aadharData.get("VILLAGE_NAME")));
					resultHashMap.setSrdhwstxn(CommonUtility.checkNullObj(aadharData.get("SRDHWSTXN")));
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return resultHashMap;
	}
	
	public static HashMap getAadharInHashMapByUID(String strUID,AadhaarProfile aadhaarProfile)
	{
		HashMap resultHashMap = new HashMap();
		try
		{
			String strUIDDetails = getAadharDetailsByUid(strUID,aadhaarProfile);
			
			resultHashMap =(HashMap)readStrXMLDataReturnHashMap(strUIDDetails);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return resultHashMap;
	}
	

	public static ArrayList getAadharInArrayListByUID(String strUID,AadhaarProfile aadhaarProfile)
	{
		ArrayList resultList = new ArrayList();
		try
		{
			String strUIDDetails = getAadharDetailsByUid(strUID,aadhaarProfile);
			
			resultList =(ArrayList)readStrXMLDataReturnArrayList(strUIDDetails);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return resultList;
	}
	
	private static String getAadharDetailsByUid(String strUID,AadhaarProfile aadharProfile)
	{
		String strXMLResult ="";
		try
		{
			ServicesStub stub = new ServicesStub();
			GetAadhaarInfo setData = new GetAadhaarInfo();
			
			/*	Options opt = stub._getServiceClient().getOptions();
			
			HttpTransportProperties.Authenticator authenticator = new HttpTransportProperties.Authenticator();
	        List<String> auth = new ArrayList<String>();
	        auth.add(Authenticator.BASIC);
	        authenticator.setAuthSchemes(auth);
	        authenticator.setUsername("srdhws");
	        authenticator.setPassword("srdhws@123");
	        authenticator.setHost("10.10.23.118");
	        authenticator.setPort(8010);
	        authenticator.setPreemptiveAuthentication(true);
	        opt.setProperty(HTTPConstants.AUTHENTICATE, authenticator);
	        stub._getServiceClient().setOptions(opt);*/
	        
	        
	        setData.setUID(strUID);
	        
	        GetAadhaarInfoResponse resp= stub.getAadhaarInfo(setData);
	        strXMLResult = resp.get_return();

		}
		catch(RemoteException re) 
		{
			aadharProfile.setError_msg("<b>01) Alert !<b> "+re.getLocalizedMessage());
			re.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
			aadharProfile.setError_msg("<b>02) Alert !<b> "+e.getLocalizedMessage());
			
		}
	return strXMLResult;
	}
	
	private static HashMap readStrXMLDataReturnHashMap(String xmlStr)
	{
		HashMap resultHashMap = new HashMap();
		try
		{
			
				if(xmlStr!=null && xmlStr.length()>0)
				{
					Document dom = convertStringToDocument(xmlStr);
					
					Element docEle = dom.getDocumentElement();
					
					//get a nodelist of elements
					NodeList nl = docEle.getElementsByTagName("void");
					//System.out.println("nl : "+nl.getLength());
					
					String hashkey 	 = "";
					String hashValue = "";
						if(nl != null && nl.getLength() > 0) 
						{
							for(int i = 0 ; i < nl.getLength();i++) 
							{
								//get the string element
								Element el = (Element)nl.item(i);
								//get the string object
						
									hashkey = (el.getAttributes().getNamedItem("property").getNodeValue()).trim();
									hashValue = getTextValue(el,"string");
									
									/*if(hashValue.trim().equals("101"))
									{
										hashValue = "NA";
									}*/
									resultHashMap.put(hashkey.toUpperCase(), hashValue);
									
									//System.out.println(hashkey+" : "+hashValue);
							
							}
						}
						
				}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return resultHashMap;
	}
	
	private static ArrayList readStrXMLDataReturnArrayList(String xmlStr)
	{
		ArrayList resultHashMap = new ArrayList();
		try
		{
			if(xmlStr!=null && xmlStr.length()>0)
			{
			
					Document dom = convertStringToDocument(xmlStr);
					
					Element docEle = dom.getDocumentElement();
					
					//get a nodelist of elements
					NodeList nl = docEle.getElementsByTagName("void");
					//System.out.println("nl : "+nl.getLength());
					
					String hashKey 	 = "";
					String hashValue = "";
						if(nl != null && nl.getLength() > 0) 
						{
							ArrayList tempList = new ArrayList();
							for(int i = 0 ; i < nl.getLength();i++) 
							{
								tempList = new ArrayList();
								
								//get the string element
								Element el = (Element)nl.item(i);
								//get the string object
						
									hashKey = (el.getAttributes().getNamedItem("property").getNodeValue()).trim();
									hashValue = getTextValue(el,"string");
									
									/*if(hashValue.trim().equals("101"))
									{
										hashValue = "NA";
									}*/
									tempList.add(hashKey.toUpperCase());
									tempList.add(hashValue);
									resultHashMap.add(tempList);
							}
						}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return resultHashMap;
	}
	
	
	private static Document convertStringToDocument(String xmlStr)
	{
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
	    DocumentBuilder builder;  
	    try 
	    {  
	        builder = factory.newDocumentBuilder();  
	        Document doc = builder.parse( new InputSource( new StringReader( xmlStr ) ) ); 
	        return doc;
	    } 
	    catch (Exception e) 
	    {  
	        e.printStackTrace();  
	    } 
	    return null;
	}	
	
	
	private static String getTextValue(Element ele, String tagName)
	{
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if(nl != null && nl.getLength() > 0) 
		{
		Element el = (Element)nl.item(0);
		textVal = el.getFirstChild().getNodeValue();
		}
		
	return textVal;
	}
	
	public static String getAadhaarErrorCodeStatus(String statusCode)
	{
		String result ="<span class=\"glyphicon glyphicon-remove-sign\" style=\"color: #009688;\">";
		try
		{
			if(statusCode.trim().length()>0 && (statusCode.equals("100") ||statusCode.equals("101") ))
			{
				result ="<span class=\"glyphicon glyphicon-ok-sign\" style=\"color: #5cb85c;\">";
			}
			else
			{
				result ="<span class=\"glyphicon glyphicon-remove-sign\" style=\"color: #F44336 ;\">";
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return result;
	}
	

	
	public static String getAadhaarStateNameByStateCode(String stateCode)
	{
		String result ="";
		try
		{
			if(stateCode.trim().equals("1") )
			{
				result ="Andhra Pradesh";
			}
			else if(stateCode.trim().equals("2") )
			{
				result ="Telangana";
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static String getAadhaarBelongstoTelangan(String stateCode)
	{
		String result ="<span class=\"glyphicon glyphicon-remove-sign\" style=\"color: #009688;\">";
		try
		{
			if(stateCode.trim().equals("2") )
			{
				result ="<span class=\"glyphicon glyphicon-ok-sign\" style=\"color: #5cb85c;\">";
			}
			else
			{
				result ="<span class=\"glyphicon glyphicon-remove-sign\" style=\"color: #F44336 ;\">";
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return result;
	}

	
	public static String getAadhaarPhotoStatus(String photoTxt)
	{
		String result ="<span class=\"glyphicon glyphicon-remove-sign\" style=\"color: #009688;\">";
		try
		{
			if(photoTxt.trim().length()>0 )
			{
				result ="<span class=\"glyphicon glyphicon-ok-sign\" style=\"color: #5cb85c;\">";
			}
			else
			{
				result ="<span class=\"glyphicon glyphicon-remove-sign\" style=\"color: #F44336 ;\">";
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return result;
	}


	public static String getAadhaarTagStatus(ArrayList sadaremList)
	{
		String result ="<span class=\"glyphicon glyphicon-remove-sign\" style=\"color: #009688;\">";
		try
		{
			if(sadaremList.size()>0 )
			{
				result ="<span class=\"glyphicon glyphicon-remove-sign\" style=\"color: #F44336 ;\">";
			}
			else
			{
				result ="<span class=\"glyphicon glyphicon-ok-sign\" style=\"color: #5cb85c;\">";
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static String getSADAREMTagedtoAadhaar(ArrayList sadaremList)
	{
		String result ="";
		try
		{
			if(sadaremList.size()>0)
			{
				ArrayList tempList = new ArrayList();
				for(int looper=0;looper<sadaremList.size();looper++)
				{
					tempList = (ArrayList)sadaremList.get(looper);
					result +="<span>"+tempList.get(0)+" ("+tempList.get(1)+")</span><br/>";
				}
			}
			else
			{
				result="Aadhaar Not Tagged to a SADAREM ID";
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	public static boolean getAadhaarFormEntryStatus(String statusCode,String stateCode,ArrayList sadaremList)
	{
		boolean result = false;
		try
		{
			if(
				(statusCode.trim().equals("100") || statusCode.trim().equals("101") ) &&
				stateCode.trim().equals("2") && 
				sadaremList.size()==0
			  )
			{
				result = true;
			}
			else
			{
				result = false;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return result;
	}

}