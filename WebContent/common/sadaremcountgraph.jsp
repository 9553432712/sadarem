<%@ page language="java" import="java.util.ArrayList,java.util.HashMap,com.tcs.sadarem.common.DAO.CommonDAO,com.tcs.sadarem.common.DAO.CommonDAOImpl" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>

    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    
<%
try
{
CommonDAO comObj = new CommonDAOImpl();

ArrayList dispDetailsList = (ArrayList)comObj.getDisabilityCountDetails();


ArrayList pieCharDataList 	 = new ArrayList();
ArrayList dashboardCountList = new ArrayList();
ArrayList tempList  	  = new ArrayList();
 
int status = 0;

if(dispDetailsList!=null && dispDetailsList.size()>0)
{
	if(dispDetailsList.size()>=7)
	{
			
		tempList = new ArrayList();
		tempList.add("'Disability'");
		tempList.add("'Persons'");
		
		pieCharDataList.add(tempList);
		
		for(int i=0;i<dispDetailsList.size()-1;i++)
		{
			tempList = (ArrayList)dispDetailsList.get(i);
			
			tempList.add(0, "'"+tempList.get(0)+"'");
			tempList.remove(1);
			
			pieCharDataList.add(tempList);
			
		}
		

		
		status = pieCharDataList.size();
	}
	
}

%>

  <script type="text/javascript">
  google.load("visualization", "1", {packages:["corechart"]});
    var status="<%=status%>";
    try
    {
     if((Math.round(status))>1)
     {
 	  	google.setOnLoadCallback(drawChart);
     }
    }
    catch(e)
    {
 	  alert(e);
    }
    
      function drawChart()
      {

    	var data = google.visualization.arrayToDataTable(<%=pieCharDataList.toString()%>);

       

        var options = {
                pieStartAngle: 0,
                is3D: true,
                legend: 'right',
                title:'Disability Details',
                width: 450,
                height: 350,
              };
        
        var chart = new google.visualization.PieChart(document.getElementById('piechartDIVID'));
        chart.draw(data, options);
      }
    </script>
   
</head>
<body> 
<div class="row">
                <div class="col-md-3" style="padding-left:10px !important; padding-right:0px !important; margin-left:0px;">
                    <div class="Notification-block" style="background-color:#77c0e6 !important; padding:10px; border-bottom:#77c0e6 solid 1px;">  
                        <div style="background-color: #FFF;"> 
                        	<img src="<%=request.getContextPath()%>/images/ministers.png" width="99%" > 
                        </div> 
		                        <div class="blk"> 
		                            <div class="homeheadertitle">Important Links</div>
		                            <div class="sidebarmenu blk1">
		                                <ul id="sidebarmenu1"> 
		                                    <li><a href="http://www.sadarem.telangana.gov.in/esaps/" target="_blank">Functional Needs</a></li>
		                                    <li><a href="http://www.ikppwd.telangana.gov.in/" target="_blank">IDPDPMS</a></li>
		                                    <li><a href="http://www.india.gov.in/" target="_blank">National Portal of India</a></li>
		                                    <!-- <li><a href="http://www.rdcallcentre.telangana.gov.in/insertSadaremRegistrationDetails.do" target="_blank">SADAREM Grievances</a></li>-->
		                                    <%-- <li><a href="<%=request.getContextPath()%>/campDetailsDateWiseReport.do">Camp Details</a></li> --%> 
		<!--                                    <li><a href="#" onclick="fun_openIssueTrackingSystemStatus('OpenIssueTrackingStatus')" style="color:red!important;"><b>SADAREM Grievances Status</b></a></li>-->
		                                    
		                                </ul>
		                            </div>
		                        </div> 
                       </div>
             	</div>
		<%
				if(dispDetailsList!=null && dispDetailsList.size()>=7)
				{
			%>
				<div class="col-md-5" style="padding-left:5px !important; padding-right:5px !important;">
                    <div class="row">
                        <div class="col-md-4" style="padding-left:10px !important; padding-right:10px !important;">
                            <div class="box_menu">
                                <div class="box_text" style="padding:5px !important;"><%=((ArrayList)dispDetailsList.get(1)).get(0).toString().replaceAll("\'", "") %></div>
                                <div class="Count" style="line-height: 20px; padding:5px !important;"><%=((ArrayList)dispDetailsList.get(1)).get(1) %></div>
                            </div>
                        </div>
                        <div class="col-md-4" style="padding-left:10px !important; padding-right:10px !important;">
                            <div class="box_menu box effect5">
                                <div class="box_text" style="padding:5px !important;"><%=((ArrayList)dispDetailsList.get(0)).get(0).toString().replaceAll("\'", "") %></div>
                                <div class="Count" style="line-height: 20px; padding:5px !important;"><%=((ArrayList)dispDetailsList.get(0)).get(1)%></div>
                            </div>
                        </div>
                        <div class="col-md-3" style="padding-left:10px !important; padding-right:10px !important;">
                            <div class="box_menu">
                                <div class="box_text" style="padding:5px !important;"><%=((ArrayList)dispDetailsList.get(2)).get(0).toString().replaceAll("\'", "") %></div>
                                <div class="Count" style="line-height: 20px; padding:5px !important;"><%=((ArrayList)dispDetailsList.get(2)).get(1)%></div>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="margin-top:5px;">
                        <div class="col-md-4"  style="padding-left:10px !important; padding-right:10px !important;">
                            <div class="box_menu box">
                                <div class="box_text" style="padding:5px !important;"><%=((ArrayList)dispDetailsList.get(3)).get(0).toString().replaceAll("\'", "") %></div>
                                <div class="Count" style="line-height:20px; padding:5px !important;"><%=((ArrayList)dispDetailsList.get(3)).get(1) %></div>
                            </div>
                        </div>
                        <div class="col-md-4"  style="padding-left:10px !important; padding-right:10px !important;">
                            <div class="box_menu">
                                <div class="box_text" style="padding:5px !important;"><%=((ArrayList)dispDetailsList.get(5)).get(0).toString().replaceAll("\'", "") %></div>
                                <div class="Count" style="line-height: 20px; padding:5px !important;"><%=((ArrayList)dispDetailsList.get(5)).get(1) %></div>
                            </div>
                        </div>
                        <div class="col-md-3"  style="padding-left:10px !important; padding-right:10px !important;">
                            <div class="box_menu">
                                <div class="box_text" style="padding:5px !important;"><%=((ArrayList)dispDetailsList.get(4)).get(0).toString().replaceAll("\'", "") %></div>
                                <div class="Count" style="line-height: 20px; padding:5px !important;"><%=((ArrayList)dispDetailsList.get(4)).get(1) %></div>
                            </div>
                        </div>
                    </div>

					<div class="row" style="margin-top:5px; padding:5px !important;">
						 <div  class="col-md-11">
							 <div class="box_menu box" style="height:50px !important;">
								<div class="Count" style="line-height: 20px;">Total - <%=((ArrayList)dispDetailsList.get(6)).get(1) %></div>
							</div>
						</div>
					</div>
                </div>
                <div class="col-md-3">
   		             <div id="piechartDIVID" style="height: 300px; border: 1px" >
                      	<img src="/sadarem/images/loading2.gif" >
                      </div>
             	</div>
                <%
				}
                %>
     </div>
      <%
}
catch(Exception e)
{
	e.printStackTrace();
}
      %>
 
 </body>
</html>