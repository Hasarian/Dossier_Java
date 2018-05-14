package Business;

import DataAccess.AnimalDBAccess;
import DataAccess.DAO.DAOAnimal;
import DataAccess.SearchAnimalsBetweenDate;
import Model.*;
import erreurs.BDConnexionError;
import erreurs.ErrorNull;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class AnimalBusiness {
    SearchAnimalsBetweenDate research;

    public AnimalBusiness(){
        System.out.println("animalBusiness créé");
    }

    public ArrayList<SearchAnimalBetweenDate> getAnimalsBetweenDates(GregorianCalendar dateDebut, GregorianCalendar dateFin) throws ErrorNull, BDConnexionError{
        return research.readAnimalsbetweenDates(dateDebut,dateFin);
    }
    public static Animal getAnimal (Integer id, String remarque, Integer numCell, String nomAnimal, Race race, GregorianCalendar dateArrivee,
                               GregorianCalendar dateDeces, Boolean estDangereux, Animal.EtatAnimal etatAnimal,
                               String remarqueSoin, Animal.EtatSoin etatFicheSoin, CareGiver careGiver) throws ErrorNull {
        Animal animal = new Animal(id, remarque, numCell, race, nomAnimal, dateArrivee, dateDeces, estDangereux, etatAnimal, remarqueSoin, etatFicheSoin, careGiver);
        return animal;
    }
    public static Espece getEspece(String libelle,Boolean estEnVoieDeDisparition,String typeDeplacement,String milieuDeVie) throws ErrorNull{
        return new Espece(libelle,estEnVoieDeDisparition,typeDeplacement,milieuDeVie);
    }

    public static Race getRace(String libelle,String traitDeCaractere,String tare,String caracteristiquesDuMillieuDeVie,Espece espece) throws ErrorNull{
        return new Race(libelle,traitDeCaractere,tare,caracteristiquesDuMillieuDeVie,espece);
    }
    public  static Vaccination getVaccination(Animal animal, Vaccin numVaccin, GregorianCalendar date, Integer idVaccination) throws ErrorNull{
        return new Vaccination(animal,numVaccin, date,idVaccination);
    }
    public static Vaccin getVaccin(String libelle, Integer numVaccin) throws ErrorNull{
        return new Vaccin(libelle, numVaccin);
    }
}
