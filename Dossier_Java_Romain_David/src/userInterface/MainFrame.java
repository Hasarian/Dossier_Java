package userInterface;

import erreurs.BDConnexionErreur;
import erreurs.ErreurrNull;
import erreurs.SoignantInexistant;

import uIController.SoignantController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame
{
    private ConteneurPanel conteneurPanel;
    private EcranPrincipalPanel basePanel;
    private JPanel panelActuel;
    private JMenuBar menuBar;
    private JMenu account,administration,newFile,search,suppression,modification;
    private JMenuItem logout,newCareGiver,searchTask,searchAnimal,searchCare,personnalInfo,suppressionSoignant,listeSoignant;
    private Container container;
    private MainFrame thisFrame;
    private SoignantController user;

    public MainFrame(SoignantController loggedIn) throws BDConnexionErreur, ErreurrNull, SoignantInexistant
    {
        user=loggedIn;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(15,15,1000,850);
        setResizable(false);
        container=getContentPane();
        container.setBackground(Color.white);
        setLayout(null);
        thisFrame=this;

        basePanel=new EcranPrincipalPanel(this,loggedIn);
        conteneurPanel =new ConteneurPanel(loggedIn,basePanel);
        container.add(conteneurPanel);


        menuBar=new JMenuBar();
        setJMenuBar(menuBar);
        administration=new JMenu("administration");
        menuBar.add(administration);

        newFile=new  JMenu("nouveau...");
        administration.add(newFile);
        newCareGiver= new JMenuItem("soignant");
        newFile.add(newCareGiver);
        newCareGiver.addActionListener(new ToCareFormListener());

        suppression=new JMenu("supprimer...");
        administration.add(suppression);
        suppressionSoignant=new JMenuItem("soignant");
        suppression.add(suppressionSoignant);
        suppressionSoignant.addActionListener(new ToSupprimerSoignant());

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

        modification=new JMenu("modifier...");
        administration.add(modification);
        listeSoignant=new JMenuItem("soignant");
        listeSoignant.addActionListener(new ToModifierSoignant());
        modification.add(listeSoignant);

        account=new JMenu("votre compte");
        menuBar.add(account);
        logout=new JMenuItem("déconnexion");
        logout.addActionListener(new LogoutListener());
        account.add(logout);
        personnalInfo=new JMenuItem("informations personnelles");
        personnalInfo.addActionListener(new ToPersonnalInfoListener());
        account.add(personnalInfo);

        setVisible(true);
    }
    public BannierePanel obtenirBanierePanel()
    {
        return conteneurPanel.getBanner();
    }
    public void changePanel(JPanel newPanel)
    {
        conteneurPanel.changePanel(newPanel);
        container.validate();
        container.repaint();
    }
    public void changePanel()
    {
        if(panelActuel!=null) changePanel(panelActuel);
        else changePanel(basePanel);
        //System.out.println(basePanel.getPersonnelPanel());
    }
    public void setPanelActuel(JPanel nouveauPanel)
    {
        this.panelActuel=nouveauPanel;
    }
    private class ToCareFormListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try {
                FormulaireInscriptionSoignantPanel newPanel = new FormulaireInscriptionSoignantPanel(thisFrame);
                changePanel(newPanel);
            }
            catch (BDConnexionErreur connexionError)
            {
                JOptionPane.showMessageDialog(null, connexionError.getMessage(),"accès BD",JOptionPane.ERROR_MESSAGE);
            }
            catch (ErreurrNull erreurrNull)
            {
                JOptionPane.showMessageDialog(null, erreurrNull.getMessage(),"db access error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private class LogoutListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            dispose();
            ConnexionFrame login=new ConnexionFrame();
        }
    }
    private class ToCareSearchListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try {

                RechercheSoinParAnimalPanel newPanel = new RechercheSoinParAnimalPanel();
                changePanel(newPanel);
            }
            catch (SoignantInexistant soignantInexistant)
            {
                JOptionPane.showMessageDialog(null, soignantInexistant.getMessage(),"unkown member",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private class ToTaskSearchListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try {
                RechercheSoinParSoignantPanel newPanel = new RechercheSoinParSoignantPanel(thisFrame);
                changePanel(newPanel);
            }
            catch(BDConnexionErreur bdConnexionErreur)
            {
                JOptionPane.showMessageDialog(null, bdConnexionErreur.getMessage(),"accès BD",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private class ToAnimalSearchListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try {
                RechercheAnimalPanel newPanel = new RechercheAnimalPanel();
                changePanel(newPanel);
            } catch (BDConnexionErreur connexionError)
            {
                JOptionPane.showMessageDialog(null, connexionError.getMessage(),"accès BD",JOptionPane.ERROR_MESSAGE);
            }
            catch (ErreurrNull erreurrNull)
            {
                JOptionPane.showMessageDialog(null, erreurrNull.getMessage(),"argument invalide",JOptionPane.ERROR_MESSAGE);
            }
            catch (SoignantInexistant loginError)
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
                changePanel(new InfoUtilisateurPanel(user,thisFrame,(BannierePanel) conteneurPanel.getBanner()));
            }
            catch (BDConnexionErreur connexionError)
            {
                JOptionPane.showMessageDialog(null, connexionError.getMessage(),"accès BD",JOptionPane.ERROR_MESSAGE);
            }
            catch (ErreurrNull erreurrNull)
            {
                JOptionPane.showMessageDialog(null, erreurrNull.getMessage(),"db access error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private class ToSupprimerSoignant implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try {
                changePanel(new SuppressionSoignant(thisFrame));
            }
            catch (BDConnexionErreur connexionError)
            {
                JOptionPane.showMessageDialog(null, connexionError.getMessage(),"accès BD",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private class ToModifierSoignant implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try {
                changePanel(new ListeSoignantsPanel(thisFrame));
            }
            catch (BDConnexionErreur connexionErreur)
            {
                JOptionPane.showMessageDialog(null, connexionErreur.getMessage(),"accès BD",JOptionPane.ERROR_MESSAGE);
            }
            catch (ErreurrNull nullE)
            {
                JOptionPane.showMessageDialog(null, nullE.getMessage(),"attribut invalide",JOptionPane.ERROR_MESSAGE);
            }
            catch (SoignantInexistant erreurExistant)
            {
                JOptionPane.showMessageDialog(null, erreurExistant.getMessage(),"utilisateur non trouvé",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
