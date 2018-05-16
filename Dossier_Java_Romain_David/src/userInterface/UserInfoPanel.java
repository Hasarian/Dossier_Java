package userInterface;

import Model.CareGiver;
import erreurs.BDConnexionError;
import erreurs.ErreurInsertCareGiver;
import erreurs.ErrorNull;
import erreurs.NumberExpection;
import uIController.CareGiverController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserInfoPanel extends RegistrationFormCareGiver
{
    CareGiverController userControl;
    public UserInfoPanel(CareGiverController user,MainFrame frame) throws BDConnexionError,ErrorNull
    {
            super(frame);
            userControl=user;
            setInfos(user.getUSerFirstName(),user.getUserName(),user.getUserEmail(),user.getUserStreet(),user.getUserHouseNumber(),user.getTelNumer(),
                    user.getUserNote(),user.isVolontaire(),user.getLocalite(),user.getDateEmbauche());
            getMailTextField().setEditable(false);
        /*private JTextField name, lastName, mail, street, houseNumber, telNumber;
        private JTextArea note;
        private JCheckBox isVolunteer;
        private JList locality;
        private JButton inscription*/
            getConfirmButton().removeAll();
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
                if (NANHouse.matches()) throw new NumberExpection(getHouseNumberInfo().toString());
                if (NANTel.matches()) throw new NumberExpection(getNumTel().toString());

                String nameTexte = getNameInfo();
                String lastNameTexte = getLastNameInfo();
                Integer tel = getNumTel();
                Integer house = getHouseNumberInfo();
                String noteTexte = getNoteText();
                String streetTexte = getStreetInfo();

                int accord = JOptionPane.showConfirmDialog(null, "êtes vous sûr de sauvegarder les changements ?", "confirmation d'inscription", JOptionPane.YES_NO_CANCEL_OPTION);
                if (accord == JOptionPane.YES_OPTION) {
                    userControl.updateCurrentUser(nameTexte,lastNameTexte,tel,house,noteTexte,streetTexte);
                    JOptionPane.showMessageDialog(null, "vos informations ont été mises à jour", "confirmation", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            catch (BDConnexionError connexionError)
            {
                JOptionPane.showMessageDialog(null, connexionError.getMessage(),"accès BD",JOptionPane.ERROR_MESSAGE);
            }
            catch (ErrorNull errorNull)
            {
                JOptionPane.showMessageDialog(null,errorNull.getMessage(),"db access error",JOptionPane.ERROR_MESSAGE);
            }
            catch (NumberExpection numberExpection)
            {
                JOptionPane.showMessageDialog(null,numberExpection.getMessage(),"db access error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
