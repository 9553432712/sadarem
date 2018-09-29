 package org.bf.disability.service;

import java.sql.SQLException;
import org.bf.disability.Exception.SADAREMDBException;
import javax.sql.DataSource;
 /**
 *
 * @author 310926
 */
   public interface ExpectionService {

       public int saveException(DataSource ds,String exp,String method,String DAO,String type) throws SADAREMDBException,SQLException;

   }