package userInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainFrame extends JFrame
{
    private JLabel bannerLabel;
    private JPanel basePanel;

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
        container.setBackground(Color.white);
        setLayout(null);
        basePanel=new JPanel();
        basePanel.add(new JLabel("bonjour"));
        container.add(basePanel);
        /*ImageIcon banner= new ImageIcon("./externalRessources/banner.jpg");
        bannerLabel=new JLabel(banner);
        bannerLabel.setBounds(0,0,800,150);
        bannerLabel.setBackground(Color.WHITE);
        container.add(bannerLabel);*/
        //initBanner();
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
        initBanner();
        container.add(newPanel);
        container.validate();
        container.repaint();
        System.out.println(newPanel.getX()+" "+newPanel.getY());
    }
    public void changePanel()
    {
        changePanel(basePanel);
    }
    public void initPanel()
    {
        basePanel.setBounds(0,bannerLabel.getY()+bannerLabel.getHeight()+20,200,200);
        changePanel(basePanel);
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
    private void initBanner()
    {
        ImageIcon banner= new ImageIcon("./externalRessources/banner.jpg");
        bannerLabel=new JLabel(banner);
        bannerLabel.setBounds(0,0,800,150);
        bannerLabel.setBackground(Color.WHITE);
        container.add(bannerLabel);
    }
}
