package Business;

import DataAccess.AnimalDBAccess;
import DataAccess.DAO.DAOAnimal;
import DataAccess.SearchAnimalsBetweenDate;
import Model.*;
import erreurs.BDConnexionError;
import erreurs.ErrorNull;
import uIController.CareGiverController;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class AnimalBusiness {
    private SearchAnimalsBetweenDate research;
    private AnimalDBAccess dbAcces;
    private ArrayList<Animal> allAnimals;
    private static AnimalBusiness instance;
    private ListAnimalBusiness listBusiness;
    private AnimalBusiness(ListAnimalBusiness listanimals)throws BDConnexionError,ErrorNull{
        research=new SearchAnimalsBetweenDate();
        allAnimals=new ArrayList<Animal>();
        dbAcces=new AnimalDBAccess(this);
        listBusiness=listanimals;
        dbAcces.readAllAnimals();
    }
    public static AnimalBusiness obtenirAnimalBusiness(ListAnimalBusiness listanimals)
    throws BDConnexionError,ErrorNull
    {
        if(instance==null)
        {instance=new AnimalBusiness(listanimals);
        }
        return instance;
    }

    public Animal getAnimal(String id)
    {
        for(Animal animal:allAnimals)
        {
            if(animal.getId().compareTo(id)==0) return animal;
        }
        return null;
    }
    public void nouvelAnimalFromDB(Integer id, String remarque, Integer numCell, String nom, Race race, GregorianCalendar dateArrivee, GregorianCalendar dateFin,
                             Boolean estDangereux, Animal.EtatAnimal etatAnimal, String remarqueSoin,Animal.EtatSoin etatSoin,CareGiver careGiver)
    throws ErrorNull
    {
        Animal newAnimal=new Animal(id,remarque,numCell,race,nom,dateArrivee,dateFin,estDangereux,etatAnimal,remarqueSoin,etatSoin,careGiver);
        System.out.print(newAnimal+"\nfrom BD");
        if(getAnimal(id.toString())!=null) {
            allAnimals.remove(getAnimal(id.toString()));
            listBusiness.removeAnimal(id.toString());
        }
        allAnimals.add(newAnimal);
        listBusiness.ajoutAnimal(newAnimal);

    }

    public Vaccin getVaccin(String libelle, Integer numVaccin) throws ErrorNull
    {
        return new Vaccin(libelle,numVaccin);
    }
    public Vaccination getVaccination(Animal animal,Vaccin vaccin,GregorianCalendar dateVaccination,Integer numVaccination) throws ErrorNull
    {
        return new Vaccination(animal,vaccin,dateVaccination,numVaccination);
    }

    public ArrayList<Animal> getAllAnimals() {
        return allAnimals;
    }
    public boolean existeDeja(Animal nouvelAnimal)
    {
        String id=nouvelAnimal.getId();
        int i=0;
        while(i<allAnimals.size()&&allAnimals.get(i).getId().compareTo(id)==0)
        {
            i++;
        }
        return allAnimals.get(i).getId().compareTo(id)==0;
    }
    public void animalUpdate(Animal animal)
    {
        dbAcces.update(animal);
    }
    public ArrayList<Vaccination> getAnimalsBetweenDates(GregorianCalendar dateDebut,GregorianCalendar dateFin) throws BDConnexionError,ErrorNull
    {
        return (new SearchAnimalsBetweenDate()).readAnimalsbetweenDates(dateDebut,dateFin);
    }
}
