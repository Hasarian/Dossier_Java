package DataAccess.DAO;

import Model.SoinEffectue;
import erreurs.BDConnexionError;
import erreurs.ErrorNull;
import erreurs.InexistantCareGiver;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface DAOsoinEffectue
{
    ArrayList<SoinEffectue> searchHistory(String mail) throws ErrorNull, BDConnexionError, InexistantCareGiver;
    void create(String mailCareGiver, GregorianCalendar heureEffectuee,Integer soinMedical,String remarque);
}
