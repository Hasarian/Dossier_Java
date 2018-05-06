package uIController;

import Business.AnimalBusiness;
import Model.Animal;
import erreurs.BDConnexionError;
import erreurs.ErrorNull;

import java.util.ArrayList;

public class AnimalController {
    AnimalBusiness animalBusiness;

    public AnimalController() throws BDConnexionError {
        animalBusiness = new AnimalBusiness();
    }

    public Animal getAnimalBusiness(int id) throws ErrorNull, BDConnexionError {
        return animalBusiness.getAnimal(id);
    }
    public ArrayList<Animal> getAllAnimalsBusiness() throws ErrorNull,BDConnexionError{
        return animalBusiness.getAllAnimals();
    }
    public ArrayList<Animal> getFicheSoinDispo() throws ErrorNull, BDConnexionError{
        ArrayList<Animal> ficheSoinDispo = new ArrayList<Animal>();
        for (Animal ficheSoin: animalBusiness.getAllAnimals()) {
            if(ficheSoin.getEtatFicheSoin() == 0) ficheSoinDispo.add(ficheSoin);
        }
        return ficheSoinDispo;
    }
}
