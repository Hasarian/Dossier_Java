package interfaceUtilisateur;

import erreurs.Erreur;
import controle.ControleSoignant;
import controle.ControleListesAnimaux;

import javax.swing.*;
import java.awt.*;

public class EcranPrincipalPanel extends JPanel {
    private MainFrame frame;
    private JTabbedPane tabbedPane;
    private ListeDeTachesPanel personnelPanel;
    private ControleListesAnimaux listController;

    public ListeDeTachesPanel getPersonnelPanel() {
        return personnelPanel;
    }
    public MainFrame getFrame(){return frame;}

    public EcranPrincipalPanel(MainFrame frame, ControleSoignant user) throws Erreur {
        setLayout(null);
        //setBounds(0,400,500,500);
        this.frame = frame;
        listController= new ControleListesAnimaux();
        setBackground(Color.WHITE);
        tabbedPane = new JTabbedPane();
        tabbedPane.insertTab("liste de taches", null, new ListeDesTachesDisponiblesPanel(this,user,frame), "", 0);
       tabbedPane.setBounds(0,0,frame.getWidth()-20,500);
        if(!listController.aucunAnimalReserve())
        {
            tabbedPane.insertTab("liste personnelle", null, new ListeDesTachesReserveesPanel(this,user, frame), "", 1);
        }
        if(user.estVeterinaire())
        {
            tabbedPane.insertTab("liste veterinaire", null, new ListeDesTachesDisponiblesVetoPanel(this,user, frame), "", 1);
            if(!listController.aucunAniumalReserveDansLaListeVeto())  tabbedPane.insertTab("liste personnelle (veterinaire)", null, new ListeDesTachesReserveesVetoPanel(this,user, frame), "", 1);
        }

       add(tabbedPane);
    }
    public void insertTab(ListeDeTachesPanel newTab, String description)
    {
        tabbedPane.insertTab(description,null,newTab,"",0);
        revalidate();
    }
    public void removeTab(ListeDeTachesPanel tab)
    {
        tabbedPane.remove(tab);
    }
 //à faire ! ici: ajoute insert Tab et appelle le listController pour qu'il transvase les données d'une liste à l'autre

}

