package DataAccess.DAO;

import erreurs.BDConnexionError;
import erreurs.ErreurInsertCareGiver;
import Model.CareGiver;
import erreurs.ErrorNull;
import erreurs.InexistantCareGiver;

public interface DAOCareGiver {
    void create(CareGiver careGiver)throws ErreurInsertCareGiver;
    CareGiver read(String id) throws InexistantCareGiver, BDConnexionError, ErrorNull;
    void update(CareGiver careGiver);
    void delete(String id);
}