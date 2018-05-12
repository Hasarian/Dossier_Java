package uIController;

import Business.AnimalBusiness;
import Business.ListAnimalBusiness;
import Model.Animal;

public class TaskController
{
    Animal animal;
    public TaskController(String id)
    {
        ListAnimalBusiness business=ListAnimalBusiness.obtenirAnimalBusiness();
        animal=business.getAnimal(id);
    }
}
