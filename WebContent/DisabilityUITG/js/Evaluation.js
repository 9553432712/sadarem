 function uncheckRadio() {
      var choice = document.form1.intellectualimpairment;
      for (i = 0; i < choice.length; i++) {
      if ( choice[i].checked = true ) 
      choice[i].checked = false; 
 }
}

function uncheckRadio1() {
   var choice = document.form1.speechdefect;
   for (i = 0; i < choice.length; i++) {
   if ( choice[i].checked = true ) 
   choice[i].checked = false; 
    }
   }
function uncheckRadio2() {
  var choice = document.form1.motorsystem;
  for (i = 0; i < choice.length; i++) {
  if ( choice[i].checked = true ) 
  choice[i].checked = false; 
    }
  }
function uncheckRadio3() {
  var choice = document.form1.bladderinvolvement;
  for (i = 0; i < choice.length; i++) {
  if ( choice[i].checked = true ) 
   choice[i].checked = false; 
    }
   }
 function uncheckRadio4() {
   var choice = document.form1.injury;
   for (i = 0; i < choice.length; i++) {
   if ( choice[i].checked = true ) 
   choice[i].checked = false; 
     }
   }
function uncheckRadio6() {
   var choice = document.form1.sensorysystem;
   for (i = 0; i < choice.length; i++) {
   if ( choice[i].checked = true ) 
   choice[i].checked = false; 
    }
   }
 function uncheckRadio7() {
    var choice = document.form1.sensorysystem1;
    for (i = 0; i < choice.length; i++) {
    if ( choice[i].checked = true ) 
    choice[i].checked = false; 
    }
  }
function uncheckRadio5() {
    var choice = document.form1.ataxia;
    for (i = 0; i < choice.length; i++) {
    if ( choice[i].checked = true ) 
    choice[i].checked = false; 
   }
     }

function uncheckRadio8() {
   var choice = document.form1.sensorysystem2;
   for (i = 0; i < choice.length; i++) {
   if ( choice[i].checked = true ) 
   choice[i].checked = false; 
    }
   }

   function uncheckRadio9() {
   var choice = document.form1.motorsystemLeftOrRight;
   for (i = 0; i < choice.length; i++) {
   if ( choice[i].checked = true )
   choice[i].checked = false;
   uncheckRadio2();
   enableRadioButton();
    }
   }

   function enableRadioButton()
    {
        var buttonRight = document.getElementById("4").checked;
        var buttonLeft = document.getElementById("5").checked;
        if(buttonRight == false && buttonLeft == false)
            {
                for(var i=1;i<=3;i++){
                    document.getElementById(i).disabled=true;
                }
                
            }
            else {
                for(var j=1;j<=3;j++){
                    document.getElementById(j).disabled=false;
                }
                
            }
            return false;
    }

    function selectRedioButton()
    {
        var buttonRight = document.getElementById("4").checked;
        var buttonLeft = document.getElementById("5").checked;
        if(buttonRight == true || buttonLeft == true)
        {
            var mild = document.getElementById("1").checked;
            var moderate = document.getElementById("2").checked;
            var severe = document.getElementById("3").checked;
            if(mild == false && moderate == false && severe == false)
                {
            alert("Please Enter 6.7 Neurological Involvement Mild/Moderate/Severe!");
            return false;
                }
        }        
            return true;
    }

   

function resetbutton()
{
            document.getElementById("dominantupperextremity").checked="";
            document.getElementById("lossofsensationupper").checked="";
            document.getElementById("lossofsensationlower").checked="";
            document.getElementById("neurologicalstatus").checked="";
            document.getElementById("sensorysystemh").checked="";
            document.getElementById("sensorysystemf").checked="";
            document.getElementById("sensorysystemt").checked="";
            document.getElementById("e6a").checked="";
            document.getElementById("e6b").checked="";
            document.getElementById("e6c").checked="";
            document.getElementById("e6d").checked="";
            document.getElementById("e6e").checked="";
            document.getElementById("e6f").checked="";
            document.getElementById("e6g").checked="";
            document.getElementById("e6h").checked="";
            document.getElementById("sca").checked="";
            document.getElementById("scb").checked="";
            document.getElementById("scc").checked="";
            document.getElementById("scd").checked="";
            document.form1.intellectualimpairment[0].checked = false;
            document.form1.intellectualimpairment[1].checked = false;
            document.form1.intellectualimpairment[2].checked = false;
            document.form1.intellectualimpairment[3].checked = false;
            document.form1.intellectualimpairment[4].checked = false;
             document.form1.speechdefect[0].checked = false;
            document.form1.speechdefect[1].checked = false;
            document.form1.speechdefect[2].checked = false;
            document.form1.motorsystem[0].checked = false;
            document.form1.motorsystem[1].checked = false;
            document.form1.motorsystem[2].checked = false;
            document.form1.motorsystemLeftOrRight[0].checked = false;
            document.form1.motorsystemLeftOrRight[1].checked = false;
            document.form1.sensorysystem[0].checked = false;
            document.form1.sensorysystem[1].checked = false;
            document.form1.sensorysystem[2].checked = false;
            document.form1.sensorysystem1[0].checked = false;
            document.form1.sensorysystem1[1].checked = false;
            document.form1.sensorysystem1[2].checked = false;    
 
             document.form1.sensorysystem2[0].checked = false;
            document.form1.sensorysystem2[1].checked = false;
            document.form1.sensorysystem2[2].checked = false;
            document.form1.bladderinvolvement[0].checked = false;
            document.form1.bladderinvolvement[1].checked = false;
            document.form1.bladderinvolvement[2].checked = false; 
           document.form1.bladderinvolvement[3].checked = false;
            document.form1.injury[0].checked = false;
            document.form1.injury[1].checked = false; 
            document.form1.injury[2].checked = false;
            document.form1.injury[3].checked = false; 

           document.form1.ataxia[0].checked = false; 
           document.form1.ataxia[1].checked = false;
           document.form1.ataxia[2].checked = false; 
           document.form1.ataxia[3].checked = false; 
           

}
