/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.dto;

/**
 *
 * @author sivakumar_g
 */
public class TotalPercentageDTO {
     public TotalPercentageDTO() {
    }
    private double upperExtrimity;
    private double lowerExtrimity;
    private double amputation;
    private double transverse;
    private double trunk;
    private double evaluation;
    private double cardiopulmonary;
    private double dwarfism;
    public double totalphysical;
    public double hearingimpairment;
    public double visualimpairment;
    public double mentalretardation;
    public double mentalillness;



    public double getTotalphysical() {
        return totalphysical;
    }

    public void setTotalphysical(double totalphysical) {
        this.totalphysical = totalphysical;
    }

    public double getHearingimpairment() {
        return hearingimpairment;
    }

    public void setHearingimpairment(double hearingimpairment)
    {
        this.hearingimpairment = hearingimpairment;
    }


    public double getVisualimpairment() {
        return visualimpairment;
    }

    public void setVisualimpairment(double visualimpairment) {
        this.visualimpairment = visualimpairment;
    }

    public double getMentalretardation() {
        return mentalretardation;
    }

    public void setMentalretardation(double mentalretardation) {
        this.mentalretardation = mentalretardation;
    }

    public double getMentalillness() {
        return mentalillness;
    }

    public void setMentalillness(double mentalillness) {
        this.mentalillness = mentalillness;
    }

    /**
     * @return the upperExtrimity
     */
    public double getUpperExtrimity() {
        return upperExtrimity;
    }

    /**
     * @param upperExtrimity the upperExtrimity to set
     */
    public void setUpperExtrimity(double upperExtrimity) {
        this.upperExtrimity = upperExtrimity;
    }

    /**
     * @return the lowerExtrimity
     */
    public double getLowerExtrimity() {
        return lowerExtrimity;
    }

    /**
     * @param lowerExtrimity the lowerExtrimity to set
     */
    public void setLowerExtrimity(double lowerExtrimity) {
        this.lowerExtrimity = lowerExtrimity;
    }

    /**
     * @return the amputation
     */
    public double getAmputation() {
        return amputation;
    }

    /**
     * @param amputation the amputation to set
     */
    public void setAmputation(double amputation) {
        this.amputation = amputation;
    }

    /**
     * @return the transverse
     */
    public double getTransverse() {
        return transverse;
    }

    /**
     * @param transverse the transverse to set
     */
    public void setTransverse(double transverse) {
        this.transverse = transverse;
    }

    /**
     * @return the trunk
     */
    public double getTrunk() {
        return trunk;
    }

    /**
     * @param trunk the trunk to set
     */
    public void setTrunk(double trunk) {
        this.trunk = trunk;
    }

    /**
     * @return the evaluation
     */
    public double getEvaluation() {
        return evaluation;
    }

    /**
     * @param evaluation the evaluation to set
     */
    public void setEvaluation(double evaluation) {
        this.evaluation = evaluation;
    }

    /**
     * @return the cardiopulmonary
     */
    public double getCardiopulmonary() {
        return cardiopulmonary;
    }

    /**
     * @param cardiopulmonary the cardiopulmonary to set
     */
    public void setCardiopulmonary(double cardiopulmonary) {
        this.cardiopulmonary = cardiopulmonary;
    }

    /**
     * @return the dwarfism
     */
    public double getDwarfism() {
        return dwarfism;
    }

    /**
     * @param dwarfism the dwarfism to set
     */
    public void setDwarfism(double dwarfism) {
        this.dwarfism = dwarfism;
    }

}
