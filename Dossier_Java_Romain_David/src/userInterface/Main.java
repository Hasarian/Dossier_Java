package userInterface;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main
{
    public static void main(String[] args)
    {
        MainFrame frame=new MainFrame();
        try {
            Connection testeConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/SPA?useSSL=false", "root", "Sd246aEq");
        }
        catch (SQLException SQLE){
            JOptionPane.showMessageDialog(null,SQLE.getMessage(),"erreur objet connection", JOptionPane.ERROR_MESSAGE);
        }
    }
}
