package business;

import erreurs.Erreur;
import model.Animal;
import model.SoinMedical;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class ListeAnimalBusiness {
    private AnimalBusiness animalBusiness;
    private SoignantBusiness utilisateurBusiness;
    public ListeAnimalBusiness() throws Erreur {

        this.utilisateurBusiness=new SoignantBusiness();
        animalBusiness=new AnimalBusiness();
    }
    public ArrayList<Animal> getListeDisponible() throws Erreur {
        ArrayList<Animal> animaux=animalBusiness.getTousLesAnimaux();
        ArrayList<Animal> animauxDispo=new ArrayList<Animal>();
        for(Animal animal:animaux) if(animal.getEtatFicheSoin()== Animal.EtatSoin.DISPONIBLE) animauxDispo.add(animal);
        return animauxDispo;
    }

    public ArrayList<Animal> obtenirTousLesAnimaux() throws Erreur {
        return animalBusiness.getTousLesAnimaux();

    }

    public ArrayList<Animal> getListeDisponibleVeto() throws Erreur {
        ArrayList<Animal> animaux = animalBusiness.getTousLesAnimaux();
        ArrayList<Animal> animauxDispo = new ArrayList<Animal>();
        for (Animal animal : animaux)
            if (animal.getEtatFicheSoin() == Animal.EtatSoin.VETODISPO) animauxDispo.add(animal);
        return animauxDispo;
    }

    public Animal getAnimal(Integer id) throws Erreur
    {
       return animalBusiness.getAnimal(id);
    }


    public void updateEtatFicheSoin(String utilisateur,Animal animal, Animal.EtatSoin nouvelEtat) throws Erreur
    {
        animal.setEtatFicheSoin(nouvelEtat);
        if(nouvelEtat== Animal.EtatSoin.RESERVEE||nouvelEtat==Animal.EtatSoin.VETORESERVEE)
            animal.setSoignant(utilisateurBusiness.getUtilisateurParMail(utilisateur));
        else if(nouvelEtat==Animal.EtatSoin.DISPONIBLE||nouvelEtat== Animal.EtatSoin.VETODISPO) animal.setSoignant(null);
        animalBusiness.animalUpdate(animal);
    }
    public ArrayList<SoinMedical> obtenirSoinParAnimal(Integer id, GregorianCalendar calendar) throws Erreur
    {
        return animalBusiness.getSoinPourUnAnimal(id,calendar);
    }
}
