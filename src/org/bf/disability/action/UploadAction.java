package org.bf.disability.action;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.BaseAction;
import org.bf.disability.common.CommonDAO;
import org.bf.disability.common.CommonDetails;
import org.bf.disability.common.CommonService;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.PartADAO;
import org.bf.disability.form.UploadForm;
import org.bf.disability.service.PartAService;
import org.bf.disability.service.TerritoryService;
import org.bf.disability.servicefactory.PartAServiceFactory;
import org.bf.disability.servicefactory.TerritoryServiceFactory;

import Aadhar.AadhaarUtility;

import com.ecentric.servicemodels.AadhaarProfile;
import com.tcs.sadarem.util.CommonUtility;

/**
 * this action class will save the uploaded photo in a particular folder in the server
 * @author deviprasd t
 * @version 1.0
 */
public class UploadAction extends BaseAction {

    /**
     * this method will save the uploaded photo in a particular folder in the server
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws java.lang.Exception
     * @return Action Forward
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionMessages actionMessages = null;
        String target = null;
        String personcode = null;
        String aadharcardNo = null;
        String districtName = null;
        String extension = null;
        String strDirectoy = null;
        String districtid = null;
        DataSource ds = null;
        String photoUploadWithPersonCodeFlag = null;
        String photoview = null;
        String loginid = null;
        CommonDetails commonDetails = null;
        boolean districtLevelAccessFlag = false;
        File sourceDir = null;
        File targetDir = null;
        try {
            ds = getDataSource(request);
            if (ds == null || "null".equals(ds)) {
                ds = JNDIDataSource.getConnection();
            }
            CommonDetails commondetails = new CommonDetails();
            PartADAO partADAO = new PartADAO();
            CommonDAO commonDAO = new CommonDAO();
            UploadForm myForm = (UploadForm) form;
            HttpSession session = request.getSession(true);
            CommonService commonservice = new CommonService();
            photoUploadWithPersonCodeFlag = (String) request.getParameter("photoUploadWithPersonCodeFlag");
            photoview = (String) request.getParameter("photoview");
            // added for upload photos based on district folder
            districtName = (String) session.getAttribute("district_name");

            if ("true".equals(photoUploadWithPersonCodeFlag)) {
                personcode = (String) request.getParameter("personcode");
            } else {
                personcode = (String) session.getAttribute("personcode");
            }
            String pensioncardno = partADAO.checkPensioncardno(ds, personcode);

          

            if (pensioncardno != null && !pensioncardno.isEmpty()) {
                request.setAttribute("pensioncardno", pensioncardno);


            }
            int currentRoleId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("roleId")));
            boolean adminLevelAccessFlag = commondetails.campareRoleIds(String.valueOf(currentRoleId), CommonConstants.ADMIN_PERMISSION_IDS);
            if (!adminLevelAccessFlag) {
                districtid = (String) session.getAttribute("districtId");
                districtLevelAccessFlag = commondetails.checkDistrictFlag(personcode, districtid);
            }
            if (null == districtName) {
                districtid = (String) session.getAttribute("districtId");
                if (adminLevelAccessFlag || districtLevelAccessFlag) {
                    TerritoryService territoryservice = TerritoryServiceFactory.getTerritoryServiceImpl();
                    districtName = territoryservice.getDistrictsName(ds, districtid);
                }
            }

            int count = commonDAO.getPhotoUploadDetails(ds, personcode);
            if ("view".equals(photoview)) {
                boolean uploadPhotoFlag = commonservice.checkUploadPhoto(ds, personcode);
                PrintWriter out = response.getWriter();
                response.setHeader("Cache-Control", "private");
                response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
                response.setDateHeader("Expires", 0);
                response.setHeader("Pragma", "no-cache");
                response.addHeader("Cache-Control", "post-check=0, pre-check=0");
                response.setContentType("text/xml");

                out.println("<root>");
                if (uploadPhotoFlag && personcode != null && districtName != null) {
                    if (personcode != null && districtName != null) {
                        commonDetails = new CommonDetails();
                        String url = getServlet().getServletContext().getRealPath("/");
                        commonDetails.copyPhotoDtoRelativePath(personcode, districtName, request, url);
                    }
                    out.println("<personcode>" + personcode + "</personcode>");
                    out.println("<districtname>" + districtName + "</districtname>");
                    out.println("<message>" + "no" + "</message>");
                } else {
                    String message = "Photo is Not Uploded";
                    out.println("<personcode>" + "no" + "</personcode>");
                    out.println("<districtname>" + "no" + "</districtname>");
                    out.println("<message>" + message + "</message>");
                }
                out.println("</root>");
            } else if ("aadharValidate".equals(photoview)) {
                boolean success1 = false;
                boolean success2 = false;
                String base64 = "";

//                ArrayList existSADAREMID = new ArrayList();
                PartAService partAService = PartAServiceFactory.getPartAServiceImpl();
                int i = 0;
                aadharcardNo = (String) request.getParameter("aadharCardNo");
                if (personcode != null && districtName != null) {
                    request.setAttribute("personcode", personcode);
                    if (aadharcardNo != null) {

                        int j = partADAO.checkAdharCardNumberMapping(ds, aadharcardNo, personcode);

                        if (j > 0) {
                            request.setAttribute("errmsg", "Assessment Already done for the given AadharCard Number ");
                            request.setAttribute("aadharEnable", "aadharEnable");
                        } else {
                            AadhaarProfile aadharProfile = null;
                            try {
                               // aadharProfile = AadharDetails.getAadharDetails(aadharcardNo);

                            	aadharProfile = AadhaarUtility.getAadhaarProfileByUID(aadharcardNo);
                            	
                            } catch (Exception e) {
                                request.setAttribute("errmsg", "Aadhar Webservice is down. Please try after some time");

                            }
                            if (aadharProfile != null) {
                                //validation Aadhar Number End

                                //System.out.println("Status: " + aadharProfile.getStatus());
                                String invalidFlag = aadharProfile.getStatus();

                                if (invalidFlag!=null && (invalidFlag.equalsIgnoreCase("100") || invalidFlag.equalsIgnoreCase("101"))) {

                                    if (aadharProfile.getBase64file() != null && !aadharProfile.getBase64file().equalsIgnoreCase("101")) {
                                        base64 = aadharProfile.getBase64file();
                                    } else {
                                        base64 = "-";
                                    }
                                } else if (invalidFlag!=null && invalidFlag.substring(0, 3).equalsIgnoreCase("121")) {
                                    request.setAttribute("errmsg", "Given Aadhar Number is Cancelled/Rejected UID -  " + aadharcardNo + " due to reason '" + invalidFlag.substring(3, invalidFlag.length()) + "'");
                                } else {
                                    request.setAttribute("errmsg", "Given Aadhar Number is In-Valid. UID - " + aadharcardNo + ".");

                                }

                            }
//                            base64 = "/9j/4AAQSkZJRgABAgAAAQABAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCADIAKADASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwD0M49KYw+U1MB7Uxu9OxmVynPSnhcZyKMjcafnigLWISORS4wMUpHOM0uBQA3aBxmkOOmKivNQsrCLzLu7gt0/vTOEB9ua5LUPiZodqQtsZ7xsnPlJgD8Wx+lFhnYEEDA6Um0fSvLrn4oX7sxt9OhRT90SSE4+uMVCfiPq5XzZLa3GO0Z4/LJpXQWZ6vt460bM15vpfxVtmxHqNu6OTjfFggfUZru7DV7PUoFmtLiOaNv4kOQP8KFYT03LmBjntQV6cUA7ujcCn8YHemBHtppUdMVNt4qMjjiiwDNoB/xpSBjIp2KaaNBDSKYRint7jIpox3NMDVQ5X2pD3pu8RqQo6dKgMkxcbsc9vSjcoeQd/SlwM+1Az1p3WpAawyMiuI8ceNz4ejFlYhX1GVcgkZEK+pHqew/MevQ+Itbg0DSZryfnYPlQHl2PQV88X2oz6pqM97cvummbc7f0HsBwPYCk3bYaVya6vLi+uPtN/dS3M5/jlYscf0HtUsEAZGd1YjtWbg5GzJar8cs0MO0Z3EYAxUstaFa5vmTMcYAA/wBkZrPe4mk4aQ49Kmmj2sc8uadFptzONyxsRScklqNJt6FLJHerthqd5Yyh7S8ngfjmNyufyq4nh+YgbgxJ9ulW4PDhIO8MvHBqPax6GioTfQ6TSPifqlqVj1KNbmPs4+VvrwOa9I0XxLaazbpPA+QeD7H0NeBXELW7NburZ7Yp2j6tc6HqcdxGWwDh0zjcvcVpGWhjKFtD6XDgimk9+1Ymkaqt7ZQXUbGSGUBge6/WtrqOOp71pcztYUmmetOOfakI4oBjenNIcE8D86CMCm/Sgk1niDj0OKhcBJApBOe/pVsnA6U1lBOTRd2LK4G0ZPSgkKucfjT2XAAHasLxhrX9h+G7q8ziTbsi5x8x4FILHknxN8QHU/ED2MT5t7MlOD1k/iP4Hj8D61xlvEZDxUM8zSStJJy7HJ+tXrUqiqWOFxk+9Zt9TWMb6F61skDBpGIHp61dmiXaNmBnpWT9taSYE/h7V1nhbRZfEV+iDiJcGWQDoPSplJRjdlxi5PliQ6F4Pn1WXzWTbED19a7qPwnFbQbUjJ4x0rsrPTILSKKCBFVEXHFTyx4U5x7V5VavKb8j16NKMFY4ldCjiQ5iwfSqVzpihSNvPr6V1lwcOeMj6Vn3KLtJxXOpu52ckbbHm+t6MrAMByOlcbfIAfmX514zXqerREqeOnauC1i1w5ZQDngivSw1RtWZ5WLopao3/hzrkkMctkSSqHfjP8JPPHsf5163DINikNgEdO1fPXhbUF0vxDFJKjNGwKMFPIyOv4V7VpOoQzwIAwwT1HT6ivQTPKaszot3B9fSgAEZqCKQlgp69jVjPp3pkjSDnA4pCPzqQnPtTGGfrTEa2Ome1BpT0puCMmpLI244ryv4vaorWNnpyklvNMkmDwMDAHv94/lXqjD5c4rx34o2g82OZWVuqlQDlSTnJ+tDBbnlp5PIqWMkjAqNuCR3rovDegT6ncx4gMgJ4Xpn6n0rKc1FXZvCDk7IseGPCd/r94scCbI8/PMw+VB7+/oO/wCte/8Ah/w3a6Lp8dtaKWA5Z26s3cmuX0/QL22slgfVo7NB1itohgfj1NIw1zSWL2PiATkfwzx4B/nXBUkqrs5aHfCDpL3Ueh/YDs9D7VUlspAGyM+9cpYfEHUYZBFq2nfL2mtjlSfoen512NlqsGoxGSE59QaynSijSNSe5jzWe4Hjmsq5s2GODxWxqN7DAfmcADqc1yOq+N7CxYqmZpSPuqKxVJt2idbrcqvIo6pbsobA5rhtbj2QluhzzW/d6/rep7nttL2xepYAn865jWJ9RMTfbLMqvXcpBx9cV2UaTjuzhr1lNaI5iJjHqEUnJwwPBx3r0vw1frHOLUuCJgXiJ4IbPKn+Y/GvNLeIXGoQRFtokkC7vQE9a6y606/09FkjfesZBjlQ5KEHjjtz+FeinoeW1qes2k/zoSeRxtPatgMD0rgPDWsjUkgk480KVlHo4HNdlHPlAc9qohl0ngCmbjjFRiTcKduz9fSmSbrH2xTc5Pen7eM0zHvSKEYbkIHFcH460ltS0uSG3j3XChXAHcZ5/wA+1d70HNc3renefdtdLuE0ULeWwzwcdKxrVOSNzow1JVJ2Z4JouhT6vqot0U4DfMcdK9ht9PTw/poESAPt5ao/A+gJYXF1uZZH8wjzF6N7iu4vNOgurYxOm7NebiKzlK3Q9GhBQXmeWTeJ5HWdlNxIIgS4gj3bQOuWPFZv/CVm/uILe1juXklHC4DHOTwcd+M/iK6+TQrnR7t3s0wjHldoIPsQeKybHSpdIvftenWCQ3ByPMC7tufQHIHBxx2rWm6dtgmql9GRaXqDTvsK/N3Fem+GVi+zl1AGfvfWuMsdNnjne7m5nfqMDnPU8V12jxNbWrE5BPOK5qrUZaG6heGpxfjvUFs5DEpPzNwAe9cZbxxqftF0yRxjksx6/wBa6Tx3H5t/DL12vzUOlXiWltdLNax3aXcPkSrJHnbHjBVSCMD/APXXRRtyq5hWT5rGXe65Z2vlw216jMyhtvkyKcEAg/MB2NYl3qpucoSGyOxyDV2Ky0/TLx7mK3mkcA7BM4IXP0AyayrfTpbjUHnij2x55X1+lbJQvdHPJ1LWZmWluBrcGeATuGR6V26Xoj+8TET1DDMbj+lc7qEBg1G2dCEIbGW6fjXQ2/mGICTaY2yBgf54rpjK6TOSUbMh0SdbTxDJHGdqTqXXnIDKeR+X8q7q3u+MfpXAXKCDU7Q26qGyccd9p/oK6CzvmkXkAMOCKpMhrqddFcVbSTJrnbe75xn9a1be4BxVoho7MdKQgU4DikI9KAGNgDrzVCfLFlOAemasXjFIw4I+Tk57iudtNYTUxdpbZaaNtuGHy8jjPtXLjE3TOvBO1UbocA00y2oOSkjKD6gGuxtGR4wT1xXFw276fPHbvKZSoGXPVveuts5NwAPWvJlZyueo1oW5LRZFOAD9aoPppZvnAAHatVGOMUOwUFiK05VbQzUmmc9cwRQP8qZduBmpFtZ2izuOT6Cq+oazp+nvJcX0qRsDgBjjFVbD4g6dcgiJ0IHqMZ/Oso0+Y3c5JaHMeLtMkKM45I55rK0L/SbUEckcFTWj4n8ZWE0xg3pufjGaz/Dl9bwawlm+B9pzs/3uv8q6EpKFiOaPtLmm+hxz8mMD14qteWENjCSFUcdBXV3BECEAYrkNcuMqT2HWopuUnqa1VFRucneqLi9jGPutn8R0rYCeXCiHOV71hxzL/aADMAWzitKaaW4xFbMMH7z+lerBWVjw5u8mVkZrrXV258u3UknsWIxj8q1kXyzkd+tRWtqlnCI1JPUlj1J7mps4PPSqILUMzAitW2uCcViRtj6VoQNjFUmS0j1MOCcCkY7VJzmkBABxWfqNwUjKq3NX5EGfrF4XikijJ24O8g/pXl2jeKLiw8Y3FjDHHImoypCrSPjy2zgMOcY55zXaa7draadMVfMhzznv3ryay1gaRq0+oLaLNKYzHCzHHlMSMuOME43D/gXtWVVKUXG1zak+WSkeqaf4ht9au50ibdJat5chB6kenr0rs7OfMaMp7V4l4V8Rfb/Gd1NJBHb/AGtOI0Hy7lA/nya9btJwgBHTHrXk4inyOx69CftI3OojmzGDxTmJcYxWTFdAjIakm1mCHl3VQOpJrFSb0LcLMTUdBs76dZ3UiZOjqcVW1XSLG5so4p1WRxhVJ64qvP4vsixSDMxHUr0FZc3iW3ujhijBOeDgitIwn0Kim9zn/EPgbS7W6j+zx7M/MW3FifzzUNhoFpZapFqBlkkljOU3EYU/hVi7137W5MpGF+7jtVVdYgZgqyAnPTvWv7y1mS1CLuzoru+DrnPBrkNcm3nINXJb5W6dK53VrtWyM8U6MPeM8RU90wpGNxeuoLAx4Ix2NdFplw3lBCq8e2M1yVhcf6Y0pxtZiCfr0roYQVTchPqP616S2PJbubySbxkcCn59Riqls/7tcHr+tTF8de1O4kTcA8cGrcLnAqgG59qtxEnBB/ChMTPWnYAda5rW7pVlKE44zkfT/wCvWlc34SNs454615z4j11TqM6byoQKvXA6Z/r+lUyEip4kvzMBEjcKOADwTXB3wCbkPXrWxNqCyB3PLNwDnPFY2oqzOX9egpdTWwlhcNp9/Bdxr9xgx9xXtem6glzbxyRtuRlDKfUGvE2IFkOBnOK6Dwb4glhmNjKd0agsh7rzyPp/9eubE0ueN+x04apySt3PXorjnHeqs2nRXsrG4TzEHRW6VFa3MUm11YDPoa10iEijy2PPvXlP3WesrNGWl9HpQSERRrEvABXirVzrlhc2/wByHd/vAitH+yIblMTgEVn3XgbSJQZGhBPbmtoVbrUOflehiatqtglsAqxjA5xjBrh3aO9vfNWIBRxuAxXXXvhTS7cs0cY46AmufuIAj7Uwq1tGonsYYiU5fFsQzzpDAcHn1rlNVvP3TsT14WtbUpkB2Z4WuPv7nz5to+6vSumjE82tPoXNPI+zNnswPSurgICIo4PIx9a5CwkAhkTvjIrqtOkEkYaQ/MK6GYI00Rgg2nFTKzEcgH2pscq5x3p5xuxjmkMkjIJq7AB1qiFJIOOhrQtj04OKpEs6a+dI4nkuJCqqeFHFePatqCzajdSADLyEgdc16hfQmVLgupY+XnmvIL2N1v5cjlm4/GmwQec+Mkj61XnufnIPJFXXsHES7vlBxgVk3Y2z8cZXNSVfQGuieCfl71peFmDeIol7MrDH6/0rCzljXQeFbG4OrQXmwhBkgkcHgjj/AD2qallFlU7uaPR5be50/E8AZ4epXHSrtj4n8kgseK19MKyW4WTBBHIrL1bwqkm6a1JQnsOleQpxk7SPWcZJXiaw8VwMAyuBx3NVrjxgu0gODn0rg73S9QtmP7ssB3Q4rEmmkjYiQTKfQitVRi9iXWkt0drf+IBKDz1965i81b7wXnNY0l6MEfOT+VUJ7h5OF4reFNI56lWUtxdQvGdHAb5j1NYy9a0DCxUgjrUUdlJI3yqa64NJHFKLkySz+WRW9etdjp+1owxPXmuQFlcR4xwTwM1taffSwWxWVCSp4K88U209ibNbnSZWPpwPXPSpEJYhuvpVBbqKRkZ5cL6EVZW5gSPdv3L6gdaVwumXY5DnLDmtC1Yu+QePSueg1KCSZkJZeO4rXt9QtI0G6UAY9DVJktxOq8RSmCzWVFBVfkdPVCRz+g/WvKL0xy6jLIvKK2QfbtXV3OozTQtA8rtE3RWO7HoOe3FYE8UaLJGqBVkGGwKzVRMzVaN7Gbc6lGYiwIPasNla4lAjQsT2pl3DJZ3DRP06g/3hWtoBWS6IfHzdMetVKXKro6Ie+7GhoXg83gE1zkoeQg712Vvp6wyKwQKAAAAOgFavh+2H2cnge3pWgbHG5fvYbk4rzKtaUpWZ6tOjGEdBtkxhORyDWzFKH71lxRlRjsO1WEbOBiuZ2budC0LM1pBNksmD6isPUdBs5ASyD8hWtJK68Zz7Gs67mYg1UW09CWkzkrzQLMMQq5NZkmgwJkgCumkR3c4H40z7JjkjJroUmc8oI5F9IUE/LVmz0yML8ycgHnFdC1gXbOKlFkFif5eAp6U+dkKnqc1LpEcsallPykH8KEsYQWKsCo4AHOa69LRPLHAzj0qsmmwQ7lRMKxyVHAJpqoU6Rw81swuSpG1RyAfSoYWkkSV84j3bVH9a6jXYYo1QhQHwQB7VkRwBbdV7Y4rphO6PFxC9nNxKNjERI8jdWOB9KmvWeK1JUc7lH6iprO2Z5whcfMTjPAGBmo7+FpbV41PJI/Q5/pV3uzBPU25IxtNZkyEgqwyOzVslQQRyareWu7Yw4rFOxkmc3fWQurUoQPNj+639Kj0GwuLa/jeZMITgGtu4t/Km4+6altMK/CgMMYz39qqU/dsdVGo4tHeaAGjhzNnk8VuKF+2sucq6gj8OKpabbpe6dDcwn5XWrMtpLCFlBPyntXmSeup9NGzimmTfZcScd6ZJZuh3KtXIhKMbhkfSriliBlcipuN3Rz0inHeqMsDsxwOPpXVSW0LDOMH0qEWKP0qk+wro5VLOQnGM1ZTTXPJFdRFpyqwJFSTWwAwBxTcmTpscm9qkWeOaqXKAQPtBHHJrpprVQSSB+NULmAeW6qMEjsM0KRXIjHhgkKgEHGKlFqB1rREPyior0fZ7GWXoQpx9apNim1FXPP8AV5ftWpsqkFVbC/hUMkWDkHpSDm9wRzgkirD28wtvtBicQk7Q+Plz9a70rKx8rVm5zcjPZSJowvU0jLuY+xpzFhfRL7U5eXce9UQaduxaHa3304b3NNkXkN1FMJMepr12zx5I9CP/ANYq08ZBP05FQQ9CpLH5iEHqKrYMbBwMEGrbfI2eT7e1Q3Y2wGRe1CKi7M9B8E3qzacLfcCQx2qcZArrlQElWHUda8j8NX7WV78rKmGWQsw7dMfqf8ivWrW6jniSQHKsNyn1BrkqwUXc9/CVeeFuxJbBPLwQMrxVgCPpgDNU/N8q8x/BIOOO4qdju+pqEkdN2Q3SKSNtMgjYGpChzhscdKlT5AKXLdl81lYnSLK802bYie4pfN+Q4qlMzSEgHIpslXbKdzJubiqsqfuiDjc5AXP1BP6VofZyTkioZoy99FHj5UUuTjv0HP4n9KS3NG9LFdYOCSOKzdbjV7Jo3mSLPOW9BzXQiMY6cVha2J30i6kGn+ZESI45j/CTnoPw6/T6G6SvK5zYupy0murOC0ix/tDW47dp44VmEg81+gCozf8AsuKhhvrn7PLpxYG38zcVxnkehqhdPLHqttGDgK2049Mc1YWIxyE56mu7ofOz0KzqP7UFCKTLLnoGpu8nVFz3yKljO6aQdjimIu3vyy27Y6MV6ev/AOoVZlY7eD9aKKiRBWYdefpUXDxtGR17UUUIaGxf6PKPNcqY9ytgZ3MM4B+p/nXo/hjUPPgaGSRXdQHAXsD2/Ag/pRRWVbZnp4KTU9Dek/eJxwVOV+tJHcFwG6HoQaKK5D2SdZTxzUpckUUUxtITeTkZzQF79KKKbJYE46VTjy95PIcY4UEY7f8A66KKXQESTcW7SEsIwwVmUZI//UDmsm7WzGlPNZX09zGpIeJ2+7wNpHHGAT+vvRRW0fgPMxMm5pM8n1JPK1WKHIyke849M4H9fyq7N2OKKK6uiPKe5krzqsftzzVkjyrhyPun0ooqmDP/2Q==";
                            if (base64 != "" && !base64.equalsIgnoreCase("-"))
                            {
                                loginid = (String) session.getAttribute("loginid");
                                commonservice.insertUploadPhoto(ds, personcode, loginid);


                                String fileName = "Profilepic.JPG";

                                String url = getServlet().getServletContext().getRealPath("/");
                                String strDirectoytemp = CommonConstants.SADAREM_DOCUMENTS_PATH+personcode+"\\Profile\\";


                                File dir = null;
                                File subdir = null;
                                File subdir1 = null;
                                File distdir = null;


                                subdir = new File(getServlet().getServletContext().getRealPath("/") + "DisabilityUITG");
                                if (!subdir.exists()) {
                                    subdir.mkdir();
                                }
                                subdir1 = new File(subdir + "/uploadedphotos");
                                if (!subdir1.exists()) {
                                    subdir1.mkdir();
                                }

                                distdir = new File(subdir1 + "/" + personcode);
                                if (!distdir.exists()) {
                                    distdir.mkdir();
                                }

                                success1 = CommonDetails.uploadingAadharPhoto(base64, "" + distdir, fileName);

                                File tempdir = new File(strDirectoytemp);
                                if (!tempdir.exists()) {
                                    tempdir.mkdirs();
                                }
                                /*File tempdistdir = new File(tempdir + "/" + districtName);
                                if (!tempdistdir.exists()) {
                                    tempdistdir.mkdir();
                                }*/
                                success2 = CommonDetails.uploadingAadharPhoto(base64, "" + tempdir, fileName);

