package DataAccess.DAO;

import Model.SoinMedical;
import erreurs.BDConnexionErreur;
import erreurs.ErreurrNull;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface DAORechercheSoinAFaire
{
    ArrayList<SoinMedical> readCareToAnimal(int id,GregorianCalendar date)throws BDConnexionErreur, ErreurrNull;

}
