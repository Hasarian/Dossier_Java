package Business;

import DAO.DAOAnimal;
import DataAccess.AnimalDBAccess;
import Model.Animal;
import erreurs.BDConnexionError;
import erreurs.ErrorNull;

import java.util.ArrayList;

public class AnimalBusiness {
    Animal animal;
    DAOAnimal daoAnimal;

    public AnimalBusiness() throws BDConnexionError {
        daoAnimal = new AnimalDBAccess();
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Animal getAnimal(int id) throws ErrorNull, BDConnexionError {
        return daoAnimal.read(id);
    }
    public ArrayList<Animal> getAllAnimals() throws ErrorNull, BDConnexionError{
        return daoAnimal.readAllAnimal();
    }

}
