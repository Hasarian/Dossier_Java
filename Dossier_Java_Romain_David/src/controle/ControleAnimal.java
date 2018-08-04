package controle;

import business.AnimalBusiness;
import erreurs.Erreur;
import modèle.Animal;
import modèle.SoinMedical;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ControleAnimal {
    private AnimalBusiness animalBusiness;

    public ControleAnimal()throws Erreur{
        animalBusiness = new AnimalBusiness();
    }
    public String[] getTableauStringAnimaux() throws Erreur
    {
        String[] animals= new String[animalBusiness.getTousLesAnimaux().size()];
        int i = 0;
        for (Animal animal: animalBusiness.getTousLesAnimaux()) {
            animals[i] = animal.getId()+":"+animal.getNomAnimal();
            i++;
        }
        return animals;
    }
    public ArrayList<ArrayList<Object >> getSoinParAnimal(Integer id) throws Erreur {
        ArrayList<ArrayList<Object>> soinMedicals = new ArrayList<ArrayList<Object>>();
        for (SoinMedical soinMedical: animalBusiness.getSoinPourUnAnimal(id,null)) {
            ArrayList<Object> row = new ArrayList<Object>();
            row.add(soinMedical.getNumOrdonnance());
            row.add(soinMedical.getIdSoinMedical());
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
    public Animal getAnimal(int id ) throws Erreur {
        return animalBusiness.getAnimal(id);
    }
    public ArrayList<ArrayList<Object>> getAnimauxEntreDates(GregorianCalendar dateDebTemp, GregorianCalendar dateFinTemp) throws Erreur
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
