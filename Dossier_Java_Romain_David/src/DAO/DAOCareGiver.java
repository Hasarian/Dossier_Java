package DAO;

import erreurs.BDConnexionError;
import erreurs.ErreurInsertCareGiver;
import Model.CareGiver;
import erreurs.ErrorNull;
import erreurs.InexistantCareGiver;

import java.sql.SQLException;

public interface DAOCareGiver {
    void create(CareGiver careGiver)throws ErreurInsertCareGiver;
    CareGiver read(String id) throws InexistantCareGiver, ErrorNull;
    CareGiver read(String id) throws InexistantCareGiver, BDConnexionError;
    void update(CareGiver careGiver);
    void delete(String id);
}