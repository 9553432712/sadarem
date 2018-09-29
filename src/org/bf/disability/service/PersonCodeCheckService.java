/*
 * PersonCodeCheckService.java
 *
 * Created on July 7, 2008, 12:33 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.service;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;

/**
 * This interface has abstract method, whose implementation places a vital role in
 * person code generation
 * @version Deviprasad
 * @version 1.0
 */
public interface PersonCodeCheckService {

    public boolean checkPersonCode(String personcode, DataSource ds) throws SADAREMDBException, SQLException;

    public ArrayList getCalculatedValues(String presoncode, DataSource ds) throws SADAREMDBException, SQLException;
}
