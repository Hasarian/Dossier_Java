package uIController;

import Business.ListAnimalBusiness;
import Model.Animal;
import erreurs.BDConnexionError;
import erreurs.ErrorNull;

import java.util.ArrayList;

public class ListsControllerAnimal
{
    private static ListsControllerAnimal instance;
    private ListAnimalBusiness listAnimalBusiness;

    private ListsControllerAnimal(CareGiverController user) throws BDConnexionError, ErrorNull
    {
        listAnimalBusiness =ListAnimalBusiness.obtenirAnimalBusiness(user);
    }
    public static ListsControllerAnimal obtenirListController(CareGiverController user) throws BDConnexionError,ErrorNull
    {
        if(instance==null)instance=new ListsControllerAnimal(user);
        return instance;
    }
    public ArrayList<ArrayList<String>> getAvailableData()
    {
        ArrayList<ArrayList<String>> data=new ArrayList<ArrayList<String>>();
        remplirData(listAnimalBusiness.getAvailableList(),data);
        return data;
    }
    public ArrayList<ArrayList<String>> getPersonnalDatz()
    {
        ArrayList<ArrayList<String>> data=(listAnimalBusiness.getPersonnalList().size()==0)?null:new ArrayList<ArrayList<String>>();
        if(data!=null)
        {
               remplirData(listAnimalBusiness.getPersonnalList(),data);
        }
        return data;
    }

    public void remplirData(ArrayList<Animal> listeAnimal,ArrayList<ArrayList<String>> data)
    {
        for(Animal animal:listeAnimal)
        {
            ArrayList<String> row=new ArrayList<String>();
            row.add(animal.getNomAnimal());
            row.add(animal.getId());
            row.add(animal.getCellNumber());
            row.add(animal.getSpecies());
            data.add(row);
        }
    }
    public boolean personnalListIsNull()
    {
        return listAnimalBusiness.getPersonnalList().size()==0;
    }
    public ArrayList<ArrayList<String>> getVetoAvailableData()
    {
        ArrayList<ArrayList<String>> data=new ArrayList<ArrayList<String>>();
        remplirData(listAnimalBusiness.getVetoAvailableList(),data);
        return data;
    }
    public ArrayList<ArrayList<String>> getVetoPersonnalDatz()
    {
        ArrayList<ArrayList<String>> data=(listAnimalBusiness.getVetoPersonnalList().size()==0)?null:new ArrayList<ArrayList<String>>();
        if(data!=null)
        {
            remplirData(listAnimalBusiness.getVetoPersonnalList(),data);
        }
        return data;
    }
    public boolean vetoPersonnalListIsNull()
    {
        return listAnimalBusiness.getVetoPersonnalList().size()==0;
    }
    public void selectVetoTask(Integer id) throws ErrorNull
    {
        Animal selected=listAnimalBusiness.getAnimal(id.toString());
        listAnimalBusiness.updateEtatFicheSoin(selected, Animal.EtatSoin.VETORESERVEE);
    }
    public void selectTask(Integer id) throws ErrorNull
    {
        Animal selected=listAnimalBusiness.getAnimal(id.toString());
        listAnimalBusiness.updateEtatFicheSoin(selected, Animal.EtatSoin.RESERVEE);
    }
    public void unselectVetoTask(Integer id) throws ErrorNull
    {
        Animal selected=listAnimalBusiness.getAnimal(id.toString());
        listAnimalBusiness.updateEtatFicheSoin(selected,Animal.EtatSoin.VETODISPO);
        listAnimalBusiness.ajoutAnimal(selected);
    }
    public void unselectTask(Integer id) throws ErrorNull
    {
        Animal selected=listAnimalBusiness.getAnimal(id.toString());
       listAnimalBusiness.updateEtatFicheSoin(selected,Animal.EtatSoin.DISPONIBLE);
       listAnimalBusiness.ajoutAnimal(selected);
    }
}
