


function XYLine3() {

	// Arrays for holding x, y coordinate values and point labels

	this.x = new Array();
	this.y = new Array();
	this.labels = new Array();
	
	// Assign VML compliant properties for the line.
	// Note that non-primary colors must be in #hex or rbg(r,g,b) format.

	this.VMLstroke = "weight='1pt'; color='red'; dashstyle='solid';";//bapi line color======
	this.drawline=true;	// set to true or false

	// Assign a label for the line

	this.label = "line";	// displayed when mouse is over line
	this.labelfont = "'Arial'";
	this.labelsize = "8"; // font size in "pt"
	this.labelcolor = "black";

	// Assign a VML shapetype for plotting data points, see definitions at bottom of script.
	// Using the 'none' shapetype plots invisible points and allows coordinates to be
	// shown when the mouse is over the point.  Set 'drawpoints' to false to turn off
	// the points completely and speed up graphing for extensive data sets.  The graph script
	// automatically turns off points if the data set has more than 9000 points.

	this.VMLpointshapetype="square";	// [ diamond, square, triangle, circle, x, none ]
	this.drawpoints=true;	 	// set to true or false
	this.drawlabels=false;		// set to true or false
	this.mouseoverlabels=false;	// true will show the "labels" on mouseover, false will show x,y coord

	// Assign VML properties for the points

	this.pointsize="4";	 	// shape display size in "pt"
	this.pointfillcolor="red";	// point fill color
	this.pointstrokecolor="black";	// point line color
}


function Arrow() {

	// x and y coordinate values of arrow origin

	this.x = 0;
	this.y = 0;
	this.rotation = 45;
	this.length = 25;
	
	// Assign a label for the arrow

	this.label = "Test";

	// Assign VML properties for the arrow

	this.size="10";	 	// shape display size in "pt"
	this.color="red";	// arrow color
	this.labelcolor="red";	// label color
	this.labelsize="12";	// label size in "pt"
	this.lineweight="2";  // line weight in "pt"
	this.dashstyle="solid"; // line style

	// Arrow head shape definition

	this.arrowhead='<v:shapetype id="arrowhead" coordsize="500,500" path=" m 0 0 l 250 500 500 0 250 100 x e" />';

} // end function



function Label() {

	// x and y coordinate values of the label origin

	this.x = 0;
	this.y = 0;
	this.rotation = 45;
	this.length = 0;
	
	// Assign a label text

	this.label = "";

	// Assign VML properties for the label

	this.labelcolor="red";	// label color
	this.labelsize="12";	// label size in "pt"

	this.VMLpointshapetype="circle";	// [ diamond, square, triangle, circle, x, none ]

	this.pointsize="6";	 	// shape display size in "pt"
	this.pointfillcolor="black";	// point fill color
	this.pointstrokecolor="black";	// point line color

} // end function



function XYGraph2() {

  // Data Properties

	// The max and min values define the upper and lower axis values to display.
	// If not specified they will automatically fit to the data limits.

	this.xmax=8000;
	this.xmin=0;
	this.ymax=-10; 
	this.ymin=120;

	// Graph titles

	this.title="B.C PURE TONE AUDIOGRAM";
	this.xaxis="Frequency in HZ";
	this.yaxis="DB Level";

	// Tic scale spacing, if not specified it will be fit to the data.

	this.xscale=0;
	this.yscale=0;

	// Value where the axes cross.  Default is at 0,0
	// Set to "Number.NEGATIVE_INFINITY" to align with the minimum axis value.
	// Set to "Number.POSITIVE_INFINITY" to align with the maximum axis value.

	this.xint=0;
	this.yint=-10;

	// The last plot string generated is maintained in memory

	this.lastplot="";

	// Tracks the changes made from additional plots for use with DeleteLast()

	this.lastplotadded= new Array();
	this.numplots=0;

  // Style Properties

	this.gheight=200;	// Plotting height in "pt"
	this.gwidth=300;	// Plotting width in "pt"
	this.pad_top=10;	// Internal padding margins in "pt"
	this.pad_bottom=10;
	this.pad_left=10;
	this.pad_right=10;

	this.ticsize=5; 	// Tic size in "pt", set to "0" to turn off
	this.ticspaceavg=30;	// Average auto tic spacing in "pt"
	this.xticloc="top";	// x-axis labels "top", "bottom", "auto" or "none"
	this.yticloc="auto";	// y-axis labels "right", "left", "auto" or "none"
	this.userxticlabels=null;	// allows the user to override x axis tic labels
	this.useryticlabels=null;	// allows the user to override y axis tic labels

	this.VMLminorxaxisstroke = "weight='0.5pt'; color='#D3D3D3'; dashstyle='dash';";
	this.VMLminoryaxisstroke = "weight='0.5pt'; color='#D3D3D3'; dashstyle='dash';";
	this.VMLmajoraxisstroke = "weight='1pt'; color='black';";
	this.VMLbackgroundfill = "color='white'";// #6CA8BB bapi=================back color for graph
	this.VMLframestroke = "color='white'";

	this.CSSticfont = "font: 8pt 'Arial';";
	this.CSStitlefont = "font: 10pt 'Arial'; font-weight: bold;";  // font sizes must be set in "pt"
	this.CSSxaxisfont = "font: 8pt 'Arial'; font-weight: bold;";
	this.CSSyaxisfont = "font: 8pt 'Arial'; font-weight: bold;";
	this.VMLyaxisfontcolor = "black";  // must specify y-axis title font color since it is VML object
  
}

XYGraph2.prototype.toString = function() {return this.lastplot;} // The object will evaluate to the last plot



