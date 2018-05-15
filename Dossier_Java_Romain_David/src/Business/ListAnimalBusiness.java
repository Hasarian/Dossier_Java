package Business;

import DataAccess.DAO.DAOAnimal;
import DataAccess.AnimalDBAccess;
import Model.Animal;
import Model.CareGiver;
import Model.Race;
import erreurs.BDConnexionError;
import erreurs.ErrorNull;
import erreurs.InexistantCareGiver;
import uIController.CareGiverController;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import static Model.Animal.EtatSoin.*;

public class ListAnimalBusiness {
    private ArrayList<Animal> availableList,personnalList,vetoAvailableList,vetoPersonnalList;
    private AnimalBusiness animalBusiness;
    private static ListAnimalBusiness instance;
    private CareGiverController user;
    private ListAnimalBusiness(CareGiverController user) throws BDConnexionError,ErrorNull, InexistantCareGiver {
        this.user=user;
        availableList=new ArrayList<Animal>();
        personnalList=new ArrayList<Animal>();
        vetoAvailableList=(user.isVeto())?new ArrayList<Animal>():null;
        vetoPersonnalList=(user.isVeto())?new ArrayList<Animal>():null;
        animalBusiness=AnimalBusiness.obtenirAnimalBusiness(this);
    }
    public static ListAnimalBusiness obtenirListAnimalBusiness(CareGiverController user)throws  BDConnexionError,ErrorNull,InexistantCareGiver
    {
        if(instance==null) instance=new ListAnimalBusiness(user);
        return instance;
    }

    public ArrayList<Animal> getAvailableList() {
        return availableList;
    }

    public ArrayList<Animal> getPersonnalList() { return personnalList; }

    public ArrayList<Animal> getVetoAvailableList() { return vetoAvailableList; }

    public ArrayList<Animal> getVetoPersonnalList(){ return vetoPersonnalList; }


    public Animal getAnimal(String id)
    {
       return animalBusiness.getAnimal(id);
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
    }
    public void ajoutAnimal(Animal animal)
    {
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
    }

    public void updateEtatFicheSoin(Animal animal, Animal.EtatSoin nouvelEtat) throws ErrorNull
    {
        removeAnimal(animal.getId());
        animal.setEtatFicheSoin(nouvelEtat);
        if(nouvelEtat== Animal.EtatSoin.RESERVEE||nouvelEtat==Animal.EtatSoin.VETORESERVEE) animal.setCareGiver(user.getCareGiver());
        else if(nouvelEtat==Animal.EtatSoin.DISPONIBLE||nouvelEtat== Animal.EtatSoin.VETODISPO) animal.setCareGiver(null);
        animalBusiness.animalUpdate(animal);
        ajoutAnimal(animal);
    }
}