                                if (success1 && success2) {
                                    i = 1;
                                    request.setAttribute("succMsg", "Photo Uploaded Successfully. To View Photo click on View");
                                }

                            } 
                            else if (base64.equalsIgnoreCase("-"))
                            {
                                request.setAttribute("uploadButton", "uploadButton");
                                request.setAttribute("invalid", "invalid");
//                            } else {
//                                request.setAttribute("errmsg", "Photo is not Uploaded");
                            }
                        }
                    } 
                    else
                    {
                        request.setAttribute("errmsg", "Error Occured Whle Photo Upload");
                    }
                } 
                else
                {
                    request.setAttribute("errmsg", "Photo is not Uploaded");
                }

            } 
            else if ("upload".equals(photoview)) 
            {

                boolean success1 = false;
                boolean success2 = false;
                String base64 = "";
                int i = 0;
                if (count == 0)
                {
                    if (personcode != null && districtName != null) 
                    {

                        aadharcardNo = commonDAO.getAadharCardno(ds, personcode);
                        if (aadharcardNo != null)
                        {
                            AadhaarProfile aadharProfile = null;
                            try
                            {
                                aadharProfile = AadhaarUtility.getAadhaarProfileByUID(aadharcardNo);
                            } 
                            catch (Exception e)
                            {
                                request.setAttribute("errmsg", "Aadhar Webservice is down. Please try after some time");

                            }
                            if (aadharProfile != null)
                            {
                                //validation Aadhar Number End

                                //System.out.println("Status: " + aadharProfile.getStatus());
                                int invalidFlag = Integer.parseInt(aadharProfile.getStatus());

                                if (invalidFlag == 100 || invalidFlag == 101) 
                                {
                                    if (aadharProfile.getBase64file() != null && !aadharProfile.getBase64file().equalsIgnoreCase("101"))
                                    {
                                        base64 = aadharProfile.getBase64file();
                                    } 
                                    else 
                                    {
                                        base64 = "-";
                                    }
                                }

                            }
//                        base64 = "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";
//                            base64 = "nonconsent";
                            if (base64 != "" && !base64.equalsIgnoreCase("-")) 
                            {
                                loginid = (String) session.getAttribute("loginid");
                                commonservice.insertUploadPhoto(ds, personcode, loginid);


                                String fileName = "Profilepic" + ".JPG";

                                String url = getServlet().getServletContext().getRealPath("/");
                                String strDirectoytemp = CommonConstants.SADAREM_DOCUMENTS_PATH+personcode+"\\Profile\\";


                                File dir = null;
                                File subdir = null;
                                File subdir1 = null;
                                File distdir = null;


                                subdir = new File(getServlet().getServletContext().getRealPath("/") + "DisabilityUITG");
                                if (!subdir.exists()) 
                                {
                                    subdir.mkdir();
                                }
                                subdir1 = new File(subdir + "/uploadedphotos");
                                if (!subdir1.exists()) {
                                    subdir1.mkdir();
                                }

                                distdir = new File(subdir1 + "/" + districtName);
                                if (!distdir.exists()) {
                                    distdir.mkdir();
                                }

                                success1 = CommonDetails.uploadingAadharPhoto(base64, "" + distdir, fileName);

                                File tempdir = new File(strDirectoytemp);
                                if (!tempdir.exists()) {
                                    tempdir.mkdirs();
                                }
                                /*File tempdistdir = new File(tempdir + "/" + districtName);
                                if (!tempdistdir.exists()) {
                                    tempdistdir.mkdir();
                                }*/
                                success2 = CommonDetails.uploadingAadharPhoto(base64, "" + tempdir, fileName);

                                if (success1 && success2)
                                {
                                    i = 1;
                                    request.setAttribute("succMsg", "Photo Uploaded Successfully. To View Photo click on View");
                                }

                            }
                            else if (base64.equalsIgnoreCase("-"))
                            {
                                request.setAttribute("uploadButton", "uploadButton");
                                request.setAttribute("invalid", "invalid");
//                            } else {
//                                request.setAttribute("errmsg", "Photo is not Uploaded");
                            }
                        }
                        else
                        {
                            request.setAttribute("personcode", personcode);
                            request.setAttribute("aadharEnable", "aadharEnable");
                        }
                    } 
                    else
                    {
                        request.setAttribute("errmsg", "Photo is not Uploaded");
                    }
                } 
                else 
                {
                    request.setAttribute("errmsg", "Photo Already Uploaded");
                }
            }
            else if ("fileUpload".equals(photoview)) 
            {
                if (personcode != null && districtName != null)
                {
                    loginid = (String) session.getAttribute("loginid");
                    commonservice.insertUploadPhoto(ds, personcode, loginid);
                    FormFile myFile = myForm.getTheFile();
                    String fileName = myFile.getFileName();

                    // Added by mohan
                    long filesizeInKB = myFile.getFileSize() / 1024;

                    if (filesizeInKB < 150)
                    {
                        //End of Added Mohan
                        int dotPos = fileName.lastIndexOf(".");
                        extension = fileName.substring(dotPos);
                        fileName = "Profilepic" + ".JPG";

                        String url = getServlet().getServletContext().getRealPath("/");
                        String strDirectoytemp = CommonConstants.SADAREM_DOCUMENTS_PATH+personcode;


                        File dir = null;
                        File subdir = null;
                        File subdir1 = null;
                        File distdir = null;

                        subdir = new File(getServlet().getServletContext().getRealPath("/") + "DisabilityUITG");
                        if (!subdir.exists())
                        {
                            subdir.mkdir();

                        }
                        subdir1 = new File(subdir + "/uploadedphotos");
                        if (!subdir1.exists())
                        {
                            subdir1.mkdir();
                        }

                        distdir = new File(subdir1 + "/" + personcode);
                        if (!distdir.exists())
                        {
                            distdir.mkdir();
                        }

                        CommonDetails.uploadingFile(myForm.getTheFile(), "" + distdir, fileName);

                        File tempdir = new File(strDirectoytemp);
                        if (!tempdir.exists())
                        {
                            tempdir.mkdirs();
                        }
                        File tempdistdir = new File(tempdir + "/" + "Profile");
                        if (!tempdistdir.exists())
                        {
                            tempdistdir.mkdirs();
                        }
                        CommonDetails.uploadingFile(myForm.getTheFile(), "" + tempdistdir, fileName);


                    }
                    else 
                    {
                        request.setAttribute("msg", "Photo Size Must be Less Than 150 KB");
                    }
                }
                else 
                {
                    request.setAttribute("msg", "Photo is not Uploaded");
                }
                return mapping.findForward("success");
            } 
            else 
            {

                if (personcode != null && districtName != null)
                {
                    String success = partADAO.checkPersoncodeData(ds, personcode);
                    if (success != null)
                    {
                        if (success.equalsIgnoreCase("Success"))
                        {
                            aadharcardNo = commonDAO.getAadharCardno(ds, personcode);
                            request.setAttribute("personcode", personcode);
                            if (aadharcardNo == null && count == 0)
                            {
                                request.setAttribute("aadharEnable", "aadharEnable");
                                
                            }
                            request.setAttribute("invalid", "invalid");
                        } 
                        else
                        {
                            request.setAttribute("errmsg", "Kindly  Upload / View Photo for SADAREM ID " + personcode + " after 15 mins");
                            
                        }
                    }
                    else
                    {
                        request.setAttribute("errmsg", "Invalid SADAREM ID");
                        request.setAttribute("invalid", "invalid");
                    }

                }
            }
            if ("true".equals(photoUploadWithPersonCodeFlag))
            {
                target = "photoupload";
            } 
            else if ("uploadview".equals(photoUploadWithPersonCodeFlag))
            {
                target = "uploadview";
            } 
            else
            {
                target = "photoupload";
            }
        } 
        catch (Exception e)
        {
            e.printStackTrace();
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }
        if ("view".equals(photoview))
        {
            return null;
        } 
        else
        {
            return mapping.findForward(target);
        }
    }
}
