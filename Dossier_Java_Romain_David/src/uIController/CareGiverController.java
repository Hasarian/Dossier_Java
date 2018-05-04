package uIController;

import Business.CareGiverBusiness;
import erreurs.BDConnexionError;
import erreurs.ErrorNull;
import erreurs.InexistantCareGiver;

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
}
