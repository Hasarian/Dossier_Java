package Business;

import DataAccess.DAO.DAORechercheSoinEffectue;
import DataAccess.DAO.DAOSoignant;
import DataAccess.SoignantDataAccess;
import DataAccess.SoinParSoignant;
import Model.SoinEffectue;
import Model.SoinMedical;
import Model.Veterinaire;
import erreurs.*;
import Model.Soignant;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class SoignantBusiness {
    private Soignant utilisateurCourant;
    private DAOSoignant daoSoignant;
    private ArrayList<Soignant> autresUtilisateurs;
    private static SoignantBusiness instance;
    private DAORechercheSoinEffectue accesSoins;

    private SoignantBusiness() throws BDConnexionErreur {
        setDaoSoignant();
        accesSoins =new SoinParSoignant ();
        autresUtilisateurs =new ArrayList<Soignant>();
    }
    public static SoignantBusiness otebnirSoignantBusiness() throws BDConnexionErreur
    {
        if(instance==null)
        {
            instance= new SoignantBusiness();
        }
            return instance;
    }

    public void setDaoSoignant() throws BDConnexionErreur {
        this.daoSoignant = new SoignantDataAccess();
    }

    public void setSoignantData(Soignant Soignant) throws ErreurInsertionSoignant {
        daoSoignant.create(Soignant);
    }

    public void setUtilisateurCourant(Soignant utilisateurCourant) {
        this.utilisateurCourant = utilisateurCourant;
    }

    public Soignant getUtilisateurCourantDansLaBD(String id) throws BDConnexionErreur,SoignantInexistant, ErreurrNull
    {
        setUtilisateurCourant(daoSoignant.read(id));
        return utilisateurCourant;
    }

    public Soignant getUtilisateurCourant() {
        return utilisateurCourant;
    }

    public String getNomFamilleUtilisateurCourant()
    {
        return utilisateurCourant.getNomDeFamille();
    }
    public String getPrenomUtilisateurCourant()
    {
        return utilisateurCourant.getPrenom();
    }
    public String getMailUtilisateurCourant()
    {
        return utilisateurCourant.getMail();
    }
    public boolean estVeterinaire()
    {
        return utilisateurCourant instanceof Veterinaire ;
    }
    public Soignant getUtilisateurParMail(String mail) throws SoignantInexistant, BDConnexionErreur, ErreurrNull
    {
        if(mail==null)return null;
        if(estConnu(mail))
        {
            for (Soignant utilisateur : autresUtilisateurs) {
                if (utilisateur.getMail().equals(mail)) return utilisateur;
            }
            return utilisateurCourant;
        }
        else
            {
                Soignant utilisateur= daoSoignant.read(mail);
                autresUtilisateurs.add(utilisateur);
                return utilisateur;
            }
    }
    public void getUtilisateurParEmailDansLaBD(String mail) throws SoignantInexistant, BDConnexionErreur, ErreurrNull
    {
        Soignant utilisateur=daoSoignant.read(mail);
        if(utilisateur.getMail().compareTo(utilisateurCourant.getMail())==0) utilisateurCourant =utilisateur;
        else
        {
            if(estConnu(mail)) autresUtilisateurs.remove(getUtilisateurParMail(mail));
            autresUtilisateurs.add(utilisateur);
        }
    }

    public boolean estConnu(String mail)
    {
            for (Soignant utilisateur : autresUtilisateurs) {
                if (utilisateur.getMail().equals(mail)) return true; }
        return utilisateurCourant.getMail().equals(mail);
    }


    public ArrayList<SoinEffectue> getSoinsEffectues(String mail) throws BDConnexionErreur, ErreurrNull,SoignantInexistant,MauvaiseTailleString
    {
        return accesSoins.searchHistory(mail);
    }

    public ArrayList<String> getTousLesUtilisateurs() throws BDConnexionErreur {
        return daoSoignant.readallMails();
    }
    public void updateUtilisateurCourantDansLaBD(String nameTexte, String lastNameTexte, Integer tel, Integer houseNumber, String noteTexte, String streetTexte)
    throws ErreurrNull, BDConnexionErreur
    {
        utilisateurCourant.setPrenom(nameTexte);
        utilisateurCourant.setNomDeFamille(lastNameTexte);
        utilisateurCourant.setNumTel(tel);
        utilisateurCourant.setNumMaison(houseNumber);
        utilisateurCourant.setRemarque(noteTexte);
        utilisateurCourant.setRue(streetTexte);
        daoSoignant.update(utilisateurCourant);
    }
    public void supprimerUtilisateur(String mail) throws SuppressionUtilisateurCourant,SoignantInexistant, ErreurrNull, BDConnexionErreur
    {
        if(mail.equals(utilisateurCourant.getMail())) throw new SuppressionUtilisateurCourant();
        else
        {
            if (estConnu(mail)) autresUtilisateurs.remove(getUtilisateurParMail(mail));
            daoSoignant.delete(mail);
        }
    }
    public void supprimerUtilisateurCourant() throws BDConnexionErreur
    {
        daoSoignant.delete(utilisateurCourant.getMail());
    }

    public void creerSoin(GregorianCalendar dateHeure, SoinMedical soinEffectue,String remarque) throws BDConnexionErreur
    {
        accesSoins.create(utilisateurCourant.getMail(),dateHeure,soinEffectue.getIdSoinMedical(),remarque);
    }
}

