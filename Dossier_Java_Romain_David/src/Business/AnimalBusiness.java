package Business;

import DataAccess.AnimalDataAccess;
import DataAccess.DAO.DAORechercheAnimalDates;
import DataAccess.DAO.DAORechercheSoinAFaire;
import DataAccess.SoinAFairePourAnimal;
import DataAccess.DAO.DAOAnimal;
import DataAccess.RechercheAnimauxEntreDates;
import Model.*;
import erreurs.DonneePermanenteErreur;
import erreurs.ErreurrNull;
import erreurs.SoignantInexistant;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class AnimalBusiness {
    private DAORechercheAnimalDates rechercheEntreDates;
    private DAORechercheSoinAFaire rechercheDeSoinParAnimal;
    private DAOAnimal dataAcces;
    public AnimalBusiness()throws DonneePermanenteErreur {
        dataAcces=new AnimalDataAccess();

        rechercheEntreDates =new RechercheAnimauxEntreDates();
        rechercheDeSoinParAnimal = new SoinAFairePourAnimal(this);
    }
    public void init()throws ErreurrNull, DonneePermanenteErreur, SoignantInexistant{
        dataAcces.readTousLesAnimaux();
    }

    public Animal getAnimal(Integer id) throws DonneePermanenteErreur,ErreurrNull,SoignantInexistant
    {
        return dataAcces.read(id);
    }
    public ArrayList<SoinMedical> getSoinPourUnAnimal(Integer id,GregorianCalendar date) throws DonneePermanenteErreur, ErreurrNull,SoignantInexistant {
        return rechercheDeSoinParAnimal.readCareToAnimal(id,date);
    }


    public ArrayList<Animal> getTousLesAnimaux()throws DonneePermanenteErreur,ErreurrNull,SoignantInexistant {
        return dataAcces.readTousLesAnimaux();
    }

    public void animalUpdate(Animal animal) throws DonneePermanenteErreur
    {
        dataAcces.updateEtat(animal);
    }
    public ArrayList<Animal> getAnimauxEntreDates(GregorianCalendar dateDebut, GregorianCalendar dateFin) throws DonneePermanenteErreur, ErreurrNull, SoignantInexistant
    {
        return rechercheEntreDates.readAnimalsbetweenDates(dateDebut,dateFin);
    }
}
