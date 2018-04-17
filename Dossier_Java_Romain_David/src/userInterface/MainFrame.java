package userInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainFrame extends JFrame
{
    private JPanel mainPanel;
    private JMenuBar menuBar;
    private JMenu account,administration,newFile;
    private JMenuItem logout,newCareGiver,newAnimal;
    private Container container;


    public MainFrame()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(15,15,1000,800);
        setResizable(false);
        container=getContentPane();
        mainPanel=new TaskListPanel(this);
        container.add(mainPanel);
        menuBar=new JMenuBar();
        setJMenuBar(menuBar);
        administration=new JMenu("administration");
        menuBar.add(administration);
        newFile=new  JMenu("new...");
        administration.add(newFile);
        newCareGiver= new JMenuItem("care giver");
        newFile.add(newCareGiver);
        newCareGiver.addActionListener(new ChangePanelMenuListener(new RegistrationFormCareGiver(this)));
        newAnimal=new JMenuItem("animal");
        newFile.add(newAnimal);
        account=new JMenu("account");
        menuBar.add(account);
        logout=new JMenuItem("logout");
        logout.addActionListener(new LogoutListener());
        account.add(logout);
        System.out.println("frame dimensions: "+getX()+" "+getY()+" "+getWidth()+" "+getHeight());
    }
    public void changePanel(JPanel newPanel)
    {
        container.removeAll();
        container.add(newPanel);
        container.validate();
        container.repaint();
    }
    public void changePanel()
    {
        changePanel(mainPanel);
    }
    private class ChangePanelMenuListener implements ActionListener
    {
        JPanel newPanel;
        private ChangePanelMenuListener(JPanel newPanel)
        {
            this.newPanel=newPanel;
        }
        public void actionPerformed(ActionEvent e)
        {
            changePanel(newPanel);
        }
    }
    private class LogoutListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            dispose();
            LoginFrame login=new LoginFrame();
        }
    }
}
