/*
 * TrunkImpl.java
 *
 * Created on January 2, 2009, 5:12 PM
 *
 */
package org.bf.disability.util;

import java.math.BigDecimal;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.apache.commons.beanutils.BeanUtils;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.bean.TrunkBean;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.dao.TrunkDAO;
import org.bf.disability.dto.TrunkDTO;
import org.bf.disability.service.TrunkService;
import org.bf.disability.servicefactory.TrunkServiceFactory;
import org.bf.disability.serviceimpl.ShowCalcualationsServiceImpl;

/**
 *
 * @author Deviprasad_t
 */
public class TrunkImpl extends ShowCalcualationsServiceImpl {

    TrunkBean trunkBean = new TrunkBean();

    public void populateTrunkCalculations(DataSource dataSource, HttpServletRequest request, String personcode) throws SADAREMDBException, SQLException {
        try {
            TrunkDAO trunkDAO = new TrunkDAO();
            TrunkDTO trunkDTO = new TrunkDTO();
//   TrunkBean trunkBean =new TrunkBean();
            TrunkService trunkservice = TrunkServiceFactory.getTrunkServiceImpl();
            trunkDTO = trunkservice.getTrunkDetails(dataSource, personcode);
            BeanUtils.copyProperties(trunkBean, trunkDTO);
            trunkBean = trunkCalculationsLogic(trunkBean);

            request.setAttribute("trunkbean", trunkBean);
        } //end of try block
        catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(dataSource,sqlEx.toString(), "populateTrunkCalculations", "TrunkImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TrunkImpl", "populateTrunkCalculations");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(dataSource,sqlEx.toString(), "populateTrunkCalculations", "TrunkImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TrunkImpl", "populateTrunkCalculations");
        }//end of catch block
    }

    public TrunkBean trunkCalculationsLogic(TrunkBean trunkBean) {
        double scoliosisextra = 0.0;
        double kyphosisextra = 0.0;
        double nontraumatic = 0.0;
        double trunk = 0.0;
        double cardio_leg = 0.0;
        double add = 0.0;
        double right = 0.0;
        double trunktotal = 0.0;

        if (trunkBean.getCardio_associateleg() == "") {
            trunkBean.setCardio_associateleg("0");
        }
        if (trunkBean.getScoliosis_measure() == "") {
            trunkBean.setScoliosis_measure("0");
        }
        if (trunkBean.getKyphosis_measure() == "") {
            trunkBean.setKyphosis_measure("0");
        }
        if (trunkBean.getCardio_chest() == "") {
            trunkBean.setCardio_chest("0");
        }
        if (trunkBean.getCardio_counting() == "") {
            trunkBean.setCardio_counting("0");
        }
        if (trunkBean.getCardio_associatecosmetic() == "") {
            trunkBean.setCardio_associatecosmetic("0");
        }
        /*
        modified by ganesh
         */

        int compression = Integer.parseInt(trunkBean.getCompression());
        String compression1 = trunkBean.getCompression();
        int posterior_fusion = Integer.parseInt(trunkBean.getPosterior_fusion());
        String posterior_fusion1 = trunkBean.getPosterior_fusion();
        int posterior_persistent = Integer.parseInt(trunkBean.getPosterior_persistent());
        String posterior_persistent1 = trunkBean.getPosterior_persistent();
        int severe_fire = Integer.parseInt(trunkBean.getSevere_fire());
        String severe_fire1 = trunkBean.getSevere_fire();
        int severe_inadequate = Integer.parseInt(trunkBean.getSevere_inadequate());
        String severe_inadequate1 = trunkBean.getSevere_inadequate();
        int cervical_disc = Integer.parseInt(trunkBean.getCervical_disc());
        String cervical_disc1 = trunkBean.getCervical_disc();
        int cervical_pain = Integer.parseInt(trunkBean.getCervical_pain());
        String cervical_pain1 = trunkBean.getCervical_pain();
        int thoracic_less = Integer.parseInt(trunkBean.getThoracic_less());
        String thoracic_less1 = trunkBean.getThoracic_less();
        int thoracic_more = Integer.parseInt(trunkBean.getThoracic_more());
        String thoracic_more1 = trunkBean.getThoracic_more();
        int thoracic_fusion = Integer.parseInt(trunkBean.getThoracic_fusion());
        String thoracic_fusion1 = trunkBean.getThoracic_fusion();
        int thoracic_radiologically = Integer.parseInt(trunkBean.getThoracic_radiologically());
        String thoracic_radiologically1 = trunkBean.getThoracic_radiologically();
        int fracture_less = Integer.parseInt(trunkBean.getFracture_less());
        String fracture_less1 = trunkBean.getFracture_less();
        int fracture_more = Integer.parseInt(trunkBean.getFracture_more());
        String fracture_more1 = trunkBean.getFracture_more();
        int fracture_radiologically = Integer.parseInt(trunkBean.getFracture_radiologically());
        String fracture_radiologically1 = trunkBean.getFracture_radiologically();

        int disc_persistent = Integer.parseInt(trunkBean.getDisc_persistent());
        String disc_persistent1 = trunkBean.getDisc_persistent();
        int disc_pain = Integer.parseInt(trunkBean.getDisc_pain());
        String disc_pain1 = trunkBean.getDisc_pain();
        int disc_diseases = Integer.parseInt(trunkBean.getDisc_diseases());
        String disc_diseases1 = trunkBean.getDisc_diseases();
        int disc_stifness = Integer.parseInt(trunkBean.getDisc_stifness());
        String disc_stifness1 = trunkBean.getDisc_stifness();
        int scoliosis_measure = Integer.parseInt(trunkBean.getScoliosis_measure());
        String scoliosis_measure1 = trunkBean.getScoliosis_measure();

        int scoliosis_torso = Integer.parseInt(trunkBean.getScoliosis_torso());
        String scoliosis_torso1 = trunkBean.getScoliosis_torso();

        int kyphosis_measure = Integer.parseInt(trunkBean.getKyphosis_measure());
        String kyphosis_measure1 = trunkBean.getKyphosis_measure();
        int kyphosis_torso = Integer.parseInt(trunkBean.getKyphosis_torso());
        String kyphosis_torso1 = trunkBean.getKyphosis_torso();
        boolean scoliosis = (trunkBean.isScoliosis());


        boolean kyphosis = (trunkBean.isKyphosis());


        int head = Integer.parseInt(trunkBean.getHead());
        String head1 = trunkBean.getHead();
        int cardio_chest = Integer.parseInt(trunkBean.getCardio_chest());
        String cardio_chest1 = trunkBean.getCardio_chest();
        int cardio_counting = Integer.parseInt(trunkBean.getCardio_counting());
        String cardio_counting1 = trunkBean.getCardio_counting();
        int cardio_associatepain = Integer.parseInt(trunkBean.getCardio_associatepain());
        String cardio_associatepain1 = trunkBean.getCardio_associatepain();

        int cardio_associatecosmetic = Integer.parseInt(trunkBean.getCardio_associatecosmetic());
        String cardio_associatecosmetic1 = trunkBean.getCardio_associatecosmetic();
        double cardio_inches = Double.parseDouble(trunkBean.getCardio_associateleg());
        String cardio_inches1 = trunkBean.getCardio_associateleg();
        int traumatic = compression + posterior_fusion + posterior_persistent
                + severe_fire + severe_inadequate + cervical_disc + cervical_pain
                + thoracic_less + thoracic_more + thoracic_fusion
                + thoracic_radiologically + fracture_less + fracture_more
                + fracture_radiologically + disc_persistent + disc_pain
                + disc_diseases + disc_stifness;

        int totalscoliosis = scoliosis_measure + scoliosis_torso;

        int totalkyphosis = kyphosis_measure + kyphosis_torso;

        if (cardio_inches <= 0.5) {
            cardio_leg = 0;
        } else {
            cardio_leg = (cardio_inches - 0.5) * 8;
        }
        double extra = head + cardio_chest + cardio_counting + cardio_associatepain
                + cardio_associatecosmetic + cardio_leg;

        int i = 0;

        if ((scoliosis == true) && (kyphosis == true)) {
            scoliosisextra = add(totalscoliosis, extra);
            kyphosisextra = add(totalkyphosis, extra);
            i++;
        } else if (scoliosis == true) {
            scoliosisextra = add(totalscoliosis, extra);
            i++;
        } else if (kyphosis == true) {
            kyphosisextra = add(totalkyphosis, extra);
            i++;
        }

        if (i == 0) {
            scoliosisextra = 0;
            kyphosisextra = 0;
        }
        nontraumatic = add(scoliosisextra, kyphosisextra);


        if (traumatic > 100) {
            trunkBean.setTraumaticflag(true);
            traumatic = 100;
            trunk = add(traumatic, nontraumatic);
        } else if (traumatic <= 100) {

            traumatic = traumatic;
            trunk = add(traumatic, nontraumatic);

        }


        if (trunk > 100) {
            trunktotal = 100;
        } else if (trunk <= 100) {
            trunktotal = trunk;
        }


        StringBuffer traumaticbuffer = new StringBuffer();
        String motortraumatic = null;
        int count = 0;
        if (traumatic != 0) {
            trunkBean.setTraumaticflag1(true);

            if (compression != 0) {
                traumaticbuffer.append(compression + "+");
                count++;
            }
            if (posterior_fusion != 0) {
                traumaticbuffer.append(posterior_fusion + "+");
                count++;
            }
            if (posterior_persistent != 0) {
                traumaticbuffer.append(posterior_persistent + "+");
                count++;
            }
            if (severe_fire != 0) {
                traumaticbuffer.append(severe_fire + "+");
                count++;
            }
            if (severe_inadequate != 0) {
                traumaticbuffer.append(severe_inadequate + "+");
                count++;
            }
            if (cervical_disc != 0) {
                traumaticbuffer.append(cervical_disc + "+");
                count++;
            }
            if (cervical_pain != 0) {
                traumaticbuffer.append(cervical_pain + "+");
                count++;
            }
            if (thoracic_less != 0) {
                traumaticbuffer.append(thoracic_less + "+");
                count++;
            }

            if (thoracic_more != 0) {
                traumaticbuffer.append(thoracic_more + "+");
                count++;
            }
            if (thoracic_fusion != 0) {
                traumaticbuffer.append(thoracic_fusion + "+");
                count++;
            }
            if (thoracic_radiologically != 0) {
                traumaticbuffer.append(thoracic_radiologically + "+");
                count++;
            }
            if (fracture_less != 0) {
                traumaticbuffer.append(fracture_less + "+");
                count++;
            }
            if (fracture_more != 0) {
                traumaticbuffer.append(fracture_more + "+");
                count++;
            }
            if (fracture_radiologically != 0) {
                traumaticbuffer.append(fracture_radiologically + "+");
                count++;
            }

            if (disc_persistent != 0) {
                traumaticbuffer.append(disc_persistent + "+");
                count++;
            }
            if (disc_pain != 0) {
                traumaticbuffer.append(disc_pain + "+");
                count++;
            }
            if (disc_diseases != 0) {
                traumaticbuffer.append(disc_diseases + "+");
                count++;
            }
            if (disc_stifness != 0) {
                traumaticbuffer.append(disc_stifness + "+");
                count++;
            }

            if (count > 1) {
                motortraumatic = traumaticbuffer.substring(0, traumaticbuffer.length() - 1);
                motortraumatic = motortraumatic + "=" + traumatic;
                trunkBean.setTraumaticflag1(true);
                trunkBean.setMotortraumatic(motortraumatic);

            }
        }

        trunkBean.setTraumatic(traumatic);
        trunkBean.setTotalscoliosis(totalscoliosis);
        trunkBean.setTotalkyphosis(totalkyphosis);
        trunkBean.setNontraumatic(nontraumatic);
        trunkBean.setTrunk(trunktotal);
        trunkBean.setExtra(extra);
        trunkBean.setCardio_leg(cardio_leg);
        trunkBean.setKyphosisextra(kyphosisextra);
        trunkBean.setScoliosisextra(scoliosisextra);

        /* string buffer part */

        StringBuffer scoliosisextrarep = new StringBuffer();
        scoliosisextrarep.append("ScoliosisExtra(" + totalscoliosis + "," + extra + ")    =    ");
        trunkBean.setScoliosis_extra(scoliosisextrarep);


        double scoliosisextraformat = formatResult(scoliosisextra);
        double kyphosisextraformat = formatResult(kyphosisextra);
        double nontraumaticformat = formatResult(nontraumatic);
        double traumaticformat = formatResult(trunktotal);

        trunkBean.setScoliosisextraformat(scoliosisextraformat);
        trunkBean.setKyphosisextraformat(kyphosisextraformat);
        trunkBean.setNontraumaticformat(nontraumaticformat);
        trunkBean.setTraumaticformat(traumaticformat);


        if (totalscoliosis > extra) {
            extraCal(totalscoliosis, extra, 1);
        } else {
            extraCal(extra, totalscoliosis, 1);
        }



        StringBuffer khyposisextrarep = new StringBuffer();
        khyposisextrarep.append("KhyposisExtra(" + formatResult(totalkyphosis) + "," + formatResult(extra) + ")    =     ");
        trunkBean.setKyphosis_extra(khyposisextrarep);
        if (totalkyphosis > extra) {
            extraCal(totalkyphosis, extra, 2);
        } else {
            extraCal(extra, totalkyphosis, 2);
        }

        StringBuffer nontraumaticrep = new StringBuffer();
        nontraumaticrep.append("NonTraumatic(" + formatResult(scoliosisextra) + "," + formatResult(kyphosisextra) + ")   =   ");
        trunkBean.setNon_traumatic(nontraumaticrep);

        if (scoliosisextra > extra) {
            extraCal(scoliosisextra, kyphosisextra, 3);
        } else {
            extraCal(kyphosisextra, scoliosisextra, 3);
        }

        StringBuffer totaltrunkrep = new StringBuffer();
        totaltrunkrep.append("Total Trunk(" + formatResult(traumatic) + "," + formatResult(nontraumatic) + ")    =   ");
        trunkBean.setTotaltrunk(totaltrunkrep);

        if (traumatic > nontraumatic) {
            extraCal(traumatic, nontraumatic, 4);
        } else {
            extraCal(nontraumatic, traumatic, 4);
        }

        return trunkBean;
    }  /* end of calulation logic method */


    public double add(double a, double b) {
        double add;
        if (a > b) {
            add = cal(a, b);
        } else {
            add = cal(b, a);
        }
        return add;
    }

    public double cal(double a, double b) {
        double right = a + (((90 - a) / 90) * b);
        return right;

    }

    public void extraCal(double total, double extra, int dec) {
        StringBuffer formula = new StringBuffer();
        StringBuffer temp = new StringBuffer();


        formula.append(formatResult(total) + "+(((90-" + formatResult(total) + ")/90)*" + formatResult(extra) + ")");
        if (dec == 1) {
            temp = trunkBean.getScoliosis_extra();

            temp.append(formula);
            trunkBean.setScoliosis_extra(temp);

        }

        if (dec == 2) {
            temp = trunkBean.getKyphosis_extra();

            temp.append(formula);

            trunkBean.setKyphosis_extra(temp);



        }

        if (dec == 3) {
            temp = trunkBean.getNon_traumatic();
            temp.append(formula);

            trunkBean.setNon_traumatic(temp);

        }

        if (dec == 4) {
            temp = trunkBean.getTotaltrunk();
            temp.append(formula);

            trunkBean.setTotaltrunk(temp);
        }

    }/* end of extra cal */


    public double formatResult(double val) {
        BigDecimal temp1 = new BigDecimal(val).setScale(2, BigDecimal.ROUND_HALF_UP);
        double temp2 = (new Double(temp1.doubleValue())).doubleValue();
        return temp2;
    }
}/* end of class */