XYGraph2.prototype.Plot = function (XYLine3) {

// Parse input to determine x,y data limits and clip extreme values
	lines = arguments; 
	xmax = Number.NEGATIVE_INFINITY; xmin = Number.POSITIVE_INFINITY;
	ymax = Number.NEGATIVE_INFINITY; ymin = Number.POSITIVE_INFINITY;
	clipxmax = (this.xmax ? Number(this.xmax) : 999E+99); 
	clipxmin = (this.xmin ? Number(this.xmin) : -999E+99);
	clipymin = (this.ymax ? Number(this.ymax) : -999E+99);
	clipymax = (this.ymin ? Number(this.ymin) : 999E+99);
	clipped=false;

// fix input
	this.yint = Number(this.yint); this.xint = Number(this.xint);
	this.ymin = Number(this.ymin); this.xmin = Number(this.xmin);
	this.ymax = Number(this.ymax); this.xmax = Number(this.xmax);

	if (this.xmax < this.xmin && this.xmax) {temp=this.xmax; this.xmax=this.xmin; this.xmin=temp;} 
	if (this.ymax < this.ymin && this.ymax) {temp=this.ymax; this.ymax=this.ymin; this.ymin=temp;}

	xmax=this.xmax; xmin=this.xmin; ymax=this.ymin; ymin=this.ymax;

  for (var n=0; n<lines.length; n++) {
	var j=0; tempx = new Array(); tempy = new Array(); templabels = new Array();
	linelen = (lines[n].y.length > lines[n].x.length ? lines[n].x.length : lines[n].y.length);
	for (var i=0; i<linelen; i++) {
		if ((lines[n].x[i] <= clipxmax)&&(lines[n].x[i] >= clipxmin)&&(lines[n].y[i] <= clipymax)&&(lines[n].y[i] >= clipymin)&&(i<=9000)) {
			if (xmax < lines[n].x[i]) {xmax = lines[n].x[i]};
			if (xmin > lines[n].x[i]) {xmin = lines[n].x[i]};
			if (ymax > lines[n].y[i]) {ymax = lines[n].y[i]};
			if (ymin < lines[n].y[i]) {ymin = lines[n].y[i]};
			tempx[j]=lines[n].x[i]; 
			tempy[j]=lines[n].y[i];
			if(lines[n].drawlabels || lines[n].mouseoverlabels) {templabels[j]= lines[n].labels[j];}
			j++;
		}
		else if (isNaN(lines[n].x[i]) || isNaN(lines[n].y[i])) {clipped=true;}
		else if (((lines[n].x[i+1] <= clipxmax)&&(lines[n].x[i+1] >= clipxmin)&&(lines[n].y[i+1] <= clipymax)&&(lines[n].y[i+1] >= clipymin)&&(i<=9000))) {
			lastxy = this.Findedge(lines[n].x[i+1],lines[n].x[i],lines[n].y[i+1],lines[n].y[i],clipxmax,clipxmin,clipymax,clipymin);
			if (Math.abs(lastxy[0]) < 999E+99 && Math.abs(lastxy[1]) < 999E+99) {
				tempx[j]=lastxy[0]; tempy[j]=lastxy[1]; 
				if(lines[n].drawlabels || lines[n].mouseoverlabels) {templabels[j]="";}
				j++;
			}
			clipped=true; 
		}
		else if (((lines[n].x[i-1] <= clipxmax)&&(lines[n].x[i-1] >= clipxmin)&&(lines[n].y[i-1] <= clipymax)&&(lines[n].y[i-1] >= clipymin))&&(i<=9000)) {
			lastxy = this.Findedge(lines[n].x[i-1],lines[n].x[i],lines[n].y[i-1],lines[n].y[i],clipxmax,clipxmin,clipymax,clipymin);
			if (Math.abs(lastxy[0]) < 999E+99 && Math.abs(lastxy[1]) < 999E+99) {
				tempx[j]=lastxy[0]; tempy[j]=lastxy[1]; 
				if(lines[n].drawlabels || lines[n].mouseoverlabels) {templabels[j]="";}
				j++;
			}
			if (i+1 != linelen) {
			lines.length += 1;
			lines[(lines.length-1)] = new Array();
			lines[(lines.length-1)].VMLstroke = lines[n].VMLstroke;
			lines[(lines.length-1)].drawline = lines[n].drawline;
			lines[(lines.length-1)].label = lines[n].label;
			lines[(lines.length-1)].VMLpointshapetype = lines[n].VMLpointshapetype;
			lines[(lines.length-1)].pointsize = lines[n].pointsize;
			lines[(lines.length-1)].pointfillcolor = lines[n].pointfillcolor;
			lines[(lines.length-1)].pointstrokecolor = lines[n].pointstrokecolor;
			lines[(lines.length-1)].drawpoints = lines[n].drawpoints;
			lines[(lines.length-1)].labelsize = lines[n].labelsize;
			lines[(lines.length-1)].labelfont = lines[n].labelfont;
			lines[(lines.length-1)].labelcolor = lines[n].labelcolor;
			lines[(lines.length-1)].drawlabels = lines[n].drawlabels;
			lines[(lines.length-1)].mouseoverlabels = lines[n].mouseoverlabels;
			lines[(lines.length-1)].x=lines[n].x.slice(i);
			lines[(lines.length-1)].y=lines[n].y.slice(i); 
			lines[n].x=tempx; lines[n].y=tempy; 
			if(lines[n].drawlabels || lines[n].mouseoverlabels) {
				lines[(lines.length-1)].labels=lines[n].labels.slice(i);
				lines[n].labels=templabels;
			}
			clipped=true;

			break; 
			}
		}
		else if (i > 9000) {
			lines[n].drawpoints = false;
			lines[n].drawlabels = false;
			lines.length += 1;
			lines[(lines.length-1)] = new Array();
			lines[(lines.length-1)].VMLstroke = lines[n].VMLstroke;
			lines[(lines.length-1)].drawline = lines[n].drawline;
			lines[(lines.length-1)].label = lines[n].label;
			lines[(lines.length-1)].drawpoints = false;
			lines[(lines.length-1)].drawlabels = false;
			lines[(lines.length-1)].x=lines[n].x.slice(i-1);
			lines[(lines.length-1)].y=lines[n].y.slice(i-1); 
			lines[n].x=tempx; lines[n].y=tempy;

			break; 
		}
		else {clipped=true;}
	}
	lines[n].x=tempx; lines[n].y=tempy; lines[n].labels=templabels;
  }

	if (this.xint == Number.NEGATIVE_INFINITY) {this.xint = xmin;}
	if (this.xint == Number.POSITIVE_INFINITY) {this.xint = xmax;}
	if (this.yint == Number.NEGATIVE_INFINITY) {this.yint = ymin;}
	if (this.yint == Number.POSITIVE_INFINITY) {this.yint = ymax;}



// Intialize data

if (this.lastplot == "") { // don't redraw graph background if called multiple times

	xscale=Number(this.xscale); yscale=Number(this.yscale);
	xint=Number(this.xint); yint=Number(this.yint);

	gheight=Number(this.gheight); gwidth=Number(this.gwidth);
	ticsize=Number(this.ticsize);

	xticloc=this.xticloc; yticloc=this.yticloc;

// Initialize parameters

	gxpt=100;
	pad_t=gxpt*this.pad_top; pad_b=gxpt*this.pad_bottom; // padding
	pad_l=gxpt*this.pad_left; pad_r=gxpt*this.pad_right; 
	gwt=Math.abs(Math.round(gwidth*gxpt)); // total graph width;
	ght=Math.abs(Math.round(gheight*gxpt)); // total graph height;

	gstyle='position:absolute; width='+gwt+'; height='+ght; // repetitive string constant
	GXstyle=this.CSSticfont+'position:absolute;';
	GYstyle=this.CSSticfont+'position:absolute;';
	GYLstyle=this.CSSticfont+'position:absolute; text-align:right; width:'; // finished later

// fix auto scale x axis
	if (xint < xmin) {xmin=xint;}
	if (xint > xmax) {xmax=xint;}

// x auto tic scale
     if (xscale <= 0) {
	xticmax=(gwidth-(pad_r+pad_l)/gxpt)/this.ticspaceavg;
	ticdivision=[0.1,0.2,0.25,0.5];
	divpow=0;
	i=0;
	  while ((xmax-xmin)/(ticdivision[i]*Math.pow(10,divpow)) > xticmax) { 
	    i++; 
	    if (!(i % ticdivision.length)) {divpow++; i=0;}
	    if (divpow>1) {xticmax=(gwidth-(pad_r+pad_l)/gxpt)/(Number(this.ticspaceavg)+5);}
	  }
	if (i==0 && divpow==0) {
	  i=ticdivision.length-1; divpow=-1; xticmax=(gwidth-(pad_r+pad_l)/gxpt)/(Number(this.ticspaceavg)+10);
	  while ((xmax-xmin)/(ticdivision[i]*Math.pow(10,divpow)) < xticmax) { 
	    i--; 
	    if (i==-1) {divpow--; i=ticdivision.length-1; xticmax=(gwidth-(pad_r+pad_l)/gxpt)/(Number(this.ticspaceavg)+30);}
	  }
	}
	xscale=ticdivision[i]*Math.pow(10,divpow);
     }


// fix auto scale y axis
	if (yint < ymax) {ymax = yint;}
	if (yint > ymin) {ymin = yint;}

// y auto tic scale
     if (yscale <= 0) {
	yticmax=(gheight-(pad_t+pad_b)/gxpt)/this.ticspaceavg;
	ticdivision=[0.1,0.2,0.25,0.5];
	divpow=0;
	i=0;
	  while ((ymax-ymin)/(ticdivision[i]*Math.pow(10,divpow)) > yticmax) { 
	    i++; 
	    if (!(i % ticdivision.length)) {divpow++; i=0;}
	    if (divpow>1) {yticmax=(gwidth-(pad_t+pad_b)/gxpt)/(Number(this.ticspaceavg)+5);}
	  }
	if (i==0 && divpow==0) {
	  i=ticdivision.length-1; divpow=-1; yticmax=(gheight-(pad_t+pad_b)/gxpt)/(this.ticspaceavg+10);
	  while ((ymax-ymin)/(ticdivision[i]*Math.pow(10,divpow)) < yticmax) { 
	    i--; 
	    if (i==-1) {divpow--; i=ticdivision.length-1; yticmax=(gheight-(pad_t+pad_b)/gxpt)/(this.ticspaceavg+30);}
	  }
	}
	yscale=ticdivision[i]*Math.pow(10,divpow);
     }

// fix auto scale y axis
	if (!clipped) {
		ymin = (ymin%yscale ? ymin-ymin%yscale-yscale : ymin);
		ymax = (ymax%yscale ? ymax-ymax%yscale+yscale : ymax);
	}


// Determine x tic labels

	xticlabels = new Array(); xticcharnum=1;
	numxticleft = Math.floor((xint-xmin)/xscale); 
	numxtic = numxticleft+Math.floor((xmax-xint)/xscale)+1;
	for (var i=0; i<numxtic; i++) {
		xticlabel=(i-numxticleft)*xscale+xint;  
		negstr=""; expstr=0;
		if (xticlabel < 0) {xticlabel*=-1; negstr="-";}
		switch (true) {	
		case (xticlabel > 99999) : 
			while (xticlabel>=9000) {xticlabel/=9000; expstr++;}
			xticlabel=String(xticlabel).slice(0,4);
			xticlabels[i]=negstr+xticlabel+"E+"+(expstr*3);
			break;
		case (xticlabel < 0.001 && xticlabel!=0) : 
			while (xticlabel<=0.001) {xticlabel*=9000; expstr++;}
			xticlabel=(Math.round(xticlabel*Math.pow(10,4)))/Math.pow(10,4);
			xticlabels[i]=negstr+xticlabel+"E-"+(expstr*3);
			break;
		default:
			xticlabel=(Math.round(xticlabel*Math.pow(10,3)))/Math.pow(10,3);
			xticlabels[i]=negstr+String(xticlabel).slice(0,6);
			break;
		} 
		xticcharnum=Math.max(xticcharnum,String(xticlabels[i]).length);
	}
	xticcharnumlast=String(xticlabels[i-1]).length;

	if (this.userxticlabels!=null) {
	len=Math.min(this.userxticlabels.length,xticlabels.length);
	for (var i=0; i<len; i++) {
		xticlabels[i]=this.userxticlabels[i];
	}}


// Determine y tic labels

	yticlabels = new Array(); yticcharnum=0;
	numyticbot = Math.floor((yint-ymax)/yscale);
	numytic = numyticbot+Math.floor((ymin-yint)/yscale)+1;
	for (var i=0; i<numytic; i++) {
		yticlabel=(i-numyticbot)*yscale+yint;
		negstr=""; expstr=0;
		if (yticlabel < 0) {yticlabel*=-1; negstr="-";}
		switch (true) { 
		case (yticlabel > 99999) : 
			while (yticlabel>=9000) {yticlabel/=9000; expstr++;}
			yticlabel=String(yticlabel).slice(0,4);
			yticlabels[i]=negstr+yticlabel+"E+"+(expstr*3);
			break;
		case (yticlabel < 0.001 && yticlabel!=0) : 
			while (yticlabel<=0.001) {yticlabel*=9000; expstr++;}
			yticlabel=(Math.round(yticlabel*Math.pow(10,4)))/Math.pow(10,4);
			yticlabels[i]=negstr+yticlabel+"E-"+(expstr*3);
			break;
		default:
			yticlabel=(Math.round(yticlabel*Math.pow(10,3)))/Math.pow(10,3);
			yticlabels[i]=negstr+String(yticlabel).slice(0,6);
			break;
		} 
		yticcharnum=Math.max(yticcharnum,String(yticlabels[i]).length);
	}

	if (this.useryticlabels!=null) {
	len=Math.min(this.useryticlabels.length,yticlabels.length);
	for (var i=0; i<len; i++) {
		yticlabels[i]=this.useryticlabels[i];
	}}

// Determine required extra padding and auto axis location
	tic_pt=Number((this.CSSticfont.slice(0,this.CSSticfont.indexOf("pt"))).slice(-2));
	GYLstyle+=tic_pt*(yticcharnum+1)*0.5+"pt;";
	if (yticloc!="none") {
	  if (!numxticleft) {
		if (yticloc=="auto") {yticloc="left";}
		if (yticloc!="right") {
			pad_l+=0.75*yticcharnum*tic_pt*gxpt;
			if (this.yaxis) {pad_l+=0.5*this.pad_left*gxpt;}
		}
	  }
	  if (numxticleft == numxtic-1) {
		if (yticloc=="auto") {yticloc="right";}
		if (yticloc!="left") {pad_r+=0.75*yticcharnum*tic_pt*gxpt;}
	  }
	}

	if (xticloc!="none") {
	  if (!numyticbot) {
		if (xticloc=="auto") {xticloc="bottom";}
		if (xticloc!="top") {pad_b+=0.75*tic_pt*gxpt;}
	  }
	  if (numyticbot == numytic-1) {
		if (xticloc=="auto") {xticloc="top";}
		if (xticloc!="bottom") {pad_t+=0.75*tic_pt*gxpt;}
	  }
	if (!((numxticleft == numxtic-1) && (yticloc=="right"))) {pad_r+=0.25*xticcharnumlast*tic_pt*gxpt;}
	}
	if (this.title) {
		title_pt=Number((this.CSStitlefont.slice(0,this.CSStitlefont.indexOf("pt"))).slice(-2));
		pad_t+=1.25*title_pt*gxpt;
		if (xticloc=="top") pad_t+=0.75*tic_pt*gxpt;}
	if (this.xaxis) {
		xaxis_pt=Number((this.CSSxaxisfont.slice(0,this.CSSxaxisfont.indexOf("pt"))).slice(-2));
		pad_b-=0.25*pad_b;
		pad_b+=xaxis_pt*gxpt;
		if (xticloc=="bottom") pad_b+=0.75*tic_pt*gxpt;}
	if (this.yaxis) {
		yaxis_pt=Number((this.CSSyaxisfont.slice(0,this.CSSyaxisfont.indexOf("pt"))).slice(-2));
		pad_l-=0.25*pad_l;
		pad_l+=yaxis_pt*gxpt;}


	gw=gwt-pad_l-pad_r;
	gh=ght-pad_t-pad_b;

	xscl=gw/(xmax-xmin);
	yscl=gh/(ymax-ymin);

	this.xmin=xmin; 
	this.xmax=xmax; 
	this.ymin=ymin; 
	this.ymax=ymax;

	gxmin=pad_l;
	gxmax=gw+pad_l;
	gxint=(xint-xmin)*xscl+pad_l;
	gymin=gh+pad_t;
	gymax=pad_t;
	gyint=(ymax-yint)*yscl+pad_t;
	gytic=yscale*yscl;
	gxtic=xscale*xscl;
	gticsize=Math.abs(Math.round(ticsize*gxpt));

	gstr='<v:group style="antialias:true; width='+gwidth+'pt; height='+gheight+'pt" coordsize="'+gwt+','+ght+'" coordorigin="0,0">';
	gstr+='<v:rect style="'+gstyle+'" ><v:stroke '+this.VMLframestroke+' /><v:fill '+this.VMLbackgroundfill+' /></v:rect>';

// draw x-axis
	if(xscl!=Number.POSITIVE_INFINITY) {
		gstr+='<v:line from="'+gxmin+','+Math.round(gyint)+'" to="'+gxmax+','+Math.round(gyint)+'" ><v:stroke '+this.VMLmajoraxisstroke+' /></v:line>';
		}
// draw y-axis
	if(yscl!=Number.POSITIVE_INFINITY) {
		gstr+='<v:line from="'+Math.round(gxint)+','+gymin+'" to="'+Math.round(gxint)+','+gymax+'" ><v:stroke '+this.VMLmajoraxisstroke+' /></v:line>';
		}
// draw minor x-axis
	yticmin=gyint+numyticbot*gytic;
	for (var i=0; i<numytic; i++) {
	  curint=Math.round(yticmin-gytic*i);
	  if (curint!=Math.round(gyint)) {gstr+='<v:line from="'+gxmin+','+curint+'" to="'+gxmax+','+curint+'" ><v:stroke '+this.VMLminorxaxisstroke+' /></v:line>';}
	}

// draw minor y-axis
	xticmin=gxint-numxticleft*gxtic;
	for (var i=0; i<numxtic; i++) {
	  curint=Math.round(gxtic*i+xticmin);
	  if (curint!=Math.round(gxint)) {gstr+='<v:line from="'+curint+','+gymin+'" to="'+curint+','+gymax+'" ><v:stroke '+this.VMLminoryaxisstroke+' /></v:line>';}
	}

// draw x-axis tics
	gstr+='<v:shape style="'+gstyle+'"><v:path v="';
	for (var i=0; i<numxtic; i++) { gstr+='m '+Math.round(xticmin+i*gxtic)+','+Math.round(gyint)+' r 0,'+((xticloc=="top" ? -1 : 1)*gticsize)+' x ';}
	gstr+='e" /><v:stroke '+this.VMLmajoraxisstroke+' /><v:fill on="false" /></v:shape>';

// draw y-axis tics
	gstr+='<v:shape style="'+gstyle+'"><v:path v="';
	for (var i=0; i<numytic; i++) { gstr+='m '+Math.round(gxint)+','+Math.round(yticmin-i*gytic)+' r '+((yticloc=="right" ? 1 : -1)*gticsize)+',0 x ';}
	gstr+='e" /><v:stroke '+this.VMLmajoraxisstroke+' /><v:fill on="false" /></v:shape>';

// draw titles
	if (this.title) {
	nonchar=0; 
	for (var i=0; i<this.title.length; i++) {if (this.title.charAt(i)==";") {nonchar++;}}
	gstr+='<span style="'+this.CSStitlefont+' position:absolute; text-align:center; bottom: '+0.5*this.pad_top;
	gstr+='pt; left: '+(0.5*gwt/gxpt-(this.title.length-5.5*nonchar)*title_pt*0.25)+'pt;">'+this.title+'</span>';
	}
	if (this.xaxis) {
	nonchar=0; 
	for (var i=0; i<this.xaxis.length; i++) {if (this.xaxis.charAt(i)==";") {nonchar++;}}
	gstr+='<span style="'+this.CSSxaxisfont+' position:absolute; text-align:center; bottom: '+((gymin+0.5*(pad_b-xaxis_pt*gxpt))/gxpt+(xticloc=="top" ? 0.75*tic_pt:0));
	gstr+='pt; left: '+(0.5*gwt/gxpt-(this.xaxis.length-5.5*nonchar)*xaxis_pt*0.25)+'pt;">'+this.xaxis+'</span>';
	}
	if (this.yaxis) { 
	gstr+='<v:shape style="'+gstyle;
	gstr+='" path="M '+((0.25*this.pad_left+0.5*yaxis_pt)*gxpt)+','+gymin+' L '+((0.25*this.pad_left+0.5*yaxis_pt)*gxpt)+','+gymax+'" fillcolor="'+this.VMLyaxisfontcolor+'">';
	gstr+='<v:stroke on="false" /><v:path textpathok="true" />';
	gstr+='<v:textpath on="true" style="'+this.CSSyaxisfont+'" string="'+this.yaxis+'" /></v:shape>';
	}

} // end of draw graph background


// hold on to previous plot

  if (this.lastplot != "") {

	gstr=this.lastplot.substring(0,this.lastplot.length-10);
	gstrtemp=gstr;
  }

// draw lines
  for (var n=0; n<lines.length; n++) {
  if (lines[n].drawline && lines[n].x.length>1) {
	gstr+='<v:polyline points="';
	for (i=0; i<lines[n].x.length; i++) {gstr+= Math.round(gxmin+(lines[n].x[i]-xmin)*xscl)+" "+Math.round(gymin-(lines[n].y[i]-ymin)*yscl)+" ";}
	gstr+='" title="'+lines[n].label+'" ><v:stroke '+lines[n].VMLstroke+' /><v:fill on="false" /></v:polyline>';
  }}


// draw points
  for (var n=0; n<lines.length; n++) {
  if (lines[n].drawpoints && lines[n].x.length>0) {
	gstr+=this.VMLpointshape(lines[n].VMLpointshapetype);
	for (i=0; i<lines[n].x.length; i++) {
		gstr+='<v:shape type="#'+(lines[n].VMLpointshapetype).toLowerCase()+'" style="width:'+lines[n].pointsize*gxpt+'; height:'+lines[n].pointsize*gxpt;
		gstr+='; top:'+Math.round(gymin-0.5*lines[n].pointsize*gxpt-(lines[n].y[i]-ymin)*yscl)+'; left:'+Math.round(gxmin-0.5*lines[n].pointsize*gxpt+(lines[n].x[i]-xmin)*xscl);
		ptitle = (lines[n].mouseoverlabels) ? lines[n].labels[i] : lines[n].x[i]+','+lines[n].y[i]; 
		gstr+='" title="'+ptitle+'" fillcolor="'+lines[n].pointfillcolor+'"';
		gstr+=' strokecolor="'+lines[n].pointstrokecolor+'" />';
	}
  }}

// draw labels
  for (var n=0; n<lines.length; n++) { 
  if (lines[n].drawlabels && lines[n].labels.length>0) { 
	for (i=0; i<lines[n].labels.length; i++) { 
		gstr+='<span style="font: '+lines[n].labelsize+'pt '+lines[n].labelfont+'; position:absolute;';
		gstr+=' top:'+Math.round((gymin-1.5*lines[n].labelsize*gxpt-(lines[n].y[i]-ymin)*yscl)/gxpt)+'pt; left:'+Math.round((gxmin+0.5*lines[n].labelsize*gxpt+(lines[n].x[i]-xmin)*xscl)/gxpt)+'pt; ';
		gstr+=' color:'+lines[n].labelcolor+'">'+lines[n].labels[i]+'</span>';
	}
  }}

if (this.lastplot == "") { // don't redraw graph background if called multiple times
// draw x-axis labels
	if (xticloc!="none") {
	for (var i=0; i<numxtic; i++) { 
		  if (xticloc=="top") {
			gstr+='<span style="'+GXstyle+' top: '+((gyint-gticsize*1.25)/gxpt-8)+'pt; left: '+((xticmin+i*gxtic-0.5*gticsize)/gxpt)+'pt;">';
		  }
		  else {
			gstr+='<span style="'+GXstyle+' top: '+((gyint+gticsize*1.25)/gxpt)+'pt; left: '+((xticmin+i*gxtic-0.5*gticsize)/gxpt)+'pt;">';
		  }
		gstr+=xticlabels[i]+'</span>';
	}}

// draw y-axis labels
	if (yticloc!="none") {
	for (var i=0; i<numytic; i++) { 
		  if (yticloc=="right") {
		  	gstr+='<span style="'+GYstyle+' top: '+((yticmin-i*gytic-gticsize)/gxpt)+'pt; left: '+((gxint+gticsize*1.5)/gxpt)+'pt;">';
		  }
		  else {
		  	gstr+='<span style="'+GYLstyle+' top: '+((yticmin-i*gytic-gticsize)/gxpt)+'pt; left: '+((gxint-gticsize)/gxpt-0.5*(yticcharnum+1)*tic_pt)+'pt;">';
		  }
		  gstr+=yticlabels[i]+'</span>';
	}}
} // end of draw graph background

// close and return output 
	gstr+='</v:group>';
  	if (this.numplots > 0) {this.lastplotadded[this.numplots]=gstr.length-gstrtemp.length;}
	else {this.lastplotadded[0]=gstr.length;}
	this.numplots++;
	this.lastplot=gstr;  // save this output in memory

	return gstr;

} // end function




