package business;

import accesDonnees.AnimalDonnees;
import accesDonnees.dao.DAORechercheAnimalDates;
import accesDonnees.dao.DAORechercheSoinAFaire;
import accesDonnees.SoinAFairePourAnimal;
import accesDonnees.dao.DAOAnimal;
import accesDonnees.RechercheAnimauxEntreDates;
import erreurs.Erreur;
import modèle.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class AnimalBusiness {
    private DAORechercheAnimalDates rechercheEntreDates;
    private DAORechercheSoinAFaire rechercheDeSoinParAnimal;
    private DAOAnimal dataAcces;
    public AnimalBusiness()throws Erreur {
        dataAcces=new AnimalDonnees();

        rechercheEntreDates =new RechercheAnimauxEntreDates();
        rechercheDeSoinParAnimal = new SoinAFairePourAnimal(this);
    }

    public Animal getAnimal(Integer id) throws Erreur
    {
        return dataAcces.read(id);
    }
    public ArrayList<SoinMedical> getSoinPourUnAnimal(Integer id,GregorianCalendar date) throws Erreur {
        return rechercheDeSoinParAnimal.readCareToAnimal(id,date);
    }


    public ArrayList<Animal> getTousLesAnimaux()throws Erreur {
        return dataAcces.readTousLesAnimaux();
    }

    public void animalUpdate(Animal animal) throws Erreur
    {
        dataAcces.updateEtat(animal);
    }
    public ArrayList<Animal> getAnimauxEntreDates(GregorianCalendar dateDebut, GregorianCalendar dateFin) throws Erreur
    {
        return rechercheEntreDates.readAnimalsbetweenDates(dateDebut,dateFin);
    }
}
