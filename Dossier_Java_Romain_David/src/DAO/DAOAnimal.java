package DAO;

import Model.Animal;

public interface DAOAnimal {
    void create(Animal animal);
    Animal read(int id);
    void update(Animal animal);
    void delete(int id);
}
