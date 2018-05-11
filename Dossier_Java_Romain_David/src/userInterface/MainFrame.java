package userInterface;

import erreurs.BDConnexionError;
import erreurs.ErrorNull;
import jdk.nashorn.internal.scripts.JO;
import uIController.CareGiverController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame
{
    private MainPanel mainPanel;
    private DashBoardPane basePanel;
    private JMenuBar menuBar;
    private JMenu account,administration,newFile,search;
    private JMenuItem logout,newCareGiver,searchTask,searchAnimal,searchCare;
    private Container container;
    private MainFrame thisFrame;

    public MainFrame(CareGiverController loggedIn) throws BDConnexionError,ErrorNull
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(15,15,1000,800);
        setResizable(false);
        container=getContentPane();
        container.setBackground(Color.white);
        setLayout(null);
        thisFrame=this;

        basePanel=new DashBoardPane(this,loggedIn);

        mainPanel=new MainPanel(loggedIn,basePanel);
        container.add(mainPanel);


        menuBar=new JMenuBar();
        setJMenuBar(menuBar);
        administration=new JMenu("administration");
        menuBar.add(administration);

        newFile=new  JMenu("nouveau...");
        administration.add(newFile);
        newCareGiver= new JMenuItem("care giver");
        newFile.add(newCareGiver);
        newCareGiver.addActionListener(new ToCareFormListener());

        search=new JMenu("recherche");
        searchAnimal=new JMenuItem("animal");
        search.add(searchAnimal);
        searchAnimal.addActionListener(new ToAnimalSearchListener());
        searchCare=new JMenuItem("soin");
        search.add(searchCare);
        searchCare.addActionListener(new ToCareSearchListener());
        searchTask=new JMenuItem("tâche");
        searchTask.add(searchTask);
        searchTask.addActionListener(new ToTaskSearchListener());


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
            try {
                RegistrationFormCareGiver newPanel = new RegistrationFormCareGiver(thisFrame);
                changePanel(newPanel);
            }
            catch (BDConnexionError connexionError)
            {
                JOptionPane.showMessageDialog(null, connexionError.getMessage(),"accès BD",JOptionPane.ERROR_MESSAGE);
            }
            catch (ErrorNull errorNull)
            {
                JOptionPane.showMessageDialog(null,errorNull.getMessage(),"db access error",JOptionPane.ERROR_MESSAGE);
            }
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
    private class ToCareSearchListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
                SearchCareDone newPanel = new SearchCareDone();
                changePanel(newPanel);
        }
    }
    private class ToTaskSearchListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            SearchTask newPanel = new SearchTask();
            changePanel(newPanel);
        }
    }
    private class ToAnimalSearchListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try {
                SearchAnimal newPanel = new SearchAnimal();
                changePanel(newPanel);
            } catch (BDConnexionError connexionError)
            {
                JOptionPane.showMessageDialog(null, connexionError.getMessage(),"accès BD",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
