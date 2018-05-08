package uIController;

import Business.AnimalBusiness;
import Model.Animal;
import erreurs.BDConnexionError;
import erreurs.ErrorNull;

import java.util.ArrayList;

public class ListsController
{

    private AnimalBusiness animalBusiness;

    public ListsController(CareGiverController user) throws BDConnexionError, ErrorNull
    {
        animalBusiness=AnimalBusiness.obtenirAnimalBusiness(user);
    }

    public ArrayList<ArrayList<String>> getAvailableData()
    {
        ArrayList<ArrayList<String>> data=new ArrayList<ArrayList<String>>();
        for(Animal animal:animalBusiness.getAvailableList())
        {
            ArrayList<String> row=new ArrayList<String>();
            row.add(animal.getNomAnimal());
            row.add(animal.getId());
            row.add(animal.getCellNumber());
            row.add(animal.getSpecies());
            data.add(row);
        }
        return data;
    }
}
