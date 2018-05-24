package userInterface;

import uIController.SoignantController;

import javax.swing.*;
import java.awt.*;

public class ConteneurPanel extends JSplitPane
{
    private JPanel banner;

    public ConteneurPanel(SoignantController user, JPanel bottomPanel)
    {
        super(JSplitPane.VERTICAL_SPLIT);
        setBackground(Color.WHITE);

        setBounds(0,0,1000,800);

        banner=new BannierePanel(user);
        setDividerLocation(0.2);
        setDividerSize(0);
        /*System.out.println("panel: "+getX()+" "+getY()+" "+getWidth()+" "+getHeight());
        System.out.println("banner: "+banner.getX()+" "+banner.getY()+" "+banner.getWidth()+" "+banner.getHeight());*/
        setTopComponent(banner);

        setBottomComponent(bottomPanel);

    }
    public void changePanel(JPanel newPanel)
    {
        setBottomComponent(newPanel);
        setDividerLocation(0.2);
    }

    public JPanel getBanner() {
        return banner;
    }
}
