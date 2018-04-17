package userInterface;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main
{
    public static void main(String[] args)
    {
        LoginFrame frame=new LoginFrame();
        try {
            Connection testeConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/SPA?useSSL=false", "root", "pwMySQLie2017");
        }
        catch (SQLException SQLE){
            JOptionPane.showMessageDialog(null,SQLE.getMessage(),"erreur objet connection", JOptionPane.ERROR_MESSAGE);
        }
    }
}
