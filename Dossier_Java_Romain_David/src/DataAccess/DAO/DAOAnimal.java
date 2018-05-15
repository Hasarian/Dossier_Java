package DataAccess.DAO;

import Model.Animal;
import erreurs.BDConnexionError;
import erreurs.ErrorNull;
import erreurs.InexistantCareGiver;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface DAOAnimal {
    void create(Animal animal);
    Animal read(int id) throws ErrorNull, BDConnexionError,InexistantCareGiver;
    void readAllAnimals() throws ErrorNull, BDConnexionError, InexistantCareGiver;
    void update(Animal animal);
    void delete(int id);
}
