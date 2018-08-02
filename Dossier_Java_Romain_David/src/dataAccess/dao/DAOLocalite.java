package dataAccess.dao;

import erreurs.Erreur;
import model.Localite;

import java.util.ArrayList;

public interface DAOLocalite {
    ArrayList<Localite> readToutesLesLocalites() throws Erreur;

}
