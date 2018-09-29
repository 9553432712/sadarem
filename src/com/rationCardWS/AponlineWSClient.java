package com.rationCardWS;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.dao.PartADAO;
import org.bf.disability.dao.PersonCodeCheckDAO;
import org.bf.disability.dao.TerritoryDAO;

//import app.common.util.Utilities;
public class AponlineWSClient {
//public static void main(String[] args) {
//	try {
//		//URL url= new URL("http://icfs2.ap.gov.in/icfs/ws/RationCardMutationWS.jws");
//                URL url= new URL("http://10.10.5.44/icfs/ws/RationCardMutationWS.jws");
////		URL url= new URL("http://125.21.85.29/aprcfv1/ws/RationCardMutationWS.jws");
//		getDetails(url);
////		syncNamcChangeDetails(url);
////		syncDOBChangeDetails(url);
////		syncAddressChangeDetails(url);
////		syncMutationDetails(url);
//	} catch (MalformedURLException e) {
//		e.printStackTrace();
//
//	}
//
//}

    public ArrayList getDetails(URL url, String rcid, HttpServletRequest request, String rationCardAddessChange) throws SADAREMDBException, SQLException {
        ArrayList list = new ArrayList();
        PersonCodeCheckDAO personCodeCheckDAO = new PersonCodeCheckDAO();
        int insertDetails = 0;
        DataSource ds = null;
        HttpSession session = request.getSession();
        String loginId = (String) session.getAttribute("loginid");
        String systemIp = request.getRemoteAddr();

        String urlStatus1 = url.toString();
        String totalUrl = null;
        String dataSeverDescription = "";
        RcRespBean response = null;

        //String urlStatus = url.toString();




        if (urlStatus1.equals(CommonConstants.Url1)) {

            totalUrl = urlStatus1.substring(7, 21);
        }


        try {

            ds = JNDIDataSource.getConnection();
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }

            if (rcid != null) {
                rcid = rcid.toUpperCase();
            }

            Map<String, String> rationCardData = null;
            String dis = null;
            String familyHead = null;
            boolean isHeadExist = false;
            try {
                //String Name = null;
                RationCardFetchServiceServiceLocator locator = new RationCardFetchServiceServiceLocator();

                response = (RcRespBean) locator.getRationCardFetchServicePort(url).getePDSRationCardDetails(rcid, "11169d3abc20724bb857cc5ea59e3c42", "12");
            } catch (Exception e) {
                String ConnectionExpection = "ServerDown1";
                HashMap listData = new HashMap();
                listData.put("expection", ConnectionExpection);
                list.add(listData);
                return list;
            }
            if (response != null && response.getRationCardDetails() != null) {
                String sessionDist = "";
                if (request.getSession() != null && request.getSession().getAttribute("districtId") != null) {
                    sessionDist = request.getSession().getAttribute("districtId").toString();
                }
                if (response.getRationCardDetails().getCarddetails() != null) {

                    familyHead = response.getRationCardDetails().getCarddetails().getCardHolderName();
                    if (response.getRationCardDetails().getCarddetails().getDistrict() != null) {

                        dis = TerritoryDAO.getSADAREMDistrictId(ds, response.getRationCardDetails().getCarddetails().getDistrict());
                    }
                } else if (response.getRationCardDetails().getMemberdetails() != null
                        && response.getRationCardDetails().getMemberdetails().length > 0
                        && response.getRationCardDetails().getMemberdetails()[0].getDistrict() != null) {
                    dis = TerritoryDAO.getSADAREMDistrictId(ds, response.getRationCardDetails().getMemberdetails()[0].getDistrict());
                }
//                dis = "12";

                String webDis = dis;

                if (webDis == null) {
                    webDis = rcid.substring(3, 5);
                }
                if (rationCardAddessChange != null && !rationCardAddessChange.equals("") && rationCardAddessChange.length() > 0
                        && rationCardAddessChange.equals(CommonConstants.RATIONCARDADDRESSCHANGE)) {

                    rationCardData = new HashMap();
                    rationCardData.put("district", webDis);
                    list.add(rationCardData);
                } else {

                    if (!sessionDist.equalsIgnoreCase("") && webDis != null && !webDis.equals(sessionDist)) {
                        request.setAttribute("districtNotMatch", webDis);

                    } else {
                        // Add member details to list
                        if (response.getRationCardDetails().getMemberdetails() != null) {
                            for (int i = 0; i < response.getRationCardDetails().getMemberdetails().length; i++) {

                                rationCardData = new HashMap();

                                if (response.getRationCardDetails().getMemberdetails()[i].getRelation() != null
                                        && !response.getRationCardDetails().getMemberdetails()[i].getRelation().equalsIgnoreCase("")
                                        && response.getRationCardDetails().getMemberdetails()[i].getRelation().equalsIgnoreCase("SELF")) {
                                    isHeadExist = true;
                                }
                                String memberName = null;
                                memberName = response.getRationCardDetails().getMemberdetails()[i].getMemberName();
                                rationCardData.put("memberName", memberName);
                                rationCardData.put("age", Integer.toString(response.getRationCardDetails().getMemberdetails()[i].getAge()));
                                rationCardData.put("dob", "");
                                if (response.getRationCardDetails().getCarddetails().getCardHolderName() != null) {
                                    rationCardData.put("relationName", response.getRationCardDetails().getCarddetails().getCardHolderName());
                                } else {
                                    rationCardData.put("relationName", "");
                                }
                                rationCardData.put("gender", Integer.toString(response.getRationCardDetails().getMemberdetails()[i].getGender()));
                                rationCardData.put("district", webDis);
                                rationCardData.put("aadharcardNo", response.getRationCardDetails().getMemberdetails()[i].getUid());
                                String slNo = null;
                                if (response.getRationCardDetails().getMemberdetails()[i].getSlNo() != 0) {
                                    slNo = Long.toString(response.getRationCardDetails().getMemberdetails()[i].getSlNo());
                                }
                                if (slNo != null) {
                                    if (slNo.length() == 13) {
                                        rationCardData.put("slNo", slNo.substring(slNo.length() - 1, slNo.length()));
                                    } else if (slNo.length() == 14) {
                                        rationCardData.put("slNo", slNo.substring(slNo.length() - 2, slNo.length()));
                                    } else {
                                        rationCardData.put("slNo", "");
                                    }
                                }
                                list.add(rationCardData);
                            }
                        }
                        //Add family head to list
                        if (!isHeadExist && response.getRationCardDetails().getCarddetails() != null) {
                            rationCardData = new HashMap();
                            rationCardData.put("memberName", familyHead);
                            rationCardData.put("slNo", "1");
                            rationCardData.put("age", Integer.toString(response.getRationCardDetails().getCarddetails().getAge()));
                            rationCardData.put("dob", "");
                            if (response.getRationCardDetails().getCarddetails().getRelationName() != null) {
                                rationCardData.put("relationName", response.getRationCardDetails().getCarddetails().getRelationName());
                            } else {
                                rationCardData.put("relationName", "");
                            }
                            rationCardData.put("gender", Integer.toString(response.getRationCardDetails().getCarddetails().getGender()));
                            rationCardData.put("district", webDis);
                            rationCardData.put("aadharcardNo", "");
                            list.add(rationCardData);


                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            String ConnectionExpection = "ServerDown";
            //System.out.println("exception-- urlStatus1--" + urlStatus1 + "CommonConstants.Url1--" + CommonConstants.Url1);
            ConnectionExpection = "ServerDown1";
            HashMap listData = new HashMap();
            listData.put("expection", ConnectionExpection);
            list.add(listData);

            try {
                insertDetails = personCodeCheckDAO.insertRationCardStatus(ds, rcid, totalUrl, dataSeverDescription, loginId, systemIp, ConnectionExpection);

            } catch (SQLException sqlEx) {
                int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDetails", "AponlineWSClient", "code");
                throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AponlineWSClient", "getDetails");
            }
            //throws  new SADAREMException();
            int exResult = ExceptionDAO.saveException(ds, e.toString(), "getDetails", "AponlineWSClient", "code");
            throw new SADAREMDBException(CommonConstants.DBERROR, e.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AponlineWSClient", "getDetails");

        }
        if (list != null && list.size() > 0) {
            Collections.sort(list, new Comparator<Map<String, String>>() {

                public int compare(Map<String, String> obj1, Map<String, String> obj2) {
                    return obj1.get("slNo").compareTo(obj2.get("slNo"));
                }
            });
        }


        return list;

    }

    public ArrayList getDetailsRationCount(String rcid, int count, DataSource ds, HashMap listData) throws SADAREMDBException {

        ArrayList list = new ArrayList();
        PersonCodeCheckDAO personCodeCheckDAO = new PersonCodeCheckDAO();

        try {

            HashMap rationCardData = new HashMap();
            String slNo = null;
            String age = null;
            String dob = null;
            String gender = null;
            String relationName = null;
            String memberName = null;
            String aadharCardNo = null;

            ArrayList personList = new ArrayList();
            //String familyName = "";
            //String nameData = "";
            if (listData.get("memberName") != null) {
                memberName = listData.get("memberName").toString();
            } else {
                memberName = "-";
            }

            if (listData.get("gender") != null) {
                gender = listData.get("gender").toString();
            } else {
                gender = "-";
            }

            if (listData.get("slNo") != null) {
                slNo = listData.get("slNo").toString();
            } else {
                slNo = "-";
            }
            if (listData.get("age") != null) {
                age = listData.get("age").toString();
            } else {
                age = "0";
            }

            if (listData.get("dob") != null) {
                dob = listData.get("dob").toString();
            } else {
                dob = "-";
            }

            if (listData.get("relationName") != null) {
                relationName = listData.get("relationName").toString();
            } else {
                relationName = "-";
            }

            if (listData.get("aadharcardNo") != null && !listData.get("aadharcardNo").equals("")) {
                aadharCardNo = listData.get("aadharcardNo").toString();
            } else {
                aadharCardNo = "-";
            }


            if (count == 0) {
                rationCardData.put("pensionNo", "-");
                rationCardData.put("personCode", "-");
                rationCardData.put("assStatus", "NotCompleted");
                rationCardData.put("PersonName", "-");
                rationCardData.put("imgs", "<img src=\"./DisabilityUITG/images/cross.JPG\" height=\"18\">");
                rationCardData.put("webServiceGo", "<a href=\"./MemberDetails.do?mode=getWebDetails&rCardSlno=" + slNo
                        + "&name=" + memberName
                        + "&age=" + age
                        + "&gender=" + gender
                        + "&district=" + listData.get("district").toString() + "&rationCardNo=" + rcid + "&aadharCardNo=" + aadharCardNo
                        + "&relationName=" + relationName + "\"><font color=red><b>Go</b></font></a>");

            } else {
                personList = personCodeCheckDAO.checkPersonDetails(ds, rcid, slNo);

                if (!personList.get(0).equals("") && personList.get(0) != null) {
                    rationCardData.put("pensionNo", personList.get(0));

                } else {
                    rationCardData.put("pensionNo", "-");
                }

                rationCardData.put("personCode", personList.get(1));
                rationCardData.put("assStatus", personList.get(2));
                rationCardData.put("PersonName", personList.get(3));
                rationCardData.put("imgs", "<img src=\"./DisabilityUITG/images/right.JPG\" height=\"18\">");
                rationCardData.put("webServiceGo", "<font color=green><b>Done</b></font></a>");

            }
            rationCardData.put("slNo", slNo);
            rationCardData.put("memberName", memberName);
            rationCardData.put("age", age);
            rationCardData.put("dob", dob);
            rationCardData.put("relationName", relationName);
            rationCardData.put("district", listData.get("district").toString());


            list.add(rationCardData);



        } catch (Exception e) {
            e.printStackTrace();
//            String ConnectionExpection = "ServerDown";
//            HashMap listData = new HashMap();
//            insertDetails = personCodeCheckDAO.insertRationCardStatus(ds, rcid, totalUrl, loginId, systemIp, ConnectionExpection);
//            listData.put("expection", ConnectionExpection);
//
//            list.add(listData);
//
            //throws  new SADAREMException();
        }

        return list;

    }

//added by kavya
    public String getRationcardSerialNumbers(DataSource ds, URL url1, URL url2, String rationCard, String serialNo, HttpServletRequest request) throws SADAREMDBException {
        String validStatus = "ERROR";

        String expection = null;
        ArrayList rationCardWS1List = new ArrayList<Object>();
        ArrayList rationCardWS2List = new ArrayList<Object>();

        String rationcardtype = "";
        try {

            rationCardWS1List = getDetails(url1, rationCard.toUpperCase(), request, "");

            rationcardtype = rationCard.substring(0, 3);
            Iterator errorIt = rationCardWS1List.iterator();
            while (errorIt.hasNext()) {
                HashMap listData = (HashMap) errorIt.next();
                expection = (String) listData.get("expection");
            }



            if ((expection != null && expection.equals("ServerDown1")) && (!rationcardtype.equalsIgnoreCase("RAP"))) {
                validStatus = "Civil Supply Website is down. Please try after some time.";
            } else {


                if (rationCardWS1List != null && rationCardWS1List.size() > 0) {
                    rationCardWS1List = rationCardWS1List;
                } else if (rationcardtype.equalsIgnoreCase("RAP")) {
                    PartADAO partADAO = new PartADAO();
                    HttpSession session = request.getSession();
                    if (session.getAttribute("districtId") != null) {
                        validStatus = partADAO.getRAPTAPRationCardSerialNumberStatus(ds, rationCard, serialNo, session.getAttribute("districtId").toString());

                    }
                } else {
                    validStatus = "RationCard not available in Civil Supplies Web Site";
                }


            }
//
//
//
//
//
//            if ((expection != null && expection.equals("ServerDown1")) && (!rationcardtype.equalsIgnoreCase("RAP"))) {
//
//                if ((expection != null && expection.equals("ServerDown2")) || (rationCardWS2List.size() == 0 && (rationcardtype.equalsIgnoreCase("RAP")))) {
//                    PartADAO partADAO = new PartADAO();
//                    HttpSession session = request.getSession();
//                    if (session.getAttribute("districtId") != null) {
//                        validStatus = partADAO.getRAPTAPRationCardSerialNumberStatus(ds, rationCard, serialNo, session.getAttribute("districtId").toString());
//
//                    } else {
//
//                        validStatus = "Civil Supply Website is down. Please raise the request after some time.";
//                    }
//
//                } else {
//                    if (rationCardWS2List != null && rationCardWS2List.size() > 0) {
//                        rationCardWS1List = rationCardWS2List;
//                    } else {
//                        validStatus = "No Details found For this Ration card Number in Civil Supply Website";
//                    }
//                }
//
//            } else {
//                if (rationCardWS1List != null && rationCardWS1List.size() > 0) {
//                    rationCardWS1List = rationCardWS1List;
//                } else {
//                    validStatus = "No Details found For this Ration card Number in Civil Supply Website";
//                }
//            }

            for (int x = 0; x < rationCardWS1List.size(); x++) {

                Map m = (Map) rationCardWS1List.get(x);

                if (m.get("slNo") != null && serialNo != null && m.get("slNo").toString().equals(serialNo)) {
                    validStatus = "SUCCESS";
                }

            }

        } catch (Exception ex) {
            ex.printStackTrace();


        }
        return validStatus;
    }
}
