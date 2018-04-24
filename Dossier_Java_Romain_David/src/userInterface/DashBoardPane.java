package userInterface;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DashBoardPane extends JPanel
{
    JFrame frame;
    public DashBoardPane(JFrame frame)
    {
        setLayout(null);
        //setBounds(0,400,500,500);
        this.frame=frame;
        setBackground(Color.WHITE);
        JTabbedPane tabbedPane=new JTabbedPane();
       tabbedPane.insertTab("task list",null,new TaskListPanel(),"",0);
       tabbedPane.insertTab("your list",null,new TaskListPanel(),"",0);
       tabbedPane.setBounds(0,0,frame.getWidth()-20,500);
       add(tabbedPane);
    }
}

