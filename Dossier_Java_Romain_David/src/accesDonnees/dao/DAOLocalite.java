package accesDonnees.dao;

import erreurs.Erreur;
import modèle.Localite;

import java.util.ArrayList;

public interface DAOLocalite {
    ArrayList<Localite> readToutesLesLocalites() throws Erreur;

}
