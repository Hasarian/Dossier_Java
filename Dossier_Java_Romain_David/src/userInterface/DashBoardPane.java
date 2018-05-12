package userInterface;

import erreurs.BDConnexionError;
import erreurs.ErrorNull;
import uIController.CareGiverController;
import uIController.ListsControllerAnimal;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DashBoardPane extends JPanel {
    JFrame frame;
    JTabbedPane tabbedPane;
    TaskListPanel personnelPanel;
    ListsControllerAnimal listController;

    public TaskListPanel getPersonnelPanel() {
        return personnelPanel;
    }

    public DashBoardPane(JFrame frame, CareGiverController user) throws ErrorNull, BDConnexionError {
        setLayout(null);
        //setBounds(0,400,500,500);
        this.frame = frame;
        listController=ListsControllerAnimal.obtenirListController(user);
        setBackground(Color.WHITE);
        tabbedPane = new JTabbedPane();
        tabbedPane.insertTab("task list", null, new GeneralTaskListPanel(this,user), "", 0);
       tabbedPane.setBounds(0,0,frame.getWidth()-20,500);
        if(!listController.personnalListIsNull())
        {
            tabbedPane.insertTab("personnal list", null, new PersonnalTaskListPanel(this,user), "", 1);
        }
        if(user.isVeto())
        {
            tabbedPane.insertTab("vet list", null, new VetoTaskListPanel(this,user), "", 1);
            if(!listController.vetoPersonnalListIsNull())  tabbedPane.insertTab("personnal list", null, new VetoPersonnalListPanel(this,user), "", 1);
        }

       add(tabbedPane);
    }
    public void insertTab(TaskListPanel newTab,String description)
    {
        tabbedPane.insertTab(description,null,newTab,"",0);
        revalidate();
    }
 //à faire ! ici: ajoute insert Tab et appelle le listController pour qu'il transvase les données d'une liste à l'autre

}

