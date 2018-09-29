

function validatecheckboxupperright(){
    
    
    
    
    if(document.Amputation.upper_fore_right.checked||document.Amputation.upper_shoulder_right.checked||document.Amputation.upper_shoulder_right.checked||document.Amputation.upper_shoulder_right.checked||document.Amputation.upper_aboveelbowupper_right.checked||document.Amputation.upper_elbowlower_right.checked||document.Amputation.upper_elbowdis_right.checked||document.Amputation.upper_belowelbowupper_right.checked||document.Amputation.upper_belowelbowlower_right.checked||document.Amputation.upper_waistdis_right.checked||document.Amputation.upper_belowelbowupper_right.checked||document.Amputation.upper_handcarpel_right.checked){
        if(document.Amputation.upper_shoulder_right.checked){
            document.Amputation.upper_fore_right.disabled=true;
        } else {
            document.Amputation.upper_shoulder_right.disabled=true;
        }
        
        if(document.Amputation.upper_aboveelbowupper_right.checked){
            document.Amputation.upper_fore_right.disabled=true;
            document.Amputation.upper_shoulder_right.disabled=true;
        } else {
            document.Amputation.upper_aboveelbowupper_right.disabled=true;
        }
        
        
        
        if(document.Amputation.upper_elbowlower_right.checked){
            document.Amputation.upper_fore_right.disabled=true;
            document.Amputation.upper_shoulder_right.disabled=true;
            document.Amputation.upper_aboveelbowupper_right.disabled=true;
        } else {
            document.Amputation.upper_elbowlower_right.disabled=true;
        }
        
        if(document.Amputation.upper_elbowdis_right.checked){
            document.Amputation.upper_fore_right.disabled=true;
            document.Amputation.upper_shoulder_right.disabled=true;
            document.Amputation.upper_aboveelbowupper_right.disabled=true;
            document.Amputation.upper_elbowlower_right.disabled=true;
        } else {
            document.Amputation.upper_elbowdis_right.disabled=true;
        }
        
        if(document.Amputation.upper_belowelbowupper_right.checked){
            document.Amputation.upper_fore_right.disabled=true;
            document.Amputation.upper_shoulder_right.disabled=true;
            document.Amputation.upper_aboveelbowupper_right.disabled=true;
            document.Amputation.upper_elbowlower_right.disabled=true;
            document.Amputation.upper_elbowdis_right.disabled=true;
        } else {
            document.Amputation.upper_belowelbowupper_right.disabled=true;
        }
        
        if(document.Amputation.upper_belowelbowlower_right.checked){
            document.Amputation.upper_fore_right.disabled=true;
            document.Amputation.upper_shoulder_right.disabled=true;
            document.Amputation.upper_aboveelbowupper_right.disabled=true;
            document.Amputation.upper_elbowlower_right.disabled=true;
            document.Amputation.upper_elbowdis_right.disabled=true;
            document.Amputation.upper_belowelbowupper_right.disabled=true;
        } else {
            document.Amputation.upper_belowelbowlower_right.disabled=true;
            
        }
        
        if(document.Amputation.upper_waistdis_right.checked){
            document.Amputation.upper_fore_right.disabled=true;
            document.Amputation.upper_shoulder_right.disabled=true;
            document.Amputation.upper_aboveelbowupper_right.disabled=true;
            document.Amputation.upper_elbowlower_right.disabled=true;
            document.Amputation.upper_elbowdis_right.disabled=true;
            document.Amputation.upper_belowelbowupper_right.disabled=true;
            document.Amputation.upper_belowelbowlower_right.disabled=true;
        } else {
            document.Amputation.upper_waistdis_right.disabled=true;
        }
        if(document.Amputation.upper_handcarpel_right.checked){
            document.Amputation.upper_fore_right.disabled=true;
            document.Amputation.upper_shoulder_right.disabled=true;
            document.Amputation.upper_aboveelbowupper_right.disabled=true;
            document.Amputation.upper_elbowlower_right.disabled=true;
            document.Amputation.upper_elbowdis_right.disabled=true;
            document.Amputation.upper_belowelbowupper_right.disabled=true;
            document.Amputation.upper_belowelbowlower_right.disabled=true;
            document.Amputation.upper_waistdis_right.disabled=true;
        } else {
            document.Amputation.upper_handcarpel_right.disabled=true;
            
        }
//upto carpel bone
        
        
        
        document.Amputation.upper_thumbCM_right.checked=false;
        document.Amputation.upper_thumbCM_right.disabled=true;
        
        document.Amputation.upper_thumbMCP_right.checked=false;
        document.Amputation.upper_thumbMCP_right.disabled=true;
        
        document.Amputation.upper_thumbIP_right.checked=false;
        document.Amputation.upper_thumbIP_right.disabled=true;
        
        document.Amputation.upper_MPIndex_right.checked=false;
        document.Amputation.upper_MPIndex_right.disabled=true;
        
        document.Amputation.upper_MPMiddle_right.checked=false;
        document.Amputation.upper_MPMiddle_right.disabled=true;
        
        document.Amputation.upper_MPRing_right.checked=false;
        document.Amputation.upper_MPRing_right.disabled=true;
        
        document.Amputation.upper_MPLittle_right.checked=false;
        document.Amputation.upper_MPLittle_right.disabled=true;
        
        document.Amputation.upper_PIPIndex_right.checked=false;
        document.Amputation.upper_PIPIndex_right.disabled=true;
        
        document.Amputation.upper_PIPMiddle_right.checked=false;
        document.Amputation.upper_PIPMiddle_right.disabled=true;
        
        document.Amputation.upper_PIPRing_right.checked=false;
        document.Amputation.upper_PIPRing_right.disabled=true;
        
        document.Amputation.upper_PIPLittle_right.checked=false;
        document.Amputation.upper_PIPLittle_right.disabled=true;
        
        document.Amputation.upper_DIPIndex_right.checked=false;
        document.Amputation.upper_DIPIndex_right.disabled=true;
        
        document.Amputation.upper_DIPMiddle_right.checked=false;
        document.Amputation.upper_DIPMiddle_right.disabled=true;
        
        document.Amputation.upper_DIPRing_right.checked=false;
        document.Amputation.upper_DIPRing_right.disabled=true;
        
        document.Amputation.upper_DIPLittle_right.checked=false;
        document.Amputation.upper_DIPLittle_right.disabled=true;
        
    } else {
        document.Amputation.upper_shoulder_right.disabled=false;
        document.Amputation.upper_fore_right.disabled=false;
        document.Amputation.upper_shoulder_right.disabled=false;
        document.Amputation.upper_aboveelbowupper_right.disabled=false;
        document.Amputation.upper_elbowlower_right.disabled=false;
        document.Amputation.upper_elbowdis_right.disabled=false;
        document.Amputation.upper_belowelbowupper_right.disabled=false;
        document.Amputation.upper_belowelbowlower_right.disabled=false;
        document.Amputation.upper_waistdis_right.disabled=false;
        document.Amputation.upper_handcarpel_right.disabled=false;
        document.Amputation.upper_thumbCM_right.disabled=false;
        document.Amputation.upper_thumbMCP_right.disabled=false;
        document.Amputation.upper_thumbIP_right.disabled=false;
        document.Amputation.upper_MPIndex_right.disabled=false;
        document.Amputation.upper_MPMiddle_right.disabled=false;
        document.Amputation.upper_MPRing_right.disabled=false;
        document.Amputation.upper_MPLittle_right.disabled=false;
        document.Amputation.upper_PIPIndex_right.disabled=false;
        document.Amputation.upper_PIPMiddle_right.disabled=false;
        document.Amputation.upper_PIPRing_right.disabled=false;
        document.Amputation.upper_PIPLittle_right.disabled=false;
        document.Amputation.upper_DIPIndex_right.disabled=false;
        document.Amputation.upper_DIPMiddle_right.disabled=false;
        document.Amputation.upper_DIPRing_right.disabled=false;
        document.Amputation.upper_DIPLittle_right.disabled=false;
        
         
        
    }
}









