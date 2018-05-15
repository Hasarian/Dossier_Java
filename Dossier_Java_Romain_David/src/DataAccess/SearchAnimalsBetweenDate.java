package DataAccess;

import Business.AnimalBusiness;
import Business.CareGiverBusiness;
import Business.ListAnimalBusiness;
import Business.ListEspeceBusiness;
import Model.*;
import erreurs.BDConnexionError;
import erreurs.ErrorNull;
import erreurs.InexistantCareGiver;
import uIController.CareGiverController;

import javax.tools.JavaCompiler;
import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class SearchAnimalsBetweenDate {
    Connection connection;
    AnimalBusiness animalBusiness;
    ListEspeceBusiness listeEspece;
    CareGiverBusiness careGiverBusiness;

    public SearchAnimalsBetweenDate() throws BDConnexionError,ErrorNull {
        connection = SingletonDB.getInstance();
    }

    public ArrayList<Vaccination> readAnimalsbetweenDates(GregorianCalendar dateDeb, GregorianCalendar dateFin) throws ErrorNull,BDConnexionError,InexistantCareGiver {
        ArrayList<Vaccination> resultSearch = new ArrayList<Vaccination>();
        try {
            //changer la date recherchée
            String sql = "select*\n" +
                    "    from ficheAnimal, vaccination, vaccin, fichesoin\n" +
                    "    where ficheAnimal.dateArrive > ? and ficheAnimal.dateArrive < ? and fichesoin.id = ficheanimal.id " +
                    "and vaccination.id = ficheanimal.id " +
                    "and vaccination.numVaccin = vaccin.numVaccin;";
            PreparedStatement statement = connection.prepareStatement(sql);
            java.sql.Date sqlDate = new Date(dateDeb.getTimeInMillis());
            statement.setDate(1, sqlDate);
            sqlDate = new Date(dateFin.getTimeInMillis());
            statement.setDate(2, sqlDate);
            ResultSet data = statement.executeQuery();

            while (data.next()) {
                    resultSearch.add(traductionSQL(data));
            }

        } catch (SQLException e) {
            new BDConnexionError();
        }
        return resultSearch;
    }

    public Vaccination traductionSQL(ResultSet data) throws SQLException, ErrorNull,BDConnexionError, InexistantCareGiver
    {
        listeEspece = ListEspeceBusiness.obtenirEspeceBusiness();
        careGiverBusiness = CareGiverBusiness.otebnirCareGiverBusiness();
        animalBusiness = AnimalBusiness.obtenirAnimalBusiness(ListAnimalBusiness.obtenirListAnimalBusiness(new CareGiverController()));
        String libelleEspece = (data.wasNull()) ? null : data.getString("espece.libelle");
        Boolean estEnVoieDeDisparition = (data.wasNull()) ? null : data.getBoolean("espece.estEnVoieDeDisparition");
        String typeDeplacement = (data.wasNull()) ? null : data.getString("espece.typeDeDeplacement");
        String milieuDeVie = (data.wasNull()) ? null : data.getString("espece.milieuDeVie");
        Espece espece = listeEspece.obtenirEspece(libelleEspece, estEnVoieDeDisparition, typeDeplacement, milieuDeVie);


        String libelleRace = (data.wasNull()) ? null : data.getString("race.libelle");
        String traitDeCaractere = (data.wasNull()) ? null : data.getString("race.traitDeCaractere");
        String caracteristiqueDuMilieuDeVie = (data.wasNull() ? null : data.getString("race.caracteristiqueDuMilieuDeVie"));
        String tare = (data.wasNull() ? null : data.getString("race.tare"));
        Race race = listeEspece.obtenirRace(libelleRace, traitDeCaractere, tare, caracteristiqueDuMilieuDeVie, espece);

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
        Integer etat = (data.wasNull() ? null : data.getInt("ficheAnimal.etat"));
        Animal.EtatAnimal etatAnimal = etatsAnimal[etat];
        etat = (data.wasNull() ? null : data.getInt("ficheSoin.etat"));
        Animal.EtatSoin etatSoin = etatSoins[etat];
        String remarqueSoin = (data.wasNull()) ? null : data.getString("ficheSoin.remarque");
        CareGiver careGiver = careGiverBusiness.getUserByMail(data.getString("fichesoin.mail"));

        animalBusiness.nouvelAnimalFromDB(id, remarque, numCell, nom, race, dateArrive, dateDeces, estDangereux, etatAnimal, remarqueSoin, etatSoin, careGiver);
        Animal animal = animalBusiness.getAnimal(id.toString());
        Integer numVaccin = (data.wasNull() ? null : data.getInt("vaccin.numVaccin"));
        String libelle = (data.wasNull() ? null : data.getString("vaccin.libelle"));
        Vaccin vaccin = animalBusiness.getVaccin(libelle, numVaccin);
        GregorianCalendar dateVaccination = new GregorianCalendar();
        dateVaccination.setTime(data.getDate("vaccination.date"));
        Integer numVaccination = (data.wasNull() ? null : data.getInt("vaccination.idVaccination"));

        return animalBusiness.getVaccination(animal, vaccin, dateVaccination, numVaccination);
    }


}
