package userInterface;

import erreurs.BDConnexionError;
import erreurs.ErrorNull;
import erreurs.InexistantCareGiver;
import uIController.CareGiverController;
import uIController.ListsControllerAnimal;
import uIController.TaskController;

import javax.swing.*;

public class TaskPanel extends JPanel
{
    private TaskController controller;
    private ListsControllerAnimal listControl;
    public TaskPanel(Integer id, CareGiverController user) throws BDConnexionError, ErrorNull, InexistantCareGiver
    {
        listControl=ListsControllerAnimal.obtenirListController(user);
        controller=new TaskController(id);
    }
}
