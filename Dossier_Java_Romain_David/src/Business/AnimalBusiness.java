package Business;

import DataAccess.AnimalDBAccess;
import DataAccess.DAO.DAORechercheAnimalDates;
import DataAccess.DAO.DAORechercheSoinAFaire;
import DataAccess.SoinAFairePourAnimal;
import DataAccess.DAO.DAOAnimal;
import DataAccess.RechercheAnimauxEntreDates;
import Model.*;
import erreurs.BDConnexionErreur;
import erreurs.ErreurrNull;
import erreurs.SoignantInexistant;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class AnimalBusiness {
    private DAORechercheAnimalDates rechercheEntreDates;
    private DAORechercheSoinAFaire rechercheDeSoinParAnimal;
    private DAOAnimal dbAcces;
    private ArrayList<Animal> listeAnimaux;
    private static AnimalBusiness instance;
    private ListeAnimalBusiness listesBusiness;
    private AnimalBusiness(ListeAnimalBusiness businessListes)throws BDConnexionErreur, ErreurrNull {

        listeAnimaux =new ArrayList<Animal>();
        listesBusiness = businessListes;
        dbAcces=new AnimalDBAccess(this);

        rechercheEntreDates =new RechercheAnimauxEntreDates();
        rechercheDeSoinParAnimal = new SoinAFairePourAnimal(this);
    }
    public void init()throws ErreurrNull, BDConnexionErreur, SoignantInexistant{
        dbAcces.readTousLesAnimaux();
    }
    public static AnimalBusiness obtenirAnimalBusiness(ListeAnimalBusiness listanimals)
    throws BDConnexionErreur, ErreurrNull, SoignantInexistant
    {
        if(instance==null)
        {instance=new AnimalBusiness(listanimals);
        }
        return instance;
    }

    public Animal getAnimal(Integer id)
    {
        for(Animal animal: listeAnimaux)
        {
            if(animal.getId().equals(id)) return animal;
        }
        return null;
    }
    public Animal getAnimalDansLaBD(Integer id) throws ErreurrNull, BDConnexionErreur, SoignantInexistant {
        return dbAcces.read(id);
    }
    public ArrayList<SoinMedical> getSoinPourUnAnimal(Integer id) throws BDConnexionErreur, ErreurrNull {
        return rechercheDeSoinParAnimal.readCareToAnimal(id);
    }
    public void nouvelAnimalDeLaDB(Integer id, String remarque, Integer numCell, String nom, Race race, GregorianCalendar dateArrivee, GregorianCalendar dateFin,
                                   Boolean estDangereux, Animal.EtatAnimal etatAnimal, String remarqueSoin, Animal.EtatSoin etatSoin, Soignant soignant)
    throws ErreurrNull
    {
        Animal newAnimal=new Animal(id,remarque,numCell,race,nom,dateArrivee,dateFin,estDangereux,etatAnimal,remarqueSoin,etatSoin, soignant);
        if(getAnimal(id)!=null) {
            listesBusiness.retirerAnimal(id);
            listeAnimaux.remove(getAnimal(id));

        }
        listeAnimaux.add(newAnimal);
        listesBusiness.ajoutAnimal(newAnimal);

    }

    public Vaccin getVaccin(String libelle, Integer numVaccin) throws ErreurrNull
    {
        return new Vaccin(libelle,numVaccin);
    }
    public Vaccination getVaccination(Animal animal,Vaccin vaccin,GregorianCalendar dateVaccination,Integer numVaccination) throws ErreurrNull
    {
        return new Vaccination(animal,vaccin,dateVaccination,numVaccination);
    }

    public ArrayList<Animal> getTousLesAnimaux() {
        return listeAnimaux;
    }
    public boolean existeDeja(Animal nouvelAnimal)
    {
        Integer id=nouvelAnimal.getId();
        int i=0;
        while(i< listeAnimaux.size()&& listeAnimaux.get(i).getId().compareTo(id)==0)
        {
            i++;
        }
        Boolean extisteDeja = (i == listeAnimaux.size()? false : listeAnimaux.get(i).getId().compareTo(id)==0);
        return  extisteDeja;
    }
    public void animalUpdate(Animal animal) throws BDConnexionErreur
    {
        dbAcces.updateEtat(animal);
    }
    public ArrayList<Animal> getAnimauxEntreDates(GregorianCalendar dateDebut, GregorianCalendar dateFin) throws BDConnexionErreur, ErreurrNull, SoignantInexistant
    {
        return rechercheEntreDates.readAnimalsbetweenDates(dateDebut,dateFin);
    }
}
