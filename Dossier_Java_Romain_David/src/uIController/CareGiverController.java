package uIController;

import Business.CareGiverBusiness;
import Model.CareGiver;
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

    public CareGiver getCareGiver()
    {
        return business.getCurrentUser();
    }

    public ArrayList<ArrayList<String>> careDoneBy(String mail) throws BDConnexionError
    {
        ArrayList<SoinEffectue> soinsEffectués=business.getSoinsEffectues(mail);
        ArrayList<ArrayList<String>> data=new ArrayList<ArrayList<String>>();
        for (SoinEffectue soin:soinsEffectués) {
            /*"animal id",
                    "date / heure du soin rendu",
                    "soin médical: heure prévue",
                    "remarque sur le soin"*/
            ArrayList<String>row=new ArrayList<String>();
            row.add(soin.getFicheSoin().getFicheSoin().getId());
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
        return data;
    }
    public String getOtherTel(String mail)
    {
        return business.getUserByMail(mail).getNumTel().toString();
    }
    public String getOtherName(String mail)
    {
        return business.getUserByMail(mail).getFirstName()+" "+business.getUserByMail(mail).getName();
    }
    public String estBenevole(String mail)
    {
        return(business.getUserByMail(mail).getEstBenevole())?"(bénévole)":"(salarié)";
    }
    public String codePostal(String mail)
    {
        return business.getUserByMail(mail).getLocalite().getCodePostal()+" "+ business.getUserByMail(mail).getLocalite().getLibelle();
    }
}
