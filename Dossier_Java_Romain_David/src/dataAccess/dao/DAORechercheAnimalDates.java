package dataAccess.dao;

import erreurs.Erreur;
import model.Animal;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface DAORechercheAnimalDates
{
    ArrayList<Animal> readAnimalsbetweenDates(GregorianCalendar dateDeb, GregorianCalendar dateFin) throws Erreur;

}