function validatecheckboxupperleft(){
    
   
    
    
    if(document.Amputation.upper_fore_left.checked||document.Amputation.upper_shoulder_left.checked||document.Amputation.upper_shoulder_left.checked||document.Amputation.upper_shoulder_left.checked||document.Amputation.upper_aboveelbowupper_left.checked||document.Amputation.upper_elbowlower_left.checked||document.Amputation.upper_elbowdis_left.checked||document.Amputation.upper_belowelbowupper_left.checked||document.Amputation.upper_belowelbowlower_left.checked||document.Amputation.upper_waistdis_left.checked||document.Amputation.upper_belowelbowupper_left.checked||document.Amputation.upper_handcarpel_left.checked){
        
        
         





        if(document.Amputation.upper_shoulder_left.checked){
            document.Amputation.upper_fore_left.disabled=true;
        } else {
            document.Amputation.upper_shoulder_left.disabled=true;
        }
        
        if(document.Amputation.upper_aboveelbowupper_left.checked){
            document.Amputation.upper_fore_left.disabled=true;
            document.Amputation.upper_shoulder_left.disabled=true;
        } else {
            document.Amputation.upper_aboveelbowupper_left.disabled=true;
        }
        
        
        
        if(document.Amputation.upper_elbowlower_left.checked){
            document.Amputation.upper_fore_left.disabled=true;
            document.Amputation.upper_shoulder_left.disabled=true;
            document.Amputation.upper_aboveelbowupper_left.disabled=true;
        } else {
            document.Amputation.upper_elbowlower_left.disabled=true;
        }
        
        if(document.Amputation.upper_elbowdis_left.checked){
            document.Amputation.upper_fore_left.disabled=true;
            document.Amputation.upper_shoulder_left.disabled=true;
            document.Amputation.upper_aboveelbowupper_left.disabled=true;
            document.Amputation.upper_elbowlower_left.disabled=true;
        } else {
            document.Amputation.upper_elbowdis_left.disabled=true;
        }
        
        if(document.Amputation.upper_belowelbowupper_left.checked){
            document.Amputation.upper_fore_left.disabled=true;
            document.Amputation.upper_shoulder_left.disabled=true;
            document.Amputation.upper_aboveelbowupper_left.disabled=true;
            document.Amputation.upper_elbowlower_left.disabled=true;
            document.Amputation.upper_elbowdis_left.disabled=true;
        } else {
            document.Amputation.upper_belowelbowupper_left.disabled=true;
        }
        
        if(document.Amputation.upper_belowelbowlower_left.checked){
            document.Amputation.upper_fore_left.disabled=true;
            document.Amputation.upper_shoulder_left.disabled=true;
            document.Amputation.upper_aboveelbowupper_left.disabled=true;
            document.Amputation.upper_elbowlower_left.disabled=true;
            document.Amputation.upper_elbowdis_left.disabled=true;
            document.Amputation.upper_belowelbowupper_left.disabled=true;
        } else {
            document.Amputation.upper_belowelbowlower_left.disabled=true;
            
        }
        
        if(document.Amputation.upper_waistdis_left.checked){
            document.Amputation.upper_fore_left.disabled=true;
            document.Amputation.upper_shoulder_left.disabled=true;
            document.Amputation.upper_aboveelbowupper_left.disabled=true;
            document.Amputation.upper_elbowlower_left.disabled=true;
            document.Amputation.upper_elbowdis_left.disabled=true;
            document.Amputation.upper_belowelbowupper_left.disabled=true;
            document.Amputation.upper_belowelbowlower_left.disabled=true;
        } else {
            document.Amputation.upper_waistdis_left.disabled=true;
        }
        if(document.Amputation.upper_handcarpel_left.checked){
            document.Amputation.upper_fore_left.disabled=true;
            document.Amputation.upper_shoulder_left.disabled=true;
            document.Amputation.upper_aboveelbowupper_left.disabled=true;
            document.Amputation.upper_elbowlower_left.disabled=true;
            document.Amputation.upper_elbowdis_left.disabled=true;
            document.Amputation.upper_belowelbowupper_left.disabled=true;
            document.Amputation.upper_belowelbowlower_left.disabled=true;
            document.Amputation.upper_waistdis_left.disabled=true;
        } else {
            document.Amputation.upper_handcarpel_left.disabled=true;
            
        }
//upto carpel bone
        
        
        
        document.Amputation.upper_thumbCM_left.checked=false;
        document.Amputation.upper_thumbCM_left.disabled=true;
        
        document.Amputation.upper_thumbMCP_left.checked=false;
        document.Amputation.upper_thumbMCP_left.disabled=true;
        
        document.Amputation.upper_thumbIP_left.checked=false;
        document.Amputation.upper_thumbIP_left.disabled=true;
        
        document.Amputation.upper_MPIndex_left.checked=false;
        document.Amputation.upper_MPIndex_left.disabled=true;
        
        document.Amputation.upper_MPMiddle_left.checked=false;
        document.Amputation.upper_MPMiddle_left.disabled=true;
        
        document.Amputation.upper_MPRing_left.checked=false;
        document.Amputation.upper_MPRing_left.disabled=true;
        
        document.Amputation.upper_MPLittle_left.checked=false;
        document.Amputation.upper_MPLittle_left.disabled=true;
        
        document.Amputation.upper_PIPIndex_left.checked=false;
        document.Amputation.upper_PIPIndex_left.disabled=true;
        
        document.Amputation.upper_PIPMiddle_left.checked=false;
        document.Amputation.upper_PIPMiddle_left.disabled=true;
        
        document.Amputation.upper_PIPRing_left.checked=false;
        document.Amputation.upper_PIPRing_left.disabled=true;
        
        document.Amputation.upper_PIPLittle_left.checked=false;
        document.Amputation.upper_PIPLittle_left.disabled=true;
        
        document.Amputation.upper_DIPIndex_left.checked=false;
        document.Amputation.upper_DIPIndex_left.disabled=true;
        
        document.Amputation.upper_DIPMiddle_left.checked=false;
        document.Amputation.upper_DIPMiddle_left.disabled=true;
        
        document.Amputation.upper_DIPRing_left.checked=false;
        document.Amputation.upper_DIPRing_left.disabled=true;
        
        document.Amputation.upper_DIPLittle_left.checked=false;
        document.Amputation.upper_DIPLittle_left.disabled=true;
        
    } else {
        document.Amputation.upper_shoulder_left.disabled=false;
        document.Amputation.upper_fore_left.disabled=false;
        document.Amputation.upper_shoulder_left.disabled=false;
        document.Amputation.upper_aboveelbowupper_left.disabled=false;
        document.Amputation.upper_elbowlower_left.disabled=false;
        document.Amputation.upper_elbowdis_left.disabled=false;
        document.Amputation.upper_belowelbowupper_left.disabled=false;
        document.Amputation.upper_belowelbowlower_left.disabled=false;
        document.Amputation.upper_waistdis_left.disabled=false;
        document.Amputation.upper_handcarpel_left.disabled=false;
        document.Amputation.upper_thumbCM_left.disabled=false;
        document.Amputation.upper_thumbMCP_left.disabled=false;
        document.Amputation.upper_thumbIP_left.disabled=false;
        document.Amputation.upper_MPIndex_left.disabled=false;
        document.Amputation.upper_MPMiddle_left.disabled=false;
        document.Amputation.upper_MPRing_left.disabled=false;
        document.Amputation.upper_MPLittle_left.disabled=false;
        document.Amputation.upper_PIPIndex_left.disabled=false;
        document.Amputation.upper_PIPMiddle_left.disabled=false;
        document.Amputation.upper_PIPRing_left.disabled=false;
        document.Amputation.upper_PIPLittle_left.disabled=false;
        document.Amputation.upper_DIPIndex_left.disabled=false;
        document.Amputation.upper_DIPMiddle_left.disabled=false;
        document.Amputation.upper_DIPRing_left.disabled=false;
        document.Amputation.upper_DIPLittle_left.disabled=false;
        
        
    }
}



























