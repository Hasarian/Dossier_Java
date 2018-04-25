package userInterface;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DashBoardPane extends JPanel {
    JFrame frame;
    JTabbedPane tabbedPane;

    public DashBoardPane(JFrame frame) {
        setLayout(null);
        //setBounds(0,400,500,500);
        this.frame = frame;
        setBackground(Color.WHITE);
        tabbedPane = new JTabbedPane();
        tabbedPane.insertTab("task list", null, new GeneralTaskListPanel(this), "", 0);
        //String[][] misty = new String[96][TaskListPanel.COLUMNNUMBER];
        /*for(int i=0;i<misty.length;i++)
        {
            String[] rowData={"misty","c3","cat"};
            misty[i]=rowData;
        }
       tabbedPane.insertTab("your list",null,new TaskListPanel(misty,this),"",0);*/
       tabbedPane.setBounds(0,0,frame.getWidth()-20,500);
       add(tabbedPane);
    }

    public void generatePersonnalList(Object[][] data) {
        tabbedPane.insertTab("your list", null, new TaskListPanel(data, this), "", 0);
        repaint();
    }
}

