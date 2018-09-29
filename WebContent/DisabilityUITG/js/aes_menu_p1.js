/*****************************************************************************
Default browsercheck - Leave this one
******************************************************************************/
function lib_bwcheck(){ //Browsercheck (needed)
	this.ver=navigator.appVersion; this.agent=navigator.userAgent
	this.dom=document.getElementById?1:0
	this.ie5=(this.ver.indexOf("MSIE 5")>-1 && this.dom)?1:0;
	this.ie6=(this.ver.indexOf("MSIE 6")>-1 && this.dom)?1:0;
	this.ie4=(document.all && !this.dom)?1:0;
	this.ie=this.ie4||this.ie5||this.ie6
	this.mac=this.agent.indexOf("Mac")>-1
	this.opera5=this.agent.indexOf("Opera 5")>-1
	this.ns6=(this.dom && parseInt(this.ver) >= 5) ?1:0; 
	this.ns4=(document.layers && !this.dom)?1:0;
	this.bw=(this.ie6 || this.ie5 || this.ie4 || this.ns4 || this.ns6 || this.opera5 || this.dom)
	return this
}
var bw=new lib_bwcheck() 

var mDebugging=2 

oCMenu=new makeCoolMenu("oCMenu") 
oCMenu.useframes=0
oCMenu.frame="mainFrame" 

oCMenu.useclick=0 

oCMenu.useNS4links=0

oCMenu.NS4padding=2 

oCMenu.checkselect=1

oCMenu.offlineUrl="file:///C|/Inetpub/wwwroot/test/" //Value: "path_to_menu_file_offline/"

oCMenu.onlineUrl="http://www.test.com/coolmenus/examples/withframes/" //Value: "path_to_menu_file_online/"

oCMenu.pagecheck=1
oCMenu.checkscroll=0
oCMenu.resizecheck=1 
oCMenu.wait=1000 

//Background bar properties
oCMenu.usebar=1
oCMenu.barcolor="#000066" //
oCMenu.barwidth= "100%" 
oCMenu.barheight="menu" 
oCMenu.barx="menu" 
oCMenu.bary="menu" 
oCMenu.barinheritborder=1

//Placement properties
oCMenu.rows=1
oCMenu.fromleft=0
oCMenu.fromtop=120
oCMenu.pxbetween=0 

oCMenu.menuplacement=0

//TOP LEVEL PROPERTIES - ALL OF THESE MUST BE SPESIFIED FOR LEVEL[0]
oCMenu.level[0]=new Array() 
oCMenu.level[0].width=50
oCMenu.level[0].height=14
oCMenu.level[0].bgcoloroff="#6699CC" 
oCMenu.level[0].bgcoloron="#6699CC" //old color : #E7E7E7
oCMenu.level[0].textcolor="white" 
oCMenu.level[0].hovercolor="white"//#CCCC98
oCMenu.level[0].style="padding:2px; font-family:verdana; font-size:11px; font-weight:normal" 
oCMenu.level[0].border=1
oCMenu.level[0].bordercolor="black"
oCMenu.level[0].offsetX=0
oCMenu.level[0].offsetY=2
oCMenu.level[0].NS4font="verdana"
oCMenu.level[0].NS4fontSize="1"

oCMenu.level[0].clip=0 
oCMenu.level[0].clippx=0 
oCMenu.level[0].cliptim=0 
oCMenu.level[0].filter=0 

oCMenu.level[0].align="bottom" 

oCMenu.level[1]=new Array() 
oCMenu.level[1].width=80
oCMenu.level[1].height=16
oCMenu.level[1].bgcoloroff="#F5F8FD"
oCMenu.level[1].bgcoloron="#D9E4F6"
oCMenu.level[1].textcolor="black" 
oCMenu.level[1].hovercolor="#000066"
oCMenu.level[1].style="padding:2px; font-family:verdana; font-size:11px; font-weight:normal"
oCMenu.level[1].align="center" 
oCMenu.level[1].offsetX=0
oCMenu.level[1].offsetY=0
oCMenu.level[1].border=1
oCMenu.level[1].bordercolor="#A5B8D9"
oCMenu.level[1].align="bottom" 