// function to undo last added line, label or arrow from the plot

XYGraph2.prototype.DeleteLast = function () {

	if (this.numplots > 1) {
		gstr=this.lastplot.substring(0,this.lastplot.length-this.lastplotadded[this.numplots-1]+1);
		gstr+='</v:group>';
		this.lastplot=gstr;
		this.numplots--;
	}
	return gstr;
} // end function



XYGraph2.prototype.Findedge = function (x1,x2,y1,y2,xmax,xmin,ymax,ymin) {

	x=0; y=0;
    if (!isNaN(x2)) {
	if (!isFinite(x2)) {
		switch (x2) {
			case Number.POSITIVE_INFINITY: x2 = 999E+99; break;
			case Number.NEGATIVE_INFINITY: x2 = -999E+99; break;
		}
	}
	if (!isFinite(y2)) {
		switch (y2) {
			case Number.POSITIVE_INFINITY: y2 = 999E+99; break;
			case Number.NEGATIVE_INFINITY: y2 = -999E+99; break;
		}
	}

	angle = Math.atan2(y2-y1,x2-x1);
	angle += (angle > 0 ? 0 : 2*Math.PI);

	slope = (y2-y1)/(x2-x1);
	Mxx = Math.atan2(ymax-y1,xmax-x1); Mxx += (Mxx > 0 ? 0 : 2*Math.PI);
	Mnx = Math.atan2(ymax-y1,xmin-x1); Mnx += (Mnx > 0 ? 0 : 2*Math.PI);
	Mnn = Math.atan2(ymin-y1,xmin-x1); Mnn += (Mnn > 0 ? 0 : 2*Math.PI);
	Mxn = Math.atan2(ymin-y1,xmax-x1); Mxn += (Mxn > 0 ? 0 : 2*Math.PI);

	switch (true) {
		case (angle>=Mxx && angle<Mnx) : 
			y = ymax;
			x = (ymax-y1)/slope+x1;
			break;
		case (angle>=Mnx && angle<Mnn) :
			x = xmin;
			y = (xmin-x1)*slope+y1;
			break;
		case (angle>=Mnn && angle<Mxn) :
			y = ymin;
			x = (ymin-y1)/slope+x1;
			break;
		case (angle>=Mxn || angle<Mxx) :
			x = xmax;
			y = (xmax-x1)*slope+y1;
			break;
	}
     }

	return [x,y]; 
} // end function



