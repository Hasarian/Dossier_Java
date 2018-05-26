package DataAccess;

import Business.AnimalBusiness;
import DataAccess.DAO.DAORechercheSoinAFaire;
import Model.Animal;
import Model.SoinMedical;
import erreurs.BDConnexionErreur;
import erreurs.ErreurrNull;

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
    private AnimalBusiness business;
    public SoinAFairePourAnimal(AnimalBusiness business) throws  BDConnexionErreur {
        connection = SingletonDB.getInstance();
        this.business = business;
    }
    public ArrayList<SoinMedical> readCareToAnimal(int id)throws BDConnexionErreur, ErreurrNull {
      String sql = "select*" +
              "from ficheAnimal, veto, soinMedical, ficheSoin, race, espece " +
              "where ficheAnimal.id = ? and ficheAnimal.id = ficheSoin.id and" +
              "  soinmedical.numDossier = fichesoin.id and soinmedical.mailVeto = veto.mail and ficheanimal.race=race.libelle and espece.libelle=race.espece";
      ArrayList<SoinMedical> soinsMedicaux = new ArrayList<SoinMedical>();
      try{
          PreparedStatement statement = connection.prepareStatement(sql);
          statement.setInt(1, id);
          ResultSet data = statement.executeQuery();

          while (data.next()){
              Integer idSoinMedical = (data.wasNull())?null : data.getInt("soinMedical.idSoinMedical");
              //System.out.println(data.getInt("soinMedical.numDossier"));
              Animal ficheSoin = business.getAnimal((data.wasNull())?null : data.getInt("soinMedical.numDossier"));



              GregorianCalendar date = new GregorianCalendar();
              date.setTime((data.wasNull())?null : data.getDate("soinMedical.dateSoinMedical"));
              GregorianCalendar heure = (data.wasNull())?null :  new GregorianCalendar();
              if (heure != null) heure.setTime(data.getDate("soinMedical.heureSoinMediacl"));
              String description=(data.wasNull())?null:data.getString("soinMedical.description");
              String remarque = (data.wasNull())?null : data.getString("soinMedical.remarque");
              Integer numOrdonnance = (data.wasNull())?null : data.getInt("soinMedical.numOrdonnance");
              String mailVeto =(data.wasNull())?null :  data.getString("soinMedical.mailVeto");
              soinsMedicaux.add(new SoinMedical(idSoinMedical,ficheSoin,date,heure,description,remarque,numOrdonnance,mailVeto));
          }
      }
      catch(SQLException sqlException) {
          throw new BDConnexionErreur(sqlException.getMessage());
      }
      return soinsMedicaux;
    }


}
