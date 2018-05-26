package uIController;

import Business.ListeAnimalBusiness;
import Model.Animal;
import erreurs.BDConnexionErreur;
import erreurs.ErreurrNull;
import erreurs.SoignantInexistant;
import sun.awt.image.BadDepthException;

import java.util.ArrayList;

public class ListesAnimauxController
{
    private static ListesAnimauxController instance;
    private ListeAnimalBusiness listeAnimalBusiness;

    private ListesAnimauxController(SoignantController user) throws BDConnexionErreur, ErreurrNull, SoignantInexistant
    {
        listeAnimalBusiness = ListeAnimalBusiness.obtenirListAnimalBusiness(user);
    }
    public static ListesAnimauxController obtenirListController(SoignantController user) throws BDConnexionErreur, ErreurrNull, SoignantInexistant
    {
        if(instance==null)instance=new ListesAnimauxController(user);
        return instance;
    }
    public String getColumn(int column,Animal animal)
    {
        switch(column)
        {
            case 0:
                return animal.getNomAnimal();
            case 1:
                return animal.getId().toString();
            case 2:
                return animal.getNumeroCellule();
            case 3:
                return animal.getEspece();
            default:
                return "unkown data";
        }
    }
    public String getDonneeDansListeDisponible(int row, int column)
    {
        Animal animal= listeAnimalBusiness.getListeDisponible().get(row);
        return getColumn(column,animal);
    }
    public int nombreDeLignesListeDisponible(){return listeAnimalBusiness.getListeDisponible().size();}

    public String getDonneeDansListeReservee(int row, int column)
    {
        return getColumn(column, listeAnimalBusiness.getListePersonnelle().get(row));
    }
    public int nombreDeLignesListeReservee(){return listeAnimalBusiness.getListePersonnelle().size();}

    public void remplirData(ArrayList<Animal> listeAnimal,ArrayList<ArrayList<String>> data)
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
    }
    public boolean aucunAnimalReserve()
    {
        return listeAnimalBusiness.getListePersonnelle().size()==0;
    }
    public String getDonneeListeVetoDispo(int row, int column)
    {
        return getColumn(column, listeAnimalBusiness.getListeDisponibleVeto().get(row));
    }
    public int nombreLignesListeVetoDispo(){return listeAnimalBusiness.getListeDisponibleVeto().size();}

    public String getDonneeListVetoReservee(int row, int column)
    {
        return getColumn(column, listeAnimalBusiness.getListePersonnelleVeto().get(row));
    }
    public int nombreLignesListeVetoReservee(){return listeAnimalBusiness.getListePersonnelleVeto().size();}
    public boolean aucunAniumalReserveDansLaListeVeto()
    {
        return listeAnimalBusiness.getListePersonnelleVeto().size()==0;
    }
    public String getIdDansLaListeDispo(int index)
    {
        return listeAnimalBusiness.getListeDisponible().get(index).getId().toString();
    }
    public String getIdDansLaListeVetoDispo(int index)
    {
        return listeAnimalBusiness.getListeDisponibleVeto().get(index).getId().toString();
    }
    public String getIdDansLaListeVetoReservee(int index)
    {
        return listeAnimalBusiness.getListePersonnelleVeto().get(index).getId().toString();
    }
    public String getIdDansLaListeReservee(int index)
    {
        return listeAnimalBusiness.getListePersonnelle().get(index).getId().toString();
    }
    public void selectionnerAnimalVeto(Integer id) throws ErreurrNull, BDConnexionErreur
    {
        Animal selected= listeAnimalBusiness.getAnimal(id);
        listeAnimalBusiness.updateEtatFicheSoin(selected, Animal.EtatSoin.VETORESERVEE);
    }
    public void selectionnerAnimal(Integer id) throws ErreurrNull, BDConnexionErreur
    {
        Animal selected= listeAnimalBusiness.getAnimal(id);
        listeAnimalBusiness.updateEtatFicheSoin(selected, Animal.EtatSoin.RESERVEE);
    }
    public void abandonnerAnimalVeto(Integer id) throws ErreurrNull, BDConnexionErreur
    {
        Animal selected= listeAnimalBusiness.getAnimal(id);
        listeAnimalBusiness.updateEtatFicheSoin(selected,Animal.EtatSoin.VETODISPO);
        listeAnimalBusiness.ajoutAnimal(selected);
    }
    public void abandonnerAnimal(Integer id) throws ErreurrNull, BDConnexionErreur
    {
        Animal selected= listeAnimalBusiness.getAnimal(id);
       listeAnimalBusiness.updateEtatFicheSoin(selected,Animal.EtatSoin.DISPONIBLE);
       //listeAnimalBusiness.ajoutAnimal(selected);
    }
    public Animal getanimal(Integer id)
    {
        return listeAnimalBusiness.getAnimal(id);
    }
}
