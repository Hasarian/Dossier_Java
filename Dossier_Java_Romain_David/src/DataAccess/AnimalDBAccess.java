package DataAccess;

import Business.AnimalBusiness;
import Business.CareGiverBusiness;
import Business.ListAnimalBusiness;
import Business.ListEspeceBusiness;
import DataAccess.DAO.DAOAnimal;
import Model.*;
import erreurs.BDConnexionError;
import erreurs.ErrorNull;
import erreurs.InexistantCareGiver;
import uIController.CareGiverController;

import java.sql.*;
import java.util.GregorianCalendar;

public class AnimalDBAccess implements DAOAnimal {
    private Connection connection;
    private AnimalBusiness business;
    public AnimalDBAccess (AnimalBusiness business) throws BDConnexionError,ErrorNull{
        connection = SingletonDB.getInstance();
        this.business=business;
    }

    @Override
    public Animal read(int id) throws ErrorNull,BDConnexionError,InexistantCareGiver {
        business= AnimalBusiness.obtenirAnimalBusiness(ListAnimalBusiness.obtenirListAnimalBusiness(new CareGiverController()));
        String sql = "select * from ficheSoin, ficheAnimal, espece, race where ficheSoin = ? " +
                "and ficheSoin.id = ficheAnimal.id and ficheAnimal.race = race.libelle and race.espece = espece.libelle";
        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet data = statement.executeQuery();
            dataToAnimal(data);
            return business.getAnimal(data.getInt("ficheAnimal.id"));
        }

        catch(SQLException e){
            new BDConnexionError();
            return null;
        }
    }
    public void readAllAnimals() throws ErrorNull,BDConnexionError, InexistantCareGiver{
        try{
            String sql = "select * from ficheanimal,fichesoin,race,espece\n" +
                    "where fichesoin.id=ficheanimal.id and ficheanimal.race=race.libelle and ficheanimal.race=race.libelle and espece.libelle=race.espece; ";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet data = statement.executeQuery();
            while(data.next()) {
                dataToAnimal(data);
                //System.out.println("data suivant");
            }

        }
        catch(SQLException e){
            new BDConnexionError();
        }
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

    private void dataToAnimal(ResultSet data)throws ErrorNull,BDConnexionError, InexistantCareGiver{
        CareGiverBusiness userBusiness=CareGiverBusiness.otebnirCareGiverBusiness();
        try {
                ListEspeceBusiness listEspeceBusiness = ListEspeceBusiness.obtenirEspeceBusiness();
                //System.out.println("il est rentré");
                String libelleEspece = data.getString("espece.libelle");
                Boolean estEnVoieDeDisparition = data.getBoolean("espece.estEnVoieDeDisparition");
                String typeDeplacement =  data.getString("espece.typeDEplacement");
                String milieuDeVie =  data.getString("espece.milieuDeVie");
                //System.out.println(libelleEspece+" "+estEnVoieDeDisparition+" "+typeDeplacement+milieuDeVie);
                Espece espece = listEspeceBusiness.obtenirEspece(libelleEspece, estEnVoieDeDisparition, typeDeplacement, milieuDeVie);
                //System.out.println(libelleEspece);

                String libelleRace = (data.wasNull()) ? null : data.getString("race.libelle");
                String traitDeCaractere = (data.wasNull()) ? null : data.getString("race.traitDeCaractere");
                String caracteristiqueDuMilieuDeVie = (data.wasNull() ? null : data.getString("race.caracteristiqueDuMilieuDeVie"));
                String tare = (data.wasNull() ? null : data.getString("race.tare"));
                Race race = listEspeceBusiness.obtenirRace(libelleRace, traitDeCaractere, tare, caracteristiqueDuMilieuDeVie, espece);
                //System.out.println(libelleRace+" "+traitDeCaractere+" "+tare+" "+caracteristiqueDuMilieuDeVie+" "+espece);

                Integer id =  data.getInt("ficheAnimal.id");
                String remarque =  data.getString("ficheAnimal.remarque");
                Integer numCell =  data.getInt("ficheAnimal.numCellule");
                String nom =  data.getString("ficheAnimal.nomAnimal");
                GregorianCalendar dateArrive = new GregorianCalendar();
                dateArrive.setTime(data.getDate("ficheAnimal.dateArrive"));
                GregorianCalendar dateDesces = (data.getDate("ficheAnimal.dateDesces") == null) ? null : new GregorianCalendar();
                if (dateDesces != null) dateDesces.setTime(data.getDate("ficheAnimal.dateDesces"));
                Boolean estDangereux = data.getBoolean("ficheAnimal.estDangereux");
                //System.out.println(data.getBoolean("ficheAnimal.estDangereux"));
                Animal.EtatSoin etatSoins = Animal.EtatSoin.values()[data.getInt("ficheSoin.etat")];
                Animal.EtatAnimal etatAnimal = Animal.EtatAnimal.values()[data.getInt("ficheAnimal.etat")];

                String remarqueSoin = (data.wasNull()) ? null : data.getString("ficheSoin.remarque");
                String email = (data.wasNull()) ? null : data.getString("ficheSoin.email");

            /*(Integer id, String remarque, Integer numCell, String nomAnimal, Race race, GregorianCalendar dateArrivee,
                               GregorianCalendar dateDeces, Boolean estDangereux, Animal.EtatAnimal etatAnimal, Animal.EtatSoin etatSoin,
                               String remarqueSoin, Animal.EtatSoin etatFicheSoin, CareGiver careGiver*/
                //System.out.println(nom);
                business.nouvelAnimalFromDB(id, remarque, numCell, nom, race, dateArrive, dateDesces, estDangereux, etatAnimal, remarqueSoin, etatSoins, userBusiness.getUserByMail(email));

        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            new BDConnexionError();
        }
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
