package DataAccess;

import erreurs.BDConnexionErreur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonDB {
    private static Connection connectionSingleton;

    private SingletonDB(){
    }
    public static Connection getInstance()throws BDConnexionErreur {
        if(connectionSingleton == null){
            try {
                connectionSingleton = DriverManager.getConnection("jdbc:mysql://localhost:3306/spa?useSSL=false", "root", "pwMySQLie2017");
                //pwMySQLie2017
            } catch (SQLException SQLE) {
               //System.out.println(SQLE.getMessage());
               throw  new BDConnexionErreur(SQLE.getMessage());
            }
        }
        return  connectionSingleton;
    }
    //faire le close
}
