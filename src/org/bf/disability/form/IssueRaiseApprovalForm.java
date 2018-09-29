/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.form;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

/**
 *
 * @author 693461
 */
public class IssueRaiseApprovalForm extends org.apache.struts.action.ActionForm {

    private String typeofdisabilityId;

    public String getTypeofdisabilityId() {
        return typeofdisabilityId;
    }

    public void setTypeofdisabilityId(String typeofdisabilityId) {
        this.typeofdisabilityId = typeofdisabilityId;
    }
    private String districtId;
    private String medicalBoardId;
    private String medicalBoardName;
    private ArrayList medicalBoardList = new ArrayList();
    private String sadaremId;
    private String pensionId;
    private String otherCategoryId;
    private String[] rationCardNo;
    private String decription;
    private FormFile fileUpload;
    private String campId;
    private String campName;
    private ArrayList campList = new ArrayList();
    private String mode;
    private String categoryFormId;
    private String categoryname;
    private String resolvedCnt;
    private String id;
    private String time;
    private String district_id;
    private ArrayList districtlist = new ArrayList();
    private String district_name;
    private String newSurName;
    private String radionameChangeId;
    private String newName;
    private String newRelationName;
    private String populateRelationName;
    //New declared the variables
    private String radioPensionId;
    private String changePension;
    private String rationCardNotOpen;
    private String rationCardType;
    private String rationCardSlnoChange;
    private String newSadaremId;
    private String forgetUserName;
    private String memberName;
    private String rationCardSlno;
    private String rationCardNumber;
    private ArrayList userNameList = new ArrayList();
    private String populateSurName;
    private String populateName;
    private String resolveCampName;
    private String resolvecategoryName;
    private String resolvesadaremId;
    private String resolvepensionId;
    private String resolverationCard;
    private String resolveDescription;
    private String requestModeRaiseId;
    private ArrayList categoryList = new ArrayList();
    private ArrayList districtList = new ArrayList();
    private String comments;
    private FormFile resolveUpload;
    private String mobile;
    private String issueId;
    private String status;
    private String[] checkEmaild;
    private String[] checkEmailCC;
    private ArrayList sadaremIdList = new ArrayList();
    private ArrayList campAddressList = new ArrayList();
    private String campAddress;
    private String rationCardChangeNo;
    private String sadaremIdRationCardSlno;
    private String populateSerialNo;
    private String newSerialNo;
    private String hiddentoSetRationCardNo;
    private String statusCategoryId;
    private String rejectComment;
    private String resolveUserName;
    private String resolverelationName;
    private String resolveName;
    private String resolvesurName;
    private String resolveMedicalAddress;
    private String resolveRationCardserialNo;
    private String resolveNewSadadremId;
    private String resolveRejectComments;
    private String resloveCampId;
    private String resolveMemberName;
    private String resloveLoginId;
    private ArrayList medicalUserNameList = new ArrayList();
    private String medicalUserName;
    private String statusRequest;
    private String newPensionId;
    private String raiseName;
    private String raiseRelationName;
    private String resolveNewPensionId;
    private String vicversaFlag;
    private String isseRaisefilePath;
    private String populateviceversaSadaremId;
    private  String populateviceversaPensionId;
    private String reslovePensionPhase;
    private String resloveDisName;
    private String resloveDisRelationName;
    private String resloveDisSurName;
    private String dateOfIssue;
    private String createdDateIssue;
    private String resolveDateOfIssue;
    private  String hiddenFlags;
    private String date;
    private String loginId;
    private String rationCardHidedndistrictId;
    private String resloveNewDistrict_id;
    private String fromDate;
    private String toDate;


    //balu start

    private String mandal_id;
    private ArrayList mandallist = new ArrayList();
    private String village_id;
    private ArrayList villagelist = new ArrayList();
    private String habitation_id;
    private ArrayList habitationlist = new ArrayList();
    private ArrayList panchayatlist;
    private String panchayat_id;
    private String addresschangeSADAREMID = null;
    
    private String houseNo = null;
    private String pin = null;
    
    private String newhouseno = null;
    private String newpincode = null;
    public String getNewhouseno() {
		return newhouseno;
	}