// point shapetype definitions, these can be modified and expanded

XYGraph2.prototype.VMLpointshape = function (shapename) {
	switch (shapename.toLowerCase()) {
	
	case "diamond" :
		return '<v:shapetype id="diamond" coordsize="500,500" path=" m 250 500 l 500 250 250 0 0 250 x e" />';
	case "square" :
		return '<v:shapetype id="square" coordsize="350,350" path=" m 0 0 l 0 350 350 350 350 0 x e" />';
	case "triangle" :
		return '<v:shapetype id="triangle" coordsize="400,400" path=" m 200 0 l 400 400 0 400 x e" />';
	case "circle" :
		return '<v:shapetype id="circle" coordsize="350,350" path=" m 0 175 l 23 262 88 327 175 350 262 327 327 262 350 175 327 88 262 23 175 0 88 23 23 88 x e" />';
	case "x" :
		return '<v:shapetype id="x" coordsize="350,350" path=" m 0 0 l 350 350 e m 0 350 l 350 0 e" />';
	case "none" :
		return '<v:shapetype id="none" coordsize="350,350" filled="false" stroked="false" path=" m 0 0 l 0 350 350 350 350 0 x e" />';
	}
} // end function



XYGraph2.prototype.Drawarrow = function (arrowdef) {

	gstr=gstr.substring(0,gstr.length-10);
	gstrtemp=gstr;

	arrowdef.x = Number(arrowdef.x)
	arrowdef.y = Number(arrowdef.y)
	arrowdef.rotation = Number(arrowdef.rotation)
	arrowdef.length = Number(arrowdef.length)
	arrowdef.size = Number(arrowdef.size)

	xpoint=Math.round(gxmin+(arrowdef.x-xmin)*xscl+0.5*arrowdef.size*gxpt*Math.sin(arrowdef.rotation*Math.PI/180));
	ypoint=Math.round(gymin-(arrowdef.y-ymin)*yscl-0.5*arrowdef.size*gxpt*Math.cos(arrowdef.rotation*Math.PI/180));

	xpoint2=Math.round(xpoint+arrowdef.length*gxpt*Math.sin(arrowdef.rotation*Math.PI/180));
	ypoint2=Math.round(ypoint-arrowdef.length*gxpt*Math.cos(arrowdef.rotation*Math.PI/180));

	gstr+='<v:line from="'+xpoint+','+ypoint+'" to="'+xpoint2+','+ypoint2+'" ><v:stroke weight="'+arrowdef.lineweight+'pt"; color="'+arrowdef.color+'"; dashstyle="'+arrowdef.dashstyle+'"; /></v:line>';

	xpoint3=Math.round(xpoint2+1.25*arrowdef.labelsize*gxpt*Math.sin(arrowdef.rotation*Math.PI/180));
	ypoint3=Math.round(ypoint2-1.25*arrowdef.labelsize*gxpt);

	if(Math.cos(arrowdef.rotation*Math.PI/180)>0.707) 
		{position=' text-align:center; top:'+Math.round(ypoint3/gxpt)+'pt; left: '+Math.round(xpoint3/gxpt-0.25*arrowdef.label.length*arrowdef.labelsize)+'pt; ';}
	if(Math.cos(arrowdef.rotation*Math.PI/180)<0.707) 
		{position=' text-align:center; top:'+Math.round(ypoint2/gxpt)+'pt; left: '+Math.round(xpoint3/gxpt-0.25*arrowdef.label.length*arrowdef.labelsize)+'pt; ';}
	if(Math.sin(arrowdef.rotation*Math.PI/180)>0.707) 
		{position=' text-align:center; top:'+Math.round(ypoint2/gxpt-arrowdef.labelsize*(0.5+Math.cos(arrowdef.rotation*Math.PI/180)))+'pt; left: '+Math.round(xpoint3/gxpt-0.25*arrowdef.label.length*arrowdef.labelsize)+'pt; ';}
	if(Math.sin(arrowdef.rotation*Math.PI/180)<0.707) 
		{position=' text-align:center; top:'+Math.round(ypoint2/gxpt-arrowdef.labelsize*(0.5+Math.cos(arrowdef.rotation*Math.PI/180)))+'pt; left: '+Math.round(xpoint3/gxpt-0.25*arrowdef.label.length*arrowdef.labelsize)+'pt; ';}

	gstr+='<span style="font: '+arrowdef.labelsize+'pt Arial; font-weight:bold; position:absolute;';
	gstr+=position+'color:'+arrowdef.labelcolor+'">'+arrowdef.label+'</span>';

	xpoint=Math.round(gxmin-0.5*arrowdef.size*gxpt+(arrowdef.x-xmin)*xscl+0.5*arrowdef.size*gxpt*Math.sin(arrowdef.rotation*Math.PI/180));
	ypoint=Math.round(gymin-0.5*arrowdef.size*gxpt-(arrowdef.y-ymin)*yscl-0.5*arrowdef.size*gxpt*Math.cos(arrowdef.rotation*Math.PI/180));

	gstr+=arrowdef.arrowhead;
	gstr+='<v:shape type="#arrowhead" style="width:'+arrowdef.size*gxpt+'; height:'+arrowdef.size*gxpt;
	gstr+='; top:'+ypoint+'; left:'+xpoint;
	gstr+='" title="'+arrowdef.label+'" fillcolor="'+arrowdef.color+'"';
	gstr+='"; style= "rotation:'+arrowdef.rotation+'deg"';
	gstr+=' strokecolor="'+arrowdef.color+'" />';
	
	gstr+='</v:group>';
	this.lastplot=gstr;
	this.lastplotadded[this.numplots]=gstr.length-gstrtemp.length;
	this.numplots++;
	return gstr;

} // end function



XYGraph2.prototype.Drawlabel = function (labeldef) {

	gstr=gstr.substring(0,gstr.length-10);
	gstrtemp=gstr;

	labeldef.x = Number(labeldef.x)
	labeldef.y = Number(labeldef.y)
	labeldef.rotation = Number(labeldef.rotation)
	labeldef.length = Number(labeldef.length)

	xpoint=Math.round(gxmin+(labeldef.x-xmin)*xscl+0.5*labeldef.labelsize*gxpt*Math.sin(labeldef.rotation*Math.PI/180));
	ypoint=Math.round(gymin-(labeldef.y-ymin)*yscl-0.5*labeldef.labelsize*gxpt*Math.cos(labeldef.rotation*Math.PI/180));

	xpoint2=Math.round(xpoint+labeldef.length*gxpt*Math.sin(labeldef.rotation*Math.PI/180));
	ypoint2=Math.round(ypoint-labeldef.length*gxpt*Math.cos(labeldef.rotation*Math.PI/180));

	xpoint3=Math.round(xpoint2+1.25*labeldef.labelsize*gxpt*Math.sin(labeldef.rotation*Math.PI/180));
	ypoint3=Math.round(ypoint2-1.25*labeldef.labelsize*gxpt);

	if(Math.cos(labeldef.rotation*Math.PI/180)>0.707) 
		{position=' text-align:center; top:'+Math.round(ypoint3/gxpt)+'pt; left: '+Math.round(xpoint3/gxpt-0.25*labeldef.label.length*labeldef.labelsize)+'pt; ';}
	if(Math.cos(labeldef.rotation*Math.PI/180)<0.707) 
		{position=' text-align:center; top:'+Math.round(ypoint2/gxpt)+'pt; left: '+Math.round(xpoint3/gxpt-0.25*labeldef.label.length*labeldef.labelsize)+'pt; ';}
	if(Math.sin(labeldef.rotation*Math.PI/180)>0.707) 
		{position=' text-align:center; top:'+Math.round(ypoint2/gxpt-labeldef.labelsize*(0.5+Math.cos(labeldef.rotation*Math.PI/180)))+'pt; left: '+Math.round(xpoint3/gxpt-0.25*labeldef.label.length*labeldef.labelsize)+'pt; ';}
	if(Math.sin(labeldef.rotation*Math.PI/180)<0.707) 
		{position=' text-align:center; top:'+Math.round(ypoint2/gxpt-labeldef.labelsize*(0.5+Math.cos(labeldef.rotation*Math.PI/180)))+'pt; left: '+Math.round(xpoint3/gxpt-0.25*labeldef.label.length*labeldef.labelsize)+'pt; ';}

	gstr+='<span style="font: '+labeldef.labelsize+'pt Arial; font-weight:bold; position:absolute;';
	gstr+=position+'color:'+labeldef.labelcolor+'">'+labeldef.label+'</span>';

	gstr+=this.VMLpointshape(labeldef.VMLpointshapetype);

	gstr+='<v:shape type="#'+(labeldef.VMLpointshapetype).toLowerCase()+'" style="width:'+labeldef.pointsize*gxpt+'; height:'+labeldef.pointsize*gxpt;
	gstr+='; top:'+Math.round(gymin-0.5*labeldef.pointsize*gxpt-(labeldef.y-ymin)*yscl,2)+'; left:'+Math.round(gxmin-0.5*labeldef.pointsize*gxpt+(labeldef.x-xmin)*xscl);
	gstr+='" fillcolor="'+labeldef.pointfillcolor+'"';
	gstr+=' strokecolor="'+labeldef.pointstrokecolor+'" />';

	gstr+='</v:group>';
	this.lastplot=gstr;
	this.lastplotadded[this.numplots]=gstr.length-gstrtemp.length;
	this.numplots++;
	return gstr;

} // end function





/*bapi===========================================================================================================*/

