package uIController;

import Business.AnimalBusiness;
import Business.ListAnimalBusiness;
import Model.Animal;
import Model.SoinEffectue;
import Model.SoinMedical;
import Model.Vaccination;
import erreurs.BDConnexionError;
import erreurs.ErrorNull;
import erreurs.InexistantCareGiver;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class AnimalController {
    private AnimalBusiness animalBusiness;

    public  AnimalController()throws BDConnexionError, ErrorNull, InexistantCareGiver{
        animalBusiness = AnimalBusiness.obtenirAnimalBusiness(ListAnimalBusiness.obtenirListAnimalBusiness(new CareGiverController()));
    }
    public String[] getStringAnimals(){
        String[] animals= new String[animalBusiness.getAllAnimals().size()];
        int i = 0;
        for (Animal animal: animalBusiness.getAllAnimals()) {
            animals[i] = animal.getId()+":"+animal.getNomAnimal();
            i++;
        }
        return animals;
    }
    public ArrayList<ArrayList<String >> getCareByAnimal(Integer id) throws BDConnexionError, ErrorNull{
        ArrayList<ArrayList<String>> soinMedicals = new ArrayList<ArrayList<String>>();
        for (SoinMedical soinMedical: animalBusiness.getCareByAnimal(id)) {
            ArrayList<String> row = new ArrayList<String>();
            row.add(soinMedical.getNumOrdonnance().toString());
            row.add(soinMedical.getIdSoinMedical().toString());
            row.add(soinMedical.getRemarque());
            row.add(soinMedical.getMailVeto());
            String date=new String();
            date+=soinMedical.getDate().get(Calendar.DAY_OF_MONTH)+"/";
            date+=soinMedical.getDate().get(Calendar.MONTH)+"/";
            date+=soinMedical.getDate().get(Calendar.YEAR);
            row.add(date);
            soinMedicals.add(row);
        }
        return soinMedicals;
    }
    public Animal getAnimal(int id ) throws BDConnexionError, ErrorNull,InexistantCareGiver {
        return animalBusiness.getAnimalFromBD(id);
    }
    public ArrayList<ArrayList<String>> getAnimalsBetweenDates(GregorianCalendar dateDebTemp,GregorianCalendar dateFinTemp) throws BDConnexionError,ErrorNull,InexistantCareGiver
    {
        ArrayList<Animal> animals =animalBusiness.getAnimalsBetweenDates(dateDebTemp,dateFinTemp);
        ArrayList<ArrayList<String>> data=new ArrayList<ArrayList<String>>();
        for(Animal animal:animals)
        {

            ArrayList<String> row=new ArrayList<String>();
            /*"Nom", "est dangereux", "num cellule", "espece", "race", "date arrive"*/
            row.add(animal.getNomAnimal());
            if(animal.getEstDangereux()) row.add("oui");
            else row.add("non");
            row.add(animal.getCellNumber());
            row.add(animal.getSpecies());
            row.add(animal.getRace().getLibelle());
            String date=new String();
            date+=animal.getDateArrive().get(Calendar.DAY_OF_MONTH)+"/";
            date+=animal.getDateArrive().get(Calendar.MONTH)+"/";
            date+=animal.getDateArrive().get(Calendar.YEAR);
            row.add(date);

            data.add(row);
        }
        return data;
    }

}