	public void setNewhouseno(String newhouseno) {
		this.newhouseno = newhouseno;
	}

	public String getNewpincode() {
		return newpincode;
	}

	public void setNewpincode(String newpincode) {
		this.newpincode = newpincode;
	}
	
    
    
    public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}
	
	
	
    //balu end

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
    

    public String getResloveNewDistrict_id() {
        return resloveNewDistrict_id;
    }

    public void setResloveNewDistrict_id(String resloveNewDistrict_id) {
        this.resloveNewDistrict_id = resloveNewDistrict_id;
    }
    

    public String getRationCardHidedndistrictId() {
        return rationCardHidedndistrictId;
    }

    public void setRationCardHidedndistrictId(String rationCardHidedndistrictId) {
        this.rationCardHidedndistrictId = rationCardHidedndistrictId;
    }
    


    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    private String relationType;
    private String populaterelationType;
    private String disabilityType;
    private String subdisabilityId[];
    private String diagnoDisablity;
    private String subsubdisabilityId[];
    private String subdisabilityFlag;
    private String diagnosisDisability;

    public String getSubdisabilityFlag() {
        return subdisabilityFlag;
    }

    public String getDiagnosisDisability() {
        return diagnosisDisability;
    }

    public void setDiagnosisDisability(String diagnosisDisability) {
        this.diagnosisDisability = diagnosisDisability;
    }

    public void setSubdisabilityFlag(String subdisabilityFlag) {
        this.subdisabilityFlag = subdisabilityFlag;
    }

    public String getDiagnoDisablity() {
        return diagnoDisablity;
    }

    public void setDiagnoDisablity(String diagnoDisablity) {
        this.diagnoDisablity = diagnoDisablity;
    }

    public ArrayList getSubsubdisabilityList() {
        return subsubdisabilityList;
    }

    public void setSubsubdisabilityList(ArrayList subsubdisabilityList) {
        this.subsubdisabilityList = subsubdisabilityList;
    }
    ArrayList subsubdisabilityList = new ArrayList();
    ArrayList subdisabilityList = new ArrayList();

    public String[] getSubdisabilityId() {
        return subdisabilityId;
    }

    public void setSubdisabilityId(String[] subdisabilityId) {
        this.subdisabilityId = subdisabilityId;
    }

    public String[] getSubsubdisabilityId() {
        return subsubdisabilityId;
    }

    public void setSubsubdisabilityId(String[] subsubdisabilityId) {
        this.subsubdisabilityId = subsubdisabilityId;
    }

    public String getDisabilityType() {
        return disabilityType;
    }

    public void setDisabilityType(String disabilityType) {
        this.disabilityType = disabilityType;
    }

    public ArrayList getSubdisabilityList() {
        return subdisabilityList;
    }

    public void setSubdisabilityList(ArrayList subdisabilityList) {
        this.subdisabilityList = subdisabilityList;
    }

    public String getPopulaterelationType() {
        return populaterelationType;
    }

    public void setPopulaterelationType(String populaterelationType) {
        this.populaterelationType = populaterelationType;
    }

    public String getRelationType() {
        return relationType;
    }

    public void setRelationType(String relationType) {
        this.relationType = relationType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    

    public String getHiddenFlags() {
        return hiddenFlags;
    }

    public void setHiddenFlags(String hiddenFlags) {
        this.hiddenFlags = hiddenFlags;
    }


    public String getResolveDateOfIssue() {
        return resolveDateOfIssue;
    }

    public void setResolveDateOfIssue(String resolveDateOfIssue) {
        this.resolveDateOfIssue = resolveDateOfIssue;
    }


    public String getCreatedDateIssue() {
        return createdDateIssue;
    }

    public void setCreatedDateIssue(String createdDateIssue) {
        this.createdDateIssue = createdDateIssue;
    }


    public String getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(String dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }


    public String getResloveDisName() {
        return resloveDisName;
    }

    public void setResloveDisName(String resloveDisName) {
        this.resloveDisName = resloveDisName;
    }

    public String getResloveDisRelationName() {
        return resloveDisRelationName;
    }

    public void setResloveDisRelationName(String resloveDisRelationName) {
        this.resloveDisRelationName = resloveDisRelationName;
    }

    public String getResloveDisSurName() {
        return resloveDisSurName;
    }

    public void setResloveDisSurName(String resloveDisSurName) {
        this.resloveDisSurName = resloveDisSurName;
    }


    public String getReslovePensionPhase() {
        return reslovePensionPhase;
    }

    public void setReslovePensionPhase(String reslovePensionPhase) {
        this.reslovePensionPhase = reslovePensionPhase;
    }


    public String getPopulateviceversaPensionId() {
        return populateviceversaPensionId;
    }

    public void setPopulateviceversaPensionId(String populateviceversaPensionId) {
        this.populateviceversaPensionId = populateviceversaPensionId;
    }

    public String getPopulateviceversaSadaremId() {
        return populateviceversaSadaremId;
    }

    public void setPopulateviceversaSadaremId(String populateviceversaSadaremId) {
        this.populateviceversaSadaremId = populateviceversaSadaremId;
    }


    public String getIsseRaisefilePath() {
        return isseRaisefilePath;
    }

    public void setIsseRaisefilePath(String isseRaisefilePath) {
        this.isseRaisefilePath = isseRaisefilePath;
    }


    public String getVicversaFlag() {
        return vicversaFlag;
    }

    public void setVicversaFlag(String vicversaFlag) {
        this.vicversaFlag = vicversaFlag;
    }



    public String getResolveNewPensionId() {
        return resolveNewPensionId;
    }

    public void setResolveNewPensionId(String resolveNewPensionId) {
        this.resolveNewPensionId = resolveNewPensionId;
    }
    public String getRaiseName() {
        return raiseName;
    }

    public void setRaiseName(String raiseName) {
        this.raiseName = raiseName;
    }

    public String getRaiseRelationName() {
        return raiseRelationName;
    }

    public void setRaiseRelationName(String raiseRelationName) {
        this.raiseRelationName = raiseRelationName;
    }

    public String getNewPensionId() {
        return newPensionId;
    }

    public void setNewPensionId(String newPensionId) {
        this.newPensionId = newPensionId;
    }

    public String getStatusRequest() {
        return statusRequest;
    }

    public void setStatusRequest(String statusRequest) {
        this.statusRequest = statusRequest;
    }

    public String getMedicalUserName() {
        return medicalUserName;
    }

    public void setMedicalUserName(String medicalUserName) {
        this.medicalUserName = medicalUserName;
    }

    public ArrayList getMedicalUserNameList() {
        return medicalUserNameList;
    }

    public void setMedicalUserNameList(ArrayList medicalUserNameList) {
        this.medicalUserNameList = medicalUserNameList;
    }

    public String getResloveLoginId() {
        return resloveLoginId;
    }

    public void setResloveLoginId(String resloveLoginId) {
        this.resloveLoginId = resloveLoginId;
    }

    public String getResolveMemberName() {
        return resolveMemberName;
    }

    public void setResolveMemberName(String resolveMemberName) {
        this.resolveMemberName = resolveMemberName;
    }

    public String getResloveCampId() {
        return resloveCampId;
    }

    public void setResloveCampId(String resloveCampId) {
        this.resloveCampId = resloveCampId;
    }

    public String getResolveMedicalAddress() {
        return resolveMedicalAddress;
    }

    public void setResolveMedicalAddress(String resolveMedicalAddress) {
        this.resolveMedicalAddress = resolveMedicalAddress;
    }

    public String getResolveName() {
        return resolveName;
    }

    public void setResolveName(String resolveName) {
        this.resolveName = resolveName;
    }

    public String getResolveNewSadadremId() {
        return resolveNewSadadremId;
    }

    public void setResolveNewSadadremId(String resolveNewSadadremId) {
        this.resolveNewSadadremId = resolveNewSadadremId;
    }

    public String getResolveRationCardserialNo() {
        return resolveRationCardserialNo;
    }

    public void setResolveRationCardserialNo(String resolveRationCardserialNo) {
        this.resolveRationCardserialNo = resolveRationCardserialNo;
    }

    public String getResolveRejectComments() {
        return resolveRejectComments;
    }

    public void setResolveRejectComments(String resolveRejectComments) {
        this.resolveRejectComments = resolveRejectComments;
    }

    public String getResolveUserName() {
        return resolveUserName;
    }

    public void setResolveUserName(String resolveUserName) {
        this.resolveUserName = resolveUserName;
    }

    public String getResolverelationName() {
        return resolverelationName;
    }

    public void setResolverelationName(String resolverelationName) {
        this.resolverelationName = resolverelationName;
    }

    public String getResolvesurName() {
        return resolvesurName;
    }

    public void setResolvesurName(String resolvesurName) {
        this.resolvesurName = resolvesurName;
    }

    public String getRejectComment() {
        return rejectComment;
    }

    public void setRejectComment(String rejectComment) {
        this.rejectComment = rejectComment;
    }

    public String getStatusCategoryId() {
        return statusCategoryId;
    }

    public void setStatusCategoryId(String statusCategoryId) {
        this.statusCategoryId = statusCategoryId;
    }

    public String getHiddentoSetRationCardNo() {
        return hiddentoSetRationCardNo;
    }

    public void setHiddentoSetRationCardNo(String hiddentoSetRationCardNo) {
        this.hiddentoSetRationCardNo = hiddentoSetRationCardNo;
    }

    public String getNewSerialNo() {
        return newSerialNo;
    }

    public void setNewSerialNo(String newSerialNo) {
        this.newSerialNo = newSerialNo;
    }

    public String getPopulateSerialNo() {
        return populateSerialNo;
    }

    public void setPopulateSerialNo(String populateSerialNo) {
        this.populateSerialNo = populateSerialNo;
    }

    public String getSadaremIdRationCardSlno() {
        return sadaremIdRationCardSlno;
    }

    public void setSadaremIdRationCardSlno(String sadaremIdRationCardSlno) {
        this.sadaremIdRationCardSlno = sadaremIdRationCardSlno;
    }

    public String getRationCardChangeNo() {
        return rationCardChangeNo;
    }

    public void setRationCardChangeNo(String rationCardChangeNo) {
        this.rationCardChangeNo = rationCardChangeNo;
    }

    public String getCampAddress() {
        return campAddress;
    }

    public void setCampAddress(String campAddress) {
        this.campAddress = campAddress;
    }

    public ArrayList getCampAddressList() {
        return campAddressList;
    }

    public void setCampAddressList(ArrayList campAddressList) {
        this.campAddressList = campAddressList;
    }

    public ArrayList getSadaremIdList() {
        return sadaremIdList;
    }

    public void setSadaremIdList(ArrayList sadaremIdList) {
        this.sadaremIdList = sadaremIdList;
    }

    public String getPopulateRelationName() {
        return populateRelationName;
    }

    public void setPopulateRelationName(String populateRelationName) {
        this.populateRelationName = populateRelationName;
    }

    public String getNewRelationName() {
        return newRelationName;
    }

    public void setNewRelationName(String newRelationName) {
        this.newRelationName = newRelationName;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public String getNewSurName() {
        return newSurName;
    }

    public void setNewSurName(String newSurName) {
        this.newSurName = newSurName;
    }

    public String getRadionameChangeId() {
        return radionameChangeId;
    }

    public void setRadionameChangeId(String radionameChangeId) {
        this.radionameChangeId = radionameChangeId;
    }

    public String getPopulateName() {
        return populateName;
    }

    public void setPopulateName(String populateName) {
        this.populateName = populateName;
    }

    public String getPopulateSurName() {
        return populateSurName;
    }

    public void setPopulateSurName(String populateSurName) {
        this.populateSurName = populateSurName;
    }

    public ArrayList getUserNameList() {
        return userNameList;
    }

    public void setUserNameList(ArrayList userNameList) {
        this.userNameList = userNameList;
    }

    public String getRationCardNumber() {
        return rationCardNumber;
    }

    public void setRationCardNumber(String rationCardNumber) {
        this.rationCardNumber = rationCardNumber;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getRationCardSlno() {
        return rationCardSlno;
    }

    public void setRationCardSlno(String rationCardSlno) {
        this.rationCardSlno = rationCardSlno;
    }

    public String getForgetUserName() {
        return forgetUserName;
    }

    public void setForgetUserName(String forgetUserName) {
        this.forgetUserName = forgetUserName;
    }

    public String getNewSadaremId() {
        return newSadaremId;
    }

    public void setNewSadaremId(String newSadaremId) {
        this.newSadaremId = newSadaremId;
    }

    public String getRationCardSlnoChange() {
        return rationCardSlnoChange;
    }

    public void setRationCardSlnoChange(String rationCardSlnoChange) {
        this.rationCardSlnoChange = rationCardSlnoChange;
    }

    public String getRationCardType() {
        return rationCardType;
    }

    public void setRationCardType(String rationCardType) {
        this.rationCardType = rationCardType;
    }

    public String getRationCardNotOpen() {
        return rationCardNotOpen;
    }

    public void setRationCardNotOpen(String rationCardNotOpen) {
        this.rationCardNotOpen = rationCardNotOpen;
    }

    public String getChangePension() {
        return changePension;
    }

    public void setChangePension(String changePension) {
        this.changePension = changePension;
    }

    public ArrayList getDistrictlist() {
        return districtlist;
    }

    public void setDistrictlist(ArrayList districtlist) {
        this.districtlist = districtlist;
    }

    public String getRadioPensionId() {
        return radioPensionId;
    }

    public void setRadioPensionId(String radioPensionId) {
        this.radioPensionId = radioPensionId;
    }

    public void reset() {
        medicalBoardId = "";
        categoryFormId = "";
        otherCategoryId = "";
        sadaremId = "";
        pensionId = "";
        rationCardNo = null;
        mobile = "";
        decription = "";
        fileUpload = null;
        radioPensionId = "";
        rationCardNumber = "";
        populateRelationName = "";
        populateName = "";
        populateSurName = "";
        newRelationName = "";
        newSurName = "";
        newName = "";
        newSadaremId = "";
        newPensionId ="";
        district_id = "0";
        populateviceversaSadaremId ="";
        populateviceversaPensionId="";
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public String getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getResolvedCnt() {
        return resolvedCnt;
    }

    public void setResolvedCnt(String resolvedCnt) {
        this.resolvedCnt = resolvedCnt;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList getDistrictList() {
        return districtList;
    }

    public void setDistrictList(ArrayList districtList) {
        this.districtList = districtList;
    }

    public String getIssueId() {
        return issueId;
    }

    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }

    public String[] getCheckEmailCC() {
        return checkEmailCC;
    }

    public void setCheckEmailCC(String[] checkEmailCC) {
        this.checkEmailCC = checkEmailCC;
    }

    public String[] getCheckEmaild() {
        return checkEmaild;
    }

    public void setCheckEmaild(String[] checkEmaild) {
        this.checkEmaild = checkEmaild;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRequestModeRaiseId() {
        return requestModeRaiseId;
    }

    public void setRequestModeRaiseId(String requestModeRaiseId) {
        this.requestModeRaiseId = requestModeRaiseId;
    }

    public FormFile getResolveUpload() {
        return resolveUpload;
    }

    public void setResolveUpload(FormFile resolveUpload) {
        this.resolveUpload = resolveUpload;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getResolveCampName() {
        return resolveCampName;
    }

    public void setResolveCampName(String resolveCampName) {
        this.resolveCampName = resolveCampName;
    }

    public String getResolveDescription() {
        return resolveDescription;
    }

    public void setResolveDescription(String resolveDescription) {
        this.resolveDescription = resolveDescription;
    }

    public String getResolvecategoryName() {
        return resolvecategoryName;
    }

    public void setResolvecategoryName(String resolvecategoryName) {
        this.resolvecategoryName = resolvecategoryName;
    }

    public String getResolvepensionId() {
        return resolvepensionId;
    }

    public void setResolvepensionId(String resolvepensionId) {
        this.resolvepensionId = resolvepensionId;
    }

    public String getResolverationCard() {
        return resolverationCard;
    }

    public void setResolverationCard(String resolverationCard) {
        this.resolverationCard = resolverationCard;
    }

    public String getResolvesadaremId() {
        return resolvesadaremId;
    }

    public void setResolvesadaremId(String resolvesadaremId) {
        this.resolvesadaremId = resolvesadaremId;
    }

    public String getOtherCategoryId() {
        return otherCategoryId;
    }

    public void setOtherCategoryId(String otherCategoryId) {
        this.otherCategoryId = otherCategoryId;
    }

    public String getCategoryFormId() {
        return categoryFormId;
    }

    public void setCategoryFormId(String categoryFormId) {
        this.categoryFormId = categoryFormId;
    }

    public ArrayList getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(ArrayList categoryList) {
        this.categoryList = categoryList;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getCampId() {
        return campId;
    }

    public void setCampId(String campId) {
        this.campId = campId;
    }

    public ArrayList getCampList() {
        return campList;
    }

    public void setCampList(ArrayList campList) {
        this.campList = campList;
    }

    public String getCampName() {
        return campName;
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }

    public String getSadaremId() {
        return sadaremId;
    }

    public void setSadaremId(String sadaremId) {
        this.sadaremId = sadaremId;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public FormFile getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(FormFile fileUpload) {
        this.fileUpload = fileUpload;
    }

    public String getMedicalBoardId() {
        return medicalBoardId;
    }

    public void setMedicalBoardId(String medicalBoardId) {
        this.medicalBoardId = medicalBoardId;
    }

    public String getPensionId() {
        return pensionId;
    }

    public void setPensionId(String pensionId) {
        this.pensionId = pensionId;
    }

    public String[] getRationCardNo() {
        return rationCardNo;
    }

    public void setRationCardNo(String[] rationCardNo) {
        this.rationCardNo = rationCardNo;
    }

    public ArrayList getMedicalBoardList() {
        return medicalBoardList;
    }

    public void setMedicalBoardList(ArrayList medicalBoardList) {
        this.medicalBoardList = medicalBoardList;
    }

    public String getMedicalBoardName() {
        return medicalBoardName;
    }

    public void setMedicalBoardName(String medicalBoardName) {
        this.medicalBoardName = medicalBoardName;
    }

    public String getAddresschangeSADAREMID() {
        return addresschangeSADAREMID;
    }

    public void setAddresschangeSADAREMID(String addresschangeSADAREMID) {
        this.addresschangeSADAREMID = addresschangeSADAREMID;
    }

    public String getHabitation_id() {
        return habitation_id;
    }

    public void setHabitation_id(String habitation_id) {
        this.habitation_id = habitation_id;
    }

    public ArrayList getHabitationlist() {
        return habitationlist;
    }

    public void setHabitationlist(ArrayList habitationlist) {
        this.habitationlist = habitationlist;
    }

    public String getMandal_id() {
        return mandal_id;
    }

    public void setMandal_id(String mandal_id) {
        this.mandal_id = mandal_id;
    }

    public ArrayList getMandallist() {
        return mandallist;
    }

    public void setMandallist(ArrayList mandallist) {
        this.mandallist = mandallist;
    }

    public String getPanchayat_id() {
        return panchayat_id;
    }

    public void setPanchayat_id(String panchayat_id) {
        this.panchayat_id = panchayat_id;
    }

    public ArrayList getPanchayatlist() {
        return panchayatlist;
    }

    public void setPanchayatlist(ArrayList panchayatlist) {
        this.panchayatlist = panchayatlist;
    }

    public String getVillage_id() {
        return village_id;
    }

    public void setVillage_id(String village_id) {
        this.village_id = village_id;
    }

    public ArrayList getVillagelist() {
        return villagelist;
    }

    public void setVillagelist(ArrayList villagelist) {
        this.villagelist = villagelist;
    }

    

    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
        setSubsubdisabilityId(null);
        setMedicalBoardId(null);
        setCategoryFormId("0");
        setOtherCategoryId(null);
        setSadaremId(null);
        setPensionId(null);
        setRationCardNo(null);
        setMobile(null);
        setDecription(null);
        setFileUpload(null);
        setRadioPensionId(null);
        setRationCardNumber(null);
        setPopulateRelationName(null);
        setPopulateName(null);
        setPopulateSurName(null);
        setNewRelationName(null);
        setNewSurName(null);
        setNewName(null);
        setNewSadaremId(null);
        setNewPensionId(null);
        setDistrict_id("0");
        setPopulateviceversaSadaremId(null);
        setPopulateviceversaPensionId(null);
        setDisabilityType(null);
    }
}