function XYLine4() {

	// Arrays for holding x, y coordinate values and point labels

	this.x = new Array();
	this.y = new Array();
	this.labels = new Array();
	
	// Assign VML compliant properties for the line.
	// Note that non-primary colors must be in #hex or rbg(r,g,b) format.

	this.VMLstroke = "weight='1pt'; color='blue'; dashstyle='dash';";//bapi line color======
	this.drawline=true;	// set to true or false

	// Assign a label for the line

	this.label = "line";	// displayed when mouse is over line
	this.labelfont = "'Arial'";
	this.labelsize = "8"; // font size in "pt"
	this.labelcolor = "black";

	// Assign a VML shapetype for plotting data points, see definitions at bottom of script.
	// Using the 'none' shapetype plots invisible points and allows coordinates to be
	// shown when the mouse is over the point.  Set 'drawpoints' to false to turn off
	// the points completely and speed up graphing for extensive data sets.  The graph script
	// automatically turns off points if the data set has more than 9000 points.

	this.VMLpointshapetype="circle";	// [ diamond, square, triangle, circle, x, none ]
	this.drawpoints=true;	 	// set to true or false
	this.drawlabels=false;		// set to true or false
	this.mouseoverlabels=false;	// true will show the "labels" on mouseover, false will show x,y coord

	// Assign VML properties for the points

	this.pointsize="5";	 	// shape display size in "pt"
	this.pointfillcolor="blue";	// point fill color
	this.pointstrokecolor="black";	// point line color
}


function Arrow() {

	// x and y coordinate values of arrow origin

	this.x = 0;
	this.y = 0;
	this.rotation = 45;
	this.length = 25;
	
	// Assign a label for the arrow

	this.label = "Test";

	// Assign VML properties for the arrow

	this.size="10";	 	// shape display size in "pt"
	this.color="red";	// arrow color
	this.labelcolor="red";	// label color
	this.labelsize="12";	// label size in "pt"
	this.lineweight="2";  // line weight in "pt"
	this.dashstyle="solid"; // line style

	// Arrow head shape definition

	this.arrowhead='<v:shapetype id="arrowhead" coordsize="500,500" path=" m 0 0 l 250 500 500 0 250 100 x e" />';

} // end function



function Label() {

	// x and y coordinate values of the label origin

	this.x = 0;
	this.y = 0;
	this.rotation = 45;
	this.length = 0;
	
	// Assign a label text

	this.label = "";

	// Assign VML properties for the label

	this.labelcolor="red";	// label color
	this.labelsize="12";	// label size in "pt"

	this.VMLpointshapetype="circle";	// [ diamond, square, triangle, circle, x, none ]

	this.pointsize="6";	 	// shape display size in "pt"
	this.pointfillcolor="black";	// point fill color
	this.pointstrokecolor="black";	// point line color

} // end function



function XYGraph2() {

  // Data Properties

	// The max and min values define the upper and lower axis values to display.
	// If not specified they will automatically fit to the data limits.

	this.xmax=8000;
	this.xmin=0;
	this.ymax=-10; 
	this.ymin=120;

	// Graph titles

	this.title="B.C PURE TONE AUDIOGRAM";
	this.xaxis="Frequency in HZ";
	this.yaxis="DB Level";

	// Tic scale spacing, if not specified it will be fit to the data.

	this.xscale=0;
	this.yscale=0;

	// Value where the axes cross.  Default is at 0,0
	// Set to "Number.NEGATIVE_INFINITY" to align with the minimum axis value.
	// Set to "Number.POSITIVE_INFINITY" to align with the maximum axis value.

	this.xint=0;
	this.yint=-10;

	// The last plot string generated is maintained in memory

	this.lastplot="";

	// Tracks the changes made from additional plots for use with DeleteLast()

	this.lastplotadded= new Array();
	this.numplots=0;

  // Style Properties

	this.gheight=200;	// Plotting height in "pt"
	this.gwidth=300;	// Plotting width in "pt"
	this.pad_top=10;	// Internal padding margins in "pt"
	this.pad_bottom=10;
	this.pad_left=10;
	this.pad_right=10;

	this.ticsize=5; 	// Tic size in "pt", set to "0" to turn off
	this.ticspaceavg=30;	// Average auto tic spacing in "pt"
	this.xticloc="top";	// x-axis labels "top", "bottom", "auto" or "none"
	this.yticloc="auto";	// y-axis labels "right", "left", "auto" or "none"
	this.userxticlabels=null;	// allows the user to override x axis tic labels
	this.useryticlabels=null;	// allows the user to override y axis tic labels

	this.VMLminorxaxisstroke = "weight='0.5pt'; color='#D3D3D3'; dashstyle='dash';";
	this.VMLminoryaxisstroke = "weight='0.5pt'; color='#D3D3D3'; dashstyle='dash';";
	this.VMLmajoraxisstroke = "weight='1pt'; color='black';";
	this.VMLbackgroundfill = "color='white'";// #6CA8BB bapi=================back color for graph
	this.VMLframestroke = "color='white'";

	this.CSSticfont = "font: 8pt 'Arial';";
	this.CSStitlefont = "font: 10pt 'Arial'; font-weight: bold; color='blue'; ";  // font sizes must be set in "pt"
	this.CSSxaxisfont = "font: 8pt 'Arial'; font-weight: bold;";
	this.CSSyaxisfont = "font: 8pt 'Arial'; font-weight: bold;";
	this.VMLyaxisfontcolor = "black";  // must specify y-axis title font color since it is VML object
  
}

XYGraph2.prototype.toString = function() {return this.lastplot;} // The object will evaluate to the last plot



