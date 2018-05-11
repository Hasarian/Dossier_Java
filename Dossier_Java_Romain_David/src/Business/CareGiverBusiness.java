package Business;

import DataAccess.DAO.DAOCareGiver;
import DataAccess.CaraGiverDataAccess;
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

    public CareGiverBusiness() throws BDConnexionError{setDaoCareGiver();}


    public void setDaoCareGiver() throws BDConnexionError {
        this.daoCareGiver = new CaraGiverDataAccess();
    }

    public void setCareGiverData(CareGiver careGiver) throws ErreurInsertCareGiver {
        System.out.println("le busi ...");
        daoCareGiver.create(careGiver);
    }

    public void setCurrentUser(CareGiver currentUser) {
        this.currentUser = currentUser;
    }

    public CareGiver getCurrentUser(String id) throws BDConnexionError, InexistantCareGiver, ErrorNull
    {
        setCurrentUser(daoCareGiver.read(id));
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
    public CareGiver getUserByMail(String mail)
    {
            if(isKnown(mail))
            {
                for (CareGiver user:otherUsers)
                {
                    if( user.getMail().equals(mail)) return user;
                }
                return currentUser;
            }
            return new CareGiver(mail);
    }
    public boolean isKnown(String mail)
    {

        for (CareGiver user:otherUsers
             )
        {
           if( user.getMail().equals(mail)) return true;
        }
        return currentUser.getMail().equals(mail);
    }

}

