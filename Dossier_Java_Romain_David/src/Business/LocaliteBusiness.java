package Business;

import DAO.DAOLocalite;
import DataAccess.LocaliteBDAccess;
import Model.Localite;
import erreurs.BDConnexionError;
import erreurs.ErrorNull;

import java.util.ArrayList;

public class LocaliteBusiness {
    private DAOLocalite daoLocalite;

    public LocaliteBusiness()throws BDConnexionError {
        daoLocalite = new LocaliteBDAccess();
    }
    public ArrayList<Localite> getAllLocalite() throws BDConnexionError, ErrorNull{
        return daoLocalite.readAllLocalite();
    }
}