function validatecheckboxupperrightCMMCPIP(){
    
    
    if(document.Amputation.upper_thumbCM_right.checked||document.Amputation.upper_thumbMCP_right.checked||document.Amputation.upper_thumbIP_right.checked){
        
        
        document.Amputation.upper_handcarpel_right.disabled=true;
        document.Amputation.upper_fore_right.disabled=true;
        document.Amputation.upper_shoulder_right.disabled=true;
        document.Amputation.upper_aboveelbowupper_right.disabled=true;
        document.Amputation.upper_elbowlower_right.disabled=true;
        document.Amputation.upper_elbowdis_right.disabled=true;
        document.Amputation.upper_belowelbowupper_right.disabled=true;
        document.Amputation.upper_belowelbowlower_right.disabled=true;
        document.Amputation.upper_waistdis_right.disabled=true;
        
        if(document.Amputation.upper_thumbCM_right.checked){
            document.Amputation.upper_thumbMCP_right.disabled=true;
            document.Amputation.upper_thumbIP_right.disabled=true;
        }
        if(document.Amputation.upper_thumbMCP_right.checked){
            document.Amputation.upper_thumbCM_right.disabled=true;
            document.Amputation.upper_thumbIP_right.disabled=true;
        }
        if(document.Amputation.upper_thumbIP_right.checked){
            document.Amputation.upper_thumbCM_right.disabled=true;
            document.Amputation.upper_thumbMCP_right.disabled=true;
        }
    }else{
        document.Amputation.upper_handcarpel_right.disabled=false;
        document.Amputation.upper_fore_right.disabled=false;
        document.Amputation.upper_shoulder_right.disabled=false;
        document.Amputation.upper_aboveelbowupper_right.disabled=false;
        document.Amputation.upper_elbowlower_right.disabled=false;
        document.Amputation.upper_elbowdis_right.disabled=false;
        document.Amputation.upper_belowelbowupper_right.disabled=false;
        document.Amputation.upper_belowelbowlower_right.disabled=false;
        document.Amputation.upper_waistdis_right.disabled=false;
        document.Amputation.upper_thumbCM_right.disabled=false;
        document.Amputation.upper_thumbMCP_right.disabled=false;
        document.Amputation.upper_thumbIP_right.disabled=false;
        
        
       
        
    }
}

function validatecheckboxupperrightfingers(){
    
    
    
    if(document.Amputation.upper_MPIndex_right.checked||document.Amputation.upper_MPMiddle_right.checked||document.Amputation.upper_MPRing_right.checked||document.Amputation.upper_MPLittle_right.checked||document.Amputation.upper_DIPIndex_right.checked||document.Amputation.upper_DIPMiddle_right.checked||document.Amputation.upper_DIPRing_right.checked||document.Amputation.upper_DIPLittle_right.checked||document.Amputation.upper_PIPIndex_right.checked||document.Amputation.upper_PIPMiddle_right.checked||document.Amputation.upper_PIPRing_right.checked||document.Amputation.upper_PIPLittle_right.checked){
        
      
        
        
        
        if(document.Amputation.upper_MPIndex_right.checked){
            document.Amputation.upper_DIPIndex_right.disabled=true;
            document.Amputation.upper_PIPIndex_right.disabled=true;
        }
        if(document.Amputation.upper_DIPIndex_right.checked){
            document.Amputation.upper_MPIndex_right.disabled=true;
            document.Amputation.upper_PIPIndex_right.disabled=true;
        }
        if(document.Amputation.upper_PIPIndex_right.checked){
            document.Amputation.upper_MPIndex_right.disabled=true;
            document.Amputation.upper_DIPIndex_right.disabled=true;
        }
        
        if(document.Amputation.upper_MPMiddle_right.checked){
            document.Amputation.upper_DIPMiddle_right.disabled=true;
            document.Amputation.upper_PIPMiddle_right.disabled=true;
        }
        if(document.Amputation.upper_DIPMiddle_right.checked){
            document.Amputation.upper_MPMiddle_right.disabled=true;
            document.Amputation.upper_PIPMiddle_right.disabled=true;
        }
        if(document.Amputation.upper_PIPMiddle_right.checked){
            document.Amputation.upper_MPMiddle_right.disabled=true;
            document.Amputation.upper_DIPMiddle_right.disabled=true;
        }
        
        if(document.Amputation.upper_MPRing_right.checked){
            document.Amputation.upper_DIPRing_right.disabled=true;
            document.Amputation.upper_PIPRing_right.disabled=true;
        }
        if(document.Amputation.upper_DIPRing_right.checked){
            document.Amputation.upper_MPRing_right.disabled=true;
            document.Amputation.upper_PIPRing_right.disabled=true;
        }
        if(document.Amputation.upper_PIPRing_right.checked){
            document.Amputation.upper_MPRing_right.disabled=true;
            document.Amputation.upper_DIPRing_right.disabled=true;
        }
        
        if(document.Amputation.upper_MPLittle_right.checked){
            document.Amputation.upper_DIPLittle_right.disabled=true;
            document.Amputation.upper_PIPLittle_right.disabled=true;
        }
        if(document.Amputation.upper_DIPLittle_right.checked){
            document.Amputation.upper_MPLittle_right.disabled=true;
            document.Amputation.upper_PIPLittle_right.disabled=true;
        }
        if(document.Amputation.upper_PIPLittle_right.checked){
            document.Amputation.upper_MPLittle_right.disabled=true;
            document.Amputation.upper_DIPLittle_right.disabled=true;
        }
        
    }else{
        document.Amputation.upper_MPIndex_right.disabled=false;
        document.Amputation.upper_MPMiddle_right.disabled=false;
        document.Amputation.upper_MPRing_right.disabled=false;
        document.Amputation.upper_MPLittle_right.disabled=false;
        document.Amputation.upper_PIPIndex_right.disabled=false;
        document.Amputation.upper_PIPMiddle_right.disabled=false;
        document.Amputation.upper_PIPRing_right.disabled=false;
        document.Amputation.upper_PIPLittle_right.disabled=false;
        document.Amputation.upper_DIPIndex_right.disabled=false;
        document.Amputation.upper_DIPMiddle_right.disabled=false;
        document.Amputation.upper_DIPRing_right.disabled=false;
        document.Amputation.upper_DIPLittle_right.disabled=false;
        
        
       
        
    }
}



