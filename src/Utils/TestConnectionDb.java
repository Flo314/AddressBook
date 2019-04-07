/*
 * Test Connection BDD
 */
package Utils;

import java.sql.Connection;
import java.sql.SQLException;



public class TestConnectionDb {
    
     public static void main(String[] args) throws SQLException {

         Connection conn = SingletonCnx.getConnection();
    }
}
