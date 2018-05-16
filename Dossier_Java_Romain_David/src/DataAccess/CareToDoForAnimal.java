package DataAccess;

import Business.AnimalBusiness;
import Business.ListAnimalBusiness;
import Model.Animal;
import Model.CareGiver;
import Model.SoinMedical;
import Model.Veterinaire;
import erreurs.BDConnexionError;
import erreurs.ErrorNull;
import erreurs.InexistantCareGiver;
import uIController.CareGiverController;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class CareToDoForAnimal {
    private CareGiver careGiver;
    private Animal animal;
    private Veterinaire veterinaire;
    private SoinMedical soinMedical;
    private Connection connection;
    private AnimalBusiness business;
    public CareToDoForAnimal(AnimalBusiness business) throws ErrorNull, BDConnexionError, InexistantCareGiver {
        connection = SingletonDB.getInstance();
        this.business = business;
    }
    public ArrayList<SoinMedical> readCareToAnimal(int id)throws BDConnexionError, ErrorNull{
      String sql = "select*" +
              "from soignant, ficheAnimal, veto, soinMedical, ficheSoin, race, espece " +
              "where ficheAnimal.id = ? and ficheAnimal.id = ficheSoin.id and ficheSoin.email = soignant.mail and" +
              "  soinmedical.numDossier = fichesoin.id and soinmedical.mailVeto = veto.mail and ficheanimal.race=race.libelle and espece.libelle=race.espece";
      ArrayList<SoinMedical> soinMedicals = new ArrayList<SoinMedical>();
      try{
          PreparedStatement statement = connection.prepareStatement(sql);
          statement.setInt(1, id);
          ResultSet data = statement.executeQuery();

          while (data.next()){
              Integer idSoinMedical = data.getInt("soinMedical.idSoinMedical");
              System.out.println(data.getInt("soinMedical.numDossier"));
              Animal ficheSoin = business.getAnimal(data.getInt("soinMedical.numDossier"));



              GregorianCalendar date = new GregorianCalendar();
              date.setTime(data.getDate("soinMedical.dateSoinMedical"));
              GregorianCalendar heure = (data.getDate("soinMedical.heureSoinMediacl") == null) ? null : new GregorianCalendar();
              if (heure != null) heure.setTime(data.getDate("soinMedical.heureSoinMediacl"));
              String remarque = data.getString("soinMedical.remarque");
              Integer numOrdonnance = data.getInt("soinMedical.numOrdonnance");
              String mailVeto = data.getString("soinMedical.mailVeto");
              soinMedicals.add(new SoinMedical(idSoinMedical,ficheSoin,date,heure,remarque,numOrdonnance,mailVeto));
          }
      }
      catch(SQLException sqlException) {
          JOptionPane.showMessageDialog(null,sqlException.getMessage(),"Attribut obligatoir non rempli",JOptionPane.ERROR_MESSAGE);
          throw new BDConnexionError();
      }
      return soinMedicals;
    }


}
