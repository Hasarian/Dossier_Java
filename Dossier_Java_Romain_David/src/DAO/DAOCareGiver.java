package DAO;

import DataAccess.DBConnection;
import DataAccess.SingletonDB;
import Model.CareGiver;

public interface DAOCareGiver {
    DBConnection singletonConnection = SingletonDB.getInstance();
    void create(CareGiver careGiver);
    Object read(String id);
    void update(CareGiver careGiver);
    void delete(String id);
}