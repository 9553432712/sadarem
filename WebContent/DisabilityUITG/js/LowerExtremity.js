



function checkradiodeformity1(){
  if(document.lowerextremityform.getAttribute("def1")=="true"){
    document.lowerextremityform.deformity[0].checked = false;
    document.lowerextremityform.setAttribute("def1","false");
  } else
  {  
    document.lowerextremityform.setAttribute("def1","true");
    document.lowerextremityform.setAttribute("def2","false");
	}
  }
  function checkradiodeformity2(){    
  if(document.lowerextremityform.getAttribute("def2")=="true"){
    document.lowerextremityform.deformity[1].checked = false;
    document.lowerextremityform.setAttribute("def2","false");
  } else
  {  
    document.lowerextremityform.setAttribute("def2","true");
    document.lowerextremityform.setAttribute("def1","false");
  }
  }
  
  
  
  function checkradiopain1(){
  if(document.lowerextremityform.getAttribute("pain1")=="true"){
    document.lowerextremityform.pain[0].checked = false;
    document.lowerextremityform.setAttribute("pain1","false");
  } else
  {  
    document.lowerextremityform.setAttribute("pain1","true");
    document.lowerextremityform.setAttribute("pain2","false");
    document.lowerextremityform.setAttribute("pain3","false");
	}
  }
  function checkradiopain2(theRadio){    
  if(document.lowerextremityform.getAttribute("pain2")=="true"){
    document.lowerextremityform.pain[1].checked = false;
    document.lowerextremityform.setAttribute("pain2","false");
  } else
  {  
    document.lowerextremityform.setAttribute("pain2","true");
    document.lowerextremityform.setAttribute("pain1","false");
    document.lowerextremityform.setAttribute("pain3","false");
  }
  }
  function checkradiopain3(theRadio){    
  if(document.lowerextremityform.getAttribute("pain3")=="true"){
    document.lowerextremityform.pain[2].checked = false;
    document.lowerextremityform.setAttribute("pain3","false");
  } else
  {  
    document.lowerextremityform.setAttribute("pain3","true");
    document.lowerextremityform.setAttribute("pain1","false");
    document.lowerextremityform.setAttribute("pain2","false");
  }
  }
  




function checkradioloss_of_Function1(){
  if(document.lowerextremityform.getAttribute("lof1")=="true"){
    document.lowerextremityform.loss_of_Function[0].checked = false;
    document.lowerextremityform.setAttribute("lof1","false");
  } else
  {  
    document.lowerextremityform.setAttribute("lof1","true");
    document.lowerextremityform.setAttribute("lof2","false");                          
	}
  }
  function checkradioloss_of_Function2(){    
  if(document.lowerextremityform.getAttribute("lof2")=="true"){
    document.lowerextremityform.loss_of_Function[1].checked = false;
    document.lowerextremityform.setAttribute("lof2","false");
  } else
  {  
    document.lowerextremityform.setAttribute("lof2","true");
    document.lowerextremityform.setAttribute("lof1","false");
  }
  }



function checkradiocomplications1(){
  if(document.lowerextremityform.getAttribute("com1")=="true"){
    document.lowerextremityform.complications[0].checked = false;
    document.lowerextremityform.setAttribute("com1","false");
  } else
  {  
    document.lowerextremityform.setAttribute("com1","true");
    document.lowerextremityform.setAttribute("com2","false");                          
	}
  }
  function checkradiocomplications2(){    
  if(document.lowerextremityform.getAttribute("com2")=="true"){
    document.lowerextremityform.complications[1].checked = false;
    document.lowerextremityform.setAttribute("com2","false");
  } else
  {  
    document.lowerextremityform.setAttribute("com2","true");
    document.lowerextremityform.setAttribute("com1","false");
  }
  }






