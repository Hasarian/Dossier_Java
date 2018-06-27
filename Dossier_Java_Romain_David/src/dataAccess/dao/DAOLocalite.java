package dataAccess.dao;

import model.Localite;
import erreurs.DonneePermanenteErreur;
import erreurs.ErreurrNull;

import java.util.ArrayList;

public interface DAOLocalite {
    ArrayList<Localite> readToutesLesLocalites() throws DonneePermanenteErreur, ErreurrNull;

}
