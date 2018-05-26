package DataAccess.DAO;

import Model.SoinMedical;
import erreurs.BDConnexionErreur;
import erreurs.ErreurrNull;

import java.util.ArrayList;

public interface DAORechercheSoinAFaire
{
    ArrayList<SoinMedical> readCareToAnimal(int id)throws BDConnexionErreur, ErreurrNull;

}
