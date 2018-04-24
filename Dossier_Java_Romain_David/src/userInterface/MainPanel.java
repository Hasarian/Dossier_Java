package userInterface;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MainPanel extends JPanel
{

    public MainPanel()
    {
        setLayout(new BorderLayout());
        BannerPanel banner=new BannerPanel();
        add(banner, BorderLayout.NORTH);


    }
}
