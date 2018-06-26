package userInterface;

import erreurs.DonneePermanenteErreur;
import erreurs.ErreurrNull;
import erreurs.SoignantInexistant;
import erreurs.SuppressionUtilisateurCourant;
import uIController.SoignantController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SuppressionSoignant extends JPanel
{
    private JLabel mailSoignant;
    private JList mails;
    private SoignantController soignantController;
    private MainFrame frame;
    private String[]users;
    public SuppressionSoignant(MainFrame frame) throws DonneePermanenteErreur
    {
        soignantController =new SoignantController();
        setBackground(Color.WHITE);
        this.frame=frame;

        mailSoignant=new JLabel("choisissez le soignant que vous voulez supprimer:");
        mailSoignant.setBounds(getX()+15,getY()+15,100,25);
        add(mailSoignant);

        users= soignantController.getTousLesUtilisateurs();
        mails= new JList(users);
        mails.setVisibleRowCount(users.length);
        mails.setBounds(mailSoignant.getX()+mailSoignant.getWidth(),mailSoignant.getY(),getWidth()-mails.getWidth()-15,300);
        add(mails);

        JButton retour=new JButton("écran principal");
        retour.setBounds(getX()+50,getY()+getHeight()-50,100,30);
        retour.addActionListener(new ToMainMenuListener());
        add(retour);

        JButton supprimer=new JButton("supprimer sélection");
        supprimer.setBounds(getX()+getWidth()-retour.getWidth()-50,retour.getY(),retour.getWidth(),retour.getHeight());
        supprimer.addActionListener(new SupressListener());
        add(supprimer);
    }
    private class ToMainMenuListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.changePanel();
        }
    }
    private class SupressListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try {
                String selected = soignantController.getNomDeFamilleAutreUtilisateur(users[mails.getSelectedIndex()]);
                int accord = JOptionPane.showConfirmDialog(null, "souhaitez vous réellement supprimer "+selected, "confirmation de suppression", JOptionPane.YES_NO_CANCEL_OPTION);
                if(accord==JOptionPane.YES_OPTION)
                {
                    try {
                        soignantController.supprimerSoignant(users[mails.getSelectedIndex()]);
                        users= soignantController.getTousLesUtilisateurs();
                        mails.setListData(users);
                    }
                    catch (SuppressionUtilisateurCourant suppressionSoignantCurrent)
                    {
                        accord = JOptionPane.showConfirmDialog(null, "voulez vous vraiment supprimer votre compte du système .", "confirmation de suppression", JOptionPane.YES_NO_CANCEL_OPTION);
                        if(accord==JOptionPane.YES_OPTION)
                        {
                            soignantController.supprimerUtilsiateurActuel();
                            frame.dispose();
                            ConnexionFrame login=new ConnexionFrame();
                        }
                    }
                }
            }
            catch(SoignantInexistant soignantInexistant)
            {
                JOptionPane.showMessageDialog(null,"cet utilisateur n'existe pas","utilisateur inconnu",JOptionPane.ERROR_MESSAGE);
            }
            catch(DonneePermanenteErreur connectError)
            {
                JOptionPane.showMessageDialog(null,"unable to connect to the BD","connexion error",JOptionPane.ERROR_MESSAGE);
            }
            catch (ErreurrNull erreurrNull) {
                JOptionPane.showMessageDialog(null, erreurrNull.getMessage(), "db access error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
