package uIController;

import Business.ListAnimalBusiness;
import Model.Animal;
import erreurs.BDConnexionError;
import erreurs.ErrorNull;
import erreurs.InexistantCareGiver;

import java.util.ArrayList;

public class ListsControllerAnimal
{
    private static ListsControllerAnimal instance;
    private ListAnimalBusiness listAnimalBusiness;

    private ListsControllerAnimal(CareGiverController user) throws BDConnexionError, ErrorNull, InexistantCareGiver
    {
        listAnimalBusiness =ListAnimalBusiness.obtenirListAnimalBusiness(user);
    }
    public static ListsControllerAnimal obtenirListController(CareGiverController user) throws BDConnexionError,ErrorNull,InexistantCareGiver
    {
        if(instance==null)instance=new ListsControllerAnimal(user);
        return instance;
    }
    public String getColumn(int column,Animal animal)
    {
        switch(column)
        {
            case 0:
                return animal.getNomAnimal();
            case 1:
                return animal.getId().toString();
            case 2:
                return animal.getCellNumber();
            case 3:
                return animal.getSpecies();
            default:
                return "unkown data";
        }
    }
    public String getAvailableData(int row, int column)
    {
        Animal animal=listAnimalBusiness.getAvailableList().get(row);
        return getColumn(column,animal);
    }
    public int availAbleDataCount(){return listAnimalBusiness.getAvailableList().size();}

    public String getPersonnalData(int row, int column)
    {
        return getColumn(column,listAnimalBusiness.getPersonnalList().get(row));
    }
    public int personnalDataCount(){return listAnimalBusiness.getPersonnalList().size();}

    public void remplirData(ArrayList<Animal> listeAnimal,ArrayList<ArrayList<String>> data)
    {
        for(Animal animal:listeAnimal)
        {
            ArrayList<String> row=new ArrayList<String>();
            row.add(animal.getNomAnimal());
            row.add(animal.getId().toString());
            row.add(animal.getCellNumber());
            row.add(animal.getSpecies());
            data.add(row);
        }
    }
    public boolean personnalListIsNull()
    {
        return listAnimalBusiness.getPersonnalList().size()==0;
    }
    public String getVetoAvailableData(int row, int column)
    {
        return getColumn(column,listAnimalBusiness.getVetoAvailableList().get(row));
    }
    public int getVetoAvailableCount(){return listAnimalBusiness.getVetoAvailableList().size();}

    public String getVetoPersonnalData(int row, int column)
    {
        return getColumn(column,listAnimalBusiness.getVetoPersonnalList().get(row));
    }
    public int getVetoPersonnalDataCount(){return listAnimalBusiness.getVetoPersonnalList().size();}
    public boolean vetoPersonnalListIsNull()
    {
        return listAnimalBusiness.getVetoPersonnalList().size()==0;
    }
    public String getAvailbleId(int index)
    {
        return listAnimalBusiness.getAvailableList().get(index).getId().toString();
    }
    public String getVetoAvailbleId(int index)
    {
        return listAnimalBusiness.getVetoAvailableList().get(index).getId().toString();
    }
    public String getVetoSelectedId(int index)
    {
        return listAnimalBusiness.getVetoPersonnalList().get(index).getId().toString();
    }
    public String getSelectedId(int index)
    {
        return listAnimalBusiness.getPersonnalList().get(index).getId().toString();
    }
    public void selectVetoTask(Integer id) throws ErrorNull
    {
        Animal selected=listAnimalBusiness.getAnimal(id);
        listAnimalBusiness.updateEtatFicheSoin(selected, Animal.EtatSoin.VETORESERVEE);
    }
    public void selectTask(Integer id) throws ErrorNull
    {
        Animal selected=listAnimalBusiness.getAnimal(id);
        listAnimalBusiness.updateEtatFicheSoin(selected, Animal.EtatSoin.RESERVEE);
    }
    public void unselectVetoTask(Integer id) throws ErrorNull
    {
        Animal selected=listAnimalBusiness.getAnimal(id);
        listAnimalBusiness.updateEtatFicheSoin(selected,Animal.EtatSoin.VETODISPO);
        listAnimalBusiness.ajoutAnimal(selected);
    }
    public void unselectTask(Integer id) throws ErrorNull
    {
        Animal selected=listAnimalBusiness.getAnimal(id);
       listAnimalBusiness.updateEtatFicheSoin(selected,Animal.EtatSoin.DISPONIBLE);
       listAnimalBusiness.ajoutAnimal(selected);
    }
    public Animal getanimal(Integer id)
    {
        return listAnimalBusiness.getAnimal(id);
    }
}
