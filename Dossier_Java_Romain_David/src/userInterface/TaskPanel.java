package userInterface;

import falseData.CareFolder;

import javax.swing.*;
import java.util.ArrayList;

public class TaskPanel extends JPanel
{
    private ArrayList<CarePanel> animals;

    public TaskPanel(ArrayList<CareFolder>animals)
    {
        this.animals=new ArrayList<CarePanel>();
        for(CareFolder animal:animals)
        {
            this.animals.add(new CarePanel(animal));
        }
        for(CarePanel animal:this.animals)
        {
            add(animal);
        }
    }
}
