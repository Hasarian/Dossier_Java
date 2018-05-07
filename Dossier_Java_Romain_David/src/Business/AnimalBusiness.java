package Business;

import DAO.DAOAnimal;
import DataAccess.AnimalDBAccess;
import Model.Animal;
import erreurs.BDConnexionError;
import erreurs.ErrorNull;
import uIController.CareGiverController;

import java.util.ArrayList;

import static Model.Animal.EtatSoin.DISPONIBLE;

public class AnimalBusiness {

    private ArrayList<Animal> availableList,personnalList,vetoAvailableList,vetoPersonnalList;
    private DAOAnimal daoAnimal;

    public AnimalBusiness(CareGiverController user) throws BDConnexionError,ErrorNull {
        daoAnimal = new AnimalDBAccess();
        availableList=new ArrayList<Animal>();
        personnalList=new ArrayList<Animal>();
        vetoAvailableList=(user.isVeto())?new ArrayList<Animal>():null;
        vetoPersonnalList=(user.isVeto())?new ArrayList<Animal>():null;
        ArrayList<Animal> allAnimals = getAllAnimals();
        ArrayList<Animal> animauxDansLaSpa=new ArrayList<Animal>();
        for(Animal animal:allAnimals)
        {
           if(animal.getEtatAnimal()!=Animal.EtatAnimal.ARCHIVE)
           {
               switch (animal.getEtatFicheSoin())
               {
                   case DISPONIBLE:
                       availableList.add(animal);
                       break;
                   case RESERVEE:
                       break;
                   case VETODISPO:
                       if(vetoAvailableList!=null)
                           vetoAvailableList.add(animal);
                       break;
                   case VETORESERVEE:
                       if(vetoPersonnalList!=null)
                           break;
               }
           }
        }

    }


    public Animal getAnimal(int id) throws ErrorNull, BDConnexionError {
        return daoAnimal.read(id);
    }
    public ArrayList<Animal> getAllAnimals() throws ErrorNull, BDConnexionError{
        return daoAnimal.readAllAnimal();
    }

    public ArrayList<Animal> getAvailableList() {
        return availableList;
    }
}
