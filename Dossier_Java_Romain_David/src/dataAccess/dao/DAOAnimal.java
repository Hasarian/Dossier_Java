package dataAccess.dao;

import erreurs.Erreur;
import model.Animal;

import java.util.ArrayList;

public interface DAOAnimal {
    Animal read(Integer id) throws Erreur;
    ArrayList<Animal> readTousLesAnimaux() throws Erreur;
    void updateEtat(Animal animal) throws Erreur;
}
