package uIController;

import Business.AnimalBusiness;
import Business.ListAnimalBusiness;
import Model.Animal;
import erreurs.BDConnexionError;
import erreurs.ErrorNull;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class AnimalController {
    ListAnimalBusiness listAnimalBusiness;
    AnimalBusiness animalBusiness;

    public AnimalController(CareGiverController user) throws BDConnexionError,ErrorNull {
        listAnimalBusiness = ListAnimalBusiness.obtenirAnimalBusiness(user);
    }

    public Animal getAnimal(int id) throws ErrorNull, BDConnexionError {
        return listAnimalBusiness.getAnimalInBD(id);
    }
    public ArrayList getAnimalsBetweenDate(GregorianCalendar dateDebut, GregorianCalendar dateFin) throws ErrorNull, BDConnexionError{
        return animalBusiness.getAnimalsBetweenDates(dateDebut, dateFin);
    }
    public ArrayList<Animal> getAllAnimals() throws ErrorNull,BDConnexionError{
        return listAnimalBusiness.getAllAnimals();
    }
}
