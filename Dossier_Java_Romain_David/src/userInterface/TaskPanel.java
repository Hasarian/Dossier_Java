package userInterface;

import erreurs.BDConnexionError;
import erreurs.ErrorNull;
import uIController.CareGiverController;
import uIController.ListsControllerAnimal;
import uIController.TaskController;

import javax.swing.*;

public class TaskPanel extends JPanel
{
    private TaskController controller;
    private ListsControllerAnimal listControl;
    public TaskPanel(String id, CareGiverController user) throws BDConnexionError, ErrorNull
    {
        listControl=ListsControllerAnimal.obtenirListController(user);
        controller=new TaskController(id);
    }
}
