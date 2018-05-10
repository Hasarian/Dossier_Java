package DataAccess;

import Business.AnimalBusiness;
import Business.ListAnimalBusiness;
import Model.*;
import erreurs.BDConnexionError;
import erreurs.ErrorNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class SearchAnimalsBetweenDate {
    Connection connection;
    AnimalBusiness animalBusiness;
    public SearchAnimalsBetweenDate() throws BDConnexionError {
        connection = SingletonDB.getInstance();
    }
    public ArrayList<SearchAnimalBetweenDate> readAnimalsbetweenDates() throws BDConnexionError, ErrorNull{
        ArrayList<SearchAnimalBetweenDate> resultSearch = new ArrayList<SearchAnimalBetweenDate>();
        try {
            String sql = "select*\n" +
                    "    from ficheAnimal, vaccination, vaccin, fichesoin\n" +
                    "    where ficheAnimal.id = ? and fichesoin.id = ficheanimal.id and vaccination.id = ficheanimal.id " +
                    "and vaccination.numVaccin = vaccin.numVaccin;";
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet data = statement.executeQuery();
            while (data.next()) {
                resultSearch.add(dataToAnimal(data));
            }

        } catch (SQLException e) {
            new BDConnexionError();
        }
        return resultSearch;
    }
    private SearchAnimalBetweenDate dataToAnimal(ResultSet data) throws ErrorNull, BDConnexionError {
        SearchAnimalBetweenDate result = null;
        try {
            String libelleEspece = (data.wasNull()) ? null : data.getString("espece.libelle");
            Boolean estEnVoieDeDisparition = (data.wasNull()) ? null : data.getBoolean("espece.estEnVoieDeDisparition");
            String typeDeplacement = (data.wasNull()) ? null : data.getString("espece.typeDeDeplacement");
            String milieuDeVie = (data.wasNull()) ? null : data.getString("espece.milieuDeVie");
            Espece espece = AnimalBusiness.getEspece(libelleEspece, estEnVoieDeDisparition, typeDeplacement, milieuDeVie);


            String libelleRace = (data.wasNull()) ? null : data.getString("race.libelle");
            String traitDeCaractere = (data.wasNull()) ? null : data.getString("race.traitDeCaractere");
            String caracteristiqueDuMilieuDeVie = (data.wasNull() ? null : data.getString("race.caracteristiqueDuMilieuDeVie"));
            String tare = (data.wasNull() ? null : data.getString("race.tare"));
            Race race = AnimalBusiness.getRace(libelleRace, traitDeCaractere, tare, caracteristiqueDuMilieuDeVie, espece);

            Integer id = (data.wasNull()) ? null : data.getInt("ficheAnimal.id");
            String remarque = (data.wasNull()) ? null : data.getString("ficheAnimal.remarque");
            Integer numCell = (data.wasNull()) ? null : data.getInt("ficheAnimal.numCell");
            String nom = (data.wasNull()) ? null : data.getString("ficheAnimal.nomAnimal");
            GregorianCalendar dateArrive = new GregorianCalendar();
            GregorianCalendar dateDeces = new GregorianCalendar();
            dateArrive.setTime(data.getDate("ficheAnimal.dateArrive"));
            dateDeces.setTime(data.getDate("ficheAnimal.dateDesces"));
            Boolean estDangereux = (data.wasNull()) ? null : data.getBoolean("ficheAnimal.estDangereux");
            Animal.EtatAnimal[] etatsAnimal = Animal.EtatAnimal.values();
            Animal.EtatSoin[] etatSoins = Animal.EtatSoin.values();
            Integer etat = (data.wasNull()? null : data.getInt("ficheAnimal.etat"));
            Animal.EtatAnimal etatAnimal = etatsAnimal[etat];
            etat = (data.wasNull()? null : data.getInt("ficheSoin.etat"));
            Animal.EtatSoin etatSoin = etatSoins[etat];
            String remarqueSoin = (data.wasNull()) ? null : data.getString("ficheSoin.remarque");


            Animal animal = AnimalBusiness.getAnimal(id, remarque,numCell,nom,race, dateArrive,
                     dateDeces, estDangereux,etatAnimal,remarqueSoin, etatSoin,null);
            Integer numVaccin = (data.wasNull()? null : data.getInt("vaccin.numVaccin"));
            String libelle = (data.wasNull()? null : data.getString("vaccin.libelle"));
            Vaccin vaccin = AnimalBusiness.getVaccin(libelle,numVaccin);
            GregorianCalendar dateVaccination = new GregorianCalendar();
            dateVaccination.setTime(data.getDate("vaccination.date"));
            Integer numVaccination = (data.wasNull()? null : data.getInt("vaccination.idVaccination"));
            Vaccination vaccination = AnimalBusiness.getVaccination(animal, vaccin,dateVaccination, numVaccination);
            result = new SearchAnimalBetweenDate(animal,vaccin,vaccination,race,espece);
        }
        catch (SQLException e) {
            new BDConnexionError();
        }
        return result;
    }
}
