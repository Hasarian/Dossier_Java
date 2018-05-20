package uIController;

import Business.AnimalBusiness;
import Business.ListeAnimalBusiness;
import Model.Animal;
import Model.SoinMedical;
import erreurs.BDConnexionErreur;
import erreurs.ErreurrNull;
import erreurs.SoignantInexistant;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class AnimalController {
    private AnimalBusiness animalBusiness;

    public  AnimalController()throws BDConnexionErreur, ErreurrNull, SoignantInexistant {
        animalBusiness = AnimalBusiness.obtenirAnimalBusiness(ListeAnimalBusiness.obtenirListAnimalBusiness(new SoignantController()));
    }
    public String[] getTableauStringAnimaux(){
        String[] animals= new String[animalBusiness.getTousLesAnimaux().size()];
        int i = 0;
        for (Animal animal: animalBusiness.getTousLesAnimaux()) {
            animals[i] = animal.getId()+":"+animal.getNomAnimal();
            i++;
        }
        return animals;
    }
    public ArrayList<ArrayList<String >> getSoinParAnimal(Integer id) throws BDConnexionErreur, ErreurrNull {
        ArrayList<ArrayList<String>> soinMedicals = new ArrayList<ArrayList<String>>();
        for (SoinMedical soinMedical: animalBusiness.getSoinPourUnAnimal(id)) {
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
    public Animal getAnimal(int id ) throws BDConnexionErreur, ErreurrNull, SoignantInexistant {
        return animalBusiness.getAnimalDansLaBD(id);
    }
    public ArrayList<ArrayList<String>> getAnimauxEntreDates(GregorianCalendar dateDebTemp, GregorianCalendar dateFinTemp) throws BDConnexionErreur, ErreurrNull, SoignantInexistant
    {
        ArrayList<Animal> animaux =animalBusiness.getAnimauxEntreDates(dateDebTemp,dateFinTemp);
        ArrayList<ArrayList<String>> data=new ArrayList<ArrayList<String>>();
        for(Animal animal: animaux)
        {

            ArrayList<String> row=new ArrayList<String>();
            /*"Nom", "est dangereux", "num cellule", "espece", "race", "date arrive"*/
            row.add(animal.getNomAnimal());
            if(animal.getEstDangereux()) row.add("oui");
            else row.add("non");
            row.add(animal.getNumeroCellule());
            row.add(animal.getEspece());
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
