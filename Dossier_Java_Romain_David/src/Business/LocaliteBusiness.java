package Business;

import DataAccess.DAO.DAOLocalite;
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

    //êut être éviter de recharger les localités que l'on a déjà + créer un moyen d'aller les chercher une par une
}
