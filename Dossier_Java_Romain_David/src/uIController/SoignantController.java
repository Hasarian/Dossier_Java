package uIController;

import business.AnimalBusiness;
import business.ListeAnimalBusiness;
import business.SoignantBusiness;
import model.Soignant;
import model.Localite;
import model.SoinEffectue;
import erreurs.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class SoignantController
{
    private SoignantBusiness business;

    public SoignantController() throws DonneePermanenteErreur
    {
        business= new SoignantBusiness();
    }

    public void setSoignantConnexion(String login) throws DonneePermanenteErreur, SoignantInexistant, ErreurrNull
    {
            business.setUtilisateurCourant(login);
    }
    public void setSoignantData(Soignant soignant) throws ErreurInsertionSoignant {
        business.setSoignantData(soignant);
    }
    public String getNomDeFamilleUtilisateurCourant() throws DonneePermanenteErreur, SoignantInexistant, ErreurrNull
    {
        return business.getNomFamilleUtilisateurCourant();
    }
    public String getPrenomUtilisateurCourant() throws DonneePermanenteErreur, SoignantInexistant, ErreurrNull
    {
        return business.getPrenomUtilisateurCourant();
    }
    public String getMailUtilisateurCourant() throws DonneePermanenteErreur, SoignantInexistant, ErreurrNull
    {
        return business.getMailUtilisateurCourant();
    }
    public boolean estVeterinaire() throws DonneePermanenteErreur, SoignantInexistant, ErreurrNull{return business.estVeterinaire();}
    public String getRueUtilisateurCourant() throws DonneePermanenteErreur, SoignantInexistant, ErreurrNull{return business.getUtilisateurCourant().getRue();}
    public String getNumeroMaisonUtilisateurCourant() throws DonneePermanenteErreur, SoignantInexistant, ErreurrNull{return business.getUtilisateurCourant().getNumMaison().toString();}
    public String getNumTelUtilisateurCourant() throws DonneePermanenteErreur, SoignantInexistant, ErreurrNull{return business.getUtilisateurCourant().getNumTel().toString();}
    public String getRemarqueUtilisateurCourant() throws DonneePermanenteErreur, SoignantInexistant, ErreurrNull{return business.getUtilisateurCourant().getRemarque();}
    public boolean estVolontaire() throws DonneePermanenteErreur, SoignantInexistant, ErreurrNull{return business.getUtilisateurCourant().getEstBenevole();}
    public Localite getLocalite() throws DonneePermanenteErreur, SoignantInexistant, ErreurrNull{return business.getUtilisateurCourant().getLocalite();}
    public GregorianCalendar getDateEmbauche() throws DonneePermanenteErreur, SoignantInexistant, ErreurrNull{return business.getUtilisateurCourant().getDateEmbauche();}
    public Soignant getSoignant ()throws DonneePermanenteErreur, SoignantInexistant, ErreurrNull{
        return business.getUtilisateurCourant();
    }

    public ArrayList<ArrayList<Object>> soinsFaitsPar(String mail) throws DonneePermanenteErreur, ErreurrNull, SoignantInexistant,MauvaiseTailleString
    {
        ArrayList<SoinEffectue> soinsEffectués=business.getSoinsEffectues(mail);
        ArrayList<ArrayList<Object>> data=new ArrayList<ArrayList<Object>>();
        if(soinsEffectués!=null)
        for (SoinEffectue soin:soinsEffectués) {
            /*"animal id",
                    "date / heure du soin rendu",
                    "soin médical: heure prévue",
                    "remarque sur le soin"*/
            ArrayList<Object>row=new ArrayList<Object>();
            row.add(soin.getFicheSoin().getId());
            row.add(soin.getSoinMedical().getMailVeto());
            String date=new String();
            date+= soin.getDateHeure().get(Calendar.DAY_OF_MONTH)+"/";
            date+= (soin.getDateHeure().get(Calendar.MONTH)+1)+"/";
            date+= soin.getDateHeure().get(Calendar.YEAR)+"  ";
            date+= soin.getDateHeure().get(Calendar.HOUR)+":";
            date+= soin.getDateHeure().get(Calendar.MINUTE);
            row.add(date);
            date= new String();
            if(soin.getFicheSoin().getDate()!=null) {
                date += soin.getSoinMedical().getDate().get(Calendar.DAY_OF_MONTH) + "/";
                date += (soin.getSoinMedical().getDate().get(Calendar.MONTH)+1)  + "/";
                date += soin.getSoinMedical().getDate().get(Calendar.YEAR) + "  ";
                date += soin.getSoinMedical().getHeure().get(Calendar.HOUR) + ":";
                date += soin.getSoinMedical().getHeure().get(Calendar.MINUTE);
            }else{
                date="nom spécifié";
            }
            row.add(date);
            row.add(soin.getRemarque() == null ?"Pas de remarque": soin.getRemarque());
            row.add(soin.getSoinMedical().getRemarque());
            data.add(row);
        }
        else
        {
            ArrayList<Object> nullrow=new ArrayList<Object>();
            for(int i=0;i<5;i++)
            nullrow.add("pas de soin effectué");
            data.add(nullrow);
        }
        return data;
    }
    public String getTelAutreUtilisateur(String mail) throws SoignantInexistant, DonneePermanenteErreur, ErreurrNull
    {
        return business.getUtilisateurParMail(mail).getNumTel().toString();
    }
    public String getNomDeFamilleAutreUtilisateur(String mail) throws SoignantInexistant, ErreurrNull, DonneePermanenteErreur
    {
        return business.getUtilisateurParMail(mail).getPrenom()+" "+business.getUtilisateurParMail(mail).getNomDeFamille();
    }
    public String AutreUtilisateurEstBenevole(String mail) throws SoignantInexistant, ErreurrNull, DonneePermanenteErreur
    {
        return(business.getUtilisateurParMail(mail).getEstBenevole())?"(bénévole)":"(salarié)";
    }
    public String getCodePostalAutreUtilisateur(String mail) throws SoignantInexistant, ErreurrNull, DonneePermanenteErreur
    {
        return business.getUtilisateurParMail(mail).getLocalite().getCodePostal()+" "+ business.getUtilisateurParMail(mail).getLocalite().getLibelle();
    }
    public String[] getTousLesUtilisateurs()throws DonneePermanenteErreur
    {
        ArrayList<String> utilisateurs=business.getTousLesMails();
        String[] data=new String[utilisateurs.size()];
        for(int i=0;i<data.length;i++)data[i]=utilisateurs.get(i);
        return data;
    }
    public void updateUtilisateur(String ancienMail,String mail,String nameTexte, String lastNameTexte, Integer tel,Boolean estVolontaire ,Integer houseNumber, String noteTexte, String streetTexte,Localite localite,GregorianCalendar dateEmbauche)
    throws ErreurrNull, DonneePermanenteErreur,SoignantInexistant
    {
        business.updateUtilisateurDansLaBD(ancienMail,mail,nameTexte,lastNameTexte,tel,estVolontaire,houseNumber,noteTexte,streetTexte,localite,dateEmbauche);
    }
    public void supprimerSoignant(String mail) throws SuppressionUtilisateurCourant, DonneePermanenteErreur
    {
        business.supprimerUtilisateur(mail);
    }
    public void supprimerUtilsiateurActuel() throws DonneePermanenteErreur
    {
        business.supprimerUtilisateurCourant();
    }
    public Object obtenirInfo(int row, int column) throws DonneePermanenteErreur, ErreurrNull
    {
        Soignant soignant;
        soignant=business.getSoignantParIndex(row);
        switch(column)
        {
            case 0: return soignant.getPrenom()+" "+soignant.getNomDeFamille();
            case 1: return soignant.getMail();
            case 2: return soignant.getNumTel();
            case 3: String date=new String();
            date+=soignant.getDateEmbauche().get(Calendar.DAY_OF_MONTH)+"/";
            date+=(soignant.getDateEmbauche().get(Calendar.MONTH)+1)+"/";
            date+=soignant.getDateEmbauche().get(Calendar.YEAR);
                return date;
            case 4: return soignant.getEstBenevole();
            case 5: return soignant.getRemarque();
            default: return "données inexistantes";
        }
    }
    public int nbSoignants()throws DonneePermanenteErreur, ErreurrNull
    {
        return business.getNbSoignants();
    }
    public Soignant getSoignantParIndex(int index)throws DonneePermanenteErreur, ErreurrNull
    {
        return business.getSoignantParIndex(index);
    }
}
