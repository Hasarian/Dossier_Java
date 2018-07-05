package uIController;

import business.AnimalBusiness;
import erreurs.Erreur;
import model.Animal;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class AnimalInfoDisplayer
{
    Integer animalId;
    AnimalBusiness business;
    public AnimalInfoDisplayer(Integer animalId)throws Erreur {
        business=new AnimalBusiness();
        this.animalId=business.getAnimal(animalId).getId();
    }
    public Animal getAnimal()throws Erreur{return business.getAnimal(animalId);}
    public String getNom()throws Erreur
    {
        return getAnimal().getNomAnimal();
    }
    public Integer getAnimalId()
    {
       //System.out.println(animal);
        return animalId;
    }
    public String getRemarque()throws Erreur
    {
        return ( getAnimal().getRemarqueAnimal()==null)?"pas de remarque spécifique": getAnimal().getRemarqueAnimal();
    }
    public String getNumCell()throws Erreur
        {
        return getAnimal().getNumeroCellule();
    }
    public String getRace()throws Erreur
    {
        return getAnimal().getEspece();
    }

    public String getDateArrivee()throws Erreur
    {
        GregorianCalendar date=getAnimal().getDateArrive();
        String retour = new String();
        retour+= date.get(Calendar.DAY_OF_MONTH)+"/";
        retour+= date.get(Calendar.MONTH)+"/";
        retour+= date.get(Calendar.YEAR);
        return retour;
    }
    public String getDatDeces()throws Erreur
    {
        GregorianCalendar date=getAnimal().getDateDeces();
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
     public String getDanger()throws Erreur
     {
         return (getAnimal().getEstDangereux())?"présente un danger":"ne présente aucun danger";
     }
     public String getEtatSoin()throws Erreur
     {
         switch(getAnimal().getEtatFicheSoin())
         {
             case VETORESERVEE:case RESERVEE:
                 return "soignant actuel: "+getAnimal().getSoignant().toString();
             case VETODISPO:case DISPONIBLE:
                 return "les soins de cet animal n'ont pas encore été effectués";
             case ARCHIVEE:default:
                 return "l'animal n'est actuellement pas dans la SPA";
         }
     }
     public String getRemarqueSoin()throws Erreur
     {
         return getAnimal().getRemaqueSoin();
     }
}
