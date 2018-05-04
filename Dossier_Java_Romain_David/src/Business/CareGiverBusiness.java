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

            if (checkCareGiverAttribut(careGiver)) {
                daoCareGiver.create(careGiver);
            }
    }

    public void setCareGiver(CareGiver careGiver) {
        this.careGiver = careGiver;
    }

    public CareGiver getCareGiver(String id) throws BDConnexionError, InexistantCareGiver, ErrorNull
    {
        setCareGiver(daoCareGiver.read(id));
            return careGiver;
    }

    private static boolean checkCareGiverAttribut(CareGiver careGiver){
        int indice = 0;
        while (indice < careGiver.getAttributObligatoir().size() && careGiver.getAttributObligatoir().get(indice) != null)
            indice++;
        return indice == careGiver.getAttributObligatoir().size();
    }

}

