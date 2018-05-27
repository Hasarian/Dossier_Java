package Business;

import DataAccess.DAO.DAORechercheSoinEffectue;
import DataAccess.DAO.DAOSoignant;
import DataAccess.SoignantDataAccess;
import DataAccess.SoinParSoignant;
import Model.*;
import com.mysql.fabric.xmlrpc.base.Array;
import erreurs.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class SoignantBusiness {
    private Soignant utilisateurCourant;
    private DAOSoignant daoSoignant;
    private ArrayList<Soignant> autresUtilisateurs;
    private static SoignantBusiness instance;
    private DAORechercheSoinEffectue accesSoins;

    private SoignantBusiness()
    throws BDConnexionErreur
    {
        setDaoSoignant();
        instance = this;
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

   public Soignant getSoignantansLaBD(String id) throws SoignantInexistant,BDConnexionErreur,ErreurrNull
   {
       return daoSoignant.read(id);
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
    public void dispose(){
        instance = null;
    }

    public ArrayList<SoinEffectue> getSoinsEffectues(String mail) throws BDConnexionErreur, ErreurrNull,SoignantInexistant,MauvaiseTailleString
    {
        return accesSoins.searchHistory(mail);
    }

    public ArrayList<String> getTousLesMails() throws BDConnexionErreur {
        return daoSoignant.readallMails();
    }
    public void updateUtilisateurDansLaBD(String ancienmail, String nouveauMail, String nameTexte, String lastNameTexte, Integer tel,Boolean estVolontaire, Integer houseNumber, String noteTexte, String streetTexte, Localite localite,GregorianCalendar dateEmbauche)
    throws ErreurrNull, BDConnexionErreur,SoignantInexistant
    {
        Soignant soignant;
        if(utilisateurCourant.getMail().equals(ancienmail)) soignant=utilisateurCourant;
        else soignant=getUtilisateurParMail(ancienmail);
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
    public Soignant getSoignantParIndex(int row)
    {
        if(row==0) return utilisateurCourant;
        return autresUtilisateurs.get(row-1);
    }
    public void initTousLesSoignants()
            throws ErreurrNull,BDConnexionErreur,SoignantInexistant
    {
        ArrayList<Soignant> soignants=daoSoignant.readTousLesSoignants();
        for(Soignant soignant:soignants)
        {
            if(utilisateurCourant.getMail().equals(soignant.getMail())) setUtilisateurCourant(soignant);
            else
            {if (estConnu(soignant.getMail())) autresUtilisateurs.remove(getUtilisateurParMail(soignant.getMail()));
            autresUtilisateurs.add(soignant);}
        }
        /*utilisateurCourant=new Soignant();
        for(int i=0;i<15;i++) autresUtilisateurs.add(new Soignant());*/
    }
    public int getNbSoignants()
    {
        return 1+autresUtilisateurs.size();
    }
    public void dispose()
    {
        instance=null;
    }
}

