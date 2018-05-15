package Business;

import DataAccess.CareByCareGiver;
import DataAccess.DAO.DAOCareGiver;
import DataAccess.CaraGiverDataAccess;
import Model.SoinEffectue;
import Model.Veterinaire;
import erreurs.BDConnexionError;
import erreurs.ErreurInsertCareGiver;
import Model.CareGiver;
import erreurs.ErrorNull;
import erreurs.InexistantCareGiver;

import java.util.ArrayList;

public class CareGiverBusiness {
    private CareGiver currentUser;
    private DAOCareGiver daoCareGiver;
    private ArrayList<CareGiver> otherUsers;
    private static CareGiverBusiness instance;

    private CareGiverBusiness() throws BDConnexionError{
        setDaoCareGiver();
        otherUsers=new ArrayList<CareGiver>();
    }
    public static CareGiverBusiness otebnirCareGiverBusiness() throws BDConnexionError
    {
        if(instance==null)
        {
            instance= new CareGiverBusiness();
        }
            return instance;
    }

    public void setDaoCareGiver() throws BDConnexionError {
        this.daoCareGiver = new CaraGiverDataAccess();
    }

    public void setCareGiverData(CareGiver careGiver) throws ErreurInsertCareGiver {
        daoCareGiver.create(careGiver);
    }

    public void setCurrentUser(CareGiver currentUser) {
        this.currentUser = currentUser;
    }

    public CareGiver getCurrentUserInDB(String id) throws BDConnexionError, InexistantCareGiver, ErrorNull
    {
        setCurrentUser(daoCareGiver.read(id));
        return currentUser;
    }

    public CareGiver getCurrentUser() {
        return currentUser;
    }

    public String getUserName()
    {
        return currentUser.getName();
    }
    public String getUserFirstName()
    {
        return currentUser.getFirstName();
    }
    public String getUserEmail()
    {
        return currentUser.getMail();
    }
    public boolean isVeto()
    {
        return currentUser instanceof Veterinaire ;
    }
    public CareGiver getUserByMail(String mail) throws InexistantCareGiver,BDConnexionError,ErrorNull
    {
        if(mail==null)return null;
        if(isKnown(mail))
        {
            for (CareGiver user : otherUsers) {
                if (user.getMail().equals(mail)) return user;
            }
            return currentUser;
        }
        else
            {
                CareGiver user= daoCareGiver.read(mail);
                otherUsers.add(user);
                return user;
            }
    }
    public void getUserByMailFromBD(String mail) throws InexistantCareGiver,BDConnexionError,ErrorNull
    {
        CareGiver user=daoCareGiver.read(mail);
        if(user.getMail().compareTo(currentUser.getMail())==0)currentUser=user;
        else
        {
            if(isKnown(mail)) otherUsers.remove(getUserByMail(mail));
            otherUsers.add(user);
        }
    }

    public boolean isKnown(String mail)
    {
            for (CareGiver user : otherUsers) {
                if (user.getMail().equals(mail)) return true; }
        return currentUser.getMail().equals(mail);
    }


    public ArrayList<SoinEffectue> getSoinsEffectues(String mail) throws BDConnexionError,ErrorNull,InexistantCareGiver
    {
        CareByCareGiver acces=new CareByCareGiver();
        return acces.searchHistory(mail);
    }

    public ArrayList<String> getAllUsers() throws BDConnexionError {
        return daoCareGiver.readallMails();
    }
}

