package uIController;

import Business.CareGiverBusiness;
import Model.CareGiver;
import Model.Localite;
import Model.SoinEffectue;
import erreurs.*;

import java.util.ArrayList;
import java.util.Calendar;

public class CareGiverController
{
    private CareGiverBusiness business;

    public CareGiverController() throws BDConnexionError
    {
        business=CareGiverBusiness.otebnirCareGiverBusiness();
    }

    public void setCareGiverConnection(String login) throws BDConnexionError, InexistantCareGiver, ErrorNull
    {
            business.setCurrentUser(business.getCurrentUserInDB(login));
    }
    public void setCareGiverData(CareGiver careGiver) throws ErreurInsertCareGiver {
        business.setCareGiverData(careGiver);
    }
    public String getUserName()
    {
        return business.getUserName();
    }
    public String getUSerFirstName()
    {
        return business.getUserFirstName();
    }
    public String getUserEmail()
    {
        return business.getUserEmail();
    }
    public boolean isVeto(){return business.isVeto();}
    public String getUserStreet(){return business.getCurrentUser().getStreet();}
    public String getUserHouseNumber(){return business.getCurrentUser().getNumMaison().toString();}
    public String getTelNumer(){return business.getCurrentUser().getNumTel().toString();}
    public String getUserNote(){return business.getCurrentUser().getRemarque();}
    public boolean isVolontaire(){return business.getCurrentUser().getEstBenevole();}
    public Localite getLocalite(){return business.getCurrentUser().getLocalite();}
    public CareGiver getCareGiver()
    {
        return business.getCurrentUser();
    }

    public ArrayList<ArrayList<String>> careDoneBy(String mail) throws BDConnexionError,ErrorNull,InexistantCareGiver
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
                date += soin.getFicheSoin().getDate().get(Calendar.DAY_OF_MONTH) + "/";
                date += soin.getFicheSoin().getDate().get(Calendar.MONTH) + "/";
                date += soin.getFicheSoin().getDate().get(Calendar.YEAR) + "  ";
                date += soin.getFicheSoin().getDate().get(Calendar.HOUR) + ":";
                date += soin.getFicheSoin().getDate().get(Calendar.MINUTE);
            }else{
                date="nom spécifié";
            }
            row.add(date);
            row.add(soin.getFicheSoin().getRemarque());
            row.add(soin.getRemarque());
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
    public String getOtherTel(String mail) throws InexistantCareGiver,BDConnexionError,ErrorNull
    {
        return business.getUserByMail(mail).getNumTel().toString();
    }
    public String getOtherName(String mail) throws InexistantCareGiver,ErrorNull,BDConnexionError
    {
        return business.getUserByMail(mail).getFirstName()+" "+business.getUserByMail(mail).getName();
    }
    public String estBenevole(String mail) throws InexistantCareGiver,ErrorNull,BDConnexionError
    {
        return(business.getUserByMail(mail).getEstBenevole())?"(bénévole)":"(salarié)";
    }
    public String codePostal(String mail) throws InexistantCareGiver,ErrorNull,BDConnexionError
    {
        return business.getUserByMail(mail).getLocalite().getCodePostal()+" "+ business.getUserByMail(mail).getLocalite().getLibelle();
    }
    public String[] getallUsers()throws  BDConnexionError
    {
        ArrayList<String> allusers=business.getAllUsers();
        String[] data=new String[allusers.size()];
        for(int i=0;i<data.length;i++)data[i]=allusers.get(i);
        return data;
    }
    public void updateCurrentUser(String nameTexte,String lastNameTexte, Integer tel,Integer houseNumber, String noteTexte, String streetTexte)
    throws ErrorNull,BDConnexionError
    {
        business.updateCurrentUserInBD(nameTexte,lastNameTexte,tel,houseNumber,noteTexte,streetTexte);
    }

}
