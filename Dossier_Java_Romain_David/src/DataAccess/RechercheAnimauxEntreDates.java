package DataAccess;

import Business.AnimalBusiness;
import Business.SoignantBusiness;
import Business.ListeAnimalBusiness;
import Business.ListeEspecebusiness;
import Model.*;
import erreurs.BDConnexionErreur;
import erreurs.ErreurrNull;
import erreurs.SoignantInexistant;
import uIController.SoignantController;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class RechercheAnimauxEntreDates {
    Connection connection;
    AnimalBusiness animalBusiness;
    ListeEspecebusiness listeEspece;
    SoignantBusiness soignantBusiness;

    public RechercheAnimauxEntreDates() throws BDConnexionErreur {
        connection = SingletonDB.getInstance();
    }

    public ArrayList<Animal> readAnimalsbetweenDates(GregorianCalendar dateDeb, GregorianCalendar dateFin) throws ErreurrNull, BDConnexionErreur, SoignantInexistant {
        ArrayList<Animal> resultatRecherche = new ArrayList<Animal>();
        try {

            String sql = "select*" +
                    "    from ficheAnimal, race, espece, fichesoin" +
                    "    where ficheAnimal.dateArrive > ? and ficheAnimal.dateArrive < ? and fichesoin.id = ficheanimal.id " +
                    "and race.libelle = ficheanimal.race " +
                    "and race.espece = espece.libelle;";
            PreparedStatement statement = connection.prepareStatement(sql);
            java.sql.Date dateSQL1 = new java.sql.Date(dateDeb.getTimeInMillis());

            statement.setDate(1, dateSQL1);
            java.sql.Date dateSQL2 = new java.sql.Date(dateFin.getTimeInMillis());
            statement.setDate(2, dateSQL2);
            ResultSet data = statement.executeQuery();

            while (data.next()) {
                    resultatRecherche.add(traductionSQL(data));
            }

        } catch (SQLException e) {
            new BDConnexionErreur(e.getMessage());
        }
        return resultatRecherche;
    }

    public Animal traductionSQL(ResultSet data) throws SQLException, ErreurrNull, BDConnexionErreur, SoignantInexistant
    {
        //System.out.println("on entre dans la fonction qui traduit le sql de la recherche d'animaux entre deux dates");
        listeEspece = ListeEspecebusiness.obtenirEspeceBusiness();
        soignantBusiness = SoignantBusiness.otebnirSoignantBusiness();
        animalBusiness = AnimalBusiness.obtenirAnimalBusiness(ListeAnimalBusiness.obtenirListAnimalBusiness(new SoignantController()));
        String libelleEspece =(data.wasNull())?null :  data.getString("espece.libelle");
        Boolean estEnVoieDeDisparition = (data.wasNull())?null : data.getBoolean("espece.estEnVoieDeDisparition");
        String typeDeplacement = (data.wasNull())?null : data.getString("espece.typeDeplacement");
        String milieuDeVie =(data.wasNull())?null :  data.getString("espece.milieuDeVie");
        Espece espece = listeEspece.obtenirEspece(libelleEspece, estEnVoieDeDisparition, typeDeplacement, milieuDeVie);


        String libelleRace = (data.wasNull())?null : data.getString("race.libelle");
        String traitDeCaractere = (data.wasNull())?null : data.getString("race.traitDeCaractere");
        String caracteristiqueDuMilieuDeVie = (data.wasNull())?null : data.getString("race.caracteristiqueDuMilieuDeVie");
        String tare = (data.wasNull() ? null : data.getString("race.tare"));
        Race race = listeEspece.obtenirRace(libelleRace, traitDeCaractere, tare, caracteristiqueDuMilieuDeVie, espece);

        Integer id = (data.wasNull())?null : data.getInt("ficheAnimal.id");
        String remarque = (data.wasNull()) ? null : data.getString("ficheAnimal.remarque");
        Integer numCell = (data.wasNull())?null : data.getInt("ficheAnimal.numCellule");
        String nom = (data.wasNull())?null : data.getString("ficheAnimal.nomAnimal");
        GregorianCalendar dateArrive = new GregorianCalendar();
        dateArrive.setTime((data.wasNull())?null :data.getDate("ficheAnimal.dateArrive"));
        GregorianCalendar dateDeces = (data.wasNull())?null  : new GregorianCalendar();
        if (dateDeces != null) dateDeces.setTime(data.getDate("ficheAnimal.dateDeces"));
        Boolean estDangereux =(data.wasNull())?null : data.getBoolean("ficheAnimal.estDangereux");
        Animal.EtatAnimal[] etatsAnimal = Animal.EtatAnimal.values();
        Animal.EtatSoin[] etatSoins = Animal.EtatSoin.values();
        Integer etat =(data.wasNull())?null : data.getInt("ficheAnimal.etat");
        Animal.EtatAnimal etatAnimal =(etat==null)?null: etatsAnimal[etat];
        etat =(data.wasNull())?null :  data.getInt("ficheSoin.etat");
        Animal.EtatSoin etatSoin =(etat==null)?null: etatSoins[etat];
        String remarqueSoin = (data.wasNull() ? null : data.getString("ficheSoin.remarque"));

        //ici, l'objet doit être refait(fais toi pas chier, hein, crée le direct)
        Soignant soignant = soignantBusiness.getUtilisateurParMail((data.wasNull())?null : data.getString("fichesoin.email"));

        animalBusiness.nouvelAnimalDeLaDB(id, remarque, numCell, nom, race, dateArrive, dateDeces, estDangereux, etatAnimal, remarqueSoin, etatSoin, soignant);
        Animal animal = animalBusiness.getAnimal(id);
        //System.out.println(animal);
        return animal;

    }


}
