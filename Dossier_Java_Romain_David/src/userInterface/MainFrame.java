package userInterface;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainFrame extends JFrame
{
    private JPanel activePanel;
    private JMenuBar menuBar;
    private JMenu account;
    private JMenuItem logout,exit;
    private Container container;


    public MainFrame()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(15,15,1000,800);
        setResizable(false);
        container=getContentPane();
        /*LoginPanel login = new LoginPanel(this);
        activePanel=login;*/
        AnimalCarePanel animalCarePanel= new AnimalCarePanel(this);
        activePanel=animalCarePanel;
        container.add(activePanel);
        setVisible(true);
        System.out.println("frame dimensions: "+getX()+" "+getY()+" "+getWidth()+" "+getHeight());
    }
}
