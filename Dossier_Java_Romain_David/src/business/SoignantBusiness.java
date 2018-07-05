package business;

import dataAccess.dao.DAORechercheSoinEffectue;
import dataAccess.dao.DAOSoignant;
import dataAccess.SoignantDataAccess;
import dataAccess.SoinParSoignant;
import erreurs.Erreur;
import erreurs.erreursExternes.DonneePermanenteErreur;
import model.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class SoignantBusiness {
    private DAOSoignant daoSoignant;
    private DAORechercheSoinEffectue accesSoins;

    public SoignantBusiness()
    throws DonneePermanenteErreur
    {
        setDaoSoignant();
        accesSoins =new SoinParSoignant ();
    }
    public void setDaoSoignant() throws DonneePermanenteErreur {
        this.daoSoignant = new SoignantDataAccess();
    }

    public void setSoignantData(Soignant Soignant) throws Erreur {
        daoSoignant.create(Soignant);
    }



    public Soignant getUtilisateurParMail(String mail) throws Erreur
    {
                return daoSoignant.read(mail);
    }


    public ArrayList<SoinEffectue> getSoinsEffectues(String mail) throws Erreur
    {
        return accesSoins.searchHistory(mail);
    }

    public ArrayList<String> getTousLesMails() throws Erreur {
        return daoSoignant.readallMails();
    }
    public void updateUtilisateurDansLaBD(String ancienmail, String nouveauMail, String nameTexte, String lastNameTexte, Integer tel,Boolean estVolontaire, Integer houseNumber, String noteTexte, String streetTexte, Localite localite,GregorianCalendar dateEmbauche)
    throws Erreur
    {
        Soignant soignant=getUtilisateurParMail(ancienmail);
        soignant.setMail(nouveauMail);
        soignant.setPrenom(nameTexte);
        soignant.setNomDeFamille(lastNameTexte);
        soignant.setNumTel(tel);
        soignant.setEstBenevole(estVolontaire);
        soignant.setNumMaison(houseNumber);
        soignant.setRemarque(noteTexte);
        soignant.setRue(streetTexte);
        soignant.setLocalite(localite);
        soignant.setDateEmbauche(dateEmbauche);
        daoSoignant.update(ancienmail,soignant);
    }
    public void supprimerUtilisateur(String mail) throws Erreur
    {
            daoSoignant.delete(mail);
    }

    public void creerSoin(String mailUtilisateurCourant,GregorianCalendar dateHeure, SoinMedical soinEffectue,String remarque) throws Erreur
    {
        accesSoins.create(mailUtilisateurCourant,dateHeure,soinEffectue.getIdSoinMedical(),remarque);
    }
    public Soignant getSoignantParIndex(int row)  throws Erreur
    {
       return daoSoignant.readTousLesSoignants().get(row);
    }
    public int getNbSoignants()throws Erreur
    {
        return daoSoignant.readTousLesSoignants().size();
    }
}

