package Business;

import DataAccess.DAO.DAOLocalite;
import DataAccess.LocaliteBDAccess;
import Model.Localite;
import erreurs.BDConnexionErreur;
import erreurs.ErreurrNull;

import java.util.ArrayList;

public class LocaliteBusiness {
    private DAOLocalite daoLocalite;

    public LocaliteBusiness()throws BDConnexionErreur {
        daoLocalite = new LocaliteBDAccess();
    }
    public ArrayList<Localite> getToutesLesLocalites() throws BDConnexionErreur, ErreurrNull {
        return daoLocalite.readToutesLesLocalites();
    }

    //êut être éviter de recharger les localités que l'on a déjà + créer un moyen d'aller les chercher une par une
}
