package accesDonnees;

import accesDonnees.dao.DAORechercheAnimalDates;
import erreurs.Erreur;
import modèle.*;
import erreurs.erreursExternes.DonneePermanenteErreur;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class RechercheAnimauxEntreDates implements DAORechercheAnimalDates {
    private Connection connection;

    public RechercheAnimauxEntreDates() throws DonneePermanenteErreur {
        connection = SingletonDB.getInstance();
    }

    public ArrayList<Animal> readAnimalsbetweenDates(GregorianCalendar dateDeb, GregorianCalendar dateFin) throws Erreur {
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
            new DonneePermanenteErreur(e.getMessage());
        }
        return resultatRecherche;
    }

    public Animal traductionSQL(ResultSet data) throws SQLException, Erreur
    {
        //System.out.println("on entre dans la fonction qui traduit le sql de la recherche d'animaux entre deux dates");
        String libelleEspece =  data.getString("espece.libelle");
        Boolean estEnVoieDeDisparition =  data.getBoolean("espece.estEnVoieDeDisparition");
        String typeDeplacement = data.getString("espece.typeDeplacement");
        if(data.wasNull()) typeDeplacement=null;
        String milieuDeVie = data.getString("espece.milieuDeVie");
        Espece espece = new Espece(libelleEspece, estEnVoieDeDisparition, typeDeplacement, milieuDeVie);


        String libelleRace = data.getString("race.libelle");
        String traitDeCaractere = data.getString("race.traitDeCaractere");
        String caracteristiqueDuMilieuDeVie = data.getString("race.caracteristiqueDuMilieuDeVie");
        if(data.wasNull()) caracteristiqueDuMilieuDeVie=null;
        String tare = data.getString("race.tare");
        if(data.wasNull()) tare=null;
        Race race = new Race(libelleRace, traitDeCaractere, tare, caracteristiqueDuMilieuDeVie, espece);

        Integer id =  data.getInt("ficheAnimal.id");
        String remarque = (data.wasNull() ? null : data.getString("ficheAnimal.remarque"));

        Integer numCell =  data.getInt("ficheAnimal.numCellule");
        String nom =  data.getString("ficheAnimal.nomAnimal");
        GregorianCalendar dateArrive = new GregorianCalendar();
        dateArrive.setTime(data.getDate("ficheAnimal.dateArrive"));
        Date dateDs = data.getDate("ficheAnimal.dateDeces");
        GregorianCalendar dateDeces;
        if (data.wasNull()){
            dateDeces=null;
        }else {
            dateDeces=new GregorianCalendar();
            dateDeces.setTime(dateDs);
        }
        Boolean estDangereux = data.getBoolean("ficheAnimal.estDangereux");
        Animal.EtatAnimal[] etatsAnimal = Animal.EtatAnimal.values();
        Animal.EtatSoin[] etatSoins = Animal.EtatSoin.values();
        Integer etat = data.getInt("ficheAnimal.etat");
        Animal.EtatAnimal etatAnimal = etatsAnimal[etat];
        etat = data.getInt("ficheSoin.etat");
        Animal.EtatSoin etatSoin = etatSoins[etat];
        String remarqueSoin =data.getString("ficheSoin.remarque");
        if(data.wasNull()) remarqueSoin=null;
        //ici, l'objet doit être refait(fais toi pas chier, hein, crée le direct)
        Soignant soignant;
        String mail=data.getString("fichesoin.email");
        if(data.wasNull())soignant=null;
        else soignant = new SoignantDonnees().read(mail);

        return new Animal(id,remarque,numCell,race,nom,dateArrive,dateDeces,estDangereux,etatAnimal,remarqueSoin,etatSoin,soignant);
        //System.out.println(animal);

        /*Integer id, String remarqueAnimal, Integer numCellule, Race race, String nomAnimal, GregorianCalendar dateArrive, GregorianCalendar dateDesces,
                  Boolean estDangereux, EtatAnimal etatAnimal, String remarqueSoin, EtatSoin etatFicheSoin, Soignant soignant*/
    }


}
