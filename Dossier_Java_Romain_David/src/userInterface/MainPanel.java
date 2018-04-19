package userInterface;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel
{
    private JPanel southPanel;

    public JPanel getSouthPanel() {
        return southPanel;
    }
    public void setSouthPanel(JPanel panel)
    {
        remove(southPanel);
        southPanel=panel;
        add(southPanel,BorderLayout.CENTER);
        revalidate();
    }
    private JFrame frame;
    public MainPanel(JFrame frame)
    {
        this.frame=frame;
        setBounds(15,15,frame.getWidth()-50,frame.getHeight()-50);
        setLayout(new BorderLayout());
        ImageIcon banner= new ImageIcon("./externalRessources/banner.jpg");
        JLabel bannerLabel=new JLabel(banner);
        bannerLabel.setBounds(getX(),getY(),getWidth(),getHeight()*3/10);
        add(bannerLabel,BorderLayout.NORTH);
        TaskListPanel task=new  TaskListPanel(frame);
        add(task);
        System.out.println("main panel dimensions: "+getX()+" "+getY()+" "+getWidth()+" "+getHeight());

    }
}
