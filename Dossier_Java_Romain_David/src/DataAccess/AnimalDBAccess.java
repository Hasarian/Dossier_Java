package DataAccess;

import DAO.DAOAnimal;
import Model.Animal;

import java.sql.Connection;

public class AnimalDBAccess implements DAOAnimal {
    private Connection connection;

    public AnimalDBAccess(){
        connection = SingletonDB.getInstance();
    }

    @Override
    public Animal read(int id) {
        return null;
    }

    @Override
    public void create(Animal animal) {

    }

    @Override
    public void update(Animal animal) {

    }

    @Override
    public void delete(int id) {

    }
}