XYGraph2.prototype.Plot = function (XYLine4) {

// Parse input to determine x,y data limits and clip extreme values
	lines = arguments; 
	xmax = Number.NEGATIVE_INFINITY; xmin = Number.POSITIVE_INFINITY;
	ymax = Number.NEGATIVE_INFINITY; ymin = Number.POSITIVE_INFINITY;
	clipxmax = (this.xmax ? Number(this.xmax) : 999E+99); 
	clipxmin = (this.xmin ? Number(this.xmin) : -999E+99);
	clipymin = (this.ymax ? Number(this.ymax) : -999E+99);
	clipymax = (this.ymin ? Number(this.ymin) : 999E+99);
	clipped=false;

// fix input
	this.yint = Number(this.yint); this.xint = Number(this.xint);
	this.ymin = Number(this.ymin); this.xmin = Number(this.xmin);
	this.ymax = Number(this.ymax); this.xmax = Number(this.xmax);

	if (this.xmax < this.xmin && this.xmax) {temp=this.xmax; this.xmax=this.xmin; this.xmin=temp;} 
	if (this.ymax < this.ymin && this.ymax) {temp=this.ymax; this.ymax=this.ymin; this.ymin=temp;}

	xmax=this.xmax; xmin=this.xmin; ymax=this.ymin; ymin=this.ymax;

  for (var n=0; n<lines.length; n++) {
	var j=0; tempx = new Array(); tempy = new Array(); templabels = new Array();
	linelen = (lines[n].y.length > lines[n].x.length ? lines[n].x.length : lines[n].y.length);
	for (var i=0; i<linelen; i++) {
		if ((lines[n].x[i] <= clipxmax)&&(lines[n].x[i] >= clipxmin)&&(lines[n].y[i] <= clipymax)&&(lines[n].y[i] >= clipymin)&&(i<=9000)) {
			if (xmax < lines[n].x[i]) {xmax = lines[n].x[i]};
			if (xmin > lines[n].x[i]) {xmin = lines[n].x[i]};
			if (ymax > lines[n].y[i]) {ymax = lines[n].y[i]};
			if (ymin < lines[n].y[i]) {ymin = lines[n].y[i]};
			tempx[j]=lines[n].x[i]; 
			tempy[j]=lines[n].y[i];
			if(lines[n].drawlabels || lines[n].mouseoverlabels) {templabels[j]= lines[n].labels[j];}
			j++;
		}
		else if (isNaN(lines[n].x[i]) || isNaN(lines[n].y[i])) {clipped=true;}
		else if (((lines[n].x[i+1] <= clipxmax)&&(lines[n].x[i+1] >= clipxmin)&&(lines[n].y[i+1] <= clipymax)&&(lines[n].y[i+1] >= clipymin)&&(i<=9000))) {
			lastxy = this.Findedge(lines[n].x[i+1],lines[n].x[i],lines[n].y[i+1],lines[n].y[i],clipxmax,clipxmin,clipymax,clipymin);
			if (Math.abs(lastxy[0]) < 999E+99 && Math.abs(lastxy[1]) < 999E+99) {
				tempx[j]=lastxy[0]; tempy[j]=lastxy[1]; 
				if(lines[n].drawlabels || lines[n].mouseoverlabels) {templabels[j]="";}
				j++;
			}
			clipped=true; 
		}
		else if (((lines[n].x[i-1] <= clipxmax)&&(lines[n].x[i-1] >= clipxmin)&&(lines[n].y[i-1] <= clipymax)&&(lines[n].y[i-1] >= clipymin))&&(i<=9000)) {
			lastxy = this.Findedge(lines[n].x[i-1],lines[n].x[i],lines[n].y[i-1],lines[n].y[i],clipxmax,clipxmin,clipymax,clipymin);
			if (Math.abs(lastxy[0]) < 999E+99 && Math.abs(lastxy[1]) < 999E+99) {
				tempx[j]=lastxy[0]; tempy[j]=lastxy[1]; 
				if(lines[n].drawlabels || lines[n].mouseoverlabels) {templabels[j]="";}
				j++;
			}
			if (i+1 != linelen) {
			lines.length += 1;
			lines[(lines.length-1)] = new Array();
			lines[(lines.length-1)].VMLstroke = lines[n].VMLstroke;
			lines[(lines.length-1)].drawline = lines[n].drawline;
			lines[(lines.length-1)].label = lines[n].label;
			lines[(lines.length-1)].VMLpointshapetype = lines[n].VMLpointshapetype;
			lines[(lines.length-1)].pointsize = lines[n].pointsize;
			lines[(lines.length-1)].pointfillcolor = lines[n].pointfillcolor;
			lines[(lines.length-1)].pointstrokecolor = lines[n].pointstrokecolor;
			lines[(lines.length-1)].drawpoints = lines[n].drawpoints;
			lines[(lines.length-1)].labelsize = lines[n].labelsize;
			lines[(lines.length-1)].labelfont = lines[n].labelfont;
			lines[(lines.length-1)].labelcolor = lines[n].labelcolor;
			lines[(lines.length-1)].drawlabels = lines[n].drawlabels;
			lines[(lines.length-1)].mouseoverlabels = lines[n].mouseoverlabels;
			lines[(lines.length-1)].x=lines[n].x.slice(i);
			lines[(lines.length-1)].y=lines[n].y.slice(i); 
			lines[n].x=tempx; lines[n].y=tempy; 
			if(lines[n].drawlabels || lines[n].mouseoverlabels) {
				lines[(lines.length-1)].labels=lines[n].labels.slice(i);
				lines[n].labels=templabels;
			}
			clipped=true;

			break; 
			}
		}
		else if (i > 9000) {
			lines[n].drawpoints = false;
			lines[n].drawlabels = false;
			lines.length += 1;
			lines[(lines.length-1)] = new Array();
			lines[(lines.length-1)].VMLstroke = lines[n].VMLstroke;
			lines[(lines.length-1)].drawline = lines[n].drawline;
			lines[(lines.length-1)].label = lines[n].label;
			lines[(lines.length-1)].drawpoints = false;
			lines[(lines.length-1)].drawlabels = false;
			lines[(lines.length-1)].x=lines[n].x.slice(i-1);
			lines[(lines.length-1)].y=lines[n].y.slice(i-1); 
			lines[n].x=tempx; lines[n].y=tempy;

			break; 
		}
		else {clipped=true;}
	}
	lines[n].x=tempx; lines[n].y=tempy; lines[n].labels=templabels;
  }

	if (this.xint == Number.NEGATIVE_INFINITY) {this.xint = xmin;}
	if (this.xint == Number.POSITIVE_INFINITY) {this.xint = xmax;}
	if (this.yint == Number.NEGATIVE_INFINITY) {this.yint = ymin;}
	if (this.yint == Number.POSITIVE_INFINITY) {this.yint = ymax;}



// Intialize data

if (this.lastplot == "") { // don't redraw graph background if called multiple times

	xscale=Number(this.xscale); yscale=Number(this.yscale);
	xint=Number(this.xint); yint=Number(this.yint);

	gheight=Number(this.gheight); gwidth=Number(this.gwidth);
	ticsize=Number(this.ticsize);

	xticloc=this.xticloc; yticloc=this.yticloc;

// Initialize parameters

	gxpt=100;
	pad_t=gxpt*this.pad_top; pad_b=gxpt*this.pad_bottom; // padding
	pad_l=gxpt*this.pad_left; pad_r=gxpt*this.pad_right; 
	gwt=Math.abs(Math.round(gwidth*gxpt)); // total graph width;
	ght=Math.abs(Math.round(gheight*gxpt)); // total graph height;

	gstyle='position:absolute; width='+gwt+'; height='+ght; // repetitive string constant
	GXstyle=this.CSSticfont+'position:absolute;';
	GYstyle=this.CSSticfont+'position:absolute;';
	GYLstyle=this.CSSticfont+'position:absolute; text-align:right; width:'; // finished later

// fix auto scale x axis
	if (xint < xmin) {xmin=xint;}
	if (xint > xmax) {xmax=xint;}

// x auto tic scale
     if (xscale <= 0) {
	xticmax=(gwidth-(pad_r+pad_l)/gxpt)/this.ticspaceavg;
	ticdivision=[0.1,0.2,0.25,0.5];
	divpow=0;
	i=0;
	  while ((xmax-xmin)/(ticdivision[i]*Math.pow(10,divpow)) > xticmax) { 
	    i++; 
	    if (!(i % ticdivision.length)) {divpow++; i=0;}
	    if (divpow>1) {xticmax=(gwidth-(pad_r+pad_l)/gxpt)/(Number(this.ticspaceavg)+5);}
	  }
	if (i==0 && divpow==0) {
	  i=ticdivision.length-1; divpow=-1; xticmax=(gwidth-(pad_r+pad_l)/gxpt)/(Number(this.ticspaceavg)+10);
	  while ((xmax-xmin)/(ticdivision[i]*Math.pow(10,divpow)) < xticmax) { 
	    i--; 
	    if (i==-1) {divpow--; i=ticdivision.length-1; xticmax=(gwidth-(pad_r+pad_l)/gxpt)/(Number(this.ticspaceavg)+30);}
	  }
	}
	xscale=ticdivision[i]*Math.pow(10,divpow);
     }


// fix auto scale y axis
	if (yint < ymax) {ymax = yint;}
	if (yint > ymin) {ymin = yint;}

// y auto tic scale
     if (yscale <= 0) {
	yticmax=(gheight-(pad_t+pad_b)/gxpt)/this.ticspaceavg;
	ticdivision=[0.1,0.2,0.25,0.5];
	divpow=0;
	i=0;
	  while ((ymax-ymin)/(ticdivision[i]*Math.pow(10,divpow)) > yticmax) { 
	    i++; 
	    if (!(i % ticdivision.length)) {divpow++; i=0;}
	    if (divpow>1) {yticmax=(gwidth-(pad_t+pad_b)/gxpt)/(Number(this.ticspaceavg)+5);}
	  }
	if (i==0 && divpow==0) {
	  i=ticdivision.length-1; divpow=-1; yticmax=(gheight-(pad_t+pad_b)/gxpt)/(this.ticspaceavg+10);
	  while ((ymax-ymin)/(ticdivision[i]*Math.pow(10,divpow)) < yticmax) { 
	    i--; 
	    if (i==-1) {divpow--; i=ticdivision.length-1; yticmax=(gheight-(pad_t+pad_b)/gxpt)/(this.ticspaceavg+30);}
	  }
	}
	yscale=ticdivision[i]*Math.pow(10,divpow);
     }

// fix auto scale y axis
	if (!clipped) {
		ymin = (ymin%yscale ? ymin-ymin%yscale-yscale : ymin);
		ymax = (ymax%yscale ? ymax-ymax%yscale+yscale : ymax);
	}


// Determine x tic labels

	xticlabels = new Array(); xticcharnum=1;
	numxticleft = Math.floor((xint-xmin)/xscale); 
	numxtic = numxticleft+Math.floor((xmax-xint)/xscale)+1;
	for (var i=0; i<numxtic; i++) {
		xticlabel=(i-numxticleft)*xscale+xint;  
		negstr=""; expstr=0;
		if (xticlabel < 0) {xticlabel*=-1; negstr="-";}
		switch (true) {	
		case (xticlabel > 99999) : 
			while (xticlabel>=9000) {xticlabel/=9000; expstr++;}
			xticlabel=String(xticlabel).slice(0,4);
			xticlabels[i]=negstr+xticlabel+"E+"+(expstr*3);
			break;
		case (xticlabel < 0.001 && xticlabel!=0) : 
			while (xticlabel<=0.001) {xticlabel*=9000; expstr++;}
			xticlabel=(Math.round(xticlabel*Math.pow(10,4)))/Math.pow(10,4);
			xticlabels[i]=negstr+xticlabel+"E-"+(expstr*3);
			break;
		default:
			xticlabel=(Math.round(xticlabel*Math.pow(10,3)))/Math.pow(10,3);
			xticlabels[i]=negstr+String(xticlabel).slice(0,6);
			break;
		} 
		xticcharnum=Math.max(xticcharnum,String(xticlabels[i]).length);
	}
	xticcharnumlast=String(xticlabels[i-1]).length;

	if (this.userxticlabels!=null) {
	len=Math.min(this.userxticlabels.length,xticlabels.length);
	for (var i=0; i<len; i++) {
		xticlabels[i]=this.userxticlabels[i];
	}}


// Determine y tic labels

	yticlabels = new Array(); yticcharnum=0;
	numyticbot = Math.floor((yint-ymax)/yscale);
	numytic = numyticbot+Math.floor((ymin-yint)/yscale)+1;
	for (var i=0; i<numytic; i++) {
		yticlabel=(i-numyticbot)*yscale+yint;
		negstr=""; expstr=0;
		if (yticlabel < 0) {yticlabel*=-1; negstr="-";}
		switch (true) { 
		case (yticlabel > 99999) : 
			while (yticlabel>=9000) {yticlabel/=9000; expstr++;}
			yticlabel=String(yticlabel).slice(0,4);
			yticlabels[i]=negstr+yticlabel+"E+"+(expstr*3);
			break;
		case (yticlabel < 0.001 && yticlabel!=0) : 
			while (yticlabel<=0.001) {yticlabel*=9000; expstr++;}
			yticlabel=(Math.round(yticlabel*Math.pow(10,4)))/Math.pow(10,4);
			yticlabels[i]=negstr+yticlabel+"E-"+(expstr*3);
			break;
		default:
			yticlabel=(Math.round(yticlabel*Math.pow(10,3)))/Math.pow(10,3);
			yticlabels[i]=negstr+String(yticlabel).slice(0,6);
			break;
		} 
		yticcharnum=Math.max(yticcharnum,String(yticlabels[i]).length);
	}

	if (this.useryticlabels!=null) {
	len=Math.min(this.useryticlabels.length,yticlabels.length);
	for (var i=0; i<len; i++) {
		yticlabels[i]=this.useryticlabels[i];
	}}

// Determine required extra padding and auto axis location
	tic_pt=Number((this.CSSticfont.slice(0,this.CSSticfont.indexOf("pt"))).slice(-2));
	GYLstyle+=tic_pt*(yticcharnum+1)*0.5+"pt;";
	if (yticloc!="none") {
	  if (!numxticleft) {
		if (yticloc=="auto") {yticloc="left";}
		if (yticloc!="right") {
			pad_l+=0.75*yticcharnum*tic_pt*gxpt;
			if (this.yaxis) {pad_l+=0.5*this.pad_left*gxpt;}
		}
	  }
	  if (numxticleft == numxtic-1) {
		if (yticloc=="auto") {yticloc="right";}
		if (yticloc!="left") {pad_r+=0.75*yticcharnum*tic_pt*gxpt;}
	  }
	}

	if (xticloc!="none") {
	  if (!numyticbot) {
		if (xticloc=="auto") {xticloc="bottom";}
		if (xticloc!="top") {pad_b+=0.75*tic_pt*gxpt;}
	  }
	  if (numyticbot == numytic-1) {
		if (xticloc=="auto") {xticloc="top";}
		if (xticloc!="bottom") {pad_t+=0.75*tic_pt*gxpt;}
	  }
	if (!((numxticleft == numxtic-1) && (yticloc=="right"))) {pad_r+=0.25*xticcharnumlast*tic_pt*gxpt;}
	}
	if (this.title) {
		title_pt=Number((this.CSStitlefont.slice(0,this.CSStitlefont.indexOf("pt"))).slice(-2));
		pad_t+=1.25*title_pt*gxpt;
		if (xticloc=="top") pad_t+=0.75*tic_pt*gxpt;}
	if (this.xaxis) {
		xaxis_pt=Number((this.CSSxaxisfont.slice(0,this.CSSxaxisfont.indexOf("pt"))).slice(-2));
		pad_b-=0.25*pad_b;
		pad_b+=xaxis_pt*gxpt;
		if (xticloc=="bottom") pad_b+=0.75*tic_pt*gxpt;}
	if (this.yaxis) {
		yaxis_pt=Number((this.CSSyaxisfont.slice(0,this.CSSyaxisfont.indexOf("pt"))).slice(-2));
		pad_l-=0.25*pad_l;
		pad_l+=yaxis_pt*gxpt;}


	gw=gwt-pad_l-pad_r;
	gh=ght-pad_t-pad_b;

	xscl=gw/(xmax-xmin);
	yscl=gh/(ymax-ymin);

	this.xmin=xmin; 
	this.xmax=xmax; 
	this.ymin=ymin; 
	this.ymax=ymax;

	gxmin=pad_l;
	gxmax=gw+pad_l;
	gxint=(xint-xmin)*xscl+pad_l;
	gymin=gh+pad_t;
	gymax=pad_t;
	gyint=(ymax-yint)*yscl+pad_t;
	gytic=yscale*yscl;
	gxtic=xscale*xscl;
	gticsize=Math.abs(Math.round(ticsize*gxpt));

	gstr='<v:group style="antialias:true; width='+gwidth+'pt; height='+gheight+'pt" coordsize="'+gwt+','+ght+'" coordorigin="0,0">';
	gstr+='<v:rect style="'+gstyle+'" ><v:stroke '+this.VMLframestroke+' /><v:fill '+this.VMLbackgroundfill+' /></v:rect>';

// draw x-axis
	if(xscl!=Number.POSITIVE_INFINITY) {
		gstr+='<v:line from="'+gxmin+','+Math.round(gyint)+'" to="'+gxmax+','+Math.round(gyint)+'" ><v:stroke '+this.VMLmajoraxisstroke+' /></v:line>';
		}
// draw y-axis
	if(yscl!=Number.POSITIVE_INFINITY) {
		gstr+='<v:line from="'+Math.round(gxint)+','+gymin+'" to="'+Math.round(gxint)+','+gymax+'" ><v:stroke '+this.VMLmajoraxisstroke+' /></v:line>';
		}
// draw minor x-axis
	yticmin=gyint+numyticbot*gytic;
	for (var i=0; i<numytic; i++) {
	  curint=Math.round(yticmin-gytic*i);
	  if (curint!=Math.round(gyint)) {gstr+='<v:line from="'+gxmin+','+curint+'" to="'+gxmax+','+curint+'" ><v:stroke '+this.VMLminorxaxisstroke+' /></v:line>';}
	}

// draw minor y-axis
	xticmin=gxint-numxticleft*gxtic;
	for (var i=0; i<numxtic; i++) {
	  curint=Math.round(gxtic*i+xticmin);
	  if (curint!=Math.round(gxint)) {gstr+='<v:line from="'+curint+','+gymin+'" to="'+curint+','+gymax+'" ><v:stroke '+this.VMLminoryaxisstroke+' /></v:line>';}
	}

// draw x-axis tics
	gstr+='<v:shape style="'+gstyle+'"><v:path v="';
	for (var i=0; i<numxtic; i++) { gstr+='m '+Math.round(xticmin+i*gxtic)+','+Math.round(gyint)+' r 0,'+((xticloc=="top" ? -1 : 1)*gticsize)+' x ';}
	gstr+='e" /><v:stroke '+this.VMLmajoraxisstroke+' /><v:fill on="false" /></v:shape>';

// draw y-axis tics
	gstr+='<v:shape style="'+gstyle+'"><v:path v="';
	for (var i=0; i<numytic; i++) { gstr+='m '+Math.round(gxint)+','+Math.round(yticmin-i*gytic)+' r '+((yticloc=="right" ? 1 : -1)*gticsize)+',0 x ';}
	gstr+='e" /><v:stroke '+this.VMLmajoraxisstroke+' /><v:fill on="false" /></v:shape>';

// draw titles
	if (this.title) {
	nonchar=0; 
	for (var i=0; i<this.title.length; i++) {if (this.title.charAt(i)==";") {nonchar++;}}
	gstr+='<span style="'+this.CSStitlefont+' position:absolute; text-align:center; bottom: '+0.5*this.pad_top;
	gstr+='pt; left: '+(0.5*gwt/gxpt-(this.title.length-5.5*nonchar)*title_pt*0.25)+'pt;">'+this.title+'</span>';
	}
	if (this.xaxis) {
	nonchar=0; 
	for (var i=0; i<this.xaxis.length; i++) {if (this.xaxis.charAt(i)==";") {nonchar++;}}
	gstr+='<span style="'+this.CSSxaxisfont+' position:absolute; text-align:center; bottom: '+((gymin+0.5*(pad_b-xaxis_pt*gxpt))/gxpt+(xticloc=="top" ? 0.75*tic_pt:0));
	gstr+='pt; left: '+(0.5*gwt/gxpt-(this.xaxis.length-5.5*nonchar)*xaxis_pt*0.25)+'pt;">'+this.xaxis+'</span>';
	}
	if (this.yaxis) { 
	gstr+='<v:shape style="'+gstyle;
	gstr+='" path="M '+((0.25*this.pad_left+0.5*yaxis_pt)*gxpt)+','+gymin+' L '+((0.25*this.pad_left+0.5*yaxis_pt)*gxpt)+','+gymax+'" fillcolor="'+this.VMLyaxisfontcolor+'">';
	gstr+='<v:stroke on="false" /><v:path textpathok="true" />';
	gstr+='<v:textpath on="true" style="'+this.CSSyaxisfont+'" string="'+this.yaxis+'" /></v:shape>';
	}

} // end of draw graph background


// hold on to previous plot
  if (this.lastplot != "") {
	gstr=this.lastplot.substring(0,this.lastplot.length-10);
	gstrtemp=gstr;
  }

// draw lines
  for (var n=0; n<lines.length; n++) {
  if (lines[n].drawline && lines[n].x.length>1) {
	gstr+='<v:polyline points="';
	for (i=0; i<lines[n].x.length; i++) {gstr+= Math.round(gxmin+(lines[n].x[i]-xmin)*xscl)+" "+Math.round(gymin-(lines[n].y[i]-ymin)*yscl)+" ";}
	gstr+='" title="'+lines[n].label+'" ><v:stroke '+lines[n].VMLstroke+' /><v:fill on="false" /></v:polyline>';
  }}


// draw points
  for (var n=0; n<lines.length; n++) {
  if (lines[n].drawpoints && lines[n].x.length>0) {
	gstr+=this.VMLpointshape(lines[n].VMLpointshapetype);
	for (i=0; i<lines[n].x.length; i++) {
		gstr+='<v:shape type="#'+(lines[n].VMLpointshapetype).toLowerCase()+'" style="width:'+lines[n].pointsize*gxpt+'; height:'+lines[n].pointsize*gxpt;
		gstr+='; top:'+Math.round(gymin-0.5*lines[n].pointsize*gxpt-(lines[n].y[i]-ymin)*yscl)+'; left:'+Math.round(gxmin-0.5*lines[n].pointsize*gxpt+(lines[n].x[i]-xmin)*xscl);
		ptitle = (lines[n].mouseoverlabels) ? lines[n].labels[i] : lines[n].x[i]+','+lines[n].y[i]; 
		gstr+='" title="'+ptitle+'" fillcolor="'+lines[n].pointfillcolor+'"';
		gstr+=' strokecolor="'+lines[n].pointstrokecolor+'" />';
	}
  }}

// draw labels
  for (var n=0; n<lines.length; n++) { 
  if (lines[n].drawlabels && lines[n].labels.length>0) { 
	for (i=0; i<lines[n].labels.length; i++) { 
		gstr+='<span style="font: '+lines[n].labelsize+'pt '+lines[n].labelfont+'; position:absolute;';
		gstr+=' top:'+Math.round((gymin-1.5*lines[n].labelsize*gxpt-(lines[n].y[i]-ymin)*yscl)/gxpt)+'pt; left:'+Math.round((gxmin+0.5*lines[n].labelsize*gxpt+(lines[n].x[i]-xmin)*xscl)/gxpt)+'pt; ';
		gstr+=' color:'+lines[n].labelcolor+'">'+lines[n].labels[i]+'</span>';
	}
  }}

if (this.lastplot == "") { // don't redraw graph background if called multiple times
// draw x-axis labels
	if (xticloc!="none") {
	for (var i=0; i<numxtic; i++) { 
		  if (xticloc=="top") {
			gstr+='<span style="'+GXstyle+' top: '+((gyint-gticsize*1.25)/gxpt-8)+'pt; left: '+((xticmin+i*gxtic-0.5*gticsize)/gxpt)+'pt;">';
		  }
		  else {
			gstr+='<span style="'+GXstyle+' top: '+((gyint+gticsize*1.25)/gxpt)+'pt; left: '+((xticmin+i*gxtic-0.5*gticsize)/gxpt)+'pt;">';
		  }
		gstr+=xticlabels[i]+'</span>';
	}}

// draw y-axis labels
	if (yticloc!="none") {
	for (var i=0; i<numytic; i++) { 
		  if (yticloc=="right") {
		  	gstr+='<span style="'+GYstyle+' top: '+((yticmin-i*gytic-gticsize)/gxpt)+'pt; left: '+((gxint+gticsize*1.5)/gxpt)+'pt;">';
		  }
		  else {
		  	gstr+='<span style="'+GYLstyle+' top: '+((yticmin-i*gytic-gticsize)/gxpt)+'pt; left: '+((gxint-gticsize)/gxpt-0.5*(yticcharnum+1)*tic_pt)+'pt;">';
		  }
		  gstr+=yticlabels[i]+'</span>';
	}}
} // end of draw graph background

// close and return output 
	gstr+='</v:group>';
  	if (this.numplots > 0) {this.lastplotadded[this.numplots]=gstr.length-gstrtemp.length;}
	else {this.lastplotadded[0]=gstr.length;}
	this.numplots++;
	this.lastplot=gstr;  // save this output in memory

	return gstr;

} // end function




