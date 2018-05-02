package DAO;

import Model.ErreurInsertCareGiver;
import Model.CareGiver;

public interface DAOCareGiver {
    void create(CareGiver careGiver)throws ErreurInsertCareGiver;
    CareGiver read(String id);
    void update(CareGiver careGiver);
    void delete(String id);
}