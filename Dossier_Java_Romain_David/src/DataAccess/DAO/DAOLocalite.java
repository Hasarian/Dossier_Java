package DataAccess.DAO;

import Model.Localite;
import erreurs.BDConnexionErreur;
import erreurs.ErreurInsertionSoignant;
import erreurs.ErreurrNull;

import java.util.ArrayList;

public interface DAOLocalite {
    void create(Localite localite)throws ErreurInsertionSoignant;
    Localite read(String libelle) throws BDConnexionErreur, ErreurrNull;
    ArrayList<Localite> readToutesLesLocalites() throws BDConnexionErreur, ErreurrNull;
    void update(Localite localite);
    void delete(String libelle);
}