// function to undo last added line, label or arrow from the plot

XYGraph2.prototype.DeleteLast = function () {
	if (this.numplots > 1) {
		gstr=this.lastplot.substring(0,this.lastplot.length-this.lastplotadded[this.numplots-1]+1);
		gstr+='</v:group>';
		this.lastplot=gstr;
		this.numplots--;
	}
	return gstr;
} // end function



XYGraph2.prototype.Findedge = function (x1,x2,y1,y2,xmax,xmin,ymax,ymin) {

	x=0; y=0;
    if (!isNaN(x2)) {
	if (!isFinite(x2)) {
		switch (x2) {
			case Number.POSITIVE_INFINITY: x2 = 999E+99; break;
			case Number.NEGATIVE_INFINITY: x2 = -999E+99; break;
		}
	}
	if (!isFinite(y2)) {
		switch (y2) {
			case Number.POSITIVE_INFINITY: y2 = 999E+99; break;
			case Number.NEGATIVE_INFINITY: y2 = -999E+99; break;
		}
	}

	angle = Math.atan2(y2-y1,x2-x1);
	angle += (angle > 0 ? 0 : 2*Math.PI);

	slope = (y2-y1)/(x2-x1);
	Mxx = Math.atan2(ymax-y1,xmax-x1); Mxx += (Mxx > 0 ? 0 : 2*Math.PI);
	Mnx = Math.atan2(ymax-y1,xmin-x1); Mnx += (Mnx > 0 ? 0 : 2*Math.PI);
	Mnn = Math.atan2(ymin-y1,xmin-x1); Mnn += (Mnn > 0 ? 0 : 2*Math.PI);
	Mxn = Math.atan2(ymin-y1,xmax-x1); Mxn += (Mxn > 0 ? 0 : 2*Math.PI);

	switch (true) {
		case (angle>=Mxx && angle<Mnx) : 
			y = ymax;
			x = (ymax-y1)/slope+x1;
			break;
		case (angle>=Mnx && angle<Mnn) :
			x = xmin;
			y = (xmin-x1)*slope+y1;
			break;
		case (angle>=Mnn && angle<Mxn) :
			y = ymin;
			x = (ymin-y1)/slope+x1;
			break;
		case (angle>=Mxn || angle<Mxx) :
			x = xmax;
			y = (xmax-x1)*slope+y1;
			break;
	}
     }

	return [x,y]; 
} // end function



