package uIController;

import Business.AnimalBusiness;
import Business.ListAnimalBusiness;
import Model.Animal;
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
            animals[i] = animal.getNomAnimal()+"(id:"+animal.getId()+")";
            i++;
        }
        return animals;
    }
    public Animal getAnimal(int id ) throws BDConnexionError, ErrorNull,InexistantCareGiver {
        return animalBusiness.getAnimalFromBD(id);
    }
    public ArrayList<ArrayList<String>> getAnimalsBetweenDates(GregorianCalendar dateDebTemp,GregorianCalendar dateFinTemp) throws BDConnexionError,ErrorNull,InexistantCareGiver
    {
        ArrayList<Vaccination> vaccinations =animalBusiness.getAnimalsBetweenDates(dateDebTemp,dateFinTemp);
        ArrayList<ArrayList<String>> data=new ArrayList<ArrayList<String>>();
        for(Vaccination vaccination:vaccinations)
        {
            ArrayList<String> row=new ArrayList<String>();
            /*"Nom", "est dangereux", "num cellule", "espece", "vaccin", "date vaccin", "date arrive"*/
            row.add(vaccination.getAnimal().getNomAnimal());
            if(vaccination.getAnimal().getEstDangereux()) row.add("oui");
            else row.add("non");
            row.add(vaccination.getAnimal().getCellNumber());
            row.add(vaccination.getAnimal().getSpecies());
            row.add(vaccination.getNumVaccin().getLibelle());
            String date=new String();
            date+=vaccination.getDate().get(Calendar.DAY_OF_MONTH)+"/";
            date+=vaccination.getDate().get(Calendar.MONTH)+"/";
            date+=vaccination.getDate().get(Calendar.YEAR);
            row.add(date);
            date=new String();
            date+=vaccination.getAnimal().getDateArrive().get(Calendar.DAY_OF_MONTH)+"/";
            date+=vaccination.getAnimal().getDateArrive().get(Calendar.MONTH)+"/";
            date+=vaccination.getAnimal().getDateArrive().get(Calendar.YEAR);
            row.add(date);
            data.add(row);
        }
        return data;
    }

}
