package Business;

import DAO.DAOCareGiver;
import DataAccess.DBDataAccess;
import Model.ErreurInsertCareGiver;
import Model.CareGiver;

public class CareGiverBusiness {
    CareGiver careGiver;
    DAOCareGiver daoCareGiver;

    public CareGiverBusiness(){setDaoCareGiver();}
    public CareGiverBusiness(CareGiver careGiver){ this(); }

    public void setDaoCareGiver() {
        this.daoCareGiver = new DBDataAccess();
    }

    public void setCareGiverData(CareGiver careGiver) throws ErreurInsertCareGiver {

            if (checkCareGiverAttribut(careGiver)) {
                try {
                daoCareGiver.create(careGiver);
                }
                catch (ErreurInsertCareGiver e){
                    throw new ErreurInsertCareGiver(e.getMessage());
                }
            }
    }

    public void setCareGiver(CareGiver careGiver) {
        this.careGiver = careGiver;
    }

    public CareGiver getCareGiver(String id) {
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

