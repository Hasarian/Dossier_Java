package userInterface;

import DataAccess.CareToDoForAnimal;
import Model.CareGiver;
import sun.management.jdp.JdpJmxPacket;
import uIController.CareGiverController;

import javax.swing.*;
import java.awt.*;

public class BannerPanel extends JPanel
{
    private JLabel nameLabel;
    private JLabel firstLabel;
    private JLabel email;
    private CareGiverController user;
    public BannerPanel(CareGiverController user)
    {
        this.user = user;
        setLayout(null);
        setBounds(0,0,950,150);
        ImageIcon banner= new ImageIcon("./externalRessources/banner.jpg");
        JLabel bannerLabel=new JLabel(banner);
        bannerLabel.setBounds(0,0,800,150);
        bannerLabel.setBackground(Color.WHITE);
        setBackground(Color.white);
        nameLabel=new JLabel(user.getUserName());
        nameLabel.setBounds(840,5,200,45);
        firstLabel=new JLabel(user.getUSerFirstName());
        firstLabel.setBounds(840,45,200,45);
        email=new JLabel(user.getUserEmail());
        email.setBounds(800,85,200,45);

        add(bannerLabel);
        add(nameLabel);
        add(firstLabel);
        add(email);
    }
    public void actualisation(){
        nameLabel.setText(user.getUserName());
        firstLabel.setText(user.getUSerFirstName());
        email.setText(user.getUserEmail());
    }
}
