package DAO;

import DataAccess.ErreurInsertCareGiver;
import DataAccess.SingletonDB;
import Model.CareGiver;

import java.sql.Connection;

public interface DAOCareGiver {
    void create(CareGiver careGiver)throws ErreurInsertCareGiver;
    Object read(String id);
    void update(CareGiver careGiver);
    void delete(String id);
}