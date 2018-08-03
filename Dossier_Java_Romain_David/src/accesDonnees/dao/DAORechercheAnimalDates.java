package accesDonnees.dao;

import erreurs.Erreur;
import modèle.Animal;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface DAORechercheAnimalDates
{
    ArrayList<Animal> readAnimalsbetweenDates(GregorianCalendar dateDeb, GregorianCalendar dateFin) throws Erreur;

}