function checkradioworking_on_plane1(){
  if(document.lowerextremityform.getAttribute("wop1")=="true"){
    document.lowerextremityform.working_on_plane[0].checked = false;
    document.lowerextremityform.setAttribute("wop1","false");
  } else
  {  
    document.lowerextremityform.setAttribute("wop1","true");
    document.lowerextremityform.setAttribute("wop2","false");
    document.lowerextremityform.setAttribute("wop3","false");
	}
  }
  function checkradioworking_on_plane2(theRadio){    
  if(document.lowerextremityform.getAttribute("wop2")=="true"){
    document.lowerextremityform.working_on_plane[1].checked = false;
    document.lowerextremityform.setAttribute("wop2","false");
  } else
  {  
    document.lowerextremityform.setAttribute("wop2","true");
    document.lowerextremityform.setAttribute("wop1","false");
    document.lowerextremityform.setAttribute("wop3","false");
  }
  }
  function checkradioworking_on_plane3(theRadio){    
  if(document.lowerextremityform.getAttribute("wop3")=="true"){
    document.lowerextremityform.working_on_plane[2].checked = false;
    document.lowerextremityform.setAttribute("wop3","false");
  } else
  {  
    document.lowerextremityform.setAttribute("wop3","true");
    document.lowerextremityform.setAttribute("wop1","false");
    document.lowerextremityform.setAttribute("wop2","false");
  }
  }
  




function checkradioworking_on_slope1(){
  if(document.lowerextremityform.getAttribute("wos1")=="true"){
    document.lowerextremityform.working_on_slope[0].checked = false;
    document.lowerextremityform.setAttribute("wos1","false");
  } else
  {  
    document.lowerextremityform.setAttribute("wos1","true");
    document.lowerextremityform.setAttribute("wos2","false");
    document.lowerextremityform.setAttribute("wos3","false");
	}
  }
  function checkradioworking_on_slope2(theRadio){    
  if(document.lowerextremityform.getAttribute("wos2")=="true"){
    document.lowerextremityform.working_on_slope[1].checked = false;
    document.lowerextremityform.setAttribute("wos2","false");
  } else
  {  
    document.lowerextremityform.setAttribute("wos2","true");
    document.lowerextremityform.setAttribute("wos1","false");
    document.lowerextremityform.setAttribute("wos3","false");
  }
  }
  function checkradioworking_on_slope3(theRadio){    
  if(document.lowerextremityform.getAttribute("wos3")=="true"){
    document.lowerextremityform.working_on_slope[2].checked = false;
    document.lowerextremityform.setAttribute("wos3","false");
  } else
  {  
    document.lowerextremityform.setAttribute("wos3","true");
    document.lowerextremityform.setAttribute("wos1","false");
    document.lowerextremityform.setAttribute("wos2","false");
  }
  }






function checkradioworking_on_stairs1(){
  if(document.lowerextremityform.getAttribute("wost1")=="true"){
    document.lowerextremityform.working_on_stairs[0].checked = false;
    document.lowerextremityform.setAttribute("wost1","false");
  } else
  {  
    document.lowerextremityform.setAttribute("wost1","true");
    document.lowerextremityform.setAttribute("wost2","false");
    document.lowerextremityform.setAttribute("wost3","false");
	}
  }
  function checkradioworking_on_stairs2(theRadio){    
  if(document.lowerextremityform.getAttribute("wost2")=="true"){
    document.lowerextremityform.working_on_stairs[1].checked = false;
    document.lowerextremityform.setAttribute("wost2","false");
  } else
  {  
    document.lowerextremityform.setAttribute("wost2","true");
    document.lowerextremityform.setAttribute("wost1","false");
    document.lowerextremityform.setAttribute("wost3","false");
  }
  }
  function checkradioworking_on_stairs3(theRadio){    
  if(document.lowerextremityform.getAttribute("wost3")=="true"){
    document.lowerextremityform.working_on_stairs[2].checked = false;
    document.lowerextremityform.setAttribute("wost3","false");
  } else
  {  
    document.lowerextremityform.setAttribute("wost3","true");                       
    document.lowerextremityform.setAttribute("wost1","false");
    document.lowerextremityform.setAttribute("wost2","false");
  }
  }




function checkradiostanding_on_both_legs1(){
  if(document.lowerextremityform.getAttribute("standing_on_both_legs1")=="true"){
    document.lowerextremityform.standing_on_both_legs[0].checked = false;
    document.lowerextremityform.setAttribute("standing_on_both_legs1","false");
  } else
  {  
    document.lowerextremityform.setAttribute("standing_on_both_legs1","true");
    document.lowerextremityform.setAttribute("standing_on_both_legs2","false");
    document.lowerextremityform.setAttribute("standing_on_both_legs3","false");
	}
  }
  function checkradiostanding_on_both_legs2(theRadio){    
  if(document.lowerextremityform.getAttribute("standing_on_both_legs2")=="true"){
    document.lowerextremityform.standing_on_both_legs[1].checked = false;
    document.lowerextremityform.setAttribute("standing_on_both_legs2","false");
  } else
  {  
    document.lowerextremityform.setAttribute("standing_on_both_legs2","true");
    document.lowerextremityform.setAttribute("standing_on_both_legs1","false");
    document.lowerextremityform.setAttribute("standing_on_both_legs3","false");
  }
  }
  function checkradiostanding_on_both_legs3(theRadio){    
  if(document.lowerextremityform.getAttribute("standing_on_both_legs3")=="true"){
    document.lowerextremityform.standing_on_both_legs[2].checked = false;
    document.lowerextremityform.setAttribute("standing_on_both_legs3","false");
  } else
  {  
    document.lowerextremityform.setAttribute("standing_on_both_legs3","true");
    document.lowerextremityform.setAttribute("standing_on_both_legs1","false");
    document.lowerextremityform.setAttribute("standing_on_both_legs2","false");
  }
  }