function validatecheckboxlowerright(){
    if(document.Amputation.lower_hind_right.checked||document.Amputation.lower_hip_right.checked||document.Amputation.lower_AKupper_right.checked||document.Amputation.lower_AKlower_right.checked||document.Amputation.lower_truknee_right.checked||document.Amputation.lower_bk8cm_right.checked||document.Amputation.lower_bklower_right.checked||document.Amputation.lower_truankle_right.checked||document.Amputation.lower_symes_right.checked||document.Amputation.lower_uptomid_right.checked||document.Amputation.lower_uptofore_right.checked){
        
        {
            if(document.Amputation.lower_hip_right.checked)
                document.Amputation.lower_hind_right.disabled=true;
            else
                document.Amputation.lower_hip_right.disabled=true;
            
            if(document.Amputation.lower_AKupper_right.checked){
                document.Amputation.lower_hind_right.disabled=true;
                document.Amputation.lower_hip_right.disabled=true;
            }else{
                document.Amputation.lower_AKupper_right.disabled=true;
            }
            
            if(document.Amputation.lower_AKlower_right.checked){
                document.Amputation.lower_hind_right.disabled=true;
                document.Amputation.lower_hip_right.disabled=true;
                document.Amputation.lower_AKupper_right.disabled=true;
            }else{
                document.Amputation.lower_AKlower_right.disabled=true;
            }
            
            if(document.Amputation.lower_truknee_right.checked){
                document.Amputation.lower_hind_right.disabled=true;
                document.Amputation.lower_hip_right.disabled=true;
                document.Amputation.lower_AKupper_right.disabled=true;
                document.Amputation.lower_AKlower_right.disabled=true;
            }else{
                document.Amputation.lower_truknee_right.disabled=true;
            }
            
            if(document.Amputation.lower_bk8cm_right.checked){
                document.Amputation.lower_hind_right.disabled=true;
                document.Amputation.lower_hip_right.disabled=true;
                document.Amputation.lower_AKupper_right.disabled=true;
                document.Amputation.lower_AKlower_right.disabled=true;
                document.Amputation.lower_truknee_right.disabled=true;
            }else{
                document.Amputation.lower_bk8cm_right.disabled=true;
            }
            
            if(document.Amputation.lower_bklower_right.checked){
                document.Amputation.lower_hind_right.disabled=true;
                document.Amputation.lower_hip_right.disabled=true;
                document.Amputation.lower_AKupper_right.disabled=true;
                document.Amputation.lower_AKlower_right.disabled=true;
                document.Amputation.lower_truknee_right.disabled=true;
                document.Amputation.lower_bk8cm_right.disabled=true;
            }else{
                document.Amputation.lower_bklower_right.disabled=true;
            }
            
            if(document.Amputation.lower_truankle_right.checked){
                document.Amputation.lower_hind_right.disabled=true;
                document.Amputation.lower_hip_right.disabled=true;
                document.Amputation.lower_AKupper_right.disabled=true;
                document.Amputation.lower_AKlower_right.disabled=true;
                document.Amputation.lower_truknee_right.disabled=true;
                document.Amputation.lower_bk8cm_right.disabled=true;
                document.Amputation.lower_bklower_right.disabled=true;
            }else{
                document.Amputation.lower_truankle_right.disabled=true;
            }
            
            if(document.Amputation.lower_symes_right.checked){
                document.Amputation.lower_hind_right.disabled=true;
                document.Amputation.lower_hip_right.disabled=true;
                document.Amputation.lower_AKupper_right.disabled=true;
                document.Amputation.lower_AKlower_right.disabled=true;
                document.Amputation.lower_truknee_right.disabled=true;
                document.Amputation.lower_bk8cm_right.disabled=true;
                document.Amputation.lower_bklower_right.disabled=true;
                document.Amputation.lower_truankle_right.disabled=true;
            }else{
                document.Amputation.lower_symes_right.disabled=true;
            }
            
            if(document.Amputation.lower_uptomid_right.checked){
                document.Amputation.lower_hind_right.disabled=true;
                document.Amputation.lower_hip_right.disabled=true;
                document.Amputation.lower_AKupper_right.disabled=true;
                document.Amputation.lower_AKlower_right.disabled=true;
                document.Amputation.lower_truknee_right.disabled=true;
                document.Amputation.lower_bk8cm_right.disabled=true;
                document.Amputation.lower_bklower_right.disabled=true;
                document.Amputation.lower_truankle_right.disabled=true;
                document.Amputation.lower_symes_right.disabled=true;
            }else{
                document.Amputation.lower_uptomid_right.disabled=true;
            }
            
            if(document.Amputation.lower_uptofore_right.checked){
                document.Amputation.lower_hind_right.disabled=true;
                document.Amputation.lower_hip_right.disabled=true;
                document.Amputation.lower_AKupper_right.disabled=true;
                document.Amputation.lower_AKlower_right.disabled=true;
                document.Amputation.lower_truknee_right.disabled=true;
                document.Amputation.lower_bk8cm_right.disabled=true;
                document.Amputation.lower_bklower_right.disabled=true;
                document.Amputation.lower_truankle_right.disabled=true;
                document.Amputation.lower_symes_right.disabled=true;
                document.Amputation.lower_uptomid_right.disabled=true;
            }else{
                document.Amputation.lower_uptofore_right.disabled=true;
            }
            
            document.Amputation.lower_alltoe_right.disabled=true;
            document.Amputation.lower_1sttoe_right.disabled=true;
            document.Amputation.lower_2ndtoe_right.disabled=true;
            document.Amputation.lower_3rdtoe_right.disabled=true;
            document.Amputation.lower_4thtoe_right.disabled=true;
            document.Amputation.lower_5thtoe_right.disabled=true;
            
            document.Amputation.lower_alltoe_right.checked=false;
            document.Amputation.lower_1sttoe_right.checked=false;
            document.Amputation.lower_2ndtoe_right.checked=false;
            document.Amputation.lower_3rdtoe_right.checked=false;
            document.Amputation.lower_4thtoe_right.checked=false;
            document.Amputation.lower_5thtoe_right.checked=false;
        }
    }else {
        document.Amputation.lower_hind_right.disabled=false;
        document.Amputation.lower_hip_right.disabled=false;
        document.Amputation.lower_AKupper_right.disabled=false;
        document.Amputation.lower_AKlower_right.disabled=false;
        document.Amputation.lower_truknee_right.disabled=false;
        document.Amputation.lower_bk8cm_right.disabled=false;
        document.Amputation.lower_bklower_right.disabled=false;
        document.Amputation.lower_truankle_right.disabled=false;
        document.Amputation.lower_symes_right.disabled=false;
        document.Amputation.lower_uptomid_right.disabled=false;
        document.Amputation.lower_uptofore_right.disabled=false;
        document.Amputation.lower_alltoe_right.disabled=false;
        document.Amputation.lower_1sttoe_right.disabled=false;
        document.Amputation.lower_2ndtoe_right.disabled=false;
        document.Amputation.lower_3rdtoe_right.disabled=false;
        document.Amputation.lower_4thtoe_right.disabled=false;
        document.Amputation.lower_5thtoe_right.disabled=false;
    }
}

