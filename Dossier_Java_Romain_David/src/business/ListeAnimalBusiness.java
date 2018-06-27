package business;

import model.Animal;
import model.SoinMedical;
import erreurs.DonneePermanenteErreur;
import erreurs.ErreurrNull;
import erreurs.SoignantInexistant;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class ListeAnimalBusiness {
    private AnimalBusiness animalBusiness;
    private SoignantBusiness utilisateurBusiness;
    public ListeAnimalBusiness() throws DonneePermanenteErreur, ErreurrNull, SoignantInexistant {

        this.utilisateurBusiness=new SoignantBusiness();
        animalBusiness=new AnimalBusiness();
        animalBusiness.init();


    }
    public ArrayList<Animal> getListeDisponible() throws DonneePermanenteErreur,ErreurrNull,SoignantInexistant {
        ArrayList<Animal> animaux=animalBusiness.getTousLesAnimaux();
        ArrayList<Animal> animauxDispo=new ArrayList<Animal>();
        for(Animal animal:animaux) if(animal.getEtatFicheSoin()== Animal.EtatSoin.DISPONIBLE) animauxDispo.add(animal);
        return animauxDispo;
    }

    public ArrayList<Animal> getListePersonnelle() throws DonneePermanenteErreur,ErreurrNull,SoignantInexistant {
        ArrayList<Animal> animaux=animalBusiness.getTousLesAnimaux();
        ArrayList<Animal> animauxReserves=new ArrayList<Animal>();
        for(Animal animal:animaux) if(animal.getEtatFicheSoin()== Animal.EtatSoin.RESERVEE&&animal.getSoignant().getMail().equals(utilisateurBusiness.getMailUtilisateurCourant())) animauxReserves.add(animal);
        return animauxReserves;
    }

    public ArrayList<Animal> getListeDisponibleVeto() throws DonneePermanenteErreur,ErreurrNull,SoignantInexistant {
        ArrayList<Animal> animaux = animalBusiness.getTousLesAnimaux();
        ArrayList<Animal> animauxDispo = new ArrayList<Animal>();
        for (Animal animal : animaux)
            if (animal.getEtatFicheSoin() == Animal.EtatSoin.VETODISPO) animauxDispo.add(animal);
        return animauxDispo;
    }

    public ArrayList<Animal> getListePersonnelleVeto() throws DonneePermanenteErreur,ErreurrNull,SoignantInexistant {
            ArrayList<Animal> animaux = animalBusiness.getTousLesAnimaux();
            ArrayList<Animal> animauxReserves = new ArrayList<Animal>();
            for (Animal animal : animaux)
                if (animal.getEtatFicheSoin() == Animal.EtatSoin.VETORESERVEE && animal.getSoignant().getMail().equals(utilisateurBusiness.getMailUtilisateurCourant()))
                    animauxReserves.add(animal);
            return animauxReserves;
        }


    public Animal getAnimal(Integer id) throws DonneePermanenteErreur,ErreurrNull,SoignantInexistant
    {
       return animalBusiness.getAnimal(id);
    }


    public void updateEtatFicheSoin(Animal animal, Animal.EtatSoin nouvelEtat) throws ErreurrNull, DonneePermanenteErreur,SoignantInexistant
    {
        animal.setEtatFicheSoin(nouvelEtat);
        if(nouvelEtat== Animal.EtatSoin.RESERVEE||nouvelEtat==Animal.EtatSoin.VETORESERVEE)
            animal.setSoignant(utilisateurBusiness.getUtilisateurCourant());
        else if(nouvelEtat==Animal.EtatSoin.DISPONIBLE||nouvelEtat== Animal.EtatSoin.VETODISPO) animal.setSoignant(null);
        animalBusiness.animalUpdate(animal);
    }
    public ArrayList<SoinMedical> obtenirSoinParAnimal(Integer id, GregorianCalendar calendar) throws DonneePermanenteErreur,ErreurrNull,SoignantInexistant
    {
        return animalBusiness.getSoinPourUnAnimal(id,calendar);
    }
    public static ArrayList<SoinMedical> obtenirListTachesTest() throws ErreurrNull
    {
        ArrayList<SoinMedical> testArray=new ArrayList<SoinMedical>();
        for(int i=0;i<5;i++)
        {
            testArray.add(new SoinMedical(i));
        }
        return testArray;
    }
}
