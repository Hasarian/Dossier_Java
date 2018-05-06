package uIController;

import Business.CareGiverBusiness;
import Model.CareGiver;
import erreurs.BDConnexionError;
import erreurs.ErrorNull;
import erreurs.InexistantCareGiver;

import java.sql.SQLException;

public class CareGiverController
{
    private CareGiverBusiness business;
    private CareGiver careGiver;

    public CareGiverController() throws BDConnexionError
    {
        business=new CareGiverBusiness();
    }

    public void setCareGiverConnection(String login) throws BDConnexionError, InexistantCareGiver, ErrorNull
    {
            business.getCareGiver(login);
    }

    public CareGiver getCareGiver(String login) throws BDConnexionError, InexistantCareGiver, ErrorNull{
        return business.getCareGiver(login);
    }
}
