package DAO;

import erreurs.ErreurInsertCareGiver;
import Model.CareGiver;
import erreurs.InexistantCareGiver;

public interface DAOCareGiver {
    void create(CareGiver careGiver)throws ErreurInsertCareGiver;
    CareGiver read(String id) throws InexistantCareGiver;
    void update(CareGiver careGiver);
    void delete(String id);
}