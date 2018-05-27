package Business;

import Model.Animal;
import Model.SoinMedical;
import com.mysql.fabric.xmlrpc.base.Array;
import erreurs.BDConnexionErreur;
import erreurs.ErreurrNull;
import erreurs.SoignantInexistant;
import uIController.SoignantController;

import java.util.ArrayList;

public class ListeAnimalBusiness {
    private ArrayList<Animal> listeDisponible, listePersonnelle, listeDisponibleVeto, listePersonnelleVeto;
    private AnimalBusiness animalBusiness;
    private static ListeAnimalBusiness instance;
    private SoignantController controlleurUtilisateur;
    private ListeAnimalBusiness(SoignantController controlleurUtilisateur) throws BDConnexionErreur, ErreurrNull, SoignantInexistant {

        this.controlleurUtilisateur = controlleurUtilisateur;
        listeDisponible =new ArrayList<Animal>();
        listePersonnelle =new ArrayList<Animal>();
        listeDisponibleVeto =(controlleurUtilisateur.estVeterinaire())?new ArrayList<Animal>():null;
        listePersonnelleVeto =(controlleurUtilisateur.estVeterinaire())?new ArrayList<Animal>():null;
        animalBusiness=AnimalBusiness.obtenirAnimalBusiness(this);
        animalBusiness.init();

    }
    public static ListeAnimalBusiness obtenirListAnimalBusiness(SoignantController user)throws BDConnexionErreur, ErreurrNull, SoignantInexistant
    {
        if(instance==null) instance=new ListeAnimalBusiness(user);
        return instance;
    }

    public ArrayList<Animal> getListeDisponible() {
        return listeDisponible;
    }

    public ArrayList<Animal> getListePersonnelle() { return listePersonnelle; }

    public ArrayList<Animal> getListeDisponibleVeto() { return listeDisponibleVeto; }

    public ArrayList<Animal> getListePersonnelleVeto(){ return listePersonnelleVeto; }


    public Animal getAnimal(Integer id)
    {
       return animalBusiness.getAnimal(id);
    }
    public void retirerAnimal(Integer id)
    {
        Animal animal=getAnimal(id);
        if (animal.getEtatAnimal() != Animal.EtatAnimal.ARCHIVE) {
            switch (animal.getEtatFicheSoin()) {
                case DISPONIBLE:
                    listeDisponible.remove(animal);
                    break;
                case RESERVEE:
                   listePersonnelle.remove(animal);
                    break;
                case VETODISPO:
                        listeDisponibleVeto.remove(animal);
                    break;
                case VETORESERVEE:
                        listePersonnelleVeto.remove(animal);
                    break;
            }
        }
    }

    public void ajoutAnimal(Animal animal)
    {

        if(animalBusiness.existeDeja(animal)) retirerAnimal(animal.getId());
            if (animal.getEtatAnimal() != Animal.EtatAnimal.ARCHIVE) {
                switch (animal.getEtatFicheSoin()) {
                    case DISPONIBLE:
                        listeDisponible.add(animal);
                        break;
                    case RESERVEE:
                        if (animal.estReserveParlUtilisateurCourant(controlleurUtilisateur.getMailUtilisateurCourant())) listePersonnelle.add(animal);
                        break;
                    case VETODISPO:
                        if (listeDisponibleVeto != null)
                            listeDisponibleVeto.add(animal);
                        break;
                    case VETORESERVEE:
                        if (listePersonnelleVeto != null && animal.estReserveParlUtilisateurCourant(controlleurUtilisateur.getMailUtilisateurCourant()))
                            listePersonnelleVeto.add(animal);
                        break;
            }
        }
    }

    public void updateEtatFicheSoin(Animal animal, Animal.EtatSoin nouvelEtat) throws ErreurrNull,BDConnexionErreur
    {
        //retirerAnimal(animal.getId());
        animal.setEtatFicheSoin(nouvelEtat);
        if(nouvelEtat== Animal.EtatSoin.RESERVEE||nouvelEtat==Animal.EtatSoin.VETORESERVEE)
            animal.setSoignant(controlleurUtilisateur.getSoignant());
        else if(nouvelEtat==Animal.EtatSoin.DISPONIBLE||nouvelEtat== Animal.EtatSoin.VETODISPO) animal.setSoignant(null);
        animalBusiness.animalUpdate(animal);
        ajoutAnimal(animal);
    }
    public ArrayList<SoinMedical> obtenirSoinParAnimal(Integer id) throws BDConnexionErreur,ErreurrNull
    {
        return animalBusiness.getSoinPourUnAnimal(id);
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
