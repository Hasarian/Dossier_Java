package interfaceUtilisateur;

import erreurs.Erreur;

import controlle.ControleSoignant;

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
    private ControleSoignant user;

    public MainFrame(ControleSoignant loggedIn) throws Erreur
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

    public EcranPrincipalPanel getBasePanel() {
        return basePanel;
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
            catch (Erreur erreur)
            {
                JOptionPane.showMessageDialog(null, erreur.getMessage(),erreur.getTitre(),JOptionPane.ERROR_MESSAGE);
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

                RechercheSoinParAnimalPanel newPanel = new RechercheSoinParAnimalPanel(thisFrame);
                changePanel(newPanel);
            }
            catch (Erreur erreur)
            {
                JOptionPane.showMessageDialog(null, erreur.getMessage(),erreur.getTitre(),JOptionPane.ERROR_MESSAGE);
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
            catch(Erreur erreur)
            {
                JOptionPane.showMessageDialog(null, erreur.getMessage(),erreur.getTitre(),JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private class ToAnimalSearchListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try {
                RechercheAnimalPanel newPanel = new RechercheAnimalPanel(thisFrame);
                changePanel(newPanel);
            }
            catch (Erreur erreur)
            {
                JOptionPane.showMessageDialog(null, erreur.getMessage(),erreur.getTitre(),JOptionPane.ERROR_MESSAGE);
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
            catch (Erreur err)
            {
                JOptionPane.showMessageDialog(null,err.getMessage(),err.getTitre(),JOptionPane.ERROR_MESSAGE);
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
            catch (Erreur erreur)
            {
                JOptionPane.showMessageDialog(null, erreur.getMessage(),erreur.getTitre(),JOptionPane.ERROR_MESSAGE);
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
            catch (Erreur erreur)
            {
                JOptionPane.showMessageDialog(null, erreur.getMessage(),erreur.getTitre(),JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
