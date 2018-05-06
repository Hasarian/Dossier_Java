package Business;

import DAO.DAOCareGiver;
import DataAccess.CaraGiverDataAccess;
import erreurs.BDConnexionError;
import erreurs.ErreurInsertCareGiver;
import Model.CareGiver;
import erreurs.ErrorNull;
import erreurs.InexistantCareGiver;

import java.sql.SQLException;

public class CareGiverBusiness {
    CareGiver careGiver;
    DAOCareGiver daoCareGiver;

    public CareGiverBusiness() throws BDConnexionError{setDaoCareGiver();}
    public CareGiverBusiness(CareGiver careGiver)throws BDConnexionError{ this(); }

    public void setDaoCareGiver() throws BDConnexionError {
        this.daoCareGiver = new CaraGiverDataAccess();
    }

    public void setCareGiverData(CareGiver careGiver) throws ErreurInsertCareGiver {

                daoCareGiver.create(careGiver);
    }

    public void setCareGiver(CareGiver careGiver) {
        this.careGiver = careGiver;
    }

    public CareGiver getCareGiver(String id) throws BDConnexionError, InexistantCareGiver, ErrorNull
    {
        System.out.println("4");
        setCareGiver(daoCareGiver.read(id));
        System.out.println("5");
            return careGiver;
    }

}

