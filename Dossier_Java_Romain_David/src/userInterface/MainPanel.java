package userInterface;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MainPanel extends JSplitPane
{
    private JPanel banner;

    public MainPanel(JFrame frame,JPanel bottomPanel)
    {
        super(JSplitPane.VERTICAL_SPLIT);
        setBackground(Color.WHITE);

        setBounds(0,0,1000,800);

        banner=new BannerPanel();
        setDividerLocation(0.2);
        setDividerSize(0);
        /*System.out.println("panel: "+getX()+" "+getY()+" "+getWidth()+" "+getHeight());
        System.out.println("banner: "+banner.getX()+" "+banner.getY()+" "+banner.getWidth()+" "+banner.getHeight());*/
        setTopComponent(banner);

        setBottomComponent(bottomPanel);
        //setBottomComponent(bottomPanel);

    }
    public void changePanel(JPanel newPanel)
    {
        setBottomComponent(newPanel);
        setDividerLocation(0.2);
    }

}
