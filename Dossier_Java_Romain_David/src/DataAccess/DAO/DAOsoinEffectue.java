package DataAccess.DAO;

import Model.SoinEffectue;
import erreurs.BDConnexionErreur;
import erreurs.ErreurrNull;
import erreurs.SoignantInexistant;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface DAOsoinEffectue
{
    ArrayList<SoinEffectue> searchHistory(String mail) throws ErreurrNull, BDConnexionErreur, SoignantInexistant;
    void create(String mailCareGiver, GregorianCalendar heureEffectuee,Integer soinMedical,String remarque);
}
