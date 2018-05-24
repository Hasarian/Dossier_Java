package uIController;

import Business.ListeAnimalBusiness;
import Model.Animal;
import Model.SoinMedical;
import erreurs.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TacheController {
    Animal animal;
    ArrayList<SoinMedical> listeTaches;

    public TacheController(Integer id) throws BDConnexionErreur, ErreurrNull, SoignantInexistant {
        ListeAnimalBusiness business = ListeAnimalBusiness.obtenirListAnimalBusiness(new SoignantController());
        animal = business.getAnimal(id);
        listeTaches = business.obtenirSoinParAnimal(id);
    }
    public TacheController() throws ErreurrNull
    {
        animal=new Animal();
        listeTaches=ListeAnimalBusiness.obtenirListTachesTest();
    }

    public int nbTaches() {
        return listeTaches.size();
    }

    public String getDescriptionTache(int i) throws NombreInvalideException {
        if (i < 0 || i >= listeTaches.size())
            throw new NombreInvalideException(i, "le nombre doit se trouver entre " + 0 + " et "
                    + listeTaches.size() + " pour éviter de sortir du tableau");
        return listeTaches.get(i).getDescriptionSoin();
    }

    public String getDateHeure(int i) throws NombreInvalideException {
        if (i < 0 || i >= listeTaches.size())
            throw new NombreInvalideException(i, "le nombre doit se trouver entre " + 0 + " et "
                    + listeTaches.size() + " pour éviter de sortir du tableau");
        GregorianCalendar date = listeTaches.get(i).getDate();
        GregorianCalendar heure = listeTaches.get(i).getHeure();

        String dateExec = new String();
        dateExec = date.get(Calendar.DAY_OF_MONTH) + "/";
        dateExec += (date.get(Calendar.MONTH) + 1) + "/";
        dateExec += date.get(Calendar.YEAR);

        if (heure != null) {
            dateExec += " " + heure.get(Calendar.HOUR_OF_DAY);
            dateExec+=(heure.get(Calendar.MINUTE)<10)?":0":":";
            dateExec += heure.get(Calendar.MINUTE);
        }
        return dateExec;
    }

    public String getRemarque(int i) throws NombreInvalideException
    {
        if (i < 0 || i >= listeTaches.size())
            throw new NombreInvalideException(i, "le nombre doit se trouver entre " + 0 + " et "
                    + listeTaches.size() + " pour éviter de sortir du tableau");
        return listeTaches.get(i).getRemarque();
    }
    public Boolean getDanger()
    {
        return animal.getEstDangereux();
    }
    public String getRemarqueGeneraleSoin()
    {
        return animal.getRemaqueSoin();
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
    public String getRemarqueAnimal()
    {
        return ( animal.getRemarqueAnimal()==null)?"pas de remarque spécifique": animal.getRemarqueAnimal();
    }
    public String getNumCell()
    {
        return animal.getNumeroCellule();
    }
    public String getId(){return animal.getId().toString();}
    public String getEspece(){return animal.getRace().getEspeceLibelle();}
    public String getRace(){return animal.getRace().getLibelle();}

}
