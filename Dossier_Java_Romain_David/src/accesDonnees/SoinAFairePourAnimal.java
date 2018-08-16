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
import java.util.Date;
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
              Integer idSoinMedical = data.getInt("soinMedical.idSoinMedical");
              if(data.wasNull()) idSoinMedical=null;
              //System.out.println(data.getInt("soinMedical.numDossier"));
              //Animal ficheSoin = new AnimalDonnees().read((data.getInt("soinMedical.numDossier")));
              Animal ficheSoin=new AnimalDonnees().resultatVersAnimal(data);


              GregorianCalendar dateObtenue = new GregorianCalendar();
              dateObtenue.setTime((data.getDate("soinMedical.dateSoinMedical")));
              if(data.wasNull()) dateObtenue=null;
              GregorianCalendar heure = new GregorianCalendar();
              Date dateHeure= data.getDate("soinMedical.heureSoinMedical");
              if(data.wasNull()) heure=null;
              else heure.setTime(dateHeure);
              String description=data.getString("soinMedical.description");
              if(data.wasNull()) description=null;
              String remarque = data.getString("soinMedical.remarque");
              if(data.wasNull()) remarque=null;
              Integer numOrdonnance = data.getInt("soinMedical.numOrdonnance");
              if(data.wasNull()) numOrdonnance=null;
              String mailVeto =data.getString("soinMedical.mailVeto");
              if(data.wasNull()) mailVeto=null;
              soinsMedicaux.add(new SoinMedical(idSoinMedical,ficheSoin,dateObtenue,heure,description,remarque,numOrdonnance,mailVeto));
          }
      }
      catch(SQLException sqlException) {
          throw new ErreurTraduction(sqlException.getMessage());
      }
      return soinsMedicaux;
    }


}
