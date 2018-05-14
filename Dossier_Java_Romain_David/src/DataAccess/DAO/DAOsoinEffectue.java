package DataAccess.DAO;

import Model.SoinEffectue;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface DAOsoinEffectue
{
    ArrayList<SoinEffectue> searchHistory(String mail);
    void create(String mailCareGiver, GregorianCalendar heureEffectuee,Integer soinMedical,String remarque);
}