function checkradiostanding_on_effected1(){
  if(document.lowerextremityform.getAttribute("standing_on_effected1")=="true"){
    document.lowerextremityform.standing_on_effected[0].checked = false;
    document.lowerextremityform.setAttribute("standing_on_effected1","false");
  } else
  {  
    document.lowerextremityform.setAttribute("standing_on_effected1","true");
    document.lowerextremityform.setAttribute("standing_on_effected2","false");
    document.lowerextremityform.setAttribute("standing_on_effected3","false");
	}
  }
  function checkradiostanding_on_effected2(theRadio){    
  if(document.lowerextremityform.getAttribute("standing_on_effected2")=="true"){
    document.lowerextremityform.standing_on_effected[1].checked = false;
    document.lowerextremityform.setAttribute("standing_on_effected2","false");
  } else
  {  
    document.lowerextremityform.setAttribute("standing_on_effected2","true");
    document.lowerextremityform.setAttribute("standing_on_effected1","false");
    document.lowerextremityform.setAttribute("standing_on_effected3","false");
  }
  }
  function checkradiostanding_on_effected3(theRadio){    
  if(document.lowerextremityform.getAttribute("standing_on_effected3")=="true"){
    document.lowerextremityform.standing_on_effected[2].checked = false;
    document.lowerextremityform.setAttribute("standing_on_effected3","false");
  } else
  {  
    document.lowerextremityform.setAttribute("standing_on_effected3","true");
    document.lowerextremityform.setAttribute("standing_on_effected1","false");
    document.lowerextremityform.setAttribute("standing_on_effected2","false");
  }
  }





function checkradiosquatting_on_floor1(){
  if(document.lowerextremityform.getAttribute("squatting_on_floor1")=="true"){
    document.lowerextremityform.squatting_on_floor[0].checked = false;
    document.lowerextremityform.setAttribute("squatting_on_floor1","false");
  } else
  {  
    document.lowerextremityform.setAttribute("squatting_on_floor1","true");
    document.lowerextremityform.setAttribute("squatting_on_floor2","false");
    document.lowerextremityform.setAttribute("squatting_on_floor3","false");
	}
  }
  function checkradiosquatting_on_floor2(theRadio){    
  if(document.lowerextremityform.getAttribute("squatting_on_floor2")=="true"){
    document.lowerextremityform.squatting_on_floor[1].checked = false;
    document.lowerextremityform.setAttribute("squatting_on_floor2","false");
  } else
  {  
    document.lowerextremityform.setAttribute("squatting_on_floor2","true");
    document.lowerextremityform.setAttribute("squatting_on_floor1","false");
    document.lowerextremityform.setAttribute("squatting_on_floor3","false");
  }
  }
  function checkradiosquatting_on_floor3(theRadio){    
  if(document.lowerextremityform.getAttribute("squatting_on_floor3")=="true"){
    document.lowerextremityform.squatting_on_floor[2].checked = false;
    document.lowerextremityform.setAttribute("squatting_on_floor3","false");
  } else
  {  
    document.lowerextremityform.setAttribute("squatting_on_floor3","true");
    document.lowerextremityform.setAttribute("squatting_on_floor1","false");
    document.lowerextremityform.setAttribute("squatting_on_floor2","false");
  }
  }


