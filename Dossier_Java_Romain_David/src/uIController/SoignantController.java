package uIController;

import Business.SoignantBusiness;
import Model.Soignant;
import Model.Localite;
import Model.SoinEffectue;
import erreurs.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class SoignantController
{
    private SoignantBusiness business;

    public SoignantController() throws BDConnexionErreur
    {
        business= SoignantBusiness.otebnirSoignantBusiness();
    }

    public void setSoignantConnexion(String login) throws BDConnexionErreur, SoignantInexistant, ErreurrNull
    {
            business.setUtilisateurCourant(business.getUtilisateurCourantDansLaBD(login));
    }
    public void setSoignantData(Soignant soignant) throws ErreurInsertionSoignant {
        business.setSoignantData(soignant);
    }
    public String getNomDeFamilleUtilisateurCourant()
    {
        return business.getNomFamilleUtilisateurCourant();
    }
    public String getPrenomUtilisateurCourant()
    {
        return business.getPrenomUtilisateurCourant();
    }
    public String getMailUtilisateurCourant()
    {
        return business.getMailUtilisateurCourant();
    }
    public boolean estVeterinaire(){return business.estVeterinaire();}
    public String getRueUtilisateurCourant(){return business.getUtilisateurCourant().getRue();}
    public String getNumeroMaisonUtilisateurCourant(){return business.getUtilisateurCourant().getNumMaison().toString();}
    public String getNumTelUtilisateurCourant(){return business.getUtilisateurCourant().getNumTel().toString();}
    public String getRemarqueUtilisateurCourant(){return business.getUtilisateurCourant().getRemarque();}
    public boolean estVolontaire(){return business.getUtilisateurCourant().getEstBenevole();}
    public Localite getLocalite(){return business.getUtilisateurCourant().getLocalite();}
    public GregorianCalendar getDateEmbauche(){return business.getUtilisateurCourant().getDateEmbauche();}
    public Soignant getSoignant()
    {
        return business.getUtilisateurCourant();
    }

    public ArrayList<ArrayList<String>> soinsFaitsPar(String mail) throws BDConnexionErreur, ErreurrNull, SoignantInexistant,MauvaiseTailleString
    {
        ArrayList<SoinEffectue> soinsEffectués=business.getSoinsEffectues(mail);
        ArrayList<ArrayList<String>> data=new ArrayList<ArrayList<String>>();
        if(soinsEffectués!=null)
        for (SoinEffectue soin:soinsEffectués) {
            /*"animal id",
                    "date / heure du soin rendu",
                    "soin médical: heure prévue",
                    "remarque sur le soin"*/
            ArrayList<String>row=new ArrayList<String>();
            row.add(soin.getFicheSoin().getFicheSoin().getId().toString());
            String date=new String();
            date+= soin.getDateHeure().get(Calendar.DAY_OF_MONTH)+"/";
            date+= soin.getDateHeure().get(Calendar.MONTH)+"/";
            date+= soin.getDateHeure().get(Calendar.YEAR)+"  ";
            date+= soin.getDateHeure().get(Calendar.HOUR)+":";
            date+= soin.getDateHeure().get(Calendar.MINUTE);
            row.add(date);
            date= new String();
            if(soin.getFicheSoin().getDate()!=null) {
                date += soin.getSoinMedical().getDate().get(Calendar.DAY_OF_MONTH) + "/";
                date += soin.getSoinMedical().getDate().get(Calendar.MONTH)  + "/";
                date += soin.getSoinMedical().getDate().get(Calendar.YEAR) + "  ";
                date += soin.getSoinMedical().getHeure().get(Calendar.HOUR) + ":";
                date += soin.getSoinMedical().getHeure().get(Calendar.MINUTE);
            }else{
                date="nom spécifié";
            }
            row.add(date);
            row.add(soin.getFicheSoin().getRemarque());
            data.add(row);
        }
        else
        {
            ArrayList<String> nullrow=new ArrayList<String>();
            for(int i=0;i<5;i++)
            nullrow.add("pas de soin effectué");
            data.add(nullrow);
        }
        return data;
    }
    public String getTelAutreUtilisateur(String mail) throws SoignantInexistant, BDConnexionErreur, ErreurrNull
    {
        return business.getUtilisateurParMail(mail).getNumTel().toString();
    }
    public String getNomDeFamilleAutreUtilisateur(String mail) throws SoignantInexistant, ErreurrNull, BDConnexionErreur
    {
        return business.getUtilisateurParMail(mail).getPrenom()+" "+business.getUtilisateurParMail(mail).getNomDeFamille();
    }
    public String AutreUtilisateurEstBenevole(String mail) throws SoignantInexistant, ErreurrNull, BDConnexionErreur
    {
        return(business.getUtilisateurParMail(mail).getEstBenevole())?"(bénévole)":"(salarié)";
    }
    public String getCodePostalAutreUtilisateur(String mail) throws SoignantInexistant, ErreurrNull, BDConnexionErreur
    {
        return business.getUtilisateurParMail(mail).getLocalite().getCodePostal()+" "+ business.getUtilisateurParMail(mail).getLocalite().getLibelle();
    }
    public String[] getTousLesUtilisateurs()throws BDConnexionErreur
    {
        ArrayList<String> utilisateurs=business.getTousLesUtilisateurs();
        String[] data=new String[utilisateurs.size()];
        for(int i=0;i<data.length;i++)data[i]=utilisateurs.get(i);
        return data;
    }
    public void updateUtilisateurCourant(String nameTexte, String lastNameTexte, Integer tel, Integer houseNumber, String noteTexte, String streetTexte)
    throws ErreurrNull, BDConnexionErreur
    {
        business.updateUtilisateurCourantDansLaBD(nameTexte,lastNameTexte,tel,houseNumber,noteTexte,streetTexte);
    }
    public void supprimerSoignant(String mail) throws SuppressionUtilisateurCourant, SoignantInexistant, ErreurrNull, BDConnexionErreur
    {
        business.supprimerUtilisateur(mail);
    }
    public void supprimerUtilsiateurActuel() throws BDConnexionErreur
    {
        business.supprimerUtilisateurCourant();
    }

}
