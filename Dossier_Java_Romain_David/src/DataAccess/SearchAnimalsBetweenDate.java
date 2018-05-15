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
    ListAnimalBusiness listAnimalBusiness;

    public SearchAnimalsBetweenDate() throws BDConnexionError,ErrorNull {
        connection = SingletonDB.getInstance();
    }

    public ArrayList<Animal> readAnimalsbetweenDates(GregorianCalendar dateDeb, GregorianCalendar dateFin) throws ErrorNull,BDConnexionError,InexistantCareGiver {
        ArrayList<Animal> resultSearch = new ArrayList<Animal>();
        try {

            String sql = "select*" +
                    "    from ficheAnimal, race, espece, fichesoin" +
                    "    where  fichesoin.id = ficheanimal.id " +
                    "and race.libelle = ficheanimal.race " +
                    "and race.espece = espece.libelle;";
            //ficheAnimal.dateArrive > ? and ficheAnimal.dateArrive < ? and
            PreparedStatement statement = connection.prepareStatement(sql);
            java.sql.Date dateSQL1 = new java.sql.Date(dateDeb.getTimeInMillis());

            /*statement.setDate(1, dateSQL1);
            java.sql.Date dateSQL2 = new java.sql.Date(dateFin.getTimeInMillis());
            statement.setDate(2, dateSQL2);*/
            ResultSet data = statement.executeQuery();

            while (data.next()) {
                System.out.println("0-");
                    resultSearch.add(traductionSQL(data));
            }

        } catch (SQLException e) {
            new BDConnexionError();
        }
        return resultSearch;
    }

    public Animal traductionSQL(ResultSet data) throws SQLException, ErrorNull,BDConnexionError, InexistantCareGiver
    {
        System.out.println("ici");
        AnimalBusiness business = AnimalBusiness.obtenirAnimalBusiness(listAnimalBusiness);
        listeEspece = ListEspeceBusiness.obtenirEspeceBusiness();
        careGiverBusiness = CareGiverBusiness.otebnirCareGiverBusiness();
        animalBusiness = AnimalBusiness.obtenirAnimalBusiness(ListAnimalBusiness.obtenirListAnimalBusiness(new CareGiverController()));
        String libelleEspece =  data.getString("espece.libelle");
        Boolean estEnVoieDeDisparition =  data.getBoolean("espece.estEnVoieDeDisparition");
        String typeDeplacement =  data.getString("espece.typeDeplacement");
        String milieuDeVie =  data.getString("espece.milieuDeVie");
        Espece espece = listeEspece.obtenirEspece(libelleEspece, estEnVoieDeDisparition, typeDeplacement, milieuDeVie);


        String libelleRace =  data.getString("race.libelle");
        String traitDeCaractere =  data.getString("race.traitDeCaractere");
        String caracteristiqueDuMilieuDeVie = data.getString("race.caracteristiqueDuMilieuDeVie");
        String tare = (data.wasNull() ? null : data.getString("race.tare"));
        Race race = listeEspece.obtenirRace(libelleRace, traitDeCaractere, tare, caracteristiqueDuMilieuDeVie, espece);

        Integer id =  data.getInt("ficheAnimal.id");
        String remarque = (data.wasNull()) ? null : data.getString("ficheAnimal.remarque");
        Integer numCell =  data.getInt("ficheAnimal.numCellule");
        String nom = data.getString("ficheAnimal.nomAnimal");
        GregorianCalendar dateArrive = new GregorianCalendar();
        dateArrive.setTime(data.getDate("ficheAnimal.dateArrive"));
        GregorianCalendar dateDesces = (data.getDate("ficheAnimal.dateDesces") == null) ? null : new GregorianCalendar();
        if (dateDesces != null) dateDesces.setTime(data.getDate("ficheAnimal.dateDesces"));
        Boolean estDangereux = data.getBoolean("ficheAnimal.estDangereux");
        Animal.EtatAnimal[] etatsAnimal = Animal.EtatAnimal.values();
        Animal.EtatSoin[] etatSoins = Animal.EtatSoin.values();
        Integer etat = data.getInt("ficheAnimal.etat");
        Animal.EtatAnimal etatAnimal = etatsAnimal[etat];
        etat = data.getInt("ficheSoin.etat");
        Animal.EtatSoin etatSoin = etatSoins[etat];
        String remarqueSoin = (data.wasNull() ? null : data.getString("ficheSoin.remarque"));
        CareGiver careGiver = careGiverBusiness.getUserByMail(data.getString("fichesoin.email"));

        animalBusiness.nouvelAnimalFromDB(id, remarque, numCell, nom, race, dateArrive, dateDesces, estDangereux, etatAnimal, remarqueSoin, etatSoin, careGiver);
        Animal animal = animalBusiness.getAnimal(id);
        System.out.println(animal);
        return animal;

    }


}
