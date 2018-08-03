package accesDonnees.dao;

import erreurs.Erreur;
import modèle.SoinMedical;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface DAORechercheSoinAFaire
{
    ArrayList<SoinMedical> readCareToAnimal(int id,GregorianCalendar date)throws Erreur;

}