function validatetoeright(){
    if(document.Amputation.lower_alltoe_right.checked||document.Amputation.lower_1sttoe_right.checked||document.Amputation.lower_2ndtoe_right.checked||document.Amputation.lower_3rdtoe_right.checked||document.Amputation.lower_4thtoe_right.checked||document.Amputation.lower_5thtoe_right.checked){
        if(document.Amputation.lower_alltoe_right.checked){
            document.Amputation.lower_1sttoe_right.disabled=true;
            document.Amputation.lower_2ndtoe_right.disabled=true;
            document.Amputation.lower_3rdtoe_right.disabled=true;
            document.Amputation.lower_4thtoe_right.disabled=true;
            document.Amputation.lower_5thtoe_right.disabled=true;

            document.Amputation.lower_1sttoe_right.checked=false;
            document.Amputation.lower_2ndtoe_right.checked=false;
            document.Amputation.lower_3rdtoe_right.checked=false;
            document.Amputation.lower_4thtoe_right.checked=false;
            document.Amputation.lower_5thtoe_right.checked=false;
        }
        if(document.Amputation.lower_1sttoe_right.checked&&document.Amputation.lower_2ndtoe_right.checked&&document.Amputation.lower_3rdtoe_right.checked&&document.Amputation.lower_4thtoe_right.checked&&document.Amputation.lower_5thtoe_right.checked){
            document.Amputation.lower_1sttoe_right.disabled=true;
            document.Amputation.lower_2ndtoe_right.disabled=true;
            document.Amputation.lower_3rdtoe_right.disabled=true;
            document.Amputation.lower_4thtoe_right.disabled=true;
            document.Amputation.lower_5thtoe_right.disabled=true;
            
            document.Amputation.lower_1sttoe_right.checked=false;
            document.Amputation.lower_2ndtoe_right.checked=false;
            document.Amputation.lower_3rdtoe_right.checked=false;
            document.Amputation.lower_4thtoe_right.checked=false;
            document.Amputation.lower_5thtoe_right.checked=false;
            document.Amputation.lower_alltoe_right.checked=false;
            
            document.Amputation.lower_alltoe_right.checked=true;
        }
        
    }else{
        document.Amputation.lower_alltoe_right.disabled=false;
        document.Amputation.lower_1sttoe_right.disabled=false;
        document.Amputation.lower_2ndtoe_right.disabled=false;
        document.Amputation.lower_3rdtoe_right.disabled=false;
        document.Amputation.lower_4thtoe_right.disabled=false;
        document.Amputation.lower_5thtoe_right.disabled=false;
        
    }
}



















function validatecheckboxupperleftCMMCPIP(){
   
    
    
    if(document.Amputation.upper_thumbCM_left.checked||document.Amputation.upper_thumbMCP_left.checked||document.Amputation.upper_thumbIP_left.checked){
        
        
        document.Amputation.upper_handcarpel_left.disabled=true;
        document.Amputation.upper_fore_left.disabled=true;
        document.Amputation.upper_shoulder_left.disabled=true;
        document.Amputation.upper_aboveelbowupper_left.disabled=true;
        document.Amputation.upper_elbowlower_left.disabled=true;
        document.Amputation.upper_elbowdis_left.disabled=true;
        document.Amputation.upper_belowelbowupper_left.disabled=true;
        document.Amputation.upper_belowelbowlower_left.disabled=true;
        document.Amputation.upper_waistdis_left.disabled=true;
        
        if(document.Amputation.upper_thumbCM_left.checked){
            document.Amputation.upper_thumbMCP_left.disabled=true;
            document.Amputation.upper_thumbIP_left.disabled=true;
        }
        if(document.Amputation.upper_thumbMCP_left.checked){
            document.Amputation.upper_thumbCM_left.disabled=true;
            document.Amputation.upper_thumbIP_left.disabled=true;
        }
        if(document.Amputation.upper_thumbIP_left.checked){
            document.Amputation.upper_thumbCM_left.disabled=true;
            document.Amputation.upper_thumbMCP_left.disabled=true;
        }
    }else{
        document.Amputation.upper_handcarpel_left.disabled=false;
        document.Amputation.upper_fore_left.disabled=false;
        document.Amputation.upper_shoulder_left.disabled=false;
        document.Amputation.upper_aboveelbowupper_left.disabled=false;
        document.Amputation.upper_elbowlower_left.disabled=false;
        document.Amputation.upper_elbowdis_left.disabled=false;
        document.Amputation.upper_belowelbowupper_left.disabled=false;
        document.Amputation.upper_belowelbowlower_left.disabled=false;
        document.Amputation.upper_waistdis_left.disabled=false;
        document.Amputation.upper_thumbCM_left.disabled=false;
        document.Amputation.upper_thumbMCP_left.disabled=false;
        document.Amputation.upper_thumbIP_left.disabled=false;
        
        
        
    }
}

function validatecheckboxupperleftfingers(){
    
    
    
    if(document.Amputation.upper_MPIndex_left.checked||document.Amputation.upper_MPMiddle_left.checked||document.Amputation.upper_MPRing_left.checked||document.Amputation.upper_MPLittle_left.checked||document.Amputation.upper_DIPIndex_left.checked||document.Amputation.upper_DIPMiddle_left.checked||document.Amputation.upper_DIPRing_left.checked||document.Amputation.upper_DIPLittle_left.checked||document.Amputation.upper_PIPIndex_left.checked||document.Amputation.upper_PIPMiddle_left.checked||document.Amputation.upper_PIPRing_left.checked||document.Amputation.upper_PIPLittle_left.checked){
        
        
        
        
        
        if(document.Amputation.upper_MPIndex_left.checked){
            document.Amputation.upper_DIPIndex_left.disabled=true;
            document.Amputation.upper_PIPIndex_left.disabled=true;
        }
        if(document.Amputation.upper_DIPIndex_left.checked){
            document.Amputation.upper_MPIndex_left.disabled=true;
            document.Amputation.upper_PIPIndex_left.disabled=true;
        }
        if(document.Amputation.upper_PIPIndex_left.checked){
            document.Amputation.upper_MPIndex_left.disabled=true;
            document.Amputation.upper_DIPIndex_left.disabled=true;
        }
        
        if(document.Amputation.upper_MPMiddle_left.checked){
            document.Amputation.upper_DIPMiddle_left.disabled=true;
            document.Amputation.upper_PIPMiddle_left.disabled=true;
        }
        if(document.Amputation.upper_DIPMiddle_left.checked){
            document.Amputation.upper_MPMiddle_left.disabled=true;
            document.Amputation.upper_PIPMiddle_left.disabled=true;
        }
        if(document.Amputation.upper_PIPMiddle_left.checked){
            document.Amputation.upper_MPMiddle_left.disabled=true;
            document.Amputation.upper_DIPMiddle_left.disabled=true;
        }
        
        if(document.Amputation.upper_MPRing_left.checked){
            document.Amputation.upper_DIPRing_left.disabled=true;
            document.Amputation.upper_PIPRing_left.disabled=true;
        }
        if(document.Amputation.upper_DIPRing_left.checked){
            document.Amputation.upper_MPRing_left.disabled=true;
            document.Amputation.upper_PIPRing_left.disabled=true;
        }
        if(document.Amputation.upper_PIPRing_left.checked){
            document.Amputation.upper_MPRing_left.disabled=true;
            document.Amputation.upper_DIPRing_left.disabled=true;
        }
        
        if(document.Amputation.upper_MPLittle_left.checked){
            document.Amputation.upper_DIPLittle_left.disabled=true;
            document.Amputation.upper_PIPLittle_left.disabled=true;
        }
        if(document.Amputation.upper_DIPLittle_left.checked){
            document.Amputation.upper_MPLittle_left.disabled=true;
            document.Amputation.upper_PIPLittle_left.disabled=true;
        }
        if(document.Amputation.upper_PIPLittle_left.checked){
            document.Amputation.upper_MPLittle_left.disabled=true;
            document.Amputation.upper_DIPLittle_left.disabled=true;
        }
        
    }else{
        document.Amputation.upper_MPIndex_left.disabled=false;
        document.Amputation.upper_MPMiddle_left.disabled=false;
        document.Amputation.upper_MPRing_left.disabled=false;
        document.Amputation.upper_MPLittle_left.disabled=false;
        document.Amputation.upper_PIPIndex_left.disabled=false;
        document.Amputation.upper_PIPMiddle_left.disabled=false;
        document.Amputation.upper_PIPRing_left.disabled=false;
        document.Amputation.upper_PIPLittle_left.disabled=false;
        document.Amputation.upper_DIPIndex_left.disabled=false;
        document.Amputation.upper_DIPMiddle_left.disabled=false;
        document.Amputation.upper_DIPRing_left.disabled=false;
        document.Amputation.upper_DIPLittle_left.disabled=false;
        
        
        
        
    }
}



