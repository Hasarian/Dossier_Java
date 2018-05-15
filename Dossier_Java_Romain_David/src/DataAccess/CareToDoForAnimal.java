package DataAccess;

import Model.Animal;
import Model.CareGiver;
import Model.SoinMedical;
import Model.Veterinaire;
import erreurs.BDConnexionError;
import erreurs.ErrorNull;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CareToDoForAnimal {
    private CareGiver careGiver;
    private Animal ficheAnimal;
    private Veterinaire veterinaire;
    private SoinMedical soinMedical;
    private Connection connection;
    public CareToDoForAnimal() throws ErrorNull, BDConnexionError {
        connection = SingletonDB.getInstance();
    }
    public ArrayList<SoinMedical> readCareToAnimal(int id)throws BDConnexionError{
      String sql = "select*" +
              "from soignant, ficheAnimal, veto, soinMedical, ficheSoin" +
              "where ficheanimal.id = ? and ficheanimal.id = ficheSoin.id and ficheSoin.email = soignant.mail and" +
              "  soinmedical.numDossier = fichesoin.id and soinmedical.mailVeto = veto.mail";
      ArrayList<SoinMedical> soinMedicals = new ArrayList<SoinMedical>();
      try{
          PreparedStatement statement = connection.prepareStatement(sql);
          statement.setInt(1, id);
          ResultSet data = statement.executeQuery();

          while (data.next()){
              data.getInt("ficheSoin.id")
          }
      }
      catch(SQLException sqlException) {
          JOptionPane.showMessageDialog(null,sqlException.getMessage(),"Attribut obligatoir non rempli",JOptionPane.ERROR_MESSAGE);
          throw new BDConnexionError();
      }
    }


}
