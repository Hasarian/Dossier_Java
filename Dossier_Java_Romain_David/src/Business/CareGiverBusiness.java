package Business;

import DataAccess.DAO.DAOCareGiver;
import DataAccess.CaraGiverDataAccess;
import Model.Veterinaire;
import erreurs.BDConnexionError;
import erreurs.ErreurInsertCareGiver;
import Model.CareGiver;
import erreurs.ErrorNull;
import erreurs.InexistantCareGiver;

public class CareGiverBusiness {
    CareGiver careGiver;
    DAOCareGiver daoCareGiver;

    public CareGiverBusiness() throws BDConnexionError{setDaoCareGiver();}


    public void setDaoCareGiver() throws BDConnexionError {
        this.daoCareGiver = new CaraGiverDataAccess();
    }

    public void setCareGiverData(CareGiver careGiver) throws ErreurInsertCareGiver {
        System.out.println("le busi ...");
        daoCareGiver.create(careGiver);
    }

    public void setCareGiver(CareGiver careGiver) {
        this.careGiver = careGiver;
    }

    public CareGiver getCareGiver(String id) throws BDConnexionError, InexistantCareGiver, ErrorNull
    {
        setCareGiver(daoCareGiver.read(id));
            return careGiver;
    }
    public String getUserName()
    {
        return careGiver.getName();
    }
    public String getUserFirstName()
    {
        return careGiver.getFirstName();
    }
    public String getUserEmail()
    {
        return careGiver.getMail();
    }
    public boolean isVeto()
    {
        return careGiver instanceof Veterinaire ;
    }

}

