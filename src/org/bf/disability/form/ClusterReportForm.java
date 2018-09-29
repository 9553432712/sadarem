/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.form;

import java.util.ArrayList;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;

/**
 *
 * @author 747577
 */
public class ClusterReportForm extends ActionForm {

    private String mode;
    private String district_name;
    private String cluster_name;
    private String mandal_name;
    ArrayList reportList = new ArrayList();
    ArrayList districtList = new ArrayList();
    ArrayList clusterList = new ArrayList();
    ArrayList mandalList = new ArrayList();
    private String reportType;
    private String reportSelection;
    private String typeOfSearch;

    public String getTypeOfSearch() {
        return typeOfSearch;
    }

    public void setTypeOfSearch(String typeOfSearch) {
        this.typeOfSearch = typeOfSearch;
    }

    public String getReportSelection() {
        return reportSelection;
    }

    public void setReportSelection(String reportSelection) {
        this.reportSelection = reportSelection;
    }

    public ArrayList getReportList() {
        return reportList;
    }

    public void setReportList(ArrayList reportList) {
        this.reportList = reportList;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public ArrayList getClusterList() {
        return clusterList;
    }

    public void setClusterList(ArrayList clusterList) {
        this.clusterList = clusterList;
    }

    public String getCluster_name() {
        return cluster_name;
    }

    public void setCluster_name(String cluster_name) {
        this.cluster_name = cluster_name;
    }

    public ArrayList getDistrictList() {
        return districtList;
    }

    public void setDistrictList(ArrayList districtList) {
        this.districtList = districtList;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public ArrayList getMandalList() {
        return mandalList;
    }

    public void setMandalList(ArrayList mandalList) {
        this.mandalList = mandalList;
    }

    public String getMandal_name() {
        return mandal_name;
    }

    public void setMandal_name(String mandal_name) {
        this.mandal_name = mandal_name;
    }
}
