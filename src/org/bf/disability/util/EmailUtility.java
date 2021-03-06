package org.bf.disability.util;

import java.text.SimpleDateFormat;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.dto.IssueRaiseApprovalDTO;
import org.bf.disability.dto.NameRelationChangeDTO;
import org.bf.disability.form.IssueRaiseApprovalForm;
//import org.aponline.eservices.constants.CommonConstants;

public class EmailUtility {

    public static boolean SendEmail(
            ArrayList<InternetAddress> ToMailsList, ArrayList<InternetAddress> CCMailsList, ArrayList<InternetAddress> BCCMailsList, String subject, String message, String from) {
        Boolean Msg = true;
        try {
            Properties properties = new Properties();
            properties.put("mail.smtp.host", "10.100.100.17");
            properties.put("mail.smtp.port", "25");
            properties.put("mail.smtp.starttls.enable", "true");
            Session session = Session.getInstance(properties, new Authenticator() {

                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(CommonConstants.Email_ID, CommonConstants.Email_Password);
                }
            });
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("SADAREMDevTeam(no-reply@sadarem.telangana.gov.in)"));

            InternetAddress[] iaarrayTo = new InternetAddress[ToMailsList.size()];
            InternetAddress[] iaarrayCC = new InternetAddress[CCMailsList.size()];
            InternetAddress[] iaarrayBCC = new InternetAddress[BCCMailsList.size()];

            int count = 0;
            for (InternetAddress ia : ToMailsList) {
                iaarrayTo[count] = ia;
                count++;
            }

            count = 0;
            for (InternetAddress ia : CCMailsList) {
                iaarrayCC[count] = ia;
                count++;
            }

            count = 0;
            for (InternetAddress ia : BCCMailsList) {
                iaarrayBCC[count] = ia;
                count++;
            }

            msg.setRecipients(Message.RecipientType.TO, iaarrayTo);
            msg.setRecipients(Message.RecipientType.CC, iaarrayCC);
            msg.setRecipients(Message.RecipientType.BCC, iaarrayBCC);

            msg.setSubject(subject);
            // msg.setText(message);
            msg.setContent(message, "text/html");
            session.setDebug(true);
            Transport tr = session.getTransport("smtp");
            tr.connect();
            msg.saveChanges(); // don't forget this
            tr.sendMessage(msg, msg.getAllRecipients());
            tr.close();
        } catch (Exception e) {
            e.printStackTrace();
            Msg = false;
        }
        return Msg;
    }

