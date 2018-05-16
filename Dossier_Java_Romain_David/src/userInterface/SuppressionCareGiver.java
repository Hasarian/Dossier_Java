package userInterface;

import Model.CareGiver;
import erreurs.BDConnexionError;
import erreurs.ErrorNull;
import erreurs.InexistantCareGiver;
import erreurs.SuppressionCurrentUser;
import uIController.CareGiverController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SuppressionCareGiver extends JPanel
{
    private JLabel mailCareGiver;
    private JList mails;
    private CareGiverController careGiverController;
    private MainFrame frame;
    private String[]users;
    public SuppressionCareGiver(MainFrame frame) throws BDConnexionError
    {
        careGiverController=new CareGiverController();
        setBackground(Color.WHITE);
        this.frame=frame;

        mailCareGiver=new JLabel("choisissez le soignant que vous voulez supprimer:");
        mailCareGiver.setBounds(getX()+15,getY()+15,100,25);
        add(mailCareGiver);

        users=careGiverController.getallUsers();
        mails= new JList(users);
        mails.setVisibleRowCount(users.length);
        mails.setBounds(mailCareGiver.getX()+mailCareGiver.getWidth(),mailCareGiver.getY(),getWidth()-mails.getWidth()-15,300);
        add(mails);

        JButton retour=new JButton("écran principal");
        retour.setBounds(getX()+15,getY()+getHeight()-50,50,30);
        retour.addActionListener(new ToMainMenuListener());
        add(retour);

        JButton supprimer=new JButton("supprimer sélection");
        supprimer.setBounds(getX()+getWidth()-retour.getWidth()-30,retour.getY(),retour.getWidth(),retour.getHeight());
        retour.addActionListener(new SupressListener());
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
                String selected = careGiverController.getOtherName(users[mails.getSelectedIndex()]);
                int accord = JOptionPane.showConfirmDialog(null, "souhaitez vous réellement supprimer "+selected, "confirmation de suppression", JOptionPane.YES_NO_CANCEL_OPTION);
                if(accord==JOptionPane.YES_OPTION)
                {
                    try {
                        careGiverController.supprimerCareGiver(users[mails.getSelectedIndex()]);
                        users=careGiverController.getallUsers();
                        mails.setListData(users);
                    }
                    catch (SuppressionCurrentUser suppressionCareGiverCurrent)
                    {
                        accord = JOptionPane.showConfirmDialog(null, "voulez vous vraiment supprimer votre compte du système .", "confirmation de suppression", JOptionPane.YES_NO_CANCEL_OPTION);
                        if(accord==JOptionPane.YES_OPTION)
                        {
                            careGiverController.supprimerUtilsiateurActuel();
                            frame.dispose();
                            LoginFrame login=new LoginFrame();
                        }
                    }
                }
            }
            catch(InexistantCareGiver inexistantCareGiver)
            {
                JOptionPane.showMessageDialog(null,"cet utilisateur n'existe pas","utilisateur inconnu",JOptionPane.ERROR_MESSAGE);
            }
            catch(BDConnexionError connectError)
            {
                JOptionPane.showMessageDialog(null,"unable to connect to the BD","connexion error",JOptionPane.ERROR_MESSAGE);
            }
            catch (ErrorNull errorNull) {
                JOptionPane.showMessageDialog(null, errorNull.getMessage(), "db access error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
