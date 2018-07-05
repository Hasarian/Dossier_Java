package dataAccess.dao;

import erreurs.Erreur;
import model.SoinEffectue;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface DAORechercheSoinEffectue
{
    ArrayList<SoinEffectue> searchHistory(String mail)
            throws Erreur;
    void create(String mailSoignant, GregorianCalendar heureEffectuee, Integer soinMedical, String remarque)
            throws Erreur;
}
