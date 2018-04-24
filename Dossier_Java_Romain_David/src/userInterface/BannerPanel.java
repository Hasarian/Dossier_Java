package userInterface;

import javax.swing.*;
import java.awt.*;

public class BannerPanel extends JPanel
{
    public BannerPanel()
    {
        setLayout(null);
        setBounds(0,0,950,150);
        ImageIcon banner= new ImageIcon("./externalRessources/banner.jpg");
        JLabel bannerLabel=new JLabel(banner);
        bannerLabel.setBounds(0,0,800,150);
        bannerLabel.setBackground(Color.WHITE);
        setBackground(Color.white);
        JLabel nameLabel=new JLabel("name");
        nameLabel.setBounds(840,5,200,45);
        JLabel firstLabel=new JLabel("firstName");
        firstLabel.setBounds(840,45,200,45);
        JLabel email=new JLabel("email");
        email.setBounds(840,85,200,45);

        add(bannerLabel);
        add(nameLabel);
        add(firstLabel);
        add(email);
    }
}