//    public Boolean sendEmail(ArrayList<InternetAddress> Recipients, String subject, String message) throws MessagingException {
//        Boolean Msg = true;
//        try {
//            Properties properties = new Properties();
//             properties.put("mail.smtp.host", "10.100.100.17");
//            properties.put("mail.smtp.port", "25");
//            properties.put("mail.smtp.starttls.enable", "true");
//
//            Session session = Session.getInstance(properties, new Authenticator() {
//
//                @Override
//                protected PasswordAuthentication getPasswordAuthentication() {
//                    return new PasswordAuthentication(CommonConstants.Email_ID, CommonConstants.Email_Password);
//                }
//            });
//
//
//            Message msg = new MimeMessage(session);
//           // msg.setFrom(new InternetAddress("SADAREM Dev Team(No reply: Autogenerated Mail)"));
//            InternetAddress[] iaarray = new InternetAddress[Recipients.size()];
//            int count = 0;
//            for (InternetAddress ia : Recipients) {
//                iaarray[count] = ia;
//                count++;
//            }
//            msg.setRecipients(Message.RecipientType.TO, iaarray);
//            msg.setSubject(subject);
//            // msg.setText(message);
//            msg.setContent(message, "text/html");
//            session.setDebug(true);
//            Transport tr = session.getTransport("smtp");
//            tr.connect();
//            msg.saveChanges(); // don't forget this
//            tr.sendMessage(msg, msg.getAllRecipients());
//            tr.close();
//        } catch (Exception e) {
//            Msg = false;
//        }
//        return Msg;
//    }
    public String IntimationForVerificationMailFormat(String distName, String campName, int dataEnter, String certificateCount, String partAEnterCount, ArrayList list) {
        String Msg = null;

        Msg = "<html><head><title>SADAREM</title></head>"
                + "<body>"
                + "<table width='80%' align='center' border='1' cellpadding='0' cellspacing='0'>"
                + "             <tr>"
                + "                 <td width='40%' style='font-weight: bold;'>"
                + "                     District Name: "
                + "                 </td>"
                + "                 <td width='40%'>" + distName + "</td>"
                + "             </tr>"
                + "             <tr>"
                + "                 <td style='font-weight: bold;'>"
                + "                     Camp Name: "
                + "                 </td>"
                + "                 <td>" + campName + "</td>"
                + "             </tr>"
                + "             <tr>"
                + "                 <td style='font-weight: bold;'>"
                + "                     Date Enter by Camp Incharge: "
                + "                 </td>"
                + "                 <td>" + dataEnter + "</td>"
                + "             </tr>"
                + "             <tr>"
                + "                 <td style='font-weight: bold;'>"
                + "                     Certification Generated: "
                + "                 </td>"
                + "                 <td>" + certificateCount + "</td>"
                + "             </tr>"
                + "             <tr>"
                + "                 <td style='font-weight: bold;'>"
                + "                     Part A Entered: "
                + "                 </td>"
                + "                 <td>" + partAEnterCount + "</td>"
                + "             </tr>"
                + "           <table> </br>"
                + "                     <table border='1' align='center' width='80%' cellpadding='0' cellspacing='0'>"
                + "                        <tr>"
                + "                              <th>S.No.</th>"
                + "                              <th>Disabilty Name</th>"
                + "                              <th>Doctor's Assessed Count</th>"
                + "                              <th>Doctor's RegNumber</th>"
                + "                              <th>Doctor's Name </th>"
                + "                              <th>Doctor's Designation </th>"
                + "                          </tr>";
        for (int k = 0; k < list.size(); k++) {

            ArrayList list2 = (ArrayList) list.get(k);
            Msg += "<tr>";
            for (int j = 0; j < list2.size(); j++) {
                Msg += "<td> " + list2.get(j) + "";
                Msg += "</td>";
            }
            Msg += "</tr>";
        }
        Msg += " </table>"
                + "</body></html>";
        return Msg;
    }

    public String MailFormatForFileDownload(String username, String password, String filepath, String fileName, String date, String mailSubjectDetsails, String mailBodyDetails) {
        String Msg = null;
        /*
         *  <tr>

        <td>Subect: <tr><td>Subect:Subject Information</td>
        </tr>

        Data Format
        </td>
        </tr>
         */
        Msg = " <html>"
                + " <body>"
                + " <table width='100%' align='left' border='0'>"
                + " <tr>"
                + "<td>"
                + "<h4>"
                + "Subect:" + mailSubjectDetsails
                + "</h4>"
                + "</td>"
                + "   <tr height='35'>"
                + " 	<td valign='top'>"
                + " 		Dear All,"
                + " 	</td>"
                + "   </tr>"
                + "   <tr>"
                + " 	<td>"
                + " 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Here I am sending "
                + " 			a file and credentials for SADAREM and  " + mailBodyDetails + ""
                + " 	</td>"
                + "   </tr>"
                + "   <tr>"
                + " 	<td>"
                + " 		<table width='80%' align='left' border='0'>"
                + " 			<tr>"
                + " 				<td width='10%'></td>"
                + " 				<td width='20%'>Sadarem URL</td>"
                + " 				<td width='2%'>:</td>"
                + " 				<td width='38%'>www.sadarem.telangana.gov.in</td> "
                + "			</tr> "
                + "			<tr>"
                + " 				<td></td>"
                + " 				<td>User Name</td>"
                + " 				<td>:</td>"
                + " 				<td>" + username + "</td>"
                + " 			</tr>"
                + " 			<tr>"
                + " 				<td></td>"
                + " 				<td>Password</td>"
                + "  				<td>:</td>"
                + " 				<td>" + password + "</td>"
                + " 			</tr>"
                + " 			<tr>"
                + " 				<td></td>"
                + " 				<td>File Download Path</td>"
                + " 				<td>:</td>"
                + "				<td>" + filepath + "</td>"
                + " 			</tr>"
                + " 		</table>"
                + " 	</td>"
                + "   </tr>"
                + "   <tr>"
                + " 	<td>"
                + " 		&nbsp;"
                + " 	</td>"
                + "   </tr>"
                + "   <tr>"
                + " 	<td align='center'>"
                + " 		<h3>"
                + " 		File Name:" + fileName + ""
                + " 		</h3>"
                + " 	</td>"
                + "   </tr>"
                + "   <tr>"
                + " 	<td>"
                + " 		&nbsp;"
                + " 	</td>"
                + "   </tr>"
                //                + "     <tr>"
                //                + " 	<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; "
                //                + "          " + mailBodyDetails + ""
                //                + " 	</td>"
                //                + "   </tr>"
                + "   <tr>"
                + " 	<td>"
                + " 		&nbsp;"
                + " 	</td>"
                + "   </tr>"
                + "   <tr>"
                + " 	<td>"
                + " 		&nbsp;"
                + " 	</td>"
                + "   </tr>"
                + "   <tr>"
                + " 	<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; "
                + " 		Note:  This file is available upto " + date + "."
                + " 	</td>"
                + "   </tr>"
                + "   <tr>"
                + " 	<td>"
                + " 		&nbsp;"
                + " 	</td>"
                + "   </tr>"
                + "   <tr>"
                + " 	<td>"
                + " 		Thanks & Regards,"
                + " 	</td> "
                + "  </tr>"
                + "   <tr>"
                + " <td>"
                + " 		SADAREM Developement Team,"
                + " 	</td>"
                + "   </tr>"
                + "   <tr>"
                + " 	<td>"
                + " 		Hyderabad."
                + " 	</td>"
                + "   </tr>"
                + "   <tr>"
                + "        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                + " 		Note: This is a system-generated e-mail. Please do not respond to this mail."
                + " 	</td>"
                + "   </tr>"
                + " </table>"
                + " </body>"
                + " </html>";
        return Msg;

    }

    public String emailNameRelationName(ArrayList emailList, String emailNameRelationName, ArrayList oldemailNameRelationName) {
        String Msg = null;
        /*
         *  <tr>

        <td>Subect: <tr><td>Subect:Subject Information</td>
        </tr>

        Data Format
        </td>
        </tr>
         */
        String oldName = null;
        String oldName1 = null;
        String newName = null;
        boolean name = false;
        boolean relatonName = false;
        String slah = null;
        String oldRelationName = null;
        String oldRelationName1 = null;
        String newRelationName = null;
        String nameChanges = "";
        String relationNameChanges = "";
        boolean nameData = false;
        boolean relatonNameData = false;
        String regDate = null;
        boolean newDataName = false;
        boolean relatonNewName = false;


        //OldName Start Details
        for (int i = 0; i < emailList.size(); i++) {
            if (emailList.get(i).toString().equals("1")) {
                oldName1 = emailList.get(0).toString();
                newName = emailList.get(1).toString();
                newRelationName = "";
                newDataName = true;


            }
            if (emailList.get(i).toString().equals("2")) {
                oldRelationName1 = emailList.get(2).toString();
                newRelationName = emailList.get(3).toString();
                relatonNewName = true;
//             newName = "";

            }
        }
        // Old Name End


        for (int k = 0; k < oldemailNameRelationName.size(); k++) {
            oldName = oldemailNameRelationName.get(0).toString();
            oldRelationName = oldemailNameRelationName.get(1).toString();
            name = true;
            relatonName = true;
        }
//         for(int i=0;i<emailList.size();i++) {
//
//             if(emailList.get(i).toString().equalsIgnoreCase("Name")){
//             nameChanges = emailList.get(16).toString();
//             nameData =true;
//             }
//            if(emailList.get(i).toString().equalsIgnoreCase("Relation Name")){
//           relationNameChanges = emailList.get(16).toString();
//             relatonNameData = true;
//            }
//
//         }

//         if(nameData==true && relatonNameData==true){
//             slah ="/";
//
//         }
        try {

            regDate = new SimpleDateFormat("dd/mm/yyyy").format(new SimpleDateFormat("yyyy-mm-dd").parse(emailList.get(17).toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Msg = " <html>"
                + " <body>"
                + " <table width='100%' align='left' border='0'>";

        Msg += " <tr>"
                + "<td>"
                + "<h4>"
                + "Subject : Regarding Changes  the Name/RelationName pension ID:  " + emailList.get(5).toString() + " SADAREM ID:  " + emailList.get(6).toString()
                + "</h4>"
                + "</td>"
                + "</tr>";

        Msg += " <tr height='35'>"
                + " 	<td valign='top'>"
                + "          I acknowledge that Mr./Mrs./Miss, " + emailList.get(14).toString() + " C/O " + emailList.get(15).toString() + " Having "
                + " 	</td>"
                + "   </tr>"
                + "   <tr>"
                + " 	<td>"
                + " 	   <b>SADAREM ID</b>: " + emailList.get(6).toString() + " and  <b>Pension ID:</b>  " + emailList.get(5).toString() + " is "
                + "          belong to <b>Address </b>" + emailList.get(11).toString() + " <b>Habitation </b>" + emailList.get(10).toString() + " <b>Village </b>"
                + " 	</td>"
                + "   </tr>"
                + "   <tr>"
                + " 	<td>"
                + "    " + emailList.get(9).toString() + "  <b>Mandal </b>" + emailList.get(8).toString() + "  <b>District </b>" + "  is applied for (Greivances) change on " + regDate + "."
                + " 	</td>"
                + "   </tr>"
                + "   <tr>"
                + " 	<td>"
                + " 		&nbsp;"
                + " 	</td>"
                + "   </tr>";
        if (name == true && newDataName == true) {
            Msg += "   <tr>"
                    + " 	<td>"
                    + " 		<h3>"
                    + " 		His/Her Old Name is :  " + oldName + "  and  " + " Correct Name is " + newName
                    + " 		</h3>"
                    + " 	</td>"
                    + "   </tr>";
        }
        if (relatonName == true && relatonNewName == true) {

            Msg += "   <tr>"
                    + " 	<td>"
                    + " 		<h3>"
                    + " 		His/Her Old RelationName is :  " + oldRelationName + "  and  " + " Correct RelationName is " + newRelationName
                    + " 		</h3>"
                    + " 	</td>"
                    + "   </tr>";
        }

        Msg += "   <tr>"
                + " 	<td>"
                + "           For the Support of Name/RelationName changes, the PWD attach the documents like - 1) SSC Certificate and 2) Residential Certificate."
                + " 	</td>"
                + "   </tr>"
                + "     <tr>"
                + " 	<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; "
                + //                "          "+mailBodyDetails+"" +
                " 	</td>"
                + "   </tr>";
        if (name == true && newDataName == true) {
            Msg += " <tr>"
                    + " 	<td>"
                    + "       <b>For Name: </b>"
                    + " 	I certify that Mr/Mrs/Miss " + newName + " found the correct based on request & document provided"
                    + "       and I update the greivances from old to new."
                    + " 	</td>"
                    + "   </tr>";
        }
        if (relatonName == true && relatonNewName == true) {
            Msg += " <tr>"
                    + " 	<td>"
                    + "      <b> For RelationName: </b>"
                    + " 	I certify that Mr/Mrs/Miss " + newRelationName + " found the correct based on request & document provided"
                    + "       and I update the greivances from old to new."
                    + " 	</td>"
                    + "   </tr>";
        }
        Msg += " <tr>"
                + " 	<td>"
                + " 		&nbsp;"
                + " 	</td>"
                + "   </tr>"
                + "   <tr>"
                + " 	<td> "
                + " 	   With Best Wishes " + ","
                + " 	</td>"
                + "   </tr>"
                + "   <tr>"
                + " 	<td>"
                + " 		Thanks & Regards,"
                + " 	</td> "
                + "  </tr>"
                + "   <tr>"
                + " <td>"
                + "" + emailNameRelationName
                + " 	</td>"
                + "   </tr>"
                + "   <tr>"
                + " 	<td>"
                + " 		Camp-Incharge,"
                + " 	</td>"
                + "   </tr>"
                + "   <tr>"
                + " 	<td>" + emailList.get(12).toString() + ","
                + " 	</td>"
                + "   </tr>"
                + "   <tr>"
                + "        <td>" + emailList.get(13).toString() + "."
                + " 	</td>"
                + "   </tr>"
                + " </table>"
                + " </body>"
                + " </html>";

        return Msg;

    }

    //Mail Format For Request Raised For Corections
    public String pwdEmailFormatBody(ArrayList list) {

        String Msg = null;

        Msg = "<html><head><title>SADAREM</title></head>"
                + "<body>"
                + "<table width='80%' align='center' border='1' cellpadding='0' cellspacing='0'>"
                + "           <table> </br>"
                + "                     <table border='1' align='center' width='80%' cellpadding='0' cellspacing='0'>"
                + "                        <tr>"
                + "                              <th>S.No.</th>"
                + "                              <th>Pension ID</th>"
                + "                              <th>SADAREM ID</th>"
                + "                              <th>Name</th>"
                + "                              <th>HousNo.</th>"
                + "                              <th>HabitationName</th>"
                + "                              <th>VillageName</th>"
                + "                              <th>MandalName</th>"
                + "                             <th>DistrictName</th>"
                + "                          </tr>";
        for (int k = 0; k < list.size(); k++) {



            ArrayList list2 = (ArrayList) list.get(k);
            Msg += "<tr>";
            for (int j = 0; j < list2.size(); j++) {
                Msg += "<td> " + list2.get(j) + "";
                Msg += "</td>";
            }
            Msg += "</tr>";
        }
        Msg += "   <tr>"
                + "        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                + " 		Note: This is a system-generated e-mail. Please do not respond to this mail."
                + " 	</td>"
                + "   </tr> </table>"
                + "</body></html>";

        return Msg;
    }

    //End
    //Mail Body For issueeRaised Format
    public String issueRaiseBodyFormat(ArrayList list, String passwordData) {
        String Msg = null;


        Msg = " <html>"
                + " <body>"
                + " <table width='100%' align='left' border='0'>"
                + "   <tr height='35'>"
                + " 	<td valign='top' colspan='10'>"
                + " 		Dear Sir,"
                + " 	</td>"
                + "   </tr>"
                + "   <tr>";
        Msg += "<td valign='top'>";


        Msg += "Details for your Issue ID :&nbsp; <b>" + list.get(0).toString() + "</b>,"
                + " raised on <b>" + list.get(3).toString() + "</b> is as below:";


        Msg += " </td>";

        Msg += "<tr>";
        Msg += " <td >"
                + "Category &nbsp;: <b>" + list.get(1).toString() + "</b>"
                + "</td>";
        Msg += "</tr>";

        Msg += "</tr>";
        Msg += "<tr>";
        Msg += " <td >"
                + "Description &nbsp;: <b>" + list.get(2).toString() + "</b>."
                + "</td>";
        Msg += "</tr>";

        Msg += "<tr>";
        Msg += " <td >"
                + "Note:-If you have any Query related to Issue, please feel free to call us :-"
                + "</td>";
        Msg += "</tr>";
        Msg += "<tr>";
        Msg += " <td >" 
                + "phone:-040-66678764"
                + "</td>";
        Msg += "</tr>";
        Msg += "<tr>";
        Msg += " <td >"
                + "email:- ts.sadarem@tcs.com"
                + "</td>";
        Msg += "</tr>";
         Msg += "<tr>";
        Msg += "<td>";
        Msg += "Thanks & regards,";
        Msg += "</td>";
        Msg += "</tr>";
       
        Msg += "<tr>";
        Msg += "<td>";
        Msg += "SADAREM Developement Team";
        Msg += "</td>";
        Msg += "</tr>";
        Msg += "<tr>";
        
        Msg += "<td>";
        Msg += " &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
        Msg += "</td>";
        Msg += "</tr>";
       
        Msg += "   <tr>"
                + "        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                + " 		Note: This is a system-generated e-mail. Please do not respond to this mail."
                + " 	</td>"
                + "   </tr></table> ";
        Msg += "</body>";
        Msg += " </html>";
        return Msg;
    }

    public String issueResloveBody(IssueRaiseApprovalForm issueRaiseApprovalForm) {
        String Msg = null;
        Msg = " <html>"
                + " <body>"
                + " <table width='100%' align='left' border='0'>"
                + "   <tr height='35'>"
                + " 	<td valign='top'>"
                + " 		Dear Sir"
                + " 	</td>"
                + "   </tr>"
                + "   <tr>"
                + " 	<td >"
                + " 		&nbsp;Details for your Issue ID : <b>" +  issueRaiseApprovalForm.getRequestModeRaiseId() + "</b> has been Approved"
                + " 	</td>"
                + "   </tr>"
                 + "   <tr>"
                + " 	<td >"
                + " 		&nbsp;Category : <b>" + issueRaiseApprovalForm.getResolvecategoryName() + "</b>,"
                + " 	</td>"
                + "   </tr>"

                + " <tr> "
                + " <td >"
                + "Description : <b>" + issueRaiseApprovalForm.getResolveDescription()+"</b>"
                + "</td>"
                + "  </tr> "
                + " <tr> "
                + " <td >"
                + " Note :- If you have any Query related to Issue, please feel free call to us :-"
                + "</td>"
                + "  </tr> "
                + " <tr> "
                + " <td >"
                + " phone:-040-66678764"
                + "</td>"
                + "  </tr> "
                + " <tr> "
                + " <td >"
                + " email:- ts.sadarem@tcs.com"
                + "</td>"
                + "  </tr> "
                + " <tr> "
                + " <td >"
                + " &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                + "</td>"
                + "  </tr> "
                + " <tr> "
                + " <td >"
                + " Thanks& Regards,"
                + "</td>"
                + "  </tr> "
                + " <tr> "
                + " <td >"
                + " SADAREM Developement Team,"
                + "</td>"
                + "  </tr> "
                + "   <tr>"
                + "        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                + " 		Note: This is a system-generated e-mail. Please do not respond to this mail."
                + " 	</td>"
                + "   </tr>"
                + " </table>"
                + " </body>"
                + " </html>";
        return Msg;

    }



    public String issueRejectedBody(IssueRaiseApprovalForm issueRaiseApprovalForm) {
        String Msg = null;
        Msg = " <html>"
                + " <body>"
                + " <table width='100%' align='left' border='0'>"
                + "   <tr height='35'>"
                + " 	<td valign='top'>"
                + " 		Dear Sir"
                + " 	</td>"
                + "   </tr>"
                + "   <tr>"
                + " 	<td >"
                + " 		&nbsp;Details for your Issue ID : <b>" +  issueRaiseApprovalForm.getRequestModeRaiseId() + "</b> has been Rejected"
                + " 	</td>"
                + "   </tr>"
                 + "   <tr>"
                + " 	<td >"
                + " 		&nbsp;Category : <b>" + issueRaiseApprovalForm.getResolvecategoryName() + "</b>,"
                + " 	</td>"
                + "   </tr>"

                + " <tr> "
                + " <td >"
                + "Description : <b>" + issueRaiseApprovalForm.getResolveDescription()+"</b>"
                + "</td>"
                + "  </tr> "
                 + " <tr> "
                + " <td >"
                + "Remarks : <b>" + issueRaiseApprovalForm.getRejectComment()+"</b>"
                + "</td>"
                + "  </tr> "
                + " <tr> "
                + " <td >"
                + " Note :- If you have any Query related to Issue, please feel free call to us :-"
                + "</td>"
                + "  </tr> "
                + " <tr> "
                + " <td >"
                + " phone:-040-66678764"
                + "</td>"
                + "  </tr> "
                + " <tr> "
                + " <td >"
                + " email:- ts.sadarem@tcs.com"
                + "</td>"
                + "  </tr> "
                + " <tr> "
                + " <td >"
                + " &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                + "</td>"
                + "  </tr> "
                + " <tr> "
                + " <td >"
                + " Thanks& Regards,"
                + "</td>"
                + "  </tr> "
                + " <tr> "
                + " <td >"
                + " SADAREM Developer Team,"
                + "</td>"
                + "  </tr> "
                + " <tr> "
                + " <td >"
                + " Kohinoor e-park,"
                + "</td>"
                + "  </tr> "
                + " <tr> "
                + " <td >"
                + " Kondapur, Hyderabad,"
                + "</td>"
                + "  </tr> "
                + " <tr> "
                + " <td >"
                + " Telangana."
                + "</td>"
                + "  </tr> "
                + "   <tr>"
                + "        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                + " 		Note: This is a system-generated e-mail. Please do not respond to this mail."
                + " 	</td>"
                + "   </tr>"
                + " </table>"
                + " </body>"
                + " </html>";
        return Msg;

    }
}
