package uIController;

import Business.AnimalBusiness;
import Business.ListeAnimalBusiness;
import Model.Animal;
import Model.SoinMedical;
import erreurs.DonneePermanenteErreur;
import erreurs.ErreurrNull;
import erreurs.SoignantInexistant;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class AnimalController {
    private AnimalBusiness animalBusiness;

    public  AnimalController()throws DonneePermanenteErreur{
        animalBusiness = new AnimalBusiness();
    }
    public String[] getTableauStringAnimaux() throws DonneePermanenteErreur,ErreurrNull,SoignantInexistant
    {
        String[] animals= new String[animalBusiness.getTousLesAnimaux().size()];
        int i = 0;
        for (Animal animal: animalBusiness.getTousLesAnimaux()) {
            animals[i] = animal.getId()+":"+animal.getNomAnimal();
            i++;
        }
        return animals;
    }
    public ArrayList<ArrayList<String >> getSoinParAnimal(Integer id) throws DonneePermanenteErreur, ErreurrNull,SoignantInexistant {
        ArrayList<ArrayList<String>> soinMedicals = new ArrayList<ArrayList<String>>();
        for (SoinMedical soinMedical: animalBusiness.getSoinPourUnAnimal(id,null)) {
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
    public Animal getAnimal(int id ) throws DonneePermanenteErreur, ErreurrNull, SoignantInexistant {
        return animalBusiness.getAnimal(id);
    }
    public ArrayList<ArrayList<Object>> getAnimauxEntreDates(GregorianCalendar dateDebTemp, GregorianCalendar dateFinTemp) throws DonneePermanenteErreur, ErreurrNull, SoignantInexistant
    {
        ArrayList<Animal> animaux =animalBusiness.getAnimauxEntreDates(dateDebTemp,dateFinTemp);
        ArrayList<ArrayList<Object>> data=new ArrayList<ArrayList<Object>>();
        for(Animal animal: animaux)
        {

            ArrayList<Object> row=new ArrayList<Object>();
            /*"Nom", "est dangereux", "num cellule", "espece", "race", "date arrive"*/
            row.add(animal.getNomAnimal());
           row.add(animal.getEstDangereux());
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
