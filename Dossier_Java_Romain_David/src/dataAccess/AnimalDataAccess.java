package dataAccess;

import dataAccess.dao.DAOAnimal;
import model.*;
import erreurs.DonneePermanenteErreur;
import erreurs.ErreurrNull;
import erreurs.SoignantInexistant;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class AnimalDataAccess implements DAOAnimal {
    private Connection connection;
    public AnimalDataAccess() throws DonneePermanenteErreur{
        connection = SingletonDB.getInstance();
    }

    @Override
    public Animal read(int id) throws ErreurrNull, DonneePermanenteErreur, SoignantInexistant {
        //business= AnimalBusiness.obtenirAnimalBusiness(ListeAnimalBusiness.obtenirListAnimalBusiness(new SoignantController()));
        String sql = "select * from ficheSoin, ficheAnimal, espece, race where ficheSoin.id = ? " +
                "and ficheSoin.id = ficheAnimal.id and ficheAnimal.race = race.libelle and race.espece = espece.libelle";
        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet data = statement.executeQuery();
            return resultatVersAnimal(data);
        }

        catch(SQLException e){
            new DonneePermanenteErreur(e.getMessage());
            return null;
        }
    }
    public ArrayList<Animal> readTousLesAnimaux() throws ErreurrNull, DonneePermanenteErreur, SoignantInexistant {
        ArrayList<Animal> animaux=new ArrayList<Animal>();
        try{
            String sql = "select * from ficheanimal,fichesoin,race,espece\n" +
                    "where fichesoin.id=ficheanimal.id and ficheanimal.race=race.libelle and espece.libelle=race.espece; ";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet data = statement.executeQuery();
            while(data.next()) {
               animaux.add(resultatVersAnimal(data));
                //System.out.println("data suivant");
            }
            return animaux;
        }
        catch(SQLException e){
            throw  new DonneePermanenteErreur(e.getMessage());
        }
    }
    @Override
    public void updateEtat(Animal animal) throws DonneePermanenteErreur {
            String sql="update ficheSoin set etat=?,email=? where id=?";
            try{
                PreparedStatement statement=connection.prepareStatement(sql);
                int etat=animal.getEtatFicheSoin().ordinal();
                statement.setInt(1,etat);
                String mail=(animal.getSoignant()!=null)?animal.getSoignant().getMail():null;
                statement.setString(2,mail);
                statement.setInt(3,animal.getId());
                statement.execute();
            }
            catch (SQLException excpt)
            {
                throw new DonneePermanenteErreur(excpt.getMessage());
            }

    }

    private Animal resultatVersAnimal(ResultSet data)throws ErreurrNull, DonneePermanenteErreur, SoignantInexistant {
        try {
                //System.out.println("il est rentré dans le bloc de tracution dans l'animal db access");
                String libelleEspece = data.getString("espece.libelle");
                Boolean estEnVoieDeDisparition = data.getBoolean("espece.estEnVoieDeDisparition");
                String typeDeplacement =  data.getString("espece.typeDEplacement");
                String milieuDeVie = data.getString("espece.milieuDeVie");
                //System.out.println(libelleEspece+" "+estEnVoieDeDisparition+" "+typeDeplacement+milieuDeVie);
                Espece espece =new Espece(libelleEspece, estEnVoieDeDisparition, typeDeplacement, milieuDeVie);
                //System.out.println(libelleEspece);

                String libelleRace =  data.getString("race.libelle");
                String traitDeCaractere = data.getString("race.traitDeCaractere");
                String caracteristiqueDuMilieuDeVie = (data.wasNull() ? null : data.getString("race.caracteristiqueDuMilieuDeVie"));
                String tare = (data.wasNull() ? null : data.getString("race.tare"));
                Race race = new Race(libelleRace, traitDeCaractere, tare, caracteristiqueDuMilieuDeVie, espece);
                //System.out.println(libelleRace+" "+traitDeCaractere+" "+tare+" "+caracteristiqueDuMilieuDeVie+" "+espece);

                Integer id =  data.getInt("ficheAnimal.id");
                String remarque = (data.wasNull())?null : data.getString("ficheAnimal.remarque");
                Integer numCell = (data.wasNull())?null : data.getInt("ficheAnimal.numCellule");
                String nom = data.getString("ficheAnimal.nomAnimal");
                GregorianCalendar dateArrive = new GregorianCalendar();
                dateArrive.setTime((data.wasNull())?null :data.getDate("ficheAnimal.dateArrive"));
                GregorianCalendar dateDeces = null;
                Object tempDate = data.getDate("ficheAnimal.dateDeces");

                if(tempDate != null){


                    dateDeces = new GregorianCalendar();
                    dateDeces.setTime(data.getDate("ficheAnimal.dateDeces"));
                }
                Boolean estDangereux = data.getBoolean("ficheAnimal.estDangereux");
                //System.out.println(data.getBoolean("ficheAnimal.estDangereux"));
                Animal.EtatSoin etatSoins = Animal.EtatSoin.values()[data.getInt("ficheSoin.etat")];
                Animal.EtatAnimal etatAnimal = Animal.EtatAnimal.values()[data.getInt("ficheAnimal.etat")];

                String remarqueSoin = (data.wasNull()) ? null : data.getString("ficheSoin.remarque");
                String email = data.getString("ficheSoin.email");

            /*(Integer id, String remarque, Integer numCell, String nomAnimal, Race race, GregorianCalendar dateArrivee,
                               GregorianCalendar dateDeces, Boolean estDangereux, Animal.EtatAnimal etatAnimal, Animal.EtatSoin etatSoin,
                               String remarqueSoin, Animal.EtatSoin etatFicheSoin, Soignant careGiver*/
                //System.out.println(nom);
                return new Animal(id, remarque, numCell,  race,nom, dateArrive, dateDeces, estDangereux, etatAnimal, remarqueSoin, etatSoins, (email==null)?null:new SoignantDataAccess().read(email));

        }
        catch(SQLException e){
            //System.out.println(e.getMessage());
            throw new DonneePermanenteErreur(e.getMessage());
        }
    }
}



    /*private Integer id;
    private String remarqueAnimal;
    private Integer numCellule;
    private String nomAnimal;
    private Race race;
    private GregorianCalendar dateArrive;
    private GregorianCalendar dateDeces;
    private Boolean estDangereux;
    private String etatAnimal;
    private String remaqueSoin;
    private Integer etatFicheSoin;*/
