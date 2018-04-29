package Business;

import DAO.DAOCareGiver;
import DataAccess.DBDataAccess;
import Model.CareGiver;

public class CareGiverBusiness {
    CareGiver careGiver;
    DAOCareGiver daoCareGiver;
    public CareGiverBusiness(CareGiver careGiver){
        setDaoCareGiver();
    }

    public void setDaoCareGiver() {
        this.daoCareGiver = new DBDataAccess();
    }

    public void setCareGiver(CareGiver careGiver) {
        if( checkCareGiverAttribut(careGiver)){
            daoCareGiver.create(careGiver);
        }
       // else
            //faire exeption
    }

    private static boolean checkCareGiverAttribut(CareGiver careGiver){
        int indice = 0;
        while (indice < careGiver.getAttributObligatoir().size() && careGiver.getAttributObligatoir().get(indice) != null)
            indice++;
        return indice == careGiver.getAttributObligatoir().size();
    }

}

