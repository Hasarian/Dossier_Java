package uIController;

import Business.CareGiverBusiness;
import Model.CareGiver;
import erreurs.*;

import java.sql.SQLException;

public class CareGiverController
{
    private CareGiverBusiness business;

    public CareGiverController() throws BDConnexionError
    {
        business=new CareGiverBusiness();
    }

    public void setCareGiverConnection(String login) throws BDConnexionError, InexistantCareGiver, ErrorNull
    {
            business.setCareGiver(business.getCareGiver(login));
    }
    public void setCareGiverData(CareGiver careGiver) throws ErreurInsertCareGiver {
        business.setCareGiverData(careGiver);
    }
}
