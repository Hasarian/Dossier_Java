package business;

import dataAccess.dao.DAOLocalite;
import dataAccess.LocaliteDataAccess;
import model.Localite;
import erreurs.DonneePermanenteErreur;
import erreurs.ErreurrNull;

import java.util.ArrayList;

public class LocaliteBusiness {
    private DAOLocalite daoLocalite;

    public LocaliteBusiness()throws DonneePermanenteErreur {
        daoLocalite = new LocaliteDataAccess();
    }
    public ArrayList<Localite> getToutesLesLocalites() throws DonneePermanenteErreur, ErreurrNull {
        return daoLocalite.readToutesLesLocalites();
    }

    //êut être éviter de recharger les localités que l'on a déjà + créer un moyen d'aller les chercher une par une
}
