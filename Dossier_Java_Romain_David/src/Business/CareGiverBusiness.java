package Business;

import DAO.DAOCareGiver;
import DataAccess.DBDataAccess;
import Model.CareGiver;

public class CareGiverBusiness {
    CareGiver careGiver;
    DAOCareGiver daoCareGiver;
    CareGiverBusiness(CareGiver careGiver){
        setDaoCareGiver();
    }

    public void setDaoCareGiver() {
        this.daoCareGiver = new DBDataAccess();
    }

    public void setCareGiver(CareGiver careGiver) {
        //if()
    }
    private boolean checkAttributNotNull(){
        boolean check = false;
        return check;
    }

}

