/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.servicefactory;

import org.bf.disability.serviceimpl.IssueRaiseApprovalServiceImpl;

/**
 *
 * @author 693461
 */
public class IssueRaiseApprovalServiceFactory {

    public static IssueRaiseApprovalServiceImpl issueRaiseApprovalServiceImpl;

    public static IssueRaiseApprovalServiceImpl getIssueRaiseApprovalServiceImpl() {
        if (issueRaiseApprovalServiceImpl == null) {
            issueRaiseApprovalServiceImpl = new IssueRaiseApprovalServiceImpl();
        }
        return issueRaiseApprovalServiceImpl;
    }
}
