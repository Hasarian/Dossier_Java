package controlle;

import business.ListeAnimalBusiness;
import erreurs.Erreur;
import modèle.Animal;

import java.util.ArrayList;

public class ControleListesAnimaux
{
    private ListeAnimalBusiness listeAnimalBusiness;
    private static ArrayList<Integer> idsDispo;
    private static ArrayList<Integer> idsPerso;
    private static ArrayList<Integer> idsDispoVeto;
    private static ArrayList<Integer> idsPersoVeto;
    public ControleListesAnimaux() throws Erreur
    {
        listeAnimalBusiness=new ListeAnimalBusiness();
        initIds();
    }
    public void initIds() throws Erreur
    {
        idsDispo=new ArrayList<Integer>();
        ArrayList<Animal> animaux=listeAnimalBusiness.getListeDisponible();
        for(Animal animal:animaux) idsDispo.add(animal.getId());

        idsPerso=new ArrayList<Integer>();
        animaux=getListeReservee();
        for(Animal animal:animaux) idsPerso.add(animal.getId());

        idsDispoVeto=new ArrayList<Integer>();
        animaux=listeAnimalBusiness.getListeDisponibleVeto();
        for(Animal animal:animaux) idsDispoVeto.add(animal.getId());

        idsPersoVeto=new ArrayList<Integer>();
        animaux=getListeVetoReservee();
        for (Animal animal:animaux) idsPersoVeto.add(animal.getId());
    }

    public Object getColumn(int column,Animal animal)
    {
        switch(column)
        {
            case 0:
                return animal.getNomAnimal();
            case 1:
                return animal.getId();
            case 2:
                return animal.getNumeroCellule();
            case 3:
                return animal.getEspece();
            case 4:
                return animal.getRace().getLibelle();
            case 5:
                return animal.getEstDangereux();
            default:
                return "unkown data";
        }
    }
    public String mailUtilisateurCourant() throws Erreur{return new ControleSoignant().getMailUtilisateurCourant();}
    public ArrayList<Animal> getListeReservee() throws Erreur
    {
        ArrayList<Animal> animaux=listeAnimalBusiness.obtenirTousLesAnimaux();
        ArrayList<Animal> animauxReserves=new ArrayList<Animal>();
        for(Animal animal:animaux) if(animal.getEtatFicheSoin()== Animal.EtatSoin.RESERVEE&&animal.getSoignant().getMail().equals(mailUtilisateurCourant())) animauxReserves.add(animal);
        return animauxReserves;
    }
    public ArrayList<Animal> getListeVetoReservee() throws Erreur
    {
        ArrayList<Animal> animaux = listeAnimalBusiness.obtenirTousLesAnimaux();
        ArrayList<Animal> animauxReserves = new ArrayList<Animal>();
        for (Animal animal : animaux)
            if (animal.getEtatFicheSoin() == Animal.EtatSoin.VETORESERVEE && animal.getSoignant().getMail().equals(mailUtilisateurCourant()))
                animauxReserves.add(animal);
        return animauxReserves;
    }
    public Object getDonneeDansListeDisponible(int row, int column) throws Erreur
    {
        initIds();
        Animal animal= listeAnimalBusiness.getAnimal(idsDispo.get(row));
        return getColumn(column,animal);
    }
    public int nombreDeLignesListeDisponible()throws Erreur{return listeAnimalBusiness.getListeDisponible().size();}

    public Object getDonneeDansListeReservee(int row, int column)throws Erreur
    {
        return getColumn(column, getListeReservee().get(row));
    }
    public int nombreDeLignesListeReservee()throws Erreur{return getListeReservee().size();}

    /*public void remplirData(ArrayList<Animal> listeAnimal,ArrayList<ArrayList<String>> data)
    {
        for(Animal animal:listeAnimal)
        {
            ArrayList<String> row=new ArrayList<String>();
            row.add(animal.getNomAnimal());
            row.add(animal.getId().toString());
            row.add(animal.getNumeroCellule());
            row.add(animal.getEspece());
            data.add(row);
        }
    }*/
    public boolean aucunAnimalReserve()throws Erreur
    {
        return getListeReservee().size()==0;
    }
    public Object getDonneeListeVetoDispo(int row, int column)throws Erreur
    {
        initIds();
        return getColumn(column, listeAnimalBusiness.getAnimal(idsDispoVeto.get(row)));
    }
    public int nombreLignesListeVetoDispo()
            throws Erreur
    {return listeAnimalBusiness.getListeDisponibleVeto().size();}

    public Object getDonneeListVetoReservee(int row, int column)throws Erreur
    {
        return getColumn(column, getListeVetoReservee().get(row));
    }
    public int nombreLignesListeVetoReservee()throws Erreur{return getListeVetoReservee().size();}
    public boolean aucunAniumalReserveDansLaListeVeto()throws Erreur
    {
        return getListeVetoReservee().size()==0;
    }
    public Integer getIdDansLaListeDispo(int index)
    {
        return idsDispo.get(index);
    }
    public Integer getIdDansLaListeVetoDispo(int index)
    {
        return idsDispoVeto.get(index);
    }
    public Integer getIdDansLaListeVetoReservee(int index)
    {
        return idsPersoVeto.get(index);
    }
    public Integer getIdDansLaListeReservee(int index)
    {
        return idsPerso.get(index);
    }
    public void selectionnerAnimalVeto(Integer index) throws Erreur
    {
        Animal selected= listeAnimalBusiness.getAnimal(idsDispoVeto.get(index));
        listeAnimalBusiness.updateEtatFicheSoin(mailUtilisateurCourant(),selected, Animal.EtatSoin.VETORESERVEE);
        initIds();
    }
    public void selectionnerAnimal(Integer index) throws Erreur
    {
        Animal selected= listeAnimalBusiness.getAnimal(idsDispo.get(index));
        listeAnimalBusiness.updateEtatFicheSoin(mailUtilisateurCourant(),selected, Animal.EtatSoin.RESERVEE);
        initIds();
    }
    public void abandonnerAnimalVeto(Integer index) throws Erreur
    {
        Animal selected= listeAnimalBusiness.getAnimal(idsPersoVeto.get(index));
        listeAnimalBusiness.updateEtatFicheSoin(mailUtilisateurCourant(),selected,Animal.EtatSoin.VETODISPO);
        initIds();
    }
    public void abandonnerAnimal(Integer index) throws Erreur
    {
        Animal selected= listeAnimalBusiness.getAnimal(idsPerso.get(index));
       listeAnimalBusiness.updateEtatFicheSoin(mailUtilisateurCourant(),selected,Animal.EtatSoin.DISPONIBLE);
       initIds();
    }
    public Animal getanimal(Integer id)throws Erreur
    {
        return listeAnimalBusiness.getAnimal(id);
    }
}
