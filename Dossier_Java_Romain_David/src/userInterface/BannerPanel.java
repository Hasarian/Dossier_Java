package userInterface;

import uIController.CareGiverController;

import javax.swing.*;
import java.awt.*;

public class BannerPanel extends JPanel
{
    public BannerPanel(CareGiverController user)
    {
        setLayout(null);
        setBounds(0,0,950,150);
        ImageIcon banner= new ImageIcon("./externalRessources/banner.jpg");
        JLabel bannerLabel=new JLabel(banner);
        bannerLabel.setBounds(0,0,800,150);
        bannerLabel.setBackground(Color.WHITE);
        setBackground(Color.white);
        JLabel nameLabel=new JLabel(user.getUserName());
        nameLabel.setBounds(840,5,200,45);
        JLabel firstLabel=new JLabel(user.getUSerFirstName());
        firstLabel.setBounds(840,45,200,45);
        JLabel email=new JLabel(user.getUserEmail());
        email.setBounds(800,85,200,45);

        add(bannerLabel);
        add(nameLabel);
        add(firstLabel);
        add(email);
    }
}
