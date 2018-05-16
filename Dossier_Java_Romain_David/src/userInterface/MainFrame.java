package userInterface;

import erreurs.BDConnexionError;
import erreurs.ErrorNull;
import erreurs.InexistantCareGiver;
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
    private JMenuItem logout,newCareGiver,searchTask,searchAnimal,searchCare,personnalInfo;
    private Container container;
    private MainFrame thisFrame;
    private CareGiverController user;

    public MainFrame(CareGiverController loggedIn) throws BDConnexionError,ErrorNull, InexistantCareGiver
    {
        user=loggedIn;
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
        searchCare=new JMenuItem("soins effectués sur un animal");
        search.add(searchCare);
        searchCare.addActionListener(new ToCareSearchListener());
        searchTask=new JMenuItem("tâches effectuées par un soignant");
        search.add(searchTask);
        searchTask.addActionListener(new ToTaskSearchListener());
        administration.add(search);

        account=new JMenu("account");
        menuBar.add(account);
        logout=new JMenuItem("logout");
        logout.addActionListener(new LogoutListener());
        account.add(logout);
        personnalInfo=new JMenuItem("personnal informations");
        personnalInfo.addActionListener(new ToPersonnalInfoListener());
        account.add(personnalInfo);

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
            try {

                SearchCareByAnimal newPanel = new SearchCareByAnimal();
                changePanel(newPanel);
            }
            catch (InexistantCareGiver inexistantCareGiver)
            {
                JOptionPane.showMessageDialog(null,inexistantCareGiver.getMessage(),"unkown member",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private class ToTaskSearchListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try {
                SearchTaskByGiver newPanel = new SearchTaskByGiver(thisFrame);
                changePanel(newPanel);
            }
            catch(BDConnexionError bdConnexionError)
            {
                JOptionPane.showMessageDialog(null, bdConnexionError.getMessage(),"accès BD",JOptionPane.ERROR_MESSAGE);
            }
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
            catch (ErrorNull errorNull)
            {
                JOptionPane.showMessageDialog(null,errorNull.getMessage(),"argument invalide",JOptionPane.ERROR_MESSAGE);
            }
            catch (InexistantCareGiver loginError)
            {
                JOptionPane.showMessageDialog(null,loginError.getMessage(),"unknown login",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private class ToPersonnalInfoListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                changePanel(new UserInfoPanel(user,thisFrame));
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
}
