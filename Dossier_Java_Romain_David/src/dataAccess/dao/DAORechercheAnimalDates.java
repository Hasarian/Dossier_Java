package dataAccess.dao;

import model.Animal;
import erreurs.DonneePermanenteErreur;
import erreurs.ErreurrNull;
import erreurs.SoignantInexistant;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface DAORechercheAnimalDates
{
    ArrayList<Animal> readAnimalsbetweenDates(GregorianCalendar dateDeb, GregorianCalendar dateFin) throws ErreurrNull, DonneePermanenteErreur, SoignantInexistant;

}