function validatecheckboxlowerleft(){
    if(document.Amputation.lower_hind_left.checked||document.Amputation.lower_hip_left.checked||document.Amputation.lower_AKupper_left.checked||document.Amputation.lower_AKlower_left.checked||document.Amputation.lower_truknee_left.checked||document.Amputation.lower_bk8cm_left.checked||document.Amputation.lower_bklower_left.checked||document.Amputation.lower_truankle_left.checked||document.Amputation.lower_symes_left.checked||document.Amputation.lower_uptomid_left.checked||document.Amputation.lower_uptofore_left.checked){
        
        {
            if(document.Amputation.lower_hip_left.checked)
                document.Amputation.lower_hind_left.disabled=true;
            else
                document.Amputation.lower_hip_left.disabled=true;
            
            if(document.Amputation.lower_AKupper_left.checked){
                document.Amputation.lower_hind_left.disabled=true;
                document.Amputation.lower_hip_left.disabled=true;
            }else{
                document.Amputation.lower_AKupper_left.disabled=true;
            }
            
            if(document.Amputation.lower_AKlower_left.checked){
                document.Amputation.lower_hind_left.disabled=true;
                document.Amputation.lower_hip_left.disabled=true;
                document.Amputation.lower_AKupper_left.disabled=true;
            }else{
                document.Amputation.lower_AKlower_left.disabled=true;
            }
            
            if(document.Amputation.lower_truknee_left.checked){
                document.Amputation.lower_hind_left.disabled=true;
                document.Amputation.lower_hip_left.disabled=true;
                document.Amputation.lower_AKupper_left.disabled=true;
                document.Amputation.lower_AKlower_left.disabled=true;
            }else{
                document.Amputation.lower_truknee_left.disabled=true;
            }
            
            if(document.Amputation.lower_bk8cm_left.checked){
                document.Amputation.lower_hind_left.disabled=true;
                document.Amputation.lower_hip_left.disabled=true;
                document.Amputation.lower_AKupper_left.disabled=true;
                document.Amputation.lower_AKlower_left.disabled=true;
                document.Amputation.lower_truknee_left.disabled=true;
            }else{
                document.Amputation.lower_bk8cm_left.disabled=true;
            }
            
            if(document.Amputation.lower_bklower_left.checked){
                document.Amputation.lower_hind_left.disabled=true;
                document.Amputation.lower_hip_left.disabled=true;
                document.Amputation.lower_AKupper_left.disabled=true;
                document.Amputation.lower_AKlower_left.disabled=true;
                document.Amputation.lower_truknee_left.disabled=true;
                document.Amputation.lower_bk8cm_left.disabled=true;
            }else{
                document.Amputation.lower_bklower_left.disabled=true;
            }
            
            if(document.Amputation.lower_truankle_left.checked){
                document.Amputation.lower_hind_left.disabled=true;
                document.Amputation.lower_hip_left.disabled=true;
                document.Amputation.lower_AKupper_left.disabled=true;
                document.Amputation.lower_AKlower_left.disabled=true;
                document.Amputation.lower_truknee_left.disabled=true;
                document.Amputation.lower_bk8cm_left.disabled=true;
                document.Amputation.lower_bklower_left.disabled=true;
            }else{
                document.Amputation.lower_truankle_left.disabled=true;
            }
            
            if(document.Amputation.lower_symes_left.checked){
                document.Amputation.lower_hind_left.disabled=true;
                document.Amputation.lower_hip_left.disabled=true;
                document.Amputation.lower_AKupper_left.disabled=true;
                document.Amputation.lower_AKlower_left.disabled=true;
                document.Amputation.lower_truknee_left.disabled=true;
                document.Amputation.lower_bk8cm_left.disabled=true;
                document.Amputation.lower_bklower_left.disabled=true;
                document.Amputation.lower_truankle_left.disabled=true;
            }else{
                document.Amputation.lower_symes_left.disabled=true;
            }
            
            if(document.Amputation.lower_uptomid_left.checked){
                document.Amputation.lower_hind_left.disabled=true;
                document.Amputation.lower_hip_left.disabled=true;
                document.Amputation.lower_AKupper_left.disabled=true;
                document.Amputation.lower_AKlower_left.disabled=true;
                document.Amputation.lower_truknee_left.disabled=true;
                document.Amputation.lower_bk8cm_left.disabled=true;
                document.Amputation.lower_bklower_left.disabled=true;
                document.Amputation.lower_truankle_left.disabled=true;
                document.Amputation.lower_symes_left.disabled=true;
            }else{
                document.Amputation.lower_uptomid_left.disabled=true;
            }
            
            if(document.Amputation.lower_uptofore_left.checked){
                document.Amputation.lower_hind_left.disabled=true;
                document.Amputation.lower_hip_left.disabled=true;
                document.Amputation.lower_AKupper_left.disabled=true;
                document.Amputation.lower_AKlower_left.disabled=true;
                document.Amputation.lower_truknee_left.disabled=true;
                document.Amputation.lower_bk8cm_left.disabled=true;
                document.Amputation.lower_bklower_left.disabled=true;
                document.Amputation.lower_truankle_left.disabled=true;
                document.Amputation.lower_symes_left.disabled=true;
                document.Amputation.lower_uptomid_left.disabled=true;
            }else{
                document.Amputation.lower_uptofore_left.disabled=true;
            }
            
            document.Amputation.lower_alltoe_left.disabled=true;
            document.Amputation.lower_1sttoe_left.disabled=true;
            document.Amputation.lower_2ndtoe_left.disabled=true;
            document.Amputation.lower_3rdtoe_left.disabled=true;
            document.Amputation.lower_4thtoe_left.disabled=true;
            document.Amputation.lower_5thtoe_left.disabled=true;
            
            document.Amputation.lower_alltoe_left.checked=false;
            document.Amputation.lower_1sttoe_left.checked=false;
            document.Amputation.lower_2ndtoe_left.checked=false;
            document.Amputation.lower_3rdtoe_left.checked=false;
            document.Amputation.lower_4thtoe_left.checked=false;
            document.Amputation.lower_5thtoe_left.checked=false;
        }
    }else {
        document.Amputation.lower_hind_left.disabled=false;
        document.Amputation.lower_hip_left.disabled=false;
        document.Amputation.lower_AKupper_left.disabled=false;
        document.Amputation.lower_AKlower_left.disabled=false;
        document.Amputation.lower_truknee_left.disabled=false;
        document.Amputation.lower_bk8cm_left.disabled=false;
        document.Amputation.lower_bklower_left.disabled=false;
        document.Amputation.lower_truankle_left.disabled=false;
        document.Amputation.lower_symes_left.disabled=false;
        document.Amputation.lower_uptomid_left.disabled=false;
        document.Amputation.lower_uptofore_left.disabled=false;
        document.Amputation.lower_alltoe_left.disabled=false;
        document.Amputation.lower_1sttoe_left.disabled=false;
        document.Amputation.lower_2ndtoe_left.disabled=false;
        document.Amputation.lower_3rdtoe_left.disabled=false;
        document.Amputation.lower_4thtoe_left.disabled=false;
        document.Amputation.lower_5thtoe_left.disabled=false;
    }
}

