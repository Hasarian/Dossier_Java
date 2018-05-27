package userInterface;

import Model.Soignant;
import erreurs.BDConnexionErreur;
import erreurs.ErreurrNull;
import erreurs.NombreExpection;
import uIController.SoignantController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InfoUtilisateurPanel extends FormulaireInscriptionSoignantPanel
{
    BannierePanel bannierePanel;
    SoignantController userControl;
    public InfoUtilisateurPanel(SoignantController user, MainFrame frame, BannierePanel bannierePanel) throws BDConnexionErreur, ErreurrNull
    {
            super(frame);
            this.bannierePanel = bannierePanel;
            userControl=user;
            setInfos(user.getPrenomUtilisateurCourant(),user.getNomDeFamilleUtilisateurCourant(),user.getMailUtilisateurCourant(),user.getRueUtilisateurCourant(),user.getNumeroMaisonUtilisateurCourant(),user.getNumTelUtilisateurCourant(),
                    user.getRemarqueUtilisateurCourant(),user.estVolontaire(),user.getLocalite(),user.getDateEmbauche());
        /*private JTextField name, lastName, mail, street, houseNumber, telNumber;
        private JTextArea note;
        private JCheckBox isVolunteer;
        private JList locality;
        private JButton inscription*/
            getConfirmButton().removeActionListener(getConfirmListener());
            repaint();
            getConfirmButton().addActionListener(new MajListener());
    }
    public InfoUtilisateurPanel(Soignant soignant,MainFrame frame) throws BDConnexionErreur,ErreurrNull
    {
        super(frame);

        setInfos(soignant.getPrenom(),soignant.getNomDeFamille(),soignant.getMail(),soignant.getRue(),soignant.getNumMaison().toString(),
                soignant.getNumTel().toString(),soignant.getRemarque(),soignant.getEstBenevole(),soignant.getLocalite(),soignant.getDateEmbauche());
        getConfirmButton().removeActionListener(getConfirmListener());
        repaint();
        getConfirmButton().addActionListener(new MajListener());
    }
    public class MajListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Pattern notNumber = Pattern.compile("\\d*\\D+\\d*");
                Matcher NANTel = notNumber.matcher(getNumTel().toString());
                Matcher NANHouse = notNumber.matcher(getHouseNumberInfo().toString());
                if (NANHouse.matches()) throw new NombreExpection(getHouseNumberInfo().toString());
                if (NANTel.matches()) throw new NombreExpection(getNumTel().toString());

                String nameTexte = getNameInfo();
                String lastNameTexte = getLastNameInfo();
                Integer tel = getNumTel();
                Integer house = getHouseNumberInfo();
                String noteTexte = getNoteText();
                String streetTexte = getStreetInfo();

                int accord = JOptionPane.showConfirmDialog(null, "êtes vous sûr de sauvegarder les changements ?", "confirmation de modification", JOptionPane.YES_NO_CANCEL_OPTION);
                if (accord == JOptionPane.YES_OPTION) {
                    userControl.updateUtilisateurCourant(nameTexte,lastNameTexte,tel,house,noteTexte,streetTexte);
                    JOptionPane.showMessageDialog(null, "vos informations ont été mises à jour", "confirmation", JOptionPane.INFORMATION_MESSAGE);
                    bannierePanel.actualisation();
                }
            }
            catch (BDConnexionErreur connexionError)
            {
                JOptionPane.showMessageDialog(null, connexionError.getMessage(),"accès BD",JOptionPane.ERROR_MESSAGE);
            }
            catch (ErreurrNull erreurrNull)
            {
                JOptionPane.showMessageDialog(null, erreurrNull.getMessage(),"db access error",JOptionPane.ERROR_MESSAGE);
            }
            catch (NombreExpection nombreExpection)
            {
                JOptionPane.showMessageDialog(null, nombreExpection.getMessage(),"db access error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
