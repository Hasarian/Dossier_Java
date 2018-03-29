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
        container=getContentPane();

        container.add(activePanel);
        setVisible(true);
    }
}
