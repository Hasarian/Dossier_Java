package DataAccess.DAO;

import Model.Localite;
import erreurs.BDConnexionErreur;
import erreurs.ErreurInsertionSoignant;
import erreurs.ErreurrNull;

import java.util.ArrayList;

public interface DAOLocalite {
    ArrayList<Localite> readToutesLesLocalites() throws BDConnexionErreur, ErreurrNull;

}
