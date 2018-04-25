package DataAccess;


import DAO.DAOCareGiver;
import Model.CareGiver;

public class DBDataAccess implements DAOCareGiver {
    private DBConnection singletonDBAcces;
    DBDataAccess(){
        singletonDBAcces = SingletonDB.getInstance();
    }


    public void create(CareGiver careGiver) {

    }



    public void delete(String id) {

    }


    public void update(CareGiver careGiver) {

    }

    public CareGiver read(String id) {
        CareGiver careGiver = null;
        return careGiver;
    }
}
