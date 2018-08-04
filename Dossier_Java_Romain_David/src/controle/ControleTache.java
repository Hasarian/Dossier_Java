package controle;

import business.ListeAnimalBusiness;
import business.SoignantBusiness;
import erreurs.Erreur;
import erreurs.erreurApplication.SoinsNonEffectues;
import erreurs.erreurFormat.MauvaiseTailleString;
import erreurs.erreurFormat.NombreInvalideException;
import modèle.Animal;
import modèle.SoinMedical;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ControleTache {
    private Integer animalId;
    private ListeAnimalBusiness business;
    public ControleTache(Integer id) throws Erreur {
        business = new ListeAnimalBusiness();
        animalId = business.getAnimal(id).getId();
    }
    public ArrayList<SoinMedical> obtenirSoins()throws Erreur
    {
        return business.obtenirSoinParAnimal(animalId,new GregorianCalendar());
    }
    public Animal obtenirAnimal()throws Erreur
    {
        return business.getAnimal(animalId);
    }
    public int nbTaches()throws Erreur {
        return obtenirSoins().size();
    }

    public String getDescriptionTache(int i) throws Erreur {
        if (i < 0 || i >= nbTaches())
            throw new NombreInvalideException(i, "le nombre doit se trouver entre " + 0 + " et "
                    + nbTaches() + " pour éviter de sortir du tableau");
        return obtenirSoins().get(i).getDescriptionSoin();
    }

    public String getDateHeure(int i) throws Erreur{
        if (i < 0 || i >= nbTaches())
            throw new NombreInvalideException(i, "le nombre doit se trouver entre " + 0 + " et "
                    + nbTaches()+ " pour éviter de sortir du tableau");
        SoinMedical soin=obtenirSoins().get(i);
        GregorianCalendar date = soin.getDate();
        GregorianCalendar heure = soin.getHeure();

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

    public String getRemarque(int i) throws Erreur
    {
        if (i < 0 || i >= nbTaches())
            throw new NombreInvalideException(i, "le nombre doit se trouver entre " + 0 + " et "
                    + nbTaches()+ " pour éviter de sortir du tableau");
        return obtenirSoins().get(i).getRemarque();
    }
    public Boolean getDanger()throws Erreur
    {
        return obtenirAnimal().getEstDangereux();
    }
    public String getRemarqueGeneraleSoin()throws Erreur
    {
        return obtenirAnimal().getRemaqueSoin();
    }
    public String getNom()throws Erreur
    {
        return obtenirAnimal().getNomAnimal();
    }
    public Integer getAnimalId()
    {
        //System.out.println(animal);
        return animalId;
    }
    public String getRemarqueAnimal()
            throws Erreur
    {
        return ( obtenirAnimal().getRemarqueAnimal()==null)?"pas de remarque spécifique": obtenirAnimal().getRemarqueAnimal();
    }
    public String getNumCell()throws Erreur
    {
        return obtenirAnimal().getNumeroCellule();
    }
    public String getId()throws Erreur{return obtenirAnimal().getId().toString();}
    public String getEspece()throws Erreur{return obtenirAnimal().getRace().getEspeceLibelle();}
    public String getRace()throws Erreur{return obtenirAnimal().getRace().getLibelle();}

    public void faireSoin(ArrayList<Boolean> ontEteEffectues,ArrayList<String> remarques,Boolean pourVeto)
            throws Erreur
    {
        int nbSoinNonEffectués=0;
        for(int i=0;i<nbTaches();i++)
        {
            if(!ontEteEffectues.get(i)&&remarques.get(i).isEmpty()) nbSoinNonEffectués++;
            if(remarques.get(i).length()>140) throw new MauvaiseTailleString("une remarque ajoutée",remarques.get(i).length(),150);
        }
        if(nbSoinNonEffectués>0) throw new SoinsNonEffectues(nbSoinNonEffectués);
        else
        {
            ArrayList<SoinMedical> soins=obtenirSoins();
            String mailUtilisateurCourant= new ControleSoignant().getMailUtilisateurCourant();
            SoignantBusiness userBusiness=new SoignantBusiness();
            for(int i=0;i<nbTaches();i++) {
                userBusiness.creerSoin(mailUtilisateurCourant,new GregorianCalendar(),soins.get(i),remarques.get(i));
            }
            business.updateEtatFicheSoin(mailUtilisateurCourant,obtenirAnimal(),(pourVeto)? Animal.EtatSoin.VETODISPO: Animal.EtatSoin.DISPONIBLE);
        }

    }

}
