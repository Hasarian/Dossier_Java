package DataAccess.DAO;

import Model.Soignant;
import erreurs.BDConnexionErreur;
import erreurs.ErreurInsertionSoignant;
import erreurs.ErreurrNull;
import erreurs.SoignantInexistant;

import java.util.ArrayList;

public interface DAOSoignant {
    void create(Soignant soignant)throws ErreurInsertionSoignant;
    Soignant read(String id) throws SoignantInexistant, BDConnexionErreur, ErreurrNull;
    void update(Soignant soignant) throws BDConnexionErreur;
    void delete(String id) throws BDConnexionErreur;
    ArrayList<String> readallMails() throws BDConnexionErreur;
    ArrayList<Soignant> readTousLesSoignants() throws BDConnexionErreur,ErreurrNull;
}