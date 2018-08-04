package interfaceUtilisateur;

import controle.ControleSoignant;
import erreurs.Erreur;

import javax.swing.*;
import java.awt.*;

public class BannierePanel extends JPanel
{
    private JLabel nameLabel;
    private JLabel firstLabel;
    private JLabel email;
    private ControleSoignant user;
    public BannierePanel(ControleSoignant user)
    {
        try {
            this.user = user;
            setLayout(null);
            setBounds(0, 0, 950, 150);
            ImageIcon banner = new ImageIcon("./externalRessources/banner.jpg");
            JLabel bannerLabel = new JLabel(banner);
            bannerLabel.setBounds(0, 0, 800, 150);
            bannerLabel.setBackground(Color.WHITE);
            setBackground(Color.white);
            nameLabel = new JLabel(user.getNomDeFamilleUtilisateurCourant());
            nameLabel.setBounds(840, 5, 200, 45);
            firstLabel = new JLabel(user.getPrenomUtilisateurCourant());
            firstLabel.setBounds(840, 45, 200, 45);
            email = new JLabel(user.getMailUtilisateurCourant());
            email.setBounds(800, 85, 200, 45);

            add(bannerLabel);
            add(nameLabel);
            add(firstLabel);
            add(email);
        }
        catch (Erreur err)
        {
            JOptionPane.showMessageDialog(null,err.getMessage(),err.getTitre(),JOptionPane.ERROR_MESSAGE);
        }
    }
    public void actualisation(){
        try{
        nameLabel.setText(user.getNomDeFamilleUtilisateurCourant());
        firstLabel.setText(user.getPrenomUtilisateurCourant());
        email.setText(user.getMailUtilisateurCourant());
        }
        catch (Erreur err)
        {
            JOptionPane.showMessageDialog(null,err.getMessage(),err.getTitre(),JOptionPane.ERROR_MESSAGE);
        }
    }
}
