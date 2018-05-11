package uIController;

import Business.AnimalBusiness;
import Business.ListAnimalBusiness;
import Model.Animal;
import Model.SearchAnimalBetweenDate;
import erreurs.BDConnexionError;
import erreurs.ErrorNull;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class AnimalController {
    ListAnimalBusiness listAnimalBusiness;
    AnimalBusiness animalBusiness;


    public  AnimalController()throws BDConnexionError{
        animalBusiness = new AnimalBusiness();
    }

    public AnimalController(CareGiverController user) throws BDConnexionError,ErrorNull {
        listAnimalBusiness = ListAnimalBusiness.obtenirAnimalBusiness(user);
    }

    public Animal getAnimal(int id) throws ErrorNull, BDConnexionError {
        return listAnimalBusiness.getAnimalInBD(id);
    }
    public ArrayList getAnimalsBetweenDate(GregorianCalendar dateDebut, GregorianCalendar dateFin) throws ErrorNull, BDConnexionError{
        ArrayList<SearchAnimalBetweenDate> results  = animalBusiness.getAnimalsBetweenDates(dateDebut, dateFin);
        ArrayList<ArrayList<String>> resultToString = new ArrayList<ArrayList<String>>();
        ArrayList<String> ligne = new ArrayList<String>();

        for (SearchAnimalBetweenDate result : results) {
            ligne.add(result.getAnimal().getId());
            ligne.add(result.getAnimal().getCellNumber());
            ligne.add(result.getAnimal().getNomAnimal());
            ligne.add(result.getAnimal().getEtatAnimal().toString());
            ligne.add(result.getRace().getLibelle());
            ligne.add(result.getEspece().getLibelle());
            ligne.add(result.getVaccin().getLibelle());
            ligne.add(result.getVaccination().getDate().getTime().toString());
            resultToString.add(ligne);
        }
        return resultToString;
    }
    public ArrayList<Animal> getAllAnimals() throws ErrorNull,BDConnexionError{
        return listAnimalBusiness.getAllAnimals();
    }
}
