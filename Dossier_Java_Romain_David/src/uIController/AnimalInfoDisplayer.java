package uIController;

import Model.Animal;
import erreurs.ErrorNull;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class AnimalInfoDisplayer
{
    Animal animal;
    public AnimalInfoDisplayer(Integer animalId,ListsControllerAnimal list){

        this.animal=list.getanimal(animalId);
    }
    public String getNom()
    {
        return animal.getNomAnimal();
    }
    public Integer getAnimalId()
    {
       //System.out.println(animal);
        return animal.getId();
    }
    public String getRemarque()
    {
        return ( animal.getRemarqueAnimal()==null)?"pas de remarque spécifique": animal.getRemarqueAnimal();
    }
    public String getNumCell()
    {
        return animal.getCellNumber();
    }
    public String getRace()
    {
        return animal.getSpecies();
    }

    public String getDateArrivee()
    {
        GregorianCalendar date=animal.getDateArrive();
        String retour = new String();
        retour+= date.get(Calendar.DAY_OF_MONTH)+"/";
        retour+= date.get(Calendar.MONTH)+"/";
        retour+= date.get(Calendar.YEAR);
        return retour;
    }
    public String getDatDeces()
    {
        GregorianCalendar date=animal.getDateDesces();
        String retour = new String();
        if(date!=null) {

            retour += date.get(Calendar.DAY_OF_MONTH) + "/";
            retour += date.get(Calendar.MONTH) + "/";
            retour += date.get(Calendar.YEAR);
        }else retour="toujours vivant";
        return retour;
    }
     /*
    private String remaqueSoin;*/
     public String getDanger()
     {
         return (animal.getEstDangereux())?"présente un danger":"ne présente aucun danger";
     }
     public String getEtatSoin()
     {
         switch(animal.getEtatFicheSoin())
         {
             case VETORESERVEE:case RESERVEE:
                 return "soignant actuel: "+animal.getCareGiver().toString();
             case VETODISPO:case DISPONIBLE:
                 return "les soins de cet animal n'ont pas encore été effectués";
             case ARCHIVEE:default:
                 return "l'animal n'est actuellement pas dans la SPA";
         }
     }
     public String getRemarqueSoin()
     {
         return animal.getRemaqueSoin();
     }
}
