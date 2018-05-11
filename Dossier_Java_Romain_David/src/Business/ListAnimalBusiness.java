package Business;

import DataAccess.DAO.DAOAnimal;
import DataAccess.AnimalDBAccess;
import Model.Animal;
import Model.CareGiver;
import Model.Race;
import erreurs.BDConnexionError;
import erreurs.ErrorNull;
import uIController.CareGiverController;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import static Model.Animal.EtatSoin.*;

public class ListAnimalBusiness {
    private ArrayList<Animal> availableList,personnalList,vetoAvailableList,vetoPersonnalList,allAnimals;
    private DAOAnimal daoAnimal;
    private static ListAnimalBusiness instance;
    private CareGiverController user;
    private ListAnimalBusiness(CareGiverController user) throws BDConnexionError,ErrorNull {
        this.user=user;
        daoAnimal = new AnimalDBAccess();
        availableList=new ArrayList<Animal>();
        personnalList=new ArrayList<Animal>();
        vetoAvailableList=(user.isVeto())?new ArrayList<Animal>():null;
        vetoPersonnalList=(user.isVeto())?new ArrayList<Animal>():null;
        daoAnimal.readAllAnimals();
    }
    public static ListAnimalBusiness obtenirAnimalBusiness(CareGiverController user)throws  BDConnexionError,ErrorNull
    {
        if(instance==null) instance=new ListAnimalBusiness(user);
        return instance;
    }
    public static ListAnimalBusiness obtenirAnimalBusiness()
    {
        return instance;
    }


    public Animal getAnimalInBD(int id) throws ErrorNull, BDConnexionError {
        return daoAnimal.read(id);
    }
    public ArrayList<Animal> getAllAnimals() {
        return allAnimals;
    }

    public ArrayList<Animal> getAvailableList() {
        return availableList;
    }

    public ArrayList<Animal> getPersonnalList() { return personnalList; }

    public ArrayList<Animal> getVetoAvailableList() { return vetoAvailableList; }

    public ArrayList<Animal> getVetoPersonnalList(){ return vetoPersonnalList; }

    public Animal ajoutAnimal (Integer id, String remarque, Integer numCell, String nomAnimal, Race race, GregorianCalendar dateArrivee,
                               GregorianCalendar dateDeces, Boolean estDangereux, Animal.EtatAnimal etatAnimal,
                               String remarqueSoin, Animal.EtatSoin etatFicheSoin, CareGiver careGiver)
    throws ErrorNull
    {
        Animal animal=new Animal(id,remarque,numCell,race,nomAnimal,dateArrivee,dateDeces,estDangereux,etatAnimal,remarqueSoin,etatFicheSoin,careGiver);
        ajoutAnimal(animal);
        return animal;
    }
    public boolean etatChange(Animal nouvelAnimal)
    {
        String id=nouvelAnimal.getId();
        Animal.EtatSoin etatSoin=nouvelAnimal.getEtatSoin();

        for(Animal animal:allAnimals)
        {
            if(id.compareTo(animal.getId())==0)
            {
                return etatSoin!=animal.getEtatSoin();
            }
        }
        return false;
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
    public Animal getAnimal(String id)
    {
        int i=0;
        while(i<allAnimals.size()&&allAnimals.get(i).getId().compareTo(id)==0)
        {
            i++;
        }
        return(allAnimals.get(i)==null)?null:allAnimals.get(i);
    }
    public void removeAnimal(String id)
    {
        Animal animal=getAnimal(id);
        if (animal.getEtatAnimal() != Animal.EtatAnimal.ARCHIVE) {
            switch (animal.getEtatFicheSoin()) {
                case DISPONIBLE:
                    availableList.remove(animal);
                    break;
                case RESERVEE:
                   personnalList.remove(animal);
                    break;
                case VETODISPO:
                        vetoAvailableList.remove(animal);
                    break;
                case VETORESERVEE:
                        vetoPersonnalList.remove(animal);
                    break;
            }
        }
        allAnimals.remove(animal);
    }
    public Animal ajoutAnimal(Animal animal)
    {
        if(!existeDeja(animal)) {
            if (animal.getEtatAnimal() != Animal.EtatAnimal.ARCHIVE) {
                switch (animal.getEtatFicheSoin()) {
                    case DISPONIBLE:
                        availableList.add(animal);
                        break;
                    case RESERVEE:
                        if (animal.isReservedByUser(user.getUserEmail())) personnalList.add(animal);
                        break;
                    case VETODISPO:
                        if (vetoAvailableList != null)
                            vetoAvailableList.add(animal);
                        break;
                    case VETORESERVEE:
                        if (vetoPersonnalList != null && animal.isReservedByUser(user.getUserEmail()))
                            vetoPersonnalList.add(animal);
                        break;
                }
            }
            allAnimals.add(animal);
        } else
        {
            if(!etatChange(animal))
            {
                animal=getAnimal(animal.getId());
            }else
            {
                removeAnimal(animal.getId());
                animal=ajoutAnimal(animal);
            }
        }
        return animal;
    }

    public void updateEtatFicheSoin(Animal animal, Animal.EtatSoin nouvelEtat) throws ErrorNull
    {
        animal.setEtatFicheSoin(nouvelEtat);
        if(nouvelEtat== Animal.EtatSoin.RESERVEE||nouvelEtat==Animal.EtatSoin.VETORESERVEE) animal.setCareGiver(user.getCareGiver());
        else if(nouvelEtat==Animal.EtatSoin.DISPONIBLE||nouvelEtat== Animal.EtatSoin.VETODISPO) animal.setCareGiver(null);
        daoAnimal.update(animal);
        ajoutAnimal(animal);
    }
}
