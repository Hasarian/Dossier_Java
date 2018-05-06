package DAO;

import Model.Animal;
import erreurs.BDConnexionError;
import erreurs.ErrorNull;

import java.util.ArrayList;

public interface DAOAnimal {
    void create(Animal animal);
    Animal read(int id) throws ErrorNull, BDConnexionError;
    ArrayList<Animal> readAllAnimal() throws ErrorNull, BDConnexionError;
    void update(Animal animal);
    void delete(int id);
}
