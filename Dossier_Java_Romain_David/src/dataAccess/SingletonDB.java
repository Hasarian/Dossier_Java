package dataAccess;

import erreurs.erreursExternes.DonneePermanenteErreur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonDB {
    private static Connection connectionSingleton;

    private SingletonDB(){
    }
    public static Connection getInstance()throws DonneePermanenteErreur {
        if(connectionSingleton == null){
            try {
                connectionSingleton = DriverManager.getConnection("jdbc:mysql://localhost:3306/spa?useSSL=false", "root", "Dr944pEn");
                //pwMySQLie2017
            } catch (SQLException SQLE) {
               //System.out.println(SQLE.getMessage());
               throw  new DonneePermanenteErreur(SQLE.getMessage());
            }
        }
        return  connectionSingleton;
    }
    //faire le close
}
