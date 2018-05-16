package DataAccess;

import Business.AnimalBusiness;
import Business.CareGiverBusiness;
import Business.ListAnimalBusiness;
import DataAccess.DAO.DAOsoinEffectue;
import Model.Animal;
import Model.SoinEffectue;
import Model.SoinMedical;
import erreurs.BDConnexionError;
import erreurs.ErrorNull;
import erreurs.InexistantCareGiver;
import uIController.CareGiverController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CareByCareGiver
implements DAOsoinEffectue
{
    private Connection connection;
    private CareGiverBusiness giverBusiness;
    public CareByCareGiver() throws BDConnexionError
    {
        connection=SingletonDB.getInstance();
        giverBusiness=CareGiverBusiness.otebnirCareGiverBusiness();
    }
    @Override
    public ArrayList<SoinEffectue> searchHistory(String mail) throws BDConnexionError,ErrorNull,InexistantCareGiver {
        String sql = "select *from soignant, localite, soineffectue, soinmedical\n" +
                "where soignant.mail = ? and soignant.localite = localite.idLocalite\n" +
                "and soineffectue.mail = soignant.mail and soineffectue.numSoinMedical = soinmedical.idSoinMedical; ";
        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet data = statement.executeQuery();
            ArrayList<SoinEffectue> soinsEffectués=new ArrayList<SoinEffectue>();
            while(data.next())
            {
                soinsEffectués.add(traductionSQL(data));
            }
            return soinsEffectués;
        }

        catch(SQLException e){
            new BDConnexionError();
            return null;
        }
    }

    @Override
    public void create(String mailCareGiver, GregorianCalendar heureEffectuee, Integer soinMedical, String remarque) {

    }

    public SoinEffectue traductionSQL(ResultSet data) throws ErrorNull,BDConnexionError, InexistantCareGiver
    {
        try {
            AnimalBusiness animalBusiness=AnimalBusiness.obtenirAnimalBusiness(ListAnimalBusiness.obtenirListAnimalBusiness(new CareGiverController()));
            String mail = data.getString("soignant.mail");
            /*GregorianCalendar dateArrive = new GregorianCalendar();
                dateArrive.setTime(data.getDate("ficheAnimal.dateArrive"));*/
            GregorianCalendar dateSoin=new GregorianCalendar();
            dateSoin.setTime(data.getTime("soinEffectue.dateSoin"));
            Integer idSoineffectue=new Integer(data.getInt("soinEffectue.idSoinEffectue"));
            String remarque=data.getString("soinEffectue.remarque");

            Integer idSoinMedical=new Integer(data.getInt("soinMedical.idSoinMedical"));
            Animal animal=animalBusiness.getAnimalFromBD(new Integer(data.getInt("soinmedical.numdossier")));
            GregorianCalendar dateSoinMedical=new GregorianCalendar();
            dateSoinMedical.setTime(data.getDate("soinMedical.dateSoinMedical"));//data.getDate("soinMdecial.dateSoinMedical")
            GregorianCalendar heureSoin=new GregorianCalendar();
            heureSoin.setTime(data.getDate("soinMedical.heureSoinMediacl"));
            String remarqueSoin=data.getString("soinMedical.remarque");
            Integer numOrdonnance=new Integer(data.getInt("soinMedical.numOrdonnance"));
            String mailVeto = data.getString("soinMedical.mailVeto");
            SoinMedical soinMedical=new SoinMedical(idSoinMedical,animal,dateSoin,heureSoin,remarqueSoin,numOrdonnance, mailVeto);

            return new SoinEffectue(giverBusiness.getUserByMail(mail),dateSoin,soinMedical,idSoineffectue,remarque);
        }
        catch(SQLException e)
        {
            new BDConnexionError();
            return null;
        }
    }
/*

 */
}
