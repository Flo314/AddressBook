/*
 * Connection base de données
 */
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class qui gère la connection à la BDD
 * @author florent
 */
public class SingletonCnx {
    
    //URL de connexion
    private static final String URL = "jdbc:mysql://localhost:3306/adressbook?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
    //Nom du user
    private static final String USER = "root";
    //Mot de passe user
    private static final String PASSWD = "";
    //Objet Connection
    private static Connection cnx;

    /**
     * Contructeur privé vide
     */
    private SingletonCnx() {
    }

    /**
     * Méthode qui va retourner notre instance et la créer si elle n'existe pas (Singleton)
     * @return instance de connection
     * @throws SQLException en cas de problème de connection
     * @author Florent
     */
    public static Connection getConnection() throws SQLException {

        if (cnx == null) {
            try {
                cnx = DriverManager.getConnection(URL, USER, PASSWD);
                System.out.println("Connected");
            } catch (SQLException e) {
                Logger.getLogger(TestConnectionDb.class.getName()).log(Level.SEVERE, null, e);
                System.out.println(e.getMessage());
            }
        }
        return cnx;
    }
    
}
