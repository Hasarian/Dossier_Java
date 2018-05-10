package uIController;

import Business.ListAnimalBusiness;
import Model.Animal;
import erreurs.BDConnexionError;
import erreurs.ErrorNull;

import java.util.ArrayList;

public class ListsControllerAnimal
{

    private ListAnimalBusiness listAnimalBusiness;

    public ListsControllerAnimal(CareGiverController user) throws BDConnexionError, ErrorNull
    {
        listAnimalBusiness =ListAnimalBusiness.obtenirAnimalBusiness(user);
    }

    public ArrayList<ArrayList<String>> getAvailableData()
    {
        ArrayList<ArrayList<String>> data=new ArrayList<ArrayList<String>>();
        for(Animal animal: listAnimalBusiness.getAvailableList())
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
