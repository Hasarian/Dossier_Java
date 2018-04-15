package userInterface;

import javax.swing.*;
import java.util.ArrayList;

public class TaskListPanel extends JPanel
{
    JFrame frame;
    ArrayList<AnimalCarePanel> animalTasks;
    public TaskListPanel(JFrame frame)
    {
        super();
        this.frame=frame;
        animalTasks=new ArrayList<AnimalCarePanel>();
        setLayout(null);
        setBounds(frame.getX()+10,frame.getY()+10,frame.getWidth(),frame.getHeight());
        int height=0;
        for(int i=0;i<10;i++)
        {
            AnimalCarePanel taskPane = new AnimalCarePanel(this, height);
            add(taskPane);
            height+=taskPane.getHeight()+15;
        }
    }
}
