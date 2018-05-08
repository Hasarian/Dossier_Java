package DataAccess;

import Business.CareGiverBusiness;
import DAO.DAOAnimal;
import Model.*;
import erreurs.BDConnexionError;
import erreurs.ErrorNull;
import sun.java2d.loops.GeneralRenderer;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class AnimalDBAccess implements DAOAnimal {
    private Connection connection;

    public AnimalDBAccess () throws BDConnexionError{
        connection = SingletonDB.getInstance();
    }

    @Override
    public Animal read(int id) throws ErrorNull, BDConnexionError {
        Animal animal = null;
        String sql = "select * from ficheSoin, ficheAnimal, espece, race where ficheSoin = ? " +
                "and ficheSoin.id = ficheAnimal.id and ficheAnimal.race = race.libelle and race.espece = espece.libelle";
        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet data = statement.executeQuery();
            animal = dataToAnimal(data);

        }

        catch(SQLException e){
            new BDConnexionError();
        }
        return animal;
    }
    public ArrayList<Animal> readAllAnimal() throws ErrorNull, BDConnexionError{

        ArrayList<Animal> animals = new ArrayList<Animal>();

        try{
            String sql = "select * from ficheSoin, ficheAnimal, espece, race where " +
                    "ficheSoin.id = ficheAnimal.id and ficheAnimal.race = race.libelle and race.espece = espece.libelle";
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet data = statement.executeQuery();
            while(data.next()) {
                animals.add(dataToAnimal(data));
            }

        }
        catch(SQLException e){
            new BDConnexionError();
        }
        return animals;
    }
    private Animal dataToAnimal(ResultSet data)throws ErrorNull, BDConnexionError{
        Animal animal = null;
        try {
            Race race;
            Espece espece;
            CareGiver careGiver;
            CareGiverBusiness careGiverBusiness;

            espece = new Espece(data.getString("espece.libelle"), data.getBoolean("espece.estEnVoieDeDisparition"),
                    data.getString("espece.typeDeDeplacement"), data.getString("espece.milieuDeVie"));

            String caracteristiqueDuMilieuDeVie = (data.wasNull() ? null : data.getString("race.caracteristiqueDuMilieuDeVie"));
            String tare = (data.wasNull() ? null : data.getString("race.tare"));
            race = new Race(data.getString("race.libelle"), data.getString("race.traitDeCaractere"),
                    tare, caracteristiqueDuMilieuDeVie, espece);


            GregorianCalendar dateArrive = new GregorianCalendar();
            GregorianCalendar dateDesces = new GregorianCalendar();

            dateArrive.setTime(data.getDate("ficheAnimal.dateArrive"));
            dateDesces.setTime(data.getDate("ficheAnimal.dateDesces"));
            Animal.EtatAnimal [] etatAnimal = Animal.EtatAnimal.values();
            Animal.EtatSoin [] etatSoins = Animal.EtatSoin.values();
            //etatSoins[data.getInt("ficheSoin.etat")];



            //il faut qu'ici on traduise les integer et String en enum, pour les envoyer à la couche business qui s'occupera de créer les objets

            animal = new Animal(data.getInt("ficheAnimal.id"), data.getString("ficheAnimal.remarque"), data.getInt("ficheAnimal.numCellule"),
                    race, data.getString("ficheAnimal.nomAnimal"), dateArrive, dateDesces, data.getBoolean("ficheAnimal.estDangereux"), etatAnimal[data.getInt("ficheAnimal.etat")],
                    data.getString("ficheSoin.remaque"), etatSoins[data.getInt("ficheSoin.etat")]);
            return animal;
        }
        catch(SQLException e){
            new BDConnexionError();
        }
        return animal;
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
    /*private Integer id;
    private String remarqueAnimal;
    private Integer numCellule;
    private String nomAnimal;
    private Race race;
    private GregorianCalendar dateArrive;
    private GregorianCalendar dateDesces;
    private Boolean estDangereux;
    private String etatAnimal;
    private String remaqueSoin;
    private Integer etatFicheSoin;*/
