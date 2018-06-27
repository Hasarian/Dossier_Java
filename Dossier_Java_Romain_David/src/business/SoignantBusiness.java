package business;

import dataAccess.dao.DAORechercheSoinEffectue;
import dataAccess.dao.DAOSoignant;
import dataAccess.SoignantDataAccess;
import dataAccess.SoinParSoignant;
import model.*;
import erreurs.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class SoignantBusiness {
    private static String mailUtilisateurCourant;
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

    public void setSoignantData(Soignant Soignant) throws ErreurInsertionSoignant {
        daoSoignant.create(Soignant);
    }

    public void setUtilisateurCourant(String mail) throws SoignantInexistant,DonneePermanenteErreur,ErreurrNull {
        this.mailUtilisateurCourant = daoSoignant.read(mail).getMail();
    }

   public Soignant getSoignantansLaBD(String id) throws SoignantInexistant, DonneePermanenteErreur,ErreurrNull
   {
       return daoSoignant.read(id);
   }

    public Soignant getUtilisateurCourant() throws SoignantInexistant,DonneePermanenteErreur,ErreurrNull{
        return daoSoignant.read(mailUtilisateurCourant);
    }

    public String getNomFamilleUtilisateurCourant()throws SoignantInexistant, DonneePermanenteErreur,ErreurrNull
    {
        return getUtilisateurCourant().getNomDeFamille();
    }
    public String getPrenomUtilisateurCourant()throws SoignantInexistant, DonneePermanenteErreur,ErreurrNull
    {
        return getUtilisateurCourant().getPrenom();
    }
    public String getMailUtilisateurCourant()throws SoignantInexistant, DonneePermanenteErreur,ErreurrNull
    {
        return getUtilisateurCourant().getMail();
    }
    public boolean estVeterinaire()throws SoignantInexistant, DonneePermanenteErreur,ErreurrNull
    {
        return getUtilisateurCourant() instanceof Veterinaire ;
    }
    public Soignant getUtilisateurParMail(String mail) throws SoignantInexistant, DonneePermanenteErreur, ErreurrNull
    {
                return daoSoignant.read(mail);
    }


    public ArrayList<SoinEffectue> getSoinsEffectues(String mail) throws DonneePermanenteErreur, ErreurrNull,SoignantInexistant,MauvaiseTailleString
    {
        return accesSoins.searchHistory(mail);
    }

    public ArrayList<String> getTousLesMails() throws DonneePermanenteErreur {
        return daoSoignant.readallMails();
    }
    public void updateUtilisateurDansLaBD(String ancienmail, String nouveauMail, String nameTexte, String lastNameTexte, Integer tel,Boolean estVolontaire, Integer houseNumber, String noteTexte, String streetTexte, Localite localite,GregorianCalendar dateEmbauche)
    throws ErreurrNull, DonneePermanenteErreur,SoignantInexistant
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
    public void supprimerUtilisateur(String mail) throws SuppressionUtilisateurCourant,DonneePermanenteErreur
    {
        if(mail.equals(mailUtilisateurCourant)) throw new SuppressionUtilisateurCourant();
            daoSoignant.delete(mail);
    }
    public void supprimerUtilisateurCourant() throws DonneePermanenteErreur
    {
        daoSoignant.delete(mailUtilisateurCourant);
    }

    public void creerSoin(GregorianCalendar dateHeure, SoinMedical soinEffectue,String remarque) throws DonneePermanenteErreur
    {
        accesSoins.create(mailUtilisateurCourant,dateHeure,soinEffectue.getIdSoinMedical(),remarque);
    }
    public Soignant getSoignantParIndex(int row)  throws DonneePermanenteErreur,ErreurrNull
    {
       return daoSoignant.readTousLesSoignants().get(row);
    }
    public int getNbSoignants()throws DonneePermanenteErreur,ErreurrNull
    {
        return daoSoignant.readTousLesSoignants().size();
    }
}

