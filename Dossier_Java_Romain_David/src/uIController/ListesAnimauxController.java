package uIController;

import Business.ListeAnimalBusiness;
import Model.Animal;
import erreurs.DonneePermanenteErreur;
import erreurs.ErreurrNull;
import erreurs.SoignantInexistant;

public class ListesAnimauxController
{
    private ListeAnimalBusiness listeAnimalBusiness;
    public ListesAnimauxController() throws DonneePermanenteErreur,ErreurrNull,SoignantInexistant
    { listeAnimalBusiness=new ListeAnimalBusiness();}

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
    public Object getDonneeDansListeDisponible(int row, int column) throws DonneePermanenteErreur,ErreurrNull,SoignantInexistant
    {
        Animal animal= listeAnimalBusiness.getListeDisponible().get(row);
        return getColumn(column,animal);
    }
    public int nombreDeLignesListeDisponible()throws DonneePermanenteErreur,ErreurrNull,SoignantInexistant{return listeAnimalBusiness.getListeDisponible().size();}

    public Object getDonneeDansListeReservee(int row, int column)throws DonneePermanenteErreur,ErreurrNull,SoignantInexistant
    {
        return getColumn(column, listeAnimalBusiness.getListePersonnelle().get(row));
    }
    public int nombreDeLignesListeReservee()throws DonneePermanenteErreur,ErreurrNull,SoignantInexistant{return listeAnimalBusiness.getListePersonnelle().size();}

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
    public boolean aucunAnimalReserve()throws DonneePermanenteErreur,ErreurrNull,SoignantInexistant
    {
        return listeAnimalBusiness.getListePersonnelle().size()==0;
    }
    public Object getDonneeListeVetoDispo(int row, int column)throws DonneePermanenteErreur,ErreurrNull,SoignantInexistant
    {
        return getColumn(column, listeAnimalBusiness.getListeDisponibleVeto().get(row));
    }
    public int nombreLignesListeVetoDispo()throws DonneePermanenteErreur,ErreurrNull,SoignantInexistant{return listeAnimalBusiness.getListeDisponibleVeto().size();}

    public Object getDonneeListVetoReservee(int row, int column)throws DonneePermanenteErreur,ErreurrNull,SoignantInexistant
    {
        return getColumn(column, listeAnimalBusiness.getListePersonnelleVeto().get(row));
    }
    public int nombreLignesListeVetoReservee()throws DonneePermanenteErreur,ErreurrNull,SoignantInexistant{return listeAnimalBusiness.getListePersonnelleVeto().size();}
    public boolean aucunAniumalReserveDansLaListeVeto()throws DonneePermanenteErreur,ErreurrNull,SoignantInexistant
    {
        return listeAnimalBusiness.getListePersonnelleVeto().size()==0;
    }
    public String getIdDansLaListeDispo(int index)throws DonneePermanenteErreur,ErreurrNull,SoignantInexistant
    {
        return listeAnimalBusiness.getListeDisponible().get(index).getId().toString();
    }
    public String getIdDansLaListeVetoDispo(int index)throws DonneePermanenteErreur,ErreurrNull,SoignantInexistant
    {
        return listeAnimalBusiness.getListeDisponibleVeto().get(index).getId().toString();
    }
    public String getIdDansLaListeVetoReservee(int index)throws DonneePermanenteErreur,ErreurrNull,SoignantInexistant
    {
        return listeAnimalBusiness.getListePersonnelleVeto().get(index).getId().toString();
    }
    public String getIdDansLaListeReservee(int index)throws DonneePermanenteErreur,ErreurrNull,SoignantInexistant
    {
        return listeAnimalBusiness.getListePersonnelle().get(index).getId().toString();
    }
    public void selectionnerAnimalVeto(Integer id) throws ErreurrNull, DonneePermanenteErreur,SoignantInexistant
    {
        Animal selected= listeAnimalBusiness.getAnimal(id);
        listeAnimalBusiness.updateEtatFicheSoin(selected, Animal.EtatSoin.VETORESERVEE);
    }
    public void selectionnerAnimal(Integer id) throws ErreurrNull, DonneePermanenteErreur,SoignantInexistant
    {
        Animal selected= listeAnimalBusiness.getAnimal(id);
        listeAnimalBusiness.updateEtatFicheSoin(selected, Animal.EtatSoin.RESERVEE);
    }
    public void abandonnerAnimalVeto(Integer id) throws ErreurrNull, DonneePermanenteErreur,SoignantInexistant
    {
        Animal selected= listeAnimalBusiness.getAnimal(id);
        listeAnimalBusiness.updateEtatFicheSoin(selected,Animal.EtatSoin.VETODISPO);
    }
    public void abandonnerAnimal(Integer id) throws ErreurrNull, DonneePermanenteErreur,SoignantInexistant
    {
        Animal selected= listeAnimalBusiness.getAnimal(id);
       listeAnimalBusiness.updateEtatFicheSoin(selected,Animal.EtatSoin.DISPONIBLE);
    }
    public Animal getanimal(Integer id)throws DonneePermanenteErreur,ErreurrNull,SoignantInexistant
    {
        return listeAnimalBusiness.getAnimal(id);
    }
}
