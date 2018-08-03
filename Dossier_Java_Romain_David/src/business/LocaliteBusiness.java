package business;

import accesDonnees.dao.DAOLocalite;
import accesDonnees.LocaliteDonnees;
import erreurs.Erreur;
import modèle.Localite;
import erreurs.erreursExternes.DonneePermanenteErreur;

import java.util.ArrayList;

public class LocaliteBusiness {
    private DAOLocalite daoLocalite;

    public LocaliteBusiness()throws DonneePermanenteErreur {
        daoLocalite = new LocaliteDonnees();
    }
    public ArrayList<Localite> getToutesLesLocalites() throws Erreur {
        return daoLocalite.readToutesLesLocalites();
    }

    //êut être éviter de recharger les localités que l'on a déjà + créer un moyen d'aller les chercher une par une
}
