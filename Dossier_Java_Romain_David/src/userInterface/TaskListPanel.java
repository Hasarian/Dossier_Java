package userInterface;

import javax.swing.*;
import java.util.ArrayList;

public class TaskListPanel extends JPanel
{
    ArrayList<AnimalCarePanel> animalTasks;
    public TaskListPanel()
    {
        animalTasks=new ArrayList<AnimalCarePanel>();
    }
}
