package controle;

import business.SoignantBusiness;
import erreurs.Erreur;
import erreurs.erreurApplication.SuppressionUtilisateurCourant;
import erreurs.erreursExternes.DonneePermanenteErreur;
import modèle.Soignant;
import modèle.Localite;
import modèle.SoinEffectue;
import modèle.Veterinaire;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ControleSoignant
{
    private SoignantBusiness business;
    private static String mailUtilisateurCourant;

    public ControleSoignant() throws DonneePermanenteErreur
    {
        business= new SoignantBusiness();
    }

    public void setSoignantConnexion(String login) throws Erreur
    {
            mailUtilisateurCourant= business.getUtilisateurParMail(login).getMail();
    }
    public void setSoignantData(Soignant soignant) throws Erreur {
        business.setSoignantData(soignant);
    }
    public String getNomDeFamilleUtilisateurCourant() throws Erreur
    {
        return business.getUtilisateurParMail(mailUtilisateurCourant).getNomDeFamille();
    }
    public String getPrenomUtilisateurCourant() throws Erreur
    {
        return business.getUtilisateurParMail(mailUtilisateurCourant).getPrenom();
    }
    public String getMailUtilisateurCourant()
    {
        return mailUtilisateurCourant;
    }
    public boolean estVeterinaire() throws Erreur{return   business.getUtilisateurParMail(mailUtilisateurCourant).getClass()== Veterinaire.class;}
    public String getRueUtilisateurCourant() throws Erreur{return  business.getUtilisateurParMail(mailUtilisateurCourant).getRue();}
    public Integer getNumeroMaisonUtilisateurCourant() throws Erreur{return  business.getUtilisateurParMail(mailUtilisateurCourant).getNumMaison();}
    public Integer getNumTelUtilisateurCourant() throws Erreur{return  business.getUtilisateurParMail(mailUtilisateurCourant).getNumTel();}
    public String getRemarqueUtilisateurCourant() throws Erreur{return  business.getUtilisateurParMail(mailUtilisateurCourant).getRemarque();}
    public boolean estVolontaire() throws Erreur{return  business.getUtilisateurParMail(mailUtilisateurCourant).getEstBenevole();}
    public Localite getLocalite() throws Erreur{return  business.getUtilisateurParMail(mailUtilisateurCourant).getLocalite();}
    public GregorianCalendar getDateEmbauche() throws Erreur{return  business.getUtilisateurParMail(mailUtilisateurCourant).getDateEmbauche();}
    public Soignant getSoignant ()throws Erreur{
        return business.getUtilisateurParMail(mailUtilisateurCourant);
    }

    public ArrayList<ArrayList<Object>> soinsFaitsPar(String mail) throws Erreur
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
    public Integer getTelAutreUtilisateur(String mail) throws Erreur
    {
        return business.getUtilisateurParMail(mail).getNumTel();
    }
    public String getNomDeFamilleAutreUtilisateur(String mail) throws Erreur
    {
        return business.getUtilisateurParMail(mail).getPrenom()+" "+business.getUtilisateurParMail(mail).getNomDeFamille();
    }
    public String AutreUtilisateurEstBenevole(String mail) throws Erreur
    {
        return(business.getUtilisateurParMail(mail).getEstBenevole())?"(bénévole)":"(salarié)";
    }
    public String getCodePostalAutreUtilisateur(String mail) throws Erreur
    {
        return business.getUtilisateurParMail(mail).getLocalite().getCodePostal()+" "+ business.getUtilisateurParMail(mail).getLocalite().getLibelle();
    }
    public String[] getTousLesUtilisateurs()throws Erreur
    {
        ArrayList<String> utilisateurs=business.getTousLesMails();
        String[] data=new String[utilisateurs.size()];
        for(int i=0;i<data.length;i++)data[i]=utilisateurs.get(i);
        return data;
    }
    public void updateUtilisateur(String ancienMail,String mail,String nameTexte, String lastNameTexte, Integer tel,Boolean estVolontaire ,Integer houseNumber, String noteTexte, String streetTexte,Localite localite,GregorianCalendar dateEmbauche)
    throws Erreur
    {
        business.updateUtilisateurDansLaBD(ancienMail,mail,nameTexte,lastNameTexte,tel,estVolontaire,houseNumber,noteTexte,streetTexte,localite,dateEmbauche);
    }
    public void supprimerSoignant(String mail) throws Erreur
    {
        if (mail.equals(mailUtilisateurCourant)) throw new SuppressionUtilisateurCourant();
        business.supprimerUtilisateur(mail);
    }
    public void supprimerUtilsiateurActuel() throws Erreur
    {
        business.supprimerUtilisateur(mailUtilisateurCourant);
    }
    public Object obtenirInfo(int row, int column) throws Erreur
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
    public int nbSoignants()throws Erreur
    {
        return business.getNbSoignants();
    }
    public Soignant getSoignantParIndex(int index)throws Erreur
    {
        return business.getSoignantParIndex(index);
    }
}
