package accesDonnees;

import business.AnimalBusiness;
import accesDonnees.dao.DAORechercheSoinAFaire;
import erreurs.Erreur;
import erreurs.erreursExternes.ErreurTraduction;
import modèle.Animal;
import modèle.SoinMedical;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class SoinAFairePourAnimal implements DAORechercheSoinAFaire{
   /* private Soignant soignant;
    private Animal animal;
    private Veterinaire veterinaire;
    private SoinMedical soinMedical;*/
    private Connection connection;
    public SoinAFairePourAnimal(AnimalBusiness business) throws Erreur {
        connection = SingletonDB.getInstance();
    }
    public ArrayList<SoinMedical> readCareToAnimal(int id,GregorianCalendar date)throws Erreur {
      String sql = "select*" +
              "from ficheAnimal, veto, soinMedical, ficheSoin, race, espece " +
              "where ficheAnimal.id = ? and ficheAnimal.id = ficheSoin.id and" +
              "  soinmedical.numDossier = fichesoin.id and soinmedical.mailVeto = veto.mail and ficheanimal.race=race.libelle and espece.libelle=race.espece";
      //sql+=(date!=null)? "and soinMedical.dateSoinMedical=?":"";
      ArrayList<SoinMedical> soinsMedicaux = new ArrayList<SoinMedical>();
      try{
          PreparedStatement statement = connection.prepareStatement(sql);
          statement.setInt(1, id);
          //statement.setTime(2,new java.sql.Time(date.getTimeInMillis()));
          ResultSet data = statement.executeQuery();

          while (data.next()){
              Integer idSoinMedical = (data.wasNull())?null : data.getInt("soinMedical.idSoinMedical");
              //System.out.println(data.getInt("soinMedical.numDossier"));
              Animal ficheSoin = new AnimalDonnees().read((data.getInt("soinMedical.numDossier")));



              GregorianCalendar dateObtenue = new GregorianCalendar();
              dateObtenue.setTime((data.wasNull())?null : data.getDate("soinMedical.dateSoinMedical"));
              GregorianCalendar heure = (data.wasNull())?null :  new GregorianCalendar();
              if (heure != null) heure.setTime(data.getDate("soinMedical.heureSoinMedical"));
              String description=(data.wasNull())?null:data.getString("soinMedical.description");
              String remarque = (data.wasNull())?null : data.getString("soinMedical.remarque");
              Integer numOrdonnance = (data.wasNull())?null : data.getInt("soinMedical.numOrdonnance");
              String mailVeto =(data.wasNull())?null :  data.getString("soinMedical.mailVeto");
              soinsMedicaux.add(new SoinMedical(idSoinMedical,ficheSoin,dateObtenue,heure,description,remarque,numOrdonnance,mailVeto));
          }
      }
      catch(SQLException sqlException) {
          throw new ErreurTraduction(sqlException.getMessage());
      }
      return soinsMedicaux;
    }


}
