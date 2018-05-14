package DataAccess;

import Business.ListAnimalBusiness;
import Business.CareGiverBusiness;
import Business.ListEspeceBusiness;
import DataAccess.DAO.DAOAnimal;
import Model.*;
import erreurs.BDConnexionError;
import erreurs.ErrorNull;

import java.sql.*;
import java.util.GregorianCalendar;

public class AnimalDBAccess implements DAOAnimal {
    private Connection connection;
    private CareGiverBusiness user;
    public AnimalDBAccess () throws BDConnexionError{
        connection = SingletonDB.getInstance();
    }
    public AnimalDBAccess(CareGiverBusiness user)throws BDConnexionError{
        this();
        this.user = user;
    }

    @Override
    public Animal read(int id) throws ErrorNull {
        String sql = "select * from ficheSoin, ficheAnimal, espece, race where ficheSoin = ? " +
                "and ficheSoin.id = ficheAnimal.id and ficheAnimal.race = race.libelle and race.espece = espece.libelle";
        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet data = statement.executeQuery();
            return dataToAnimal(data);

        }

        catch(SQLException e){
            new BDConnexionError();
            return null;
        }
    }
    public void readAllAnimals() throws ErrorNull{


        try{
            String sql = "select * from ficheSoin, ficheAnimal, espece, race where " +
                    "ficheSoin.id = ficheAnimal.id and ficheAnimal.race = race.libelle and race.espece = espece.libelle";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet data = statement.executeQuery();
            while(data.next()) {
                dataToAnimal(data);
            }

        }
        catch(SQLException e){
            new BDConnexionError();
        }
    }
    private Animal dataToAnimal(ResultSet data)throws ErrorNull{
        ListAnimalBusiness business=ListAnimalBusiness.obtenirAnimalBusiness();
        try {
            ListEspeceBusiness listEspeceBusiness =ListEspeceBusiness.obtenirEspeceBusiness();

            //System.out.println(data.getString("espece.libelle"));
            String libelleEspece=(data.wasNull())?null:data.getString("espece.libelle");
            Boolean estEnVoieDeDisparition=(data.wasNull())?null:data.getBoolean("espece.estEnVoieDeDisparition");
            String typeDeplacement=(data.wasNull())?null:data.getString("espece.typeDEDeplacement");
            String milieuDeVie=(data.wasNull())?null:data.getString("espece.milieuDeVie");
            Espece espece= listEspeceBusiness.obtenirEspece(libelleEspece,estEnVoieDeDisparition,typeDeplacement,milieuDeVie);


            String libelleRace=(data.wasNull())?null:data.getString("race.libelle");
            String traitDeCaractere=(data.wasNull())?null:data.getString("race.traitDeCaractere");
            String caracteristiqueDuMilieuDeVie = (data.wasNull() ? null : data.getString("race.caracteristiqueDuMilieuDeVie"));
            String tare = (data.wasNull() ? null : data.getString("race.tare"));
            Race race= listEspeceBusiness.obtenirRace(libelleRace,traitDeCaractere,tare,caracteristiqueDuMilieuDeVie,espece);

            Integer id = (data.wasNull())?null : data.getInt("ficheAnimal.id");
            String remarque=(data.wasNull())?null:data.getString("ficheAnimal.remarque");
            Integer numCell = (data.wasNull())?null: data.getInt("ficheAnimal.numCell");
            String nom=(data.wasNull())?null:data.getString("ficheAnimal.nomAnimal");
            GregorianCalendar dateArrive = new GregorianCalendar();
            GregorianCalendar dateDesces = new GregorianCalendar();
            dateArrive.setTime(data.getDate("ficheAnimal.dateArrive"));
            dateDesces.setTime(data.getDate("ficheAnimal.dateDesces"));
            Boolean estDangereux=(data.wasNull())?null:data.getBoolean("ficheAnimal.estDangereux");
            Animal.EtatSoin etatSoins = Animal.EtatSoin.values()[data.getInt("ficheSoin.etat")];
            Animal.EtatAnimal etatAnimal=Animal.EtatAnimal.values()[data.getInt("ficheAnimal.etat")];

            String remarqueSoin=(data.wasNull())?null:data.getString("ficheSoin.remarque");
            String email=(data.wasNull())?null:data.getString("ficheSoin.email");

            /*(Integer id, String remarque, Integer numCell, String nomAnimal, Race race, GregorianCalendar dateArrivee,
                               GregorianCalendar dateDeces, Boolean estDangereux, Animal.EtatAnimal etatAnimal, Animal.EtatSoin etatSoin,
                               String remarqueSoin, Animal.EtatSoin etatFicheSoin, CareGiver careGiver*/
            return business.ajoutAnimal(id,remarque,numCell,nom,race,dateArrive,dateDesces,estDangereux,etatAnimal,remarqueSoin,etatSoins,user.getUserByMail(email));

        }
        catch(SQLException e){
            new BDConnexionError();
            return null;
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
