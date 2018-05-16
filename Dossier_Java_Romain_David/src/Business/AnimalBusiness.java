package Business;

import DataAccess.AnimalDBAccess;
import DataAccess.CareToDoForAnimal;
import DataAccess.DAO.DAOAnimal;
import DataAccess.SearchAnimalsBetweenDate;
import Model.*;
import erreurs.BDConnexionError;
import erreurs.ErrorNull;
import erreurs.InexistantCareGiver;
import userInterface.SearchCareByAnimal;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class AnimalBusiness {
    private SearchAnimalsBetweenDate research;
    private CareToDoForAnimal searchCareByAnimal;
    private DAOAnimal dbAcces;
    private ArrayList<Animal> allAnimals;
    private static AnimalBusiness instance;
    private ListAnimalBusiness listBusiness;
    private AnimalBusiness(ListAnimalBusiness listanimals)throws BDConnexionError,ErrorNull, InexistantCareGiver{
        research=new SearchAnimalsBetweenDate();
        allAnimals=new ArrayList<Animal>();
        dbAcces=new AnimalDBAccess(this);
        listBusiness=listanimals;
        dbAcces.readAllAnimals();
        searchCareByAnimal = new CareToDoForAnimal(this);
    }
    public static AnimalBusiness obtenirAnimalBusiness(ListAnimalBusiness listanimals)
    throws BDConnexionError,ErrorNull,InexistantCareGiver
    {
        if(instance==null)
        {instance=new AnimalBusiness(listanimals);
        }
        return instance;
    }

    public Animal getAnimal(Integer id)
    {
        for(Animal animal:allAnimals)
        {
            System.out.println(id+"\t"+animal.getId());
            if(animal.getId().equals(id)) return animal;
        }
        return null;
    }
    public Animal getAnimalFromBD(Integer id) throws ErrorNull, BDConnexionError,InexistantCareGiver{
        return dbAcces.read(id);
    }
    public ArrayList<SoinMedical> getCareByAnimal(Integer id) throws BDConnexionError, ErrorNull{
        return searchCareByAnimal.readCareToAnimal(id);
    }
    public void nouvelAnimalFromDB(Integer id, String remarque, Integer numCell, String nom, Race race, GregorianCalendar dateArrivee, GregorianCalendar dateFin,
                             Boolean estDangereux, Animal.EtatAnimal etatAnimal, String remarqueSoin,Animal.EtatSoin etatSoin,CareGiver careGiver)
    throws ErrorNull
    {
        Animal newAnimal=new Animal(id,remarque,numCell,race,nom,dateArrivee,dateFin,estDangereux,etatAnimal,remarqueSoin,etatSoin,careGiver);
        if(getAnimal(id)!=null) {
            listBusiness.removeAnimal(id);
            allAnimals.remove(getAnimal(id));

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
        Integer id=nouvelAnimal.getId();
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
    public ArrayList<Animal> getAnimalsBetweenDates(GregorianCalendar dateDebut,GregorianCalendar dateFin) throws BDConnexionError,ErrorNull,InexistantCareGiver
    {
        return research.readAnimalsbetweenDates(dateDebut,dateFin);
    }
}
