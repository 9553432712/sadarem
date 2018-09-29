<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<html>
    <head>
        <title>SADAREM</title>
       
      
        <script language="JavaScript">            
            var exts = "jpg|gif|png|bmp|mpg|mpeg";
            function StartUpload()
            {
            var len=document.forms[0].length-1;
            for (var i=0;i<len;i++)
            {   
            current = document.forms[0].elements[i];
            if(current.type=='file' && current.value=='')
            {
            alert('Select images to upload');
            return false;         
            }
            else if(current.type=='file' && current.value!='' &&  !checkExt(current.value))
            {        
            return false;         
            }
            else if(current.type=='text' && current.value=='')
            {
            alert('Enter Description');
            return false;         
            }     
            }
            }
            function checkExt(value){
            if(value=="")return true;
            var re = new RegExp("^.+\.("+exts+")$","i");   
            if(!re.test(value))
            {
            alert("Extension not allowed for file: \"" + value + "\"\nOnly these extensions are allowed: "+exts.replace(/\|/g,',')+" \n\n");		
            return false;
            }
            return true;
            }
            
        </script>
       
    </head> 
    
    <body>
        <% int x=0; %>
        <html:form action="upload.do?photoview=fileUpload" enctype="multipart/form-data" onsubmit="return StartUpload();">
           
                <table  cellpadding="0" cellspacing="0"  align="center" class="inputform"  border="0" >
                 
                    <tr><th style="background-color:#8b8b8b;color:#fff;">Select Image</th></tr>
           
                <tr><td>
                    <html:file   property="theFile" onchange="checkExt(this.value)" style="width:250px"/>
                </td></tr>
        
                <tr><th style="background-color:#8b8b8b;text-align: center">
                <html:submit/><html:reset/></th></tr></table>
          
        </html:form>
    </body>    
</html>