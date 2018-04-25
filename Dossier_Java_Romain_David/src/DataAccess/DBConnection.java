package DataAccess;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public DBConnection() {
        try {
            Connection testeConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/SPA?useSSL=false", "root", "pwMySQLie2017");
        } catch (SQLException SQLE) {
            JOptionPane.showMessageDialog(null, SQLE.getMessage(), "erreur objet connection", JOptionPane.ERROR_MESSAGE);
        }
    }
}
