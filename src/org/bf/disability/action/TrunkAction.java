package org.bf.disability.action;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.bf.disability.Exception.BaseDispatchAction;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.DataBase.JNDIDataSource;
import org.bf.disability.dao.TrunkDAO;
import org.bf.disability.dto.TrunkDTO;
import org.bf.disability.form.TrunkForm;
import org.bf.disability.service.TrunkService;
import org.bf.disability.servicefactory.TrunkServiceFactory;
import org.bf.disability.util.TrunkCalculation;

import com.tcs.sadarem.util.CommonUtility;

/**
 *
 * Title:TrunkActio.
 * this Action Class is mainly used for inserting and modifying the trunk details
 * @author Bapinaidu.t
 */
public class TrunkAction extends BaseDispatchAction {

    /**
     * This method is mainly used for inserting the Trunk details
     * @param form trunkform
     * @throws java.lang.Exception
     */
    public ActionForward insertTrunkDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        //DataSource datasource=getDataSource(request);

        double trunk = 0;
        String loginid = null;
        TrunkDTO trunkdto = null;
        TrunkDAO trunkdao = null;
        String personcode = null;
        DataSource datasource = null;
        TrunkService trunkservice = null;
        ActionMessages actionMessages = null;
        TrunkCalculation trunkcalculation = null;
        String target = "success";

