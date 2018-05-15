package uIController;

import Business.AnimalBusiness;
import Business.ListAnimalBusiness;
import Model.Animal;
import erreurs.BDConnexionError;
import erreurs.ErrorNull;

public class TaskController
{
    Animal animal;
    public TaskController(String id) throws BDConnexionError, ErrorNull
    {
        ListAnimalBusiness business=ListAnimalBusiness.obtenirListAnimalBusiness(new CareGiverController());
        animal=business.getAnimal(id);
    }
}
