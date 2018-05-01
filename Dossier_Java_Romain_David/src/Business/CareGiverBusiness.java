package Business;

import DAO.DAOCareGiver;
import DataAccess.DBDataAccess;
import DataAccess.ErreurInsertCareGiver;
import Model.CareGiver;

public class CareGiverBusiness {
    CareGiver careGiver;
    DAOCareGiver daoCareGiver;

    public CareGiverBusiness(){setDaoCareGiver();}
    public CareGiverBusiness(CareGiver careGiver){ this(); }

    public void setDaoCareGiver() {
        this.daoCareGiver = new DBDataAccess();
    }

    public void setCareGiver(CareGiver careGiver) throws ErreurInsertCareGiver {

            if (checkCareGiverAttribut(careGiver)) {
                try {
                daoCareGiver.create(careGiver);
                }
                catch (ErreurInsertCareGiver e){
                    throw new ErreurInsertCareGiver(e.getMessage());
                }
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

