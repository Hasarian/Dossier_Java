package DataAccess.DAO;

import Model.SoinEffectue;
import erreurs.DonneePermanenteErreur;
import erreurs.ErreurrNull;
import erreurs.MauvaiseTailleString;
import erreurs.SoignantInexistant;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface DAORechercheSoinEffectue
{
    ArrayList<SoinEffectue> searchHistory(String mail)
            throws DonneePermanenteErreur, ErreurrNull, SoignantInexistant, MauvaiseTailleString;
    void create(String mailSoignant, GregorianCalendar heureEffectuee, Integer soinMedical, String remarque)
            throws DonneePermanenteErreur;
}
