package DAO;

import Model.CareGiver;
import Model.Localite;
import erreurs.BDConnexionError;
import erreurs.ErreurInsertCareGiver;
import erreurs.ErrorNull;
import erreurs.InexistantCareGiver;

import java.util.ArrayList;

public interface DAOLocalite {
    void create(Localite localite)throws ErreurInsertCareGiver;
    Localite read(String libelle) throws BDConnexionError, ErrorNull;
    ArrayList<Localite> readAllLocalite() throws BDConnexionError, ErrorNull;
    void update(Localite localite);
    void delete(String libelle);
}
