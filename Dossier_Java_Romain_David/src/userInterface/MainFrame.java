package userInterface;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame
{
    private JPanel activePanel;
    private JMenuBar menuBar;
    private JMenu account;
    private JMenuItem logout,exit;
    private Container container;


    public MainFrame()
    {
        setBounds(15,15,500,500);
        setResizable(false);
        container=getContentPane();
        LoginPanel login = new LoginPanel(this);
        activePanel=login;
        container.add(activePanel);
        setVisible(true);
    }
}