function checkradiositting_cross_leg1(){
  if(document.lowerextremityform.getAttribute("sitting_cross_leg1")=="true"){
    document.lowerextremityform.sitting_cross_leg[0].checked = false;
    document.lowerextremityform.setAttribute("sitting_cross_leg1","false");
  } else
  {  
    document.lowerextremityform.setAttribute("sitting_cross_leg1","true");
    document.lowerextremityform.setAttribute("sitting_cross_leg2","false");
    document.lowerextremityform.setAttribute("sitting_cross_leg3","false");
	}
  }
  function checkradiositting_cross_leg2(theRadio){    
  if(document.lowerextremityform.getAttribute("sitting_cross_leg2")=="true"){
    document.lowerextremityform.sitting_cross_leg[1].checked = false;
    document.lowerextremityform.setAttribute("sitting_cross_leg2","false");
  } else
  {  
    document.lowerextremityform.setAttribute("sitting_cross_leg2","true");
    document.lowerextremityform.setAttribute("sitting_cross_leg1","false");
    document.lowerextremityform.setAttribute("sitting_cross_leg3","false");
  }
  }
  function checkradiositting_cross_leg3(theRadio){    
  if(document.lowerextremityform.getAttribute("sitting_cross_leg3")=="true"){
    document.lowerextremityform.sitting_cross_leg[2].checked = false;
    document.lowerextremityform.setAttribute("sitting_cross_leg3","false");
  } else
  {  
    document.lowerextremityform.setAttribute("sitting_cross_leg3","true");
    document.lowerextremityform.setAttribute("sitting_cross_leg1","false");
    document.lowerextremityform.setAttribute("sitting_cross_leg2","false");
  }
  }




function checkradiokneeling1(){
  if(document.lowerextremityform.getAttribute("kneeling1")=="true"){
    document.lowerextremityform.kneeling[0].checked = false;
    document.lowerextremityform.setAttribute("kneeling1","false");
  } else
  {  
    document.lowerextremityform.setAttribute("kneeling1","true");
    document.lowerextremityform.setAttribute("kneeling2","false");
    document.lowerextremityform.setAttribute("kneeling3","false");
	}
  }
  function checkradiokneeling2(theRadio){    
  if(document.lowerextremityform.getAttribute("kneeling2")=="true"){
    document.lowerextremityform.kneeling[1].checked = false;
    document.lowerextremityform.setAttribute("kneeling2","false");
  } else
  {  
    document.lowerextremityform.setAttribute("kneeling2","true");
    document.lowerextremityform.setAttribute("kneeling1","false");
    document.lowerextremityform.setAttribute("kneeling3","false");
  }
  }
  function checkradiokneeling3(theRadio){    
  if(document.lowerextremityform.getAttribute("kneeling3")=="true"){
    document.lowerextremityform.kneeling[2].checked = false;
    document.lowerextremityform.setAttribute("kneeling3","false");
  } else
  {  
    document.lowerextremityform.setAttribute("kneeling3","true");
    document.lowerextremityform.setAttribute("kneeling1","false");
    document.lowerextremityform.setAttribute("kneeling2","false");
  }
  }




function checkradiotaking_turns1(){
  if(document.lowerextremityform.getAttribute("taking_turns1")=="true"){
    document.lowerextremityform.taking_turns[0].checked = false;
    document.lowerextremityform.setAttribute("taking_turns1","false");
  } else
  {  
    document.lowerextremityform.setAttribute("taking_turns1","true");
    document.lowerextremityform.setAttribute("taking_turns2","false");
    document.lowerextremityform.setAttribute("taking_turns3","false");
	}
  }
  function checkradiotaking_turns2(theRadio){    
  if(document.lowerextremityform.getAttribute("taking_turns2")=="true"){
    document.lowerextremityform.taking_turns[1].checked = false;
    document.lowerextremityform.setAttribute("taking_turns2","false");
  } else
  {  
    document.lowerextremityform.setAttribute("taking_turns2","true");
    document.lowerextremityform.setAttribute("taking_turns1","false");
    document.lowerextremityform.setAttribute("taking_turns3","false");
  }
  }
  function checkradiotaking_turns3(theRadio){    
  if(document.lowerextremityform.getAttribute("taking_turns3")=="true"){
    document.lowerextremityform.taking_turns[2].checked = false;
    document.lowerextremityform.setAttribute("taking_turns3","false");
  } else
  {  
    document.lowerextremityform.setAttribute("taking_turns3","true");
    document.lowerextremityform.setAttribute("taking_turns1","false");
    document.lowerextremityform.setAttribute("taking_turns2","false");
  }
  }









