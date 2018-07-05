package dataAccess.dao;

import erreurs.Erreur;
import model.Localite;
import erreurs.erreursExternes.DonneePermanenteErreur;
import erreurs.erreurFormat.ErreurrNull;

import java.util.ArrayList;

public interface DAOLocalite {
    ArrayList<Localite> readToutesLesLocalites() throws Erreur;

}