function validatetoeleft(){
    if(document.Amputation.lower_alltoe_left.checked||document.Amputation.lower_1sttoe_left.checked||document.Amputation.lower_2ndtoe_left.checked||document.Amputation.lower_3rdtoe_left.checked||document.Amputation.lower_4thtoe_left.checked||document.Amputation.lower_5thtoe_left.checked){
        if(document.Amputation.lower_alltoe_left.checked){
            document.Amputation.lower_1sttoe_left.disabled=true;
            document.Amputation.lower_2ndtoe_left.disabled=true;
            document.Amputation.lower_3rdtoe_left.disabled=true;
            document.Amputation.lower_4thtoe_left.disabled=true;
            document.Amputation.lower_5thtoe_left.disabled=true;

            document.Amputation.lower_1sttoe_left.checked=false;
            document.Amputation.lower_2ndtoe_left.checked=false;
            document.Amputation.lower_3rdtoe_left.checked=false;
            document.Amputation.lower_4thtoe_left.checked=false;
            document.Amputation.lower_5thtoe_left.checked=false;
        }
        if(document.Amputation.lower_1sttoe_left.checked&&document.Amputation.lower_2ndtoe_left.checked&&document.Amputation.lower_3rdtoe_left.checked&&document.Amputation.lower_4thtoe_left.checked&&document.Amputation.lower_5thtoe_left.checked){
            document.Amputation.lower_1sttoe_left.disabled=true;
            document.Amputation.lower_2ndtoe_left.disabled=true;
            document.Amputation.lower_3rdtoe_left.disabled=true;
            document.Amputation.lower_4thtoe_left.disabled=true;
            document.Amputation.lower_5thtoe_left.disabled=true;
            
            document.Amputation.lower_1sttoe_left.checked=false;
            document.Amputation.lower_2ndtoe_left.checked=false;
            document.Amputation.lower_3rdtoe_left.checked=false;
            document.Amputation.lower_4thtoe_left.checked=false;
            document.Amputation.lower_5thtoe_left.checked=false;
            document.Amputation.lower_alltoe_left.checked=false;
            
            document.Amputation.lower_alltoe_left.checked=true;
        }
        
    }else{
        document.Amputation.lower_alltoe_left.disabled=false;
        document.Amputation.lower_1sttoe_left.disabled=false;
        document.Amputation.lower_2ndtoe_left.disabled=false;
        document.Amputation.lower_3rdtoe_left.disabled=false;
        document.Amputation.lower_4thtoe_left.disabled=false;
        document.Amputation.lower_5thtoe_left.disabled=false;
        
    }
}


function devi(){
    validatecheckboxupperright();
    validatecheckboxupperleft();
    validatecheckboxlowerright();
    validatecheckboxlowerleft();
if(document.Amputation.upper_thumbCM_right.checked||document.Amputation.upper_thumbMCP_right.checked||document.Amputation.upper_thumbIP_right.checked){
validatecheckboxupperrightCMMCPIP();
}
if(document.Amputation.upper_MPIndex_right.checked||document.Amputation.upper_MPMiddle_right.checked||document.Amputation.upper_MPRing_right.checked||document.Amputation.upper_MPLittle_right.checked||document.Amputation.upper_DIPIndex_right.checked||document.Amputation.upper_DIPMiddle_right.checked||document.Amputation.upper_DIPRing_right.checked||document.Amputation.upper_DIPLittle_right.checked||document.Amputation.upper_PIPIndex_right.checked||document.Amputation.upper_PIPMiddle_right.checked||document.Amputation.upper_PIPRing_right.checked||document.Amputation.upper_PIPLittle_right.checked){
validatecheckboxupperrightfingers();
}   
if(document.Amputation.lower_alltoe_right.checked||document.Amputation.lower_1sttoe_right.checked||document.Amputation.lower_2ndtoe_right.checked||document.Amputation.lower_3rdtoe_right.checked||document.Amputation.lower_4thtoe_right.checked||document.Amputation.lower_5thtoe_right.checked){
validatetoeright();
}   

if(document.Amputation.upper_thumbCM_left.checked||document.Amputation.upper_thumbMCP_left.checked||document.Amputation.upper_thumbIP_left.checked){
validatecheckboxupperleftCMMCPIP();
}
if(document.Amputation.upper_MPIndex_left.checked||document.Amputation.upper_MPMiddle_left.checked||document.Amputation.upper_MPRing_left.checked||document.Amputation.upper_MPLittle_left.checked||document.Amputation.upper_DIPIndex_left.checked||document.Amputation.upper_DIPMiddle_left.checked||document.Amputation.upper_DIPRing_left.checked||document.Amputation.upper_DIPLittle_left.checked||document.Amputation.upper_PIPIndex_left.checked||document.Amputation.upper_PIPMiddle_left.checked||document.Amputation.upper_PIPRing_left.checked||document.Amputation.upper_PIPLittle_left.checked){
validatecheckboxupperleftfingers();
}   
if(document.Amputation.lower_alltoe_left.checked||document.Amputation.lower_1sttoe_left.checked||document.Amputation.lower_2ndtoe_left.checked||document.Amputation.lower_3rdtoe_left.checked||document.Amputation.lower_4thtoe_left.checked||document.Amputation.lower_5thtoe_left.checked){
validatetoeleft();
}       
}



