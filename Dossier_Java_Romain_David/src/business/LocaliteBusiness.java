package business;

import dataAccess.dao.DAOLocalite;
import dataAccess.LocaliteDataAccess;
import erreurs.Erreur;
import model.Localite;
import erreurs.erreursExternes.DonneePermanenteErreur;
import erreurs.erreurFormat.ErreurrNull;

import java.util.ArrayList;

public class LocaliteBusiness {
    private DAOLocalite daoLocalite;

    public LocaliteBusiness()throws DonneePermanenteErreur {
        daoLocalite = new LocaliteDataAccess();
    }
    public ArrayList<Localite> getToutesLesLocalites() throws Erreur {
        return daoLocalite.readToutesLesLocalites();
    }

    //êut être éviter de recharger les localités que l'on a déjà + créer un moyen d'aller les chercher une par une
}
