package com.tcs.sadarem.mis.action;



import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Workbook;
import jxl.biff.DisplayFormat;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.bf.disability.Exception.BaseDispatchAction;

import com.tcs.sadarem.common.DAO.CommonDAO;
import com.tcs.sadarem.common.DAO.CommonDAOImpl;
import com.tcs.sadarem.mis.DAO.MisDAO;
import com.tcs.sadarem.mis.DAO.MisDAOImpl;
import com.tcs.sadarem.util.CommonUtility;

	public class MisSpmuLevelReportAction extends BaseDispatchAction  {

		public ActionForward unspecified(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
		{
		
			String target="success";
			ArrayList activeactivityList = new ArrayList();
			ArrayList monthyearlistcombo = new ArrayList();
		
				
			ArrayList actsubactList = new ArrayList();
			ArrayList DistrictList = new ArrayList();
			
			ArrayList resultlist = new ArrayList();
			ArrayList ranklist = new ArrayList();
			
			
			
		try{	
			 HttpSession session = request.getSession();
			MisDAO obj = new MisDAOImpl();
			
		
			activeactivityList = obj.getActiveActivityList();
			
 			
			actsubactList = obj.getActSubactList();
			CommonDAO obj1 = new CommonDAOImpl(); 
			DistrictList=obj.getNewDistrictList();

			String monthyear= CommonUtility.checkNullObj((String)request.getParameter("monthyear"));
				
			String monthName="";
			
			Calendar cal = Calendar.getInstance();
			
			if(monthyear.length()>0)
			{
			 monthName = obj.getMonthName(monthyear);
			}
		
			String Year = new SimpleDateFormat("yyyy").format(cal.getTime());
		     int currentmonth = cal.get(Calendar.MONTH) + 1;
			String finyear=null,yr=null;
			String financialyear=null;
			
		     if(currentmonth<4)
		     {
		    	 finyear=(Integer.parseInt(Year)-1)+"";
		     }
		     else
		     {
		    	 finyear=Year;
		     }
			
		     yr=(Integer.parseInt(finyear)+1)+"";

		     financialyear=finyear+"-"+yr;
		     String month="";
		     if(monthyear.length()>0)
		     {
		      month = ""+Integer.parseInt((String)monthyear);   
		     }
		     resultlist = obj.getSPMUDistWiseRanks(finyear,monthyear);	
		     
		      ranklist = obj.getRanks(finyear,monthyear);	
		     
		     monthyearlistcombo=obj.getMonths(Integer.parseInt(finyear));
		
			request.setAttribute("monthName",monthName);
			request.setAttribute("Year",financialyear);			
			request.setAttribute("DistrictList",DistrictList);
			request.setAttribute("activeactivityList",activeactivityList);
			request.setAttribute("actsubactList",actsubactList);
			request.setAttribute("monthyearlistcombo",monthyearlistcombo);
			request.setAttribute("monthyear",monthyear);
			request.setAttribute("resultlist",resultlist);
			request.setAttribute("ranklist",ranklist);
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
			
			return mapping.findForward(target);
			
		}
		public ActionForward excelNew(ActionMapping mapping,ActionForm form, HttpServletRequest request, HttpServletResponse response)
		   {

		        try {
		        	String target="success";
					ArrayList activeactivityList = new ArrayList();
					ArrayList monthyearlistcombo = new ArrayList();
				    ArrayList actsubactList = new ArrayList();
					ArrayList DistrictList = new ArrayList();					
					ArrayList resultlist = new ArrayList();
					
					ArrayList ranklist = new ArrayList();
					
					ArrayList Masterranklist = new ArrayList();
					
					
					 HttpSession session = request.getSession();
					MisDAO obj = new MisDAOImpl();
					
				
					activeactivityList = obj.getActiveActivityList();
					
		 			
					actsubactList = obj.getActSubactList();
					CommonDAO obj1 = new CommonDAOImpl(); 
					DistrictList=obj.getNewDistrictList();
					

					String monthyear= CommonUtility.checkNullObj((String)request.getParameter("monthyear"));
					
				String monthName="";
						
					Calendar cal = Calendar.getInstance();
					
					if(monthyear.length()>0)
					{
					 monthName = obj.getMonthName(monthyear);
					}
					

			        
					
					String Year = new SimpleDateFormat("yyyy").format(cal.getTime());
				     int currentmonth = cal.get(Calendar.MONTH) + 1;
				     
				     
					String finyear=null,yr=null;
					String financialyear=null;
					
				     if(currentmonth<4)
				     {
				    	 finyear=(Integer.parseInt(Year)-1)+"";
				     }
				     else
				     {
				    	 finyear=Year;
				     }
					
				     yr=(Integer.parseInt(finyear)+1)+"";

				     financialyear=finyear+"-"+yr;
				     String month="";
				     if(monthyear.length()>0)
				     {
				      month = ""+Integer.parseInt((String)monthyear);   
				     }
		
				     
				     monthyearlistcombo=obj.getMonths(Integer.parseInt(finyear));
				
					request.setAttribute("monthName",monthName);
					request.setAttribute("Year",financialyear);			
					request.setAttribute("DistrictList",DistrictList);
					request.setAttribute("activeactivityList",activeactivityList);
					request.setAttribute("actsubactList",actsubactList);
					request.setAttribute("monthyearlistcombo",monthyearlistcombo);
					request.setAttribute("monthyear",monthyear);
				    request.setAttribute("resultlist",resultlist);
		  	 	
		  	 	
		  	 	
		  	 	
					  resultlist = obj.getSPMUDistWiseRanks(finyear,monthyear);

				      ranklist = obj.getRanks(finyear,monthyear);	
				      
				      Masterranklist = obj.getMasterRanksActivityWise(finyear,monthyear);
				  
				      
		  	    exportExcel(resultlist,ranklist,Masterranklist,request, response, financialyear,DistrictList,activeactivityList,actsubactList,monthName);
		  		

					  
						
					
			
		
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		        return null;
		    }
		
		public static void exportExcel(ArrayList resultList,ArrayList ranklist,ArrayList Masterranklist,HttpServletRequest request, HttpServletResponse response,
		         String financialyear,ArrayList DistrictList,ArrayList activeactivityList,ArrayList actsubactList,String monthName)
		 {
		     		OutputStream out = null;

		     try {
		    	 String  currentdate = CommonUtility.getDateTime("dd/MM/yyyy_hh_mm_ss"); 		    		
		         response.setContentType("application/vnd.ms-excel");
		         response.setHeader("Content-Disposition", "attachment;filename=MIS_Report_"+currentdate+".xls");
		         WritableWorkbook w = Workbook.createWorkbook(response.getOutputStream());
		         WritableFont bold = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
		         String  currentdate1 = CommonUtility.getDateTime("dd/MM/yyyy hh:mm:ss"); 
		         
		         int fontSize = 10;  
		         WritableFont wfontStatus = new WritableFont(WritableFont.ARIAL, fontSize);
		         wfontStatus.setColour(Colour.BLACK);		         

		         WritableCellFormat fCellstatus = new WritableCellFormat(wfontStatus);  
		         fCellstatus.setBorder(Border.ALL, BorderLineStyle.THIN);
		         fCellstatus.setBorder(Border.ALL, BorderLineStyle.THIN);
		         fCellstatus.setAlignment(Alignment.CENTRE);
		         fCellstatus.setBackground(Colour.GRAY_25);
		         fCellstatus.setVerticalAlignment(VerticalAlignment.CENTRE);
		         
		         int fontSizes = 11;  
		         WritableFont wfontStatuss = new WritableFont(WritableFont.ARIAL, fontSizes);
		         wfontStatuss.setColour(Colour.BLACK); 
		         
		         
		         
		         WritableCellFormat cell = new WritableCellFormat(wfontStatuss);
		         WritableCellFormat cellLeft = new WritableCellFormat();
		         cell.setBorder(Border.ALL, BorderLineStyle.THIN);
		         cell.setBorder(Border.ALL, BorderLineStyle.THIN);
		         cellLeft.setBorder(Border.ALL, BorderLineStyle.THIN);
		         cell.setAlignment(Alignment.CENTRE);
		         cellLeft.setAlignment(Alignment.LEFT);
		         cell.setVerticalAlignment(VerticalAlignment.CENTRE);

		         wfontStatuss.setBoldStyle(WritableFont.BOLD);
		         WritableCellFormat cell111 = new WritableCellFormat(wfontStatuss);
		         cell111.setBorder(Border.ALL, BorderLineStyle.THIN);
		         cell111.setBorder(Border.ALL, BorderLineStyle.THIN);
		         cell111.setAlignment(Alignment.CENTRE);
		         cell111.setBackground(Colour.GRAY_25);
		         cell111.setVerticalAlignment(VerticalAlignment.CENTRE);
		         cellLeft.setAlignment(Alignment.RIGHT);
		         

		         WritableCellFormat cell13 = new WritableCellFormat(wfontStatuss);
		         cell13.setBorder(Border.ALL, BorderLineStyle.THIN);
		         cell13.setBorder(Border.ALL, BorderLineStyle.THIN);
		         cell13.setAlignment(Alignment.LEFT);
		         cell13.setBackground(Colour.GRAY_25);
		         cellLeft.setAlignment(Alignment.RIGHT);
		         
		       
		         
		         
		         
		         
		              int page = 1;
		         WritableSheet s = w.createSheet("MIS OverAll", page - 1);
		         
		         ArrayList counts= new ArrayList();
		         
		         for(int i=0;i<actsubactList.size();i++)
		         {
		         	counts.add(((ArrayList)actsubactList.get(i)).get(1));
		         }
		        
		    

		         if (resultList.size() == 0) 
		         {
		             s.addCell(new Label(0, 0, "SADAREM MIS report........<--no data found-->........"));
		         } 
		       else 
		         {
		     	  
		     	  s.mergeCells(0, 0, 2, 0);
		             s.addCell(new Label(0, 0, "Generated Date:"+currentdate1+"", fCellstatus));
		     
	            
		             s.mergeCells(3, 0,(actsubactList.size()*3)+2 , 0);
		             s.addCell(new Label(3, 0, " "+"SADAREM MIS Report Month:"+monthName+"   Financial Year :"+financialyear, cell13));


		         }
		         
	
		             s.mergeCells(0, 1, (actsubactList.size()*3)+2, 1);
		             s.addCell(new Label(0, 1,"",cell111));
		     


			             s.mergeCells(0, 2, 0, 4);
			             s.addCell(new Label(0, 2, "S.No", cell111));
			             setWidth(s, 0, 10);
		             
		      
		       	       s.mergeCells(1, 2, 1, 4);
		               s.addCell(new Label(1, 2, "DISTRICT", cell111));
		               setWidth(s, 1, 25);
			         
		               int b=0,d=2,c=0,m=0,g=0,f=2;
		               if(activeactivityList!=null && activeactivityList.size()>0)
		      			 
		    		   {
		            	    int i,l=0,h=0;
		    		        for(i=0;i<activeactivityList.size();i++)
		    		        {    	
		    		            l= Collections.frequency(counts,((String)((ArrayList)activeactivityList.get(i)).get(0)));  
		    		            
		    		            if(!(h>=actsubactList.size()))
		    		            {		    		           
		    		            	h=g+l;   	
		    		            }

		    		            s.mergeCells(f, 2,sum((f-1),(l*3)), 2);
					            s.addCell(new Label(f, 2, (String)(((ArrayList)activeactivityList.get(i)).get(1)), cell111));
					            setWidth(s, 1, 25);         
					          
					            f=sum((f-1),(l*3));
					            f++;    
					            
					            if(actsubactList!=null && actsubactList.size()>0)
						        {
							 		
						            for(b=c;b<h;b++)						          
									   {
						            	    s.mergeCells(d, 3, d+2, 3);
								            s.addCell(new Label(d, 3, (String)((ArrayList)actsubactList.get(b)).get(4), cell111));
								            setWidth(s, 1, 25); 								            
								            
								            s.mergeCells(d, 4, d, 4);
								            s.addCell(new Label(d, 4, "Target", cell111));
								            setWidth(s, 1, 25); 
								            d++;
								            
								            s.mergeCells(d, 4, d, 4);
								            s.addCell(new Label(d, 4, "Achievement", cell111));
								            setWidth(s, 1, 25); 
								            d++;
								            
								            s.mergeCells(d, 4, d, 4);
								            s.addCell(new Label(d, 4,"Rank", cell111));
								            setWidth(s, 1, 25); 
								          d++;
								            
								            if(b==h-1)
								            {
								            	c=g+l;
								            	g=g+l;
								            	break;
								            	
								            }
								            
									   }
						        }   
					        
					           
					            
		    		        }  
		    		        s.mergeCells(d, 2, d, 4);
				            s.addCell(new Label(d, 2,"Master\n Rank", cell111));
				            setWidth(s, 1, 25);    
		    		        
		    		        
		    		        
		    		            
		    		   }     
			          
		             for(int u=0;u<(actsubactList.size()*3)+3;u++)
		             {
		            	   s.addCell(new Label(u, 5, ""+(u+1), cell111));
				             setWidth(s, 4, 30); 
		             }
		            


		             ArrayList templist = new ArrayList();
		             ArrayList TarginnerList = new ArrayList();
		             ArrayList rankinnerList = new ArrayList();
		             ArrayList AchvinnerList = new ArrayList();
		             ArrayList rankylist = new ArrayList();
		             ArrayList finallist = new ArrayList();
		             
		 		     int loopCount=0;
		 		     String style="";
		 		     String id="";
		 		  int q=0;
		 		  int rankloop=0;
		     	  for(loopCount=0, rankloop=0;loopCount<resultList.size();loopCount++,rankloop++)
		     		 { 
		     		  if(rankloop<(int)(-1+resultList.size()/3))
		     		  {
		     		  rankylist = (ArrayList)ranklist.get(rankloop);
		     		  }
		     		  TarginnerList = (ArrayList)resultList.get(loopCount);
		     		  rankinnerList  = (ArrayList)resultList.get(loopCount+1);
		     		  AchvinnerList = (ArrayList)resultList.get(loopCount+2);
	  
		      if(TarginnerList.get(1).toString().contains("ZZ_Total")) 
		      {
		    	  finallist.add("Total");
		      }
		      else
		      { 
		    		  finallist.add(TarginnerList.get(1)); 
		      }
		      int h=  actsubactList.size()+3;
		    for(int r=3,z=0;r<h;r++,z++)		           
		      { 
		 			
		 			 if(TarginnerList.get(2).equals("T") || TarginnerList.get(2).equals("ST")) 
		 			 {
		       
				              if(((String)((ArrayList)actsubactList.get(z)).get(0)).equals("MISTARGET00010"))
				              {
				       
						 		        if(TarginnerList.get(r)==null) 
						 		        {
						 			        finallist.add(q); 
						 			
						 		        }
						 		        else
						 		        {
				                               finallist.add((String)TarginnerList.get(r).toString().trim()+"("+(int)Float.parseFloat(((String)TarginnerList.get(r).toString().trim()))*1.5+")");
						 			
						 		        }
				 		
				 		      }
				              else
				              { 
				 		
				 		                 if(TarginnerList.get(r)==null) 
				 		                 {
				 			               finallist.add(q); 
				 		                 }
				 		                 else
				 		                 {
				 		                	 finallist.add((String)TarginnerList.get(r).toString().trim()); 
				 		                 }
				              }
		 		     
		 		      }
		 			 if(AchvinnerList.get(2).equals("A") || AchvinnerList.get(2).equals("SA")) 
		 			 {
		 				 		if(((String)((ArrayList)actsubactList.get(z)).get(0)).equals("MISTARGET00010"))
		 				 		{
		 				 				if(AchvinnerList.get(r)==null) 
		 				 				{
		 				 						finallist.add(q);
		 				 				}
		 				 				else
		 				 				{
		 				 						finallist.add(((String)AchvinnerList.get(r).toString().trim())+"("+(int)Float.parseFloat(((String)AchvinnerList.get(r).toString().trim()))*1.5+")"); 
		 				 				}
		 		
		 				 		}
		 				 		else
		 				 		{ 
		 		
		 				 				if(AchvinnerList.get(r)==null) 
		 				 				{
		 				 						finallist.add(q) ; 
		 				 				}
		 				 				else
		 				 				{ 
		 				 					finallist.add((String)AchvinnerList.get(r).toString().trim()); 
		 				 				}
				           
		 				 		} 
		             
		             }
		 			 if(rankinnerList.get(2).equals("R") || rankinnerList.get(2).equals("SR")) 
		 			 {
		            
				 				 		if(rankinnerList.get(r)==null || rankinnerList.get(r).equals("0")) 
				 				 		{
				 				 				finallist.add("-") ;
				 				 		}
				 				 		else
				 				 		{ 
				 				 				finallist.add(((String)rankinnerList.get(r).toString().trim()));
				 				 		}
		            
		              }
		 			 
		 			 
		 	}
		    
		    if(rankloop<(int)(-1+resultList.size()/3))
			  {
		    
		    if(rankylist.get(3)==null) 
		 		{
		    	finallist.add("-") ;
		 		}
		    else
		    {

	    		finallist.add((int)Float.parseFloat((String)rankylist.get(3).toString().trim()));
		    }
		    
			  }
		    else
		    {
		    	finallist.add("-");
		    }
		                
		    			templist.add(finallist);
		      
		       finallist=new ArrayList();
		       loopCount = loopCount + 2;
		       
		            } //for loop close          
		       
		     	  
		     	
		     	     int x = 6;
			         int k = 0;

			        ArrayList innerlist=new ArrayList();
			        ArrayList masterrankinnerlist=new ArrayList();
			        
			         for (int i = 0; i < templist.size(); i++, x++) 
			         {
			         	 int j = 0;
			             k++;
			             innerlist = (ArrayList)templist.get(i);
			             

			             
			             if(innerlist.get(0).toString().equals("ZZZZ"))
			     		  {
			            	  s.addCell(new Label(j++, x, "", cell111));
			     		  }
			             else
			             {
			            	  s.addCell(new Label(j++, x, i + 1 + "", cell));
			             }
			             
			             
		     	    for(int p=0;p<innerlist.size();p++)
		     	    {
		     	  
		     	    	if(innerlist.get(p).toString().equals("ZZZZ"))
		     	    	{
		     	    		  s.addCell(new Label(j++, x,"Total", cell111));
		     	    	}
		     	    	else
		     	    	{
		     	    		
		     	    			 s.addCell(new Label(j++, x,innerlist.get(p).toString(), cellLeft));	
		     	    		
		     	    	}		           
		             
		             
		     	    }
		     	    
		        
		          
		         }
		         
			         
	if(activeactivityList!=null && activeactivityList.size()>0)
	 {
		int i,l=0,h=0,hh=0;
		int bb=0,cc=0,gg=0,ff=2,nn=1,jjj=1;;
		for( i=0;i<activeactivityList.size();i++)
	    	{    		    
			   l= Collections.frequency(counts,((String)((ArrayList)activeactivityList.get(i)).get(0)));  
		        int dd=2; 
		      int sheet = ++page;
		      s = w.createSheet((String)((ArrayList)activeactivityList.get(i)).get(1), sheet - 1);
		      
		      
		      if(Masterranklist.size()>0)
		      {
		      masterrankinnerlist = (ArrayList)Masterranklist.get(i);}          

		       if (resultList.size() == 0) 
				 {
				   s.addCell(new Label(0, 0, "SADAREM Assessed report.......<--No data found-->......"));
				 } 
				else 
				 {
				     	  
				 s.mergeCells(0, 0, 2, 0);
				 s.addCell(new Label(0, 0, "Generated Date:"+currentdate1+"", fCellstatus));
				 
				 s.mergeCells(3, 0,2+(l*3) , 0);
				 s.addCell(new Label(3, 0, " "+"SADAREM MIS Report Month:"+monthName+"   Financial Year :"+financialyear, cell13));

                 }        
		         
		         s.mergeCells(0, 1, 2+(l*3), 1);
	             s.addCell(new Label(0, 1,"",cell111));
	     


		             s.mergeCells(0, 2, 0, 4);
		             s.addCell(new Label(0, 2, "S.No", cell111));
		             setWidth(s, 0, 10);
	             
	      
	       	       s.mergeCells(1, 2, 1, 4);
	               s.addCell(new Label(1, 2, "DISTRICT", cell111));
	               setWidth(s, 1, 25);   
	               
	               
	               
	               if(activeactivityList!=null && activeactivityList.size()>0)
	      			 
	    		   {
	            
	    		            if(!(h>=actsubactList.size()))
	    		            {		    		           	    		     
	    		            	h=l;
	    		            }

	    		            s.mergeCells(ff, 2,1+(l*3), 2);
				            s.addCell(new Label(ff, 2, (String)(((ArrayList)activeactivityList.get(i)).get(1)), cell111));
				            setWidth(s, 1, 25);         

				            
				            if(actsubactList!=null && actsubactList.size()>0)
					        {
						 		
					            for(bb=0;bb<h;bb++,cc++)						          
								   {
					            	    s.mergeCells(dd, 3, dd+2, 3);
							            s.addCell(new Label(dd, 3, (String)((ArrayList)actsubactList.get(cc)).get(4), cell111));
							            setWidth(s, 1, 25); 								            
							            
							            s.mergeCells(dd, 4, dd, 4);
							            s.addCell(new Label(dd, 4, "Target", cell111));
							            setWidth(s, 1, 25); 
							            dd++;
							            
							            s.mergeCells(dd, 4, dd, 4);
							            s.addCell(new Label(dd, 4, "Achievement", cell111));
							            setWidth(s, 1, 25); 
							            dd++;
							            
							            s.mergeCells(dd, 4, dd, 4);
							            s.addCell(new Label(dd, 4,"Rank", cell111));
							            setWidth(s, 1, 25); 
							          dd++;
							            
							           if(bb==h-1)
							            {
							            	cc=gg+l;
							            	gg=gg+l;
							            	break;
							            	
							            }
							            
								   }
					        }   
				        
				           
				            
	    		      //  }  
	    		        s.mergeCells(dd, 2, dd, 4);
			            s.addCell(new Label(dd, 2,"Master\n Rank", cell111));
			            setWidth(s, 1, 25);    
	    		        
	    		        
	    		        
	    		            
	    		   }     
		          
	             for(int u=0;u<(l*3)+3;u++)
	             {
	            	   s.addCell(new Label(u, 5, ""+(u+1), cell111));
			             setWidth(s, 4, 30); 
	             }
	               
	               
	    	     int xx = 6;
		         int kk = 0;
		         int tt = 1;
		      
		       
		         int j = 0, ii=0,jj=0;
		
		         
		        
		         for (ii = 0,jj=hh;ii < templist.size(); ii++, xx++) 
		         {
		        	
		             kk++;
		             innerlist = (ArrayList)templist.get(ii);
		             
		            
		             if(innerlist.get(0).toString().equals("ZZZZ"))
		     		  {
		            	  s.addCell(new Label(j++, xx, "", cell111));
		     		  }
		             else
		             {
		            	  s.addCell(new Label(j++, xx, ii + 1 + "", cell));
		             }
		             
	     	    for(int p=0;p<(l*3)+1;p++)
	     	    {
	     	    	
	     	  if(p<1)
	     	  {
	     		  if(innerlist.get(p).toString().equals("ZZZZ"))
	     		  {
	     			 s.addCell(new Label(j++, xx,"Total", cell111));  
	     		  }
	     		  else
	     		  { 
	     			
	     				 s.addCell(new Label(j++, xx,innerlist.get(p).toString(), cellLeft));		 
	     			 
	     		  }
	             
	     	  }
	     	  else
	     	  {
		             s.addCell(new Label(j++, xx,innerlist.get(nn).toString(), cellLeft));
		             nn++;
	     	  }
	     	  
	     	
	     	 
	     	    }
	     	    tt=nn;
	     	   if(ii==templist.size()-1)
		        {
		        	nn=tt;
		        }
		        else
		        {
		        	nn=jjj;
		        }
	     	    
	     	    
	     	    if(ii<templist.size()-1)
	     	    {
	     	    	if(Masterranklist.size()>0)
	     	    	{
	     	    s.addCell(new Label(j++, xx,((ArrayList)Masterranklist.get(jj)).get(4).toString().trim(), cellLeft));
	     	    	}
	     	    	else
	     	    	{
	     	    		  s.addCell(new Label(j++, xx,"-", cellLeft));		
	     	    	}
	     	   jj++;
	     	    }
	     	    else
	     	    {
	     	    	s.addCell(new Label(j++, xx,"-", cellLeft));
	     	    }
	     	    
	     	    
	        j=0;
	      
	        
	       
	         }    
		       hh=jj; 
		       nn=tt;
		       jjj=tt;
	               
	    	}//main for loop
			         
	}//main if loop
		
		         
		         w.write();
		         w.close();

		     } catch (Exception e) {
		         e.printStackTrace();
		     } finally {
		         if (out != null) {
		             try {
		                 out.close();
		             } catch (IOException ex) {
		                 ex.printStackTrace();
		             }
		         }
		     }
		 }
		public static int sum(int a, int b) {

			return a+b;
			} 

		public static void setWidth(WritableSheet s, int col, int widthInChars) {

		s.setColumnView(col, widthInChars);
		}        	
	}