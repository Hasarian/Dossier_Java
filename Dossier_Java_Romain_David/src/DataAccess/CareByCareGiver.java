package DataAccess;

import Business.CareGiverBusiness;
import DataAccess.DAO.DAOsoinEffectue;
import Model.SoinEffectue;
import erreurs.BDConnexionError;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    public ArrayList<SoinEffectue> searchHistory(String mail) {
        String sql = "select * from * ";
        try{
            PreparedStatement statement = connection.prepareStatement(sql);

        }

        catch(SQLException e){
            new BDConnexionError();
            return null;
        }
        return null;
    }

    @Override
    public void create(String mailCareGiver, GregorianCalendar heureEffectuee, Integer soinMedical, String remarque) {

    }
/*
from soignant, localite, soineffectue, soinmedical
where soignant.mail = ? and soignant.localite = localite.idLocalite
and soineffectue.mail = soignant.mail and soineffectue.numSoinMedical = soinmedical.idSoinMedical;
 */
}