function resetbutton(){
document.Amputation.upper_fore_right.checked=false;
document.Amputation.upper_shoulder_right.checked=false;
document.Amputation.upper_aboveelbowupper_right.checked=false;
document.Amputation.upper_elbowlower_right.checked=false;
document.Amputation.upper_elbowdis_right.checked=false;
document.Amputation.upper_belowelbowupper_right.checked=false;
document.Amputation.upper_belowelbowlower_right.checked=false;
document.Amputation.upper_waistdis_right.checked=false;
document.Amputation.upper_handcarpel_right.checked=false;
document.Amputation.upper_thumbCM_right.checked=false;
document.Amputation.upper_thumbMCP_right.checked=false;
document.Amputation.upper_thumbIP_right.checked=false;
document.Amputation.upper_fore_left.checked=false;
document.Amputation.upper_shoulder_left.checked=false;
document.Amputation.upper_aboveelbowupper_left.checked=false;
document.Amputation.upper_elbowlower_left.checked=false;
document.Amputation.upper_elbowdis_left.checked=false;
document.Amputation.upper_belowelbowupper_left.checked=false;
document.Amputation.upper_belowelbowlower_left.checked=false;
document.Amputation.upper_waistdis_left.checked=false;
document.Amputation.upper_handcarpel_left.checked=false;
document.Amputation.upper_thumbCM_left.checked=false;
document.Amputation.upper_thumbMCP_left.checked=false;
document.Amputation.upper_thumbIP_left.checked=false;
document.Amputation.upper_MPIndex_right.checked=false;
document.Amputation.upper_MPMiddle_right.checked=false;
document.Amputation.upper_MPRing_right.checked=false;
document.Amputation.upper_MPLittle_right.checked=false;
document.Amputation.upper_MPIndex_left.checked=false;
document.Amputation.upper_MPMiddle_left.checked=false;
document.Amputation.upper_MPRing_left.checked=false;
document.Amputation.upper_MPLittle_left.checked=false;
document.Amputation.upper_DIPIndex_right.checked=false;
document.Amputation.upper_DIPMiddle_right.checked=false;
document.Amputation.upper_DIPRing_right.checked=false;
document.Amputation.upper_DIPLittle_right.checked=false;
document.Amputation.upper_DIPIndex_left.checked=false;
document.Amputation.upper_DIPMiddle_left.checked=false;
document.Amputation.upper_DIPRing_left.checked=false;
document.Amputation.upper_DIPLittle_left.checked=false;
document.Amputation.upper_PIPIndex_right.checked=false;
document.Amputation.upper_PIPMiddle_right.checked=false;
document.Amputation.upper_PIPRing_right.checked=false;
document.Amputation.upper_PIPLittle_right.checked=false;
document.Amputation.upper_PIPIndex_left.checked=false;
document.Amputation.upper_PIPMiddle_left.checked=false;
document.Amputation.upper_PIPRing_left.checked=false;
document.Amputation.upper_PIPLittle_left.checked=false;
document.Amputation.lower_hind_right.checked=false;
document.Amputation.lower_hip_right.checked=false;
document.Amputation.lower_AKupper_right.checked=false;
document.Amputation.lower_AKlower_right.checked=false;
document.Amputation.lower_truknee_right.checked=false;
document.Amputation.lower_bk8cm_right.checked=false;
document.Amputation.lower_bklower_right.checked=false;
document.Amputation.lower_truankle_right.checked=false;
document.Amputation.lower_symes_right.checked=false;
document.Amputation.lower_uptomid_right.checked=false;
document.Amputation.lower_uptofore_right.checked=false;
document.Amputation.lower_alltoe_right.checked=false;
document.Amputation.lower_1sttoe_right.checked=false;
document.Amputation.lower_2ndtoe_right.checked=false;
document.Amputation.lower_3rdtoe_right.checked=false;
document.Amputation.lower_4thtoe_right.checked=false;
document.Amputation.lower_5thtoe_right.checked=false;
document.Amputation.lower_hind_left.checked=false;
document.Amputation.lower_hip_left.checked=false;
document.Amputation.lower_AKupper_left.checked=false;
document.Amputation.lower_AKlower_left.checked=false;
document.Amputation.lower_truknee_left.checked=false;
document.Amputation.lower_bk8cm_left.checked=false;
document.Amputation.lower_bklower_left.checked=false;
document.Amputation.lower_truankle_left.checked=false;
document.Amputation.lower_symes_left.checked=false;
document.Amputation.lower_uptomid_left.checked=false;
document.Amputation.lower_uptofore_left.checked=false;
document.Amputation.lower_alltoe_left.checked=false;
document.Amputation.lower_1sttoe_left.checked=false;
document.Amputation.lower_2ndtoe_left.checked=false;
document.Amputation.lower_3rdtoe_left.checked=false;
document.Amputation.lower_4thtoe_left.checked=false;
document.Amputation.lower_5thtoe_left.checked=false;
document.Amputation.fitting_of_prosthesis.checked=false;

document.Amputation.upper_fore_right.disabled=false;
document.Amputation.upper_shoulder_right.disabled=false;
document.Amputation.upper_aboveelbowupper_right.disabled=false;
document.Amputation.upper_elbowlower_right.disabled=false;
document.Amputation.upper_elbowdis_right.disabled=false;
document.Amputation.upper_belowelbowupper_right.disabled=false;
document.Amputation.upper_belowelbowlower_right.disabled=false;
document.Amputation.upper_waistdis_right.disabled=false;
document.Amputation.upper_handcarpel_right.disabled=false;
document.Amputation.upper_thumbCM_right.disabled=false;
document.Amputation.upper_thumbMCP_right.disabled=false;
document.Amputation.upper_thumbIP_right.disabled=false;
document.Amputation.upper_fore_left.disabled=false;
document.Amputation.upper_shoulder_left.disabled=false;
document.Amputation.upper_aboveelbowupper_left.disabled=false;
document.Amputation.upper_elbowlower_left.disabled=false;
document.Amputation.upper_elbowdis_left.disabled=false;
document.Amputation.upper_belowelbowupper_left.disabled=false;
document.Amputation.upper_belowelbowlower_left.disabled=false;
document.Amputation.upper_waistdis_left.disabled=false;
document.Amputation.upper_handcarpel_left.disabled=false;
document.Amputation.upper_thumbCM_left.disabled=false;
document.Amputation.upper_thumbMCP_left.disabled=false;
document.Amputation.upper_thumbIP_left.disabled=false;
document.Amputation.upper_MPIndex_right.disabled=false;
document.Amputation.upper_MPMiddle_right.disabled=false;
document.Amputation.upper_MPRing_right.disabled=false;
document.Amputation.upper_MPLittle_right.disabled=false;
document.Amputation.upper_MPIndex_left.disabled=false;
document.Amputation.upper_MPMiddle_left.disabled=false;
document.Amputation.upper_MPRing_left.disabled=false;
document.Amputation.upper_MPLittle_left.disabled=false;
document.Amputation.upper_DIPIndex_right.disabled=false;
document.Amputation.upper_DIPMiddle_right.disabled=false;
document.Amputation.upper_DIPRing_right.disabled=false;
document.Amputation.upper_DIPLittle_right.disabled=false;
document.Amputation.upper_DIPIndex_left.disabled=false;
document.Amputation.upper_DIPMiddle_left.disabled=false;
document.Amputation.upper_DIPRing_left.disabled=false;
document.Amputation.upper_DIPLittle_left.disabled=false;
document.Amputation.upper_PIPIndex_right.disabled=false;
document.Amputation.upper_PIPMiddle_right.disabled=false;
document.Amputation.upper_PIPRing_right.disabled=false;
document.Amputation.upper_PIPLittle_right.disabled=false;
document.Amputation.upper_PIPIndex_left.disabled=false;
document.Amputation.upper_PIPMiddle_left.disabled=false;
document.Amputation.upper_PIPRing_left.disabled=false;
document.Amputation.upper_PIPLittle_left.disabled=false;
document.Amputation.lower_hind_right.disabled=false;
document.Amputation.lower_hip_right.disabled=false;
document.Amputation.lower_AKupper_right.disabled=false;
document.Amputation.lower_AKlower_right.disabled=false;
document.Amputation.lower_truknee_right.disabled=false;
document.Amputation.lower_bk8cm_right.disabled=false;
document.Amputation.lower_bklower_right.disabled=false;
document.Amputation.lower_truankle_right.disabled=false;
document.Amputation.lower_symes_right.disabled=false;
document.Amputation.lower_uptomid_right.disabled=false;
document.Amputation.lower_uptofore_right.disabled=false;
document.Amputation.lower_alltoe_right.disabled=false;
document.Amputation.lower_1sttoe_right.disabled=false;
document.Amputation.lower_2ndtoe_right.disabled=false;
document.Amputation.lower_3rdtoe_right.disabled=false;
document.Amputation.lower_4thtoe_right.disabled=false;
document.Amputation.lower_5thtoe_right.disabled=false;
document.Amputation.lower_hind_left.disabled=false;
document.Amputation.lower_hip_left.disabled=false;
document.Amputation.lower_AKupper_left.disabled=false;
document.Amputation.lower_AKlower_left.disabled=false;
document.Amputation.lower_truknee_left.disabled=false;
document.Amputation.lower_bk8cm_left.disabled=false;
document.Amputation.lower_bklower_left.disabled=false;
document.Amputation.lower_truankle_left.disabled=false;
document.Amputation.lower_symes_left.disabled=false;
document.Amputation.lower_uptomid_left.disabled=false;
document.Amputation.lower_uptofore_left.disabled=false;
document.Amputation.lower_alltoe_left.disabled=false;
document.Amputation.lower_1sttoe_left.disabled=false;
document.Amputation.lower_2ndtoe_left.disabled=false;
document.Amputation.lower_3rdtoe_left.disabled=false;
document.Amputation.lower_4thtoe_left.disabled=false;
document.Amputation.lower_5thtoe_left.disabled=false;
document.Amputation.fitting_of_prosthesis.disabled=false;
document.Amputation.proximal_joint.disabled=false;
document.Amputation.neuroma.disabled=false;
document.Amputation.infection.disabled=false;

document.getElementById("prox").selected=true;
document.getElementById("neur").selected=true;
document.getElementById("inf").selected=true;
document.Amputation.dominant.checked=false;


}