        try {
            datasource = getDataSource(request);
            if (datasource == null || "null".equals(datasource)) {
                datasource = JNDIDataSource.getConnection();
            }
            HttpSession session = request.getSession(true);


            trunkdao = new TrunkDAO();
            trunkdto = new TrunkDTO();
            trunkservice = TrunkServiceFactory.getTrunkServiceImpl();
            trunkcalculation = new TrunkCalculation();
            TrunkForm trunkform = (TrunkForm) form;
            // personcode = (String) session.getAttribute("personcode");


            if (session.getAttribute("sadaremCodeAu") != null) {
                personcode = (String) session.getAttribute("sadaremCodeAu");
            } else {
                personcode = (String) session.getAttribute("personcode");
            }

            loginid = (String) session.getAttribute("loginid");

            trunkform.setSystemip(request.getRemoteAddr());
            trunkform.setLoginid(loginid);
            boolean personcodecheck = trunkdao.personCode(datasource, personcode);
            if (personcodecheck == false) {
                trunkform = trunkcalculation.calculation(trunkform, request);
                if (trunkform.getTrunk() > 0) {
                    trunk = trunkform.getTrunk();
                    trunkform.setSystemip((String) CommonUtility.getClientIPAddress(request));
                    session.setAttribute("trunk", new Double(trunk));
                    try {
                        BeanUtils.copyProperties(trunkdto, trunkform);
                    } catch (InvocationTargetException ex) {
                        ex.printStackTrace();
                    } catch (IllegalAccessException ex) {
                        ex.printStackTrace();
                    }
                    trunkdto.setPersoncode(personcode);

                    int trunkinsert = trunkservice.insertTrunkDetails(datasource, trunkdto, request);
                    request.setAttribute("msg", "Trunk Added Successfully");
                    target = "success";
                } else {
                    target = "success";
                }
            } else if (session.getAttribute("sadaremCodeAu") != null) {
                trunkform = trunkcalculation.calculation(trunkform, request);
                if (trunkform.getTrunk() > 0) {
                    trunk = trunkform.getTrunk();
                    trunkform.setSystemip((String) CommonUtility.getClientIPAddress(request));
                    session.setAttribute("trunk", new Double(trunk));
                    try {
                        BeanUtils.copyProperties(trunkdto, trunkform);
                    } catch (InvocationTargetException ex) {
                        ex.printStackTrace();
                    } catch (IllegalAccessException ex) {
                        ex.printStackTrace();
                    }
                    trunkdto.setPersoncode(personcode);

                    int trunkinsert = trunkservice.insertTrunkDetailsAU(datasource, trunkdto, request);
                    if (trunkinsert == -1) {
                        target = "failure";
                    } else {
                        request.setAttribute("msg", "Trunk Added Successfully");
                        target = "success";
                    }
                } else {
                    target = "success";
                }
            } else {
                target = "failure";
            }
        } catch (SADAREMDBException sADAREMException) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        } catch (Exception sADAREMException) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }
        return mapping.findForward(target);
    }

    /**
     * This method is mainly used for inserting the Trunk details
     * @param form trunkform
     * @throws java.lang.Exception
     */
    public ActionForward insertTrunkDetailsAU(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws SADAREMDBException, SQLException {
        //DataSource datasource=getDataSource(request);

        double trunk = 0;
        String loginid = null;
        TrunkDTO trunkdto = null;
        TrunkDAO trunkdao = null;
        String personcode = null;
        DataSource datasource = null;
        TrunkService trunkservice = null;
        ActionMessages actionMessages = null;
        TrunkCalculation trunkcalculation = null;
        String target = "success";

        try {
            datasource = getDataSource(request);
            if (datasource == null || "null".equals(datasource)) {
                datasource = JNDIDataSource.getConnection();
            }
            HttpSession session = request.getSession(true);


            trunkdao = new TrunkDAO();
            trunkdto = new TrunkDTO();
            trunkservice = TrunkServiceFactory.getTrunkServiceImpl();
            trunkcalculation = new TrunkCalculation();
            TrunkForm trunkform = (TrunkForm) form;
            personcode = (String) session.getAttribute("sadaremCodeAu");
            loginid = (String) session.getAttribute("loginid");

            trunkform.setSystemip(request.getRemoteAddr());
            trunkform.setLoginid(loginid);
            boolean personcodecheck = trunkdao.personCode(datasource, personcode);
            if (personcodecheck == false) {
                trunkform = trunkcalculation.calculation(trunkform, request);
                if (trunkform.getTrunk() > 0) {
                    trunk = trunkform.getTrunk();
                    trunkform.setSystemip((String) CommonUtility.getClientIPAddress(request));
                    session.setAttribute("trunk", new Double(trunk));
                    try {
                        BeanUtils.copyProperties(trunkdto, trunkform);
                    } catch (InvocationTargetException ex) {
                        ex.printStackTrace();
                    } catch (IllegalAccessException ex) {
                        ex.printStackTrace();
                    }
                    trunkdto.setPersoncode(personcode);
                    int trunkinsert = trunkservice.insertTrunkDetailsAU(datasource, trunkdto, request);

                    if (trunkinsert > 0) {
                        request.setAttribute("msgSuccess", "Trunk Added Successfully");
                        target = "success";
                    } else {
                        request.setAttribute("msgFailure", "Error in Adding the Trunk Details");
                        target = "failure";
                    }
                } else {
                    target = "success";
                }
            } else {
                target = "failure";
            }
        } catch (SADAREMDBException sADAREMException) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        } catch (Exception sADAREMException) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }
        return mapping.findForward(target);
    }

    /**
     * this method is mainly used for retriving the details of trunk
     * @param form trunkform
     * @throws java.lang.Exception
     */
    public ActionForward getTrunkDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException {
        //DataSource datasource=getDataSource(request);


        TrunkDTO trunkdto = null;
        TrunkService trunkservice = null;
        String target = "success";
        String personcode = null;
        DataSource datasource = null;
        ActionMessages actionMessages = null;
        try {
            datasource = getDataSource(request);
            if (datasource == null || "null".equals(datasource)) {
                datasource = JNDIDataSource.getConnection();
            }
            trunkdto = new TrunkDTO();
            trunkservice = TrunkServiceFactory.getTrunkServiceImpl();

            TrunkForm trunkform = (TrunkForm) form;
            HttpSession session = request.getSession(true);
            // personcode = (String) session.getAttribute("personcode");

            if (session.getAttribute("sadaremCodeAu") != null) {
                personcode = (String) session.getAttribute("sadaremCodeAu");
            } else {
                personcode = (String) session.getAttribute("personcode");
            }


            trunkdto = trunkservice.getTrunkDetails(datasource, personcode);
            try {
                BeanUtils.copyProperties(trunkform, trunkdto);
            } catch (InvocationTargetException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
            saveToken(request);
            target = "success";
        } catch (SADAREMDBException sADAREMException) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        } catch (Exception sADAREMException) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }
        return mapping.findForward(target);
    }

    /**
     * this method is mainly used for updating  the details of trunk
     * @param form trunkform
     * @throws java.lang.Exception
     */
    public ActionForward updateTrunkDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SADAREMDBException, SQLException {
        //DataSource datasource=getDataSource(request);

        double trunk = 0;
        TrunkDTO trunkdto = null;
        TrunkDAO trunkdao = null;
        String personcode = null;
        DataSource datasource = null;
        TrunkService trunkservice = null;
        ActionMessages actionMessages = null;
        TrunkCalculation trunkcalculation = null;
        String target = "success";

        try {
            datasource = getDataSource(request);
            if (datasource == null || "null".equals(datasource)) {
                datasource = JNDIDataSource.getConnection();
            }
            boolean flag = isTokenValid(request, true);
            if (flag) {
                HttpSession session = request.getSession(true);
                trunkdao = new TrunkDAO();
                trunkdto = new TrunkDTO();
                trunkservice = TrunkServiceFactory.getTrunkServiceImpl();
                trunkcalculation = new TrunkCalculation();
                TrunkForm trunkform = (TrunkForm) form;


                trunkform.setSystemip(request.getRemoteAddr());
                //personcode = (String) session.getAttribute("personcode");

                if (session.getAttribute("sadaremCodeAu") != null) {
                    personcode = (String) session.getAttribute("sadaremCodeAu");
                } else {
                    personcode = (String) session.getAttribute("personcode");
                }



                String loginid = (String) session.getAttribute("loginid");
                trunkform.setLoginid(loginid);
                trunkform = trunkcalculation.calculation(trunkform, request);
                boolean personcodecheck = trunkdao.personCode(datasource, personcode);
                if (personcodecheck == false) {
                    trunkform = trunkcalculation.calculation(trunkform, request);
                    try {
                        BeanUtils.copyProperties(trunkdto, trunkform);
                    } catch (InvocationTargetException ex) {
                        ex.printStackTrace();
                    } catch (IllegalAccessException ex) {
                        ex.printStackTrace();
                    }
                    trunkdto.setPersoncode(personcode);
                    int trunkinsert = trunkservice.insertTrunkDetails(datasource, trunkdto, request);
                } else {
                    trunk = trunkform.getTrunk();
                    session.setAttribute("trunk", new Double(trunk));
                    try {
                        BeanUtils.copyProperties(trunkdto, trunkform);
                    } catch (InvocationTargetException ex) {
                        ex.printStackTrace();
                    } catch (IllegalAccessException ex) {
                        ex.printStackTrace();
                    }
                    trunkdto.setPersoncode(personcode);
                    int trunkinsert = 0;
                    //  if(session.getAttribute("sadaremCodeAu")!=null) {
                    //  trunkinsert = trunkservice.insertTrunkDetails(datasource, trunkdto,request);
                    //   }else {
                    trunkinsert = trunkservice.updateTrunkDetails(datasource, trunkdto, request);
                    //   }
                    request.setAttribute("msg", "Trunk Updated Successfully");
                }
            }
            target = "success";
        } catch (SADAREMDBException sADAREMException) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        } catch (Exception sADAREMException) {
            target = "exception";
            actionMessages = new ActionMessages();
            actionMessages.add("sadaremexceptionmessage", new ActionMessage("sadarem.exception.sadaremexceptionmessage"));
            saveErrors(request, actionMessages);
        }
        return mapping.findForward(target);

    }
}

