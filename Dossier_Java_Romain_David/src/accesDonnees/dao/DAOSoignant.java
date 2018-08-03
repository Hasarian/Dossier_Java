package accesDonnees.dao;

import erreurs.Erreur;
import modèle.Soignant;

import java.util.ArrayList;

public interface DAOSoignant {
    void create(Soignant soignant)throws Erreur;
    Soignant read(String id) throws Erreur;
    void update(String ancienMail,Soignant soignant) throws Erreur;
    void delete(String id) throws Erreur;
    ArrayList<String> readallMails() throws Erreur;
    ArrayList<Soignant> readTousLesSoignants() throws Erreur;
}