oCMenu.level[2]=new Array() 
oCMenu.level[2].width=80
oCMenu.level[2].height=16
oCMenu.level[2].bgcoloroff="#F5F8FD" 
oCMenu.level[2].bgcoloron="#D9E4F6" 
oCMenu.level[2].textcolor="black" 
oCMenu.level[2].hovercolor="#000066" //
oCMenu.level[2].style="padding:2px; font-family:verdana; font-size:11px; font-weight:normal" 
oCMenu.level[2].border=0
oCMenu.level[2].bordercolor="#A5B8D9"
oCMenu.level[2].offsetX=0 
oCMenu.level[2].offsetY=0 
oCMenu.level[2].NS4font="verdana"
oCMenu.level[2].NS4fontSize="1"

oCMenu.makeMenu('top0','','&nbsp;Home','in.html','','250','20')
oCMenu.makeMenu('top1','','&nbsp;Masters','in.html','','250','20')
      oCMenu.makeMenu('sub110','top1','&nbsp;Add','Home.html','','250','20')
	
	//oCMenu.makeMenu('sub00','top0','My Home','aes_home.htm','','100','20')
	//oCMenu.makeMenu('sub01','top0','Personalize','aes_home.htm','','100','20')

oCMenu.makeMenu('top2','','&nbsp;Reports','in.html','','250','20')

oCMenu.makeMenu('top5','','&nbsp; Logout','Login.html','','250','20')
//oCMenu.makeMenu('top3','','&nbsp;HelpDesk','Create1.html','','140','20')
   // oCMenu.makeMenu('sub310','top3','&nbsp;View Opened Tickets','open1.html','','150','20')
	//oCMenu.makeMenu('sub311','top3','&nbsp;View Closed Tickets','close1.html','','150','20')
	//oCMenu.makeMenu('sub312','top3','&nbsp;View Reopened Tickets','openchange1.html','','150','20')
	//oCMenu.makeMenu('sub313','top3','&nbsp;Search','search.html','','150','20')
	//oCMenu.makeMenu('sub314','top3','&nbsp;Update','update.html','','150','20')
	//oCMenu.makeMenu('sub315','top3','&nbsp;Reports','reports.html','','150','20')
	
		
//oCMenu.makeMenu('top4','','&nbsp; OnlineExam','in2.html','','140','20')

//oCMenu.makeMenu('top1','','&nbsp;Project','home.jsp','','90','20')
//	oCMenu.makeMenu('sub11','top1','AddProject','./getmandals.do','','150','20')
//	oCMenu.makeMenu('sub110','top1','Modify/Delete Project','./getmandals1.do','','150','20')
	//oCMenu.makeMenu('sub111','top1','DeleteProject','./getmodulelist2.do','','150','20')
//oCMenu.makeMenu('sub112','top1','SearchProject','Search.do','','150','20')
	//oCMenu.makeMenu('sub312','top3','Hypertension care','Hypertension_Care.html','','150','20')
	//oCMenu.makeMenu('sub313','top3','School Health Program','SchoolHealth.html','','150','20')
	
//oCMenu.makeMenu('top2','','&nbsp;Reports','./home.jsp','','100','20')
//oCMenu.makeMenu('sub21','top2','AllActivities','./getmandals3.do','','150','20')
//oCMenu.makeMenu('sub210','top2','SpecificActivity','./getmandals4.do','','150','20')
//	oCMenu.makeMenu('sub211','top2','SpecificProject','./getmandals2.do','','150','20')


//oCMenu.makeMenu('top3','','&nbsp;Logout','login.jsp','','80','20')
//	oCMenu.makeMenu('sub61','top6','test','aes_home.htm','','100','20')

//oCMenu.makeMenu('top7','','&nbsp;Tutorial','logout.do','','90','20')
//	oCMenu.makeMenu('sub71','top7','test','aes_home.htm','','100','20')

oCMenu.makeStyle(); oCMenu.construct()