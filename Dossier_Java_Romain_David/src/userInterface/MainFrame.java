package userInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame
{
    private MainPanel mainPanel;
    private DashBoardPane basePanel;
    private JMenuBar menuBar;
    private JMenu account,administration,newFile;
    private JMenuItem logout,newCareGiver,newAnimal;
    private Container container;
    private MainFrame thisFrame;

    public MainFrame()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(15,15,1000,800);
        setResizable(false);
        container=getContentPane();
        container.setBackground(Color.white);
        setLayout(null);
        thisFrame=this;

        basePanel=new DashBoardPane(this);

        mainPanel=new MainPanel(this,new DashBoardPane(this));
        container.add(mainPanel);


        menuBar=new JMenuBar();
        setJMenuBar(menuBar);
        administration=new JMenu("administration");
        menuBar.add(administration);
        newFile=new  JMenu("new...");
        administration.add(newFile);
        newCareGiver= new JMenuItem("care giver");
        newFile.add(newCareGiver);
        newCareGiver.addActionListener(new ToCareFormListener());
        newAnimal=new JMenuItem("animal");
        newFile.add(newAnimal);
        account=new JMenu("account");
        menuBar.add(account);
        logout=new JMenuItem("logout");
        logout.addActionListener(new LogoutListener());
        account.add(logout);

        setVisible(true);
    }
    public void changePanel(JPanel newPanel)
    {
        mainPanel.changePanel(newPanel);
        container.validate();
        container.repaint();
    }
    public void changePanel()
    {
        changePanel(basePanel);
        System.out.println(basePanel.getPersonnelPanel());
    }
    private class ToCareFormListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            RegistrationFormCareGiver newPanel=new RegistrationFormCareGiver(thisFrame);
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
