package userInterface;

import erreurs.Erreur;
import erreurs.erreurFormat.ErreurrNull;
import erreurs.erreurFormat.NombreExpection;
import model.Soignant;
import uIController.SoignantController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InfoUtilisateurPanel extends FormulaireInscriptionSoignantPanel
{
    BannierePanel bannierePanel;
    SoignantController userControl;
    String ancienMail;
    public InfoUtilisateurPanel(SoignantController user, MainFrame frame, BannierePanel bannierePanel) throws Erreur
    {
            super(frame);
            this.bannierePanel = bannierePanel;
            userControl=user;
            ancienMail=user.getMailUtilisateurCourant();
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
    public InfoUtilisateurPanel(Soignant soignant,MainFrame frame) throws Erreur
    {
        super(frame);
        bannierePanel=frame.obtenirBanierePanel();
        userControl=new SoignantController();
        setInfos(soignant.getPrenom(),soignant.getNomDeFamille(),soignant.getMail(),soignant.getRue(),soignant.getNumMaison().toString(),
                soignant.getNumTel().toString(),soignant.getRemarque(),soignant.getEstBenevole(),soignant.getLocalite(),soignant.getDateEmbauche());
        getConfirmButton().removeActionListener(getConfirmListener());
        ancienMail=soignant.getMail();
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

                String argumentsIncorrects="";
                String mailTexte= getMailInfo();
                String nameTexte = getNameInfo();
                if(nameTexte==null)argumentsIncorrects+="prénom \n";
                String lastNameTexte = getLastNameInfo();
                if(lastNameTexte==null) argumentsIncorrects+="nom de famille \n";
                Integer tel = getNumTel();
                Integer house = getHouseNumberInfo();
                if(house<0||house==null) argumentsIncorrects+="numéro de maison \n";
                String noteTexte = getNoteText();
                String streetTexte = getStreetInfo();
                if(streetTexte==null)argumentsIncorrects+="nom de rue";
                if(!argumentsIncorrects.equals("")) throw new ErreurrNull(argumentsIncorrects);
                GregorianCalendar dateEmbauche=getDateEmbauche();

                int accord = JOptionPane.showConfirmDialog(null, "êtes vous sûr de sauvegarder les changements ?", "confirmation de modification", JOptionPane.YES_NO_OPTION);
                if (accord == JOptionPane.YES_OPTION) {
                    userControl.updateUtilisateur(ancienMail,mailTexte,nameTexte,lastNameTexte,tel,estVolontaire(),house,noteTexte,streetTexte,getLocalite(),dateEmbauche);
                    JOptionPane.showMessageDialog(null, "vos informations ont été mises à jour", "confirmation", JOptionPane.INFORMATION_MESSAGE);
                    bannierePanel.actualisation();
                }
            }
            catch (Erreur err)
            {
                JOptionPane.showMessageDialog(null,err.getMessage(),err.getTitre(),JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