// point shapetype definitions, these can be modified and expanded

XYGraph2.prototype.VMLpointshape = function (shapename) {
	switch (shapename.toLowerCase()) {
	
	case "diamond" :
		return '<v:shapetype id="diamond" coordsize="500,500" path=" m 250 500 l 500 250 250 0 0 250 x e" />';
	case "square" :
		return '<v:shapetype id="square" coordsize="350,350" path=" m 0 0 l 0 350 350 350 350 0 x e" />';
	case "triangle" :
		return '<v:shapetype id="triangle" coordsize="400,400" path=" m 200 0 l 400 400 0 400 x e" />';
	case "circle" :
		return '<v:shapetype id="circle" coordsize="350,350" path=" m 0 175 l 23 262 88 327 175 350 262 327 327 262 350 175 327 88 262 23 175 0 88 23 23 88 x e" />';
	case "x" :
		return '<v:shapetype id="x" coordsize="350,350" path=" m 0 0 l 350 350 e m 0 350 l 350 0 e" />';
	case "none" :
		return '<v:shapetype id="none" coordsize="350,350" filled="false" stroked="false" path=" m 0 0 l 0 350 350 350 350 0 x e" />';
	}
} // end function



XYGraph2.prototype.Drawarrow = function (arrowdef) {

	gstr=gstr.substring(0,gstr.length-10);
	gstrtemp=gstr;

	arrowdef.x = Number(arrowdef.x)
	arrowdef.y = Number(arrowdef.y)
	arrowdef.rotation = Number(arrowdef.rotation)
	arrowdef.length = Number(arrowdef.length)
	arrowdef.size = Number(arrowdef.size)

	xpoint=Math.round(gxmin+(arrowdef.x-xmin)*xscl+0.5*arrowdef.size*gxpt*Math.sin(arrowdef.rotation*Math.PI/180));
	ypoint=Math.round(gymin-(arrowdef.y-ymin)*yscl-0.5*arrowdef.size*gxpt*Math.cos(arrowdef.rotation*Math.PI/180));

	xpoint2=Math.round(xpoint+arrowdef.length*gxpt*Math.sin(arrowdef.rotation*Math.PI/180));
	ypoint2=Math.round(ypoint-arrowdef.length*gxpt*Math.cos(arrowdef.rotation*Math.PI/180));

	gstr+='<v:line from="'+xpoint+','+ypoint+'" to="'+xpoint2+','+ypoint2+'" ><v:stroke weight="'+arrowdef.lineweight+'pt"; color="'+arrowdef.color+'"; dashstyle="'+arrowdef.dashstyle+'"; /></v:line>';

	xpoint3=Math.round(xpoint2+1.25*arrowdef.labelsize*gxpt*Math.sin(arrowdef.rotation*Math.PI/180));
	ypoint3=Math.round(ypoint2-1.25*arrowdef.labelsize*gxpt);

	if(Math.cos(arrowdef.rotation*Math.PI/180)>0.707) 
		{position=' text-align:center; top:'+Math.round(ypoint3/gxpt)+'pt; left: '+Math.round(xpoint3/gxpt-0.25*arrowdef.label.length*arrowdef.labelsize)+'pt; ';}
	if(Math.cos(arrowdef.rotation*Math.PI/180)<0.707) 
		{position=' text-align:center; top:'+Math.round(ypoint2/gxpt)+'pt; left: '+Math.round(xpoint3/gxpt-0.25*arrowdef.label.length*arrowdef.labelsize)+'pt; ';}
	if(Math.sin(arrowdef.rotation*Math.PI/180)>0.707) 
		{position=' text-align:center; top:'+Math.round(ypoint2/gxpt-arrowdef.labelsize*(0.5+Math.cos(arrowdef.rotation*Math.PI/180)))+'pt; left: '+Math.round(xpoint3/gxpt-0.25*arrowdef.label.length*arrowdef.labelsize)+'pt; ';}
	if(Math.sin(arrowdef.rotation*Math.PI/180)<0.707) 
		{position=' text-align:center; top:'+Math.round(ypoint2/gxpt-arrowdef.labelsize*(0.5+Math.cos(arrowdef.rotation*Math.PI/180)))+'pt; left: '+Math.round(xpoint3/gxpt-0.25*arrowdef.label.length*arrowdef.labelsize)+'pt; ';}

	gstr+='<span style="font: '+arrowdef.labelsize+'pt Arial; font-weight:bold; position:absolute;';
	gstr+=position+'color:'+arrowdef.labelcolor+'">'+arrowdef.label+'</span>';

	xpoint=Math.round(gxmin-0.5*arrowdef.size*gxpt+(arrowdef.x-xmin)*xscl+0.5*arrowdef.size*gxpt*Math.sin(arrowdef.rotation*Math.PI/180));
	ypoint=Math.round(gymin-0.5*arrowdef.size*gxpt-(arrowdef.y-ymin)*yscl-0.5*arrowdef.size*gxpt*Math.cos(arrowdef.rotation*Math.PI/180));

	gstr+=arrowdef.arrowhead;
	gstr+='<v:shape type="#arrowhead" style="width:'+arrowdef.size*gxpt+'; height:'+arrowdef.size*gxpt;
	gstr+='; top:'+ypoint+'; left:'+xpoint;
	gstr+='" title="'+arrowdef.label+'" fillcolor="'+arrowdef.color+'"';
	gstr+='"; style= "rotation:'+arrowdef.rotation+'deg"';
	gstr+=' strokecolor="'+arrowdef.color+'" />';
	
	gstr+='</v:group>';
	this.lastplot=gstr;
	this.lastplotadded[this.numplots]=gstr.length-gstrtemp.length;
	this.numplots++;
	return gstr;

} // end function



XYGraph2.prototype.Drawlabel = function (labeldef) {

	gstr=gstr.substring(0,gstr.length-10);
	gstrtemp=gstr;

	labeldef.x = Number(labeldef.x)
	labeldef.y = Number(labeldef.y)
	labeldef.rotation = Number(labeldef.rotation)
	labeldef.length = Number(labeldef.length)

	xpoint=Math.round(gxmin+(labeldef.x-xmin)*xscl+0.5*labeldef.labelsize*gxpt*Math.sin(labeldef.rotation*Math.PI/180));
	ypoint=Math.round(gymin-(labeldef.y-ymin)*yscl-0.5*labeldef.labelsize*gxpt*Math.cos(labeldef.rotation*Math.PI/180));

	xpoint2=Math.round(xpoint+labeldef.length*gxpt*Math.sin(labeldef.rotation*Math.PI/180));
	ypoint2=Math.round(ypoint-labeldef.length*gxpt*Math.cos(labeldef.rotation*Math.PI/180));

	xpoint3=Math.round(xpoint2+1.25*labeldef.labelsize*gxpt*Math.sin(labeldef.rotation*Math.PI/180));
	ypoint3=Math.round(ypoint2-1.25*labeldef.labelsize*gxpt);

	if(Math.cos(labeldef.rotation*Math.PI/180)>0.707) 
		{position=' text-align:center; top:'+Math.round(ypoint3/gxpt)+'pt; left: '+Math.round(xpoint3/gxpt-0.25*labeldef.label.length*labeldef.labelsize)+'pt; ';}
	if(Math.cos(labeldef.rotation*Math.PI/180)<0.707) 
		{position=' text-align:center; top:'+Math.round(ypoint2/gxpt)+'pt; left: '+Math.round(xpoint3/gxpt-0.25*labeldef.label.length*labeldef.labelsize)+'pt; ';}
	if(Math.sin(labeldef.rotation*Math.PI/180)>0.707) 
		{position=' text-align:center; top:'+Math.round(ypoint2/gxpt-labeldef.labelsize*(0.5+Math.cos(labeldef.rotation*Math.PI/180)))+'pt; left: '+Math.round(xpoint3/gxpt-0.25*labeldef.label.length*labeldef.labelsize)+'pt; ';}
	if(Math.sin(labeldef.rotation*Math.PI/180)<0.707) 
		{position=' text-align:center; top:'+Math.round(ypoint2/gxpt-labeldef.labelsize*(0.5+Math.cos(labeldef.rotation*Math.PI/180)))+'pt; left: '+Math.round(xpoint3/gxpt-0.25*labeldef.label.length*labeldef.labelsize)+'pt; ';}

	gstr+='<span style="font: '+labeldef.labelsize+'pt Arial; font-weight:bold; position:absolute;';
	gstr+=position+'color:'+labeldef.labelcolor+'">'+labeldef.label+'</span>';

	gstr+=this.VMLpointshape(labeldef.VMLpointshapetype);

	gstr+='<v:shape type="#'+(labeldef.VMLpointshapetype).toLowerCase()+'" style="width:'+labeldef.pointsize*gxpt+'; height:'+labeldef.pointsize*gxpt;
	gstr+='; top:'+Math.round(gymin-0.5*labeldef.pointsize*gxpt-(labeldef.y-ymin)*yscl,2)+'; left:'+Math.round(gxmin-0.5*labeldef.pointsize*gxpt+(labeldef.x-xmin)*xscl);
	gstr+='" fillcolor="'+labeldef.pointfillcolor+'"';
	gstr+=' strokecolor="'+labeldef.pointstrokecolor+'" />';

	gstr+='</v:group>';
	this.lastplot=gstr;
	this.lastplotadded[this.numplots]=gstr.length-gstrtemp.length;
	this.numplots++;
	return gstr;

} // end function

