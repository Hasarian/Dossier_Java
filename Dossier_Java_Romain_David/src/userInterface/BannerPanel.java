package userInterface;

import javax.swing.*;
import java.awt.*;

public class BannerPanel extends JPanel
{
    public BannerPanel()
    {
        setLayout(null);

        ImageIcon banner= new ImageIcon("./externalRessources/banner.jpg");
        JLabel bannerLabel=new JLabel(banner);
        bannerLabel.setBounds(0,0,800,150);
        bannerLabel.setBackground(Color.WHITE);

        JLabel nameLabel=new JLabel("name");
        nameLabel.setBounds(840,5,200,45);
        JLabel firstLabel=new JLabel("firstName");
        firstLabel.setBounds(840,60,200,45);
        JLabel email=new JLabel("email");
        email.setBounds(840,100,200,45);

        add(bannerLabel);
        add(nameLabel);
        add(firstLabel);
        add(email);
    }
}
