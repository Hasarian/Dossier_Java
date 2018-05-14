package Business;

import Model.Espece;
import Model.Race;
import erreurs.ErrorNull;

import java.util.ArrayList;

public class ListEspeceBusiness
{
    private ArrayList<Espece> especes;
    private ArrayList<Race> races;
    private static ListEspeceBusiness instance;
    private ListEspeceBusiness()
    {
        especes=new ArrayList<Espece>();
        races=new ArrayList<Race>();
    }
    public static ListEspeceBusiness obtenirEspeceBusiness()
    {
        if(instance==null)instance=new ListEspeceBusiness();
        return instance;
    }
    public Espece obtenirEspece(String libelle,boolean estEnVoieDeDisparition,String typeDeplacement,String milieuDeVie) throws ErrorNull
    {
        int i=0;
        Espece espece;
        while(i<especes.size()&&libelle.compareTo(especes.get(i).getLibelle())!=0)
        {
            i++;
        }
        if(i==especes.size()){
            espece=new Espece(libelle,estEnVoieDeDisparition,typeDeplacement,milieuDeVie);
            especes.add(espece);
        } else espece=especes.get(i);
        return espece;

    }
    public Race obtenirRace(String libelle,String traitDeCaractere,String tare,String caracteristiquesDuMillieuDeVie,Espece espece) throws ErrorNull
    {
       int i=0;
       Race race;
       while(i<races.size()&&libelle.compareTo(races.get(i).getLibelle())!=0)
       {
           i++;
       }
       if(i==especes.size()){
           race=new Race(libelle,traitDeCaractere,tare,caracteristiquesDuMillieuDeVie,espece);
           races.add(race);
       }else race=races.get(i);
       return race;
    }
}
