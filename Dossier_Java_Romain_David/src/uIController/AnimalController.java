package uIController;

import Business.AnimalBusiness;
import Model.Animal;
import erreurs.BDConnexionError;
import erreurs.ErrorNull;

import java.util.ArrayList;

public class AnimalController {
    AnimalBusiness animalBusiness;

    public AnimalController(CareGiverController user) throws BDConnexionError,ErrorNull {
        animalBusiness = new AnimalBusiness(user);
    }

    public Animal getAnimal(int id) throws ErrorNull, BDConnexionError {
        return animalBusiness.getAnimal(id);
    }
    public ArrayList<Animal> getAllAnimals() throws ErrorNull,BDConnexionError{
        return animalBusiness.getAllAnimals();
    }
}
