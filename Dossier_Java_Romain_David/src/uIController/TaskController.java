package uIController;

import Business.AnimalBusiness;
import Business.ListAnimalBusiness;
import Model.Animal;
import erreurs.BDConnexionError;
import erreurs.ErrorNull;
import erreurs.InexistantCareGiver;

public class TaskController
{
    Animal animal;
    public TaskController(Integer id) throws BDConnexionError, ErrorNull, InexistantCareGiver
    {
        ListAnimalBusiness business=ListAnimalBusiness.obtenirListAnimalBusiness(new CareGiverController());
        animal=business.getAnimal(id);
    }
}
