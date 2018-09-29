 var status=0;
        function OnBodyLoad(rowCount,CoulmnCount)
        {
        	if(rowCount=="")
        		{
        		rowCount =1;
        		}
        	if(CoulmnCount=="")
        		{
        		CoulmnCount =3;
        		}
            var gridContentHeight=0;
            var gridContentWidth =screen.availWidth;
            //alert(gridContentWidth);
            if (window.innerWidth && window.innerHeight) 
                {gridContentHeight=parseInt(window.innerHeight)-227;}
            else  
                {gridContentHeight=parseInt( document.documentElement.offsetHeight )-100;}
            document.getElementById('tdGridContent').height= gridContentHeight+"px"
            MakeStaticHeader('ctl00_dtg_Grid',gridContentHeight,gridContentWidth-100,rowCount,CoulmnCount,'#e0f8ff','scrollme');
            var _gridWidth=document .getElementById ('ctl00_dtg_Grid').offsetWidth ;
        }
        
        function MakeStaticHeader(gridId, height, width, hdrRowsCount, hdrClmnsCount, defaultBgColor,className)   //(GridId, GridDisplayHeight, GridDisplayWidth, GridHdrRowsCount, GridIndexColumnsCount, DefaultGridBgColor) 
        {
            var GridClass=GetElementByClass (className);
            GridClass.style .display ="block";
            var tbl = document.getElementById(gridId);
            
            var hdrRowHeight=getHgt(hdrRowsCount,tbl);  //function getHgt(){var len=0;for(var i=0;i<hdrRowsCount;i++){len += parseInt (tbl.rows[0].cells[i].offsetHeight)+1;}return len ;}
            var hdrClmnWidth=getWdth(hdrClmnsCount,tbl);  //function getWdth(){var len=0;for(var i=0;i<hdrClmnsCount;i++){len += parseInt (tbl.rows[0].cells[i].offsetWidth)+1;}return len ;}
                   
            // Div's Initialization;
            
            // Verfication for user height, width not exceeding actual Grid total hieght, width
            
            var flagWidth=0,flagHeight=0;
            if(height > ( parseInt (tbl.offsetHeight)+ 17) )
            {
                height = ( parseInt (tbl.offsetHeight)+ 17);
                flagHeight=1;
            }
            if(width > ( parseInt (tbl.offsetWidth)+17) )
            {
                width = ( parseInt (tbl.offsetWidth)+17);
                flagWidth=1;
            }
//            height = (height > ( parseInt (tbl.offsetHeight)+ 17) ) ? ( parseInt (tbl.offsetHeight)+ 17) : height ;
//            width = (width > ( parseInt (tbl.offsetWidth)+17) ) ? ( parseInt (tbl.offsetWidth)+17) : width ;
//            
//            if(parseInt (tbl.offsetWidth)>950)
//            {
//                flagWidth=1;
//            }
            ReBuildClassBody(className);
            var DivHR = document.getElementById(''+className+'DivHeaderRow'), DivHC = document.getElementById(''+className+'DivHeaderClmn'), DivHRC = document.getElementById(''+className+'DivHdrRwClmn'), DivMC = document.getElementById(''+className+'DivMainContent'), DivGd = document.getElementById(''+className+'DivGridContent');                 
            
            if (tbl) 
            {                     
                // Main relative Div
                DivGd .style .height =height +'px'; DivGd .style .width =width + 'px';  DivGd.style.position = 'relative'; DivGd .style .overflow ='hidden';   DivGd .align ="left";   DivGd.style.verticalAlign = 'top';  DivGd .parentNode .align="center";
                
                // Header Row Div style properties
                DivHR.style.height = hdrRowHeight + 'px';DivHR.style.width = (width-17) + 'px';DivHR.style.position = 'absolute';DivHR .style .overflow ='hidden';DivHR.style.top = '0px';DivHR.style.left = '0px';DivHR.style.zIndex = '10'; DivHR .align ='left';DivHR.style.verticalAlign = 'top';DivHR .style .backgroundColor =defaultBgColor;
                 
                // Header Column Div style properties   
                DivHC.style.height = (height-17) + 'px';DivHC.style.width = hdrClmnWidth + 'px';DivHC.style.position = 'absolute';DivHC .style .overflow ='hidden';DivHC.style.top = '0px';DivHC.style.left = '0px';DivHC.style.zIndex = '15'; DivHC .align ='left';DivHC .style .verticalAlign ='top';DivHC .style .backgroundColor =defaultBgColor;
                
                // Header Row-Column Div style properties
                DivHRC.style.height = hdrRowHeight + 'px';DivHRC.style.width = hdrClmnWidth + 'px';DivHRC.style.position = 'absolute';DivHRC .style .overflow ='hidden';DivHRC.style.top = '0px';DivHRC.style.left = '0px';DivHRC.style.zIndex = '20';  DivHRC .align ='left';DivHRC.style.verticalAlign = 'top';DivHRC .style .backgroundColor =defaultBgColor;
                
                // Main Grid Div style properties
                DivMC.style.height = height + 'px';DivMC.style.width = width + 'px';DivMC.style.position = 'absolute';DivMC .style .overflow ="scroll"; DivMC.style.top = '0px';DivMC.style.left = '0px';DivMC .align ='left';DivMC.style.verticalAlign = 'top';DivMC .onscroll =OnScrollDiv;
                
                if(flagWidth==1)
                {
                    DivHC.style.display="none";
                    DivHRC.style.display="none";
                    DivMC.className="over"; 
                                       
                    if(flagHeight==1)
                        DivMC .style .overflow ="hidden";
                }
            }
        }
        
        function getHgt(hdrRowsCount,tbl){var len=0;for(var i=0;i<hdrRowsCount;i++){len += parseInt (tbl.rows[0].cells[i].offsetHeight)+1;}return len ;}
        function getWdth(hdrClmnsCount,tbl){var len=0;for(var i=0;i<hdrClmnsCount;i++){len += parseInt (tbl.rows[0].cells[i].offsetWidth)+1;}return len ;}

        function OnScrollDiv() 
        {
            var cls=this.id.toString().replace ("DivMainContent","");
            document.getElementById(''+cls+'DivHeaderRow').scrollLeft = this.scrollLeft;
            document.getElementById(''+cls+'DivHeaderClmn').scrollTop = this.scrollTop;
        }
        
        function ReBuildClassBody(className)
        {
            var GridTdElmnt=GetElementByClass (className);
            GridTdElmnt .innerHTML="<div id='"+className+"DivGridContent'   ><div  id='"+className+"DivHeaderRow'>"+GridTdElmnt .innerHTML+"</div><div  id='"+className+"DivHeaderClmn'>"+GridTdElmnt .innerHTML+"</div><div  id='"+className+"DivHdrRwClmn'>"+GridTdElmnt .innerHTML+"</div><div  id='"+className+"DivMainContent'>"+GridTdElmnt .innerHTML+"</div></div>";
            return GridTdElmnt ;
        }
        
        function GetElementByClass(className)
        {
            var allElements=document .getElementsByTagName ("*");
//            var returnElements=[];
            var returnElement;
            for(var i=0;i<allElements .length ;i++)
            {
                if(allElements [i].className==className )
                {
                    returnElement = allElements [i];
                    break ;
                }
            }
            return returnElement ;
        }
        
        
        function OnPrintClick() 
        {
            try
            {
                if(status ==0)
                {
                    window.print ();
                }
                else 
                {
                    var a = window.open ('','','left =0,top=0,width='+ screen.width +',height='+ screen.height +',toolbar=0,scrollbars=0,status=0');
                    var content="";
                    content="<table ><tr><td align='center' style='color:red;'>"+document.getElementById('ctl00_lbl_MainHdr').innerHTML+"</td></tr>";
                    content +="<tr><td align='center' >"+document.getElementById('ctl00_tblCell_subHdr').innerHTML+"</td></tr>";
                    try
                    {   content +="<tr><td align='center' >"+document.getElementById('scrollmeDivMainContent').innerHTML+"</td></tr></table>";   }
                    catch(err)
                    {   content +="<tr><td align='center' >"+document.getElementById('td_grid').innerHTML+"</td></tr></table>";   }
                    
                    a.document.write(content );
                    a.document.close();
                    a.focus();
                    a.print();
                    a.close();

                    return false;
                }
            }
            catch (err)
            {
                return false ;
            }            
        }// JavaScript Document