function resetbutton(form){

  document.lowerextremityform.deformity[0].checked = false;
  document.lowerextremityform.deformity[1].checked = false;
 document.lowerextremityform.pain[0].checked = false;
  document.lowerextremityform.pain[1].checked=false;
document.lowerextremityform.pain[2].checked=false;
  document.lowerextremityform.loss_of_Function[0].checked=false;
document.lowerextremityform.loss_of_Function[1].checked=false;
  document.lowerextremityform.complications[0].checked=false;
document.lowerextremityform.complications[1].checked=false;
   document.lowerextremityform.working_on_plane[0].checked = false;
document.lowerextremityform.working_on_plane[1].checked = false;
document.lowerextremityform.working_on_plane[2].checked = false;
  document.lowerextremityform.working_on_slope[0].checked=false;
document.lowerextremityform.working_on_slope[1].checked=false;
document.lowerextremityform.working_on_slope[2].checked=false;
  document.lowerextremityform.working_on_stairs[0].checked=false;
document.lowerextremityform.working_on_stairs[1].checked=false;
document.lowerextremityform.working_on_stairs[2].checked=false;
  document.lowerextremityform.standing_on_both_legs[0].checked=false;
document.lowerextremityform.standing_on_both_legs[1].checked=false;
document.lowerextremityform.standing_on_both_legs[2].checked=false;
  document.lowerextremityform.standing_on_effected[0].checked=false;
document.lowerextremityform.standing_on_effected[1].checked=false;
document.lowerextremityform.standing_on_effected[2].checked=false;
  document.lowerextremityform.squatting_on_floor[0].checked=false;
document.lowerextremityform.squatting_on_floor[1].checked=false;
document.lowerextremityform.squatting_on_floor[2].checked=false;
  document.lowerextremityform.sitting_cross_leg[0].checked=false;
document.lowerextremityform.sitting_cross_leg[1].checked=false;
document.lowerextremityform.sitting_cross_leg[2].checked=false;
  document.lowerextremityform.kneeling[0].checked=false;
document.lowerextremityform.kneeling[1].checked=false;
document.lowerextremityform.kneeling[2].checked=false;
  document.lowerextremityform.taking_turns[0].checked=false;
document.lowerextremityform.taking_turns[1].checked=false;
document.lowerextremityform.taking_turns[2].checked=false;

document.lowerextremityform.romhipflexionextensionright.value="";
document.lowerextremityform.romhiprotationright.value="";
document.lowerextremityform.romhipabductionadductionright.value="";
document.lowerextremityform.romhipflexionextensionleft.value="";
document.lowerextremityform.romhiprotationleft.value="";
document.lowerextremityform.romhipabductionadductionleft.value="";
document.lowerextremityform.romkneeflexionextensionright.value="";
document.lowerextremityform.romkneeflexionextensionleft.value="";
document.lowerextremityform.romankledorsiflexionpalmarflexionright.value="";
document.lowerextremityform.romankleinversioneversionright.value="";
document.lowerextremityform.romankledorsiflexionpalmarflexionleft.value="";
document.lowerextremityform.romankleinversioneversionleft.value="";
document.lowerextremityform.mshipflexormusclesright.value="";
document.lowerextremityform.mshipextensormusclesright.value="";
document.lowerextremityform.mshiprotatormusclesright.value="";
document.lowerextremityform.mshipabductormusclesright.value="";
document.lowerextremityform.mshipadductormusclesright.value="";
document.lowerextremityform.mshipflexormusclesleft.value="";
document.lowerextremityform.mshipextensormusclesleft.value="";
document.lowerextremityform.mshiprotatormusclesleft.value="";
document.lowerextremityform.mshipabductormusclesleft.value="";
document.lowerextremityform.mshipadductormusclesleft.value="";
document.lowerextremityform.mskneeflexormusclesright.value="";
document.lowerextremityform.mskneeextensormusclesright.value="";
document.lowerextremityform.mskneeflexormusclesleft.value="";
document.lowerextremityform.mskneeextensormusclesleft.value="";
document.lowerextremityform.msankleplanterflexormusclesright.value="";
document.lowerextremityform.msankledorsiflexormusclesright.value="";
document.lowerextremityform.msankleinvertormusclesright.value="";
document.lowerextremityform.msankleevertormusclesright.value="";
document.lowerextremityform.msankleplanterflexormusclesleft.value="";
document.lowerextremityform.msankledorsiflexormusclesleft.value="";
document.lowerextremityform.msankleinvertormusclesleft.value="";
document.lowerextremityform.msankleevertormusclesleft.value="";

document.lowerextremityform.shortening.value="";
}





