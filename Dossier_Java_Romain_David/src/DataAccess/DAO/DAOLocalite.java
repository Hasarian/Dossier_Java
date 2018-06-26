package DataAccess.DAO;

import Model.Localite;
import erreurs.DonneePermanenteErreur;
import erreurs.ErreurrNull;

import java.util.ArrayList;

public interface DAOLocalite {
    ArrayList<Localite> readToutesLesLocalites() throws DonneePermanenteErreur, ErreurrNull;

}
